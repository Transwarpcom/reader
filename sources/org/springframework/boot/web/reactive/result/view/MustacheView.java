package org.springframework.boot.web.reactive.result.view;

import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import org.springframework.core.io.Resource;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.result.view.AbstractUrlBasedView;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/web/reactive/result/view/MustacheView.class */
public class MustacheView extends AbstractUrlBasedView {
    private Mustache.Compiler compiler;
    private String charset;

    public void setCompiler(Mustache.Compiler compiler) {
        this.compiler = compiler;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public boolean checkResourceExists(Locale locale) throws Exception {
        return resolveResource() != null;
    }

    /* JADX WARN: Finally extract failed */
    protected Mono<Void> renderInternal(Map<String, Object> model, MediaType contentType, ServerWebExchange exchange) throws IOException {
        Resource resource = resolveResource();
        if (resource == null) {
            return Mono.error(new IllegalStateException("Could not find Mustache template with URL [" + getUrl() + "]"));
        }
        DataBuffer dataBuffer = exchange.getResponse().bufferFactory().allocateBuffer();
        try {
            Reader reader = getReader(resource);
            Throwable th = null;
            try {
                Template template = this.compiler.compile(reader);
                Charset charset = getCharset(contentType).orElse(getDefaultCharset());
                Writer writer = new OutputStreamWriter(dataBuffer.asOutputStream(), charset);
                Throwable th2 = null;
                try {
                    try {
                        template.execute(model, writer);
                        writer.flush();
                        if (writer != null) {
                            if (0 != 0) {
                                try {
                                    writer.close();
                                } catch (Throwable th3) {
                                    th2.addSuppressed(th3);
                                }
                            } else {
                                writer.close();
                            }
                        }
                        if (reader != null) {
                            if (0 != 0) {
                                try {
                                    reader.close();
                                } catch (Throwable th4) {
                                    th.addSuppressed(th4);
                                }
                            } else {
                                reader.close();
                            }
                        }
                        return exchange.getResponse().writeWith(Flux.just(dataBuffer));
                    } catch (Throwable th5) {
                        if (writer != null) {
                            if (th2 != null) {
                                try {
                                    writer.close();
                                } catch (Throwable th6) {
                                    th2.addSuppressed(th6);
                                }
                            } else {
                                writer.close();
                            }
                        }
                        throw th5;
                    }
                } finally {
                }
            } catch (Throwable th7) {
                if (reader != null) {
                    if (0 != 0) {
                        try {
                            reader.close();
                        } catch (Throwable th8) {
                            th.addSuppressed(th8);
                        }
                    } else {
                        reader.close();
                    }
                }
                throw th7;
            }
        } catch (Exception ex) {
            DataBufferUtils.release(dataBuffer);
            return Mono.error(ex);
        }
    }

    private Resource resolveResource() {
        Resource resource = getApplicationContext().getResource(getUrl());
        if (resource == null || !resource.exists()) {
            return null;
        }
        return resource;
    }

    private Reader getReader(Resource resource) throws IOException {
        if (this.charset != null) {
            return new InputStreamReader(resource.getInputStream(), this.charset);
        }
        return new InputStreamReader(resource.getInputStream());
    }

    private Optional<Charset> getCharset(MediaType mediaType) {
        return Optional.ofNullable(mediaType != null ? mediaType.getCharset() : null);
    }
}
