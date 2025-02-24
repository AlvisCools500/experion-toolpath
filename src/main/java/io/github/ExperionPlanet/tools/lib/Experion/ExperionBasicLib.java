package io.github.ExperionPlanet.tools.lib.Experion;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.random.Random;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ExperionBasicLib {
    public static String getBlockID(Block block) {
        return Registries.BLOCK.getId(block).getPath();
    }

    public static String getItemID(Item item) {
        return Registries.ITEM.getId(item).getPath();
    }

    public static Block getBlockFromID(Identifier id) {
        Block res = null;

        try {
            res = Registries.BLOCK.get(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    public static Item getItemFromID(Identifier id) {
        Item res = null;

        try {
            res = Registries.ITEM.get(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    public static List<Item> ListBlockToItem(List<Block> v) {
        return new ArrayList<>(){{
            for (Block a : v) {
                add(a.asItem());
            }
        }};
    }

    public static int rgbToHex(Color col) {
        return (col.getRed() << 16) | (col.getGreen() << 8) | col.getBlue();
    }

    public static int easyRandomInt(int min, int max, Random random) {
        return random.nextInt(max - min) + min;
    }

    public static float easyRandomFloat(float min, float max, Random random) {
        return random.nextFloat() * (max - min) + min;
    }

    public static List<Identifier> createIdList(Block... blocks) {
        List<Identifier> resList = new ArrayList<>();

        for (Block b : blocks) {
            resList.add(Registries.BLOCK.getId(b));
        }

        return resList;
    }
}