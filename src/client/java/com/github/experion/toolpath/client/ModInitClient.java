package com.github.experion.toolpath.client;

import com.github.experion.toolpath.ModInit;
import com.github.experion.toolpath.client.initializer.ModClientEvents;
import com.github.experion.toolpath.client.initializer.ModItemProperties;
import com.github.experion.toolpath.client.particles.AzaleaExplosionParticle;
import com.github.experion.toolpath.initializer.ModParticles;
import com.github.experion.toolpath.client.particles.AzaleaEffectParticle;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.client.data.ItemModels;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModInitClient implements ClientModInitializer {
  public static final Logger LOGGER = LoggerFactory.getLogger(ModInitClient.class);

  @Override
  public void onInitializeClient() {
    ModInit.LOGGER.info("Hello, World! (Client initialize)");

    ModClientEvents.register();
    ModItemProperties.bootstrap();

    regParti(ModParticles.AZALEA_EFFECT, AzaleaEffectParticle.Factory::new);
    regParti(ModParticles.AZALEA_EXPLOSIVE, AzaleaExplosionParticle.Factory::new);
  }

  private static <T extends ParticleEffect> void regParti(ParticleType<T> var1, ParticleFactoryRegistry.PendingParticleFactory<T> var2) {
      ParticleFactoryRegistry.getInstance().register(var1,var2);
  }


}
