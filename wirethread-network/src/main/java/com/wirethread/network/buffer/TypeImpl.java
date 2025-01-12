package com.wirethread.network.buffer;

import com.wirethread.core.registry.Registries;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface TypeImpl<T> extends Type<T> {

    default long sizeOf(@NotNull T value) {
        return this.sizeOf(value, null);
    }

    default long sizeOf(@NotNull T value, @Nullable Registries registries) {
        Buffer buffer = BufferImpl.dummy(registries);

        this.write(buffer, value);

        return buffer.writeIndex();
    }
}
