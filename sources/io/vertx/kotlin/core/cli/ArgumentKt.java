package io.vertx.kotlin.core.cli;

import io.vertx.core.cli.Argument;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.validation.DefaultBindingErrorProcessor;

/* compiled from: Argument.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u001e\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n��\n\u0002\u0010\b\n\u0002\b\u0005\u001aa\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0007H\u0007¢\u0006\u0002\u0010\f\u001a_\u0010\r\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\f¨\u0006\u000e"}, d2 = {"Argument", "Lio/vertx/core/cli/Argument;", "argName", "", "defaultValue", "description", "hidden", "", "index", "", "multiValued", DefaultBindingErrorProcessor.MISSING_FIELD_ERROR_CODE, "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/lang/Boolean;)Lio/vertx/core/cli/Argument;", "argumentOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/core/cli/ArgumentKt.class */
public final class ArgumentKt {
    @NotNull
    public static /* synthetic */ Argument argumentOf$default(String str, String str2, String str3, Boolean bool, Integer num, Boolean bool2, Boolean bool3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        if ((i & 2) != 0) {
            str2 = (String) null;
        }
        if ((i & 4) != 0) {
            str3 = (String) null;
        }
        if ((i & 8) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 16) != 0) {
            num = (Integer) null;
        }
        if ((i & 32) != 0) {
            bool2 = (Boolean) null;
        }
        if ((i & 64) != 0) {
            bool3 = (Boolean) null;
        }
        return argumentOf(str, str2, str3, bool, num, bool2, bool3);
    }

    @NotNull
    public static final Argument argumentOf(@Nullable String argName, @Nullable String defaultValue, @Nullable String description, @Nullable Boolean hidden, @Nullable Integer index, @Nullable Boolean multiValued, @Nullable Boolean required) {
        Argument $this$apply = new Argument();
        if (argName != null) {
            $this$apply.setArgName(argName);
        }
        if (defaultValue != null) {
            $this$apply.setDefaultValue(defaultValue);
        }
        if (description != null) {
            $this$apply.setDescription(description);
        }
        if (hidden != null) {
            $this$apply.setHidden(hidden.booleanValue());
        }
        if (index != null) {
            $this$apply.setIndex(index.intValue());
        }
        if (multiValued != null) {
            $this$apply.setMultiValued(multiValued.booleanValue());
        }
        if (required != null) {
            $this$apply.setRequired(required.booleanValue());
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "argumentOf(argName, defaultValue, description, hidden, index, multiValued, required)"))
    @NotNull
    public static /* synthetic */ Argument Argument$default(String str, String str2, String str3, Boolean bool, Integer num, Boolean bool2, Boolean bool3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        if ((i & 2) != 0) {
            str2 = (String) null;
        }
        if ((i & 4) != 0) {
            str3 = (String) null;
        }
        if ((i & 8) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 16) != 0) {
            num = (Integer) null;
        }
        if ((i & 32) != 0) {
            bool2 = (Boolean) null;
        }
        if ((i & 64) != 0) {
            bool3 = (Boolean) null;
        }
        return Argument(str, str2, str3, bool, num, bool2, bool3);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "argumentOf(argName, defaultValue, description, hidden, index, multiValued, required)"))
    @NotNull
    public static final Argument Argument(@Nullable String argName, @Nullable String defaultValue, @Nullable String description, @Nullable Boolean hidden, @Nullable Integer index, @Nullable Boolean multiValued, @Nullable Boolean required) {
        Argument $this$apply = new Argument();
        if (argName != null) {
            $this$apply.setArgName(argName);
        }
        if (defaultValue != null) {
            $this$apply.setDefaultValue(defaultValue);
        }
        if (description != null) {
            $this$apply.setDescription(description);
        }
        if (hidden != null) {
            $this$apply.setHidden(hidden.booleanValue());
        }
        if (index != null) {
            $this$apply.setIndex(index.intValue());
        }
        if (multiValued != null) {
            $this$apply.setMultiValued(multiValued.booleanValue());
        }
        if (required != null) {
            $this$apply.setRequired(required.booleanValue());
        }
        return $this$apply;
    }
}
