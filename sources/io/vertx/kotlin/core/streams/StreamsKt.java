package io.vertx.kotlin.core.streams;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.streams.WriteStream;
import io.vertx.kotlin.core.json.Json;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: streams.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��(\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aE\u0010��\u001a\u0002H\u0001\"\u000e\b��\u0010\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002*\u0002H\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0017\u0010\u0006\u001a\u0013\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\u0002\b\nH\u0086\b¢\u0006\u0002\u0010\u000b\u001aE\u0010\f\u001a\u0002H\u0001\"\u000e\b��\u0010\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002*\u0002H\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0017\u0010\u0006\u001a\u0013\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\u0002\b\nH\u0086\b¢\u0006\u0002\u0010\u000b¨\u0006\r"}, d2 = {"end", "S", "Lio/vertx/core/streams/WriteStream;", "Lio/vertx/core/buffer/Buffer;", "pretty", "", "block", "Lkotlin/Function1;", "Lio/vertx/kotlin/core/json/Json;", "", "Lkotlin/ExtensionFunctionType;", "(Lio/vertx/core/streams/WriteStream;ZLkotlin/jvm/functions/Function1;)Lio/vertx/core/streams/WriteStream;", "write", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/core/streams/StreamsKt.class */
public final class StreamsKt {
    @NotNull
    public static /* synthetic */ WriteStream write$default(WriteStream write, boolean pretty, Function1 block, int i, Object obj) {
        if ((i & 1) != 0) {
            pretty = false;
        }
        Intrinsics.checkParameterIsNotNull(write, "$this$write");
        Intrinsics.checkParameterIsNotNull(block, "block");
        Buffer $this$appendJson$iv = Buffer.buffer();
        Intrinsics.checkExpressionValueIsNotNull($this$appendJson$iv, "Buffer.buffer()");
        Object json$iv = block.invoke(Json.INSTANCE);
        String encoded$iv = pretty ? io.vertx.core.json.Json.encodePrettily(json$iv) : io.vertx.core.json.Json.encode(json$iv);
        Buffer bufferAppendString = $this$appendJson$iv.appendString(encoded$iv);
        Intrinsics.checkExpressionValueIsNotNull(bufferAppendString, "appendString(encoded)");
        Intrinsics.checkExpressionValueIsNotNull(bufferAppendString, "block(Json).let { json -…> appendString(encoded) }");
        write.write(bufferAppendString);
        return write;
    }

    @NotNull
    public static final <S extends WriteStream<Buffer>> S write(@NotNull S write, boolean pretty, @NotNull Function1<? super Json, ? extends Object> block) {
        Intrinsics.checkParameterIsNotNull(write, "$this$write");
        Intrinsics.checkParameterIsNotNull(block, "block");
        Buffer $this$appendJson$iv = Buffer.buffer();
        Intrinsics.checkExpressionValueIsNotNull($this$appendJson$iv, "Buffer.buffer()");
        Object json$iv = block.invoke(Json.INSTANCE);
        String encoded$iv = pretty ? io.vertx.core.json.Json.encodePrettily(json$iv) : io.vertx.core.json.Json.encode(json$iv);
        Buffer bufferAppendString = $this$appendJson$iv.appendString(encoded$iv);
        Intrinsics.checkExpressionValueIsNotNull(bufferAppendString, "appendString(encoded)");
        Intrinsics.checkExpressionValueIsNotNull(bufferAppendString, "block(Json).let { json -…> appendString(encoded) }");
        write.write(bufferAppendString);
        return write;
    }

    @NotNull
    public static /* synthetic */ WriteStream end$default(WriteStream end, boolean pretty, Function1 block, int i, Object obj) {
        if ((i & 1) != 0) {
            pretty = false;
        }
        Intrinsics.checkParameterIsNotNull(end, "$this$end");
        Intrinsics.checkParameterIsNotNull(block, "block");
        Buffer $this$appendJson$iv = Buffer.buffer();
        Intrinsics.checkExpressionValueIsNotNull($this$appendJson$iv, "Buffer.buffer()");
        Object json$iv = block.invoke(Json.INSTANCE);
        String encoded$iv = pretty ? io.vertx.core.json.Json.encodePrettily(json$iv) : io.vertx.core.json.Json.encode(json$iv);
        Buffer bufferAppendString = $this$appendJson$iv.appendString(encoded$iv);
        Intrinsics.checkExpressionValueIsNotNull(bufferAppendString, "appendString(encoded)");
        Intrinsics.checkExpressionValueIsNotNull(bufferAppendString, "block(Json).let { json -…> appendString(encoded) }");
        end.end((WriteStream) bufferAppendString);
        return end;
    }

    @NotNull
    public static final <S extends WriteStream<Buffer>> S end(@NotNull S end, boolean pretty, @NotNull Function1<? super Json, ? extends Object> block) {
        Intrinsics.checkParameterIsNotNull(end, "$this$end");
        Intrinsics.checkParameterIsNotNull(block, "block");
        Buffer $this$appendJson$iv = Buffer.buffer();
        Intrinsics.checkExpressionValueIsNotNull($this$appendJson$iv, "Buffer.buffer()");
        Object json$iv = block.invoke(Json.INSTANCE);
        String encoded$iv = pretty ? io.vertx.core.json.Json.encodePrettily(json$iv) : io.vertx.core.json.Json.encode(json$iv);
        Buffer bufferAppendString = $this$appendJson$iv.appendString(encoded$iv);
        Intrinsics.checkExpressionValueIsNotNull(bufferAppendString, "appendString(encoded)");
        Intrinsics.checkExpressionValueIsNotNull(bufferAppendString, "block(Json).let { json -…> appendString(encoded) }");
        end.end(bufferAppendString);
        return end;
    }
}
