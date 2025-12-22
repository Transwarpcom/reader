package io.vertx.kotlin.ext.unit;

import io.netty.handler.codec.rtsp.RtspHeaders;
import io.vertx.ext.unit.TestOptions;
import io.vertx.ext.unit.report.ReportOptions;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: TestOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"�� \n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n��\n\u0002\u0010\t\n��\n\u0002\u0010\u000b\n\u0002\b\u0003\u001a7\u0010��\u001a\u00020\u00012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0007¢\u0006\u0002\u0010\t\u001a5\u0010\n\u001a\u00020\u00012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\t¨\u0006\u000b"}, d2 = {"TestOptions", "Lio/vertx/ext/unit/TestOptions;", "reporters", "", "Lio/vertx/ext/unit/report/ReportOptions;", RtspHeaders.Values.TIMEOUT, "", "useEventLoop", "", "(Ljava/lang/Iterable;Ljava/lang/Long;Ljava/lang/Boolean;)Lio/vertx/ext/unit/TestOptions;", "testOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/unit/TestOptionsKt.class */
public final class TestOptionsKt {
    @NotNull
    public static /* synthetic */ TestOptions testOptionsOf$default(Iterable iterable, Long l, Boolean bool, int i, Object obj) {
        if ((i & 1) != 0) {
            iterable = (Iterable) null;
        }
        if ((i & 2) != 0) {
            l = (Long) null;
        }
        if ((i & 4) != 0) {
            bool = (Boolean) null;
        }
        return testOptionsOf(iterable, l, bool);
    }

    @NotNull
    public static final TestOptions testOptionsOf(@Nullable Iterable<? extends ReportOptions> iterable, @Nullable Long timeout, @Nullable Boolean useEventLoop) {
        TestOptions $this$apply = new TestOptions();
        if (iterable != null) {
            $this$apply.setReporters(CollectionsKt.toList(iterable));
        }
        if (timeout != null) {
            $this$apply.setTimeout(timeout.longValue());
        }
        if (useEventLoop != null) {
            $this$apply.setUseEventLoop(useEventLoop);
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "testOptionsOf(reporters, timeout, useEventLoop)"))
    @NotNull
    public static /* synthetic */ TestOptions TestOptions$default(Iterable iterable, Long l, Boolean bool, int i, Object obj) {
        if ((i & 1) != 0) {
            iterable = (Iterable) null;
        }
        if ((i & 2) != 0) {
            l = (Long) null;
        }
        if ((i & 4) != 0) {
            bool = (Boolean) null;
        }
        return TestOptions(iterable, l, bool);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "testOptionsOf(reporters, timeout, useEventLoop)"))
    @NotNull
    public static final TestOptions TestOptions(@Nullable Iterable<? extends ReportOptions> iterable, @Nullable Long timeout, @Nullable Boolean useEventLoop) {
        TestOptions $this$apply = new TestOptions();
        if (iterable != null) {
            $this$apply.setReporters(CollectionsKt.toList(iterable));
        }
        if (timeout != null) {
            $this$apply.setTimeout(timeout.longValue());
        }
        if (useEventLoop != null) {
            $this$apply.setUseEventLoop(useEventLoop);
        }
        return $this$apply;
    }
}
