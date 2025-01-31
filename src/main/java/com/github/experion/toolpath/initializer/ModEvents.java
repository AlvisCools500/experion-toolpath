package com.github.experion.toolpath.initializer;

import com.github.experion.toolpath.ModInit;
import com.github.experion.toolpath.items.tool_identify.ToolIdList;
import com.github.experion.toolpath.misc.persistent_state.ToolPathData;
import com.github.experion.toolpath.misc.persistent_state.ToolPathDataManager;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;

public class ModEvents {
    public static void init() {
        ServerLifecycleEvents.SERVER_STARTED.register((server) -> {
            ToolPathData state = ToolPathDataManager.getOrCreate(server);

            if (state.getMain_map().isEmpty()) {
                ModInit.LOGGER.info("not available so generating...");
            }else {
                ModInit.LOGGER.info("It's already available!");
            }

            ToolIdList.init();
        });




    }
}
