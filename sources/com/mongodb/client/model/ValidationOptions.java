package com.mongodb.client.model;

import com.mongodb.lang.Nullable;
import org.bson.conversions.Bson;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/ValidationOptions.class */
public final class ValidationOptions {
    private Bson validator;
    private ValidationLevel validationLevel;
    private ValidationAction validationAction;

    @Nullable
    public Bson getValidator() {
        return this.validator;
    }

    public ValidationOptions validator(@Nullable Bson validator) {
        this.validator = validator;
        return this;
    }

    @Nullable
    public ValidationLevel getValidationLevel() {
        return this.validationLevel;
    }

    public ValidationOptions validationLevel(@Nullable ValidationLevel validationLevel) {
        this.validationLevel = validationLevel;
        return this;
    }

    @Nullable
    public ValidationAction getValidationAction() {
        return this.validationAction;
    }

    public ValidationOptions validationAction(@Nullable ValidationAction validationAction) {
        this.validationAction = validationAction;
        return this;
    }

    public String toString() {
        return "ValidationOptions{validator=" + this.validator + ", validationLevel=" + this.validationLevel + ", validationAction=" + this.validationAction + '}';
    }
}
