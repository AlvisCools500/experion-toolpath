package io.github.ExperionPlanet.tools.item.custom_lambdas;

import io.github.ExperionPlanet.tools.ModInit;
import io.github.ExperionPlanet.tools.initializer.ModDataComponents;
import io.github.ExperionPlanet.tools.initializer.ModParticles;
import io.github.ExperionPlanet.tools.initializer.ModSoundEvents;
import io.github.ExperionPlanet.tools.item.ToolIdList;
import io.github.ExperionPlanet.tools.item.tool_lambdas.ExistsLambdas;
import io.github.ExperionPlanet.tools.item.tool_lambdas.TriggerLambdas;
import io.github.ExperionPlanet.tools.lib.FastProperty;
import io.github.ExperionPlanet.tools.lib.ToolAppend;
import io.github.ExperionPlanet.tools.lib.ToolLib;
import io.github.ExperionPlanet.tools.lib.ToolTypes;
import net.minecraft.block.BlockState;
import net.minecraft.component.ComponentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

import java.util.List;

public class AquamondLambdas implements TriggerLambdas {
    private static final ComponentType<Boolean> IS_UNDERWATER = ModDataComponents.TOOL_BOOLEAN_FIRST;
    private static final ComponentType<Boolean> IS_PENALTY = ModDataComponents.TOOL_BOOLEAN_SECOND;
    private static final ComponentType<Boolean> IS_ACTIVE = ModDataComponents.TOOL_BOOLEAN_THIRD;
    private static final ComponentType<Integer> BUBBLES = ModDataComponents.TOOL_COUNTER_FIRST;
    private static final ComponentType<Long> ACTIVE_TICK = ModDataComponents.TOOL_LONGTICK;

    private static final long MAX_DURATION = 60;
    private static final int MAX_BUBBLES = 10;

    private static boolean isUnderwater(ItemStack stack) {
        return FastProperty.boolVal(stack, IS_UNDERWATER, false);
    }

    private static boolean isPenalty(ItemStack stack) {
        return FastProperty.boolVal(stack, IS_PENALTY, false);
    }

    private static boolean isActive(ItemStack stack) {
        return FastProperty.boolVal(stack, IS_ACTIVE, false);
    }

    private static long getActiveTick(ItemStack stack) {
        return FastProperty.longVal(stack, ACTIVE_TICK, 0);
    }

    private static int getBubbles(ItemStack stack) {
        return FastProperty.intVal(stack, BUBBLES, 0);
    }

    private static void addBubbles(ItemStack stack, World world, Vec3d pos) {
        if (!stack.contains(BUBBLES)) {
            stack.set(BUBBLES, 0);
        }
        ServerWorld serverWorld = (ServerWorld) world;

        if (stack.get(BUBBLES) < MAX_BUBBLES) {
            stack.set(BUBBLES, stack.get(BUBBLES) + 1);
            serverWorld.spawnParticles(ModParticles.AQUAMOND_BUBBLES_POP, pos.getX(),pos.getY(),pos.getZ(), 1,0,0,0, 0);
            serverWorld.spawnParticles(ParticleTypes.BUBBLE, pos.getX(),pos.getY(),pos.getZ(), 5,0,0,0, 0.5);
            serverWorld.playSound(null, pos.getX(),pos.getY(),pos.getZ(), ModSoundEvents.AQUAMOND_BUBBLES, SoundCategory.PLAYERS);
        }
    }

    private static boolean bubblesChance(World world) {
        Random rand = world.getRandom();
        if (rand.nextFloat() <= 0.3f) {
            return true;
        }
        return false;
    }

    @Override
    public ExistsLambdas exists() {
        return TriggerLambdas.super.exists().PostHit().PostMine().Use();
    }

