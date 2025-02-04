package com.github.experion.toolpath.initializer;

import com.github.experion.toolpath.items.*;
import com.github.experion.toolpath.items.tool_lambdas.*;
import com.github.experion.toolpath.lib.BaseVals;

import com.github.experion.toolpath.lib.ToolLib;
import net.minecraft.item.*;
import net.minecraft.registry.tag.ItemTags;


public class ModTools {
    // FLINT
    public static final Item FLINT_SWORD = ToolLib.register("flint_sword", ToolLib.ToolType.SWORD, ModToolMaterials.FLINT, BaseVals.WOODEN_SWORD.getAttackDMG() + 1F, BaseVals.WOODEN_SWORD.getAttackSPEED(), null, new ToolLambdas().setLambda(CustomLambdas.FLINT_LAMBDAS).enableEditDamage().enableOnUseblock().enableOnPostHit().DurableAbility().setTag(ModTags.Tools.FLINT_TOOLS)
            .setRecipe(RecipeLambda.create()
                    .addLine(" B ")
                    .addLine(" B ")
                    .addLine(" # ")
                    .addChar('B', Items.FLINT)
                    .addChar('#', Items.STICK)
                    .setItemCriterion(Items.FLINT)
            ));
    public static final Item FLINT_AXE = ToolLib.register("flint_axe", ToolLib.ToolType.AXE, ModToolMaterials.FLINT, BaseVals.WOODEN_AXE.getAttackDMG() + 0.5F, BaseVals.WOODEN_AXE.getAttackSPEED(), null, new ToolLambdas().setLambda(CustomLambdas.FLINT_LAMBDAS).enableOnUseblock().enableOnPostMine().enableOnPostHit().enableEditEffeciency().enableEditDamage().DurableAbility().setTag(ModTags.Tools.FLINT_TOOLS)
            .setRecipe(RecipeLambda.create()
                    .addLine("BB ")
                    .addLine("BP ")
                    .addLine(" # ")
                    .addChar('B', Items.FLINT)
                    .addChar('P', ItemTags.PLANKS)
                    .addChar('#', Items.STICK)
                    .setItemCriterion(Items.FLINT)
            ));
    public static final Item FLINT_PICKAXE = ToolLib.register("flint_pickaxe", ToolLib.ToolType.PICKAXE, ModToolMaterials.FLINT, BaseVals.WOODEN_PICKAXE.getAttackDMG(), BaseVals.WOODEN_PICKAXE.getAttackSPEED(), null, new ToolLambdas().setLambda(CustomLambdas.FLINT_LAMBDAS).enableOnUseblock().enableOnPostMine().enableEditEffeciency().DurableAbility().setTag(ModTags.Tools.FLINT_TOOLS)
            .setRecipe(RecipeLambda.create()
                    .addLine("BPB")
                    .addLine(" # ")
                    .addLine(" # ")
                    .addChar('B', Items.FLINT)
                    .addChar('P', ItemTags.PLANKS)
                    .addChar('#', Items.STICK)
                    .setItemCriterion(Items.FLINT)
            ));
    public static final Item FLINT_SHOVEL = ToolLib.register("flint_shovel", ToolLib.ToolType.SHOVEL, ModToolMaterials.FLINT, BaseVals.WOODEN_SHOVEL.getAttackDMG(), BaseVals.WOODEN_SHOVEL.getAttackSPEED(), null, new ToolLambdas().setLambda(CustomLambdas.FLINT_LAMBDAS).enableOnUseblock().enableOnPostMine().enableEditEffeciency().DurableAbility().setTag(ModTags.Tools.FLINT_TOOLS)
            .setRecipe(RecipeLambda.create()
                    .addLine(" B ")
                    .addLine(" # ")
                    .addLine(" # ")
                    .addChar('B', Items.FLINT)
                    .addChar('#', Items.STICK)
                    .setItemCriterion(Items.FLINT)
            ));
    public static final Item FLINT_HOE = ToolLib.register("flint_hoe", ToolLib.ToolType.HOE, ModToolMaterials.FLINT, BaseVals.WOODEN_HOE.getAttackDMG(), BaseVals.WOODEN_HOE.getAttackSPEED(), null, new ToolLambdas().setLambda(CustomLambdas.FLINT_LAMBDAS).setTag(ModTags.Tools.FLINT_TOOLS)
            .setRecipe(RecipeLambda.create()
                    .addLine("BP ")
                    .addLine(" # ")
                    .addLine(" # ")
                    .addChar('B', Items.FLINT)
                    .addChar('P', ItemTags.PLANKS)
                    .addChar('#', Items.STICK)
                    .setItemCriterion(Items.FLINT)
            ));

