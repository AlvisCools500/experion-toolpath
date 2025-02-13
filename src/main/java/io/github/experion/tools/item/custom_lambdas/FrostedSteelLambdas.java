package io.github.experion.tools.item.custom_lambdas;

import io.github.experion.tools.initializer.ModDataComponents;
import io.github.experion.tools.initializer.ModStatusEffects;
import io.github.experion.tools.item.tool_lambdas.ExistsLambdas;
import io.github.experion.tools.item.tool_lambdas.TriggerLambdas;
import io.github.experion.tools.lib.ToolAppend;
import io.github.experion.tools.lib.ToolLib;
import io.github.experion.tools.lib.ToolTypes;
import net.minecraft.block.BlockState;
import net.minecraft.component.ComponentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

import java.util.List;

public class FrostedSteelLambdas implements TriggerLambdas {
    private static final ComponentType<Integer> FREEZE_STATUS = ModDataComponents.TOOL_STATUS;
    private static final int NORMAL = 1;
    private static final int FROSTING = 2;
    private static final int MELTING = 3;

    @Override
    public ExistsLambdas exists() {
        return new ExistsLambdas().PostHit().PostMine();
    }

    private boolean isInSnow(World world, Entity entity) {
        RegistryKey<Biome> biome = world.getBiome(entity.getBlockPos()).getKey().orElse(null);
        if (biome != null) {
            return world.getRegistryManager().getOrThrow(RegistryKeys.BIOME).get(biome).isCold(entity.getBlockPos(),world.getSeaLevel());
        }
        return false;
    }

    private int check_status(ItemStack stack) {
        if (stack.contains(FREEZE_STATUS)) {
            return stack.get(FREEZE_STATUS);
        }
        return NORMAL;
    }

    private boolean isInNether(World world) {
        return world.getRegistryKey() == World.NETHER;
    }

    @Override
    public String getLambdasName() {
        return "FrostedSteel";
    }

    @Override
    public float setEfficiency(ItemStack stack, BlockState state, float default_float) {
        int status = check_status(stack);

        if (status == FROSTING) {
            return default_float * 1.5f;
        } else if (status == MELTING) {
            return default_float * 0.5f;
        }
        return default_float;
    }

    @Override
    public float setDamage(Entity target, float baseAttackDamage, DamageSource damageSource, float default_float) {
        int status = check_status(damageSource.getWeaponStack());

        if (status == FROSTING) {
            return (baseAttackDamage * 1.5f) - baseAttackDamage;
        }
        return default_float;
    }

    @Override
    public void onPostMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner, boolean success) {
        if (!world.isClient()) {
            if (check_status(stack) == MELTING) {
                ToolLib.DamageToolAbility(stack, 3, (ServerWorld) world, (ServerPlayerEntity) miner);
            }
        }
    }

    @Override
    public void onPostHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        World world = attacker.getWorld();
        if (!world.isClient()) {
            if (check_status(stack) == MELTING) {
                ToolLib.DamageToolAbility(stack,3,(ServerWorld) world,(ServerPlayerEntity) attacker) ;
            }else {
                target.addStatusEffect(new StatusEffectInstance(ModStatusEffects.GROW_FROST, 100, 1), attacker);
            }
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
        int mystatus = check_status(stack);

        if (mystatus == NORMAL) {
            ToolAppend.of(stack.getItem())
                    .line("Try use on Cold Biomes", ToolAppend.TYPE.QUOTE)
                    .line("Grow Frost Effect I", ToolAppend.TYPE.POSITIVE, true, ToolTypes.ATTACKABLE_TOOLS)
                    .offer(tooltip);
        }else if (mystatus == FROSTING) {
            ToolAppend.of(stack.getItem())
                    .line("Increased Efficiency", ToolAppend.TYPE.POSITIVE, true, ToolTypes.MINEABLE_TOOLS)
                    .line("Increased Damage", ToolAppend.TYPE.POSITIVE, true, ToolTypes.ATTACKABLE_TOOLS)
                    .line("Grow Frost Effect II", ToolAppend.TYPE.POSITIVE, true, ToolTypes.ATTACKABLE_TOOLS)
                    .offer(tooltip);
        }else if (mystatus == MELTING) {
            ToolAppend.of(stack.getItem())
                    .line("Don't use this in nether", ToolAppend.TYPE.QUOTE)
                    .line("Getting weaker", ToolAppend.TYPE.NEGATIVE)
                    .line("4x durability use", ToolAppend.TYPE.NEGATIVE)
                    .offer(tooltip);
        }
    }

    @Override
    public Text getName(Text def, ItemStack stack) {
        if (check_status(stack) == FROSTING) {
            return Text.literal("FROSTING ").formatted(Formatting.BLUE).formatted(Formatting.BOLD).append(def);
        }else if (check_status(stack) == MELTING) {
            return Text.literal("MELTING ").formatted(Formatting.RED).formatted(Formatting.BOLD).append(def).formatted(Formatting.RESET);
        }
        return def;
    }

    @Override
    public void inventroytick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient()) {
            if (isInNether(world)) {
                stack.set(FREEZE_STATUS,MELTING);
            }else  {
                if (isInSnow(world,entity)) {
                    stack.set(FREEZE_STATUS,FROSTING);
                }else {
                    stack.set(FREEZE_STATUS,NORMAL);
                }
            }
        }
    }
}
