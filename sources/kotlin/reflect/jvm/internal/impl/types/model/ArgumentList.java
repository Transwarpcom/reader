package kotlin.reflect.jvm.internal.impl.types.model;

import java.util.ArrayList;

/* compiled from: TypeSystemContext.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/types/model/ArgumentList.class */
public final class ArgumentList extends ArrayList<TypeArgumentMarker> implements TypeArgumentListMarker {
    public ArgumentList(int initialSize) {
        super(initialSize);
    }

    public /* bridge */ boolean contains(TypeArgumentMarker element) {
        return super.contains((Object) element);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ boolean contains(Object element) {
        if (element instanceof TypeArgumentMarker) {
            return contains((TypeArgumentMarker) element);
        }
        return false;
    }

    public /* bridge */ int indexOf(TypeArgumentMarker element) {
        return super.indexOf((Object) element);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final /* bridge */ int indexOf(Object element) {
        if (element instanceof TypeArgumentMarker) {
            return indexOf((TypeArgumentMarker) element);
        }
        return -1;
    }

    public /* bridge */ int lastIndexOf(TypeArgumentMarker element) {
        return super.lastIndexOf((Object) element);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final /* bridge */ int lastIndexOf(Object element) {
        if (element instanceof TypeArgumentMarker) {
            return lastIndexOf((TypeArgumentMarker) element);
        }
        return -1;
    }

    public /* bridge */ boolean remove(TypeArgumentMarker element) {
        return super.remove((Object) element);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ boolean remove(Object element) {
        if (element instanceof TypeArgumentMarker) {
            return remove((TypeArgumentMarker) element);
        }
        return false;
    }

    public /* bridge */ int getSize() {
        return super.size();
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ int size() {
        return getSize();
    }
}
