package com.wirethread.network.packet.server;

import com.wirethread.network.packet.Packet;

/**
 * Represents a server-side packet in the network protocol.
 *
 * <p> This interface extends the {@link Packet} interface, indicating that it is
 * specifically designed for server-to-client communication.
 *
 * @param <T> The primitive of packet this interface handles.
 */
public interface ServerBoundPacket<T> extends Packet<T> {
}
