package com.wirethread.network.types;

import com.wirethread.network.buffer.Buffer;
import com.wirethread.network.buffer.Type;
import org.jetbrains.annotations.NotNull;

public record BooleanType() implements Type<Boolean> {
    @Override
    public void write(@NotNull Buffer buffer, Boolean value) {
        buffer.ensureWritable(1);
        buffer.putByte(buffer.writeIndex(), (byte)(value ? 1 : 0));
        buffer.advanceWrite(1);
    }

    @Override
    public Boolean read(@NotNull Buffer buffer) {
        final byte bool = buffer.getByte(buffer.readIndex());
        buffer.advanceRead(1);
        return bool == 1;
    }
}
