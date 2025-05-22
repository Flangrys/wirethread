package com.wirethread.core.namespaces;

import org.intellij.lang.annotations.RegExp;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.regex.Pattern;


/**
 * This record represents a Minecraft resource locator.
 */
public record Namespace(String domain, String key) {

    public static final @RegExp String ALLOWED_CHARACTERS = "[a-z0-9_-]";
    public static final @RegExp String ALLOWED_GROUPS = "^(" + ALLOWED_CHARACTERS + ")(/" + ALLOWED_CHARACTERS + ")*(\\." + ALLOWED_CHARACTERS + ")*$";
    private static final Pattern ALLOWED_CHARACTERS_PATTERN = Pattern.compile("(" + ALLOWED_CHARACTERS + ")");
    private static final Pattern ALLOWED_GROUPS_PATTERN = Pattern.compile(ALLOWED_GROUPS);

    /**
     * Create a new {@code Namespace} from a domain and a key.
     *
     * @param domain The owner of this namespace.
     * @param key    A full resource location key.
     */
    public Namespace(@NotNull final String domain, @NotNull final String key) {
        if (!assertValidDomain(domain)) throw new IllegalArgumentException("The provided namespace domain is invalid.");
        if (!assertValidKey(key)) throw new IllegalArgumentException("The provided namespace key is invalid.");

        this.domain = domain;
        this.key = key;

        if (this.toString().length() > 255) {
            throw new IllegalArgumentException("The namespace is valid but is greater than 255 characters.");
        }
    }

    /**
     * Asserts if a namespace domain is valid.
     *
     * @param domain A string-like namespace domain.
     * @return True when the namespace domain were valid. False otherwise.
     */
    public static boolean assertValidDomain(@NotNull String domain) {
        return !domain.isBlank() && ALLOWED_CHARACTERS_PATTERN.matcher(domain).matches();
    }

    /**
     * Asserts if a namespace key is valid.
     *
     * @param key A string-like namespace key.
     * @return True when the namespace key were valid. False otherwise.
     */
    public static boolean assertValidKey(@NotNull String key) {
        return !key.isBlank() && (
                ALLOWED_GROUPS_PATTERN.matcher(key).matches() || ALLOWED_CHARACTERS_PATTERN.matcher(key).matches()
        );
    }

    /**
     * Retrieves the namespace module base on the '.' separator.
     */
    @ApiStatus.Experimental
    public String[] getModule() {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    /**
     * Retrieves the namespace key base on the '/' separator.
     */
    @ApiStatus.Experimental
    public String[] getPath() {
        throw new UnsupportedOperationException("Not implemented yet.");
    }


    @Override
    public String toString() {
        return this.domain + ":" + this.key;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Namespace namespace)) return false;
        return Objects.equals(domain, namespace.domain) && Objects.equals(key, namespace.key);
    }

}
