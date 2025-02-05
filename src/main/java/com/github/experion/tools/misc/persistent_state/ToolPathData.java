package com.github.experion.tools.misc.persistent_state;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtInt;
import net.minecraft.nbt.NbtList;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.PersistentState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class ToolPathData extends PersistentState {
    // PLAYER UUID > TOOL TYPE ID > TOOL ID
    public static class ToolPathMap extends HashMap<UUID, HashMap<Integer, List<Integer>>> {}
    public static final String PATH_ID = "mod_toolpath_data";

    public static class ToolIdType {
        public static final int SWORD = 1;
        public static final int AXE = 2;
        public static final int PICKAXE = 3;
        public static final int SHOVEL = 4;
        public static final int HOE = 5;
    }

    private ToolPathMap main_map = new ToolPathMap();

    public static ToolPathData fromNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        ToolPathData dat = new ToolPathData();
        dat.main_map = convertToMap(nbt.getCompound(PATH_ID));
        return dat;
    }

    public static ToolPathMap convertToMap(NbtCompound nbtcomp) {
        ToolPathMap resMap = new ToolPathMap();

        for (String raw_uuid : nbtcomp.getKeys()) {
            UUID uuid = UUID.fromString(raw_uuid);
            HashMap<Integer, List<Integer>> ToolTypeMap = new HashMap<>();

            NbtCompound playercomp = nbtcomp.getCompound(raw_uuid);

            for (String raw_tooltype : playercomp.getKeys()) {
                int tooltype_id = Integer.parseInt(raw_tooltype.substring("type_".length()));

                List<Integer> mylist = new ArrayList<>();

                NbtList toolidlist = playercomp.getList(raw_tooltype,NbtElement.INT_TYPE);

                for (int index = 0; index <= toolidlist.size() - 1; index++) {
                    mylist.add(toolidlist.getInt(index));
                }

                ToolTypeMap.put(tooltype_id, mylist);
            }

            resMap.put(uuid,ToolTypeMap);
        }

        return resMap;
    }

    public static NbtCompound convertToNbt(ToolPathMap map) {
        NbtCompound playerlist = new NbtCompound();

        for (UUID uuid : map.keySet()) {
            NbtCompound tooltype_comp = new NbtCompound();

            for (int tool_type : map.get(uuid).keySet()) {
                NbtList toolids_list = new NbtList();

                for (int tool_id : map.get(uuid).get(tool_type)) {
                    toolids_list.add(NbtInt.of(tool_id));
                }

                tooltype_comp.put("type_" + tool_type, toolids_list);
            }

            playerlist.put(uuid.toString(),tooltype_comp);
        }

        return playerlist;
    }

    public ToolPathMap getMain_map() {
        return this.main_map;
    }

    public void putMap(UUID uuid, int tooltype, int toolid) {
        if (!this.main_map.containsKey(uuid)) {
            this.main_map.put(uuid,new HashMap<>());
        }

        if (!this.main_map.get(uuid).containsKey(tooltype)) {
            this.main_map.get(uuid).put(tooltype, new ArrayList<>());
        }

        if (!this.main_map.get(uuid).get(tooltype).contains(toolid)) {
            this.main_map.get(uuid).get(tooltype).add(toolid);
        }

        markDirty();
    }

    public boolean valid_check(UUID uuid, int tooltype, int toolid) {
        if (this.main_map.containsKey(uuid)) {
            if (this.main_map.get(uuid).containsKey(tooltype)) {
                return this.main_map.get(uuid).get(tooltype).contains(toolid);
            }
        }

        return false;
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        nbt.put("mod_toolpath_data", convertToNbt(main_map));
        return nbt;
    }


}
