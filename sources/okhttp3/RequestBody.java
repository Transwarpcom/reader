package okhttp3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import okhttp3.internal.Util;
import okio.BufferedSink;
import okio.ByteString;
import okio.Okio;
import okio.Source;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: RequestBody.kt */
@Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"��.\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0010\t\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018�� \u000e2\u00020\u0001:\u0001\u000eB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\n\u0010\u0005\u001a\u0004\u0018\u00010\u0006H&J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\bH\u0016J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH&¨\u0006\u000f"}, d2 = {"Lokhttp3/RequestBody;", "", "()V", "contentLength", "", "contentType", "Lokhttp3/MediaType;", "isDuplex", "", "isOneShot", "writeTo", "", "sink", "Lokio/BufferedSink;", "Companion", "okhttp"})
/* loaded from: reader.jar:BOOT-INF/lib/okhttp-4.9.1.jar:okhttp3/RequestBody.class */
public abstract class RequestBody {
    public static final Companion Companion = new Companion(null);

    @Nullable
    public abstract MediaType contentType();

    public abstract void writeTo(@NotNull BufferedSink bufferedSink) throws IOException;

    @JvmStatic
    @JvmName(name = "create")
    @NotNull
    public static final RequestBody create(@NotNull String $this$toRequestBody, @Nullable MediaType contentType) {
        return Companion.create($this$toRequestBody, contentType);
    }

    @JvmStatic
    @JvmName(name = "create")
    @NotNull
    public static final RequestBody create(@NotNull ByteString $this$toRequestBody, @Nullable MediaType contentType) {
        return Companion.create($this$toRequestBody, contentType);
    }

    @JvmStatic
    @JvmName(name = "create")
    @NotNull
    @JvmOverloads
    public static final RequestBody create(@NotNull byte[] $this$toRequestBody, @Nullable MediaType contentType, int offset, int byteCount) {
        return Companion.create($this$toRequestBody, contentType, offset, byteCount);
    }

    @JvmStatic
    @JvmName(name = "create")
    @NotNull
    @JvmOverloads
    public static final RequestBody create(@NotNull byte[] $this$toRequestBody, @Nullable MediaType contentType, int offset) {
        return Companion.create$default(Companion, $this$toRequestBody, contentType, offset, 0, 4, (Object) null);
    }

    @JvmStatic
    @JvmName(name = "create")
    @NotNull
    @JvmOverloads
    public static final RequestBody create(@NotNull byte[] $this$toRequestBody, @Nullable MediaType contentType) {
        return Companion.create$default(Companion, $this$toRequestBody, contentType, 0, 0, 6, (Object) null);
    }

    @JvmStatic
    @JvmName(name = "create")
    @NotNull
    @JvmOverloads
    public static final RequestBody create(@NotNull byte[] $this$toRequestBody) {
        return Companion.create$default(Companion, $this$toRequestBody, (MediaType) null, 0, 0, 7, (Object) null);
    }

    @JvmStatic
    @JvmName(name = "create")
    @NotNull
    public static final RequestBody create(@NotNull File $this$asRequestBody, @Nullable MediaType contentType) {
        return Companion.create($this$asRequestBody, contentType);
    }

    @JvmStatic
    @Deprecated(message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(imports = {"okhttp3.RequestBody.Companion.toRequestBody"}, expression = "content.toRequestBody(contentType)"), level = DeprecationLevel.WARNING)
    @NotNull
    public static final RequestBody create(@Nullable MediaType contentType, @NotNull String content) {
        return Companion.create(contentType, content);
    }

    @JvmStatic
    @Deprecated(message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(imports = {"okhttp3.RequestBody.Companion.toRequestBody"}, expression = "content.toRequestBody(contentType)"), level = DeprecationLevel.WARNING)
    @NotNull
    public static final RequestBody create(@Nullable MediaType contentType, @NotNull ByteString content) {
        return Companion.create(contentType, content);
    }

