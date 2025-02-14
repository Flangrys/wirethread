package com.wirethread.network.buffer;

import com.wirethread.core.registry.Registries;
import io.netty.buffer.ByteBuf;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class DummyProtoBuffer extends AbstractBuffer {

    DummyProtoBuffer(int initialCapacity, @Nullable Registries registries) {
        super(initialCapacity, registries);
    }

    DummyProtoBuffer(@NotNull ByteBuf buffer, Registries registries) {
        super(buffer, registries);
    }
}
