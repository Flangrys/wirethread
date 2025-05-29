package com.wirethread.network.registry.record;

import com.wirethread.network.registry.repository.PacketRegistry;
import org.jetbrains.annotations.NotNull;

public record PacketRegistries(
        @NotNull PacketRegistry<?> handshake,
        @NotNull PacketRegistry<?> status,
        @NotNull PacketRegistry<?> login,
        @NotNull PacketRegistry<?> configuration,
        @NotNull PacketRegistry<?> play

) {
}
