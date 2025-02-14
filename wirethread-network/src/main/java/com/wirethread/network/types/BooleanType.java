package com.wirethread.network.types;

import com.wirethread.network.buffer.Buffer;
import com.wirethread.network.buffer.Type;
import org.jetbrains.annotations.NotNull;

public record BooleanType() implements Type<Boolean> {
    @Override
    public void write(@NotNull Buffer buffer, Boolean value) {
        buffer.ensureWritable(1);
        buffer.putByte(buffer.writerIndex(), (byte)(value ? 1 : 0));
        buffer.advanceWrite(1);
    }

    @Override
    public Boolean read(@NotNull Buffer buffer) {
        final byte bool = buffer.getByte(buffer.readerIndex());
        buffer.advanceRead(1);
        return switch(bool) {
            case 0 -> false;
            case 1 -> true;
            default -> throw new IllegalStateException("Cannot read < %d > because is an invalid boolean value.".formatted(bool));
        };
    }
}
