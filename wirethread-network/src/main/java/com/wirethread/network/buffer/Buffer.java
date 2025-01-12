package com.wirethread.network.buffer;

import com.wirethread.core.registry.Registries;
import org.jetbrains.annotations.NotNull;

import java.lang.ref.Cleaner;
import java.util.function.Consumer;

public interface Buffer {

    Cleaner REF_CLEANER = Cleaner.create();

    long DUMMY_ADDRESS = -1;

    <T> void write(@NotNull Type<T> type, T value) throws IndexOutOfBoundsException;

    <T> T read(@NotNull Type<T> type) throws IndexOutOfBoundsException;

    <T> void writeAt(long index, @NotNull Type<T> type, T value) throws IndexOutOfBoundsException;

    <T> void readAt(long index, @NotNull Type<T> type, T value) throws IndexOutOfBoundsException;

    Buffer copy(long index, long length, long readIndex, long writeIndex);
    Buffer copy(long index, long length);

    void copyTo(long srcOffset, byte @NotNull [] dest, long destOffset, long length);

    byte @NotNull [] extractBytes(@NotNull Consumer<Buffer> filter);

    @NotNull Buffer clear();

    long writeIndex();
    @NotNull Buffer writeIndex(long writeIndex);

    long readIndex();
    @NotNull Buffer readIndex(long readIndex);

    @NotNull Buffer index(long readIndex, long writeIndex);

    long advanceWrite(long length);

    long advanceRead(long length);

    long readableBytes();

    long writeableBytes();

    long capacity();


    boolean isReadOnly();

    /**
     * Set this buffer as read-only.
     */
    void readOnly();

    void resize(long newSize);

    void ensureWritable(long length);

    void compact();
}
