package io.github.ExperionPlanet.tools.item.custom_lambdas;

import io.github.ExperionPlanet.tools.initializer.ModDataComponents;
import io.github.ExperionPlanet.tools.initializer.ModParticles;
import io.github.ExperionPlanet.tools.item.ToolIdList;
import io.github.ExperionPlanet.tools.item.tool_lambdas.ExistsLambdas;
import io.github.ExperionPlanet.tools.item.tool_lambdas.TriggerLambdas;
import io.github.ExperionPlanet.tools.lib.ToolAppend;
import io.github.ExperionPlanet.tools.lib.ToolTypes;
import net.minecraft.block.BlockState;
import net.minecraft.component.ComponentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class VampireSteelLambdas implements TriggerLambdas {
    private static final ComponentType<Integer> LIFESTEAL_COUNTER = ModDataComponents.TOOL_COUNTER_FIRST;
    private static final ComponentType<Integer> LIFESTEAL_MAX = ModDataComponents.TOOL_COUNTER_SECOND;

    @Override
    public ExistsLambdas exists() {
        return TriggerLambdas.super.exists().PostHit().PostMine();
    }

    @Override
    public void onPostHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        World world = attacker.getWorld();

        if (!world.isClient()) {
            if (target.isDead()) {
                lifesteal(stack,attacker);
            }
        }
    }

    private int getCount(ItemStack stack) {
        if (stack.contains(LIFESTEAL_COUNTER)) {
            return stack.get(LIFESTEAL_COUNTER);
        }
        return 0;
    }

    private void setMax(ItemStack stack) {
        stack.set(LIFESTEAL_MAX,new Random().nextInt(5,15));
    }

    private void lifesteal(ItemStack stack, LivingEntity holder) {


        World world = holder.getWorld();
        ServerWorld serverWorld = (ServerWorld) world;
        net.minecraft.util.math.random.Random rand = serverWorld.getRandom();
        int num = rand.nextBetween(1,2);

        if (num == 1 && stack.getDamage() > 0) {
            int res = 1;

            if (ToolIdList.getToolType(stack.getItem()) != ToolIdList.tooltypeid.SWORD && ToolIdList.getToolType(stack.getItem()) != ToolIdList.tooltypeid.HOE) {
                res = rand.nextBetween(1,5);
            }

            stack.setDamage(Math.max(stack.getDamage() - res, 0));
        }else {
            holder.heal(1.0f);
            world.playSound(null, holder.getX(),holder.getY(),holder.getZ(), SoundEvents.ENTITY_WITCH_DRINK, SoundCategory.PLAYERS);
            serverWorld.spawnParticles(ModParticles.LIFESTEALED, holder.getX(), holder.getY() + 0, holder.getZ(), 5, 0.5,0.2,0.5,0);
        }

    }

    @Override
    public void onPostMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner, boolean succes) {
        if (!world.isClient()) {
            if (!stack.contains(LIFESTEAL_MAX)) {
                setMax(stack);
            }

            if (new Random().nextInt(1, 4) == 1) {
                stack.set(LIFESTEAL_COUNTER, getCount(stack) + 1);
            }

            if (getCount(stack) >= stack.get(LIFESTEAL_MAX)) {
                lifesteal(stack,miner);
                stack.set(LIFESTEAL_COUNTER, 0);
                setMax(stack);
            }
        }

    }

    @Override
    public float setEfficiency(ItemStack stack, BlockState state, float default_float) {
        return TriggerLambdas.super.setEfficiency(stack, state, default_float);
    }

    @Override
    public float setDamage(Entity target, float baseAttackDamage, DamageSource damageSource, float default_float) {
        return TriggerLambdas.super.setDamage(target, baseAttackDamage, damageSource, default_float);
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
        int tooltype = ToolIdList.getToolType(stack.getItem());
        ToolAppend.of(stack.getItem())
                .line("Night, increases Damage and Efficiency", ToolAppend.TYPE.POSITIVE)
                .line("Guaranteed lifesteal every kill", ToolAppend.TYPE.POSITIVE, true, List.of(ToolTypes.SWORD, ToolTypes.HOE))
                .line("Chance to fill up the lifesteal", ToolAppend.TYPE.POSITIVE, true, ToolTypes.MINEABLE_TOOLS)
                .line("If lifesteal full, Gives health", ToolAppend.TYPE.POSITIVE, true, ToolTypes.MINEABLE_TOOLS)
                .offer(tooltip);

        // Break Block Section
        if (tooltype != ToolIdList.tooltypeid.SWORD && tooltype != ToolIdList.tooltypeid.HOE) {
            if (stack.contains(LIFESTEAL_MAX)) {
                tooltip.add(Text.literal("Lifesteal " + getCount(stack) + "/" + stack.get(LIFESTEAL_MAX)).formatted(Formatting.RED));
            }
        }

    }

    @Override
    public String getLambdasName() {
        return "VampireSteel";
    }
}
