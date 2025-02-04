package com.github.experion.toolpath.items;

import com.github.experion.toolpath.initializer.ModItems;
import com.github.experion.toolpath.initializer.ModTags;
import com.github.experion.toolpath.lib.MatsVals;
import com.github.experion.toolpath.lib.ToolVals;
import com.google.common.base.Suppliers;
import net.minecraft.block.Block;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;

import java.util.Objects;
import java.util.function.Supplier;

public class ModToolMaterials {
    public static final ToolMaterial AZALEA = new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 60, 2.5F, 0.5F, 25, ModTags.Materials.AZALEA);
    public static final ToolMaterial FLINT = new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 75, 3.0F, 0.75F, 20, ModTags.Materials.FLINT);
    public static final ToolMaterial COPPER = new ToolMaterial(BlockTags.INCORRECT_FOR_STONE_TOOL, 200, 5.5F, 2F, 19, ModTags.Materials.COPPER);
    public static final ToolMaterial REDSTONE = new ToolMaterial(BlockTags.INCORRECT_FOR_IRON_TOOL, 350, 6.9F, 2.5F, 9, ModTags.Materials.REDSTONE);

}
