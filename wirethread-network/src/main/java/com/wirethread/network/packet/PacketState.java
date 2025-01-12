package com.wirethread.network.packet;

import com.wirethread.network.connection.ConnectionState;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Provide additional packet information about the
 * packet state.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface PacketState {
    ConnectionState value();
}
