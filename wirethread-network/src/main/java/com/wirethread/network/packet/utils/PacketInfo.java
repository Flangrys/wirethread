package com.wirethread.network.packet.utils;

import com.wirethread.network.connection.ConnectionState;
import com.wirethread.network.packet.Packet;
import com.wirethread.network.packet.PacketBound;
import com.wirethread.network.types.Primitives;
import com.wirethread.network.types.Type;
import io.netty.buffer.ByteBuf;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public record PacketInfo(@NotNull Integer packetSize, @NotNull Integer packetId) implements Packet<PacketInfo> {

    public static final Type<PacketInfo> SERIALIZER = new Type<>() {
        @Override
        public void write(@NotNull ByteBuf buffer, PacketInfo value) throws IOException {
            throw new IOException("YOU CANNOT WRITE A PACKET INFO");
        }

        @Override
        public PacketInfo read(@NotNull ByteBuf buffer) throws IOException {
            return new PacketInfo(
                    Primitives.VAR_INT.read(buffer),
                    Primitives.VAR_INT.read(buffer)
            );
        }
    };

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
