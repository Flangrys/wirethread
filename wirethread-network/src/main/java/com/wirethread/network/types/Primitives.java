package com.wirethread.network.types;

import com.wirethread.network.buffer.Type;

public final class Primitives {
    public static Type<Boolean> BOOLEAN = new BooleanType();
    public static Type<Byte> BYTE = new ByteType();
    public static Type<String> STRING = new StringType();
}
