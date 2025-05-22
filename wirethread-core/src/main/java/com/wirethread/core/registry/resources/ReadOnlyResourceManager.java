package com.wirethread.core.registry.resources;

import org.jetbrains.annotations.NotNull;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class ReadOnlyResourceManager extends ResourceManager {

    public ReadOnlyResourceManager(@NotNull String filename) {

    }

    /**
     * Reads the content from a file.
     *
     * @return A {@link FileInputStream} view of its content.
     */
    @Override
    public InputStream read() {
        return null;
    }

    /**
     * Writes the content into a file.
     *
     * @return A {@link FileInputStream} view of the file.
     */
    @Override
    public OutputStream write() throws IOException {
        throw new IOException("Cannot write in a read-only resource.");
    }
}
