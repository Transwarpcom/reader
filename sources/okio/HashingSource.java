package okio;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import me.ag2s.epublib.epub.PackageDocumentBase;
import org.jetbrains.annotations.NotNull;

/* compiled from: HashingSource.kt */
@Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"��<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018�� \u00162\u00020\u0001:\u0001\u0016B\u0017\b\u0010\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u001f\b\u0010\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\tJ\r\u0010\n\u001a\u00020\bH\u0007¢\u0006\u0002\b\u0010J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0012H\u0016R\u0011\u0010\n\u001a\u00020\b8G¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u0004¢\u0006\u0002\n��R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u0004¢\u0006\u0002\n��¨\u0006\u0017"}, d2 = {"Lokio/HashingSource;", "Lokio/ForwardingSource;", PackageDocumentBase.DCTags.source, "Lokio/Source;", "algorithm", "", "(Lokio/Source;Ljava/lang/String;)V", "key", "Lokio/ByteString;", "(Lokio/Source;Lokio/ByteString;Ljava/lang/String;)V", "hash", "()Lokio/ByteString;", "mac", "Ljavax/crypto/Mac;", "messageDigest", "Ljava/security/MessageDigest;", "-deprecated_hash", "read", "", "sink", "Lokio/Buffer;", "byteCount", "Companion", "okio"})
/* loaded from: reader.jar:BOOT-INF/lib/okio-jvm-2.8.0.jar:okio/HashingSource.class */
public final class HashingSource extends ForwardingSource {
    private final MessageDigest messageDigest;
    private final Mac mac;
    public static final Companion Companion = new Companion(null);

    @JvmStatic
    @NotNull
    public static final HashingSource md5(@NotNull Source source) {
        return Companion.md5(source);
    }

    @JvmStatic
    @NotNull
    public static final HashingSource sha1(@NotNull Source source) {
        return Companion.sha1(source);
    }

    @JvmStatic
    @NotNull
    public static final HashingSource sha256(@NotNull Source source) {
        return Companion.sha256(source);
    }

    @JvmStatic
    @NotNull
    public static final HashingSource sha512(@NotNull Source source) {
        return Companion.sha512(source);
    }

    @JvmStatic
    @NotNull
    public static final HashingSource hmacSha1(@NotNull Source source, @NotNull ByteString key) {
        return Companion.hmacSha1(source, key);
    }

    @JvmStatic
    @NotNull
    public static final HashingSource hmacSha256(@NotNull Source source, @NotNull ByteString key) {
        return Companion.hmacSha256(source, key);
    }

