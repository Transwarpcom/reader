package kotlin.reflect.jvm.internal.impl.util;

import java.util.Collection;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.util.CheckResult;
import kotlin.text.Regex;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: modifierChecks.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/util/Checks.class */
public final class Checks {

    @Nullable
    private final Name name;

    @Nullable
    private final Regex regex;

    @Nullable
    private final Collection<Name> nameList;

    @NotNull
    private final Function1<FunctionDescriptor, String> additionalCheck;

    @NotNull
    private final Check[] checks;

    /* JADX WARN: Multi-variable type inference failed */
    private Checks(Name name, Regex regex, Collection<Name> collection, Function1<? super FunctionDescriptor, String> function1, Check... checks) {
        this.name = name;
        this.regex = regex;
        this.nameList = collection;
        this.additionalCheck = function1;
        this.checks = checks;
    }

    public final boolean isApplicable(@NotNull FunctionDescriptor functionDescriptor) {
        Intrinsics.checkNotNullParameter(functionDescriptor, "functionDescriptor");
        if (this.name != null && !Intrinsics.areEqual(functionDescriptor.getName(), this.name)) {
            return false;
        }
        if (this.regex != null) {
            String strAsString = functionDescriptor.getName().asString();
            Intrinsics.checkNotNullExpressionValue(strAsString, "functionDescriptor.name.asString()");
            if (!this.regex.matches(strAsString)) {
                return false;
            }
        }
        return this.nameList == null || this.nameList.contains(functionDescriptor.getName());
    }

    @NotNull
    public final CheckResult checkAll(@NotNull FunctionDescriptor functionDescriptor) {
        Intrinsics.checkNotNullParameter(functionDescriptor, "functionDescriptor");
        Check[] checkArr = this.checks;
        int i = 0;
        int length = checkArr.length;
        while (i < length) {
            Check check = checkArr[i];
            i++;
            String checkResult = check.invoke(functionDescriptor);
            if (checkResult != null) {
                return new CheckResult.IllegalSignature(checkResult);
            }
        }
        String additionalCheckResult = this.additionalCheck.invoke(functionDescriptor);
        if (additionalCheckResult != null) {
            return new CheckResult.IllegalSignature(additionalCheckResult);
        }
        return CheckResult.SuccessCheck.INSTANCE;
    }

    public /* synthetic */ Checks(Name name, Check[] checkArr, Function1 function1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(name, checkArr, (Function1<? super FunctionDescriptor, String>) ((i & 4) != 0 ? new Function1() { // from class: kotlin.reflect.jvm.internal.impl.util.Checks.2
            @Override // kotlin.jvm.functions.Function1
            @Nullable
            public final Void invoke(@NotNull FunctionDescriptor $this$null) {
                Intrinsics.checkNotNullParameter($this$null, "$this$null");
                return null;
            }
        } : function1));
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public Checks(@NotNull Name name, @NotNull Check[] checks, @NotNull Function1<? super FunctionDescriptor, String> additionalChecks) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(checks, "checks");
        Intrinsics.checkNotNullParameter(additionalChecks, "additionalChecks");
        Check[] checkArr = new Check[checks.length];
        System.arraycopy(checks, 0, checkArr, 0, checks.length);
        this(name, (Regex) null, (Collection<Name>) null, additionalChecks, checkArr);
    }

    public /* synthetic */ Checks(Regex regex, Check[] checkArr, Function1 function1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(regex, checkArr, (Function1<? super FunctionDescriptor, String>) ((i & 4) != 0 ? new Function1() { // from class: kotlin.reflect.jvm.internal.impl.util.Checks.3
            @Override // kotlin.jvm.functions.Function1
            @Nullable
            public final Void invoke(@NotNull FunctionDescriptor $this$null) {
                Intrinsics.checkNotNullParameter($this$null, "$this$null");
                return null;
            }
        } : function1));
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public Checks(@NotNull Regex regex, @NotNull Check[] checks, @NotNull Function1<? super FunctionDescriptor, String> additionalChecks) {
        Intrinsics.checkNotNullParameter(regex, "regex");
        Intrinsics.checkNotNullParameter(checks, "checks");
        Intrinsics.checkNotNullParameter(additionalChecks, "additionalChecks");
        Check[] checkArr = new Check[checks.length];
        System.arraycopy(checks, 0, checkArr, 0, checks.length);
        this((Name) null, regex, (Collection<Name>) null, additionalChecks, checkArr);
    }

    public /* synthetic */ Checks(Collection collection, Check[] checkArr, Function1 function1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((Collection<Name>) collection, checkArr, (Function1<? super FunctionDescriptor, String>) ((i & 4) != 0 ? new Function1() { // from class: kotlin.reflect.jvm.internal.impl.util.Checks.4
            @Override // kotlin.jvm.functions.Function1
            @Nullable
            public final Void invoke(@NotNull FunctionDescriptor $this$null) {
                Intrinsics.checkNotNullParameter($this$null, "$this$null");
                return null;
            }
        } : function1));
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public Checks(@NotNull Collection<Name> nameList, @NotNull Check[] checks, @NotNull Function1<? super FunctionDescriptor, String> additionalChecks) {
        Intrinsics.checkNotNullParameter(nameList, "nameList");
        Intrinsics.checkNotNullParameter(checks, "checks");
        Intrinsics.checkNotNullParameter(additionalChecks, "additionalChecks");
        Check[] checkArr = new Check[checks.length];
        System.arraycopy(checks, 0, checkArr, 0, checks.length);
        this((Name) null, (Regex) null, nameList, additionalChecks, checkArr);
    }
}