    @Override
    public void onPostHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        World world = attacker.getWorld();
        if (!world.isClient()) {
            if (bubblesChance(world)) {
                addBubbles(stack, world, target.getPos().add(0,target.getHeight() / 2.0,0));
            }
        }
    }

    @Override
    public void onPostMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner, boolean success) {
        if (!world.isClient()) {
            if (bubblesChance(world)) {
                addBubbles(stack, world, ToolLib.BlockPos_To_Vec3d(pos).add(0.5,0.5,0.5));
            }
        }
    }

    @Override
    public ActionResult onUse(World world, PlayerEntity user, Hand hand, ActionResult actionResult) {
        if (!world.isClient()) {
            ItemStack stack = user.getStackInHand(hand);
            if (user.isSneaking()) {
                stack.set(BUBBLES, 10);
            }
            if (isUnderwater(stack) && getBubbles(stack) >= MAX_BUBBLES && !isActive(stack)) {
                ServerWorld serverWorld = (ServerWorld) world;
                if (ToolTypes.MINEABLE_TOOLS.contains(ToolIdList.getToolType(stack.getItem()))) {
                    stack.set(ACTIVE_TICK, world.getTime());
                    stack.set(IS_ACTIVE, true);
                }else if (ToolIdList.getToolType(stack.getItem()) == ToolTypes.SWORD) {
                    ModInit.LOGGER.info("YEAH");
                    Vec3d origin = user.getPos();
                    int radius = 1;
                    Box box = new Box(origin.x - radius, origin.y - radius, origin.z - radius,
                                    origin.x + radius, origin.y + radius, origin.z + radius);

                    List<LivingEntity> targList = world.getEntitiesByClass(LivingEntity.class, box, livingEntity -> livingEntity.isAlive() && livingEntity != user);
                    for (LivingEntity v : targList) {
                        double x = v.getX() - origin.getX();
                        double y = v.getY() - origin.getY();
                        double z = v.getZ() - origin.getZ();
                        double dist = Math.sqrt(x * x + y * y + z * z);

                        double strength = 0.5;

                        if (dist > 0.01) {
                            v.addVelocity(
                                    (x / dist) * strength,
                                    (y / dist) * strength,
                                    (z / dist) * strength
                            );
                        }
                        v.damage(serverWorld, user.getDamageSources().playerAttack(user),1);
                    }
                }
                stack.set(BUBBLES, 0);
                serverWorld.playSound(null, user.getX(),user.getY(),user.getZ(), SoundEvents.BLOCK_AMETHYST_CLUSTER_BREAK, SoundCategory.PLAYERS);
                serverWorld.spawnParticles(ParticleTypes.BUBBLE, user.getX(),user.getY(),user.getZ(),12, 0,0,0,0.5);

                return ActionResult.SUCCESS_SERVER;
            }
        }

        return ActionResult.PASS;
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (isUnderwater(stack)) {
            tooltip.add(Text.literal("Bubbles " + getBubbles(stack) + "/" + MAX_BUBBLES).formatted(Formatting.AQUA));

            if (isActive(stack)) {
                tooltip.add(Text.literal("BUBBLES ACTIVATED").formatted(Formatting.LIGHT_PURPLE));
            }else {
                if (getBubbles(stack) >= MAX_BUBBLES) {
                    tooltip.add(Text.literal("RIGHT CLICK TO ACTIVATE!").formatted(Formatting.YELLOW));
                }
            }

            ToolAppend.of(stack.getItem())
                    .line("Can mining underwater", ToolAppend.TYPE.POSITIVE, true, ToolTypes.MINEABLE_TOOLS)
                    .line("More damage to underwater mobs", ToolAppend.TYPE.POSITIVE, ToolTypes.SWORD)
                    .offer(tooltip);

            if (isActive(stack)) {
                ToolAppend.of(stack.getItem())
                        .line("Mining faster", ToolAppend.TYPE.POSITIVE, true, ToolTypes.MINEABLE_TOOLS)
                        .offer(tooltip);
            }
        }else {
            ToolAppend.of(stack.getItem())
                    .line("Try use this in underwater", ToolAppend.TYPE.HINT)
                    .line("2x durability use", ToolAppend.TYPE.NEGATIVE)
                    .offer(tooltip);
        }
    }

    @Override
    public float setEfficiency(ItemStack stack, BlockState state, float default_float) {
        if (isUnderwater(stack) && isPenalty(stack)) {
            return default_float * 5.0f;
        }
        return default_float;
    }

    @Override
    public void inventroytick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient()) {
            if (entity instanceof PlayerEntity) {
                PlayerEntity plr = (PlayerEntity) entity;
                ServerWorld serverWorld = (ServerWorld) world;

                if (plr.isSubmergedIn(FluidTags.WATER) && !isUnderwater(stack)) {
                    stack.set(IS_UNDERWATER, true);
                }else {
                    if (!plr.isSubmergedIn(FluidTags.WATER) && isUnderwater(stack)) {
                        stack.set(IS_UNDERWATER, false);
                    }
                }
                if (plr.getAttributeInstance(EntityAttributes.SUBMERGED_MINING_SPEED).getValue() < 1.0f) {
                    if (!isPenalty(stack)) {
                        stack.set(IS_PENALTY, true);
                    }
                }else {
                    if (isPenalty(stack)) {
                        stack.set(IS_PENALTY, false);
                    }
                }

                if (isActive(stack)) {
                    long ticknow = world.getTime();
                    long tickbef = getActiveTick(stack);

                    if (tickbef > 0) {
                        if (ticknow - tickbef >= MAX_DURATION) {
                            stack.set(IS_ACTIVE, false);
                            serverWorld.playSound(null, plr.getX(),plr.getY(),plr.getZ(), SoundEvents.BLOCK_AMETHYST_BLOCK_BREAK, SoundCategory.PLAYERS);
                            serverWorld.spawnParticles(ParticleTypes.WHITE_ASH, plr.getX(),plr.getY(),plr.getZ(),12, 0,0,0,0.5);
                        }
                    }
                }
            }
        }
    }

    @Override
    public String getLambdasName() {
        return "Aquamond";
    }
}
