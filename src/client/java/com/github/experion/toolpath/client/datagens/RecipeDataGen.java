package com.github.experion.toolpath.client.datagens;

import com.github.experion.toolpath.initializer.TaggingList;
import com.github.experion.toolpath.items.tool_lambdas.GetLambdas;
import com.github.experion.toolpath.items.tool_lambdas.RecipeLambda;
import com.github.experion.toolpath.items.tool_lambdas.ToolLambdas;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.data.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class RecipeDataGen extends FabricRecipeProvider {
    public RecipeDataGen(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup wrapperLookup, RecipeExporter recipeExporter) {
        return new RecipeGenerator(wrapperLookup, recipeExporter) {
            @Override
            public void generate() {
                RegistryWrapper.Impl<Item> itemLookup = registries.getOrThrow(RegistryKeys.ITEM);

                for (Item v : TaggingList.DEFAULT_TOOLS) {
                    ToolLambdas toolLambdas = ((GetLambdas) v).getLambdas();

                    if (toolLambdas.getRecipe() != null) {
                        RecipeLambda recipeLambda = toolLambdas.getRecipe();

                        autogen(recipeLambda, v);
                    }
                }
            }

            private void autogen(RecipeLambda recipeLambda, Item v) {
                ShapedRecipeJsonBuilder jsonbuild = createShaped(RecipeCategory.TOOLS,v);

                for (int i = 0; i <= recipeLambda.getRecipeLine().size() - 1; i++) {
                    jsonbuild.pattern(recipeLambda.getRecipeLine().get(i));
                }

                for (Character a : recipeLambda.getRecipechars_ITEMS().keySet()) {
                    jsonbuild.input(a, recipeLambda.getRecipechars_ITEMS().get(a));
                }

                for (Character a : recipeLambda.getRecipechars_TAG().keySet()) {
                    jsonbuild.input(a, recipeLambda.getRecipechars_TAG().get(a));
                }

                String str_criterion = recipeLambda.getCriterionSTR();
                Item item_criterion = Registries.ITEM.get(recipeLambda.getCriterion_identify());

                jsonbuild.criterion(str_criterion, conditionsFromItem(item_criterion));

                jsonbuild.offerTo(recipeExporter);
            }
        };
    }


    @Override
    public String getName() {
        return "ExperionRecipeGen";
    }
}
