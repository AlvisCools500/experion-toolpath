package com.github.experion.toolpath.client.datagens;

import com.github.experion.toolpath.initializer.ModDataComponents;
import com.github.experion.toolpath.initializer.ModItems;
import com.github.experion.toolpath.initializer.ModTools;
import com.github.experion.toolpath.initializer.TaggingList;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.ItemModels;
import net.minecraft.client.data.Models;
import net.minecraft.client.render.item.model.special.SpecialModelTypes;
import net.minecraft.client.render.item.property.numeric.NumericProperties;
import net.minecraft.client.render.item.property.numeric.NumericProperty;
import net.minecraft.item.Item;
import net.minecraft.state.property.BooleanProperty;

public class ModelDataGen extends FabricModelProvider {
    public ModelDataGen(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        for (Item v : TaggingList.DEFAULT_MODEL) {
            itemModelGenerator.register(v, Models.HANDHELD);
        }

        itemModelGenerator.register(ModItems.AZALEA_CLIPPINGS, Models.GENERATED);
        itemModelGenerator.register(ModItems.AZALEA_PICKER, Models.HANDHELD);
    }
}
