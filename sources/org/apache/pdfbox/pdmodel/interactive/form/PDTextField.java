package org.apache.pdfbox.pdmodel.interactive.form;

import java.io.IOException;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;

/* loaded from: reader.jar:BOOT-INF/lib/pdfbox-2.0.27.jar:org/apache/pdfbox/pdmodel/interactive/form/PDTextField.class */
public final class PDTextField extends PDVariableText {
    private static final int FLAG_MULTILINE = 4096;
    private static final int FLAG_PASSWORD = 8192;
    private static final int FLAG_FILE_SELECT = 1048576;
    private static final int FLAG_DO_NOT_SPELL_CHECK = 4194304;
    private static final int FLAG_DO_NOT_SCROLL = 8388608;
    private static final int FLAG_COMB = 16777216;
    private static final int FLAG_RICH_TEXT = 33554432;

    public PDTextField(PDAcroForm acroForm) {
        super(acroForm);
        getCOSObject().setItem(COSName.FT, (COSBase) COSName.TX);
    }

    PDTextField(PDAcroForm acroForm, COSDictionary field, PDNonTerminalField parent) {
        super(acroForm, field, parent);
    }

    public boolean isMultiline() {
        return getCOSObject().getFlag(COSName.FF, 4096);
    }

    public void setMultiline(boolean multiline) {
        getCOSObject().setFlag(COSName.FF, 4096, multiline);
    }

    public boolean isPassword() {
        return getCOSObject().getFlag(COSName.FF, 8192);
    }

    public void setPassword(boolean password) {
        getCOSObject().setFlag(COSName.FF, 8192, password);
    }

    public boolean isFileSelect() {
        return getCOSObject().getFlag(COSName.FF, FLAG_FILE_SELECT);
    }

    public void setFileSelect(boolean fileSelect) {
        getCOSObject().setFlag(COSName.FF, FLAG_FILE_SELECT, fileSelect);
    }

    public boolean doNotSpellCheck() {
        return getCOSObject().getFlag(COSName.FF, FLAG_DO_NOT_SPELL_CHECK);
    }

    public void setDoNotSpellCheck(boolean doNotSpellCheck) {
        getCOSObject().setFlag(COSName.FF, FLAG_DO_NOT_SPELL_CHECK, doNotSpellCheck);
    }

    public boolean doNotScroll() {
        return getCOSObject().getFlag(COSName.FF, FLAG_DO_NOT_SCROLL);
    }

    public void setDoNotScroll(boolean doNotScroll) {
        getCOSObject().setFlag(COSName.FF, FLAG_DO_NOT_SCROLL, doNotScroll);
    }

    public boolean isComb() {
        return getCOSObject().getFlag(COSName.FF, 16777216);
    }

    public void setComb(boolean comb) {
        getCOSObject().setFlag(COSName.FF, 16777216, comb);
    }

    public boolean isRichText() {
        return getCOSObject().getFlag(COSName.FF, FLAG_RICH_TEXT);
    }

    public void setRichText(boolean richText) {
        getCOSObject().setFlag(COSName.FF, FLAG_RICH_TEXT, richText);
    }

    public int getMaxLen() {
        return getCOSObject().getInt(COSName.MAX_LEN);
    }

    public void setMaxLen(int maxLen) {
        getCOSObject().setInt(COSName.MAX_LEN, maxLen);
    }

    @Override // org.apache.pdfbox.pdmodel.interactive.form.PDField
    public void setValue(String value) throws IOException {
        getCOSObject().setString(COSName.V, value);
        applyChange();
    }

    public void setDefaultValue(String value) throws IOException {
        getCOSObject().setString(COSName.DV, value);
    }

    public String getValue() {
        return getStringOrStream(getInheritableAttribute(COSName.V));
    }

    public String getDefaultValue() {
        return getStringOrStream(getInheritableAttribute(COSName.DV));
    }

    @Override // org.apache.pdfbox.pdmodel.interactive.form.PDField
    public String getValueAsString() {
        return getValue();
    }

    @Override // org.apache.pdfbox.pdmodel.interactive.form.PDTerminalField
    void constructAppearances() throws IOException {
        AppearanceGeneratorHelper apHelper = new AppearanceGeneratorHelper(this);
        apHelper.setAppearanceValue(getValue());
    }
}
