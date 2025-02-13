package io.github.experion.tools.initializer;

import io.github.experion.tools.item.AzaleaPickerItem;
import io.github.experion.tools.item.IcePickerItem;
import io.github.experion.tools.lib.Experion.ExperionRegistery;
import net.minecraft.item.Item;

public class ModItems {
    public static final Item AZALEA_CLIPPINGS = ExperionRegistery.registerBasicItem("azalea_clippings",16);

    public static final Item AZALEA_PICKER = ExperionRegistery.registerItem(new AzaleaPickerItem(new Item.Settings().maxCount(1).registryKey(ExperionRegistery.keyItem("azalea_picker"))),"azalea_picker");
    public static final Item ICE_PICKER = ExperionRegistery.registerItem(new IcePickerItem(new Item.Settings().maxDamage(100).maxCount(1).registryKey(ExperionRegistery.keyItem("ice_picker"))), "ice_picker");

    public static final Item STEEL_INGOT = ExperionRegistery.registerBasicItem("steel_ingot",64);
    public static final Item VAMPIRE_STEEL_INGOT = ExperionRegistery.registerBasicItem("vampire_steel_ingot",64);
    public static final Item FROSTED_STEEL_INGOT = ExperionRegistery.registerBasicItem("frosted_steel_ingot",64);

    public static void init() {}
}
