package org.apache.pdfbox.pdmodel.graphics.color;

import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSFloat;
import org.apache.pdfbox.cos.COSNumber;
import org.apache.pdfbox.pdmodel.common.COSObjectable;

/* loaded from: reader.jar:BOOT-INF/lib/pdfbox-2.0.27.jar:org/apache/pdfbox/pdmodel/graphics/color/PDTristimulus.class */
public final class PDTristimulus implements COSObjectable {
    private final COSArray values;

    public PDTristimulus() {
        this.values = new COSArray();
        this.values.add((COSBase) new COSFloat(0.0f));
        this.values.add((COSBase) new COSFloat(0.0f));
        this.values.add((COSBase) new COSFloat(0.0f));
    }

    public PDTristimulus(COSArray array) {
        this.values = array;
    }

    public PDTristimulus(float[] array) {
        this.values = new COSArray();
        for (int i = 0; i < array.length && i < 3; i++) {
            this.values.add((COSBase) new COSFloat(array[i]));
        }
    }

    @Override // org.apache.pdfbox.pdmodel.common.COSObjectable
    public COSBase getCOSObject() {
        return this.values;
    }

    public float getX() {
        return ((COSNumber) this.values.get(0)).floatValue();
    }

    public void setX(float x) {
        this.values.set(0, (COSBase) new COSFloat(x));
    }

    public float getY() {
        return ((COSNumber) this.values.get(1)).floatValue();
    }

    public void setY(float y) {
        this.values.set(1, (COSBase) new COSFloat(y));
    }

    public float getZ() {
        return ((COSNumber) this.values.get(2)).floatValue();
    }

    public void setZ(float z) {
        this.values.set(2, (COSBase) new COSFloat(z));
    }
}
