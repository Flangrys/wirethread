package com.wirethread.server;

import com.wirethread.core.registry.records.DynamicRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Models an abstract minecraft server.
 */
public abstract class Server extends ServerTick {

    private final @NotNull String serverBrandName = "Wirethread";

    protected final AtomicReference<ServerStatus> serverStatusAF;

    protected static final Logger LOGGER = LogManager.getLogger(Server.class);

    /**
     * Contains each of the server process managers.
     */
    protected static volatile ServerProcess serverProcess;

    /**
     * Contains each dynamic game registry.
     */
    protected static volatile DynamicRegistries serverRegistries;

    protected Server() {
        this.serverStatusAF = new AtomicReference<>(ServerStatus.CLOSED);
    }

    /**
     * Retrieves the current status of this server.
     */
    public ServerStatus getServerStatus() {
        return this.serverStatusAF.get();
    }


    public final @NotNull String getServerBrandName() {
        return this.serverBrandName;
    }

    /**
     * Starts this server using a generic socket.
     *
     * @param socket A socket which the server will listen connections.
     */
    public void start(@NotNull SocketAddress socket) {
        if (this.getServerStatus().equals(ServerStatus.OPEN)) {
            throw new IllegalStateException("Server already started.");
        }

        LOGGER.info("Starting {} server.", this.getServerBrandName());

        try {
            // TODO: Implement a call to the socket thread-server.
            // TODO: Implement a call to the tick thread-server.

        } catch (Exception exc) {
            serverProcess.exceptionManager().handleException(exc);
            throw new RuntimeException(exc);
        }
    }


    /**
     * Starts this server using a specific address and port.
     *
     * @param address The port which the socket will listen connections.
     * @param port    The port which the socket will listen.
     */
    public void start(@NotNull String address, int port) {
        this.start(new InetSocketAddress(address, port));
    }


    /**
     * Stops this server.
     */
    public void stop() {
    }
}
