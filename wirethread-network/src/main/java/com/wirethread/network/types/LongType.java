package com.wirethread.network.types;

import io.netty.buffer.ByteBuf;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public final class LongType implements Type<Long> {
    @Override
    public void write(@NotNull ByteBuf buffer, Long value) throws IOException {

    }

    @Override
    public Long read(@NotNull ByteBuf buffer) throws IOException {
        return 0L;
    }
}
