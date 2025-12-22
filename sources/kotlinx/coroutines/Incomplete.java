package kotlinx.coroutines;

import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.xml.BeanDefinitionParserDelegate;

/* compiled from: JobSupport.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��\u001a\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b`\u0018��2\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0004R\u0014\u0010\u0005\u001a\u0004\u0018\u00010\u0006X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lkotlinx/coroutines/Incomplete;", "", "isActive", "", "()Z", BeanDefinitionParserDelegate.LIST_ELEMENT, "Lkotlinx/coroutines/NodeList;", "getList", "()Lkotlinx/coroutines/NodeList;", "kotlinx-coroutines-core"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/Incomplete.class */
public interface Incomplete {
    boolean isActive();

    @Nullable
    NodeList getList();
}
