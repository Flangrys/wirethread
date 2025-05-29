package com.wirethread.core.namespaces;

import com.wirethread.core.namespaces.annotations.NamespacePattern;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an object which is namespaced.
 */
public interface Namespaced {

    /**
     * Retrieves the namespace associated with this resource.
     * @return A {@link Namespace} object.
     */
    @NamespacePattern.Domain
    @NotNull String getDomain();

    /**
     * Return the namespace as a string.
     * @return A {@link Namespace} as a string.
     */
    @NamespacePattern.Key
    @NotNull String getKey();
}
