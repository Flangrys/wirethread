package com.wirethread.core.registry.repository;

import com.wirethread.core.exceptions.UnsafeContextOperation;
import com.wirethread.core.namespaces.Namespace;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class Registry<T extends Registrable<T>> {

    protected final @NotNull String id;

    /**
     * Creates a new registry with an immutable namespace.
     *
     * @param id The resource location for this registry.
     */
    public Registry(@NotNull final String id) {
        this.id = id;
    }

    /**
     * Register a new entry overriding the existing registry, if one exists.
     *
     * @param entry An entry which must me extends from {@link Registrable}
     * @return The namespace of this registry entry.
     * @throws UnsupportedOperationException When this operation weren't supported by the subclass.
     */
    public abstract @NotNull Namespace register(final @NotNull T entry) throws UnsupportedOperationException, UnsafeContextOperation;

    /**
     * Unregister an entry by using their namespace.
     *
     * @param namespace The entry namespace.
     * @return The removed registry entry.
     * @throws UnsupportedOperationException When this operation weren't supported by the subclass.
     */
    public abstract @Nullable T unregister(@NotNull Namespace namespace) throws UnsupportedOperationException, UnsafeContextOperation;

    /**
     * Retrieves a registry entry with the namespace given.
     *
     * @param namespace A registry entry namespace.
     * @return A registry entry or null if the id isn't associated with an entry.
     */
    public abstract @Nullable T get(@NotNull Namespace namespace);

    /**
     * Retrieves a registry entry with the index given.
     *
     * @param index A registered entry index.
     * @return A registry entry or null if the index isn't associated with an entry.
     */
    public abstract @Nullable T get(int index);

    /**
     * Retrieves the namespace of a registry.
     *
     * @param registry The registry to being lookup.
     * @return The namespace for this registry, null if not exist.
     */
    public abstract @Nullable Namespace getNamespaceOf(final @NotNull T registry);

    /**
     * Retrieves a view of all the entries of this registry.
     *
     * @return A list of type {@code T}
     */
    public abstract @NotNull List<T> values();

}
