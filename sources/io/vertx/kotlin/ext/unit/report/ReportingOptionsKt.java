package io.vertx.kotlin.ext.unit.report;

import io.vertx.ext.unit.report.ReportOptions;
import io.vertx.ext.unit.report.ReportingOptions;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ReportingOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u0014\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001a\u0010��\u001a\u00020\u00012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003H\u0007\u001a\u0018\u0010\u0005\u001a\u00020\u00012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¨\u0006\u0006"}, d2 = {"ReportingOptions", "Lio/vertx/ext/unit/report/ReportingOptions;", "reporters", "", "Lio/vertx/ext/unit/report/ReportOptions;", "reportingOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/unit/report/ReportingOptionsKt.class */
public final class ReportingOptionsKt {
    @NotNull
    public static final ReportingOptions reportingOptionsOf(@Nullable Iterable<? extends ReportOptions> iterable) {
        ReportingOptions $this$apply = new ReportingOptions();
        if (iterable != null) {
            $this$apply.setReporters(CollectionsKt.toList(iterable));
        }
        return $this$apply;
    }

    @NotNull
    public static /* synthetic */ ReportingOptions reportingOptionsOf$default(Iterable iterable, int i, Object obj) {
        if ((i & 1) != 0) {
            iterable = (Iterable) null;
        }
        return reportingOptionsOf(iterable);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "reportingOptionsOf(reporters)"))
    @NotNull
    public static final ReportingOptions ReportingOptions(@Nullable Iterable<? extends ReportOptions> iterable) {
        ReportingOptions $this$apply = new ReportingOptions();
        if (iterable != null) {
            $this$apply.setReporters(CollectionsKt.toList(iterable));
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "reportingOptionsOf(reporters)"))
    @NotNull
    public static /* synthetic */ ReportingOptions ReportingOptions$default(Iterable iterable, int i, Object obj) {
        if ((i & 1) != 0) {
            iterable = (Iterable) null;
        }
        return ReportingOptions(iterable);
    }
}
