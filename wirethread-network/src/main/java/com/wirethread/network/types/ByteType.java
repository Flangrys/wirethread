package com.wirethread.network.types;

import com.wirethread.network.buffer.Buffer;
import com.wirethread.network.buffer.Type;
import org.jetbrains.annotations.NotNull;

public record ByteType() implements Type<Byte> {
    @Override
    public void write(@NotNull Buffer buffer, Byte value) {
        buffer.ensureWritable(1);
        buffer.putByte(buffer.writerIndex(), value);
        buffer.advanceWrite(1);
    }

    @Override
    public Byte read(@NotNull Buffer buffer) {
        final byte value = buffer.getByte(buffer.readerIndex());
        buffer.advanceRead(1);
        return value;
    }
}
