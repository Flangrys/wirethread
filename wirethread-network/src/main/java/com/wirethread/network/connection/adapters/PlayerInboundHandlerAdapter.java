package com.wirethread.network.connection.adapters;

import com.wirethread.network.connection.ConnectionState;
import com.wirethread.network.packet.utils.PacketInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.net.SocketAddress;

/**
 * This {@link ChannelInboundHandlerAdapter} extension is responsible for
 * handling the player connection.
 */
public final class PlayerInboundHandlerAdapter extends ChannelInboundHandlerAdapter {

    private static final Logger LOGGER = LogManager.getLogger(PlayerInboundHandlerAdapter.class);

    /**
     * Initializes the connection state to {@link ConnectionState#HANDSHAKE}.
     *
     * <p> This is the first thing that happens when a client connects to the server
     * and is used to determine how to handle the incoming packets.
     */
    private final @NotNull ConnectionState INITIAL_CONNECTION_STATE = ConnectionState.HANDSHAKE;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        final SocketAddress clientAddress = ctx.channel().remoteAddress();

        if (msg instanceof PacketInfo packet) {
            LOGGER.info("Dispatched event at the event queue: {}", packet);

        } else {
            LOGGER.info("Received a packet from {}: {}", clientAddress, msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        LOGGER.error("Exception caught in player inbound handler.", cause);
    }
}
