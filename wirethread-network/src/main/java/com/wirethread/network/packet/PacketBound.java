package com.wirethread.network.packet;

/**
 * On server terms, the packet boundary identifies which member of the connection
 * are responsible for handling a packet.
 */
public enum PacketBound {
    /**
     * The client receives the server response.
     */
    CLIENT_BOUND,

    /**
     * The server receives the client request.
     */
    SERVER_BOUND;

    public int getBound() {
        return this.ordinal() + 1;
    }

    /**
     * This utility method returns the opposite boundary.
     * @return The opposite boundary.
     */
    public PacketBound getOpposite() {
        return switch (this) {
            case CLIENT_BOUND -> SERVER_BOUND;
            case SERVER_BOUND -> CLIENT_BOUND;
        };
    }

    @Override
    public String toString() {
        return switch (this) {
            case SERVER_BOUND -> "serverbound";
            case CLIENT_BOUND -> "clientbound";
        };
    }
}
