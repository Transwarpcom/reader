package kotlinx.coroutines.internal;

import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.sequences.SequencesKt;
import kotlinx.coroutines.MainCoroutineDispatcher;
import org.jetbrains.annotations.NotNull;

/* compiled from: MainDispatchers.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��\u001a\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0010\u000b\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018��2\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\u0006H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n��R\u0010\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n��¨\u0006\b"}, d2 = {"Lkotlinx/coroutines/internal/MainDispatcherLoader;", "", "()V", "FAST_SERVICE_LOADER_ENABLED", "", "dispatcher", "Lkotlinx/coroutines/MainCoroutineDispatcher;", "loadMainDispatcher", "kotlinx-coroutines-core"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/internal/MainDispatcherLoader.class */
public final class MainDispatcherLoader {

    @NotNull
    public static final MainDispatcherLoader INSTANCE = new MainDispatcherLoader();
    private static final boolean FAST_SERVICE_LOADER_ENABLED = SystemPropsKt.systemProp("kotlinx.coroutines.fast.service.loader", true);

    @JvmField
    @NotNull
    public static final MainCoroutineDispatcher dispatcher = INSTANCE.loadMainDispatcher();

    private MainDispatcherLoader() {
    }

    private final MainCoroutineDispatcher loadMainDispatcher() {
        MainCoroutineDispatcher mainCoroutineDispatcherCreateMissingDispatcher$default;
        Object obj;
        try {
            List factories = FAST_SERVICE_LOADER_ENABLED ? FastServiceLoader.INSTANCE.loadMainDispatcherFactory$kotlinx_coroutines_core() : SequencesKt.toList(SequencesKt.asSequence(ServiceLoader.load(MainDispatcherFactory.class, MainDispatcherFactory.class.getClassLoader()).iterator()));
            List $this$maxByOrNull$iv = factories;
            Iterator iterator$iv = $this$maxByOrNull$iv.iterator();
            if (iterator$iv.hasNext()) {
                Object maxElem$iv = iterator$iv.next();
                if (iterator$iv.hasNext()) {
                    MainDispatcherFactory it = (MainDispatcherFactory) maxElem$iv;
                    int maxValue$iv = it.getLoadPriority();
                    do {
                        Object e$iv = iterator$iv.next();
                        MainDispatcherFactory it2 = (MainDispatcherFactory) e$iv;
                        int v$iv = it2.getLoadPriority();
                        if (maxValue$iv < v$iv) {
                            maxElem$iv = e$iv;
                            maxValue$iv = v$iv;
                        }
                    } while (iterator$iv.hasNext());
                    obj = maxElem$iv;
                } else {
                    obj = maxElem$iv;
                }
            } else {
                obj = null;
            }
            MainDispatcherFactory mainDispatcherFactory = (MainDispatcherFactory) obj;
            mainCoroutineDispatcherCreateMissingDispatcher$default = mainDispatcherFactory == null ? MainDispatchersKt.createMissingDispatcher$default(null, null, 3, null) : MainDispatchersKt.tryCreateDispatcher(mainDispatcherFactory, factories);
        } catch (Throwable e) {
            mainCoroutineDispatcherCreateMissingDispatcher$default = MainDispatchersKt.createMissingDispatcher$default(e, null, 2, null);
        }
        return mainCoroutineDispatcherCreateMissingDispatcher$default;
    }
}
