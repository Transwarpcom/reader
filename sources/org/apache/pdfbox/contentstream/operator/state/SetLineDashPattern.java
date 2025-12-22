package org.apache.pdfbox.contentstream.operator.state;

import java.util.Iterator;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.pdfbox.contentstream.operator.MissingOperandException;
import org.apache.pdfbox.contentstream.operator.Operator;
import org.apache.pdfbox.contentstream.operator.OperatorProcessor;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSNumber;

/* loaded from: reader.jar:BOOT-INF/lib/pdfbox-2.0.27.jar:org/apache/pdfbox/contentstream/operator/state/SetLineDashPattern.class */
public class SetLineDashPattern extends OperatorProcessor {
    private static final Log LOG = LogFactory.getLog((Class<?>) SetLineDashPattern.class);

    @Override // org.apache.pdfbox.contentstream.operator.OperatorProcessor
    public void process(Operator operator, List<COSBase> arguments) throws MissingOperandException {
        if (arguments.size() < 2) {
            throw new MissingOperandException(operator, arguments);
        }
        COSBase base0 = arguments.get(0);
        if (!(base0 instanceof COSArray)) {
            return;
        }
        COSBase base1 = arguments.get(1);
        if (!(base1 instanceof COSNumber)) {
            return;
        }
        COSArray dashArray = (COSArray) base0;
        int dashPhase = ((COSNumber) base1).intValue();
        Iterator<COSBase> it = dashArray.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            COSBase base = it.next();
            if (base instanceof COSNumber) {
                COSNumber num = (COSNumber) base;
                if (num.floatValue() != 0.0f) {
                    break;
                }
            } else {
                LOG.warn("dash array has non number element " + base + ", ignored");
                dashArray = new COSArray();
                break;
            }
        }
        this.context.setLineDashPattern(dashArray, dashPhase);
    }

    @Override // org.apache.pdfbox.contentstream.operator.OperatorProcessor
    public String getName() {
        return "d";
    }
}
