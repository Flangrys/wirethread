package com.wirethread.network.buffer;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.function.Consumer;

/**
 * This interface defines a minimal wrapper for the {@link ByteBuf} buffer
 * providing access to packet-related methods and other IO-related features.
 */
public interface Buffer {

    int INITIAL_BUFFER_SIZE = 4096;

    /**
     * Asserts when this buffer is a dummy buffer.
     * @throws UnsupportedOperationException If the buffer is a dummy buffer.
     */
    default void assertDummyBuffer() throws UnsupportedOperationException {
        if (this.isDummyOnly()) throw new UnsupportedOperationException("Buffer is dummy.");
    }

    /**
     * Asserts when this buffer is a read-only buffer.
     * @throws UnsupportedOperationException If the buffer is a read-only buffer.
     */
    default void assertReadOnlyBuffer() throws UnsupportedOperationException {
        if (this.isReadOnly()) throw new UnsupportedOperationException("Buffer is read-only.");
    }

    /**
     * Retrieves the internal memory address for this buffer.
     *
     * @return An integer which represents a memory address.
     * @apiNote Do not share this memory address.
     */
    long address();

    /**
     * Writes a specific type of packet into the buffer.
     * @param type The packet class.
     * @param value The packet value.
     * @param <T> The kind of the packet.
     * @throws IndexOutOfBoundsException If the buffer run out of space.
     */
    <T> void write(@NotNull Type<T> type, T value) throws IndexOutOfBoundsException;

    /**
     * Reads a specific type of packet from the buffer.
     * @param type The packet class.
     * @return A new packet instance.
     * @param <T> The packet type.
     * @throws IndexOutOfBoundsException If the buffer does not contain the specified packet.
     */
    <T> T read(@NotNull Type<T> type) throws IndexOutOfBoundsException;

    void putBytes(int index, byte @NotNull [] byteArray);

    void putByte(int index, byte value);

    void getBytes(int index, byte @NotNull [] byteArray);

    byte getByte(int index);

    <T> void writeAt(int index, @NotNull Type<T> type, T value) throws IndexOutOfBoundsException;

    <T> void readAt(int index, @NotNull Type<T> type, T value) throws IndexOutOfBoundsException;

    /**
     * Write at the given channel returning the amount of written bytes.
     * @param channel A netty channel.
     * @return True if it has not remaining chunks to be written.
     * @throws IOException If the channel was closed.
     */
    boolean writeChannel(Channel channel) throws IOException;

    /**
     *
     * @param channel A netty channel.
     * @return The amount of read bytes.
     * @throws IOException Is the channel was closed.
     */
    int readChannel(Channel channel) throws IOException;


    @NotNull
    Buffer copy(int index, int length, int readIndex, int writeIndex);

    @NotNull
    Buffer copy(int index, int length);

    void copyTo(int srcOffset, byte @NotNull [] dest, int destOffset, int length);

    byte @NotNull [] extractBytes(@NotNull Consumer<Buffer> filter);

    @NotNull
    Buffer clear();

    boolean release();

    int writeIndex();

    @NotNull
    Buffer writeIndex(int writeIndex);

    int readIndex();

    @NotNull
    Buffer readIndex(int readIndex);

    @NotNull
    Buffer index(int readIndex, int writeIndex);

    int advanceWrite(int length);

    int advanceRead(int length);

    int readableBytes();

    int writeableBytes();

    int capacity();

    @NotNull
    Buffer capacity(int newCapacity);

    boolean isDummyOnly();

    boolean isReadOnly();

    void ensureWritable(int length);

    void compact();
}
