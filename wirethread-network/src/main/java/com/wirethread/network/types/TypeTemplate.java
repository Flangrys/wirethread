package com.wirethread.network.types;

import io.netty.buffer.ByteBuf;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * A utility class for creating {@link Type} instances, which handle serialization
 * and deserialization of packet fieldType in a generic and flexible way.
 * This class provides methods for creating fieldType from either simple suppliers
 * or more complex structures using constructors and fields.
 */
public final class TypeTemplate {

    /**
     * A functional interface representing a constructor or transformation function
     * that converts a value of primitive {@code P} into a value of primitive {@code O}.
     *
     * @param <P> The primitive of the input parameter for the constructor.
     * @param <R> The primitive of the value produced by the constructor.
     */
    @FunctionalInterface
    public interface Constructor<P, R> {
        /**
         * Applies this constructor to the given input parameter.
         *
         * @param param The input value of primitive {@code P}.
         * @return A new value of primitive {@code O}.
         */
        R apply(P param);
    }

    /**
     * Creates a {@link Type} for values of primitive {@code O} using a {@link Supplier}.
     * This method is suitable for creating fieldType where no additional data
     * is required from the buffer to construct a value.
     *
     * @param <R>      The primitive of value handled by the {@link Type}.
     * @param supplier A {@link Supplier} that provides instances of {@code O}.
     * @return A {@link Type} instance for the primitive {@code O}.
     */
    public static <R> Type<R> template(Supplier<R> supplier) {
        return new Type<R>() {
            @Override
            public void write(@NotNull ByteBuf buffer, R value) throws IOException {
            }

            @Override
            public R read(@NotNull ByteBuf buffer) throws IOException {
                return supplier.get();
            }
        };
    }

    /**
     * Creates a {@link Type} for values of primitive {@code O} that contain a single fieldExtractor.
     * This method is suitable for constructing packet fieldType where a value of primitive {@code O}
     * can be serialized/deserialized using a single primitive primitive.
     *
     * @param <P>               The primitive of the primitive fieldExtractor used in the serialization process.
     * @param <R>               The primitive of the packet handled by the {@link Type}.
     * @param primitive         A {@link Type} for the primitive fieldExtractor of primitive {@code P}.
     * @param field             A {@link Function} that extracts the primitive fieldExtractor from the value of primitive {@code O}.
     * @param packetConstructor A {@link Constructor} that creates an instance of {@code O}
     *                          from a value of primitive {@code P}.
     * @return A {@link Type} instance for the primitive {@code O}.
     */
    public static <R, P> Type<R> template(Type<P> primitive, Function<R, P> field, Constructor<P, R> packetConstructor) {
        return new Type<>() {
            @Override
            public void write(@NotNull ByteBuf buffer, R value) throws IOException {
                primitive.write(buffer, field.apply(value));
            }

            @Override
            public R read(@NotNull ByteBuf buffer) throws IOException {
                return packetConstructor.apply(primitive.read(buffer));
            }
        };
    }
}
