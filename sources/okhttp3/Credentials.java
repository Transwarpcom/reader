package okhttp3;

import io.vertx.ext.web.handler.FormLoginHandler;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;

/* compiled from: Credentials.kt */
@Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"��\u001a\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\bÆ\u0002\u0018��2\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\"\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\bH\u0007¨\u0006\t"}, d2 = {"Lokhttp3/Credentials;", "", "()V", "basic", "", FormLoginHandler.DEFAULT_USERNAME_PARAM, FormLoginHandler.DEFAULT_PASSWORD_PARAM, "charset", "Ljava/nio/charset/Charset;", "okhttp"})
/* loaded from: reader.jar:BOOT-INF/lib/okhttp-4.9.1.jar:okhttp3/Credentials.class */
public final class Credentials {
    public static final Credentials INSTANCE = new Credentials();

    @JvmStatic
    @JvmOverloads
    @NotNull
    public static final String basic(@NotNull String username, @NotNull String password) {
        return basic$default(username, password, null, 4, null);
    }

    private Credentials() {
    }

    public static /* synthetic */ String basic$default(String str, String str2, Charset charset, int i, Object obj) {
        if ((i & 4) != 0) {
            Charset ISO_8859_1 = StandardCharsets.ISO_8859_1;
            Intrinsics.checkNotNullExpressionValue(ISO_8859_1, "ISO_8859_1");
            charset = ISO_8859_1;
        }
        return basic(str, str2, charset);
    }

    @JvmStatic
    @JvmOverloads
    @NotNull
    public static final String basic(@NotNull String username, @NotNull String password, @NotNull Charset charset) {
        Intrinsics.checkNotNullParameter(username, "username");
        Intrinsics.checkNotNullParameter(password, "password");
        Intrinsics.checkNotNullParameter(charset, "charset");
        String usernameAndPassword = username + ':' + password;
        String encoded = ByteString.Companion.encodeString(usernameAndPassword, charset).base64();
        return "Basic " + encoded;
    }
}
