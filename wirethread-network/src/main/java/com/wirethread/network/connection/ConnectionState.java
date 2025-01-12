package com.wirethread.network.connection;

public enum ConnectionState {
    /**
     * Default state before any packet is received.
     */
    HANDSHAKE,

    /**
     * Client declares {@link ConnectionIntention#STATUS} intent during handshake.
     */
    STATUS,

    /**
     * Client declares {@link ConnectionIntention#LOGIN} intent during handshake.
     */
    LOGIN,

    /**
     * Client acknowledged login and is now configuring the game.
     * Can also go back to configuration from play.
     */
    CONFIGURATION,

    /**
     * Client (re-)finished configuration.
     */
    PLAY
}
