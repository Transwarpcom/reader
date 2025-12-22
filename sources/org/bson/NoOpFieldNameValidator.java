package org.bson;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/NoOpFieldNameValidator.class */
class NoOpFieldNameValidator implements FieldNameValidator {
    NoOpFieldNameValidator() {
    }

    @Override // org.bson.FieldNameValidator
    public boolean validate(String fieldName) {
        return true;
    }

    @Override // org.bson.FieldNameValidator
    public FieldNameValidator getValidatorForField(String fieldName) {
        return this;
    }
}
