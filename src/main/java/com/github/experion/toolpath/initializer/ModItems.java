package com.github.experion.toolpath.initializer;

import com.github.experion.toolpath.items.ToolDeveloper;
import com.github.experion.toolpath.lib.Experion.ExperionRegistery;
import net.minecraft.item.Item;

public class ModItems {

    public static final Item TOOL_DEVELOPER = ExperionRegistery.registerItem(new ToolDeveloper(new Item.Settings()), "tool_developer");

    public static void init() {}
}
