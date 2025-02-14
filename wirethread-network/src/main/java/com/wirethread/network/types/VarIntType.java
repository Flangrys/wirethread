package com.wirethread.network.types;

import com.wirethread.network.buffer.Buffer;
import com.wirethread.network.buffer.Type;
import org.jetbrains.annotations.NotNull;

public record VarIntType() implements Type<Integer> {

    private static final int INTEGER_SEGMENT_BITS = 0x7F;
    private static final int INTEGER_CONTINUE_BIT = 0x80;
    private static final int MINIMAL_INTEGER_LENGTH_SIZE = 5;

    @Override
    public void write(@NotNull Buffer buffer, Integer value) {
        buffer.ensureWritable(MINIMAL_INTEGER_LENGTH_SIZE);

        int writeIndex = buffer.writerIndex();

        while (true) {
            if ((value & ~INTEGER_SEGMENT_BITS) == 0) {
                buffer.putByte(writeIndex++, value.byteValue());
                buffer.advanceWrite(writeIndex - buffer.writerIndex());
                return;
            }
            buffer.putByte(writeIndex++, (byte) ((value & INTEGER_SEGMENT_BITS) | INTEGER_CONTINUE_BIT));
            value >>>= 7;
        }
    }
    @Override
    public Integer read(@NotNull Buffer buffer) {
        int readIndex = buffer.readerIndex();
        int result = 0;
        int shift = 0;

        while (true) {
            byte readByte = buffer.getByte(readIndex++);
            result |= (readByte & 0x7f) << shift;

            // When there are not remaining bytes left, then update the index and return the result.
            if (readByte >= 0) {
                buffer.advanceRead(readIndex - buffer.readerIndex());
                return result;
            }

            shift += 7;
        }

    }
}
