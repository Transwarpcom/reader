package okhttp3;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.legado.app.data.entities.Book;
import java.security.Principal;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.net.ssl.SSLPeerUnverifiedException;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.text.StringsKt;
import okhttp3.internal.HostnamesKt;
import okhttp3.internal.tls.CertificateChainCleaner;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.jmx.export.naming.IdentityNamingStrategy;

/* compiled from: CertificatePinner.kt */
@Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"��T\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\"\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\u0018�� \"2\u00020\u0001:\u0003!\"#B!\b��\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J)\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0012\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\u0011H��¢\u0006\u0002\b\u0014J)\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0012\u0010\u0015\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00170\u0016\"\u00020\u0017H\u0007¢\u0006\u0002\u0010\u0018J\u001c\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0012J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\u0014\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00040\u00122\u0006\u0010\u000e\u001a\u00020\u000fJ\b\u0010\u001d\u001a\u00020\u001eH\u0016J\u0015\u0010\u001f\u001a\u00020��2\u0006\u0010\u0005\u001a\u00020\u0006H��¢\u0006\u0002\b R\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0080\u0004¢\u0006\b\n��\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000b¨\u0006$"}, d2 = {"Lokhttp3/CertificatePinner;", "", "pins", "", "Lokhttp3/CertificatePinner$Pin;", "certificateChainCleaner", "Lokhttp3/internal/tls/CertificateChainCleaner;", "(Ljava/util/Set;Lokhttp3/internal/tls/CertificateChainCleaner;)V", "getCertificateChainCleaner$okhttp", "()Lokhttp3/internal/tls/CertificateChainCleaner;", "getPins", "()Ljava/util/Set;", "check", "", "hostname", "", "cleanedPeerCertificatesFn", "Lkotlin/Function0;", "", "Ljava/security/cert/X509Certificate;", "check$okhttp", "peerCertificates", "", "Ljava/security/cert/Certificate;", "(Ljava/lang/String;[Ljava/security/cert/Certificate;)V", "equals", "", "other", "findMatchingPins", IdentityNamingStrategy.HASH_CODE_KEY, "", "withCertificateChainCleaner", "withCertificateChainCleaner$okhttp", "Builder", "Companion", "Pin", "okhttp"})
/* loaded from: reader.jar:BOOT-INF/lib/okhttp-4.9.1.jar:okhttp3/CertificatePinner.class */
public final class CertificatePinner {

    @NotNull
    private final Set<Pin> pins;

    @Nullable
    private final CertificateChainCleaner certificateChainCleaner;
    public static final Companion Companion = new Companion(null);

    @JvmField
    @NotNull
    public static final CertificatePinner DEFAULT = new Builder().build();

    @JvmStatic
    @NotNull
    public static final ByteString sha1Hash(@NotNull X509Certificate $this$sha1Hash) {
        return Companion.sha1Hash($this$sha1Hash);
    }

    @JvmStatic
    @NotNull
    public static final ByteString sha256Hash(@NotNull X509Certificate $this$sha256Hash) {
        return Companion.sha256Hash($this$sha256Hash);
    }

    @JvmStatic
    @NotNull
    public static final String pin(@NotNull Certificate certificate) {
        return Companion.pin(certificate);
    }

    public CertificatePinner(@NotNull Set<Pin> pins, @Nullable CertificateChainCleaner certificateChainCleaner) {
        Intrinsics.checkNotNullParameter(pins, "pins");
        this.pins = pins;
        this.certificateChainCleaner = certificateChainCleaner;
    }

    @NotNull
    public final Set<Pin> getPins() {
        return this.pins;
    }

    @Nullable
    public final CertificateChainCleaner getCertificateChainCleaner$okhttp() {
        return this.certificateChainCleaner;
    }

    public /* synthetic */ CertificatePinner(Set set, CertificateChainCleaner certificateChainCleaner, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(set, (i & 2) != 0 ? (CertificateChainCleaner) null : certificateChainCleaner);
    }

