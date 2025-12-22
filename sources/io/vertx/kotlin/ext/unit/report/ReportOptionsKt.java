package io.vertx.kotlin.ext.unit.report;

import io.vertx.ext.unit.report.ReportOptions;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import me.ag2s.epublib.epub.PackageDocumentBase;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ReportOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u0010\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a \u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003H\u0007\u001a\u001e\u0010\u0005\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003¨\u0006\u0006"}, d2 = {"ReportOptions", "Lio/vertx/ext/unit/report/ReportOptions;", PackageDocumentBase.DCTags.format, "", "to", "reportOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/unit/report/ReportOptionsKt.class */
public final class ReportOptionsKt {
    @NotNull
    public static /* synthetic */ ReportOptions reportOptionsOf$default(String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        if ((i & 2) != 0) {
            str2 = (String) null;
        }
        return reportOptionsOf(str, str2);
    }

    @NotNull
    public static final ReportOptions reportOptionsOf(@Nullable String format, @Nullable String to) {
        ReportOptions $this$apply = new ReportOptions();
        if (format != null) {
            $this$apply.setFormat(format);
        }
        if (to != null) {
            $this$apply.setTo(to);
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "reportOptionsOf(format, to)"))
    @NotNull
    public static /* synthetic */ ReportOptions ReportOptions$default(String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        if ((i & 2) != 0) {
            str2 = (String) null;
        }
        return ReportOptions(str, str2);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "reportOptionsOf(format, to)"))
    @NotNull
    public static final ReportOptions ReportOptions(@Nullable String format, @Nullable String to) {
        ReportOptions $this$apply = new ReportOptions();
        if (format != null) {
            $this$apply.setFormat(format);
        }
        if (to != null) {
            $this$apply.setTo(to);
        }
        return $this$apply;
    }
}
