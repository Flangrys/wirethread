package com.wirethread.server;

/**
 * Represents the different states a server can be in during its lifecycle.
 * <p>
 * The status codes are mapped to integer values for internal use:
 * <ul>
 *   <li>OPEN (0) - The server is actively running and accepting connections.</li>
 *   <li>PAUSED  (1) - The server is temporarily suspended and not accepting new connections.</li>
 *   <li>CLOSED  (2) - The server has been shut down and cannot be restarted.</li>
 * </ul>
 */
public enum ServerStatus {
    OPEN(0),
    PAUSED(1),
    CLOSED(2);

    public final int value;

    ServerStatus(int value) {
        this.value = value;
    }
}
