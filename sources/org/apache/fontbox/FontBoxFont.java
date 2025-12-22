package org.apache.fontbox;

import java.awt.geom.GeneralPath;
import java.io.IOException;
import java.util.List;
import org.apache.fontbox.util.BoundingBox;

/* loaded from: reader.jar:BOOT-INF/lib/fontbox-2.0.27.jar:org/apache/fontbox/FontBoxFont.class */
public interface FontBoxFont {
    String getName() throws IOException;

    BoundingBox getFontBBox() throws IOException;

    List<Number> getFontMatrix() throws IOException;

    GeneralPath getPath(String str) throws IOException;

    float getWidth(String str) throws IOException;

    boolean hasGlyph(String str) throws IOException;
}
