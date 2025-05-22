package com.wirethread.core.namespaces.annotations;

import com.wirethread.core.namespaces.Namespace;
import org.intellij.lang.annotations.Pattern;

import java.lang.annotation.*;

/**
 * An annotation used to annotate elements which must match against a valid {@link Namespace}
 */
@Documented
public @interface NamespacePattern {

    @Documented
    @Pattern(Namespace.ALLOWED_CHARACTERS)
    @Retention(RetentionPolicy.CLASS)
    @Target({ElementType.FIELD, ElementType.LOCAL_VARIABLE, ElementType.METHOD, ElementType.PARAMETER})
    @interface Domain {}

    @Documented
    @Pattern(Namespace.ALLOWED_GROUPS)
    @Retention(RetentionPolicy.CLASS)
    @Target({ElementType.FIELD, ElementType.LOCAL_VARIABLE, ElementType.METHOD, ElementType.PARAMETER})
    @interface Key {}
}
