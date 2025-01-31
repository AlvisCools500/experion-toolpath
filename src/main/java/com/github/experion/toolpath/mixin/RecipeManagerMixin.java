package com.github.experion.toolpath.mixin;

import com.github.experion.toolpath.ModInit;
import com.github.experion.toolpath.initializer.RecipeRemoveList;
import com.google.gson.JsonElement;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Mixin(RecipeManager.class)
public class RecipeManagerMixin {
    @Inject(method = "apply(Ljava/util/Map;Lnet/minecraft/resource/ResourceManager;Lnet/minecraft/util/profiler/Profiler;)V", at = @At("HEAD"))
    private void ApplyRemove(Map<Identifier, JsonElement> map, ResourceManager resourceManager, Profiler profiler, CallbackInfo info) {
        RecipeRemoveList.LOGGER.info("Injected RecipeManager!");

        List<Identifier> idList = new ArrayList<>();//RecipeRemoveList.GetIdList();

        for (Identifier id : idList) {
            try {
                if (map.containsKey(id)) {
                    map.remove(id);
                }else {
                    RecipeRemoveList.LOGGER.warn(id.getPath() + " not found!");
                }
            } catch (Exception e) {
                ModInit.LOGGER.warn("something went wrong with removing recipe?");
            }

        }


    }
}
