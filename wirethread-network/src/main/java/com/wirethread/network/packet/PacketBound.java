package com.wirethread.network.packet;

/**
 * On server terms, the packet boundary identifies which member of the connection
 * are responsible for handling a packet.
 * In other words: (ClientBound)
 */
public enum PacketBound {
    /**
     * The client receives the server response.
     */
    ClientBound(0),

    /**
     * The server receives the client request.
     */
    ServerBound(1);

    private final int bound;

    PacketBound(int bound) {
        this.bound = bound;
    }

    public int getBound() {
        return this.bound;
    }

    /**
     * This utility method returns the opposite boundary.
     * @return The opposite boundary.
     */
    public PacketBound getOpposite() {
        return switch (this) {
            case ClientBound -> ServerBound;
            case ServerBound -> ClientBound;
        };
    }

    @Override
    public String toString() {
        return switch (this) {
            case ServerBound -> "serverbound";
            case ClientBound -> "clientbound";
        };
    }
}
