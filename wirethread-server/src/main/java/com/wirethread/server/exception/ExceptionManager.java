package com.wirethread.server.exception;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ExceptionManager {

    private static final Logger LOGGER = LogManager.getLogger(ExceptionManager.class);

    public void handleException(Throwable throwable) {
        LOGGER.error(throwable);
    }

    public void handleException(String message, Throwable throwable) {
        LOGGER.error(message, throwable);
    }

    public void handleException(Thread thread, Throwable throwable) {
        LOGGER.error("An error occurred during thread {} execution: {}", thread.getName(), throwable.getMessage());
    }
}
