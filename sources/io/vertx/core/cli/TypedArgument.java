package io.vertx.core.cli;

import io.vertx.core.cli.converters.Converter;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/cli/TypedArgument.class */
public class TypedArgument<T> extends Argument {
    protected Class<T> type;
    protected Converter<T> converter;

    public TypedArgument(TypedArgument<T> arg) {
        super(arg);
        this.type = arg.getType();
        this.converter = arg.getConverter();
    }

    public TypedArgument() {
    }

    public Class<T> getType() {
        return this.type;
    }

    public TypedArgument<T> setType(Class<T> type) {
        this.type = type;
        return this;
    }

    public Converter<T> getConverter() {
        return this.converter;
    }

    public TypedArgument<T> setConverter(Converter<T> converter) {
        this.converter = converter;
        return this;
    }

    @Override // io.vertx.core.cli.Argument
    public void ensureValidity() {
        super.ensureValidity();
        if (this.type == null) {
            throw new IllegalArgumentException("Type must not be null");
        }
    }

    @Override // io.vertx.core.cli.Argument
    public TypedArgument<T> setArgName(String argName) {
        super.setArgName(argName);
        return this;
    }

    @Override // io.vertx.core.cli.Argument
    public TypedArgument<T> setDefaultValue(String defaultValue) {
        super.setDefaultValue(defaultValue);
        return this;
    }

    @Override // io.vertx.core.cli.Argument
    public TypedArgument<T> setDescription(String description) {
        super.setDescription(description);
        return this;
    }

    @Override // io.vertx.core.cli.Argument
    public TypedArgument<T> setHidden(boolean hidden) {
        super.setHidden(hidden);
        return this;
    }

    @Override // io.vertx.core.cli.Argument
    public TypedArgument<T> setIndex(int index) {
        super.setIndex(index);
        return this;
    }

    @Override // io.vertx.core.cli.Argument
    public TypedArgument<T> setRequired(boolean required) {
        super.setRequired(required);
        return this;
    }

    @Override // io.vertx.core.cli.Argument
    public TypedArgument<T> setMultiValued(boolean multiValued) {
        super.setMultiValued(multiValued);
        return this;
    }
}
