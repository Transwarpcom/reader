package com.mongodb.internal.validator;

import org.bson.FieldNameValidator;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/validator/NoOpFieldNameValidator.class */
public class NoOpFieldNameValidator implements FieldNameValidator {
    @Override // org.bson.FieldNameValidator
    public boolean validate(String fieldName) {
        return true;
    }

    @Override // org.bson.FieldNameValidator
    public FieldNameValidator getValidatorForField(String fieldName) {
        return this;
    }
}
