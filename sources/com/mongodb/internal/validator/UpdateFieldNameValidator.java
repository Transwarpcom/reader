package com.mongodb.internal.validator;

import org.bson.FieldNameValidator;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/validator/UpdateFieldNameValidator.class */
public class UpdateFieldNameValidator implements FieldNameValidator {
    @Override // org.bson.FieldNameValidator
    public boolean validate(String fieldName) {
        return fieldName.startsWith(PropertiesBeanDefinitionReader.CONSTRUCTOR_ARG_PREFIX);
    }

    @Override // org.bson.FieldNameValidator
    public FieldNameValidator getValidatorForField(String fieldName) {
        return new NoOpFieldNameValidator();
    }
}