    public final void check(@NotNull final String hostname, @NotNull final List<? extends Certificate> peerCertificates) throws SSLPeerUnverifiedException {
        Intrinsics.checkNotNullParameter(hostname, "hostname");
        Intrinsics.checkNotNullParameter(peerCertificates, "peerCertificates");
        check$okhttp(hostname, new Function0<List<? extends X509Certificate>>() { // from class: okhttp3.CertificatePinner.check.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Removed duplicated region for block: B:7:0x001d  */
            @Override // kotlin.jvm.functions.Function0
            @org.jetbrains.annotations.NotNull
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final java.util.List<? extends java.security.cert.X509Certificate> invoke() throws javax.net.ssl.SSLPeerUnverifiedException {
                /*
                    r5 = this;
                    r0 = r5
                    okhttp3.CertificatePinner r0 = okhttp3.CertificatePinner.this
                    okhttp3.internal.tls.CertificateChainCleaner r0 = r0.getCertificateChainCleaner$okhttp()
                    r1 = r0
                    if (r1 == 0) goto L1d
                    r1 = r5
                    java.util.List r1 = r5
                    r2 = r5
                    java.lang.String r2 = r6
                    java.util.List r0 = r0.clean(r1, r2)
                    r1 = r0
                    if (r1 == 0) goto L1d
                    goto L22
                L1d:
                    r0 = r5
                    java.util.List r0 = r5
                L22:
                    java.lang.Iterable r0 = (java.lang.Iterable) r0
                    r6 = r0
                    r0 = 0
                    r7 = r0
                    r0 = r6
                    r8 = r0
                    java.util.ArrayList r0 = new java.util.ArrayList
                    r1 = r0
                    r2 = r6
                    r3 = 10
                    int r2 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r2, r3)
                    r1.<init>(r2)
                    java.util.Collection r0 = (java.util.Collection) r0
                    r9 = r0
                    r0 = 0
                    r10 = r0
                    r0 = r8
                    java.util.Iterator r0 = r0.iterator()
                    r11 = r0
                L47:
                    r0 = r11
                    boolean r0 = r0.hasNext()
                    if (r0 == 0) goto L8a
                    r0 = r11
                    java.lang.Object r0 = r0.next()
                    r12 = r0
                    r0 = r9
                    r1 = r12
                    java.security.cert.Certificate r1 = (java.security.cert.Certificate) r1
                    r13 = r1
                    r15 = r0
                    r0 = 0
                    r14 = r0
                    r0 = r13
                    r1 = r0
                    if (r1 != 0) goto L78
                    java.lang.NullPointerException r1 = new java.lang.NullPointerException
                    r2 = r1
                    java.lang.String r3 = "null cannot be cast to non-null type java.security.cert.X509Certificate"
                    r2.<init>(r3)
                    throw r1
                L78:
                    java.security.cert.X509Certificate r0 = (java.security.cert.X509Certificate) r0
                    r16 = r0
                    r0 = r15
                    r1 = r16
                    boolean r0 = r0.add(r1)
                    goto L47
                L8a:
                    r0 = r9
                    java.util.List r0 = (java.util.List) r0
                    return r0
                */
                throw new UnsupportedOperationException("Method not decompiled: okhttp3.CertificatePinner.AnonymousClass1.invoke():java.util.List");
            }
        });
    }

    public final void check$okhttp(@NotNull String hostname, @NotNull Function0<? extends List<? extends X509Certificate>> cleanedPeerCertificatesFn) throws SSLPeerUnverifiedException {
        Intrinsics.checkNotNullParameter(hostname, "hostname");
        Intrinsics.checkNotNullParameter(cleanedPeerCertificatesFn, "cleanedPeerCertificatesFn");
        List pins = findMatchingPins(hostname);
        if (pins.isEmpty()) {
            return;
        }
        List peerCertificates = cleanedPeerCertificatesFn.invoke();
        for (X509Certificate peerCertificate : peerCertificates) {
            ByteString sha1 = (ByteString) null;
            ByteString sha256 = (ByteString) null;
            for (Pin pin : pins) {
                String hashAlgorithm = pin.getHashAlgorithm();
                switch (hashAlgorithm.hashCode()) {
                    case -903629273:
                        if (hashAlgorithm.equals("sha256")) {
                            if (sha256 == null) {
                                sha256 = Companion.sha256Hash(peerCertificate);
                            }
                            if (Intrinsics.areEqual(pin.getHash(), sha256)) {
                                return;
                            }
                        } else {
                            throw new AssertionError("unsupported hashAlgorithm: " + pin.getHashAlgorithm());
                        }
                    case 3528965:
                        if (hashAlgorithm.equals("sha1")) {
                            if (sha1 == null) {
                                sha1 = Companion.sha1Hash(peerCertificate);
                            }
                            if (Intrinsics.areEqual(pin.getHash(), sha1)) {
                                return;
                            }
                        } else {
                            throw new AssertionError("unsupported hashAlgorithm: " + pin.getHashAlgorithm());
                        }
                    default:
                        throw new AssertionError("unsupported hashAlgorithm: " + pin.getHashAlgorithm());
                }
            }
        }
        StringBuilder $this$buildString = new StringBuilder();
        $this$buildString.append("Certificate pinning failure!");
        $this$buildString.append("\n  Peer certificate chain:");
        for (X509Certificate element : peerCertificates) {
            $this$buildString.append("\n    ");
            $this$buildString.append(Companion.pin(element));
            $this$buildString.append(": ");
            Principal subjectDN = element.getSubjectDN();
            Intrinsics.checkNotNullExpressionValue(subjectDN, "element.subjectDN");
            $this$buildString.append(subjectDN.getName());
        }
        $this$buildString.append("\n  Pinned certificates for ");
        $this$buildString.append(hostname);
        $this$buildString.append(":");
        for (Pin pin2 : pins) {
            $this$buildString.append("\n    ");
            $this$buildString.append(pin2);
        }
        String message = $this$buildString.toString();
        Intrinsics.checkNotNullExpressionValue(message, "StringBuilder().apply(builderAction).toString()");
        throw new SSLPeerUnverifiedException(message);
    }

