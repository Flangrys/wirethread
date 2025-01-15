package com.wirethread.plugin;

import org.intellij.lang.annotations.Pattern;
import org.jetbrains.annotations.NotNull;

public interface Pluggable {

    /**
     * Retrieves the name of this plugin.
     * @return A
     */
    @NotNull
    @Pattern("[a-zA-Z0-9]+")
    String getName();
}
