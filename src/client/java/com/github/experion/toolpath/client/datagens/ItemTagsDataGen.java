package com.github.experion.toolpath.client.datagens;

import com.github.experion.toolpath.initializer.ModTags;
import com.github.experion.toolpath.initializer.TaggingList;
import com.github.experion.toolpath.items.tool_lambdas.GetLambdas;
import com.github.experion.toolpath.items.tool_lambdas.ToolLambdas;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class ItemTagsDataGen extends FabricTagProvider<Item> {
    public ItemTagsDataGen(FabricDataOutput output,  CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.ITEM, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        FabricTagProvider<Item>.FabricTagBuilder TOOLPATH_TOOLS = getOrCreateTagBuilder(ModTags.TOOLPATH_TOOLS);

        FabricTagProvider<Item>.FabricTagBuilder MINING_ENCHANTABLE = getOrCreateTagBuilder(ItemTags.MINING_ENCHANTABLE).setReplace(false);
        FabricTagProvider<Item>.FabricTagBuilder MINING_LOOT_ENCHANTABLE = getOrCreateTagBuilder(ItemTags.MINING_LOOT_ENCHANTABLE).setReplace(false);

        FabricTagProvider<Item>.FabricTagBuilder WEAPON_ENCHANTABLE = getOrCreateTagBuilder(ItemTags.WEAPON_ENCHANTABLE).setReplace(false);
        FabricTagProvider<Item>.FabricTagBuilder SHARP_WEAPON_ENCHANTABLE = getOrCreateTagBuilder(ItemTags.SHARP_WEAPON_ENCHANTABLE).setReplace(false);
        FabricTagProvider<Item>.FabricTagBuilder FIRE_ASPECT_ENCHANTABLE = getOrCreateTagBuilder(ItemTags.FIRE_ASPECT_ENCHANTABLE).setReplace(false);


        FabricTagProvider<Item>.FabricTagBuilder DURABILITY_ENCHANTABLE = getOrCreateTagBuilder(ItemTags.DURABILITY_ENCHANTABLE).setReplace(false);

        for (Item v : TaggingList.DEFAULT_TOOLS) {
            DURABILITY_ENCHANTABLE.add(v);
            TOOLPATH_TOOLS.add(v);

            ToolLambdas myLamb = ((GetLambdas) v).getLambdas();

            if (myLamb.getMain_tag() != null) {
                getOrCreateTagBuilder(myLamb.getMain_tag()).add(v);
            }
        }

        for (Item v : TaggingList.SWORD) { // SWORD
            WEAPON_ENCHANTABLE.add(v);
            SHARP_WEAPON_ENCHANTABLE.add(v);
            FIRE_ASPECT_ENCHANTABLE.add(v);
            getOrCreateTagBuilder(ItemTags.SWORDS).add(v).setReplace(false);
        }

        for (Item v : TaggingList.AXE) { // AXE
            MINING_ENCHANTABLE.add(v);
            MINING_LOOT_ENCHANTABLE.add(v);
            WEAPON_ENCHANTABLE.add(v);
            SHARP_WEAPON_ENCHANTABLE.add(v);
            getOrCreateTagBuilder(ItemTags.AXES).add(v).setReplace(false);
        }

        for (Item v : TaggingList.PICKAXE) { // PICKAXE
            MINING_ENCHANTABLE.add(v);
            MINING_LOOT_ENCHANTABLE.add(v);
            getOrCreateTagBuilder(ItemTags.PICKAXES).add(v).setReplace(false);
        }

        for (Item v : TaggingList.HOE) { // HOE
            MINING_ENCHANTABLE.add(v);
            MINING_LOOT_ENCHANTABLE.add(v);
            getOrCreateTagBuilder(ItemTags.HOES).add(v).setReplace(false);
        }

        for (Item v : TaggingList.SHOVEL) { // SHOVEL
            MINING_ENCHANTABLE.add(v);
            MINING_LOOT_ENCHANTABLE.add(v);
            getOrCreateTagBuilder(ItemTags.SHOVELS).add(v).setReplace(false);
        }

        getOrCreateTagBuilder(ModTags.Tools.WOODEN_TOOLS)
                .add(Items.WOODEN_AXE)
                .add(Items.WOODEN_PICKAXE)
                .add(Items.WOODEN_HOE)
                .add(Items.WOODEN_SHOVEL)
                .add(Items.WOODEN_SWORD)
        ;
        getOrCreateTagBuilder(ModTags.Tools.STONE_TOOLS)
                .add(Items.STONE_AXE)
                .add(Items.STONE_PICKAXE)
                .add(Items.STONE_HOE)
                .add(Items.STONE_SHOVEL)
                .add(Items.STONE_SWORD)
        ;
        getOrCreateTagBuilder(ModTags.Tools.IRON_TOOLS)
                .add(Items.IRON_AXE)
                .add(Items.IRON_PICKAXE)
                .add(Items.IRON_HOE)
                .add(Items.IRON_SHOVEL)
                .add(Items.IRON_SWORD)
        ;
        getOrCreateTagBuilder(ModTags.Tools.DIAMOND_TOOLS)
                .add(Items.DIAMOND_AXE)
                .add(Items.DIAMOND_PICKAXE)
                .add(Items.DIAMOND_HOE)
                .add(Items.DIAMOND_SHOVEL)
                .add(Items.DIAMOND_SWORD)
        ;
        getOrCreateTagBuilder(ModTags.Tools.NETHERITE_TOOLS)
                .add(Items.NETHERITE_AXE)
                .add(Items.NETHERITE_PICKAXE)
                .add(Items.NETHERITE_HOE)
                .add(Items.NETHERITE_SHOVEL)
                .add(Items.NETHERITE_SWORD)
        ;

        getOrCreateTagBuilder(ModTags.Tools.GOLDEN_TOOLS)
                .add(Items.GOLDEN_AXE)
                .add(Items.GOLDEN_PICKAXE)
                .add(Items.GOLDEN_HOE)
                .add(Items.GOLDEN_SHOVEL)
                .add(Items.GOLDEN_SWORD)
        ;

        TOOLPATH_TOOLS
                .addTag(ModTags.Tools.WOODEN_TOOLS)
                .addTag(ModTags.Tools.STONE_TOOLS)
                .addTag(ModTags.Tools.IRON_TOOLS)
                .addTag(ModTags.Tools.DIAMOND_TOOLS)
                .addTag(ModTags.Tools.NETHERITE_TOOLS)
                .addTag(ModTags.Tools.GOLDEN_TOOLS)
        ;

        for (Item v : TaggingList.DURABLE_ABILITY) {
            getOrCreateTagBuilder(ModTags.Misc.DURABLE_ABILITY).add(v);
        }

    }



    private TagKey<Item> getTag(String namespace, String path) {
        return TagKey.of(RegistryKeys.ITEM, Identifier.of(namespace,path));
    }
}
