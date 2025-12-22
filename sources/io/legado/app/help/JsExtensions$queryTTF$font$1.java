package io.legado.app.help;

import io.legado.app.help.http.HttpHelperKt;
import io.legado.app.help.http.OkHttpUtilsKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: JsExtensions.kt */
@Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0010\u0012\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"})
@DebugMetadata(f = "JsExtensions.kt", l = {508}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "io.legado.app.help.JsExtensions$queryTTF$font$1")
/* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/help/JsExtensions$queryTTF$font$1.class */
final class JsExtensions$queryTTF$font$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super byte[]>, Object> {
    int label;
    final /* synthetic */ Ref.ObjectRef<CacheManager> $cacheInstance;
    final /* synthetic */ String $key;
    final /* synthetic */ String $str;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    JsExtensions$queryTTF$font$1(Ref.ObjectRef<CacheManager> $cacheInstance, String $key, String $str, Continuation<? super JsExtensions$queryTTF$font$1> $completion) {
        super(2, $completion);
        this.$cacheInstance = $cacheInstance;
        this.$key = $key;
        this.$str = $str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
        return new JsExtensions$queryTTF$font$1(this.$cacheInstance, this.$key, this.$str, $completion);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope p1, @Nullable Continuation<? super byte[]> p2) {
        return ((JsExtensions$queryTTF$font$1) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object $result) throws Throwable {
        Object objNewCall$default;
        byte[] x;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                x = this.$cacheInstance.element.getByteArray(this.$key);
                if (x == null) {
                    OkHttpClient okHttpClient = HttpHelperKt.getOkHttpClient();
                    final String str = this.$str;
                    this.label = 1;
                    objNewCall$default = OkHttpUtilsKt.newCall$default(okHttpClient, 0, new Function1<Request.Builder, Unit>() { // from class: io.legado.app.help.JsExtensions$queryTTF$font$1.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(@NotNull Request.Builder newCall) {
                            Intrinsics.checkNotNullParameter(newCall, "$this$newCall");
                            newCall.url(str);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Request.Builder builder) {
                            invoke2(builder);
                            return Unit.INSTANCE;
                        }
                    }, this, 1, null);
                    if (objNewCall$default == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    x = ((ResponseBody) objNewCall$default).bytes();
                    Ref.ObjectRef<CacheManager> objectRef = this.$cacheInstance;
                    CacheManager.put$default(objectRef.element, this.$key, x, 0, 4, null);
                }
                return x;
            case 1:
                ResultKt.throwOnFailure($result);
                objNewCall$default = $result;
                x = ((ResponseBody) objNewCall$default).bytes();
                Ref.ObjectRef<CacheManager> objectRef2 = this.$cacheInstance;
                CacheManager.put$default(objectRef2.element, this.$key, x, 0, 4, null);
                return x;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
