package com.wirethread.network.types;

import com.wirethread.network.buffer.Buffer;
import com.wirethread.network.buffer.TypeImpl;
import org.jetbrains.annotations.NotNull;

public record StringType() implements TypeImpl<String> {
    @Override
    public void write(@NotNull Buffer buffer, String value) {
    }

    @Override
    public String read(@NotNull Buffer buffer) {
        return null;
    }
}
