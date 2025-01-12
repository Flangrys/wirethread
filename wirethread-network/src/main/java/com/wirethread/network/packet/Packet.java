package com.wirethread.network.packet;

public interface Packet<T> {

    /**
     * Returns the packet bound
     * @return
     */
    PacketBound getBound();
}
