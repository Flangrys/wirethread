package com.wirethread.server;

import com.wirethread.core.registry.records.DynamicRegistries;
import com.wirethread.core.registry.records.StaticRegistries;
import com.wirethread.server.exception.ExceptionManager;
import com.wirethread.server.thread.ServerThread;
import com.wirethread.server.thread.SocketServerThread;
import com.wirethread.server.thread.TickServerThread;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Models an abstract minecraft server.
 */
public abstract class Server {

    private static final String WIRETHREAD_BRAND_NAME = "Wirethread";

    protected static final Logger LOGGER = LogManager.getLogger(Server.class);

    /**
     * Contains a reference to the global server state.
     */
    protected final AtomicReference<State> serverState;

    /**
     * Contains each of the server process managers.
     */
    protected volatile ServerProcess serverProcess;

    /**
     * Contains each dynamic game registry.
     */
    protected volatile DynamicRegistries serverRegistries;

    /**
     * Contains each static game registry.
     */
    private volatile StaticRegistries staticRegistries;

    private ServerThread socketWorker;
    private ServerThread tickWorker;


    protected Server() {
        this.serverState = new AtomicReference<>(State.STOPPED);
        this.serverProcess = new ServerProcess(
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                new ExceptionManager()
        );
    }

    /**
     * Retrieves the current status of this server.
     */
    public State getServerStatus() {
        return null;
    }


    /**
     * Retrieves the server brand name.
     */
    public final @NotNull String getServerBrandName() {
        return WIRETHREAD_BRAND_NAME;
    }

    /**
     * Starts this server using a generic socket.
     *
     * @param socketAddress A socket that the server will listen connections.
     */
    public void start(@NotNull SocketAddress socketAddress) {
        if (!this.serverState.compareAndSet(State.STOPPED, State.STARTED)) {
            throw new IllegalStateException("Server already started.");
        }

        LOGGER.info("Starting {} server.", this.getServerBrandName());

        var exceptionManager = this.serverProcess.exceptionManager();

        try {
            this.tickWorker = new TickServerThread(exceptionManager);
            this.tickWorker.start();

            this.socketWorker = new SocketServerThread(exceptionManager, socketAddress);
            this.socketWorker.start();

        } catch (Exception exc) {
            exceptionManager.handleException(exc);
            throw new RuntimeException(exc);
        }
    }


    /**
     * Starts this server using a specific address and port.
     *
     * @param address The port which the socket will listen connections.
     * @param port    The port which the socket will listen connections.
     */
    public void start(@NotNull String address, int port) {
        // TODO: Catch the IllegalArgumentException thrown by the socket address constructor.
        this.start(new InetSocketAddress(address, port));
    }


    /**
     * Stops this server.
     */
    public void stop() {
        if (!this.serverState.compareAndSet(State.STARTED, State.STOPPED)) {
            throw new IllegalStateException("Server already stopped.");
        }

        var exceptionManager = this.serverProcess.exceptionManager();

        try {
            this.socketWorker.shutdown();
            this.tickWorker.shutdown();

        } catch (Exception exc){
            exceptionManager.handleException(exc);
            throw new RuntimeException(exc);
        }
    }
}
