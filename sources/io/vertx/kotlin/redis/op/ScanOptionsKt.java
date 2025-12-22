package io.vertx.kotlin.redis.op;

import io.vertx.redis.op.ScanOptions;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.xml.BeanDefinitionParserDelegate;

/* compiled from: ScanOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u0016\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n��\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a%\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0007¢\u0006\u0002\u0010\u0006\u001a#\u0010\u0007\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006¨\u0006\b"}, d2 = {"ScanOptions", "Lio/vertx/redis/op/ScanOptions;", "count", "", BeanDefinitionParserDelegate.ARG_TYPE_MATCH_ATTRIBUTE, "", "(Ljava/lang/Integer;Ljava/lang/String;)Lio/vertx/redis/op/ScanOptions;", "scanOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/redis/op/ScanOptionsKt.class */
public final class ScanOptionsKt {
    @NotNull
    public static /* synthetic */ ScanOptions scanOptionsOf$default(Integer num, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            num = (Integer) null;
        }
        if ((i & 2) != 0) {
            str = (String) null;
        }
        return scanOptionsOf(num, str);
    }

    @NotNull
    public static final ScanOptions scanOptionsOf(@Nullable Integer count, @Nullable String match) {
        ScanOptions $this$apply = new ScanOptions();
        if (count != null) {
            $this$apply.setCount(count.intValue());
        }
        if (match != null) {
            $this$apply.setMatch(match);
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "scanOptionsOf(count, match)"))
    @NotNull
    public static /* synthetic */ ScanOptions ScanOptions$default(Integer num, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            num = (Integer) null;
        }
        if ((i & 2) != 0) {
            str = (String) null;
        }
        return ScanOptions(num, str);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "scanOptionsOf(count, match)"))
    @NotNull
    public static final ScanOptions ScanOptions(@Nullable Integer count, @Nullable String match) {
        ScanOptions $this$apply = new ScanOptions();
        if (count != null) {
            $this$apply.setCount(count.intValue());
        }
        if (match != null) {
            $this$apply.setMatch(match);
        }
        return $this$apply;
    }
}
