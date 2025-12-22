package io.vertx.kotlin.core.file;

import ch.qos.logback.classic.ClassicConstants;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.cli.converters.FromBasedConverter;
import io.vertx.core.file.AsyncFile;
import io.vertx.core.file.CopyOptions;
import io.vertx.core.file.FileProps;
import io.vertx.core.file.FileSystem;
import io.vertx.core.file.FileSystemProps;
import io.vertx.core.file.OpenOptions;
import io.vertx.kotlin.coroutines.VertxCoroutineKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: FileSystem.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��X\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0006\u001a%\u0010��\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0006\u001a-\u0010\u0007\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004H\u0086@ø\u0001��¢\u0006\u0002\u0010\t\u001a1\u0010\n\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\u0010\u000b\u001a\u0004\u0018\u00010\u00042\b\u0010\f\u001a\u0004\u0018\u00010\u0004H\u0086@ø\u0001��¢\u0006\u0002\u0010\t\u001a%\u0010\r\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0004H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0006\u001a-\u0010\r\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0011H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0012\u001a-\u0010\u0013\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u0015H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0016\u001a\u001d\u0010\u0017\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0018\u001a%\u0010\u0017\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0006\u001a\u001d\u0010\u0019\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u0004H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0018\u001a%\u0010\u0019\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0006\u001a-\u0010\u0019\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0086@ø\u0001��¢\u0006\u0002\u0010\t\u001a%\u0010\u001c\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u0004H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0006\u001a-\u0010\u001c\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0086@ø\u0001��¢\u0006\u0002\u0010\t\u001a5\u0010\u001c\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0086@ø\u0001��¢\u0006\u0002\u0010\u001e\u001a\u001d\u0010\u001f\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0018\u001a%\u0010 \u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u0015H\u0086@ø\u0001��¢\u0006\u0002\u0010!\u001a\u001d\u0010\"\u001a\u00020\u0015*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0018\u001a\u001d\u0010#\u001a\u00020$*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0018\u001a%\u0010%\u001a\u00020\u0001*\u00020\u00022\u0006\u0010&\u001a\u00020\u00042\u0006\u0010'\u001a\u00020\u0004H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0006\u001a\u001d\u0010(\u001a\u00020)*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0018\u001a\u001d\u0010*\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0018\u001a%\u0010*\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0006\u001a\u001d\u0010+\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0018\u001a%\u0010+\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0006\u001a%\u0010,\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0004H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0006\u001a-\u0010,\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0011H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0012\u001a%\u0010-\u001a\u00020.*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020/H\u0086@ø\u0001��¢\u0006\u0002\u00100\u001a\u001d\u00101\u001a\u00020)*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0018\u001a#\u00102\u001a\b\u0012\u0004\u0012\u00020\u000403*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0018\u001a+\u00102\u001a\b\u0012\u0004\u0012\u00020\u000403*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u00104\u001a\u00020\u0004H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0006\u001a\u001d\u00105\u001a\u000206*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0018\u001a\u001d\u00107\u001a\u00020\u0004*\u00020\u00022\u0006\u0010&\u001a\u00020\u0004H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0018\u001a%\u00108\u001a\u00020\u0001*\u00020\u00022\u0006\u0010&\u001a\u00020\u00042\u0006\u0010'\u001a\u00020\u0004H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0006\u001a%\u00109\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010:\u001a\u00020;H\u0086@ø\u0001��¢\u0006\u0002\u0010<\u001a\u001d\u0010=\u001a\u00020\u0001*\u00020\u00022\u0006\u0010&\u001a\u00020\u0004H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0018\u001a%\u0010>\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010?\u001a\u000206H\u0086@ø\u0001��¢\u0006\u0002\u0010@\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006A"}, d2 = {"chmodAwait", "", "Lio/vertx/core/file/FileSystem;", "path", "", "perms", "(Lio/vertx/core/file/FileSystem;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "chmodRecursiveAwait", "dirPerms", "(Lio/vertx/core/file/FileSystem;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "chownAwait", ClassicConstants.USER_MDC_KEY, "group", "copyAwait", FromBasedConverter.FROM, "to", "options", "Lio/vertx/core/file/CopyOptions;", "(Lio/vertx/core/file/FileSystem;Ljava/lang/String;Ljava/lang/String;Lio/vertx/core/file/CopyOptions;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "copyRecursiveAwait", "recursive", "", "(Lio/vertx/core/file/FileSystem;Ljava/lang/String;Ljava/lang/String;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createFileAwait", "(Lio/vertx/core/file/FileSystem;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createTempDirectoryAwait", "prefix", "dir", "createTempFileAwait", "suffix", "(Lio/vertx/core/file/FileSystem;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteAwait", "deleteRecursiveAwait", "(Lio/vertx/core/file/FileSystem;Ljava/lang/String;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "existsAwait", "fsPropsAwait", "Lio/vertx/core/file/FileSystemProps;", "linkAwait", "link", "existing", "lpropsAwait", "Lio/vertx/core/file/FileProps;", "mkdirAwait", "mkdirsAwait", "moveAwait", "openAwait", "Lio/vertx/core/file/AsyncFile;", "Lio/vertx/core/file/OpenOptions;", "(Lio/vertx/core/file/FileSystem;Ljava/lang/String;Lio/vertx/core/file/OpenOptions;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "propsAwait", "readDirAwait", "", "filter", "readFileAwait", "Lio/vertx/core/buffer/Buffer;", "readSymlinkAwait", "symlinkAwait", "truncateAwait", "len", "", "(Lio/vertx/core/file/FileSystem;Ljava/lang/String;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "unlinkAwait", "writeFileAwait", "data", "(Lio/vertx/core/file/FileSystem;Ljava/lang/String;Lio/vertx/core/buffer/Buffer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/core/file/FileSystemKt.class */
public final class FileSystemKt {
    @Nullable
    public static final Object copyAwait(@NotNull final FileSystem $this$copyAwait, @NotNull final String from, @NotNull final String to, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.core.file.FileSystemKt.copyAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Unit>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull final Handler<AsyncResult<Unit>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$copyAwait.copy(from, to, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.core.file.FileSystemKt.copyAwait.2.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // io.vertx.core.Handler
                    public final void handle(AsyncResult<Void> asyncResult) {
                        it.handle(asyncResult.mapEmpty());
                    }
                });
            }
        }, continuation);
    }

    @Nullable
    public static final Object copyAwait(@NotNull final FileSystem $this$copyAwait, @NotNull final String from, @NotNull final String to, @NotNull final CopyOptions options, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.core.file.FileSystemKt.copyAwait.4
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Unit>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull final Handler<AsyncResult<Unit>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$copyAwait.copy(from, to, options, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.core.file.FileSystemKt.copyAwait.4.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // io.vertx.core.Handler
                    public final void handle(AsyncResult<Void> asyncResult) {
                        it.handle(asyncResult.mapEmpty());
                    }
                });
            }
        }, continuation);
    }

    @Nullable
    public static final Object copyRecursiveAwait(@NotNull final FileSystem $this$copyRecursiveAwait, @NotNull final String from, @NotNull final String to, final boolean recursive, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.core.file.FileSystemKt.copyRecursiveAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Unit>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull final Handler<AsyncResult<Unit>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$copyRecursiveAwait.copyRecursive(from, to, recursive, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.core.file.FileSystemKt.copyRecursiveAwait.2.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // io.vertx.core.Handler
                    public final void handle(AsyncResult<Void> asyncResult) {
                        it.handle(asyncResult.mapEmpty());
                    }
                });
            }
        }, continuation);
    }

    @Nullable
    public static final Object moveAwait(@NotNull final FileSystem $this$moveAwait, @NotNull final String from, @NotNull final String to, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.core.file.FileSystemKt.moveAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Unit>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull final Handler<AsyncResult<Unit>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$moveAwait.move(from, to, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.core.file.FileSystemKt.moveAwait.2.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // io.vertx.core.Handler
                    public final void handle(AsyncResult<Void> asyncResult) {
                        it.handle(asyncResult.mapEmpty());
                    }
                });
            }
        }, continuation);
    }

    @Nullable
    public static final Object moveAwait(@NotNull final FileSystem $this$moveAwait, @NotNull final String from, @NotNull final String to, @NotNull final CopyOptions options, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.core.file.FileSystemKt.moveAwait.4
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Unit>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull final Handler<AsyncResult<Unit>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$moveAwait.move(from, to, options, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.core.file.FileSystemKt.moveAwait.4.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // io.vertx.core.Handler
                    public final void handle(AsyncResult<Void> asyncResult) {
                        it.handle(asyncResult.mapEmpty());
                    }
                });
            }
        }, continuation);
    }

    @Nullable
    public static final Object truncateAwait(@NotNull final FileSystem $this$truncateAwait, @NotNull final String path, final long len, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.core.file.FileSystemKt.truncateAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Unit>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull final Handler<AsyncResult<Unit>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$truncateAwait.truncate(path, len, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.core.file.FileSystemKt.truncateAwait.2.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // io.vertx.core.Handler
                    public final void handle(AsyncResult<Void> asyncResult) {
                        it.handle(asyncResult.mapEmpty());
                    }
                });
            }
        }, continuation);
    }

    @Nullable
    public static final Object chmodAwait(@NotNull final FileSystem $this$chmodAwait, @NotNull final String path, @NotNull final String perms, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.core.file.FileSystemKt.chmodAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Unit>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull final Handler<AsyncResult<Unit>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$chmodAwait.chmod(path, perms, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.core.file.FileSystemKt.chmodAwait.2.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // io.vertx.core.Handler
                    public final void handle(AsyncResult<Void> asyncResult) {
                        it.handle(asyncResult.mapEmpty());
                    }
                });
            }
        }, continuation);
    }

    @Nullable
    public static final Object chmodRecursiveAwait(@NotNull final FileSystem $this$chmodRecursiveAwait, @NotNull final String path, @NotNull final String perms, @NotNull final String dirPerms, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.core.file.FileSystemKt.chmodRecursiveAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Unit>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull final Handler<AsyncResult<Unit>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$chmodRecursiveAwait.chmodRecursive(path, perms, dirPerms, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.core.file.FileSystemKt.chmodRecursiveAwait.2.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // io.vertx.core.Handler
                    public final void handle(AsyncResult<Void> asyncResult) {
                        it.handle(asyncResult.mapEmpty());
                    }
                });
            }
        }, continuation);
    }

    @Nullable
    public static final Object chownAwait(@NotNull final FileSystem $this$chownAwait, @NotNull final String path, @Nullable final String user, @Nullable final String group, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.core.file.FileSystemKt.chownAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Unit>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull final Handler<AsyncResult<Unit>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$chownAwait.chown(path, user, group, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.core.file.FileSystemKt.chownAwait.2.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // io.vertx.core.Handler
                    public final void handle(AsyncResult<Void> asyncResult) {
                        it.handle(asyncResult.mapEmpty());
                    }
                });
            }
        }, continuation);
    }

    @Nullable
    public static final Object propsAwait(@NotNull final FileSystem $this$propsAwait, @NotNull final String path, @NotNull Continuation<? super FileProps> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<FileProps>>, Unit>() { // from class: io.vertx.kotlin.core.file.FileSystemKt.propsAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<FileProps>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<FileProps>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$propsAwait.props(path, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object lpropsAwait(@NotNull final FileSystem $this$lpropsAwait, @NotNull final String path, @NotNull Continuation<? super FileProps> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<FileProps>>, Unit>() { // from class: io.vertx.kotlin.core.file.FileSystemKt.lpropsAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<FileProps>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<FileProps>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$lpropsAwait.lprops(path, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object linkAwait(@NotNull final FileSystem $this$linkAwait, @NotNull final String link, @NotNull final String existing, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.core.file.FileSystemKt.linkAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Unit>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull final Handler<AsyncResult<Unit>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$linkAwait.link(link, existing, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.core.file.FileSystemKt.linkAwait.2.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // io.vertx.core.Handler
                    public final void handle(AsyncResult<Void> asyncResult) {
                        it.handle(asyncResult.mapEmpty());
                    }
                });
            }
        }, continuation);
    }

    @Nullable
    public static final Object symlinkAwait(@NotNull final FileSystem $this$symlinkAwait, @NotNull final String link, @NotNull final String existing, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.core.file.FileSystemKt.symlinkAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Unit>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull final Handler<AsyncResult<Unit>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$symlinkAwait.symlink(link, existing, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.core.file.FileSystemKt.symlinkAwait.2.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // io.vertx.core.Handler
                    public final void handle(AsyncResult<Void> asyncResult) {
                        it.handle(asyncResult.mapEmpty());
                    }
                });
            }
        }, continuation);
    }

    @Nullable
    public static final Object unlinkAwait(@NotNull final FileSystem $this$unlinkAwait, @NotNull final String link, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.core.file.FileSystemKt.unlinkAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Unit>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull final Handler<AsyncResult<Unit>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$unlinkAwait.unlink(link, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.core.file.FileSystemKt.unlinkAwait.2.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // io.vertx.core.Handler
                    public final void handle(AsyncResult<Void> asyncResult) {
                        it.handle(asyncResult.mapEmpty());
                    }
                });
            }
        }, continuation);
    }

    @Nullable
    public static final Object readSymlinkAwait(@NotNull final FileSystem $this$readSymlinkAwait, @NotNull final String link, @NotNull Continuation<? super String> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<String>>, Unit>() { // from class: io.vertx.kotlin.core.file.FileSystemKt.readSymlinkAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<String>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<String>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$readSymlinkAwait.readSymlink(link, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object deleteAwait(@NotNull final FileSystem $this$deleteAwait, @NotNull final String path, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.core.file.FileSystemKt.deleteAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Unit>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull final Handler<AsyncResult<Unit>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$deleteAwait.delete(path, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.core.file.FileSystemKt.deleteAwait.2.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // io.vertx.core.Handler
                    public final void handle(AsyncResult<Void> asyncResult) {
                        it.handle(asyncResult.mapEmpty());
                    }
                });
            }
        }, continuation);
    }

    @Nullable
    public static final Object deleteRecursiveAwait(@NotNull final FileSystem $this$deleteRecursiveAwait, @NotNull final String path, final boolean recursive, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.core.file.FileSystemKt.deleteRecursiveAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Unit>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull final Handler<AsyncResult<Unit>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$deleteRecursiveAwait.deleteRecursive(path, recursive, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.core.file.FileSystemKt.deleteRecursiveAwait.2.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // io.vertx.core.Handler
                    public final void handle(AsyncResult<Void> asyncResult) {
                        it.handle(asyncResult.mapEmpty());
                    }
                });
            }
        }, continuation);
    }

    @Nullable
    public static final Object mkdirAwait(@NotNull final FileSystem $this$mkdirAwait, @NotNull final String path, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.core.file.FileSystemKt.mkdirAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Unit>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull final Handler<AsyncResult<Unit>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$mkdirAwait.mkdir(path, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.core.file.FileSystemKt.mkdirAwait.2.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // io.vertx.core.Handler
                    public final void handle(AsyncResult<Void> asyncResult) {
                        it.handle(asyncResult.mapEmpty());
                    }
                });
            }
        }, continuation);
    }

    @Nullable
    public static final Object mkdirAwait(@NotNull final FileSystem $this$mkdirAwait, @NotNull final String path, @NotNull final String perms, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.core.file.FileSystemKt.mkdirAwait.4
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Unit>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull final Handler<AsyncResult<Unit>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$mkdirAwait.mkdir(path, perms, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.core.file.FileSystemKt.mkdirAwait.4.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // io.vertx.core.Handler
                    public final void handle(AsyncResult<Void> asyncResult) {
                        it.handle(asyncResult.mapEmpty());
                    }
                });
            }
        }, continuation);
    }

    @Nullable
    public static final Object mkdirsAwait(@NotNull final FileSystem $this$mkdirsAwait, @NotNull final String path, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.core.file.FileSystemKt.mkdirsAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Unit>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull final Handler<AsyncResult<Unit>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$mkdirsAwait.mkdirs(path, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.core.file.FileSystemKt.mkdirsAwait.2.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // io.vertx.core.Handler
                    public final void handle(AsyncResult<Void> asyncResult) {
                        it.handle(asyncResult.mapEmpty());
                    }
                });
            }
        }, continuation);
    }

    @Nullable
    public static final Object mkdirsAwait(@NotNull final FileSystem $this$mkdirsAwait, @NotNull final String path, @NotNull final String perms, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.core.file.FileSystemKt.mkdirsAwait.4
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Unit>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull final Handler<AsyncResult<Unit>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$mkdirsAwait.mkdirs(path, perms, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.core.file.FileSystemKt.mkdirsAwait.4.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // io.vertx.core.Handler
                    public final void handle(AsyncResult<Void> asyncResult) {
                        it.handle(asyncResult.mapEmpty());
                    }
                });
            }
        }, continuation);
    }

    @Nullable
    public static final Object readDirAwait(@NotNull final FileSystem $this$readDirAwait, @NotNull final String path, @NotNull Continuation<? super List<String>> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<List<? extends String>>>, Unit>() { // from class: io.vertx.kotlin.core.file.FileSystemKt.readDirAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<List<? extends String>>> handler) {
                invoke2((Handler<AsyncResult<List<String>>>) handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<List<String>>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$readDirAwait.readDir(path, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object readDirAwait(@NotNull final FileSystem $this$readDirAwait, @NotNull final String path, @NotNull final String filter, @NotNull Continuation<? super List<String>> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<List<? extends String>>>, Unit>() { // from class: io.vertx.kotlin.core.file.FileSystemKt.readDirAwait.4
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<List<? extends String>>> handler) {
                invoke2((Handler<AsyncResult<List<String>>>) handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<List<String>>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$readDirAwait.readDir(path, filter, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object readFileAwait(@NotNull final FileSystem $this$readFileAwait, @NotNull final String path, @NotNull Continuation<? super Buffer> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Buffer>>, Unit>() { // from class: io.vertx.kotlin.core.file.FileSystemKt.readFileAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Buffer>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<Buffer>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$readFileAwait.readFile(path, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object writeFileAwait(@NotNull final FileSystem $this$writeFileAwait, @NotNull final String path, @NotNull final Buffer data, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.core.file.FileSystemKt.writeFileAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Unit>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull final Handler<AsyncResult<Unit>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$writeFileAwait.writeFile(path, data, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.core.file.FileSystemKt.writeFileAwait.2.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // io.vertx.core.Handler
                    public final void handle(AsyncResult<Void> asyncResult) {
                        it.handle(asyncResult.mapEmpty());
                    }
                });
            }
        }, continuation);
    }

    @Nullable
    public static final Object openAwait(@NotNull final FileSystem $this$openAwait, @NotNull final String path, @NotNull final OpenOptions options, @NotNull Continuation<? super AsyncFile> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<AsyncFile>>, Unit>() { // from class: io.vertx.kotlin.core.file.FileSystemKt.openAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<AsyncFile>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<AsyncFile>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$openAwait.open(path, options, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object createFileAwait(@NotNull final FileSystem $this$createFileAwait, @NotNull final String path, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.core.file.FileSystemKt.createFileAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Unit>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull final Handler<AsyncResult<Unit>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$createFileAwait.createFile(path, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.core.file.FileSystemKt.createFileAwait.2.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // io.vertx.core.Handler
                    public final void handle(AsyncResult<Void> asyncResult) {
                        it.handle(asyncResult.mapEmpty());
                    }
                });
            }
        }, continuation);
    }

    @Nullable
    public static final Object createFileAwait(@NotNull final FileSystem $this$createFileAwait, @NotNull final String path, @NotNull final String perms, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.core.file.FileSystemKt.createFileAwait.4
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Unit>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull final Handler<AsyncResult<Unit>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$createFileAwait.createFile(path, perms, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.core.file.FileSystemKt.createFileAwait.4.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // io.vertx.core.Handler
                    public final void handle(AsyncResult<Void> asyncResult) {
                        it.handle(asyncResult.mapEmpty());
                    }
                });
            }
        }, continuation);
    }

    @Nullable
    public static final Object existsAwait(@NotNull final FileSystem $this$existsAwait, @NotNull final String path, @NotNull Continuation<? super Boolean> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Boolean>>, Unit>() { // from class: io.vertx.kotlin.core.file.FileSystemKt.existsAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Boolean>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<Boolean>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$existsAwait.exists(path, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object fsPropsAwait(@NotNull final FileSystem $this$fsPropsAwait, @NotNull final String path, @NotNull Continuation<? super FileSystemProps> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<FileSystemProps>>, Unit>() { // from class: io.vertx.kotlin.core.file.FileSystemKt.fsPropsAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<FileSystemProps>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<FileSystemProps>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$fsPropsAwait.fsProps(path, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object createTempDirectoryAwait(@NotNull final FileSystem $this$createTempDirectoryAwait, @NotNull final String prefix, @NotNull Continuation<? super String> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<String>>, Unit>() { // from class: io.vertx.kotlin.core.file.FileSystemKt.createTempDirectoryAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<String>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<String>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$createTempDirectoryAwait.createTempDirectory(prefix, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object createTempDirectoryAwait(@NotNull final FileSystem $this$createTempDirectoryAwait, @NotNull final String prefix, @NotNull final String perms, @NotNull Continuation<? super String> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<String>>, Unit>() { // from class: io.vertx.kotlin.core.file.FileSystemKt.createTempDirectoryAwait.4
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<String>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<String>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$createTempDirectoryAwait.createTempDirectory(prefix, perms, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object createTempDirectoryAwait(@NotNull final FileSystem $this$createTempDirectoryAwait, @NotNull final String dir, @NotNull final String prefix, @NotNull final String perms, @NotNull Continuation<? super String> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<String>>, Unit>() { // from class: io.vertx.kotlin.core.file.FileSystemKt.createTempDirectoryAwait.6
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<String>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<String>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$createTempDirectoryAwait.createTempDirectory(dir, prefix, perms, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object createTempFileAwait(@NotNull final FileSystem $this$createTempFileAwait, @NotNull final String prefix, @NotNull final String suffix, @NotNull Continuation<? super String> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<String>>, Unit>() { // from class: io.vertx.kotlin.core.file.FileSystemKt.createTempFileAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<String>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<String>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$createTempFileAwait.createTempFile(prefix, suffix, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object createTempFileAwait(@NotNull final FileSystem $this$createTempFileAwait, @NotNull final String prefix, @NotNull final String suffix, @NotNull final String perms, @NotNull Continuation<? super String> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<String>>, Unit>() { // from class: io.vertx.kotlin.core.file.FileSystemKt.createTempFileAwait.4
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<String>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<String>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$createTempFileAwait.createTempFile(prefix, suffix, perms, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object createTempFileAwait(@NotNull final FileSystem $this$createTempFileAwait, @NotNull final String dir, @NotNull final String prefix, @NotNull final String suffix, @NotNull final String perms, @NotNull Continuation<? super String> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<String>>, Unit>() { // from class: io.vertx.kotlin.core.file.FileSystemKt.createTempFileAwait.6
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<String>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<String>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$createTempFileAwait.createTempFile(dir, prefix, suffix, perms, it);
            }
        }, continuation);
    }
}