    @Deprecated(message = "replaced with {@link #check(String, List)}.", replaceWith = @ReplaceWith(imports = {}, expression = "check(hostname, peerCertificates.toList())"))
    public final void check(@NotNull String hostname, @NotNull Certificate... peerCertificates) throws SSLPeerUnverifiedException {
        Intrinsics.checkNotNullParameter(hostname, "hostname");
        Intrinsics.checkNotNullParameter(peerCertificates, "peerCertificates");
        check(hostname, ArraysKt.toList(peerCertificates));
    }

    @NotNull
    public final List<Pin> findMatchingPins(@NotNull String hostname) {
        Intrinsics.checkNotNullParameter(hostname, "hostname");
        Iterable $this$filterList$iv = this.pins;
        List result$iv = CollectionsKt.emptyList();
        for (Object i$iv : $this$filterList$iv) {
            Pin $this$filterList = (Pin) i$iv;
            if ($this$filterList.matchesHostname(hostname)) {
                if (result$iv.isEmpty()) {
                    result$iv = new ArrayList();
                }
                List list = result$iv;
                if (list == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.MutableList<T>");
                }
                TypeIntrinsics.asMutableList(list).add(i$iv);
            }
        }
        return result$iv;
    }

    @NotNull
    public final CertificatePinner withCertificateChainCleaner$okhttp(@NotNull CertificateChainCleaner certificateChainCleaner) {
        Intrinsics.checkNotNullParameter(certificateChainCleaner, "certificateChainCleaner");
        if (Intrinsics.areEqual(this.certificateChainCleaner, certificateChainCleaner)) {
            return this;
        }
        return new CertificatePinner(this.pins, certificateChainCleaner);
    }

    public boolean equals(@Nullable Object other) {
        return (other instanceof CertificatePinner) && Intrinsics.areEqual(((CertificatePinner) other).pins, this.pins) && Intrinsics.areEqual(((CertificatePinner) other).certificateChainCleaner, this.certificateChainCleaner);
    }

    public int hashCode() {
        int result = (41 * 37) + this.pins.hashCode();
        int i = 41 * result;
        CertificateChainCleaner certificateChainCleaner = this.certificateChainCleaner;
        int result2 = i + (certificateChainCleaner != null ? certificateChainCleaner.hashCode() : 0);
        return result2;
    }

