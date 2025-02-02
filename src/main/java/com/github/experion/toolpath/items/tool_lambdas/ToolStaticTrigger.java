package com.github.experion.toolpath.items.tool_lambdas;

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
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ToolStaticTrigger {
    private static final Logger LOGGER = LoggerFactory.getLogger(ToolStaticTrigger.class);
    private static final boolean CanLog = false;

    private static void print(String str) {
        if (CanLog) {
            LOGGER.info(str);
        }
    }

    public static void mainTrig(ToolLambdas toolLamb, ItemStack stack, World world, Vec3d vec3d, LivingEntity Player, ToolLib.TriggerType triggerType) {
        toolLamb.lambdas.main_trigger(stack,world,vec3d,Player,triggerType);
    }

    public static void PostMine(ToolLambdas toolLamb, ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        if (toolLamb.Enable_postmine) {
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
        if (toolLamb.Enable_posthit) {
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
        if (toolLamb.Enable_useblock) {
            if (toolLamb.lambdas.exists().onUseBlock) {
                toolLamb.lambdas.onUseBlock(context, action);
            }else {
                mainTrig(toolLamb,context.getStack(), context.getWorld(), ToolLib.BlockPos_To_Vec3d(context.getBlockPos()), context.getPlayer(), ToolLib.TriggerType.USE_BLOCK);
            }
        }else {
            print("No OnUseBlock");
        }
        return action;
    }

    public static TypedActionResult<ItemStack> OnUse(ToolLambdas toolLamb, World world, PlayerEntity user, Hand hand, TypedActionResult<ItemStack> typedActionResult) {
        TypedActionResult<ItemStack> res = typedActionResult;
        if (toolLamb.Enable_use) {
            if (toolLamb.lambdas.exists().onUse) {
                res = toolLamb.lambdas.onUse(world,user,hand);
            }else {
                mainTrig(toolLamb,user.getStackInHand(hand),world,user.getPos(),user, ToolLib.TriggerType.USE);
            }
        }else {
            print("No OnUse");
        }
        return res;
    }

    public static float getMiningSpeed(ToolLambdas toolLambdas, ItemStack stack, BlockState state, float default_float) {
        if (toolLambdas.Edit_effeciency) {
            TriggerLambdas lambdas = toolLambdas.lambdas;
            return lambdas.setEfficiency(stack,state,default_float);
        }
        return default_float;
    }

    public static float getDamage(ToolLambdas toolLambdas, Entity target, float baseAttackDamage, DamageSource damageSource, float default_float) {
        if (toolLambdas.Edit_damage) {
            TriggerLambdas lambdas = toolLambdas.lambdas;
            return lambdas.setDamage(target,baseAttackDamage,damageSource,default_float);
        }

        return default_float;
    }
}
