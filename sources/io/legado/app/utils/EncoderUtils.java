package io.legado.app.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import kotlin.text.Charsets;
import me.ag2s.epublib.epub.NCXDocumentV2;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.aop.framework.autoproxy.target.QuickTargetSourceCreator;

/* compiled from: EncoderUtils.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��B\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n��\bÆ\u0002\u0018��2\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0007J\u001c\u0010\b\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0007J2\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\f\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\r\u001a\u00020\u00042\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\nJ2\u0010\u000f\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\f\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\r\u001a\u00020\u00042\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\nJ2\u0010\u0010\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\f\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\r\u001a\u00020\u00042\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\nJ2\u0010\u0011\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\f\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\r\u001a\u00020\u00042\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\nJ\u0016\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u0015J\u0016\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u0018J2\u0010\u0019\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\f\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\r\u001a\u00020\u00042\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\nJ2\u0010\u001a\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\f\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\r\u001a\u00020\u00042\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\nJ\"\u0010\u001b\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u001c\u001a\u00020\u0007J\"\u0010\u001d\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u001c\u001a\u00020\u0007J4\u0010\u001e\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\f\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\nJ4\u0010\u001f\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\f\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\nJ\u0016\u0010 \u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u0015J\u0016\u0010!\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u0018J4\u0010\"\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\f\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\nJ4\u0010#\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\f\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\nJ4\u0010$\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\f\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\nJ4\u0010%\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\f\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\nJ \u0010&\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u001c\u001a\u00020\u0007J \u0010'\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u001c\u001a\u00020\u0007J\u000e\u0010(\u001a\u00020\u00042\u0006\u0010)\u001a\u00020\u0004J\u0006\u0010*\u001a\u00020+J@\u0010,\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\f\u001a\u0004\u0018\u00010\n2\u0006\u0010-\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u00042\b\u0010\u000e\u001a\u0004\u0018\u00010\n2\u0006\u0010.\u001a\u00020/H\u0002¨\u00060"}, d2 = {"Lio/legado/app/utils/EncoderUtils;", "", "()V", "base64Decode", "", "str", "flags", "", "base64Encode", "decryptAES", "", "data", "key", "transformation", "iv", "decryptBase64AES", "decryptBase64DES", "decryptBase64DESede", "decryptByPrivateKey", "input", "privateKey", "Ljava/security/PrivateKey;", "decryptByPublicKey", "publicKey", "Ljava/security/PublicKey;", "decryptDES", "decryptDESede", "decryptSegmentByPrivateKey", "keySize", "decryptSegmentByPublicKey", "encryptAES", "encryptAES2Base64", "encryptByPrivateKey", "encryptByPublicKey", "encryptDES", "encryptDES2Base64", "encryptDESede", "encryptDESede2Base64", "encryptSegmentByPrivateKey", "encryptSegmentByPublicKey", "escape", NCXDocumentV2.NCXAttributes.src, "generateKeys", "Ljava/security/KeyPair;", "symmetricTemplate", "algorithm", "isEncrypt", "", "reader-pro"})
/* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/utils/EncoderUtils.class */
public final class EncoderUtils {

    @NotNull
    public static final EncoderUtils INSTANCE = new EncoderUtils();

