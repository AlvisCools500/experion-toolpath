package io.github.experion.tools.client.initializer;

import io.github.experion.tools.ModInit;
import io.github.experion.tools.client.DiscoveryTitleRenderer;
import io.github.experion.tools.misc.payloads.DiscoveryPayLoad;
import io.github.experion.tools.misc.payloads.PathDataPayLoad;
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
