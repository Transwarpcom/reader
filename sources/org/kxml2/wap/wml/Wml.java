package org.kxml2.wap.wml;

import io.legado.app.constant.Action;
import io.vertx.ext.web.handler.FormLoginHandler;
import me.ag2s.epublib.epub.NCXDocumentV2;
import me.ag2s.epublib.epub.NCXDocumentV3;
import me.ag2s.epublib.epub.PackageDocumentBase;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.kxml2.wap.WbxmlParser;
import org.kxml2.wap.WbxmlSerializer;

/* loaded from: reader.jar:BOOT-INF/classes/org/kxml2/wap/wml/Wml.class */
public abstract class Wml {
    public static final String[] TAG_TABLE = {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, "a", "td", "tr", "table", "p", "postfield", "anchor", "access", OperatorName.CLOSE_FILL_NON_ZERO_AND_STROKE, "big", "br", "card", "do", "em", "fieldset", "go", "head", "i", "img", "input", "meta", "noop", Action.prev, "onevent", "optgroup", "option", "refresh", "select", "small", "strong", null, "template", "timer", "u", "setvar", "wml"};
    public static final String[] ATTR_START_TABLE = {"accept-charset", "align=bottom", "align=center", "align=left", "align=middle", "align=right", "align=top", "alt", "content", null, "domain", "emptyok=false", "emptyok=true", PackageDocumentBase.DCTags.format, "height", "hspace", "ivalue", "iname", null, "label", "localsrc", "maxlength", "method=get", "method=post", "mode=nowrap", "mode=wrap", "multiple=false", "multiple=true", "name", "newcontext=false", "newcontext=true", "onpick", "onenterbackward", "onenterforward", "ontimer", "optimal=false", "optimal=true", "path", null, null, null, "scheme", "sendreferer=false", "sendreferer=true", "size", NCXDocumentV2.NCXAttributes.src, "ordered=true", "ordered=false", "tabindex", "title", "type", "type=accept", "type=delete", "type=help", "type=password", "type=onpick", "type=onenterbackward", "type=onenterforward", "type=ontimer", null, null, null, null, null, "type=options", "type=prev", "type=reset", "type=text", "type=vnd.", "href", "href=http://", "href=https://", "value", "vspace", "width", NCXDocumentV3.XHTMLAttributes.xml_lang, null, "align", "columns", "class", "id", "forua=false", "forua=true", "src=http://", "src=https://", NCXDocumentV3.XHTMLAttributes.http_equiv, "http-equiv=Content-Type", "content=application/vnd.wap.wmlc;charset=", "http-equiv=Expires", null, null};
    public static final String[] ATTR_VALUE_TABLE = {".com/", ".edu/", ".net/", ".org/", "accept", "bottom", "clear", "delete", "help", "http://", "http://www.", "https://", "https://www.", null, "middle", "nowrap", "onpick", "onenterbackward", "onenterforward", "ontimer", "options", FormLoginHandler.DEFAULT_PASSWORD_PARAM, "reset", null, NCXDocumentV2.NCXTags.text, "top", "unknown", "wrap", "www."};

    public static WbxmlParser createParser() {
        WbxmlParser p = new WbxmlParser();
        p.setTagTable(0, TAG_TABLE);
        p.setAttrStartTable(0, ATTR_START_TABLE);
        p.setAttrValueTable(0, ATTR_VALUE_TABLE);
        return p;
    }

    public static WbxmlSerializer createSerializer() {
        WbxmlSerializer s = new WbxmlSerializer();
        s.setTagTable(0, TAG_TABLE);
        s.setAttrStartTable(0, ATTR_START_TABLE);
        s.setAttrValueTable(0, ATTR_VALUE_TABLE);
        return s;
    }
}
