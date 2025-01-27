package com.wirethread.network.types;

import com.wirethread.network.buffer.Buffer;
import com.wirethread.network.buffer.Type;
import org.jetbrains.annotations.NotNull;

public record VarLongType() implements Type<Long> {
    @Override
    public void write(@NotNull Buffer buffer, Long value) {

    }

    @Override
    public Long read(@NotNull Buffer buffer) {
        return 0L;
    }
}
