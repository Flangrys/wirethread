package com.wirethread.network.types;

import com.wirethread.network.buffer.Buffer;
import com.wirethread.network.buffer.Type;
import org.jetbrains.annotations.NotNull;

import java.nio.charset.StandardCharsets;

public record StringType() implements Type<String> {
    @Override
    public void write(@NotNull Buffer buffer, String value) {
        final byte @NotNull [] bytes = value.getBytes(StandardCharsets.UTF_8);
        buffer.write(Primitives.BYTE_ARRAY, bytes);
    }

    @Override
    public String read(@NotNull Buffer buffer) {
        final byte[] bytes = buffer.read(Primitives.BYTE_ARRAY);
        return new String(bytes, StandardCharsets.UTF_8);
    }
}
