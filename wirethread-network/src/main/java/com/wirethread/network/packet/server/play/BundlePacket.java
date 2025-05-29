package com.wirethread.network.packet.server.play;

import com.wirethread.network.connection.ConnectionState;
import com.wirethread.network.packet.PacketBound;
import com.wirethread.network.types.TypeTemplate;
import com.wirethread.network.packet.server.ServerBoundPacket;
import com.wirethread.network.types.Type;

public record BundlePacket() implements ServerBoundPacket<BundlePacket> {

    public static final Type<BundlePacket> SERIALIZER = TypeTemplate.template(BundlePacket::new);

    @Override
    public PacketBound getBound() {
        return PacketBound.CLIENT_BOUND;
    }

    @Override
    public ConnectionState getConnectionState() {
        return ConnectionState.STATUS;
    }
}
