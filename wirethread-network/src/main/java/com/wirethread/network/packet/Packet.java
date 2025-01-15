package com.wirethread.network.packet;

import com.wirethread.network.connection.ConnectionState;

public interface Packet<T> {

    /**
     * Returns the bound of this packet.
     * @return The {@link PacketBound} of this packet.
     */
    PacketBound getBound();

    /**
     * Returns the current state of this packet.
     * @return The {@link ConnectionState} of this packet.
     */
    ConnectionState getConnectionState();
}
