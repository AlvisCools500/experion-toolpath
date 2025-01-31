package com.github.experion.toolpath.items.tool_lambdas;

import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.TypedActionResult;

public interface OnUseBlock {
    void trigger(ItemUsageContext context, ActionResult actionResult);
}