    /* compiled from: CertificatePinner.kt */
    @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"��2\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018��2\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\u000e\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u0015J\u000e\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u0003J\b\u0010\u0018\u001a\u00020\u0003H\u0016R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n��\u001a\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\r\u0010\f¨\u0006\u0019"}, d2 = {"Lokhttp3/CertificatePinner$Pin;", "", "pattern", "", "pin", "(Ljava/lang/String;Ljava/lang/String;)V", "hash", "Lokio/ByteString;", "getHash", "()Lokio/ByteString;", "hashAlgorithm", "getHashAlgorithm", "()Ljava/lang/String;", "getPattern", "equals", "", "other", IdentityNamingStrategy.HASH_CODE_KEY, "", "matchesCertificate", "certificate", "Ljava/security/cert/X509Certificate;", "matchesHostname", "hostname", "toString", "okhttp"})
    /* loaded from: reader.jar:BOOT-INF/lib/okhttp-4.9.1.jar:okhttp3/CertificatePinner$Pin.class */
    public static final class Pin {

        @NotNull
        private final String pattern;

        @NotNull
        private final String hashAlgorithm;

        @NotNull
        private final ByteString hash;

        public Pin(@NotNull String pattern, @NotNull String pin) {
            Intrinsics.checkNotNullParameter(pattern, "pattern");
            Intrinsics.checkNotNullParameter(pin, "pin");
            if (!((StringsKt.startsWith$default(pattern, "*.", false, 2, (Object) null) && StringsKt.indexOf$default((CharSequence) pattern, "*", 1, false, 4, (Object) null) == -1) || (StringsKt.startsWith$default(pattern, "**.", false, 2, (Object) null) && StringsKt.indexOf$default((CharSequence) pattern, "*", 2, false, 4, (Object) null) == -1) || StringsKt.indexOf$default((CharSequence) pattern, "*", 0, false, 6, (Object) null) == -1)) {
                throw new IllegalArgumentException(("Unexpected pattern: " + pattern).toString());
            }
            String canonicalHost = HostnamesKt.toCanonicalHost(pattern);
            if (canonicalHost == null) {
                throw new IllegalArgumentException("Invalid pattern: " + pattern);
            }
            this.pattern = canonicalHost;
            if (StringsKt.startsWith$default(pin, "sha1/", false, 2, (Object) null)) {
                this.hashAlgorithm = "sha1";
                ByteString.Companion companion = ByteString.Companion;
                String strSubstring = pin.substring("sha1/".length());
                Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.String).substring(startIndex)");
                ByteString byteStringDecodeBase64 = companion.decodeBase64(strSubstring);
                if (byteStringDecodeBase64 == null) {
                    throw new IllegalArgumentException("Invalid pin hash: " + pin);
                }
                this.hash = byteStringDecodeBase64;
                return;
            }
            if (StringsKt.startsWith$default(pin, "sha256/", false, 2, (Object) null)) {
                this.hashAlgorithm = "sha256";
                ByteString.Companion companion2 = ByteString.Companion;
                String strSubstring2 = pin.substring("sha256/".length());
                Intrinsics.checkNotNullExpressionValue(strSubstring2, "(this as java.lang.String).substring(startIndex)");
                ByteString byteStringDecodeBase642 = companion2.decodeBase64(strSubstring2);
                if (byteStringDecodeBase642 == null) {
                    throw new IllegalArgumentException("Invalid pin hash: " + pin);
                }
                this.hash = byteStringDecodeBase642;
                return;
            }
            throw new IllegalArgumentException("pins must start with 'sha256/' or 'sha1/': " + pin);
        }

        @NotNull
        public final String getPattern() {
            return this.pattern;
        }

        @NotNull
        public final String getHashAlgorithm() {
            return this.hashAlgorithm;
        }

        @NotNull
        public final ByteString getHash() {
            return this.hash;
        }

        public final boolean matchesHostname(@NotNull String hostname) {
            Intrinsics.checkNotNullParameter(hostname, "hostname");
            if (StringsKt.startsWith$default(this.pattern, "**.", false, 2, (Object) null)) {
                int suffixLength = this.pattern.length() - 3;
                int prefixLength = hostname.length() - suffixLength;
                return StringsKt.regionMatches$default(hostname, hostname.length() - suffixLength, this.pattern, 3, suffixLength, false, 16, (Object) null) && (prefixLength == 0 || hostname.charAt(prefixLength - 1) == '.');
            }
            if (StringsKt.startsWith$default(this.pattern, "*.", false, 2, (Object) null)) {
                int suffixLength2 = this.pattern.length() - 1;
                return StringsKt.regionMatches$default(hostname, hostname.length() - suffixLength2, this.pattern, 1, suffixLength2, false, 16, (Object) null) && StringsKt.lastIndexOf$default((CharSequence) hostname, '.', (hostname.length() - suffixLength2) - 1, false, 4, (Object) null) == -1;
            }
            return Intrinsics.areEqual(hostname, this.pattern);
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        public final boolean matchesCertificate(@NotNull X509Certificate certificate) {
            Intrinsics.checkNotNullParameter(certificate, "certificate");
            String str = this.hashAlgorithm;
            switch (str.hashCode()) {
                case -903629273:
                    if (str.equals("sha256")) {
                        return Intrinsics.areEqual(this.hash, CertificatePinner.Companion.sha256Hash(certificate));
                    }
                    return false;
                case 3528965:
                    if (str.equals("sha1")) {
                        return Intrinsics.areEqual(this.hash, CertificatePinner.Companion.sha1Hash(certificate));
                    }
                    return false;
                default:
                    return false;
            }
        }

        @NotNull
        public String toString() {
            return this.hashAlgorithm + '/' + this.hash.base64();
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            return (!(other instanceof Pin) || (Intrinsics.areEqual(this.pattern, ((Pin) other).pattern) ^ true) || (Intrinsics.areEqual(this.hashAlgorithm, ((Pin) other).hashAlgorithm) ^ true) || (Intrinsics.areEqual(this.hash, ((Pin) other).hash) ^ true)) ? false : true;
        }

        public int hashCode() {
            int result = this.pattern.hashCode();
            return (31 * ((31 * result) + this.hashAlgorithm.hashCode())) + this.hash.hashCode();
        }
    }

    /* compiled from: CertificatePinner.kt */
    @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"��*\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\u0018��2\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J'\u0010\b\u001a\u00020��2\u0006\u0010\t\u001a\u00020\n2\u0012\u0010\u0003\u001a\n\u0012\u0006\b\u0001\u0012\u00020\n0\u000b\"\u00020\n¢\u0006\u0002\u0010\fJ\u0006\u0010\r\u001a\u00020\u000eR\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000f"}, d2 = {"Lokhttp3/CertificatePinner$Builder;", "", "()V", "pins", "", "Lokhttp3/CertificatePinner$Pin;", "getPins", "()Ljava/util/List;", BeanUtil.PREFIX_ADDER, "pattern", "", "", "(Ljava/lang/String;[Ljava/lang/String;)Lokhttp3/CertificatePinner$Builder;", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "Lokhttp3/CertificatePinner;", "okhttp"})
    /* loaded from: reader.jar:BOOT-INF/lib/okhttp-4.9.1.jar:okhttp3/CertificatePinner$Builder.class */
    public static final class Builder {

        @NotNull
        private final List<Pin> pins = new ArrayList();

        @NotNull
        public final List<Pin> getPins() {
            return this.pins;
        }

        @NotNull
        public final Builder add(@NotNull String pattern, @NotNull String... pins) {
            Intrinsics.checkNotNullParameter(pattern, "pattern");
            Intrinsics.checkNotNullParameter(pins, "pins");
            Builder $this$apply = this;
            for (String pin : pins) {
                $this$apply.pins.add(new Pin(pattern, pin));
            }
            return this;
        }

        @NotNull
        public final CertificatePinner build() {
            return new CertificatePinner(CollectionsKt.toSet(this.pins), null, 2, null);
        }
    }

    /* compiled from: CertificatePinner.kt */
    @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"��*\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018��2\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\f\u0010\t\u001a\u00020\n*\u00020\u000bH\u0007J\f\u0010\f\u001a\u00020\n*\u00020\u000bH\u0007R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n��¨\u0006\r"}, d2 = {"Lokhttp3/CertificatePinner$Companion;", "", "()V", Book.imgStyleDefault, "Lokhttp3/CertificatePinner;", "pin", "", "certificate", "Ljava/security/cert/Certificate;", "sha1Hash", "Lokio/ByteString;", "Ljava/security/cert/X509Certificate;", "sha256Hash", "okhttp"})
    /* loaded from: reader.jar:BOOT-INF/lib/okhttp-4.9.1.jar:okhttp3/CertificatePinner$Companion.class */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final ByteString sha1Hash(@NotNull X509Certificate sha1Hash) {
            Intrinsics.checkNotNullParameter(sha1Hash, "$this$sha1Hash");
            ByteString.Companion companion = ByteString.Companion;
            PublicKey publicKey = sha1Hash.getPublicKey();
            Intrinsics.checkNotNullExpressionValue(publicKey, "publicKey");
            byte[] encoded = publicKey.getEncoded();
            Intrinsics.checkNotNullExpressionValue(encoded, "publicKey.encoded");
            return ByteString.Companion.of$default(companion, encoded, 0, 0, 3, null).sha1();
        }

        @JvmStatic
        @NotNull
        public final ByteString sha256Hash(@NotNull X509Certificate sha256Hash) {
            Intrinsics.checkNotNullParameter(sha256Hash, "$this$sha256Hash");
            ByteString.Companion companion = ByteString.Companion;
            PublicKey publicKey = sha256Hash.getPublicKey();
            Intrinsics.checkNotNullExpressionValue(publicKey, "publicKey");
            byte[] encoded = publicKey.getEncoded();
            Intrinsics.checkNotNullExpressionValue(encoded, "publicKey.encoded");
            return ByteString.Companion.of$default(companion, encoded, 0, 0, 3, null).sha256();
        }

        @JvmStatic
        @NotNull
        public final String pin(@NotNull Certificate certificate) {
            Intrinsics.checkNotNullParameter(certificate, "certificate");
            if (!(certificate instanceof X509Certificate)) {
                throw new IllegalArgumentException("Certificate pinning requires X509 certificates".toString());
            }
            return "sha256/" + sha256Hash((X509Certificate) certificate).base64();
        }
    }
}
