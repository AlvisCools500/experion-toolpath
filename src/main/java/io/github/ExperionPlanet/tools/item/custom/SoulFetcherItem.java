package io.github.ExperionPlanet.tools.item.custom;

import io.github.ExperionPlanet.tools.initializer.ModItems;
import io.github.ExperionPlanet.tools.lib.ToolLib;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class SoulFetcherItem extends Item {

    public SoulFetcherItem(Settings settings) {
        super(settings.maxDamage(69));
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        PlayerEntity plr = context.getPlayer();
        BlockPos pos = context.getBlockPos();
        ItemStack stack = context.getStack();

        if (!world.isClient()) {
            BlockState state = world.getBlockState(pos);

            PlayerInventory inv = plr.getInventory();

            ItemStack targetStack = ModItems.EMPTY_SOUL_JAR.getDefaultStack();

            if (state.isOf(Blocks.SOUL_SAND) && (inv.contains(targetStack) || plr.isInCreativeMode())) {
                ItemStack filledStack = ModItems.SOUL_JAR.getDefaultStack();
                ServerWorld serverWorld = (ServerWorld) world;

                BlockState targetState = Blocks.SOUL_SOIL.getDefaultState();

                serverWorld.setBlockState(pos, targetState);
                world.addBlockBreakParticles(pos, targetState);

                Vec3d dbPos = ToolLib.BlockPos_To_Vec3d(pos).add(0.5,1.1,0.5);

                serverWorld.spawnParticles(ParticleTypes.SOUL, dbPos.getX(),dbPos.getY(),dbPos.getZ(),1,0,0,0,0);
                serverWorld.playSound(null,pos, SoundEvents.BLOCK_SOUL_SAND_BREAK, SoundCategory.BLOCKS);

                inv.insertStack(filledStack);

                int numSlot = inv.getSlotWithStack(targetStack);
                if (numSlot != -1) {
                    inv.getStack(numSlot).decrement(1);
                }

                stack.damage(1,plr, EquipmentSlot.MAINHAND);

                return ActionResult.SUCCESS_SERVER;
            }else {
                if (!state.isOf(Blocks.SOUL_SAND)) {
                    plr.sendMessage(Text.literal("Use it on Soul Sand!").formatted(Formatting.RED),true);
                }else if (!inv.contains(targetStack)) {
                    plr.sendMessage(Text.literal("Empty soul jar required!").formatted(Formatting.RED), true);
                }
            }
        }

        return ActionResult.PASS;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.literal("Used for fetching soul from Soul Sands").formatted(Formatting.GRAY));
        super.appendTooltip(stack, context, tooltip, type);
    }
}
