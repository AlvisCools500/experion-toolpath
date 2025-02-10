package com.github.experion.tools.item;

import com.github.experion.tools.initializer.ModTags;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;

public class ModToolMaterials {
    public static final ToolMaterial AZALEA = new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 60, 2.5F, 0.5F, 25, ModTags.Materials.AZALEA);
    public static final ToolMaterial FLINT = new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 75, 3.0F, 0.75F, 20, ModTags.Materials.FLINT);
    public static final ToolMaterial COPPER = new ToolMaterial(BlockTags.INCORRECT_FOR_STONE_TOOL, 200, 5.5F, 2F, 19, ModTags.Materials.COPPER);
    public static final ToolMaterial REDSTONE = new ToolMaterial(BlockTags.INCORRECT_FOR_IRON_TOOL, 350, 6.9F, 2.5F, 9, ModTags.Materials.REDSTONE);
    public static final ToolMaterial MODIFIED_IRON = new ToolMaterial(BlockTags.INCORRECT_FOR_IRON_TOOL, 240, 6.0f, 2.25f, 17, ItemTags.IRON_TOOL_MATERIALS);
    public static final ToolMaterial GILDED_IRON = new ToolMaterial(BlockTags.INCORRECT_FOR_IRON_TOOL, 150, 9.5f, 2.5f, 15, ItemTags.IRON_TOOL_MATERIALS);
    public static final ToolMaterial STEEL = new ToolMaterial(BlockTags.INCORRECT_FOR_IRON_TOOL, 628, 8.0f, 4.5f, 12, ModTags.Materials.STEEL);
    public static final ToolMaterial VAMPIRE_STEEL = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1690, 8.5f, 4.5f, 15, ModTags.Materials.VAMPIRE_STEEL);
    public static final ToolMaterial FROSTED_STEEL = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1777, 9.5f, 5f, 30, ModTags.Materials.FROSTED_STEEL);
}
