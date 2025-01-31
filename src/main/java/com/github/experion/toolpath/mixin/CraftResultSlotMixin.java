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
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.CraftingResultSlot;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

@Mixin(CraftingResultSlot.class)
public class CraftResultSlotMixin {
    @Inject(method = "onTakeItem", at = @At("TAIL"), cancellable = true)
    private void onTakeInject(PlayerEntity player, ItemStack stack, CallbackInfo info) {
        World world = player.getWorld();

        if (!world.isClient()) {
            if (stack.isIn(ModTags.TOOLPATH_TOOLS)) {
                ToolIdentifier toolid = ToolIdList.getIdentifier(stack.getItem());

                if (toolid != null) {
                    ToolPathData dat = ToolPathDataManager.getOrCreate(world.getServer());

                    UUID uuid = player.getGameProfile().getId();

                    int tooltype = ToolIdList.getToolType(stack.getItem());

                    if (!dat.valid_check(uuid,tooltype, toolid.getNumID())) {
                        dat.putMap(uuid,tooltype,toolid.getNumID());

                        ServerPlayNetworking.send((ServerPlayerEntity) player, new DiscoveryPayLoad(1, toolid.getToolIdentifier(tooltype)));
                    }
                }


            }
        }
    }
}
