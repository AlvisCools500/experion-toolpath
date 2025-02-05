package com.github.experion.tools.client.datagens;

import com.github.experion.tools.initializer.ModEnchantments;
import com.github.experion.tools.initializer.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceCondition;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class EnchantmentsDataGen extends FabricDynamicRegistryProvider {
    public EnchantmentsDataGen(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
        register(entries, ModEnchantments.DURABLE_BLESSING_KEY,Enchantment.builder(
                Enchantment.definition(
                        registries.getOrThrow(RegistryKeys.ITEM).getOrThrow(ModTags.Misc.DURABLE_ABILITY),
                        10,
                        5,
                        Enchantment.leveledCost(3,10),
                        Enchantment.leveledCost(5,15),
                        7,
                        AttributeModifierSlot.MAINHAND
                )
        ));
    }

    private void register(Entries entries, RegistryKey<Enchantment> key, Enchantment.Builder builder, ResourceCondition... resourceConditions) {
        entries.add(key, builder.build(key.getValue()), resourceConditions);
    }

    @Override
    public String getName() {
        return "EnchantmentGenerator";
    }
}
