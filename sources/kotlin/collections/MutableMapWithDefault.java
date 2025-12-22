package kotlin.collections;

import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMutableMap;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.xml.BeanDefinitionParserDelegate;

/* compiled from: MapWithDefault.kt */
@Metadata(mv = {1, 5, 1}, k = 1, d1 = {"��\u0014\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0004\bb\u0018��*\u0004\b��\u0010\u0001*\u0004\b\u0001\u0010\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u00032\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0004R\u001e\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028��\u0012\u0004\u0012\u00028\u00010\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lkotlin/collections/MutableMapWithDefault;", OperatorName.STROKING_COLOR_CMYK, "V", "", "Lkotlin/collections/MapWithDefault;", BeanDefinitionParserDelegate.MAP_ELEMENT, "getMap", "()Ljava/util/Map;", "kotlin-stdlib"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-stdlib-1.5.21.jar:kotlin/collections/MutableMapWithDefault.class */
interface MutableMapWithDefault<K, V> extends Map<K, V>, MapWithDefault<K, V>, KMutableMap {
    @Override // kotlin.collections.MapWithDefault
    @NotNull
    Map<K, V> getMap();
}
