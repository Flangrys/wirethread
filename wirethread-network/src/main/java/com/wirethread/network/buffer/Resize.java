package com.wirethread.network.buffer;

@FunctionalInterface
public interface Resize {
    long resize(long initialSize, long newSize);
}
