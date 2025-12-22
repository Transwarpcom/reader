package kotlin;

import kotlin.internal.InlineOnly;
import org.springframework.jmx.export.naming.IdentityNamingStrategy;

/* compiled from: HashCode.kt */
@Metadata(mv = {1, 5, 1}, k = 2, d1 = {"��\f\n��\n\u0002\u0010\b\n\u0002\u0010��\n��\u001a\u000f\u0010��\u001a\u00020\u0001*\u0004\u0018\u00010\u0002H\u0087\b¨\u0006\u0003"}, d2 = {IdentityNamingStrategy.HASH_CODE_KEY, "", "", "kotlin-stdlib"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-stdlib-1.5.21.jar:kotlin/HashCodeKt.class */
public final class HashCodeKt {
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final int hashCode(Object $this$hashCode) {
        if ($this$hashCode != null) {
            return $this$hashCode.hashCode();
        }
        return 0;
    }
}
