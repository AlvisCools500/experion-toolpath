package com.github.experion.toolpath.misc.persistent_state;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.PersistentState;
import net.minecraft.world.PersistentStateManager;



public class ToolPathDataManager {
    private static final PersistentState.Type<ToolPathData> TYPE = new PersistentState.Type<>(
            ToolPathData::new,
            ToolPathData::fromNbt,
            null
    );

    public static ToolPathData getOrCreate(MinecraftServer server) {
        PersistentStateManager stateManager = server.getWorld(ServerWorld.OVERWORLD).getPersistentStateManager();

        ToolPathData state = stateManager.getOrCreate(TYPE, "tool_path_data");

        state.markDirty();

        return state;
    }
}
