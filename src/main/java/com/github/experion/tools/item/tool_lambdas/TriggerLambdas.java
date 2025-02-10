package com.github.experion.tools.item.tool_lambdas;

import com.github.experion.tools.lib.ToolLib;
import net.fabricmc.fabric.api.item.v1.EnchantingContext;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;


public interface TriggerLambdas {

    default ExistsLambdas exists() {
        return new ExistsLambdas();
    }

    default void main_trigger(ItemStack stack, World world, Vec3d pos, LivingEntity Player, ToolLib.TriggerType triggerType) {};

    default ActionResult onUseBlock(ItemUsageContext context, ActionResult actionResult) {return actionResult;}
    default void onPostHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {}
    default void onPostMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner, boolean success) {}
    default void onPunchBlock(ItemStack stack, World world, BlockPos pos, LivingEntity puncher) {}
    default void onPunchAir(ItemStack stack, World world, LivingEntity puncher) {}
    default ActionResult onUse(World world, PlayerEntity user, Hand hand, ActionResult actionResult) {return actionResult;}

    default void onThundered(ItemStack stack, ServerWorld world, ServerPlayerEntity player) {}

    default Text getName(Text def, ItemStack stack) {return def;}
    default void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {}

    default float setEfficiency(ItemStack stack, BlockState state, float default_float) {return default_float;};
    default float setDamage(Entity target, float baseAttackDamage, DamageSource damageSource, float default_float) {return default_float;}

    default void usagetick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {}
    default void inventroytick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {}

    default boolean canBeEnchanted(ItemStack stack, RegistryEntry<Enchantment> enchantment, EnchantingContext context, boolean def_res) {return def_res;};

    String getLambdasName();
}
