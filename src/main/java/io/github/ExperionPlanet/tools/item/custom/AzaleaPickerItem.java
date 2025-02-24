package io.github.ExperionPlanet.tools.item.custom;

import io.github.ExperionPlanet.tools.initializer.ModItems;
import io.github.ExperionPlanet.tools.lib.ToolLib;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.List;

public class AzaleaPickerItem extends Item {
    private static final HashMap<Identifier, Identifier> valid_use = new HashMap<>(){{
       final HashMap<Block, Block> temp = new HashMap<>(){{
          put(Blocks.FLOWERING_AZALEA,Blocks.AZALEA);
          put(Blocks.FLOWERING_AZALEA_LEAVES,Blocks.AZALEA_LEAVES);
       }};

       for (Block v : temp.keySet()) {
           put(Registries.BLOCK.getId(v),Registries.BLOCK.getId(temp.get(v)));
       }
    }};

    public AzaleaPickerItem(Settings settings) {
        super(settings.maxDamage(16));
    }

    @Override
    public Text getName(ItemStack stack) {
        Formatting[] formatAzalea = {Formatting.DARK_GREEN, Formatting.DARK_PURPLE};
        Formatting[] formatPicker = {Formatting.YELLOW, Formatting.GOLD};

        Text AZALEA = ToolLib.getCyclingColoredText("Azalea", formatAzalea);
        Text PICKER = ToolLib.getCyclingColoredText("Picker", formatPicker);

        return Text.empty().append(AZALEA).append(Text.literal(" ")).append(PICKER);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.literal("Used for picking Azalea Flowers").formatted(Formatting.GRAY).formatted(Formatting.ITALIC));

        super.appendTooltip(stack, context, tooltip, type);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();

        if (!world.isClient()) {
            BlockPos blockPos = context.getBlockPos();
            BlockState state = world.getBlockState(blockPos);
            Block block = state.getBlock();
            Identifier identifier = Registries.BLOCK.getId(block);

            if (valid_use.get(identifier) != null) {
                BlockState convertBlock = Registries.BLOCK.get(valid_use.get(identifier)).getDefaultState();
                world.setBlockState(blockPos,convertBlock);
                ToolLib.dropItem(world, ModItems.AZALEA_CLIPPINGS.getDefaultStack(), ToolLib.BlockPos_To_Vec3d(blockPos).add(0.5,0.5,0.5));
                world.playSound(null,blockPos, SoundEvents.ENTITY_SHEEP_SHEAR, SoundCategory.BLOCKS);

                ServerPlayerEntity plr = (ServerPlayerEntity) context.getPlayer();

                context.getStack().damage(1,(ServerWorld) world,plr, (item) -> plr.sendEquipmentBreakStatus(item, EquipmentSlot.MAINHAND));

                return ActionResult.SUCCESS;
            }
        }

        return ActionResult.PASS;
    }

}
