package com.github.experion.toolpath.items.tool_lambdas;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface OnPostMine {
    void trigger(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner);
}
