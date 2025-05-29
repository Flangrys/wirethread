package com.wirethread.network.types;

import com.wirethread.network.connection.ConnectionIntention;
import io.netty.buffer.ByteBuf;

import java.time.Instant;
import java.util.BitSet;
import java.util.Optional;

/**
 * - [ ] TODO: Inspect if the SERIALIZER object of each {@link Type} must use {@link ByteBuf#retain()} and {@link ByteBuf#release()} on each call.
 */
public final class Primitives {
    public static final Type<Boolean> BOOLEAN = new BooleanType();
    public static final Type<Byte> BYTE = new ByteType();
    public static final Type<Short> SHORT = new ShortType();
    public static final Type<Integer> UNSIGNED_SHORT = new UnsignedShortType();
    public static final Type<Integer> INTEGER = new IntegerType();
    public static final Type<Long> LONG = new LongType();
    //public static final Type<Float> FLOAT = new FloatType();
    //public static final Type<Double> DOUBLE = new DoubleType();
    public static final Type<Integer> VAR_INT = new VarIntType();
    public static final Type<Optional<Integer>> OPTIONAL_VAR_INT = new OptionalType();
    public static final Type<Long> VAR_LONG = new VarLongType();
    public static final Type<String> STRING = new StringType();

    public static final Type<long[]> LONG_ARRAY = new LongArrayType();
    public static final Type<byte[]> RAW_BYTES = new RawBytesType(-1);
    public static final Type<byte[]> BYTE_ARRAY = new ByteArrayType();
    //public static final Type<int[]> VAR_INT_ARRAY = new VarIntArrayType();
    //public static final Type<long[]> VAR_LONG_ARRAY = new VarLongArrayType();

    public static final Type<BitSet> BIT_SET_TYPE = LONG_ARRAY.transform(BitSet::valueOf, BitSet::toLongArray);
    public static final Type<Instant> INSTANT_TYPE = LONG.transform(Instant::ofEpochMilli, Instant::toEpochMilli);
    public static final Type<ConnectionIntention> INTENTION_TYPE = VAR_INT.transform(ConnectionIntention::getFromId, ConnectionIntention::id);
}
