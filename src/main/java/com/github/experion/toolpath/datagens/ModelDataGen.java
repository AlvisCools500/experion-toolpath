package com.github.experion.toolpath.datagens;

import com.github.experion.toolpath.initializer.ModItems;
import com.github.experion.toolpath.initializer.ModTools;
import com.github.experion.toolpath.initializer.TaggingList;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.item.Item;

public class ModelDataGen extends FabricModelProvider {
    public ModelDataGen(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        for (Item v : TaggingList.DEFAULT_TOOLS) {
            itemModelGenerator.register(v, Models.HANDHELD);
        }
    }
}
