package kotlin.time;

import java.util.concurrent.TimeUnit;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.ReplaceWith;
import kotlin.SinceKotlin;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.JvmInline;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.LongRange;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import okhttp3.internal.http2.Http2Connection;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;
import org.springframework.jmx.export.naming.IdentityNamingStrategy;

/* compiled from: Duration.kt */
@SinceKotlin(version = "1.3")
@Metadata(mv = {1, 5, 1}, k = 1, d1 = {"��f\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n��\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b4\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0002\u0010��\n\u0002\b\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0011\b\u0087@\u0018�� ¥\u00012\b\u0012\u0004\u0012\u00020��0\u0001:\u0002¥\u0001B\u0014\b��\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001��¢\u0006\u0004\b\u0004\u0010\u0005J%\u0010K\u001a\u00020��2\u0006\u0010L\u001a\u00020\u00032\u0006\u0010M\u001a\u00020\u0003H\u0002ø\u0001��ø\u0001\u0001¢\u0006\u0004\bN\u0010OJ\u001b\u0010P\u001a\u00020\t2\u0006\u0010Q\u001a\u00020��H\u0096\u0002ø\u0001��¢\u0006\u0004\bR\u0010SJ\u001e\u0010T\u001a\u00020��2\u0006\u0010U\u001a\u00020\u000fH\u0086\u0002ø\u0001��ø\u0001\u0001¢\u0006\u0004\bV\u0010WJ\u001e\u0010T\u001a\u00020��2\u0006\u0010U\u001a\u00020\tH\u0086\u0002ø\u0001��ø\u0001\u0001¢\u0006\u0004\bV\u0010XJ\u001b\u0010T\u001a\u00020\u000f2\u0006\u0010Q\u001a\u00020��H\u0086\u0002ø\u0001��¢\u0006\u0004\bY\u0010ZJ\u001a\u0010[\u001a\u00020\\2\b\u0010Q\u001a\u0004\u0018\u00010]HÖ\u0003¢\u0006\u0004\b^\u0010_J\u0010\u0010`\u001a\u00020\tHÖ\u0001¢\u0006\u0004\ba\u0010\rJ\r\u0010b\u001a\u00020\\¢\u0006\u0004\bc\u0010dJ\u000f\u0010e\u001a\u00020\\H\u0002¢\u0006\u0004\bf\u0010dJ\u000f\u0010g\u001a\u00020\\H\u0002¢\u0006\u0004\bh\u0010dJ\r\u0010i\u001a\u00020\\¢\u0006\u0004\bj\u0010dJ\r\u0010k\u001a\u00020\\¢\u0006\u0004\bl\u0010dJ\r\u0010m\u001a\u00020\\¢\u0006\u0004\bn\u0010dJ\u001b\u0010o\u001a\u00020��2\u0006\u0010Q\u001a\u00020��H\u0086\u0002ø\u0001��¢\u0006\u0004\bp\u0010qJ\u001b\u0010r\u001a\u00020��2\u0006\u0010Q\u001a\u00020��H\u0086\u0002ø\u0001��¢\u0006\u0004\bs\u0010qJ\u0017\u0010t\u001a\u00020\t2\u0006\u0010I\u001a\u00020\u000fH\u0002¢\u0006\u0004\bu\u0010vJ\u001e\u0010w\u001a\u00020��2\u0006\u0010U\u001a\u00020\u000fH\u0086\u0002ø\u0001��ø\u0001\u0001¢\u0006\u0004\bx\u0010WJ\u001e\u0010w\u001a\u00020��2\u0006\u0010U\u001a\u00020\tH\u0086\u0002ø\u0001��ø\u0001\u0001¢\u0006\u0004\bx\u0010XJ£\u0001\u0010y\u001a\u0002Hz\"\u0004\b��\u0010z2y\u0010{\u001au\u0012\u0013\u0012\u00110\t¢\u0006\f\b}\u0012\b\b~\u0012\u0004\b\b(\u007f\u0012\u0014\u0012\u00120\t¢\u0006\r\b}\u0012\t\b~\u0012\u0005\b\b(\u0080\u0001\u0012\u0014\u0012\u00120\t¢\u0006\r\b}\u0012\t\b~\u0012\u0005\b\b(\u0081\u0001\u0012\u0014\u0012\u00120\t¢\u0006\r\b}\u0012\t\b~\u0012\u0005\b\b(\u0082\u0001\u0012\u0014\u0012\u00120\t¢\u0006\r\b}\u0012\t\b~\u0012\u0005\b\b(\u0083\u0001\u0012\u0004\u0012\u0002Hz0|H\u0086\bø\u0001\u0002\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0006\b\u0084\u0001\u0010\u0085\u0001J\u008f\u0001\u0010y\u001a\u0002Hz\"\u0004\b��\u0010z2e\u0010{\u001aa\u0012\u0014\u0012\u00120\t¢\u0006\r\b}\u0012\t\b~\u0012\u0005\b\b(\u0080\u0001\u0012\u0014\u0012\u00120\t¢\u0006\r\b}\u0012\t\b~\u0012\u0005\b\b(\u0081\u0001\u0012\u0014\u0012\u00120\t¢\u0006\r\b}\u0012\t\b~\u0012\u0005\b\b(\u0082\u0001\u0012\u0014\u0012\u00120\t¢\u0006\r\b}\u0012\t\b~\u0012\u0005\b\b(\u0083\u0001\u0012\u0004\u0012\u0002Hz0\u0086\u0001H\u0086\bø\u0001\u0002\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0006\b\u0084\u0001\u0010\u0087\u0001Jy\u0010y\u001a\u0002Hz\"\u0004\b��\u0010z2O\u0010{\u001aK\u0012\u0014\u0012\u00120\t¢\u0006\r\b}\u0012\t\b~\u0012\u0005\b\b(\u0081\u0001\u0012\u0014\u0012\u00120\t¢\u0006\r\b}\u0012\t\b~\u0012\u0005\b\b(\u0082\u0001\u0012\u0014\u0012\u00120\t¢\u0006\r\b}\u0012\t\b~\u0012\u0005\b\b(\u0083\u0001\u0012\u0004\u0012\u0002Hz0\u0088\u0001H\u0086\bø\u0001\u0002\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0006\b\u0084\u0001\u0010\u0089\u0001Jc\u0010y\u001a\u0002Hz\"\u0004\b��\u0010z29\u0010{\u001a5\u0012\u0014\u0012\u00120\u0003¢\u0006\r\b}\u0012\t\b~\u0012\u0005\b\b(\u0082\u0001\u0012\u0014\u0012\u00120\t¢\u0006\r\b}\u0012\t\b~\u0012\u0005\b\b(\u0083\u0001\u0012\u0004\u0012\u0002Hz0\u008a\u0001H\u0086\bø\u0001\u0002\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0006\b\u0084\u0001\u0010\u008b\u0001J\u001e\u0010\u008c\u0001\u001a\u00020\u000f2\f\u0010\u008d\u0001\u001a\u00070Dj\u0003`\u008e\u0001¢\u0006\u0006\b\u008f\u0001\u0010\u0090\u0001J\u001e\u0010\u0091\u0001\u001a\u00020\t2\f\u0010\u008d\u0001\u001a\u00070Dj\u0003`\u008e\u0001¢\u0006\u0006\b\u0092\u0001\u0010\u0093\u0001J\u0011\u0010\u0094\u0001\u001a\u00030\u0095\u0001¢\u0006\u0006\b\u0096\u0001\u0010\u0097\u0001J\u001e\u0010\u0098\u0001\u001a\u00020\u00032\f\u0010\u008d\u0001\u001a\u00070Dj\u0003`\u008e\u0001¢\u0006\u0006\b\u0099\u0001\u0010\u009a\u0001J\u0011\u0010\u009b\u0001\u001a\u00020\u0003H\u0007¢\u0006\u0005\b\u009c\u0001\u0010\u0005J\u0011\u0010\u009d\u0001\u001a\u00020\u0003H\u0007¢\u0006\u0005\b\u009e\u0001\u0010\u0005J\u0013\u0010\u009f\u0001\u001a\u00030\u0095\u0001H\u0016¢\u0006\u0006\b \u0001\u0010\u0097\u0001J*\u0010\u009f\u0001\u001a\u00030\u0095\u00012\f\u0010\u008d\u0001\u001a\u00070Dj\u0003`\u008e\u00012\t\b\u0002\u0010¡\u0001\u001a\u00020\t¢\u0006\u0006\b \u0001\u0010¢\u0001J\u0018\u0010£\u0001\u001a\u00020��H\u0086\u0002ø\u0001��ø\u0001\u0001¢\u0006\u0005\b¤\u0001\u0010\u0005R\u0017\u0010\u0006\u001a\u00020��8Fø\u0001��ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005R\u001a\u0010\b\u001a\u00020\t8@X\u0081\u0004¢\u0006\f\u0012\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\u000f8FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0010\u0010\u000b\u001a\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u000f8FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0014\u0010\u000b\u001a\u0004\b\u0015\u0010\u0012R\u001a\u0010\u0016\u001a\u00020\u000f8FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0017\u0010\u000b\u001a\u0004\b\u0018\u0010\u0012R\u001a\u0010\u0019\u001a\u00020\u000f8FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u001a\u0010\u000b\u001a\u0004\b\u001b\u0010\u0012R\u001a\u0010\u001c\u001a\u00020\u000f8FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u001d\u0010\u000b\u001a\u0004\b\u001e\u0010\u0012R\u001a\u0010\u001f\u001a\u00020\u000f8FX\u0087\u0004¢\u0006\f\u0012\u0004\b \u0010\u000b\u001a\u0004\b!\u0010\u0012R\u001a\u0010\"\u001a\u00020\u000f8FX\u0087\u0004¢\u0006\f\u0012\u0004\b#\u0010\u000b\u001a\u0004\b$\u0010\u0012R\u001a\u0010%\u001a\u00020\u00038FX\u0087\u0004¢\u0006\f\u0012\u0004\b&\u0010\u000b\u001a\u0004\b'\u0010\u0005R\u001a\u0010(\u001a\u00020\u00038FX\u0087\u0004¢\u0006\f\u0012\u0004\b)\u0010\u000b\u001a\u0004\b*\u0010\u0005R\u001a\u0010+\u001a\u00020\u00038FX\u0087\u0004¢\u0006\f\u0012\u0004\b,\u0010\u000b\u001a\u0004\b-\u0010\u0005R\u001a\u0010.\u001a\u00020\u00038FX\u0087\u0004¢\u0006\f\u0012\u0004\b/\u0010\u000b\u001a\u0004\b0\u0010\u0005R\u001a\u00101\u001a\u00020\u00038FX\u0087\u0004¢\u0006\f\u0012\u0004\b2\u0010\u000b\u001a\u0004\b3\u0010\u0005R\u001a\u00104\u001a\u00020\u00038FX\u0087\u0004¢\u0006\f\u0012\u0004\b5\u0010\u000b\u001a\u0004\b6\u0010\u0005R\u001a\u00107\u001a\u00020\u00038FX\u0087\u0004¢\u0006\f\u0012\u0004\b8\u0010\u000b\u001a\u0004\b9\u0010\u0005R\u001a\u0010:\u001a\u00020\t8@X\u0081\u0004¢\u0006\f\u0012\u0004\b;\u0010\u000b\u001a\u0004\b<\u0010\rR\u001a\u0010=\u001a\u00020\t8@X\u0081\u0004¢\u0006\f\u0012\u0004\b>\u0010\u000b\u001a\u0004\b?\u0010\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n��R\u001a\u0010@\u001a\u00020\t8@X\u0081\u0004¢\u0006\f\u0012\u0004\bA\u0010\u000b\u001a\u0004\bB\u0010\rR\u0014\u0010C\u001a\u00020D8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bE\u0010FR\u0015\u0010G\u001a\u00020\t8Â\u0002X\u0082\u0004¢\u0006\u0006\u001a\u0004\bH\u0010\rR\u0014\u0010I\u001a\u00020\u00038BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bJ\u0010\u0005\u0088\u0001\u0002\u0092\u0001\u00020\u0003ø\u0001��\u0082\u0002\u000f\n\u0002\b\u0019\n\u0002\b!\n\u0005\b\u009920\u0001¨\u0006¦\u0001"}, d2 = {"Lkotlin/time/Duration;", "", "rawValue", "", "constructor-impl", "(J)J", "absoluteValue", "getAbsoluteValue-UwyO8pc", "hoursComponent", "", "getHoursComponent$annotations", "()V", "getHoursComponent-impl", "(J)I", "inDays", "", "getInDays$annotations", "getInDays-impl", "(J)D", "inHours", "getInHours$annotations", "getInHours-impl", "inMicroseconds", "getInMicroseconds$annotations", "getInMicroseconds-impl", "inMilliseconds", "getInMilliseconds$annotations", "getInMilliseconds-impl", "inMinutes", "getInMinutes$annotations", "getInMinutes-impl", "inNanoseconds", "getInNanoseconds$annotations", "getInNanoseconds-impl", "inSeconds", "getInSeconds$annotations", "getInSeconds-impl", "inWholeDays", "getInWholeDays$annotations", "getInWholeDays-impl", "inWholeHours", "getInWholeHours$annotations", "getInWholeHours-impl", "inWholeMicroseconds", "getInWholeMicroseconds$annotations", "getInWholeMicroseconds-impl", "inWholeMilliseconds", "getInWholeMilliseconds$annotations", "getInWholeMilliseconds-impl", "inWholeMinutes", "getInWholeMinutes$annotations", "getInWholeMinutes-impl", "inWholeNanoseconds", "getInWholeNanoseconds$annotations", "getInWholeNanoseconds-impl", "inWholeSeconds", "getInWholeSeconds$annotations", "getInWholeSeconds-impl", "minutesComponent", "getMinutesComponent$annotations", "getMinutesComponent-impl", "nanosecondsComponent", "getNanosecondsComponent$annotations", "getNanosecondsComponent-impl", "secondsComponent", "getSecondsComponent$annotations", "getSecondsComponent-impl", "storageUnit", "Ljava/util/concurrent/TimeUnit;", "getStorageUnit-impl", "(J)Ljava/util/concurrent/TimeUnit;", "unitDiscriminator", "getUnitDiscriminator-impl", "value", "getValue-impl", "addValuesMixedRanges", "thisMillis", "otherNanos", "addValuesMixedRanges-UwyO8pc", "(JJJ)J", "compareTo", "other", "compareTo-LRDsOJo", "(JJ)I", "div", "scale", "div-UwyO8pc", "(JD)J", "(JI)J", "div-LRDsOJo", "(JJ)D", "equals", "", "", "equals-impl", "(JLjava/lang/Object;)Z", IdentityNamingStrategy.HASH_CODE_KEY, "hashCode-impl", "isFinite", "isFinite-impl", "(J)Z", "isInMillis", "isInMillis-impl", "isInNanos", "isInNanos-impl", "isInfinite", "isInfinite-impl", "isNegative", "isNegative-impl", "isPositive", "isPositive-impl", "minus", "minus-LRDsOJo", "(JJ)J", "plus", "plus-LRDsOJo", "precision", "precision-impl", "(JD)I", "times", "times-UwyO8pc", "toComponents", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "action", "Lkotlin/Function5;", "Lkotlin/ParameterName;", "name", "days", "hours", "minutes", "seconds", "nanoseconds", "toComponents-impl", "(JLkotlin/jvm/functions/Function5;)Ljava/lang/Object;", "Lkotlin/Function4;", "(JLkotlin/jvm/functions/Function4;)Ljava/lang/Object;", "Lkotlin/Function3;", "(JLkotlin/jvm/functions/Function3;)Ljava/lang/Object;", "Lkotlin/Function2;", "(JLkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "toDouble", "unit", "Lkotlin/time/DurationUnit;", "toDouble-impl", "(JLjava/util/concurrent/TimeUnit;)D", "toInt", "toInt-impl", "(JLjava/util/concurrent/TimeUnit;)I", "toIsoString", "", "toIsoString-impl", "(J)Ljava/lang/String;", "toLong", "toLong-impl", "(JLjava/util/concurrent/TimeUnit;)J", "toLongMilliseconds", "toLongMilliseconds-impl", "toLongNanoseconds", "toLongNanoseconds-impl", "toString", "toString-impl", "decimals", "(JLjava/util/concurrent/TimeUnit;I)Ljava/lang/String;", "unaryMinus", "unaryMinus-UwyO8pc", "Companion", "kotlin-stdlib"})
@JvmInline
@ExperimentalTime
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-stdlib-1.5.21.jar:kotlin/time/Duration.class */
public final class Duration implements Comparable<Duration> {
    private final long rawValue;

    @NotNull
    public static final Companion Companion = new Companion(null);
    private static final long ZERO = m4104constructorimpl(0);
    private static final long INFINITE = DurationKt.durationOfMillis(DurationKt.MAX_MILLIS);
    private static final long NEG_INFINITE = DurationKt.durationOfMillis(-4611686018427387903L);

    /* renamed from: compareTo-LRDsOJo, reason: not valid java name */
    public int m4051compareToLRDsOJo(long j) {
        return m4071compareToLRDsOJo(this.rawValue, j);
    }

    @PublishedApi
    public static /* synthetic */ void getHoursComponent$annotations() {
    }

    @PublishedApi
    public static /* synthetic */ void getMinutesComponent$annotations() {
    }

    @PublishedApi
    public static /* synthetic */ void getSecondsComponent$annotations() {
    }

    @PublishedApi
    public static /* synthetic */ void getNanosecondsComponent$annotations() {
    }

    @Deprecated(message = "Use inWholeDays property instead or convert toDouble(DAYS) if a double value is required.", replaceWith = @ReplaceWith(imports = {}, expression = "toDouble(DurationUnit.DAYS)"))
    public static /* synthetic */ void getInDays$annotations() {
    }

    @Deprecated(message = "Use inWholeHours property instead or convert toDouble(HOURS) if a double value is required.", replaceWith = @ReplaceWith(imports = {}, expression = "toDouble(DurationUnit.HOURS)"))
    public static /* synthetic */ void getInHours$annotations() {
    }

    @Deprecated(message = "Use inWholeMinutes property instead or convert toDouble(MINUTES) if a double value is required.", replaceWith = @ReplaceWith(imports = {}, expression = "toDouble(DurationUnit.MINUTES)"))
    public static /* synthetic */ void getInMinutes$annotations() {
    }

    @Deprecated(message = "Use inWholeSeconds property instead or convert toDouble(SECONDS) if a double value is required.", replaceWith = @ReplaceWith(imports = {}, expression = "toDouble(DurationUnit.SECONDS)"))
    public static /* synthetic */ void getInSeconds$annotations() {
    }

    @Deprecated(message = "Use inWholeMilliseconds property instead or convert toDouble(MILLISECONDS) if a double value is required.", replaceWith = @ReplaceWith(imports = {}, expression = "toDouble(DurationUnit.MILLISECONDS)"))
    public static /* synthetic */ void getInMilliseconds$annotations() {
    }

    @Deprecated(message = "Use inWholeMicroseconds property instead or convert toDouble(MICROSECONDS) if a double value is required.", replaceWith = @ReplaceWith(imports = {}, expression = "toDouble(DurationUnit.MICROSECONDS)"))
    public static /* synthetic */ void getInMicroseconds$annotations() {
    }

    @Deprecated(message = "Use inWholeNanoseconds property instead or convert toDouble(NANOSECONDS) if a double value is required.", replaceWith = @ReplaceWith(imports = {}, expression = "toDouble(DurationUnit.NANOSECONDS)"))
    public static /* synthetic */ void getInNanoseconds$annotations() {
    }

    @SinceKotlin(version = "1.5")
    public static /* synthetic */ void getInWholeDays$annotations() {
    }

    @SinceKotlin(version = "1.5")
    public static /* synthetic */ void getInWholeHours$annotations() {
    }

    @SinceKotlin(version = "1.5")
    public static /* synthetic */ void getInWholeMinutes$annotations() {
    }

    @SinceKotlin(version = "1.5")
    public static /* synthetic */ void getInWholeSeconds$annotations() {
    }

    @SinceKotlin(version = "1.5")
    public static /* synthetic */ void getInWholeMilliseconds$annotations() {
    }

    @SinceKotlin(version = "1.5")
    public static /* synthetic */ void getInWholeMicroseconds$annotations() {
    }

    @SinceKotlin(version = "1.5")
    public static /* synthetic */ void getInWholeNanoseconds$annotations() {
    }

    @NotNull
    public String toString() {
        return m4099toStringimpl(this.rawValue);
    }

    /* renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ Duration m4105boximpl(long v) {
        return new Duration(v);
    }

    /* renamed from: hashCode-impl, reason: not valid java name */
    public static int m4106hashCodeimpl(long j) {
        return (int) (j ^ (j >>> 32));
    }

    /* renamed from: equals-impl, reason: not valid java name */
    public static boolean m4107equalsimpl(long j, Object obj) {
        return (obj instanceof Duration) && j == ((Duration) obj).m4109unboximpl();
    }

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m4108equalsimpl0(long p1, long p2) {
        return p1 == p2;
    }

    /* renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ long m4109unboximpl() {
        return this.rawValue;
    }

    public int hashCode() {
        return m4106hashCodeimpl(this.rawValue);
    }

    public boolean equals(Object obj) {
        return m4107equalsimpl(this.rawValue, obj);
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(Duration duration) {
        return m4051compareToLRDsOJo(duration.m4109unboximpl());
    }

    private /* synthetic */ Duration(long rawValue) {
        this.rawValue = rawValue;
    }

    /* renamed from: getValue-impl, reason: not valid java name */
    private static final long m4052getValueimpl(long $this) {
        return $this >> 1;
    }

    /* renamed from: getUnitDiscriminator-impl, reason: not valid java name */
    private static final int m4053getUnitDiscriminatorimpl(long $this) {
        return ((int) $this) & 1;
    }

    /* renamed from: isInNanos-impl, reason: not valid java name */
    private static final boolean m4054isInNanosimpl(long $this) {
        return (((int) $this) & 1) == 0;
    }

    /* renamed from: isInMillis-impl, reason: not valid java name */
    private static final boolean m4055isInMillisimpl(long $this) {
        return (((int) $this) & 1) == 1;
    }

    /* renamed from: getStorageUnit-impl, reason: not valid java name */
    private static final TimeUnit m4056getStorageUnitimpl(long $this) {
        return m4054isInNanosimpl($this) ? TimeUnit.NANOSECONDS : TimeUnit.MILLISECONDS;
    }

    /* renamed from: constructor-impl, reason: not valid java name */
    public static long m4104constructorimpl(long rawValue) {
        if (m4054isInNanosimpl(rawValue)) {
            long jM4052getValueimpl = m4052getValueimpl(rawValue);
            if (-4611686018426999999L > jM4052getValueimpl || DurationKt.MAX_NANOS < jM4052getValueimpl) {
                throw new AssertionError(m4052getValueimpl(rawValue) + " ns is out of nanoseconds range");
            }
        } else {
            long jM4052getValueimpl2 = m4052getValueimpl(rawValue);
            if (-4611686018427387903L > jM4052getValueimpl2 || DurationKt.MAX_MILLIS < jM4052getValueimpl2) {
                throw new AssertionError(m4052getValueimpl(rawValue) + " ms is out of milliseconds range");
            }
            long jM4052getValueimpl3 = m4052getValueimpl(rawValue);
            if (-4611686018426L <= jM4052getValueimpl3 && 4611686018426L >= jM4052getValueimpl3) {
                throw new AssertionError(m4052getValueimpl(rawValue) + " ms is denormalized");
            }
        }
        return rawValue;
    }

    /* compiled from: Duration.kt */
    @Metadata(mv = {1, 5, 1}, k = 1, d1 = {"��6\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n��\n\u0002\u0010\t\n\u0002\b\u000e\b\u0086\u0003\u0018��2\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J&\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\n\u0010\u000f\u001a\u00060\u0010j\u0002`\u00112\n\u0010\u0012\u001a\u00060\u0010j\u0002`\u0011J\u001d\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\rH\u0007ø\u0001��ø\u0001\u0001¢\u0006\u0004\b\u0014\u0010\u0015J\u001d\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0016H\u0007ø\u0001��ø\u0001\u0001¢\u0006\u0004\b\u0014\u0010\u0017J\u001d\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0018H\u0007ø\u0001��ø\u0001\u0001¢\u0006\u0004\b\u0014\u0010\u0019J\u001d\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\rH\u0007ø\u0001��ø\u0001\u0001¢\u0006\u0004\b\u001b\u0010\u0015J\u001d\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0016H\u0007ø\u0001��ø\u0001\u0001¢\u0006\u0004\b\u001b\u0010\u0017J\u001d\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0018H\u0007ø\u0001��ø\u0001\u0001¢\u0006\u0004\b\u001b\u0010\u0019J\u001d\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\rH\u0007ø\u0001��ø\u0001\u0001¢\u0006\u0004\b\u001d\u0010\u0015J\u001d\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0016H\u0007ø\u0001��ø\u0001\u0001¢\u0006\u0004\b\u001d\u0010\u0017J\u001d\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0018H\u0007ø\u0001��ø\u0001\u0001¢\u0006\u0004\b\u001d\u0010\u0019J\u001d\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\rH\u0007ø\u0001��ø\u0001\u0001¢\u0006\u0004\b\u001f\u0010\u0015J\u001d\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0016H\u0007ø\u0001��ø\u0001\u0001¢\u0006\u0004\b\u001f\u0010\u0017J\u001d\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0018H\u0007ø\u0001��ø\u0001\u0001¢\u0006\u0004\b\u001f\u0010\u0019J\u001d\u0010 \u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\rH\u0007ø\u0001��ø\u0001\u0001¢\u0006\u0004\b!\u0010\u0015J\u001d\u0010 \u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0016H\u0007ø\u0001��ø\u0001\u0001¢\u0006\u0004\b!\u0010\u0017J\u001d\u0010 \u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0018H\u0007ø\u0001��ø\u0001\u0001¢\u0006\u0004\b!\u0010\u0019J\u001d\u0010\"\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\rH\u0007ø\u0001��ø\u0001\u0001¢\u0006\u0004\b#\u0010\u0015J\u001d\u0010\"\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0016H\u0007ø\u0001��ø\u0001\u0001¢\u0006\u0004\b#\u0010\u0017J\u001d\u0010\"\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0018H\u0007ø\u0001��ø\u0001\u0001¢\u0006\u0004\b#\u0010\u0019J\u001d\u0010$\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\rH\u0007ø\u0001��ø\u0001\u0001¢\u0006\u0004\b%\u0010\u0015J\u001d\u0010$\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0016H\u0007ø\u0001��ø\u0001\u0001¢\u0006\u0004\b%\u0010\u0017J\u001d\u0010$\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0018H\u0007ø\u0001��ø\u0001\u0001¢\u0006\u0004\b%\u0010\u0019R\u0019\u0010\u0003\u001a\u00020\u0004ø\u0001��ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006R\u001c\u0010\b\u001a\u00020\u0004X\u0080\u0004ø\u0001��ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\t\u0010\u0006R\u0019\u0010\n\u001a\u00020\u0004ø\u0001��ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u000b\u0010\u0006\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006&"}, d2 = {"Lkotlin/time/Duration$Companion;", "", "()V", "INFINITE", "Lkotlin/time/Duration;", "getINFINITE-UwyO8pc", "()J", OperatorName.SET_LINE_CAPSTYLE, "NEG_INFINITE", "getNEG_INFINITE-UwyO8pc$kotlin_stdlib", "ZERO", "getZERO-UwyO8pc", "convert", "", "value", "sourceUnit", "Ljava/util/concurrent/TimeUnit;", "Lkotlin/time/DurationUnit;", "targetUnit", "days", "days-UwyO8pc", "(D)J", "", "(I)J", "", "(J)J", "hours", "hours-UwyO8pc", "microseconds", "microseconds-UwyO8pc", "milliseconds", "milliseconds-UwyO8pc", "minutes", "minutes-UwyO8pc", "nanoseconds", "nanoseconds-UwyO8pc", "seconds", "seconds-UwyO8pc", "kotlin-stdlib"})
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-stdlib-1.5.21.jar:kotlin/time/Duration$Companion.class */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        /* renamed from: getZERO-UwyO8pc, reason: not valid java name */
        public final long m4114getZEROUwyO8pc() {
            return Duration.ZERO;
        }

        /* renamed from: getINFINITE-UwyO8pc, reason: not valid java name */
        public final long m4115getINFINITEUwyO8pc() {
            return Duration.INFINITE;
        }

        /* renamed from: getNEG_INFINITE-UwyO8pc$kotlin_stdlib, reason: not valid java name */
        public final long m4116getNEG_INFINITEUwyO8pc$kotlin_stdlib() {
            return Duration.NEG_INFINITE;
        }

        public final double convert(double value, @NotNull TimeUnit sourceUnit, @NotNull TimeUnit targetUnit) {
            Intrinsics.checkNotNullParameter(sourceUnit, "sourceUnit");
            Intrinsics.checkNotNullParameter(targetUnit, "targetUnit");
            return DurationUnitKt.convertDurationUnit(value, sourceUnit, targetUnit);
        }

        @SinceKotlin(version = "1.5")
        /* renamed from: nanoseconds-UwyO8pc, reason: not valid java name */
        public final long m4117nanosecondsUwyO8pc(int value) {
            return DurationKt.toDuration(value, TimeUnit.NANOSECONDS);
        }

        @SinceKotlin(version = "1.5")
        /* renamed from: nanoseconds-UwyO8pc, reason: not valid java name */
        public final long m4118nanosecondsUwyO8pc(long value) {
            return DurationKt.toDuration(value, TimeUnit.NANOSECONDS);
        }

        @SinceKotlin(version = "1.5")
        /* renamed from: nanoseconds-UwyO8pc, reason: not valid java name */
        public final long m4119nanosecondsUwyO8pc(double value) {
            return DurationKt.toDuration(value, TimeUnit.NANOSECONDS);
        }

        @SinceKotlin(version = "1.5")
        /* renamed from: microseconds-UwyO8pc, reason: not valid java name */
        public final long m4120microsecondsUwyO8pc(int value) {
            return DurationKt.toDuration(value, TimeUnit.MICROSECONDS);
        }

        @SinceKotlin(version = "1.5")
        /* renamed from: microseconds-UwyO8pc, reason: not valid java name */
        public final long m4121microsecondsUwyO8pc(long value) {
            return DurationKt.toDuration(value, TimeUnit.MICROSECONDS);
        }

        @SinceKotlin(version = "1.5")
        /* renamed from: microseconds-UwyO8pc, reason: not valid java name */
        public final long m4122microsecondsUwyO8pc(double value) {
            return DurationKt.toDuration(value, TimeUnit.MICROSECONDS);
        }

        @SinceKotlin(version = "1.5")
        /* renamed from: milliseconds-UwyO8pc, reason: not valid java name */
        public final long m4123millisecondsUwyO8pc(int value) {
            return DurationKt.toDuration(value, TimeUnit.MILLISECONDS);
        }

        @SinceKotlin(version = "1.5")
        /* renamed from: milliseconds-UwyO8pc, reason: not valid java name */
        public final long m4124millisecondsUwyO8pc(long value) {
            return DurationKt.toDuration(value, TimeUnit.MILLISECONDS);
        }

        @SinceKotlin(version = "1.5")
        /* renamed from: milliseconds-UwyO8pc, reason: not valid java name */
        public final long m4125millisecondsUwyO8pc(double value) {
            return DurationKt.toDuration(value, TimeUnit.MILLISECONDS);
        }

        @SinceKotlin(version = "1.5")
        /* renamed from: seconds-UwyO8pc, reason: not valid java name */
        public final long m4126secondsUwyO8pc(int value) {
            return DurationKt.toDuration(value, TimeUnit.SECONDS);
        }

        @SinceKotlin(version = "1.5")
        /* renamed from: seconds-UwyO8pc, reason: not valid java name */
        public final long m4127secondsUwyO8pc(long value) {
            return DurationKt.toDuration(value, TimeUnit.SECONDS);
        }

        @SinceKotlin(version = "1.5")
        /* renamed from: seconds-UwyO8pc, reason: not valid java name */
        public final long m4128secondsUwyO8pc(double value) {
            return DurationKt.toDuration(value, TimeUnit.SECONDS);
        }

        @SinceKotlin(version = "1.5")
        /* renamed from: minutes-UwyO8pc, reason: not valid java name */
        public final long m4129minutesUwyO8pc(int value) {
            return DurationKt.toDuration(value, TimeUnit.MINUTES);
        }

        @SinceKotlin(version = "1.5")
        /* renamed from: minutes-UwyO8pc, reason: not valid java name */
        public final long m4130minutesUwyO8pc(long value) {
            return DurationKt.toDuration(value, TimeUnit.MINUTES);
        }

        @SinceKotlin(version = "1.5")
        /* renamed from: minutes-UwyO8pc, reason: not valid java name */
        public final long m4131minutesUwyO8pc(double value) {
            return DurationKt.toDuration(value, TimeUnit.MINUTES);
        }

        @SinceKotlin(version = "1.5")
        /* renamed from: hours-UwyO8pc, reason: not valid java name */
        public final long m4132hoursUwyO8pc(int value) {
            return DurationKt.toDuration(value, TimeUnit.HOURS);
        }

        @SinceKotlin(version = "1.5")
        /* renamed from: hours-UwyO8pc, reason: not valid java name */
        public final long m4133hoursUwyO8pc(long value) {
            return DurationKt.toDuration(value, TimeUnit.HOURS);
        }

        @SinceKotlin(version = "1.5")
        /* renamed from: hours-UwyO8pc, reason: not valid java name */
        public final long m4134hoursUwyO8pc(double value) {
            return DurationKt.toDuration(value, TimeUnit.HOURS);
        }

        @SinceKotlin(version = "1.5")
        /* renamed from: days-UwyO8pc, reason: not valid java name */
        public final long m4135daysUwyO8pc(int value) {
            return DurationKt.toDuration(value, TimeUnit.DAYS);
        }

        @SinceKotlin(version = "1.5")
        /* renamed from: days-UwyO8pc, reason: not valid java name */
        public final long m4136daysUwyO8pc(long value) {
            return DurationKt.toDuration(value, TimeUnit.DAYS);
        }

        @SinceKotlin(version = "1.5")
        /* renamed from: days-UwyO8pc, reason: not valid java name */
        public final long m4137daysUwyO8pc(double value) {
            return DurationKt.toDuration(value, TimeUnit.DAYS);
        }
    }

    /* renamed from: unaryMinus-UwyO8pc, reason: not valid java name */
    public static final long m4057unaryMinusUwyO8pc(long $this) {
        return DurationKt.durationOf(-m4052getValueimpl($this), ((int) $this) & 1);
    }

    /* renamed from: plus-LRDsOJo, reason: not valid java name */
    public static final long m4058plusLRDsOJo(long $this, long other) {
        if (m4068isInfiniteimpl($this)) {
            if (m4069isFiniteimpl(other) || ($this ^ other) >= 0) {
                return $this;
            }
            throw new IllegalArgumentException("Summing infinite durations of different signs yields an undefined result.");
        }
        if (m4068isInfiniteimpl(other)) {
            return other;
        }
        if ((((int) $this) & 1) == (((int) other) & 1)) {
            long result = m4052getValueimpl($this) + m4052getValueimpl(other);
            return m4054isInNanosimpl($this) ? DurationKt.durationOfNanosNormalized(result) : DurationKt.durationOfMillisNormalized(result);
        }
        if (m4055isInMillisimpl($this)) {
            return m4059addValuesMixedRangesUwyO8pc($this, m4052getValueimpl($this), m4052getValueimpl(other));
        }
        return m4059addValuesMixedRangesUwyO8pc($this, m4052getValueimpl(other), m4052getValueimpl($this));
    }

    /* renamed from: addValuesMixedRanges-UwyO8pc, reason: not valid java name */
    private static final long m4059addValuesMixedRangesUwyO8pc(long $this, long thisMillis, long otherNanos) {
        long otherMillis = DurationKt.nanosToMillis(otherNanos);
        long resultMillis = thisMillis + otherMillis;
        if (-4611686018426L > resultMillis || 4611686018426L < resultMillis) {
            return DurationKt.durationOfMillis(RangesKt.coerceIn(resultMillis, -4611686018427387903L, DurationKt.MAX_MILLIS));
        }
        long otherNanoRemainder = otherNanos - DurationKt.millisToNanos(otherMillis);
        return DurationKt.durationOfNanos(DurationKt.millisToNanos(resultMillis) + otherNanoRemainder);
    }

    /* renamed from: minus-LRDsOJo, reason: not valid java name */
    public static final long m4060minusLRDsOJo(long $this, long other) {
        return m4058plusLRDsOJo($this, m4057unaryMinusUwyO8pc(other));
    }

    /* renamed from: times-UwyO8pc, reason: not valid java name */
    public static final long m4061timesUwyO8pc(long $this, int scale) {
        if (m4068isInfiniteimpl($this)) {
            if (scale == 0) {
                throw new IllegalArgumentException("Multiplying infinite duration by zero yields an undefined result.");
            }
            return scale > 0 ? $this : m4057unaryMinusUwyO8pc($this);
        }
        if (scale == 0) {
            return ZERO;
        }
        long value = m4052getValueimpl($this);
        long result = value * scale;
        if (!m4054isInNanosimpl($this)) {
            if (result / scale == value) {
                return DurationKt.durationOfMillis(RangesKt.coerceIn(result, new LongRange(-4611686018427387903L, DurationKt.MAX_MILLIS)));
            }
            return MathKt.getSign(value) * MathKt.getSign(scale) > 0 ? INFINITE : NEG_INFINITE;
        }
        if (-2147483647L <= value && 2147483647L >= value) {
            return DurationKt.durationOfNanos(result);
        }
        if (result / scale == value) {
            return DurationKt.durationOfNanosNormalized(result);
        }
        long millis = DurationKt.nanosToMillis(value);
        long remNanos = value - DurationKt.millisToNanos(millis);
        long resultMillis = millis * scale;
        long totalMillis = resultMillis + DurationKt.nanosToMillis(remNanos * scale);
        if (resultMillis / scale != millis || (totalMillis ^ resultMillis) < 0) {
            return MathKt.getSign(value) * MathKt.getSign(scale) > 0 ? INFINITE : NEG_INFINITE;
        }
        return DurationKt.durationOfMillis(RangesKt.coerceIn(totalMillis, new LongRange(-4611686018427387903L, DurationKt.MAX_MILLIS)));
    }

    /* renamed from: times-UwyO8pc, reason: not valid java name */
    public static final long m4062timesUwyO8pc(long $this, double scale) {
        int intScale = MathKt.roundToInt(scale);
        if (intScale == scale) {
            return m4061timesUwyO8pc($this, intScale);
        }
        TimeUnit unit = m4056getStorageUnitimpl($this);
        double result = m4080toDoubleimpl($this, unit) * scale;
        return DurationKt.toDuration(result, unit);
    }

    /* renamed from: div-UwyO8pc, reason: not valid java name */
    public static final long m4063divUwyO8pc(long $this, int scale) {
        if (scale == 0) {
            if (m4067isPositiveimpl($this)) {
                return INFINITE;
            }
            if (m4066isNegativeimpl($this)) {
                return NEG_INFINITE;
            }
            throw new IllegalArgumentException("Dividing zero duration by zero yields an undefined result.");
        }
        if (m4054isInNanosimpl($this)) {
            return DurationKt.durationOfNanos(m4052getValueimpl($this) / scale);
        }
        if (m4068isInfiniteimpl($this)) {
            return m4061timesUwyO8pc($this, MathKt.getSign(scale));
        }
        long result = m4052getValueimpl($this) / scale;
        if (-4611686018426L <= result && 4611686018426L >= result) {
            long rem = DurationKt.millisToNanos(m4052getValueimpl($this) - (result * scale)) / scale;
            return DurationKt.durationOfNanos(DurationKt.millisToNanos(result) + rem);
        }
        return DurationKt.durationOfMillis(result);
    }

    /* renamed from: div-UwyO8pc, reason: not valid java name */
    public static final long m4064divUwyO8pc(long $this, double scale) {
        int intScale = MathKt.roundToInt(scale);
        if (intScale == scale && intScale != 0) {
            return m4063divUwyO8pc($this, intScale);
        }
        TimeUnit unit = m4056getStorageUnitimpl($this);
        double result = m4080toDoubleimpl($this, unit) / scale;
        return DurationKt.toDuration(result, unit);
    }

    /* renamed from: div-LRDsOJo, reason: not valid java name */
    public static final double m4065divLRDsOJo(long $this, long other) {
        TimeUnit coarserUnit = (TimeUnit) ComparisonsKt.maxOf(m4056getStorageUnitimpl($this), m4056getStorageUnitimpl(other));
        return m4080toDoubleimpl($this, coarserUnit) / m4080toDoubleimpl(other, coarserUnit);
    }

    /* renamed from: isNegative-impl, reason: not valid java name */
    public static final boolean m4066isNegativeimpl(long $this) {
        return $this < 0;
    }

    /* renamed from: isPositive-impl, reason: not valid java name */
    public static final boolean m4067isPositiveimpl(long $this) {
        return $this > 0;
    }

    /* renamed from: isInfinite-impl, reason: not valid java name */
    public static final boolean m4068isInfiniteimpl(long $this) {
        return $this == INFINITE || $this == NEG_INFINITE;
    }

    /* renamed from: isFinite-impl, reason: not valid java name */
    public static final boolean m4069isFiniteimpl(long $this) {
        return !m4068isInfiniteimpl($this);
    }

    /* renamed from: getAbsoluteValue-UwyO8pc, reason: not valid java name */
    public static final long m4070getAbsoluteValueUwyO8pc(long $this) {
        return m4066isNegativeimpl($this) ? m4057unaryMinusUwyO8pc($this) : $this;
    }

    /* renamed from: compareTo-LRDsOJo, reason: not valid java name */
    public static int m4071compareToLRDsOJo(long $this, long other) {
        long compareBits = $this ^ other;
        if (compareBits < 0 || (((int) compareBits) & 1) == 0) {
            return ($this > other ? 1 : ($this == other ? 0 : -1));
        }
        int r = (((int) $this) & 1) - (((int) other) & 1);
        return m4066isNegativeimpl($this) ? -r : r;
    }

    /* renamed from: toComponents-impl, reason: not valid java name */
    public static final <T> T m4072toComponentsimpl(long $this, @NotNull Function5<? super Integer, ? super Integer, ? super Integer, ? super Integer, ? super Integer, ? extends T> action) {
        Intrinsics.checkNotNullParameter(action, "action");
        return action.invoke(Integer.valueOf(m4082toIntimpl($this, TimeUnit.DAYS)), Integer.valueOf(m4076getHoursComponentimpl($this)), Integer.valueOf(m4077getMinutesComponentimpl($this)), Integer.valueOf(m4078getSecondsComponentimpl($this)), Integer.valueOf(m4079getNanosecondsComponentimpl($this)));
    }

    /* renamed from: toComponents-impl, reason: not valid java name */
    public static final <T> T m4073toComponentsimpl(long $this, @NotNull Function4<? super Integer, ? super Integer, ? super Integer, ? super Integer, ? extends T> action) {
        Intrinsics.checkNotNullParameter(action, "action");
        return action.invoke(Integer.valueOf(m4082toIntimpl($this, TimeUnit.HOURS)), Integer.valueOf(m4077getMinutesComponentimpl($this)), Integer.valueOf(m4078getSecondsComponentimpl($this)), Integer.valueOf(m4079getNanosecondsComponentimpl($this)));
    }

    /* renamed from: toComponents-impl, reason: not valid java name */
    public static final <T> T m4074toComponentsimpl(long $this, @NotNull Function3<? super Integer, ? super Integer, ? super Integer, ? extends T> action) {
        Intrinsics.checkNotNullParameter(action, "action");
        return action.invoke(Integer.valueOf(m4082toIntimpl($this, TimeUnit.MINUTES)), Integer.valueOf(m4078getSecondsComponentimpl($this)), Integer.valueOf(m4079getNanosecondsComponentimpl($this)));
    }

    /* renamed from: toComponents-impl, reason: not valid java name */
    public static final <T> T m4075toComponentsimpl(long $this, @NotNull Function2<? super Long, ? super Integer, ? extends T> action) {
        Intrinsics.checkNotNullParameter(action, "action");
        return action.invoke(Long.valueOf(m4093getInWholeSecondsimpl($this)), Integer.valueOf(m4079getNanosecondsComponentimpl($this)));
    }

    /* renamed from: getHoursComponent-impl, reason: not valid java name */
    public static final int m4076getHoursComponentimpl(long $this) {
        if (m4068isInfiniteimpl($this)) {
            return 0;
        }
        return (int) (m4091getInWholeHoursimpl($this) % 24);
    }

    /* renamed from: getMinutesComponent-impl, reason: not valid java name */
    public static final int m4077getMinutesComponentimpl(long $this) {
        if (m4068isInfiniteimpl($this)) {
            return 0;
        }
        return (int) (m4092getInWholeMinutesimpl($this) % 60);
    }

    /* renamed from: getSecondsComponent-impl, reason: not valid java name */
    public static final int m4078getSecondsComponentimpl(long $this) {
        if (m4068isInfiniteimpl($this)) {
            return 0;
        }
        return (int) (m4093getInWholeSecondsimpl($this) % 60);
    }

    /* renamed from: getNanosecondsComponent-impl, reason: not valid java name */
    public static final int m4079getNanosecondsComponentimpl(long $this) {
        if (m4068isInfiniteimpl($this)) {
            return 0;
        }
        if (m4055isInMillisimpl($this)) {
            return (int) DurationKt.millisToNanos(m4052getValueimpl($this) % 1000);
        }
        return (int) (m4052getValueimpl($this) % Http2Connection.DEGRADED_PONG_TIMEOUT_NS);
    }

    /* renamed from: toDouble-impl, reason: not valid java name */
    public static final double m4080toDoubleimpl(long $this, @NotNull TimeUnit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        if ($this == INFINITE) {
            return Double.POSITIVE_INFINITY;
        }
        if ($this == NEG_INFINITE) {
            return Double.NEGATIVE_INFINITY;
        }
        return DurationUnitKt.convertDurationUnit(m4052getValueimpl($this), m4056getStorageUnitimpl($this), unit);
    }

    /* renamed from: toLong-impl, reason: not valid java name */
    public static final long m4081toLongimpl(long $this, @NotNull TimeUnit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        if ($this == INFINITE) {
            return Long.MAX_VALUE;
        }
        if ($this == NEG_INFINITE) {
            return Long.MIN_VALUE;
        }
        return DurationUnitKt.convertDurationUnit(m4052getValueimpl($this), m4056getStorageUnitimpl($this), unit);
    }

    /* renamed from: toInt-impl, reason: not valid java name */
    public static final int m4082toIntimpl(long $this, @NotNull TimeUnit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        return (int) RangesKt.coerceIn(m4081toLongimpl($this, unit), Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    /* renamed from: getInDays-impl, reason: not valid java name */
    public static final double m4083getInDaysimpl(long $this) {
        return m4080toDoubleimpl($this, TimeUnit.DAYS);
    }

    /* renamed from: getInHours-impl, reason: not valid java name */
    public static final double m4084getInHoursimpl(long $this) {
        return m4080toDoubleimpl($this, TimeUnit.HOURS);
    }

    /* renamed from: getInMinutes-impl, reason: not valid java name */
    public static final double m4085getInMinutesimpl(long $this) {
        return m4080toDoubleimpl($this, TimeUnit.MINUTES);
    }

    /* renamed from: getInSeconds-impl, reason: not valid java name */
    public static final double m4086getInSecondsimpl(long $this) {
        return m4080toDoubleimpl($this, TimeUnit.SECONDS);
    }

    /* renamed from: getInMilliseconds-impl, reason: not valid java name */
    public static final double m4087getInMillisecondsimpl(long $this) {
        return m4080toDoubleimpl($this, TimeUnit.MILLISECONDS);
    }

    /* renamed from: getInMicroseconds-impl, reason: not valid java name */
    public static final double m4088getInMicrosecondsimpl(long $this) {
        return m4080toDoubleimpl($this, TimeUnit.MICROSECONDS);
    }

    /* renamed from: getInNanoseconds-impl, reason: not valid java name */
    public static final double m4089getInNanosecondsimpl(long $this) {
        return m4080toDoubleimpl($this, TimeUnit.NANOSECONDS);
    }

    /* renamed from: getInWholeDays-impl, reason: not valid java name */
    public static final long m4090getInWholeDaysimpl(long $this) {
        return m4081toLongimpl($this, TimeUnit.DAYS);
    }

    /* renamed from: getInWholeHours-impl, reason: not valid java name */
    public static final long m4091getInWholeHoursimpl(long $this) {
        return m4081toLongimpl($this, TimeUnit.HOURS);
    }

    /* renamed from: getInWholeMinutes-impl, reason: not valid java name */
    public static final long m4092getInWholeMinutesimpl(long $this) {
        return m4081toLongimpl($this, TimeUnit.MINUTES);
    }

    /* renamed from: getInWholeSeconds-impl, reason: not valid java name */
    public static final long m4093getInWholeSecondsimpl(long $this) {
        return m4081toLongimpl($this, TimeUnit.SECONDS);
    }

    /* renamed from: getInWholeMilliseconds-impl, reason: not valid java name */
    public static final long m4094getInWholeMillisecondsimpl(long $this) {
        return (m4055isInMillisimpl($this) && m4069isFiniteimpl($this)) ? m4052getValueimpl($this) : m4081toLongimpl($this, TimeUnit.MILLISECONDS);
    }

    /* renamed from: getInWholeMicroseconds-impl, reason: not valid java name */
    public static final long m4095getInWholeMicrosecondsimpl(long $this) {
        return m4081toLongimpl($this, TimeUnit.MICROSECONDS);
    }

    /* renamed from: getInWholeNanoseconds-impl, reason: not valid java name */
    public static final long m4096getInWholeNanosecondsimpl(long $this) {
        long value = m4052getValueimpl($this);
        if (m4054isInNanosimpl($this)) {
            return value;
        }
        if (value > 9223372036854L) {
            return Long.MAX_VALUE;
        }
        if (value < -9223372036854L) {
            return Long.MIN_VALUE;
        }
        return DurationKt.millisToNanos(value);
    }

    @Deprecated(message = "Use inWholeNanoseconds property instead.", replaceWith = @ReplaceWith(imports = {}, expression = "this.inWholeNanoseconds"))
    /* renamed from: toLongNanoseconds-impl, reason: not valid java name */
    public static final long m4097toLongNanosecondsimpl(long $this) {
        return m4096getInWholeNanosecondsimpl($this);
    }

    @Deprecated(message = "Use inWholeMilliseconds property instead.", replaceWith = @ReplaceWith(imports = {}, expression = "this.inWholeMilliseconds"))
    /* renamed from: toLongMilliseconds-impl, reason: not valid java name */
    public static final long m4098toLongMillisecondsimpl(long $this) {
        return m4094getInWholeMillisecondsimpl($this);
    }

    @NotNull
    /* renamed from: toString-impl, reason: not valid java name */
    public static String m4099toStringimpl(long $this) {
        TimeUnit timeUnit;
        String upToDecimals;
        if ($this == 0) {
            return "0s";
        }
        if ($this == INFINITE) {
            return "Infinity";
        }
        if ($this == NEG_INFINITE) {
            return "-Infinity";
        }
        double absNs = m4080toDoubleimpl(m4070getAbsoluteValueUwyO8pc($this), TimeUnit.NANOSECONDS);
        boolean scientific = false;
        int maxDecimals = 0;
        if (absNs < 1.0E-6d) {
            scientific = true;
            timeUnit = TimeUnit.SECONDS;
        } else if (absNs < 1) {
            maxDecimals = 7;
            timeUnit = TimeUnit.NANOSECONDS;
        } else if (absNs < 1000.0d) {
            timeUnit = TimeUnit.NANOSECONDS;
        } else if (absNs < 1000000.0d) {
            timeUnit = TimeUnit.MICROSECONDS;
        } else if (absNs < 1.0E9d) {
            timeUnit = TimeUnit.MILLISECONDS;
        } else if (absNs < 1.0E12d) {
            timeUnit = TimeUnit.SECONDS;
        } else if (absNs < 6.0E13d) {
            timeUnit = TimeUnit.MINUTES;
        } else if (absNs < 3.6E15d) {
            timeUnit = TimeUnit.HOURS;
        } else if (absNs < 8.64E20d) {
            timeUnit = TimeUnit.DAYS;
        } else {
            scientific = true;
            timeUnit = TimeUnit.DAYS;
        }
        TimeUnit unit = timeUnit;
        double value = m4080toDoubleimpl($this, unit);
        StringBuilder sb = new StringBuilder();
        if (scientific) {
            upToDecimals = FormatToDecimalsKt.formatScientific(value);
        } else {
            upToDecimals = maxDecimals > 0 ? FormatToDecimalsKt.formatUpToDecimals(value, maxDecimals) : FormatToDecimalsKt.formatToExactDecimals(value, m4100precisionimpl($this, Math.abs(value)));
        }
        return sb.append(upToDecimals).append(DurationUnitKt.shortName(unit)).toString();
    }

    /* renamed from: precision-impl, reason: not valid java name */
    private static final int m4100precisionimpl(long $this, double value) {
        if (value < 1) {
            return 3;
        }
        if (value < 10) {
            return 2;
        }
        return value < ((double) 100) ? 1 : 0;
    }

    /* renamed from: toString-impl$default, reason: not valid java name */
    public static /* synthetic */ String m4102toStringimpl$default(long j, TimeUnit timeUnit, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return m4101toStringimpl(j, timeUnit, i);
    }

    @NotNull
    /* renamed from: toString-impl, reason: not valid java name */
    public static final String m4101toStringimpl(long $this, @NotNull TimeUnit unit, int decimals) {
        String scientific;
        Intrinsics.checkNotNullParameter(unit, "unit");
        if (!(decimals >= 0)) {
            throw new IllegalArgumentException(("decimals must be not negative, but was " + decimals).toString());
        }
        double number = m4080toDoubleimpl($this, unit);
        if (Double.isInfinite(number)) {
            return String.valueOf(number);
        }
        StringBuilder sb = new StringBuilder();
        if (Math.abs(number) < 1.0E14d) {
            scientific = FormatToDecimalsKt.formatToExactDecimals(number, RangesKt.coerceAtMost(decimals, 12));
        } else {
            scientific = FormatToDecimalsKt.formatScientific(number);
        }
        return sb.append(scientific).append(DurationUnitKt.shortName(unit)).toString();
    }

    @NotNull
    /* renamed from: toIsoString-impl, reason: not valid java name */
    public static final String m4103toIsoStringimpl(long $this) {
        StringBuilder $this$buildString = new StringBuilder();
        if (m4066isNegativeimpl($this)) {
            $this$buildString.append('-');
        }
        $this$buildString.append("PT");
        long $this$iv = m4070getAbsoluteValueUwyO8pc($this);
        int hours = m4082toIntimpl($this$iv, TimeUnit.HOURS);
        int minutes = m4077getMinutesComponentimpl($this$iv);
        int seconds = m4078getSecondsComponentimpl($this$iv);
        int nanoseconds = m4079getNanosecondsComponentimpl($this$iv);
        boolean hasHours = hours != 0;
        boolean hasSeconds = (seconds == 0 && nanoseconds == 0) ? false : true;
        boolean hasMinutes = minutes != 0 || (hasSeconds && hasHours);
        if (hasHours) {
            $this$buildString.append(hours).append('H');
        }
        if (hasMinutes) {
            $this$buildString.append(minutes).append('M');
        }
        if (hasSeconds || (!hasHours && !hasMinutes)) {
            $this$buildString.append(seconds);
            if (nanoseconds != 0) {
                $this$buildString.append('.');
                String nss = StringsKt.padStart(String.valueOf(nanoseconds), 9, '0');
                if (nanoseconds % DurationKt.NANOS_IN_MILLIS == 0) {
                    Intrinsics.checkNotNullExpressionValue($this$buildString.append((CharSequence) nss, 0, 3), "this.append(value, startIndex, endIndex)");
                } else if (nanoseconds % 1000 == 0) {
                    Intrinsics.checkNotNullExpressionValue($this$buildString.append((CharSequence) nss, 0, 6), "this.append(value, startIndex, endIndex)");
                } else {
                    $this$buildString.append(nss);
                }
            }
            $this$buildString.append('S');
        }
        String string = $this$buildString.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }
}
