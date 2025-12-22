package io.vertx.ext.web.handler.impl;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.LanguageHeader;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.common.template.TemplateEngine;
import io.vertx.ext.web.handler.TemplateHandler;
import io.vertx.ext.web.impl.Utils;
import java.util.Iterator;
import java.util.Locale;
import me.ag2s.epublib.epub.NCXDocumentV3;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/impl/TemplateHandlerImpl.class */
public class TemplateHandlerImpl implements TemplateHandler {
    private final TemplateEngine engine;
    private final String templateDirectory;
    private final String contentType;
    private String indexTemplate;

    public TemplateHandlerImpl(TemplateEngine engine, String templateDirectory, String contentType) {
        this.engine = engine;
        this.templateDirectory = (templateDirectory == null || templateDirectory.isEmpty()) ? "." : templateDirectory;
        this.contentType = contentType;
        this.indexTemplate = "index";
    }

    @Override // io.vertx.core.Handler
    public void handle(RoutingContext context) {
        String file = Utils.pathOffset(context.normalisedPath(), context);
        if (file.endsWith("/") && null != this.indexTemplate) {
            file = file + this.indexTemplate;
        }
        if (this.templateDirectory == null || "".equals(this.templateDirectory)) {
            file = file.substring(1);
        }
        if (!context.data().containsKey(NCXDocumentV3.XHTMLAttributes.lang)) {
            Iterator<LanguageHeader> it = context.acceptableLanguages().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                LanguageHeader acceptableLocale = it.next();
                try {
                    Locale.forLanguageTag(acceptableLocale.value());
                    context.data().put(NCXDocumentV3.XHTMLAttributes.lang, acceptableLocale.value());
                    break;
                } catch (RuntimeException e) {
                }
            }
        }
        this.engine.render(new JsonObject(context.data()), this.templateDirectory + file, res -> {
            if (res.succeeded()) {
                context.response().putHeader(HttpHeaders.CONTENT_TYPE, this.contentType).end((Buffer) res.result());
            } else {
                context.fail(res.cause());
            }
        });
    }

    @Override // io.vertx.ext.web.handler.TemplateHandler
    public TemplateHandler setIndexTemplate(String indexTemplate) {
        this.indexTemplate = indexTemplate;
        return this;
    }
}
