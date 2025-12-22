package org.apache.pdfbox.pdmodel.interactive.action;

import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.pdmodel.common.COSObjectable;

/* loaded from: reader.jar:BOOT-INF/lib/pdfbox-2.0.27.jar:org/apache/pdfbox/pdmodel/interactive/action/PDURIDictionary.class */
public class PDURIDictionary implements COSObjectable {
    private final COSDictionary uriDictionary;

    public PDURIDictionary() {
        this.uriDictionary = new COSDictionary();
    }

    public PDURIDictionary(COSDictionary dictionary) {
        this.uriDictionary = dictionary;
    }

    @Override // org.apache.pdfbox.pdmodel.common.COSObjectable
    public COSDictionary getCOSObject() {
        return this.uriDictionary;
    }

    public String getBase() {
        return getCOSObject().getString("Base");
    }

    public void setBase(String base) {
        getCOSObject().setString("Base", base);
    }
}
