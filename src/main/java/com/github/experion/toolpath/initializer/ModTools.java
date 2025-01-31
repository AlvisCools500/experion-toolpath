package com.github.experion.toolpath.initializer;

import com.github.experion.toolpath.items.*;
import com.github.experion.toolpath.items.tool_lambdas.*;
import com.github.experion.toolpath.lib.BaseVals;
import com.github.experion.toolpath.lib.EasyParticle;
import com.github.experion.toolpath.lib.Experion.ExperionRegistery;

import com.github.experion.toolpath.lib.ToolLib;
import net.minecraft.item.*;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.Vec3d;


public class ModTools {
    // REDSTONE
    public static final Item REDSTONE_SWORD = ExperionRegistery.registerItem(
            new ExperionSwordItem(ModToolMaterials.REDSTONE,
                    new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.REDSTONE,3,-2.4f)),
                    new ToolLambdas()
                            .setMainTrigger(LambdaFunc.REDSTONE_MAINTRIG)
                            .enableOnPostHit()
                            .setTag(ModTags.Tools.REDSTONE_TOOLS)
            ),
            "redstone_sword");
    public static final Item REDSTONE_AXE = ExperionRegistery.registerItem(
            new ExperionAxeItem(
                    ModToolMaterials.REDSTONE,
                    new Item.Settings().attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterials.REDSTONE, 6.0F, -3F)),
                    new ToolLambdas()
                            .setMainTrigger(LambdaFunc.REDSTONE_MAINTRIG)
                            .enableOnPostHit()
                            .enableOnPostMine()
                            .setTag(ModTags.Tools.REDSTONE_TOOLS)
            ), "redstone_axe");
    public static final Item REDSTONE_PICKAXE = ExperionRegistery.registerItem(
            new ExperionPickaxeItem(
                    ModToolMaterials.REDSTONE,
                    new Item.Settings().attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.REDSTONE, 1.0F, -2.8F)),
                    new ToolLambdas()
                            .setMainTrigger(LambdaFunc.REDSTONE_MAINTRIG)
                            .enableOnPostHit()
                            .enableOnPostMine()
                            .setTag(ModTags.Tools.REDSTONE_TOOLS)
            ), "redstone_pickaxe");
    public static final Item REDSTONE_SHOVEL = ExperionRegistery.registerItem(
            new ExperionShovelItem(
                    ModToolMaterials.REDSTONE,
                    new Item.Settings().attributeModifiers(ShovelItem.createAttributeModifiers(ModToolMaterials.REDSTONE, 1.6F, -2.9F)),
                    new ToolLambdas()
                            .setMainTrigger(LambdaFunc.REDSTONE_MAINTRIG)
                            .enableOnPostHit()
                            .enableOnPostMine()
                            .setTag(ModTags.Tools.REDSTONE_TOOLS)
            ), "redstone_shovel");

    public static final Item REDSTONE_HOE = ExperionRegistery.registerItem(new ExperionHoeItem(ModToolMaterials.REDSTONE,
                    new Item.Settings().attributeModifiers(HoeItem.createAttributeModifiers(ModToolMaterials.REDSTONE,-2,-1.0F)),
                    new ToolLambdas()
                            .setMainTrigger(LambdaFunc.REDSTONE_MAINTRIG)
                            .setOnUseBlock(LambdaFunc.REDSTONE_ONUSEBLOCK)
                            .enableOnPostHit()
                            .enableOnUseblock()
                            .setTag(ModTags.Tools.REDSTONE_TOOLS)
            ),
            "redstone_hoe");

    // FLINT
    public static final Item FLINT_SWORD = ToolLib.register("flint_sword", ToolLib.ToolType.SWORD, ModToolMaterials.FLINT, BaseVals.WOODEN_SWORD.getAttackDMG() + 1F, BaseVals.WOODEN_SWORD.getAttackSPEED(), new Item.Settings(), new ToolLambdas().setTag(ModTags.Tools.FLINT_TOOLS)
            .setRecipe(RecipeLambda.create()
                    .addLine(" B ")
                    .addLine(" B ")
                    .addLine(" # ")
                    .addChar('B', Items.FLINT)
                    .addChar('#', Items.STICK)
                    .setItemCriterion(Items.FLINT)
            ));
    public static final Item FLINT_AXE = ToolLib.register("flint_axe", ToolLib.ToolType.AXE, ModToolMaterials.FLINT, BaseVals.WOODEN_AXE.getAttackDMG() + 0.5F, BaseVals.WOODEN_AXE.getAttackSPEED(), new Item.Settings(), new ToolLambdas().setTag(ModTags.Tools.FLINT_TOOLS)
            .setRecipe(RecipeLambda.create()
                    .addLine("BB ")
                    .addLine("BP ")
                    .addLine(" # ")
                    .addChar('B', Items.FLINT)
                    .addChar('P', ItemTags.PLANKS)
                    .addChar('#', Items.STICK)
                    .setItemCriterion(Items.FLINT)
            ));
    public static final Item FLINT_PICKAXE = ToolLib.register("flint_pickaxe", ToolLib.ToolType.PICKAXE, ModToolMaterials.FLINT, BaseVals.WOODEN_PICKAXE.getAttackDMG(), BaseVals.WOODEN_PICKAXE.getAttackSPEED(), new Item.Settings(), new ToolLambdas().setTag(ModTags.Tools.FLINT_TOOLS)
            .setRecipe(RecipeLambda.create()
                    .addLine("BPB")
                    .addLine(" # ")
                    .addLine(" # ")
                    .addChar('B', Items.FLINT)
                    .addChar('P', ItemTags.PLANKS)
                    .addChar('#', Items.STICK)
                    .setItemCriterion(Items.FLINT)
            ));
    public static final Item FLINT_SHOVEL = ToolLib.register("flint_shovel", ToolLib.ToolType.SHOVEL, ModToolMaterials.FLINT, BaseVals.WOODEN_SHOVEL.getAttackDMG(), BaseVals.WOODEN_SHOVEL.getAttackSPEED(), new Item.Settings(), new ToolLambdas().setTag(ModTags.Tools.FLINT_TOOLS)
            .setRecipe(RecipeLambda.create()
                    .addLine(" B ")
                    .addLine(" # ")
                    .addLine(" # ")
                    .addChar('B', Items.FLINT)
                    .addChar('#', Items.STICK)
                    .setItemCriterion(Items.FLINT)
            ));
    public static final Item FLINT_HOE = ToolLib.register("flint_hoe", ToolLib.ToolType.HOE, ModToolMaterials.FLINT, BaseVals.WOODEN_HOE.getAttackDMG(), BaseVals.WOODEN_HOE.getAttackSPEED(), new Item.Settings(), new ToolLambdas().setTag(ModTags.Tools.FLINT_TOOLS)
            .setRecipe(RecipeLambda.create()
                    .addLine("BP ")
                    .addLine(" # ")
                    .addLine(" # ")
                    .addChar('B', Items.FLINT)
                    .addChar('P', ItemTags.PLANKS)
                    .addChar('#', Items.STICK)
                    .setItemCriterion(Items.FLINT)
            ));

    static class LambdaFunc {
        // REDSTONE
        public static final ToolMainTrigger REDSTONE_MAINTRIG = (stack, world, pos, plr,trigType) -> {
            if (!world.isClient()) {
                ServerWorld serverWorld = (ServerWorld) world;
                DustParticleEffect myDust = EasyParticle.getColoredDust(1f, 0f, 0f,1f);
                EasyParticle.SummonSpreadedParticle(
                        serverWorld,
                        pos.add(0.5,0.5,0.5),
                        new Vec3d(0.0F,0.0F,0.0F),
                        serverWorld.getRandom().nextBetween(5,10),
                        myDust
                );
            }
        };

        public static final OnUseBlock REDSTONE_ONUSEBLOCK = (context, actionResult) -> {
            if (actionResult == ActionResult.SUCCESS) {
                LambdaFunc.REDSTONE_MAINTRIG.trigger(
                        context.getStack(),context.getWorld(),
                        ToolLib.BlockPos_To_Vec3d(context.getBlockPos()).add(0,0.5,0),
                        context.getPlayer(),
                        ToolLib.TriggerType.USE_BLOCK);
            }
        };
    }
    public static void init() {}
}
