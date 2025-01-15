package com.wirethread.network.packet.server.status;

import com.wirethread.network.buffer.Type;
import com.wirethread.network.connection.ConnectionState;
import com.wirethread.network.packet.Packet;
import com.wirethread.network.packet.PacketBound;
import com.wirethread.network.packet.PacketTemplate;
import com.wirethread.network.types.Primitives;
import org.jetbrains.annotations.NotNull;

public record StatusResponsePacket(@NotNull String jsonResponse) implements Packet<StatusResponsePacket> {

    public static final Type<StatusResponsePacket> SERIALIZER = PacketTemplate.template(
            Primitives.STRING, StatusResponsePacket::jsonResponse, StatusResponsePacket::new
    );

    @Override
    public PacketBound getBound() {
        return PacketBound.Clientbound;
    }

    @Override
    public ConnectionState getConnectionState() { return ConnectionState.STATUS; }
}
