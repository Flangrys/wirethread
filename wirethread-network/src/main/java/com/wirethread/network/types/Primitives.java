package com.wirethread.network.types;

import com.wirethread.network.buffer.Type;

public final class Primitives {
    public static Type<Boolean> BOOLEAN = new BooleanType();
    public static Type<Byte> BYTE = new ByteType();
    public static Type<String> STRING = new StringType();

    public static Type<Integer> VAR_INT = new VarIntType();
    public static Type<Long> VAR_LONG = new VarLongType();
    public static Type<byte[]> BYTE_ARRAY = new ByteArrayType();
    public static Type<byte[]> RAW_BYTES = new RawBytesType(-1);

}
