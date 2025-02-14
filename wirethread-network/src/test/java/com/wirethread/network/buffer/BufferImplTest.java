package com.wirethread.network.buffer;

import com.wirethread.network.packet.Packet;
import com.wirethread.network.packet.server.status.StatusResponsePacket;
import com.wirethread.network.types.BooleanType;
import com.wirethread.network.types.StringType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import static com.wirethread.network.buffer.Buffer.INITIAL_BUFFER_SIZE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class BufferImplTest {

    private static final Logger logger = LogManager.getLogger(BufferImplTest.class);

    private static final boolean DUMMY_BOOLEAN = false;
    private static final String DUMMY_MOTD = "{'response': 'Minecraft Server 1.21.2 - By Wirethread V0.1.0'}";
    private static final String DUMMY_STRING = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.";

    private static @NotNull Buffer buildBuffer() {
        final Buffer buffer = new DummyProtoBuffer(INITIAL_BUFFER_SIZE, null);

        logger.info("Creating a new buffer with : {}", buffer);

        return buffer;
    }

    private static void releaseBuffer(final @NotNull Buffer buffer) {
        if (buffer.release()) {
            logger.info("Successfully released the buffer.");
        } else {
            logger.warn("The buffer still allocated.");
        }
    }

    private static <T> void traceWriteMethod(Class<? extends Type<T>> type, T value) {
        logger.info("Writing: '{}' of type <{}> into the buffer.", type.getName(), value);
    }

    private static <T> void traceReadMethod(Class<? extends Type<T>> type, T value) {
        logger.info("Reading: '{}' of type <{}> from the buffer.", type.getName(), value);
    }

    @Test
    void testWriteABooleanValueIntoADummyBuffer_WithCorrectInputs_ExpectingReadTheSameValue() {
        final Buffer buffer = buildBuffer();

        traceWriteMethod(BooleanType.class, DUMMY_BOOLEAN);
        buffer.write(new BooleanType(), DUMMY_BOOLEAN);

        final boolean value = buffer.read(new BooleanType());
        traceReadMethod(BooleanType.class, value);

        logger.info(buffer);
        releaseBuffer(buffer);

        assertEquals(DUMMY_BOOLEAN, value);
    }

    @Test
    void tesWriteAStringValueIntoADummyBuffer_WithCorrectInputs_ExpectingReadTheSameValue() {
        final Buffer buffer = buildBuffer();

        traceWriteMethod(StringType.class, DUMMY_STRING);
        buffer.write(new StringType(), DUMMY_STRING);

        final String value = buffer.read(new StringType());
        traceReadMethod(StringType.class, value);

        logger.info(buffer);
        releaseBuffer(buffer);

        assertEquals(value, DUMMY_STRING);
    }

    @Test
    void testCopyMethodAndCheckingIfBothAreEquals_WithoutArgs_ExpectingComparingTheSameBuffer() {
        final Buffer buffer = buildBuffer();

        var bufferCopy = buffer.copy(0, buffer.capacity());
        logger.info(bufferCopy);

        assertEquals(buffer, bufferCopy);

        releaseBuffer(buffer);
        releaseBuffer(bufferCopy);
    }

    @Test
    void testClearMethod_Using00AsIndex_ExpectingCleanTheBufferAndOverwriteValues() {

    }

    @Test
    void testMeasuringTheSizeOfATypeUsingADummyBuffer_WithAStringType_ExpectingMeasureThePacketSizeOfThePacket() {
        final Buffer buffer = buildBuffer();

        try {
            traceWriteMethod(StringType.class, DUMMY_STRING);
            buffer.write(new StringType(), DUMMY_STRING);
        } catch (UnsupportedOperationException exc) {
            fail("This test fail because the write method should be allowed to write over a dummy buffer.");
        }

        final int bytesWrittenDummy = buffer.writerIndex();
        final int bytesWrittenReference = AbstractBuffer.sizeOf(new StringType(), "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.");

        logger.info(buffer);
        releaseBuffer(buffer);

        assertEquals(bytesWrittenDummy, bytesWrittenReference);
    }

    @Test
    void testWriteAPacketAndCheckingIfBothAreEquals_WithoutArgs_ExpectingComparingTheSamePacket() {
        final Buffer buffer = buildBuffer();

        traceWriteMethod(StringType.class, DUMMY_MOTD);
        buffer.write(StatusResponsePacket.SERIALIZER, new StatusResponsePacket(DUMMY_MOTD));

        final StatusResponsePacket packet = buffer.read(StatusResponsePacket.SERIALIZER);
        traceReadMethod(StringType.class, packet.jsonResponse());

        logger.info(buffer);
        releaseBuffer(buffer);

        assertEquals(DUMMY_MOTD, packet.jsonResponse());
    }
}