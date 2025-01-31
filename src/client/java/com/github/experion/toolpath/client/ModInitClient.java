package com.github.experion.toolpath.client;

import com.github.experion.toolpath.ModInit;
import com.github.experion.toolpath.misc.ServerMessage;
import com.github.experion.toolpath.misc.payloads.DiscoveryPayLoad;
import com.github.experion.toolpath.misc.payloads.PathDataPayLoad;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class ModInitClient implements ClientModInitializer {
  public static final Logger LOGGER = LoggerFactory.getLogger(ModInitClient.class);

  @Override
  public void onInitializeClient() {
    ModInit.LOGGER.info("Hello, World! (Client initialize)");

    ClientPlayNetworking.registerGlobalReceiver(PathDataPayLoad.ID, (pathDataPayLoad, context) -> {
      ModInit.LOGGER.info("Umm i'm client i guess?");
    });

    HudRenderCallback.EVENT.register((drawContext, renderTickCounter) -> {
        DiscoveryTitleRenderer.renderTitle(drawContext, renderTickCounter);
    });

    ClientPlayNetworking.registerGlobalReceiver(DiscoveryPayLoad.ID, (discoveryPayLoad, context) -> {
      DiscoveryTitleRenderer.trigger(discoveryPayLoad.type(), discoveryPayLoad.item_id());
    });


  }


}
