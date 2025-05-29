package com.wirethread.network.types;

import io.netty.buffer.ByteBuf;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public final class LongArrayType implements Type<long[]> {
    @Override
    public void write(@NotNull ByteBuf buffer, long[] value) throws IOException {
        final int length = Primitives.VAR_INT.read(buffer);
        final long[] arr = new long[length];

        for (long i : value) {
            Primitives.LONG.write(buffer, i);
        }
    }

    @Override
    public long[] read(@NotNull ByteBuf buffer) throws IOException {
        final int length = Primitives.VAR_INT.read(buffer);
        final long[] arr = new long[length];

        for (int i = 0; i < length; i++) {
            arr[i] = Primitives.LONG.read(buffer);
        }

        return arr;
    }
}
