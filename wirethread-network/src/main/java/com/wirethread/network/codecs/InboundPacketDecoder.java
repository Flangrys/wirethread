package com.wirethread.network.codecs;

import com.wirethread.network.packet.utils.PacketInfo;
import com.wirethread.network.types.Primitives;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * This extension of {@link ByteToMessageDecoder} is responsible for decoding
 * incoming packets from the client.
 */
public final class InboundPacketDecoder extends ByteToMessageDecoder {

    private static final Logger LOGGER = LogManager.getLogger(InboundPacketDecoder.class);

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf buffer, List<Object> out) throws Exception {
        if (buffer.readableBytes() < 8) return;

        PacketInfo packetInfo = new PacketInfo(0, 0);

        try {
            packetInfo = PacketInfo.SERIALIZER.read(buffer);

        } catch (Exception e) {
            LOGGER.error("Paso algo aca adentro", e);
        }

        out.add(packetInfo);
    }
}
