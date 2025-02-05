package com.github.experion.toolpath.items.tool_lambdas;

import com.github.experion.toolpath.ModInit;
import com.github.experion.toolpath.lib.ToolLib;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ToolStaticTrigger {
    private static final Logger LOGGER = LoggerFactory.getLogger(ToolStaticTrigger.class);
    private static final boolean CanLog = true;

    private static void print(String str) {
        if (CanLog) {
            LOGGER.info(str);
        }
    }

    public static void mainTrig(ToolLambdas toolLamb, ItemStack stack, World world, Vec3d vec3d, LivingEntity Player, ToolLib.TriggerType triggerType) {
        toolLamb.lambdas.main_trigger(stack,world,vec3d,Player,triggerType);
    }

    public static void PostMine(ToolLambdas toolLamb, ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        if (toolLamb.enable_postmine) {
            if (toolLamb.lambdas.exists().onPostMine) {
                print("Triggering PostMine");
                toolLamb.lambdas.onPostMine(stack,world,state,pos,miner);
            }else {
                mainTrig(toolLamb,stack, world, ToolLib.BlockPos_To_Vec3d(pos), miner, ToolLib.TriggerType.MINE_BLOCK);
                print("Null PostMine");
            }
        }else {
            print("No PostMine");
        }
    }

    public static void PostHit(ToolLambdas toolLamb, ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (toolLamb.enable_posthit) {
            if (toolLamb.lambdas.exists().onPostHit) {
                print("Triggering PostHit");
                toolLamb.lambdas.onPostHit(stack,target,attacker);
            }else {
                mainTrig(toolLamb, stack, attacker.getWorld(), target.getPos(), attacker, ToolLib.TriggerType.HIT);
                print("Null PostHit");
            }
        }else {
            print("No PostHit");
        }
    }

    public static ActionResult OnUseBlock(ToolLambdas toolLamb, ItemUsageContext context, ActionResult action) {
        if (toolLamb.enable_useblock) {
            if (toolLamb.lambdas.exists().onUseBlock) {
                return toolLamb.lambdas.onUseBlock(context, action);
            }else {
                mainTrig(toolLamb,context.getStack(), context.getWorld(), ToolLib.BlockPos_To_Vec3d(context.getBlockPos()), context.getPlayer(), ToolLib.TriggerType.USE_BLOCK);
            }
        }else {
            print("No OnUseBlock");
        }
        return action;
    }

    public static ActionResult OnUse(ToolLambdas toolLambdas, World world, PlayerEntity user, Hand hand, ActionResult actionResult) {
        if (toolLambdas.enable_use) {
            if (toolLambdas.lambdas.exists().onUse) {
                return toolLambdas.lambdas.onUse(world,user,hand, actionResult);
            }
        }

        return actionResult;
    }

    public static float getMiningSpeed(ToolLambdas toolLambdas, ItemStack stack, BlockState state, float default_float) {
        if (toolLambdas.edit_effeciency) {
            TriggerLambdas lambdas = toolLambdas.lambdas;
            return lambdas.setEfficiency(stack,state,default_float);
        }
        return default_float;
    }

    public static float getDamage(ToolLambdas toolLambdas, Entity target, float baseAttackDamage, DamageSource damageSource, float default_float) {
        if (toolLambdas.edit_damage) {
            TriggerLambdas lambdas = toolLambdas.lambdas;
            return lambdas.setDamage(target,baseAttackDamage,damageSource,default_float);
        }

        return default_float;
    }

    public static void usageTick(ToolLambdas toolLambdas, World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        TriggerLambdas lambdas = toolLambdas.lambdas;

        ModInit.LOGGER.info("USAGE TICK");

        lambdas.usagetick(world,user,stack,remainingUseTicks);
    }


}
