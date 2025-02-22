package io.github.experion.tools.initializer;

import io.github.experion.tools.lib.Experion.ExperionRegistery;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSoundEvents {

    public static final SoundEvent AQUAMOND_BUBBLES = registerSound("aquamond_bubbles");

    public static void init() {}

    // Source: Fabric Wiki itself ofc lol
    private static SoundEvent registerSound(String id) {
        Identifier v = ExperionRegistery.newId(id);
        return Registry.register(Registries.SOUND_EVENT, v, SoundEvent.of(v));
    }
}
