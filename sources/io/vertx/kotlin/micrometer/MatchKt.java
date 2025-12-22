package io.vertx.kotlin.micrometer;

import io.vertx.micrometer.Match;
import io.vertx.micrometer.MatchType;
import io.vertx.micrometer.MetricsDomain;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Match.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u001e\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aD\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003H\u0007\u001aB\u0010\n\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003¨\u0006\u000b"}, d2 = {"Match", "Lio/vertx/micrometer/Match;", "alias", "", "domain", "Lio/vertx/micrometer/MetricsDomain;", "label", "type", "Lio/vertx/micrometer/MatchType;", "value", "matchOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/micrometer/MatchKt.class */
public final class MatchKt {
    @NotNull
    public static /* synthetic */ Match matchOf$default(String str, MetricsDomain metricsDomain, String str2, MatchType matchType, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        if ((i & 2) != 0) {
            metricsDomain = (MetricsDomain) null;
        }
        if ((i & 4) != 0) {
            str2 = (String) null;
        }
        if ((i & 8) != 0) {
            matchType = (MatchType) null;
        }
        if ((i & 16) != 0) {
            str3 = (String) null;
        }
        return matchOf(str, metricsDomain, str2, matchType, str3);
    }

    @NotNull
    public static final Match matchOf(@Nullable String alias, @Nullable MetricsDomain domain, @Nullable String label, @Nullable MatchType type, @Nullable String value) {
        Match $this$apply = new Match();
        if (alias != null) {
            $this$apply.setAlias(alias);
        }
        if (domain != null) {
            $this$apply.setDomain(domain);
        }
        if (label != null) {
            $this$apply.setLabel(label);
        }
        if (type != null) {
            $this$apply.setType(type);
        }
        if (value != null) {
            $this$apply.setValue(value);
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "matchOf(alias, domain, label, type, value)"))
    @NotNull
    public static /* synthetic */ Match Match$default(String str, MetricsDomain metricsDomain, String str2, MatchType matchType, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        if ((i & 2) != 0) {
            metricsDomain = (MetricsDomain) null;
        }
        if ((i & 4) != 0) {
            str2 = (String) null;
        }
        if ((i & 8) != 0) {
            matchType = (MatchType) null;
        }
        if ((i & 16) != 0) {
            str3 = (String) null;
        }
        return Match(str, metricsDomain, str2, matchType, str3);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "matchOf(alias, domain, label, type, value)"))
    @NotNull
    public static final Match Match(@Nullable String alias, @Nullable MetricsDomain domain, @Nullable String label, @Nullable MatchType type, @Nullable String value) {
        Match $this$apply = new Match();
        if (alias != null) {
            $this$apply.setAlias(alias);
        }
        if (domain != null) {
            $this$apply.setDomain(domain);
        }
        if (label != null) {
            $this$apply.setLabel(label);
        }
        if (type != null) {
            $this$apply.setType(type);
        }
        if (value != null) {
            $this$apply.setValue(value);
        }
        return $this$apply;
    }
}
