package kotlinx.coroutines.selects;

import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.selects.SelectBuilder;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.jetbrains.annotations.NotNull;

/* compiled from: SelectUnbiased.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��h\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n��\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0010\t\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0001\u0018��*\u0006\b��\u0010\u0001 ��2\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028��0\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u0013H\u0001J\n\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0001J6\u0010\u0016\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\u00182\u001c\u0010\u0019\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028��0\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00150\u001aH\u0016ø\u0001��¢\u0006\u0002\u0010\u001bJ3\u0010\u001c\u001a\u00020\t*\u00020\u001d2\u001c\u0010\u0019\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028��0\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00150\u001aH\u0096\u0002ø\u0001��¢\u0006\u0002\u0010\u001eJE\u0010\u001c\u001a\u00020\t\"\u0004\b\u0001\u0010\u001f*\b\u0012\u0004\u0012\u0002H\u001f0 2\"\u0010\u0019\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00028��0\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00150!H\u0096\u0002ø\u0001��¢\u0006\u0002\u0010\"JY\u0010\u001c\u001a\u00020\t\"\u0004\b\u0001\u0010#\"\u0004\b\u0002\u0010\u001f*\u000e\u0012\u0004\u0012\u0002H#\u0012\u0004\u0012\u0002H\u001f0$2\u0006\u0010%\u001a\u0002H#2\"\u0010\u0019\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00028��0\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00150!H\u0096\u0002ø\u0001��¢\u0006\u0002\u0010&R-\u0010\u0006\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u0007j\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b`\n¢\u0006\b\n��\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00028��0\u000e¢\u0006\b\n��\u001a\u0004\b\u000f\u0010\u0010\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006'"}, d2 = {"Lkotlinx/coroutines/selects/UnbiasedSelectBuilderImpl;", "R", "Lkotlinx/coroutines/selects/SelectBuilder;", "uCont", "Lkotlin/coroutines/Continuation;", "(Lkotlin/coroutines/Continuation;)V", "clauses", "Ljava/util/ArrayList;", "Lkotlin/Function0;", "", "Lkotlin/collections/ArrayList;", "getClauses", "()Ljava/util/ArrayList;", "instance", "Lkotlinx/coroutines/selects/SelectBuilderImpl;", "getInstance", "()Lkotlinx/coroutines/selects/SelectBuilderImpl;", "handleBuilderException", "e", "", "initSelectResult", "", "onTimeout", "timeMillis", "", "block", "Lkotlin/Function1;", "(JLkotlin/jvm/functions/Function1;)V", "invoke", "Lkotlinx/coroutines/selects/SelectClause0;", "(Lkotlinx/coroutines/selects/SelectClause0;Lkotlin/jvm/functions/Function1;)V", OperatorName.RESTORE, "Lkotlinx/coroutines/selects/SelectClause1;", "Lkotlin/Function2;", "(Lkotlinx/coroutines/selects/SelectClause1;Lkotlin/jvm/functions/Function2;)V", "P", "Lkotlinx/coroutines/selects/SelectClause2;", "param", "(Lkotlinx/coroutines/selects/SelectClause2;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)V", "kotlinx-coroutines-core"})
@PublishedApi
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/selects/UnbiasedSelectBuilderImpl.class */
public final class UnbiasedSelectBuilderImpl<R> implements SelectBuilder<R> {

    @NotNull
    private final SelectBuilderImpl<R> instance;

    @NotNull
    private final ArrayList<Function0<Unit>> clauses = new ArrayList<>();

    public UnbiasedSelectBuilderImpl(@NotNull Continuation<? super R> continuation) {
        this.instance = new SelectBuilderImpl<>(continuation);
    }

    @Override // kotlinx.coroutines.selects.SelectBuilder
    public <P, Q> void invoke(@NotNull SelectClause2<? super P, ? extends Q> selectClause2, @NotNull Function2<? super Q, ? super Continuation<? super R>, ? extends Object> function2) {
        SelectBuilder.DefaultImpls.invoke(this, selectClause2, function2);
    }

    @NotNull
    public final SelectBuilderImpl<R> getInstance() {
        return this.instance;
    }

    @NotNull
    public final ArrayList<Function0<Unit>> getClauses() {
        return this.clauses;
    }

