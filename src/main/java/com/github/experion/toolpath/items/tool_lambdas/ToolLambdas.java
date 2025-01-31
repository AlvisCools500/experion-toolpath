package com.github.experion.toolpath.items.tool_lambdas;

import net.minecraft.item.Item;
import net.minecraft.registry.tag.TagKey;

public class ToolLambdas {
    OnPostMine onPostMine;
    OnPostHit onPostHit;
    OnUseBlock onUseBlock;
    OnUse onUse;

    boolean Enable_useblock;
    boolean Enable_posthit;
    boolean Enable_postmine;
    boolean Enable_use;

    boolean notranslate;

    TagKey<Item> main_tag;

    ToolMainTrigger mainTrigger;

    RecipeLambda recipe;

    public ToolLambdas() {
        this.Enable_useblock = false;
        this.Enable_posthit = false;
        this.Enable_postmine = false;
        this.Enable_use = false;
        this.main_tag = null;
        this.notranslate = false;
        this.recipe = null;
    }

    public ToolLambdas enableOnUseblock() {
        this.Enable_useblock = true;
        return this;
    }

    public ToolLambdas enableOnPostHit() {
        this.Enable_posthit = true;
        return this;
    }

    public ToolLambdas enableOnPostMine() {
        this.Enable_postmine = true;
        return this;
    }

    public ToolLambdas enableOnUse() {
        this.Enable_use = true;
        return this;
    }

    public ToolLambdas setOnPostMine(OnPostMine onPostMine) {
        this.onPostMine = onPostMine;
        return this;
    }

    public ToolLambdas setOnPostHit(OnPostHit onPostHit) {
        this.onPostHit = onPostHit;
        return this;
    }

    public ToolLambdas setOnUseBlock(OnUseBlock onUseBlock) {
        this.onUseBlock = onUseBlock;
        return this;
    }

    public ToolLambdas setOnUse(OnUse onUse) {
        this.onUse = onUse;
        return this;
    }

    public ToolLambdas setMainTrigger(ToolMainTrigger mainTrigger) {
        this.mainTrigger = mainTrigger;
        return this;
    }

    public ToolLambdas setTag(TagKey<Item> key) {
        this.main_tag = key;
        return this;
    }

    public ToolLambdas disableTranslate() {
        this.notranslate = true;
        return this;
    }

    public boolean isNotTranslate() {
        return notranslate;
    }

    public TagKey<Item> getMain_tag() {
        return this.main_tag;
    }

    public ToolLambdas setRecipe(RecipeLambda recipeLambda) {
        this.recipe = recipeLambda;
        return this;
    }

    public RecipeLambda getRecipe() {
        return recipe;
    }
}
