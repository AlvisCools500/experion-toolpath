package com.github.experion.toolpath.items.tool_identify;

import com.github.experion.toolpath.ModInit;
import com.github.experion.toolpath.initializer.ModTags;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ToolIdList {
    private static HashMap<String, ToolGetter> ToolMap = new HashMap<>();
    private static HashMap<Integer, ToolIdentifier> IDList = new HashMap<>();

    public static List<TagKey<Item>> TagList = new ArrayList<>();

    public static final ToolIdentifier WOODEN_TOOLS = new ToolIdentifier(toolID.WOODEN,ModTags.Tools.WOODEN_TOOLS, -1);
    public static final ToolIdentifier FLINT_TOOLS = new ToolIdentifier(toolID.FLINT, ModTags.Tools.FLINT_TOOLS, toolID.WOODEN);
    public static final ToolIdentifier STONE_TOOLS = new ToolIdentifier(toolID.STONE,ModTags.Tools.STONE_TOOLS, toolID.FLINT);
    public static final ToolIdentifier IRON_TOOLS = new ToolIdentifier(toolID.IRON,ModTags.Tools.IRON_TOOLS, toolID.STONE);

    public static ToolIdentifier getIdentifier(int numid) {
        return IDList.get(numid);
    }

    public static ToolIdentifier getIdentifier(Item item) {
        Identifier id = Registries.ITEM.getId(item);
        String raw_id = id.getNamespace() + ":" + id.getPath();

        if (ToolMap.containsKey(raw_id)) {
            ToolGetter getter = ToolMap.get(raw_id);

            return IDList.get(getter.ToolID);
        }

        return null;
    }

    public static int getToolType(Item item) {
        ItemStack stack = item.getDefaultStack();

        if (stack.isIn(ItemTags.SWORDS)) {
            return tooltypeid.SWORD;
        } else if (stack.isIn(ItemTags.AXES)) {
            return tooltypeid.AXE;
        } else if (stack.isIn(ItemTags.PICKAXES)) {
            return tooltypeid.PICKAXE;
        } else if (stack.isIn(ItemTags.SHOVELS)) {
            return tooltypeid.SHOVEL;
        } else if (stack.isIn(ItemTags.HOES)) {
            return tooltypeid.HOE;
        }

        return -1;
    }

    public static class tooltypeid {
        public static final int SWORD = 1;
        public static final int AXE = 2;
        public static final int PICKAXE = 3;
        public static final int SHOVEL = 4;
        public static final int HOE = 5;
    }

    public static void tryAddID(ToolIdentifier id) {
        if (!IDList.containsKey(id.getNumID())) {
            IDList.put(id.getNumID(),id);
        }
    }

    public static void tryAddTag(int ToolID, TagKey<Item> tag) {
        if (!TagList.contains(tag)) {
            TagList.add(tag);

            RegistryEntryList<Item> itemList = Registries.ITEM.getEntryList(tag).orElse(null);

            if (itemList != null) {
                for (RegistryEntry<Item> a : itemList) {
                    Item item = a.value();
                    int tooltype = getToolType(item);

                    Identifier id = Registries.ITEM.getId(item);

                    if (tooltype != -1) {
                        ToolMap.put(id.getNamespace() + ":" + id.getPath(),new ToolGetter(tooltype, ToolID));
                    }else {
                        ModInit.LOGGER.warn("ToolType Fail?");
                    }
                }
            }else {
                ModInit.LOGGER.warn("oh no fail to iterate?");
            }
        }
    }

    public static Identifier convertIdentifier(String str) {
        String[] list = str.split(":");

        return Identifier.of(list[0],list[1]);
    }

    public static void init() {
        ModInit.LOGGER.info("ToolMap: " + ToolMap.size());
        ModInit.LOGGER.info("IDList: " + IDList.size());
    }

    public static class toolID {
        public static final int WOODEN = 1;
        public static final int STONE = 2;
        public static final int IRON = 3;
        public static final int DIAMOND = 4;
        public static final int NETHERITE = 5;
        public static final int GOLDEN = 6;
        public static final int REDSTONE = 20;
        public static final int FLINT = 22;
        public static final int STEEL = 25;
        public static final int GILDED_IRON = 28;
        public static final int AZALEA = 29;

        public static final HashMap<Integer, TagKey<Item>> list = new HashMap<>(){{
            put(WOODEN, ModTags.Tools.WOODEN_TOOLS);
            put(STONE, ModTags.Tools.STONE_TOOLS);
            put(IRON, ModTags.Tools.IRON_TOOLS);
            put(DIAMOND, ModTags.Tools.DIAMOND_TOOLS);
            put(NETHERITE, ModTags.Tools.NETHERITE_TOOLS);
            put(GOLDEN, ModTags.Tools.GOLDEN_TOOLS);
            put(REDSTONE, ModTags.Tools.REDSTONE_TOOLS);
            put(FLINT, ModTags.Tools.FLINT_TOOLS);
            put(STEEL, ModTags.Tools.STEEL_TOOLS);
            put(GILDED_IRON, ModTags.Tools.GILDED_IRON_TOOLS);
            put(AZALEA, ModTags.Tools.AZALEA_TOOLS);
        }};

        public static final HashMap<Integer, Integer> depedency = new HashMap<>(){{
            put(STONE,WOODEN);
            put(IRON,STONE);
        }};
    }
}
