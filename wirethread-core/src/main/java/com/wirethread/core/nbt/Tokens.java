package com.wirethread.core.nbt;

import org.jetbrains.annotations.NotNull;

/**
 * This Enum contains the tokes which encloses.
 */
public enum Tokens {
    OBJECT_BEGIN("{"),
    OBJECT_END("}"),
    OBJECT_DELIMITER(":"),

    ARRAY_BEGIN("["),
    ARRAY_END("]"),
    ARRAY_DELIMITER(";"),

    VALUE_DELIMITER(","),

    SINGLE_QUOTE("'"),
    DOUBLE_QUOTE("\""),
    ESCAPE_MARKER("\\"),

    TYPE_BYTE("b"),
    TYPE_SHORT("s"),
    TYPE_INT("i"),
    TYPE_LONG("l"),
    TYPE_FLOAT("f"),
    TYPE_DOUBLE("d"),

    LIT_TRUE("true"),
    LIT_FALSE("false"),

    NEWLINE("\n"),
    EOF("\0");

    private final @NotNull String token;

    Tokens(final @NotNull String token) {
        this.token = token;
    }
}
