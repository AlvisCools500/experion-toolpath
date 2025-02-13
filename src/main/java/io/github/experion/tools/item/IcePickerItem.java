package io.github.experion.tools.item;

import io.github.experion.tools.initializer.ModTags;
import net.fabricmc.fabric.api.item.v1.EnchantingContext;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class IcePickerItem extends Item {
    public IcePickerItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.literal("Used for obtaining Ice blocks").formatted(Formatting.GRAY));
        tooltip.add(Text.literal("(Without silk touch needed)").formatted(Formatting.GRAY));
        super.appendTooltip(stack, context, tooltip, type);
    }

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        if (!world.isClient()) {
            int dmg = 2;
            if (state.isIn(ModTags.Misc.ICE_PICKER_MINEABLE)) {
                Item item = state.getBlock().asItem();
                if (item != null) {
                    Vec3d v = new Vec3d(pos.getX(),pos.getY(),pos.getZ()).add(0.5,0.5,0.5);
                    ItemEntity itemEntity = new ItemEntity(world,v.getX(),v.getY(),v.getZ(), item.getDefaultStack());
                    world.spawnEntity(itemEntity);
                    dmg = 1;
                }
            }
            stack.damage(dmg, (ServerWorld) world, (ServerPlayerEntity) miner, item -> miner.sendEquipmentBreakStatus(item, EquipmentSlot.MAINHAND));
        }
        return false;
    }

    @Override
    public boolean canBeEnchantedWith(ItemStack stack, RegistryEntry<Enchantment> enchantment, EnchantingContext context) {
        return enchantment.matchesKey(Enchantments.EFFICIENCY) || enchantment.matchesKey(Enchantments.UNBREAKING);
    }
}
