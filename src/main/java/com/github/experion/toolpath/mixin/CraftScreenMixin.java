package com.github.experion.toolpath.mixin;

import com.github.experion.toolpath.ModInit;
import com.github.experion.toolpath.initializer.ModTags;
import com.github.experion.toolpath.items.tool_identify.ToolIdList;
import com.github.experion.toolpath.items.tool_identify.ToolIdentifier;
import com.github.experion.toolpath.misc.payloads.DiscoveryPayLoad;
import com.github.experion.toolpath.misc.persistent_state.ToolPathData;
import com.github.experion.toolpath.misc.persistent_state.ToolPathDataManager;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.CraftingRecipe;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.screen.CraftingScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

@Debug(export = true)
@Mixin(value = CraftingScreenHandler.class, priority = 1069)
public class CraftScreenMixin {

    @Inject(method = "updateResult", at = @At("TAIL"), cancellable = true)
    private static void myUpdResult(ScreenHandler handler, World world, PlayerEntity player, RecipeInputInventory craftingInventory, CraftingResultInventory resultInventory, @Nullable RecipeEntry<CraftingRecipe> recipe, CallbackInfo info) {
        // resultInventory.setStack(0, ItemStack.EMPTY); to decline

        if (!world.isClient()) {
            ItemStack result = resultInventory.getStack(0);

            ModInit.LOGGER.info("UPDATE SERVER");

            if (!result.isEmpty()) {
                if (result.isIn(ModTags.TOOLPATH_TOOLS)) {
                    ToolIdentifier toolid = ToolIdList.getIdentifier(result.getItem());

                    MinecraftServer server = world.getServer();
                    ToolPathData dat = ToolPathDataManager.getOrCreate(server);
                    UUID uuid = player.getGameProfile().getId();

                    int type = ToolIdList.getToolType(result.getItem());

                    if (toolid != null) {
                        if (toolid.getDepedency() != -1) {
                            ToolIdentifier dependTool = ToolIdList.getIdentifier(toolid.getDepedency());

                            if (!dat.valid_check(uuid, type, dependTool.getNumID())) {
                                resultInventory.setStack(0, ItemStack.EMPTY);
                                ServerPlayNetworking.send((ServerPlayerEntity) player,new DiscoveryPayLoad(2, dependTool.getToolIdentifier(type)));
                                info.cancel();
                            }
                        }else {

                            ModInit.LOGGER.info("Nah not work");

                            //if (!dat.valid_check(uuid,type,toolid.getNumID())) {
                            //    dat.putMap(uuid,type,toolid.getNumID());
                            //}
                        }
                    }else {
                        ModInit.LOGGER.warn("FAIL?");
                    }
                }
            }
        }else {
            ModInit.LOGGER.info("UPDATE CLIENT");
        }
    }

}
