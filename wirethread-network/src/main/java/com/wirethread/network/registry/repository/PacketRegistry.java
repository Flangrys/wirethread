package com.wirethread.network.registry.repository;

import com.wirethread.core.registry.repository.DynamicRegistry;
import com.wirethread.core.registry.repository.Registrable;

/**
 * This class represents a registry for packets, extending the {@link DynamicRegistry} class.
 * It is used to manage and register packet types in the network system.
 *
 * @param <T> The primitive of the registrable packet.
 */
public final class PacketRegistry<T extends Registrable<T>> extends DynamicRegistry<T> {
}
