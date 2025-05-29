package com.wirethread.server.thread;

import com.wirethread.server.exception.ExceptionManager;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Models a reusable server thread that handles a specific core
 * functionality of the server, such as accepting connections,
 * processing requests, or handling background tasks.
 * <p>
 * This class is abstract and should be extended to define
 * specific server thread behavior in {@link #run()}.
 */
public abstract class ServerThread extends Thread {

    /**
     * Creates a new server thread with the given name, shared stop signal,
     * and exception handling strategy.
     *
     * @param exceptionManager An {@link ExceptionManager} instance wrapped in an {@link AtomicReference}.
     * @param threadName       A human-readable name for the thread.
     */
    public ServerThread(final @NotNull ExceptionManager exceptionManager, final @NotNull String threadName) {
        super(threadName);

        this.setUncaughtExceptionHandler(exceptionManager::handleException);
    }

    /**
     * Requests the thread to shut down gracefully.
     */
    public abstract void shutdown();
}

