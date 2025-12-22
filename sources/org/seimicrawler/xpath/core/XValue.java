package org.seimicrawler.xpath.core;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.seimicrawler.xpath.exception.XpathParserException;

/* loaded from: reader.jar:BOOT-INF/lib/JsoupXpath-2.5.0.jar:org/seimicrawler/xpath/core/XValue.class */
public class XValue implements Comparable<XValue> {
    private Object value;
    private boolean isAttr = false;
    private boolean isExprStr = false;
    private int siblingIndex;
    private List<XValue> xValues;

    public XValue(Object val) {
        this.value = val;
    }

    public static XValue create(Object val) {
        return new XValue(val);
    }

    public boolean isBoolean() {
        return this.value instanceof Boolean;
    }

    public boolean isNumber() {
        return this.value instanceof Number;
    }

    public boolean isElements() {
        return this.value instanceof Elements;
    }

    public boolean isString() {
        return this.value instanceof String;
    }

    public boolean isList() {
        return this.value instanceof List;
    }

    public boolean isDate() {
        return this.value instanceof Date;
    }

    public Boolean asBoolean() {
        if (this.value instanceof Boolean) {
            return (Boolean) this.value;
        }
        return Boolean.valueOf((this.value == null || StringUtils.isBlank(asString())) ? false : true);
    }

    public Date asDate() {
        if (this.value instanceof String) {
            try {
                return DateFormatUtils.ISO_DATETIME_TIME_ZONE_FORMAT.parse((String) this.value);
            } catch (ParseException e) {
                throw new XpathParserException("cast to date fail. vale = " + this.value);
            }
        }
        if (this.value instanceof Date) {
            return (Date) this.value;
        }
        throw new XpathParserException("cast to date fail. vale = " + this.value);
    }

    public Double asDouble() {
        if (this.value instanceof String) {
            return Double.valueOf(new BigDecimal((String) this.value).doubleValue());
        }
        if (this.value instanceof Number) {
            return Double.valueOf(((Number) this.value).doubleValue());
        }
        throw new XpathParserException("cast to number fail. vale = " + this.value);
    }

    public Long asLong() {
        if (this.value instanceof String) {
            return Long.valueOf(new BigDecimal((String) this.value).setScale(0, 4).longValue());
        }
        if (this.value instanceof Number) {
            return Long.valueOf(((Number) this.value).longValue());
        }
        throw new XpathParserException("cast to number fail. vale = " + this.value);
    }

    public Elements asElements() {
        return (Elements) this.value;
    }

    public String asString() {
        if (isElements()) {
            StringBuilder accum = new StringBuilder();
            Iterator<Element> it = asElements().iterator();
            while (it.hasNext()) {
                Element e = it.next();
                accum.append(e.ownText());
            }
            return accum.toString();
        }
        if ((this.value instanceof Element) && Objects.equals(((Element) this.value).tagName(), Constants.DEF_TEXT_TAG_NAME)) {
            return ((Element) this.value).ownText();
        }
        if (this.value instanceof List) {
            return StringUtils.join((List) this.value, ",");
        }
        return String.valueOf(this.value).trim();
    }

    public List<String> asList() {
        return (List) this.value;
    }

    public XValue attr() {
        this.isAttr = true;
        return this;
    }

    public boolean isAttr() {
        return this.isAttr;
    }

    public XValue exprStr() {
        this.isExprStr = true;
        String str = StringUtils.removeStart(String.valueOf(this.value), OperatorName.SHOW_TEXT_LINE);
        this.value = StringUtils.removeEnd(StringUtils.removeEnd(StringUtils.removeStart(str, OperatorName.SHOW_TEXT_LINE_AND_SPACE), OperatorName.SHOW_TEXT_LINE), OperatorName.SHOW_TEXT_LINE_AND_SPACE);
        return this;
    }

    public boolean isExprStr() {
        return this.isExprStr;
    }

    public int getSiblingIndex() {
        return this.siblingIndex;
    }

    public void setSiblingIndex(int siblingIndex) {
        this.siblingIndex = siblingIndex;
    }

    public List<XValue> getxValues() {
        return this.xValues;
    }

    public void setxValues(List<XValue> xValues) {
        this.xValues = xValues;
    }

    public String toString() {
        return new ToStringBuilder(this).append("value", this.value).append("isAttr", this.isAttr).append("isExprStr", this.isExprStr).toString();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        XValue value1 = (XValue) o;
        return Objects.equals(this.value, value1.value);
    }

    public int hashCode() {
        return Objects.hashCode(this.value);
    }

    @Override // java.lang.Comparable
    public int compareTo(XValue o) {
        if (equals(o)) {
            return 0;
        }
        if (o == null || o.value == null) {
            return 1;
        }
        if (this.value == null) {
            return -1;
        }
        if (isString()) {
            return asString().compareTo(o.asString());
        }
        if (isNumber()) {
            return asDouble().compareTo(o.asDouble());
        }
        throw new XpathParserException("Unsupported comparable XValue = " + toString());
    }

    public Class valType() {
        if (this.value == null) {
            return Object.class;
        }
        return this.value.getClass();
    }
}
