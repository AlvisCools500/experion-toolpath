package com.github.experion.toolpath.mixin;

import com.github.experion.toolpath.ModInit;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.RecipeUnlocker;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RecipeUnlocker.class)
public interface RecipeUnlockerMixin {
    @Inject(method = "shouldCraftRecipe", at = @At(value = "RETURN"))
    default boolean redirectShouldCraftRecipe(RecipeUnlocker instance, World world, ServerPlayerEntity player, RecipeEntry<?> recipe) {
        System.out.println("shouldCraftRecipe was called!");

        return false; // Override behavior (deny crafting)
    }
}
