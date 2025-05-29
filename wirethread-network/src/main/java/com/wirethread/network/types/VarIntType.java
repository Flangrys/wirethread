package com.wirethread.network.types;

import io.netty.buffer.ByteBuf;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public final class VarIntType implements Type<Integer> {

    private static final int VARINT_SEGMENT_MASK = 0x7F;
    private static final int VARINT_CONTINUE_MASK = 0x80;
    private static final int VARINT_MAX_SIZE = 5;

    @Override
    public void write(@NotNull ByteBuf buffer, Integer value) throws IOException {
        if (!buffer.isWritable(VARINT_MAX_SIZE)) {
            throw new IndexOutOfBoundsException("Not enough space to write VarInt of size: " + value);
        }

        do {
            final int segment = value & VARINT_SEGMENT_MASK;
            value >>>= 7;

            if (value != 0) {
                buffer.writeByte(segment | VARINT_CONTINUE_MASK);
            } else {
                buffer.writeByte(segment);
            }

        } while (value != 0);
    }

    @Override
    public Integer read(@NotNull ByteBuf buffer) throws IOException {
        if (!buffer.isReadable(VARINT_MAX_SIZE)) {
            throw new IndexOutOfBoundsException("Not enough bytes to read a VarInt");
        }

        int val = 0, pos = 0;
        byte curr;

        do {
            curr = buffer.readByte();
            val |= (curr & VARINT_SEGMENT_MASK) << pos;
            pos += 7;

        } while ((curr & VARINT_CONTINUE_MASK) != 0);

        if (pos - 7 >= 32) throw new IOException("VarInt is too big");
        else return val;
    }
}
