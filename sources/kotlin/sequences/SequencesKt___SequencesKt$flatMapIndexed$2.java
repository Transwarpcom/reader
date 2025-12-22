package kotlin.sequences;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: Add missing generic type declarations: [R] */
/* compiled from: _Sequences.kt */
@Metadata(mv = {1, 5, 1}, k = 3, d1 = {"��\u0010\n��\n\u0002\u0010(\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\u0010��\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b��\u0010\u0003\"\u0004\b\u0001\u0010\u00022\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0005¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "R", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "p1", "Lkotlin/sequences/Sequence;", "invoke"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-stdlib-1.5.21.jar:kotlin/sequences/SequencesKt___SequencesKt$flatMapIndexed$2.class */
final /* synthetic */ class SequencesKt___SequencesKt$flatMapIndexed$2<R> extends FunctionReferenceImpl implements Function1<Sequence<? extends R>, Iterator<? extends R>> {
    public static final SequencesKt___SequencesKt$flatMapIndexed$2 INSTANCE = new SequencesKt___SequencesKt$flatMapIndexed$2();

    SequencesKt___SequencesKt$flatMapIndexed$2() {
        super(1, Sequence.class, "iterator", "iterator()Ljava/util/Iterator;", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    @NotNull
    public final Iterator<R> invoke(@NotNull Sequence<? extends R> p1) {
        Intrinsics.checkNotNullParameter(p1, "p1");
        return p1.iterator();
    }
}
