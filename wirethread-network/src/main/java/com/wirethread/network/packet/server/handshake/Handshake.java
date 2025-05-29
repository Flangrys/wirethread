package com.wirethread.network.packet.server.handshake;

import com.wirethread.network.connection.ConnectionIntention;
import com.wirethread.network.connection.ConnectionState;
import com.wirethread.network.packet.PacketBound;
import com.wirethread.network.packet.server.ServerBoundPacket;
import com.wirethread.network.types.Primitives;
import com.wirethread.network.types.Type;
import io.netty.buffer.ByteBuf;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public record Handshake(
        @NotNull Integer protocolVersion,
        @NotNull String serverAddress,
        @NotNull Integer serverPort,
        @NotNull ConnectionIntention intention
) implements ServerBoundPacket<Handshake> {

    public static final Type<Handshake> SERIALIZER = new Type<Handshake>() {
        @Override
        public void write(@NotNull ByteBuf buffer, Handshake value) throws IOException {
            throw new IOException("You must not send this package to the client.");
        }

        @Override
        public Handshake read(@NotNull ByteBuf buffer) throws IOException {
            return new Handshake(
                    Primitives.VAR_INT.read(buffer),
                    Primitives.STRING.read(buffer),
                    Primitives.UNSIGNED_SHORT.read(buffer),
                    Primitives.INTENTION_TYPE.read(buffer)
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
        return null;
    }

    /**
     * Returns the current state of this packet.
     *
     * @return The {@link ConnectionState} of this packet.
     */
    @Override
    public ConnectionState getConnectionState() {
        return null;
    }
}
