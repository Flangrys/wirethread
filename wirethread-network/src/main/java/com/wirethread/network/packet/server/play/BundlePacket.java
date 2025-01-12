package com.wirethread.network.packet.server.play;

import com.wirethread.network.buffer.Type;
import com.wirethread.network.packet.Packet;
import com.wirethread.network.packet.PacketBound;
import com.wirethread.network.packet.PacketTemplate;

public record BundlePacket() implements Packet<BundlePacket> {

    public static final Type<BundlePacket> SERIALIZER = PacketTemplate.template(BundlePacket::new);

    @Override
    public PacketBound getBound() {
        return PacketBound.Clientbound;
    }
}
