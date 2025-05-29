package com.wirethread.network.types;

import io.netty.buffer.ByteBuf;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Optional;

public final class OptionalType implements Type<Optional<Integer>> {
    @Override
    public void write(@NotNull ByteBuf buffer, Optional<Integer> value) throws IOException {
        final int size = value.orElse(0);
        Primitives.VAR_INT.write(buffer, size);
    }

    @Override
    public Optional<Integer> read(@NotNull ByteBuf buffer) throws IOException {
        final int size = Primitives.VAR_INT.read(buffer);
        return size == 0 ? Optional.empty() : Optional.of(size - 1);
    }
}
