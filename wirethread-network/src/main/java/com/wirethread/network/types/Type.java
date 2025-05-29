package com.wirethread.network.types;

import io.netty.buffer.ByteBuf;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.function.Function;

/**
 * Represents a generic primitive that can handle serialization and deserialization
 * of values of primitive {@code <T>}. Each implementation of this interface defines
 * how a specific primitive is written to and read from a {@link ByteBuf}.
 *
 * @param <T> The primitive of value this interface can handle.
 */
public interface Type<T> {

    /**
     * Serializes a value of primitive {@code T} and writes it to the given {@link ByteBuf}.
     *
     * @param buffer The {@link ByteBuf} to which the serialized value will be written. Must not be null.
     * @param value  The value to serialize.
     */
    void write(@NotNull ByteBuf buffer, T value) throws IOException;

    /**
     * Deserializes a value of primitive {@code T} from the given {@link ByteBuf}.
     *
     * @param buffer The {@link ByteBuf} from which the value will be deserialized. Must not be null.
     * @return The deserialized value of primitive {@code T}.
     */
    T read(@NotNull ByteBuf buffer) throws IOException;

    /**
     * Build a new {@link Type} which encodes or decodes data based on this current type.
     *
     * @param to   A decoder function which takes the input and encodes to this current {@link Type}.
     * @param from A decoder function which takes this current type and decodes to another type {@link S}.
     * @param <S>  A type which captures the decoder type.
     * @return A new {@link Type} which encodes the type {@link S}.
     */
    default <S> @NotNull Type<S> transform(@NotNull Function<T, S> to, @NotNull Function<S, T> from) {
        final Type<T> superType = this;

        return new Type<>() {
            @Override
            public void write(@NotNull ByteBuf buffer, S value) throws IOException {
                superType.write(buffer, from.apply(value));
            }

            @Override
            public S read(@NotNull ByteBuf buffer) throws IOException {
                return to.apply(superType.read(buffer));
            }
        };
    }
}

