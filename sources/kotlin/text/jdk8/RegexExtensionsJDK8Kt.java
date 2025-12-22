package kotlin.text.jdk8;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.MatchGroup;
import kotlin.text.MatchGroupCollection;
import kotlin.text.MatchNamedGroupCollection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: RegexExtensions.kt */
@Metadata(mv = {1, 5, 1}, k = 2, d1 = {"��\u0012\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\u001a\u0017\u0010��\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\u0002¨\u0006\u0005"}, d2 = {BeanUtil.PREFIX_GETTER_GET, "Lkotlin/text/MatchGroup;", "Lkotlin/text/MatchGroupCollection;", "name", "", "kotlin-stdlib-jdk8"}, pn = "kotlin.text")
@JvmName(name = "RegexExtensionsJDK8Kt")
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-stdlib-jdk8-1.5.21.jar:kotlin/text/jdk8/RegexExtensionsJDK8Kt.class */
public final class RegexExtensionsJDK8Kt {
    @SinceKotlin(version = "1.2")
    @Nullable
    public static final MatchGroup get(@NotNull MatchGroupCollection get, @NotNull String name) {
        Intrinsics.checkNotNullParameter(get, "$this$get");
        Intrinsics.checkNotNullParameter(name, "name");
        MatchGroupCollection matchGroupCollection = get;
        if (!(matchGroupCollection instanceof MatchNamedGroupCollection)) {
            matchGroupCollection = null;
        }
        MatchNamedGroupCollection namedGroups = (MatchNamedGroupCollection) matchGroupCollection;
        if (namedGroups == null) {
            throw new UnsupportedOperationException("Retrieving groups by name is not supported on this platform.");
        }
        return namedGroups.get(name);
    }
}
