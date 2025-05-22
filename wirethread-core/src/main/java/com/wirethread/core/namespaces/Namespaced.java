package com.wirethread.core.namespaces;

import org.intellij.lang.annotations.Pattern;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an object which is namespaced.
 */
public interface Namespaced {

    /**
     * Retrieves the namespace associated with this resource.
     * @return A {@link Namespace} object.
     */
    @Pattern("[a-z0-9_-]")
    @NotNull String getDomain();

    /**
     * Return the namespace as a string.
     * @return A {@link Namespace} as a string.
     */
    @Pattern("[a-z0-9_-/]")
    @NotNull String getKey();
}
