package com.wirethread.network.types;

import io.netty.buffer.ByteBuf;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public final class BooleanType implements Type<Boolean> {
    @Override
    public void write(@NotNull ByteBuf buffer, Boolean value) throws IOException {
        buffer.ensureWritable(1);
        buffer.writeByte(value ? 1 : 0);
    }

    @Override
    public Boolean read(@NotNull ByteBuf buffer) throws IOException {
        final byte bool = buffer.getByte(buffer.readerIndex());
        return switch (bool) {
            case 0 -> false;
            case 1 -> true;
            default -> throw new IllegalStateException("Unexpected value: " + bool + ". Expected 0 or 1.");
        };
    }
}
