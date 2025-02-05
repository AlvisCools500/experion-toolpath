package com.github.experion.tools.items.tool_lambdas;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RecipeLambda {
    List<String> recipe_line;
    HashMap<Character, Item> recipe_chars_item;
    HashMap<Character, TagKey<Item>> recipe_chars_tag;

    String criterion_str;
    Identifier criterion_identify;

    public RecipeLambda() {
        this.recipe_line = new ArrayList<>();
        this.recipe_chars_item = new HashMap<>();
        this.recipe_chars_tag = new HashMap<>();
        this.criterion_str = "";
    }

    public RecipeLambda setCriterion_str(String criterion_str) {
        this.criterion_str = criterion_str;
        return this;
    }

    public RecipeLambda addLine(String str) {
        this.recipe_line.add(str);
        return this;
    }

    public RecipeLambda addChar(Character c, Item item) {
        this.recipe_chars_item.put(c,item);
        return this;
    }

    public RecipeLambda addChar(Character c, TagKey<Item> tag) {
        this.recipe_chars_tag.put(c, tag);
        return this;
    }

    public RecipeLambda setItemCriterion(Item item) {
        this.criterion_identify = Registries.ITEM.getId(item);
        return this;
    }

    public List<String> getRecipeLine() {
        return recipe_line;
    }

    public HashMap<Character, Item> getRecipechars_ITEMS() {
        return recipe_chars_item;
    }

    public HashMap<Character, TagKey<Item>> getRecipechars_TAG() {
        return recipe_chars_tag;
    }

    public String getCriterionSTR() {
        String str = this.criterion_str;

        if (str.isEmpty() && this.criterion_identify != null) {
            str = "has_" + this.criterion_identify.getPath();
        }
        return str;
    }

    public Identifier getCriterion_identify() {
        return criterion_identify;
    }

    public static RecipeLambda create() {
        return new RecipeLambda();
    }
}
