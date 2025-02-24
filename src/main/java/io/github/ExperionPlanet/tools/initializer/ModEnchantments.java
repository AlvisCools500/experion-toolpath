package io.github.ExperionPlanet.tools.initializer;

import io.github.ExperionPlanet.tools.enchantments.DurableBlessingEnchantment;
import io.github.ExperionPlanet.tools.lib.Experion.ExperionRegistery;
import com.mojang.serialization.MapCodec;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public class ModEnchantments {
    public static final RegistryKey<Enchantment> DURABLE_BLESSING_KEY = of("durable_blessing");
    public static final MapCodec<DurableBlessingEnchantment> DURABLE_BLESSING_EFFECT = register("durable_blessing_effect",DurableBlessingEnchantment.CODEC);

    public static void init() {}

    private static RegistryKey<Enchantment> of(String path) {
        return RegistryKey.of(RegistryKeys.ENCHANTMENT, ExperionRegistery.newId(path));
    }

    private static <T extends EnchantmentEntityEffect> MapCodec<T> register(String name, MapCodec<T> codec) {
        return Registry.register(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, ExperionRegistery.newId(name), codec);
    }
}
