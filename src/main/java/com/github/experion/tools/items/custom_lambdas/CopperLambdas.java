package com.github.experion.tools.items.custom_lambdas;

import com.github.experion.tools.ModInit;
import com.github.experion.tools.initializer.ModDataComponents;
import com.github.experion.tools.items.ToolIdList;
import com.github.experion.tools.items.tool_lambdas.ExistsLambdas;
import com.github.experion.tools.items.tool_lambdas.TriggerLambdas;
import com.github.experion.tools.lib.ToolAppend;
import com.github.experion.tools.lib.ToolTypes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.network.ServerPlayerEntity;
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
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CopperLambdas implements TriggerLambdas {
    private final int maxtick = 100;

    @Override
    public ExistsLambdas exists() {
        return new ExistsLambdas().PostMine().PostHit().UseBlock().Use();
    }

    private int check_status(ItemStack stack) {
        if (stack.contains(ModDataComponents.TOOL_STATUS)) {
            return stack.get(ModDataComponents.TOOL_STATUS);
        }

        return 1;
    }

    private boolean isWaxed(ItemStack stack) {
        if (stack.contains(ModDataComponents.WAXED)) {
            return stack.get(ModDataComponents.WAXED);
        }

        return false;
    }

    private void reset(ItemStack stack, World world) {
        long clock = world.getTime();

        stack.set(ModDataComponents.TOOL_STATUS, 1);
        stack.set(ModDataComponents.TOOL_LONGTICK, clock);

        long resclock = world.getRandom().nextBetween(9000,12000);
        //long resclock = world.getRandom().nextBetween(40,100);

        stack.set(ModDataComponents.TOOL_NEXTTICK, resclock);


    }

    @Override
    public String getLambdasName() {
        return "Copper";
    }

    @Override
    public void onThundered(ItemStack stack, ServerWorld world, ServerPlayerEntity player) {
        stack.set(ModDataComponents.TOOL_STATUS,3);
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (check_status(stack) == 1) {
            tooltip.add(Text.literal("Try holding this while thunderstorm").formatted(Formatting.DARK_GRAY).formatted(Formatting.ITALIC));
            tooltip.add(Text.literal("(left hand works too)").formatted(Formatting.DARK_GRAY).formatted(Formatting.ITALIC));
        }

        if (check_status(stack) == 2) {
            tooltip.add(Text.literal("Why the hell this tool has oxidization too?!").formatted(Formatting.DARK_GRAY).formatted(Formatting.ITALIC));
            ToolAppend.of(stack.getItem())
                    .line("Put axe on left hand then right click", ToolAppend.TYPE.HINT)
                    .line("Put Honeycomb for stopping oxidization", ToolAppend.TYPE.HINT)
                    .line("Weaker than before", ToolAppend.TYPE.NEGATIVE);
        }

        if (check_status(stack) == 3) {
            tooltip.add(Text.literal("You might wanna tell your friend").formatted(Formatting.DARK_GRAY).formatted(Formatting.ITALIC));
            tooltip.add(Text.literal("to not close by you or else...").formatted(Formatting.DARK_GRAY).formatted(Formatting.ITALIC));
            tooltip.add(Text.literal("THUNDERED").formatted(Formatting.BOLD));

            ToolAppend.of(stack.getItem())
                    .line("Chain Damage", ToolAppend.TYPE.INSANE, ToolTypes.SWORD)
                    .line("Chain Mining", ToolAppend.TYPE.INSANE, true, ToolTypes.MINEABLE_TOOLS)
                    .line("Chain Farming", ToolAppend.TYPE.INSANE, ToolTypes.HOE)
                    .offer(tooltip);
        }else {
            if (isWaxed(stack)) {
                tooltip.add(Text.literal("Waxed").formatted(Formatting.YELLOW));
            }
        }



    }

    @Override
    public float setDamage(Entity target, float baseAttackDamage, DamageSource damageSource, float default_float) {
        if (check_status(damageSource.getWeaponStack()) == 2) {
            return baseAttackDamage - (baseAttackDamage * 0.5f);
        }

        return default_float;
    }

    @Override
    public float setEfficiency(ItemStack stack, BlockState state, float default_float) {
        if (check_status(stack) == 2) {
            return default_float * 0.5f;
        }

        return default_float;
    }

    @Override
    public void onPostMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner, boolean succes) {
        if (!world.isClient) {
            if (check_status(stack) == 3) {
                Block main = state.getBlock();

                ToolComponent toolComponent = stack.get(DataComponentTypes.TOOL);

                if (toolComponent.isCorrectForDrops(state)) {
                    for (int x = -1; x <= 1; x++) {
                        for (int y = -1; y <= 1; y++) {
                            for (int z = -1; z <= 1; z++) {
                                BlockPos respos = pos.add(x,y,z);

                                if (!respos.equals(pos)) {
                                    BlockState targstate = world.getBlockState(respos);

                                    if (targstate.isOf(main)) {
                                        if (new Random().nextInt(1,10) == 1) {
                                            world.breakBlock(respos,true,miner,1);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void ElectricHit(List<LivingEntity> eletrocuted, LivingEntity target, LivingEntity attacker) {
        World world = attacker.getWorld();

        Vec3d pos = target.getPos();
        int range = 1;
        Box area = new Box(pos.subtract(range,range,range), pos.add(range,range,range));

        List<LivingEntity> entities = world.getEntitiesByClass(LivingEntity.class, area, (v) -> !(v instanceof PlayerEntity));
        List<LivingEntity> targetlist = new ArrayList<>();

        if (!entities.isEmpty()) {
            for (LivingEntity v : entities) {
                v.damage((ServerWorld) world, world.getDamageSources().thorns(attacker),(float) world.getRandom().nextBetween(1,3));

                if (!eletrocuted.contains(v)) {
                    targetlist.add(v);
                    eletrocuted.add(v);
                }
            }
        }

        if (eletrocuted.size() < 10) {
            if (!targetlist.isEmpty()) {
                for (LivingEntity v : targetlist) {
                    ElectricHit(eletrocuted, v, attacker);
                }
            }
        }else {
            ModInit.LOGGER.info("OK STOP");
        }


    }

    @Override
    public void onPostHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        World world = attacker.getWorld();

        ModInit.LOGGER.info("TRYING HIT?");

        if (!world.isClient()) {
            if (check_status(stack) == 3) {
                List<LivingEntity> electrocuted = new ArrayList<>();

                ElectricHit(electrocuted,target,attacker);
            }

        }
    }

    @Override
    public ActionResult onUseBlock(ItemUsageContext context, ActionResult actionResult) {
        if (actionResult == ActionResult.SUCCESS) {
            World world = context.getWorld();

            if (!world.isClient()) {
                BlockPos pos = context.getBlockPos();

                for (int x = -1; x <= 1; x++) {
                    for (int z = -1; z <= 1; z++) {
                        BlockPos res = pos.add(x,0,z);

                        if (!res.equals(pos)) {
                            BlockState state = world.getBlockState(pos);

                            if (!state.isAir()) {
                                if (state.isOf(Blocks.GRASS_BLOCK) || state.isOf(Blocks.DIRT)) {
                                    if (new Random().nextInt(1,15) == 1) {
                                        world.setBlockState(res,Blocks.FARMLAND.getDefaultState());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return actionResult;
    }

    @Override
    public ActionResult onUse(World world, PlayerEntity user, Hand hand, ActionResult actionResult) {
        if (!world.isClient) {
            if (hand == Hand.MAIN_HAND) {
                ItemStack leftStack = user.getStackInHand(Hand.OFF_HAND);
                ItemStack stack = user.getStackInHand(hand);
                if (!leftStack.isEmpty()) {
                    Item item = leftStack.getItem();
                    if (item instanceof HoneycombItem && !isWaxed(stack)) {
                        leftStack.decrement(1);
                        stack.set(ModDataComponents.WAXED,true);
                        world.playSound(null,user.getX(),user.getY(),user.getZ(), SoundEvents.ITEM_HONEYCOMB_WAX_ON, SoundCategory.PLAYERS);
                    }else if (leftStack.isIn(ItemTags.AXES) && check_status(stack) == 2) {
                        leftStack.damage(1,(ServerWorld) world,(ServerPlayerEntity) user,v -> user.sendEquipmentBreakStatus(item, EquipmentSlot.OFFHAND));
                        reset(stack,world);
                        world.playSound(null,user.getX(),user.getY(),user.getZ(), SoundEvents.ITEM_AXE_WAX_OFF, SoundCategory.PLAYERS);
                    }
                }
            }
        }
        return actionResult;
    }

    @Override
    public void inventroytick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (world.isClient) {return;}
        if (!(entity instanceof PlayerEntity)) {return;}

        long ClockNow = world.getTime();

        if (!stack.contains(ModDataComponents.TOOL_STATUS) && !stack.contains(ModDataComponents.TOOL_LONGTICK) && !stack.contains(ModDataComponents.TOOL_NEXTTICK)) {
            reset(stack,world);
        }

        if (check_status(stack) == 1) {
            if (!isWaxed(stack)) {
                long CurrClock = stack.get(ModDataComponents.TOOL_LONGTICK);
                if (ClockNow - CurrClock >= stack.get(ModDataComponents.TOOL_NEXTTICK)) {
                    stack.set(ModDataComponents.TOOL_STATUS, 2);
                }
            }

            if (world.isThundering() && ClockNow % 100 == 0) {
                PlayerEntity player = (PlayerEntity) entity;

                ItemStack mainstack = player.getMainHandStack();
                ItemStack offstack = player.getOffHandStack();

                if (mainstack == stack || offstack == stack) {
                    float chance = world.random.nextFloat();

                    ModInit.LOGGER.info("float " + chance);

                    if (chance < 0.1f) {
                        LightningEntity lightning = EntityType.LIGHTNING_BOLT.create(world,SpawnReason.TRIGGERED);

                        if (lightning != null) {
                            lightning.refreshPositionAfterTeleport(entity.getPos());
                            ((ServerWorld) world).spawnEntity(lightning);
                        }
                    }
                }
            }
        }
    }

}
