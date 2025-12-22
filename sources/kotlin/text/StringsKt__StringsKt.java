package kotlin.text;

import ch.qos.logback.core.pattern.parser.Parser;
import cn.hutool.core.text.StrPool;
import com.jayway.jsonpath.internal.function.text.Length;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Deprecated;
import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.OverloadResolutionByLambdaReturnType;
import kotlin.Pair;
import kotlin.ReplaceWith;
import kotlin.SinceKotlin;
import kotlin.TuplesKt;
import kotlin.WasExperimental;
import kotlin.collections.ArraysKt;
import kotlin.collections.CharIterator;
import kotlin.collections.CollectionsKt;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntProgression;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Strings.kt */
@Metadata(mv = {1, 5, 1}, k = 5, xi = 1, d1 = {"��|\n��\n\u0002\u0018\u0002\n\u0002\u0010\r\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\f\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n��\n\u0002\u0010\u001e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0019\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010 \n\u0002\b\b\n\u0002\u0010\u0011\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b!\u001a\u001c\u0010\t\u001a\u00020\n*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\r\u001a\u001c\u0010\u000e\u001a\u00020\n*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\r\u001a\u001f\u0010\u000f\u001a\u00020\r*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\f\u001a\u00020\rH\u0086\u0002\u001a\u001f\u0010\u000f\u001a\u00020\r*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\rH\u0086\u0002\u001a\u0015\u0010\u000f\u001a\u00020\r*\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0013H\u0087\n\u001a\u0018\u0010\u0014\u001a\u00020\r*\u0004\u0018\u00010\u00022\b\u0010\u000b\u001a\u0004\u0018\u00010\u0002H��\u001a\u0018\u0010\u0015\u001a\u00020\r*\u0004\u0018\u00010\u00022\b\u0010\u000b\u001a\u0004\u0018\u00010\u0002H��\u001a\u001c\u0010\u0016\u001a\u00020\r*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\f\u001a\u00020\r\u001a\u001c\u0010\u0016\u001a\u00020\r*\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\r\u001a:\u0010\u0018\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\n\u0018\u00010\u0019*\u00020\u00022\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\n0\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001aE\u0010\u0018\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\n\u0018\u00010\u0019*\u00020\u00022\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\n0\u001b2\u0006\u0010\u001c\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u001d\u001a\u00020\rH\u0002¢\u0006\u0002\b\u001e\u001a:\u0010\u001f\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\n\u0018\u00010\u0019*\u00020\u00022\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\n0\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001a\u0012\u0010 \u001a\u00020\r*\u00020\u00022\u0006\u0010!\u001a\u00020\u0006\u001a7\u0010\"\u001a\u0002H#\"\f\b��\u0010$*\u00020\u0002*\u0002H#\"\u0004\b\u0001\u0010#*\u0002H$2\f\u0010%\u001a\b\u0012\u0004\u0012\u0002H#0&H\u0087\bø\u0001��¢\u0006\u0002\u0010'\u001a7\u0010(\u001a\u0002H#\"\f\b��\u0010$*\u00020\u0002*\u0002H#\"\u0004\b\u0001\u0010#*\u0002H$2\f\u0010%\u001a\b\u0012\u0004\u0012\u0002H#0&H\u0087\bø\u0001��¢\u0006\u0002\u0010'\u001a&\u0010)\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u001c\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001a;\u0010)\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010\u001c\u001a\u00020\u00062\u0006\u0010*\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u001d\u001a\u00020\rH\u0002¢\u0006\u0002\b+\u001a&\u0010)\u001a\u00020\u0006*\u00020\u00022\u0006\u0010,\u001a\u00020\n2\b\b\u0002\u0010\u001c\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001a&\u0010-\u001a\u00020\u0006*\u00020\u00022\u0006\u0010.\u001a\u00020/2\b\b\u0002\u0010\u001c\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001a,\u0010-\u001a\u00020\u0006*\u00020\u00022\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\n0\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001a\r\u00100\u001a\u00020\r*\u00020\u0002H\u0087\b\u001a\r\u00101\u001a\u00020\r*\u00020\u0002H\u0087\b\u001a\r\u00102\u001a\u00020\r*\u00020\u0002H\u0087\b\u001a \u00103\u001a\u00020\r*\u0004\u0018\u00010\u0002H\u0087\b\u0082\u0002\u000e\n\f\b��\u0012\u0002\u0018\u0001\u001a\u0004\b\u0003\u0010��\u001a \u00104\u001a\u00020\r*\u0004\u0018\u00010\u0002H\u0087\b\u0082\u0002\u000e\n\f\b��\u0012\u0002\u0018\u0001\u001a\u0004\b\u0003\u0010��\u001a\r\u00105\u001a\u000206*\u00020\u0002H\u0086\u0002\u001a&\u00107\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u001c\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001a&\u00107\u001a\u00020\u0006*\u00020\u00022\u0006\u0010,\u001a\u00020\n2\b\b\u0002\u0010\u001c\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001a&\u00108\u001a\u00020\u0006*\u00020\u00022\u0006\u0010.\u001a\u00020/2\b\b\u0002\u0010\u001c\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001a,\u00108\u001a\u00020\u0006*\u00020\u00022\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\n0\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001a\u0010\u00109\u001a\b\u0012\u0004\u0012\u00020\n0:*\u00020\u0002\u001a\u0010\u0010;\u001a\b\u0012\u0004\u0012\u00020\n0<*\u00020\u0002\u001a\u0015\u0010=\u001a\u00020\r*\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0013H\u0087\f\u001a\u000f\u0010>\u001a\u00020\n*\u0004\u0018\u00010\nH\u0087\b\u001a\u001c\u0010?\u001a\u00020\u0002*\u00020\u00022\u0006\u0010@\u001a\u00020\u00062\b\b\u0002\u0010A\u001a\u00020\u0011\u001a\u001c\u0010?\u001a\u00020\n*\u00020\n2\u0006\u0010@\u001a\u00020\u00062\b\b\u0002\u0010A\u001a\u00020\u0011\u001a\u001c\u0010B\u001a\u00020\u0002*\u00020\u00022\u0006\u0010@\u001a\u00020\u00062\b\b\u0002\u0010A\u001a\u00020\u0011\u001a\u001c\u0010B\u001a\u00020\n*\u00020\n2\u0006\u0010@\u001a\u00020\u00062\b\b\u0002\u0010A\u001a\u00020\u0011\u001aG\u0010C\u001a\b\u0012\u0004\u0012\u00020\u00010:*\u00020\u00022\u000e\u0010D\u001a\n\u0012\u0006\b\u0001\u0012\u00020\n0E2\b\b\u0002\u0010\u001c\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010F\u001a\u00020\u0006H\u0002¢\u0006\u0004\bG\u0010H\u001a=\u0010C\u001a\b\u0012\u0004\u0012\u00020\u00010:*\u00020\u00022\u0006\u0010D\u001a\u00020/2\b\b\u0002\u0010\u001c\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010F\u001a\u00020\u0006H\u0002¢\u0006\u0002\bG\u001a4\u0010I\u001a\u00020\r*\u00020\u00022\u0006\u0010J\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010K\u001a\u00020\u00062\u0006\u0010@\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\rH��\u001a\u0012\u0010L\u001a\u00020\u0002*\u00020\u00022\u0006\u0010M\u001a\u00020\u0002\u001a\u0012\u0010L\u001a\u00020\n*\u00020\n2\u0006\u0010M\u001a\u00020\u0002\u001a\u001a\u0010N\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u001c\u001a\u00020\u00062\u0006\u0010*\u001a\u00020\u0006\u001a\u0012\u0010N\u001a\u00020\u0002*\u00020\u00022\u0006\u0010O\u001a\u00020\u0001\u001a\u001d\u0010N\u001a\u00020\n*\u00020\n2\u0006\u0010\u001c\u001a\u00020\u00062\u0006\u0010*\u001a\u00020\u0006H\u0087\b\u001a\u0015\u0010N\u001a\u00020\n*\u00020\n2\u0006\u0010O\u001a\u00020\u0001H\u0087\b\u001a\u0012\u0010P\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u0002\u001a\u0012\u0010P\u001a\u00020\n*\u00020\n2\u0006\u0010\u0017\u001a\u00020\u0002\u001a\u0012\u0010Q\u001a\u00020\u0002*\u00020\u00022\u0006\u0010R\u001a\u00020\u0002\u001a\u001a\u0010Q\u001a\u00020\u0002*\u00020\u00022\u0006\u0010M\u001a\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u0002\u001a\u0012\u0010Q\u001a\u00020\n*\u00020\n2\u0006\u0010R\u001a\u00020\u0002\u001a\u001a\u0010Q\u001a\u00020\n*\u00020\n2\u0006\u0010M\u001a\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u0002\u001a.\u0010S\u001a\u00020\n*\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00132\u0014\b\b\u0010T\u001a\u000e\u0012\u0004\u0012\u00020V\u0012\u0004\u0012\u00020\u00020UH\u0087\bø\u0001��\u001a\u001d\u0010S\u001a\u00020\n*\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010W\u001a\u00020\nH\u0087\b\u001a$\u0010X\u001a\u00020\n*\u00020\n2\u0006\u0010R\u001a\u00020\u00112\u0006\u0010W\u001a\u00020\n2\b\b\u0002\u0010Y\u001a\u00020\n\u001a$\u0010X\u001a\u00020\n*\u00020\n2\u0006\u0010R\u001a\u00020\n2\u0006\u0010W\u001a\u00020\n2\b\b\u0002\u0010Y\u001a\u00020\n\u001a$\u0010Z\u001a\u00020\n*\u00020\n2\u0006\u0010R\u001a\u00020\u00112\u0006\u0010W\u001a\u00020\n2\b\b\u0002\u0010Y\u001a\u00020\n\u001a$\u0010Z\u001a\u00020\n*\u00020\n2\u0006\u0010R\u001a\u00020\n2\u0006\u0010W\u001a\u00020\n2\b\b\u0002\u0010Y\u001a\u00020\n\u001a$\u0010[\u001a\u00020\n*\u00020\n2\u0006\u0010R\u001a\u00020\u00112\u0006\u0010W\u001a\u00020\n2\b\b\u0002\u0010Y\u001a\u00020\n\u001a$\u0010[\u001a\u00020\n*\u00020\n2\u0006\u0010R\u001a\u00020\n2\u0006\u0010W\u001a\u00020\n2\b\b\u0002\u0010Y\u001a\u00020\n\u001a$\u0010\\\u001a\u00020\n*\u00020\n2\u0006\u0010R\u001a\u00020\u00112\u0006\u0010W\u001a\u00020\n2\b\b\u0002\u0010Y\u001a\u00020\n\u001a$\u0010\\\u001a\u00020\n*\u00020\n2\u0006\u0010R\u001a\u00020\n2\u0006\u0010W\u001a\u00020\n2\b\b\u0002\u0010Y\u001a\u00020\n\u001a\u001d\u0010]\u001a\u00020\n*\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010W\u001a\u00020\nH\u0087\b\u001a)\u0010^\u001a\u00020\n*\u00020\n2\u0012\u0010T\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00110UH\u0087\bø\u0001��¢\u0006\u0002\b_\u001a)\u0010^\u001a\u00020\n*\u00020\n2\u0012\u0010T\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00020UH\u0087\bø\u0001��¢\u0006\u0002\b`\u001a\"\u0010a\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u001c\u001a\u00020\u00062\u0006\u0010*\u001a\u00020\u00062\u0006\u0010W\u001a\u00020\u0002\u001a\u001a\u0010a\u001a\u00020\u0002*\u00020\u00022\u0006\u0010O\u001a\u00020\u00012\u0006\u0010W\u001a\u00020\u0002\u001a%\u0010a\u001a\u00020\n*\u00020\n2\u0006\u0010\u001c\u001a\u00020\u00062\u0006\u0010*\u001a\u00020\u00062\u0006\u0010W\u001a\u00020\u0002H\u0087\b\u001a\u001d\u0010a\u001a\u00020\n*\u00020\n2\u0006\u0010O\u001a\u00020\u00012\u0006\u0010W\u001a\u00020\u0002H\u0087\b\u001a=\u0010b\u001a\b\u0012\u0004\u0012\u00020\n0<*\u00020\u00022\u0012\u0010D\u001a\n\u0012\u0006\b\u0001\u0012\u00020\n0E\"\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010F\u001a\u00020\u0006¢\u0006\u0002\u0010c\u001a0\u0010b\u001a\b\u0012\u0004\u0012\u00020\n0<*\u00020\u00022\n\u0010D\u001a\u00020/\"\u00020\u00112\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010F\u001a\u00020\u0006\u001a/\u0010b\u001a\b\u0012\u0004\u0012\u00020\n0<*\u00020\u00022\u0006\u0010R\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010F\u001a\u00020\u0006H\u0002¢\u0006\u0002\bd\u001a%\u0010b\u001a\b\u0012\u0004\u0012\u00020\n0<*\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010F\u001a\u00020\u0006H\u0087\b\u001a=\u0010e\u001a\b\u0012\u0004\u0012\u00020\n0:*\u00020\u00022\u0012\u0010D\u001a\n\u0012\u0006\b\u0001\u0012\u00020\n0E\"\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010F\u001a\u00020\u0006¢\u0006\u0002\u0010f\u001a0\u0010e\u001a\b\u0012\u0004\u0012\u00020\n0:*\u00020\u00022\n\u0010D\u001a\u00020/\"\u00020\u00112\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010F\u001a\u00020\u0006\u001a\u001c\u0010g\u001a\u00020\r*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\f\u001a\u00020\r\u001a\u001c\u0010g\u001a\u00020\r*\u00020\u00022\u0006\u0010M\u001a\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\r\u001a$\u0010g\u001a\u00020\r*\u00020\u00022\u0006\u0010M\u001a\u00020\u00022\u0006\u0010\u001c\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001a\u0012\u0010h\u001a\u00020\u0002*\u00020\u00022\u0006\u0010O\u001a\u00020\u0001\u001a\u001d\u0010h\u001a\u00020\u0002*\u00020\n2\u0006\u0010i\u001a\u00020\u00062\u0006\u0010j\u001a\u00020\u0006H\u0087\b\u001a\u001f\u0010k\u001a\u00020\n*\u00020\u00022\u0006\u0010\u001c\u001a\u00020\u00062\b\b\u0002\u0010*\u001a\u00020\u0006H\u0087\b\u001a\u0012\u0010k\u001a\u00020\n*\u00020\u00022\u0006\u0010O\u001a\u00020\u0001\u001a\u0012\u0010k\u001a\u00020\n*\u00020\n2\u0006\u0010O\u001a\u00020\u0001\u001a\u001c\u0010l\u001a\u00020\n*\u00020\n2\u0006\u0010R\u001a\u00020\u00112\b\b\u0002\u0010Y\u001a\u00020\n\u001a\u001c\u0010l\u001a\u00020\n*\u00020\n2\u0006\u0010R\u001a\u00020\n2\b\b\u0002\u0010Y\u001a\u00020\n\u001a\u001c\u0010m\u001a\u00020\n*\u00020\n2\u0006\u0010R\u001a\u00020\u00112\b\b\u0002\u0010Y\u001a\u00020\n\u001a\u001c\u0010m\u001a\u00020\n*\u00020\n2\u0006\u0010R\u001a\u00020\n2\b\b\u0002\u0010Y\u001a\u00020\n\u001a\u001c\u0010n\u001a\u00020\n*\u00020\n2\u0006\u0010R\u001a\u00020\u00112\b\b\u0002\u0010Y\u001a\u00020\n\u001a\u001c\u0010n\u001a\u00020\n*\u00020\n2\u0006\u0010R\u001a\u00020\n2\b\b\u0002\u0010Y\u001a\u00020\n\u001a\u001c\u0010o\u001a\u00020\n*\u00020\n2\u0006\u0010R\u001a\u00020\u00112\b\b\u0002\u0010Y\u001a\u00020\n\u001a\u001c\u0010o\u001a\u00020\n*\u00020\n2\u0006\u0010R\u001a\u00020\n2\b\b\u0002\u0010Y\u001a\u00020\n\u001a\f\u0010p\u001a\u00020\r*\u00020\nH\u0007\u001a\u0013\u0010q\u001a\u0004\u0018\u00010\r*\u00020\nH\u0007¢\u0006\u0002\u0010r\u001a\n\u0010s\u001a\u00020\u0002*\u00020\u0002\u001a$\u0010s\u001a\u00020\u0002*\u00020\u00022\u0012\u0010t\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\r0UH\u0086\bø\u0001��\u001a\u0016\u0010s\u001a\u00020\u0002*\u00020\u00022\n\u0010.\u001a\u00020/\"\u00020\u0011\u001a\r\u0010s\u001a\u00020\n*\u00020\nH\u0087\b\u001a$\u0010s\u001a\u00020\n*\u00020\n2\u0012\u0010t\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\r0UH\u0086\bø\u0001��\u001a\u0016\u0010s\u001a\u00020\n*\u00020\n2\n\u0010.\u001a\u00020/\"\u00020\u0011\u001a\n\u0010u\u001a\u00020\u0002*\u00020\u0002\u001a$\u0010u\u001a\u00020\u0002*\u00020\u00022\u0012\u0010t\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\r0UH\u0086\bø\u0001��\u001a\u0016\u0010u\u001a\u00020\u0002*\u00020\u00022\n\u0010.\u001a\u00020/\"\u00020\u0011\u001a\r\u0010u\u001a\u00020\n*\u00020\nH\u0087\b\u001a$\u0010u\u001a\u00020\n*\u00020\n2\u0012\u0010t\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\r0UH\u0086\bø\u0001��\u001a\u0016\u0010u\u001a\u00020\n*\u00020\n2\n\u0010.\u001a\u00020/\"\u00020\u0011\u001a\n\u0010v\u001a\u00020\u0002*\u00020\u0002\u001a$\u0010v\u001a\u00020\u0002*\u00020\u00022\u0012\u0010t\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\r0UH\u0086\bø\u0001��\u001a\u0016\u0010v\u001a\u00020\u0002*\u00020\u00022\n\u0010.\u001a\u00020/\"\u00020\u0011\u001a\r\u0010v\u001a\u00020\n*\u00020\nH\u0087\b\u001a$\u0010v\u001a\u00020\n*\u00020\n2\u0012\u0010t\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\r0UH\u0086\bø\u0001��\u001a\u0016\u0010v\u001a\u00020\n*\u00020\n2\n\u0010.\u001a\u00020/\"\u00020\u0011\"\u0015\u0010��\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0015\u0010\u0005\u001a\u00020\u0006*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006w"}, d2 = {"indices", "Lkotlin/ranges/IntRange;", "", "getIndices", "(Ljava/lang/CharSequence;)Lkotlin/ranges/IntRange;", "lastIndex", "", "getLastIndex", "(Ljava/lang/CharSequence;)I", "commonPrefixWith", "", "other", "ignoreCase", "", "commonSuffixWith", "contains", "char", "", "regex", "Lkotlin/text/Regex;", "contentEqualsIgnoreCaseImpl", "contentEqualsImpl", "endsWith", "suffix", "findAnyOf", "Lkotlin/Pair;", "strings", "", "startIndex", "last", "findAnyOf$StringsKt__StringsKt", "findLastAnyOf", "hasSurrogatePairAt", "index", "ifBlank", "R", "C", "defaultValue", "Lkotlin/Function0;", "(Ljava/lang/CharSequence;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "ifEmpty", "indexOf", "endIndex", "indexOf$StringsKt__StringsKt", "string", "indexOfAny", "chars", "", "isEmpty", "isNotBlank", "isNotEmpty", "isNullOrBlank", "isNullOrEmpty", "iterator", "Lkotlin/collections/CharIterator;", "lastIndexOf", "lastIndexOfAny", "lineSequence", "Lkotlin/sequences/Sequence;", "lines", "", "matches", "orEmpty", "padEnd", Length.TOKEN_NAME, "padChar", "padStart", "rangesDelimitedBy", "delimiters", "", "limit", "rangesDelimitedBy$StringsKt__StringsKt", "(Ljava/lang/CharSequence;[Ljava/lang/String;IZI)Lkotlin/sequences/Sequence;", "regionMatchesImpl", "thisOffset", "otherOffset", "removePrefix", "prefix", "removeRange", "range", "removeSuffix", "removeSurrounding", "delimiter", Parser.REPLACE_CONVERTER_WORD, "transform", "Lkotlin/Function1;", "Lkotlin/text/MatchResult;", "replacement", "replaceAfter", "missingDelimiterValue", "replaceAfterLast", "replaceBefore", "replaceBeforeLast", "replaceFirst", "replaceFirstChar", "replaceFirstCharWithChar", "replaceFirstCharWithCharSequence", "replaceRange", "split", "(Ljava/lang/CharSequence;[Ljava/lang/String;ZI)Ljava/util/List;", "split$StringsKt__StringsKt", "splitToSequence", "(Ljava/lang/CharSequence;[Ljava/lang/String;ZI)Lkotlin/sequences/Sequence;", "startsWith", "subSequence", "start", "end", "substring", "substringAfter", "substringAfterLast", "substringBefore", "substringBeforeLast", "toBooleanStrict", "toBooleanStrictOrNull", "(Ljava/lang/String;)Ljava/lang/Boolean;", "trim", "predicate", "trimEnd", "trimStart", "kotlin-stdlib"}, xs = "kotlin/text/StringsKt")
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-stdlib-1.5.21.jar:kotlin/text/StringsKt__StringsKt.class */
public class StringsKt__StringsKt extends StringsKt__StringsJVMKt {
    @NotNull
    public static final CharSequence trim(@NotNull CharSequence trim, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(trim, "$this$trim");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int startIndex = 0;
        int endIndex = trim.length() - 1;
        boolean startFound = false;
        while (startIndex <= endIndex) {
            int index = !startFound ? startIndex : endIndex;
            boolean match = predicate.invoke(Character.valueOf(trim.charAt(index))).booleanValue();
            if (!startFound) {
                if (!match) {
                    startFound = true;
                } else {
                    startIndex++;
                }
            } else {
                if (!match) {
                    break;
                }
                endIndex--;
            }
        }
        return trim.subSequence(startIndex, endIndex + 1);
    }

    @NotNull
    public static final String trim(@NotNull String trim, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(trim, "$this$trim");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        String $this$trim$iv = trim;
        int startIndex$iv = 0;
        int endIndex$iv = $this$trim$iv.length() - 1;
        boolean startFound$iv = false;
        while (startIndex$iv <= endIndex$iv) {
            int index$iv = !startFound$iv ? startIndex$iv : endIndex$iv;
            boolean match$iv = predicate.invoke(Character.valueOf($this$trim$iv.charAt(index$iv))).booleanValue();
            if (!startFound$iv) {
                if (!match$iv) {
                    startFound$iv = true;
                } else {
                    startIndex$iv++;
                }
            } else {
                if (!match$iv) {
                    break;
                }
                endIndex$iv--;
            }
        }
        return $this$trim$iv.subSequence(startIndex$iv, endIndex$iv + 1).toString();
    }

    @NotNull
    public static final CharSequence trimStart(@NotNull CharSequence trimStart, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(trimStart, "$this$trimStart");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        int length = trimStart.length();
        for (int index = 0; index < length; index++) {
            if (!predicate.invoke(Character.valueOf(trimStart.charAt(index))).booleanValue()) {
                return trimStart.subSequence(index, trimStart.length());
            }
        }
        return "";
    }

    @NotNull
    public static final String trimStart(@NotNull String trimStart, @NotNull Function1<? super Character, Boolean> predicate) {
        String strSubSequence;
        Intrinsics.checkNotNullParameter(trimStart, "$this$trimStart");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        String $this$trimStart$iv = trimStart;
        int index$iv = 0;
        int length = $this$trimStart$iv.length();
        while (true) {
            if (index$iv < length) {
                if (predicate.invoke(Character.valueOf($this$trimStart$iv.charAt(index$iv))).booleanValue()) {
                    index$iv++;
                } else {
                    strSubSequence = $this$trimStart$iv.subSequence(index$iv, $this$trimStart$iv.length());
                    break;
                }
            } else {
                break;
            }
        }
        return strSubSequence.toString();
    }

    @NotNull
    public static final CharSequence trimEnd(@NotNull CharSequence trimEnd, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkNotNullParameter(trimEnd, "$this$trimEnd");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        for (int index = trimEnd.length() - 1; index >= 0; index--) {
            if (!predicate.invoke(Character.valueOf(trimEnd.charAt(index))).booleanValue()) {
                return trimEnd.subSequence(0, index + 1);
            }
        }
        return "";
    }

    @NotNull
    public static final String trimEnd(@NotNull String trimEnd, @NotNull Function1<? super Character, Boolean> predicate) {
        String strSubSequence;
        Intrinsics.checkNotNullParameter(trimEnd, "$this$trimEnd");
        Intrinsics.checkNotNullParameter(predicate, "predicate");
        String $this$trimEnd$iv = trimEnd;
        int index$iv = $this$trimEnd$iv.length() - 1;
        while (true) {
            if (index$iv >= 0) {
                if (predicate.invoke(Character.valueOf($this$trimEnd$iv.charAt(index$iv))).booleanValue()) {
                    index$iv--;
                } else {
                    strSubSequence = $this$trimEnd$iv.subSequence(0, index$iv + 1);
                    break;
                }
            } else {
                break;
            }
        }
        return strSubSequence.toString();
    }

    @NotNull
    public static final CharSequence trim(@NotNull CharSequence trim, @NotNull char... chars) {
        Intrinsics.checkNotNullParameter(trim, "$this$trim");
        Intrinsics.checkNotNullParameter(chars, "chars");
        int startIndex$iv = 0;
        int endIndex$iv = trim.length() - 1;
        boolean startFound$iv = false;
        while (startIndex$iv <= endIndex$iv) {
            int index$iv = !startFound$iv ? startIndex$iv : endIndex$iv;
            char it = trim.charAt(index$iv);
            boolean match$iv = ArraysKt.contains(chars, it);
            if (!startFound$iv) {
                if (!match$iv) {
                    startFound$iv = true;
                } else {
                    startIndex$iv++;
                }
            } else {
                if (!match$iv) {
                    break;
                }
                endIndex$iv--;
            }
        }
        return trim.subSequence(startIndex$iv, endIndex$iv + 1);
    }

    @NotNull
    public static final String trim(@NotNull String trim, @NotNull char... chars) {
        Intrinsics.checkNotNullParameter(trim, "$this$trim");
        Intrinsics.checkNotNullParameter(chars, "chars");
        String $this$trim$iv$iv = trim;
        int startIndex$iv$iv = 0;
        int endIndex$iv$iv = $this$trim$iv$iv.length() - 1;
        boolean startFound$iv$iv = false;
        while (startIndex$iv$iv <= endIndex$iv$iv) {
            int index$iv$iv = !startFound$iv$iv ? startIndex$iv$iv : endIndex$iv$iv;
            char it = $this$trim$iv$iv.charAt(index$iv$iv);
            boolean match$iv$iv = ArraysKt.contains(chars, it);
            if (!startFound$iv$iv) {
                if (!match$iv$iv) {
                    startFound$iv$iv = true;
                } else {
                    startIndex$iv$iv++;
                }
            } else {
                if (!match$iv$iv) {
                    break;
                }
                endIndex$iv$iv--;
            }
        }
        return $this$trim$iv$iv.subSequence(startIndex$iv$iv, endIndex$iv$iv + 1).toString();
    }

    @NotNull
    public static final CharSequence trimStart(@NotNull CharSequence trimStart, @NotNull char... chars) {
        Intrinsics.checkNotNullParameter(trimStart, "$this$trimStart");
        Intrinsics.checkNotNullParameter(chars, "chars");
        int length = trimStart.length();
        for (int index$iv = 0; index$iv < length; index$iv++) {
            char it = trimStart.charAt(index$iv);
            if (!ArraysKt.contains(chars, it)) {
                return trimStart.subSequence(index$iv, trimStart.length());
            }
        }
        return "";
    }

    @NotNull
    public static final String trimStart(@NotNull String trimStart, @NotNull char... chars) {
        String strSubSequence;
        Intrinsics.checkNotNullParameter(trimStart, "$this$trimStart");
        Intrinsics.checkNotNullParameter(chars, "chars");
        String $this$trimStart$iv$iv = trimStart;
        int index$iv$iv = 0;
        int length = $this$trimStart$iv$iv.length();
        while (true) {
            if (index$iv$iv < length) {
                char it = $this$trimStart$iv$iv.charAt(index$iv$iv);
                if (ArraysKt.contains(chars, it)) {
                    index$iv$iv++;
                } else {
                    strSubSequence = $this$trimStart$iv$iv.subSequence(index$iv$iv, $this$trimStart$iv$iv.length());
                    break;
                }
            } else {
                break;
            }
        }
        return strSubSequence.toString();
    }

    @NotNull
    public static final CharSequence trimEnd(@NotNull CharSequence trimEnd, @NotNull char... chars) {
        Intrinsics.checkNotNullParameter(trimEnd, "$this$trimEnd");
        Intrinsics.checkNotNullParameter(chars, "chars");
        for (int index$iv = trimEnd.length() - 1; index$iv >= 0; index$iv--) {
            char it = trimEnd.charAt(index$iv);
            if (!ArraysKt.contains(chars, it)) {
                return trimEnd.subSequence(0, index$iv + 1);
            }
        }
        return "";
    }

    @NotNull
    public static final String trimEnd(@NotNull String trimEnd, @NotNull char... chars) {
        String strSubSequence;
        Intrinsics.checkNotNullParameter(trimEnd, "$this$trimEnd");
        Intrinsics.checkNotNullParameter(chars, "chars");
        String $this$trimEnd$iv$iv = trimEnd;
        int index$iv$iv = $this$trimEnd$iv$iv.length() - 1;
        while (true) {
            if (index$iv$iv >= 0) {
                char it = $this$trimEnd$iv$iv.charAt(index$iv$iv);
                if (ArraysKt.contains(chars, it)) {
                    index$iv$iv--;
                } else {
                    strSubSequence = $this$trimEnd$iv$iv.subSequence(0, index$iv$iv + 1);
                    break;
                }
            } else {
                break;
            }
        }
        return strSubSequence.toString();
    }

    @NotNull
    public static final CharSequence trim(@NotNull CharSequence trim) {
        Intrinsics.checkNotNullParameter(trim, "$this$trim");
        int startIndex$iv = 0;
        int endIndex$iv = trim.length() - 1;
        boolean startFound$iv = false;
        while (startIndex$iv <= endIndex$iv) {
            int index$iv = !startFound$iv ? startIndex$iv : endIndex$iv;
            char p1 = trim.charAt(index$iv);
            boolean match$iv = CharsKt.isWhitespace(p1);
            if (!startFound$iv) {
                if (!match$iv) {
                    startFound$iv = true;
                } else {
                    startIndex$iv++;
                }
            } else {
                if (!match$iv) {
                    break;
                }
                endIndex$iv--;
            }
        }
        return trim.subSequence(startIndex$iv, endIndex$iv + 1);
    }

    @InlineOnly
    private static final String trim(String $this$trim) {
        if ($this$trim == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.CharSequence");
        }
        return StringsKt.trim((CharSequence) $this$trim).toString();
    }

    @NotNull
    public static final CharSequence trimStart(@NotNull CharSequence trimStart) {
        Intrinsics.checkNotNullParameter(trimStart, "$this$trimStart");
        int length = trimStart.length();
        for (int index$iv = 0; index$iv < length; index$iv++) {
            char p1 = trimStart.charAt(index$iv);
            if (!CharsKt.isWhitespace(p1)) {
                return trimStart.subSequence(index$iv, trimStart.length());
            }
        }
        return "";
    }

    @InlineOnly
    private static final String trimStart(String $this$trimStart) {
        if ($this$trimStart == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.CharSequence");
        }
        return StringsKt.trimStart((CharSequence) $this$trimStart).toString();
    }

    @NotNull
    public static final CharSequence trimEnd(@NotNull CharSequence trimEnd) {
        Intrinsics.checkNotNullParameter(trimEnd, "$this$trimEnd");
        for (int index$iv = trimEnd.length() - 1; index$iv >= 0; index$iv--) {
            char p1 = trimEnd.charAt(index$iv);
            if (!CharsKt.isWhitespace(p1)) {
                return trimEnd.subSequence(0, index$iv + 1);
            }
        }
        return "";
    }

    @InlineOnly
    private static final String trimEnd(String $this$trimEnd) {
        if ($this$trimEnd == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.CharSequence");
        }
        return StringsKt.trimEnd((CharSequence) $this$trimEnd).toString();
    }

    public static /* synthetic */ CharSequence padStart$default(CharSequence charSequence, int i, char c, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            c = ' ';
        }
        return StringsKt.padStart(charSequence, i, c);
    }

    @NotNull
    public static final CharSequence padStart(@NotNull CharSequence padStart, int length, char padChar) {
        Intrinsics.checkNotNullParameter(padStart, "$this$padStart");
        if (length < 0) {
            throw new IllegalArgumentException("Desired length " + length + " is less than zero.");
        }
        if (length <= padStart.length()) {
            return padStart.subSequence(0, padStart.length());
        }
        StringBuilder sb = new StringBuilder(length);
        int i = 1;
        int length2 = length - padStart.length();
        if (1 <= length2) {
            while (true) {
                sb.append(padChar);
                if (i == length2) {
                    break;
                }
                i++;
            }
        }
        sb.append(padStart);
        return sb;
    }

    public static /* synthetic */ String padStart$default(String str, int i, char c, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            c = ' ';
        }
        return StringsKt.padStart(str, i, c);
    }

    @NotNull
    public static final String padStart(@NotNull String padStart, int length, char padChar) {
        Intrinsics.checkNotNullParameter(padStart, "$this$padStart");
        return StringsKt.padStart((CharSequence) padStart, length, padChar).toString();
    }

    public static /* synthetic */ CharSequence padEnd$default(CharSequence charSequence, int i, char c, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            c = ' ';
        }
        return StringsKt.padEnd(charSequence, i, c);
    }

    @NotNull
    public static final CharSequence padEnd(@NotNull CharSequence padEnd, int length, char padChar) {
        Intrinsics.checkNotNullParameter(padEnd, "$this$padEnd");
        if (length < 0) {
            throw new IllegalArgumentException("Desired length " + length + " is less than zero.");
        }
        if (length <= padEnd.length()) {
            return padEnd.subSequence(0, padEnd.length());
        }
        StringBuilder sb = new StringBuilder(length);
        sb.append(padEnd);
        int i = 1;
        int length2 = length - padEnd.length();
        if (1 <= length2) {
            while (true) {
                sb.append(padChar);
                if (i == length2) {
                    break;
                }
                i++;
            }
        }
        return sb;
    }

    public static /* synthetic */ String padEnd$default(String str, int i, char c, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            c = ' ';
        }
        return StringsKt.padEnd(str, i, c);
    }

    @NotNull
    public static final String padEnd(@NotNull String padEnd, int length, char padChar) {
        Intrinsics.checkNotNullParameter(padEnd, "$this$padEnd");
        return StringsKt.padEnd((CharSequence) padEnd, length, padChar).toString();
    }

    @InlineOnly
    private static final boolean isNullOrEmpty(CharSequence $this$isNullOrEmpty) {
        return $this$isNullOrEmpty == null || $this$isNullOrEmpty.length() == 0;
    }

    @InlineOnly
    private static final boolean isEmpty(CharSequence $this$isEmpty) {
        return $this$isEmpty.length() == 0;
    }

    @InlineOnly
    private static final boolean isNotEmpty(CharSequence $this$isNotEmpty) {
        return $this$isNotEmpty.length() > 0;
    }

    @InlineOnly
    private static final boolean isNotBlank(CharSequence $this$isNotBlank) {
        return !StringsKt.isBlank($this$isNotBlank);
    }

    @InlineOnly
    private static final boolean isNullOrBlank(CharSequence $this$isNullOrBlank) {
        return $this$isNullOrBlank == null || StringsKt.isBlank($this$isNullOrBlank);
    }

    @NotNull
    public static final CharIterator iterator(@NotNull final CharSequence iterator) {
        Intrinsics.checkNotNullParameter(iterator, "$this$iterator");
        return new CharIterator() { // from class: kotlin.text.StringsKt__StringsKt.iterator.1
            private int index;

            @Override // kotlin.collections.CharIterator
            public char nextChar() {
                CharSequence charSequence = iterator;
                int i = this.index;
                this.index = i + 1;
                return charSequence.charAt(i);
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.index < iterator.length();
            }
        };
    }

    @InlineOnly
    private static final String orEmpty(String $this$orEmpty) {
        return $this$orEmpty != null ? $this$orEmpty : "";
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final <C extends CharSequence & R, R> R ifEmpty(C c, Function0<? extends R> function0) {
        return c.length() == 0 ? function0.invoke() : c;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final <C extends CharSequence & R, R> R ifBlank(C c, Function0<? extends R> function0) {
        return StringsKt.isBlank(c) ? function0.invoke() : c;
    }

    @NotNull
    public static final IntRange getIndices(@NotNull CharSequence indices) {
        Intrinsics.checkNotNullParameter(indices, "$this$indices");
        return new IntRange(0, indices.length() - 1);
    }

    public static final int getLastIndex(@NotNull CharSequence lastIndex) {
        Intrinsics.checkNotNullParameter(lastIndex, "$this$lastIndex");
        return lastIndex.length() - 1;
    }

    public static final boolean hasSurrogatePairAt(@NotNull CharSequence hasSurrogatePairAt, int index) {
        Intrinsics.checkNotNullParameter(hasSurrogatePairAt, "$this$hasSurrogatePairAt");
        return 0 <= index && hasSurrogatePairAt.length() - 2 >= index && Character.isHighSurrogate(hasSurrogatePairAt.charAt(index)) && Character.isLowSurrogate(hasSurrogatePairAt.charAt(index + 1));
    }

    @NotNull
    public static final String substring(@NotNull String substring, @NotNull IntRange range) {
        Intrinsics.checkNotNullParameter(substring, "$this$substring");
        Intrinsics.checkNotNullParameter(range, "range");
        String strSubstring = substring.substring(range.getStart().intValue(), range.getEndInclusive().intValue() + 1);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return strSubstring;
    }

    @NotNull
    public static final CharSequence subSequence(@NotNull CharSequence subSequence, @NotNull IntRange range) {
        Intrinsics.checkNotNullParameter(subSequence, "$this$subSequence");
        Intrinsics.checkNotNullParameter(range, "range");
        return subSequence.subSequence(range.getStart().intValue(), range.getEndInclusive().intValue() + 1);
    }

    @Deprecated(message = "Use parameters named startIndex and endIndex.", replaceWith = @ReplaceWith(imports = {}, expression = "subSequence(startIndex = start, endIndex = end)"))
    @InlineOnly
    private static final CharSequence subSequence(String $this$subSequence, int start, int end) {
        return $this$subSequence.subSequence(start, end);
    }

    @InlineOnly
    private static final String substring(CharSequence $this$substring, int startIndex, int endIndex) {
        return $this$substring.subSequence(startIndex, endIndex).toString();
    }

    static /* synthetic */ String substring$default(CharSequence $this$substring, int startIndex, int endIndex, int i, Object obj) {
        if ((i & 2) != 0) {
            endIndex = $this$substring.length();
        }
        return $this$substring.subSequence(startIndex, endIndex).toString();
    }

    @NotNull
    public static final String substring(@NotNull CharSequence substring, @NotNull IntRange range) {
        Intrinsics.checkNotNullParameter(substring, "$this$substring");
        Intrinsics.checkNotNullParameter(range, "range");
        return substring.subSequence(range.getStart().intValue(), range.getEndInclusive().intValue() + 1).toString();
    }

    public static /* synthetic */ String substringBefore$default(String str, char c, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = str;
        }
        return StringsKt.substringBefore(str, c, str2);
    }

    @NotNull
    public static final String substringBefore(@NotNull String substringBefore, char delimiter, @NotNull String missingDelimiterValue) {
        Intrinsics.checkNotNullParameter(substringBefore, "$this$substringBefore");
        Intrinsics.checkNotNullParameter(missingDelimiterValue, "missingDelimiterValue");
        int index = StringsKt.indexOf$default((CharSequence) substringBefore, delimiter, 0, false, 6, (Object) null);
        if (index == -1) {
            return missingDelimiterValue;
        }
        String strSubstring = substringBefore.substring(0, index);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return strSubstring;
    }

    public static /* synthetic */ String substringBefore$default(String str, String str2, String str3, int i, Object obj) {
        if ((i & 2) != 0) {
            str3 = str;
        }
        return StringsKt.substringBefore(str, str2, str3);
    }

    @NotNull
    public static final String substringBefore(@NotNull String substringBefore, @NotNull String delimiter, @NotNull String missingDelimiterValue) {
        Intrinsics.checkNotNullParameter(substringBefore, "$this$substringBefore");
        Intrinsics.checkNotNullParameter(delimiter, "delimiter");
        Intrinsics.checkNotNullParameter(missingDelimiterValue, "missingDelimiterValue");
        int index = StringsKt.indexOf$default((CharSequence) substringBefore, delimiter, 0, false, 6, (Object) null);
        if (index == -1) {
            return missingDelimiterValue;
        }
        String strSubstring = substringBefore.substring(0, index);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return strSubstring;
    }

    public static /* synthetic */ String substringAfter$default(String str, char c, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = str;
        }
        return StringsKt.substringAfter(str, c, str2);
    }

    @NotNull
    public static final String substringAfter(@NotNull String substringAfter, char delimiter, @NotNull String missingDelimiterValue) {
        Intrinsics.checkNotNullParameter(substringAfter, "$this$substringAfter");
        Intrinsics.checkNotNullParameter(missingDelimiterValue, "missingDelimiterValue");
        int index = StringsKt.indexOf$default((CharSequence) substringAfter, delimiter, 0, false, 6, (Object) null);
        if (index == -1) {
            return missingDelimiterValue;
        }
        String strSubstring = substringAfter.substring(index + 1, substringAfter.length());
        Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return strSubstring;
    }

    public static /* synthetic */ String substringAfter$default(String str, String str2, String str3, int i, Object obj) {
        if ((i & 2) != 0) {
            str3 = str;
        }
        return StringsKt.substringAfter(str, str2, str3);
    }

    @NotNull
    public static final String substringAfter(@NotNull String substringAfter, @NotNull String delimiter, @NotNull String missingDelimiterValue) {
        Intrinsics.checkNotNullParameter(substringAfter, "$this$substringAfter");
        Intrinsics.checkNotNullParameter(delimiter, "delimiter");
        Intrinsics.checkNotNullParameter(missingDelimiterValue, "missingDelimiterValue");
        int index = StringsKt.indexOf$default((CharSequence) substringAfter, delimiter, 0, false, 6, (Object) null);
        if (index == -1) {
            return missingDelimiterValue;
        }
        String strSubstring = substringAfter.substring(index + delimiter.length(), substringAfter.length());
        Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return strSubstring;
    }

    public static /* synthetic */ String substringBeforeLast$default(String str, char c, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = str;
        }
        return StringsKt.substringBeforeLast(str, c, str2);
    }

    @NotNull
    public static final String substringBeforeLast(@NotNull String substringBeforeLast, char delimiter, @NotNull String missingDelimiterValue) {
        Intrinsics.checkNotNullParameter(substringBeforeLast, "$this$substringBeforeLast");
        Intrinsics.checkNotNullParameter(missingDelimiterValue, "missingDelimiterValue");
        int index = StringsKt.lastIndexOf$default((CharSequence) substringBeforeLast, delimiter, 0, false, 6, (Object) null);
        if (index == -1) {
            return missingDelimiterValue;
        }
        String strSubstring = substringBeforeLast.substring(0, index);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return strSubstring;
    }

    public static /* synthetic */ String substringBeforeLast$default(String str, String str2, String str3, int i, Object obj) {
        if ((i & 2) != 0) {
            str3 = str;
        }
        return StringsKt.substringBeforeLast(str, str2, str3);
    }

    @NotNull
    public static final String substringBeforeLast(@NotNull String substringBeforeLast, @NotNull String delimiter, @NotNull String missingDelimiterValue) {
        Intrinsics.checkNotNullParameter(substringBeforeLast, "$this$substringBeforeLast");
        Intrinsics.checkNotNullParameter(delimiter, "delimiter");
        Intrinsics.checkNotNullParameter(missingDelimiterValue, "missingDelimiterValue");
        int index = StringsKt.lastIndexOf$default((CharSequence) substringBeforeLast, delimiter, 0, false, 6, (Object) null);
        if (index == -1) {
            return missingDelimiterValue;
        }
        String strSubstring = substringBeforeLast.substring(0, index);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return strSubstring;
    }

    public static /* synthetic */ String substringAfterLast$default(String str, char c, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = str;
        }
        return StringsKt.substringAfterLast(str, c, str2);
    }

    @NotNull
    public static final String substringAfterLast(@NotNull String substringAfterLast, char delimiter, @NotNull String missingDelimiterValue) {
        Intrinsics.checkNotNullParameter(substringAfterLast, "$this$substringAfterLast");
        Intrinsics.checkNotNullParameter(missingDelimiterValue, "missingDelimiterValue");
        int index = StringsKt.lastIndexOf$default((CharSequence) substringAfterLast, delimiter, 0, false, 6, (Object) null);
        if (index == -1) {
            return missingDelimiterValue;
        }
        String strSubstring = substringAfterLast.substring(index + 1, substringAfterLast.length());
        Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return strSubstring;
    }

    public static /* synthetic */ String substringAfterLast$default(String str, String str2, String str3, int i, Object obj) {
        if ((i & 2) != 0) {
            str3 = str;
        }
        return StringsKt.substringAfterLast(str, str2, str3);
    }

    @NotNull
    public static final String substringAfterLast(@NotNull String substringAfterLast, @NotNull String delimiter, @NotNull String missingDelimiterValue) {
        Intrinsics.checkNotNullParameter(substringAfterLast, "$this$substringAfterLast");
        Intrinsics.checkNotNullParameter(delimiter, "delimiter");
        Intrinsics.checkNotNullParameter(missingDelimiterValue, "missingDelimiterValue");
        int index = StringsKt.lastIndexOf$default((CharSequence) substringAfterLast, delimiter, 0, false, 6, (Object) null);
        if (index == -1) {
            return missingDelimiterValue;
        }
        String strSubstring = substringAfterLast.substring(index + delimiter.length(), substringAfterLast.length());
        Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return strSubstring;
    }

    @NotNull
    public static final CharSequence replaceRange(@NotNull CharSequence replaceRange, int startIndex, int endIndex, @NotNull CharSequence replacement) {
        Intrinsics.checkNotNullParameter(replaceRange, "$this$replaceRange");
        Intrinsics.checkNotNullParameter(replacement, "replacement");
        if (endIndex < startIndex) {
            throw new IndexOutOfBoundsException("End index (" + endIndex + ") is less than start index (" + startIndex + ").");
        }
        StringBuilder sb = new StringBuilder();
        Intrinsics.checkNotNullExpressionValue(sb.append(replaceRange, 0, startIndex), "this.append(value, startIndex, endIndex)");
        sb.append(replacement);
        Intrinsics.checkNotNullExpressionValue(sb.append(replaceRange, endIndex, replaceRange.length()), "this.append(value, startIndex, endIndex)");
        return sb;
    }

    @InlineOnly
    private static final String replaceRange(String $this$replaceRange, int startIndex, int endIndex, CharSequence replacement) {
        if ($this$replaceRange == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.CharSequence");
        }
        return StringsKt.replaceRange((CharSequence) $this$replaceRange, startIndex, endIndex, replacement).toString();
    }

    @NotNull
    public static final CharSequence replaceRange(@NotNull CharSequence replaceRange, @NotNull IntRange range, @NotNull CharSequence replacement) {
        Intrinsics.checkNotNullParameter(replaceRange, "$this$replaceRange");
        Intrinsics.checkNotNullParameter(range, "range");
        Intrinsics.checkNotNullParameter(replacement, "replacement");
        return StringsKt.replaceRange(replaceRange, range.getStart().intValue(), range.getEndInclusive().intValue() + 1, replacement);
    }

    @InlineOnly
    private static final String replaceRange(String $this$replaceRange, IntRange range, CharSequence replacement) {
        if ($this$replaceRange == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.CharSequence");
        }
        return StringsKt.replaceRange((CharSequence) $this$replaceRange, range, replacement).toString();
    }

    @NotNull
    public static final CharSequence removeRange(@NotNull CharSequence removeRange, int startIndex, int endIndex) {
        Intrinsics.checkNotNullParameter(removeRange, "$this$removeRange");
        if (endIndex < startIndex) {
            throw new IndexOutOfBoundsException("End index (" + endIndex + ") is less than start index (" + startIndex + ").");
        }
        if (endIndex == startIndex) {
            return removeRange.subSequence(0, removeRange.length());
        }
        StringBuilder sb = new StringBuilder(removeRange.length() - (endIndex - startIndex));
        Intrinsics.checkNotNullExpressionValue(sb.append(removeRange, 0, startIndex), "this.append(value, startIndex, endIndex)");
        Intrinsics.checkNotNullExpressionValue(sb.append(removeRange, endIndex, removeRange.length()), "this.append(value, startIndex, endIndex)");
        return sb;
    }

    @InlineOnly
    private static final String removeRange(String $this$removeRange, int startIndex, int endIndex) {
        if ($this$removeRange == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.CharSequence");
        }
        return StringsKt.removeRange((CharSequence) $this$removeRange, startIndex, endIndex).toString();
    }

    @NotNull
    public static final CharSequence removeRange(@NotNull CharSequence removeRange, @NotNull IntRange range) {
        Intrinsics.checkNotNullParameter(removeRange, "$this$removeRange");
        Intrinsics.checkNotNullParameter(range, "range");
        return StringsKt.removeRange(removeRange, range.getStart().intValue(), range.getEndInclusive().intValue() + 1);
    }

    @InlineOnly
    private static final String removeRange(String $this$removeRange, IntRange range) {
        if ($this$removeRange == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.CharSequence");
        }
        return StringsKt.removeRange((CharSequence) $this$removeRange, range).toString();
    }

    @NotNull
    public static final CharSequence removePrefix(@NotNull CharSequence removePrefix, @NotNull CharSequence prefix) {
        Intrinsics.checkNotNullParameter(removePrefix, "$this$removePrefix");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        if (StringsKt.startsWith$default(removePrefix, prefix, false, 2, (Object) null)) {
            return removePrefix.subSequence(prefix.length(), removePrefix.length());
        }
        return removePrefix.subSequence(0, removePrefix.length());
    }

    @NotNull
    public static final String removePrefix(@NotNull String removePrefix, @NotNull CharSequence prefix) {
        Intrinsics.checkNotNullParameter(removePrefix, "$this$removePrefix");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        if (StringsKt.startsWith$default((CharSequence) removePrefix, prefix, false, 2, (Object) null)) {
            String strSubstring = removePrefix.substring(prefix.length());
            Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.String).substring(startIndex)");
            return strSubstring;
        }
        return removePrefix;
    }

    @NotNull
    public static final CharSequence removeSuffix(@NotNull CharSequence removeSuffix, @NotNull CharSequence suffix) {
        Intrinsics.checkNotNullParameter(removeSuffix, "$this$removeSuffix");
        Intrinsics.checkNotNullParameter(suffix, "suffix");
        if (StringsKt.endsWith$default(removeSuffix, suffix, false, 2, (Object) null)) {
            return removeSuffix.subSequence(0, removeSuffix.length() - suffix.length());
        }
        return removeSuffix.subSequence(0, removeSuffix.length());
    }

    @NotNull
    public static final String removeSuffix(@NotNull String removeSuffix, @NotNull CharSequence suffix) {
        Intrinsics.checkNotNullParameter(removeSuffix, "$this$removeSuffix");
        Intrinsics.checkNotNullParameter(suffix, "suffix");
        if (StringsKt.endsWith$default((CharSequence) removeSuffix, suffix, false, 2, (Object) null)) {
            String strSubstring = removeSuffix.substring(0, removeSuffix.length() - suffix.length());
            Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            return strSubstring;
        }
        return removeSuffix;
    }

    @NotNull
    public static final CharSequence removeSurrounding(@NotNull CharSequence removeSurrounding, @NotNull CharSequence prefix, @NotNull CharSequence suffix) {
        Intrinsics.checkNotNullParameter(removeSurrounding, "$this$removeSurrounding");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        Intrinsics.checkNotNullParameter(suffix, "suffix");
        if (removeSurrounding.length() >= prefix.length() + suffix.length() && StringsKt.startsWith$default(removeSurrounding, prefix, false, 2, (Object) null) && StringsKt.endsWith$default(removeSurrounding, suffix, false, 2, (Object) null)) {
            return removeSurrounding.subSequence(prefix.length(), removeSurrounding.length() - suffix.length());
        }
        return removeSurrounding.subSequence(0, removeSurrounding.length());
    }

    @NotNull
    public static final String removeSurrounding(@NotNull String removeSurrounding, @NotNull CharSequence prefix, @NotNull CharSequence suffix) {
        Intrinsics.checkNotNullParameter(removeSurrounding, "$this$removeSurrounding");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        Intrinsics.checkNotNullParameter(suffix, "suffix");
        if (removeSurrounding.length() >= prefix.length() + suffix.length() && StringsKt.startsWith$default((CharSequence) removeSurrounding, prefix, false, 2, (Object) null) && StringsKt.endsWith$default((CharSequence) removeSurrounding, suffix, false, 2, (Object) null)) {
            String strSubstring = removeSurrounding.substring(prefix.length(), removeSurrounding.length() - suffix.length());
            Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            return strSubstring;
        }
        return removeSurrounding;
    }

    @NotNull
    public static final CharSequence removeSurrounding(@NotNull CharSequence removeSurrounding, @NotNull CharSequence delimiter) {
        Intrinsics.checkNotNullParameter(removeSurrounding, "$this$removeSurrounding");
        Intrinsics.checkNotNullParameter(delimiter, "delimiter");
        return StringsKt.removeSurrounding(removeSurrounding, delimiter, delimiter);
    }

    @NotNull
    public static final String removeSurrounding(@NotNull String removeSurrounding, @NotNull CharSequence delimiter) {
        Intrinsics.checkNotNullParameter(removeSurrounding, "$this$removeSurrounding");
        Intrinsics.checkNotNullParameter(delimiter, "delimiter");
        return StringsKt.removeSurrounding(removeSurrounding, delimiter, delimiter);
    }

    public static /* synthetic */ String replaceBefore$default(String str, char c, String str2, String str3, int i, Object obj) {
        if ((i & 4) != 0) {
            str3 = str;
        }
        return StringsKt.replaceBefore(str, c, str2, str3);
    }

    @NotNull
    public static final String replaceBefore(@NotNull String replaceBefore, char delimiter, @NotNull String replacement, @NotNull String missingDelimiterValue) {
        Intrinsics.checkNotNullParameter(replaceBefore, "$this$replaceBefore");
        Intrinsics.checkNotNullParameter(replacement, "replacement");
        Intrinsics.checkNotNullParameter(missingDelimiterValue, "missingDelimiterValue");
        int index = StringsKt.indexOf$default((CharSequence) replaceBefore, delimiter, 0, false, 6, (Object) null);
        return index == -1 ? missingDelimiterValue : StringsKt.replaceRange((CharSequence) replaceBefore, 0, index, (CharSequence) replacement).toString();
    }

    public static /* synthetic */ String replaceBefore$default(String str, String str2, String str3, String str4, int i, Object obj) {
        if ((i & 4) != 0) {
            str4 = str;
        }
        return StringsKt.replaceBefore(str, str2, str3, str4);
    }

    @NotNull
    public static final String replaceBefore(@NotNull String replaceBefore, @NotNull String delimiter, @NotNull String replacement, @NotNull String missingDelimiterValue) {
        Intrinsics.checkNotNullParameter(replaceBefore, "$this$replaceBefore");
        Intrinsics.checkNotNullParameter(delimiter, "delimiter");
        Intrinsics.checkNotNullParameter(replacement, "replacement");
        Intrinsics.checkNotNullParameter(missingDelimiterValue, "missingDelimiterValue");
        int index = StringsKt.indexOf$default((CharSequence) replaceBefore, delimiter, 0, false, 6, (Object) null);
        return index == -1 ? missingDelimiterValue : StringsKt.replaceRange((CharSequence) replaceBefore, 0, index, (CharSequence) replacement).toString();
    }

    public static /* synthetic */ String replaceAfter$default(String str, char c, String str2, String str3, int i, Object obj) {
        if ((i & 4) != 0) {
            str3 = str;
        }
        return StringsKt.replaceAfter(str, c, str2, str3);
    }

    @NotNull
    public static final String replaceAfter(@NotNull String replaceAfter, char delimiter, @NotNull String replacement, @NotNull String missingDelimiterValue) {
        Intrinsics.checkNotNullParameter(replaceAfter, "$this$replaceAfter");
        Intrinsics.checkNotNullParameter(replacement, "replacement");
        Intrinsics.checkNotNullParameter(missingDelimiterValue, "missingDelimiterValue");
        int index = StringsKt.indexOf$default((CharSequence) replaceAfter, delimiter, 0, false, 6, (Object) null);
        if (index == -1) {
            return missingDelimiterValue;
        }
        return StringsKt.replaceRange((CharSequence) replaceAfter, index + 1, replaceAfter.length(), (CharSequence) replacement).toString();
    }

    public static /* synthetic */ String replaceAfter$default(String str, String str2, String str3, String str4, int i, Object obj) {
        if ((i & 4) != 0) {
            str4 = str;
        }
        return StringsKt.replaceAfter(str, str2, str3, str4);
    }

    @NotNull
    public static final String replaceAfter(@NotNull String replaceAfter, @NotNull String delimiter, @NotNull String replacement, @NotNull String missingDelimiterValue) {
        Intrinsics.checkNotNullParameter(replaceAfter, "$this$replaceAfter");
        Intrinsics.checkNotNullParameter(delimiter, "delimiter");
        Intrinsics.checkNotNullParameter(replacement, "replacement");
        Intrinsics.checkNotNullParameter(missingDelimiterValue, "missingDelimiterValue");
        int index = StringsKt.indexOf$default((CharSequence) replaceAfter, delimiter, 0, false, 6, (Object) null);
        if (index == -1) {
            return missingDelimiterValue;
        }
        return StringsKt.replaceRange((CharSequence) replaceAfter, index + delimiter.length(), replaceAfter.length(), (CharSequence) replacement).toString();
    }

    public static /* synthetic */ String replaceAfterLast$default(String str, String str2, String str3, String str4, int i, Object obj) {
        if ((i & 4) != 0) {
            str4 = str;
        }
        return StringsKt.replaceAfterLast(str, str2, str3, str4);
    }

    @NotNull
    public static final String replaceAfterLast(@NotNull String replaceAfterLast, @NotNull String delimiter, @NotNull String replacement, @NotNull String missingDelimiterValue) {
        Intrinsics.checkNotNullParameter(replaceAfterLast, "$this$replaceAfterLast");
        Intrinsics.checkNotNullParameter(delimiter, "delimiter");
        Intrinsics.checkNotNullParameter(replacement, "replacement");
        Intrinsics.checkNotNullParameter(missingDelimiterValue, "missingDelimiterValue");
        int index = StringsKt.lastIndexOf$default((CharSequence) replaceAfterLast, delimiter, 0, false, 6, (Object) null);
        if (index == -1) {
            return missingDelimiterValue;
        }
        return StringsKt.replaceRange((CharSequence) replaceAfterLast, index + delimiter.length(), replaceAfterLast.length(), (CharSequence) replacement).toString();
    }

    public static /* synthetic */ String replaceAfterLast$default(String str, char c, String str2, String str3, int i, Object obj) {
        if ((i & 4) != 0) {
            str3 = str;
        }
        return StringsKt.replaceAfterLast(str, c, str2, str3);
    }

    @NotNull
    public static final String replaceAfterLast(@NotNull String replaceAfterLast, char delimiter, @NotNull String replacement, @NotNull String missingDelimiterValue) {
        Intrinsics.checkNotNullParameter(replaceAfterLast, "$this$replaceAfterLast");
        Intrinsics.checkNotNullParameter(replacement, "replacement");
        Intrinsics.checkNotNullParameter(missingDelimiterValue, "missingDelimiterValue");
        int index = StringsKt.lastIndexOf$default((CharSequence) replaceAfterLast, delimiter, 0, false, 6, (Object) null);
        if (index == -1) {
            return missingDelimiterValue;
        }
        return StringsKt.replaceRange((CharSequence) replaceAfterLast, index + 1, replaceAfterLast.length(), (CharSequence) replacement).toString();
    }

    public static /* synthetic */ String replaceBeforeLast$default(String str, char c, String str2, String str3, int i, Object obj) {
        if ((i & 4) != 0) {
            str3 = str;
        }
        return StringsKt.replaceBeforeLast(str, c, str2, str3);
    }

    @NotNull
    public static final String replaceBeforeLast(@NotNull String replaceBeforeLast, char delimiter, @NotNull String replacement, @NotNull String missingDelimiterValue) {
        Intrinsics.checkNotNullParameter(replaceBeforeLast, "$this$replaceBeforeLast");
        Intrinsics.checkNotNullParameter(replacement, "replacement");
        Intrinsics.checkNotNullParameter(missingDelimiterValue, "missingDelimiterValue");
        int index = StringsKt.lastIndexOf$default((CharSequence) replaceBeforeLast, delimiter, 0, false, 6, (Object) null);
        return index == -1 ? missingDelimiterValue : StringsKt.replaceRange((CharSequence) replaceBeforeLast, 0, index, (CharSequence) replacement).toString();
    }

    public static /* synthetic */ String replaceBeforeLast$default(String str, String str2, String str3, String str4, int i, Object obj) {
        if ((i & 4) != 0) {
            str4 = str;
        }
        return StringsKt.replaceBeforeLast(str, str2, str3, str4);
    }

    @NotNull
    public static final String replaceBeforeLast(@NotNull String replaceBeforeLast, @NotNull String delimiter, @NotNull String replacement, @NotNull String missingDelimiterValue) {
        Intrinsics.checkNotNullParameter(replaceBeforeLast, "$this$replaceBeforeLast");
        Intrinsics.checkNotNullParameter(delimiter, "delimiter");
        Intrinsics.checkNotNullParameter(replacement, "replacement");
        Intrinsics.checkNotNullParameter(missingDelimiterValue, "missingDelimiterValue");
        int index = StringsKt.lastIndexOf$default((CharSequence) replaceBeforeLast, delimiter, 0, false, 6, (Object) null);
        return index == -1 ? missingDelimiterValue : StringsKt.replaceRange((CharSequence) replaceBeforeLast, 0, index, (CharSequence) replacement).toString();
    }

    @InlineOnly
    private static final String replace(CharSequence $this$replace, Regex regex, String replacement) {
        return regex.replace($this$replace, replacement);
    }

    @InlineOnly
    private static final String replace(CharSequence $this$replace, Regex regex, Function1<? super MatchResult, ? extends CharSequence> function1) {
        return regex.replace($this$replace, function1);
    }

    @InlineOnly
    private static final String replaceFirst(CharSequence $this$replaceFirst, Regex regex, String replacement) {
        return regex.replaceFirst($this$replaceFirst, replacement);
    }

    @SinceKotlin(version = "1.5")
    @JvmName(name = "replaceFirstCharWithChar")
    @InlineOnly
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @OverloadResolutionByLambdaReturnType
    private static final String replaceFirstCharWithChar(String $this$replaceFirstChar, Function1<? super Character, Character> function1) {
        if (!($this$replaceFirstChar.length() > 0)) {
            return $this$replaceFirstChar;
        }
        char cCharValue = function1.invoke(Character.valueOf($this$replaceFirstChar.charAt(0))).charValue();
        if ($this$replaceFirstChar == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String strSubstring = $this$replaceFirstChar.substring(1);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.String).substring(startIndex)");
        return String.valueOf(cCharValue) + strSubstring;
    }

    @SinceKotlin(version = "1.5")
    @JvmName(name = "replaceFirstCharWithCharSequence")
    @InlineOnly
    @WasExperimental(markerClass = {ExperimentalStdlibApi.class})
    @OverloadResolutionByLambdaReturnType
    private static final String replaceFirstCharWithCharSequence(String $this$replaceFirstChar, Function1<? super Character, ? extends CharSequence> function1) {
        if (!($this$replaceFirstChar.length() > 0)) {
            return $this$replaceFirstChar;
        }
        StringBuilder sbAppend = new StringBuilder().append(function1.invoke(Character.valueOf($this$replaceFirstChar.charAt(0))).toString());
        if ($this$replaceFirstChar == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String strSubstring = $this$replaceFirstChar.substring(1);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.String).substring(startIndex)");
        return sbAppend.append(strSubstring).toString();
    }

    @InlineOnly
    private static final boolean matches(CharSequence $this$matches, Regex regex) {
        return regex.matches($this$matches);
    }

    public static final boolean regionMatchesImpl(@NotNull CharSequence regionMatchesImpl, int thisOffset, @NotNull CharSequence other, int otherOffset, int length, boolean ignoreCase) {
        Intrinsics.checkNotNullParameter(regionMatchesImpl, "$this$regionMatchesImpl");
        Intrinsics.checkNotNullParameter(other, "other");
        if (otherOffset < 0 || thisOffset < 0 || thisOffset > regionMatchesImpl.length() - length || otherOffset > other.length() - length) {
            return false;
        }
        for (int index = 0; index < length; index++) {
            if (!CharsKt.equals(regionMatchesImpl.charAt(thisOffset + index), other.charAt(otherOffset + index), ignoreCase)) {
                return false;
            }
        }
        return true;
    }

    public static /* synthetic */ boolean startsWith$default(CharSequence charSequence, char c, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return StringsKt.startsWith(charSequence, c, z);
    }

    public static final boolean startsWith(@NotNull CharSequence startsWith, char c, boolean ignoreCase) {
        Intrinsics.checkNotNullParameter(startsWith, "$this$startsWith");
        return startsWith.length() > 0 && CharsKt.equals(startsWith.charAt(0), c, ignoreCase);
    }

    public static /* synthetic */ boolean endsWith$default(CharSequence charSequence, char c, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return StringsKt.endsWith(charSequence, c, z);
    }

    public static final boolean endsWith(@NotNull CharSequence endsWith, char c, boolean ignoreCase) {
        Intrinsics.checkNotNullParameter(endsWith, "$this$endsWith");
        return endsWith.length() > 0 && CharsKt.equals(endsWith.charAt(StringsKt.getLastIndex(endsWith)), c, ignoreCase);
    }

    public static /* synthetic */ boolean startsWith$default(CharSequence charSequence, CharSequence charSequence2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return StringsKt.startsWith(charSequence, charSequence2, z);
    }

    public static final boolean startsWith(@NotNull CharSequence startsWith, @NotNull CharSequence prefix, boolean ignoreCase) {
        Intrinsics.checkNotNullParameter(startsWith, "$this$startsWith");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        if (!ignoreCase && (startsWith instanceof String) && (prefix instanceof String)) {
            return StringsKt.startsWith$default((String) startsWith, (String) prefix, false, 2, (Object) null);
        }
        return StringsKt.regionMatchesImpl(startsWith, 0, prefix, 0, prefix.length(), ignoreCase);
    }

    public static /* synthetic */ boolean startsWith$default(CharSequence charSequence, CharSequence charSequence2, int i, boolean z, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            z = false;
        }
        return StringsKt.startsWith(charSequence, charSequence2, i, z);
    }

    public static final boolean startsWith(@NotNull CharSequence startsWith, @NotNull CharSequence prefix, int startIndex, boolean ignoreCase) {
        Intrinsics.checkNotNullParameter(startsWith, "$this$startsWith");
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        if (!ignoreCase && (startsWith instanceof String) && (prefix instanceof String)) {
            return StringsKt.startsWith$default((String) startsWith, (String) prefix, startIndex, false, 4, (Object) null);
        }
        return StringsKt.regionMatchesImpl(startsWith, startIndex, prefix, 0, prefix.length(), ignoreCase);
    }

    public static /* synthetic */ boolean endsWith$default(CharSequence charSequence, CharSequence charSequence2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return StringsKt.endsWith(charSequence, charSequence2, z);
    }

    public static final boolean endsWith(@NotNull CharSequence endsWith, @NotNull CharSequence suffix, boolean ignoreCase) {
        Intrinsics.checkNotNullParameter(endsWith, "$this$endsWith");
        Intrinsics.checkNotNullParameter(suffix, "suffix");
        if (!ignoreCase && (endsWith instanceof String) && (suffix instanceof String)) {
            return StringsKt.endsWith$default((String) endsWith, (String) suffix, false, 2, (Object) null);
        }
        return StringsKt.regionMatchesImpl(endsWith, endsWith.length() - suffix.length(), suffix, 0, suffix.length(), ignoreCase);
    }

    public static /* synthetic */ String commonPrefixWith$default(CharSequence charSequence, CharSequence charSequence2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return StringsKt.commonPrefixWith(charSequence, charSequence2, z);
    }

    @NotNull
    public static final String commonPrefixWith(@NotNull CharSequence commonPrefixWith, @NotNull CharSequence other, boolean ignoreCase) {
        Intrinsics.checkNotNullParameter(commonPrefixWith, "$this$commonPrefixWith");
        Intrinsics.checkNotNullParameter(other, "other");
        int shortestLength = Math.min(commonPrefixWith.length(), other.length());
        int i = 0;
        while (i < shortestLength && CharsKt.equals(commonPrefixWith.charAt(i), other.charAt(i), ignoreCase)) {
            i++;
        }
        if (StringsKt.hasSurrogatePairAt(commonPrefixWith, i - 1) || StringsKt.hasSurrogatePairAt(other, i - 1)) {
            i--;
        }
        return commonPrefixWith.subSequence(0, i).toString();
    }

    public static /* synthetic */ String commonSuffixWith$default(CharSequence charSequence, CharSequence charSequence2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return StringsKt.commonSuffixWith(charSequence, charSequence2, z);
    }

    @NotNull
    public static final String commonSuffixWith(@NotNull CharSequence commonSuffixWith, @NotNull CharSequence other, boolean ignoreCase) {
        Intrinsics.checkNotNullParameter(commonSuffixWith, "$this$commonSuffixWith");
        Intrinsics.checkNotNullParameter(other, "other");
        int thisLength = commonSuffixWith.length();
        int otherLength = other.length();
        int shortestLength = Math.min(thisLength, otherLength);
        int i = 0;
        while (i < shortestLength && CharsKt.equals(commonSuffixWith.charAt((thisLength - i) - 1), other.charAt((otherLength - i) - 1), ignoreCase)) {
            i++;
        }
        if (StringsKt.hasSurrogatePairAt(commonSuffixWith, (thisLength - i) - 1) || StringsKt.hasSurrogatePairAt(other, (otherLength - i) - 1)) {
            i--;
        }
        return commonSuffixWith.subSequence(thisLength - i, thisLength).toString();
    }

    public static /* synthetic */ int indexOfAny$default(CharSequence charSequence, char[] cArr, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return StringsKt.indexOfAny(charSequence, cArr, i, z);
    }

    public static final int indexOfAny(@NotNull CharSequence indexOfAny, @NotNull char[] chars, int startIndex, boolean ignoreCase) {
        boolean z;
        Intrinsics.checkNotNullParameter(indexOfAny, "$this$indexOfAny");
        Intrinsics.checkNotNullParameter(chars, "chars");
        if (!ignoreCase && chars.length == 1 && (indexOfAny instanceof String)) {
            return ((String) indexOfAny).indexOf(ArraysKt.single(chars), startIndex);
        }
        int index = RangesKt.coerceAtLeast(startIndex, 0);
        int lastIndex = StringsKt.getLastIndex(indexOfAny);
        if (index > lastIndex) {
            return -1;
        }
        while (true) {
            char charAtIndex = indexOfAny.charAt(index);
            int length = chars.length;
            int i = 0;
            while (true) {
                if (i < length) {
                    char element$iv = chars[i];
                    if (CharsKt.equals(element$iv, charAtIndex, ignoreCase)) {
                        z = true;
                        break;
                    }
                    i++;
                } else {
                    z = false;
                    break;
                }
            }
            if (!z) {
                if (index == lastIndex) {
                    return -1;
                }
                index++;
            } else {
                return index;
            }
        }
    }

    public static /* synthetic */ int lastIndexOfAny$default(CharSequence charSequence, char[] cArr, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = StringsKt.getLastIndex(charSequence);
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return StringsKt.lastIndexOfAny(charSequence, cArr, i, z);
    }

    public static final int lastIndexOfAny(@NotNull CharSequence lastIndexOfAny, @NotNull char[] chars, int startIndex, boolean ignoreCase) {
        boolean z;
        Intrinsics.checkNotNullParameter(lastIndexOfAny, "$this$lastIndexOfAny");
        Intrinsics.checkNotNullParameter(chars, "chars");
        if (!ignoreCase && chars.length == 1 && (lastIndexOfAny instanceof String)) {
            return ((String) lastIndexOfAny).lastIndexOf(ArraysKt.single(chars), startIndex);
        }
        for (int index = RangesKt.coerceAtMost(startIndex, StringsKt.getLastIndex(lastIndexOfAny)); index >= 0; index--) {
            char charAtIndex = lastIndexOfAny.charAt(index);
            int length = chars.length;
            int i = 0;
            while (true) {
                if (i < length) {
                    char element$iv = chars[i];
                    if (CharsKt.equals(element$iv, charAtIndex, ignoreCase)) {
                        z = true;
                        break;
                    }
                    i++;
                } else {
                    z = false;
                    break;
                }
            }
            if (z) {
                return index;
            }
        }
        return -1;
    }

    static /* synthetic */ int indexOf$StringsKt__StringsKt$default(CharSequence charSequence, CharSequence charSequence2, int i, int i2, boolean z, boolean z2, int i3, Object obj) {
        if ((i3 & 16) != 0) {
            z2 = false;
        }
        return indexOf$StringsKt__StringsKt(charSequence, charSequence2, i, i2, z, z2);
    }

    private static final int indexOf$StringsKt__StringsKt(CharSequence $this$indexOf, CharSequence other, int startIndex, int endIndex, boolean ignoreCase, boolean last) {
        IntRange intRangeDownTo;
        if (!last) {
            intRangeDownTo = new IntRange(RangesKt.coerceAtLeast(startIndex, 0), RangesKt.coerceAtMost(endIndex, $this$indexOf.length()));
        } else {
            intRangeDownTo = RangesKt.downTo(RangesKt.coerceAtMost(startIndex, StringsKt.getLastIndex($this$indexOf)), RangesKt.coerceAtLeast(endIndex, 0));
        }
        IntProgression indices = intRangeDownTo;
        if (($this$indexOf instanceof String) && (other instanceof String)) {
            int index = indices.getFirst();
            int last2 = indices.getLast();
            int step = indices.getStep();
            if (step >= 0) {
                if (index > last2) {
                    return -1;
                }
            } else if (index < last2) {
                return -1;
            }
            while (!StringsKt.regionMatches((String) other, 0, (String) $this$indexOf, index, other.length(), ignoreCase)) {
                if (index == last2) {
                    return -1;
                }
                index += step;
            }
            return index;
        }
        int index2 = indices.getFirst();
        int last3 = indices.getLast();
        int step2 = indices.getStep();
        if (step2 >= 0) {
            if (index2 > last3) {
                return -1;
            }
        } else if (index2 < last3) {
            return -1;
        }
        while (!StringsKt.regionMatchesImpl(other, 0, $this$indexOf, index2, other.length(), ignoreCase)) {
            if (index2 == last3) {
                return -1;
            }
            index2 += step2;
        }
        return index2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Pair<Integer, String> findAnyOf$StringsKt__StringsKt(CharSequence $this$findAnyOf, Collection<String> collection, int startIndex, boolean ignoreCase, boolean last) {
        Object obj;
        Object obj2;
        if (!ignoreCase && collection.size() == 1) {
            String string = (String) CollectionsKt.single(collection);
            int index = !last ? StringsKt.indexOf$default($this$findAnyOf, string, startIndex, false, 4, (Object) null) : StringsKt.lastIndexOf$default($this$findAnyOf, string, startIndex, false, 4, (Object) null);
            if (index < 0) {
                return null;
            }
            return TuplesKt.to(Integer.valueOf(index), string);
        }
        IntProgression indices = !last ? new IntRange(RangesKt.coerceAtLeast(startIndex, 0), $this$findAnyOf.length()) : RangesKt.downTo(RangesKt.coerceAtMost(startIndex, StringsKt.getLastIndex($this$findAnyOf)), 0);
        if ($this$findAnyOf instanceof String) {
            int index2 = indices.getFirst();
            int last2 = indices.getLast();
            int step = indices.getStep();
            if (step >= 0) {
                if (index2 > last2) {
                    return null;
                }
            } else if (index2 < last2) {
                return null;
            }
            while (true) {
                Collection<String> $this$firstOrNull$iv = collection;
                Iterator it = $this$firstOrNull$iv.iterator();
                while (true) {
                    if (it.hasNext()) {
                        Object element$iv = it.next();
                        String it2 = (String) element$iv;
                        if (StringsKt.regionMatches(it2, 0, (String) $this$findAnyOf, index2, it2.length(), ignoreCase)) {
                            obj2 = element$iv;
                            break;
                        }
                    } else {
                        obj2 = null;
                        break;
                    }
                }
                String matchingString = (String) obj2;
                if (matchingString == null) {
                    if (index2 == last2) {
                        return null;
                    }
                    index2 += step;
                } else {
                    return TuplesKt.to(Integer.valueOf(index2), matchingString);
                }
            }
        } else {
            int index3 = indices.getFirst();
            int last3 = indices.getLast();
            int step2 = indices.getStep();
            if (step2 >= 0) {
                if (index3 > last3) {
                    return null;
                }
            } else if (index3 < last3) {
                return null;
            }
            while (true) {
                Collection<String> $this$firstOrNull$iv2 = collection;
                Iterator it3 = $this$firstOrNull$iv2.iterator();
                while (true) {
                    if (it3.hasNext()) {
                        Object element$iv2 = it3.next();
                        String it4 = (String) element$iv2;
                        if (StringsKt.regionMatchesImpl(it4, 0, $this$findAnyOf, index3, it4.length(), ignoreCase)) {
                            obj = element$iv2;
                            break;
                        }
                    } else {
                        obj = null;
                        break;
                    }
                }
                String matchingString2 = (String) obj;
                if (matchingString2 == null) {
                    if (index3 == last3) {
                        return null;
                    }
                    index3 += step2;
                } else {
                    return TuplesKt.to(Integer.valueOf(index3), matchingString2);
                }
            }
        }
    }

    public static /* synthetic */ Pair findAnyOf$default(CharSequence charSequence, Collection collection, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return StringsKt.findAnyOf(charSequence, collection, i, z);
    }

    @Nullable
    public static final Pair<Integer, String> findAnyOf(@NotNull CharSequence findAnyOf, @NotNull Collection<String> strings, int startIndex, boolean ignoreCase) {
        Intrinsics.checkNotNullParameter(findAnyOf, "$this$findAnyOf");
        Intrinsics.checkNotNullParameter(strings, "strings");
        return findAnyOf$StringsKt__StringsKt(findAnyOf, strings, startIndex, ignoreCase, false);
    }

    public static /* synthetic */ Pair findLastAnyOf$default(CharSequence charSequence, Collection collection, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = StringsKt.getLastIndex(charSequence);
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return StringsKt.findLastAnyOf(charSequence, collection, i, z);
    }

    @Nullable
    public static final Pair<Integer, String> findLastAnyOf(@NotNull CharSequence findLastAnyOf, @NotNull Collection<String> strings, int startIndex, boolean ignoreCase) {
        Intrinsics.checkNotNullParameter(findLastAnyOf, "$this$findLastAnyOf");
        Intrinsics.checkNotNullParameter(strings, "strings");
        return findAnyOf$StringsKt__StringsKt(findLastAnyOf, strings, startIndex, ignoreCase, true);
    }

    public static /* synthetic */ int indexOfAny$default(CharSequence charSequence, Collection collection, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return StringsKt.indexOfAny(charSequence, (Collection<String>) collection, i, z);
    }

    public static final int indexOfAny(@NotNull CharSequence indexOfAny, @NotNull Collection<String> strings, int startIndex, boolean ignoreCase) {
        Intrinsics.checkNotNullParameter(indexOfAny, "$this$indexOfAny");
        Intrinsics.checkNotNullParameter(strings, "strings");
        Pair<Integer, String> pairFindAnyOf$StringsKt__StringsKt = findAnyOf$StringsKt__StringsKt(indexOfAny, strings, startIndex, ignoreCase, false);
        if (pairFindAnyOf$StringsKt__StringsKt != null) {
            Integer first = pairFindAnyOf$StringsKt__StringsKt.getFirst();
            if (first != null) {
                return first.intValue();
            }
        }
        return -1;
    }

    public static /* synthetic */ int lastIndexOfAny$default(CharSequence charSequence, Collection collection, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = StringsKt.getLastIndex(charSequence);
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return StringsKt.lastIndexOfAny(charSequence, (Collection<String>) collection, i, z);
    }

    public static final int lastIndexOfAny(@NotNull CharSequence lastIndexOfAny, @NotNull Collection<String> strings, int startIndex, boolean ignoreCase) {
        Intrinsics.checkNotNullParameter(lastIndexOfAny, "$this$lastIndexOfAny");
        Intrinsics.checkNotNullParameter(strings, "strings");
        Pair<Integer, String> pairFindAnyOf$StringsKt__StringsKt = findAnyOf$StringsKt__StringsKt(lastIndexOfAny, strings, startIndex, ignoreCase, true);
        if (pairFindAnyOf$StringsKt__StringsKt != null) {
            Integer first = pairFindAnyOf$StringsKt__StringsKt.getFirst();
            if (first != null) {
                return first.intValue();
            }
        }
        return -1;
    }

    public static /* synthetic */ int indexOf$default(CharSequence charSequence, char c, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return StringsKt.indexOf(charSequence, c, i, z);
    }

    public static final int indexOf(@NotNull CharSequence indexOf, char c, int startIndex, boolean ignoreCase) {
        Intrinsics.checkNotNullParameter(indexOf, "$this$indexOf");
        if (ignoreCase || !(indexOf instanceof String)) {
            return StringsKt.indexOfAny(indexOf, new char[]{c}, startIndex, ignoreCase);
        }
        return ((String) indexOf).indexOf(c, startIndex);
    }

    public static /* synthetic */ int indexOf$default(CharSequence charSequence, String str, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return StringsKt.indexOf(charSequence, str, i, z);
    }

    public static final int indexOf(@NotNull CharSequence indexOf, @NotNull String string, int startIndex, boolean ignoreCase) {
        Intrinsics.checkNotNullParameter(indexOf, "$this$indexOf");
        Intrinsics.checkNotNullParameter(string, "string");
        if (ignoreCase || !(indexOf instanceof String)) {
            return indexOf$StringsKt__StringsKt$default(indexOf, string, startIndex, indexOf.length(), ignoreCase, false, 16, null);
        }
        return ((String) indexOf).indexOf(string, startIndex);
    }

    public static /* synthetic */ int lastIndexOf$default(CharSequence charSequence, char c, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = StringsKt.getLastIndex(charSequence);
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return StringsKt.lastIndexOf(charSequence, c, i, z);
    }

    public static final int lastIndexOf(@NotNull CharSequence lastIndexOf, char c, int startIndex, boolean ignoreCase) {
        Intrinsics.checkNotNullParameter(lastIndexOf, "$this$lastIndexOf");
        if (ignoreCase || !(lastIndexOf instanceof String)) {
            return StringsKt.lastIndexOfAny(lastIndexOf, new char[]{c}, startIndex, ignoreCase);
        }
        return ((String) lastIndexOf).lastIndexOf(c, startIndex);
    }

    public static /* synthetic */ int lastIndexOf$default(CharSequence charSequence, String str, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = StringsKt.getLastIndex(charSequence);
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return StringsKt.lastIndexOf(charSequence, str, i, z);
    }

    public static final int lastIndexOf(@NotNull CharSequence lastIndexOf, @NotNull String string, int startIndex, boolean ignoreCase) {
        Intrinsics.checkNotNullParameter(lastIndexOf, "$this$lastIndexOf");
        Intrinsics.checkNotNullParameter(string, "string");
        if (ignoreCase || !(lastIndexOf instanceof String)) {
            return indexOf$StringsKt__StringsKt(lastIndexOf, string, startIndex, 0, ignoreCase, true);
        }
        return ((String) lastIndexOf).lastIndexOf(string, startIndex);
    }

    public static /* synthetic */ boolean contains$default(CharSequence charSequence, CharSequence charSequence2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return StringsKt.contains(charSequence, charSequence2, z);
    }

    public static final boolean contains(@NotNull CharSequence contains, @NotNull CharSequence other, boolean ignoreCase) {
        Intrinsics.checkNotNullParameter(contains, "$this$contains");
        Intrinsics.checkNotNullParameter(other, "other");
        return other instanceof String ? StringsKt.indexOf$default(contains, (String) other, 0, ignoreCase, 2, (Object) null) >= 0 : indexOf$StringsKt__StringsKt$default(contains, other, 0, contains.length(), ignoreCase, false, 16, null) >= 0;
    }

    public static /* synthetic */ boolean contains$default(CharSequence charSequence, char c, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return StringsKt.contains(charSequence, c, z);
    }

    public static final boolean contains(@NotNull CharSequence contains, char c, boolean ignoreCase) {
        Intrinsics.checkNotNullParameter(contains, "$this$contains");
        return StringsKt.indexOf$default(contains, c, 0, ignoreCase, 2, (Object) null) >= 0;
    }

    @InlineOnly
    private static final boolean contains(CharSequence contains, Regex regex) {
        Intrinsics.checkNotNullParameter(contains, "$this$contains");
        return regex.containsMatchIn(contains);
    }

    static /* synthetic */ Sequence rangesDelimitedBy$StringsKt__StringsKt$default(CharSequence charSequence, char[] cArr, int i, boolean z, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            z = false;
        }
        if ((i3 & 8) != 0) {
            i2 = 0;
        }
        return rangesDelimitedBy$StringsKt__StringsKt(charSequence, cArr, i, z, i2);
    }

    private static final Sequence<IntRange> rangesDelimitedBy$StringsKt__StringsKt(CharSequence $this$rangesDelimitedBy, final char[] delimiters, int startIndex, final boolean ignoreCase, int limit) {
        if (!(limit >= 0)) {
            throw new IllegalArgumentException(("Limit must be non-negative, but was " + limit + '.').toString());
        }
        return new DelimitedRangesSequence($this$rangesDelimitedBy, startIndex, limit, new Function2<CharSequence, Integer, Pair<? extends Integer, ? extends Integer>>() { // from class: kotlin.text.StringsKt__StringsKt$rangesDelimitedBy$2
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Pair<? extends Integer, ? extends Integer> invoke(CharSequence charSequence, Integer num) {
                return invoke(charSequence, num.intValue());
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Nullable
            public final Pair<Integer, Integer> invoke(@NotNull CharSequence receiver, int currentIndex) {
                Intrinsics.checkNotNullParameter(receiver, "$receiver");
                int it = StringsKt.indexOfAny(receiver, delimiters, currentIndex, ignoreCase);
                if (it < 0) {
                    return null;
                }
                return TuplesKt.to(Integer.valueOf(it), 1);
            }
        });
    }

    static /* synthetic */ Sequence rangesDelimitedBy$StringsKt__StringsKt$default(CharSequence charSequence, String[] strArr, int i, boolean z, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            z = false;
        }
        if ((i3 & 8) != 0) {
            i2 = 0;
        }
        return rangesDelimitedBy$StringsKt__StringsKt(charSequence, strArr, i, z, i2);
    }

    private static final Sequence<IntRange> rangesDelimitedBy$StringsKt__StringsKt(CharSequence $this$rangesDelimitedBy, String[] delimiters, int startIndex, final boolean ignoreCase, int limit) {
        if (!(limit >= 0)) {
            throw new IllegalArgumentException(("Limit must be non-negative, but was " + limit + '.').toString());
        }
        final List delimitersList = ArraysKt.asList(delimiters);
        return new DelimitedRangesSequence($this$rangesDelimitedBy, startIndex, limit, new Function2<CharSequence, Integer, Pair<? extends Integer, ? extends Integer>>() { // from class: kotlin.text.StringsKt__StringsKt$rangesDelimitedBy$4
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Pair<? extends Integer, ? extends Integer> invoke(CharSequence charSequence, Integer num) {
                return invoke(charSequence, num.intValue());
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Nullable
            public final Pair<Integer, Integer> invoke(@NotNull CharSequence receiver, int currentIndex) {
                Intrinsics.checkNotNullParameter(receiver, "$receiver");
                Pair it = StringsKt__StringsKt.findAnyOf$StringsKt__StringsKt(receiver, delimitersList, currentIndex, ignoreCase, false);
                if (it != null) {
                    return TuplesKt.to(it.getFirst(), Integer.valueOf(((String) it.getSecond()).length()));
                }
                return null;
            }
        });
    }

    public static /* synthetic */ Sequence splitToSequence$default(CharSequence charSequence, String[] strArr, boolean z, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        if ((i2 & 4) != 0) {
            i = 0;
        }
        return StringsKt.splitToSequence(charSequence, strArr, z, i);
    }

    @NotNull
    public static final Sequence<String> splitToSequence(@NotNull final CharSequence splitToSequence, @NotNull String[] delimiters, boolean ignoreCase, int limit) {
        Intrinsics.checkNotNullParameter(splitToSequence, "$this$splitToSequence");
        Intrinsics.checkNotNullParameter(delimiters, "delimiters");
        return SequencesKt.map(rangesDelimitedBy$StringsKt__StringsKt$default(splitToSequence, delimiters, 0, ignoreCase, limit, 2, (Object) null), new Function1<IntRange, String>() { // from class: kotlin.text.StringsKt__StringsKt.splitToSequence.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final String invoke(@NotNull IntRange it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return StringsKt.substring(splitToSequence, it);
            }
        });
    }

    public static /* synthetic */ List split$default(CharSequence charSequence, String[] strArr, boolean z, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        if ((i2 & 4) != 0) {
            i = 0;
        }
        return StringsKt.split(charSequence, strArr, z, i);
    }

    @NotNull
    public static final List<String> split(@NotNull CharSequence split, @NotNull String[] delimiters, boolean ignoreCase, int limit) {
        Intrinsics.checkNotNullParameter(split, "$this$split");
        Intrinsics.checkNotNullParameter(delimiters, "delimiters");
        if (delimiters.length == 1) {
            String delimiter = delimiters[0];
            if (!(delimiter.length() == 0)) {
                return split$StringsKt__StringsKt(split, delimiter, ignoreCase, limit);
            }
        }
        Iterable $this$map$iv = SequencesKt.asIterable(rangesDelimitedBy$StringsKt__StringsKt$default(split, delimiters, 0, ignoreCase, limit, 2, (Object) null));
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            IntRange it = (IntRange) item$iv$iv;
            destination$iv$iv.add(StringsKt.substring(split, it));
        }
        return (List) destination$iv$iv;
    }

    public static /* synthetic */ Sequence splitToSequence$default(CharSequence charSequence, char[] cArr, boolean z, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        if ((i2 & 4) != 0) {
            i = 0;
        }
        return StringsKt.splitToSequence(charSequence, cArr, z, i);
    }

    @NotNull
    public static final Sequence<String> splitToSequence(@NotNull final CharSequence splitToSequence, @NotNull char[] delimiters, boolean ignoreCase, int limit) {
        Intrinsics.checkNotNullParameter(splitToSequence, "$this$splitToSequence");
        Intrinsics.checkNotNullParameter(delimiters, "delimiters");
        return SequencesKt.map(rangesDelimitedBy$StringsKt__StringsKt$default(splitToSequence, delimiters, 0, ignoreCase, limit, 2, (Object) null), new Function1<IntRange, String>() { // from class: kotlin.text.StringsKt__StringsKt.splitToSequence.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final String invoke(@NotNull IntRange it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return StringsKt.substring(splitToSequence, it);
            }
        });
    }

    public static /* synthetic */ List split$default(CharSequence charSequence, char[] cArr, boolean z, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        if ((i2 & 4) != 0) {
            i = 0;
        }
        return StringsKt.split(charSequence, cArr, z, i);
    }

    @NotNull
    public static final List<String> split(@NotNull CharSequence split, @NotNull char[] delimiters, boolean ignoreCase, int limit) {
        Intrinsics.checkNotNullParameter(split, "$this$split");
        Intrinsics.checkNotNullParameter(delimiters, "delimiters");
        if (delimiters.length == 1) {
            return split$StringsKt__StringsKt(split, String.valueOf(delimiters[0]), ignoreCase, limit);
        }
        Iterable $this$map$iv = SequencesKt.asIterable(rangesDelimitedBy$StringsKt__StringsKt$default(split, delimiters, 0, ignoreCase, limit, 2, (Object) null));
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            IntRange it = (IntRange) item$iv$iv;
            destination$iv$iv.add(StringsKt.substring(split, it));
        }
        return (List) destination$iv$iv;
    }

    private static final List<String> split$StringsKt__StringsKt(CharSequence $this$split, String delimiter, boolean ignoreCase, int limit) {
        if (!(limit >= 0)) {
            throw new IllegalArgumentException(("Limit must be non-negative, but was " + limit + '.').toString());
        }
        int currentOffset = 0;
        int nextIndex = StringsKt.indexOf($this$split, delimiter, 0, ignoreCase);
        if (nextIndex == -1 || limit == 1) {
            return CollectionsKt.listOf($this$split.toString());
        }
        boolean isLimited = limit > 0;
        ArrayList result = new ArrayList(isLimited ? RangesKt.coerceAtMost(limit, 10) : 10);
        do {
            result.add($this$split.subSequence(currentOffset, nextIndex).toString());
            currentOffset = nextIndex + delimiter.length();
            if (isLimited && result.size() == limit - 1) {
                break;
            }
            nextIndex = StringsKt.indexOf($this$split, delimiter, currentOffset, ignoreCase);
        } while (nextIndex != -1);
        result.add($this$split.subSequence(currentOffset, $this$split.length()).toString());
        return result;
    }

    @InlineOnly
    private static final List<String> split(CharSequence $this$split, Regex regex, int limit) {
        return regex.split($this$split, limit);
    }

    static /* synthetic */ List split$default(CharSequence $this$split, Regex regex, int limit, int i, Object obj) {
        if ((i & 2) != 0) {
            limit = 0;
        }
        return regex.split($this$split, limit);
    }

    @NotNull
    public static final Sequence<String> lineSequence(@NotNull CharSequence lineSequence) {
        Intrinsics.checkNotNullParameter(lineSequence, "$this$lineSequence");
        return StringsKt.splitToSequence$default(lineSequence, new String[]{StrPool.CRLF, "\n", "\r"}, false, 0, 6, (Object) null);
    }

    @NotNull
    public static final List<String> lines(@NotNull CharSequence lines) {
        Intrinsics.checkNotNullParameter(lines, "$this$lines");
        return SequencesKt.toList(StringsKt.lineSequence(lines));
    }

    public static final boolean contentEqualsIgnoreCaseImpl(@Nullable CharSequence $this$contentEqualsIgnoreCaseImpl, @Nullable CharSequence other) {
        if (($this$contentEqualsIgnoreCaseImpl instanceof String) && (other instanceof String)) {
            return StringsKt.equals((String) $this$contentEqualsIgnoreCaseImpl, (String) other, true);
        }
        if ($this$contentEqualsIgnoreCaseImpl == other) {
            return true;
        }
        if ($this$contentEqualsIgnoreCaseImpl == null || other == null || $this$contentEqualsIgnoreCaseImpl.length() != other.length()) {
            return false;
        }
        int length = $this$contentEqualsIgnoreCaseImpl.length();
        for (int i = 0; i < length; i++) {
            if (!CharsKt.equals($this$contentEqualsIgnoreCaseImpl.charAt(i), other.charAt(i), true)) {
                return false;
            }
        }
        return true;
    }

    public static final boolean contentEqualsImpl(@Nullable CharSequence $this$contentEqualsImpl, @Nullable CharSequence other) {
        if (($this$contentEqualsImpl instanceof String) && (other instanceof String)) {
            return Intrinsics.areEqual($this$contentEqualsImpl, other);
        }
        if ($this$contentEqualsImpl == other) {
            return true;
        }
        if ($this$contentEqualsImpl == null || other == null || $this$contentEqualsImpl.length() != other.length()) {
            return false;
        }
        int length = $this$contentEqualsImpl.length();
        for (int i = 0; i < length; i++) {
            if ($this$contentEqualsImpl.charAt(i) != other.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @SinceKotlin(version = "1.5")
    public static final boolean toBooleanStrict(@NotNull String toBooleanStrict) {
        Intrinsics.checkNotNullParameter(toBooleanStrict, "$this$toBooleanStrict");
        switch (toBooleanStrict.hashCode()) {
            case 3569038:
                if (toBooleanStrict.equals("true")) {
                    return true;
                }
                break;
            case 97196323:
                if (toBooleanStrict.equals("false")) {
                    return false;
                }
                break;
        }
        throw new IllegalArgumentException("The string doesn't represent a boolean value: " + toBooleanStrict);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @SinceKotlin(version = "1.5")
    @Nullable
    public static final Boolean toBooleanStrictOrNull(@NotNull String toBooleanStrictOrNull) {
        Intrinsics.checkNotNullParameter(toBooleanStrictOrNull, "$this$toBooleanStrictOrNull");
        switch (toBooleanStrictOrNull.hashCode()) {
            case 3569038:
                if (toBooleanStrictOrNull.equals("true")) {
                    return true;
                }
                return null;
            case 97196323:
                if (toBooleanStrictOrNull.equals("false")) {
                    return false;
                }
                return null;
            default:
                return null;
        }
    }
}
