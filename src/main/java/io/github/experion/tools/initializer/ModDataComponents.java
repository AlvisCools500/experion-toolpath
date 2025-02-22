package io.github.experion.tools.initializer;

import io.github.experion.tools.lib.Experion.ExperionRegistery;
import com.mojang.serialization.Codec;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModDataComponents {
    public static final ComponentType<Integer> FLINT_AMOUNTCRITICAL = register("flint_amountcritical", Codec.INT);
    public static final ComponentType<Integer> TOOL_STATUS = register("tool_status", Codec.INT);
    public static final ComponentType<Long> TOOL_LONGTICK = register("tool_tick",Codec.LONG);
    public static final ComponentType<Long> TOOL_NEXTTICK = register("tool_nexttick", Codec.LONG);
    public static final ComponentType<Boolean> TOOL_BOOLEAN_FIRST = register("tool_boolean_first",Codec.BOOL);
    public static final ComponentType<Boolean> TOOL_BOOLEAN_SECOND = register("tool_boolean_second",Codec.BOOL);
    public static final ComponentType<Boolean> TOOL_BOOLEAN_THIRD = register("tool_boolean_third",Codec.BOOL);
    public static final ComponentType<Boolean> WAXED = register("waxed", Codec.BOOL);

    public static final ComponentType<Integer> TOOL_COUNTER_FIRST = register("tool_counter_first", Codec.INT);
    public static final ComponentType<Integer> TOOL_COUNTER_SECOND = register("tool_counter_second", Codec.INT);
    public static final ComponentType<Integer> TOOL_COUNTER_THIRD = register("tool_counter_third", Codec.INT);

    public static void init() {}

    private static <T> ComponentType<T> register(String name, Codec<T> cod) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, ExperionRegistery.newId(name), ComponentType.<T>builder().codec(cod).build());
    }
}