    // AZALEA
    public static final Item AZALEA_SWORD = ToolLib.register("azalea_sword", ToolLib.ToolType.SWORD, ModToolMaterials.AZALEA, BaseVals.WOODEN_SWORD.getAttackDMG(),BaseVals.WOODEN_SWORD.getAttackSPEED(),null, new ToolLambdas().setLambda(CustomLambdas.AZALEA_LAMBDAS).setTag(ModTags.Tools.AZALEA_TOOLS).enableOnPostMine().enableOnPostHit().setRecipe(
            RecipeLambda.create()
                    .addLine(" A ")
                    .addLine(" A ")
                    .addLine(" T ")
                    .addChar('A',ModItems.AZALEA_CLIPPINGS)
                    .addChar('T',Items.WOODEN_SWORD)
                    .setItemCriterion(ModItems.AZALEA_CLIPPINGS)
    ));
    public static final Item AZALEA_AXE = ToolLib.register("azalea_axe", ToolLib.ToolType.AXE, ModToolMaterials.AZALEA, BaseVals.WOODEN_AXE.getAttackDMG(),BaseVals.WOODEN_AXE.getAttackSPEED(),null, new ToolLambdas().setLambda(CustomLambdas.AZALEA_LAMBDAS).setTag(ModTags.Tools.AZALEA_TOOLS).enableOnPostMine().enableOnPostHit().setRecipe(
            RecipeLambda.create()
                    .addLine("AA ")
                    .addLine("AT ")
                    .addChar('A',ModItems.AZALEA_CLIPPINGS)
                    .addChar('T',Items.WOODEN_AXE)
                    .setItemCriterion(ModItems.AZALEA_CLIPPINGS)
    ));
    public static final Item AZALEA_PICKAXE = ToolLib.register("azalea_pickaxe", ToolLib.ToolType.PICKAXE, ModToolMaterials.AZALEA, BaseVals.WOODEN_PICKAXE.getAttackDMG(),BaseVals.WOODEN_PICKAXE.getAttackSPEED(),null, new ToolLambdas().setLambda(CustomLambdas.AZALEA_LAMBDAS).setTag(ModTags.Tools.AZALEA_TOOLS).enableOnPostMine().enableOnPostHit().setRecipe(
            RecipeLambda.create()
                    .addLine("AAA")
                    .addLine(" T ")
                    .addChar('A',ModItems.AZALEA_CLIPPINGS)
                    .addChar('T',Items.WOODEN_PICKAXE)
                    .setItemCriterion(ModItems.AZALEA_CLIPPINGS)
    ));
    public static final Item AZALEA_SHOVEL = ToolLib.register("azalea_shovel", ToolLib.ToolType.SHOVEL, ModToolMaterials.AZALEA, BaseVals.WOODEN_SHOVEL.getAttackDMG(),BaseVals.WOODEN_SHOVEL.getAttackSPEED(),null, new ToolLambdas().setLambda(CustomLambdas.AZALEA_LAMBDAS).setTag(ModTags.Tools.AZALEA_TOOLS).enableOnPostMine().enableOnPostHit().setRecipe(
            RecipeLambda.create()
                    .addLine(" A ")
                    .addLine(" T ")
                    .addChar('A',ModItems.AZALEA_CLIPPINGS)
                    .addChar('T',Items.WOODEN_SHOVEL)
                    .setItemCriterion(ModItems.AZALEA_CLIPPINGS)
    ));
    public static final Item AZALEA_HOE = ToolLib.register("azalea_hoe", ToolLib.ToolType.HOE, ModToolMaterials.AZALEA, BaseVals.WOODEN_HOE.getAttackDMG(),BaseVals.WOODEN_HOE.getAttackSPEED(),null, new ToolLambdas().setLambda(CustomLambdas.AZALEA_LAMBDAS).setTag(ModTags.Tools.AZALEA_TOOLS).enableOnPostMine().enableOnPostHit().setRecipe(
            RecipeLambda.create()
                    .addLine("AA ")
                    .addLine(" T ")
                    .addChar('A',ModItems.AZALEA_CLIPPINGS)
                    .addChar('T',Items.WOODEN_HOE)
                    .setItemCriterion(ModItems.AZALEA_CLIPPINGS)
    ));

