package com.github.experion.toolpath.items.custom_lambdas;

import com.github.experion.toolpath.items.tool_lambdas.TriggerLambdas;
import com.github.experion.toolpath.lib.ToolLib;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class NonLambdas implements TriggerLambdas {
    @Override
    public TypedActionResult<ItemStack> onUse(World world, PlayerEntity user, Hand hand) {
        return null;
    }

    @Override
    public String getLambdasName() {
        return "Non";
    }
}
