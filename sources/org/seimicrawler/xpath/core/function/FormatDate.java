package org.seimicrawler.xpath.core.function;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import org.apache.commons.lang3.time.FastDateFormat;
import org.seimicrawler.xpath.core.Function;
import org.seimicrawler.xpath.core.Scope;
import org.seimicrawler.xpath.core.XValue;
import org.seimicrawler.xpath.exception.XpathParserException;

/* loaded from: reader.jar:BOOT-INF/lib/JsoupXpath-2.5.0.jar:org/seimicrawler/xpath/core/function/FormatDate.class */
public class FormatDate implements Function {
    @Override // org.seimicrawler.xpath.core.Function
    public String name() {
        return "format-date";
    }

    @Override // org.seimicrawler.xpath.core.Function
    public XValue call(Scope scope, List<XValue> params) {
        String value = params.get(0).asString();
        String patten = params.get(1).asString();
        try {
            if (params.size() > 2 && null != params.get(2)) {
                Locale locale = Locale.forLanguageTag(params.get(2).asString());
                SimpleDateFormat format = new SimpleDateFormat(patten, locale);
                return XValue.create(format.parse(value));
            }
            return XValue.create(FastDateFormat.getInstance(patten).parse(value));
        } catch (ParseException e) {
            throw new XpathParserException("date format exception!", e);
        }
    }
}
