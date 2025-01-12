package com.wirethread.network.packet.server.status;

import com.wirethread.network.buffer.Type;
import com.wirethread.network.connection.ConnectionState;
import com.wirethread.network.packet.Packet;
import com.wirethread.network.packet.PacketBound;
import com.wirethread.network.packet.PacketState;
import com.wirethread.network.packet.PacketTemplate;
import com.wirethread.network.types.Primitives;
import org.jetbrains.annotations.NotNull;

@PacketState(ConnectionState.STATUS)
public record ResponsePacket(@NotNull String jsonResponse) implements Packet<ResponsePacket> {

    public static final Type<ResponsePacket> SERIALIZER = PacketTemplate.template(
            Primitives.STRING, ResponsePacket::jsonResponse, ResponsePacket::new
    );

    @Override
    public PacketBound getBound() {
        return PacketBound.Clientbound;
    }
}
