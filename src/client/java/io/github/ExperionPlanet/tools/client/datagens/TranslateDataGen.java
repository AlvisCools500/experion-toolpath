package io.github.ExperionPlanet.tools.client.datagens;

import io.github.ExperionPlanet.tools.initializer.ModItems;
import io.github.ExperionPlanet.tools.initializer.TaggingList;
import io.github.ExperionPlanet.tools.lib.Experion.ExperionTranslatable;
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
        expe.translateItem(ModItems.VAMPIRE_STEEL_INGOT, "Vampire Steel Ingot");
        expe.translateItem(ModItems.FROSTED_STEEL_INGOT, "Frosted Steel Ingot");
        expe.translateItem(ModItems.SOUL_STEEL_INGOT, "Soul Steel Ingot");
        expe.translateItem(ModItems.ICE_PICKER,"Ice Picker");
        expe.translateItem(ModItems.SOUL_JAR, "?");
        expe.translateItem(ModItems.EMPTY_SOUL_JAR, "?");
        expe.translateItem(ModItems.SOUL_FETCHER, "?");
        translationBuilder.add("enchantment.experion_tool_path.durable_blessing", "Durable Blessing");
    }

    @Override
    public String getName() {
        return "ExperionTranslateGen";
    }
}