    @JvmOverloads
    @NotNull
    public final String base64Decode(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "str");
        return base64Decode$default(this, str, 0, 2, null);
    }

    @JvmOverloads
    @Nullable
    public final String base64Encode(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "str");
        return base64Encode$default(this, str, 0, 2, null);
    }

    private EncoderUtils() {
    }

    @NotNull
    public final String escape(@NotNull String src) {
        String str;
        Intrinsics.checkNotNullParameter(src, "src");
        StringBuilder tmp = new StringBuilder();
        int i = 0;
        int length = src.length();
        while (i < length) {
            char cCharAt = src.charAt(i);
            i++;
            boolean z = '0' <= cCharAt && cCharAt <= '9';
            if (!z) {
                boolean z2 = 'A' <= cCharAt && cCharAt <= 'Z';
                if (!z2) {
                    boolean z3 = 'a' <= cCharAt && cCharAt <= 'z';
                    if (!z3) {
                        if (cCharAt < 16) {
                            str = "%0";
                        } else {
                            str = cCharAt < 256 ? QuickTargetSourceCreator.PREFIX_THREAD_LOCAL : "%u";
                        }
                        String prefix = str;
                        StringBuilder sbAppend = tmp.append(prefix);
                        String string = Integer.toString(cCharAt, CharsKt.checkRadix(16));
                        Intrinsics.checkNotNullExpressionValue(string, "java.lang.Integer.toStri…(this, checkRadix(radix))");
                        sbAppend.append(string);
                    }
                }
            }
            tmp.append(cCharAt);
        }
        String string2 = tmp.toString();
        Intrinsics.checkNotNullExpressionValue(string2, "tmp.toString()");
        return string2;
    }

    public static /* synthetic */ String base64Decode$default(EncoderUtils encoderUtils, String str, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return encoderUtils.base64Decode(str, i);
    }

    @JvmOverloads
    @NotNull
    public final String base64Decode(@NotNull String str, int flags) {
        Intrinsics.checkNotNullParameter(str, "str");
        byte[] bytes = Base64.decode(str, flags);
        Intrinsics.checkNotNullExpressionValue(bytes, "bytes");
        return new String(bytes, Charsets.UTF_8);
    }

    public static /* synthetic */ String base64Encode$default(EncoderUtils encoderUtils, String str, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 2;
        }
        return encoderUtils.base64Encode(str, i);
    }

    @JvmOverloads
    @Nullable
    public final String base64Encode(@NotNull String str, int flags) {
        Intrinsics.checkNotNullParameter(str, "str");
        byte[] bytes = str.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "(this as java.lang.String).getBytes(charset)");
        return Base64.encodeToString(bytes, flags);
    }

    public static /* synthetic */ byte[] encryptAES2Base64$default(EncoderUtils encoderUtils, byte[] bArr, byte[] bArr2, String str, byte[] bArr3, int i, Object obj) throws Exception {
        if ((i & 4) != 0) {
            str = "DES/ECB/PKCS5Padding";
        }
        if ((i & 8) != 0) {
            bArr3 = null;
        }
        return encoderUtils.encryptAES2Base64(bArr, bArr2, str, bArr3);
    }

    @Nullable
    public final byte[] encryptAES2Base64(@Nullable byte[] data, @Nullable byte[] key, @Nullable String transformation, @Nullable byte[] iv) throws Exception {
        return Base64.encode(encryptAES(data, key, transformation, iv), 2);
    }

    public static /* synthetic */ byte[] encryptAES$default(EncoderUtils encoderUtils, byte[] bArr, byte[] bArr2, String str, byte[] bArr3, int i, Object obj) throws Exception {
        if ((i & 4) != 0) {
            str = "DES/ECB/PKCS5Padding";
        }
        if ((i & 8) != 0) {
            bArr3 = null;
        }
        return encoderUtils.encryptAES(bArr, bArr2, str, bArr3);
    }

    @Nullable
    public final byte[] encryptAES(@Nullable byte[] data, @Nullable byte[] key, @Nullable String transformation, @Nullable byte[] iv) throws Exception {
        Intrinsics.checkNotNull(transformation);
        return symmetricTemplate(data, key, "AES", transformation, iv, true);
    }

    public static /* synthetic */ byte[] decryptBase64AES$default(EncoderUtils encoderUtils, byte[] bArr, byte[] bArr2, String str, byte[] bArr3, int i, Object obj) throws Exception {
        if ((i & 4) != 0) {
            str = "DES/ECB/PKCS5Padding";
        }
        if ((i & 8) != 0) {
            bArr3 = null;
        }
        return encoderUtils.decryptBase64AES(bArr, bArr2, str, bArr3);
    }

    @Nullable
    public final byte[] decryptBase64AES(@Nullable byte[] data, @Nullable byte[] key, @NotNull String transformation, @Nullable byte[] iv) throws Exception {
        Intrinsics.checkNotNullParameter(transformation, "transformation");
        return decryptAES(Base64.decode(data, 2), key, transformation, iv);
    }

    public static /* synthetic */ byte[] decryptAES$default(EncoderUtils encoderUtils, byte[] bArr, byte[] bArr2, String str, byte[] bArr3, int i, Object obj) throws Exception {
        if ((i & 4) != 0) {
            str = "DES/ECB/PKCS5Padding";
        }
        if ((i & 8) != 0) {
            bArr3 = null;
        }
        return encoderUtils.decryptAES(bArr, bArr2, str, bArr3);
    }

    @Nullable
    public final byte[] decryptAES(@Nullable byte[] data, @Nullable byte[] key, @NotNull String transformation, @Nullable byte[] iv) throws Exception {
        Intrinsics.checkNotNullParameter(transformation, "transformation");
        return symmetricTemplate(data, key, "AES", transformation, iv, false);
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x006c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final byte[] symmetricTemplate(byte[] r6, byte[] r7, java.lang.String r8, java.lang.String r9, byte[] r10, boolean r11) throws java.lang.Exception {
        /*
            r5 = this;
            r0 = r6
            if (r0 == 0) goto L30
            r0 = r6
            r12 = r0
            r0 = 0
            r13 = r0
            r0 = r12
            int r0 = r0.length
            if (r0 != 0) goto L14
            r0 = 1
            goto L15
        L14:
            r0 = 0
        L15:
            if (r0 != 0) goto L30
            r0 = r7
            if (r0 == 0) goto L30
            r0 = r7
            r12 = r0
            r0 = 0
            r13 = r0
            r0 = r12
            int r0 = r0.length
            if (r0 != 0) goto L2c
            r0 = 1
            goto L2d
        L2c:
            r0 = 0
        L2d:
            if (r0 == 0) goto L34
        L30:
            r0 = 0
            goto L9d
        L34:
            javax.crypto.spec.SecretKeySpec r0 = new javax.crypto.spec.SecretKeySpec
            r1 = r0
            r2 = r7
            r3 = r8
            r1.<init>(r2, r3)
            r12 = r0
            r0 = r9
            javax.crypto.Cipher r0 = javax.crypto.Cipher.getInstance(r0)
            r13 = r0
            r0 = r11
            if (r0 == 0) goto L4f
            r0 = 1
            goto L50
        L4f:
            r0 = 2
        L50:
            r14 = r0
            r0 = r10
            if (r0 == 0) goto L6c
            r0 = r10
            r15 = r0
            r0 = 0
            r16 = r0
            r0 = r15
            int r0 = r0.length
            if (r0 != 0) goto L68
            r0 = 1
            goto L69
        L68:
            r0 = 0
        L69:
            if (r0 == 0) goto L7b
        L6c:
            r0 = r13
            r1 = r14
            r2 = r12
            java.security.Key r2 = (java.security.Key) r2
            r0.init(r1, r2)
            goto L97
        L7b:
            javax.crypto.spec.IvParameterSpec r0 = new javax.crypto.spec.IvParameterSpec
            r1 = r0
            r2 = r10
            r1.<init>(r2)
            java.security.spec.AlgorithmParameterSpec r0 = (java.security.spec.AlgorithmParameterSpec) r0
            r15 = r0
            r0 = r13
            r1 = r14
            r2 = r12
            java.security.Key r2 = (java.security.Key) r2
            r3 = r15
            r0.init(r1, r2, r3)
        L97:
            r0 = r13
            r1 = r6
            byte[] r0 = r0.doFinal(r1)
        L9d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.legado.app.utils.EncoderUtils.symmetricTemplate(byte[], byte[], java.lang.String, java.lang.String, byte[], boolean):byte[]");
    }

    public static /* synthetic */ byte[] encryptDES2Base64$default(EncoderUtils encoderUtils, byte[] bArr, byte[] bArr2, String str, byte[] bArr3, int i, Object obj) throws Exception {
        if ((i & 4) != 0) {
            str = "DES/ECB/PKCS5Padding";
        }
        if ((i & 8) != 0) {
            bArr3 = null;
        }
        return encoderUtils.encryptDES2Base64(bArr, bArr2, str, bArr3);
    }

    @Nullable
    public final byte[] encryptDES2Base64(@Nullable byte[] data, @Nullable byte[] key, @Nullable String transformation, @Nullable byte[] iv) throws Exception {
        return Base64.encode(encryptDES(data, key, transformation, iv), 2);
    }

    public static /* synthetic */ byte[] encryptDES$default(EncoderUtils encoderUtils, byte[] bArr, byte[] bArr2, String str, byte[] bArr3, int i, Object obj) throws Exception {
        if ((i & 4) != 0) {
            str = "DES/ECB/PKCS5Padding";
        }
        if ((i & 8) != 0) {
            bArr3 = null;
        }
        return encoderUtils.encryptDES(bArr, bArr2, str, bArr3);
    }

    @Nullable
    public final byte[] encryptDES(@Nullable byte[] data, @Nullable byte[] key, @Nullable String transformation, @Nullable byte[] iv) throws Exception {
        Intrinsics.checkNotNull(transformation);
        return symmetricTemplate(data, key, "DES", transformation, iv, true);
    }

    public static /* synthetic */ byte[] decryptBase64DES$default(EncoderUtils encoderUtils, byte[] bArr, byte[] bArr2, String str, byte[] bArr3, int i, Object obj) throws Exception {
        if ((i & 4) != 0) {
            str = "DES/ECB/PKCS5Padding";
        }
        if ((i & 8) != 0) {
            bArr3 = null;
        }
        return encoderUtils.decryptBase64DES(bArr, bArr2, str, bArr3);
    }

    @Nullable
    public final byte[] decryptBase64DES(@Nullable byte[] data, @Nullable byte[] key, @NotNull String transformation, @Nullable byte[] iv) throws Exception {
        Intrinsics.checkNotNullParameter(transformation, "transformation");
        return decryptDES(Base64.decode(data, 2), key, transformation, iv);
    }

    public static /* synthetic */ byte[] decryptDES$default(EncoderUtils encoderUtils, byte[] bArr, byte[] bArr2, String str, byte[] bArr3, int i, Object obj) throws Exception {
        if ((i & 4) != 0) {
            str = "DES/ECB/PKCS5Padding";
        }
        if ((i & 8) != 0) {
            bArr3 = null;
        }
        return encoderUtils.decryptDES(bArr, bArr2, str, bArr3);
    }

    @Nullable
    public final byte[] decryptDES(@Nullable byte[] data, @Nullable byte[] key, @NotNull String transformation, @Nullable byte[] iv) throws Exception {
        Intrinsics.checkNotNullParameter(transformation, "transformation");
        return symmetricTemplate(data, key, "DES", transformation, iv, false);
    }

    public static /* synthetic */ byte[] encryptDESede2Base64$default(EncoderUtils encoderUtils, byte[] bArr, byte[] bArr2, String str, byte[] bArr3, int i, Object obj) throws Exception {
        if ((i & 4) != 0) {
            str = "DESede/ECB/PKCS5Padding";
        }
        if ((i & 8) != 0) {
            bArr3 = null;
        }
        return encoderUtils.encryptDESede2Base64(bArr, bArr2, str, bArr3);
    }

    @Nullable
    public final byte[] encryptDESede2Base64(@Nullable byte[] data, @Nullable byte[] key, @Nullable String transformation, @Nullable byte[] iv) throws Exception {
        return Base64.encode(encryptDESede(data, key, transformation, iv), 2);
    }

    public static /* synthetic */ byte[] encryptDESede$default(EncoderUtils encoderUtils, byte[] bArr, byte[] bArr2, String str, byte[] bArr3, int i, Object obj) throws Exception {
        if ((i & 4) != 0) {
            str = "DESede/ECB/PKCS5Padding";
        }
        if ((i & 8) != 0) {
            bArr3 = null;
        }
        return encoderUtils.encryptDESede(bArr, bArr2, str, bArr3);
    }

    @Nullable
    public final byte[] encryptDESede(@Nullable byte[] data, @Nullable byte[] key, @Nullable String transformation, @Nullable byte[] iv) throws Exception {
        Intrinsics.checkNotNull(transformation);
        return symmetricTemplate(data, key, "DESede", transformation, iv, true);
    }

    public static /* synthetic */ byte[] decryptBase64DESede$default(EncoderUtils encoderUtils, byte[] bArr, byte[] bArr2, String str, byte[] bArr3, int i, Object obj) throws Exception {
        if ((i & 4) != 0) {
            str = "DESede/ECB/PKCS5Padding";
        }
        if ((i & 8) != 0) {
            bArr3 = null;
        }
        return encoderUtils.decryptBase64DESede(bArr, bArr2, str, bArr3);
    }

    @Nullable
    public final byte[] decryptBase64DESede(@Nullable byte[] data, @Nullable byte[] key, @NotNull String transformation, @Nullable byte[] iv) throws Exception {
        Intrinsics.checkNotNullParameter(transformation, "transformation");
        return decryptDESede(Base64.decode(data, 2), key, transformation, iv);
    }

    public static /* synthetic */ byte[] decryptDESede$default(EncoderUtils encoderUtils, byte[] bArr, byte[] bArr2, String str, byte[] bArr3, int i, Object obj) throws Exception {
        if ((i & 4) != 0) {
            str = "DESede/ECB/PKCS5Padding";
        }
        if ((i & 8) != 0) {
            bArr3 = null;
        }
        return encoderUtils.decryptDESede(bArr, bArr2, str, bArr3);
    }

    @Nullable
    public final byte[] decryptDESede(@Nullable byte[] data, @Nullable byte[] key, @NotNull String transformation, @Nullable byte[] iv) throws Exception {
        Intrinsics.checkNotNullParameter(transformation, "transformation");
        return symmetricTemplate(data, key, "DESede", transformation, iv, false);
    }

    @NotNull
    public final String encryptByPrivateKey(@NotNull String input, @NotNull PrivateKey privateKey) throws BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeyException {
        Intrinsics.checkNotNullParameter(input, "input");
        Intrinsics.checkNotNullParameter(privateKey, "privateKey");
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(1, privateKey);
        byte[] bytes = input.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "(this as java.lang.String).getBytes(charset)");
        byte[] encrypt = cipher.doFinal(bytes);
        String strEncodeToString = Base64.encodeToString(encrypt, 2);
        Intrinsics.checkNotNullExpressionValue(strEncodeToString, "encodeToString(encrypt, Base64.NO_WRAP)");
        return strEncodeToString;
    }

    @NotNull
    public final String decryptByPublicKey(@NotNull String input, @NotNull PublicKey publicKey) throws BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeyException {
        Intrinsics.checkNotNullParameter(input, "input");
        Intrinsics.checkNotNullParameter(publicKey, "publicKey");
        byte[] decode = Base64.decode(input, 2);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(2, publicKey);
        byte[] encrypt = cipher.doFinal(decode);
        Intrinsics.checkNotNullExpressionValue(encrypt, "encrypt");
        return new String(encrypt, Charsets.UTF_8);
    }

    @NotNull
    public final String encryptByPublicKey(@NotNull String input, @NotNull PublicKey publicKey) throws BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeyException {
        Intrinsics.checkNotNullParameter(input, "input");
        Intrinsics.checkNotNullParameter(publicKey, "publicKey");
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(1, publicKey);
        byte[] bytes = input.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "(this as java.lang.String).getBytes(charset)");
        byte[] encrypt = cipher.doFinal(bytes);
        String strEncodeToString = Base64.encodeToString(encrypt, 2);
        Intrinsics.checkNotNullExpressionValue(strEncodeToString, "encodeToString(encrypt, Base64.NO_WRAP)");
        return strEncodeToString;
    }

    @NotNull
    public final String decryptByPrivateKey(@NotNull String input, @NotNull PrivateKey privateKey) throws BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeyException {
        Intrinsics.checkNotNullParameter(input, "input");
        Intrinsics.checkNotNullParameter(privateKey, "privateKey");
        byte[] decode = Base64.decode(input, 2);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(2, privateKey);
        byte[] encrypt = cipher.doFinal(decode);
        Intrinsics.checkNotNullExpressionValue(encrypt, "encrypt");
        return new String(encrypt, Charsets.UTF_8);
    }

    public static /* synthetic */ String encryptSegmentByPrivateKey$default(EncoderUtils encoderUtils, String str, PrivateKey privateKey, int i, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = 2048;
        }
        return encoderUtils.encryptSegmentByPrivateKey(str, privateKey, i);
    }

    @NotNull
    public final String encryptSegmentByPrivateKey(@NotNull String input, @NotNull PrivateKey privateKey, int keySize) throws BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeyException, IOException {
        byte[] temp;
        int length;
        Intrinsics.checkNotNullParameter(input, "input");
        Intrinsics.checkNotNullParameter(privateKey, "privateKey");
        byte[] byteArray = input.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(byteArray, "(this as java.lang.String).getBytes(charset)");
        int offset = 0;
        int MAX_ENCRYPT_BLOCK = (keySize / 8) - 11;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(1, privateKey);
        while (byteArray.length - offset > 0) {
            if (byteArray.length - offset >= MAX_ENCRYPT_BLOCK) {
                byte[] bArrDoFinal = cipher.doFinal(byteArray, offset, MAX_ENCRYPT_BLOCK);
                Intrinsics.checkNotNullExpressionValue(bArrDoFinal, "cipher.doFinal(byteArray, offset, MAX_ENCRYPT_BLOCK)");
                temp = bArrDoFinal;
                length = offset + MAX_ENCRYPT_BLOCK;
            } else {
                byte[] bArrDoFinal2 = cipher.doFinal(byteArray, offset, byteArray.length - offset);
                Intrinsics.checkNotNullExpressionValue(bArrDoFinal2, "cipher.doFinal(byteArray, offset, byteArray.size - offset)");
                temp = bArrDoFinal2;
                length = byteArray.length;
            }
            offset = length;
            bos.write(temp);
        }
        bos.close();
        String strEncodeToString = Base64.encodeToString(bos.toByteArray(), 2);
        Intrinsics.checkNotNullExpressionValue(strEncodeToString, "encodeToString(bos.toByteArray(), Base64.NO_WRAP)");
        return strEncodeToString;
    }

    public static /* synthetic */ String decryptSegmentByPublicKey$default(EncoderUtils encoderUtils, String str, PublicKey publicKey, int i, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = 2048;
        }
        return encoderUtils.decryptSegmentByPublicKey(str, publicKey, i);
    }

    @Nullable
    public final String decryptSegmentByPublicKey(@NotNull String input, @NotNull PublicKey publicKey, int keySize) throws BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeyException, IOException {
        byte[] temp;
        int length;
        Intrinsics.checkNotNullParameter(input, "input");
        Intrinsics.checkNotNullParameter(publicKey, "publicKey");
        byte[] byteArray = Base64.decode(input, 2);
        int offset = 0;
        int MAX_DECRYPT_BLOCK = keySize / 8;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(2, publicKey);
        while (byteArray.length - offset > 0) {
            if (byteArray.length - offset >= MAX_DECRYPT_BLOCK) {
                byte[] bArrDoFinal = cipher.doFinal(byteArray, offset, MAX_DECRYPT_BLOCK);
                Intrinsics.checkNotNullExpressionValue(bArrDoFinal, "cipher.doFinal(byteArray, offset, MAX_DECRYPT_BLOCK)");
                temp = bArrDoFinal;
                length = offset + MAX_DECRYPT_BLOCK;
            } else {
                byte[] bArrDoFinal2 = cipher.doFinal(byteArray, offset, byteArray.length - offset);
                Intrinsics.checkNotNullExpressionValue(bArrDoFinal2, "cipher.doFinal(byteArray, offset, byteArray.size - offset)");
                temp = bArrDoFinal2;
                length = byteArray.length;
            }
            offset = length;
            bos.write(temp);
        }
        bos.close();
        byte[] byteArray2 = bos.toByteArray();
        Intrinsics.checkNotNullExpressionValue(byteArray2, "bos.toByteArray()");
        return new String(byteArray2, Charsets.UTF_8);
    }

    public static /* synthetic */ String encryptSegmentByPublicKey$default(EncoderUtils encoderUtils, String str, PublicKey publicKey, int i, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = 2048;
        }
        return encoderUtils.encryptSegmentByPublicKey(str, publicKey, i);
    }

    @NotNull
    public final String encryptSegmentByPublicKey(@NotNull String input, @NotNull PublicKey publicKey, int keySize) throws BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeyException, IOException {
        byte[] temp;
        int length;
        Intrinsics.checkNotNullParameter(input, "input");
        Intrinsics.checkNotNullParameter(publicKey, "publicKey");
        byte[] byteArray = input.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(byteArray, "(this as java.lang.String).getBytes(charset)");
        int offset = 0;
        int MAX_ENCRYPT_BLOCK = (keySize / 8) - 11;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(1, publicKey);
        while (byteArray.length - offset > 0) {
            if (byteArray.length - offset >= MAX_ENCRYPT_BLOCK) {
                byte[] bArrDoFinal = cipher.doFinal(byteArray, offset, MAX_ENCRYPT_BLOCK);
                Intrinsics.checkNotNullExpressionValue(bArrDoFinal, "cipher.doFinal(byteArray, offset, MAX_ENCRYPT_BLOCK)");
                temp = bArrDoFinal;
                length = offset + MAX_ENCRYPT_BLOCK;
            } else {
                byte[] bArrDoFinal2 = cipher.doFinal(byteArray, offset, byteArray.length - offset);
                Intrinsics.checkNotNullExpressionValue(bArrDoFinal2, "cipher.doFinal(byteArray, offset, byteArray.size - offset)");
                temp = bArrDoFinal2;
                length = byteArray.length;
            }
            offset = length;
            bos.write(temp);
        }
        bos.close();
        String strEncodeToString = Base64.encodeToString(bos.toByteArray(), 2);
        Intrinsics.checkNotNullExpressionValue(strEncodeToString, "encodeToString(bos.toByteArray(), Base64.NO_WRAP)");
        return strEncodeToString;
    }

    public static /* synthetic */ String decryptSegmentByPrivateKey$default(EncoderUtils encoderUtils, String str, PrivateKey privateKey, int i, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = 2048;
        }
        return encoderUtils.decryptSegmentByPrivateKey(str, privateKey, i);
    }

    @Nullable
    public final String decryptSegmentByPrivateKey(@NotNull String input, @NotNull PrivateKey privateKey, int keySize) throws BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeyException, IOException {
        byte[] temp;
        int length;
        Intrinsics.checkNotNullParameter(input, "input");
        Intrinsics.checkNotNullParameter(privateKey, "privateKey");
        byte[] byteArray = Base64.decode(input, 2);
        int offset = 0;
        int MAX_DECRYPT_BLOCK = keySize / 8;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(2, privateKey);
        while (byteArray.length - offset > 0) {
            if (byteArray.length - offset >= MAX_DECRYPT_BLOCK) {
                byte[] bArrDoFinal = cipher.doFinal(byteArray, offset, MAX_DECRYPT_BLOCK);
                Intrinsics.checkNotNullExpressionValue(bArrDoFinal, "cipher.doFinal(byteArray, offset, MAX_DECRYPT_BLOCK)");
                temp = bArrDoFinal;
                length = offset + MAX_DECRYPT_BLOCK;
            } else {
                byte[] bArrDoFinal2 = cipher.doFinal(byteArray, offset, byteArray.length - offset);
                Intrinsics.checkNotNullExpressionValue(bArrDoFinal2, "cipher.doFinal(byteArray, offset, byteArray.size - offset)");
                temp = bArrDoFinal2;
                length = byteArray.length;
            }
            offset = length;
            bos.write(temp);
        }
        bos.close();
        byte[] byteArray2 = bos.toByteArray();
        Intrinsics.checkNotNullExpressionValue(byteArray2, "bos.toByteArray()");
        return new String(byteArray2, Charsets.UTF_8);
    }

    @NotNull
    public final KeyPair generateKeys() throws NoSuchAlgorithmException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        KeyPair keyPairGenKeyPair = generator.genKeyPair();
        Intrinsics.checkNotNullExpressionValue(keyPairGenKeyPair, "generator.genKeyPair()");
        return keyPairGenKeyPair;
    }
}
