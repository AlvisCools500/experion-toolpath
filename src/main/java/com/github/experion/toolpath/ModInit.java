package com.github.experion.toolpath;

import com.github.experion.toolpath.initializer.*;
import com.github.experion.toolpath.items.CustomLambdas;
import com.github.experion.toolpath.items.tool_identify.ToolIdList;
import com.github.experion.toolpath.misc.ServerMessage;
import com.github.experion.toolpath.misc.persistent_state.ToolPathData;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class ModInit implements ModInitializer {
  public static final String ID = "experion_tool_path";
  public static final Logger LOGGER = LoggerFactory.getLogger(ModInit.class);

  @Override
  public void onInitialize() {
    ModInit.LOGGER.info("Hello, World! (Common initialize)");

    CustomLambdas.init();

    ModDataComponents.init();

    ModEnchantments.init();
    ModItems.init();
    ModTools.init();

    ModItemGroups.init();

    ModParticles.register();
    ModEvents.init();

    ServerMessage.connect();
  }
}
