package com.wirethread.network.packet;

import com.wirethread.network.buffer.Buffer;
import com.wirethread.network.buffer.Type;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * A utility class for creating {@link Type} instances, which handle serialization
 * and deserialization of packet types in a generic and flexible way.
 * This class provides methods for creating types from either simple suppliers
 * or more complex structures using constructors and fields.
 */
public final class PacketTemplate {

    /**
     * A functional interface representing a constructor or transformation function
     * that converts a value of type {@code P} into a value of type {@code R}.
     *
     * @param <P> The input type for the constructor.
     * @param <R> The output type of the constructor.
     */
    @FunctionalInterface
    public interface Constructor<P, R> {
        /**
         * Applies this constructor to the given input parameter.
         *
         * @param param The input value of type {@code P}.
         *
         * @return A new value of type {@code R}.
         */
        R apply(P param);
    }

    /**
     * Creates a {@link Type} for values of type {@code R} using a {@link Supplier}.
     * This method is suitable for creating types where no additional data
     * is required from the buffer to construct a value.
     *
     * @param <R>      The type of value handled by the {@link Type}.
     * @param supplier A {@link Supplier} that provides instances of {@code R}.
     *
     * @return A {@link Type} instance for the type {@code R}.
     */
    public static <R> Type<R> template(Supplier<R> supplier) {
        return new Type<R>() {
            @Override
            public void write(@NotNull Buffer buffer, R value) {
            }

            @Override
            public R read(@NotNull Buffer buffer) {
                return supplier.get();
            }
        };
    }

    /**
     * Creates a {@link Type} for values of type {@code R} that contain a single field.
     * This method is suitable for constructing packet types where a value of type {@code R}
     * can be serialized/deserialized using a single primitive type.
     *
     * @param <P>               The type of the primitive field used in the serialization process.
     * @param <R>               The type of the packet handled by the {@link Type}.
     * @param primitive         A {@link Type} for the primitive field of type {@code P}.
     * @param field             A {@link Function} that extracts the primitive field from the value of type {@code R}.
     * @param packetConstructor A {@link Constructor} that creates an instance of {@code R}
     *                          from a value of type {@code P}.
     * @return A {@link Type} instance for the type {@code R}.
     */
    public static <P, R> Type<R> template(Type<P> primitive, Function<R, P> field, Constructor<P, R> packetConstructor) {
        return new Type<>() {
            @Override
            public void write(@NotNull Buffer buffer, R value) {
                primitive.write(buffer, field.apply(value));
            }

            @Override
            public R read(@NotNull Buffer buffer) {
                return packetConstructor.apply(primitive.read(buffer));
            }
        };
    }
}
