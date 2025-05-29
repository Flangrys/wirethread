package com.wirethread.server;

/**
 * Represents the different states a server can be in during its lifecycle.
 * <p>
 * The status codes are mapped to integer values for internal use:
 * <ul>
 *   <li>STARTED (true) - The server is actively running and accepting connections.</li>
 *   <li>STOPPED  (false) - The server has been shut down and cannot be restarted.</li>
 * </ul>
 */
public enum State {
    STARTED(true),
    STOPPED(false);

    public final boolean value;

    State(boolean value) {
        this.value = value;
    }
}
