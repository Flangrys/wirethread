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
     * Reads a specific type of object from the buffer.
     *
     * @param type  A serialization type.
     * @param value The {@link Type} to be written.
     * @param <T>   The primitive type contained into the {@code Type<?>}.
     * @throws IndexOutOfBoundsException When the {@param index} got out of range.
     */
    <T> void write(@NotNull Type<T> type, T value) throws IndexOutOfBoundsException;

    /**
     * Writes a specific type of object to an index in the buffer.
     *
     * @param index The writer index.
     * @param type  A deserialization type.
     * @param value The {@code Type<?>} to be written.
     * @param <T>   The primitive type contained into the {@code Type<?>}.
     * @throws IndexOutOfBoundsException When the {@param index} got out of range.
     */
    <T> void writeAt(int index, @NotNull Type<T> type, T value) throws IndexOutOfBoundsException;

    /**
     * Reads a specific type of object from the buffer.
     *
     * @param type A deserialization type.
     * @return A new {@link Type} instance of the deserialized type.
     * @param <T> The primitive type contained into the {@code Type<?>}.
     * @throws IndexOutOfBoundsException When the {@param index} got out of range.
     */
    <T> T read(@NotNull Type<T> type) throws IndexOutOfBoundsException;

    /**
     * Reads a specific type of object to an index in the buffer.
     *
     * @param index The reader index.
     * @param type  The {@link Type} to read.
     * @param value The {@code Type<?>} to be read.
     * @param <T>   The primitive type contained into the {@code Type<?>}.
     * @throws IndexOutOfBoundsException When the {@param index} got out of range.
     */
    <T> void readAt(int index, @NotNull Type<T> type, T value) throws IndexOutOfBoundsException;

    void putBytes(int index, byte @NotNull [] byteArray);

    void putByte(int index, byte value);

    void getBytes(int index, byte @NotNull [] byteArray);

    byte getByte(int index);

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
    Buffer copy(int index, int length, int readerIndex, int writeIndex);

    @NotNull
    Buffer copy(int index, int length);

    void copyTo(int srcOffset, byte @NotNull [] dest, int destOffset, int length);

    byte @NotNull [] extractBytes(@NotNull Consumer<Buffer> filter);

    @NotNull
    Buffer clear();

    boolean release();

    int writerIndex();

    @NotNull
    Buffer writerIndex(int writerIndex);

    int readerIndex();

    @NotNull
    Buffer readerIndex(int readerIndex);

    @NotNull
    Buffer index(int readerIndex, int writeIndex);

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