    @PublishedApi
    public final void handleBuilderException(@NotNull Throwable e) throws Throwable {
        this.instance.handleBuilderException(e);
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockSplitter
        jadx.core.utils.exceptions.JadxRuntimeException: Unexpected missing predecessor for block: B:4:0x000a
        	at jadx.core.dex.visitors.blocks.BlockSplitter.addTempConnectionsForExcHandlers(BlockSplitter.java:280)
        	at jadx.core.dex.visitors.blocks.BlockSplitter.visit(BlockSplitter.java:79)
        */
    @kotlin.PublishedApi
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object initSelectResult() {
        /*
            r3 = this;
            r0 = r3
            kotlinx.coroutines.selects.SelectBuilderImpl<R> r0 = r0.instance
            boolean r0 = r0.isSelected()
            if (r0 != 0) goto L5d
        Lb:
            r0 = r3
            java.util.ArrayList<kotlin.jvm.functions.Function0<kotlin.Unit>> r0 = r0.clauses     // Catch: java.lang.Throwable -> L54
            java.util.List r0 = (java.util.List) r0     // Catch: java.lang.Throwable -> L54
            r4 = r0
            r0 = 0
            r5 = r0
            r0 = r4
            java.util.Collections.shuffle(r0)     // Catch: java.lang.Throwable -> L54
            r0 = r3
            java.util.ArrayList<kotlin.jvm.functions.Function0<kotlin.Unit>> r0 = r0.clauses     // Catch: java.lang.Throwable -> L54
            java.lang.Iterable r0 = (java.lang.Iterable) r0     // Catch: java.lang.Throwable -> L54
            r4 = r0
            r0 = 0
            r5 = r0
            r0 = r4
            java.util.Iterator r0 = r0.iterator()     // Catch: java.lang.Throwable -> L54
            r6 = r0
        L2a:
            r0 = r6
            boolean r0 = r0.hasNext()     // Catch: java.lang.Throwable -> L54
            if (r0 == 0) goto L50
            r0 = r6
            java.lang.Object r0 = r0.next()     // Catch: java.lang.Throwable -> L54
            r7 = r0
            r0 = r7
            kotlin.jvm.functions.Function0 r0 = (kotlin.jvm.functions.Function0) r0     // Catch: java.lang.Throwable -> L54
            r8 = r0
            r0 = 0
            r9 = r0
            r0 = r8
            java.lang.Object r0 = r0.invoke()     // Catch: java.lang.Throwable -> L54
            goto L2a
        L50:
            goto L5d
        L54:
            r4 = move-exception
            r0 = r3
            kotlinx.coroutines.selects.SelectBuilderImpl<R> r0 = r0.instance
            r1 = r4
            r0.handleBuilderException(r1)
        L5d:
            r0 = r3
            kotlinx.coroutines.selects.SelectBuilderImpl<R> r0 = r0.instance
            java.lang.Object r0 = r0.getResult()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.selects.UnbiasedSelectBuilderImpl.initSelectResult():java.lang.Object");
    }

    @Override // kotlinx.coroutines.selects.SelectBuilder
    public void invoke(@NotNull final SelectClause0 $this$invoke, @NotNull final Function1<? super Continuation<? super R>, ? extends Object> function1) {
        this.clauses.add(new Function0<Unit>() { // from class: kotlinx.coroutines.selects.UnbiasedSelectBuilderImpl.invoke.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(0);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                $this$invoke.registerSelectClause0(this.getInstance(), function1);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }
        });
    }

    @Override // kotlinx.coroutines.selects.SelectBuilder
    public <Q> void invoke(@NotNull final SelectClause1<? extends Q> selectClause1, @NotNull final Function2<? super Q, ? super Continuation<? super R>, ? extends Object> function2) {
        this.clauses.add(new Function0<Unit>() { // from class: kotlinx.coroutines.selects.UnbiasedSelectBuilderImpl.invoke.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(0);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                selectClause1.registerSelectClause1(this.getInstance(), function2);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }
        });
    }

    @Override // kotlinx.coroutines.selects.SelectBuilder
    public <P, Q> void invoke(@NotNull final SelectClause2<? super P, ? extends Q> selectClause2, final P p, @NotNull final Function2<? super Q, ? super Continuation<? super R>, ? extends Object> function2) {
        this.clauses.add(new Function0<Unit>() { // from class: kotlinx.coroutines.selects.UnbiasedSelectBuilderImpl.invoke.3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(0);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                selectClause2.registerSelectClause2(this.getInstance(), p, function2);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }
        });
    }

    @Override // kotlinx.coroutines.selects.SelectBuilder
    public void onTimeout(final long timeMillis, @NotNull final Function1<? super Continuation<? super R>, ? extends Object> function1) {
        this.clauses.add(new Function0<Unit>(this) { // from class: kotlinx.coroutines.selects.UnbiasedSelectBuilderImpl.onTimeout.1
            final /* synthetic */ UnbiasedSelectBuilderImpl<R> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(0);
                this.this$0 = this;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                this.this$0.getInstance().onTimeout(timeMillis, function1);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }
        });
    }
}
