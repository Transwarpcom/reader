package org.bson;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/FieldNameValidator.class */
public interface FieldNameValidator {
    boolean validate(String str);

    FieldNameValidator getValidatorForField(String str);
}
