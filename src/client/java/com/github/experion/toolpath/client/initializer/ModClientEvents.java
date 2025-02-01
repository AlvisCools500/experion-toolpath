package com.github.experion.toolpath.client.initializer;

import com.github.experion.toolpath.ModInit;
import com.github.experion.toolpath.client.DiscoveryTitleRenderer;
import com.github.experion.toolpath.misc.payloads.DiscoveryPayLoad;
import com.github.experion.toolpath.misc.payloads.PathDataPayLoad;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

public class ModClientEvents {
    public static void register() {
        HudRenderCallback.EVENT.register(DiscoveryTitleRenderer::renderTitle);

        ClientPlayNetworking.registerGlobalReceiver(DiscoveryPayLoad.ID, (discoveryPayLoad, context) -> {
            DiscoveryTitleRenderer.trigger(discoveryPayLoad.type(), discoveryPayLoad.item_id());
        });

        ClientPlayNetworking.registerGlobalReceiver(PathDataPayLoad.ID, (pathDataPayLoad, context) -> {
            ModInit.LOGGER.info("Umm i'm client i guess?");
        });
    }
}
