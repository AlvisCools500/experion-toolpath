package com.github.experion.toolpath.lib.Experion;

import com.github.experion.toolpath.ModInit;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ExperionRegistery {
    public static final Logger regLog = LoggerFactory.getLogger(ExperionRegistery.class);

    public static Identifier newId(String name) {
        String res = name.toLowerCase();

        if (!res.equals(name)) {
            regLog.warn(name + " is not lower cased! Automatically lower casing... (newId)");
        }

        return Identifier.of(ModInit.ID,res);
    }

    public static String newStringId(String name) {
        String res = name.toLowerCase();

        if (!res.equals(name)) {
            regLog.warn(name + " is not lower cased! Automatically lower casing... (newStringId)");
        }

        return ModInit.ID + ":" + res;
    }

    public static Item registerBasicItem(String name, Integer stack) {
        return registerItem(new Item(new Item.Settings().maxCount(stack)), name);
    }

    public static Item registerItem(Item item, String name) {
        return Registry.register(Registries.ITEM, newId(name), item);
    }

    public static Block registerBlock(Block block, String name, boolean blockItem, boolean defaultDrop) {
        Block res = Registry.register(Registries.BLOCK, newId(name), block);

        return res;
    }

    public static Item registerBlockItem(Block block, String name, Integer stack) {
        String str = name;

        if (name.equals("?")) {
            str = Registries.BLOCK.getId(block).getPath();
        }
        return registerItem(new BlockItem(block, new Item.Settings().maxCount(stack)), str);
    }
}
