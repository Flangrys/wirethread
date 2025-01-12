package com.wirethread.network.buffer;

import com.wirethread.core.registry.Registries;
import io.netty.buffer.ByteBuf;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public final class BufferImpl implements Buffer {

    private final Resize autoResize;
    private final Registries registries;
    private ByteBuf buffer = null;

    private long readIndex, writeIndex, address, capacity;

    public BufferImpl(long address, long capacity, long readIndex, long writeIndex, @Nullable Resize autoResizer, @Nullable Registries registries) {
        this.address = address;
        this.capacity = capacity;
        this.readIndex = readIndex;
        this.writeIndex = writeIndex;
        this.autoResize = autoResizer;
        this.registries = registries;

        if (address == DUMMY_ADDRESS) return;

        // TODO: Cleaner logic.
        REF_CLEANER.register(this, () -> {

        });
    }

    @Override
    public <T> void write(@NotNull Type<T> type, T value) throws IndexOutOfBoundsException {

    }

    @Override
    public <T> T read(@NotNull Type<T> type) throws IndexOutOfBoundsException {
        return null;
    }

    @Override
    public <T> void writeAt(long index, @NotNull Type<T> type, T value) throws IndexOutOfBoundsException {

    }

    @Override
    public <T> void readAt(long index, @NotNull Type<T> type, T value) throws IndexOutOfBoundsException {

    }

    @Override
    public Buffer copy(long index, long length, long readIndex, long writeIndex) {
        return null;
    }

    @Override
    public Buffer copy(long index, long length) {
        return copy(index, length, readIndex(), writeIndex());
    }

    @Override
    public void copyTo(long srcOffset, byte @NotNull [] dest, long destOffset, long length) {

    }

    @Override
    public byte @NotNull [] extractBytes(@NotNull Consumer<Buffer> filter) {
        return new byte[0];
    }

    /**
     * Return the same buffer but with the readIndex and readIndex at 0.
     *
     * @return This buffer set in overwrite mode.
     */
    @Override
    public @NotNull Buffer clear() {
        return index(0, 0);
    }

    @Override
    public long writeIndex() {
        return 0;
    }

    @Override
    public @NotNull Buffer writeIndex(long writeIndex) {
        return null;
    }

    @Override
    public long readIndex() {
        return 0;
    }

    @Override
    public @NotNull Buffer readIndex(long readIndex) {
        return null;
    }

    @Override
    public @NotNull Buffer index(long readIndex, long writeIndex) {
        this.readIndex = readIndex;
        this.writeIndex = writeIndex;

        return this;
    }

    @Override
    public long advanceWrite(long length) {
        return 0;
    }

    @Override
    public long advanceRead(long length) {
        return 0;
    }

    @Override
    public long readableBytes() {
        return 0;
    }

    @Override
    public long writeableBytes() {
        return 0;
    }

    @Override
    public long capacity() {
        return 0;
    }

    @Override
    public boolean isReadOnly() {
        return false;
    }

    @Override
    public void readOnly() {

    }

    @Override
    public void resize(long newSize) {

    }

    @Override
    public void ensureWritable(long length) {

    }

    @Override
    public void compact() {

    }

    public static Buffer dummy(Registries registries) {
        return new BufferImpl(DUMMY_ADDRESS, Long.MAX_VALUE, 0, 0, null, registries);
    }
}
