package com.wirethread.network.types;

import com.wirethread.network.buffer.Buffer;
import com.wirethread.network.buffer.TypeImpl;
import org.jetbrains.annotations.NotNull;

public record ByteType() implements TypeImpl<Byte> {
    @Override
    public void write(@NotNull Buffer buffer, Byte value) {

    }

    @Override
    public Byte read(@NotNull Buffer buffer) {
        return 0;
    }
}
