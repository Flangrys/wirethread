package com.wirethread.network.packet.client;

import com.wirethread.network.packet.Packet;

/**
 * Represents a packet that is sent from the client to the server.
 *
 * <p> This interface extends the {@link Packet} interface, indicating that it
 * is a specific primitive of packet used in client-server communication.
 *
 * @param <T> The primitive of packet this interface represents.
 */
public interface ClientBoundPacket<T> extends Packet<T> {
}
