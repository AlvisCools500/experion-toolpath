package com.github.experion.toolpath.initializer;

import com.github.experion.toolpath.items.AzaleaPickerItem;
import com.github.experion.toolpath.items.ToolDeveloper;
import com.github.experion.toolpath.lib.Experion.ExperionRegistery;
import net.minecraft.item.Item;

public class ModItems {

    public static final Item TOOL_DEVELOPER = ExperionRegistery.registerItem(new ToolDeveloper(new Item.Settings()), "tool_developer");
    public static final Item AZALEA_CLIPPINGS = ExperionRegistery.registerBasicItem("azalea_clippings",16);

    public static final Item AZALEA_PICKER = ExperionRegistery.registerItem(new AzaleaPickerItem(new Item.Settings()),"azalea_picker");

    public static void init() {}
}
