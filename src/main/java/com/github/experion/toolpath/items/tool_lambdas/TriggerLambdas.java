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

public interface TriggerLambdas {

    default ExistsLambdas exists() {
        return new ExistsLambdas();
    }

    default TypedActionResult<ItemStack> onUse(World world, PlayerEntity user, Hand hand) {return TypedActionResult.pass(user.getStackInHand(hand));};

    default void main_trigger(ItemStack stack, World world, Vec3d vec3d, LivingEntity Player, ToolLib.TriggerType triggerType) {};

    default void onUseBlock(ItemUsageContext context, ActionResult actionResult) {};
    default void onPostHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {};
    default void onPostMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {};

    default float setEffeciency(ItemStack stack, BlockState state, float default_float) {return default_float;};
}
