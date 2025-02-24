package io.github.ExperionPlanet.tools;

import io.github.ExperionPlanet.tools.initializer.*;
import io.github.ExperionPlanet.tools.item.CustomLambdas;
import io.github.ExperionPlanet.tools.misc.ServerMessage;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModInit implements ModInitializer {
  public static final String ID = "experion_tools";
  public static final Logger LOGGER = LoggerFactory.getLogger(ModInit.class);

  @Override
  public void onInitialize() {
    ModInit.LOGGER.info("Hello, World! (Common initialize)");

    CustomLambdas.init();

    ModDataComponents.init();

    ModEnchantments.init();
    ModItems.init();
    ModTools.init();
    ModStatusEffects.init();

    ModSoundEvents.init();

    ModItemGroups.init();

    ModParticles.register();
    ModEvents.init();

    ServerMessage.connect();
  }
}
