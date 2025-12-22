package io.vertx.kotlin.core.buffer;

import io.vertx.core.buffer.Buffer;
import io.vertx.kotlin.core.json.Json;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: buffer.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"�� \n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\u0018\u0002\n��\u001a0\u0010��\u001a\u00020\u0001*\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0017\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\b\bH\u0086\b¨\u0006\t"}, d2 = {"appendJson", "Lio/vertx/core/buffer/Buffer;", "pretty", "", "block", "Lkotlin/Function1;", "Lio/vertx/kotlin/core/json/Json;", "", "Lkotlin/ExtensionFunctionType;", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/core/buffer/BufferKt.class */
public final class BufferKt {
    @NotNull
    public static /* synthetic */ Buffer appendJson$default(Buffer appendJson, boolean pretty, Function1 block, int i, Object obj) {
        if ((i & 1) != 0) {
            pretty = false;
        }
        Intrinsics.checkParameterIsNotNull(appendJson, "$this$appendJson");
        Intrinsics.checkParameterIsNotNull(block, "block");
        Object json = block.invoke(Json.INSTANCE);
        String encoded = pretty ? io.vertx.core.json.Json.encodePrettily(json) : io.vertx.core.json.Json.encode(json);
        Buffer bufferAppendString = appendJson.appendString(encoded);
        Intrinsics.checkExpressionValueIsNotNull(bufferAppendString, "appendString(encoded)");
        Intrinsics.checkExpressionValueIsNotNull(bufferAppendString, "block(Json).let { json -…> appendString(encoded) }");
        return bufferAppendString;
    }

    @NotNull
    public static final Buffer appendJson(@NotNull Buffer appendJson, boolean pretty, @NotNull Function1<? super Json, ? extends Object> block) {
        Intrinsics.checkParameterIsNotNull(appendJson, "$this$appendJson");
        Intrinsics.checkParameterIsNotNull(block, "block");
        Object json = block.invoke(Json.INSTANCE);
        String encoded = pretty ? io.vertx.core.json.Json.encodePrettily(json) : io.vertx.core.json.Json.encode(json);
        Buffer bufferAppendString = appendJson.appendString(encoded);
        Intrinsics.checkExpressionValueIsNotNull(bufferAppendString, "appendString(encoded)");
        Intrinsics.checkExpressionValueIsNotNull(bufferAppendString, "block(Json).let { json -…> appendString(encoded) }");
        return bufferAppendString;
    }
}
