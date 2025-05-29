package com.wirethread.network.packet.client.status;

import com.wirethread.network.connection.ConnectionState;
import com.wirethread.network.packet.Packet;
import com.wirethread.network.packet.PacketBound;
import com.wirethread.network.types.TypeTemplate;
import com.wirethread.network.types.Type;

public record StatusRequestPacket() implements Packet<StatusRequestPacket> {

    public static final Type<StatusRequestPacket> SERIALIZER = TypeTemplate.template(StatusRequestPacket::new);

    @Override
    public PacketBound getBound() {
        return PacketBound.SERVER_BOUND;
    }

    @Override
    public ConnectionState getConnectionState() {
        return ConnectionState.STATUS;
    }
}
