package org.apache.fontbox.ttf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/* loaded from: reader.jar:BOOT-INF/lib/fontbox-2.0.27.jar:org/apache/fontbox/ttf/GlyfCompositeDescript.class */
public class GlyfCompositeDescript extends GlyfDescript {
    private static final Log LOG = LogFactory.getLog((Class<?>) GlyfCompositeDescript.class);
    private final List<GlyfCompositeComp> components;
    private final Map<Integer, GlyphDescription> descriptions;
    private GlyphTable glyphTable;
    private boolean beingResolved;
    private boolean resolved;
    private int pointCount;
    private int contourCount;

    GlyfCompositeDescript(TTFDataStream bais, GlyphTable glyphTable) throws IOException {
        GlyfCompositeComp comp;
        super((short) -1, bais);
        this.components = new ArrayList();
        this.descriptions = new HashMap();
        this.glyphTable = null;
        this.beingResolved = false;
        this.resolved = false;
        this.pointCount = -1;
        this.contourCount = -1;
        this.glyphTable = glyphTable;
        do {
            comp = new GlyfCompositeComp(bais);
            this.components.add(comp);
        } while ((comp.getFlags() & 32) != 0);
        if ((comp.getFlags() & 256) != 0) {
            readInstructions(bais, bais.readUnsignedShort());
        }
        initDescriptions();
    }

    @Override // org.apache.fontbox.ttf.GlyfDescript, org.apache.fontbox.ttf.GlyphDescription
    public void resolve() {
        if (this.resolved) {
            return;
        }
        if (this.beingResolved) {
            LOG.error("Circular reference in GlyfCompositeDesc");
            return;
        }
        this.beingResolved = true;
        int firstIndex = 0;
        int firstContour = 0;
        for (GlyfCompositeComp comp : this.components) {
            comp.setFirstIndex(firstIndex);
            comp.setFirstContour(firstContour);
            GlyphDescription desc = this.descriptions.get(Integer.valueOf(comp.getGlyphIndex()));
            if (desc != null) {
                desc.resolve();
                firstIndex += desc.getPointCount();
                firstContour += desc.getContourCount();
            }
        }
        this.resolved = true;
        this.beingResolved = false;
    }

    @Override // org.apache.fontbox.ttf.GlyphDescription
    public int getEndPtOfContours(int i) {
        GlyfCompositeComp c = getCompositeCompEndPt(i);
        if (c != null) {
            GlyphDescription gd = this.descriptions.get(Integer.valueOf(c.getGlyphIndex()));
            return gd.getEndPtOfContours(i - c.getFirstContour()) + c.getFirstIndex();
        }
        return 0;
    }

    @Override // org.apache.fontbox.ttf.GlyphDescription
    public byte getFlags(int i) {
        GlyfCompositeComp c = getCompositeComp(i);
        if (c != null) {
            GlyphDescription gd = this.descriptions.get(Integer.valueOf(c.getGlyphIndex()));
            return gd.getFlags(i - c.getFirstIndex());
        }
        return (byte) 0;
    }

    @Override // org.apache.fontbox.ttf.GlyphDescription
    public short getXCoordinate(int i) {
        GlyfCompositeComp c = getCompositeComp(i);
        if (c != null) {
            GlyphDescription gd = this.descriptions.get(Integer.valueOf(c.getGlyphIndex()));
            int n = i - c.getFirstIndex();
            int x = gd.getXCoordinate(n);
            int y = gd.getYCoordinate(n);
            return (short) (c.scaleX(x, y) + c.getXTranslate());
        }
        return (short) 0;
    }

    @Override // org.apache.fontbox.ttf.GlyphDescription
    public short getYCoordinate(int i) {
        GlyfCompositeComp c = getCompositeComp(i);
        if (c != null) {
            GlyphDescription gd = this.descriptions.get(Integer.valueOf(c.getGlyphIndex()));
            int n = i - c.getFirstIndex();
            int x = gd.getXCoordinate(n);
            int y = gd.getYCoordinate(n);
            return (short) (c.scaleY(x, y) + c.getYTranslate());
        }
        return (short) 0;
    }

    @Override // org.apache.fontbox.ttf.GlyphDescription
    public boolean isComposite() {
        return true;
    }

    @Override // org.apache.fontbox.ttf.GlyphDescription
    public int getPointCount() {
        if (!this.resolved) {
            LOG.error("getPointCount called on unresolved GlyfCompositeDescript");
        }
        if (this.pointCount < 0) {
            GlyfCompositeComp c = this.components.get(this.components.size() - 1);
            GlyphDescription gd = this.descriptions.get(Integer.valueOf(c.getGlyphIndex()));
            if (gd == null) {
                LOG.error("GlyphDescription for index " + c.getGlyphIndex() + " is null, returning 0");
                this.pointCount = 0;
            } else {
                this.pointCount = c.getFirstIndex() + gd.getPointCount();
            }
        }
        return this.pointCount;
    }

    @Override // org.apache.fontbox.ttf.GlyfDescript, org.apache.fontbox.ttf.GlyphDescription
    public int getContourCount() {
        if (!this.resolved) {
            LOG.error("getContourCount called on unresolved GlyfCompositeDescript");
        }
        if (this.contourCount < 0) {
            GlyfCompositeComp c = this.components.get(this.components.size() - 1);
            GlyphDescription gd = this.descriptions.get(Integer.valueOf(c.getGlyphIndex()));
            if (gd == null) {
                LOG.error("missing glyph description for index " + c.getGlyphIndex());
                this.contourCount = 0;
            } else {
                this.contourCount = c.getFirstContour() + gd.getContourCount();
            }
        }
        return this.contourCount;
    }

    public int getComponentCount() {
        return this.components.size();
    }

    private GlyfCompositeComp getCompositeComp(int i) {
        for (GlyfCompositeComp c : this.components) {
            GlyphDescription gd = this.descriptions.get(Integer.valueOf(c.getGlyphIndex()));
            if (c.getFirstIndex() <= i && gd != null && i < c.getFirstIndex() + gd.getPointCount()) {
                return c;
            }
        }
        return null;
    }

    private GlyfCompositeComp getCompositeCompEndPt(int i) {
        for (GlyfCompositeComp c : this.components) {
            GlyphDescription gd = this.descriptions.get(Integer.valueOf(c.getGlyphIndex()));
            if (c.getFirstContour() <= i && gd != null && i < c.getFirstContour() + gd.getContourCount()) {
                return c;
            }
        }
        return null;
    }

    private void initDescriptions() {
        for (GlyfCompositeComp component : this.components) {
            try {
                int index = component.getGlyphIndex();
                GlyphData glyph = this.glyphTable.getGlyph(index);
                if (glyph != null) {
                    this.descriptions.put(Integer.valueOf(index), glyph.getDescription());
                }
            } catch (IOException e) {
                LOG.error(e);
            }
        }
    }
}
