package okhttp3.internal.http1;

import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import me.ag2s.epublib.epub.PackageDocumentBase;
import okhttp3.Headers;
import okio.BufferedSource;
import org.jetbrains.annotations.NotNull;

/* compiled from: HeadersReader.kt */
@Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"��(\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018�� \r2\u00020\u0001:\u0001\rB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\t\u001a\u00020\nJ\u0006\u0010\u000b\u001a\u00020\fR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n��R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0007\u0010\b¨\u0006\u000e"}, d2 = {"Lokhttp3/internal/http1/HeadersReader;", "", PackageDocumentBase.DCTags.source, "Lokio/BufferedSource;", "(Lokio/BufferedSource;)V", "headerLimit", "", "getSource", "()Lokio/BufferedSource;", "readHeaders", "Lokhttp3/Headers;", "readLine", "", "Companion", "okhttp"})
/* loaded from: reader.jar:BOOT-INF/lib/okhttp-4.9.1.jar:okhttp3/internal/http1/HeadersReader.class */
public final class HeadersReader {
    private long headerLimit;

    @NotNull
    private final BufferedSource source;
    private static final int HEADER_LIMIT = 262144;
    public static final Companion Companion = new Companion(null);

    @NotNull
    public final BufferedSource getSource() {
        return this.source;
    }

    public HeadersReader(@NotNull BufferedSource source) {
        Intrinsics.checkNotNullParameter(source, "source");
        this.source = source;
        this.headerLimit = 262144;
    }

    @NotNull
    public final String readLine() throws IOException {
        String line = this.source.readUtf8LineStrict(this.headerLimit);
        this.headerLimit -= line.length();
        return line;
    }

    @NotNull
    public final Headers readHeaders() throws IOException {
        Headers.Builder result = new Headers.Builder();
        while (true) {
            String line = readLine();
            if (!(line.length() == 0)) {
                result.addLenient$okhttp(line);
            } else {
                return result.build();
            }
        }
    }

    /* compiled from: HeadersReader.kt */
    @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0010\b\n��\b\u0086\u0003\u0018��2\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n��¨\u0006\u0005"}, d2 = {"Lokhttp3/internal/http1/HeadersReader$Companion;", "", "()V", "HEADER_LIMIT", "", "okhttp"})
    /* loaded from: reader.jar:BOOT-INF/lib/okhttp-4.9.1.jar:okhttp3/internal/http1/HeadersReader$Companion.class */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}
