package com.github.experion.tools.item.tool_lambdas;

import com.github.experion.tools.item.CustomLambdas;
import net.minecraft.item.Item;
import net.minecraft.registry.tag.TagKey;

public class ToolLambdas {

    public TriggerLambdas lambdas;

    public boolean edit_damage;
    public boolean edit_effeciency;
    public boolean edit_texture;

    public boolean enable_useblock;
    public boolean enable_posthit;
    public boolean enable_postmine;
    public boolean enable_use;
    public boolean enable_punchblock;
    public boolean enable_punchair;
    public boolean enable_usagetick;
    public boolean enable_inventorytick;

    public boolean defaultModel;
    public boolean durableAbility;

    public boolean getThundered;

    boolean notranslate;

    TagKey<Item> main_tag;

    RecipeLambda recipe;

    public ToolLambdas() {
        this.enable_useblock = false;
        this.enable_posthit = false;
        this.enable_postmine = false;
        this.enable_use = false;
        this.enable_punchblock = false;
        this.enable_punchair = false;
        this.edit_texture = false;
        this.enable_usagetick = false;

        this.getThundered = false;

        this.edit_damage = false;
        this.edit_effeciency = false;

        this.main_tag = null;
        this.notranslate = false;
        this.recipe = null;
        this.lambdas = null;
        this.durableAbility = false;
        this.defaultModel = true;
        this.enable_inventorytick = false;

        this.lambdas = CustomLambdas.NON_LAMBDAS;
    }

    public ToolLambdas setLambda(TriggerLambdas triggerLambdas) {
        this.lambdas = triggerLambdas;
        return this;
    }

    public ToolLambdas EnableOnUseblock() {
        this.enable_useblock = true;
        return this;
    }

    public ToolLambdas EnableOnPostHit() {
        this.enable_posthit = true;
        return this;
    }

    public ToolLambdas EnableOnPostMine() {
        this.enable_postmine = true;
        return this;
    }

    public ToolLambdas EnablePunchBlock() {
        this.enable_punchblock = true;
        return this;
    }

    public ToolLambdas EnablePunchAir() {
        this.enable_punchair = true;
        return this;
    }

    public ToolLambdas EnableEditEffeciency() {
        this.edit_effeciency = true;
        return this;
    }

    public ToolLambdas EnableEditDamage() {
        this.edit_damage = true;
        return this;
    }

    public ToolLambdas EnableEditTexture() {
        this.edit_texture = true;
        return this;
    }

    public ToolLambdas EnableUsageTick() {
        this.enable_usagetick = true;
        return this;
    }

    public ToolLambdas GetThundered() {
        this.getThundered = true;
        return this;
    }

    public ToolLambdas EnableOnUse() {
        this.enable_use = true;
        return this;
    }

    public ToolLambdas EnableInventoryTick() {
        this.enable_inventorytick = true;
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

    public ToolLambdas DurableAbility() {
        this.durableAbility = true;
        return this;
    }

    public ToolLambdas DisableDefaultModel() {
        this.defaultModel = false;
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
