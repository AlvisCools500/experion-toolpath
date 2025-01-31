package com.github.experion.toolpath.datagens;

import com.github.experion.toolpath.initializer.ModItems;
import com.github.experion.toolpath.initializer.ModTools;
import com.github.experion.toolpath.initializer.TaggingList;
import com.github.experion.toolpath.lib.Experion.ExperionTranslatable;
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

        expe.translateItem(ModItems.TOOL_DEVELOPER, "Tool Developer");
    }
}
