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

    ModItems.init();
    ModTools.init();

    ModItemGroups.init();

    ModParticles.register();

    //temp();

    ModEvents.init();

    ServerMessage.connect();
  }

  public static void temp(ToolPathData state) {
    Random rand = new Random();

    for (int am_plr = 1; am_plr <= 5; am_plr++) {
      UUID uuid = UUID.randomUUID();
      for (int itype = 1; itype <= 5; itype++) {
        for (int a = 1; a <= 5; a++) {
          state.putMap(uuid, itype, rand.nextInt(1,99));
        }

      }
    }
  }

  /*public static ToolPathData.ToolPathMap temp(ToolPathData state) {
    Random rand = new Random();

    ToolPathData.ToolPathMap map = new ToolPathData.ToolPathMap();

    for (int am_plr = 1; am_plr <= 5; am_plr++) {
      HashMap<Integer, List<Integer>> tooltype_map = new HashMap<>();

      for (int itype = 1; itype <= 5; itype++) {
        List<Integer> MyList = new ArrayList<>();

        for (int a = 1; a <= 5; a++) {
          MyList.add(rand.nextInt(1,99));
        }

        tooltype_map.put(itype,MyList);
      }

      map.put(UUID.randomUUID(),tooltype_map);
    }

    /*
    NbtCompound nbt = ToolPathData.convertToNbt(map);
    ToolPathData.ToolPathMap remap = ToolPathData.convertToMap(nbt);

    for (UUID uuid : remap.keySet()) {
      ModInit.LOGGER.info("ENTER PLAYER " + uuid);

      for (int tooltype : remap.get(uuid).keySet()) {
        ModInit.LOGGER.info("ENTER TOOL TYPE " + tooltype);

        for (int toolid : remap.get(uuid).get(tooltype)) {
          ModInit.LOGGER.info("ID " + toolid);
        }
      }
    }
  }*/
}
