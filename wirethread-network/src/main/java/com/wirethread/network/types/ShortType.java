package com.wirethread.network.types;

import io.netty.buffer.ByteBuf;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public final class ShortType implements Type<Short> {
    @Override
    public Short read(@NotNull ByteBuf buffer) throws IOException {
        return buffer.readShort();
    }

    @Override
    public void write(@NotNull ByteBuf buffer, Short value) throws IOException {
        buffer.writeShort(value);
    }
}
