package com.github.experion.toolpath.items.tool_lambdas;

import com.github.experion.toolpath.lib.ToolLib;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;


public interface TriggerLambdas {

    default ExistsLambdas exists() {
        return new ExistsLambdas();
    }

    default TypedActionResult<ItemStack> onUse(World world, PlayerEntity user, Hand hand) {return TypedActionResult.pass(user.getStackInHand(hand));};

    default void main_trigger(ItemStack stack, World world, Vec3d pos, LivingEntity Player, ToolLib.TriggerType triggerType) {};

    default void onUseBlock(ItemUsageContext context, ActionResult actionResult) {};
    default void onPostHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {};
    default void onPostMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {};
    default void onPunchBlock(ItemStack stack, World world, BlockPos pos, LivingEntity puncher) {}
    default void onPunchAir(ItemStack stack, World world, LivingEntity puncher) {}

    default Text getName(Text def) {return def;}
    default void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {}

    default float setEfficiency(ItemStack stack, BlockState state, float default_float) {return default_float;};
    default float setDamage(Entity target, float baseAttackDamage, DamageSource damageSource, float default_float) {return default_float;}

    String getLambdasName();
}
