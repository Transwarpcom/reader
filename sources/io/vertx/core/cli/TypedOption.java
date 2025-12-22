package io.vertx.core.cli;

import io.vertx.core.cli.converters.Converter;
import java.util.Objects;
import java.util.Set;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/cli/TypedOption.class */
public class TypedOption<T> extends Option {
    protected Class<T> type;
    protected boolean parsedAsList;
    protected String listSeparator;
    protected Converter<T> converter;

    @Override // io.vertx.core.cli.Option
    public /* bridge */ /* synthetic */ Option setChoices(Set set) {
        return setChoices((Set<String>) set);
    }

    public TypedOption() {
        this.listSeparator = ",";
    }

    public TypedOption(TypedOption<T> option) {
        super(option);
        this.listSeparator = ",";
        this.type = option.getType();
        this.converter = option.getConverter();
        this.parsedAsList = option.isParsedAsList();
        this.listSeparator = option.getListSeparator();
    }

    @Override // io.vertx.core.cli.Option
    public TypedOption<T> setMultiValued(boolean acceptMultipleValues) {
        super.setMultiValued(acceptMultipleValues);
        return this;
    }

    @Override // io.vertx.core.cli.Option
    public TypedOption<T> setSingleValued(boolean acceptSingleValue) {
        super.setSingleValued(acceptSingleValue);
        return this;
    }

    @Override // io.vertx.core.cli.Option
    public TypedOption<T> setArgName(String argName) {
        super.setArgName(argName);
        return this;
    }

    @Override // io.vertx.core.cli.Option
    public TypedOption<T> setDefaultValue(String defaultValue) {
        super.setDefaultValue(defaultValue);
        return this;
    }

    @Override // io.vertx.core.cli.Option
    public TypedOption<T> setDescription(String description) {
        super.setDescription(description);
        return this;
    }

    @Override // io.vertx.core.cli.Option
    public TypedOption<T> setFlag(boolean flag) {
        super.setFlag(flag);
        return this;
    }

    @Override // io.vertx.core.cli.Option
    public TypedOption<T> setHidden(boolean hidden) {
        super.setHidden(hidden);
        return this;
    }

    @Override // io.vertx.core.cli.Option
    public TypedOption<T> setLongName(String longName) {
        super.setLongName(longName);
        return this;
    }

    @Override // io.vertx.core.cli.Option
    public TypedOption<T> setRequired(boolean required) {
        super.setRequired(required);
        return this;
    }

    @Override // io.vertx.core.cli.Option
    public TypedOption<T> setShortName(String shortName) {
        super.setShortName(shortName);
        return this;
    }

    public Class<T> getType() {
        return this.type;
    }

    public TypedOption<T> setType(Class<T> type) {
        this.type = type;
        if (type != null && getChoices().isEmpty() && type.isEnum()) {
            setChoicesFromEnumType();
        }
        return this;
    }

    public boolean isParsedAsList() {
        return this.parsedAsList;
    }

    public TypedOption<T> setParsedAsList(boolean isList) {
        this.parsedAsList = isList;
        return this;
    }

    public String getListSeparator() {
        return this.listSeparator;
    }

    public TypedOption<T> setListSeparator(String listSeparator) {
        Objects.requireNonNull(listSeparator);
        this.parsedAsList = true;
        this.listSeparator = listSeparator;
        return this;
    }

    public Converter<T> getConverter() {
        return this.converter;
    }

    public TypedOption<T> setConverter(Converter<T> converter) {
        this.converter = converter;
        return this;
    }

    @Override // io.vertx.core.cli.Option
    public void ensureValidity() {
        super.ensureValidity();
        if (this.type == null) {
            throw new IllegalArgumentException("Type must not be null");
        }
    }

    @Override // io.vertx.core.cli.Option
    public TypedOption<T> setChoices(Set<String> choices) {
        super.setChoices(choices);
        return this;
    }

    @Override // io.vertx.core.cli.Option
    public TypedOption<T> addChoice(String choice) {
        super.addChoice(choice);
        return this;
    }

    private void setChoicesFromEnumType() {
        Object[] constants = this.type.getEnumConstants();
        for (Object c : constants) {
            addChoice(c.toString());
        }
    }
}
