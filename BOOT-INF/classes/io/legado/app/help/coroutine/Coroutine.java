package io.legado.app.help.coroutine;

import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.TimeoutKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 5, 1},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u0090\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 E*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0004DEFGBC\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012'\u0010\u0007\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\t\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b¢\u0006\u0002\b\nø\u0001\u0000¢\u0006\u0002\u0010\u000bJ\u0018\u0010\f\u001a\u00020\"2\u0010\b\u0002\u0010#\u001a\n\u0018\u00010$j\u0004\u0018\u0001`%J?\u0010&\u001a\u00020\"\"\u0004\b\u0001\u0010'2\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010(\u001a\u0002H'2\u0016\u0010)\u001a\u0012\u0012\u0004\u0012\u0002H'0\u000fR\b\u0012\u0004\u0012\u00028\u00000\u0000H\u0082Hø\u0001\u0000¢\u0006\u0002\u0010*J+\u0010+\u001a\u00020\"2\u0006\u0010\u0003\u001a\u00020\u00042\u0010\u0010)\u001a\f0\rR\b\u0012\u0004\u0012\u00028\u00000\u0000H\u0082Hø\u0001\u0000¢\u0006\u0002\u0010,JT\u0010-\u001a\u00028\u00002\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020 2)\b\b\u0010\u0007\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\t\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b¢\u0006\u0002\b\nH\u0082Hø\u0001\u0000¢\u0006\u0002\u0010.JA\u0010/\u001a\u00020\u001a2\u0006\u0010\u0005\u001a\u00020\u00062'\u0010\u0007\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\t\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b¢\u0006\u0002\b\nH\u0002ø\u0001\u0000¢\u0006\u0002\u00100J/\u00101\u001a\u0002022'\u00103\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\u0010¢\u0006\f\b5\u0012\b\b6\u0012\u0004\b\b(#\u0012\u0004\u0012\u00020\"04j\u0002`7JI\u00108\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062'\u0010\u0007\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\"0\t\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b¢\u0006\u0002\b\nø\u0001\u0000¢\u0006\u0002\u00109JO\u0010:\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062-\u0010\u0007\u001a)\b\u0001\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\"0\t\u0012\u0006\u0012\u0004\u0018\u00010\u00020;¢\u0006\u0002\b\nø\u0001\u0000¢\u0006\u0002\u0010<J\u001c\u0010=\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u000e\u0010(\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000>J\u001b\u0010=\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\u0010(\u001a\u0004\u0018\u00018\u0000¢\u0006\u0002\u0010?JI\u0010@\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062'\u0010\u0007\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\"0\t\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b¢\u0006\u0002\b\nø\u0001\u0000¢\u0006\u0002\u00109JI\u0010A\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062'\u0010\u0007\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\"0\t\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b¢\u0006\u0002\b\nø\u0001\u0000¢\u0006\u0002\u00109JO\u0010B\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062-\u0010\u0007\u001a)\b\u0001\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\"0\t\u0012\u0006\u0012\u0004\u0018\u00010\u00020;¢\u0006\u0002\b\nø\u0001\u0000¢\u0006\u0002\u0010<J\u001a\u0010C\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020 0>J\u0014\u0010C\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u001f\u001a\u00020 R\u001a\u0010\f\u001a\u000e\u0018\u00010\rR\b\u0012\u0004\u0012\u00028\u00000\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R \u0010\u000e\u001a\u0014\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000fR\b\u0012\u0004\u0012\u00028\u00000\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0011\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0013\u001a\u000e\u0018\u00010\rR\b\u0012\u0004\u0012\u00028\u00000\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0014\u001a\u00020\u00158F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0016R\u0011\u0010\u0017\u001a\u00020\u00158F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0016R\u0011\u0010\u0018\u001a\u00020\u00158F¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0016R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u001a\u0010\u001d\u001a\u000e\u0018\u00010\rR\b\u0012\u0004\u0012\u00028\u00000\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R \u0010\u001e\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u000fR\b\u0012\u0004\u0012\u00028\u00000\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u001f\u001a\u0004\u0018\u00010 X\u0082\u000e¢\u0006\u0004\n\u0002\u0010!\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006H"},
   d2 = {"Lio/legado/app/help/coroutine/Coroutine;", "T", "", "scope", "Lkotlinx/coroutines/CoroutineScope;", "context", "Lkotlin/coroutines/CoroutineContext;", "block", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "Lkotlin/ExtensionFunctionType;", "(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function2;)V", "cancel", "Lio/legado/app/help/coroutine/Coroutine$VoidCallback;", "error", "Lio/legado/app/help/coroutine/Coroutine$Callback;", "", "errorReturn", "Lio/legado/app/help/coroutine/Coroutine$Result;", "finally", "isActive", "", "()Z", "isCancelled", "isCompleted", "job", "Lkotlinx/coroutines/Job;", "getScope", "()Lkotlinx/coroutines/CoroutineScope;", "start", "success", "timeMillis", "", "Ljava/lang/Long;", "", "cause", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "dispatchCallback", "R", "value", "callback", "(Lkotlinx/coroutines/CoroutineScope;Ljava/lang/Object;Lio/legado/app/help/coroutine/Coroutine$Callback;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "dispatchVoidCallback", "(Lkotlinx/coroutines/CoroutineScope;Lio/legado/app/help/coroutine/Coroutine$VoidCallback;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "executeBlock", "(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;JLkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "executeInternal", "(Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/Job;", "invokeOnCompletion", "Lkotlinx/coroutines/DisposableHandle;", "handler", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "Lkotlinx/coroutines/CompletionHandler;", "onCancel", "(Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function2;)Lio/legado/app/help/coroutine/Coroutine;", "onError", "Lkotlin/Function3;", "(Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function3;)Lio/legado/app/help/coroutine/Coroutine;", "onErrorReturn", "Lkotlin/Function0;", "(Ljava/lang/Object;)Lio/legado/app/help/coroutine/Coroutine;", "onFinally", "onStart", "onSuccess", "timeout", "Callback", "Companion", "Result", "VoidCallback", "reader-pro"}
)
public final class Coroutine<T> {
   @NotNull
   public static final Coroutine.Companion Companion = new Coroutine.Companion((DefaultConstructorMarker)null);
   @NotNull
   private final CoroutineScope scope;
   @NotNull
   private final Job job;
   @Nullable
   private Coroutine<T>.VoidCallback start;
   @Nullable
   private Coroutine<T>.Callback<T> success;
   @Nullable
   private Coroutine<T>.Callback<Throwable> error;
   @Nullable
   private Coroutine<T>.VoidCallback finally;
   @Nullable
   private Coroutine<T>.VoidCallback cancel;
   @Nullable
   private Long timeMillis;
   @Nullable
   private Coroutine.Result<? extends T> errorReturn;
   @NotNull
   private static final CoroutineScope DEFAULT = CoroutineScopeKt.MainScope();

   public Coroutine(@NotNull CoroutineScope scope, @NotNull CoroutineContext context, @NotNull Function2<? super CoroutineScope, ? super Continuation<? super T>, ? extends Object> block) {
      Intrinsics.checkNotNullParameter(scope, "scope");
      Intrinsics.checkNotNullParameter(context, "context");
      Intrinsics.checkNotNullParameter(block, "block");
      super();
      this.scope = scope;
      this.job = this.executeInternal(context, block);
   }

   // $FF: synthetic method
   public Coroutine(CoroutineScope var1, CoroutineContext var2, Function2 var3, int var4, DefaultConstructorMarker var5) {
      if ((var4 & 2) != 0) {
         var2 = (CoroutineContext)Dispatchers.getIO();
      }

      this(var1, var2, var3);
   }

   @NotNull
   public final CoroutineScope getScope() {
      return this.scope;
   }

   public final boolean isCancelled() {
      return this.job.isCancelled();
   }

   public final boolean isActive() {
      return this.job.isActive();
   }

   public final boolean isCompleted() {
      return this.job.isCompleted();
   }

   @NotNull
   public final Coroutine<T> timeout(@NotNull Function0<Long> timeMillis) {
      Intrinsics.checkNotNullParameter(timeMillis, "timeMillis");
      this.timeMillis = (Long)timeMillis.invoke();
      return this;
   }

   @NotNull
   public final Coroutine<T> timeout(long timeMillis) {
      this.timeMillis = timeMillis;
      return this;
   }

   @NotNull
   public final Coroutine<T> onErrorReturn(@NotNull Function0<? extends T> value) {
      Intrinsics.checkNotNullParameter(value, "value");
      this.errorReturn = new Coroutine.Result(value.invoke());
      return this;
   }

   @NotNull
   public final Coroutine<T> onErrorReturn(@Nullable T value) {
      this.errorReturn = new Coroutine.Result(value);
      return this;
   }

   @NotNull
   public final Coroutine<T> onStart(@Nullable CoroutineContext context, @NotNull Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object> block) {
      Intrinsics.checkNotNullParameter(block, "block");
      this.start = new Coroutine.VoidCallback(context, block);
      return this;
   }

   // $FF: synthetic method
   public static Coroutine onStart$default(Coroutine var0, CoroutineContext var1, Function2 var2, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         var1 = null;
      }

      return var0.onStart(var1, var2);
   }

   @NotNull
   public final Coroutine<T> onSuccess(@Nullable CoroutineContext context, @NotNull Function3<? super CoroutineScope, ? super T, ? super Continuation<? super Unit>, ? extends Object> block) {
      Intrinsics.checkNotNullParameter(block, "block");
      this.success = new Coroutine.Callback(context, block);
      return this;
   }

   // $FF: synthetic method
   public static Coroutine onSuccess$default(Coroutine var0, CoroutineContext var1, Function3 var2, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         var1 = null;
      }

      return var0.onSuccess(var1, var2);
   }

   @NotNull
   public final Coroutine<T> onError(@Nullable CoroutineContext context, @NotNull Function3<? super CoroutineScope, ? super Throwable, ? super Continuation<? super Unit>, ? extends Object> block) {
      Intrinsics.checkNotNullParameter(block, "block");
      this.error = new Coroutine.Callback(context, block);
      return this;
   }

   // $FF: synthetic method
   public static Coroutine onError$default(Coroutine var0, CoroutineContext var1, Function3 var2, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         var1 = null;
      }

      return var0.onError(var1, var2);
   }

   @NotNull
   public final Coroutine<T> onFinally(@Nullable CoroutineContext context, @NotNull Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object> block) {
      Intrinsics.checkNotNullParameter(block, "block");
      this.finally = new Coroutine.VoidCallback(context, block);
      return this;
   }

   // $FF: synthetic method
   public static Coroutine onFinally$default(Coroutine var0, CoroutineContext var1, Function2 var2, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         var1 = null;
      }

      return var0.onFinally(var1, var2);
   }

   @NotNull
   public final Coroutine<T> onCancel(@Nullable CoroutineContext context, @NotNull Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object> block) {
      Intrinsics.checkNotNullParameter(block, "block");
      this.cancel = new Coroutine.VoidCallback(context, block);
      return this;
   }

   // $FF: synthetic method
   public static Coroutine onCancel$default(Coroutine var0, CoroutineContext var1, Function2 var2, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         var1 = null;
      }

      return var0.onCancel(var1, var2);
   }

   public final void cancel(@Nullable CancellationException cause) {
      this.job.cancel(cause);
      final Coroutine.VoidCallback var2 = this.cancel;
      if (var2 != null) {
         boolean var4 = false;
         boolean var5 = false;
         int var7 = false;
         BuildersKt.launch$default(CoroutineScopeKt.MainScope(), (CoroutineContext)null, (CoroutineStart)null, (Function2)(new Function2<CoroutineScope, Continuation<? super Unit>, Object>((Continuation)null) {
            int label;

            @Nullable
            public final Object invokeSuspend(@NotNull Object $result) {
               Object var2x = IntrinsicsKt.getCOROUTINE_SUSPENDED();
               switch(this.label) {
               case 0:
                  ResultKt.throwOnFailure($result);
                  if (var2.getContext() == null) {
                     Function2 var3 = var2.getBlock();
                     CoroutineScope var4 = Coroutine.this.getScope();
                     this.label = 1;
                     if (var3.invoke(var4, this) == var2x) {
                        return var2x;
                     }
                  } else {
                     CoroutineContext var10000 = Coroutine.this.getScope().getCoroutineContext().plus(var2.getContext());
                     Function2 var10001 = (Function2)(new Function2<CoroutineScope, Continuation<? super Unit>, Object>((Continuation)null) {
                        int label;
                        // $FF: synthetic field
                        private Object L$0;

                        @Nullable
                        public final Object invokeSuspend(@NotNull Object $result) {
                           Object var3 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                           switch(this.label) {
                           case 0:
                              ResultKt.throwOnFailure($result);
                              CoroutineScope $this$withContext = (CoroutineScope)this.L$0;
                              Function2 var10000 = var2.getBlock();
                              this.label = 1;
                              if (var10000.invoke($this$withContext, this) == var3) {
                                 return var3;
                              }
                              break;
                           case 1:
                              ResultKt.throwOnFailure($result);
                              break;
                           default:
                              throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                           }

                           return Unit.INSTANCE;
                        }

                        @NotNull
                        public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
                           Function2 var3 = new <anonymous constructor>($completion);
                           var3.L$0 = value;
                           return (Continuation)var3;
                        }

                        @Nullable
                        public final Object invoke(@NotNull CoroutineScope p1, @Nullable Continuation<? super Unit> p2) {
                           return ((<undefinedtype>)this.create(p1, p2)).invokeSuspend(Unit.INSTANCE);
                        }
                     });
                     Continuation var10002 = (Continuation)this;
                     this.label = 2;
                     if (BuildersKt.withContext(var10000, var10001, var10002) == var2x) {
                        return var2x;
                     }
                  }
                  break;
               case 1:
                  ResultKt.throwOnFailure($result);
                  break;
               case 2:
                  ResultKt.throwOnFailure($result);
                  break;
               default:
                  throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
               }

               return Unit.INSTANCE;
            }

            @NotNull
            public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
               return (Continuation)(new <anonymous constructor>($completion));
            }

            @Nullable
            public final Object invoke(@NotNull CoroutineScope p1, @Nullable Continuation<? super Unit> p2) {
               return ((<undefinedtype>)this.create(p1, p2)).invokeSuspend(Unit.INSTANCE);
            }
         }), 3, (Object)null);
      }

   }

   // $FF: synthetic method
   public static void cancel$default(Coroutine var0, CancellationException var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = null;
      }

      var0.cancel(var1);
   }

   @NotNull
   public final DisposableHandle invokeOnCompletion(@NotNull Function1<? super Throwable, Unit> handler) {
      Intrinsics.checkNotNullParameter(handler, "handler");
      return this.job.invokeOnCompletion(handler);
   }

   private final Job executeInternal(CoroutineContext context, Function2<? super CoroutineScope, ? super Continuation<? super T>, ? extends Object> block) {
      return BuildersKt.launch$default(CoroutineScopeKt.plus(this.scope, (CoroutineContext)Dispatchers.getIO()), (CoroutineContext)null, (CoroutineStart)null, (Function2)(new Function2<CoroutineScope, Continuation<? super Unit>, Object>((Continuation)null) {
         Object L$1;
         int label;
         // $FF: synthetic field
         private Object L$0;

         @Nullable
         public final Object invokeSuspend(@NotNull Object $result) {
            // $FF: Couldn't be decompiled
         }

         @NotNull
         public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            Function2 var3 = new <anonymous constructor>($completion);
            var3.L$0 = value;
            return (Continuation)var3;
         }

         @Nullable
         public final Object invoke(@NotNull CoroutineScope p1, @Nullable Continuation<? super Unit> p2) {
            return ((<undefinedtype>)this.create(p1, p2)).invokeSuspend(Unit.INSTANCE);
         }
      }), 3, (Object)null);
   }

   private final Object dispatchVoidCallback(CoroutineScope scope, Coroutine<T>.VoidCallback callback, Continuation<? super Unit> $completion) {
      int $i$f$dispatchVoidCallback = false;
      if (callback.getContext() == null) {
         Function2 var5 = callback.getBlock();
         InlineMarker.mark(0);
         var5.invoke(scope, $completion);
         InlineMarker.mark(1);
         return Unit.INSTANCE;
      } else {
         CoroutineContext var10000 = scope.getCoroutineContext().plus(callback.getContext());
         Function2 var10001 = (Function2)(new Function2<CoroutineScope, Continuation<? super Unit>, Object>((Continuation)null) {
            int label;
            // $FF: synthetic field
            private Object L$0;

            @Nullable
            public final Object invokeSuspend(@NotNull Object $result) {
               Object var3 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
               switch(this.label) {
               case 0:
                  ResultKt.throwOnFailure($result);
                  CoroutineScope $this$withContext = (CoroutineScope)this.L$0;
                  Function2 var10000 = callback.getBlock();
                  this.label = 1;
                  if (var10000.invoke($this$withContext, this) == var3) {
                     return var3;
                  }
                  break;
               case 1:
                  ResultKt.throwOnFailure($result);
                  break;
               default:
                  throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
               }

               return Unit.INSTANCE;
            }

            @NotNull
            public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
               Function2 var3 = new <anonymous constructor>($completion);
               var3.L$0 = value;
               return (Continuation)var3;
            }

            @Nullable
            public final Object invoke(@NotNull CoroutineScope p1, @Nullable Continuation<? super Unit> p2) {
               return ((<undefinedtype>)this.create(p1, p2)).invokeSuspend(Unit.INSTANCE);
            }
         });
         InlineMarker.mark(0);
         BuildersKt.withContext(var10000, var10001, $completion);
         InlineMarker.mark(1);
         return Unit.INSTANCE;
      }
   }

   private final <R> Object dispatchCallback(CoroutineScope scope, R value, Coroutine<T>.Callback<R> callback, Continuation<? super Unit> $completion) {
      int $i$f$dispatchCallback = false;
      if (!CoroutineScopeKt.isActive(scope)) {
         return Unit.INSTANCE;
      } else if (callback.getContext() == null) {
         Function3 var6 = callback.getBlock();
         InlineMarker.mark(0);
         var6.invoke(scope, value, $completion);
         InlineMarker.mark(1);
         return Unit.INSTANCE;
      } else {
         CoroutineContext var10000 = scope.getCoroutineContext().plus(callback.getContext());
         Function2 var10001 = (Function2)(new Function2<CoroutineScope, Continuation<? super Unit>, Object>((Continuation)null) {
            int label;
            // $FF: synthetic field
            private Object L$0;

            @Nullable
            public final Object invokeSuspend(@NotNull Object $result) {
               Object var3 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
               switch(this.label) {
               case 0:
                  ResultKt.throwOnFailure($result);
                  CoroutineScope $this$withContext = (CoroutineScope)this.L$0;
                  Function3 var10000 = callback.getBlock();
                  Object var10002 = value;
                  this.label = 1;
                  if (var10000.invoke($this$withContext, var10002, this) == var3) {
                     return var3;
                  }
                  break;
               case 1:
                  ResultKt.throwOnFailure($result);
                  break;
               default:
                  throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
               }

               return Unit.INSTANCE;
            }

            @NotNull
            public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
               Function2 var3 = new <anonymous constructor>($completion);
               var3.L$0 = valuex;
               return (Continuation)var3;
            }

            @Nullable
            public final Object invoke(@NotNull CoroutineScope p1, @Nullable Continuation<? super Unit> p2) {
               return ((<undefinedtype>)this.create(p1, p2)).invokeSuspend(Unit.INSTANCE);
            }
         });
         InlineMarker.mark(0);
         BuildersKt.withContext(var10000, var10001, $completion);
         InlineMarker.mark(1);
         return Unit.INSTANCE;
      }
   }

   private final Object executeBlock(CoroutineScope scope, CoroutineContext context, long timeMillis, Function2<? super CoroutineScope, ? super Continuation<? super T>, ? extends Object> block, Continuation<? super T> $completion) {
      int $i$f$executeBlock = false;
      CoroutineContext var10000 = scope.getCoroutineContext().plus(context);
      Function2 var10001 = (Function2)(new Function2<CoroutineScope, Continuation<? super T>, Object>((Continuation)null) {
         int label;
         // $FF: synthetic field
         private Object L$0;

         @Nullable
         public final Object invokeSuspend(@NotNull Object $result) {
            Object var3 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            Object var10000;
            switch(this.label) {
            case 0:
               ResultKt.throwOnFailure($result);
               CoroutineScope $this$withContext = (CoroutineScope)this.L$0;
               if (timeMillis > 0L) {
                  long var4 = timeMillis;
                  Function2 var10001 = (Function2)(new Function2<CoroutineScope, Continuation<? super T>, Object>((Continuation)null) {
                     int label;
                     // $FF: synthetic field
                     private Object L$0;

                     @Nullable
                     public final Object invokeSuspend(@NotNull Object $result) {
                        Object var3 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        Object var10000;
                        switch(this.label) {
                        case 0:
                           ResultKt.throwOnFailure($result);
                           CoroutineScope $this$withTimeout = (CoroutineScope)this.L$0;
                           Function2 var4 = block;
                           this.label = 1;
                           var10000 = var4.invoke($this$withTimeout, this);
                           if (var10000 == var3) {
                              return var3;
                           }
                           break;
                        case 1:
                           ResultKt.throwOnFailure($result);
                           var10000 = $result;
                           break;
                        default:
                           throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }

                        return var10000;
                     }

                     @NotNull
                     public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
                        Function2 var3 = new <anonymous constructor>($completion);
                        var3.L$0 = value;
                        return (Continuation)var3;
                     }

                     @Nullable
                     public final Object invoke(@NotNull CoroutineScope p1, @Nullable Continuation<? super T> p2) {
                        return ((<undefinedtype>)this.create(p1, p2)).invokeSuspend(Unit.INSTANCE);
                     }
                  });
                  Continuation var10002 = (Continuation)this;
                  this.label = 1;
                  var10000 = TimeoutKt.withTimeout(var4, var10001, var10002);
                  if (var10000 == var3) {
                     return var3;
                  }
               } else {
                  Function2 var5 = block;
                  this.label = 2;
                  var10000 = var5.invoke($this$withContext, this);
                  if (var10000 == var3) {
                     return var3;
                  }
               }
               break;
            case 1:
               ResultKt.throwOnFailure($result);
               var10000 = $result;
               break;
            case 2:
               ResultKt.throwOnFailure($result);
               var10000 = $result;
               break;
            default:
               throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }

            return var10000;
         }

         @NotNull
         public final Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> $completion) {
            Function2 var3 = new <anonymous constructor>($completion);
            var3.L$0 = value;
            return (Continuation)var3;
         }

         @Nullable
         public final Object invoke(@NotNull CoroutineScope p1, @Nullable Continuation<? super T> p2) {
            return ((<undefinedtype>)this.create(p1, p2)).invokeSuspend(Unit.INSTANCE);
         }
      });
      InlineMarker.mark(0);
      Object var8 = BuildersKt.withContext(var10000, var10001, $completion);
      InlineMarker.mark(1);
      return var8;
   }

   // $FF: synthetic method
   public static final Coroutine.VoidCallback access$getStart$p(Coroutine $this) {
      return $this.start;
   }

   // $FF: synthetic method
   public static final Long access$getTimeMillis$p(Coroutine $this) {
      return $this.timeMillis;
   }

   // $FF: synthetic method
   public static final Coroutine.Callback access$getSuccess$p(Coroutine $this) {
      return $this.success;
   }

   // $FF: synthetic method
   public static final Coroutine.Result access$getErrorReturn$p(Coroutine $this) {
      return $this.errorReturn;
   }

   // $FF: synthetic method
   public static final Coroutine.Callback access$getError$p(Coroutine $this) {
      return $this.error;
   }

   // $FF: synthetic method
   public static final Coroutine.VoidCallback access$getFinally$p(Coroutine $this) {
      return $this.finally;
   }

   @Metadata(
      mv = {1, 5, 1},
      k = 1,
      xi = 48,
      d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JW\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0006\"\u0004\b\u0001\u0010\u00072\b\b\u0002\u0010\b\u001a\u00020\u00042\b\b\u0002\u0010\t\u001a\u00020\n2'\u0010\u000b\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00070\r\u0012\u0006\u0012\u0004\u0018\u00010\u00010\f¢\u0006\u0002\b\u000eø\u0001\u0000¢\u0006\u0002\u0010\u000fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0010"},
      d2 = {"Lio/legado/app/help/coroutine/Coroutine$Companion;", "", "()V", "DEFAULT", "Lkotlinx/coroutines/CoroutineScope;", "async", "Lio/legado/app/help/coroutine/Coroutine;", "T", "scope", "context", "Lkotlin/coroutines/CoroutineContext;", "block", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "Lkotlin/ExtensionFunctionType;", "(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function2;)Lio/legado/app/help/coroutine/Coroutine;", "reader-pro"}
   )
   public static final class Companion {
      private Companion() {
      }

      @NotNull
      public final <T> Coroutine<T> async(@NotNull CoroutineScope scope, @NotNull CoroutineContext context, @NotNull Function2<? super CoroutineScope, ? super Continuation<? super T>, ? extends Object> block) {
         Intrinsics.checkNotNullParameter(scope, "scope");
         Intrinsics.checkNotNullParameter(context, "context");
         Intrinsics.checkNotNullParameter(block, "block");
         return new Coroutine(scope, context, block);
      }

      // $FF: synthetic method
      public static Coroutine async$default(Coroutine.Companion var0, CoroutineScope var1, CoroutineContext var2, Function2 var3, int var4, Object var5) {
         if ((var4 & 1) != 0) {
            var1 = Coroutine.DEFAULT;
         }

         if ((var4 & 2) != 0) {
            var2 = (CoroutineContext)Dispatchers.getIO();
         }

         return var0.async(var1, var2, var3);
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker $constructor_marker) {
         this();
      }
   }

   @Metadata(
      mv = {1, 5, 1},
      k = 1,
      xi = 48,
      d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u0000*\u0006\b\u0001\u0010\u0001 \u00012\u00020\u0002B\u000f\u0012\b\u0010\u0003\u001a\u0004\u0018\u00018\u0001¢\u0006\u0002\u0010\u0004J\u0010\u0010\b\u001a\u0004\u0018\u00018\u0001HÆ\u0003¢\u0006\u0002\u0010\u0006J \u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00018\u0001HÆ\u0001¢\u0006\u0002\u0010\nJ\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0015\u0010\u0003\u001a\u0004\u0018\u00018\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0012"},
      d2 = {"Lio/legado/app/help/coroutine/Coroutine$Result;", "T", "", "value", "(Ljava/lang/Object;)V", "getValue", "()Ljava/lang/Object;", "Ljava/lang/Object;", "component1", "copy", "(Ljava/lang/Object;)Lio/legado/app/help/coroutine/Coroutine$Result;", "equals", "", "other", "hashCode", "", "toString", "", "reader-pro"}
   )
   private static final class Result<T> {
      @Nullable
      private final T value;

      public Result(@Nullable T value) {
         this.value = value;
      }

      @Nullable
      public final T getValue() {
         return this.value;
      }

      @Nullable
      public final T component1() {
         return this.value;
      }

      @NotNull
      public final Coroutine.Result<T> copy(@Nullable T value) {
         return new Coroutine.Result(value);
      }

      // $FF: synthetic method
      public static Coroutine.Result copy$default(Coroutine.Result var0, Object var1, int var2, Object var3) {
         if ((var2 & 1) != 0) {
            var1 = var0.value;
         }

         return var0.copy(var1);
      }

      @NotNull
      public String toString() {
         return "Result(value=" + this.value + ')';
      }

      public int hashCode() {
         return this.value == null ? 0 : this.value.hashCode();
      }

      public boolean equals(@Nullable Object other) {
         if (this == other) {
            return true;
         } else if (!(other instanceof Coroutine.Result)) {
            return false;
         } else {
            Coroutine.Result var2 = (Coroutine.Result)other;
            return Intrinsics.areEqual(this.value, var2.value);
         }
      }
   }

   @Metadata(
      mv = {1, 5, 1},
      k = 1,
      xi = 48,
      d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0082\u0004\u0018\u00002\u00020\u0001B;\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012'\u0010\u0004\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0005¢\u0006\u0002\b\tø\u0001\u0000¢\u0006\u0002\u0010\nR7\u0010\u0004\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0005¢\u0006\u0002\b\tø\u0001\u0000¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0010"},
      d2 = {"Lio/legado/app/help/coroutine/Coroutine$VoidCallback;", "", "context", "Lkotlin/coroutines/CoroutineContext;", "block", "Lkotlin/Function2;", "Lkotlinx/coroutines/CoroutineScope;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Lio/legado/app/help/coroutine/Coroutine;Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function2;)V", "getBlock", "()Lkotlin/jvm/functions/Function2;", "Lkotlin/jvm/functions/Function2;", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "reader-pro"}
   )
   private final class VoidCallback {
      @Nullable
      private final CoroutineContext context;
      @NotNull
      private final Function2<CoroutineScope, Continuation<? super Unit>, Object> block;

      public VoidCallback(@Nullable CoroutineContext context, @NotNull Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object> block) {
         Intrinsics.checkNotNullParameter(Coroutine.this, "this$0");
         Intrinsics.checkNotNullParameter(block, "block");
         super();
         this.context = context;
         this.block = block;
      }

      @Nullable
      public final CoroutineContext getContext() {
         return this.context;
      }

      @NotNull
      public final Function2<CoroutineScope, Continuation<? super Unit>, Object> getBlock() {
         return this.block;
      }
   }

   @Metadata(
      mv = {1, 5, 1},
      k = 1,
      xi = 48,
      d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0082\u0004\u0018\u0000*\u0004\b\u0001\u0010\u00012\u00020\u0002BA\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012-\u0010\u0005\u001a)\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00028\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0006¢\u0006\u0002\b\nø\u0001\u0000¢\u0006\u0002\u0010\u000bR=\u0010\u0005\u001a)\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00028\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0006¢\u0006\u0002\b\nø\u0001\u0000¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0011"},
      d2 = {"Lio/legado/app/help/coroutine/Coroutine$Callback;", "VALUE", "", "context", "Lkotlin/coroutines/CoroutineContext;", "block", "Lkotlin/Function3;", "Lkotlinx/coroutines/CoroutineScope;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Lio/legado/app/help/coroutine/Coroutine;Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function3;)V", "getBlock", "()Lkotlin/jvm/functions/Function3;", "Lkotlin/jvm/functions/Function3;", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "reader-pro"}
   )
   private final class Callback<VALUE> {
      @Nullable
      private final CoroutineContext context;
      @NotNull
      private final Function3<CoroutineScope, VALUE, Continuation<? super Unit>, Object> block;

      public Callback(@Nullable CoroutineContext context, @NotNull Function3<? super CoroutineScope, ? super VALUE, ? super Continuation<? super Unit>, ? extends Object> block) {
         Intrinsics.checkNotNullParameter(Coroutine.this, "this$0");
         Intrinsics.checkNotNullParameter(block, "block");
         super();
         this.context = context;
         this.block = block;
      }

      @Nullable
      public final CoroutineContext getContext() {
         return this.context;
      }

      @NotNull
      public final Function3<CoroutineScope, VALUE, Continuation<? super Unit>, Object> getBlock() {
         return this.block;
      }
   }
}
