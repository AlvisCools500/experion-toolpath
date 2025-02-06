package com.github.experion.tools.initializer;

import com.github.experion.tools.items.AzaleaPickerItem;
import com.github.experion.tools.lib.Experion.ExperionRegistery;
import net.minecraft.item.Item;

public class ModItems {
    public static final Item AZALEA_CLIPPINGS = ExperionRegistery.registerBasicItem("azalea_clippings",16);

    public static final Item AZALEA_PICKER = ExperionRegistery.registerItem(new AzaleaPickerItem(new Item.Settings().registryKey(ExperionRegistery.keyItem("azalea_picker"))),"azalea_picker");

    public static final Item STEEL_INGOT = ExperionRegistery.registerBasicItem("steel_ingot",64);

    public static void init() {}
}
