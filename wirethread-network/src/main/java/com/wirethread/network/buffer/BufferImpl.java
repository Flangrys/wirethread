package com.wirethread.network.buffer;

import com.wirethread.core.registry.records.DynamicRegistries;
import io.netty.buffer.ByteBuf;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class BufferImpl extends AbstractBuffer {

    private BufferImpl(@NotNull ByteBuf buffer, @Nullable DynamicRegistries registries) {
        super(buffer, registries);
    }

    /**
     * Writes a specific type of packet into the buffer.
     *
     * @param type  The packet class.
     * @param value The packet value.
     * @throws IndexOutOfBoundsException If the buffer run out of space.
     */
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
    public <T> T read(@NotNull Type<T> type) throws IndexOutOfBoundsException {
        assertDummyBuffer();

        return type.read(this);
    }

    @Override
    public void putBytes(int index, byte @NotNull [] byteArray) {
        if (byteArray.length == 0) return;

        assertReadOnlyBuffer();

        this.writerIndex(index);
        this.buffer.writeBytes(byteArray);
    }

    @Override
    public void putByte(int index, byte value) {
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
}
