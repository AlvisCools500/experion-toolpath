package com.github.experion.toolpath.initializer;

import com.github.experion.toolpath.lib.Experion.ExperionRegistery;
import com.mojang.serialization.Codec;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModDataComponents {
    public static final ComponentType<Integer> FLINT_AMOUNTCRITICAL = register("flint_amountcritical", Codec.INT);
    public static final ComponentType<Integer> COPPER_STATUS = register("copper_status", Codec.INT);

    public static void init() {}

    private static <T> ComponentType<T> register(String name, Codec<T> cod) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, ExperionRegistery.newId(name), ComponentType.<T>builder().codec(cod).build());
    }
}
