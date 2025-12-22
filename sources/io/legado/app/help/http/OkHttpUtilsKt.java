package io.legado.app.help.http;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import io.legado.app.utils.EncodingDetect;
import io.legado.app.utils.GsonExtensionsKt;
import io.legado.app.utils.Utf8BomUtils;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import me.ag2s.epublib.epub.NCXDocumentV2;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: OkHttpUtils.kt */
@Metadata(mv = {1, 5, 1}, k = 2, xi = 48, d1 = {"��T\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010$\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010��\n\u0002\b\u0003\u001a\u001e\u0010��\u001a\u00020\u0001*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004\u001a\u0015\u0010\u0006\u001a\u00020\u0007*\u00020\bH\u0086@ø\u0001��¢\u0006\u0002\u0010\t\u001a0\u0010\n\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u00052\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00042\b\b\u0002\u0010\r\u001a\u00020\u000e\u001a8\u0010\u000f\u001a\u00020\u0010*\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\u0017\u0010\u0014\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00010\u0015¢\u0006\u0002\b\u0016H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0017\u001a8\u0010\u0018\u001a\u00020\u0007*\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\u0017\u0010\u0014\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00010\u0015¢\u0006\u0002\b\u0016H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0017\u001a8\u0010\u0019\u001a\u00020\u0010*\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\u0017\u0010\u0014\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00010\u0015¢\u0006\u0002\b\u0016H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0017\u001a8\u0010\u001a\u001a\u00020\u001b*\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\u0017\u0010\u0014\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00010\u0015¢\u0006\u0002\b\u0016H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0017\u001a(\u0010\u001c\u001a\u00020\u0001*\u00020\u00022\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00042\b\b\u0002\u0010\r\u001a\u00020\u000e\u001a\u0014\u0010\u001e\u001a\u00020\u0001*\u00020\u00022\b\u0010\u001f\u001a\u0004\u0018\u00010\u0005\u001a(\u0010 \u001a\u00020\u0001*\u00020\u00022\b\u0010!\u001a\u0004\u0018\u00010\u00052\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\"0\u0004\u001a\u0016\u0010#\u001a\u00020\u0005*\u00020\u00102\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\u0005\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006%"}, d2 = {"addHeaders", "", "Lokhttp3/Request$Builder;", "headers", "", "", "await", "Lokhttp3/Response;", "Lokhttp3/Call;", "(Lokhttp3/Call;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", BeanUtil.PREFIX_GETTER_GET, "url", "queryMap", "encoded", "", "newCall", "Lokhttp3/ResponseBody;", "Lokhttp3/OkHttpClient;", "retry", "", "builder", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "(Lokhttp3/OkHttpClient;ILkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "newCallResponse", "newCallResponseBody", "newCallStrResponse", "Lio/legado/app/help/http/StrResponse;", "postForm", "form", "postJson", "json", "postMultipart", "type", "", NCXDocumentV2.NCXTags.text, "encode", "reader-pro"})
/* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/help/http/OkHttpUtilsKt.class */
public final class OkHttpUtilsKt {

    /* compiled from: OkHttpUtils.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "OkHttpUtils.kt", l = {59}, i = {0}, s = {"I$2"}, n = {"i"}, m = "newCall", c = "io.legado.app.help.http.OkHttpUtilsKt")
    /* renamed from: io.legado.app.help.http.OkHttpUtilsKt$newCall$1, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/help/http/OkHttpUtilsKt$newCall$1.class */
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int I$0;
        int I$1;
        int I$2;
        /* synthetic */ Object result;
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return OkHttpUtilsKt.newCall(null, 0, null, this);
        }
    }

    /* compiled from: OkHttpUtils.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "OkHttpUtils.kt", l = {46}, i = {}, s = {}, n = {}, m = "newCallResponseBody", c = "io.legado.app.help.http.OkHttpUtilsKt")
    /* renamed from: io.legado.app.help.http.OkHttpUtilsKt$newCallResponseBody$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/help/http/OkHttpUtilsKt$newCallResponseBody$1.class */
    static final class C02001 extends ContinuationImpl {
        /* synthetic */ Object result;
        int label;

        C02001(Continuation<? super C02001> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return OkHttpUtilsKt.newCallResponseBody(null, 0, null, this);
        }
    }

    /* compiled from: OkHttpUtils.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48)
    @DebugMetadata(f = "OkHttpUtils.kt", l = {76}, i = {}, s = {}, n = {}, m = "newCallStrResponse", c = "io.legado.app.help.http.OkHttpUtilsKt")
    /* renamed from: io.legado.app.help.http.OkHttpUtilsKt$newCallStrResponse$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/help/http/OkHttpUtilsKt$newCallStrResponse$1.class */
    static final class C02011 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int I$0;
        int I$1;
        int I$2;
        /* synthetic */ Object result;
        int label;

        C02011(Continuation<? super C02011> $completion) {
            super($completion);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return OkHttpUtilsKt.newCallStrResponse(null, 0, null, this);
        }
    }

    public static /* synthetic */ Object newCallResponse$default(OkHttpClient okHttpClient, int i, Function1 function1, Continuation continuation, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        return newCallResponse(okHttpClient, i, function1, continuation);
    }

    /* compiled from: OkHttpUtils.kt */
    @Metadata(mv = {1, 5, 1}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lokhttp3/Response;", "Lkotlinx/coroutines/CoroutineScope;"})
    @DebugMetadata(f = "OkHttpUtils.kt", l = {33}, i = {0}, s = {"I$1"}, n = {"i"}, m = "invokeSuspend", c = "io.legado.app.help.http.OkHttpUtilsKt$newCallResponse$2")
    /* renamed from: io.legado.app.help.http.OkHttpUtilsKt$newCallResponse$2, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/help/http/OkHttpUtilsKt$newCallResponse$2.class */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Response>, Object> {
        Object L$0;
        int I$0;
        int I$1;
        int label;
        final /* synthetic */ Function1<Request.Builder, Unit> $builder;
        final /* synthetic */ int $retry;
        final /* synthetic */ OkHttpClient $this_newCallResponse;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass2(Function1<? super Request.Builder, Unit> $builder, int $retry, OkHttpClient $this_newCallResponse, Continuation<? super AnonymousClass2> $completion) {
            super(2, $completion);
            this.$builder = $builder;
            this.$retry = $retry;
            this.$this_newCallResponse = $this_newCallResponse;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            return new AnonymousClass2(this.$builder, this.$retry, this.$this_newCallResponse, $completion);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope p1, @Nullable Continuation<? super Response> p2) {
            return ((AnonymousClass2) create(p1, p2)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code restructure failed: missing block: B:16:0x00b5, code lost:
        
            if (r10 == r5.$retry) goto L17;
         */
        /* JADX WARN: Code restructure failed: missing block: B:17:0x00b8, code lost:
        
            r0 = r8;
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0);
         */
        /* JADX WARN: Code restructure failed: missing block: B:18:0x00bd, code lost:
        
            return r0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:5:0x004e, code lost:
        
            if (0 <= r5.$retry) goto L6;
         */
        /* JADX WARN: Code restructure failed: missing block: B:6:0x0051, code lost:
        
            r10 = r9;
            r9 = r9 + 1;
            r5.L$0 = r7;
            r5.I$0 = r9;
            r5.I$1 = r10;
            r5.label = 1;
            r0 = io.legado.app.help.http.OkHttpUtilsKt.await(r5.$this_newCallResponse.newCall(r7.build()), r5);
         */
        /* JADX WARN: Code restructure failed: missing block: B:7:0x0083, code lost:
        
            if (r0 != r0) goto L11;
         */
        /* JADX WARN: Code restructure failed: missing block: B:9:0x0088, code lost:
        
            return r0;
         */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x00b5 -> B:6:0x0051). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @org.jetbrains.annotations.Nullable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r6) throws java.lang.Throwable {
            /*
                r5 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                r12 = r0
                r0 = r5
                int r0 = r0.label
                switch(r0) {
                    case 0: goto L20;
                    case 1: goto L89;
                    default: goto Lbe;
                }
            L20:
                r0 = r6
                kotlin.ResultKt.throwOnFailure(r0)
                okhttp3.Request$Builder r0 = new okhttp3.Request$Builder
                r1 = r0
                r1.<init>()
                r7 = r0
                r0 = r7
                r8 = r0
                r0 = r5
                kotlin.jvm.functions.Function1<okhttp3.Request$Builder, kotlin.Unit> r0 = r0.$builder
                r9 = r0
                r0 = 0
                r10 = r0
                r0 = 0
                r11 = r0
                r0 = r9
                r1 = r8
                java.lang.Object r0 = r0.invoke(r1)
                r0 = 0
                r8 = r0
                r0 = 0
                r9 = r0
                r0 = r9
                r1 = r5
                int r1 = r1.$retry
                if (r0 > r1) goto Lb8
            L51:
                r0 = r9
                r10 = r0
                int r9 = r9 + 1
                r0 = r5
                okhttp3.OkHttpClient r0 = r0.$this_newCallResponse
                r1 = r7
                okhttp3.Request r1 = r1.build()
                okhttp3.Call r0 = r0.newCall(r1)
                r1 = r5
                kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
                r2 = r5
                r3 = r7
                r2.L$0 = r3
                r2 = r5
                r3 = r9
                r2.I$0 = r3
                r2 = r5
                r3 = r10
                r2.I$1 = r3
                r2 = r5
                r3 = 1
                r2.label = r3
                java.lang.Object r0 = io.legado.app.help.http.OkHttpUtilsKt.await(r0, r1)
                r1 = r0
                r2 = r12
                if (r1 != r2) goto La2
                r1 = r12
                return r1
            L89:
                r0 = r5
                int r0 = r0.I$1
                r10 = r0
                r0 = r5
                int r0 = r0.I$0
                r9 = r0
                r0 = r5
                java.lang.Object r0 = r0.L$0
                okhttp3.Request$Builder r0 = (okhttp3.Request.Builder) r0
                r7 = r0
                r0 = r6
                kotlin.ResultKt.throwOnFailure(r0)
                r0 = r6
            La2:
                okhttp3.Response r0 = (okhttp3.Response) r0
                r8 = r0
                r0 = r8
                boolean r0 = r0.isSuccessful()
                if (r0 == 0) goto Laf
                r0 = r8
                return r0
            Laf:
                r0 = r10
                r1 = r5
                int r1 = r1.$retry
                if (r0 != r1) goto L51
            Lb8:
                r0 = r8
                r1 = r0
                kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
                return r0
            Lbe:
                java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
                r1 = r0
                java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
                r1.<init>(r2)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: io.legado.app.help.http.OkHttpUtilsKt.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @Nullable
    public static final Object newCallResponse(@NotNull OkHttpClient $this$newCallResponse, int retry, @NotNull Function1<? super Request.Builder, Unit> builder, @NotNull Continuation<? super Response> $completion) {
        return BuildersKt.withContext(Dispatchers.getIO(), new AnonymousClass2(builder, retry, $this$newCallResponse, null), $completion);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object newCallResponseBody(@org.jetbrains.annotations.NotNull okhttp3.OkHttpClient r7, int r8, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super okhttp3.Request.Builder, kotlin.Unit> r9, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super okhttp3.ResponseBody> r10) {
        /*
            r0 = r10
            boolean r0 = r0 instanceof io.legado.app.help.http.OkHttpUtilsKt.C02001
            if (r0 == 0) goto L27
            r0 = r10
            io.legado.app.help.http.OkHttpUtilsKt$newCallResponseBody$1 r0 = (io.legado.app.help.http.OkHttpUtilsKt.C02001) r0
            r18 = r0
            r0 = r18
            int r0 = r0.label
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r0 & r1
            if (r0 == 0) goto L27
            r0 = r18
            r1 = r0
            int r1 = r1.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            int r1 = r1 - r2
            r0.label = r1
            goto L31
        L27:
            io.legado.app.help.http.OkHttpUtilsKt$newCallResponseBody$1 r0 = new io.legado.app.help.http.OkHttpUtilsKt$newCallResponseBody$1
            r1 = r0
            r2 = r10
            r1.<init>(r2)
            r18 = r0
        L31:
            r0 = r18
            java.lang.Object r0 = r0.result
            r17 = r0
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            r19 = r0
            r0 = r18
            int r0 = r0.label
            switch(r0) {
                case 0: goto L58;
                case 1: goto L74;
                default: goto Laa;
            }
        L58:
            r0 = r17
            kotlin.ResultKt.throwOnFailure(r0)
            r0 = r7
            r1 = r8
            r2 = r9
            r3 = r18
            r4 = r18
            r5 = 1
            r4.label = r5
            java.lang.Object r0 = newCallResponse(r0, r1, r2, r3)
            r1 = r0
            r2 = r19
            if (r1 != r2) goto L7b
            r1 = r19
            return r1
        L74:
            r0 = r17
            kotlin.ResultKt.throwOnFailure(r0)
            r0 = r17
        L7b:
            r11 = r0
            r0 = 0
            r12 = r0
            r0 = 0
            r13 = r0
            r0 = r11
            okhttp3.Response r0 = (okhttp3.Response) r0
            r14 = r0
            r0 = 0
            r15 = r0
            r0 = r14
            okhttp3.ResponseBody r0 = r0.body()
            r16 = r0
            r0 = r16
            if (r0 != 0) goto La6
            java.io.IOException r0 = new java.io.IOException
            r1 = r0
            r2 = r14
            java.lang.String r2 = r2.message()
            r1.<init>(r2)
            throw r0
        La6:
            r0 = r16
            return r0
        Laa:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r1 = r0
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.legado.app.help.http.OkHttpUtilsKt.newCallResponseBody(okhttp3.OkHttpClient, int, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object newCallResponseBody$default(OkHttpClient okHttpClient, int i, Function1 function1, Continuation continuation, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        return newCallResponseBody(okHttpClient, i, function1, continuation);
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0082, code lost:
    
        if (0 <= r6) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0085, code lost:
    
        r12 = r11;
        r11 = r11 + 1;
        r14.L$0 = r5;
        r14.L$1 = r9;
        r14.I$0 = r6;
        r14.I$1 = r11;
        r14.I$2 = r12;
        r14.label = 1;
        r0 = await(r5.newCall(r9.build()), r14);
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x00c4, code lost:
    
        if (r0 != r0) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x00c9, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0112, code lost:
    
        if (r12 == r6) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0115, code lost:
    
        r0 = r10;
        kotlin.jvm.internal.Intrinsics.checkNotNull(r0);
        r0 = r0.body();
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0122, code lost:
    
        if (r0 != null) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0131, code lost:
    
        throw new java.io.IOException(r10.message());
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0134, code lost:
    
        return r0;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x0112 -> B:12:0x0085). Please report as a decompilation issue!!! */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object newCall(@org.jetbrains.annotations.NotNull okhttp3.OkHttpClient r5, int r6, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super okhttp3.Request.Builder, kotlin.Unit> r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super okhttp3.ResponseBody> r8) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 319
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.legado.app.help.http.OkHttpUtilsKt.newCall(okhttp3.OkHttpClient, int, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object newCall$default(OkHttpClient okHttpClient, int i, Function1 function1, Continuation continuation, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        return newCall(okHttpClient, i, function1, continuation);
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0082, code lost:
    
        if (0 <= r9) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0085, code lost:
    
        r15 = r14;
        r14 = r14 + 1;
        kotlinx.coroutines.JobKt.ensureActive(r17.getContext());
        r17.L$0 = r8;
        r17.L$1 = r12;
        r17.I$0 = r9;
        r17.I$1 = r14;
        r17.I$2 = r15;
        r17.label = 1;
        r0 = await(r8.newCall(r12.build()), r17);
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x00ce, code lost:
    
        if (r0 != r0) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x00d3, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x012b, code lost:
    
        if (r15 == r9) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x012e, code lost:
    
        r2 = r13;
        kotlin.jvm.internal.Intrinsics.checkNotNull(r2);
        r3 = r13.body();
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0141, code lost:
    
        if (r3 != null) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0144, code lost:
    
        r3 = r13.message();
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x014c, code lost:
    
        r3 = text$default(r3, null, 1, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0157, code lost:
    
        return new io.legado.app.help.http.StrResponse(r2, r3);
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x012b -> B:12:0x0085). Please report as a decompilation issue!!! */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object newCallStrResponse(@org.jetbrains.annotations.NotNull okhttp3.OkHttpClient r8, int r9, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super okhttp3.Request.Builder, kotlin.Unit> r10, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super io.legado.app.help.http.StrResponse> r11) {
        /*
            Method dump skipped, instructions count: 354
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.legado.app.help.http.OkHttpUtilsKt.newCallStrResponse(okhttp3.OkHttpClient, int, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object newCallStrResponse$default(OkHttpClient okHttpClient, int i, Function1 function1, Continuation continuation, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        return newCallStrResponse(okHttpClient, i, function1, continuation);
    }

    @Nullable
    public static final Object await(@NotNull final Call $this$await, @NotNull Continuation<? super Response> $completion) {
        CancellableContinuationImpl cancellable$iv = new CancellableContinuationImpl(IntrinsicsKt.intercepted($completion), 1);
        cancellable$iv.initCancellability();
        final CancellableContinuationImpl block = cancellable$iv;
        block.invokeOnCancellation(new Function1<Throwable, Unit>() { // from class: io.legado.app.help.http.OkHttpUtilsKt$await$2$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                invoke2(th);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@Nullable Throwable it) {
                $this$await.cancel();
            }
        });
        $this$await.enqueue(new Callback() { // from class: io.legado.app.help.http.OkHttpUtilsKt$await$2$2
            @Override // okhttp3.Callback
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(e, "e");
                CancellableContinuation<Response> cancellableContinuation = block;
                Result.Companion companion = Result.Companion;
                cancellableContinuation.resumeWith(Result.m2105constructorimpl(ResultKt.createFailure(e)));
            }

            @Override // okhttp3.Callback
            public void onResponse(@NotNull Call call, @NotNull Response response) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                CancellableContinuation<Response> cancellableContinuation = block;
                Result.Companion companion = Result.Companion;
                cancellableContinuation.resumeWith(Result.m2105constructorimpl(response));
            }
        });
        Object result = cancellable$iv.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended($completion);
        }
        return result;
    }

    public static /* synthetic */ String text$default(ResponseBody responseBody, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        return text(responseBody, str);
    }

    @NotNull
    public static final String text(@NotNull ResponseBody $this$text, @Nullable String encode) {
        Charset it;
        Intrinsics.checkNotNullParameter($this$text, "<this>");
        byte[] responseBytes = Utf8BomUtils.INSTANCE.removeUTF8BOM($this$text.bytes());
        if (encode != null) {
            Charset charsetForName = Charset.forName(encode);
            Intrinsics.checkNotNullExpressionValue(charsetForName, "forName(charsetName)");
            return new String(responseBytes, charsetForName);
        }
        MediaType mediaTypeContentType = $this$text.contentType();
        if (mediaTypeContentType != null && (it = MediaType.charset$default(mediaTypeContentType, null, 1, null)) != null) {
            return new String(responseBytes, it);
        }
        Charset charsetForName2 = Charset.forName(EncodingDetect.INSTANCE.getHtmlEncode(responseBytes));
        Intrinsics.checkNotNullExpressionValue(charsetForName2, "forName(charsetName)");
        return new String(responseBytes, charsetForName2);
    }

    public static final void addHeaders(@NotNull Request.Builder $this$addHeaders, @NotNull Map<String, String> headers) {
        Intrinsics.checkNotNullParameter($this$addHeaders, "<this>");
        Intrinsics.checkNotNullParameter(headers, "headers");
        for (Map.Entry element$iv : headers.entrySet()) {
            $this$addHeaders.addHeader(element$iv.getKey(), element$iv.getValue());
        }
    }

    public static /* synthetic */ void get$default(Request.Builder builder, String str, Map map, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        get(builder, str, map, z);
    }

    public static final void get(@NotNull Request.Builder $this$get, @NotNull String url, @NotNull Map<String, String> queryMap, boolean encoded) {
        Intrinsics.checkNotNullParameter($this$get, "<this>");
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(queryMap, "queryMap");
        HttpUrl.Builder httpBuilder = HttpUrl.Companion.get(url).newBuilder();
        for (Map.Entry element$iv : queryMap.entrySet()) {
            if (encoded) {
                httpBuilder.addEncodedQueryParameter(element$iv.getKey(), element$iv.getValue());
            } else {
                httpBuilder.addQueryParameter(element$iv.getKey(), element$iv.getValue());
            }
        }
        $this$get.url(httpBuilder.build());
    }

    public static /* synthetic */ void postForm$default(Request.Builder builder, Map map, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        postForm(builder, map, z);
    }

    public static final void postForm(@NotNull Request.Builder $this$postForm, @NotNull Map<String, String> form, boolean encoded) {
        Intrinsics.checkNotNullParameter($this$postForm, "<this>");
        Intrinsics.checkNotNullParameter(form, "form");
        FormBody.Builder formBody = new FormBody.Builder(null, 1, null);
        for (Map.Entry element$iv : form.entrySet()) {
            if (encoded) {
                formBody.addEncoded(element$iv.getKey(), element$iv.getValue());
            } else {
                formBody.add(element$iv.getKey(), element$iv.getValue());
            }
        }
        $this$postForm.post(formBody.build());
    }

    public static final void postMultipart(@NotNull Request.Builder $this$postMultipart, @Nullable String type, @NotNull Map<String, ? extends Object> form) {
        RequestBody requestBodyCreate;
        Intrinsics.checkNotNullParameter($this$postMultipart, "<this>");
        Intrinsics.checkNotNullParameter(form, "form");
        MultipartBody.Builder multipartBody = new MultipartBody.Builder(null, 1, null);
        if (type != null) {
            multipartBody.setType(MediaType.Companion.get(type));
        }
        for (Map.Entry element$iv : form.entrySet()) {
            Object value = element$iv.getValue();
            if (value instanceof Map) {
                Object obj = ((Map) value).get("fileName");
                if (obj == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                }
                String fileName = (String) obj;
                Object file = ((Map) value).get("file");
                Object obj2 = ((Map) value).get("contentType");
                String str = obj2 instanceof String ? (String) obj2 : null;
                MediaType mediaType = str == null ? null : MediaType.Companion.get(str);
                if (file instanceof File) {
                    requestBodyCreate = RequestBody.Companion.create((File) file, mediaType);
                } else if (file instanceof byte[]) {
                    requestBodyCreate = RequestBody.Companion.create$default(RequestBody.Companion, (byte[]) file, mediaType, 0, 0, 6, (Object) null);
                } else if (file instanceof String) {
                    requestBodyCreate = RequestBody.Companion.create((String) file, mediaType);
                } else {
                    RequestBody.Companion companion = RequestBody.Companion;
                    String json = GsonExtensionsKt.getGSON().toJson(file);
                    Intrinsics.checkNotNullExpressionValue(json, "GSON.toJson(file)");
                    requestBodyCreate = companion.create(json, mediaType);
                }
                RequestBody requestBody = requestBodyCreate;
                multipartBody.addFormDataPart(element$iv.getKey(), fileName, requestBody);
            } else {
                multipartBody.addFormDataPart(element$iv.getKey(), element$iv.getValue().toString());
            }
        }
        $this$postMultipart.post(multipartBody.build());
    }

    public static final void postJson(@NotNull Request.Builder $this$postJson, @Nullable String json) {
        Intrinsics.checkNotNullParameter($this$postJson, "<this>");
        if (json != null) {
            RequestBody requestBody = RequestBody.Companion.create(json, MediaType.Companion.get("application/json; charset=UTF-8"));
            $this$postJson.post(requestBody);
        }
    }
}
