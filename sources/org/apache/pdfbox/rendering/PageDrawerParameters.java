package org.apache.pdfbox.rendering;

import java.awt.RenderingHints;
import org.apache.pdfbox.pdmodel.PDPage;

/* loaded from: reader.jar:BOOT-INF/lib/pdfbox-2.0.27.jar:org/apache/pdfbox/rendering/PageDrawerParameters.class */
public final class PageDrawerParameters {
    private final PDFRenderer renderer;
    private final PDPage page;
    private final boolean subsamplingAllowed;
    private final RenderDestination destination;
    private final RenderingHints renderingHints;
    private final float imageDownscalingOptimizationThreshold;

    PageDrawerParameters(PDFRenderer renderer, PDPage page, boolean subsamplingAllowed, RenderDestination destination, RenderingHints renderingHints, float imageDownscalingOptimizationThreshold) {
        this.renderer = renderer;
        this.page = page;
        this.subsamplingAllowed = subsamplingAllowed;
        this.destination = destination;
        this.renderingHints = renderingHints;
        this.imageDownscalingOptimizationThreshold = imageDownscalingOptimizationThreshold;
    }

    public PDPage getPage() {
        return this.page;
    }

    PDFRenderer getRenderer() {
        return this.renderer;
    }

    public boolean isSubsamplingAllowed() {
        return this.subsamplingAllowed;
    }

    public RenderDestination getDestination() {
        return this.destination;
    }

    public RenderingHints getRenderingHints() {
        return this.renderingHints;
    }

    public float getImageDownscalingOptimizationThreshold() {
        return this.imageDownscalingOptimizationThreshold;
    }
}
