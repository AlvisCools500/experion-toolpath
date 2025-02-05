package com.github.experion.toolpath.lib;

import com.github.experion.toolpath.ModInit;
import com.github.experion.toolpath.initializer.ModEnchantments;
import com.github.experion.toolpath.initializer.TaggingList;
import com.github.experion.toolpath.items.tool_lambdas.ToolLambdas;
import com.github.experion.toolpath.items.tools.*;
import com.github.experion.toolpath.lib.Experion.ExperionRegistery;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.*;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;


public class ToolLib {

    private static final Logger LOGGER = LoggerFactory.getLogger(ToolLib.class);

    public static void onAdded(Item toolItem, ToolType type, ToolLambdas toolLambdas) {
        TaggingList.DEFAULT_TOOLS.add(toolItem);

        if (!toolLambdas.isNotTranslate()) {
            TaggingList.AUTO_TRANSLATE.add(toolItem);
        }

        if (toolLambdas.defaultModel) {
            TaggingList.DEFAULT_MODEL.add(toolItem);
        }

        if (toolLambdas.durableAbility) {
            TaggingList.DURABLE_ABILITY.add(toolItem);
        }

        if (toolLambdas.edit_texture) {
            TaggingList.EDIT_TEXTURE.add(toolItem);
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

    public static Item register(String name, ToolType type, ToolMaterial material, float baseDamage, float baseSpeed, Item.Settings setting, ToolLambdas lambdas) {
        Item.Settings settings = setting;

        if (setting == null) {
            settings = new Item.Settings();
        }

        Item res = null;

        if (type == ToolType.SWORD) {
            res = new ExperionSwordItem(material,settings.registryKey(ExperionRegistery.keyItem(name)),lambdas,baseDamage,baseSpeed);
        }else if (type == ToolType.AXE) {
            res = new ExperionAxeItem(material,settings.registryKey(ExperionRegistery.keyItem(name)),lambdas,baseDamage,baseSpeed);
        }else if (type == ToolType.PICKAXE) {
            res = new ExperionPickaxeItem(material,settings.registryKey(ExperionRegistery.keyItem(name)),lambdas,baseDamage,baseSpeed);
        }else if (type == ToolType.SHOVEL) {
            res = new ExperionShovelItem(material,settings.registryKey(ExperionRegistery.keyItem(name)),lambdas,baseDamage,baseSpeed);
        }else if (type == ToolType.HOE) {
            res = new ExperionHoeItem(material,settings.registryKey(ExperionRegistery.keyItem(name)),lambdas,baseDamage,baseSpeed);
        }

        return ExperionRegistery.registerItem(res,name);
    }

    public static void dropItem(World world, ItemStack stack, Vec3d pos) {
        if (!world.isClient()) {
            ItemEntity itemEntity = new ItemEntity(world,pos.getX(),pos.getY(),pos.getZ(),stack);

            world.spawnEntity(itemEntity);
        }
    }

    public static Text getCyclingColoredText(String input, Formatting[] colors) {
        MutableText result = Text.empty();
        for (int i = 0; i < input.length(); i++) {
            Formatting color = colors[i % colors.length]; // Cycle through colors
            result.append(Text.literal(String.valueOf(input.charAt(i))).setStyle(Style.EMPTY.withColor(color)));
        }

        return result;
    }

    public static void DamageToolAbility(ItemStack stack, int amount, ServerWorld serverWorld, ServerPlayerEntity player) {
        boolean candamage = true;

        RegistryEntry<Enchantment> DurableEntry = serverWorld.getRegistryManager().getOrThrow(RegistryKeys.ENCHANTMENT).getOrThrow(ModEnchantments.DURABLE_BLESSING_KEY);

        if (DurableEntry != null) {
            int level = EnchantmentHelper.getLevel(DurableEntry,stack);

            if (level > 0) {
                if (level < 5) {
                    Random rand = new Random();

                    ModInit.LOGGER.info("LVL: " + level);

                    if (rand.nextInt(0,level) != 0) {
                        candamage = false;
                    }else {
                        LOGGER.info("FAIL TO BLESS");
                    }
                }else {
                    candamage = false;
                }
            }
        }

        if (candamage) {
            stack.damage(amount,serverWorld,player,(item) -> player.sendEquipmentBreakStatus(item, EquipmentSlot.MAINHAND));
        }else {
            LOGGER.info("BLESSED");
        }
    }

}
