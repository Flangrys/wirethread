package com.wirethread.network.buffer;

import com.wirethread.core.registry.Registries;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.function.Consumer;

public final class ReadOnlyProtoBuffer extends AbstractBuffer{

    ReadOnlyProtoBuffer(@NotNull ByteBuf buffer, @Nullable Registries registries) {
        super(buffer, registries);
    }

    @Override
    public void putBytes(int index, byte @NotNull [] byteArray) {

    }

    @Override
    public void putByte(int index, byte value) {

    }

    @Override
    public void getBytes(int index, byte @NotNull [] byteArray) {

    }

    @Override
    public byte getByte(int index) {
        return 0;
    }

    @Override
    public @NotNull Buffer copy(int index, int length, int readerIndex, int writeIndex) {
        return null;
    }

    @Override
    public @NotNull Buffer copy(int index, int length) {
        return null;
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
        return null;
    }

    @Override
    public boolean release() {
        return false;
    }

    @Override
    public int writerIndex() {
        return 0;
    }

    @Override
    public @NotNull Buffer writerIndex(int writerIndex) {
        return null;
    }

    @Override
    public int readerIndex() {
        return 0;
    }

    @Override
    public @NotNull Buffer readerIndex(int readerIndex) {
        return null;
    }

    @Override
    public @NotNull Buffer index(int readerIndex, int writeIndex) {
        return null;
    }

    @Override
    public int advanceWrite(int length) {
        return 0;
    }

    @Override
    public int advanceRead(int length) {
        return 0;
    }

    @Override
    public int readableBytes() {
        return 0;
    }

    @Override
    public int writeableBytes() {
        return 0;
    }

    @Override
    public int capacity() {
        return 0;
    }

    @Override
    public @NotNull Buffer capacity(int newCapacity) {
        return null;
    }

    @Override
    public boolean isDummyOnly() {
        return false;
    }

    @Override
    public boolean isReadOnly() {
        return true;
    }

    @Override
    public void ensureWritable(int length) {

    }

    @Override
    public void compact() {

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
        return false;
    }

    /**
     * @param channel A netty channel.
     * @return The amount of read bytes.
     * @throws IOException Is the channel was closed.
     */
    @Override
    public int readChannel(Channel channel) throws IOException {
        return 0;
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
        return null;
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

    }
}
