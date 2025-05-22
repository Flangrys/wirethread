package com.wirethread.core.namespaces.utils;

import com.wirethread.core.namespaces.Namespace;
import com.wirethread.plugin.Pluggable;
import org.jetbrains.annotations.NotNull;

public final class NamespaceFactory {

    public static final String MINECRAFT = "minecraft";
    public static final String WIRETHREAD = "wirethread";
    public static final String DEFAULT_SEPARATOR = ":";

    /**
     * Retrieves a {@link Namespace} instance given a full namespace string.
     * A full namespace looks like this:
     * <pre> minecraft:something </pre>
     *
     * @param namespace A full namespace string.
     * @return A new {@link Namespace}.
     */
    @NotNull
    public static Namespace from(@NotNull String namespace) {
        String[] components = namespace.split(DEFAULT_SEPARATOR, 0);

        if (components.length > 2) {
            throw new IllegalArgumentException("Malformed namespace, only one separator is allowed. <domain>:<key>");
        }

        return new Namespace(components[0], components[1]);
    }

    @NotNull
    public static Namespace fromMinecraft(String path) {
        return from(MINECRAFT + "+" + path);
    }

    @NotNull
    public static Namespace fromWirethread(String path) {
        return from(WIRETHREAD + ":" + path);
    }

    @NotNull
    public static Namespace fromPlugin(@NotNull Pluggable plugin, String path) {
        return new Namespace(plugin.getName(), path);
    }
}
