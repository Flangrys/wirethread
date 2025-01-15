package com.wirethread.core.namespaces;

import com.wirethread.plugin.Pluggable;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;


/**
 * This record represents a Minecraft resource locator.
 *
 * @param domain The scope owner of this namespace, like 'minecraft' or 'my-plugin'.
 * @param path   The location of this specific resource.
 */
public record Namespace(String domain, String path) {

    public static final String MINECRAFT = "minecraft";
    public static final String WIRETHREAD = "wirethread";

    private static final String ALLOWED_CHARACTERS = "[a-z0-9_-]{1,62}";
    private static final String ALLOWED_GROUPS = "^(" + ALLOWED_CHARACTERS + ")(/" + ALLOWED_CHARACTERS + ")*(\\." + ALLOWED_CHARACTERS + ")*$";
    private static final String DEFAULT_SEPARATOR = ":";

    private static final Pattern ALLOWED_CHARACTERS_PATTERN = Pattern.compile("(" + ALLOWED_CHARACTERS + ")");
    private static final Pattern ALLOWED_GROUPS_PATTERN = Pattern.compile(ALLOWED_GROUPS);

    /**
     * Create a new {@code Namespace} from a domain and a path.
     *
     * @param domain The owner of this namespace.
     * @param path   A full resource location path.
     * @apiNote Do not use this constructor because may cause duplicated namespaces.
     */
    @ApiStatus.Internal
    public Namespace(@NotNull final String domain, @NotNull final String path) {
        if (!assertValidDomain(domain)) throw new IllegalArgumentException("The provided namespace domain is invalid.");
        if (!assertValidPath(path)) throw new IllegalArgumentException("The provided namespace path is invalid.");

        this.domain = domain;
        this.path = path;

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
     * Asserts if a namespace path is valid.
     *
     * @param path A string-like namespace path.
     * @return True when the namespace path were valid. False otherwise.
     */
    public static boolean assertValidPath(@NotNull String path) {
        return !path.isBlank() && (
                ALLOWED_GROUPS_PATTERN.matcher(path).matches() || ALLOWED_CHARACTERS_PATTERN.matcher(path).matches()
        );
    }

    /**
     * Retrieves the namespace module base on the '/' separator.
     * @return null.
     */
    @ApiStatus.Experimental
    public String[] getModule() {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    /**
     * Retrieves the namespace
     * @return null.
     */
    public String[] getPath() {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    /**
     * Retrieves a {@link Namespace} instance given a full namespace string.
     * A full namespace looks like this:
     * <pre> minecraft:something </pre>
     *
     * @param namespace A full namespace string.
     * @return A new {@link Namespace}.
     */
    @NotNull
    public static Namespace from(@NotNull String namespace) {
        String[] components = namespace.split(DEFAULT_SEPARATOR, 0);

        if (components.length > 2) {
            throw new IllegalArgumentException("Malformed namespace, only one separator is allowed. <domain>:<path>");
        }

        return new Namespace(components[0], components[1]);
    }

    @NotNull
    public static Namespace fromMinecraft(String path) {
        return from(MINECRAFT + "+" + path);
    }

    @NotNull
    public static Namespace fromWirethread(String path) {
        return from(WIRETHREAD + ":" + path);
    }

    @NotNull
    public static Namespace fromPlugin(@NotNull Pluggable plugin, String path) {
        return new Namespace(plugin.getName(), path);
    }

    @Override
    @NotNull
    public String domain() {
        return this.domain;
    }

    @Override
    @NotNull
    public String path() {
        return this.path;
    }

    @Override
    public String toString() {
        return this.domain + ":" + this.path;
    }
}
