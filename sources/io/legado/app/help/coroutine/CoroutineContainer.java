package io.legado.app.help.coroutine;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

/* compiled from: CoroutineContainer.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��(\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u000b\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b`\u0018��2\u00020\u0001J\u0014\u0010\u0002\u001a\u00020\u00032\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005H&J)\u0010\u0006\u001a\u00020\u00032\u001a\u0010\u0007\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u00050\b\"\u0006\u0012\u0002\b\u00030\u0005H&¢\u0006\u0002\u0010\tJ\b\u0010\n\u001a\u00020\u000bH&J\u0014\u0010\f\u001a\u00020\u00032\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005H&J\u0014\u0010\r\u001a\u00020\u00032\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005H&¨\u0006\u000e"}, d2 = {"Lio/legado/app/help/coroutine/CoroutineContainer;", "", BeanUtil.PREFIX_ADDER, "", "coroutine", "Lio/legado/app/help/coroutine/Coroutine;", "addAll", "coroutines", "", "([Lio/legado/app/help/coroutine/Coroutine;)Z", "clear", "", "delete", "remove", "reader-pro"})
/* loaded from: reader.jar:BOOT-INF/classes/io/legado/app/help/coroutine/CoroutineContainer.class */
public interface CoroutineContainer {
    boolean add(@NotNull Coroutine<?> coroutine);

    boolean addAll(@NotNull Coroutine<?>... coroutines);

    boolean remove(@NotNull Coroutine<?> coroutine);

    boolean delete(@NotNull Coroutine<?> coroutine);

    void clear();
}
