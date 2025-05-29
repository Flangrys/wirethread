package com.wirethread.core.registry.repository;

import com.wirethread.core.namespaces.Namespace;
import com.wirethread.core.registry.resources.ResourceManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public final class StaticRegistry<T extends Registrable<T>> extends Registry<T> {

    /**
     * Creates a new registry with an immutable namespace.
     *
     * @param id The resource location for this registry.
     */
    public StaticRegistry(@NotNull String id, final @NotNull ResourceManager resourceManager) {
    }

    /**
     * Register a new entry overriding the existing registry, if one exists.
     *
     * @param entry An entry which must me extends from {@link Registrable}
     * @return The namespace of this registry entry.
     * @throws UnsupportedOperationException When this operation weren't supported by the subclass.
     */
    @Override
    public @NotNull Namespace register(@NotNull T entry) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("This class does not support register new entries.");
    }

    /**
     * Unregister an entry by using their namespace.
     *
     * @param namespace The entry namespace.
     * @return The removed registry entry.
     * @throws UnsupportedOperationException When this operation weren't supported by the subclass.
     */
    @Override
    public @Nullable T unregister(@NotNull Namespace namespace) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("This class does not support unregister entries.");
    }

    /**
     * Retrieves a registry entry with the namespace given.
     *
     * @param namespace A registry entry namespace.
     * @return A registry entry or null if the id isn't associated with an entry.
     */
    @Override
    public @Nullable T get(@NotNull Namespace namespace) {
        return null;
    }

    /**
     * Retrieves a registry entry with the index given.
     *
     * @param index A registered entry index.
     * @return A registry entry or null if the index isn't associated with an entry.
     */
    @Override
    public @Nullable T get(int index) {
        return null;
    }

    /**
     * Retrieves the namespace of a registry.
     *
     * @param registry The registry to being lookup.
     * @return The namespace for this registry, null if not exist.
     */
    @Override
    public @Nullable Namespace getNamespaceOf(@NotNull T registry) {
        return null;
    }

    /**
     * Retrieves a view of all the entries of this registry.
     *
     * @return A list of type {@code T}
     */
    @Override
    public @NotNull List<T> values() {
        return List.of();
    }
}
