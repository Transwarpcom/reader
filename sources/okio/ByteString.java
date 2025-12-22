package okio;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import cn.hutool.core.text.StrPool;
import io.netty.handler.codec.http.HttpHeaders;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.ArraysKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import okio.internal.ByteStringKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.xml.BeanDefinitionParserDelegate;
import org.springframework.cglib.core.Constants;
import org.springframework.jmx.export.naming.IdentityNamingStrategy;

/* compiled from: ByteString.kt */
@Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"��p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n��\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u0005\n\u0002\b\u001a\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018�� Z2\u00020\u00012\b\u0012\u0004\u0012\u00020��0\u0002:\u0001ZB\u000f\b��\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\b\u0010\u0017\u001a\u00020\u0010H\u0016J\b\u0010\u0018\u001a\u00020\u0010H\u0016J\u0011\u0010\u0019\u001a\u00020\t2\u0006\u0010\u001a\u001a\u00020��H\u0096\u0002J\u0015\u0010\u001b\u001a\u00020��2\u0006\u0010\u001c\u001a\u00020\u0010H\u0010¢\u0006\u0002\b\u001dJ\u000e\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u0004J\u000e\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020��J\u0013\u0010!\u001a\u00020\u001f2\b\u0010\u001a\u001a\u0004\u0018\u00010\"H\u0096\u0002J\u0016\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\tH\u0087\u0002¢\u0006\u0002\b&J\u0015\u0010&\u001a\u00020$2\u0006\u0010%\u001a\u00020\tH\u0007¢\u0006\u0002\b'J\r\u0010(\u001a\u00020\tH\u0010¢\u0006\u0002\b)J\b\u0010\b\u001a\u00020\tH\u0016J\b\u0010*\u001a\u00020\u0010H\u0016J\u001d\u0010+\u001a\u00020��2\u0006\u0010\u001c\u001a\u00020\u00102\u0006\u0010,\u001a\u00020��H\u0010¢\u0006\u0002\b-J\u0010\u0010.\u001a\u00020��2\u0006\u0010,\u001a\u00020��H\u0016J\u0010\u0010/\u001a\u00020��2\u0006\u0010,\u001a\u00020��H\u0016J\u0010\u00100\u001a\u00020��2\u0006\u0010,\u001a\u00020��H\u0016J\u001a\u00101\u001a\u00020\t2\u0006\u0010\u001a\u001a\u00020\u00042\b\b\u0002\u00102\u001a\u00020\tH\u0017J\u001a\u00101\u001a\u00020\t2\u0006\u0010\u001a\u001a\u00020��2\b\b\u0002\u00102\u001a\u00020\tH\u0007J\r\u00103\u001a\u00020\u0004H\u0010¢\u0006\u0002\b4J\u0015\u00105\u001a\u00020$2\u0006\u00106\u001a\u00020\tH\u0010¢\u0006\u0002\b7J\u001a\u00108\u001a\u00020\t2\u0006\u0010\u001a\u001a\u00020\u00042\b\b\u0002\u00102\u001a\u00020\tH\u0017J\u001a\u00108\u001a\u00020\t2\u0006\u0010\u001a\u001a\u00020��2\b\b\u0002\u00102\u001a\u00020\tH\u0007J\b\u00109\u001a\u00020��H\u0016J(\u0010:\u001a\u00020\u001f2\u0006\u0010;\u001a\u00020\t2\u0006\u0010\u001a\u001a\u00020\u00042\u0006\u0010<\u001a\u00020\t2\u0006\u0010=\u001a\u00020\tH\u0016J(\u0010:\u001a\u00020\u001f2\u0006\u0010;\u001a\u00020\t2\u0006\u0010\u001a\u001a\u00020��2\u0006\u0010<\u001a\u00020\t2\u0006\u0010=\u001a\u00020\tH\u0016J\u0010\u0010>\u001a\u00020?2\u0006\u0010@\u001a\u00020AH\u0002J\b\u0010B\u001a\u00020��H\u0016J\b\u0010C\u001a\u00020��H\u0016J\b\u0010D\u001a\u00020��H\u0016J\r\u0010\u000e\u001a\u00020\tH\u0007¢\u0006\u0002\bEJ\u000e\u0010F\u001a\u00020\u001f2\u0006\u0010G\u001a\u00020\u0004J\u000e\u0010F\u001a\u00020\u001f2\u0006\u0010G\u001a\u00020��J\u0010\u0010H\u001a\u00020\u00102\u0006\u0010I\u001a\u00020JH\u0016J\u001c\u0010K\u001a\u00020��2\b\b\u0002\u0010L\u001a\u00020\t2\b\b\u0002\u0010M\u001a\u00020\tH\u0017J\b\u0010N\u001a\u00020��H\u0016J\b\u0010O\u001a\u00020��H\u0016J\b\u0010P\u001a\u00020\u0004H\u0016J\b\u0010Q\u001a\u00020\u0010H\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\u0010\u0010R\u001a\u00020?2\u0006\u0010S\u001a\u00020TH\u0016J%\u0010R\u001a\u00020?2\u0006\u0010U\u001a\u00020V2\u0006\u0010;\u001a\u00020\t2\u0006\u0010=\u001a\u00020\tH\u0010¢\u0006\u0002\bWJ\u0010\u0010X\u001a\u00020?2\u0006\u0010S\u001a\u00020YH\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0080\u0004¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\u00020\tX\u0080\u000e¢\u0006\u000e\n��\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u0011\u0010\u000e\u001a\u00020\t8G¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000bR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0080\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006["}, d2 = {"Lokio/ByteString;", "Ljava/io/Serializable;", "", "data", "", "([B)V", "getData$okio", "()[B", IdentityNamingStrategy.HASH_CODE_KEY, "", "getHashCode$okio", "()I", "setHashCode$okio", "(I)V", "size", "utf8", "", "getUtf8$okio", "()Ljava/lang/String;", "setUtf8$okio", "(Ljava/lang/String;)V", "asByteBuffer", "Ljava/nio/ByteBuffer;", HttpHeaders.Values.BASE64, "base64Url", "compareTo", "other", "digest", "algorithm", "digest$okio", "endsWith", "", "suffix", "equals", "", BeanUtil.PREFIX_GETTER_GET, "", "index", "getByte", "-deprecated_getByte", "getSize", "getSize$okio", "hex", "hmac", "key", "hmac$okio", "hmacSha1", "hmacSha256", "hmacSha512", "indexOf", "fromIndex", "internalArray", "internalArray$okio", "internalGet", "pos", "internalGet$okio", "lastIndexOf", "md5", "rangeEquals", "offset", "otherOffset", "byteCount", "readObject", "", "in", "Ljava/io/ObjectInputStream;", "sha1", "sha256", "sha512", "-deprecated_size", "startsWith", "prefix", "string", "charset", "Ljava/nio/charset/Charset;", "substring", "beginIndex", "endIndex", "toAsciiLowercase", "toAsciiUppercase", "toByteArray", "toString", "write", "out", "Ljava/io/OutputStream;", "buffer", "Lokio/Buffer;", "write$okio", "writeObject", "Ljava/io/ObjectOutputStream;", "Companion", "okio"})
/* loaded from: reader.jar:BOOT-INF/lib/okio-jvm-2.8.0.jar:okio/ByteString.class */
public class ByteString implements Serializable, Comparable<ByteString> {
    private transient int hashCode;

