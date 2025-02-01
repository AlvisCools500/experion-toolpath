package com.github.experion.toolpath.items.tool_lambdas;

import com.github.experion.toolpath.items.CustomLambdas;
import net.minecraft.item.Item;
import net.minecraft.registry.tag.TagKey;

public class ToolLambdas {

    TriggerLambdas lambdas;

    boolean Edit_damage;
    boolean Edit_effeciency;

    boolean Enable_useblock;
    boolean Enable_posthit;
    boolean Enable_postmine;
    boolean Enable_use;

    boolean notranslate;

    TagKey<Item> main_tag;

    RecipeLambda recipe;

    public ToolLambdas() {
        this.Enable_useblock = false;
        this.Enable_posthit = false;
        this.Enable_postmine = false;
        this.Enable_use = false;

        this.Edit_damage = false;
        this.Edit_effeciency = false;

        this.main_tag = null;
        this.notranslate = false;
        this.recipe = null;
        this.lambdas = null;

        this.lambdas = CustomLambdas.NON_LAMBDAS;
    }

    public ToolLambdas setLambda(TriggerLambdas triggerLambdas) {
        this.lambdas = triggerLambdas;
        return this;
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

    public ToolLambdas enableEditEffeciency() {
        this.Edit_effeciency = true;
        return this;
    }

    public ToolLambdas enableEditDamage() {
        this.Edit_damage = true;
        return this;
    }

    public ToolLambdas enableOnUse() {
        this.Enable_use = true;
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

    public static ToolLambdas of() {
        return new ToolLambdas();
    }
}
