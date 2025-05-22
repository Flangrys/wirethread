package com.wirethread.network.buffer;

import com.wirethread.core.registry.records.DynamicRegistries;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.Objects;
import java.util.function.Consumer;

public class AbstractBuffer implements Buffer {
    protected final @Nullable DynamicRegistries registries;
    protected final @NotNull ByteBuf buffer;

    protected AbstractBuffer(int initialCapacity, ByteBufAllocator alloc, @Nullable DynamicRegistries registries) {
        this.buffer = alloc.buffer(initialCapacity);
        this.registries = registries;
    }

    protected AbstractBuffer(int initialCapacity, @Nullable DynamicRegistries registries) {
        this(initialCapacity, PooledByteBufAllocator.DEFAULT, registries);
    }

    protected AbstractBuffer(@NotNull ByteBuf buffer, @Nullable DynamicRegistries registries) {
        this.registries = registries;
        this.buffer = buffer;
    }

    public static <T> int sizeOf(@NotNull Type<T> type, T value) {
        var buffer = new AbstractBuffer(Buffer.INITIAL_BUFFER_SIZE, PooledByteBufAllocator.DEFAULT, null);

        buffer.write(type, value);

        var writeBytes = buffer.writerIndex();

        buffer.release();

        return writeBytes;
    }

    public ReadOnlyProtoBuffer asReadOnly() {
        return new ReadOnlyProtoBuffer(this.buffer, this.registries);
    }

    public DummyProtoBuffer asDummyOnly() {
        return new DummyProtoBuffer(this.buffer, this.registries);
    }

    @Override
    public @NotNull Buffer copy(int index, int length, int readerIndex, int writeIndex) {
        var copy = this.buffer.copy(index, length);
        copy.readerIndex(readerIndex);
        copy.writerIndex(writeIndex);

        return new AbstractBuffer(copy, this.registries);
    }

    @Override
    public @NotNull Buffer copy(int index, int length) {
        return this.copy(index, length, this.readerIndex(), this.writerIndex());
    }

    @Override
    public void copyTo(int srcOffset, byte @NotNull [] dest, int destOffset, int length) {

    }

    @Override
    public byte @NotNull [] extractBytes(@NotNull Consumer<Buffer> filter) {
        return new byte[0];
    }

    @Override
    public @NotNull Buffer clear() {
        this.buffer.clear();
        return this;
    }

    @Override
    public boolean release() {
        return this.buffer.release();
    }

    @Override
    public int writerIndex() {
        return this.buffer.writerIndex();
    }

    @Override
    public @NotNull Buffer writerIndex(int writerIndex) {
        this.buffer.writerIndex(writerIndex);
        return this;
    }

    @Override
    public int readerIndex() {
        return this.buffer.readerIndex();
    }

    @Override
    public @NotNull Buffer readerIndex(int readerIndex) {
        this.buffer.readerIndex(readerIndex);
        return this;
    }

    @Override
    public @NotNull Buffer index(int readerIndex, int writeIndex) {
        this.buffer.setIndex(readerIndex, writeIndex);
        return this;
    }

    @Override
    public int advanceWrite(int length) {
        var oldWriteIndex = this.writerIndex();
        this.writerIndex(oldWriteIndex + length);
        return oldWriteIndex;
    }

    @Override
    public int advanceRead(int length) {
        var oldReadIndex = this.readerIndex();
        this.readerIndex(oldReadIndex + length);
        return oldReadIndex;
    }

    @Override
    public int readableBytes() {
        return this.buffer.readableBytes();
    }

    @Override
    public int writeableBytes() {
        return this.buffer.writableBytes();
    }

    @Override
    public int capacity() {
        return this.buffer.capacity();
    }

    @Override
    public @NotNull Buffer capacity(int newCapacity) {
        this.buffer.capacity(newCapacity);
        return this;
    }

    @Override
    public boolean isDummyOnly() {
        return false;
    }

    @Override
    public boolean isReadOnly() {
        return false;
    }

    @Override
    public void ensureWritable(int length) {
        this.buffer.ensureWritable(length);
    }

    @Override
    public void compact() {

    }

    /**
     * Reads a specific type of object from the buffer.
     *
     * @param type  A serialization type.
     * @param value The {@link Type} to be written.
     * @throws IndexOutOfBoundsException When the {@param index} got out of range.
     */
    @Override
    public <T> void write(@NotNull Type<T> type, T value) throws IndexOutOfBoundsException {
        assertReadOnlyBuffer();
        type.write(this, value);
    }

    /**
     * Writes a specific type of object to an index in the buffer.
     *
     * @param index The writer index.
     * @param type  A deserialization type.
     * @param value The {@code Type<?>} to be written.
     * @throws IndexOutOfBoundsException When the {@param index} got out of range.
     */
    @Override
    public <T> void writeAt(int index, @NotNull Type<T> type, T value) throws IndexOutOfBoundsException {
        assertReadOnlyBuffer();
    }

    /**
     * Reads a specific type of object from the buffer.
     *
     * @param type A deserialization type.
     * @return A new {@link Type} instance of the deserialized type.
     * @throws IndexOutOfBoundsException When the {@param index} got out of range.
     */
    @Override
    public <T> T read(@NotNull Type<T> type) throws IndexOutOfBoundsException {
        return type.read(this);
    }

    /**
     * Reads a specific type of object to an index in the buffer.
     *
     * @param index The reader index.
     * @param type  The {@link Type} to read.
     * @param value The {@code Type<?>} to be read.
     * @throws IndexOutOfBoundsException When the {@param index} got out of range.
     */
    @Override
    public <T> void readAt(int index, @NotNull Type<T> type, T value) throws IndexOutOfBoundsException {
        assertDummyBuffer();
    }

    @Override
    public void putBytes(int index, byte @NotNull [] byteArray) {
        assertDummyBuffer();
        assertReadOnlyBuffer();

        this.writerIndex(index);
        this.buffer.writeBytes(byteArray);
    }

    @Override
    public void putByte(int index, byte value) {
        assertDummyBuffer();
        assertReadOnlyBuffer();

        this.writerIndex(index);
        this.buffer.writeByte(value);
    }

    @Override
    public void getBytes(int index, byte @NotNull [] byteArray) {
        assertDummyBuffer();

        this.buffer.getBytes(index, byteArray);
    }

    @Override
    public byte getByte(int index) {
        assertDummyBuffer();

        return this.buffer.getByte(index);
    }

    /**
     * Write at the given channel returning the amount of written bytes.
     *
     * @param channel A netty channel.
     * @return True if it has not remaining chunks to be written.
     * @throws IOException If the channel was closed.
     */
    @Override
    public boolean writeChannel(Channel channel) throws IOException {
        assertDummyBuffer();
        assertReadOnlyBuffer();

        return false;
    }

    /**
     * @param channel A netty channel.
     * @return The amount of read bytes.
     * @throws IOException Is the channel was closed.
     */
    @Override
    public int readChannel(Channel channel) throws IOException {
        assertDummyBuffer();
        assertReadOnlyBuffer();

        return 0;
    }

    @Override
    public String toString() {
        return "AbstractBuffer{" +
                "buffer=" + buffer +
                ", registries=" + registries +
                ", isReadOnly=" + isReadOnly() +
                ", isDummyOnly=" + isDummyOnly() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractBuffer that)) return false;
        return Objects.equals(registries, that.registries) && Objects.equals(buffer, that.buffer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(registries, buffer);
    }
}
