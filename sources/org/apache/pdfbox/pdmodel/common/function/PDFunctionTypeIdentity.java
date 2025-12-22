package org.apache.pdfbox.pdmodel.common.function;

import java.io.IOException;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;

/* loaded from: reader.jar:BOOT-INF/lib/pdfbox-2.0.27.jar:org/apache/pdfbox/pdmodel/common/function/PDFunctionTypeIdentity.class */
public class PDFunctionTypeIdentity extends PDFunction {
    public PDFunctionTypeIdentity(COSBase function) {
        super(null);
    }

    @Override // org.apache.pdfbox.pdmodel.common.function.PDFunction
    public int getFunctionType() {
        throw new UnsupportedOperationException();
    }

    @Override // org.apache.pdfbox.pdmodel.common.function.PDFunction
    public float[] eval(float[] input) throws IOException {
        return input;
    }

    @Override // org.apache.pdfbox.pdmodel.common.function.PDFunction
    protected COSArray getRangeValues() {
        return null;
    }

    @Override // org.apache.pdfbox.pdmodel.common.function.PDFunction
    public String toString() {
        return "FunctionTypeIdentity";
    }
}
