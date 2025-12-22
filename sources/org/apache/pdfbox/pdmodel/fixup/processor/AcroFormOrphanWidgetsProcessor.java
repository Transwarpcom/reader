package org.apache.pdfbox.pdmodel.fixup.processor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.fontbox.ttf.TrueTypeFont;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.font.FontMapper;
import org.apache.pdfbox.pdmodel.font.FontMappers;
import org.apache.pdfbox.pdmodel.font.FontMapping;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationWidget;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAppearanceStream;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.apache.pdfbox.pdmodel.interactive.form.PDFieldFactory;
import org.apache.pdfbox.pdmodel.interactive.form.PDVariableText;
import org.slf4j.Marker;

/* loaded from: reader.jar:BOOT-INF/lib/pdfbox-2.0.27.jar:org/apache/pdfbox/pdmodel/fixup/processor/AcroFormOrphanWidgetsProcessor.class */
public class AcroFormOrphanWidgetsProcessor extends AbstractProcessor {
    private static final Log LOG = LogFactory.getLog((Class<?>) AcroFormOrphanWidgetsProcessor.class);

    public AcroFormOrphanWidgetsProcessor(PDDocument document) {
        super(document);
    }

    @Override // org.apache.pdfbox.pdmodel.fixup.processor.PDDocumentProcessor
    public void process() {
        PDAcroForm acroForm = this.document.getDocumentCatalog().getAcroForm(null);
        if (acroForm != null) {
            resolveFieldsFromWidgets(acroForm);
        }
    }

    private void resolveFieldsFromWidgets(PDAcroForm acroForm) {
        LOG.debug("rebuilding fields from widgets");
        PDResources resources = acroForm.getDefaultResources();
        if (resources == null) {
            LOG.debug("AcroForm default resources is null");
            return;
        }
        List<PDField> fields = new ArrayList<>();
        Map<String, PDField> nonTerminalFieldsMap = new HashMap<>();
        Iterator<PDPage> it = this.document.getPages().iterator();
        while (it.hasNext()) {
            PDPage page = it.next();
            try {
                handleAnnotations(acroForm, resources, fields, page.getAnnotations(), nonTerminalFieldsMap);
            } catch (IOException ioe) {
                LOG.debug("couldn't read annotations for page " + ioe.getMessage());
            }
        }
        acroForm.setFields(fields);
        Iterator<PDField> it2 = acroForm.getFieldTree().iterator();
        while (it2.hasNext()) {
            PDField field = it2.next();
            if (field instanceof PDVariableText) {
                ensureFontResources(resources, (PDVariableText) field);
            }
        }
    }

    private void handleAnnotations(PDAcroForm acroForm, PDResources acroFormResources, List<PDField> fields, List<PDAnnotation> annotations, Map<String, PDField> nonTerminalFieldsMap) {
        for (PDAnnotation annot : annotations) {
            if (annot instanceof PDAnnotationWidget) {
                addFontFromWidget(acroFormResources, annot);
                COSDictionary parent = annot.getCOSObject().getCOSDictionary(COSName.PARENT);
                if (parent != null) {
                    PDField resolvedField = resolveNonRootField(acroForm, parent, nonTerminalFieldsMap);
                    if (resolvedField != null) {
                        fields.add(resolvedField);
                    }
                } else {
                    fields.add(PDFieldFactory.createField(acroForm, annot.getCOSObject(), null));
                }
            }
        }
    }

    private void addFontFromWidget(PDResources acroFormResources, PDAnnotation annotation) {
        PDResources widgetResources;
        PDAppearanceStream normalAppearanceStream = annotation.getNormalAppearanceStream();
        if (normalAppearanceStream == null || (widgetResources = normalAppearanceStream.getResources()) == null) {
            return;
        }
        for (COSName fontName : widgetResources.getFontNames()) {
            if (!fontName.getName().startsWith(Marker.ANY_NON_NULL_MARKER)) {
                try {
                    if (acroFormResources.getFont(fontName) == null) {
                        acroFormResources.put(fontName, widgetResources.getFont(fontName));
                        LOG.debug("added font resource to AcroForm from widget for font name " + fontName.getName());
                    }
                } catch (IOException e) {
                    LOG.debug("unable to add font to AcroForm for font name " + fontName.getName());
                }
            } else {
                LOG.debug("font resource for widget was a subsetted font - ignored: " + fontName.getName());
            }
        }
    }

    private PDField resolveNonRootField(PDAcroForm acroForm, COSDictionary parent, Map<String, PDField> nonTerminalFieldsMap) {
        while (parent.containsKey(COSName.PARENT)) {
            parent = parent.getCOSDictionary(COSName.PARENT);
            if (parent == null) {
                return null;
            }
        }
        if (nonTerminalFieldsMap.get(parent.getString(COSName.T)) == null) {
            PDField field = PDFieldFactory.createField(acroForm, parent, null);
            if (field != null) {
                nonTerminalFieldsMap.put(field.getFullyQualifiedName(), field);
            }
            return field;
        }
        return null;
    }

    private void ensureFontResources(PDResources defaultResources, PDVariableText field) {
        String daString = field.getDefaultAppearance();
        if (daString.startsWith("/") && daString.length() > 1) {
            COSName fontName = COSName.getPDFName(daString.substring(1, daString.indexOf(" ")));
            try {
                if (defaultResources.getFont(fontName) == null) {
                    LOG.debug("trying to add missing font resource for field " + field.getFullyQualifiedName());
                    FontMapper mapper = FontMappers.instance();
                    FontMapping<TrueTypeFont> fontMapping = mapper.getTrueTypeFont(fontName.getName(), null);
                    if (fontMapping != null) {
                        PDType0Font pdFont = PDType0Font.load(this.document, (TrueTypeFont) fontMapping.getFont(), false);
                        LOG.debug("looked up font for " + fontName.getName() + " - found " + ((TrueTypeFont) fontMapping.getFont()).getName());
                        defaultResources.put(fontName, pdFont);
                    } else {
                        LOG.debug("no suitable font found for field " + field.getFullyQualifiedName() + " for font name " + fontName.getName());
                    }
                }
            } catch (IOException ioe) {
                LOG.debug("Unable to handle font resources for field " + field.getFullyQualifiedName() + ": " + ioe.getMessage());
            }
        }
    }
}
