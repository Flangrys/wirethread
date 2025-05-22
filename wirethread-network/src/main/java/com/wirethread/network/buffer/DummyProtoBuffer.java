package com.wirethread.network.buffer;

import com.wirethread.core.registry.records.DynamicRegistries;
import io.netty.buffer.ByteBuf;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class DummyProtoBuffer extends AbstractBuffer {

    DummyProtoBuffer(int initialCapacity, @Nullable DynamicRegistries registries) {
        super(initialCapacity, registries);
    }

    DummyProtoBuffer(@NotNull ByteBuf buffer, DynamicRegistries registries) {
        super(buffer, registries);
    }
}
