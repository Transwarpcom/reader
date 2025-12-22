package io.vertx.kotlin.redis.op;

import ch.qos.logback.core.pattern.parser.Parser;
import io.vertx.redis.op.MigrateOptions;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: MigrateOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u0010\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n\u0002\b\u0004\u001a%\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003H\u0007¢\u0006\u0002\u0010\u0005\u001a#\u0010\u0006\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005¨\u0006\u0007"}, d2 = {"MigrateOptions", "Lio/vertx/redis/op/MigrateOptions;", "copy", "", Parser.REPLACE_CONVERTER_WORD, "(Ljava/lang/Boolean;Ljava/lang/Boolean;)Lio/vertx/redis/op/MigrateOptions;", "migrateOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/redis/op/MigrateOptionsKt.class */
public final class MigrateOptionsKt {
    @NotNull
    public static /* synthetic */ MigrateOptions migrateOptionsOf$default(Boolean bool, Boolean bool2, int i, Object obj) {
        if ((i & 1) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 2) != 0) {
            bool2 = (Boolean) null;
        }
        return migrateOptionsOf(bool, bool2);
    }

    @NotNull
    public static final MigrateOptions migrateOptionsOf(@Nullable Boolean copy, @Nullable Boolean replace) {
        MigrateOptions $this$apply = new MigrateOptions();
        if (copy != null) {
            $this$apply.setCopy(copy);
        }
        if (replace != null) {
            $this$apply.setReplace(replace);
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "migrateOptionsOf(copy, replace)"))
    @NotNull
    public static /* synthetic */ MigrateOptions MigrateOptions$default(Boolean bool, Boolean bool2, int i, Object obj) {
        if ((i & 1) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 2) != 0) {
            bool2 = (Boolean) null;
        }
        return MigrateOptions(bool, bool2);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "migrateOptionsOf(copy, replace)"))
    @NotNull
    public static final MigrateOptions MigrateOptions(@Nullable Boolean copy, @Nullable Boolean replace) {
        MigrateOptions $this$apply = new MigrateOptions();
        if (copy != null) {
            $this$apply.setCopy(copy);
        }
        if (replace != null) {
            $this$apply.setReplace(replace);
        }
        return $this$apply;
    }
}
