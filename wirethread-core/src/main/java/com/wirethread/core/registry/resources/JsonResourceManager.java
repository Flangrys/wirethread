package com.wirethread.core.registry.resources;

import com.wirethread.core.exceptions.InvalidResourceException;
import org.jetbrains.annotations.NotNull;

import java.io.*;

public final class JsonResourceManager extends ResourceManager {

    private final @NotNull File resource;

    public JsonResourceManager(@NotNull Resources resource) throws InvalidResourceException {
        var res = new File(resource.getPath());

        if (res.exists() && !res.isDirectory()) {
            throw new InvalidResourceException("This resource was not found.");
        }

        this.resource = res;
    }

    /**
     * Reads the content from a file.
     *
     * @return A {@link FileInputStream} view of its content.
     */
    @Override
    public InputStream read() throws IOException {
        return null;
    }

    /**
     * Writes the content into a file.
     *
     * @return A {@link FileInputStream} view of the file.
     */
    @Override
    public OutputStream write() throws IOException {
        return null;
    }
}
