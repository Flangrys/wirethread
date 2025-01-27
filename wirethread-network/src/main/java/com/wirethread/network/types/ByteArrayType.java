package com.wirethread.network.types;

import com.wirethread.network.buffer.Buffer;
import com.wirethread.network.buffer.Type;
import org.jetbrains.annotations.NotNull;

public record ByteArrayType() implements Type<byte[]> {
    @Override
    public void write(@NotNull Buffer buffer, byte[] value) {
        buffer.write(Primitives.VAR_INT, value.length);
        buffer.write(Primitives.RAW_BYTES, value);
    }

    @Override
    public byte[] read(@NotNull Buffer buffer) {
        final int byteArrayLength = buffer.read(Primitives.VAR_INT);
        if (byteArrayLength == 0) return new byte[0];

        final int remainingBytes = buffer.readableBytes();
        if (remainingBytes < byteArrayLength) {
            throw new IllegalArgumentException(
                    String.format("String is too long: (Remaining bytes: %d, Value length: %d)", remainingBytes, byteArrayLength)
            );
        }

        return buffer.read(new RawBytesType(byteArrayLength));
    }
}
