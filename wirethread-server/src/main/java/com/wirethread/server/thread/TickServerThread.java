package com.wirethread.server.thread;

import com.wirethread.server.exception.ExceptionManager;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public final class TickServerThread extends ServerThread {

    public static final String WIRETHREAD_TICK_SERVER_THREAD = "wirethread-tick-worker";

    public TickServerThread(final ExceptionManager exceptionManager) {
        super(exceptionManager, WIRETHREAD_TICK_SERVER_THREAD);
    }

    @Override
    public void run() {
    }

    /**
     * Requests the thread to shut down gracefully.
     *
     * <p> The thread should monitor {@link #isWorkerStopped} during its execution,
     * and exit any loops or block points as soon as possible to finish gracefully.
     */
    @Override
    public void shutdown() {

    }
}
