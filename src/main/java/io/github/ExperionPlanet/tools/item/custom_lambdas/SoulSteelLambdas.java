package io.github.ExperionPlanet.tools.item.custom_lambdas;

import io.github.ExperionPlanet.tools.initializer.ModDataComponents;
import io.github.ExperionPlanet.tools.initializer.ModItems;
import io.github.ExperionPlanet.tools.initializer.ModStatusEffects;
import io.github.ExperionPlanet.tools.item.ToolIdList;
import io.github.ExperionPlanet.tools.item.tool_lambdas.ExistsLambdas;
import io.github.ExperionPlanet.tools.item.tool_lambdas.TriggerLambdas;
import io.github.ExperionPlanet.tools.lib.FastProperty;
import io.github.ExperionPlanet.tools.lib.ToolTypes;
import net.minecraft.block.BlockState;
import net.minecraft.component.ComponentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

import java.util.List;

public class SoulSteelLambdas implements TriggerLambdas {
    private static final ComponentType<Integer> SOULS = ModDataComponents.TOOL_COUNTER_FIRST;
    private static final int MAX_SOULS = 10;

    private static int getSouls(ItemStack stack) {
        return FastProperty.intVal(stack, SOULS, 0);
    }

    private static void incrementSouls(ItemStack stack) {
        int curr = getSouls(stack);
        if (curr < MAX_SOULS) {
            stack.set(SOULS, curr + 1);
        }
    }

    private static void decreaseSouls(ItemStack stack) {
        int curr = getSouls(stack);
        if (curr > 0) {
            stack.set(SOULS, curr - 1);
        }
    }

    @Override
    public ExistsLambdas exists() {
        return TriggerLambdas.super.exists().PostMine().PostHit().Use();
    }

    @Override
    public void onPostHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        World world = attacker.getWorld();

        if (!world.isClient()) {
            int type = ToolIdList.getToolType(stack.getItem());

            if (type == ToolTypes.SWORD) {
                decreaseSouls(stack);
            }

            if (!target.isAlive()) {
                Random rand = world.getRandom();

                if (rand.nextFloat() <= 0.75f) {
                    incrementSouls(stack);
                }
            }else {
                if (getSouls(stack) >= MAX_SOULS) {
                    stack.set(SOULS, 0);
                    target.addStatusEffect(new StatusEffectInstance(ModStatusEffects.POSSESSED, 40));
                }
            }
        }
    }

    @Override
    public void onPostMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner, boolean success) {
        if (!world.isClient()) {
            decreaseSouls(stack);
        }
    }

    @Override
    public ActionResult onUse(World world, PlayerEntity user, Hand hand, ActionResult actionResult) {
        if (!world.isClient()) {
            ItemStack leftStack = user.getStackInHand(Hand.OFF_HAND);
            ItemStack stack = user.getStackInHand(Hand.MAIN_HAND);

            if (leftStack.isOf(ModItems.SOUL_JAR) && getSouls(stack) < MAX_SOULS) {
                incrementSouls(stack);
                leftStack.decrement(1);

                Vec3d pos = user.getPos();

                world.playSound(null, pos.getX(),pos.getY(),pos.getZ(), SoundEvents.BLOCK_AMETHYST_BLOCK_BREAK, SoundCategory.PLAYERS);
            }
        }

        return ActionResult.PASS;
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.literal("Souls " + getSouls(stack) + "/" + MAX_SOULS));
    }

    @Override
    public float setEfficiency(ItemStack stack, BlockState state, float default_float) {
        return default_float * (1f + (0.1f * getSouls(stack)));
    }

    @Override
    public float setDamage(Entity target, float baseAttackDamage, DamageSource damageSource, float default_float) {
        return (baseAttackDamage * (1f + (0.1f * getSouls(damageSource.getWeaponStack())))) - baseAttackDamage;
    }

    @Override
    public String getLambdasName() {
        return "SoulSteel";
    }
}
