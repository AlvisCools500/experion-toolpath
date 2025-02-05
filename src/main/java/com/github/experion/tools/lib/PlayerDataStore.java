package com.github.experion.tools.lib;

import com.github.experion.tools.ModInit;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;

import java.util.ArrayList;
import java.util.List;

public class PlayerDataStore {
    public static class NbtKeys {
        public static final String TOOL_PATH = "tool_path_unlocks";
    }

    public static ImplyData ImplyThePlayer(PlayerEntity plr) {
        return (ImplyData) plr;
    }

    public static NbtList get_NbtList(PlayerEntity v, String key, int type) {
        ImplyData plr = ImplyThePlayer(v);
        NbtCompound nbtcomp = plr.getToolPathData();

        return nbtcomp.getList(key, type);
    }

    public static void add_NbtList(PlayerEntity v, String key, int type, NbtElement val) {
        ImplyData plr = ImplyThePlayer(v);
        NbtCompound nbtComp = plr.getToolPathData();
        NbtList nbtList = nbtComp.getList(key,type);

        if (nbtList == null) {
            nbtList = new NbtList();
        }

        try {
            nbtList.add(val);
            nbtComp.put(key,nbtList);
            ModInit.LOGGER.info("ADDED " + val);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    public static List<Integer> convertIntList(NbtList nbtList) {
        return new ArrayList<>(){
            {
                for (int i = 0; i <= nbtList.size() - 1; i++ ) {
                    add(nbtList.getInt(i));
                }
            }
        };
    }

    public static List<Integer> get_ToolPathList(PlayerEntity v) {
        ImplyData plr = ImplyThePlayer(v);
        NbtList nbtList = get_NbtList(v, NbtKeys.TOOL_PATH, NbtElement.INT_TYPE);

        return convertIntList(nbtList);
    }
}
