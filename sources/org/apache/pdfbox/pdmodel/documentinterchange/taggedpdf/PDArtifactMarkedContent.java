package org.apache.pdfbox.pdmodel.documentinterchange.taggedpdf;

import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.documentinterchange.markedcontent.PDMarkedContent;

/* loaded from: reader.jar:BOOT-INF/lib/pdfbox-2.0.27.jar:org/apache/pdfbox/pdmodel/documentinterchange/taggedpdf/PDArtifactMarkedContent.class */
public class PDArtifactMarkedContent extends PDMarkedContent {
    public PDArtifactMarkedContent(COSDictionary properties) {
        super(COSName.ARTIFACT, properties);
    }

    public String getType() {
        return getProperties().getNameAsString(COSName.TYPE);
    }

    public PDRectangle getBBox() {
        PDRectangle retval = null;
        COSArray a = (COSArray) getProperties().getDictionaryObject(COSName.BBOX);
        if (a != null) {
            retval = new PDRectangle(a);
        }
        return retval;
    }

    public boolean isTopAttached() {
        return isAttached("Top");
    }

    public boolean isBottomAttached() {
        return isAttached("Bottom");
    }

    public boolean isLeftAttached() {
        return isAttached("Left");
    }

    public boolean isRightAttached() {
        return isAttached("Right");
    }

    public String getSubtype() {
        return getProperties().getNameAsString(COSName.SUBTYPE);
    }

    private boolean isAttached(String edge) {
        COSArray a = (COSArray) getProperties().getDictionaryObject(COSName.ATTACHED);
        if (a != null) {
            for (int i = 0; i < a.size(); i++) {
                if (edge.equals(a.getName(i))) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }
}
