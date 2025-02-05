package com.github.experion.tools.misc;

import com.github.experion.tools.misc.payloads.DiscoveryPayLoad;
import com.github.experion.tools.misc.server_packet.PathDataPacket;
import com.github.experion.tools.misc.payloads.PathDataPayLoad;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

public class ServerMessage {
    public static void connect() {
        PayloadTypeRegistry.playS2C().register(PathDataPayLoad.ID,PathDataPayLoad.CODEC);
        PayloadTypeRegistry.playC2S().register(PathDataPayLoad.ID,PathDataPayLoad.CODEC);

        PayloadTypeRegistry.playS2C().register(DiscoveryPayLoad.ID,DiscoveryPayLoad.CODEC);
        PayloadTypeRegistry.playC2S().register(DiscoveryPayLoad.ID,DiscoveryPayLoad.CODEC);

        ServerPlayNetworking.registerGlobalReceiver(PathDataPayLoad.ID,
                (payload, context) -> context.player().getServer().execute(() -> PathDataPacket.recieve())
        );


    }
}
