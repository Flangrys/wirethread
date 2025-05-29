package com.wirethread.network.types;

import io.netty.buffer.ByteBuf;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public final class RawBytesType implements Type<byte[]> {

    private final int length;

    /**
     * Creates a new instance of {@link RawBytesType} with a specified length.
     *
     * @param length The length of the byte array to read/write. Use -1 for variable length.
     */
    public RawBytesType(int length) {
        if (length < -1 || length == 0) {
            throw new IllegalArgumentException("Length must be -1 (variable) or a positive integer.");
        }
        this.length = length;
    }

    @Override
    public void write(@NotNull ByteBuf buffer, byte[] value) throws IOException {
        int actualLength = value.length;

        if (length != -1 && actualLength != length) {
            throw new IllegalArgumentException("Expected byte array of length " + length + ", but got " + actualLength + ".");
        }

        if (actualLength > 0) {
            buffer.ensureWritable(actualLength);
            buffer.writeBytes(value);
        }
    }

    @Override
    public byte[] read(@NotNull ByteBuf buffer) throws IOException {
        int available = buffer.readableBytes();

        int actualLength = (length == -1) ? available : Math.min(length, available);

        if (actualLength < 0) {
            throw new IllegalStateException("Negative length encountered while reading bytes.");
        }

        byte[] result = new byte[actualLength];

        if (actualLength > 0) {
            buffer.readBytes(result);
        }

        return result;
    }
}
