package com.wirethread.core.registry.resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Models a resource writer/reader which is responsible for handling a static game resource file
 * like JSON, SNBT, NBT, or simple text.
 */
public abstract class ResourceManager {

    /**
     * Reads the content from a file.
     *
     * @return A {@link FileInputStream} view of its content.
     */
    public abstract InputStream read() throws IOException;

    /**
     * Writes the content into a file.
     *
     * @return A {@link FileInputStream} view of the file.
     */
    public abstract OutputStream write() throws IOException;
}
