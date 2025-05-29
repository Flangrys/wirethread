package com.wirethread.network.types;

import io.netty.buffer.ByteBuf;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public final class ByteArrayType implements Type<byte[]> {
    @Override
    public void write(@NotNull ByteBuf buffer, byte[] value) throws IOException {
        Primitives.VAR_INT.write(buffer, value.length);
        Primitives.RAW_BYTES.write(buffer, value);
    }

    @Override
    public byte[] read(@NotNull ByteBuf buffer) throws IOException {
        final int byteArrayLength = Primitives.VAR_INT.read(buffer);

        if (byteArrayLength == 0) return new byte[0];

        final int remainingBytes = buffer.readableBytes();

        if (remainingBytes < byteArrayLength) {
            throw new IllegalArgumentException("Not enough bytes in buffer to read byte array. Expected: " + byteArrayLength + ", but got: " + remainingBytes);
        }

        return new RawBytesType(byteArrayLength).read(buffer);
    }
}
