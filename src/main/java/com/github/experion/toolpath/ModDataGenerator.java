package com.github.experion.toolpath;

import com.github.experion.toolpath.datagens.ItemTagsDataGen;
import com.github.experion.toolpath.datagens.ModelDataGen;
import com.github.experion.toolpath.datagens.RecipeDataGen;
import com.github.experion.toolpath.datagens.TranslateDataGen;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class ModDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator gen) {
        FabricDataGenerator.Pack pack = gen.createPack();

        pack.addProvider(RecipeDataGen::new);
        pack.addProvider(ModelDataGen::new);
        pack.addProvider(TranslateDataGen::new);
        pack.addProvider(ItemTagsDataGen::new);
    }
}
