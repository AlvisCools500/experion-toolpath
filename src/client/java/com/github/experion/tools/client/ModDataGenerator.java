package com.github.experion.tools.client;

import com.github.experion.tools.client.datagens.*;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class ModDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator wrap) {
        ModInitClient.LOGGER.info("RUN");
        FabricDataGenerator.Pack pack = wrap.createPack();

        pack.addProvider(EnchantmentsDataGen::new);
        pack.addProvider(ItemTagsDataGen::new);
        pack.addProvider(ModelDataGen::new);
        pack.addProvider(TranslateDataGen::new);
        pack.addProvider(RecipeDataGen::new);

    }
}
