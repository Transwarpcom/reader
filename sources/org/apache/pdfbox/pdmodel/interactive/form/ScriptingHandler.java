package org.apache.pdfbox.pdmodel.interactive.form;

import org.apache.pdfbox.pdmodel.interactive.action.PDActionJavaScript;

/* loaded from: reader.jar:BOOT-INF/lib/pdfbox-2.0.27.jar:org/apache/pdfbox/pdmodel/interactive/form/ScriptingHandler.class */
public interface ScriptingHandler {
    String keyboard(PDActionJavaScript pDActionJavaScript, String str);

    String format(PDActionJavaScript pDActionJavaScript, String str);

    boolean validate(PDActionJavaScript pDActionJavaScript, String str);

    String calculate(PDActionJavaScript pDActionJavaScript, String str);
}
