package cn.hutool.core.bean.copier;

import cn.hutool.core.lang.Editor;
import cn.hutool.core.util.ArrayUtil;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/bean/copier/CopyOptions.class */
public class CopyOptions implements Serializable {
    private static final long serialVersionUID = 1;
    protected Class<?> editable;
    protected boolean ignoreNullValue;
    private BiPredicate<Field, Object> propertiesFilter;
    protected boolean ignoreError;
    protected boolean ignoreCase;
    private Editor<String> fieldNameEditor;
    protected BiFunction<String, Object, Object> fieldValueEditor;
    protected boolean transientSupport;
    protected boolean override;

    public static CopyOptions create() {
        return new CopyOptions();
    }

    public static CopyOptions create(Class<?> editable, boolean ignoreNullValue, String... ignoreProperties) {
        return new CopyOptions(editable, ignoreNullValue, ignoreProperties);
    }

    public CopyOptions() {
        this.transientSupport = true;
        this.override = true;
    }

    public CopyOptions(Class<?> editable, boolean ignoreNullValue, String... ignoreProperties) {
        this.transientSupport = true;
        this.override = true;
        this.propertiesFilter = (f, v) -> {
            return true;
        };
        this.editable = editable;
        this.ignoreNullValue = ignoreNullValue;
        setIgnoreProperties(ignoreProperties);
    }

    public CopyOptions setEditable(Class<?> editable) {
        this.editable = editable;
        return this;
    }

    public CopyOptions setIgnoreNullValue(boolean ignoreNullVall) {
        this.ignoreNullValue = ignoreNullVall;
        return this;
    }

    public CopyOptions ignoreNullValue() {
        return setIgnoreNullValue(true);
    }

    public CopyOptions setPropertiesFilter(BiPredicate<Field, Object> propertiesFilter) {
        this.propertiesFilter = propertiesFilter;
        return this;
    }

    public CopyOptions setIgnoreProperties(String... ignoreProperties) {
        return setPropertiesFilter((field, o) -> {
            return false == ArrayUtil.contains(ignoreProperties, field.getName());
        });
    }

    public CopyOptions setIgnoreError(boolean ignoreError) {
        this.ignoreError = ignoreError;
        return this;
    }

    public CopyOptions ignoreError() {
        return setIgnoreError(true);
    }

    public CopyOptions setIgnoreCase(boolean ignoreCase) {
        this.ignoreCase = ignoreCase;
        return this;
    }

    public CopyOptions ignoreCase() {
        return setIgnoreCase(true);
    }

    public CopyOptions setFieldMapping(Map<String, String> fieldMapping) {
        return setFieldNameEditor(key -> {
            return (String) fieldMapping.getOrDefault(key, key);
        });
    }

    public CopyOptions setFieldNameEditor(Editor<String> fieldNameEditor) {
        this.fieldNameEditor = fieldNameEditor;
        return this;
    }

    public CopyOptions setFieldValueEditor(BiFunction<String, Object, Object> fieldValueEditor) {
        this.fieldValueEditor = fieldValueEditor;
        return this;
    }

    protected Object editFieldValue(String fieldName, Object fieldValue) {
        return null != this.fieldValueEditor ? this.fieldValueEditor.apply(fieldName, fieldValue) : fieldValue;
    }

    public CopyOptions setTransientSupport(boolean transientSupport) {
        this.transientSupport = transientSupport;
        return this;
    }

    public CopyOptions setOverride(boolean override) {
        this.override = override;
        return this;
    }

    protected String editFieldName(String fieldName) {
        return null != this.fieldNameEditor ? this.fieldNameEditor.edit(fieldName) : fieldName;
    }

    protected boolean testPropertyFilter(Field field, Object value) {
        return null == this.propertiesFilter || this.propertiesFilter.test(field, value);
    }
}
