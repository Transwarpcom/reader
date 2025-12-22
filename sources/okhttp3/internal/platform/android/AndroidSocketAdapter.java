package okhttp3.internal.platform.android;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.internal.platform.AndroidPlatform;
import okhttp3.internal.platform.android.DeferredSocketAdapter;
import okhttp3.internal.platform.android.SocketAdapter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: AndroidSocketAdapter.kt */
@Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"��@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n��\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0016\u0018�� \u00182\u00020\u0001:\u0001\u0018B\u0015\u0012\u000e\u0010\u0002\u001a\n\u0012\u0006\b��\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J(\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00042\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012H\u0016J\u0012\u0010\u0014\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u000e\u001a\u00020\u0004H\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\u0010\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u000e\u001a\u00020\u0004H\u0016R\u0016\u0010\u0006\u001a\n \b*\u0004\u0018\u00010\u00070\u0007X\u0082\u0004¢\u0006\u0002\n��R\u0016\u0010\t\u001a\n \b*\u0004\u0018\u00010\u00070\u0007X\u0082\u0004¢\u0006\u0002\n��R\u0016\u0010\n\u001a\n \b*\u0004\u0018\u00010\u00070\u0007X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\u000b\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n��R\u0016\u0010\u0002\u001a\n\u0012\u0006\b��\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n��¨\u0006\u0019"}, d2 = {"Lokhttp3/internal/platform/android/AndroidSocketAdapter;", "Lokhttp3/internal/platform/android/SocketAdapter;", "sslSocketClass", "Ljava/lang/Class;", "Ljavax/net/ssl/SSLSocket;", "(Ljava/lang/Class;)V", "getAlpnSelectedProtocol", "Ljava/lang/reflect/Method;", "kotlin.jvm.PlatformType", "setAlpnProtocols", "setHostname", "setUseSessionTickets", "configureTlsExtensions", "", "sslSocket", "hostname", "", "protocols", "", "Lokhttp3/Protocol;", "getSelectedProtocol", "isSupported", "", "matchesSocket", "Companion", "okhttp"})
/* loaded from: reader.jar:BOOT-INF/lib/okhttp-4.9.1.jar:okhttp3/internal/platform/android/AndroidSocketAdapter.class */
public class AndroidSocketAdapter implements SocketAdapter {
    private final Method setUseSessionTickets;
    private final Method setHostname;
    private final Method getAlpnSelectedProtocol;
    private final Method setAlpnProtocols;
    private final Class<? super SSLSocket> sslSocketClass;
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static final DeferredSocketAdapter.Factory playProviderFactory = Companion.factory("com.google.android.gms.org.conscrypt");

    public AndroidSocketAdapter(@NotNull Class<? super SSLSocket> sslSocketClass) throws NoSuchMethodException, SecurityException {
        Intrinsics.checkNotNullParameter(sslSocketClass, "sslSocketClass");
        this.sslSocketClass = sslSocketClass;
        Method declaredMethod = this.sslSocketClass.getDeclaredMethod("setUseSessionTickets", Boolean.TYPE);
        Intrinsics.checkNotNullExpressionValue(declaredMethod, "sslSocketClass.getDeclar…:class.javaPrimitiveType)");
        this.setUseSessionTickets = declaredMethod;
        this.setHostname = this.sslSocketClass.getMethod("setHostname", String.class);
        this.getAlpnSelectedProtocol = this.sslSocketClass.getMethod("getAlpnSelectedProtocol", new Class[0]);
        this.setAlpnProtocols = this.sslSocketClass.getMethod("setAlpnProtocols", byte[].class);
    }

    @Override // okhttp3.internal.platform.android.SocketAdapter
    @Nullable
    public X509TrustManager trustManager(@NotNull SSLSocketFactory sslSocketFactory) {
        Intrinsics.checkNotNullParameter(sslSocketFactory, "sslSocketFactory");
        return SocketAdapter.DefaultImpls.trustManager(this, sslSocketFactory);
    }

    @Override // okhttp3.internal.platform.android.SocketAdapter
    public boolean matchesSocketFactory(@NotNull SSLSocketFactory sslSocketFactory) {
        Intrinsics.checkNotNullParameter(sslSocketFactory, "sslSocketFactory");
        return SocketAdapter.DefaultImpls.matchesSocketFactory(this, sslSocketFactory);
    }

