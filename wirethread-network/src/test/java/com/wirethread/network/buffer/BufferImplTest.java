package com.wirethread.network.buffer;

import com.wirethread.network.types.BooleanType;
import com.wirethread.network.types.StringType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import static com.wirethread.network.buffer.Buffer.INITIAL_BUFFER_SIZE;
import static org.junit.jupiter.api.Assertions.*;

class BufferImplTest {

    private static final Logger logger = LogManager.getLogger(BufferImplTest.class);

    private static final boolean DUMMY_BOOLEAN = false;
    private static final String DUMMY_STRING = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.";

    private static void releaseBuffer(final @NotNull Buffer buffer) {
        if (buffer.release()) {
            logger.info("Successfully released the buffer.");
        } else {
            logger.warn("The buffer still allocated.");
        }

    }

    private static void logBufferInfo(final @NotNull Buffer buffer) {
        logger.info("Showing dummy buffer info: C{} bytes <- rx{} | wx{}", buffer.capacity(), buffer.readIndex(), buffer.writeIndex());
    }

    private static void logBufferStats(final @NotNull Buffer buffer) {
        logger.info("Showing dummy buffer stats: C{} bytes <- w{} bytes | r{} bytes", buffer.capacity(), buffer.writeableBytes(), buffer.readableBytes());
    }

    @Test
    void testWriteABooleanValueIntoADummyBuffer_WithCorrectInputs_ExpectingReadTheSameValue() {
        logger.trace("Creating new dummy buffer.");
        final Buffer dummyBuffer = new BufferImpl(INITIAL_BUFFER_SIZE, null);
        logBufferInfo(dummyBuffer);

        logger.trace("Writing the dummy string: {}", DUMMY_BOOLEAN);
        dummyBuffer.write(new BooleanType(), DUMMY_BOOLEAN);
        logBufferStats(dummyBuffer);

        logger.trace("Reading the dummy string.");
        final boolean value = dummyBuffer.read(new BooleanType());
        logBufferStats(dummyBuffer);

        logBufferInfo(dummyBuffer);
        logger.info("Read value: {}", value);

        releaseBuffer(dummyBuffer);

        assertEquals(value, DUMMY_BOOLEAN);
    }

    @Test
    void tesWriteAStringValueIntoADummyBuffer_WithCorrectInputs_ExpectingReadTheSameValue() {
        logger.trace("Creating new dummy buffer.");
        final Buffer dummyBuffer = new BufferImpl(INITIAL_BUFFER_SIZE, null);
        logBufferInfo(dummyBuffer);

        logger.trace("Writing the dummy string: {}", DUMMY_STRING);
        dummyBuffer.write(new StringType(), DUMMY_STRING);
        logBufferStats(dummyBuffer);


        logger.trace("Reading the dummy string.");
        final String value = dummyBuffer.read(new StringType());
        logBufferStats(dummyBuffer);

        logBufferInfo(dummyBuffer);
        logger.info("Read value: {}", value);

        releaseBuffer(dummyBuffer);

        assertEquals(value, DUMMY_STRING);
    }

    @Test
    void testCopyMethodAndCheckingIfBothAreEquals_WithoutArgs_ExpectingComparingTheSameBuffer() {
        logger.trace("Creating new dummy buffer.");
        final Buffer dummyBuffer = BufferImpl.dummy(INITIAL_BUFFER_SIZE, null);
        logBufferInfo(dummyBuffer);

        final Buffer dummyBufferCopy = dummyBuffer.copy(0, dummyBuffer.capacity());
        logBufferInfo(dummyBufferCopy);

        assertEquals(dummyBuffer, dummyBufferCopy);

        releaseBuffer(dummyBuffer);
        releaseBuffer(dummyBufferCopy);
    }

    @Test
    void testClearMethod_Using00AsIndex_ExpectingCleanTheBufferAndOverwriteValues() {
    }

    @Test
    void testMeasuringTheSizeOfATypeUsingADummyBuffer_WithAStringType_ExpectingMeasureThePacketSizeOfThePacket() {
        logger.trace("Creating a new dummy buffer.");
        final Buffer dummyBuffer = BufferImpl.dummy(INITIAL_BUFFER_SIZE, null);
        logBufferInfo(dummyBuffer);

        try {
            dummyBuffer.write(new StringType(), DUMMY_STRING);
            logBufferStats(dummyBuffer);
        } catch (UnsupportedOperationException exc) {
            fail("This test fail because the write method should be allowed to write over a dummy buffer.");
        }

        final int bytesWrittenDummy = dummyBuffer.writeIndex();
        final int bytesWrittenReference = BufferImpl.sizeOf(new StringType(), DUMMY_STRING);

        logBufferInfo(dummyBuffer);
        releaseBuffer(dummyBuffer);

        assertEquals(bytesWrittenDummy, bytesWrittenReference);
    }
}