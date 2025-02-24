package io.github.ExperionPlanet.tools.client.datagens;

import io.github.ExperionPlanet.tools.client.item.properties.HasAmountProperty;
import io.github.ExperionPlanet.tools.client.item.properties.ToolBooleanFirstProperty;
import io.github.ExperionPlanet.tools.client.item.properties.ToolStatusProperty;
import io.github.ExperionPlanet.tools.initializer.ModItems;
import io.github.ExperionPlanet.tools.initializer.ModTools;
import io.github.ExperionPlanet.tools.initializer.TaggingList;
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


        itemModelGenerator.register(ModItems.AZALEA_PICKER, Models.HANDHELD);
        itemModelGenerator.register(ModItems.ICE_PICKER, Models.HANDHELD);
        itemModelGenerator.register(ModItems.SOUL_FETCHER,Models.HANDHELD);

        itemModelGenerator.register(ModItems.STEEL_INGOT,Models.GENERATED);
        itemModelGenerator.register(ModItems.VAMPIRE_STEEL_INGOT,Models.GENERATED);
        itemModelGenerator.register(ModItems.FROSTED_STEEL_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.SOUL_STEEL_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.AZALEA_CLIPPINGS, Models.GENERATED);

        itemModelGenerator.register(ModItems.EMPTY_SOUL_JAR, Models.GENERATED);
        itemModelGenerator.register(ModItems.SOUL_JAR, Models.GENERATED);

        for (Item v : List.of(ModTools.COPPER_SWORD,ModTools.COPPER_SHOVEL,ModTools.COPPER_AXE,ModTools.COPPER_PICKAXE,ModTools.COPPER_HOE)) {
            ItemModel.Unbaked normalbaked = ItemModels.basic(itemModelGenerator.upload(v,Models.HANDHELD));
            ItemModel.Unbaked oxidizedbaked = ItemModels.basic(itemModelGenerator.registerSubModel(v,"_oxidized",Models.HANDHELD));
            ItemModel.Unbaked thunderedbaked = ItemModels.basic(itemModelGenerator.registerSubModel(v,"_thundered", Models.HANDHELD));

            itemModelGenerator.output.accept(v, ItemModels.rangeDispatch(
                            new ToolStatusProperty(),
                            List.of(
                                    ItemModels.rangeDispatchEntry(normalbaked, 1.0f),
                                    ItemModels.rangeDispatchEntry(oxidizedbaked, 2.0f),
                                    ItemModels.rangeDispatchEntry(thunderedbaked,3.0f)
                            )
                    )
            );
        }

        for (Item v : List.of(ModTools.OBSIDIAN_SWORD,ModTools.OBSIDIAN_AXE,ModTools.OBSIDIAN_PICKAXE,ModTools.OBSIDIAN_SHOVEL)) {
            ItemModel.Unbaked normalbaked = ItemModels.basic(itemModelGenerator.upload(v,Models.HANDHELD));
            ItemModel.Unbaked brokenbaked = ItemModels.basic(itemModelGenerator.registerSubModel(v, "_broken",Models.HANDHELD));

            itemModelGenerator.output.accept(v, ItemModels.condition(new ToolBooleanFirstProperty(), brokenbaked, normalbaked));
        }

        for (Item v : List.of(ModTools.SOUL_STEEL_SWORD,ModTools.SOUL_STEEL_AXE,ModTools.SOUL_STEEL_PICKAXE,ModTools.SOUL_STEEL_SHOVEL)) {
            ItemModel.Unbaked normalbaked = ItemModels.basic(itemModelGenerator.upload(v,Models.HANDHELD));
            ItemModel.Unbaked activebaked = ItemModels.basic(itemModelGenerator.registerSubModel(v, "_active",Models.HANDHELD));

            itemModelGenerator.output.accept(v, ItemModels.condition(new HasAmountProperty(), activebaked, normalbaked));
        }
    }
}
