package org.apache.fontbox.afm;

/* loaded from: reader.jar:BOOT-INF/lib/fontbox-2.0.27.jar:org/apache/fontbox/afm/CompositePart.class */
public class CompositePart {
    private String name;
    private int xDisplacement;
    private int yDisplacement;

    public String getName() {
        return this.name;
    }

    public void setName(String nameValue) {
        this.name = nameValue;
    }

    public int getXDisplacement() {
        return this.xDisplacement;
    }

    public void setXDisplacement(int xDisp) {
        this.xDisplacement = xDisp;
    }

    public int getYDisplacement() {
        return this.yDisplacement;
    }

    public void setYDisplacement(int yDisp) {
        this.yDisplacement = yDisp;
    }
}
