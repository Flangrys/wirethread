package com.wirethread.network.packet.server.handshake;

import com.wirethread.network.connection.ConnectionState;
import com.wirethread.network.packet.PacketBound;
import com.wirethread.network.types.TypeTemplate;
import com.wirethread.network.packet.server.ServerBoundPacket;
import com.wirethread.network.types.Primitives;
import com.wirethread.network.types.Type;
import org.jetbrains.annotations.NotNull;

public record LegacyServerPing(@NotNull Byte payload) implements ServerBoundPacket<LegacyServerPing> {

    public static final Type<LegacyServerPing> SERIALIZER = TypeTemplate.template(
            Primitives.BYTE, LegacyServerPing::payload, LegacyServerPing::new
    );

    /**
     * Returns the bound of this packet.
     *
     * @return The {@link PacketBound} of this packet.
     */
    @Override
    public PacketBound getBound() {
        return PacketBound.SERVER_BOUND;
    }

    /**
     * Returns the current state of this packet.
     *
     * @return The {@link ConnectionState} of this packet.
     */
    @Override
    public ConnectionState getConnectionState() {
        return ConnectionState.HANDSHAKE;
    }
}
