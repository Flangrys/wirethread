package com.wirethread.network.types;

import io.netty.buffer.ByteBuf;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public final class ByteType implements Type<Byte> {
    @Override
    public void write(@NotNull ByteBuf buffer, Byte value) throws IOException {
        buffer.ensureWritable(1);
        buffer.writeByte(value);
    }

    @Override
    public Byte read(@NotNull ByteBuf buffer) throws IOException {
        return buffer.readByte();
    }
}