    @JvmStatic
    @NotNull
    @Deprecated(message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(imports = {"okhttp3.RequestBody.Companion.toRequestBody"}, expression = "content.toRequestBody(contentType, offset, byteCount)"), level = DeprecationLevel.WARNING)
    @JvmOverloads
    public static final RequestBody create(@Nullable MediaType contentType, @NotNull byte[] content, int offset, int byteCount) {
        return Companion.create(contentType, content, offset, byteCount);
    }

    @JvmStatic
    @NotNull
    @Deprecated(message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(imports = {"okhttp3.RequestBody.Companion.toRequestBody"}, expression = "content.toRequestBody(contentType, offset, byteCount)"), level = DeprecationLevel.WARNING)
    @JvmOverloads
    public static final RequestBody create(@Nullable MediaType contentType, @NotNull byte[] content, int offset) {
        return Companion.create$default(Companion, contentType, content, offset, 0, 8, (Object) null);
    }

    @JvmStatic
    @NotNull
    @Deprecated(message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(imports = {"okhttp3.RequestBody.Companion.toRequestBody"}, expression = "content.toRequestBody(contentType, offset, byteCount)"), level = DeprecationLevel.WARNING)
    @JvmOverloads
    public static final RequestBody create(@Nullable MediaType contentType, @NotNull byte[] content) {
        return Companion.create$default(Companion, contentType, content, 0, 0, 12, (Object) null);
    }

    @JvmStatic
    @Deprecated(message = "Moved to extension function. Put the 'file' argument first to fix Java", replaceWith = @ReplaceWith(imports = {"okhttp3.RequestBody.Companion.asRequestBody"}, expression = "file.asRequestBody(contentType)"), level = DeprecationLevel.WARNING)
    @NotNull
    public static final RequestBody create(@Nullable MediaType contentType, @NotNull File file) {
        return Companion.create(contentType, file);
    }

    public long contentLength() throws IOException {
        return -1L;
    }

    public boolean isDuplex() {
        return false;
    }

    public boolean isOneShot() {
        return false;
    }