    @Override // okhttp3.internal.platform.android.SocketAdapter
    public boolean isSupported() {
        return AndroidPlatform.Companion.isSupported();
    }

    @Override // okhttp3.internal.platform.android.SocketAdapter
    public boolean matchesSocket(@NotNull SSLSocket sslSocket) {
        Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
        return this.sslSocketClass.isInstance(sslSocket);
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockSplitter
        jadx.core.utils.exceptions.JadxRuntimeException: Unexpected missing predecessor for block: B:4:0x0014
        	at jadx.core.dex.visitors.blocks.BlockSplitter.addTempConnectionsForExcHandlers(BlockSplitter.java:280)
        	at jadx.core.dex.visitors.blocks.BlockSplitter.visit(BlockSplitter.java:79)
        */
    @Override // okhttp3.internal.platform.android.SocketAdapter
    public void configureTlsExtensions(@org.jetbrains.annotations.NotNull javax.net.ssl.SSLSocket r9, @org.jetbrains.annotations.Nullable java.lang.String r10, @org.jetbrains.annotations.NotNull java.util.List<? extends okhttp3.Protocol> r11) {
        /*
            r8 = this;
            r0 = r9
            java.lang.String r1 = "sslSocket"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
            r0 = r11
            java.lang.String r1 = "protocols"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
            r0 = r8
            r1 = r9
            boolean r0 = r0.matchesSocket(r1)
            if (r0 == 0) goto L76
        L15:
            r0 = r8
            java.lang.reflect.Method r0 = r0.setUseSessionTickets     // Catch: java.lang.IllegalAccessException -> L58 java.lang.reflect.InvocationTargetException -> L67
            r1 = r9
            r2 = 1
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch: java.lang.IllegalAccessException -> L58 java.lang.reflect.InvocationTargetException -> L67
            r3 = r2
            r4 = 0
            r5 = 1
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)     // Catch: java.lang.IllegalAccessException -> L58 java.lang.reflect.InvocationTargetException -> L67
            r3[r4] = r5     // Catch: java.lang.IllegalAccessException -> L58 java.lang.reflect.InvocationTargetException -> L67
            java.lang.Object r0 = r0.invoke(r1, r2)     // Catch: java.lang.IllegalAccessException -> L58 java.lang.reflect.InvocationTargetException -> L67
            r0 = r10
            if (r0 == 0) goto L3e
            r0 = r8
            java.lang.reflect.Method r0 = r0.setHostname     // Catch: java.lang.IllegalAccessException -> L58 java.lang.reflect.InvocationTargetException -> L67
            r1 = r9
            r2 = 1
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch: java.lang.IllegalAccessException -> L58 java.lang.reflect.InvocationTargetException -> L67
            r3 = r2
            r4 = 0
            r5 = r10
            r3[r4] = r5     // Catch: java.lang.IllegalAccessException -> L58 java.lang.reflect.InvocationTargetException -> L67
            java.lang.Object r0 = r0.invoke(r1, r2)     // Catch: java.lang.IllegalAccessException -> L58 java.lang.reflect.InvocationTargetException -> L67
        L3e:
            r0 = r8
            java.lang.reflect.Method r0 = r0.setAlpnProtocols     // Catch: java.lang.IllegalAccessException -> L58 java.lang.reflect.InvocationTargetException -> L67
            r1 = r9
            r2 = 1
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch: java.lang.IllegalAccessException -> L58 java.lang.reflect.InvocationTargetException -> L67
            r3 = r2
            r4 = 0
            okhttp3.internal.platform.Platform$Companion r5 = okhttp3.internal.platform.Platform.Companion     // Catch: java.lang.IllegalAccessException -> L58 java.lang.reflect.InvocationTargetException -> L67
            r6 = r11
            byte[] r5 = r5.concatLengthPrefixed(r6)     // Catch: java.lang.IllegalAccessException -> L58 java.lang.reflect.InvocationTargetException -> L67
            r3[r4] = r5     // Catch: java.lang.IllegalAccessException -> L58 java.lang.reflect.InvocationTargetException -> L67
            java.lang.Object r0 = r0.invoke(r1, r2)     // Catch: java.lang.IllegalAccessException -> L58 java.lang.reflect.InvocationTargetException -> L67
            goto L76
        L58:
            r12 = move-exception
            java.lang.AssertionError r0 = new java.lang.AssertionError
            r1 = r0
            r2 = r12
            r1.<init>(r2)
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            throw r0
        L67:
            r12 = move-exception
            java.lang.AssertionError r0 = new java.lang.AssertionError
            r1 = r0
            r2 = r12
            r1.<init>(r2)
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            throw r0
        L76:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.platform.android.AndroidSocketAdapter.configureTlsExtensions(javax.net.ssl.SSLSocket, java.lang.String, java.util.List):void");
    }

    @Override // okhttp3.internal.platform.android.SocketAdapter
    @Nullable
    public String getSelectedProtocol(@NotNull SSLSocket sslSocket) {
        String str;
        String str2;
        Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
        if (!matchesSocket(sslSocket)) {
            return null;
        }
        try {
            byte[] alpnResult = (byte[]) this.getAlpnSelectedProtocol.invoke(sslSocket, new Object[0]);
            if (alpnResult != null) {
                Charset charset = StandardCharsets.UTF_8;
                Intrinsics.checkNotNullExpressionValue(charset, "StandardCharsets.UTF_8");
                str2 = new String(alpnResult, charset);
            } else {
                str2 = null;
            }
            str = str2;
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (NullPointerException e2) {
            if (Intrinsics.areEqual(e2.getMessage(), "ssl == null")) {
                str = (String) null;
            } else {
                throw e2;
            }
        } catch (InvocationTargetException e3) {
            throw new AssertionError(e3);
        }
        return str;
    }

    /* compiled from: AndroidSocketAdapter.kt */
    @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"��,\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n��\b\u0086\u0003\u0018��2\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0007\u001a\u00020\b2\u000e\u0010\t\u001a\n\u0012\u0006\b��\u0012\u00020\u000b0\nH\u0002J\u000e\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000eR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n��\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000f"}, d2 = {"Lokhttp3/internal/platform/android/AndroidSocketAdapter$Companion;", "", "()V", "playProviderFactory", "Lokhttp3/internal/platform/android/DeferredSocketAdapter$Factory;", "getPlayProviderFactory", "()Lokhttp3/internal/platform/android/DeferredSocketAdapter$Factory;", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "Lokhttp3/internal/platform/android/AndroidSocketAdapter;", "actualSSLSocketClass", "Ljava/lang/Class;", "Ljavax/net/ssl/SSLSocket;", "factory", "packageName", "", "okhttp"})
    /* loaded from: reader.jar:BOOT-INF/lib/okhttp-4.9.1.jar:okhttp3/internal/platform/android/AndroidSocketAdapter$Companion.class */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        @NotNull
        public final DeferredSocketAdapter.Factory getPlayProviderFactory() {
            return AndroidSocketAdapter.playProviderFactory;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final AndroidSocketAdapter build(Class<? super SSLSocket> cls) {
            Class possibleClass = cls;
            while (possibleClass != null && (!Intrinsics.areEqual(possibleClass.getSimpleName(), "OpenSSLSocketImpl"))) {
                possibleClass = possibleClass.getSuperclass();
                if (possibleClass == null) {
                    throw new AssertionError("No OpenSSLSocketImpl superclass of socket of type " + cls);
                }
            }
            Class cls2 = possibleClass;
            Intrinsics.checkNotNull(cls2);
            return new AndroidSocketAdapter(cls2);
        }

        @NotNull
        public final DeferredSocketAdapter.Factory factory(@NotNull final String packageName) {
            Intrinsics.checkNotNullParameter(packageName, "packageName");
            return new DeferredSocketAdapter.Factory() { // from class: okhttp3.internal.platform.android.AndroidSocketAdapter$Companion$factory$1
                @Override // okhttp3.internal.platform.android.DeferredSocketAdapter.Factory
                public boolean matchesSocket(@NotNull SSLSocket sslSocket) {
                    Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
                    String name = sslSocket.getClass().getName();
                    Intrinsics.checkNotNullExpressionValue(name, "sslSocket.javaClass.name");
                    return StringsKt.startsWith$default(name, packageName + '.', false, 2, (Object) null);
                }

                @Override // okhttp3.internal.platform.android.DeferredSocketAdapter.Factory
                @NotNull
                public SocketAdapter create(@NotNull SSLSocket sslSocket) {
                    Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
                    return AndroidSocketAdapter.Companion.build(sslSocket.getClass());
                }
            };
        }
    }
}
