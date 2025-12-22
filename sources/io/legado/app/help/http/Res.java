package io.legado.app.help.http;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import me.ag2s.epublib.epub.NCXDocumentV3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.jmx.export.naming.IdentityNamingStrategy;

/* compiled from: Res.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��\"\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018��2\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\n\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u001f\u0010\u000b\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, d2 = {"Lio/legado/app/help/http/Res;", "", "url", "", NCXDocumentV3.XHTMLTgs.body, "(Ljava/lang/String;Ljava/lang/String;)V", "getBody", "()Ljava/lang/String;", "getUrl", "component1", "component2", "copy", "equals", "", "other", IdentityNamingStrategy.HASH_CODE_KEY, "", "toString", "reader-pro"})
/* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/help/http/Res.class */
public final class Res {

    @NotNull
    private final String url;

    @Nullable
    private final String body;

    @NotNull
    public final String component1() {
        return this.url;
    }

    @Nullable
    public final String component2() {
        return this.body;
    }

    @NotNull
    public final Res copy(@NotNull String url, @Nullable String body) {
        Intrinsics.checkNotNullParameter(url, "url");
        return new Res(url, body);
    }

    public static /* synthetic */ Res copy$default(Res res, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = res.url;
        }
        if ((i & 2) != 0) {
            str2 = res.body;
        }
        return res.copy(str, str2);
    }

    @NotNull
    public String toString() {
        return "Res(url=" + this.url + ", body=" + ((Object) this.body) + ')';
    }

    public int hashCode() {
        int result = this.url.hashCode();
        return (result * 31) + (this.body == null ? 0 : this.body.hashCode());
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Res)) {
            return false;
        }
        Res res = (Res) other;
        return Intrinsics.areEqual(this.url, res.url) && Intrinsics.areEqual(this.body, res.body);
    }

    public Res(@NotNull String url, @Nullable String body) {
        Intrinsics.checkNotNullParameter(url, "url");
        this.url = url;
        this.body = body;
    }

    @NotNull
    public final String getUrl() {
        return this.url;
    }

    @Nullable
    public final String getBody() {
        return this.body;
    }
}
