package com.wirethread.network.buffer;

import com.wirethread.core.registry.Registries;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.Objects;
import java.util.function.Consumer;

public final class BufferImpl implements Buffer {

    private final @NotNull ByteBuf buffer;
    private final @Nullable Registries registries;
    private final boolean isDummy;

    private BufferImpl(boolean isDummy, final @NotNull ByteBuf buffer, final @Nullable Registries registries) {
        this.isDummy = isDummy;
        this.buffer = buffer;
        this.registries = registries;
    }

    public BufferImpl(int initialSize, @Nullable Registries registries) {
        this(false, PooledByteBufAllocator.DEFAULT.buffer(initialSize), registries);
    }

    public static Buffer dummy(int initialSize, @Nullable Registries registries) {
        final ByteBuf buffer = Unpooled.buffer(initialSize);
        return new BufferImpl(true, buffer, registries);
    }

    public static <T> int sizeOf(@NotNull Type<T> type, T value) {
        final Buffer dummyBuffer = BufferImpl.dummy(INITIAL_BUFFER_SIZE, null);

        dummyBuffer.write(type, value);

        return dummyBuffer.writeIndex();
    }

    /**
     * Retrieves the internal memory address for this buffer.
     *
     * @return A number which represents a memory address.
     * @apiNote Do not share this memory address.
     */
    @Override
    public long address() {
        return this.buffer.memoryAddress();
    }

    /**
     * Writes a specific type of packet into the buffer.
     *
     * @param type  The packet class.
     * @param value The packet value.
     * @throws IndexOutOfBoundsException If the buffer run out of space.
     */
    @Override
    public <T> void write(@NotNull Type<T> type, T value) throws IndexOutOfBoundsException {
        assertReadOnlyBuffer();

        type.write(this, value);
    }

    /**
     * Reads a specific type of packet from the buffer.
     *
     * @param type The packet class.
     * @return A new packet instance.
     * @throws IndexOutOfBoundsException If the buffer does not contain the specified packet.
     */
    @Override
    public <T> T read(@NotNull Type<T> type) throws IndexOutOfBoundsException {
        assertDummyBuffer();

        return type.read(this);
    }

    @Override
    public void putBytes(int index, byte @NotNull [] byteArray) {
        if (byteArray.length == 0) return;

        assertReadOnlyBuffer();

        this.writeIndex(index);
        this.buffer.writeBytes(byteArray);
    }

    @Override
    public void putByte(int index, byte value) {
        assertReadOnlyBuffer();

        this.writeIndex(index);
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

    @Override
    public <T> void writeAt(int index, @NotNull Type<T> type, T value) throws IndexOutOfBoundsException {

    }

    @Override
    public <T> void readAt(int index, @NotNull Type<T> type, T value) throws IndexOutOfBoundsException {

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

        return false;
    }

    /**
     * @param channel A netty channel.
     * @return The amount of read bytes.
     * @throws IOException Is the channel was closed.
     */
    @Override
    public int readChannel(Channel channel) throws IOException {
        assertReadOnlyBuffer();
        assertDummyBuffer();

        return 0;
    }

    @Override
    public @NotNull Buffer copy(int index, int length, int readIndex, int writeIndex) {
        final boolean isDummyBuffer = this.isDummyOnly();
        final ByteBuf bufferCopy = this.buffer.copy(index, length);

        bufferCopy.readerIndex(readIndex);
        bufferCopy.writerIndex(writeIndex);

        return new BufferImpl(isDummyBuffer, bufferCopy, this.registries);
    }

    @Override
    public @NotNull Buffer copy(int index, int length) {
        return this.copy(index, length, this.readIndex(), this.writeIndex());
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
        return index(0, 0);
    }

    @Override
    public boolean release() {
        return this.buffer.release();
    }

    @Override
    public int writeIndex() {
        return this.buffer.writerIndex();
    }

    @Override
    public @NotNull Buffer writeIndex(int writeIndex) {
        this.buffer.writerIndex(writeIndex);
        return this;
    }

    @Override
    public int readIndex() {
        return this.buffer.readerIndex();
    }

    @Override
    public @NotNull Buffer readIndex(int readIndex) {
        this.buffer.readerIndex(readIndex);
        return this;
    }

    @Override
    public @NotNull Buffer index(int readIndex, int writeIndex) {
        this.buffer.setIndex(readIndex, writeIndex);
        return this;
    }

    @Override
    public int advanceWrite(int length) {
        final int oldWriteIndex = this.writeIndex();

        this.writeIndex(oldWriteIndex + length);

        return oldWriteIndex;
    }

    @Override
    public int advanceRead(int length) {
        final int oldReadIndex = this.readIndex();

        this.readIndex(oldReadIndex + length);

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
        return this.isDummy;
    }

    @Override
    public boolean isReadOnly() {
        return this.buffer.isReadOnly();
    }

    @Override
    public void ensureWritable(int length) {
        assertReadOnlyBuffer();

        this.buffer.ensureWritable(length);
    }

    @Override
    public void compact() {

    }

    @Override
    public String toString() {
        return String.format(
                "Buffer{r%d|w%d->%d, registries=%s, readOnly=%s}",
                this.readIndex(), this.writeIndex(), this.capacity(), this.registries != null, this.isReadOnly()
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BufferImpl buff)) return false;
        if (this.capacity() != buff.capacity()) return false;
        return Objects.equals(buffer, buff.buffer) && Objects.equals(registries, buff.registries);
    }

    @Override
    public int hashCode() {
        return Objects.hash(buffer, registries);
    }
}
