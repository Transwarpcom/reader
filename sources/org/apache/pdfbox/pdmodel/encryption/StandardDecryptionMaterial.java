package org.apache.pdfbox.pdmodel.encryption;

/* loaded from: reader.jar:BOOT-INF/lib/pdfbox-2.0.27.jar:org/apache/pdfbox/pdmodel/encryption/StandardDecryptionMaterial.class */
public class StandardDecryptionMaterial extends DecryptionMaterial {
    private final String password;

    public StandardDecryptionMaterial(String pwd) {
        this.password = pwd;
    }

    public String getPassword() {
        return this.password;
    }
}
