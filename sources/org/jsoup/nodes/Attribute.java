package org.jsoup.nodes;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import javax.annotation.Nullable;
import org.jsoup.SerializationException;
import org.jsoup.helper.Validate;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Document;
import org.springframework.validation.DefaultBindingErrorProcessor;

/* loaded from: reader.jar:BOOT-INF/lib/jsoup-1.14.1.jar:org/jsoup/nodes/Attribute.class */
public class Attribute implements Map.Entry<String, String>, Cloneable {
    private static final String[] booleanAttributes = {"allowfullscreen", "async", "autofocus", "checked", "compact", "declare", "default", "defer", "disabled", "formnovalidate", "hidden", "inert", "ismap", "itemscope", "multiple", "muted", "nohref", "noresize", "noshade", "novalidate", "nowrap", "open", "readonly", DefaultBindingErrorProcessor.MISSING_FIELD_ERROR_CODE, "reversed", "seamless", "selected", "sortable", "truespeed", "typemustmatch"};
    private String key;

    @Nullable
    private String val;

    @Nullable
    Attributes parent;

    public Attribute(String key, @Nullable String value) {
        this(key, value, null);
    }

    public Attribute(String key, @Nullable String val, @Nullable Attributes parent) {
        Validate.notNull(key);
        String key2 = key.trim();
        Validate.notEmpty(key2);
        this.key = key2;
        this.val = val;
        this.parent = parent;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map.Entry
    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        int i;
        Validate.notNull(key);
        String key2 = key.trim();
        Validate.notEmpty(key2);
        if (this.parent != null && (i = this.parent.indexOfKey(this.key)) != -1) {
            this.parent.keys[i] = key2;
        }
        this.key = key2;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map.Entry
    public String getValue() {
        return Attributes.checkNotNull(this.val);
    }

    public boolean hasDeclaredValue() {
        return this.val != null;
    }

    @Override // java.util.Map.Entry
    public String setValue(String val) {
        String oldVal = this.val;
        if (this.parent != null) {
            oldVal = this.parent.get(this.key);
            int i = this.parent.indexOfKey(this.key);
            if (i != -1) {
                this.parent.vals[i] = val;
            }
        }
        this.val = val;
        return Attributes.checkNotNull(oldVal);
    }

    public String html() {
        StringBuilder sb = StringUtil.borrowBuilder();
        try {
            html(sb, new Document("").outputSettings());
            return StringUtil.releaseBuilder(sb);
        } catch (IOException exception) {
            throw new SerializationException(exception);
        }
    }

    protected static void html(String key, @Nullable String val, Appendable accum, Document.OutputSettings out) throws IOException {
        accum.append(key);
        if (!shouldCollapseAttribute(key, val, out)) {
            accum.append("=\"");
            Entities.escape(accum, Attributes.checkNotNull(val), out, true, false, false);
            accum.append('\"');
        }
    }

    protected void html(Appendable accum, Document.OutputSettings out) throws IOException {
        html(this.key, this.val, accum, out);
    }

    public String toString() {
        return html();
    }

    public static Attribute createFromEncoded(String unencodedKey, String encodedValue) {
        String value = Entities.unescape(encodedValue, true);
        return new Attribute(unencodedKey, value, null);
    }

    protected boolean isDataAttribute() {
        return isDataAttribute(this.key);
    }

    protected static boolean isDataAttribute(String key) {
        return key.startsWith("data-") && key.length() > "data-".length();
    }

    protected final boolean shouldCollapseAttribute(Document.OutputSettings out) {
        return shouldCollapseAttribute(this.key, this.val, out);
    }

    protected static boolean shouldCollapseAttribute(String key, @Nullable String val, Document.OutputSettings out) {
        return out.syntax() == Document.OutputSettings.Syntax.html && (val == null || ((val.isEmpty() || val.equalsIgnoreCase(key)) && isBooleanAttribute(key)));
    }

    protected static boolean isBooleanAttribute(String key) {
        return Arrays.binarySearch(booleanAttributes, key) >= 0;
    }

    @Override // java.util.Map.Entry
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Attribute attribute = (Attribute) o;
        if (this.key != null) {
            if (!this.key.equals(attribute.key)) {
                return false;
            }
        } else if (attribute.key != null) {
            return false;
        }
        return this.val != null ? this.val.equals(attribute.val) : attribute.val == null;
    }

    @Override // java.util.Map.Entry
    public int hashCode() {
        int result = this.key != null ? this.key.hashCode() : 0;
        return (31 * result) + (this.val != null ? this.val.hashCode() : 0);
    }

    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public Attribute m5201clone() {
        try {
            return (Attribute) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
