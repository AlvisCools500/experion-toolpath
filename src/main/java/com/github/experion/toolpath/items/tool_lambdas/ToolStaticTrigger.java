package com.github.experion.toolpath.items.tool_lambdas;

import com.github.experion.toolpath.lib.ToolLib;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ToolStaticTrigger {
    public static void mainTrig(ToolLambdas toolLamb, ItemStack stack, World world, Vec3d vec3d, LivingEntity Player, ToolLib.TriggerType triggerType) {
        if (toolLamb.mainTrigger != null) {
            toolLamb.mainTrigger.trigger(stack,world,vec3d,Player,triggerType);
        }

    }

    public static void PostMine(ToolLambdas toolLamb, ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        if (toolLamb.Enable_postmine) {
            if (toolLamb.onPostMine != null) {
                toolLamb.onPostMine.trigger(stack,world,state,pos,miner);
            }else {
                mainTrig(toolLamb,stack, world, ToolLib.BlockPos_To_Vec3d(pos), miner, ToolLib.TriggerType.MINE_BLOCK);
            }
        }
    }

    public static void PostHit(ToolLambdas toolLamb, ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (toolLamb.Enable_posthit) {
            if (toolLamb.onPostHit != null) {
                toolLamb.onPostHit.trigger(stack,target,attacker);
            }else {
                mainTrig(toolLamb, stack, attacker.getWorld(), target.getPos(), attacker, ToolLib.TriggerType.HIT);
            }
        }
    }

    public static ActionResult OnUseBlock(ToolLambdas toolLamb, ItemUsageContext context, ActionResult action) {
        if (toolLamb.Enable_useblock) {
            if (toolLamb.onUseBlock != null) {
                toolLamb.onUseBlock.trigger(context, action);
            }else {
                mainTrig(toolLamb,context.getStack(), context.getWorld(), ToolLib.BlockPos_To_Vec3d(context.getBlockPos()), context.getPlayer(), ToolLib.TriggerType.USE_BLOCK);
            }
        }

        return action;
    }

    public static TypedActionResult<ItemStack> OnUse(ToolLambdas toolLamb, World world, PlayerEntity user, Hand hand, TypedActionResult<ItemStack> typedActionResult) {
        TypedActionResult<ItemStack> res = typedActionResult;

        if (toolLamb.Enable_use) {
            if (toolLamb.onUse != null) {
                res = toolLamb.onUse.trigger(world,user,hand);
            }else {
                mainTrig(toolLamb,user.getStackInHand(hand),world,user.getPos(),user, ToolLib.TriggerType.USE);
            }
        }

        return res;
    }
}
