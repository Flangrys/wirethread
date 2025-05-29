package com.wirethread.server.thread;

import com.wirethread.network.codecs.InboundPacketDecoder;
import com.wirethread.network.connection.adapters.PlayerInboundHandlerAdapter;
import com.wirethread.server.exception.ExceptionManager;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.net.SocketAddress;

/**
 * This {@link Thread} extension is responsible for handling the socket server
 * which manages incoming client connections.
 */
public final class SocketServerThread extends ServerThread {

    private static final String WIRETHREAD_SOCKET_SERVER_THREAD = "wirethread-socket-worker";
    private static final @NotNull Logger LOGGER = LogManager.getLogger(SocketServerThread.class);
    private final @NotNull SocketAddress serverSocketAddress;
    private final EventLoopGroup inboundGroup = new NioEventLoopGroup(1);
    private final EventLoopGroup handlerGroup = new NioEventLoopGroup();

    /**
     * Creates a new {@link SocketServerThread}.
     *
     * @param exceptionManager An atomic reference to an exception manager.
     * @param socketAddress    The socket address to bind the server to.
     */
    public SocketServerThread(final ExceptionManager exceptionManager, final @NotNull SocketAddress socketAddress) {
        super(exceptionManager, WIRETHREAD_SOCKET_SERVER_THREAD);
        this.serverSocketAddress = socketAddress;
    }

    @Override
    public void run() {
        try {
            LOGGER.info("Listening for incoming connections on {}.", this.serverSocketAddress);

            final ServerBootstrap bootstrap = new ServerBootstrap()
                    .group(inboundGroup, handlerGroup)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(serverSocketAddress)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)

                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline()
                                    .addLast("packet_decoder", new InboundPacketDecoder())
                                    .addLast("handler", new PlayerInboundHandlerAdapter());
                        }
                    });

            final ChannelFuture f = bootstrap.bind().sync();
            f.channel().closeFuture().sync();

        } catch (Exception e) {
            LOGGER.error("Failed to initialize server.", e);

        } finally {
            // Shutdown the event loop groups.
            this.inboundGroup.shutdownGracefully();
            this.handlerGroup.shutdownGracefully();

            LOGGER.info("Shutting down event loops.");
        }
    }

    /**
     * Requests the thread to shut down gracefully.
     */
    @Override
    public void shutdown() {
        this.inboundGroup.shutdownGracefully();
        this.handlerGroup.shutdownGracefully();

        LOGGER.info("Shutting down the socket server.");
    }
}
