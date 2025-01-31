package com.github.experion.toolpath.misc.server_packet;

import com.github.experion.toolpath.ModInit;

public class PathDataPacket {
    public static void recieve(){//(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        ModInit.LOGGER.info("TOOLPATH DATA PACKET CONNECTED");
    }
}
