package io.github.ExperionPlanet.tools.item.custom_lambdas;

import io.github.ExperionPlanet.tools.ModInit;
import io.github.ExperionPlanet.tools.initializer.ModDataComponents;
import io.github.ExperionPlanet.tools.item.tool_lambdas.ExistsLambdas;
import io.github.ExperionPlanet.tools.item.tool_lambdas.TriggerLambdas;
import io.github.ExperionPlanet.tools.lib.FastProperty;
import net.fabricmc.fabric.api.item.v1.EnchantingContext;
import net.minecraft.block.BlockState;
import net.minecraft.component.ComponentType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.UnbreakableComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class ObsidianLambdas implements TriggerLambdas {
    private static final ComponentType<Boolean> IS_BROKEN = ModDataComponents.TOOL_BOOLEAN_FIRST;

    private static boolean isBroken(ItemStack stack) {
        return FastProperty.boolVal(stack,IS_BROKEN,false);
    }

    @Override
    public ExistsLambdas exists() {
        return TriggerLambdas.super.exists().PostHit().Use();
    }

    @Override
    public Text getName(Text def, ItemStack stack) {
        if (isBroken(stack)) {
            return Text.literal("BROKEN ").formatted(Formatting.RED).append(def);
        }

        return def;
    }

    @Override
    public float setEfficiency(ItemStack stack, BlockState state, float default_float) {
        if (isBroken(stack)) {
            return 0;
        }
        return default_float;
    }

    @Override
    public float setDamage(Entity target, float baseAttackDamage, DamageSource damageSource, float default_float) {
        ModInit.LOGGER.info(baseAttackDamage + "/" + default_float);
        return -baseAttackDamage;
    }

    @Override
    public void inventroytick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (world.isClient()) {return;}

        if (stack.getDamage() >= stack.getMaxDamage() - 1 && !isBroken(stack)) {
            stack.set(IS_BROKEN, true);
            stack.set(DataComponentTypes.UNBREAKABLE, new UnbreakableComponent(true));
        }else {
            if (isBroken(stack) && stack.getDamage() < stack.getMaxDamage() - 1) {
                stack.set(IS_BROKEN, false);
                stack.set(DataComponentTypes.UNBREAKABLE, new UnbreakableComponent(false));
            }
        }
    }

    @Override
    public ActionResult onUse(World world, PlayerEntity user, Hand hand, ActionResult actionResult) {
        if (!world.isClient()) {
            ItemStack stack = user.getStackInHand(hand);

            stack.setDamage(stack.getMaxDamage() - 3);
        }

        return ActionResult.PASS;
    }

    @Override
    public boolean canBeEnchanted(ItemStack stack, RegistryEntry<Enchantment> enchantment, EnchantingContext context, boolean def_res) {
        if (enchantment != Enchantments.UNBREAKING && enchantment != Enchantments.MENDING) {
            return false;
        }
        return def_res;
    }

    @Override
    public String getLambdasName() {
        return "Obsidian";
    }
}
