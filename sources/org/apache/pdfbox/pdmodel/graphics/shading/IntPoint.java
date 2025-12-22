package org.apache.pdfbox.pdmodel.graphics.shading;

import java.awt.Point;
import java.awt.geom.Point2D;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/* loaded from: reader.jar:BOOT-INF/lib/pdfbox-2.0.27.jar:org/apache/pdfbox/pdmodel/graphics/shading/IntPoint.class */
class IntPoint extends Point {
    private static final Log LOG = LogFactory.getLog((Class<?>) IntPoint.class);

    IntPoint(int x, int y) {
        super(x, y);
    }

    public int hashCode() {
        return (89 * (623 + this.x)) + this.y;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            if (obj instanceof Point2D) {
                LOG.error("IntPoint should not be used together with its base class");
                return false;
            }
            return false;
        }
        IntPoint other = (IntPoint) obj;
        return this.x == other.x && this.y == other.y;
    }
}
