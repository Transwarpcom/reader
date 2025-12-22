package okhttp3.internal.cache;

import java.io.IOException;
import kotlin.Metadata;
import me.ag2s.epublib.epub.NCXDocumentV3;
import okio.Sink;
import org.jetbrains.annotations.NotNull;

/* compiled from: CacheRequest.kt */
@Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"��\u0016\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n��\bf\u0018��2\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lokhttp3/internal/cache/CacheRequest;", "", "abort", "", NCXDocumentV3.XHTMLTgs.body, "Lokio/Sink;", "okhttp"})
/* loaded from: reader.jar:BOOT-INF/lib/okhttp-4.9.1.jar:okhttp3/internal/cache/CacheRequest.class */
public interface CacheRequest {
    @NotNull
    Sink body() throws IOException;

    void abort();
}
