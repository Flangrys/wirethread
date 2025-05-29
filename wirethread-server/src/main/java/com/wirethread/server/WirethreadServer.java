package com.wirethread.server;

import com.wirethread.core.testing.TestingContext;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

public final class WirethreadServer extends Server {

    public static void main(String[] args) {
        final WirethreadServer server = new WirethreadServer();
        final SocketAddress serverAddress = new InetSocketAddress("localhost", 25565);

        server.start(serverAddress);

        if (TestingContext.isUnsafePerformThreadsOperations()) {
            Runtime.getRuntime().addShutdownHook(new Thread(server::stop));
        }
    }
}