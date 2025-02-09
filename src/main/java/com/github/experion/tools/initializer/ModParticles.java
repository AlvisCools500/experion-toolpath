package com.github.experion.tools.initializer;

import com.github.experion.tools.lib.Experion.ExperionRegistery;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModParticles {
    public static final SimpleParticleType AZALEA_EFFECT = FabricParticleTypes.simple();
    public static final SimpleParticleType AZALEA_EXPLOSIVE = FabricParticleTypes.simple();
    public static final SimpleParticleType LIFESTEALED = FabricParticleTypes.simple();
    public static final SimpleParticleType GILDED_SHINE = FabricParticleTypes.simple();

    public static void register() {
        Registry.register(Registries.PARTICLE_TYPE, ExperionRegistery.newId("azalea_effect"), AZALEA_EFFECT);
        Registry.register(Registries.PARTICLE_TYPE, ExperionRegistery.newId("azalea_explosion"), AZALEA_EXPLOSIVE);
        Registry.register(Registries.PARTICLE_TYPE, ExperionRegistery.newId("lifestealed"), LIFESTEALED);
        Registry.register(Registries.PARTICLE_TYPE, ExperionRegistery.newId("gilded_shine"), GILDED_SHINE);
    }
}
