package com.wirethread.core.registry.repository;

import com.wirethread.core.exceptions.UnsafeContextOperation;
import com.wirethread.core.namespaces.Namespace;
import com.wirethread.core.testing.TestingContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.ReentrantLock;

public final class DynamicRegistry<T extends Registrable<T>> extends Registry<T> {

    private final ReentrantLock lock;

    /**
     * Maps an entry to a namespace.
     *
     * <p>An inverse mapping of {@link DynamicRegistry#mapNamespacesByEntry}.
     */
    private final ConcurrentMap<Namespace, T> mapEntriesByNamespace;

    /**
     * Maps a namespace to an entry.
     *
     * <p>An inverse mapping of {@link DynamicRegistry#mapEntriesByNamespace}.
     */
    private final ConcurrentMap<T, Namespace> mapNamespacesByEntry;

    /**
     * Ensures if the index is within the range {@code [0; this.mapEntriesByNamespace.size()]}
     *
     * @param index The index to validate.
     * @return true if the index is constrained, false otherwise.
     */
    private boolean asserValidIndex(final int index) {
        return 0 < index && (this.mapEntriesByNamespace.size() <= index && this.mapNamespacesByEntry.size() <= index);
    }

    /**
     * Creates a new registry with an immutable namespace.
     *
     * @param id The resource location for this registry.
     */
    public DynamicRegistry(@NotNull String id) {
        super(id);

        this.lock = new ReentrantLock();

        this.mapEntriesByNamespace = new ConcurrentHashMap<>();
        this.mapNamespacesByEntry = new ConcurrentHashMap<>();
    }

    @Override
    public @NotNull Namespace register(@NotNull T entry) throws UnsupportedOperationException, UnsafeContextOperation {

        if (TestingContext.isUnsafePerformRegistryOperations()) {
            throw new UnsafeContextOperation("Cannot perform this operation while the server is running.");
        }

        // Prevent multiple threads to registry at the same time.
        this.lock.lock();

        final Namespace namespace = new Namespace(entry.getDomain(), entry.getKey());

        try {
            this.mapEntriesByNamespace.put(namespace, entry);
            this.mapNamespacesByEntry.put(entry, namespace);

        } finally {
            // Finally, unlock the thread.
            this.lock.unlock();
        }

        return namespace;
    }

    @Override
    public @Nullable T unregister(@NotNull Namespace namespace) throws UnsupportedOperationException, UnsafeContextOperation {

        if (TestingContext.isUnsafePerformRegistryOperations()) {
            throw new UnsafeContextOperation("Cannot perform this operation while the server is running.");
        }

        // Prevent multiple threads to registry at the same time.
        this.lock.lock();

        final T entry;

        try {
            entry = this.mapEntriesByNamespace.remove(namespace);
            this.mapNamespacesByEntry.remove(entry);

        } finally {
            // Finally, unlock the thread.
            this.lock.unlock();
        }

        return entry;
    }

    /**
     * Retrieves a registry entry with the namespace given.
     *
     * @param namespace A registry entry namespace.
     * @return A registry entry or null if the id isn't associated with an entry.
     */
    @Override
    public @Nullable T get(@NotNull Namespace namespace) {
        return this.mapEntriesByNamespace.get(namespace);

    }

    /**
     * Retrieves a registry entry with the index given.
     *
     * @param index A registered entry index.
     * @return A registry entry or null if the index isn't associated with an entry.
     */
    @Override
    public @Nullable T get(int index) {
        if (asserValidIndex(index)) return null;

        return this.mapEntriesByNamespace
                .values()
                .stream()
                .toList()
                .get(index);
    }

    /**
     * Retrieves the namespace of a registry.
     *
     * @param registry The registry to being lookup.
     * @return The namespace for this registry, null if not exist.
     */
    @Override
    public @Nullable Namespace getNamespaceOf(@NotNull T registry) {
        return this.mapNamespacesByEntry.get(registry);
    }

    /**
     * Retrieves a view of all the entries of this registry.
     *
     * @return A list of type {@code T}
     */
    @Override
    public @NotNull List<T> values() {
        return this.mapEntriesByNamespace
                .values()
                .stream()
                .toList();
    }
}
