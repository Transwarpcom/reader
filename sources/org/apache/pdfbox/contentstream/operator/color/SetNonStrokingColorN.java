package org.apache.pdfbox.contentstream.operator.color;

import org.apache.pdfbox.contentstream.operator.OperatorName;

/* loaded from: reader.jar:BOOT-INF/lib/pdfbox-2.0.27.jar:org/apache/pdfbox/contentstream/operator/color/SetNonStrokingColorN.class */
public class SetNonStrokingColorN extends SetNonStrokingColor {
    @Override // org.apache.pdfbox.contentstream.operator.color.SetNonStrokingColor, org.apache.pdfbox.contentstream.operator.OperatorProcessor
    public String getName() {
        return OperatorName.NON_STROKING_COLOR_N;
    }
}
