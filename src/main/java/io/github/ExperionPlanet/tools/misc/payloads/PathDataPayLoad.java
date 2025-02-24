package io.github.ExperionPlanet.tools.misc.payloads;

import io.github.ExperionPlanet.tools.ModInit;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record PathDataPayLoad() implements CustomPayload {
    public static final PathDataPayLoad INST = new PathDataPayLoad();
    public static final CustomPayload.Id<PathDataPayLoad> ID = new Id<>(Identifier.of(ModInit.ID, "toolpath_data"));
    public static final PacketCodec<RegistryByteBuf, PathDataPayLoad> CODEC = PacketCodec.unit(INST);

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }


}
