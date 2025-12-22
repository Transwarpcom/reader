package com.fasterxml.jackson.module.kotlin;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import java.io.Closeable;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KParameter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Exceptions.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018��2\u00020\u0001B#\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bB!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lcom/fasterxml/jackson/module/kotlin/MissingKotlinParameterException;", "Lcom/fasterxml/jackson/databind/exc/MismatchedInputException;", "parameter", "Lkotlin/reflect/KParameter;", "processor", "Ljava/io/Closeable;", "msg", "", "(Lkotlin/reflect/KParameter;Ljava/io/Closeable;Ljava/lang/String;)V", "Lcom/fasterxml/jackson/core/JsonParser;", "(Lkotlin/reflect/KParameter;Lcom/fasterxml/jackson/core/JsonParser;Ljava/lang/String;)V", "getParameter", "()Lkotlin/reflect/KParameter;", "jackson-module-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/jackson-module-kotlin-2.13.5.jar:com/fasterxml/jackson/module/kotlin/MissingKotlinParameterException.class */
public final class MissingKotlinParameterException extends MismatchedInputException {

    @NotNull
    private final KParameter parameter;

    public /* synthetic */ MissingKotlinParameterException(KParameter kParameter, JsonParser jsonParser, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(kParameter, (i & 2) != 0 ? null : jsonParser, str);
    }

    @NotNull
    public final KParameter getParameter() {
        return this.parameter;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MissingKotlinParameterException(@NotNull KParameter parameter, @Nullable JsonParser processor, @NotNull String msg) {
        super(processor, msg);
        Intrinsics.checkNotNullParameter(parameter, "parameter");
        Intrinsics.checkNotNullParameter(msg, "msg");
        this.parameter = parameter;
    }

    public /* synthetic */ MissingKotlinParameterException(KParameter kParameter, Closeable closeable, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(kParameter, (i & 2) != 0 ? null : closeable, str);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @Deprecated(message = "Use main constructor", replaceWith = @ReplaceWith(expression = "MissingKotlinParameterException(KParameter, JsonParser?, String)", imports = {}))
    public MissingKotlinParameterException(@NotNull KParameter parameter, @Nullable Closeable processor, @NotNull String msg) {
        this(parameter, (JsonParser) processor, msg);
        Intrinsics.checkNotNullParameter(parameter, "parameter");
        Intrinsics.checkNotNullParameter(msg, "msg");
        if (processor == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.fasterxml.jackson.core.JsonParser");
        }
    }
}