    //COPPER
    public static final Item COPPER_PICKAXE = ToolLib.register("copper_pickaxe", ToolLib.ToolType.PICKAXE, ModToolMaterials.COPPER, BaseVals.IRON_PICKAXE.getAttackSPEED(), BaseVals.IRON_PICKAXE.getAttackSPEED(), null, new ToolLambdas().enableEditEffeciency().enableOnPostMine().GetThundered().DisableDefaultModel().enableEditTexture().setLambda(CustomLambdas.COPPER_LAMBDAS).setTag(ModTags.Tools.COPPER_TOOLS).setRecipe(
            RecipeLambda.create()
                    .addLine("BBB")
                    .addLine(" # ")
                    .addLine(" # ")
                    .addChar('B',Items.COPPER_INGOT)
                    .addChar('#',Items.STICK)
                    .setItemCriterion(Items.COPPER_INGOT)
    ));

    public static void init() {}
}

/*
* SCRAPPED TOOLS
* // REDSTONE
    public static final Item REDSTONE_SWORD = ToolLib.register("redstone_sword", ToolLib.ToolType.SWORD, ModToolMaterials.REDSTONE, 3,-2.4f, null, new ToolLambdas().setLambda(CustomLambdas.REDSTONE_LAMBDAS).setTag(ModTags.Tools.REDSTONE_TOOLS).enableOnPostHit().setRecipe(
            RecipeLambda.create()
                    .addLine(" B ")
                    .addLine(" B ")
                    .addLine(" # ")
                    .addChar('B', Items.REDSTONE_BLOCK)
                    .addChar('#',Items.STICK)
    ));

    public static final Item REDSTONE_AXE = ToolLib.register("redstone_axe", ToolLib.ToolType.AXE, ModToolMaterials.REDSTONE, 6.0F, -3F, null, new ToolLambdas().setLambda(CustomLambdas.REDSTONE_LAMBDAS).setTag(ModTags.Tools.REDSTONE_TOOLS).enableOnPostMine().enableOnPostHit().setRecipe(
            RecipeLambda.create()
                    .addLine("BB ")
                    .addLine("B# ")
                    .addLine(" # ")
                    .addChar('B', Items.REDSTONE_BLOCK)
                    .addChar('#',Items.STICK)
    ));

    public static final Item REDSTONE_PICKAXE = ToolLib.register("redstone_pickaxe", ToolLib.ToolType.PICKAXE, ModToolMaterials.REDSTONE, 1.0F, -2.8F, null, new ToolLambdas().setLambda(CustomLambdas.REDSTONE_LAMBDAS).setTag(ModTags.Tools.REDSTONE_TOOLS).enableOnPostMine().enableOnPostHit().setRecipe(
            RecipeLambda.create()
                    .addLine("BBB")
                    .addLine(" # ")
                    .addLine(" # ")
                    .addChar('B', Items.REDSTONE_BLOCK)
                    .addChar('#',Items.STICK)
    ));

    public static final Item REDSTONE_SHOVEL = ToolLib.register("redstone_shovel", ToolLib.ToolType.SHOVEL, ModToolMaterials.REDSTONE, 1.6F, -2.9F, null, new ToolLambdas().setLambda(CustomLambdas.REDSTONE_LAMBDAS).setTag(ModTags.Tools.REDSTONE_TOOLS).enableOnPostMine().enableOnPostHit().setRecipe(
            RecipeLambda.create()
                    .addLine(" B ")
                    .addLine(" # ")
                    .addLine(" # ")
                    .addChar('B', Items.REDSTONE_BLOCK)
                    .addChar('#',Items.STICK)
    ));

    public static final Item REDSTONE_HOE = ToolLib.register("redstone_hoe", ToolLib.ToolType.HOE, ModToolMaterials.REDSTONE, -2,-1.0F, null, new ToolLambdas().setLambda(CustomLambdas.REDSTONE_LAMBDAS).setTag(ModTags.Tools.REDSTONE_TOOLS).enableOnUseblock().enableOnPostHit().setRecipe(
            RecipeLambda.create()
                    .addLine("BB ")
                    .addLine(" # ")
                    .addLine(" # ")
                    .addChar('B', Items.REDSTONE_BLOCK)
                    .addChar('#',Items.STICK)
    ));
* */