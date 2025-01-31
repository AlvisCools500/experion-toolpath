package com.github.experion.toolpath.misc.payloads;

import com.github.experion.toolpath.ModInit;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.dynamic.Codecs;

public record DiscoveryPayLoad(int type, Identifier item_id) implements CustomPayload {

    public static final CustomPayload.Id<DiscoveryPayLoad> ID = new Id<>(Identifier.of(ModInit.ID, "discovery_payload"));
    public static final PacketCodec<RegistryByteBuf, DiscoveryPayLoad> CODEC = PacketCodec.tuple(PacketCodecs.INTEGER,DiscoveryPayLoad::type, Identifier.PACKET_CODEC,DiscoveryPayLoad::item_id,DiscoveryPayLoad::new);

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
