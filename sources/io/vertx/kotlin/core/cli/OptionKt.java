package io.vertx.kotlin.core.cli;

import io.vertx.core.cli.Option;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.validation.DefaultBindingErrorProcessor;

/* compiled from: Option.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u001e\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010\u001c\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\n\u001a£\u0001\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\tH\u0007¢\u0006\u0002\u0010\u0011\u001a¡\u0001\u0010\u0012\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\u0011¨\u0006\u0013"}, d2 = {"Option", "Lio/vertx/core/cli/Option;", "argName", "", "choices", "", "defaultValue", "description", "flag", "", "help", "hidden", "longName", "multiValued", DefaultBindingErrorProcessor.MISSING_FIELD_ERROR_CODE, "shortName", "singleValued", "(Ljava/lang/String;Ljava/lang/Iterable;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Boolean;)Lio/vertx/core/cli/Option;", "optionOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/core/cli/OptionKt.class */
public final class OptionKt {
    @NotNull
    public static /* synthetic */ Option optionOf$default(String str, Iterable iterable, String str2, String str3, Boolean bool, Boolean bool2, Boolean bool3, String str4, Boolean bool4, Boolean bool5, String str5, Boolean bool6, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        if ((i & 2) != 0) {
            iterable = (Iterable) null;
        }
        if ((i & 4) != 0) {
            str2 = (String) null;
        }
        if ((i & 8) != 0) {
            str3 = (String) null;
        }
        if ((i & 16) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 32) != 0) {
            bool2 = (Boolean) null;
        }
        if ((i & 64) != 0) {
            bool3 = (Boolean) null;
        }
        if ((i & 128) != 0) {
            str4 = (String) null;
        }
        if ((i & 256) != 0) {
            bool4 = (Boolean) null;
        }
        if ((i & 512) != 0) {
            bool5 = (Boolean) null;
        }
        if ((i & 1024) != 0) {
            str5 = (String) null;
        }
        if ((i & 2048) != 0) {
            bool6 = (Boolean) null;
        }
        return optionOf(str, iterable, str2, str3, bool, bool2, bool3, str4, bool4, bool5, str5, bool6);
    }

    @NotNull
    public static final Option optionOf(@Nullable String argName, @Nullable Iterable<String> iterable, @Nullable String defaultValue, @Nullable String description, @Nullable Boolean flag, @Nullable Boolean help, @Nullable Boolean hidden, @Nullable String longName, @Nullable Boolean multiValued, @Nullable Boolean required, @Nullable String shortName, @Nullable Boolean singleValued) {
        Option $this$apply = new Option();
        if (argName != null) {
            $this$apply.setArgName(argName);
        }
        if (iterable != null) {
            $this$apply.setChoices(CollectionsKt.toSet(iterable));
        }
        if (defaultValue != null) {
            $this$apply.setDefaultValue(defaultValue);
        }
        if (description != null) {
            $this$apply.setDescription(description);
        }
        if (flag != null) {
            $this$apply.setFlag(flag.booleanValue());
        }
        if (help != null) {
            $this$apply.setHelp(help.booleanValue());
        }
        if (hidden != null) {
            $this$apply.setHidden(hidden.booleanValue());
        }
        if (longName != null) {
            $this$apply.setLongName(longName);
        }
        if (multiValued != null) {
            $this$apply.setMultiValued(multiValued.booleanValue());
        }
        if (required != null) {
            $this$apply.setRequired(required.booleanValue());
        }
        if (shortName != null) {
            $this$apply.setShortName(shortName);
        }
        if (singleValued != null) {
            $this$apply.setSingleValued(singleValued.booleanValue());
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "optionOf(argName, choices, defaultValue, description, flag, help, hidden, longName, multiValued, required, shortName, singleValued)"))
    @NotNull
    public static /* synthetic */ Option Option$default(String str, Iterable iterable, String str2, String str3, Boolean bool, Boolean bool2, Boolean bool3, String str4, Boolean bool4, Boolean bool5, String str5, Boolean bool6, int i, Object obj) {
        if ((i & 1) != 0) {
            str = (String) null;
        }
        if ((i & 2) != 0) {
            iterable = (Iterable) null;
        }
        if ((i & 4) != 0) {
            str2 = (String) null;
        }
        if ((i & 8) != 0) {
            str3 = (String) null;
        }
        if ((i & 16) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 32) != 0) {
            bool2 = (Boolean) null;
        }
        if ((i & 64) != 0) {
            bool3 = (Boolean) null;
        }
        if ((i & 128) != 0) {
            str4 = (String) null;
        }
        if ((i & 256) != 0) {
            bool4 = (Boolean) null;
        }
        if ((i & 512) != 0) {
            bool5 = (Boolean) null;
        }
        if ((i & 1024) != 0) {
            str5 = (String) null;
        }
        if ((i & 2048) != 0) {
            bool6 = (Boolean) null;
        }
        return Option(str, iterable, str2, str3, bool, bool2, bool3, str4, bool4, bool5, str5, bool6);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "optionOf(argName, choices, defaultValue, description, flag, help, hidden, longName, multiValued, required, shortName, singleValued)"))
    @NotNull
    public static final Option Option(@Nullable String argName, @Nullable Iterable<String> iterable, @Nullable String defaultValue, @Nullable String description, @Nullable Boolean flag, @Nullable Boolean help, @Nullable Boolean hidden, @Nullable String longName, @Nullable Boolean multiValued, @Nullable Boolean required, @Nullable String shortName, @Nullable Boolean singleValued) {
        Option $this$apply = new Option();
        if (argName != null) {
            $this$apply.setArgName(argName);
        }
        if (iterable != null) {
            $this$apply.setChoices(CollectionsKt.toSet(iterable));
        }
        if (defaultValue != null) {
            $this$apply.setDefaultValue(defaultValue);
        }
        if (description != null) {
            $this$apply.setDescription(description);
        }
        if (flag != null) {
            $this$apply.setFlag(flag.booleanValue());
        }
        if (help != null) {
            $this$apply.setHelp(help.booleanValue());
        }
        if (hidden != null) {
            $this$apply.setHidden(hidden.booleanValue());
        }
        if (longName != null) {
            $this$apply.setLongName(longName);
        }
        if (multiValued != null) {
            $this$apply.setMultiValued(multiValued.booleanValue());
        }
        if (required != null) {
            $this$apply.setRequired(required.booleanValue());
        }
        if (shortName != null) {
            $this$apply.setShortName(shortName);
        }
        if (singleValued != null) {
            $this$apply.setSingleValued(singleValued.booleanValue());
        }
        return $this$apply;
    }
}
