package com.github.experion.tools.initializer;

import com.github.experion.tools.ModInit;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup MOD_ITEM_GROUP = Registry.register(Registries.ITEM_GROUP, Identifier.of(ModInit.ID, "mod_item_group"), FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModTools.AZALEA_PICKAXE))
            .displayName(Text.literal("Experion Tool Path"))
            .entries((enabledfeatures, g) -> {
                g.add(ModTools.AZALEA_SWORD);
                g.add(ModTools.AZALEA_AXE);
                g.add(ModTools.AZALEA_PICKAXE);
                g.add(ModTools.AZALEA_SHOVEL);
                g.add(ModTools.AZALEA_HOE);

                g.add(ModTools.FLINT_SWORD);
                g.add(ModTools.FLINT_AXE);
                g.add(ModTools.FLINT_PICKAXE);
                g.add(ModTools.FLINT_SHOVEL);
                g.add(ModTools.FLINT_HOE);

                g.add(ModTools.COPPER_SWORD);
                g.add(ModTools.COPPER_AXE);
                g.add(ModTools.COPPER_PICKAXE);
                g.add(ModTools.COPPER_SHOVEL);
                g.add(ModTools.COPPER_HOE);

                g.add(ModTools.GILDED_IRON_SWORD);
                g.add(ModTools.GILDED_IRON_AXE);
                g.add(ModTools.GILDED_IRON_PICKAXE);
                g.add(ModTools.GILDED_IRON_SHOVEL);
                g.add(ModTools.GILDED_IRON_HOE);

                g.add(ModTools.STEEL_SWORD);
                g.add(ModTools.STEEL_AXE);
                g.add(ModTools.STEEL_PICKAXE);
                g.add(ModTools.STEEL_SHOVEL);
                g.add(ModTools.STEEL_HOE);

                g.add(ModTools.VAMPIRE_STEEL_SWORD);
                g.add(ModTools.VAMPIRE_STEEL_AXE);
                g.add(ModTools.VAMPIRE_STEEL_PICKAXE);
                g.add(ModTools.VAMPIRE_STEEL_SHOVEL);
                g.add(ModTools.VAMPIRE_STEEL_HOE);

                g.add(ModTools.FROSTED_STEEL_SWORD);
                g.add(ModTools.FROSTED_STEEL_AXE);
                g.add(ModTools.FROSTED_STEEL_PICKAXE);
                g.add(ModTools.FROSTED_STEEL_SHOVEL);
                g.add(ModTools.FROSTED_STEEL_HOE);

                g.add(ModItems.AZALEA_PICKER);
                g.add(ModItems.ICE_PICKER);
                g.add(ModItems.AZALEA_CLIPPINGS);
                g.add(ModItems.STEEL_INGOT);
                g.add(ModItems.VAMPIRE_STEEL_INGOT);
                g.add(ModItems.FROSTED_STEEL_INGOT);
            })
            .build()
    );

    public static void init() {}
}
