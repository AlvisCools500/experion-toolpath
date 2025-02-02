package com.github.experion.toolpath.datagens;

import com.github.experion.toolpath.initializer.ModItems;
import com.github.experion.toolpath.initializer.TaggingList;
import com.github.experion.toolpath.items.tool_lambdas.GetLambdas;
import com.github.experion.toolpath.items.tool_lambdas.RecipeLambda;
import com.github.experion.toolpath.items.tool_lambdas.ToolLambdas;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class RecipeDataGen extends FabricRecipeProvider {
    public RecipeDataGen(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter recipeExporter) {
        /*ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, Items.DIAMOND_PICKAXE)
                .pattern("BBB")
                .pattern(" # ")
                .input('B', Items.DIAMOND)
                .input('#', Items.IRON_PICKAXE)
                .criterion("has_iron_pick", conditionsFromItem(Items.IRON_PICKAXE))
                .offerTo(recipeExporter);*/

        /*
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModTools.REDSTONE_PICKAXE)
                .pattern("BBB")
                .pattern(" # ")
                .input('B', Items.REDSTONE_BLOCK)
                .input('#', Items.IRON_PICKAXE)
                .criterion("has_iron_pick_redstone_pickaxe", conditionsFromItem(Items.IRON_PICKAXE))
                .offerTo(recipeExporter);*/
        for (Item item_result : TaggingList.DEFAULT_TOOLS) {
            ToolLambdas toolLamb = null;

            try {
                toolLamb = ((GetLambdas) item_result).getLambdas();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            if (toolLamb != null) {
                if (toolLamb.getRecipe() != null) {
                    RecipeLambda RECIPE_LAMBDA = toolLamb.getRecipe();

                    List<String> recipe_line = RECIPE_LAMBDA.getRecipeLine();
                    HashMap<Character, Item> recipe_chars_item = RECIPE_LAMBDA.getRecipechars_ITEMS();
                    HashMap<Character, TagKey<Item>> recipe_chars_tag = RECIPE_LAMBDA.getRecipechars_TAG();

                    String str_criterion = RECIPE_LAMBDA.getCriterionSTR();
                    Item item_criterion = Registries.ITEM.get(RECIPE_LAMBDA.getCriterion_identify());

                    ShapedRecipeJsonBuilder shaped = ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, item_result);

                    for (int i = 0; i <= recipe_line.size() - 1; i++) {
                        shaped.pattern(recipe_line.get(i));
                    }

                    for (Character c : recipe_chars_item.keySet()) {
                        shaped.input(c,recipe_chars_item.get(c));
                    }

                    for (Character c : recipe_chars_tag.keySet()) {
                        shaped.input(c,recipe_chars_tag.get(c));
                    }

                    shaped.criterion(str_criterion, conditionsFromItem(item_criterion));

                    shaped.offerTo(recipeExporter);

                }
            }
        }

        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.AZALEA_PICKER)
                .pattern("#  ")
                .pattern("B# ")
                .input('#', Items.STICK)
                .input('B', Items.GLOW_BERRIES)
                .criterion("has_glow_berries",conditionsFromItem(Items.GLOW_BERRIES))
                .offerTo(recipeExporter);
    }
}
