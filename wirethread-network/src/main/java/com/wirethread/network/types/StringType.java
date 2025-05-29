package com.wirethread.network.types;

import io.netty.buffer.ByteBuf;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public final class StringType implements Type<String> {
    @Override
    public void write(@NotNull ByteBuf buffer, String value) throws IOException {
        final byte @NotNull [] bytes = value.getBytes(StandardCharsets.UTF_8);
        Primitives.BYTE_ARRAY.write(buffer, bytes);
    }

    @Override
    public String read(@NotNull ByteBuf buffer) throws IOException {
        final byte[] bytes = Primitives.BYTE_ARRAY.read(buffer);
        return new String(bytes, StandardCharsets.UTF_8);
    }
}
