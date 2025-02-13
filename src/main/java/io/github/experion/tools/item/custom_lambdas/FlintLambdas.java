package io.github.experion.tools.item.custom_lambdas;

import io.github.experion.tools.initializer.ModDataComponents;
import io.github.experion.tools.item.ToolIdList;
import io.github.experion.tools.item.tool_lambdas.ExistsLambdas;
import io.github.experion.tools.item.tool_lambdas.TriggerLambdas;
import io.github.experion.tools.lib.ToolAppend;
import io.github.experion.tools.lib.ToolLib;
import io.github.experion.tools.lib.ToolTypes;
import net.minecraft.block.BlockState;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class FlintLambdas implements TriggerLambdas {

    @Override
    public ExistsLambdas exists() {
        return new ExistsLambdas().PostHit().PostMine().UseBlock();
    }

    private void decrease_critical(ItemStack stack, World world, LivingEntity player) {
        if (stack.contains(ModDataComponents.FLINT_AMOUNTCRITICAL)) {
            int amount = stack.get(ModDataComponents.FLINT_AMOUNTCRITICAL);

            ServerPlayerEntity plr = (ServerPlayerEntity) player;
            ServerWorld serverWorld = (ServerWorld) world;

            if (amount > 0) {
                stack.set(ModDataComponents.FLINT_AMOUNTCRITICAL,Math.max(0,amount - 1));

                if (stack.get(ModDataComponents.FLINT_AMOUNTCRITICAL) == 0) {
                    world.playSound(null,new BlockPos((int) player.getPos().x, (int) player.getPos().y, (int) player.getPos().z),SoundEvents.BLOCK_BASALT_BREAK,SoundCategory.PLAYERS);
                }

                ToolLib.DamageToolAbility(stack,1,serverWorld,plr);

            }
        }
    }

    private void raycastitemparticle(ServerWorld serverWorld, PlayerEntity player, ItemStack stack) {
        HitResult raycast = player.raycast(20,0,false);
        ItemStackParticleEffect stackParti = new ItemStackParticleEffect(ParticleTypes.ITEM, stack);
        Vec3d mypos = raycast.getPos();
        serverWorld.spawnParticles(stackParti,mypos.getX(),mypos.getY(),mypos.z,3,0,0,0, 0.05);
    }

    @Override
    public ActionResult onUseBlock(ItemUsageContext context, ActionResult actionResult) {
        World world = context.getWorld();
        ItemStack stack = context.getStack();
        BlockPos pos = context.getBlockPos();
        PlayerEntity puncher = context.getPlayer();

        if (puncher.isSneaking()) {
            boolean criticalable = false;

            if (world.getBlockState(pos).isIn(BlockTags.PICKAXE_MINEABLE)) {
                criticalable = true;
                if (stack.contains(ModDataComponents.FLINT_AMOUNTCRITICAL)) {
                    if (stack.get(ModDataComponents.FLINT_AMOUNTCRITICAL) > 0) {
                        criticalable = false;
                    }
                }
            }


            if (criticalable) {
                if (!world.isClient) {
                    Random rand = new Random();

                    if (rand.nextInt(1,4) == 1) {
                        stack.set(ModDataComponents.FLINT_AMOUNTCRITICAL, rand.nextInt(10,30));

                        world.playSound(null,pos, SoundEvents.BLOCK_CHAIN_BREAK, SoundCategory.PLAYERS);
                    }else {
                        world.playSound(null,pos, SoundEvents.BLOCK_BAMBOO_WOOD_BREAK, SoundCategory.PLAYERS);
                    }

                    ServerWorld serverWorld = (ServerWorld) world;

                    ToolLib.DamageToolAbility(stack,1,serverWorld,(ServerPlayerEntity) puncher);

                    raycastitemparticle(serverWorld, puncher, stack);

                    return ActionResult.SUCCESS;
                } else {
                    return ActionResult.SUCCESS;
                }

            }
        }

        return actionResult;
    }

    @Override
    public Text getName(Text def, ItemStack stack) {
        if (stack.contains(ModDataComponents.FLINT_AMOUNTCRITICAL)) {
            if (stack.get(ModDataComponents.FLINT_AMOUNTCRITICAL) > 0) {
                return Text.literal("CRITICAL").formatted(Formatting.BOLD).formatted(Formatting.YELLOW).append(Text.literal(" ").formatted(Formatting.RESET)).append(def);
            }
        }

        return def;
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {

        int tooltype = ToolIdList.getToolType(stack.getItem());

        if (tooltype != ToolIdList.tooltypeid.HOE) {
            int amount = 0;

            if (stack.contains(ModDataComponents.FLINT_AMOUNTCRITICAL)) {
                amount = stack.get(ModDataComponents.FLINT_AMOUNTCRITICAL);
            }

            if (amount > 0) {

                ToolAppend.of(stack.getItem())
                        .line("Increases Damage", ToolAppend.TYPE.POSITIVE, true, List.of(ToolTypes.SWORD, ToolTypes.AXE))
                        .line("Increases Efficiency", ToolAppend.TYPE.POSITIVE, true, List.of(ToolTypes.AXE, ToolTypes.SHOVEL, ToolTypes.PICKAXE))
                        .line("Higher Critical, Higher Damage & Efficiency!", ToolAppend.TYPE.POSITIVE)
                        .line("2x durability use", ToolAppend.TYPE.NEGATIVE)
                        .offer(tooltip);

                tooltip.add(Text.literal("Criticals: " + amount).formatted(Formatting.YELLOW));
            }else {
                tooltip.add(Text.literal("? Crouch and right click on the rocks").formatted(Formatting.GRAY));
            }
        }


    }

    @Override
    public void onPostMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner, boolean succes) {
        if (!world.isClient) {
            decrease_critical(stack, world, miner);
        }
    }

    @Override
    public void onPostHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        World world = attacker.getWorld();

        if (!world.isClient()) {
            decrease_critical(attacker.getMainHandStack(),world,attacker);
        }
    }

    @Override
    public float setEfficiency(ItemStack stack, BlockState state, float default_float) {
        ToolComponent toolComponent = (ToolComponent)stack.get(DataComponentTypes.TOOL);

        if (toolComponent.isCorrectForDrops(state)) {
            if (stack.contains(ModDataComponents.FLINT_AMOUNTCRITICAL)) {
                if (stack.get(ModDataComponents.FLINT_AMOUNTCRITICAL) > 0) {
                    return default_float + (multiplyBase(default_float,(float) stack.get(ModDataComponents.FLINT_AMOUNTCRITICAL)) * 1.25f);
                }
            }
        }

        return default_float;
    }

    @Override
    public float setDamage(Entity target, float baseAttackDamage, DamageSource damageSource, float default_float) {
        ItemStack stack = damageSource.getWeaponStack();

        if (!stack.isEmpty()) {
            if (stack.contains(ModDataComponents.FLINT_AMOUNTCRITICAL)) {
                if (stack.get(ModDataComponents.FLINT_AMOUNTCRITICAL) > 0) {
                    return multiplyBase(baseAttackDamage,(float) stack.get(ModDataComponents.FLINT_AMOUNTCRITICAL));
                }
            }
        }

        return baseAttackDamage;
    }

    @Override
    public String getLambdasName() {
        return "Flint";
    }

    private float multiplyBase(float base, float amount) {
        float basePercent = 1f + (0.05f * amount);
        float CalBaseDmg = base * basePercent;
        return CalBaseDmg - base;
    }
}
