package org.apache.fontbox.afm;

/* loaded from: reader.jar:BOOT-INF/lib/fontbox-2.0.27.jar:org/apache/fontbox/afm/Ligature.class */
public class Ligature {
    private String successor;
    private String ligature;

    public String getLigature() {
        return this.ligature;
    }

    public void setLigature(String lig) {
        this.ligature = lig;
    }

    public String getSuccessor() {
        return this.successor;
    }

    public void setSuccessor(String successorValue) {
        this.successor = successorValue;
    }
}
