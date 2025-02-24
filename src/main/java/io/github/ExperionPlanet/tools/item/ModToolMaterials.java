package io.github.ExperionPlanet.tools.item;

import io.github.ExperionPlanet.tools.initializer.ModTags;
import io.github.ExperionPlanet.tools.lib.ToolLib;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;

public class ModToolMaterials {
    public static final ToolMaterial AZALEA = ToolLib.newToolMats(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 60, 2.5F, 0.5F, 25, ModTags.Materials.AZALEA);
    public static final ToolMaterial FLINT = ToolLib.newToolMats(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 75, 3.0F, 0.75F, 20, ModTags.Materials.FLINT);
    public static final ToolMaterial COPPER = ToolLib.newToolMats(BlockTags.INCORRECT_FOR_STONE_TOOL, 200, 5.5F, 2F, 19, ModTags.Materials.COPPER);
    public static final ToolMaterial REDSTONE = ToolLib.newToolMats(BlockTags.INCORRECT_FOR_IRON_TOOL, 350, 6.9F, 2.5F, 9, ModTags.Materials.REDSTONE);
    public static final ToolMaterial MODIFIED_IRON = ToolLib.newToolMats(BlockTags.INCORRECT_FOR_IRON_TOOL, 240, 6.0f, 2.25f, 17, ItemTags.IRON_TOOL_MATERIALS);
    public static final ToolMaterial GILDED_IRON = ToolLib.newToolMats(BlockTags.INCORRECT_FOR_IRON_TOOL, 150, 9.5f, 2.5f, 15, ItemTags.IRON_TOOL_MATERIALS);
    public static final ToolMaterial STEEL = ToolLib.newToolMats(BlockTags.INCORRECT_FOR_IRON_TOOL, 628, 8.0f, 4.5f, 12, ModTags.Materials.STEEL);
    public static final ToolMaterial VAMPIRE_STEEL = ToolLib.newToolMats(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1690, 8.5f, 4.5f, 15, ModTags.Materials.VAMPIRE_STEEL);
    public static final ToolMaterial FROSTED_STEEL = ToolLib.newToolMats(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1777, 9.5f, 5f, 30, ModTags.Materials.FROSTED_STEEL);
    public static final ToolMaterial OBSIDIAN = ToolLib.newToolMats(BlockTags.INCORRECT_FOR_DIAMOND_TOOL,2345, 8.5f, 5.0f, 20, ModTags.Materials.OBSIDIAN);
    public static final ToolMaterial SOUL_STEEL = ToolLib.newToolMats(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 895, 8f, 3.0F, 15, ModTags.Materials.SOUL_STEEL);
    public static final ToolMaterial BLAZING_MAGMA = ToolLib.newToolMats(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 500, 9f, 3.5f,20, ModTags.Materials.BLAZE);
}
