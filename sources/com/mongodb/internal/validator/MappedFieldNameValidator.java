package com.mongodb.internal.validator;

import java.util.Map;
import org.bson.FieldNameValidator;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/validator/MappedFieldNameValidator.class */
public class MappedFieldNameValidator implements FieldNameValidator {
    private final FieldNameValidator defaultValidator;
    private final Map<String, FieldNameValidator> fieldNameToValidatorMap;

    public MappedFieldNameValidator(FieldNameValidator defaultValidator, Map<String, FieldNameValidator> fieldNameToValidatorMap) {
        this.defaultValidator = defaultValidator;
        this.fieldNameToValidatorMap = fieldNameToValidatorMap;
    }

    @Override // org.bson.FieldNameValidator
    public boolean validate(String fieldName) {
        return this.defaultValidator.validate(fieldName);
    }

    @Override // org.bson.FieldNameValidator
    public FieldNameValidator getValidatorForField(String fieldName) {
        if (this.fieldNameToValidatorMap.containsKey(fieldName)) {
            return this.fieldNameToValidatorMap.get(fieldName);
        }
        return this.defaultValidator;
    }
}
