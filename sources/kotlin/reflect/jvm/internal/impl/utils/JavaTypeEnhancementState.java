package kotlin.reflect.jvm.internal.impl.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: JavaTypeEnhancementState.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/utils/JavaTypeEnhancementState.class */
public final class JavaTypeEnhancementState {

    @NotNull
    private final ReportLevel globalJsr305Level;

    @Nullable
    private final ReportLevel migrationLevelForJsr305;

    @NotNull
    private final Map<String, ReportLevel> userDefinedLevelForSpecificJsr305Annotation;
    private final boolean enableCompatqualCheckerFrameworkAnnotations;

    @NotNull
    private final ReportLevel jspecifyReportLevel;

    @NotNull
    private final Lazy description$delegate;
    private final boolean disabledJsr305;
    private final boolean disabledDefaultAnnotations;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @JvmField
    @NotNull
    public static final ReportLevel DEFAULT_REPORT_LEVEL_FOR_JSPECIFY = ReportLevel.WARN;

    @JvmField
    @NotNull
    public static final JavaTypeEnhancementState DEFAULT = new JavaTypeEnhancementState(ReportLevel.WARN, null, MapsKt.emptyMap(), false, null, 24, null);

    @JvmField
    @NotNull
    public static final JavaTypeEnhancementState DISABLED_JSR_305 = new JavaTypeEnhancementState(ReportLevel.IGNORE, ReportLevel.IGNORE, MapsKt.emptyMap(), false, null, 24, null);

    @JvmField
    @NotNull
    public static final JavaTypeEnhancementState STRICT = new JavaTypeEnhancementState(ReportLevel.STRICT, ReportLevel.STRICT, MapsKt.emptyMap(), false, null, 24, null);

    /* JADX WARN: Multi-variable type inference failed */
    public JavaTypeEnhancementState(@NotNull ReportLevel globalJsr305Level, @Nullable ReportLevel migrationLevelForJsr305, @NotNull Map<String, ? extends ReportLevel> userDefinedLevelForSpecificJsr305Annotation, boolean enableCompatqualCheckerFrameworkAnnotations, @NotNull ReportLevel jspecifyReportLevel) {
        Intrinsics.checkNotNullParameter(globalJsr305Level, "globalJsr305Level");
        Intrinsics.checkNotNullParameter(userDefinedLevelForSpecificJsr305Annotation, "userDefinedLevelForSpecificJsr305Annotation");
        Intrinsics.checkNotNullParameter(jspecifyReportLevel, "jspecifyReportLevel");
        this.globalJsr305Level = globalJsr305Level;
        this.migrationLevelForJsr305 = migrationLevelForJsr305;
        this.userDefinedLevelForSpecificJsr305Annotation = userDefinedLevelForSpecificJsr305Annotation;
        this.enableCompatqualCheckerFrameworkAnnotations = enableCompatqualCheckerFrameworkAnnotations;
        this.jspecifyReportLevel = jspecifyReportLevel;
        this.description$delegate = LazyKt.lazy(new Function0<String[]>() { // from class: kotlin.reflect.jvm.internal.impl.utils.JavaTypeEnhancementState$description$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final String[] invoke() {
                List result = new ArrayList();
                result.add(this.this$0.getGlobalJsr305Level().getDescription());
                ReportLevel it = this.this$0.getMigrationLevelForJsr305();
                if (it != null) {
                    result.add(Intrinsics.stringPlus("under-migration:", it.getDescription()));
                }
                Map $this$forEach$iv = this.this$0.getUserDefinedLevelForSpecificJsr305Annotation();
                for (Map.Entry element$iv : $this$forEach$iv.entrySet()) {
                    result.add('@' + element$iv.getKey() + ':' + element$iv.getValue().getDescription());
                }
                List $this$toTypedArray$iv = result;
                Object[] array = $this$toTypedArray$iv.toArray(new String[0]);
                if (array == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
                }
                return (String[]) array;
            }
        });
        this.disabledJsr305 = this.globalJsr305Level == ReportLevel.IGNORE && this.migrationLevelForJsr305 == ReportLevel.IGNORE && this.userDefinedLevelForSpecificJsr305Annotation.isEmpty();
        this.disabledDefaultAnnotations = this.disabledJsr305 || this.jspecifyReportLevel == ReportLevel.IGNORE;
    }

    public /* synthetic */ JavaTypeEnhancementState(ReportLevel reportLevel, ReportLevel reportLevel2, Map map, boolean z, ReportLevel reportLevel3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(reportLevel, reportLevel2, map, (i & 8) != 0 ? true : z, (i & 16) != 0 ? DEFAULT_REPORT_LEVEL_FOR_JSPECIFY : reportLevel3);
    }

    @NotNull
    public final ReportLevel getGlobalJsr305Level() {
        return this.globalJsr305Level;
    }

    @Nullable
    public final ReportLevel getMigrationLevelForJsr305() {
        return this.migrationLevelForJsr305;
    }

    @NotNull
    public final Map<String, ReportLevel> getUserDefinedLevelForSpecificJsr305Annotation() {
        return this.userDefinedLevelForSpecificJsr305Annotation;
    }

    public final boolean getEnableCompatqualCheckerFrameworkAnnotations() {
        return this.enableCompatqualCheckerFrameworkAnnotations;
    }

    @NotNull
    public final ReportLevel getJspecifyReportLevel() {
        return this.jspecifyReportLevel;
    }

    public final boolean getDisabledJsr305() {
        return this.disabledJsr305;
    }

    public final boolean getDisabledDefaultAnnotations() {
        return this.disabledDefaultAnnotations;
    }

    /* compiled from: JavaTypeEnhancementState.kt */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/utils/JavaTypeEnhancementState$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }
    }
}
