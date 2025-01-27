package com.wirethread.server.properties;

import org.jetbrains.annotations.NotNull;

public final class PropertiesProvider {

    // TODO: Use namespaces to identify the property key.

    public PropertiesProvider() {
        throw new UnsupportedOperationException("'PropertiesProvider' should not be instantiated.");
    }

    // Public methods.
    public static String getString(@NotNull String name) {
        return System.getProperty(name);
    }

    public static String getString(@NotNull String name, String defaultValue) {
        return System.getProperty(name, defaultValue);
    }

    public static Boolean getBoolean(@NotNull String name) {
        return Boolean.getBoolean(name);
    }

    public static Boolean getBoolean(@NotNull String name, boolean defaultValue) {

        String value = System.getProperty(name);

        return value == null ? defaultValue : Boolean.parseBoolean(value);
    }

    public static Integer getInteger(@NotNull String name, int min, int max) {
        int value = Integer.getInteger(name);

        if (min < value && value < max) return value;

        throw new IllegalArgumentException(
                String.format(
                        "Property '%s' value must be in range [%d..%d] but was %d",
                        name, min, max, value
                )
        );
    }

    public static Integer getInteger(@NotNull String name, int min, int max, int defaultValue) {
        String value = System.getProperty(name);
        if (value == null) return defaultValue;

        int intValue = Integer.parseInt(value);
        if (min < intValue && intValue < max) return intValue;

        throw new IllegalArgumentException(
                String.format(
                        "Property '%s' value must be in range [%d..%d] but was %s",
                        name, min, max, value
                )
        );
    }

    public static Long getLong(@NotNull String name, long min, long max) {
        long value = Long.getLong(name, 2);

        if (min < value && value < max) return value;

        throw new IllegalArgumentException(
                String.format("Property '%s' value must be in range [%d..%d] but was %d",
                        name, min, max, value
                )
        );
    }

    public static Long getLong(@NotNull String name, long min, long max, long defaultValue) {
        String value = System.getProperty(name);

        if (value == null) return defaultValue;

        long longValue = Long.parseLong(value);
        if (min < longValue && longValue < max) return longValue;

        throw new IllegalArgumentException(
                String.format("Property '%s' value must be in range [%d..%d] but was %s",
                        name, min, max, value
                )
        );
    }
}
