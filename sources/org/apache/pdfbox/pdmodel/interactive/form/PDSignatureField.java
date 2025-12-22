package org.apache.pdfbox.pdmodel.interactive.form;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationWidget;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.PDSeedValue;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.PDSignature;

/* loaded from: reader.jar:BOOT-INF/lib/pdfbox-2.0.27.jar:org/apache/pdfbox/pdmodel/interactive/form/PDSignatureField.class */
public class PDSignatureField extends PDTerminalField {
    private static final Log LOG = LogFactory.getLog((Class<?>) PDSignatureField.class);

    public PDSignatureField(PDAcroForm acroForm) throws IOException {
        super(acroForm);
        getCOSObject().setItem(COSName.FT, (COSBase) COSName.SIG);
        PDAnnotationWidget firstWidget = getWidgets().get(0);
        firstWidget.setLocked(true);
        firstWidget.setPrinted(true);
        setPartialName(generatePartialName());
    }

    PDSignatureField(PDAcroForm acroForm, COSDictionary field, PDNonTerminalField parent) {
        super(acroForm, field, parent);
    }

    private String generatePartialName() {
        Set<String> nameSet = new HashSet<>();
        Iterator<PDField> it = getAcroForm().getFieldTree().iterator();
        while (it.hasNext()) {
            PDField field = it.next();
            nameSet.add(field.getPartialName());
        }
        int i = 1;
        while (nameSet.contains("Signature" + i)) {
            i++;
        }
        return "Signature" + i;
    }

    @Deprecated
    public void setSignature(PDSignature value) throws IOException {
        setValue(value);
    }

    public PDSignature getSignature() {
        return getValue();
    }

    public void setValue(PDSignature value) throws IOException {
        getCOSObject().setItem(COSName.V, value);
        applyChange();
    }

    @Override // org.apache.pdfbox.pdmodel.interactive.form.PDField
    public void setValue(String value) {
        throw new UnsupportedOperationException("Signature fields don't support setting the value as String - use setValue(PDSignature value) instead");
    }

    public void setDefaultValue(PDSignature value) throws IOException {
        getCOSObject().setItem(COSName.DV, value);
    }

    public PDSignature getValue() {
        COSBase value = getCOSObject().getDictionaryObject(COSName.V);
        if (value instanceof COSDictionary) {
            return new PDSignature((COSDictionary) value);
        }
        return null;
    }

    public PDSignature getDefaultValue() {
        COSBase value = getCOSObject().getDictionaryObject(COSName.DV);
        if (value == null) {
            return null;
        }
        return new PDSignature((COSDictionary) value);
    }

    @Override // org.apache.pdfbox.pdmodel.interactive.form.PDField
    public String getValueAsString() {
        PDSignature signature = getValue();
        return signature != null ? signature.toString() : "";
    }

    public PDSeedValue getSeedValue() {
        COSDictionary dict = (COSDictionary) getCOSObject().getDictionaryObject(COSName.SV);
        PDSeedValue sv = null;
        if (dict != null) {
            sv = new PDSeedValue(dict);
        }
        return sv;
    }

    public void setSeedValue(PDSeedValue sv) {
        if (sv != null) {
            getCOSObject().setItem(COSName.SV, sv);
        }
    }

    @Override // org.apache.pdfbox.pdmodel.interactive.form.PDTerminalField
    void constructAppearances() throws IOException {
        PDAnnotationWidget widget = getWidgets().get(0);
        if (widget != null && widget.getRectangle() != null) {
            if ((widget.getRectangle().getHeight() == 0.0f && widget.getRectangle().getWidth() == 0.0f) || widget.isNoView() || widget.isHidden()) {
                return;
            }
            LOG.warn("Appearance generation for signature fields not implemented here. You need to generate/update that manually, see the CreateVisibleSignature*.java files in the examples subproject of the source code download");
        }
    }
}
