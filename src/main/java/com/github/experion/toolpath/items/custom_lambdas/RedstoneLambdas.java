package com.github.experion.toolpath.items.custom_lambdas;

import com.github.experion.toolpath.ModInit;
import com.github.experion.toolpath.items.tool_lambdas.ExistsLambdas;
import com.github.experion.toolpath.items.tool_lambdas.TriggerLambdas;
import com.github.experion.toolpath.lib.EasyParticle;
import com.github.experion.toolpath.lib.ToolLib;
import net.minecraft.block.BlockState;
import net.minecraft.block.RedstoneWireBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class RedstoneLambdas implements TriggerLambdas {
    // SCRAPPED TOOL BUT MAY ADD LATER WHEN I CAN

    @Override
    public TypedActionResult<ItemStack> onUse(World world, PlayerEntity user, Hand hand) {
        return null;
    }

    @Override
    public void main_trigger(ItemStack stack, World world, Vec3d pos, LivingEntity Player, ToolLib.TriggerType triggerType) {
        if (!world.isClient()) {
            ServerWorld serverWorld = (ServerWorld) world;
            DustParticleEffect myDust = EasyParticle.getColoredDust(1f, 0f, 0f,1f);

            Vec3d respos = pos;

            if (triggerType == ToolLib.TriggerType.HIT) {
                respos.add(0.5,0.5,0.5);
            }

            EasyParticle.SummonSpreadedParticle(
                    serverWorld,
                    respos,
                    new Vec3d(0.0F,0.0F,0.0F),
                    serverWorld.getRandom().nextBetween(5,10),
                    myDust
            );

            BlockPos blockPos = new BlockPos((int) pos.getX(), (int) pos.getY(), (int) pos.getZ());

            for (BlockPos targpos : BlockPos.iterate(blockPos.add(-1,0,-1),blockPos.add(1,0,1))) {
                BlockState state = world.getBlockState(targpos);

                if (state.getBlock() instanceof RedstoneWireBlock) {
                    world.setBlockState(targpos,state.with(RedstoneWireBlock.POWER,15), 3);

                    //world.scheduleBlockTick(targpos,state.getBlock(),20);
                    ModInit.LOGGER.info("POWER");
                }
            }
        }
    }

    @Override
    public void onUseBlock(ItemUsageContext context, ActionResult actionResult) {
        if (actionResult == ActionResult.SUCCESS) {
            main_trigger(context.getStack(),context.getWorld(),
                    ToolLib.BlockPos_To_Vec3d(context.getBlockPos()).add(0,0.5,0),
                    context.getPlayer(),
                    ToolLib.TriggerType.USE_BLOCK);
        }
    }

    @Override
    public String getLambdasName() {
        return "Redstone";
    }
}
