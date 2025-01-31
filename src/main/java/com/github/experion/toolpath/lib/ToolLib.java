package com.github.experion.toolpath.lib;

import com.github.experion.toolpath.initializer.TaggingList;
import com.github.experion.toolpath.items.*;
import com.github.experion.toolpath.items.tool_lambdas.ToolLambdas;
import com.github.experion.toolpath.lib.Experion.ExperionRegistery;
import net.minecraft.item.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class ToolLib {

    public static void onAdded(Item toolItem, ToolType type, ToolLambdas toolLambdas) {
        TaggingList.DEFAULT_TOOLS.add(toolItem);

        if (!toolLambdas.isNotTranslate()) {
            TaggingList.AUTO_TRANSLATE.add(toolItem);
        }

        if (type == ToolType.SWORD) {
            TaggingList.SWORD.add(toolItem);
        }else if (type == ToolType.SHOVEL) {
            TaggingList.SHOVEL.add(toolItem);
        }else if (type == ToolType.AXE) {
            TaggingList.AXE.add(toolItem);
        }else if (type == ToolType.HOE) {
            TaggingList.HOE.add(toolItem);
        }else if (type == ToolType.PICKAXE) {
            TaggingList.PICKAXE.add(toolItem);
        }

    }

    public static BlockPos Vec3d_To_BlockPos(Vec3d pos) {
        return new BlockPos((int) pos.getX(),(int) pos.getY(),(int) pos.getZ());
    }

    public static Vec3d BlockPos_To_Vec3d(BlockPos pos) {
        return new Vec3d(pos.getX(),pos.getY(),pos.getZ());
    }

    public static Vec3d centerVec3D(Vec3d vec) {
       return vec.add(0.5,0.5,0.5);
    }

    public enum ToolType {
        PICKAXE,
        SWORD,
        AXE,
        SHOVEL,
        HOE
    }

    public enum TriggerType {
        USE_BLOCK,
        MINE_BLOCK,
        USE,
        HIT,
        PUNCH_AIR
    }

    /*
    SWORD
    SHOVEL
    PICKAXE
    AXE
    HOE
    */

    public static Item register(String name, ToolType type, ToolMaterial material, float baseDamage, float baseSpeed, Item.Settings settings, ToolLambdas lambdas) {
        Item res = null;

        if (type == ToolType.SWORD) {
            res = new ExperionSwordItem(material,
                    settings.attributeModifiers(SwordItem.createAttributeModifiers(material,(int) baseDamage,baseSpeed)),
                    lambdas
            );
        }else if (type == ToolType.AXE) {
            res = new ExperionAxeItem(material,
                    settings.attributeModifiers(AxeItem.createAttributeModifiers(material,baseDamage,baseSpeed)),
                    lambdas
            );
        }else if (type == ToolType.PICKAXE) {
            res = new ExperionPickaxeItem(material,
                    settings.attributeModifiers(PickaxeItem.createAttributeModifiers(material,baseDamage,baseSpeed)),
                    lambdas
            );
        }else if (type == ToolType.SHOVEL) {
            res = new ExperionShovelItem(material,
                    settings.attributeModifiers(ShovelItem.createAttributeModifiers(material,baseDamage,baseSpeed)),
                    lambdas
            );
        }else if (type == ToolType.HOE) {
            res = new ExperionHoeItem(material,
                    settings.attributeModifiers(HoeItem.createAttributeModifiers(material,baseDamage,baseSpeed)),
                    lambdas
            );
        }

        return ExperionRegistery.registerItem(res,name);
    }

}
