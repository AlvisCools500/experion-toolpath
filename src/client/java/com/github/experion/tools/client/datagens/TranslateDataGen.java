package com.github.experion.tools.client.datagens;

import com.github.experion.tools.initializer.ModItems;
import com.github.experion.tools.initializer.TaggingList;
import com.github.experion.tools.lib.Experion.ExperionTranslatable;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class TranslateDataGen extends FabricLanguageProvider {
    public TranslateDataGen(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup wrapperLookup, TranslationBuilder translationBuilder) {
        ExperionTranslatable expe = new ExperionTranslatable(translationBuilder);
        for (Item v : TaggingList.AUTO_TRANSLATE) {
            expe.translateItem(v, "?");
        }

        expe.translateItem(ModItems.AZALEA_CLIPPINGS, "Azalea Clippings");
        expe.translateItem(ModItems.STEEL_INGOT, "Steel Ingot");
        translationBuilder.add("enchantment.experion_tool_path.durable_blessing", "Durable Blessing");
    }

    @Override
    public String getName() {
        return "ExperionTranslateGen";
    }
}
