package kotlin.collections;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import io.netty.handler.codec.rtsp.RtspHeaders;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;
import java.util.SortedSet;
import java.util.TreeSet;
import kotlin.Deprecated;
import kotlin.DeprecatedSinceKotlin;
import kotlin.Metadata;
import kotlin.OverloadResolutionByLambdaReturnType;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.internal.LowPriorityInOverloadResolution;
import kotlin.internal.PlatformImplementationsKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: _ArraysJvm.kt */
@Metadata(mv = {1, 5, 1}, k = 5, xi = 1, d1 = {"��¬\u0001\n��\n\u0002\u0010 \n��\n\u0002\u0010\u0011\n��\n\u0002\u0010\u000b\n\u0002\u0010\u0018\n\u0002\u0010\u0005\n\u0002\u0010\u0012\n\u0002\u0010\f\n\u0002\u0010\u0019\n\u0002\u0010\u0006\n\u0002\u0010\u0013\n\u0002\u0010\u0007\n\u0002\u0010\u0014\n\u0002\u0010\b\n\u0002\u0010\u0015\n\u0002\u0010\t\n\u0002\u0010\u0016\n\u0002\u0010\n\n\u0002\u0010\u0017\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000e\n\u0002\b\u001b\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u001f\n\u0002\b\u0005\n\u0002\u0010\u001e\n\u0002\b\u0004\n\u0002\u0010\u000f\n\u0002\b\u0007\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\u001a#\u0010��\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b��\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0003¢\u0006\u0002\u0010\u0004\u001a\u0010\u0010��\u001a\b\u0012\u0004\u0012\u00020\u00050\u0001*\u00020\u0006\u001a\u0010\u0010��\u001a\b\u0012\u0004\u0012\u00020\u00070\u0001*\u00020\b\u001a\u0010\u0010��\u001a\b\u0012\u0004\u0012\u00020\t0\u0001*\u00020\n\u001a\u0010\u0010��\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0001*\u00020\f\u001a\u0010\u0010��\u001a\b\u0012\u0004\u0012\u00020\r0\u0001*\u00020\u000e\u001a\u0010\u0010��\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0001*\u00020\u0010\u001a\u0010\u0010��\u001a\b\u0012\u0004\u0012\u00020\u00110\u0001*\u00020\u0012\u001a\u0010\u0010��\u001a\b\u0012\u0004\u0012\u00020\u00130\u0001*\u00020\u0014\u001aU\u0010\u0015\u001a\u00020\u000f\"\u0004\b��\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\u0006\u0010\u0016\u001a\u0002H\u00022\u001a\u0010\u0017\u001a\u0016\u0012\u0006\b��\u0012\u0002H\u00020\u0018j\n\u0012\u0006\b��\u0012\u0002H\u0002`\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f¢\u0006\u0002\u0010\u001c\u001a9\u0010\u0015\u001a\u00020\u000f\"\u0004\b��\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\u0006\u0010\u0016\u001a\u0002H\u00022\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f¢\u0006\u0002\u0010\u001d\u001a&\u0010\u0015\u001a\u00020\u000f*\u00020\b2\u0006\u0010\u0016\u001a\u00020\u00072\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a&\u0010\u0015\u001a\u00020\u000f*\u00020\n2\u0006\u0010\u0016\u001a\u00020\t2\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a&\u0010\u0015\u001a\u00020\u000f*\u00020\f2\u0006\u0010\u0016\u001a\u00020\u000b2\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a&\u0010\u0015\u001a\u00020\u000f*\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\r2\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a&\u0010\u0015\u001a\u00020\u000f*\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u000f2\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a&\u0010\u0015\u001a\u00020\u000f*\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u00112\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a&\u0010\u0015\u001a\u00020\u000f*\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u00132\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a2\u0010\u001e\u001a\u00020\u0005\"\u0004\b��\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\u000e\u0010\u001f\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0003H\u0087\f¢\u0006\u0004\b \u0010!\u001a6\u0010\u001e\u001a\u00020\u0005\"\u0004\b��\u0010\u0002*\f\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0018\u00010\u00032\u0010\u0010\u001f\u001a\f\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0018\u00010\u0003H\u0087\f¢\u0006\u0004\b\"\u0010!\u001a\"\u0010#\u001a\u00020\u000f\"\u0004\b��\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0003H\u0087\b¢\u0006\u0004\b$\u0010%\u001a$\u0010#\u001a\u00020\u000f\"\u0004\b��\u0010\u0002*\f\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0018\u00010\u0003H\u0087\b¢\u0006\u0004\b&\u0010%\u001a\"\u0010'\u001a\u00020(\"\u0004\b��\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0003H\u0087\b¢\u0006\u0004\b)\u0010*\u001a$\u0010'\u001a\u00020(\"\u0004\b��\u0010\u0002*\f\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0018\u00010\u0003H\u0087\b¢\u0006\u0004\b+\u0010*\u001a0\u0010,\u001a\u00020\u0005\"\u0004\b��\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\u000e\u0010\u001f\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0003H\u0087\f¢\u0006\u0002\u0010!\u001a6\u0010,\u001a\u00020\u0005\"\u0004\b��\u0010\u0002*\f\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0018\u00010\u00032\u0010\u0010\u001f\u001a\f\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0018\u00010\u0003H\u0087\f¢\u0006\u0004\b-\u0010!\u001a\u0015\u0010,\u001a\u00020\u0005*\u00020\u00062\u0006\u0010\u001f\u001a\u00020\u0006H\u0087\f\u001a\u001e\u0010,\u001a\u00020\u0005*\u0004\u0018\u00010\u00062\b\u0010\u001f\u001a\u0004\u0018\u00010\u0006H\u0087\f¢\u0006\u0002\b-\u001a\u0015\u0010,\u001a\u00020\u0005*\u00020\b2\u0006\u0010\u001f\u001a\u00020\bH\u0087\f\u001a\u001e\u0010,\u001a\u00020\u0005*\u0004\u0018\u00010\b2\b\u0010\u001f\u001a\u0004\u0018\u00010\bH\u0087\f¢\u0006\u0002\b-\u001a\u0015\u0010,\u001a\u00020\u0005*\u00020\n2\u0006\u0010\u001f\u001a\u00020\nH\u0087\f\u001a\u001e\u0010,\u001a\u00020\u0005*\u0004\u0018\u00010\n2\b\u0010\u001f\u001a\u0004\u0018\u00010\nH\u0087\f¢\u0006\u0002\b-\u001a\u0015\u0010,\u001a\u00020\u0005*\u00020\f2\u0006\u0010\u001f\u001a\u00020\fH\u0087\f\u001a\u001e\u0010,\u001a\u00020\u0005*\u0004\u0018\u00010\f2\b\u0010\u001f\u001a\u0004\u0018\u00010\fH\u0087\f¢\u0006\u0002\b-\u001a\u0015\u0010,\u001a\u00020\u0005*\u00020\u000e2\u0006\u0010\u001f\u001a\u00020\u000eH\u0087\f\u001a\u001e\u0010,\u001a\u00020\u0005*\u0004\u0018\u00010\u000e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u000eH\u0087\f¢\u0006\u0002\b-\u001a\u0015\u0010,\u001a\u00020\u0005*\u00020\u00102\u0006\u0010\u001f\u001a\u00020\u0010H\u0087\f\u001a\u001e\u0010,\u001a\u00020\u0005*\u0004\u0018\u00010\u00102\b\u0010\u001f\u001a\u0004\u0018\u00010\u0010H\u0087\f¢\u0006\u0002\b-\u001a\u0015\u0010,\u001a\u00020\u0005*\u00020\u00122\u0006\u0010\u001f\u001a\u00020\u0012H\u0087\f\u001a\u001e\u0010,\u001a\u00020\u0005*\u0004\u0018\u00010\u00122\b\u0010\u001f\u001a\u0004\u0018\u00010\u0012H\u0087\f¢\u0006\u0002\b-\u001a\u0015\u0010,\u001a\u00020\u0005*\u00020\u00142\u0006\u0010\u001f\u001a\u00020\u0014H\u0087\f\u001a\u001e\u0010,\u001a\u00020\u0005*\u0004\u0018\u00010\u00142\b\u0010\u001f\u001a\u0004\u0018\u00010\u0014H\u0087\f¢\u0006\u0002\b-\u001a \u0010.\u001a\u00020\u000f\"\u0004\b��\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0003H\u0087\b¢\u0006\u0002\u0010%\u001a$\u0010.\u001a\u00020\u000f\"\u0004\b��\u0010\u0002*\f\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0018\u00010\u0003H\u0087\b¢\u0006\u0004\b/\u0010%\u001a\r\u0010.\u001a\u00020\u000f*\u00020\u0006H\u0087\b\u001a\u0014\u0010.\u001a\u00020\u000f*\u0004\u0018\u00010\u0006H\u0087\b¢\u0006\u0002\b/\u001a\r\u0010.\u001a\u00020\u000f*\u00020\bH\u0087\b\u001a\u0014\u0010.\u001a\u00020\u000f*\u0004\u0018\u00010\bH\u0087\b¢\u0006\u0002\b/\u001a\r\u0010.\u001a\u00020\u000f*\u00020\nH\u0087\b\u001a\u0014\u0010.\u001a\u00020\u000f*\u0004\u0018\u00010\nH\u0087\b¢\u0006\u0002\b/\u001a\r\u0010.\u001a\u00020\u000f*\u00020\fH\u0087\b\u001a\u0014\u0010.\u001a\u00020\u000f*\u0004\u0018\u00010\fH\u0087\b¢\u0006\u0002\b/\u001a\r\u0010.\u001a\u00020\u000f*\u00020\u000eH\u0087\b\u001a\u0014\u0010.\u001a\u00020\u000f*\u0004\u0018\u00010\u000eH\u0087\b¢\u0006\u0002\b/\u001a\r\u0010.\u001a\u00020\u000f*\u00020\u0010H\u0087\b\u001a\u0014\u0010.\u001a\u00020\u000f*\u0004\u0018\u00010\u0010H\u0087\b¢\u0006\u0002\b/\u001a\r\u0010.\u001a\u00020\u000f*\u00020\u0012H\u0087\b\u001a\u0014\u0010.\u001a\u00020\u000f*\u0004\u0018\u00010\u0012H\u0087\b¢\u0006\u0002\b/\u001a\r\u0010.\u001a\u00020\u000f*\u00020\u0014H\u0087\b\u001a\u0014\u0010.\u001a\u00020\u000f*\u0004\u0018\u00010\u0014H\u0087\b¢\u0006\u0002\b/\u001a \u00100\u001a\u00020(\"\u0004\b��\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0003H\u0087\b¢\u0006\u0002\u0010*\u001a$\u00100\u001a\u00020(\"\u0004\b��\u0010\u0002*\f\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0018\u00010\u0003H\u0087\b¢\u0006\u0004\b1\u0010*\u001a\r\u00100\u001a\u00020(*\u00020\u0006H\u0087\b\u001a\u0014\u00100\u001a\u00020(*\u0004\u0018\u00010\u0006H\u0087\b¢\u0006\u0002\b1\u001a\r\u00100\u001a\u00020(*\u00020\bH\u0087\b\u001a\u0014\u00100\u001a\u00020(*\u0004\u0018\u00010\bH\u0087\b¢\u0006\u0002\b1\u001a\r\u00100\u001a\u00020(*\u00020\nH\u0087\b\u001a\u0014\u00100\u001a\u00020(*\u0004\u0018\u00010\nH\u0087\b¢\u0006\u0002\b1\u001a\r\u00100\u001a\u00020(*\u00020\fH\u0087\b\u001a\u0014\u00100\u001a\u00020(*\u0004\u0018\u00010\fH\u0087\b¢\u0006\u0002\b1\u001a\r\u00100\u001a\u00020(*\u00020\u000eH\u0087\b\u001a\u0014\u00100\u001a\u00020(*\u0004\u0018\u00010\u000eH\u0087\b¢\u0006\u0002\b1\u001a\r\u00100\u001a\u00020(*\u00020\u0010H\u0087\b\u001a\u0014\u00100\u001a\u00020(*\u0004\u0018\u00010\u0010H\u0087\b¢\u0006\u0002\b1\u001a\r\u00100\u001a\u00020(*\u00020\u0012H\u0087\b\u001a\u0014\u00100\u001a\u00020(*\u0004\u0018\u00010\u0012H\u0087\b¢\u0006\u0002\b1\u001a\r\u00100\u001a\u00020(*\u00020\u0014H\u0087\b\u001a\u0014\u00100\u001a\u00020(*\u0004\u0018\u00010\u0014H\u0087\b¢\u0006\u0002\b1\u001aQ\u00102\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b��\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\f\u00103\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00032\b\b\u0002\u00104\u001a\u00020\u000f2\b\b\u0002\u00105\u001a\u00020\u000f2\b\b\u0002\u00106\u001a\u00020\u000fH\u0007¢\u0006\u0002\u00107\u001a2\u00102\u001a\u00020\u0006*\u00020\u00062\u0006\u00103\u001a\u00020\u00062\b\b\u0002\u00104\u001a\u00020\u000f2\b\b\u0002\u00105\u001a\u00020\u000f2\b\b\u0002\u00106\u001a\u00020\u000fH\u0007\u001a2\u00102\u001a\u00020\b*\u00020\b2\u0006\u00103\u001a\u00020\b2\b\b\u0002\u00104\u001a\u00020\u000f2\b\b\u0002\u00105\u001a\u00020\u000f2\b\b\u0002\u00106\u001a\u00020\u000fH\u0007\u001a2\u00102\u001a\u00020\n*\u00020\n2\u0006\u00103\u001a\u00020\n2\b\b\u0002\u00104\u001a\u00020\u000f2\b\b\u0002\u00105\u001a\u00020\u000f2\b\b\u0002\u00106\u001a\u00020\u000fH\u0007\u001a2\u00102\u001a\u00020\f*\u00020\f2\u0006\u00103\u001a\u00020\f2\b\b\u0002\u00104\u001a\u00020\u000f2\b\b\u0002\u00105\u001a\u00020\u000f2\b\b\u0002\u00106\u001a\u00020\u000fH\u0007\u001a2\u00102\u001a\u00020\u000e*\u00020\u000e2\u0006\u00103\u001a\u00020\u000e2\b\b\u0002\u00104\u001a\u00020\u000f2\b\b\u0002\u00105\u001a\u00020\u000f2\b\b\u0002\u00106\u001a\u00020\u000fH\u0007\u001a2\u00102\u001a\u00020\u0010*\u00020\u00102\u0006\u00103\u001a\u00020\u00102\b\b\u0002\u00104\u001a\u00020\u000f2\b\b\u0002\u00105\u001a\u00020\u000f2\b\b\u0002\u00106\u001a\u00020\u000fH\u0007\u001a2\u00102\u001a\u00020\u0012*\u00020\u00122\u0006\u00103\u001a\u00020\u00122\b\b\u0002\u00104\u001a\u00020\u000f2\b\b\u0002\u00105\u001a\u00020\u000f2\b\b\u0002\u00106\u001a\u00020\u000fH\u0007\u001a2\u00102\u001a\u00020\u0014*\u00020\u00142\u0006\u00103\u001a\u00020\u00142\b\b\u0002\u00104\u001a\u00020\u000f2\b\b\u0002\u00105\u001a\u00020\u000f2\b\b\u0002\u00106\u001a\u00020\u000fH\u0007\u001a$\u00108\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b��\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0087\b¢\u0006\u0002\u00109\u001a.\u00108\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u0003\"\u0004\b��\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010:\u001a\u00020\u000fH\u0087\b¢\u0006\u0002\u0010;\u001a\r\u00108\u001a\u00020\u0006*\u00020\u0006H\u0087\b\u001a\u0015\u00108\u001a\u00020\u0006*\u00020\u00062\u0006\u0010:\u001a\u00020\u000fH\u0087\b\u001a\r\u00108\u001a\u00020\b*\u00020\bH\u0087\b\u001a\u0015\u00108\u001a\u00020\b*\u00020\b2\u0006\u0010:\u001a\u00020\u000fH\u0087\b\u001a\r\u00108\u001a\u00020\n*\u00020\nH\u0087\b\u001a\u0015\u00108\u001a\u00020\n*\u00020\n2\u0006\u0010:\u001a\u00020\u000fH\u0087\b\u001a\r\u00108\u001a\u00020\f*\u00020\fH\u0087\b\u001a\u0015\u00108\u001a\u00020\f*\u00020\f2\u0006\u0010:\u001a\u00020\u000fH\u0087\b\u001a\r\u00108\u001a\u00020\u000e*\u00020\u000eH\u0087\b\u001a\u0015\u00108\u001a\u00020\u000e*\u00020\u000e2\u0006\u0010:\u001a\u00020\u000fH\u0087\b\u001a\r\u00108\u001a\u00020\u0010*\u00020\u0010H\u0087\b\u001a\u0015\u00108\u001a\u00020\u0010*\u00020\u00102\u0006\u0010:\u001a\u00020\u000fH\u0087\b\u001a\r\u00108\u001a\u00020\u0012*\u00020\u0012H\u0087\b\u001a\u0015\u00108\u001a\u00020\u0012*\u00020\u00122\u0006\u0010:\u001a\u00020\u000fH\u0087\b\u001a\r\u00108\u001a\u00020\u0014*\u00020\u0014H\u0087\b\u001a\u0015\u00108\u001a\u00020\u0014*\u00020\u00142\u0006\u0010:\u001a\u00020\u000fH\u0087\b\u001a6\u0010<\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b��\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\u0087\b¢\u0006\u0004\b=\u0010>\u001a\"\u0010<\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\u0087\b¢\u0006\u0002\b=\u001a\"\u0010<\u001a\u00020\b*\u00020\b2\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\u0087\b¢\u0006\u0002\b=\u001a\"\u0010<\u001a\u00020\n*\u00020\n2\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\u0087\b¢\u0006\u0002\b=\u001a\"\u0010<\u001a\u00020\f*\u00020\f2\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\u0087\b¢\u0006\u0002\b=\u001a\"\u0010<\u001a\u00020\u000e*\u00020\u000e2\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\u0087\b¢\u0006\u0002\b=\u001a\"\u0010<\u001a\u00020\u0010*\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\u0087\b¢\u0006\u0002\b=\u001a\"\u0010<\u001a\u00020\u0012*\u00020\u00122\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\u0087\b¢\u0006\u0002\b=\u001a\"\u0010<\u001a\u00020\u0014*\u00020\u00142\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\u0087\b¢\u0006\u0002\b=\u001a5\u0010?\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b��\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\u0001¢\u0006\u0004\b<\u0010>\u001a!\u0010?\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\u0001¢\u0006\u0002\b<\u001a!\u0010?\u001a\u00020\b*\u00020\b2\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\u0001¢\u0006\u0002\b<\u001a!\u0010?\u001a\u00020\n*\u00020\n2\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\u0001¢\u0006\u0002\b<\u001a!\u0010?\u001a\u00020\f*\u00020\f2\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\u0001¢\u0006\u0002\b<\u001a!\u0010?\u001a\u00020\u000e*\u00020\u000e2\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\u0001¢\u0006\u0002\b<\u001a!\u0010?\u001a\u00020\u0010*\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\u0001¢\u0006\u0002\b<\u001a!\u0010?\u001a\u00020\u0012*\u00020\u00122\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\u0001¢\u0006\u0002\b<\u001a!\u0010?\u001a\u00020\u0014*\u00020\u00142\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\u0001¢\u0006\u0002\b<\u001a(\u0010@\u001a\u0002H\u0002\"\u0004\b��\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\u0006\u0010A\u001a\u00020\u000fH\u0087\b¢\u0006\u0002\u0010B\u001a\u0015\u0010@\u001a\u00020\u0005*\u00020\u00062\u0006\u0010A\u001a\u00020\u000fH\u0087\b\u001a\u0015\u0010@\u001a\u00020\u0007*\u00020\b2\u0006\u0010A\u001a\u00020\u000fH\u0087\b\u001a\u0015\u0010@\u001a\u00020\t*\u00020\n2\u0006\u0010A\u001a\u00020\u000fH\u0087\b\u001a\u0015\u0010@\u001a\u00020\u000b*\u00020\f2\u0006\u0010A\u001a\u00020\u000fH\u0087\b\u001a\u0015\u0010@\u001a\u00020\r*\u00020\u000e2\u0006\u0010A\u001a\u00020\u000fH\u0087\b\u001a\u0015\u0010@\u001a\u00020\u000f*\u00020\u00102\u0006\u0010A\u001a\u00020\u000fH\u0087\b\u001a\u0015\u0010@\u001a\u00020\u0011*\u00020\u00122\u0006\u0010A\u001a\u00020\u000fH\u0087\b\u001a\u0015\u0010@\u001a\u00020\u0013*\u00020\u00142\u0006\u0010A\u001a\u00020\u000fH\u0087\b\u001a7\u0010C\u001a\u00020D\"\u0004\b��\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0016\u001a\u0002H\u00022\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f¢\u0006\u0002\u0010E\u001a&\u0010C\u001a\u00020D*\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u00052\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a&\u0010C\u001a\u00020D*\u00020\b2\u0006\u0010\u0016\u001a\u00020\u00072\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a&\u0010C\u001a\u00020D*\u00020\n2\u0006\u0010\u0016\u001a\u00020\t2\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a&\u0010C\u001a\u00020D*\u00020\f2\u0006\u0010\u0016\u001a\u00020\u000b2\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a&\u0010C\u001a\u00020D*\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\r2\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a&\u0010C\u001a\u00020D*\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u000f2\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a&\u0010C\u001a\u00020D*\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u00112\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a&\u0010C\u001a\u00020D*\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u00132\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a-\u0010F\u001a\b\u0012\u0004\u0012\u0002HG0\u0001\"\u0004\b��\u0010G*\u0006\u0012\u0002\b\u00030\u00032\f\u0010H\u001a\b\u0012\u0004\u0012\u0002HG0I¢\u0006\u0002\u0010J\u001aA\u0010K\u001a\u0002HL\"\u0010\b��\u0010L*\n\u0012\u0006\b��\u0012\u0002HG0M\"\u0004\b\u0001\u0010G*\u0006\u0012\u0002\b\u00030\u00032\u0006\u00103\u001a\u0002HL2\f\u0010H\u001a\b\u0012\u0004\u0012\u0002HG0I¢\u0006\u0002\u0010N\u001a,\u0010O\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b��\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0016\u001a\u0002H\u0002H\u0086\u0002¢\u0006\u0002\u0010P\u001a4\u0010O\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b��\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u000e\u0010Q\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0003H\u0086\u0002¢\u0006\u0002\u0010R\u001a2\u0010O\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b��\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\f\u0010Q\u001a\b\u0012\u0004\u0012\u0002H\u00020SH\u0086\u0002¢\u0006\u0002\u0010T\u001a\u0015\u0010O\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u0005H\u0086\u0002\u001a\u0015\u0010O\u001a\u00020\u0006*\u00020\u00062\u0006\u0010Q\u001a\u00020\u0006H\u0086\u0002\u001a\u001b\u0010O\u001a\u00020\u0006*\u00020\u00062\f\u0010Q\u001a\b\u0012\u0004\u0012\u00020\u00050SH\u0086\u0002\u001a\u0015\u0010O\u001a\u00020\b*\u00020\b2\u0006\u0010\u0016\u001a\u00020\u0007H\u0086\u0002\u001a\u0015\u0010O\u001a\u00020\b*\u00020\b2\u0006\u0010Q\u001a\u00020\bH\u0086\u0002\u001a\u001b\u0010O\u001a\u00020\b*\u00020\b2\f\u0010Q\u001a\b\u0012\u0004\u0012\u00020\u00070SH\u0086\u0002\u001a\u0015\u0010O\u001a\u00020\n*\u00020\n2\u0006\u0010\u0016\u001a\u00020\tH\u0086\u0002\u001a\u0015\u0010O\u001a\u00020\n*\u00020\n2\u0006\u0010Q\u001a\u00020\nH\u0086\u0002\u001a\u001b\u0010O\u001a\u00020\n*\u00020\n2\f\u0010Q\u001a\b\u0012\u0004\u0012\u00020\t0SH\u0086\u0002\u001a\u0015\u0010O\u001a\u00020\f*\u00020\f2\u0006\u0010\u0016\u001a\u00020\u000bH\u0086\u0002\u001a\u0015\u0010O\u001a\u00020\f*\u00020\f2\u0006\u0010Q\u001a\u00020\fH\u0086\u0002\u001a\u001b\u0010O\u001a\u00020\f*\u00020\f2\f\u0010Q\u001a\b\u0012\u0004\u0012\u00020\u000b0SH\u0086\u0002\u001a\u0015\u0010O\u001a\u00020\u000e*\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\rH\u0086\u0002\u001a\u0015\u0010O\u001a\u00020\u000e*\u00020\u000e2\u0006\u0010Q\u001a\u00020\u000eH\u0086\u0002\u001a\u001b\u0010O\u001a\u00020\u000e*\u00020\u000e2\f\u0010Q\u001a\b\u0012\u0004\u0012\u00020\r0SH\u0086\u0002\u001a\u0015\u0010O\u001a\u00020\u0010*\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u000fH\u0086\u0002\u001a\u0015\u0010O\u001a\u00020\u0010*\u00020\u00102\u0006\u0010Q\u001a\u00020\u0010H\u0086\u0002\u001a\u001b\u0010O\u001a\u00020\u0010*\u00020\u00102\f\u0010Q\u001a\b\u0012\u0004\u0012\u00020\u000f0SH\u0086\u0002\u001a\u0015\u0010O\u001a\u00020\u0012*\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0011H\u0086\u0002\u001a\u0015\u0010O\u001a\u00020\u0012*\u00020\u00122\u0006\u0010Q\u001a\u00020\u0012H\u0086\u0002\u001a\u001b\u0010O\u001a\u00020\u0012*\u00020\u00122\f\u0010Q\u001a\b\u0012\u0004\u0012\u00020\u00110SH\u0086\u0002\u001a\u0015\u0010O\u001a\u00020\u0014*\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0013H\u0086\u0002\u001a\u0015\u0010O\u001a\u00020\u0014*\u00020\u00142\u0006\u0010Q\u001a\u00020\u0014H\u0086\u0002\u001a\u001b\u0010O\u001a\u00020\u0014*\u00020\u00142\f\u0010Q\u001a\b\u0012\u0004\u0012\u00020\u00130SH\u0086\u0002\u001a,\u0010U\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b��\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0016\u001a\u0002H\u0002H\u0087\b¢\u0006\u0002\u0010P\u001a\u001d\u0010V\u001a\u00020D\"\u0004\b��\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0003¢\u0006\u0002\u0010W\u001a*\u0010V\u001a\u00020D\"\u000e\b��\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020X*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0003H\u0087\b¢\u0006\u0002\u0010Y\u001a1\u0010V\u001a\u00020D\"\u0004\b��\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f¢\u0006\u0002\u0010Z\u001a=\u0010V\u001a\u00020D\"\u000e\b��\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020X*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000fH\u0007¢\u0006\u0002\u0010[\u001a\n\u0010V\u001a\u00020D*\u00020\b\u001a\u001e\u0010V\u001a\u00020D*\u00020\b2\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a\n\u0010V\u001a\u00020D*\u00020\n\u001a\u001e\u0010V\u001a\u00020D*\u00020\n2\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a\n\u0010V\u001a\u00020D*\u00020\f\u001a\u001e\u0010V\u001a\u00020D*\u00020\f2\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a\n\u0010V\u001a\u00020D*\u00020\u000e\u001a\u001e\u0010V\u001a\u00020D*\u00020\u000e2\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a\n\u0010V\u001a\u00020D*\u00020\u0010\u001a\u001e\u0010V\u001a\u00020D*\u00020\u00102\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a\n\u0010V\u001a\u00020D*\u00020\u0012\u001a\u001e\u0010V\u001a\u00020D*\u00020\u00122\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a\n\u0010V\u001a\u00020D*\u00020\u0014\u001a\u001e\u0010V\u001a\u00020D*\u00020\u00142\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f\u001a9\u0010\\\u001a\u00020D\"\u0004\b��\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\u001a\u0010\u0017\u001a\u0016\u0012\u0006\b��\u0012\u0002H\u00020\u0018j\n\u0012\u0006\b��\u0012\u0002H\u0002`\u0019¢\u0006\u0002\u0010]\u001aM\u0010\\\u001a\u00020D\"\u0004\b��\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\u001a\u0010\u0017\u001a\u0016\u0012\u0006\b��\u0012\u0002H\u00020\u0018j\n\u0012\u0006\b��\u0012\u0002H\u0002`\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u000f2\b\b\u0002\u0010\u001b\u001a\u00020\u000f¢\u0006\u0002\u0010^\u001a9\u0010_\u001a\u00020`\"\u0004\b��\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\u0012\u0010a\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020`0bH\u0087\bø\u0001��¢\u0006\u0004\bc\u0010d\u001a9\u0010_\u001a\u00020e\"\u0004\b��\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\u0012\u0010a\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020e0bH\u0087\bø\u0001��¢\u0006\u0004\bf\u0010g\u001a)\u0010_\u001a\u00020`*\u00020\u00062\u0012\u0010a\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020`0bH\u0087\bø\u0001��¢\u0006\u0002\bc\u001a)\u0010_\u001a\u00020e*\u00020\u00062\u0012\u0010a\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020e0bH\u0087\bø\u0001��¢\u0006\u0002\bf\u001a)\u0010_\u001a\u00020`*\u00020\b2\u0012\u0010a\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020`0bH\u0087\bø\u0001��¢\u0006\u0002\bc\u001a)\u0010_\u001a\u00020e*\u00020\b2\u0012\u0010a\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020e0bH\u0087\bø\u0001��¢\u0006\u0002\bf\u001a)\u0010_\u001a\u00020`*\u00020\n2\u0012\u0010a\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020`0bH\u0087\bø\u0001��¢\u0006\u0002\bc\u001a)\u0010_\u001a\u00020e*\u00020\n2\u0012\u0010a\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020e0bH\u0087\bø\u0001��¢\u0006\u0002\bf\u001a)\u0010_\u001a\u00020`*\u00020\f2\u0012\u0010a\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020`0bH\u0087\bø\u0001��¢\u0006\u0002\bc\u001a)\u0010_\u001a\u00020e*\u00020\f2\u0012\u0010a\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020e0bH\u0087\bø\u0001��¢\u0006\u0002\bf\u001a)\u0010_\u001a\u00020`*\u00020\u000e2\u0012\u0010a\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020`0bH\u0087\bø\u0001��¢\u0006\u0002\bc\u001a)\u0010_\u001a\u00020e*\u00020\u000e2\u0012\u0010a\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020e0bH\u0087\bø\u0001��¢\u0006\u0002\bf\u001a)\u0010_\u001a\u00020`*\u00020\u00102\u0012\u0010a\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020`0bH\u0087\bø\u0001��¢\u0006\u0002\bc\u001a)\u0010_\u001a\u00020e*\u00020\u00102\u0012\u0010a\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020e0bH\u0087\bø\u0001��¢\u0006\u0002\bf\u001a)\u0010_\u001a\u00020`*\u00020\u00122\u0012\u0010a\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020`0bH\u0087\bø\u0001��¢\u0006\u0002\bc\u001a)\u0010_\u001a\u00020e*\u00020\u00122\u0012\u0010a\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020e0bH\u0087\bø\u0001��¢\u0006\u0002\bf\u001a)\u0010_\u001a\u00020`*\u00020\u00142\u0012\u0010a\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020`0bH\u0087\bø\u0001��¢\u0006\u0002\bc\u001a)\u0010_\u001a\u00020e*\u00020\u00142\u0012\u0010a\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020e0bH\u0087\bø\u0001��¢\u0006\u0002\bf\u001a-\u0010h\u001a\b\u0012\u0004\u0012\u0002H\u00020i\"\u000e\b��\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020X*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0003¢\u0006\u0002\u0010j\u001a?\u0010h\u001a\b\u0012\u0004\u0012\u0002H\u00020i\"\u0004\b��\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\u001a\u0010\u0017\u001a\u0016\u0012\u0006\b��\u0012\u0002H\u00020\u0018j\n\u0012\u0006\b��\u0012\u0002H\u0002`\u0019¢\u0006\u0002\u0010k\u001a\u0010\u0010h\u001a\b\u0012\u0004\u0012\u00020\u00050i*\u00020\u0006\u001a\u0010\u0010h\u001a\b\u0012\u0004\u0012\u00020\u00070i*\u00020\b\u001a\u0010\u0010h\u001a\b\u0012\u0004\u0012\u00020\t0i*\u00020\n\u001a\u0010\u0010h\u001a\b\u0012\u0004\u0012\u00020\u000b0i*\u00020\f\u001a\u0010\u0010h\u001a\b\u0012\u0004\u0012\u00020\r0i*\u00020\u000e\u001a\u0010\u0010h\u001a\b\u0012\u0004\u0012\u00020\u000f0i*\u00020\u0010\u001a\u0010\u0010h\u001a\b\u0012\u0004\u0012\u00020\u00110i*\u00020\u0012\u001a\u0010\u0010h\u001a\b\u0012\u0004\u0012\u00020\u00130i*\u00020\u0014\u001a\u0015\u0010l\u001a\b\u0012\u0004\u0012\u00020\u00050\u0003*\u00020\u0006¢\u0006\u0002\u0010m\u001a\u0015\u0010l\u001a\b\u0012\u0004\u0012\u00020\u00070\u0003*\u00020\b¢\u0006\u0002\u0010n\u001a\u0015\u0010l\u001a\b\u0012\u0004\u0012\u00020\t0\u0003*\u00020\n¢\u0006\u0002\u0010o\u001a\u0015\u0010l\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0003*\u00020\f¢\u0006\u0002\u0010p\u001a\u0015\u0010l\u001a\b\u0012\u0004\u0012\u00020\r0\u0003*\u00020\u000e¢\u0006\u0002\u0010q\u001a\u0015\u0010l\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0003*\u00020\u0010¢\u0006\u0002\u0010r\u001a\u0015\u0010l\u001a\b\u0012\u0004\u0012\u00020\u00110\u0003*\u00020\u0012¢\u0006\u0002\u0010s\u001a\u0015\u0010l\u001a\b\u0012\u0004\u0012\u00020\u00130\u0003*\u00020\u0014¢\u0006\u0002\u0010t\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006u"}, d2 = {"asList", "", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "", "([Ljava/lang/Object;)Ljava/util/List;", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "binarySearch", "element", "comparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "fromIndex", "toIndex", "([Ljava/lang/Object;Ljava/lang/Object;Ljava/util/Comparator;II)I", "([Ljava/lang/Object;Ljava/lang/Object;II)I", "contentDeepEquals", "other", "contentDeepEqualsInline", "([Ljava/lang/Object;[Ljava/lang/Object;)Z", "contentDeepEqualsNullable", "contentDeepHashCode", "contentDeepHashCodeInline", "([Ljava/lang/Object;)I", "contentDeepHashCodeNullable", "contentDeepToString", "", "contentDeepToStringInline", "([Ljava/lang/Object;)Ljava/lang/String;", "contentDeepToStringNullable", "contentEquals", "contentEqualsNullable", "contentHashCode", "contentHashCodeNullable", "contentToString", "contentToStringNullable", "copyInto", RtspHeaders.Values.DESTINATION, "destinationOffset", "startIndex", "endIndex", "([Ljava/lang/Object;[Ljava/lang/Object;III)[Ljava/lang/Object;", "copyOf", "([Ljava/lang/Object;)[Ljava/lang/Object;", "newSize", "([Ljava/lang/Object;I)[Ljava/lang/Object;", "copyOfRange", "copyOfRangeInline", "([Ljava/lang/Object;II)[Ljava/lang/Object;", "copyOfRangeImpl", "elementAt", "index", "([Ljava/lang/Object;I)Ljava/lang/Object;", "fill", "", "([Ljava/lang/Object;Ljava/lang/Object;II)V", "filterIsInstance", "R", "klass", "Ljava/lang/Class;", "([Ljava/lang/Object;Ljava/lang/Class;)Ljava/util/List;", "filterIsInstanceTo", "C", "", "([Ljava/lang/Object;Ljava/util/Collection;Ljava/lang/Class;)Ljava/util/Collection;", "plus", "([Ljava/lang/Object;Ljava/lang/Object;)[Ljava/lang/Object;", "elements", "([Ljava/lang/Object;[Ljava/lang/Object;)[Ljava/lang/Object;", "", "([Ljava/lang/Object;Ljava/util/Collection;)[Ljava/lang/Object;", "plusElement", "sort", "([Ljava/lang/Object;)V", "", "([Ljava/lang/Comparable;)V", "([Ljava/lang/Object;II)V", "([Ljava/lang/Comparable;II)V", "sortWith", "([Ljava/lang/Object;Ljava/util/Comparator;)V", "([Ljava/lang/Object;Ljava/util/Comparator;II)V", "sumOf", "Ljava/math/BigDecimal;", "selector", "Lkotlin/Function1;", "sumOfBigDecimal", "([Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)Ljava/math/BigDecimal;", "Ljava/math/BigInteger;", "sumOfBigInteger", "([Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)Ljava/math/BigInteger;", "toSortedSet", "Ljava/util/SortedSet;", "([Ljava/lang/Comparable;)Ljava/util/SortedSet;", "([Ljava/lang/Object;Ljava/util/Comparator;)Ljava/util/SortedSet;", "toTypedArray", "([Z)[Ljava/lang/Boolean;", "([B)[Ljava/lang/Byte;", "([C)[Ljava/lang/Character;", "([D)[Ljava/lang/Double;", "([F)[Ljava/lang/Float;", "([I)[Ljava/lang/Integer;", "([J)[Ljava/lang/Long;", "([S)[Ljava/lang/Short;", "kotlin-stdlib"}, xs = "kotlin/collections/ArraysKt")
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-stdlib-1.5.21.jar:kotlin/collections/ArraysKt___ArraysJvmKt.class */
public class ArraysKt___ArraysJvmKt extends ArraysKt__ArraysKt {
    @InlineOnly
    private static final <T> T elementAt(T[] tArr, int index) {
        return tArr[index];
    }

    @InlineOnly
    private static final byte elementAt(byte[] $this$elementAt, int index) {
        return $this$elementAt[index];
    }

    @InlineOnly
    private static final short elementAt(short[] $this$elementAt, int index) {
        return $this$elementAt[index];
    }

    @InlineOnly
    private static final int elementAt(int[] $this$elementAt, int index) {
        return $this$elementAt[index];
    }

    @InlineOnly
    private static final long elementAt(long[] $this$elementAt, int index) {
        return $this$elementAt[index];
    }

    @InlineOnly
    private static final float elementAt(float[] $this$elementAt, int index) {
        return $this$elementAt[index];
    }

    @InlineOnly
    private static final double elementAt(double[] $this$elementAt, int index) {
        return $this$elementAt[index];
    }

    @InlineOnly
    private static final boolean elementAt(boolean[] $this$elementAt, int index) {
        return $this$elementAt[index];
    }

    @InlineOnly
    private static final char elementAt(char[] $this$elementAt, int index) {
        return $this$elementAt[index];
    }

    @NotNull
    public static final <R> List<R> filterIsInstance(@NotNull Object[] filterIsInstance, @NotNull Class<R> klass) {
        Intrinsics.checkNotNullParameter(filterIsInstance, "$this$filterIsInstance");
        Intrinsics.checkNotNullParameter(klass, "klass");
        return (List) ArraysKt.filterIsInstanceTo(filterIsInstance, new ArrayList(), klass);
    }

    @NotNull
    public static final <C extends Collection<? super R>, R> C filterIsInstanceTo(@NotNull Object[] filterIsInstanceTo, @NotNull C destination, @NotNull Class<R> klass) {
        Intrinsics.checkNotNullParameter(filterIsInstanceTo, "$this$filterIsInstanceTo");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(klass, "klass");
        for (Object element : filterIsInstanceTo) {
            if (klass.isInstance(element)) {
                destination.add(element);
            }
        }
        return destination;
    }

    @NotNull
    public static final <T> List<T> asList(@NotNull T[] asList) {
        Intrinsics.checkNotNullParameter(asList, "$this$asList");
        List<T> listAsList = ArraysUtilJVM.asList(asList);
        Intrinsics.checkNotNullExpressionValue(listAsList, "ArraysUtilJVM.asList(this)");
        return listAsList;
    }

    /* compiled from: _ArraysJvm.kt */
    @Metadata(mv = {1, 5, 1}, k = 1, d1 = {"��'\n��\n\u0002\u0018\u0002\n\u0002\u0010\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b*\u0001��\b\n\u0018��2\b\u0012\u0004\u0012\u00020\u00020\u00012\u00060\u0003j\u0002`\u0004J\u0011\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0002H\u0096\u0002J\u0016\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u0006H\u0096\u0002¢\u0006\u0002\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0002H\u0016J\b\u0010\u0010\u001a\u00020\nH\u0016J\u0010\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0002H\u0016R\u0014\u0010\u0005\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u0012"}, d2 = {"kotlin/collections/ArraysKt___ArraysJvmKt$asList$1", "Lkotlin/collections/AbstractList;", "", "Ljava/util/RandomAccess;", "Lkotlin/collections/RandomAccess;", "size", "", "getSize", "()I", "contains", "", "element", BeanUtil.PREFIX_GETTER_GET, "index", "(I)Ljava/lang/Byte;", "indexOf", "isEmpty", "lastIndexOf", "kotlin-stdlib"})
    /* renamed from: kotlin.collections.ArraysKt___ArraysJvmKt$asList$1, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-stdlib-1.5.21.jar:kotlin/collections/ArraysKt___ArraysJvmKt$asList$1.class */
    public static final class AnonymousClass1 extends AbstractList<Byte> implements RandomAccess {
        final /* synthetic */ byte[] $this_asList;

        @Override // kotlin.collections.AbstractCollection, java.util.Collection
        public final /* bridge */ boolean contains(Object obj) {
            if (obj instanceof Byte) {
                return contains(((Number) obj).byteValue());
            }
            return false;
        }

        @Override // kotlin.collections.AbstractList, java.util.List
        public final /* bridge */ int indexOf(Object obj) {
            if (obj instanceof Byte) {
                return indexOf(((Number) obj).byteValue());
            }
            return -1;
        }

        @Override // kotlin.collections.AbstractList, java.util.List
        public final /* bridge */ int lastIndexOf(Object obj) {
            if (obj instanceof Byte) {
                return lastIndexOf(((Number) obj).byteValue());
            }
            return -1;
        }

        AnonymousClass1(byte[] $receiver) {
            this.$this_asList = $receiver;
        }

        @Override // kotlin.collections.AbstractList, kotlin.collections.AbstractCollection
        public int getSize() {
            return this.$this_asList.length;
        }

        @Override // kotlin.collections.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return this.$this_asList.length == 0;
        }

        public boolean contains(byte element) {
            return ArraysKt.contains(this.$this_asList, element);
        }

        @Override // kotlin.collections.AbstractList, java.util.List
        @NotNull
        public Byte get(int index) {
            return Byte.valueOf(this.$this_asList[index]);
        }

        public int indexOf(byte element) {
            return ArraysKt.indexOf(this.$this_asList, element);
        }

        public int lastIndexOf(byte element) {
            return ArraysKt.lastIndexOf(this.$this_asList, element);
        }
    }

    @NotNull
    public static final List<Byte> asList(@NotNull byte[] asList) {
        Intrinsics.checkNotNullParameter(asList, "$this$asList");
        return new AnonymousClass1(asList);
    }

    /* compiled from: _ArraysJvm.kt */
    @Metadata(mv = {1, 5, 1}, k = 1, d1 = {"��'\n��\n\u0002\u0018\u0002\n\u0002\u0010\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b*\u0001��\b\n\u0018��2\b\u0012\u0004\u0012\u00020\u00020\u00012\u00060\u0003j\u0002`\u0004J\u0011\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0002H\u0096\u0002J\u0016\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u0006H\u0096\u0002¢\u0006\u0002\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0002H\u0016J\b\u0010\u0010\u001a\u00020\nH\u0016J\u0010\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0002H\u0016R\u0014\u0010\u0005\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u0012"}, d2 = {"kotlin/collections/ArraysKt___ArraysJvmKt$asList$2", "Lkotlin/collections/AbstractList;", "", "Ljava/util/RandomAccess;", "Lkotlin/collections/RandomAccess;", "size", "", "getSize", "()I", "contains", "", "element", BeanUtil.PREFIX_GETTER_GET, "index", "(I)Ljava/lang/Short;", "indexOf", "isEmpty", "lastIndexOf", "kotlin-stdlib"})
    /* renamed from: kotlin.collections.ArraysKt___ArraysJvmKt$asList$2, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-stdlib-1.5.21.jar:kotlin/collections/ArraysKt___ArraysJvmKt$asList$2.class */
    public static final class AnonymousClass2 extends AbstractList<Short> implements RandomAccess {
        final /* synthetic */ short[] $this_asList;

        @Override // kotlin.collections.AbstractCollection, java.util.Collection
        public final /* bridge */ boolean contains(Object obj) {
            if (obj instanceof Short) {
                return contains(((Number) obj).shortValue());
            }
            return false;
        }

        @Override // kotlin.collections.AbstractList, java.util.List
        public final /* bridge */ int indexOf(Object obj) {
            if (obj instanceof Short) {
                return indexOf(((Number) obj).shortValue());
            }
            return -1;
        }

        @Override // kotlin.collections.AbstractList, java.util.List
        public final /* bridge */ int lastIndexOf(Object obj) {
            if (obj instanceof Short) {
                return lastIndexOf(((Number) obj).shortValue());
            }
            return -1;
        }

        AnonymousClass2(short[] $receiver) {
            this.$this_asList = $receiver;
        }

        @Override // kotlin.collections.AbstractList, kotlin.collections.AbstractCollection
        public int getSize() {
            return this.$this_asList.length;
        }

        @Override // kotlin.collections.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return this.$this_asList.length == 0;
        }

        public boolean contains(short element) {
            return ArraysKt.contains(this.$this_asList, element);
        }

        @Override // kotlin.collections.AbstractList, java.util.List
        @NotNull
        public Short get(int index) {
            return Short.valueOf(this.$this_asList[index]);
        }

        public int indexOf(short element) {
            return ArraysKt.indexOf(this.$this_asList, element);
        }

        public int lastIndexOf(short element) {
            return ArraysKt.lastIndexOf(this.$this_asList, element);
        }
    }

    @NotNull
    public static final List<Short> asList(@NotNull short[] asList) {
        Intrinsics.checkNotNullParameter(asList, "$this$asList");
        return new AnonymousClass2(asList);
    }

    /* compiled from: _ArraysJvm.kt */
    @Metadata(mv = {1, 5, 1}, k = 1, d1 = {"��!\n��\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\b*\u0001��\b\n\u0018��2\b\u0012\u0004\u0012\u00020\u00020\u00012\u00060\u0003j\u0002`\u0004J\u0011\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0002H\u0096\u0002J\u0016\u0010\u000b\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u0002H\u0096\u0002¢\u0006\u0002\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u0002H\u0016J\b\u0010\u000f\u001a\u00020\tH\u0016J\u0010\u0010\u0010\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u0002H\u0016R\u0014\u0010\u0005\u001a\u00020\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"kotlin/collections/ArraysKt___ArraysJvmKt$asList$3", "Lkotlin/collections/AbstractList;", "", "Ljava/util/RandomAccess;", "Lkotlin/collections/RandomAccess;", "size", "getSize", "()I", "contains", "", "element", BeanUtil.PREFIX_GETTER_GET, "index", "(I)Ljava/lang/Integer;", "indexOf", "isEmpty", "lastIndexOf", "kotlin-stdlib"})
    /* renamed from: kotlin.collections.ArraysKt___ArraysJvmKt$asList$3, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-stdlib-1.5.21.jar:kotlin/collections/ArraysKt___ArraysJvmKt$asList$3.class */
    public static final class AnonymousClass3 extends AbstractList<Integer> implements RandomAccess {
        final /* synthetic */ int[] $this_asList;

        @Override // kotlin.collections.AbstractCollection, java.util.Collection
        public final /* bridge */ boolean contains(Object obj) {
            if (obj instanceof Integer) {
                return contains(((Number) obj).intValue());
            }
            return false;
        }

        @Override // kotlin.collections.AbstractList, java.util.List
        public final /* bridge */ int indexOf(Object obj) {
            if (obj instanceof Integer) {
                return indexOf(((Number) obj).intValue());
            }
            return -1;
        }

        @Override // kotlin.collections.AbstractList, java.util.List
        public final /* bridge */ int lastIndexOf(Object obj) {
            if (obj instanceof Integer) {
                return lastIndexOf(((Number) obj).intValue());
            }
            return -1;
        }

        AnonymousClass3(int[] $receiver) {
            this.$this_asList = $receiver;
        }

        @Override // kotlin.collections.AbstractList, kotlin.collections.AbstractCollection
        public int getSize() {
            return this.$this_asList.length;
        }

        @Override // kotlin.collections.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return this.$this_asList.length == 0;
        }

        public boolean contains(int element) {
            return ArraysKt.contains(this.$this_asList, element);
        }

        @Override // kotlin.collections.AbstractList, java.util.List
        @NotNull
        public Integer get(int index) {
            return Integer.valueOf(this.$this_asList[index]);
        }

        public int indexOf(int element) {
            return ArraysKt.indexOf(this.$this_asList, element);
        }

        public int lastIndexOf(int element) {
            return ArraysKt.lastIndexOf(this.$this_asList, element);
        }
    }

    @NotNull
    public static final List<Integer> asList(@NotNull int[] asList) {
        Intrinsics.checkNotNullParameter(asList, "$this$asList");
        return new AnonymousClass3(asList);
    }

    /* compiled from: _ArraysJvm.kt */
    @Metadata(mv = {1, 5, 1}, k = 1, d1 = {"��'\n��\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b*\u0001��\b\n\u0018��2\b\u0012\u0004\u0012\u00020\u00020\u00012\u00060\u0003j\u0002`\u0004J\u0011\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0002H\u0096\u0002J\u0016\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u0006H\u0096\u0002¢\u0006\u0002\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0002H\u0016J\b\u0010\u0010\u001a\u00020\nH\u0016J\u0010\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0002H\u0016R\u0014\u0010\u0005\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u0012"}, d2 = {"kotlin/collections/ArraysKt___ArraysJvmKt$asList$4", "Lkotlin/collections/AbstractList;", "", "Ljava/util/RandomAccess;", "Lkotlin/collections/RandomAccess;", "size", "", "getSize", "()I", "contains", "", "element", BeanUtil.PREFIX_GETTER_GET, "index", "(I)Ljava/lang/Long;", "indexOf", "isEmpty", "lastIndexOf", "kotlin-stdlib"})
    /* renamed from: kotlin.collections.ArraysKt___ArraysJvmKt$asList$4, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-stdlib-1.5.21.jar:kotlin/collections/ArraysKt___ArraysJvmKt$asList$4.class */
    public static final class AnonymousClass4 extends AbstractList<Long> implements RandomAccess {
        final /* synthetic */ long[] $this_asList;

        @Override // kotlin.collections.AbstractCollection, java.util.Collection
        public final /* bridge */ boolean contains(Object obj) {
            if (obj instanceof Long) {
                return contains(((Number) obj).longValue());
            }
            return false;
        }

        @Override // kotlin.collections.AbstractList, java.util.List
        public final /* bridge */ int indexOf(Object obj) {
            if (obj instanceof Long) {
                return indexOf(((Number) obj).longValue());
            }
            return -1;
        }

        @Override // kotlin.collections.AbstractList, java.util.List
        public final /* bridge */ int lastIndexOf(Object obj) {
            if (obj instanceof Long) {
                return lastIndexOf(((Number) obj).longValue());
            }
            return -1;
        }

        AnonymousClass4(long[] $receiver) {
            this.$this_asList = $receiver;
        }

        @Override // kotlin.collections.AbstractList, kotlin.collections.AbstractCollection
        public int getSize() {
            return this.$this_asList.length;
        }

        @Override // kotlin.collections.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return this.$this_asList.length == 0;
        }

        public boolean contains(long element) {
            return ArraysKt.contains(this.$this_asList, element);
        }

        @Override // kotlin.collections.AbstractList, java.util.List
        @NotNull
        public Long get(int index) {
            return Long.valueOf(this.$this_asList[index]);
        }

        public int indexOf(long element) {
            return ArraysKt.indexOf(this.$this_asList, element);
        }

        public int lastIndexOf(long element) {
            return ArraysKt.lastIndexOf(this.$this_asList, element);
        }
    }

    @NotNull
    public static final List<Long> asList(@NotNull long[] asList) {
        Intrinsics.checkNotNullParameter(asList, "$this$asList");
        return new AnonymousClass4(asList);
    }

    /* compiled from: _ArraysJvm.kt */
    @Metadata(mv = {1, 5, 1}, k = 1, d1 = {"��'\n��\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b*\u0001��\b\n\u0018��2\b\u0012\u0004\u0012\u00020\u00020\u00012\u00060\u0003j\u0002`\u0004J\u0011\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0002H\u0096\u0002J\u0016\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u0006H\u0096\u0002¢\u0006\u0002\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0002H\u0016J\b\u0010\u0010\u001a\u00020\nH\u0016J\u0010\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0002H\u0016R\u0014\u0010\u0005\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u0012"}, d2 = {"kotlin/collections/ArraysKt___ArraysJvmKt$asList$5", "Lkotlin/collections/AbstractList;", "", "Ljava/util/RandomAccess;", "Lkotlin/collections/RandomAccess;", "size", "", "getSize", "()I", "contains", "", "element", BeanUtil.PREFIX_GETTER_GET, "index", "(I)Ljava/lang/Float;", "indexOf", "isEmpty", "lastIndexOf", "kotlin-stdlib"})
    /* renamed from: kotlin.collections.ArraysKt___ArraysJvmKt$asList$5, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-stdlib-1.5.21.jar:kotlin/collections/ArraysKt___ArraysJvmKt$asList$5.class */
    public static final class AnonymousClass5 extends AbstractList<Float> implements RandomAccess {
        final /* synthetic */ float[] $this_asList;

        @Override // kotlin.collections.AbstractCollection, java.util.Collection
        public final /* bridge */ boolean contains(Object obj) {
            if (obj instanceof Float) {
                return contains(((Number) obj).floatValue());
            }
            return false;
        }

        @Override // kotlin.collections.AbstractList, java.util.List
        public final /* bridge */ int indexOf(Object obj) {
            if (obj instanceof Float) {
                return indexOf(((Number) obj).floatValue());
            }
            return -1;
        }

        @Override // kotlin.collections.AbstractList, java.util.List
        public final /* bridge */ int lastIndexOf(Object obj) {
            if (obj instanceof Float) {
                return lastIndexOf(((Number) obj).floatValue());
            }
            return -1;
        }

        AnonymousClass5(float[] $receiver) {
            this.$this_asList = $receiver;
        }

        @Override // kotlin.collections.AbstractList, kotlin.collections.AbstractCollection
        public int getSize() {
            return this.$this_asList.length;
        }

        @Override // kotlin.collections.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return this.$this_asList.length == 0;
        }

        public boolean contains(float element) {
            float[] $this$any$iv = this.$this_asList;
            for (float element$iv : $this$any$iv) {
                if (Float.floatToIntBits(element$iv) == Float.floatToIntBits(element)) {
                    return true;
                }
            }
            return false;
        }

        @Override // kotlin.collections.AbstractList, java.util.List
        @NotNull
        public Float get(int index) {
            return Float.valueOf(this.$this_asList[index]);
        }

        public int indexOf(float element) {
            float[] $this$indexOfFirst$iv = this.$this_asList;
            int length = $this$indexOfFirst$iv.length;
            for (int index$iv = 0; index$iv < length; index$iv++) {
                float it = $this$indexOfFirst$iv[index$iv];
                if (Float.floatToIntBits(it) == Float.floatToIntBits(element)) {
                    return index$iv;
                }
            }
            return -1;
        }

        public int lastIndexOf(float element) {
            float[] $this$indexOfLast$iv = this.$this_asList;
            for (int index$iv = $this$indexOfLast$iv.length - 1; index$iv >= 0; index$iv--) {
                float it = $this$indexOfLast$iv[index$iv];
                if (Float.floatToIntBits(it) == Float.floatToIntBits(element)) {
                    return index$iv;
                }
            }
            return -1;
        }
    }

    @NotNull
    public static final List<Float> asList(@NotNull float[] asList) {
        Intrinsics.checkNotNullParameter(asList, "$this$asList");
        return new AnonymousClass5(asList);
    }

    /* compiled from: _ArraysJvm.kt */
    @Metadata(mv = {1, 5, 1}, k = 1, d1 = {"��'\n��\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b*\u0001��\b\n\u0018��2\b\u0012\u0004\u0012\u00020\u00020\u00012\u00060\u0003j\u0002`\u0004J\u0011\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0002H\u0096\u0002J\u0016\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u0006H\u0096\u0002¢\u0006\u0002\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0002H\u0016J\b\u0010\u0010\u001a\u00020\nH\u0016J\u0010\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0002H\u0016R\u0014\u0010\u0005\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u0012"}, d2 = {"kotlin/collections/ArraysKt___ArraysJvmKt$asList$6", "Lkotlin/collections/AbstractList;", "", "Ljava/util/RandomAccess;", "Lkotlin/collections/RandomAccess;", "size", "", "getSize", "()I", "contains", "", "element", BeanUtil.PREFIX_GETTER_GET, "index", "(I)Ljava/lang/Double;", "indexOf", "isEmpty", "lastIndexOf", "kotlin-stdlib"})
    /* renamed from: kotlin.collections.ArraysKt___ArraysJvmKt$asList$6, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-stdlib-1.5.21.jar:kotlin/collections/ArraysKt___ArraysJvmKt$asList$6.class */
    public static final class AnonymousClass6 extends AbstractList<Double> implements RandomAccess {
        final /* synthetic */ double[] $this_asList;

        @Override // kotlin.collections.AbstractCollection, java.util.Collection
        public final /* bridge */ boolean contains(Object obj) {
            if (obj instanceof Double) {
                return contains(((Number) obj).doubleValue());
            }
            return false;
        }

        @Override // kotlin.collections.AbstractList, java.util.List
        public final /* bridge */ int indexOf(Object obj) {
            if (obj instanceof Double) {
                return indexOf(((Number) obj).doubleValue());
            }
            return -1;
        }

        @Override // kotlin.collections.AbstractList, java.util.List
        public final /* bridge */ int lastIndexOf(Object obj) {
            if (obj instanceof Double) {
                return lastIndexOf(((Number) obj).doubleValue());
            }
            return -1;
        }

        AnonymousClass6(double[] $receiver) {
            this.$this_asList = $receiver;
        }

        @Override // kotlin.collections.AbstractList, kotlin.collections.AbstractCollection
        public int getSize() {
            return this.$this_asList.length;
        }

        @Override // kotlin.collections.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return this.$this_asList.length == 0;
        }

        public boolean contains(double element) {
            double[] $this$any$iv = this.$this_asList;
            for (double element$iv : $this$any$iv) {
                if (Double.doubleToLongBits(element$iv) == Double.doubleToLongBits(element)) {
                    return true;
                }
            }
            return false;
        }

        @Override // kotlin.collections.AbstractList, java.util.List
        @NotNull
        public Double get(int index) {
            return Double.valueOf(this.$this_asList[index]);
        }

        public int indexOf(double element) {
            double[] $this$indexOfFirst$iv = this.$this_asList;
            int length = $this$indexOfFirst$iv.length;
            for (int index$iv = 0; index$iv < length; index$iv++) {
                double it = $this$indexOfFirst$iv[index$iv];
                if (Double.doubleToLongBits(it) == Double.doubleToLongBits(element)) {
                    return index$iv;
                }
            }
            return -1;
        }

        public int lastIndexOf(double element) {
            double[] $this$indexOfLast$iv = this.$this_asList;
            for (int index$iv = $this$indexOfLast$iv.length - 1; index$iv >= 0; index$iv--) {
                double it = $this$indexOfLast$iv[index$iv];
                if (Double.doubleToLongBits(it) == Double.doubleToLongBits(element)) {
                    return index$iv;
                }
            }
            return -1;
        }
    }

    @NotNull
    public static final List<Double> asList(@NotNull double[] asList) {
        Intrinsics.checkNotNullParameter(asList, "$this$asList");
        return new AnonymousClass6(asList);
    }

    /* compiled from: _ArraysJvm.kt */
    @Metadata(mv = {1, 5, 1}, k = 1, d1 = {"��\u001f\n��\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n\u0002\b\u000b*\u0001��\b\n\u0018��2\b\u0012\u0004\u0012\u00020\u00020\u00012\u00060\u0003j\u0002`\u0004J\u0011\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u0002H\u0096\u0002J\u0016\u0010\u000b\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u0006H\u0096\u0002¢\u0006\u0002\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u0002H\u0016J\b\u0010\u000f\u001a\u00020\u0002H\u0016J\u0010\u0010\u0010\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u0002H\u0016R\u0014\u0010\u0005\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u0011"}, d2 = {"kotlin/collections/ArraysKt___ArraysJvmKt$asList$7", "Lkotlin/collections/AbstractList;", "", "Ljava/util/RandomAccess;", "Lkotlin/collections/RandomAccess;", "size", "", "getSize", "()I", "contains", "element", BeanUtil.PREFIX_GETTER_GET, "index", "(I)Ljava/lang/Boolean;", "indexOf", "isEmpty", "lastIndexOf", "kotlin-stdlib"})
    /* renamed from: kotlin.collections.ArraysKt___ArraysJvmKt$asList$7, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-stdlib-1.5.21.jar:kotlin/collections/ArraysKt___ArraysJvmKt$asList$7.class */
    public static final class AnonymousClass7 extends AbstractList<Boolean> implements RandomAccess {
        final /* synthetic */ boolean[] $this_asList;

        @Override // kotlin.collections.AbstractCollection, java.util.Collection
        public final /* bridge */ boolean contains(Object obj) {
            if (obj instanceof Boolean) {
                return contains(((Boolean) obj).booleanValue());
            }
            return false;
        }

        @Override // kotlin.collections.AbstractList, java.util.List
        public final /* bridge */ int indexOf(Object obj) {
            if (obj instanceof Boolean) {
                return indexOf(((Boolean) obj).booleanValue());
            }
            return -1;
        }

        @Override // kotlin.collections.AbstractList, java.util.List
        public final /* bridge */ int lastIndexOf(Object obj) {
            if (obj instanceof Boolean) {
                return lastIndexOf(((Boolean) obj).booleanValue());
            }
            return -1;
        }

        AnonymousClass7(boolean[] $receiver) {
            this.$this_asList = $receiver;
        }

        @Override // kotlin.collections.AbstractList, kotlin.collections.AbstractCollection
        public int getSize() {
            return this.$this_asList.length;
        }

        @Override // kotlin.collections.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return this.$this_asList.length == 0;
        }

        public boolean contains(boolean element) {
            return ArraysKt.contains(this.$this_asList, element);
        }

        @Override // kotlin.collections.AbstractList, java.util.List
        @NotNull
        public Boolean get(int index) {
            return Boolean.valueOf(this.$this_asList[index]);
        }

        public int indexOf(boolean element) {
            return ArraysKt.indexOf(this.$this_asList, element);
        }

        public int lastIndexOf(boolean element) {
            return ArraysKt.lastIndexOf(this.$this_asList, element);
        }
    }

    @NotNull
    public static final List<Boolean> asList(@NotNull boolean[] asList) {
        Intrinsics.checkNotNullParameter(asList, "$this$asList");
        return new AnonymousClass7(asList);
    }

    /* compiled from: _ArraysJvm.kt */
    @Metadata(mv = {1, 5, 1}, k = 1, d1 = {"��'\n��\n\u0002\u0018\u0002\n\u0002\u0010\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b*\u0001��\b\n\u0018��2\b\u0012\u0004\u0012\u00020\u00020\u00012\u00060\u0003j\u0002`\u0004J\u0011\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0002H\u0096\u0002J\u0016\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u0006H\u0096\u0002¢\u0006\u0002\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0002H\u0016J\b\u0010\u0010\u001a\u00020\nH\u0016J\u0010\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0002H\u0016R\u0014\u0010\u0005\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u0012"}, d2 = {"kotlin/collections/ArraysKt___ArraysJvmKt$asList$8", "Lkotlin/collections/AbstractList;", "", "Ljava/util/RandomAccess;", "Lkotlin/collections/RandomAccess;", "size", "", "getSize", "()I", "contains", "", "element", BeanUtil.PREFIX_GETTER_GET, "index", "(I)Ljava/lang/Character;", "indexOf", "isEmpty", "lastIndexOf", "kotlin-stdlib"})
    /* renamed from: kotlin.collections.ArraysKt___ArraysJvmKt$asList$8, reason: invalid class name */
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-stdlib-1.5.21.jar:kotlin/collections/ArraysKt___ArraysJvmKt$asList$8.class */
    public static final class AnonymousClass8 extends AbstractList<Character> implements RandomAccess {
        final /* synthetic */ char[] $this_asList;

        @Override // kotlin.collections.AbstractCollection, java.util.Collection
        public final /* bridge */ boolean contains(Object obj) {
            if (obj instanceof Character) {
                return contains(((Character) obj).charValue());
            }
            return false;
        }

        @Override // kotlin.collections.AbstractList, java.util.List
        public final /* bridge */ int indexOf(Object obj) {
            if (obj instanceof Character) {
                return indexOf(((Character) obj).charValue());
            }
            return -1;
        }

        @Override // kotlin.collections.AbstractList, java.util.List
        public final /* bridge */ int lastIndexOf(Object obj) {
            if (obj instanceof Character) {
                return lastIndexOf(((Character) obj).charValue());
            }
            return -1;
        }

        AnonymousClass8(char[] $receiver) {
            this.$this_asList = $receiver;
        }

        @Override // kotlin.collections.AbstractList, kotlin.collections.AbstractCollection
        public int getSize() {
            return this.$this_asList.length;
        }

        @Override // kotlin.collections.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return this.$this_asList.length == 0;
        }

        public boolean contains(char element) {
            return ArraysKt.contains(this.$this_asList, element);
        }

        @Override // kotlin.collections.AbstractList, java.util.List
        @NotNull
        public Character get(int index) {
            return Character.valueOf(this.$this_asList[index]);
        }

        public int indexOf(char element) {
            return ArraysKt.indexOf(this.$this_asList, element);
        }

        public int lastIndexOf(char element) {
            return ArraysKt.lastIndexOf(this.$this_asList, element);
        }
    }

    @NotNull
    public static final List<Character> asList(@NotNull char[] asList) {
        Intrinsics.checkNotNullParameter(asList, "$this$asList");
        return new AnonymousClass8(asList);
    }

    public static /* synthetic */ int binarySearch$default(Object[] objArr, Object obj, Comparator comparator, int i, int i2, int i3, Object obj2) {
        if ((i3 & 4) != 0) {
            i = 0;
        }
        if ((i3 & 8) != 0) {
            i2 = objArr.length;
        }
        return ArraysKt.binarySearch(objArr, obj, comparator, i, i2);
    }

    public static final <T> int binarySearch(@NotNull T[] binarySearch, T t, @NotNull Comparator<? super T> comparator, int fromIndex, int toIndex) {
        Intrinsics.checkNotNullParameter(binarySearch, "$this$binarySearch");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        return Arrays.binarySearch(binarySearch, fromIndex, toIndex, t, comparator);
    }

    public static /* synthetic */ int binarySearch$default(Object[] objArr, Object obj, int i, int i2, int i3, Object obj2) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = objArr.length;
        }
        return ArraysKt.binarySearch(objArr, obj, i, i2);
    }

    public static final <T> int binarySearch(@NotNull T[] binarySearch, T t, int fromIndex, int toIndex) {
        Intrinsics.checkNotNullParameter(binarySearch, "$this$binarySearch");
        return Arrays.binarySearch(binarySearch, fromIndex, toIndex, t);
    }

    public static /* synthetic */ int binarySearch$default(byte[] bArr, byte b, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = bArr.length;
        }
        return ArraysKt.binarySearch(bArr, b, i, i2);
    }

    public static final int binarySearch(@NotNull byte[] binarySearch, byte element, int fromIndex, int toIndex) {
        Intrinsics.checkNotNullParameter(binarySearch, "$this$binarySearch");
        return Arrays.binarySearch(binarySearch, fromIndex, toIndex, element);
    }

    public static /* synthetic */ int binarySearch$default(short[] sArr, short s, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = sArr.length;
        }
        return ArraysKt.binarySearch(sArr, s, i, i2);
    }

    public static final int binarySearch(@NotNull short[] binarySearch, short element, int fromIndex, int toIndex) {
        Intrinsics.checkNotNullParameter(binarySearch, "$this$binarySearch");
        return Arrays.binarySearch(binarySearch, fromIndex, toIndex, element);
    }

    public static /* synthetic */ int binarySearch$default(int[] iArr, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i2 = 0;
        }
        if ((i4 & 4) != 0) {
            i3 = iArr.length;
        }
        return ArraysKt.binarySearch(iArr, i, i2, i3);
    }

    public static final int binarySearch(@NotNull int[] binarySearch, int element, int fromIndex, int toIndex) {
        Intrinsics.checkNotNullParameter(binarySearch, "$this$binarySearch");
        return Arrays.binarySearch(binarySearch, fromIndex, toIndex, element);
    }

    public static /* synthetic */ int binarySearch$default(long[] jArr, long j, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = jArr.length;
        }
        return ArraysKt.binarySearch(jArr, j, i, i2);
    }

    public static final int binarySearch(@NotNull long[] binarySearch, long element, int fromIndex, int toIndex) {
        Intrinsics.checkNotNullParameter(binarySearch, "$this$binarySearch");
        return Arrays.binarySearch(binarySearch, fromIndex, toIndex, element);
    }

    public static /* synthetic */ int binarySearch$default(float[] fArr, float f, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = fArr.length;
        }
        return ArraysKt.binarySearch(fArr, f, i, i2);
    }

    public static final int binarySearch(@NotNull float[] binarySearch, float element, int fromIndex, int toIndex) {
        Intrinsics.checkNotNullParameter(binarySearch, "$this$binarySearch");
        return Arrays.binarySearch(binarySearch, fromIndex, toIndex, element);
    }

    public static /* synthetic */ int binarySearch$default(double[] dArr, double d, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = dArr.length;
        }
        return ArraysKt.binarySearch(dArr, d, i, i2);
    }

    public static final int binarySearch(@NotNull double[] binarySearch, double element, int fromIndex, int toIndex) {
        Intrinsics.checkNotNullParameter(binarySearch, "$this$binarySearch");
        return Arrays.binarySearch(binarySearch, fromIndex, toIndex, element);
    }

    public static /* synthetic */ int binarySearch$default(char[] cArr, char c, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = cArr.length;
        }
        return ArraysKt.binarySearch(cArr, c, i, i2);
    }

    public static final int binarySearch(@NotNull char[] binarySearch, char element, int fromIndex, int toIndex) {
        Intrinsics.checkNotNullParameter(binarySearch, "$this$binarySearch");
        return Arrays.binarySearch(binarySearch, fromIndex, toIndex, element);
    }

    @SinceKotlin(version = "1.1")
    @JvmName(name = "contentDeepEqualsInline")
    @InlineOnly
    @LowPriorityInOverloadResolution
    private static final <T> boolean contentDeepEqualsInline(T[] tArr, T[] tArr2) {
        return PlatformImplementationsKt.apiVersionIsAtLeast(1, 3, 0) ? ArraysKt.contentDeepEquals(tArr, tArr2) : Arrays.deepEquals(tArr, tArr2);
    }

    @SinceKotlin(version = "1.4")
    @JvmName(name = "contentDeepEqualsNullable")
    @InlineOnly
    private static final <T> boolean contentDeepEqualsNullable(T[] tArr, T[] tArr2) {
        if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 3, 0)) {
            return ArraysKt.contentDeepEquals(tArr, tArr2);
        }
        return Arrays.deepEquals(tArr, tArr2);
    }

    @SinceKotlin(version = "1.1")
    @JvmName(name = "contentDeepHashCodeInline")
    @InlineOnly
    @LowPriorityInOverloadResolution
    private static final <T> int contentDeepHashCodeInline(T[] tArr) {
        return PlatformImplementationsKt.apiVersionIsAtLeast(1, 3, 0) ? ArraysKt.contentDeepHashCode(tArr) : Arrays.deepHashCode(tArr);
    }

    @SinceKotlin(version = "1.4")
    @JvmName(name = "contentDeepHashCodeNullable")
    @InlineOnly
    private static final <T> int contentDeepHashCodeNullable(T[] tArr) {
        if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 3, 0)) {
            return ArraysKt.contentDeepHashCode(tArr);
        }
        return Arrays.deepHashCode(tArr);
    }

    @SinceKotlin(version = "1.1")
    @JvmName(name = "contentDeepToStringInline")
    @InlineOnly
    @LowPriorityInOverloadResolution
    private static final <T> String contentDeepToStringInline(T[] tArr) {
        if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 3, 0)) {
            return ArraysKt.contentDeepToString(tArr);
        }
        String strDeepToString = Arrays.deepToString(tArr);
        Intrinsics.checkNotNullExpressionValue(strDeepToString, "java.util.Arrays.deepToString(this)");
        return strDeepToString;
    }

    @SinceKotlin(version = "1.4")
    @JvmName(name = "contentDeepToStringNullable")
    @InlineOnly
    private static final <T> String contentDeepToStringNullable(T[] tArr) {
        if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 3, 0)) {
            return ArraysKt.contentDeepToString(tArr);
        }
        String strDeepToString = Arrays.deepToString(tArr);
        Intrinsics.checkNotNullExpressionValue(strDeepToString, "java.util.Arrays.deepToString(this)");
        return strDeepToString;
    }

    @SinceKotlin(version = "1.4")
    @JvmName(name = "contentEqualsNullable")
    @InlineOnly
    private static final <T> boolean contentEqualsNullable(T[] tArr, T[] tArr2) {
        return Arrays.equals(tArr, tArr2);
    }

    @SinceKotlin(version = "1.4")
    @JvmName(name = "contentEqualsNullable")
    @InlineOnly
    private static final boolean contentEqualsNullable(byte[] $this$contentEquals, byte[] other) {
        return Arrays.equals($this$contentEquals, other);
    }

    @SinceKotlin(version = "1.4")
    @JvmName(name = "contentEqualsNullable")
    @InlineOnly
    private static final boolean contentEqualsNullable(short[] $this$contentEquals, short[] other) {
        return Arrays.equals($this$contentEquals, other);
    }

    @SinceKotlin(version = "1.4")
    @JvmName(name = "contentEqualsNullable")
    @InlineOnly
    private static final boolean contentEqualsNullable(int[] $this$contentEquals, int[] other) {
        return Arrays.equals($this$contentEquals, other);
    }

    @SinceKotlin(version = "1.4")
    @JvmName(name = "contentEqualsNullable")
    @InlineOnly
    private static final boolean contentEqualsNullable(long[] $this$contentEquals, long[] other) {
        return Arrays.equals($this$contentEquals, other);
    }

    @SinceKotlin(version = "1.4")
    @JvmName(name = "contentEqualsNullable")
    @InlineOnly
    private static final boolean contentEqualsNullable(float[] $this$contentEquals, float[] other) {
        return Arrays.equals($this$contentEquals, other);
    }

    @SinceKotlin(version = "1.4")
    @JvmName(name = "contentEqualsNullable")
    @InlineOnly
    private static final boolean contentEqualsNullable(double[] $this$contentEquals, double[] other) {
        return Arrays.equals($this$contentEquals, other);
    }

    @SinceKotlin(version = "1.4")
    @JvmName(name = "contentEqualsNullable")
    @InlineOnly
    private static final boolean contentEqualsNullable(boolean[] $this$contentEquals, boolean[] other) {
        return Arrays.equals($this$contentEquals, other);
    }

    @SinceKotlin(version = "1.4")
    @JvmName(name = "contentEqualsNullable")
    @InlineOnly
    private static final boolean contentEqualsNullable(char[] $this$contentEquals, char[] other) {
        return Arrays.equals($this$contentEquals, other);
    }

    @SinceKotlin(version = "1.4")
    @JvmName(name = "contentHashCodeNullable")
    @InlineOnly
    private static final <T> int contentHashCodeNullable(T[] tArr) {
        return Arrays.hashCode(tArr);
    }

    @SinceKotlin(version = "1.4")
    @JvmName(name = "contentHashCodeNullable")
    @InlineOnly
    private static final int contentHashCodeNullable(byte[] $this$contentHashCode) {
        return Arrays.hashCode($this$contentHashCode);
    }

    @SinceKotlin(version = "1.4")
    @JvmName(name = "contentHashCodeNullable")
    @InlineOnly
    private static final int contentHashCodeNullable(short[] $this$contentHashCode) {
        return Arrays.hashCode($this$contentHashCode);
    }

    @SinceKotlin(version = "1.4")
    @JvmName(name = "contentHashCodeNullable")
    @InlineOnly
    private static final int contentHashCodeNullable(int[] $this$contentHashCode) {
        return Arrays.hashCode($this$contentHashCode);
    }

    @SinceKotlin(version = "1.4")
    @JvmName(name = "contentHashCodeNullable")
    @InlineOnly
    private static final int contentHashCodeNullable(long[] $this$contentHashCode) {
        return Arrays.hashCode($this$contentHashCode);
    }

    @SinceKotlin(version = "1.4")
    @JvmName(name = "contentHashCodeNullable")
    @InlineOnly
    private static final int contentHashCodeNullable(float[] $this$contentHashCode) {
        return Arrays.hashCode($this$contentHashCode);
    }

    @SinceKotlin(version = "1.4")
    @JvmName(name = "contentHashCodeNullable")
    @InlineOnly
    private static final int contentHashCodeNullable(double[] $this$contentHashCode) {
        return Arrays.hashCode($this$contentHashCode);
    }

    @SinceKotlin(version = "1.4")
    @JvmName(name = "contentHashCodeNullable")
    @InlineOnly
    private static final int contentHashCodeNullable(boolean[] $this$contentHashCode) {
        return Arrays.hashCode($this$contentHashCode);
    }

    @SinceKotlin(version = "1.4")
    @JvmName(name = "contentHashCodeNullable")
    @InlineOnly
    private static final int contentHashCodeNullable(char[] $this$contentHashCode) {
        return Arrays.hashCode($this$contentHashCode);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    @Deprecated(message = "Use Kotlin compiler 1.4 to avoid deprecation warning.")
    @DeprecatedSinceKotlin(hiddenSince = "1.4")
    private static final /* synthetic */ <T> String contentToString(T[] tArr) {
        String string = Arrays.toString(tArr);
        Intrinsics.checkNotNullExpressionValue(string, "java.util.Arrays.toString(this)");
        return string;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    @Deprecated(message = "Use Kotlin compiler 1.4 to avoid deprecation warning.")
    @DeprecatedSinceKotlin(hiddenSince = "1.4")
    private static final /* synthetic */ String contentToString(byte[] $this$contentToString) {
        String string = Arrays.toString($this$contentToString);
        Intrinsics.checkNotNullExpressionValue(string, "java.util.Arrays.toString(this)");
        return string;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    @Deprecated(message = "Use Kotlin compiler 1.4 to avoid deprecation warning.")
    @DeprecatedSinceKotlin(hiddenSince = "1.4")
    private static final /* synthetic */ String contentToString(short[] $this$contentToString) {
        String string = Arrays.toString($this$contentToString);
        Intrinsics.checkNotNullExpressionValue(string, "java.util.Arrays.toString(this)");
        return string;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    @Deprecated(message = "Use Kotlin compiler 1.4 to avoid deprecation warning.")
    @DeprecatedSinceKotlin(hiddenSince = "1.4")
    private static final /* synthetic */ String contentToString(int[] $this$contentToString) {
        String string = Arrays.toString($this$contentToString);
        Intrinsics.checkNotNullExpressionValue(string, "java.util.Arrays.toString(this)");
        return string;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    @Deprecated(message = "Use Kotlin compiler 1.4 to avoid deprecation warning.")
    @DeprecatedSinceKotlin(hiddenSince = "1.4")
    private static final /* synthetic */ String contentToString(long[] $this$contentToString) {
        String string = Arrays.toString($this$contentToString);
        Intrinsics.checkNotNullExpressionValue(string, "java.util.Arrays.toString(this)");
        return string;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    @Deprecated(message = "Use Kotlin compiler 1.4 to avoid deprecation warning.")
    @DeprecatedSinceKotlin(hiddenSince = "1.4")
    private static final /* synthetic */ String contentToString(float[] $this$contentToString) {
        String string = Arrays.toString($this$contentToString);
        Intrinsics.checkNotNullExpressionValue(string, "java.util.Arrays.toString(this)");
        return string;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    @Deprecated(message = "Use Kotlin compiler 1.4 to avoid deprecation warning.")
    @DeprecatedSinceKotlin(hiddenSince = "1.4")
    private static final /* synthetic */ String contentToString(double[] $this$contentToString) {
        String string = Arrays.toString($this$contentToString);
        Intrinsics.checkNotNullExpressionValue(string, "java.util.Arrays.toString(this)");
        return string;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    @Deprecated(message = "Use Kotlin compiler 1.4 to avoid deprecation warning.")
    @DeprecatedSinceKotlin(hiddenSince = "1.4")
    private static final /* synthetic */ String contentToString(boolean[] $this$contentToString) {
        String string = Arrays.toString($this$contentToString);
        Intrinsics.checkNotNullExpressionValue(string, "java.util.Arrays.toString(this)");
        return string;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    @Deprecated(message = "Use Kotlin compiler 1.4 to avoid deprecation warning.")
    @DeprecatedSinceKotlin(hiddenSince = "1.4")
    private static final /* synthetic */ String contentToString(char[] $this$contentToString) {
        String string = Arrays.toString($this$contentToString);
        Intrinsics.checkNotNullExpressionValue(string, "java.util.Arrays.toString(this)");
        return string;
    }

    @SinceKotlin(version = "1.4")
    @JvmName(name = "contentToStringNullable")
    @InlineOnly
    private static final <T> String contentToStringNullable(T[] tArr) {
        String string = Arrays.toString(tArr);
        Intrinsics.checkNotNullExpressionValue(string, "java.util.Arrays.toString(this)");
        return string;
    }

    @SinceKotlin(version = "1.4")
    @JvmName(name = "contentToStringNullable")
    @InlineOnly
    private static final String contentToStringNullable(byte[] $this$contentToString) {
        String string = Arrays.toString($this$contentToString);
        Intrinsics.checkNotNullExpressionValue(string, "java.util.Arrays.toString(this)");
        return string;
    }

    @SinceKotlin(version = "1.4")
    @JvmName(name = "contentToStringNullable")
    @InlineOnly
    private static final String contentToStringNullable(short[] $this$contentToString) {
        String string = Arrays.toString($this$contentToString);
        Intrinsics.checkNotNullExpressionValue(string, "java.util.Arrays.toString(this)");
        return string;
    }

    @SinceKotlin(version = "1.4")
    @JvmName(name = "contentToStringNullable")
    @InlineOnly
    private static final String contentToStringNullable(int[] $this$contentToString) {
        String string = Arrays.toString($this$contentToString);
        Intrinsics.checkNotNullExpressionValue(string, "java.util.Arrays.toString(this)");
        return string;
    }

    @SinceKotlin(version = "1.4")
    @JvmName(name = "contentToStringNullable")
    @InlineOnly
    private static final String contentToStringNullable(long[] $this$contentToString) {
        String string = Arrays.toString($this$contentToString);
        Intrinsics.checkNotNullExpressionValue(string, "java.util.Arrays.toString(this)");
        return string;
    }

    @SinceKotlin(version = "1.4")
    @JvmName(name = "contentToStringNullable")
    @InlineOnly
    private static final String contentToStringNullable(float[] $this$contentToString) {
        String string = Arrays.toString($this$contentToString);
        Intrinsics.checkNotNullExpressionValue(string, "java.util.Arrays.toString(this)");
        return string;
    }

    @SinceKotlin(version = "1.4")
    @JvmName(name = "contentToStringNullable")
    @InlineOnly
    private static final String contentToStringNullable(double[] $this$contentToString) {
        String string = Arrays.toString($this$contentToString);
        Intrinsics.checkNotNullExpressionValue(string, "java.util.Arrays.toString(this)");
        return string;
    }

    @SinceKotlin(version = "1.4")
    @JvmName(name = "contentToStringNullable")
    @InlineOnly
    private static final String contentToStringNullable(boolean[] $this$contentToString) {
        String string = Arrays.toString($this$contentToString);
        Intrinsics.checkNotNullExpressionValue(string, "java.util.Arrays.toString(this)");
        return string;
    }

    @SinceKotlin(version = "1.4")
    @JvmName(name = "contentToStringNullable")
    @InlineOnly
    private static final String contentToStringNullable(char[] $this$contentToString) {
        String string = Arrays.toString($this$contentToString);
        Intrinsics.checkNotNullExpressionValue(string, "java.util.Arrays.toString(this)");
        return string;
    }

    public static /* synthetic */ Object[] copyInto$default(Object[] objArr, Object[] objArr2, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i = 0;
        }
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = objArr.length;
        }
        return ArraysKt.copyInto(objArr, objArr2, i, i2, i3);
    }

    @SinceKotlin(version = "1.3")
    @NotNull
    public static final <T> T[] copyInto(@NotNull T[] copyInto, @NotNull T[] destination, int destinationOffset, int startIndex, int endIndex) {
        Intrinsics.checkNotNullParameter(copyInto, "$this$copyInto");
        Intrinsics.checkNotNullParameter(destination, "destination");
        System.arraycopy(copyInto, startIndex, destination, destinationOffset, endIndex - startIndex);
        return destination;
    }

    public static /* synthetic */ byte[] copyInto$default(byte[] bArr, byte[] bArr2, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i = 0;
        }
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = bArr.length;
        }
        return ArraysKt.copyInto(bArr, bArr2, i, i2, i3);
    }

    @SinceKotlin(version = "1.3")
    @NotNull
    public static final byte[] copyInto(@NotNull byte[] copyInto, @NotNull byte[] destination, int destinationOffset, int startIndex, int endIndex) {
        Intrinsics.checkNotNullParameter(copyInto, "$this$copyInto");
        Intrinsics.checkNotNullParameter(destination, "destination");
        System.arraycopy(copyInto, startIndex, destination, destinationOffset, endIndex - startIndex);
        return destination;
    }

    public static /* synthetic */ short[] copyInto$default(short[] sArr, short[] sArr2, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i = 0;
        }
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = sArr.length;
        }
        return ArraysKt.copyInto(sArr, sArr2, i, i2, i3);
    }

    @SinceKotlin(version = "1.3")
    @NotNull
    public static final short[] copyInto(@NotNull short[] copyInto, @NotNull short[] destination, int destinationOffset, int startIndex, int endIndex) {
        Intrinsics.checkNotNullParameter(copyInto, "$this$copyInto");
        Intrinsics.checkNotNullParameter(destination, "destination");
        System.arraycopy(copyInto, startIndex, destination, destinationOffset, endIndex - startIndex);
        return destination;
    }

    public static /* synthetic */ int[] copyInto$default(int[] iArr, int[] iArr2, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i = 0;
        }
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = iArr.length;
        }
        return ArraysKt.copyInto(iArr, iArr2, i, i2, i3);
    }

    @SinceKotlin(version = "1.3")
    @NotNull
    public static final int[] copyInto(@NotNull int[] copyInto, @NotNull int[] destination, int destinationOffset, int startIndex, int endIndex) {
        Intrinsics.checkNotNullParameter(copyInto, "$this$copyInto");
        Intrinsics.checkNotNullParameter(destination, "destination");
        System.arraycopy(copyInto, startIndex, destination, destinationOffset, endIndex - startIndex);
        return destination;
    }

    public static /* synthetic */ long[] copyInto$default(long[] jArr, long[] jArr2, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i = 0;
        }
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = jArr.length;
        }
        return ArraysKt.copyInto(jArr, jArr2, i, i2, i3);
    }

    @SinceKotlin(version = "1.3")
    @NotNull
    public static final long[] copyInto(@NotNull long[] copyInto, @NotNull long[] destination, int destinationOffset, int startIndex, int endIndex) {
        Intrinsics.checkNotNullParameter(copyInto, "$this$copyInto");
        Intrinsics.checkNotNullParameter(destination, "destination");
        System.arraycopy(copyInto, startIndex, destination, destinationOffset, endIndex - startIndex);
        return destination;
    }

    public static /* synthetic */ float[] copyInto$default(float[] fArr, float[] fArr2, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i = 0;
        }
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = fArr.length;
        }
        return ArraysKt.copyInto(fArr, fArr2, i, i2, i3);
    }

    @SinceKotlin(version = "1.3")
    @NotNull
    public static final float[] copyInto(@NotNull float[] copyInto, @NotNull float[] destination, int destinationOffset, int startIndex, int endIndex) {
        Intrinsics.checkNotNullParameter(copyInto, "$this$copyInto");
        Intrinsics.checkNotNullParameter(destination, "destination");
        System.arraycopy(copyInto, startIndex, destination, destinationOffset, endIndex - startIndex);
        return destination;
    }

    public static /* synthetic */ double[] copyInto$default(double[] dArr, double[] dArr2, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i = 0;
        }
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = dArr.length;
        }
        return ArraysKt.copyInto(dArr, dArr2, i, i2, i3);
    }

    @SinceKotlin(version = "1.3")
    @NotNull
    public static final double[] copyInto(@NotNull double[] copyInto, @NotNull double[] destination, int destinationOffset, int startIndex, int endIndex) {
        Intrinsics.checkNotNullParameter(copyInto, "$this$copyInto");
        Intrinsics.checkNotNullParameter(destination, "destination");
        System.arraycopy(copyInto, startIndex, destination, destinationOffset, endIndex - startIndex);
        return destination;
    }

    public static /* synthetic */ boolean[] copyInto$default(boolean[] zArr, boolean[] zArr2, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i = 0;
        }
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = zArr.length;
        }
        return ArraysKt.copyInto(zArr, zArr2, i, i2, i3);
    }

    @SinceKotlin(version = "1.3")
    @NotNull
    public static final boolean[] copyInto(@NotNull boolean[] copyInto, @NotNull boolean[] destination, int destinationOffset, int startIndex, int endIndex) {
        Intrinsics.checkNotNullParameter(copyInto, "$this$copyInto");
        Intrinsics.checkNotNullParameter(destination, "destination");
        System.arraycopy(copyInto, startIndex, destination, destinationOffset, endIndex - startIndex);
        return destination;
    }

    public static /* synthetic */ char[] copyInto$default(char[] cArr, char[] cArr2, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i = 0;
        }
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = cArr.length;
        }
        return ArraysKt.copyInto(cArr, cArr2, i, i2, i3);
    }

    @SinceKotlin(version = "1.3")
    @NotNull
    public static final char[] copyInto(@NotNull char[] copyInto, @NotNull char[] destination, int destinationOffset, int startIndex, int endIndex) {
        Intrinsics.checkNotNullParameter(copyInto, "$this$copyInto");
        Intrinsics.checkNotNullParameter(destination, "destination");
        System.arraycopy(copyInto, startIndex, destination, destinationOffset, endIndex - startIndex);
        return destination;
    }

    @InlineOnly
    private static final <T> T[] copyOf(T[] tArr) {
        T[] tArr2 = (T[]) Arrays.copyOf(tArr, tArr.length);
        Intrinsics.checkNotNullExpressionValue(tArr2, "java.util.Arrays.copyOf(this, size)");
        return tArr2;
    }

    @InlineOnly
    private static final byte[] copyOf(byte[] $this$copyOf) {
        byte[] bArrCopyOf = Arrays.copyOf($this$copyOf, $this$copyOf.length);
        Intrinsics.checkNotNullExpressionValue(bArrCopyOf, "java.util.Arrays.copyOf(this, size)");
        return bArrCopyOf;
    }

    @InlineOnly
    private static final short[] copyOf(short[] $this$copyOf) {
        short[] sArrCopyOf = Arrays.copyOf($this$copyOf, $this$copyOf.length);
        Intrinsics.checkNotNullExpressionValue(sArrCopyOf, "java.util.Arrays.copyOf(this, size)");
        return sArrCopyOf;
    }

    @InlineOnly
    private static final int[] copyOf(int[] $this$copyOf) {
        int[] iArrCopyOf = Arrays.copyOf($this$copyOf, $this$copyOf.length);
        Intrinsics.checkNotNullExpressionValue(iArrCopyOf, "java.util.Arrays.copyOf(this, size)");
        return iArrCopyOf;
    }

    @InlineOnly
    private static final long[] copyOf(long[] $this$copyOf) {
        long[] jArrCopyOf = Arrays.copyOf($this$copyOf, $this$copyOf.length);
        Intrinsics.checkNotNullExpressionValue(jArrCopyOf, "java.util.Arrays.copyOf(this, size)");
        return jArrCopyOf;
    }

    @InlineOnly
    private static final float[] copyOf(float[] $this$copyOf) {
        float[] fArrCopyOf = Arrays.copyOf($this$copyOf, $this$copyOf.length);
        Intrinsics.checkNotNullExpressionValue(fArrCopyOf, "java.util.Arrays.copyOf(this, size)");
        return fArrCopyOf;
    }

    @InlineOnly
    private static final double[] copyOf(double[] $this$copyOf) {
        double[] dArrCopyOf = Arrays.copyOf($this$copyOf, $this$copyOf.length);
        Intrinsics.checkNotNullExpressionValue(dArrCopyOf, "java.util.Arrays.copyOf(this, size)");
        return dArrCopyOf;
    }

    @InlineOnly
    private static final boolean[] copyOf(boolean[] $this$copyOf) {
        boolean[] zArrCopyOf = Arrays.copyOf($this$copyOf, $this$copyOf.length);
        Intrinsics.checkNotNullExpressionValue(zArrCopyOf, "java.util.Arrays.copyOf(this, size)");
        return zArrCopyOf;
    }

    @InlineOnly
    private static final char[] copyOf(char[] $this$copyOf) {
        char[] cArrCopyOf = Arrays.copyOf($this$copyOf, $this$copyOf.length);
        Intrinsics.checkNotNullExpressionValue(cArrCopyOf, "java.util.Arrays.copyOf(this, size)");
        return cArrCopyOf;
    }

    @InlineOnly
    private static final byte[] copyOf(byte[] $this$copyOf, int newSize) {
        byte[] bArrCopyOf = Arrays.copyOf($this$copyOf, newSize);
        Intrinsics.checkNotNullExpressionValue(bArrCopyOf, "java.util.Arrays.copyOf(this, newSize)");
        return bArrCopyOf;
    }

    @InlineOnly
    private static final short[] copyOf(short[] $this$copyOf, int newSize) {
        short[] sArrCopyOf = Arrays.copyOf($this$copyOf, newSize);
        Intrinsics.checkNotNullExpressionValue(sArrCopyOf, "java.util.Arrays.copyOf(this, newSize)");
        return sArrCopyOf;
    }

    @InlineOnly
    private static final int[] copyOf(int[] $this$copyOf, int newSize) {
        int[] iArrCopyOf = Arrays.copyOf($this$copyOf, newSize);
        Intrinsics.checkNotNullExpressionValue(iArrCopyOf, "java.util.Arrays.copyOf(this, newSize)");
        return iArrCopyOf;
    }

    @InlineOnly
    private static final long[] copyOf(long[] $this$copyOf, int newSize) {
        long[] jArrCopyOf = Arrays.copyOf($this$copyOf, newSize);
        Intrinsics.checkNotNullExpressionValue(jArrCopyOf, "java.util.Arrays.copyOf(this, newSize)");
        return jArrCopyOf;
    }

    @InlineOnly
    private static final float[] copyOf(float[] $this$copyOf, int newSize) {
        float[] fArrCopyOf = Arrays.copyOf($this$copyOf, newSize);
        Intrinsics.checkNotNullExpressionValue(fArrCopyOf, "java.util.Arrays.copyOf(this, newSize)");
        return fArrCopyOf;
    }

    @InlineOnly
    private static final double[] copyOf(double[] $this$copyOf, int newSize) {
        double[] dArrCopyOf = Arrays.copyOf($this$copyOf, newSize);
        Intrinsics.checkNotNullExpressionValue(dArrCopyOf, "java.util.Arrays.copyOf(this, newSize)");
        return dArrCopyOf;
    }

    @InlineOnly
    private static final boolean[] copyOf(boolean[] $this$copyOf, int newSize) {
        boolean[] zArrCopyOf = Arrays.copyOf($this$copyOf, newSize);
        Intrinsics.checkNotNullExpressionValue(zArrCopyOf, "java.util.Arrays.copyOf(this, newSize)");
        return zArrCopyOf;
    }

    @InlineOnly
    private static final char[] copyOf(char[] $this$copyOf, int newSize) {
        char[] cArrCopyOf = Arrays.copyOf($this$copyOf, newSize);
        Intrinsics.checkNotNullExpressionValue(cArrCopyOf, "java.util.Arrays.copyOf(this, newSize)");
        return cArrCopyOf;
    }

    @InlineOnly
    private static final <T> T[] copyOf(T[] tArr, int i) {
        T[] tArr2 = (T[]) Arrays.copyOf(tArr, i);
        Intrinsics.checkNotNullExpressionValue(tArr2, "java.util.Arrays.copyOf(this, newSize)");
        return tArr2;
    }

    @JvmName(name = "copyOfRangeInline")
    @InlineOnly
    private static final <T> T[] copyOfRangeInline(T[] tArr, int i, int i2) {
        if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 3, 0)) {
            return (T[]) ArraysKt.copyOfRange(tArr, i, i2);
        }
        if (i2 > tArr.length) {
            throw new IndexOutOfBoundsException("toIndex: " + i2 + ", size: " + tArr.length);
        }
        T[] tArr2 = (T[]) Arrays.copyOfRange(tArr, i, i2);
        Intrinsics.checkNotNullExpressionValue(tArr2, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
        return tArr2;
    }

    @JvmName(name = "copyOfRangeInline")
    @InlineOnly
    private static final byte[] copyOfRangeInline(byte[] $this$copyOfRange, int fromIndex, int toIndex) {
        if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 3, 0)) {
            return ArraysKt.copyOfRange($this$copyOfRange, fromIndex, toIndex);
        }
        if (toIndex > $this$copyOfRange.length) {
            throw new IndexOutOfBoundsException("toIndex: " + toIndex + ", size: " + $this$copyOfRange.length);
        }
        byte[] bArrCopyOfRange = Arrays.copyOfRange($this$copyOfRange, fromIndex, toIndex);
        Intrinsics.checkNotNullExpressionValue(bArrCopyOfRange, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
        return bArrCopyOfRange;
    }

    @JvmName(name = "copyOfRangeInline")
    @InlineOnly
    private static final short[] copyOfRangeInline(short[] $this$copyOfRange, int fromIndex, int toIndex) {
        if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 3, 0)) {
            return ArraysKt.copyOfRange($this$copyOfRange, fromIndex, toIndex);
        }
        if (toIndex > $this$copyOfRange.length) {
            throw new IndexOutOfBoundsException("toIndex: " + toIndex + ", size: " + $this$copyOfRange.length);
        }
        short[] sArrCopyOfRange = Arrays.copyOfRange($this$copyOfRange, fromIndex, toIndex);
        Intrinsics.checkNotNullExpressionValue(sArrCopyOfRange, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
        return sArrCopyOfRange;
    }

    @JvmName(name = "copyOfRangeInline")
    @InlineOnly
    private static final int[] copyOfRangeInline(int[] $this$copyOfRange, int fromIndex, int toIndex) {
        if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 3, 0)) {
            return ArraysKt.copyOfRange($this$copyOfRange, fromIndex, toIndex);
        }
        if (toIndex > $this$copyOfRange.length) {
            throw new IndexOutOfBoundsException("toIndex: " + toIndex + ", size: " + $this$copyOfRange.length);
        }
        int[] iArrCopyOfRange = Arrays.copyOfRange($this$copyOfRange, fromIndex, toIndex);
        Intrinsics.checkNotNullExpressionValue(iArrCopyOfRange, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
        return iArrCopyOfRange;
    }

    @JvmName(name = "copyOfRangeInline")
    @InlineOnly
    private static final long[] copyOfRangeInline(long[] $this$copyOfRange, int fromIndex, int toIndex) {
        if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 3, 0)) {
            return ArraysKt.copyOfRange($this$copyOfRange, fromIndex, toIndex);
        }
        if (toIndex > $this$copyOfRange.length) {
            throw new IndexOutOfBoundsException("toIndex: " + toIndex + ", size: " + $this$copyOfRange.length);
        }
        long[] jArrCopyOfRange = Arrays.copyOfRange($this$copyOfRange, fromIndex, toIndex);
        Intrinsics.checkNotNullExpressionValue(jArrCopyOfRange, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
        return jArrCopyOfRange;
    }

    @JvmName(name = "copyOfRangeInline")
    @InlineOnly
    private static final float[] copyOfRangeInline(float[] $this$copyOfRange, int fromIndex, int toIndex) {
        if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 3, 0)) {
            return ArraysKt.copyOfRange($this$copyOfRange, fromIndex, toIndex);
        }
        if (toIndex > $this$copyOfRange.length) {
            throw new IndexOutOfBoundsException("toIndex: " + toIndex + ", size: " + $this$copyOfRange.length);
        }
        float[] fArrCopyOfRange = Arrays.copyOfRange($this$copyOfRange, fromIndex, toIndex);
        Intrinsics.checkNotNullExpressionValue(fArrCopyOfRange, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
        return fArrCopyOfRange;
    }

    @JvmName(name = "copyOfRangeInline")
    @InlineOnly
    private static final double[] copyOfRangeInline(double[] $this$copyOfRange, int fromIndex, int toIndex) {
        if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 3, 0)) {
            return ArraysKt.copyOfRange($this$copyOfRange, fromIndex, toIndex);
        }
        if (toIndex > $this$copyOfRange.length) {
            throw new IndexOutOfBoundsException("toIndex: " + toIndex + ", size: " + $this$copyOfRange.length);
        }
        double[] dArrCopyOfRange = Arrays.copyOfRange($this$copyOfRange, fromIndex, toIndex);
        Intrinsics.checkNotNullExpressionValue(dArrCopyOfRange, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
        return dArrCopyOfRange;
    }

    @JvmName(name = "copyOfRangeInline")
    @InlineOnly
    private static final boolean[] copyOfRangeInline(boolean[] $this$copyOfRange, int fromIndex, int toIndex) {
        if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 3, 0)) {
            return ArraysKt.copyOfRange($this$copyOfRange, fromIndex, toIndex);
        }
        if (toIndex > $this$copyOfRange.length) {
            throw new IndexOutOfBoundsException("toIndex: " + toIndex + ", size: " + $this$copyOfRange.length);
        }
        boolean[] zArrCopyOfRange = Arrays.copyOfRange($this$copyOfRange, fromIndex, toIndex);
        Intrinsics.checkNotNullExpressionValue(zArrCopyOfRange, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
        return zArrCopyOfRange;
    }

    @JvmName(name = "copyOfRangeInline")
    @InlineOnly
    private static final char[] copyOfRangeInline(char[] $this$copyOfRange, int fromIndex, int toIndex) {
        if (PlatformImplementationsKt.apiVersionIsAtLeast(1, 3, 0)) {
            return ArraysKt.copyOfRange($this$copyOfRange, fromIndex, toIndex);
        }
        if (toIndex > $this$copyOfRange.length) {
            throw new IndexOutOfBoundsException("toIndex: " + toIndex + ", size: " + $this$copyOfRange.length);
        }
        char[] cArrCopyOfRange = Arrays.copyOfRange($this$copyOfRange, fromIndex, toIndex);
        Intrinsics.checkNotNullExpressionValue(cArrCopyOfRange, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
        return cArrCopyOfRange;
    }

    @SinceKotlin(version = "1.3")
    @JvmName(name = "copyOfRange")
    @NotNull
    @PublishedApi
    public static final <T> T[] copyOfRange(@NotNull T[] copyOfRangeImpl, int i, int i2) {
        Intrinsics.checkNotNullParameter(copyOfRangeImpl, "$this$copyOfRangeImpl");
        ArraysKt.copyOfRangeToIndexCheck(i2, copyOfRangeImpl.length);
        T[] tArr = (T[]) Arrays.copyOfRange(copyOfRangeImpl, i, i2);
        Intrinsics.checkNotNullExpressionValue(tArr, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
        return tArr;
    }

    @SinceKotlin(version = "1.3")
    @JvmName(name = "copyOfRange")
    @NotNull
    @PublishedApi
    public static final byte[] copyOfRange(@NotNull byte[] copyOfRangeImpl, int fromIndex, int toIndex) {
        Intrinsics.checkNotNullParameter(copyOfRangeImpl, "$this$copyOfRangeImpl");
        ArraysKt.copyOfRangeToIndexCheck(toIndex, copyOfRangeImpl.length);
        byte[] bArrCopyOfRange = Arrays.copyOfRange(copyOfRangeImpl, fromIndex, toIndex);
        Intrinsics.checkNotNullExpressionValue(bArrCopyOfRange, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
        return bArrCopyOfRange;
    }

    @SinceKotlin(version = "1.3")
    @JvmName(name = "copyOfRange")
    @NotNull
    @PublishedApi
    public static final short[] copyOfRange(@NotNull short[] copyOfRangeImpl, int fromIndex, int toIndex) {
        Intrinsics.checkNotNullParameter(copyOfRangeImpl, "$this$copyOfRangeImpl");
        ArraysKt.copyOfRangeToIndexCheck(toIndex, copyOfRangeImpl.length);
        short[] sArrCopyOfRange = Arrays.copyOfRange(copyOfRangeImpl, fromIndex, toIndex);
        Intrinsics.checkNotNullExpressionValue(sArrCopyOfRange, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
        return sArrCopyOfRange;
    }

    @SinceKotlin(version = "1.3")
    @JvmName(name = "copyOfRange")
    @NotNull
    @PublishedApi
    public static final int[] copyOfRange(@NotNull int[] copyOfRangeImpl, int fromIndex, int toIndex) {
        Intrinsics.checkNotNullParameter(copyOfRangeImpl, "$this$copyOfRangeImpl");
        ArraysKt.copyOfRangeToIndexCheck(toIndex, copyOfRangeImpl.length);
        int[] iArrCopyOfRange = Arrays.copyOfRange(copyOfRangeImpl, fromIndex, toIndex);
        Intrinsics.checkNotNullExpressionValue(iArrCopyOfRange, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
        return iArrCopyOfRange;
    }

    @SinceKotlin(version = "1.3")
    @JvmName(name = "copyOfRange")
    @NotNull
    @PublishedApi
    public static final long[] copyOfRange(@NotNull long[] copyOfRangeImpl, int fromIndex, int toIndex) {
        Intrinsics.checkNotNullParameter(copyOfRangeImpl, "$this$copyOfRangeImpl");
        ArraysKt.copyOfRangeToIndexCheck(toIndex, copyOfRangeImpl.length);
        long[] jArrCopyOfRange = Arrays.copyOfRange(copyOfRangeImpl, fromIndex, toIndex);
        Intrinsics.checkNotNullExpressionValue(jArrCopyOfRange, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
        return jArrCopyOfRange;
    }

    @SinceKotlin(version = "1.3")
    @JvmName(name = "copyOfRange")
    @NotNull
    @PublishedApi
    public static final float[] copyOfRange(@NotNull float[] copyOfRangeImpl, int fromIndex, int toIndex) {
        Intrinsics.checkNotNullParameter(copyOfRangeImpl, "$this$copyOfRangeImpl");
        ArraysKt.copyOfRangeToIndexCheck(toIndex, copyOfRangeImpl.length);
        float[] fArrCopyOfRange = Arrays.copyOfRange(copyOfRangeImpl, fromIndex, toIndex);
        Intrinsics.checkNotNullExpressionValue(fArrCopyOfRange, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
        return fArrCopyOfRange;
    }

    @SinceKotlin(version = "1.3")
    @JvmName(name = "copyOfRange")
    @NotNull
    @PublishedApi
    public static final double[] copyOfRange(@NotNull double[] copyOfRangeImpl, int fromIndex, int toIndex) {
        Intrinsics.checkNotNullParameter(copyOfRangeImpl, "$this$copyOfRangeImpl");
        ArraysKt.copyOfRangeToIndexCheck(toIndex, copyOfRangeImpl.length);
        double[] dArrCopyOfRange = Arrays.copyOfRange(copyOfRangeImpl, fromIndex, toIndex);
        Intrinsics.checkNotNullExpressionValue(dArrCopyOfRange, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
        return dArrCopyOfRange;
    }

    @SinceKotlin(version = "1.3")
    @JvmName(name = "copyOfRange")
    @NotNull
    @PublishedApi
    public static final boolean[] copyOfRange(@NotNull boolean[] copyOfRangeImpl, int fromIndex, int toIndex) {
        Intrinsics.checkNotNullParameter(copyOfRangeImpl, "$this$copyOfRangeImpl");
        ArraysKt.copyOfRangeToIndexCheck(toIndex, copyOfRangeImpl.length);
        boolean[] zArrCopyOfRange = Arrays.copyOfRange(copyOfRangeImpl, fromIndex, toIndex);
        Intrinsics.checkNotNullExpressionValue(zArrCopyOfRange, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
        return zArrCopyOfRange;
    }

    @SinceKotlin(version = "1.3")
    @JvmName(name = "copyOfRange")
    @NotNull
    @PublishedApi
    public static final char[] copyOfRange(@NotNull char[] copyOfRangeImpl, int fromIndex, int toIndex) {
        Intrinsics.checkNotNullParameter(copyOfRangeImpl, "$this$copyOfRangeImpl");
        ArraysKt.copyOfRangeToIndexCheck(toIndex, copyOfRangeImpl.length);
        char[] cArrCopyOfRange = Arrays.copyOfRange(copyOfRangeImpl, fromIndex, toIndex);
        Intrinsics.checkNotNullExpressionValue(cArrCopyOfRange, "java.util.Arrays.copyOfR…this, fromIndex, toIndex)");
        return cArrCopyOfRange;
    }

    public static /* synthetic */ void fill$default(Object[] objArr, Object obj, int i, int i2, int i3, Object obj2) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = objArr.length;
        }
        ArraysKt.fill(objArr, obj, i, i2);
    }

    public static final <T> void fill(@NotNull T[] fill, T t, int fromIndex, int toIndex) {
        Intrinsics.checkNotNullParameter(fill, "$this$fill");
        Arrays.fill(fill, fromIndex, toIndex, t);
    }

    public static /* synthetic */ void fill$default(byte[] bArr, byte b, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = bArr.length;
        }
        ArraysKt.fill(bArr, b, i, i2);
    }

    public static final void fill(@NotNull byte[] fill, byte element, int fromIndex, int toIndex) {
        Intrinsics.checkNotNullParameter(fill, "$this$fill");
        Arrays.fill(fill, fromIndex, toIndex, element);
    }

    public static /* synthetic */ void fill$default(short[] sArr, short s, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = sArr.length;
        }
        ArraysKt.fill(sArr, s, i, i2);
    }

    public static final void fill(@NotNull short[] fill, short element, int fromIndex, int toIndex) {
        Intrinsics.checkNotNullParameter(fill, "$this$fill");
        Arrays.fill(fill, fromIndex, toIndex, element);
    }

    public static /* synthetic */ void fill$default(int[] iArr, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i2 = 0;
        }
        if ((i4 & 4) != 0) {
            i3 = iArr.length;
        }
        ArraysKt.fill(iArr, i, i2, i3);
    }

    public static final void fill(@NotNull int[] fill, int element, int fromIndex, int toIndex) {
        Intrinsics.checkNotNullParameter(fill, "$this$fill");
        Arrays.fill(fill, fromIndex, toIndex, element);
    }

    public static /* synthetic */ void fill$default(long[] jArr, long j, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = jArr.length;
        }
        ArraysKt.fill(jArr, j, i, i2);
    }

    public static final void fill(@NotNull long[] fill, long element, int fromIndex, int toIndex) {
        Intrinsics.checkNotNullParameter(fill, "$this$fill");
        Arrays.fill(fill, fromIndex, toIndex, element);
    }

    public static /* synthetic */ void fill$default(float[] fArr, float f, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = fArr.length;
        }
        ArraysKt.fill(fArr, f, i, i2);
    }

    public static final void fill(@NotNull float[] fill, float element, int fromIndex, int toIndex) {
        Intrinsics.checkNotNullParameter(fill, "$this$fill");
        Arrays.fill(fill, fromIndex, toIndex, element);
    }

    public static /* synthetic */ void fill$default(double[] dArr, double d, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = dArr.length;
        }
        ArraysKt.fill(dArr, d, i, i2);
    }

    public static final void fill(@NotNull double[] fill, double element, int fromIndex, int toIndex) {
        Intrinsics.checkNotNullParameter(fill, "$this$fill");
        Arrays.fill(fill, fromIndex, toIndex, element);
    }

    public static /* synthetic */ void fill$default(boolean[] zArr, boolean z, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = zArr.length;
        }
        ArraysKt.fill(zArr, z, i, i2);
    }

    public static final void fill(@NotNull boolean[] fill, boolean element, int fromIndex, int toIndex) {
        Intrinsics.checkNotNullParameter(fill, "$this$fill");
        Arrays.fill(fill, fromIndex, toIndex, element);
    }

    public static /* synthetic */ void fill$default(char[] cArr, char c, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = cArr.length;
        }
        ArraysKt.fill(cArr, c, i, i2);
    }

    public static final void fill(@NotNull char[] fill, char element, int fromIndex, int toIndex) {
        Intrinsics.checkNotNullParameter(fill, "$this$fill");
        Arrays.fill(fill, fromIndex, toIndex, element);
    }

    @NotNull
    public static final <T> T[] plus(@NotNull T[] plus, T t) {
        Intrinsics.checkNotNullParameter(plus, "$this$plus");
        int length = plus.length;
        T[] result = (T[]) Arrays.copyOf(plus, length + 1);
        result[length] = t;
        Intrinsics.checkNotNullExpressionValue(result, "result");
        return result;
    }

    @NotNull
    public static final byte[] plus(@NotNull byte[] plus, byte element) {
        Intrinsics.checkNotNullParameter(plus, "$this$plus");
        int index = plus.length;
        byte[] result = Arrays.copyOf(plus, index + 1);
        result[index] = element;
        Intrinsics.checkNotNullExpressionValue(result, "result");
        return result;
    }

    @NotNull
    public static final short[] plus(@NotNull short[] plus, short element) {
        Intrinsics.checkNotNullParameter(plus, "$this$plus");
        int index = plus.length;
        short[] result = Arrays.copyOf(plus, index + 1);
        result[index] = element;
        Intrinsics.checkNotNullExpressionValue(result, "result");
        return result;
    }

    @NotNull
    public static final int[] plus(@NotNull int[] plus, int element) {
        Intrinsics.checkNotNullParameter(plus, "$this$plus");
        int index = plus.length;
        int[] result = Arrays.copyOf(plus, index + 1);
        result[index] = element;
        Intrinsics.checkNotNullExpressionValue(result, "result");
        return result;
    }

    @NotNull
    public static final long[] plus(@NotNull long[] plus, long element) {
        Intrinsics.checkNotNullParameter(plus, "$this$plus");
        int index = plus.length;
        long[] result = Arrays.copyOf(plus, index + 1);
        result[index] = element;
        Intrinsics.checkNotNullExpressionValue(result, "result");
        return result;
    }

    @NotNull
    public static final float[] plus(@NotNull float[] plus, float element) {
        Intrinsics.checkNotNullParameter(plus, "$this$plus");
        int index = plus.length;
        float[] result = Arrays.copyOf(plus, index + 1);
        result[index] = element;
        Intrinsics.checkNotNullExpressionValue(result, "result");
        return result;
    }

    @NotNull
    public static final double[] plus(@NotNull double[] plus, double element) {
        Intrinsics.checkNotNullParameter(plus, "$this$plus");
        int index = plus.length;
        double[] result = Arrays.copyOf(plus, index + 1);
        result[index] = element;
        Intrinsics.checkNotNullExpressionValue(result, "result");
        return result;
    }

    @NotNull
    public static final boolean[] plus(@NotNull boolean[] plus, boolean element) {
        Intrinsics.checkNotNullParameter(plus, "$this$plus");
        int index = plus.length;
        boolean[] result = Arrays.copyOf(plus, index + 1);
        result[index] = element;
        Intrinsics.checkNotNullExpressionValue(result, "result");
        return result;
    }

    @NotNull
    public static final char[] plus(@NotNull char[] plus, char element) {
        Intrinsics.checkNotNullParameter(plus, "$this$plus");
        int index = plus.length;
        char[] result = Arrays.copyOf(plus, index + 1);
        result[index] = element;
        Intrinsics.checkNotNullExpressionValue(result, "result");
        return result;
    }

    @NotNull
    public static final <T> T[] plus(@NotNull T[] plus, @NotNull Collection<? extends T> elements) {
        Intrinsics.checkNotNullParameter(plus, "$this$plus");
        Intrinsics.checkNotNullParameter(elements, "elements");
        int length = plus.length;
        T[] result = (T[]) Arrays.copyOf(plus, length + elements.size());
        Iterator<? extends T> it = elements.iterator();
        while (it.hasNext()) {
            int i = length;
            length++;
            result[i] = it.next();
        }
        Intrinsics.checkNotNullExpressionValue(result, "result");
        return result;
    }

    @NotNull
    public static final byte[] plus(@NotNull byte[] plus, @NotNull Collection<Byte> elements) {
        Intrinsics.checkNotNullParameter(plus, "$this$plus");
        Intrinsics.checkNotNullParameter(elements, "elements");
        int index = plus.length;
        byte[] result = Arrays.copyOf(plus, index + elements.size());
        Iterator<Byte> it = elements.iterator();
        while (it.hasNext()) {
            byte element = it.next().byteValue();
            int i = index;
            index++;
            result[i] = element;
        }
        Intrinsics.checkNotNullExpressionValue(result, "result");
        return result;
    }

    @NotNull
    public static final short[] plus(@NotNull short[] plus, @NotNull Collection<Short> elements) {
        Intrinsics.checkNotNullParameter(plus, "$this$plus");
        Intrinsics.checkNotNullParameter(elements, "elements");
        int index = plus.length;
        short[] result = Arrays.copyOf(plus, index + elements.size());
        Iterator<Short> it = elements.iterator();
        while (it.hasNext()) {
            short element = it.next().shortValue();
            int i = index;
            index++;
            result[i] = element;
        }
        Intrinsics.checkNotNullExpressionValue(result, "result");
        return result;
    }

    @NotNull
    public static final int[] plus(@NotNull int[] plus, @NotNull Collection<Integer> elements) {
        Intrinsics.checkNotNullParameter(plus, "$this$plus");
        Intrinsics.checkNotNullParameter(elements, "elements");
        int index = plus.length;
        int[] result = Arrays.copyOf(plus, index + elements.size());
        Iterator<Integer> it = elements.iterator();
        while (it.hasNext()) {
            int element = it.next().intValue();
            int i = index;
            index++;
            result[i] = element;
        }
        Intrinsics.checkNotNullExpressionValue(result, "result");
        return result;
    }

    @NotNull
    public static final long[] plus(@NotNull long[] plus, @NotNull Collection<Long> elements) {
        Intrinsics.checkNotNullParameter(plus, "$this$plus");
        Intrinsics.checkNotNullParameter(elements, "elements");
        int index = plus.length;
        long[] result = Arrays.copyOf(plus, index + elements.size());
        Iterator<Long> it = elements.iterator();
        while (it.hasNext()) {
            long element = it.next().longValue();
            int i = index;
            index++;
            result[i] = element;
        }
        Intrinsics.checkNotNullExpressionValue(result, "result");
        return result;
    }

    @NotNull
    public static final float[] plus(@NotNull float[] plus, @NotNull Collection<Float> elements) {
        Intrinsics.checkNotNullParameter(plus, "$this$plus");
        Intrinsics.checkNotNullParameter(elements, "elements");
        int index = plus.length;
        float[] result = Arrays.copyOf(plus, index + elements.size());
        Iterator<Float> it = elements.iterator();
        while (it.hasNext()) {
            float element = it.next().floatValue();
            int i = index;
            index++;
            result[i] = element;
        }
        Intrinsics.checkNotNullExpressionValue(result, "result");
        return result;
    }

    @NotNull
    public static final double[] plus(@NotNull double[] plus, @NotNull Collection<Double> elements) {
        Intrinsics.checkNotNullParameter(plus, "$this$plus");
        Intrinsics.checkNotNullParameter(elements, "elements");
        int index = plus.length;
        double[] result = Arrays.copyOf(plus, index + elements.size());
        Iterator<Double> it = elements.iterator();
        while (it.hasNext()) {
            double element = it.next().doubleValue();
            int i = index;
            index++;
            result[i] = element;
        }
        Intrinsics.checkNotNullExpressionValue(result, "result");
        return result;
    }

    @NotNull
    public static final boolean[] plus(@NotNull boolean[] plus, @NotNull Collection<Boolean> elements) {
        Intrinsics.checkNotNullParameter(plus, "$this$plus");
        Intrinsics.checkNotNullParameter(elements, "elements");
        int index = plus.length;
        boolean[] result = Arrays.copyOf(plus, index + elements.size());
        Iterator<Boolean> it = elements.iterator();
        while (it.hasNext()) {
            boolean element = it.next().booleanValue();
            int i = index;
            index++;
            result[i] = element;
        }
        Intrinsics.checkNotNullExpressionValue(result, "result");
        return result;
    }

    @NotNull
    public static final char[] plus(@NotNull char[] plus, @NotNull Collection<Character> elements) {
        Intrinsics.checkNotNullParameter(plus, "$this$plus");
        Intrinsics.checkNotNullParameter(elements, "elements");
        int index = plus.length;
        char[] result = Arrays.copyOf(plus, index + elements.size());
        Iterator<Character> it = elements.iterator();
        while (it.hasNext()) {
            char element = it.next().charValue();
            int i = index;
            index++;
            result[i] = element;
        }
        Intrinsics.checkNotNullExpressionValue(result, "result");
        return result;
    }

    @NotNull
    public static final <T> T[] plus(@NotNull T[] plus, @NotNull T[] elements) {
        Intrinsics.checkNotNullParameter(plus, "$this$plus");
        Intrinsics.checkNotNullParameter(elements, "elements");
        int length = plus.length;
        int length2 = elements.length;
        T[] result = (T[]) Arrays.copyOf(plus, length + length2);
        System.arraycopy(elements, 0, result, length, length2);
        Intrinsics.checkNotNullExpressionValue(result, "result");
        return result;
    }

    @NotNull
    public static final byte[] plus(@NotNull byte[] plus, @NotNull byte[] elements) {
        Intrinsics.checkNotNullParameter(plus, "$this$plus");
        Intrinsics.checkNotNullParameter(elements, "elements");
        int thisSize = plus.length;
        int arraySize = elements.length;
        byte[] result = Arrays.copyOf(plus, thisSize + arraySize);
        System.arraycopy(elements, 0, result, thisSize, arraySize);
        Intrinsics.checkNotNullExpressionValue(result, "result");
        return result;
    }

    @NotNull
    public static final short[] plus(@NotNull short[] plus, @NotNull short[] elements) {
        Intrinsics.checkNotNullParameter(plus, "$this$plus");
        Intrinsics.checkNotNullParameter(elements, "elements");
        int thisSize = plus.length;
        int arraySize = elements.length;
        short[] result = Arrays.copyOf(plus, thisSize + arraySize);
        System.arraycopy(elements, 0, result, thisSize, arraySize);
        Intrinsics.checkNotNullExpressionValue(result, "result");
        return result;
    }

    @NotNull
    public static final int[] plus(@NotNull int[] plus, @NotNull int[] elements) {
        Intrinsics.checkNotNullParameter(plus, "$this$plus");
        Intrinsics.checkNotNullParameter(elements, "elements");
        int thisSize = plus.length;
        int arraySize = elements.length;
        int[] result = Arrays.copyOf(plus, thisSize + arraySize);
        System.arraycopy(elements, 0, result, thisSize, arraySize);
        Intrinsics.checkNotNullExpressionValue(result, "result");
        return result;
    }

    @NotNull
    public static final long[] plus(@NotNull long[] plus, @NotNull long[] elements) {
        Intrinsics.checkNotNullParameter(plus, "$this$plus");
        Intrinsics.checkNotNullParameter(elements, "elements");
        int thisSize = plus.length;
        int arraySize = elements.length;
        long[] result = Arrays.copyOf(plus, thisSize + arraySize);
        System.arraycopy(elements, 0, result, thisSize, arraySize);
        Intrinsics.checkNotNullExpressionValue(result, "result");
        return result;
    }

    @NotNull
    public static final float[] plus(@NotNull float[] plus, @NotNull float[] elements) {
        Intrinsics.checkNotNullParameter(plus, "$this$plus");
        Intrinsics.checkNotNullParameter(elements, "elements");
        int thisSize = plus.length;
        int arraySize = elements.length;
        float[] result = Arrays.copyOf(plus, thisSize + arraySize);
        System.arraycopy(elements, 0, result, thisSize, arraySize);
        Intrinsics.checkNotNullExpressionValue(result, "result");
        return result;
    }

    @NotNull
    public static final double[] plus(@NotNull double[] plus, @NotNull double[] elements) {
        Intrinsics.checkNotNullParameter(plus, "$this$plus");
        Intrinsics.checkNotNullParameter(elements, "elements");
        int thisSize = plus.length;
        int arraySize = elements.length;
        double[] result = Arrays.copyOf(plus, thisSize + arraySize);
        System.arraycopy(elements, 0, result, thisSize, arraySize);
        Intrinsics.checkNotNullExpressionValue(result, "result");
        return result;
    }

    @NotNull
    public static final boolean[] plus(@NotNull boolean[] plus, @NotNull boolean[] elements) {
        Intrinsics.checkNotNullParameter(plus, "$this$plus");
        Intrinsics.checkNotNullParameter(elements, "elements");
        int thisSize = plus.length;
        int arraySize = elements.length;
        boolean[] result = Arrays.copyOf(plus, thisSize + arraySize);
        System.arraycopy(elements, 0, result, thisSize, arraySize);
        Intrinsics.checkNotNullExpressionValue(result, "result");
        return result;
    }

    @NotNull
    public static final char[] plus(@NotNull char[] plus, @NotNull char[] elements) {
        Intrinsics.checkNotNullParameter(plus, "$this$plus");
        Intrinsics.checkNotNullParameter(elements, "elements");
        int thisSize = plus.length;
        int arraySize = elements.length;
        char[] result = Arrays.copyOf(plus, thisSize + arraySize);
        System.arraycopy(elements, 0, result, thisSize, arraySize);
        Intrinsics.checkNotNullExpressionValue(result, "result");
        return result;
    }

    @InlineOnly
    private static final <T> T[] plusElement(T[] tArr, T t) {
        return (T[]) ArraysKt.plus(tArr, t);
    }

    public static final void sort(@NotNull int[] sort) {
        Intrinsics.checkNotNullParameter(sort, "$this$sort");
        if (sort.length > 1) {
            Arrays.sort(sort);
        }
    }

    public static final void sort(@NotNull long[] sort) {
        Intrinsics.checkNotNullParameter(sort, "$this$sort");
        if (sort.length > 1) {
            Arrays.sort(sort);
        }
    }

    public static final void sort(@NotNull byte[] sort) {
        Intrinsics.checkNotNullParameter(sort, "$this$sort");
        if (sort.length > 1) {
            Arrays.sort(sort);
        }
    }

    public static final void sort(@NotNull short[] sort) {
        Intrinsics.checkNotNullParameter(sort, "$this$sort");
        if (sort.length > 1) {
            Arrays.sort(sort);
        }
    }

    public static final void sort(@NotNull double[] sort) {
        Intrinsics.checkNotNullParameter(sort, "$this$sort");
        if (sort.length > 1) {
            Arrays.sort(sort);
        }
    }

    public static final void sort(@NotNull float[] sort) {
        Intrinsics.checkNotNullParameter(sort, "$this$sort");
        if (sort.length > 1) {
            Arrays.sort(sort);
        }
    }

    public static final void sort(@NotNull char[] sort) {
        Intrinsics.checkNotNullParameter(sort, "$this$sort");
        if (sort.length > 1) {
            Arrays.sort(sort);
        }
    }

    @InlineOnly
    private static final <T extends Comparable<? super T>> void sort(T[] tArr) {
        if (tArr == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
        }
        ArraysKt.sort((Object[]) tArr);
    }

    public static final <T> void sort(@NotNull T[] sort) {
        Intrinsics.checkNotNullParameter(sort, "$this$sort");
        if (sort.length > 1) {
            Arrays.sort(sort);
        }
    }

    public static /* synthetic */ void sort$default(Comparable[] comparableArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = comparableArr.length;
        }
        ArraysKt.sort(comparableArr, i, i2);
    }

    @SinceKotlin(version = "1.4")
    public static final <T extends Comparable<? super T>> void sort(@NotNull T[] sort, int fromIndex, int toIndex) {
        Intrinsics.checkNotNullParameter(sort, "$this$sort");
        Arrays.sort(sort, fromIndex, toIndex);
    }

    public static /* synthetic */ void sort$default(byte[] bArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = bArr.length;
        }
        ArraysKt.sort(bArr, i, i2);
    }

    public static final void sort(@NotNull byte[] sort, int fromIndex, int toIndex) {
        Intrinsics.checkNotNullParameter(sort, "$this$sort");
        Arrays.sort(sort, fromIndex, toIndex);
    }

    public static /* synthetic */ void sort$default(short[] sArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = sArr.length;
        }
        ArraysKt.sort(sArr, i, i2);
    }

    public static final void sort(@NotNull short[] sort, int fromIndex, int toIndex) {
        Intrinsics.checkNotNullParameter(sort, "$this$sort");
        Arrays.sort(sort, fromIndex, toIndex);
    }

    public static /* synthetic */ void sort$default(int[] iArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = iArr.length;
        }
        ArraysKt.sort(iArr, i, i2);
    }

    public static final void sort(@NotNull int[] sort, int fromIndex, int toIndex) {
        Intrinsics.checkNotNullParameter(sort, "$this$sort");
        Arrays.sort(sort, fromIndex, toIndex);
    }

    public static /* synthetic */ void sort$default(long[] jArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = jArr.length;
        }
        ArraysKt.sort(jArr, i, i2);
    }

    public static final void sort(@NotNull long[] sort, int fromIndex, int toIndex) {
        Intrinsics.checkNotNullParameter(sort, "$this$sort");
        Arrays.sort(sort, fromIndex, toIndex);
    }

    public static /* synthetic */ void sort$default(float[] fArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = fArr.length;
        }
        ArraysKt.sort(fArr, i, i2);
    }

    public static final void sort(@NotNull float[] sort, int fromIndex, int toIndex) {
        Intrinsics.checkNotNullParameter(sort, "$this$sort");
        Arrays.sort(sort, fromIndex, toIndex);
    }

    public static /* synthetic */ void sort$default(double[] dArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = dArr.length;
        }
        ArraysKt.sort(dArr, i, i2);
    }

    public static final void sort(@NotNull double[] sort, int fromIndex, int toIndex) {
        Intrinsics.checkNotNullParameter(sort, "$this$sort");
        Arrays.sort(sort, fromIndex, toIndex);
    }

    public static /* synthetic */ void sort$default(char[] cArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = cArr.length;
        }
        ArraysKt.sort(cArr, i, i2);
    }

    public static final void sort(@NotNull char[] sort, int fromIndex, int toIndex) {
        Intrinsics.checkNotNullParameter(sort, "$this$sort");
        Arrays.sort(sort, fromIndex, toIndex);
    }

    public static /* synthetic */ void sort$default(Object[] objArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = objArr.length;
        }
        ArraysKt.sort(objArr, i, i2);
    }

    public static final <T> void sort(@NotNull T[] sort, int fromIndex, int toIndex) {
        Intrinsics.checkNotNullParameter(sort, "$this$sort");
        Arrays.sort(sort, fromIndex, toIndex);
    }

    public static final <T> void sortWith(@NotNull T[] sortWith, @NotNull Comparator<? super T> comparator) {
        Intrinsics.checkNotNullParameter(sortWith, "$this$sortWith");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        if (sortWith.length > 1) {
            Arrays.sort(sortWith, comparator);
        }
    }

    public static /* synthetic */ void sortWith$default(Object[] objArr, Comparator comparator, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = objArr.length;
        }
        ArraysKt.sortWith(objArr, comparator, i, i2);
    }

    public static final <T> void sortWith(@NotNull T[] sortWith, @NotNull Comparator<? super T> comparator, int fromIndex, int toIndex) {
        Intrinsics.checkNotNullParameter(sortWith, "$this$sortWith");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        Arrays.sort(sortWith, fromIndex, toIndex, comparator);
    }

    @NotNull
    public static final Byte[] toTypedArray(@NotNull byte[] toTypedArray) {
        Intrinsics.checkNotNullParameter(toTypedArray, "$this$toTypedArray");
        Byte[] result = new Byte[toTypedArray.length];
        int length = toTypedArray.length;
        for (int index = 0; index < length; index++) {
            result[index] = Byte.valueOf(toTypedArray[index]);
        }
        return result;
    }

    @NotNull
    public static final Short[] toTypedArray(@NotNull short[] toTypedArray) {
        Intrinsics.checkNotNullParameter(toTypedArray, "$this$toTypedArray");
        Short[] result = new Short[toTypedArray.length];
        int length = toTypedArray.length;
        for (int index = 0; index < length; index++) {
            result[index] = Short.valueOf(toTypedArray[index]);
        }
        return result;
    }

    @NotNull
    public static final Integer[] toTypedArray(@NotNull int[] toTypedArray) {
        Intrinsics.checkNotNullParameter(toTypedArray, "$this$toTypedArray");
        Integer[] result = new Integer[toTypedArray.length];
        int length = toTypedArray.length;
        for (int index = 0; index < length; index++) {
            result[index] = Integer.valueOf(toTypedArray[index]);
        }
        return result;
    }

    @NotNull
    public static final Long[] toTypedArray(@NotNull long[] toTypedArray) {
        Intrinsics.checkNotNullParameter(toTypedArray, "$this$toTypedArray");
        Long[] result = new Long[toTypedArray.length];
        int length = toTypedArray.length;
        for (int index = 0; index < length; index++) {
            result[index] = Long.valueOf(toTypedArray[index]);
        }
        return result;
    }

    @NotNull
    public static final Float[] toTypedArray(@NotNull float[] toTypedArray) {
        Intrinsics.checkNotNullParameter(toTypedArray, "$this$toTypedArray");
        Float[] result = new Float[toTypedArray.length];
        int length = toTypedArray.length;
        for (int index = 0; index < length; index++) {
            result[index] = Float.valueOf(toTypedArray[index]);
        }
        return result;
    }

    @NotNull
    public static final Double[] toTypedArray(@NotNull double[] toTypedArray) {
        Intrinsics.checkNotNullParameter(toTypedArray, "$this$toTypedArray");
        Double[] result = new Double[toTypedArray.length];
        int length = toTypedArray.length;
        for (int index = 0; index < length; index++) {
            result[index] = Double.valueOf(toTypedArray[index]);
        }
        return result;
    }

    @NotNull
    public static final Boolean[] toTypedArray(@NotNull boolean[] toTypedArray) {
        Intrinsics.checkNotNullParameter(toTypedArray, "$this$toTypedArray");
        Boolean[] result = new Boolean[toTypedArray.length];
        int length = toTypedArray.length;
        for (int index = 0; index < length; index++) {
            result[index] = Boolean.valueOf(toTypedArray[index]);
        }
        return result;
    }

    @NotNull
    public static final Character[] toTypedArray(@NotNull char[] toTypedArray) {
        Intrinsics.checkNotNullParameter(toTypedArray, "$this$toTypedArray");
        Character[] result = new Character[toTypedArray.length];
        int length = toTypedArray.length;
        for (int index = 0; index < length; index++) {
            result[index] = Character.valueOf(toTypedArray[index]);
        }
        return result;
    }

    @NotNull
    public static final <T extends Comparable<? super T>> SortedSet<T> toSortedSet(@NotNull T[] toSortedSet) {
        Intrinsics.checkNotNullParameter(toSortedSet, "$this$toSortedSet");
        return (SortedSet) ArraysKt.toCollection(toSortedSet, new TreeSet());
    }

    @NotNull
    public static final SortedSet<Byte> toSortedSet(@NotNull byte[] toSortedSet) {
        Intrinsics.checkNotNullParameter(toSortedSet, "$this$toSortedSet");
        return (SortedSet) ArraysKt.toCollection(toSortedSet, new TreeSet());
    }

    @NotNull
    public static final SortedSet<Short> toSortedSet(@NotNull short[] toSortedSet) {
        Intrinsics.checkNotNullParameter(toSortedSet, "$this$toSortedSet");
        return (SortedSet) ArraysKt.toCollection(toSortedSet, new TreeSet());
    }

    @NotNull
    public static final SortedSet<Integer> toSortedSet(@NotNull int[] toSortedSet) {
        Intrinsics.checkNotNullParameter(toSortedSet, "$this$toSortedSet");
        return (SortedSet) ArraysKt.toCollection(toSortedSet, new TreeSet());
    }

    @NotNull
    public static final SortedSet<Long> toSortedSet(@NotNull long[] toSortedSet) {
        Intrinsics.checkNotNullParameter(toSortedSet, "$this$toSortedSet");
        return (SortedSet) ArraysKt.toCollection(toSortedSet, new TreeSet());
    }

    @NotNull
    public static final SortedSet<Float> toSortedSet(@NotNull float[] toSortedSet) {
        Intrinsics.checkNotNullParameter(toSortedSet, "$this$toSortedSet");
        return (SortedSet) ArraysKt.toCollection(toSortedSet, new TreeSet());
    }

    @NotNull
    public static final SortedSet<Double> toSortedSet(@NotNull double[] toSortedSet) {
        Intrinsics.checkNotNullParameter(toSortedSet, "$this$toSortedSet");
        return (SortedSet) ArraysKt.toCollection(toSortedSet, new TreeSet());
    }

    @NotNull
    public static final SortedSet<Boolean> toSortedSet(@NotNull boolean[] toSortedSet) {
        Intrinsics.checkNotNullParameter(toSortedSet, "$this$toSortedSet");
        return (SortedSet) ArraysKt.toCollection(toSortedSet, new TreeSet());
    }

    @NotNull
    public static final SortedSet<Character> toSortedSet(@NotNull char[] toSortedSet) {
        Intrinsics.checkNotNullParameter(toSortedSet, "$this$toSortedSet");
        return (SortedSet) ArraysKt.toCollection(toSortedSet, new TreeSet());
    }

    @NotNull
    public static final <T> SortedSet<T> toSortedSet(@NotNull T[] toSortedSet, @NotNull Comparator<? super T> comparator) {
        Intrinsics.checkNotNullParameter(toSortedSet, "$this$toSortedSet");
        Intrinsics.checkNotNullParameter(comparator, "comparator");
        return (SortedSet) ArraysKt.toCollection(toSortedSet, new TreeSet(comparator));
    }

    @SinceKotlin(version = "1.4")
    @JvmName(name = "sumOfBigDecimal")
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    private static final <T> BigDecimal sumOfBigDecimal(T[] tArr, Function1<? super T, ? extends BigDecimal> function1) {
        BigDecimal bigDecimalValueOf = BigDecimal.valueOf(0);
        Intrinsics.checkNotNullExpressionValue(bigDecimalValueOf, "BigDecimal.valueOf(this.toLong())");
        BigDecimal sum = bigDecimalValueOf;
        for (T t : tArr) {
            BigDecimal bigDecimalAdd = sum.add(function1.invoke(t));
            Intrinsics.checkNotNullExpressionValue(bigDecimalAdd, "this.add(other)");
            sum = bigDecimalAdd;
        }
        return sum;
    }

    @SinceKotlin(version = "1.4")
    @JvmName(name = "sumOfBigDecimal")
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    private static final BigDecimal sumOfBigDecimal(byte[] $this$sumOf, Function1<? super Byte, ? extends BigDecimal> function1) {
        BigDecimal bigDecimalValueOf = BigDecimal.valueOf(0);
        Intrinsics.checkNotNullExpressionValue(bigDecimalValueOf, "BigDecimal.valueOf(this.toLong())");
        BigDecimal sum = bigDecimalValueOf;
        for (byte element : $this$sumOf) {
            BigDecimal bigDecimalAdd = sum.add(function1.invoke(Byte.valueOf(element)));
            Intrinsics.checkNotNullExpressionValue(bigDecimalAdd, "this.add(other)");
            sum = bigDecimalAdd;
        }
        return sum;
    }

    @SinceKotlin(version = "1.4")
    @JvmName(name = "sumOfBigDecimal")
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    private static final BigDecimal sumOfBigDecimal(short[] $this$sumOf, Function1<? super Short, ? extends BigDecimal> function1) {
        BigDecimal bigDecimalValueOf = BigDecimal.valueOf(0);
        Intrinsics.checkNotNullExpressionValue(bigDecimalValueOf, "BigDecimal.valueOf(this.toLong())");
        BigDecimal sum = bigDecimalValueOf;
        for (short element : $this$sumOf) {
            BigDecimal bigDecimalAdd = sum.add(function1.invoke(Short.valueOf(element)));
            Intrinsics.checkNotNullExpressionValue(bigDecimalAdd, "this.add(other)");
            sum = bigDecimalAdd;
        }
        return sum;
    }

    @SinceKotlin(version = "1.4")
    @JvmName(name = "sumOfBigDecimal")
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    private static final BigDecimal sumOfBigDecimal(int[] $this$sumOf, Function1<? super Integer, ? extends BigDecimal> function1) {
        BigDecimal bigDecimalValueOf = BigDecimal.valueOf(0);
        Intrinsics.checkNotNullExpressionValue(bigDecimalValueOf, "BigDecimal.valueOf(this.toLong())");
        BigDecimal sum = bigDecimalValueOf;
        for (int element : $this$sumOf) {
            BigDecimal bigDecimalAdd = sum.add(function1.invoke(Integer.valueOf(element)));
            Intrinsics.checkNotNullExpressionValue(bigDecimalAdd, "this.add(other)");
            sum = bigDecimalAdd;
        }
        return sum;
    }

    @SinceKotlin(version = "1.4")
    @JvmName(name = "sumOfBigDecimal")
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    private static final BigDecimal sumOfBigDecimal(long[] $this$sumOf, Function1<? super Long, ? extends BigDecimal> function1) {
        BigDecimal bigDecimalValueOf = BigDecimal.valueOf(0);
        Intrinsics.checkNotNullExpressionValue(bigDecimalValueOf, "BigDecimal.valueOf(this.toLong())");
        BigDecimal sum = bigDecimalValueOf;
        for (long element : $this$sumOf) {
            BigDecimal bigDecimalAdd = sum.add(function1.invoke(Long.valueOf(element)));
            Intrinsics.checkNotNullExpressionValue(bigDecimalAdd, "this.add(other)");
            sum = bigDecimalAdd;
        }
        return sum;
    }

    @SinceKotlin(version = "1.4")
    @JvmName(name = "sumOfBigDecimal")
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    private static final BigDecimal sumOfBigDecimal(float[] $this$sumOf, Function1<? super Float, ? extends BigDecimal> function1) {
        BigDecimal bigDecimalValueOf = BigDecimal.valueOf(0);
        Intrinsics.checkNotNullExpressionValue(bigDecimalValueOf, "BigDecimal.valueOf(this.toLong())");
        BigDecimal sum = bigDecimalValueOf;
        for (float element : $this$sumOf) {
            BigDecimal bigDecimalAdd = sum.add(function1.invoke(Float.valueOf(element)));
            Intrinsics.checkNotNullExpressionValue(bigDecimalAdd, "this.add(other)");
            sum = bigDecimalAdd;
        }
        return sum;
    }

    @SinceKotlin(version = "1.4")
    @JvmName(name = "sumOfBigDecimal")
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    private static final BigDecimal sumOfBigDecimal(double[] $this$sumOf, Function1<? super Double, ? extends BigDecimal> function1) {
        BigDecimal bigDecimalValueOf = BigDecimal.valueOf(0);
        Intrinsics.checkNotNullExpressionValue(bigDecimalValueOf, "BigDecimal.valueOf(this.toLong())");
        BigDecimal sum = bigDecimalValueOf;
        for (double element : $this$sumOf) {
            BigDecimal bigDecimalAdd = sum.add(function1.invoke(Double.valueOf(element)));
            Intrinsics.checkNotNullExpressionValue(bigDecimalAdd, "this.add(other)");
            sum = bigDecimalAdd;
        }
        return sum;
    }

    @SinceKotlin(version = "1.4")
    @JvmName(name = "sumOfBigDecimal")
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    private static final BigDecimal sumOfBigDecimal(boolean[] $this$sumOf, Function1<? super Boolean, ? extends BigDecimal> function1) {
        BigDecimal bigDecimalValueOf = BigDecimal.valueOf(0);
        Intrinsics.checkNotNullExpressionValue(bigDecimalValueOf, "BigDecimal.valueOf(this.toLong())");
        BigDecimal sum = bigDecimalValueOf;
        for (boolean element : $this$sumOf) {
            BigDecimal bigDecimalAdd = sum.add(function1.invoke(Boolean.valueOf(element)));
            Intrinsics.checkNotNullExpressionValue(bigDecimalAdd, "this.add(other)");
            sum = bigDecimalAdd;
        }
        return sum;
    }

    @SinceKotlin(version = "1.4")
    @JvmName(name = "sumOfBigDecimal")
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    private static final BigDecimal sumOfBigDecimal(char[] $this$sumOf, Function1<? super Character, ? extends BigDecimal> function1) {
        BigDecimal bigDecimalValueOf = BigDecimal.valueOf(0);
        Intrinsics.checkNotNullExpressionValue(bigDecimalValueOf, "BigDecimal.valueOf(this.toLong())");
        BigDecimal sum = bigDecimalValueOf;
        for (char element : $this$sumOf) {
            BigDecimal bigDecimalAdd = sum.add(function1.invoke(Character.valueOf(element)));
            Intrinsics.checkNotNullExpressionValue(bigDecimalAdd, "this.add(other)");
            sum = bigDecimalAdd;
        }
        return sum;
    }

    @SinceKotlin(version = "1.4")
    @JvmName(name = "sumOfBigInteger")
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    private static final <T> BigInteger sumOfBigInteger(T[] tArr, Function1<? super T, ? extends BigInteger> function1) {
        BigInteger bigIntegerValueOf = BigInteger.valueOf(0);
        Intrinsics.checkNotNullExpressionValue(bigIntegerValueOf, "BigInteger.valueOf(this.toLong())");
        BigInteger sum = bigIntegerValueOf;
        for (T t : tArr) {
            BigInteger bigIntegerAdd = sum.add(function1.invoke(t));
            Intrinsics.checkNotNullExpressionValue(bigIntegerAdd, "this.add(other)");
            sum = bigIntegerAdd;
        }
        return sum;
    }

    @SinceKotlin(version = "1.4")
    @JvmName(name = "sumOfBigInteger")
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    private static final BigInteger sumOfBigInteger(byte[] $this$sumOf, Function1<? super Byte, ? extends BigInteger> function1) {
        BigInteger bigIntegerValueOf = BigInteger.valueOf(0);
        Intrinsics.checkNotNullExpressionValue(bigIntegerValueOf, "BigInteger.valueOf(this.toLong())");
        BigInteger sum = bigIntegerValueOf;
        for (byte element : $this$sumOf) {
            BigInteger bigIntegerAdd = sum.add(function1.invoke(Byte.valueOf(element)));
            Intrinsics.checkNotNullExpressionValue(bigIntegerAdd, "this.add(other)");
            sum = bigIntegerAdd;
        }
        return sum;
    }

    @SinceKotlin(version = "1.4")
    @JvmName(name = "sumOfBigInteger")
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    private static final BigInteger sumOfBigInteger(short[] $this$sumOf, Function1<? super Short, ? extends BigInteger> function1) {
        BigInteger bigIntegerValueOf = BigInteger.valueOf(0);
        Intrinsics.checkNotNullExpressionValue(bigIntegerValueOf, "BigInteger.valueOf(this.toLong())");
        BigInteger sum = bigIntegerValueOf;
        for (short element : $this$sumOf) {
            BigInteger bigIntegerAdd = sum.add(function1.invoke(Short.valueOf(element)));
            Intrinsics.checkNotNullExpressionValue(bigIntegerAdd, "this.add(other)");
            sum = bigIntegerAdd;
        }
        return sum;
    }

    @SinceKotlin(version = "1.4")
    @JvmName(name = "sumOfBigInteger")
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    private static final BigInteger sumOfBigInteger(int[] $this$sumOf, Function1<? super Integer, ? extends BigInteger> function1) {
        BigInteger bigIntegerValueOf = BigInteger.valueOf(0);
        Intrinsics.checkNotNullExpressionValue(bigIntegerValueOf, "BigInteger.valueOf(this.toLong())");
        BigInteger sum = bigIntegerValueOf;
        for (int element : $this$sumOf) {
            BigInteger bigIntegerAdd = sum.add(function1.invoke(Integer.valueOf(element)));
            Intrinsics.checkNotNullExpressionValue(bigIntegerAdd, "this.add(other)");
            sum = bigIntegerAdd;
        }
        return sum;
    }

    @SinceKotlin(version = "1.4")
    @JvmName(name = "sumOfBigInteger")
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    private static final BigInteger sumOfBigInteger(long[] $this$sumOf, Function1<? super Long, ? extends BigInteger> function1) {
        BigInteger bigIntegerValueOf = BigInteger.valueOf(0);
        Intrinsics.checkNotNullExpressionValue(bigIntegerValueOf, "BigInteger.valueOf(this.toLong())");
        BigInteger sum = bigIntegerValueOf;
        for (long element : $this$sumOf) {
            BigInteger bigIntegerAdd = sum.add(function1.invoke(Long.valueOf(element)));
            Intrinsics.checkNotNullExpressionValue(bigIntegerAdd, "this.add(other)");
            sum = bigIntegerAdd;
        }
        return sum;
    }

    @SinceKotlin(version = "1.4")
    @JvmName(name = "sumOfBigInteger")
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    private static final BigInteger sumOfBigInteger(float[] $this$sumOf, Function1<? super Float, ? extends BigInteger> function1) {
        BigInteger bigIntegerValueOf = BigInteger.valueOf(0);
        Intrinsics.checkNotNullExpressionValue(bigIntegerValueOf, "BigInteger.valueOf(this.toLong())");
        BigInteger sum = bigIntegerValueOf;
        for (float element : $this$sumOf) {
            BigInteger bigIntegerAdd = sum.add(function1.invoke(Float.valueOf(element)));
            Intrinsics.checkNotNullExpressionValue(bigIntegerAdd, "this.add(other)");
            sum = bigIntegerAdd;
        }
        return sum;
    }

    @SinceKotlin(version = "1.4")
    @JvmName(name = "sumOfBigInteger")
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    private static final BigInteger sumOfBigInteger(double[] $this$sumOf, Function1<? super Double, ? extends BigInteger> function1) {
        BigInteger bigIntegerValueOf = BigInteger.valueOf(0);
        Intrinsics.checkNotNullExpressionValue(bigIntegerValueOf, "BigInteger.valueOf(this.toLong())");
        BigInteger sum = bigIntegerValueOf;
        for (double element : $this$sumOf) {
            BigInteger bigIntegerAdd = sum.add(function1.invoke(Double.valueOf(element)));
            Intrinsics.checkNotNullExpressionValue(bigIntegerAdd, "this.add(other)");
            sum = bigIntegerAdd;
        }
        return sum;
    }

    @SinceKotlin(version = "1.4")
    @JvmName(name = "sumOfBigInteger")
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    private static final BigInteger sumOfBigInteger(boolean[] $this$sumOf, Function1<? super Boolean, ? extends BigInteger> function1) {
        BigInteger bigIntegerValueOf = BigInteger.valueOf(0);
        Intrinsics.checkNotNullExpressionValue(bigIntegerValueOf, "BigInteger.valueOf(this.toLong())");
        BigInteger sum = bigIntegerValueOf;
        for (boolean element : $this$sumOf) {
            BigInteger bigIntegerAdd = sum.add(function1.invoke(Boolean.valueOf(element)));
            Intrinsics.checkNotNullExpressionValue(bigIntegerAdd, "this.add(other)");
            sum = bigIntegerAdd;
        }
        return sum;
    }

    @SinceKotlin(version = "1.4")
    @JvmName(name = "sumOfBigInteger")
    @InlineOnly
    @OverloadResolutionByLambdaReturnType
    private static final BigInteger sumOfBigInteger(char[] $this$sumOf, Function1<? super Character, ? extends BigInteger> function1) {
        BigInteger bigIntegerValueOf = BigInteger.valueOf(0);
        Intrinsics.checkNotNullExpressionValue(bigIntegerValueOf, "BigInteger.valueOf(this.toLong())");
        BigInteger sum = bigIntegerValueOf;
        for (char element : $this$sumOf) {
            BigInteger bigIntegerAdd = sum.add(function1.invoke(Character.valueOf(element)));
            Intrinsics.checkNotNullExpressionValue(bigIntegerAdd, "this.add(other)");
            sum = bigIntegerAdd;
        }
        return sum;
    }
}
