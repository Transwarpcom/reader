package org.springframework.boot.context.properties.bind.validation;

import org.springframework.util.Assert;

/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/context/properties/bind/validation/BindValidationException.class */
public class BindValidationException extends RuntimeException {
    private final ValidationErrors validationErrors;

    BindValidationException(ValidationErrors validationErrors) {
        super(getMessage(validationErrors));
        Assert.notNull(validationErrors, "ValidationErrors must not be null");
        this.validationErrors = validationErrors;
    }

    public ValidationErrors getValidationErrors() {
        return this.validationErrors;
    }

    private static String getMessage(ValidationErrors errors) {
        StringBuilder message = new StringBuilder("Binding validation errors");
        if (errors != null) {
            message.append(" on " + errors.getName());
            errors.getAllErrors().forEach(error -> {
                message.append(String.format("%n   - %s", error));
            });
        }
        return message.toString();
    }
}
