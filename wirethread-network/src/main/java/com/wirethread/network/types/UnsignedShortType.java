package com.wirethread.network.types;

import io.netty.buffer.ByteBuf;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public final class UnsignedShortType implements Type<Integer> {
    @Override
    public void write(@NotNull ByteBuf buffer, Integer value) throws IOException {

    }

    @Override
    public Integer read(@NotNull ByteBuf buffer) throws IOException {
        return 0;
    }
}