    /* compiled from: RequestBody.kt */
    @Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"��6\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0012\n��\n\u0002\u0010\b\n��\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018��2\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J.\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\fH\u0007J\u001a\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\t\u001a\u00020\u000eH\u0007J\u001a\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\t\u001a\u00020\u000fH\u0007J\u001d\u0010\u0010\u001a\u00020\u0004*\u00020\b2\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007¢\u0006\u0002\b\u0003J1\u0010\u0011\u001a\u00020\u0004*\u00020\n2\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\fH\u0007¢\u0006\u0002\b\u0003J\u001d\u0010\u0011\u001a\u00020\u0004*\u00020\u000e2\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007¢\u0006\u0002\b\u0003J\u001d\u0010\u0011\u001a\u00020\u0004*\u00020\u000f2\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007¢\u0006\u0002\b\u0003¨\u0006\u0012"}, d2 = {"Lokhttp3/RequestBody$Companion;", "", "()V", "create", "Lokhttp3/RequestBody;", "contentType", "Lokhttp3/MediaType;", "file", "Ljava/io/File;", "content", "", "offset", "", "byteCount", "", "Lokio/ByteString;", "asRequestBody", "toRequestBody", "okhttp"})
    /* loaded from: reader.jar:BOOT-INF/lib/okhttp-4.9.1.jar:okhttp3/RequestBody$Companion.class */
    public static final class Companion {
        @JvmStatic
        @JvmName(name = "create")
        @NotNull
        @JvmOverloads
        public final RequestBody create(@NotNull byte[] $this$toRequestBody, @Nullable MediaType contentType, int offset) {
            return create$default(this, $this$toRequestBody, contentType, offset, 0, 4, (Object) null);
        }

        @JvmStatic
        @JvmName(name = "create")
        @NotNull
        @JvmOverloads
        public final RequestBody create(@NotNull byte[] $this$toRequestBody, @Nullable MediaType contentType) {
            return create$default(this, $this$toRequestBody, contentType, 0, 0, 6, (Object) null);
        }

        @JvmStatic
        @JvmName(name = "create")
        @NotNull
        @JvmOverloads
        public final RequestBody create(@NotNull byte[] $this$toRequestBody) {
            return create$default(this, $this$toRequestBody, (MediaType) null, 0, 0, 7, (Object) null);
        }

        @JvmStatic
        @NotNull
        @Deprecated(message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(imports = {"okhttp3.RequestBody.Companion.toRequestBody"}, expression = "content.toRequestBody(contentType, offset, byteCount)"), level = DeprecationLevel.WARNING)
        @JvmOverloads
        public final RequestBody create(@Nullable MediaType contentType, @NotNull byte[] content, int offset) {
            return create$default(this, contentType, content, offset, 0, 8, (Object) null);
        }

        @JvmStatic
        @NotNull
        @Deprecated(message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(imports = {"okhttp3.RequestBody.Companion.toRequestBody"}, expression = "content.toRequestBody(contentType, offset, byteCount)"), level = DeprecationLevel.WARNING)
        @JvmOverloads
        public final RequestBody create(@Nullable MediaType contentType, @NotNull byte[] content) {
            return create$default(this, contentType, content, 0, 0, 12, (Object) null);
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        public static /* synthetic */ RequestBody create$default(Companion companion, String str, MediaType mediaType, int i, Object obj) {
            if ((i & 1) != 0) {
                mediaType = (MediaType) null;
            }
            return companion.create(str, mediaType);
        }

        @JvmStatic
        @JvmName(name = "create")
        @NotNull
        public final RequestBody create(@NotNull String toRequestBody, @Nullable MediaType contentType) {
            Intrinsics.checkNotNullParameter(toRequestBody, "$this$toRequestBody");
            Charset charset = Charsets.UTF_8;
            MediaType finalContentType = contentType;
            if (contentType != null) {
                Charset resolvedCharset = MediaType.charset$default(contentType, null, 1, null);
                if (resolvedCharset == null) {
                    charset = Charsets.UTF_8;
                    finalContentType = MediaType.Companion.parse(contentType + "; charset=utf-8");
                } else {
                    charset = resolvedCharset;
                }
            }
            byte[] bytes = toRequestBody.getBytes(charset);
            Intrinsics.checkNotNullExpressionValue(bytes, "(this as java.lang.String).getBytes(charset)");
            return create(bytes, finalContentType, 0, bytes.length);
        }

        public static /* synthetic */ RequestBody create$default(Companion companion, ByteString byteString, MediaType mediaType, int i, Object obj) {
            if ((i & 1) != 0) {
                mediaType = (MediaType) null;
            }
            return companion.create(byteString, mediaType);
        }

        @JvmStatic
        @JvmName(name = "create")
        @NotNull
        public final RequestBody create(@NotNull final ByteString toRequestBody, @Nullable final MediaType contentType) {
            Intrinsics.checkNotNullParameter(toRequestBody, "$this$toRequestBody");
            return new RequestBody() { // from class: okhttp3.RequestBody$Companion$toRequestBody$1
                @Override // okhttp3.RequestBody
                @Nullable
                public MediaType contentType() {
                    return contentType;
                }

                @Override // okhttp3.RequestBody
                public long contentLength() {
                    return toRequestBody.size();
                }

                @Override // okhttp3.RequestBody
                public void writeTo(@NotNull BufferedSink sink) throws IOException {
                    Intrinsics.checkNotNullParameter(sink, "sink");
                    sink.write(toRequestBody);
                }
            };
        }

        public static /* synthetic */ RequestBody create$default(Companion companion, byte[] bArr, MediaType mediaType, int i, int i2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                mediaType = (MediaType) null;
            }
            if ((i3 & 2) != 0) {
                i = 0;
            }
            if ((i3 & 4) != 0) {
                i2 = bArr.length;
            }
            return companion.create(bArr, mediaType, i, i2);
        }

        @JvmStatic
        @JvmName(name = "create")
        @NotNull
        @JvmOverloads
        public final RequestBody create(@NotNull final byte[] toRequestBody, @Nullable final MediaType contentType, final int offset, final int byteCount) {
            Intrinsics.checkNotNullParameter(toRequestBody, "$this$toRequestBody");
            Util.checkOffsetAndCount(toRequestBody.length, offset, byteCount);
            return new RequestBody() { // from class: okhttp3.RequestBody$Companion$toRequestBody$2
                @Override // okhttp3.RequestBody
                @Nullable
                public MediaType contentType() {
                    return contentType;
                }

                @Override // okhttp3.RequestBody
                public long contentLength() {
                    return byteCount;
                }

                @Override // okhttp3.RequestBody
                public void writeTo(@NotNull BufferedSink sink) throws IOException {
                    Intrinsics.checkNotNullParameter(sink, "sink");
                    sink.write(toRequestBody, offset, byteCount);
                }
            };
        }

        public static /* synthetic */ RequestBody create$default(Companion companion, File file, MediaType mediaType, int i, Object obj) {
            if ((i & 1) != 0) {
                mediaType = (MediaType) null;
            }
            return companion.create(file, mediaType);
        }

        @JvmStatic
        @JvmName(name = "create")
        @NotNull
        public final RequestBody create(@NotNull final File asRequestBody, @Nullable final MediaType contentType) {
            Intrinsics.checkNotNullParameter(asRequestBody, "$this$asRequestBody");
            return new RequestBody() { // from class: okhttp3.RequestBody$Companion$asRequestBody$1
                @Override // okhttp3.RequestBody
                @Nullable
                public MediaType contentType() {
                    return contentType;
                }

                @Override // okhttp3.RequestBody
                public long contentLength() {
                    return asRequestBody.length();
                }

                @Override // okhttp3.RequestBody
                public void writeTo(@NotNull BufferedSink sink) throws FileNotFoundException {
                    Intrinsics.checkNotNullParameter(sink, "sink");
                    Source source = Okio.source(asRequestBody);
                    Throwable th = (Throwable) null;
                    try {
                        try {
                            Source source2 = source;
                            sink.writeAll(source2);
                            CloseableKt.closeFinally(source, th);
                        } finally {
                        }
                    } catch (Throwable th2) {
                        CloseableKt.closeFinally(source, th);
                        throw th2;
                    }
                }
            };
        }

        @JvmStatic
        @Deprecated(message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(imports = {"okhttp3.RequestBody.Companion.toRequestBody"}, expression = "content.toRequestBody(contentType)"), level = DeprecationLevel.WARNING)
        @NotNull
        public final RequestBody create(@Nullable MediaType contentType, @NotNull String content) {
            Intrinsics.checkNotNullParameter(content, "content");
            return create(content, contentType);
        }

        @JvmStatic
        @Deprecated(message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(imports = {"okhttp3.RequestBody.Companion.toRequestBody"}, expression = "content.toRequestBody(contentType)"), level = DeprecationLevel.WARNING)
        @NotNull
        public final RequestBody create(@Nullable MediaType contentType, @NotNull ByteString content) {
            Intrinsics.checkNotNullParameter(content, "content");
            return create(content, contentType);
        }

        public static /* synthetic */ RequestBody create$default(Companion companion, MediaType mediaType, byte[] bArr, int i, int i2, int i3, Object obj) {
            if ((i3 & 4) != 0) {
                i = 0;
            }
            if ((i3 & 8) != 0) {
                i2 = bArr.length;
            }
            return companion.create(mediaType, bArr, i, i2);
        }

        @JvmStatic
        @NotNull
        @Deprecated(message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(imports = {"okhttp3.RequestBody.Companion.toRequestBody"}, expression = "content.toRequestBody(contentType, offset, byteCount)"), level = DeprecationLevel.WARNING)
        @JvmOverloads
        public final RequestBody create(@Nullable MediaType contentType, @NotNull byte[] content, int offset, int byteCount) {
            Intrinsics.checkNotNullParameter(content, "content");
            return create(content, contentType, offset, byteCount);
        }

        @JvmStatic
        @Deprecated(message = "Moved to extension function. Put the 'file' argument first to fix Java", replaceWith = @ReplaceWith(imports = {"okhttp3.RequestBody.Companion.asRequestBody"}, expression = "file.asRequestBody(contentType)"), level = DeprecationLevel.WARNING)
        @NotNull
        public final RequestBody create(@Nullable MediaType contentType, @NotNull File file) {
            Intrinsics.checkNotNullParameter(file, "file");
            return create(file, contentType);
        }
    }
}
