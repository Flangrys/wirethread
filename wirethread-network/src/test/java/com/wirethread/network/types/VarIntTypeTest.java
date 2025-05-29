package com.wirethread.network.types;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

class VarIntTypeTest {
    private static ByteBuf buffer;

    @BeforeAll
    static void setUp() {
        buffer = PooledByteBufAllocator.DEFAULT.buffer(5);
    }

    @AfterAll
    static void tearDown() {
        buffer.release();
    }

    @Test
    void testWriteMethodIfWritesAReallyBigNumberAndItsBytesAreCorrect() {
        final int TEST_TARGET_NUMBER = 2147483647;
        final int TEST_TARGET_NUMBER_BYTES_LENGTH = 5;

        assertEquals(buffer.writerIndex(), 0, "The writer index should start at 0.");

        assertDoesNotThrow(
                () -> Primitives.VAR_INT.write(buffer, TEST_TARGET_NUMBER),
                "The write method should not raise an exception."
        );

        assertTrue(buffer.isReadable(TEST_TARGET_NUMBER_BYTES_LENGTH), "This buffer should contain at least " + TEST_TARGET_NUMBER_BYTES_LENGTH + " bytes");
//        assertFalse(buffer.isReadable(5), "This buffer should not contain more than two bytes.");
    }

    @Test
    void testReadMethodIfReadsAReallyBigNumberItsBytesAreCorrect() {
        final int TEST_TARGET_NUMBER = 2147483647;
        final int TEST_TARGET_NUMBER_BYTES_LENGTH = 5;


        assertTrue(buffer.isReadable(TEST_TARGET_NUMBER_BYTES_LENGTH), "This buffer should contain at least " + TEST_TARGET_NUMBER_BYTES_LENGTH + " bytes");
//        assertFalse(buffer.isReadable(5), "This buffer should not contain more than two bytes.");

        AtomicInteger value = new AtomicInteger();
        assertDoesNotThrow(
                () -> value.set(Primitives.VAR_INT.read(buffer)),
                "The read method should not raise an exception."
        );

        assertEquals(TEST_TARGET_NUMBER, value.get());
    }
}