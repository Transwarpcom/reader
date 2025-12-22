package io.netty.handler.ssl;

import io.netty.handler.ssl.JdkApplicationProtocolNegotiator;
import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.function.BiFunction;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLEngineResult;
import javax.net.ssl.SSLException;

/* loaded from: reader.jar:BOOT-INF/lib/netty-handler-4.1.42.Final.jar:io/netty/handler/ssl/Java9SslEngine.class */
final class Java9SslEngine extends JdkSslEngine {
    private final JdkApplicationProtocolNegotiator.ProtocolSelectionListener selectionListener;
    private final AlpnSelector alpnSelector;
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !Java9SslEngine.class.desiredAssertionStatus();
    }

    /* loaded from: reader.jar:BOOT-INF/lib/netty-handler-4.1.42.Final.jar:io/netty/handler/ssl/Java9SslEngine$AlpnSelector.class */
    private final class AlpnSelector implements BiFunction<SSLEngine, List<String>, String> {
        private final JdkApplicationProtocolNegotiator.ProtocolSelector selector;
        private boolean called;
        static final /* synthetic */ boolean $assertionsDisabled;

        static {
            $assertionsDisabled = !Java9SslEngine.class.desiredAssertionStatus();
        }

        AlpnSelector(JdkApplicationProtocolNegotiator.ProtocolSelector selector) {
            this.selector = selector;
        }

        @Override // java.util.function.BiFunction
        public String apply(SSLEngine sslEngine, List<String> strings) {
            if (!$assertionsDisabled && this.called) {
                throw new AssertionError();
            }
            this.called = true;
            try {
                String selected = this.selector.select(strings);
                return selected == null ? "" : selected;
            } catch (Exception e) {
                return null;
            }
        }

        void checkUnsupported() {
            if (this.called) {
                return;
            }
            String protocol = Java9SslEngine.this.getApplicationProtocol();
            if (!$assertionsDisabled && protocol == null) {
                throw new AssertionError();
            }
            if (protocol.isEmpty()) {
                this.selector.unsupported();
            }
        }
    }

    Java9SslEngine(SSLEngine engine, JdkApplicationProtocolNegotiator applicationNegotiator, boolean isServer) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        super(engine);
        if (isServer) {
            this.selectionListener = null;
            this.alpnSelector = new AlpnSelector(applicationNegotiator.protocolSelectorFactory().newSelector(this, new LinkedHashSet(applicationNegotiator.protocols())));
            Java9SslUtils.setHandshakeApplicationProtocolSelector(engine, this.alpnSelector);
        } else {
            this.selectionListener = applicationNegotiator.protocolListenerFactory().newListener(this, applicationNegotiator.protocols());
            this.alpnSelector = null;
            Java9SslUtils.setApplicationProtocols(engine, applicationNegotiator.protocols());
        }
    }

    private SSLEngineResult verifyProtocolSelection(SSLEngineResult result) throws SSLException {
        if (result.getHandshakeStatus() == SSLEngineResult.HandshakeStatus.FINISHED) {
            if (this.alpnSelector == null) {
                try {
                    String protocol = getApplicationProtocol();
                    if (!$assertionsDisabled && protocol == null) {
                        throw new AssertionError();
                    }
                    if (protocol.isEmpty()) {
                        this.selectionListener.unsupported();
                    } else {
                        this.selectionListener.selected(protocol);
                    }
                } catch (Throwable e) {
                    throw SslUtils.toSSLHandshakeException(e);
                }
            } else {
                if (!$assertionsDisabled && this.selectionListener != null) {
                    throw new AssertionError();
                }
                this.alpnSelector.checkUnsupported();
            }
        }
        return result;
    }

    @Override // io.netty.handler.ssl.JdkSslEngine, javax.net.ssl.SSLEngine
    public SSLEngineResult wrap(ByteBuffer src, ByteBuffer dst) throws SSLException {
        return verifyProtocolSelection(super.wrap(src, dst));
    }

    @Override // io.netty.handler.ssl.JdkSslEngine, javax.net.ssl.SSLEngine
    public SSLEngineResult wrap(ByteBuffer[] srcs, ByteBuffer dst) throws SSLException {
        return verifyProtocolSelection(super.wrap(srcs, dst));
    }

    @Override // io.netty.handler.ssl.JdkSslEngine, javax.net.ssl.SSLEngine
    public SSLEngineResult wrap(ByteBuffer[] srcs, int offset, int len, ByteBuffer dst) throws SSLException {
        return verifyProtocolSelection(super.wrap(srcs, offset, len, dst));
    }

    @Override // io.netty.handler.ssl.JdkSslEngine, javax.net.ssl.SSLEngine
    public SSLEngineResult unwrap(ByteBuffer src, ByteBuffer dst) throws SSLException {
        return verifyProtocolSelection(super.unwrap(src, dst));
    }

    @Override // io.netty.handler.ssl.JdkSslEngine, javax.net.ssl.SSLEngine
    public SSLEngineResult unwrap(ByteBuffer src, ByteBuffer[] dsts) throws SSLException {
        return verifyProtocolSelection(super.unwrap(src, dsts));
    }

    @Override // io.netty.handler.ssl.JdkSslEngine, javax.net.ssl.SSLEngine
    public SSLEngineResult unwrap(ByteBuffer src, ByteBuffer[] dst, int offset, int len) throws SSLException {
        return verifyProtocolSelection(super.unwrap(src, dst, offset, len));
    }

    @Override // io.netty.handler.ssl.JdkSslEngine
    void setNegotiatedApplicationProtocol(String applicationProtocol) {
    }

    @Override // io.netty.handler.ssl.JdkSslEngine, io.netty.handler.ssl.ApplicationProtocolAccessor
    public String getNegotiatedApplicationProtocol() {
        String protocol = getApplicationProtocol();
        if (protocol != null) {
            if (protocol.isEmpty()) {
                return null;
            }
            return protocol;
        }
        return protocol;
    }

    @Override // javax.net.ssl.SSLEngine
    public String getApplicationProtocol() {
        return Java9SslUtils.getApplicationProtocol(getWrappedEngine());
    }

    @Override // javax.net.ssl.SSLEngine
    public String getHandshakeApplicationProtocol() {
        return Java9SslUtils.getHandshakeApplicationProtocol(getWrappedEngine());
    }

    @Override // javax.net.ssl.SSLEngine
    public void setHandshakeApplicationProtocolSelector(BiFunction<SSLEngine, List<String>, String> selector) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Java9SslUtils.setHandshakeApplicationProtocolSelector(getWrappedEngine(), selector);
    }

    @Override // javax.net.ssl.SSLEngine
    public BiFunction<SSLEngine, List<String>, String> getHandshakeApplicationProtocolSelector() {
        return Java9SslUtils.getHandshakeApplicationProtocolSelector(getWrappedEngine());
    }
}
