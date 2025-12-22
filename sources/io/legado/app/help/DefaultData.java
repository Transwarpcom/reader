package io.legado.app.help;

import com.google.gson.Gson;
import io.legado.app.data.entities.TxtTocRule;
import io.legado.app.utils.GsonExtensionsKt;
import io.legado.app.utils.ParameterizedTypeImpl;
import java.net.URL;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;

/* compiled from: DefaultData.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��\u001e\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0010\u000e\n��\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018��2\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n��R!\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\t¨\u0006\f"}, d2 = {"Lio/legado/app/help/DefaultData;", "", "()V", "txtTocRuleFileName", "", "txtTocRules", "", "Lio/legado/app/data/entities/TxtTocRule;", "getTxtTocRules", "()Ljava/util/List;", "txtTocRules$delegate", "Lkotlin/Lazy;", "reader-pro"})
/* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/help/DefaultData.class */
public final class DefaultData {

    @NotNull
    public static final String txtTocRuleFileName = "txtTocRule.json";

    @NotNull
    public static final DefaultData INSTANCE = new DefaultData();

    @NotNull
    private static final Lazy txtTocRules$delegate = LazyKt.lazy(new Function0<List<? extends TxtTocRule>>() { // from class: io.legado.app.help.DefaultData$txtTocRules$2
        @Override // kotlin.jvm.functions.Function0
        @NotNull
        public final List<? extends TxtTocRule> invoke() {
            Object objM2105constructorimpl;
            URL resource = DefaultData.class.getResource("/defaultData/txtTocRule.json");
            Intrinsics.checkNotNullExpressionValue(resource, "DefaultData::class.java.getResource(\"/defaultData/${txtTocRuleFileName}\")");
            String json = new String(TextStreamsKt.readBytes(resource), Charsets.UTF_8);
            Gson $this$fromJsonArray$iv = GsonExtensionsKt.getGSON();
            try {
                Result.Companion companion = Result.Companion;
                Object objFromJson = $this$fromJsonArray$iv.fromJson(json, new ParameterizedTypeImpl(TxtTocRule.class));
                objM2105constructorimpl = Result.m2105constructorimpl(objFromJson instanceof List ? (List) objFromJson : null);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.Companion;
                objM2105constructorimpl = Result.m2105constructorimpl(ResultKt.createFailure(th));
            }
            Object obj = objM2105constructorimpl;
            List<? extends TxtTocRule> list = (List) (Result.m2101isFailureimpl(obj) ? null : obj);
            return list == null ? CollectionsKt.emptyList() : list;
        }
    });

    private DefaultData() {
    }

    @NotNull
    public final List<TxtTocRule> getTxtTocRules() {
        return (List) txtTocRules$delegate.getValue();
    }
}
