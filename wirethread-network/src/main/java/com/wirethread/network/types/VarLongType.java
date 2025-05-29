package com.wirethread.network.types;

import io.netty.buffer.ByteBuf;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public final class VarLongType implements Type<Long> {

    private static final int VARLONG_DATA_MASK = 0x7F;
    private static final int VARLONG_CONTINUE_MASK = 0x80;
    private static final int MAX_VARLONG_SIZE = 5;

    @Override
    public void write(@NotNull ByteBuf buffer, Long value) throws IOException {
        if (!buffer.isWritable(MAX_VARLONG_SIZE)) {
            throw new IndexOutOfBoundsException("Not enough space to write VarInt of size: " + value);
        }

        boolean doWriteLoop = true;

        while (doWriteLoop) {
            // Mask the value to get the bits that will be written.
            int bits = (int) (value & VARLONG_DATA_MASK);

            // Shift 7 positions to the right to prepare for the next byte.
            value >>>= 7;

            if (value == 0) {
                // If there are no more bits left, write the bits without the continue mask.
                buffer.writeByte(bits);
                doWriteLoop = false;

            } else {
                // If there are still bits left, write the bits with the continue mask.
                buffer.writeByte(bits | VARLONG_CONTINUE_MASK);
            }
        }
    }

    @Override
    public Long read(@NotNull ByteBuf buffer) throws IOException {
        long result = 0;
        int shift = 0;

        // We assume the value is malformed until proven otherwise.
        boolean isMalformed = true;

        for (int length = 0; length < MAX_VARLONG_SIZE; length++) {
            byte readByte = buffer.readByte();
            result |= (long) (readByte & VARLONG_DATA_MASK) << shift;

            // If the byte does not have the continue mask, we can stop reading.
            if ((readByte & VARLONG_CONTINUE_MASK) == 0) {
                isMalformed = false;
                length = MAX_VARLONG_SIZE;
            }

            shift += 7;
        }

        if (isMalformed) {
            throw new IllegalArgumentException("VarLong is too long (more than 5 bytes)");
        }

        return result;
    }
}