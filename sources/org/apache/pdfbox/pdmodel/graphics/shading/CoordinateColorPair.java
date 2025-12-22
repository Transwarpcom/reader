package org.apache.pdfbox.pdmodel.graphics.shading;

import java.awt.geom.Point2D;

/* loaded from: reader.jar:BOOT-INF/lib/pdfbox-2.0.27.jar:org/apache/pdfbox/pdmodel/graphics/shading/CoordinateColorPair.class */
class CoordinateColorPair {
    final Point2D coordinate;
    final float[] color;

    CoordinateColorPair(Point2D p, float[] c) {
        this.coordinate = p;
        this.color = (float[]) c.clone();
    }
}
