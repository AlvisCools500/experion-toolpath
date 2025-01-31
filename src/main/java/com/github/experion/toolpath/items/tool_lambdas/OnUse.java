package com.github.experion.toolpath.items.tool_lambdas;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public interface OnUse {
    TypedActionResult<ItemStack> trigger(World world, PlayerEntity user, Hand hand);
}
