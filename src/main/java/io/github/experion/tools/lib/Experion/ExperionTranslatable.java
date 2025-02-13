package io.github.experion.tools.lib.Experion;

import io.github.experion.tools.ModInit;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class ExperionTranslatable {
    private final String id_str = ModInit.ID;
    private FabricLanguageProvider.TranslationBuilder translate;

    public ExperionTranslatable(FabricLanguageProvider.TranslationBuilder translator){
        this.translate = translator;
    }

    public void translateBasic(String backSTR, String frontSTR, String input) {
        translate.add(backSTR + "." + id_str + "." + frontSTR, input);
    }

    public void translateBlock(Block block, String input) {
        String v = input;

        if (v.equals("?")) {
            v = easyTranslate(ExperionBasicLib.getBlockID(block));
        }

        translateBasic("block", ExperionBasicLib.getBlockID(block), v);
    }

    public void translateItem(Item item, String input) {
        String v = input;

        if (v.equals("?")) {
            v = easyTranslate(ExperionBasicLib.getItemID(item));
        }

        translateBasic("item",ExperionBasicLib.getItemID(item),v);
    }

    public String easyTranslate(String name) {
        String[] var = name.split("_");

        String res = "";
        boolean first = false;

        for (String a : var) {
            String firstChar = a.substring(0,1).toUpperCase();
            String mySTR = firstChar + a.substring(1);

            if (first) {
                res = res + " " + mySTR;
            }else {
                res = res + mySTR;
                first = true;
            }
        }

        return res;

    }

}
