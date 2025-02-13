package io.github.experion.tools.initializer;

import io.github.experion.tools.lib.Experion.ExperionRegistery;
import io.github.experion.tools.mob_effect.GrowFrostEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;

public class ModStatusEffects {
    public static final RegistryEntry<StatusEffect> GROW_FROST = Registry.registerReference(Registries.STATUS_EFFECT, ExperionRegistery.newId("grow_frost"), new GrowFrostEffect(StatusEffectCategory.HARMFUL, 0x8ae6ff));

    public static void init() {}
}
