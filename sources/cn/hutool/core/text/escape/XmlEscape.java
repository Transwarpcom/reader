package cn.hutool.core.text.escape;

import cn.hutool.core.text.replacer.LookupReplacer;
import cn.hutool.core.text.replacer.ReplacerChain;
import cn.hutool.core.text.replacer.StrReplacer;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.springframework.beans.factory.BeanFactory;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/text/escape/XmlEscape.class */
public class XmlEscape extends ReplacerChain {
    private static final long serialVersionUID = 1;
    protected static final String[][] BASIC_ESCAPE = {new String[]{OperatorName.SHOW_TEXT_LINE_AND_SPACE, "&quot;"}, new String[]{BeanFactory.FACTORY_BEAN_PREFIX, "&amp;"}, new String[]{"<", "&lt;"}, new String[]{">", "&gt;"}};

    public XmlEscape() {
        super(new StrReplacer[0]);
        addChain((StrReplacer) new LookupReplacer(BASIC_ESCAPE));
    }
}
