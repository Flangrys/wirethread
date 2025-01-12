package com.wirethread.network.buffer;

import com.wirethread.core.registry.Registries;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Represents a generic type that can handle serialization and deserialization
 * of values of type {@code <T>}. Each implementation of this interface defines
 * how a specific type is written to and read from a {@link Buffer}.
 *
 * @param <T> The type of value this interface can handle.
 */
public interface Type<T> {

    /**
     * Serializes a value of type {@code T} and writes it to the given {@link Buffer}.
     *
     * @param buffer The {@link Buffer} to which the serialized value will be written. Must not be null.
     * @param value The value to serialize. Must not be null.
     * @throws IllegalArgumentException If the value cannot be serialized.
     * @throws IllegalStateException If an error occurs during writing to the buffer.
     */
    void write(@NotNull Buffer buffer, T value);

    /**
     * Deserializes a value of type {@code T} from the given {@link Buffer}.
     *
     * @param buffer The {@link Buffer} from which the value will be deserialized. Must not be null.
     * @return The deserialized value of type {@code T}.
     * @throws IllegalArgumentException If the data in the buffer is invalid for type {@code T}.
     * @throws IllegalStateException If an error occurs during reading from the buffer.
     */
    T read(@NotNull Buffer buffer);

    /**
     * Calculates the size in bytes of the serialized representation of the given value.
     * The calculation may depend on additional context provided by {@code Registries}.
     *
     * @param value The value whose serialized size is being calculated. Must not be null.
     * @param registries Additional contextual information that may influence the size calculation. Can be null.
     * @return The size in bytes of the serialized value.
     * @throws IllegalArgumentException If the value is invalid or cannot be measured.
     */
    long sizeOf(@NotNull T value, @Nullable Registries registries);

    /**
     * Calculates the size in bytes of the serialized representation of the given value,
     * without requiring additional context.
     *
     * @param value The value whose serialized size is being calculated. Must not be null.
     * @return The size in bytes of the serialized value.
     * @throws IllegalArgumentException If the value is invalid or cannot be measured.
     */
    long sizeOf(@NotNull T value);
}

