package com.wirethread.network.types;

import com.wirethread.network.buffer.Buffer;
import com.wirethread.network.buffer.Type;
import org.jetbrains.annotations.NotNull;

public record RawBytesType(int length) implements Type<byte[]> {
    @Override
    public void write(@NotNull Buffer buffer, byte[] value) {
        if (length != -1 && value.length != length) {
            throw new IllegalArgumentException("Invalid length for buffer.");
        }

        if (value.length == 0) return;

        buffer.ensureWritable(value.length);
        buffer.putBytes(buffer.writeIndex(), value);
        buffer.advanceWrite(value.length);
    }

    @Override
    public byte[] read(@NotNull Buffer buffer) {
        int byteArrayLength = buffer.readableBytes();

        if (this.length != -1) {
            byteArrayLength = Math.min(byteArrayLength, this.length);
        }

        if (byteArrayLength == 0) return new byte[0];
        if (byteArrayLength <= 0) throw new IllegalArgumentException("Invalid remaining: " + byteArrayLength);

        final byte[] byteArray = new byte[byteArrayLength];

        buffer.getBytes(buffer.readIndex(), byteArray);
        buffer.advanceRead(byteArrayLength);

        return byteArray;
    }
}
