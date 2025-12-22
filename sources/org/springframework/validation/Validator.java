package org.springframework.validation;

/* loaded from: reader.jar:BOOT-INF/lib/spring-context-5.1.8.RELEASE.jar:org/springframework/validation/Validator.class */
public interface Validator {
    boolean supports(Class<?> cls);

    void validate(Object obj, Errors errors);
}
