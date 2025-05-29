package com.wirethread.core.registry.repository;

import com.wirethread.core.namespaces.Namespace;
import org.jetbrains.annotations.NotNull;

/**
 * Represents any resource which is handled by the server.
 *
 * @param <T> A resource type.
 */
public interface Registrable<T> {

    /**
     * Retrieves the namespace of this registry.
     *
     * <p> {@code "minecraft:stone" -> "Granite"}
     */
    @NotNull
    Namespace getNamespace();

    /**
     * Retrieves the id of this registry.
     *
     * <p> {@code "1:1" -> "Granite"}
     */
    @NotNull
    String getID();
}
