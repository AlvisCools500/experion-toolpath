package com.github.experion.tools.item.custom_lambdas;

import com.github.experion.tools.initializer.ModParticles;
import com.github.experion.tools.initializer.ModTags;
import com.github.experion.tools.item.tool_lambdas.ExistsLambdas;
import com.github.experion.tools.item.tool_lambdas.TriggerLambdas;
import com.github.experion.tools.lib.ToolAppend;
import com.github.experion.tools.lib.ToolTypes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class GildedIronLambdas implements TriggerLambdas {
    private static final List<Block> FORTUNE_BLOCK = new ArrayList<>(){{
        add(Blocks.GOLD_ORE);
        add(Blocks.DEEPSLATE_GOLD_ORE);
        add(Blocks.NETHER_GOLD_ORE);
    }};

    @Override
    public ExistsLambdas exists() {
        return new ExistsLambdas().PostMine().PostHit();
    }

    @Override
    public String getLambdasName() {
        return "GildedIron";
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
        ToolAppend.of(stack.getItem())
                .line("More damage to Piglins", ToolAppend.TYPE.POSITIVE, true, List.of(ToolTypes.SWORD,ToolTypes.AXE))
                .line("Mining gold ores drops gold nugget", ToolAppend.TYPE.POSITIVE, ToolTypes.PICKAXE)
                .offer(tooltip);
    }

    @Override
    public void onPostHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        World world = attacker.getWorld();

        if (!world.isClient()) {
            Vec3d v = target.getPos();

            ((ServerWorld) world).spawnParticles(ModParticles.GILDED_SHINE,v.getX(),v.getY(),v.getZ(),world.getRandom().nextBetween(4,8),0.5,0.5,0.5,0);
        }
    }

    @Override
    public void onPostMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner, boolean success) {
        if (!world.isClient()) {
            ServerWorld serverWorld = (ServerWorld) world;

            Vec3d v = new Vec3d(pos.getX(),pos.getY(),pos.getZ()).add(0.5,0.5,0.5);
            serverWorld.spawnParticles(ModParticles.GILDED_SHINE,v.getX(),v.getY(),v.getZ(),world.getRandom().nextBetween(4,8),0.5,0.5,0.5,0);

            if (success) {
                if (state.isIn(ModTags.Misc.GILDED_FORTUNE)) {
                    ItemEntity itemEntity = new ItemEntity(serverWorld,v.getX(),v.getY(),v.getZ(),Items.GOLD_NUGGET.getDefaultStack());
                    serverWorld.spawnEntity(itemEntity);
                }
            }
        }
    }

    @Override
    public float setDamage(Entity target, float baseAttackDamage, DamageSource damageSource, float default_float) {
        if (target.getType().isIn(ModTags.Misc.PIGLINS)) {
            return (baseAttackDamage * 1.5f) - baseAttackDamage;
        }
        return default_float;
    }
}