    @Nullable
    private transient String utf8;

    @NotNull
    private final byte[] data;
    private static final long serialVersionUID = 1;
    public static final Companion Companion = new Companion(null);

    @JvmField
    @NotNull
    public static final ByteString EMPTY = new ByteString(new byte[0]);

    public static /* synthetic */ ByteString substring$default(ByteString byteString, int i, int i2, int i3, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: substring");
        }
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = byteString.size();
        }
        return byteString.substring(i, i2);
    }

    @JvmOverloads
    @NotNull
    public final ByteString substring(int beginIndex) {
        return substring$default(this, beginIndex, 0, 2, null);
    }

    @JvmOverloads
    @NotNull
    public final ByteString substring() {
        return substring$default(this, 0, 0, 3, null);
    }

    public static /* synthetic */ int indexOf$default(ByteString byteString, ByteString byteString2, int i, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: indexOf");
        }
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return byteString.indexOf(byteString2, i);
    }

    @JvmOverloads
    public final int indexOf(@NotNull ByteString other) {
        return indexOf$default(this, other, 0, 2, (Object) null);
    }

    public static /* synthetic */ int indexOf$default(ByteString byteString, byte[] bArr, int i, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: indexOf");
        }
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return byteString.indexOf(bArr, i);
    }

    @JvmOverloads
    public final int indexOf(@NotNull byte[] other) {
        return indexOf$default(this, other, 0, 2, (Object) null);
    }

    public static /* synthetic */ int lastIndexOf$default(ByteString byteString, ByteString byteString2, int i, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: lastIndexOf");
        }
        if ((i2 & 2) != 0) {
            i = byteString.size();
        }
        return byteString.lastIndexOf(byteString2, i);
    }

    @JvmOverloads
    public final int lastIndexOf(@NotNull ByteString other) {
        return lastIndexOf$default(this, other, 0, 2, (Object) null);
    }

    public static /* synthetic */ int lastIndexOf$default(ByteString byteString, byte[] bArr, int i, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: lastIndexOf");
        }
        if ((i2 & 2) != 0) {
            i = byteString.size();
        }
        return byteString.lastIndexOf(bArr, i);
    }

    @JvmOverloads
    public final int lastIndexOf(@NotNull byte[] other) {
        return lastIndexOf$default(this, other, 0, 2, (Object) null);
    }

    @JvmStatic
    @NotNull
    public static final ByteString of(@NotNull byte... data) {
        return Companion.of(data);
    }

    @JvmStatic
    @JvmName(name = "of")
    @NotNull
    public static final ByteString of(@NotNull byte[] $this$toByteString, int offset, int byteCount) {
        return Companion.of($this$toByteString, offset, byteCount);
    }

    @JvmStatic
    @JvmName(name = "of")
    @NotNull
    public static final ByteString of(@NotNull ByteBuffer $this$toByteString) {
        return Companion.of($this$toByteString);
    }

    @JvmStatic
    @NotNull
    public static final ByteString encodeUtf8(@NotNull String $this$encodeUtf8) {
        return Companion.encodeUtf8($this$encodeUtf8);
    }

    @JvmStatic
    @JvmName(name = "encodeString")
    @NotNull
    public static final ByteString encodeString(@NotNull String $this$encode, @NotNull Charset charset) {
        return Companion.encodeString($this$encode, charset);
    }

    @JvmStatic
    @Nullable
    public static final ByteString decodeBase64(@NotNull String $this$decodeBase64) {
        return Companion.decodeBase64($this$decodeBase64);
    }

    @JvmStatic
    @NotNull
    public static final ByteString decodeHex(@NotNull String $this$decodeHex) {
        return Companion.decodeHex($this$decodeHex);
    }

    @JvmStatic
    @JvmName(name = "read")
    @NotNull
    public static final ByteString read(@NotNull InputStream $this$readByteString, int byteCount) throws IOException {
        return Companion.read($this$readByteString, byteCount);
    }

    public ByteString(@NotNull byte[] data) {
        Intrinsics.checkNotNullParameter(data, "data");
        this.data = data;
    }

    @NotNull
    public final byte[] getData$okio() {
        return this.data;
    }

    public final int getHashCode$okio() {
        return this.hashCode;
    }

    public final void setHashCode$okio(int i) {
        this.hashCode = i;
    }

    @Nullable
    public final String getUtf8$okio() {
        return this.utf8;
    }

    public final void setUtf8$okio(@Nullable String str) {
        this.utf8 = str;
    }

    @NotNull
    public String utf8() {
        String result$iv = getUtf8$okio();
        if (result$iv == null) {
            result$iv = Platform.toUtf8String(internalArray$okio());
            setUtf8$okio(result$iv);
        }
        return result$iv;
    }

    @NotNull
    public String string(@NotNull Charset charset) {
        Intrinsics.checkNotNullParameter(charset, "charset");
        return new String(this.data, charset);
    }

    @NotNull
    public String base64() {
        return Base64.encodeBase64$default(getData$okio(), null, 1, null);
    }

    @NotNull
    public ByteString md5() {
        return digest$okio("MD5");
    }

    @NotNull
    public ByteString sha1() {
        return digest$okio("SHA-1");
    }

    @NotNull
    public ByteString sha256() {
        return digest$okio("SHA-256");
    }

    @NotNull
    public ByteString sha512() {
        return digest$okio("SHA-512");
    }

    @NotNull
    public ByteString digest$okio(@NotNull String algorithm) {
        Intrinsics.checkNotNullParameter(algorithm, "algorithm");
        byte[] bArrDigest = MessageDigest.getInstance(algorithm).digest(this.data);
        Intrinsics.checkNotNullExpressionValue(bArrDigest, "MessageDigest.getInstance(algorithm).digest(data)");
        return new ByteString(bArrDigest);
    }

    @NotNull
    public ByteString hmacSha1(@NotNull ByteString key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return hmac$okio("HmacSHA1", key);
    }

    @NotNull
    public ByteString hmacSha256(@NotNull ByteString key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return hmac$okio("HmacSHA256", key);
    }

    @NotNull
    public ByteString hmacSha512(@NotNull ByteString key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return hmac$okio("HmacSHA512", key);
    }

    @NotNull
    public ByteString hmac$okio(@NotNull String algorithm, @NotNull ByteString key) throws IllegalStateException, NoSuchAlgorithmException, InvalidKeyException {
        Intrinsics.checkNotNullParameter(algorithm, "algorithm");
        Intrinsics.checkNotNullParameter(key, "key");
        try {
            Mac mac = Mac.getInstance(algorithm);
            mac.init(new SecretKeySpec(key.toByteArray(), algorithm));
            byte[] bArrDoFinal = mac.doFinal(this.data);
            Intrinsics.checkNotNullExpressionValue(bArrDoFinal, "mac.doFinal(data)");
            return new ByteString(bArrDoFinal);
        } catch (InvalidKeyException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @NotNull
    public String base64Url() {
        return Base64.encodeBase64(getData$okio(), Base64.getBASE64_URL_SAFE());
    }

    @NotNull
    public String hex() {
        char[] result$iv = new char[getData$okio().length * 2];
        int c$iv = 0;
        for (byte b$iv : getData$okio()) {
            int i = c$iv;
            int c$iv2 = c$iv + 1;
            result$iv[i] = ByteStringKt.getHEX_DIGIT_CHARS()[(b$iv >> 4) & 15];
            c$iv = c$iv2 + 1;
            result$iv[c$iv2] = ByteStringKt.getHEX_DIGIT_CHARS()[b$iv & 15];
        }
        return new String(result$iv);
    }

    @NotNull
    public ByteString toAsciiLowercase() {
        for (int i$iv = 0; i$iv < getData$okio().length; i$iv++) {
            byte c$iv = getData$okio()[i$iv];
            if (c$iv >= ((byte) 65) && c$iv <= ((byte) 90)) {
                byte[] data$okio = getData$okio();
                byte[] lowercase$iv = Arrays.copyOf(data$okio, data$okio.length);
                Intrinsics.checkNotNullExpressionValue(lowercase$iv, "java.util.Arrays.copyOf(this, size)");
                int i = i$iv;
                int i$iv2 = i$iv + 1;
                lowercase$iv[i] = (byte) (c$iv - (-32));
                while (i$iv2 < lowercase$iv.length) {
                    byte c$iv2 = lowercase$iv[i$iv2];
                    if (c$iv2 < ((byte) 65) || c$iv2 > ((byte) 90)) {
                        i$iv2++;
                    } else {
                        lowercase$iv[i$iv2] = (byte) (c$iv2 - (-32));
                        i$iv2++;
                    }
                }
                return new ByteString(lowercase$iv);
            }
        }
        return this;
    }

    @NotNull
    public ByteString toAsciiUppercase() {
        for (int i$iv = 0; i$iv < getData$okio().length; i$iv++) {
            byte c$iv = getData$okio()[i$iv];
            if (c$iv >= ((byte) 97) && c$iv <= ((byte) 122)) {
                byte[] data$okio = getData$okio();
                byte[] lowercase$iv = Arrays.copyOf(data$okio, data$okio.length);
                Intrinsics.checkNotNullExpressionValue(lowercase$iv, "java.util.Arrays.copyOf(this, size)");
                int i = i$iv;
                int i$iv2 = i$iv + 1;
                lowercase$iv[i] = (byte) (c$iv - 32);
                while (i$iv2 < lowercase$iv.length) {
                    byte c$iv2 = lowercase$iv[i$iv2];
                    if (c$iv2 < ((byte) 97) || c$iv2 > ((byte) 122)) {
                        i$iv2++;
                    } else {
                        lowercase$iv[i$iv2] = (byte) (c$iv2 - 32);
                        i$iv2++;
                    }
                }
                return new ByteString(lowercase$iv);
            }
        }
        return this;
    }

    @JvmOverloads
    @NotNull
    public ByteString substring(int beginIndex, int endIndex) {
        if (!(beginIndex >= 0)) {
            throw new IllegalArgumentException("beginIndex < 0".toString());
        }
        if (!(endIndex <= getData$okio().length)) {
            throw new IllegalArgumentException(("endIndex > length(" + getData$okio().length + ')').toString());
        }
        int subLen$iv = endIndex - beginIndex;
        if (!(subLen$iv >= 0)) {
            throw new IllegalArgumentException("endIndex < beginIndex".toString());
        }
        if (beginIndex == 0 && endIndex == getData$okio().length) {
            return this;
        }
        return new ByteString(ArraysKt.copyOfRange(getData$okio(), beginIndex, endIndex));
    }

    public byte internalGet$okio(int pos) {
        return getData$okio()[pos];
    }

    @JvmName(name = "getByte")
    public final byte getByte(int index) {
        return internalGet$okio(index);
    }

    @JvmName(name = "size")
    public final int size() {
        return getSize$okio();
    }

    public int getSize$okio() {
        return getData$okio().length;
    }

    @NotNull
    public byte[] toByteArray() {
        byte[] data$okio = getData$okio();
        byte[] bArrCopyOf = Arrays.copyOf(data$okio, data$okio.length);
        Intrinsics.checkNotNullExpressionValue(bArrCopyOf, "java.util.Arrays.copyOf(this, size)");
        return bArrCopyOf;
    }

    @NotNull
    public byte[] internalArray$okio() {
        return getData$okio();
    }

    @NotNull
    public ByteBuffer asByteBuffer() {
        ByteBuffer byteBufferAsReadOnlyBuffer = ByteBuffer.wrap(this.data).asReadOnlyBuffer();
        Intrinsics.checkNotNullExpressionValue(byteBufferAsReadOnlyBuffer, "ByteBuffer.wrap(data).asReadOnlyBuffer()");
        return byteBufferAsReadOnlyBuffer;
    }

    public void write(@NotNull OutputStream out) throws IOException {
        Intrinsics.checkNotNullParameter(out, "out");
        out.write(this.data);
    }

    public void write$okio(@NotNull Buffer buffer, int offset, int byteCount) {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        ByteStringKt.commonWrite(this, buffer, offset, byteCount);
    }

    public boolean rangeEquals(int offset, @NotNull ByteString other, int otherOffset, int byteCount) {
        Intrinsics.checkNotNullParameter(other, "other");
        return other.rangeEquals(otherOffset, getData$okio(), offset, byteCount);
    }

    public boolean rangeEquals(int offset, @NotNull byte[] other, int otherOffset, int byteCount) {
        Intrinsics.checkNotNullParameter(other, "other");
        return offset >= 0 && offset <= getData$okio().length - byteCount && otherOffset >= 0 && otherOffset <= other.length - byteCount && Util.arrayRangeEquals(getData$okio(), offset, other, otherOffset, byteCount);
    }

    public final boolean startsWith(@NotNull ByteString prefix) {
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        return rangeEquals(0, prefix, 0, prefix.size());
    }

    public final boolean startsWith(@NotNull byte[] prefix) {
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        return rangeEquals(0, prefix, 0, prefix.length);
    }

    public final boolean endsWith(@NotNull ByteString suffix) {
        Intrinsics.checkNotNullParameter(suffix, "suffix");
        return rangeEquals(size() - suffix.size(), suffix, 0, suffix.size());
    }

    public final boolean endsWith(@NotNull byte[] suffix) {
        Intrinsics.checkNotNullParameter(suffix, "suffix");
        return rangeEquals(size() - suffix.length, suffix, 0, suffix.length);
    }

    @JvmOverloads
    public final int indexOf(@NotNull ByteString other, int fromIndex) {
        Intrinsics.checkNotNullParameter(other, "other");
        return indexOf(other.internalArray$okio(), fromIndex);
    }

    @JvmOverloads
    public int indexOf(@NotNull byte[] other, int fromIndex) {
        Intrinsics.checkNotNullParameter(other, "other");
        int limit$iv = getData$okio().length - other.length;
        int i$iv = Math.max(fromIndex, 0);
        if (i$iv <= limit$iv) {
            while (!Util.arrayRangeEquals(getData$okio(), i$iv, other, 0, other.length)) {
                if (i$iv != limit$iv) {
                    i$iv++;
                }
            }
            return i$iv;
        }
        return -1;
    }

    @JvmOverloads
    public final int lastIndexOf(@NotNull ByteString other, int fromIndex) {
        Intrinsics.checkNotNullParameter(other, "other");
        return lastIndexOf(other.internalArray$okio(), fromIndex);
    }

    @JvmOverloads
    public int lastIndexOf(@NotNull byte[] other, int fromIndex) {
        Intrinsics.checkNotNullParameter(other, "other");
        int limit$iv = getData$okio().length - other.length;
        for (int i$iv = Math.min(fromIndex, limit$iv); i$iv >= 0; i$iv--) {
            if (Util.arrayRangeEquals(getData$okio(), i$iv, other, 0, other.length)) {
                return i$iv;
            }
        }
        return -1;
    }

    public boolean equals(@Nullable Object other) {
        if (other == this) {
            return true;
        }
        return (other instanceof ByteString) && ((ByteString) other).size() == getData$okio().length && ((ByteString) other).rangeEquals(0, getData$okio(), 0, getData$okio().length);
    }

    public int hashCode() {
        int result$iv = getHashCode$okio();
        if (result$iv != 0) {
            return result$iv;
        }
        int it$iv = Arrays.hashCode(getData$okio());
        setHashCode$okio(it$iv);
        return it$iv;
    }

    @Override // java.lang.Comparable
    public int compareTo(@NotNull ByteString other) {
        Intrinsics.checkNotNullParameter(other, "other");
        int sizeA$iv = size();
        int sizeB$iv = other.size();
        int size$iv = Math.min(sizeA$iv, sizeB$iv);
        for (int i$iv = 0; i$iv < size$iv; i$iv++) {
            int $this$and$iv$iv = getByte(i$iv);
            int byteA$iv = $this$and$iv$iv & 255;
            byte $this$and$iv$iv2 = other.getByte(i$iv);
            int byteB$iv = $this$and$iv$iv2 & 255;
            if (byteA$iv != byteB$iv) {
                return byteA$iv < byteB$iv ? -1 : 1;
            }
        }
        if (sizeA$iv == sizeB$iv) {
            return 0;
        }
        return sizeA$iv < sizeB$iv ? -1 : 1;
    }

    @NotNull
    public String toString() {
        ByteString byteString;
        if (getData$okio().length == 0) {
            return "[size=0]";
        }
        int i$iv = ByteStringKt.codePointIndexToCharIndex(getData$okio(), 64);
        if (i$iv == -1) {
            if (getData$okio().length <= 64) {
                return "[hex=" + hex() + ']';
            }
            StringBuilder sbAppend = new StringBuilder().append("[size=").append(getData$okio().length).append(" hex=");
            if (!(64 <= getData$okio().length)) {
                throw new IllegalArgumentException(("endIndex > length(" + getData$okio().length + ')').toString());
            }
            int subLen$iv$iv = 64 - 0;
            if (!(subLen$iv$iv >= 0)) {
                throw new IllegalArgumentException("endIndex < beginIndex".toString());
            }
            if (64 == getData$okio().length) {
                byteString = this;
            } else {
                byteString = new ByteString(ArraysKt.copyOfRange(getData$okio(), 0, 64));
            }
            return sbAppend.append(byteString.hex()).append("…]").toString();
        }
        String text$iv = utf8();
        if (text$iv == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String strSubstring = text$iv.substring(0, i$iv);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        String safeText$iv = StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(strSubstring, StrPool.BACKSLASH, "\\\\", false, 4, (Object) null), "\n", "\\n", false, 4, (Object) null), "\r", "\\r", false, 4, (Object) null);
        return i$iv < text$iv.length() ? "[size=" + getData$okio().length + " text=" + safeText$iv + "…]" : "[text=" + safeText$iv + ']';
    }

    private final void readObject(ObjectInputStream in) throws IllegalAccessException, NoSuchFieldException, IOException, IllegalArgumentException {
        int dataLength = in.readInt();
        ByteString byteString = Companion.read(in, dataLength);
        Field field = ByteString.class.getDeclaredField("data");
        Intrinsics.checkNotNullExpressionValue(field, "field");
        field.setAccessible(true);
        field.set(this, byteString.data);
    }

    private final void writeObject(ObjectOutputStream out) throws IOException {
        out.writeInt(this.data.length);
        out.write(this.data);
    }

    @Deprecated(message = "moved to operator function", replaceWith = @ReplaceWith(imports = {}, expression = "this[index]"), level = DeprecationLevel.ERROR)
    @JvmName(name = "-deprecated_getByte")
    /* renamed from: -deprecated_getByte, reason: not valid java name */
    public final byte m4651deprecated_getByte(int index) {
        return getByte(index);
    }

    @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(imports = {}, expression = "size"), level = DeprecationLevel.ERROR)
    @JvmName(name = "-deprecated_size")
    /* renamed from: -deprecated_size, reason: not valid java name */
    public final int m4652deprecated_size() {
        return size();
    }

    /* compiled from: ByteString.kt */
    @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"��N\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018��2\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0017\u0010\u0007\u001a\u0004\u0018\u00010\u00042\u0006\u0010\b\u001a\u00020\tH\u0007¢\u0006\u0002\b\nJ\u0015\u0010\u000b\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tH\u0007¢\u0006\u0002\b\fJ\u001d\u0010\r\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000fH\u0007¢\u0006\u0002\b\u0010J\u0015\u0010\u0011\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tH\u0007¢\u0006\u0002\b\u0012J\u0015\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u0015H\u0007¢\u0006\u0002\b\u0016J\u0014\u0010\u0013\u001a\u00020\u00042\n\u0010\u0017\u001a\u00020\u0018\"\u00020\u0019H\u0007J%\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001cH\u0007¢\u0006\u0002\b\u0016J\u001d\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010\u001d\u001a\u00020\u001cH\u0007¢\u0006\u0002\b!J\u000e\u0010\u0007\u001a\u0004\u0018\u00010\u0004*\u00020\tH\u0007J\f\u0010\u000b\u001a\u00020\u0004*\u00020\tH\u0007J\u001b\u0010\"\u001a\u00020\u0004*\u00020\t2\b\b\u0002\u0010\u000e\u001a\u00020\u000fH\u0007¢\u0006\u0002\b\rJ\f\u0010\u0011\u001a\u00020\u0004*\u00020\tH\u0007J\u0019\u0010#\u001a\u00020\u0004*\u00020 2\u0006\u0010\u001d\u001a\u00020\u001cH\u0007¢\u0006\u0002\b\u001eJ\u0011\u0010$\u001a\u00020\u0004*\u00020\u0015H\u0007¢\u0006\u0002\b\u0013J%\u0010$\u001a\u00020\u0004*\u00020\u00182\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u001cH\u0007¢\u0006\u0002\b\u0013R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n��R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n��¨\u0006%"}, d2 = {"Lokio/ByteString$Companion;", "", "()V", "EMPTY", "Lokio/ByteString;", Constants.SUID_FIELD_NAME, "", "decodeBase64", "string", "", "-deprecated_decodeBase64", "decodeHex", "-deprecated_decodeHex", "encodeString", "charset", "Ljava/nio/charset/Charset;", "-deprecated_encodeString", "encodeUtf8", "-deprecated_encodeUtf8", "of", "buffer", "Ljava/nio/ByteBuffer;", "-deprecated_of", "data", "", "", BeanDefinitionParserDelegate.ARRAY_ELEMENT, "offset", "", "byteCount", "read", "inputstream", "Ljava/io/InputStream;", "-deprecated_read", "encode", "readByteString", "toByteString", "okio"})
    /* loaded from: reader.jar:BOOT-INF/lib/okio-jvm-2.8.0.jar:okio/ByteString$Companion.class */
    public static final class Companion {
        public static /* synthetic */ ByteString of$default(Companion companion, byte[] bArr, int i, int i2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                i = 0;
            }
            if ((i3 & 2) != 0) {
                i2 = bArr.length;
            }
            return companion.of(bArr, i, i2);
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final ByteString of(@NotNull byte... data) {
            Intrinsics.checkNotNullParameter(data, "data");
            byte[] bArrCopyOf = Arrays.copyOf(data, data.length);
            Intrinsics.checkNotNullExpressionValue(bArrCopyOf, "java.util.Arrays.copyOf(this, size)");
            return new ByteString(bArrCopyOf);
        }

        @JvmStatic
        @JvmName(name = "of")
        @NotNull
        public final ByteString of(@NotNull byte[] toByteString, int offset, int byteCount) {
            Intrinsics.checkNotNullParameter(toByteString, "$this$toByteString");
            Util.checkOffsetAndCount(toByteString.length, offset, byteCount);
            return new ByteString(ArraysKt.copyOfRange(toByteString, offset, offset + byteCount));
        }

        @JvmStatic
        @JvmName(name = "of")
        @NotNull
        public final ByteString of(@NotNull ByteBuffer toByteString) {
            Intrinsics.checkNotNullParameter(toByteString, "$this$toByteString");
            byte[] copy = new byte[toByteString.remaining()];
            toByteString.get(copy);
            return new ByteString(copy);
        }

        @JvmStatic
        @NotNull
        public final ByteString encodeUtf8(@NotNull String encodeUtf8) {
            Intrinsics.checkNotNullParameter(encodeUtf8, "$this$encodeUtf8");
            ByteString byteString$iv = new ByteString(Platform.asUtf8ToByteArray(encodeUtf8));
            byteString$iv.setUtf8$okio(encodeUtf8);
            return byteString$iv;
        }

        @JvmStatic
        @JvmName(name = "encodeString")
        @NotNull
        public final ByteString encodeString(@NotNull String encode, @NotNull Charset charset) {
            Intrinsics.checkNotNullParameter(encode, "$this$encode");
            Intrinsics.checkNotNullParameter(charset, "charset");
            byte[] bytes = encode.getBytes(charset);
            Intrinsics.checkNotNullExpressionValue(bytes, "(this as java.lang.String).getBytes(charset)");
            return new ByteString(bytes);
        }

        public static /* synthetic */ ByteString encodeString$default(Companion companion, String str, Charset charset, int i, Object obj) {
            if ((i & 1) != 0) {
                charset = Charsets.UTF_8;
            }
            return companion.encodeString(str, charset);
        }

        @JvmStatic
        @Nullable
        public final ByteString decodeBase64(@NotNull String decodeBase64) {
            Intrinsics.checkNotNullParameter(decodeBase64, "$this$decodeBase64");
            byte[] decoded$iv = Base64.decodeBase64ToArray(decodeBase64);
            if (decoded$iv != null) {
                return new ByteString(decoded$iv);
            }
            return null;
        }

        @JvmStatic
        @NotNull
        public final ByteString decodeHex(@NotNull String decodeHex) {
            Intrinsics.checkNotNullParameter(decodeHex, "$this$decodeHex");
            if (!(decodeHex.length() % 2 == 0)) {
                throw new IllegalArgumentException(("Unexpected hex string: " + decodeHex).toString());
            }
            byte[] result$iv = new byte[decodeHex.length() / 2];
            int length = result$iv.length;
            for (int i$iv = 0; i$iv < length; i$iv++) {
                int d1$iv = ByteStringKt.decodeHexDigit(decodeHex.charAt(i$iv * 2)) << 4;
                int d2$iv = ByteStringKt.decodeHexDigit(decodeHex.charAt((i$iv * 2) + 1));
                result$iv[i$iv] = (byte) (d1$iv + d2$iv);
            }
            return new ByteString(result$iv);
        }

        @JvmStatic
        @JvmName(name = "read")
        @NotNull
        public final ByteString read(@NotNull InputStream readByteString, int byteCount) throws IOException {
            Intrinsics.checkNotNullParameter(readByteString, "$this$readByteString");
            if (!(byteCount >= 0)) {
                throw new IllegalArgumentException(("byteCount < 0: " + byteCount).toString());
            }
            byte[] result = new byte[byteCount];
            int offset = 0;
            while (offset < byteCount) {
                int read = readByteString.read(result, offset, byteCount - offset);
                if (read == -1) {
                    throw new EOFException();
                }
                offset += read;
            }
            return new ByteString(result);
        }

        @Deprecated(message = "moved to extension function", replaceWith = @ReplaceWith(imports = {"okio.ByteString.Companion.decodeBase64"}, expression = "string.decodeBase64()"), level = DeprecationLevel.ERROR)
        @JvmName(name = "-deprecated_decodeBase64")
        @Nullable
        /* renamed from: -deprecated_decodeBase64, reason: not valid java name */
        public final ByteString m4654deprecated_decodeBase64(@NotNull String string) {
            Intrinsics.checkNotNullParameter(string, "string");
            return decodeBase64(string);
        }

        @Deprecated(message = "moved to extension function", replaceWith = @ReplaceWith(imports = {"okio.ByteString.Companion.decodeHex"}, expression = "string.decodeHex()"), level = DeprecationLevel.ERROR)
        @JvmName(name = "-deprecated_decodeHex")
        @NotNull
        /* renamed from: -deprecated_decodeHex, reason: not valid java name */
        public final ByteString m4655deprecated_decodeHex(@NotNull String string) {
            Intrinsics.checkNotNullParameter(string, "string");
            return decodeHex(string);
        }

        @Deprecated(message = "moved to extension function", replaceWith = @ReplaceWith(imports = {"okio.ByteString.Companion.encode"}, expression = "string.encode(charset)"), level = DeprecationLevel.ERROR)
        @JvmName(name = "-deprecated_encodeString")
        @NotNull
        /* renamed from: -deprecated_encodeString, reason: not valid java name */
        public final ByteString m4656deprecated_encodeString(@NotNull String string, @NotNull Charset charset) {
            Intrinsics.checkNotNullParameter(string, "string");
            Intrinsics.checkNotNullParameter(charset, "charset");
            return encodeString(string, charset);
        }

        @Deprecated(message = "moved to extension function", replaceWith = @ReplaceWith(imports = {"okio.ByteString.Companion.encodeUtf8"}, expression = "string.encodeUtf8()"), level = DeprecationLevel.ERROR)
        @JvmName(name = "-deprecated_encodeUtf8")
        @NotNull
        /* renamed from: -deprecated_encodeUtf8, reason: not valid java name */
        public final ByteString m4657deprecated_encodeUtf8(@NotNull String string) {
            Intrinsics.checkNotNullParameter(string, "string");
            return encodeUtf8(string);
        }

        @Deprecated(message = "moved to extension function", replaceWith = @ReplaceWith(imports = {"okio.ByteString.Companion.toByteString"}, expression = "buffer.toByteString()"), level = DeprecationLevel.ERROR)
        @JvmName(name = "-deprecated_of")
        @NotNull
        /* renamed from: -deprecated_of, reason: not valid java name */
        public final ByteString m4658deprecated_of(@NotNull ByteBuffer buffer) {
            Intrinsics.checkNotNullParameter(buffer, "buffer");
            return of(buffer);
        }

        @Deprecated(message = "moved to extension function", replaceWith = @ReplaceWith(imports = {"okio.ByteString.Companion.toByteString"}, expression = "array.toByteString(offset, byteCount)"), level = DeprecationLevel.ERROR)
        @JvmName(name = "-deprecated_of")
        @NotNull
        /* renamed from: -deprecated_of, reason: not valid java name */
        public final ByteString m4659deprecated_of(@NotNull byte[] array, int offset, int byteCount) {
            Intrinsics.checkNotNullParameter(array, "array");
            return of(array, offset, byteCount);
        }

        @Deprecated(message = "moved to extension function", replaceWith = @ReplaceWith(imports = {"okio.ByteString.Companion.readByteString"}, expression = "inputstream.readByteString(byteCount)"), level = DeprecationLevel.ERROR)
        @JvmName(name = "-deprecated_read")
        @NotNull
        /* renamed from: -deprecated_read, reason: not valid java name */
        public final ByteString m4660deprecated_read(@NotNull InputStream inputstream, int byteCount) {
            Intrinsics.checkNotNullParameter(inputstream, "inputstream");
            return read(inputstream, byteCount);
        }
    }
}
