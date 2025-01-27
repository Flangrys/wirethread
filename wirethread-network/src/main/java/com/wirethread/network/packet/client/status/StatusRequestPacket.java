package com.wirethread.network.packet.client.status;

import com.wirethread.network.buffer.Type;
import com.wirethread.network.connection.ConnectionState;
import com.wirethread.network.packet.Packet;
import com.wirethread.network.packet.PacketBound;
import com.wirethread.network.packet.PacketTemplate;

public record StatusRequestPacket() implements Packet<StatusRequestPacket> {

    public static final Type<StatusRequestPacket> SERIALIZER = PacketTemplate.template(StatusRequestPacket::new);

    @Override
    public PacketBound getBound() {
        return PacketBound.ServerBound;
    }

    @Override
    public ConnectionState getConnectionState() {
        return ConnectionState.STATUS;
    }
}
