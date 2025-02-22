package io.github.experion.tools.client.datagens;

import io.github.experion.tools.initializer.ModItems;
import io.github.experion.tools.initializer.ModTags;
import io.github.experion.tools.initializer.TaggingList;
import io.github.experion.tools.item.tool_lambdas.GetLambdas;
import io.github.experion.tools.item.tool_lambdas.RecipeLambda;
import io.github.experion.tools.item.tool_lambdas.ToolLambdas;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.data.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.List;
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

                offerSmelting(List.of(Items.IRON_INGOT),RecipeCategory.MISC, ModItems.STEEL_INGOT, 1.0f, 200, "smelt_steel");
                offerBlasting(List.of(Items.IRON_INGOT),RecipeCategory.MISC, ModItems.STEEL_INGOT, 1.0f, 100, "blasting_steel");

                createShaped(RecipeCategory.MISC, ModItems.VAMPIRE_STEEL_INGOT)
                        .pattern("B# ")
                        .pattern("D# ")
                        .input('B',ModItems.STEEL_INGOT)
                        .input('D',Items.DIAMOND)
                        .input('#',Items.REDSTONE_BLOCK)
                        .criterion("has_steel_ingot_vampire",conditionsFromItem(ModItems.STEEL_INGOT))
                        .offerTo(recipeExporter);

                createShaped(RecipeCategory.MISC,ModItems.FROSTED_STEEL_INGOT)
                        .pattern("B# ")
                        .pattern("D# ")
                        .input('B',ModItems.STEEL_INGOT)
                        .input('D',Items.DIAMOND)
                        .input('#', ModTags.Materials.ICE_BLOCKS)
                        .criterion("has_steel_ingot_frosted",conditionsFromItem(ModItems.STEEL_INGOT))
                        .offerTo(recipeExporter);



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