    @JvmStatic
    @NotNull
    public static final HashingSource hmacSha512(@NotNull Source source, @NotNull ByteString key) {
        return Companion.hmacSha512(source, key);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HashingSource(@NotNull Source source, @NotNull String algorithm) {
        super(source);
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(algorithm, "algorithm");
        this.messageDigest = MessageDigest.getInstance(algorithm);
        this.mac = (Mac) null;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HashingSource(@NotNull Source source, @NotNull ByteString key, @NotNull String algorithm) throws NoSuchAlgorithmException, InvalidKeyException {
        super(source);
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(algorithm, "algorithm");
        try {
            Mac $this$apply = Mac.getInstance(algorithm);
            $this$apply.init(new SecretKeySpec(key.toByteArray(), algorithm));
            Unit unit = Unit.INSTANCE;
            this.mac = $this$apply;
            this.messageDigest = (MessageDigest) null;
        } catch (InvalidKeyException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override // okio.ForwardingSource, okio.Source
    public long read(@NotNull Buffer sink, long byteCount) throws IllegalStateException, IOException {
        Intrinsics.checkNotNullParameter(sink, "sink");
        long result = super.read(sink, byteCount);
        if (result != -1) {
            long start = sink.size() - result;
            long offset = sink.size();
            Segment segment = sink.head;
            Intrinsics.checkNotNull(segment);
            Segment s = segment;
            while (offset > start) {
                Segment segment2 = s.prev;
                Intrinsics.checkNotNull(segment2);
                s = segment2;
                offset -= s.limit - s.pos;
            }
            while (offset < sink.size()) {
                int pos = (int) ((s.pos + start) - offset);
                if (this.messageDigest != null) {
                    this.messageDigest.update(s.data, pos, s.limit - pos);
                } else {
                    Mac mac = this.mac;
                    Intrinsics.checkNotNull(mac);
                    mac.update(s.data, pos, s.limit - pos);
                }
                offset += s.limit - s.pos;
                start = offset;
                Segment segment3 = s.next;
                Intrinsics.checkNotNull(segment3);
                s = segment3;
            }
        }
        return result;
    }

    @JvmName(name = "hash")
    @NotNull
    public final ByteString hash() throws IllegalStateException {
        byte[] bArrDoFinal;
        if (this.messageDigest != null) {
            bArrDoFinal = this.messageDigest.digest();
        } else {
            Mac mac = this.mac;
            Intrinsics.checkNotNull(mac);
            bArrDoFinal = mac.doFinal();
        }
        byte[] result = bArrDoFinal;
        Intrinsics.checkNotNullExpressionValue(result, "result");
        return new ByteString(result);
    }

    @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "hash"), level = DeprecationLevel.ERROR)
    @JvmName(name = "-deprecated_hash")
    @NotNull
    /* renamed from: -deprecated_hash, reason: not valid java name */
    public final ByteString m4667deprecated_hash() {
        return hash();
    }

    /* compiled from: HashingSource.kt */
    @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"�� \n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0003\u0018��2\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0018\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0018\u0010\n\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0010\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\r\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u000f"}, d2 = {"Lokio/HashingSource$Companion;", "", "()V", "hmacSha1", "Lokio/HashingSource;", PackageDocumentBase.DCTags.source, "Lokio/Source;", "key", "Lokio/ByteString;", "hmacSha256", "hmacSha512", "md5", "sha1", "sha256", "sha512", "okio"})
    /* loaded from: reader.jar:BOOT-INF/lib/okio-jvm-2.8.0.jar:okio/HashingSource$Companion.class */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final HashingSource md5(@NotNull Source source) {
            Intrinsics.checkNotNullParameter(source, "source");
            return new HashingSource(source, "MD5");
        }

        @JvmStatic
        @NotNull
        public final HashingSource sha1(@NotNull Source source) {
            Intrinsics.checkNotNullParameter(source, "source");
            return new HashingSource(source, "SHA-1");
        }

        @JvmStatic
        @NotNull
        public final HashingSource sha256(@NotNull Source source) {
            Intrinsics.checkNotNullParameter(source, "source");
            return new HashingSource(source, "SHA-256");
        }

        @JvmStatic
        @NotNull
        public final HashingSource sha512(@NotNull Source source) {
            Intrinsics.checkNotNullParameter(source, "source");
            return new HashingSource(source, "SHA-512");
        }

        @JvmStatic
        @NotNull
        public final HashingSource hmacSha1(@NotNull Source source, @NotNull ByteString key) {
            Intrinsics.checkNotNullParameter(source, "source");
            Intrinsics.checkNotNullParameter(key, "key");
            return new HashingSource(source, key, "HmacSHA1");
        }

        @JvmStatic
        @NotNull
        public final HashingSource hmacSha256(@NotNull Source source, @NotNull ByteString key) {
            Intrinsics.checkNotNullParameter(source, "source");
            Intrinsics.checkNotNullParameter(key, "key");
            return new HashingSource(source, key, "HmacSHA256");
        }

        @JvmStatic
        @NotNull
        public final HashingSource hmacSha512(@NotNull Source source, @NotNull ByteString key) {
            Intrinsics.checkNotNullParameter(source, "source");
            Intrinsics.checkNotNullParameter(key, "key");
            return new HashingSource(source, key, "HmacSHA512");
        }
    }
}
