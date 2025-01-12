package com.wirethread.network.types;

import com.wirethread.network.buffer.Buffer;
import com.wirethread.network.buffer.TypeImpl;
import org.jetbrains.annotations.NotNull;

public record BooleanType() implements TypeImpl<Boolean> {
    @Override
    public void write(@NotNull Buffer buffer, Boolean value) {
    }

    @Override
    public Boolean read(@NotNull Buffer buffer) {
        return false;
    }
}
