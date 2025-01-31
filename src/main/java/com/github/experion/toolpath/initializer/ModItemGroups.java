package com.github.experion.toolpath.initializer;

import com.github.experion.toolpath.ModInit;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup MOD_ITEM_GROUP = Registry.register(Registries.ITEM_GROUP, Identifier.of(ModInit.ID, "mod_item_group"), FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModTools.REDSTONE_PICKAXE))
            .displayName(Text.literal("Experion Tool Path"))
            .entries((enabledfeatures, g) -> {
                g.add(ModTools.REDSTONE_SWORD);
                g.add(ModTools.REDSTONE_AXE);
                g.add(ModTools.REDSTONE_PICKAXE);
                g.add(ModTools.REDSTONE_SHOVEL);
                g.add(ModTools.REDSTONE_HOE);

                g.add(ModTools.FLINT_SWORD);
                g.add(ModTools.FLINT_AXE);
                g.add(ModTools.FLINT_PICKAXE);
                g.add(ModTools.FLINT_SHOVEL);
                g.add(ModTools.FLINT_HOE);

                g.add(ModItems.TOOL_DEVELOPER);
            })
            .build()
    );

    public static void init() {}
}
