package com.github.experion.tools.client.datagens;

import com.github.experion.tools.client.item.properties.ToolStatusProperty;
import com.github.experion.tools.initializer.ModItems;
import com.github.experion.tools.initializer.ModTools;
import com.github.experion.tools.initializer.TaggingList;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.*;
import net.minecraft.client.render.item.model.ItemModel;
import net.minecraft.item.Item;

import java.util.List;

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

        itemModelGenerator.register(ModItems.STEEL_INGOT,Models.GENERATED);
        itemModelGenerator.register(ModItems.VAMPIRE_STEEL_INGOT,Models.GENERATED);

        for (Item v : List.of(ModTools.COPPER_SWORD,ModTools.COPPER_SHOVEL,ModTools.COPPER_AXE,ModTools.COPPER_PICKAXE,ModTools.COPPER_HOE)) {
            copper_generate(itemModelGenerator,v);
        }
    }

    private void copper_generate(ItemModelGenerator generator, Item item) {
        ItemModel.Unbaked normalbaked = ItemModels.basic(generator.upload(item,Models.HANDHELD));
        ItemModel.Unbaked oxidizedbaked = ItemModels.basic(generator.registerSubModel(item,"_oxidized",Models.HANDHELD));
        ItemModel.Unbaked thunderedbaked = ItemModels.basic(generator.registerSubModel(item,"_thundered", Models.HANDHELD));

        generator.output.accept(item, ItemModels.rangeDispatch(
                        new ToolStatusProperty(),
                List.of(
                        ItemModels.rangeDispatchEntry(normalbaked, 1.0f),
                        ItemModels.rangeDispatchEntry(oxidizedbaked, 2.0f),
                        ItemModels.rangeDispatchEntry(thunderedbaked,3.0f)
                )
                )
        );
    }




}
