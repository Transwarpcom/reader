package org.springframework.boot.web.embedded.undertow;

import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.UndertowOptions;
import io.undertow.server.handlers.accesslog.AccessLogHandler;
import io.undertow.server.handlers.accesslog.DefaultAccessLogReceiver;
import io.vertx.core.net.NetServerOptions;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.springframework.boot.web.reactive.server.AbstractReactiveWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.UndertowHttpHandlerAdapter;
import org.springframework.util.Assert;
import org.xnio.OptionMap;
import org.xnio.Options;
import org.xnio.Xnio;
import org.xnio.XnioWorker;

/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/web/embedded/undertow/UndertowReactiveWebServerFactory.class */
public class UndertowReactiveWebServerFactory extends AbstractReactiveWebServerFactory implements ConfigurableUndertowWebServerFactory {
    private List<UndertowBuilderCustomizer> builderCustomizers;
    private List<UndertowDeploymentInfoCustomizer> deploymentInfoCustomizers;
    private Integer bufferSize;
    private Integer ioThreads;
    private Integer workerThreads;
    private Boolean directBuffers;
    private File accessLogDirectory;
    private String accessLogPattern;
    private String accessLogPrefix;
    private String accessLogSuffix;
    private boolean accessLogEnabled;
    private boolean accessLogRotate;
    private boolean useForwardHeaders;

    public UndertowReactiveWebServerFactory() {
        this.builderCustomizers = new ArrayList();
        this.deploymentInfoCustomizers = new ArrayList();
        this.accessLogEnabled = false;
        this.accessLogRotate = true;
    }

    public UndertowReactiveWebServerFactory(int port) {
        super(port);
        this.builderCustomizers = new ArrayList();
        this.deploymentInfoCustomizers = new ArrayList();
        this.accessLogEnabled = false;
        this.accessLogRotate = true;
    }

    @Override // org.springframework.boot.web.reactive.server.ReactiveWebServerFactory
    public WebServer getWebServer(HttpHandler httpHandler) throws NoSuchAlgorithmException, KeyManagementException {
        Undertow.Builder builder = createBuilder(getPort());
        Closeable closeable = configureHandler(builder, httpHandler);
        return new UndertowWebServer(builder, getPort() >= 0, closeable);
    }

    private Undertow.Builder createBuilder(int port) throws NoSuchAlgorithmException, KeyManagementException {
        Undertow.Builder builder = Undertow.builder();
        if (this.bufferSize != null) {
            builder.setBufferSize(this.bufferSize.intValue());
        }
        if (this.ioThreads != null) {
            builder.setIoThreads(this.ioThreads.intValue());
        }
        if (this.workerThreads != null) {
            builder.setWorkerThreads(this.workerThreads.intValue());
        }
        if (this.directBuffers != null) {
            builder.setDirectBuffers(this.directBuffers.booleanValue());
        }
        if (getSsl() != null && getSsl().isEnabled()) {
            customizeSsl(builder);
        } else {
            builder.addHttpListener(port, getListenAddress());
        }
        for (UndertowBuilderCustomizer customizer : this.builderCustomizers) {
            customizer.customize(builder);
        }
        return builder;
    }

    private Closeable configureHandler(Undertow.Builder builder, HttpHandler httpHandler) {
        io.undertow.server.HttpHandler handler = new UndertowHttpHandlerAdapter(httpHandler);
        if (this.useForwardHeaders) {
            handler = Handlers.proxyPeerAddress(handler);
        }
        io.undertow.server.HttpHandler handler2 = UndertowCompressionConfigurer.configureCompression(getCompression(), handler);
        Closeable closeable = null;
        if (isAccessLogEnabled()) {
            closeable = configureAccessLogHandler(builder, handler2);
        } else {
            builder.setHandler(handler2);
        }
        return closeable;
    }

