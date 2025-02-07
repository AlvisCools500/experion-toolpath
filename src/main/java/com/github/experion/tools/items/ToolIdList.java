package com.github.experion.tools.items;

import com.github.experion.tools.initializer.ModTags;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;


import java.util.HashMap;

public class ToolIdList {
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

    public static boolean correctTool(Item item, int... a) {
        for (int v : a) {
            if (v == getToolType(item)) {
                return true;
            }
        }

        return false;
    }

    public static class tooltypeid {
        public static final int SWORD = 1;
        public static final int AXE = 2;
        public static final int PICKAXE = 3;
        public static final int SHOVEL = 4;
        public static final int HOE = 5;
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
        public static final int COPPER = 23;
        public static final int VAMPIRE_STEEL = 50;

        // Coming soon
        public static final int EXPERIONS = 69;

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
    }
}
