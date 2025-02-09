package com.github.experion.tools.initializer;

import com.github.experion.tools.ModInit;
import jdk.javadoc.doclet.Taglet;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.EntityTypeTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static final TagKey<Item> TOOLPATH_TOOLS = getItemKey("toolpath_tools");

    public static class Tools {
        public static final TagKey<Item> FLINT_TOOLS = getToolKey( "flint_tools");
        public static final TagKey<Item> AZALEA_TOOLS = getToolKey( "azalea_tools");
        public static final TagKey<Item> COPPER_TOOLS = getToolKey("copper_tools");
        public static final TagKey<Item> STEEL_TOOLS = getToolKey("steel_tools");
        public static final TagKey<Item> REDSTONE_TOOLS = getToolKey( "redstone_tools");
        public static final TagKey<Item> GILDED_IRON_TOOLS = getToolKey( "gilded_tools");
        public static final TagKey<Item> VAMPIRE_STEEL_TOOLS = getToolKey("vampire_steel_tools");

        public static final TagKey<Item> WOODEN_TOOLS = getToolKey("wooden_tools");
        public static final TagKey<Item> STONE_TOOLS = getToolKey("stone_tools");
        public static final TagKey<Item> IRON_TOOLS = getToolKey("iron_tools");
        public static final TagKey<Item> DIAMOND_TOOLS = getToolKey("diamond_tools");
        public static final TagKey<Item> NETHERITE_TOOLS = getToolKey("netherite_tools");
        public static final TagKey<Item> GOLDEN_TOOLS = getToolKey("golden_tools");

        private static TagKey<Item> getToolKey(String path) {
            return getItemKey("mod_tools/" + path);
        }
    }

    public static class Materials {
        public static final TagKey<Item> AZALEA = getMatsKey("azalea");
        public static final TagKey<Item> FLINT = getMatsKey("flint");
        public static final TagKey<Item> REDSTONE = getMatsKey("redstone");
        public static final TagKey<Item> COPPER = getMatsKey("copper");
        public static final TagKey<Item> STEEL = getMatsKey("steel");
        public static final TagKey<Item> VAMPIRE_STEEL = getMatsKey("vampire_steel");

        private static TagKey<Item> getMatsKey(String path) {
            return getItemKey("materials/" + path + "_tool_materials");
        }
    }

    public static class Misc {
        public static final TagKey<Item> DURABLE_ABILITY = getItemKey("misc/durable_ability");
        public static final TagKey<EntityType<?>> PIGLINS = getEntityKey("piglins");
    }

    public static TagKey<EntityType<?>> getEntityKey(String path) {
        return TagKey.of(RegistryKeys.ENTITY_TYPE,Identifier.of(ModInit.ID,path));
    }

    public static TagKey<Item> getItemKey(String path) {
        return TagKey.of(RegistryKeys.ITEM, Identifier.of(ModInit.ID,path));
    }
}
