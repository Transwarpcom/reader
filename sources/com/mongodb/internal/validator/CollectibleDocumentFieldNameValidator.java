package com.mongodb.internal.validator;

import java.util.Arrays;
import java.util.List;
import org.bson.FieldNameValidator;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/validator/CollectibleDocumentFieldNameValidator.class */
public class CollectibleDocumentFieldNameValidator implements FieldNameValidator {
    private static final List<String> EXCEPTIONS = Arrays.asList("$db", "$ref", "$id");

    @Override // org.bson.FieldNameValidator
    public boolean validate(String fieldName) {
        if (fieldName == null) {
            throw new IllegalArgumentException("Field name can not be null");
        }
        if (fieldName.contains(".")) {
            return false;
        }
        if (fieldName.startsWith(PropertiesBeanDefinitionReader.CONSTRUCTOR_ARG_PREFIX) && !EXCEPTIONS.contains(fieldName)) {
            return false;
        }
        return true;
    }

    @Override // org.bson.FieldNameValidator
    public FieldNameValidator getValidatorForField(String fieldName) {
        return this;
    }
}