    private Closeable configureAccessLogHandler(Undertow.Builder builder, io.undertow.server.HttpHandler handler) {
        try {
            createAccessLogDirectoryIfNecessary();
            XnioWorker worker = createWorker();
            String prefix = this.accessLogPrefix != null ? this.accessLogPrefix : "access_log.";
            DefaultAccessLogReceiver accessLogReceiver = new DefaultAccessLogReceiver(worker, this.accessLogDirectory, prefix, this.accessLogSuffix, this.accessLogRotate);
            String formatString = this.accessLogPattern != null ? this.accessLogPattern : "common";
            builder.setHandler(new AccessLogHandler(handler, accessLogReceiver, formatString, Undertow.class.getClassLoader()));
            return () -> {
                try {
                    accessLogReceiver.close();
                    worker.shutdown();
                    worker.awaitTermination(30L, TimeUnit.SECONDS);
                } catch (IOException ex) {
                    throw new IllegalStateException(ex);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            };
        } catch (IOException ex) {
            throw new IllegalStateException("Failed to create AccessLogHandler", ex);
        }
    }

    private void createAccessLogDirectoryIfNecessary() {
        Assert.state(this.accessLogDirectory != null, "Access log directory is not set");
        if (!this.accessLogDirectory.isDirectory() && !this.accessLogDirectory.mkdirs()) {
            throw new IllegalStateException("Failed to create access log directory '" + this.accessLogDirectory + OperatorName.SHOW_TEXT_LINE);
        }
    }

    private XnioWorker createWorker() throws IOException {
        Xnio xnio = Xnio.getInstance(Undertow.class.getClassLoader());
        return xnio.createWorker(OptionMap.builder().set(Options.THREAD_DAEMON, true).getMap());
    }

    private void customizeSsl(Undertow.Builder builder) throws NoSuchAlgorithmException, KeyManagementException {
        new SslBuilderCustomizer(getPort(), getAddress(), getSsl(), getSslStoreProvider()).customize(builder);
        if (getHttp2() != null) {
            builder.setServerOption(UndertowOptions.ENABLE_HTTP2, Boolean.valueOf(getHttp2().isEnabled()));
        }
    }

    private String getListenAddress() {
        if (getAddress() == null) {
            return NetServerOptions.DEFAULT_HOST;
        }
        return getAddress().getHostAddress();
    }

    public void setDeploymentInfoCustomizers(Collection<? extends UndertowDeploymentInfoCustomizer> customizers) {
        Assert.notNull(customizers, "Customizers must not be null");
        this.deploymentInfoCustomizers = new ArrayList(customizers);
    }

    public Collection<UndertowDeploymentInfoCustomizer> getDeploymentInfoCustomizers() {
        return this.deploymentInfoCustomizers;
    }

    @Override // org.springframework.boot.web.embedded.undertow.ConfigurableUndertowWebServerFactory
    public void addDeploymentInfoCustomizers(UndertowDeploymentInfoCustomizer... customizers) {
        Assert.notNull(customizers, "UndertowDeploymentInfoCustomizers must not be null");
        this.deploymentInfoCustomizers.addAll(Arrays.asList(customizers));
    }

    @Override // org.springframework.boot.web.embedded.undertow.ConfigurableUndertowWebServerFactory
    public void setAccessLogDirectory(File accessLogDirectory) {
        this.accessLogDirectory = accessLogDirectory;
    }

    @Override // org.springframework.boot.web.embedded.undertow.ConfigurableUndertowWebServerFactory
    public void setAccessLogPattern(String accessLogPattern) {
        this.accessLogPattern = accessLogPattern;
    }

    @Override // org.springframework.boot.web.embedded.undertow.ConfigurableUndertowWebServerFactory
    public void setAccessLogPrefix(String accessLogPrefix) {
        this.accessLogPrefix = accessLogPrefix;
    }

    @Override // org.springframework.boot.web.embedded.undertow.ConfigurableUndertowWebServerFactory
    public void setAccessLogSuffix(String accessLogSuffix) {
        this.accessLogSuffix = accessLogSuffix;
    }

    public boolean isAccessLogEnabled() {
        return this.accessLogEnabled;
    }

    @Override // org.springframework.boot.web.embedded.undertow.ConfigurableUndertowWebServerFactory
    public void setAccessLogEnabled(boolean accessLogEnabled) {
        this.accessLogEnabled = accessLogEnabled;
    }

    @Override // org.springframework.boot.web.embedded.undertow.ConfigurableUndertowWebServerFactory
    public void setAccessLogRotate(boolean accessLogRotate) {
        this.accessLogRotate = accessLogRotate;
    }

    protected final boolean isUseForwardHeaders() {
        return this.useForwardHeaders;
    }

    @Override // org.springframework.boot.web.embedded.undertow.ConfigurableUndertowWebServerFactory
    public void setUseForwardHeaders(boolean useForwardHeaders) {
        this.useForwardHeaders = useForwardHeaders;
    }

    @Override // org.springframework.boot.web.embedded.undertow.ConfigurableUndertowWebServerFactory
    public void setBufferSize(Integer bufferSize) {
        this.bufferSize = bufferSize;
    }

    @Override // org.springframework.boot.web.embedded.undertow.ConfigurableUndertowWebServerFactory
    public void setIoThreads(Integer ioThreads) {
        this.ioThreads = ioThreads;
    }

    @Override // org.springframework.boot.web.embedded.undertow.ConfigurableUndertowWebServerFactory
    public void setWorkerThreads(Integer workerThreads) {
        this.workerThreads = workerThreads;
    }

    @Override // org.springframework.boot.web.embedded.undertow.ConfigurableUndertowWebServerFactory
    public void setUseDirectBuffers(Boolean directBuffers) {
        this.directBuffers = directBuffers;
    }

    public void setBuilderCustomizers(Collection<? extends UndertowBuilderCustomizer> customizers) {
        Assert.notNull(customizers, "Customizers must not be null");
        this.builderCustomizers = new ArrayList(customizers);
    }

    public Collection<UndertowBuilderCustomizer> getBuilderCustomizers() {
        return this.builderCustomizers;
    }

    @Override // org.springframework.boot.web.embedded.undertow.ConfigurableUndertowWebServerFactory
    public void addBuilderCustomizers(UndertowBuilderCustomizer... customizers) {
        Assert.notNull(customizers, "Customizers must not be null");
        this.builderCustomizers.addAll(Arrays.asList(customizers));
    }
}
