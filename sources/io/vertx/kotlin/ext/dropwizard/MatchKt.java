package io.vertx.kotlin.ext.dropwizard;

import io.vertx.ext.dropwizard.Match;
import io.vertx.ext.dropwizard.MatchType;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Match.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u0016\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a,\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003H\u0007\u001a*\u0010\u0007\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003¨\u0006\b"}, d2 = {"Match", "Lio/vertx/ext/dropwizard/Match;", "alias", "", "type", "Lio/vertx/ext/dropwizard/MatchType;", "value", "matchOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/dropwizard/MatchKt.class */
public final class MatchKt {
    @NotNull
    public static /* synthetic */ Match matchOf$default(String str, MatchType matchType, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        if ((i & 2) != 0) {
            matchType = (MatchType) null;
        }
        if ((i & 4) != 0) {
            str2 = (String) null;
        }
        return matchOf(str, matchType, str2);
    }

    @NotNull
    public static final Match matchOf(@Nullable String alias, @Nullable MatchType type, @Nullable String value) {
        Match $this$apply = new Match();
        if (alias != null) {
            $this$apply.setAlias(alias);
        }
        if (type != null) {
            $this$apply.setType(type);
        }
        if (value != null) {
            $this$apply.setValue(value);
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "matchOf(alias, type, value)"))
    @NotNull
    public static /* synthetic */ Match Match$default(String str, MatchType matchType, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        if ((i & 2) != 0) {
            matchType = (MatchType) null;
        }
        if ((i & 4) != 0) {
            str2 = (String) null;
        }
        return Match(str, matchType, str2);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "matchOf(alias, type, value)"))
    @NotNull
    public static final Match Match(@Nullable String alias, @Nullable MatchType type, @Nullable String value) {
        Match $this$apply = new Match();
        if (alias != null) {
            $this$apply.setAlias(alias);
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
