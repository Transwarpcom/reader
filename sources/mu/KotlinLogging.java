package mu;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import mu.internal.KLoggerFactory;
import mu.internal.KLoggerNameResolver;
import mu.internal.LocationAwareKLogger;
import mu.internal.LocationIgnorantKLogger;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.spi.LocationAwareLogger;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;

/* compiled from: KotlinLogging.kt */
@Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"��\"\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n��\n\u0002\u0010\u000e\n��\bÆ\u0002\u0018��2\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t¨\u0006\n"}, d2 = {"Lmu/KotlinLogging;", "", "()V", "logger", "Lmu/KLogger;", "func", "Lkotlin/Function0;", "", "name", "", "kotlin-logging"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-logging-1.6.24.jar:mu/KotlinLogging.class */
public final class KotlinLogging {
    public static final KotlinLogging INSTANCE = new KotlinLogging();

    private KotlinLogging() {
    }

    @NotNull
    public final KLogger logger(@NotNull Function0<Unit> func) {
        String strSubstringBefore$default;
        Intrinsics.checkParameterIsNotNull(func, "func");
        KLoggerFactory kLoggerFactory = KLoggerFactory.INSTANCE;
        KLoggerNameResolver kLoggerNameResolver = KLoggerNameResolver.INSTANCE;
        String name$iv$iv = func.getClass().getName();
        if (StringsKt.contains$default((CharSequence) name$iv$iv, (CharSequence) "Kt$", false, 2, (Object) null)) {
            strSubstringBefore$default = StringsKt.substringBefore$default(name$iv$iv, "Kt$", (String) null, 2, (Object) null);
        } else {
            strSubstringBefore$default = StringsKt.contains$default((CharSequence) name$iv$iv, (CharSequence) PropertiesBeanDefinitionReader.CONSTRUCTOR_ARG_PREFIX, false, 2, (Object) null) ? StringsKt.substringBefore$default(name$iv$iv, PropertiesBeanDefinitionReader.CONSTRUCTOR_ARG_PREFIX, (String) null, 2, (Object) null) : name$iv$iv;
        }
        String slicedName = strSubstringBefore$default;
        Intrinsics.checkExpressionValueIsNotNull(slicedName, "slicedName");
        Logger jLogger$iv$iv$iv = LoggerFactory.getLogger(slicedName);
        Intrinsics.checkExpressionValueIsNotNull(jLogger$iv$iv$iv, "LoggerFactory.getLogger(name)");
        if (jLogger$iv$iv$iv instanceof LocationAwareLogger) {
            return new LocationAwareKLogger((LocationAwareLogger) jLogger$iv$iv$iv);
        }
        return new LocationIgnorantKLogger(jLogger$iv$iv$iv);
    }

    @NotNull
    public final KLogger logger(@NotNull String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        KLoggerFactory kLoggerFactory = KLoggerFactory.INSTANCE;
        Logger jLogger$iv$iv = LoggerFactory.getLogger(name);
        Intrinsics.checkExpressionValueIsNotNull(jLogger$iv$iv, "LoggerFactory.getLogger(name)");
        if (jLogger$iv$iv instanceof LocationAwareLogger) {
            return new LocationAwareKLogger((LocationAwareLogger) jLogger$iv$iv);
        }
        return new LocationIgnorantKLogger(jLogger$iv$iv);
    }
}
