package kotlinx.coroutines;

import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.internal.LockFreeLinkedListKt;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.OpDescriptor;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;
import kotlinx.coroutines.intrinsics.CancellableKt;
import kotlinx.coroutines.intrinsics.UndispatchedKt;
import kotlinx.coroutines.selects.SelectClause0;
import kotlinx.coroutines.selects.SelectInstance;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.xml.BeanDefinitionParserDelegate;
import org.springframework.cglib.core.Constants;

/* compiled from: JobSupport.kt */
@Deprecated(message = "This is internal API and may be removed in the future releases", level = DeprecationLevel.ERROR)
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��è\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n��\n\u0002\u0010 \n��\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0001\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0012\b\u0017\u0018��2\u00020X2\u00020\u00172\u00020\u007f2\u00030Ã\u0001:\u0006Ò\u0001Ó\u0001Ô\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0003\u0010\u0004J'\u0010\u000b\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\tH\u0002¢\u0006\u0004\b\u000b\u0010\fJ%\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\r2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\r0\u000fH\u0002¢\u0006\u0004\b\u0012\u0010\u0013J\u0019\u0010\u0015\u001a\u00020\u00112\b\u0010\u0014\u001a\u0004\u0018\u00010\u0005H\u0014¢\u0006\u0004\b\u0015\u0010\u0016J\u0015\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0018\u001a\u00020\u0017¢\u0006\u0004\b\u001a\u0010\u001bJ\u0015\u0010\u001e\u001a\u0004\u0018\u00010\u0005H\u0080@ø\u0001��¢\u0006\u0004\b\u001c\u0010\u001dJ\u0015\u0010\u001f\u001a\u0004\u0018\u00010\u0005H\u0082@ø\u0001��¢\u0006\u0004\b\u001f\u0010\u001dJ\u0019\u0010!\u001a\u00020\u00012\b\u0010 \u001a\u0004\u0018\u00010\rH\u0017¢\u0006\u0004\b!\u0010\"J\u001f\u0010!\u001a\u00020\u00112\u000e\u0010 \u001a\n\u0018\u00010#j\u0004\u0018\u0001`$H\u0016¢\u0006\u0004\b!\u0010%J\u0017\u0010&\u001a\u00020\u00012\b\u0010 \u001a\u0004\u0018\u00010\r¢\u0006\u0004\b&\u0010\"J\u0019\u0010)\u001a\u00020\u00012\b\u0010 \u001a\u0004\u0018\u00010\u0005H��¢\u0006\u0004\b'\u0010(J\u0017\u0010*\u001a\u00020\u00112\u0006\u0010 \u001a\u00020\rH\u0016¢\u0006\u0004\b*\u0010+J\u001b\u0010,\u001a\u0004\u0018\u00010\u00052\b\u0010 \u001a\u0004\u0018\u00010\u0005H\u0002¢\u0006\u0004\b,\u0010-J\u0017\u0010.\u001a\u00020\u00012\u0006\u0010 \u001a\u00020\rH\u0002¢\u0006\u0004\b.\u0010\"J\u000f\u00100\u001a\u00020/H\u0014¢\u0006\u0004\b0\u00101J\u0017\u00102\u001a\u00020\u00012\u0006\u0010 \u001a\u00020\rH\u0016¢\u0006\u0004\b2\u0010\"J!\u00105\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u0002032\b\u00104\u001a\u0004\u0018\u00010\u0005H\u0002¢\u0006\u0004\b5\u00106J)\u0010;\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u0002072\u0006\u00109\u001a\u0002082\b\u0010:\u001a\u0004\u0018\u00010\u0005H\u0002¢\u0006\u0004\b;\u0010<J\u0019\u0010=\u001a\u00020\r2\b\u0010 \u001a\u0004\u0018\u00010\u0005H\u0002¢\u0006\u0004\b=\u0010>J(\u0010C\u001a\u00020@2\n\b\u0002\u0010?\u001a\u0004\u0018\u00010/2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\rH\u0080\b¢\u0006\u0004\bA\u0010BJ#\u0010D\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0014\u001a\u0002072\b\u0010:\u001a\u0004\u0018\u00010\u0005H\u0002¢\u0006\u0004\bD\u0010EJ\u0019\u0010F\u001a\u0004\u0018\u0001082\u0006\u0010\u0014\u001a\u000203H\u0002¢\u0006\u0004\bF\u0010GJ\u0011\u0010H\u001a\u00060#j\u0002`$¢\u0006\u0004\bH\u0010IJ\u0013\u0010J\u001a\u00060#j\u0002`$H\u0016¢\u0006\u0004\bJ\u0010IJ\u0011\u0010M\u001a\u0004\u0018\u00010\u0005H��¢\u0006\u0004\bK\u0010LJ\u000f\u0010N\u001a\u0004\u0018\u00010\r¢\u0006\u0004\bN\u0010OJ'\u0010P\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0014\u001a\u0002072\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\r0\u000fH\u0002¢\u0006\u0004\bP\u0010QJ\u0019\u0010R\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0014\u001a\u000203H\u0002¢\u0006\u0004\bR\u0010SJ\u0017\u0010U\u001a\u00020\u00012\u0006\u0010T\u001a\u00020\rH\u0014¢\u0006\u0004\bU\u0010\"J\u0017\u0010W\u001a\u00020\u00112\u0006\u0010T\u001a\u00020\rH\u0010¢\u0006\u0004\bV\u0010+J\u0019\u0010Z\u001a\u00020\u00112\b\u0010Y\u001a\u0004\u0018\u00010XH\u0004¢\u0006\u0004\bZ\u0010[JF\u0010d\u001a\u00020c2\u0006\u0010\\\u001a\u00020\u00012\u0006\u0010]\u001a\u00020\u00012'\u0010b\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\r¢\u0006\f\b_\u0012\b\b`\u0012\u0004\b\b( \u0012\u0004\u0012\u00020\u00110^j\u0002`a¢\u0006\u0004\bd\u0010eJ6\u0010d\u001a\u00020c2'\u0010b\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\r¢\u0006\f\b_\u0012\b\b`\u0012\u0004\b\b( \u0012\u0004\u0012\u00020\u00110^j\u0002`a¢\u0006\u0004\bd\u0010fJ\u0013\u0010g\u001a\u00020\u0011H\u0086@ø\u0001��¢\u0006\u0004\bg\u0010\u001dJ\u000f\u0010h\u001a\u00020\u0001H\u0002¢\u0006\u0004\bh\u0010iJ\u0013\u0010j\u001a\u00020\u0011H\u0082@ø\u0001��¢\u0006\u0004\bj\u0010\u001dJ&\u0010m\u001a\u00020l2\u0014\u0010k\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0012\u0004\u0012\u00020\u00110^H\u0082\b¢\u0006\u0004\bm\u0010nJ\u001b\u0010o\u001a\u0004\u0018\u00010\u00052\b\u0010 \u001a\u0004\u0018\u00010\u0005H\u0002¢\u0006\u0004\bo\u0010-J\u0019\u0010q\u001a\u00020\u00012\b\u0010:\u001a\u0004\u0018\u00010\u0005H��¢\u0006\u0004\bp\u0010(J\u001b\u0010s\u001a\u0004\u0018\u00010\u00052\b\u0010:\u001a\u0004\u0018\u00010\u0005H��¢\u0006\u0004\br\u0010-J@\u0010t\u001a\u00020\t2'\u0010b\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\r¢\u0006\f\b_\u0012\b\b`\u0012\u0004\b\b( \u0012\u0004\u0012\u00020\u00110^j\u0002`a2\u0006\u0010\\\u001a\u00020\u0001H\u0002¢\u0006\u0004\bt\u0010uJ\u000f\u0010w\u001a\u00020/H\u0010¢\u0006\u0004\bv\u00101J\u001f\u0010x\u001a\u00020\u00112\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010 \u001a\u00020\rH\u0002¢\u0006\u0004\bx\u0010yJ.\u0010{\u001a\u00020\u0011\"\n\b��\u0010z\u0018\u0001*\u00020\t2\u0006\u0010\b\u001a\u00020\u00072\b\u0010 \u001a\u0004\u0018\u00010\rH\u0082\b¢\u0006\u0004\b{\u0010yJ\u0019\u0010\\\u001a\u00020\u00112\b\u0010 \u001a\u0004\u0018\u00010\rH\u0014¢\u0006\u0004\b\\\u0010+J\u0019\u0010|\u001a\u00020\u00112\b\u0010\u0014\u001a\u0004\u0018\u00010\u0005H\u0014¢\u0006\u0004\b|\u0010\u0016J\u000f\u0010}\u001a\u00020\u0011H\u0014¢\u0006\u0004\b}\u0010~J\u0019\u0010\u0081\u0001\u001a\u00020\u00112\u0007\u0010\u0080\u0001\u001a\u00020\u007f¢\u0006\u0006\b\u0081\u0001\u0010\u0082\u0001J\u001b\u0010\u0084\u0001\u001a\u00020\u00112\u0007\u0010\u0014\u001a\u00030\u0083\u0001H\u0002¢\u0006\u0006\b\u0084\u0001\u0010\u0085\u0001J\u001a\u0010\u0086\u0001\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\tH\u0002¢\u0006\u0006\b\u0086\u0001\u0010\u0087\u0001JI\u0010\u008c\u0001\u001a\u00020\u0011\"\u0005\b��\u0010\u0088\u00012\u000e\u0010\u008a\u0001\u001a\t\u0012\u0004\u0012\u00028��0\u0089\u00012\u001d\u0010k\u001a\u0019\b\u0001\u0012\u000b\u0012\t\u0012\u0004\u0012\u00028��0\u008b\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00050^ø\u0001��¢\u0006\u0006\b\u008c\u0001\u0010\u008d\u0001JX\u0010\u0091\u0001\u001a\u00020\u0011\"\u0004\b��\u0010z\"\u0005\b\u0001\u0010\u0088\u00012\u000e\u0010\u008a\u0001\u001a\t\u0012\u0004\u0012\u00028\u00010\u0089\u00012$\u0010k\u001a \b\u0001\u0012\u0004\u0012\u00028��\u0012\u000b\u0012\t\u0012\u0004\u0012\u00028\u00010\u008b\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u008e\u0001H��ø\u0001��¢\u0006\u0006\b\u008f\u0001\u0010\u0090\u0001J\u001a\u0010\u0093\u0001\u001a\u00020\u00112\u0006\u0010\n\u001a\u00020\tH��¢\u0006\u0006\b\u0092\u0001\u0010\u0087\u0001JX\u0010\u0095\u0001\u001a\u00020\u0011\"\u0004\b��\u0010z\"\u0005\b\u0001\u0010\u0088\u00012\u000e\u0010\u008a\u0001\u001a\t\u0012\u0004\u0012\u00028\u00010\u0089\u00012$\u0010k\u001a \b\u0001\u0012\u0004\u0012\u00028��\u0012\u000b\u0012\t\u0012\u0004\u0012\u00028\u00010\u008b\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u008e\u0001H��ø\u0001��¢\u0006\u0006\b\u0094\u0001\u0010\u0090\u0001J\u000f\u0010\u0096\u0001\u001a\u00020\u0001¢\u0006\u0005\b\u0096\u0001\u0010iJ\u001d\u0010\u0098\u0001\u001a\u00030\u0097\u00012\b\u0010\u0014\u001a\u0004\u0018\u00010\u0005H\u0002¢\u0006\u0006\b\u0098\u0001\u0010\u0099\u0001J\u001c\u0010\u009a\u0001\u001a\u00020/2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0005H\u0002¢\u0006\u0006\b\u009a\u0001\u0010\u009b\u0001J\u0011\u0010\u009c\u0001\u001a\u00020/H\u0007¢\u0006\u0005\b\u009c\u0001\u00101J\u0011\u0010\u009d\u0001\u001a\u00020/H\u0016¢\u0006\u0005\b\u009d\u0001\u00101J$\u0010\u009e\u0001\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u0002032\b\u00104\u001a\u0004\u0018\u00010\u0005H\u0002¢\u0006\u0006\b\u009e\u0001\u0010\u009f\u0001J\"\u0010 \u0001\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u0002032\u0006\u0010\u000e\u001a\u00020\rH\u0002¢\u0006\u0006\b \u0001\u0010¡\u0001J(\u0010¢\u0001\u001a\u0004\u0018\u00010\u00052\b\u0010\u0014\u001a\u0004\u0018\u00010\u00052\b\u0010:\u001a\u0004\u0018\u00010\u0005H\u0002¢\u0006\u0006\b¢\u0001\u0010£\u0001J&\u0010¤\u0001\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0014\u001a\u0002032\b\u0010:\u001a\u0004\u0018\u00010\u0005H\u0002¢\u0006\u0006\b¤\u0001\u0010¥\u0001J-\u0010¦\u0001\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u0002072\u0006\u0010\u0018\u001a\u0002082\b\u0010:\u001a\u0004\u0018\u00010\u0005H\u0082\u0010¢\u0006\u0006\b¦\u0001\u0010§\u0001J\u0019\u0010©\u0001\u001a\u0004\u0018\u000108*\u00030¨\u0001H\u0002¢\u0006\u0006\b©\u0001\u0010ª\u0001J\u001f\u0010«\u0001\u001a\u00020\u0011*\u00020\u00072\b\u0010 \u001a\u0004\u0018\u00010\rH\u0002¢\u0006\u0005\b«\u0001\u0010yJ&\u0010¬\u0001\u001a\u00060#j\u0002`$*\u00020\r2\n\b\u0002\u0010?\u001a\u0004\u0018\u00010/H\u0004¢\u0006\u0006\b¬\u0001\u0010\u00ad\u0001R\u001b\u0010±\u0001\u001a\t\u0012\u0004\u0012\u00020X0®\u00018F¢\u0006\b\u001a\u0006\b¯\u0001\u0010°\u0001R\u0018\u0010³\u0001\u001a\u0004\u0018\u00010\r8DX\u0084\u0004¢\u0006\u0007\u001a\u0005\b²\u0001\u0010OR\u0016\u0010µ\u0001\u001a\u00020\u00018DX\u0084\u0004¢\u0006\u0007\u001a\u0005\b´\u0001\u0010iR\u0016\u0010·\u0001\u001a\u00020\u00018PX\u0090\u0004¢\u0006\u0007\u001a\u0005\b¶\u0001\u0010iR\u0016\u0010¸\u0001\u001a\u00020\u00018VX\u0096\u0004¢\u0006\u0007\u001a\u0005\b¸\u0001\u0010iR\u0013\u0010¹\u0001\u001a\u00020\u00018F¢\u0006\u0007\u001a\u0005\b¹\u0001\u0010iR\u0013\u0010º\u0001\u001a\u00020\u00018F¢\u0006\u0007\u001a\u0005\bº\u0001\u0010iR\u0013\u0010»\u0001\u001a\u00020\u00018F¢\u0006\u0007\u001a\u0005\b»\u0001\u0010iR\u0016\u0010¼\u0001\u001a\u00020\u00018TX\u0094\u0004¢\u0006\u0007\u001a\u0005\b¼\u0001\u0010iR\u0019\u0010À\u0001\u001a\u0007\u0012\u0002\b\u00030½\u00018F¢\u0006\b\u001a\u0006\b¾\u0001\u0010¿\u0001R\u0016\u0010Â\u0001\u001a\u00020\u00018PX\u0090\u0004¢\u0006\u0007\u001a\u0005\bÁ\u0001\u0010iR\u0015\u0010Æ\u0001\u001a\u00030Ã\u00018F¢\u0006\b\u001a\u0006\bÄ\u0001\u0010Å\u0001R.\u0010Ì\u0001\u001a\u0004\u0018\u00010\u00192\t\u0010Ç\u0001\u001a\u0004\u0018\u00010\u00198@@@X\u0080\u000e¢\u0006\u0010\u001a\u0006\bÈ\u0001\u0010É\u0001\"\u0006\bÊ\u0001\u0010Ë\u0001R\u0017\u0010\u0014\u001a\u0004\u0018\u00010\u00058@X\u0080\u0004¢\u0006\u0007\u001a\u0005\bÍ\u0001\u0010LR\u001e\u0010Ï\u0001\u001a\u0004\u0018\u00010\r*\u0004\u0018\u00010\u00058BX\u0082\u0004¢\u0006\u0007\u001a\u0005\bÎ\u0001\u0010>R\u001b\u0010Ð\u0001\u001a\u00020\u0001*\u0002038BX\u0082\u0004¢\u0006\b\u001a\u0006\bÐ\u0001\u0010Ñ\u0001\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006Õ\u0001"}, d2 = {"Lkotlinx/coroutines/JobSupport;", "", "active", Constants.CONSTRUCTOR_NAME, "(Z)V", "", "expect", "Lkotlinx/coroutines/NodeList;", BeanDefinitionParserDelegate.LIST_ELEMENT, "Lkotlinx/coroutines/JobNode;", "node", "addLastAtomic", "(Ljava/lang/Object;Lkotlinx/coroutines/NodeList;Lkotlinx/coroutines/JobNode;)Z", "", "rootCause", "", "exceptions", "", "addSuppressedExceptions", "(Ljava/lang/Throwable;Ljava/util/List;)V", "state", "afterCompletion", "(Ljava/lang/Object;)V", "Lkotlinx/coroutines/ChildJob;", "child", "Lkotlinx/coroutines/ChildHandle;", "attachChild", "(Lkotlinx/coroutines/ChildJob;)Lkotlinx/coroutines/ChildHandle;", "awaitInternal$kotlinx_coroutines_core", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitInternal", "awaitSuspend", "cause", "cancel", "(Ljava/lang/Throwable;)Z", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "(Ljava/util/concurrent/CancellationException;)V", "cancelCoroutine", "cancelImpl$kotlinx_coroutines_core", "(Ljava/lang/Object;)Z", "cancelImpl", "cancelInternal", "(Ljava/lang/Throwable;)V", "cancelMakeCompleting", "(Ljava/lang/Object;)Ljava/lang/Object;", "cancelParent", "", "cancellationExceptionMessage", "()Ljava/lang/String;", "childCancelled", "Lkotlinx/coroutines/Incomplete;", "update", "completeStateFinalization", "(Lkotlinx/coroutines/Incomplete;Ljava/lang/Object;)V", "Lkotlinx/coroutines/JobSupport$Finishing;", "Lkotlinx/coroutines/ChildHandleNode;", "lastChild", "proposedUpdate", "continueCompleting", "(Lkotlinx/coroutines/JobSupport$Finishing;Lkotlinx/coroutines/ChildHandleNode;Ljava/lang/Object;)V", "createCauseException", "(Ljava/lang/Object;)Ljava/lang/Throwable;", "message", "Lkotlinx/coroutines/JobCancellationException;", "defaultCancellationException$kotlinx_coroutines_core", "(Ljava/lang/String;Ljava/lang/Throwable;)Lkotlinx/coroutines/JobCancellationException;", "defaultCancellationException", "finalizeFinishingState", "(Lkotlinx/coroutines/JobSupport$Finishing;Ljava/lang/Object;)Ljava/lang/Object;", "firstChild", "(Lkotlinx/coroutines/Incomplete;)Lkotlinx/coroutines/ChildHandleNode;", "getCancellationException", "()Ljava/util/concurrent/CancellationException;", "getChildJobCancellationCause", "getCompletedInternal$kotlinx_coroutines_core", "()Ljava/lang/Object;", "getCompletedInternal", "getCompletionExceptionOrNull", "()Ljava/lang/Throwable;", "getFinalRootCause", "(Lkotlinx/coroutines/JobSupport$Finishing;Ljava/util/List;)Ljava/lang/Throwable;", "getOrPromoteCancellingList", "(Lkotlinx/coroutines/Incomplete;)Lkotlinx/coroutines/NodeList;", "exception", "handleJobException", "handleOnCompletionException$kotlinx_coroutines_core", "handleOnCompletionException", "Lkotlinx/coroutines/Job;", "parent", "initParentJob", "(Lkotlinx/coroutines/Job;)V", "onCancelling", "invokeImmediately", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "Lkotlinx/coroutines/CompletionHandler;", "handler", "Lkotlinx/coroutines/DisposableHandle;", "invokeOnCompletion", "(ZZLkotlin/jvm/functions/Function1;)Lkotlinx/coroutines/DisposableHandle;", "(Lkotlin/jvm/functions/Function1;)Lkotlinx/coroutines/DisposableHandle;", "join", "joinInternal", "()Z", "joinSuspend", "block", "", "loopOnState", "(Lkotlin/jvm/functions/Function1;)Ljava/lang/Void;", "makeCancelling", "makeCompleting$kotlinx_coroutines_core", "makeCompleting", "makeCompletingOnce$kotlinx_coroutines_core", "makeCompletingOnce", "makeNode", "(Lkotlin/jvm/functions/Function1;Z)Lkotlinx/coroutines/JobNode;", "nameString$kotlinx_coroutines_core", "nameString", "notifyCancelling", "(Lkotlinx/coroutines/NodeList;Ljava/lang/Throwable;)V", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "notifyHandlers", "onCompletionInternal", "onStart", "()V", "Lkotlinx/coroutines/ParentJob;", "parentJob", "parentCancelled", "(Lkotlinx/coroutines/ParentJob;)V", "Lkotlinx/coroutines/Empty;", "promoteEmptyToNodeList", "(Lkotlinx/coroutines/Empty;)V", "promoteSingleToNodeList", "(Lkotlinx/coroutines/JobNode;)V", "R", "Lkotlinx/coroutines/selects/SelectInstance;", "select", "Lkotlin/coroutines/Continuation;", "registerSelectClause0", "(Lkotlinx/coroutines/selects/SelectInstance;Lkotlin/jvm/functions/Function1;)V", "Lkotlin/Function2;", "registerSelectClause1Internal$kotlinx_coroutines_core", "(Lkotlinx/coroutines/selects/SelectInstance;Lkotlin/jvm/functions/Function2;)V", "registerSelectClause1Internal", "removeNode$kotlinx_coroutines_core", "removeNode", "selectAwaitCompletion$kotlinx_coroutines_core", "selectAwaitCompletion", "start", "", "startInternal", "(Ljava/lang/Object;)I", "stateString", "(Ljava/lang/Object;)Ljava/lang/String;", "toDebugString", "toString", "tryFinalizeSimpleState", "(Lkotlinx/coroutines/Incomplete;Ljava/lang/Object;)Z", "tryMakeCancelling", "(Lkotlinx/coroutines/Incomplete;Ljava/lang/Throwable;)Z", "tryMakeCompleting", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "tryMakeCompletingSlowPath", "(Lkotlinx/coroutines/Incomplete;Ljava/lang/Object;)Ljava/lang/Object;", "tryWaitForChild", "(Lkotlinx/coroutines/JobSupport$Finishing;Lkotlinx/coroutines/ChildHandleNode;Ljava/lang/Object;)Z", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "nextChild", "(Lkotlinx/coroutines/internal/LockFreeLinkedListNode;)Lkotlinx/coroutines/ChildHandleNode;", "notifyCompletion", "toCancellationException", "(Ljava/lang/Throwable;Ljava/lang/String;)Ljava/util/concurrent/CancellationException;", "Lkotlin/sequences/Sequence;", "getChildren", "()Lkotlin/sequences/Sequence;", "children", "getCompletionCause", "completionCause", "getCompletionCauseHandled", "completionCauseHandled", "getHandlesException$kotlinx_coroutines_core", "handlesException", "isActive", "isCancelled", "isCompleted", "isCompletedExceptionally", "isScopedCoroutine", "Lkotlin/coroutines/CoroutineContext$Key;", "getKey", "()Lkotlin/coroutines/CoroutineContext$Key;", "key", "getOnCancelComplete$kotlinx_coroutines_core", "onCancelComplete", "Lkotlinx/coroutines/selects/SelectClause0;", "getOnJoin", "()Lkotlinx/coroutines/selects/SelectClause0;", "onJoin", "value", "getParentHandle$kotlinx_coroutines_core", "()Lkotlinx/coroutines/ChildHandle;", "setParentHandle$kotlinx_coroutines_core", "(Lkotlinx/coroutines/ChildHandle;)V", "parentHandle", "getState$kotlinx_coroutines_core", "getExceptionOrNull", "exceptionOrNull", "isCancelling", "(Lkotlinx/coroutines/Incomplete;)Z", "AwaitContinuation", "ChildCompletion", "Finishing", "kotlinx-coroutines-core"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/JobSupport.class */
public class JobSupport implements Job, ChildJob, ParentJob, SelectClause0 {

    @NotNull
    private volatile /* synthetic */ Object _state;
    private static final /* synthetic */ AtomicReferenceFieldUpdater _state$FU = AtomicReferenceFieldUpdater.newUpdater(JobSupport.class, Object.class, "_state");

    @NotNull
    private volatile /* synthetic */ Object _parentHandle;

    public JobSupport(boolean active) {
        this._state = active ? JobSupportKt.EMPTY_ACTIVE : JobSupportKt.EMPTY_NEW;
        this._parentHandle = null;
    }

    @Override // kotlinx.coroutines.Job
    @Deprecated(message = "Since 1.2.0, binary compatibility with versions <= 1.1.x", level = DeprecationLevel.HIDDEN)
    public /* synthetic */ void cancel() {
        cancel((CancellationException) null);
    }

    @Override // kotlinx.coroutines.Job
    @Deprecated(message = "Operator '+' on two Job objects is meaningless. Job is a coroutine context element and `+` is a set-sum operator for coroutine contexts. The job to the right of `+` just replaces the job the left of `+`.", level = DeprecationLevel.ERROR)
    @NotNull
    public Job plus(@NotNull Job other) {
        return Job.DefaultImpls.plus((Job) this, other);
    }

    @Override // kotlin.coroutines.CoroutineContext.Element, kotlin.coroutines.CoroutineContext
    @Nullable
    public <E extends CoroutineContext.Element> E get(@NotNull CoroutineContext.Key<E> key) {
        return (E) Job.DefaultImpls.get(this, key);
    }

    @Override // kotlin.coroutines.CoroutineContext.Element, kotlin.coroutines.CoroutineContext
    public <R> R fold(R r, @NotNull Function2<? super R, ? super CoroutineContext.Element, ? extends R> function2) {
        return (R) Job.DefaultImpls.fold(this, r, function2);
    }

    @Override // kotlin.coroutines.CoroutineContext.Element, kotlin.coroutines.CoroutineContext
    @NotNull
    public CoroutineContext minusKey(@NotNull CoroutineContext.Key<?> key) {
        return Job.DefaultImpls.minusKey(this, key);
    }

    @Override // kotlin.coroutines.CoroutineContext
    @NotNull
    public CoroutineContext plus(@NotNull CoroutineContext context) {
        return Job.DefaultImpls.plus(this, context);
    }

    @Override // kotlin.coroutines.CoroutineContext.Element
    @NotNull
    public final CoroutineContext.Key<?> getKey() {
        return Job.Key;
    }

    @Nullable
    public final ChildHandle getParentHandle$kotlinx_coroutines_core() {
        return (ChildHandle) this._parentHandle;
    }

    public final void setParentHandle$kotlinx_coroutines_core(@Nullable ChildHandle value) {
        this._parentHandle = value;
    }

    protected final void initParentJob(@Nullable Job parent) {
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(getParentHandle$kotlinx_coroutines_core() == null)) {
                throw new AssertionError();
            }
        }
        if (parent == null) {
            setParentHandle$kotlinx_coroutines_core(NonDisposableHandle.INSTANCE);
            return;
        }
        parent.start();
        ChildHandle handle = parent.attachChild(this);
        setParentHandle$kotlinx_coroutines_core(handle);
        if (isCompleted()) {
            handle.dispose();
            setParentHandle$kotlinx_coroutines_core(NonDisposableHandle.INSTANCE);
        }
    }

    @Nullable
    public final Object getState$kotlinx_coroutines_core() {
        while (true) {
            Object state = this._state;
            if (!(state instanceof OpDescriptor)) {
                return state;
            }
            ((OpDescriptor) state).perform(this);
        }
    }

    private final Void loopOnState(Function1<Object, Unit> function1) {
        while (true) {
            function1.invoke(getState$kotlinx_coroutines_core());
        }
    }

    @Override // kotlinx.coroutines.Job
    public boolean isActive() {
        Object state = getState$kotlinx_coroutines_core();
        return (state instanceof Incomplete) && ((Incomplete) state).isActive();
    }

    @Override // kotlinx.coroutines.Job
    public final boolean isCompleted() {
        return !(getState$kotlinx_coroutines_core() instanceof Incomplete);
    }

    @Override // kotlinx.coroutines.Job
    public final boolean isCancelled() {
        Object state = getState$kotlinx_coroutines_core();
        return (state instanceof CompletedExceptionally) || ((state instanceof Finishing) && ((Finishing) state).isCancelling());
    }

    private final Object finalizeFinishingState(Finishing state, Object proposedUpdate) throws Throwable {
        boolean wasCancelling;
        Throwable finalCause;
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(getState$kotlinx_coroutines_core() == state)) {
                throw new AssertionError();
            }
        }
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(!state.isSealed())) {
                throw new AssertionError();
            }
        }
        if (DebugKt.getASSERTIONS_ENABLED() && !state.isCompleting()) {
            throw new AssertionError();
        }
        CompletedExceptionally completedExceptionally = proposedUpdate instanceof CompletedExceptionally ? (CompletedExceptionally) proposedUpdate : null;
        Throwable proposedException = completedExceptionally == null ? null : completedExceptionally.cause;
        synchronized (state) {
            wasCancelling = state.isCancelling();
            List exceptions = state.sealLocked(proposedException);
            finalCause = getFinalRootCause(state, exceptions);
            if (finalCause != null) {
                addSuppressedExceptions(finalCause, exceptions);
            }
        }
        Object completedExceptionally2 = (finalCause == null || finalCause == proposedException) ? proposedUpdate : new CompletedExceptionally(finalCause, false, 2, null);
        Object finalState = completedExceptionally2;
        if (finalCause != null) {
            boolean handled = cancelParent(finalCause) || handleJobException(finalCause);
            if (handled) {
                if (finalState == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.CompletedExceptionally");
                }
                ((CompletedExceptionally) finalState).makeHandled();
            }
        }
        if (!wasCancelling) {
            onCancelling(finalCause);
        }
        onCompletionInternal(finalState);
        boolean casSuccess = _state$FU.compareAndSet(this, state, JobSupportKt.boxIncomplete(finalState));
        if (DebugKt.getASSERTIONS_ENABLED() && !casSuccess) {
            throw new AssertionError();
        }
        completeStateFinalization(state, finalState);
        return finalState;
    }

    private final Throwable getFinalRootCause(Finishing state, List<? extends Throwable> list) {
        Object obj;
        Object obj2;
        if (list.isEmpty()) {
            if (!state.isCancelling()) {
                return null;
            }
            return new JobCancellationException(cancellationExceptionMessage(), null, this);
        }
        List<? extends Throwable> $this$firstOrNull$iv = list;
        Iterator it = $this$firstOrNull$iv.iterator();
        while (true) {
            if (it.hasNext()) {
                Object element$iv = it.next();
                if (!(((Throwable) element$iv) instanceof CancellationException)) {
                    obj = element$iv;
                    break;
                }
            } else {
                obj = null;
                break;
            }
        }
        Throwable firstNonCancellation = (Throwable) obj;
        if (firstNonCancellation != null) {
            return firstNonCancellation;
        }
        Throwable first = list.get(0);
        if (first instanceof TimeoutCancellationException) {
            List<? extends Throwable> $this$firstOrNull$iv2 = list;
            Iterator it2 = $this$firstOrNull$iv2.iterator();
            while (true) {
                if (it2.hasNext()) {
                    Object element$iv2 = it2.next();
                    Throwable it3 = (Throwable) element$iv2;
                    if (it3 != first && (it3 instanceof TimeoutCancellationException)) {
                        obj2 = element$iv2;
                        break;
                    }
                } else {
                    obj2 = null;
                    break;
                }
            }
            Throwable detailedTimeoutException = (Throwable) obj2;
            if (detailedTimeoutException != null) {
                return detailedTimeoutException;
            }
        }
        return first;
    }

    private final void addSuppressedExceptions(Throwable rootCause, List<? extends Throwable> list) {
        if (list.size() <= 1) {
            return;
        }
        int expectedSize$iv = list.size();
        Set seenExceptions = Collections.newSetFromMap(new IdentityHashMap(expectedSize$iv));
        Throwable unwrappedCause = !DebugKt.getRECOVER_STACK_TRACES() ? rootCause : StackTraceRecoveryKt.unwrapImpl(rootCause);
        for (Throwable exception : list) {
            Throwable unwrapped = !DebugKt.getRECOVER_STACK_TRACES() ? exception : StackTraceRecoveryKt.unwrapImpl(exception);
            if (unwrapped != rootCause && unwrapped != unwrappedCause && !(unwrapped instanceof CancellationException) && seenExceptions.add(unwrapped)) {
                kotlin.ExceptionsKt.addSuppressed(rootCause, unwrapped);
            }
        }
    }

    private final boolean tryFinalizeSimpleState(Incomplete state, Object update) throws Throwable {
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!((state instanceof Empty) || (state instanceof JobNode))) {
                throw new AssertionError();
            }
        }
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(!(update instanceof CompletedExceptionally))) {
                throw new AssertionError();
            }
        }
        if (!_state$FU.compareAndSet(this, state, JobSupportKt.boxIncomplete(update))) {
            return false;
        }
        onCancelling(null);
        onCompletionInternal(update);
        completeStateFinalization(state, update);
        return true;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockSplitter
        jadx.core.utils.exceptions.JadxRuntimeException: Unexpected missing predecessor for block: B:16:0x0056
        	at jadx.core.dex.visitors.blocks.BlockSplitter.addTempConnectionsForExcHandlers(BlockSplitter.java:280)
        	at jadx.core.dex.visitors.blocks.BlockSplitter.visit(BlockSplitter.java:79)
        */
    private final void completeStateFinalization(kotlinx.coroutines.Incomplete r7, java.lang.Object r8) throws java.lang.Throwable {
        /*
            r6 = this;
            r0 = r6
            kotlinx.coroutines.ChildHandle r0 = r0.getParentHandle$kotlinx_coroutines_core()
            r9 = r0
            r0 = r9
            if (r0 != 0) goto Lc
            goto L2f
        Lc:
            r0 = r9
            r10 = r0
            r0 = 0
            r11 = r0
            r0 = 0
            r12 = r0
            r0 = r10
            r13 = r0
            r0 = 0
            r14 = r0
            r0 = r13
            r0.dispose()
            r0 = r6
            kotlinx.coroutines.NonDisposableHandle r1 = kotlinx.coroutines.NonDisposableHandle.INSTANCE
            kotlinx.coroutines.ChildHandle r1 = (kotlinx.coroutines.ChildHandle) r1
            r0.setParentHandle$kotlinx_coroutines_core(r1)
        L2f:
            r0 = r8
            boolean r0 = r0 instanceof kotlinx.coroutines.CompletedExceptionally
            if (r0 == 0) goto L3d
            r0 = r8
            kotlinx.coroutines.CompletedExceptionally r0 = (kotlinx.coroutines.CompletedExceptionally) r0
            goto L3e
        L3d:
            r0 = 0
        L3e:
            r10 = r0
            r0 = r10
            if (r0 != 0) goto L49
            r0 = 0
            goto L4e
        L49:
            r0 = r10
            java.lang.Throwable r0 = r0.cause
        L4e:
            r9 = r0
            r0 = r7
            boolean r0 = r0 instanceof kotlinx.coroutines.JobNode
            if (r0 == 0) goto L95
        L57:
            r0 = r7
            kotlinx.coroutines.JobNode r0 = (kotlinx.coroutines.JobNode) r0     // Catch: java.lang.Throwable -> L62
            r1 = r9
            r0.invoke(r1)     // Catch: java.lang.Throwable -> L62
            goto Lac
        L62:
            r10 = move-exception
            r0 = r6
            kotlinx.coroutines.CompletionHandlerException r1 = new kotlinx.coroutines.CompletionHandlerException
            r2 = r1
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r4 = r3
            r4.<init>()
            java.lang.String r4 = "Exception in completion handler "
            java.lang.StringBuilder r3 = r3.append(r4)
            r4 = r7
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r4 = " for "
            java.lang.StringBuilder r3 = r3.append(r4)
            r4 = r6
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r3 = r3.toString()
            r4 = r10
            r2.<init>(r3, r4)
            java.lang.Throwable r1 = (java.lang.Throwable) r1
            r0.handleOnCompletionException$kotlinx_coroutines_core(r1)
            goto Lac
        L95:
            r0 = r7
            kotlinx.coroutines.NodeList r0 = r0.getList()
            r10 = r0
            r0 = r10
            if (r0 != 0) goto La5
            goto Lac
        La5:
            r0 = r6
            r1 = r10
            r2 = r9
            r0.notifyCompletion(r1, r2)
        Lac:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.JobSupport.completeStateFinalization(kotlinx.coroutines.Incomplete, java.lang.Object):void");
    }

    private final void notifyCancelling(NodeList list, Throwable cause) throws Throwable {
        Throwable th;
        onCancelling(cause);
        Object exception$iv = null;
        NodeList this_$iv$iv = list;
        LockFreeLinkedListNode nextNode = (LockFreeLinkedListNode) this_$iv$iv.getNext();
        while (true) {
            LockFreeLinkedListNode cur$iv$iv = nextNode;
            if (Intrinsics.areEqual(cur$iv$iv, this_$iv$iv)) {
                break;
            }
            if (cur$iv$iv instanceof JobCancellingNode) {
                JobNode node$iv = (JobNode) cur$iv$iv;
                try {
                    node$iv.invoke(cause);
                } catch (Throwable ex$iv) {
                    Throwable $this$notifyHandlers_u24lambda_u2d14_u24lambda_u2d12$iv = (Throwable) exception$iv;
                    if ($this$notifyHandlers_u24lambda_u2d14_u24lambda_u2d12$iv == null) {
                        th = null;
                    } else {
                        kotlin.ExceptionsKt.addSuppressed($this$notifyHandlers_u24lambda_u2d14_u24lambda_u2d12$iv, ex$iv);
                        th = $this$notifyHandlers_u24lambda_u2d14_u24lambda_u2d12$iv;
                    }
                    if (th == null) {
                        JobSupport $this$notifyHandlers_u24lambda_u2d14_u24lambda_u2d13$iv = this;
                        exception$iv = new CompletionHandlerException("Exception in completion handler " + node$iv + " for " + $this$notifyHandlers_u24lambda_u2d14_u24lambda_u2d13$iv, ex$iv);
                    }
                }
            }
            nextNode = cur$iv$iv.getNextNode();
        }
        Throwable it$iv = (Throwable) exception$iv;
        if (it$iv != null) {
            handleOnCompletionException$kotlinx_coroutines_core(it$iv);
        }
        cancelParent(cause);
    }

    private final boolean cancelParent(Throwable cause) {
        if (isScopedCoroutine()) {
            return true;
        }
        boolean isCancellation = cause instanceof CancellationException;
        ChildHandle parent = getParentHandle$kotlinx_coroutines_core();
        if (parent == null || parent == NonDisposableHandle.INSTANCE) {
            return isCancellation;
        }
        return parent.childCancelled(cause) || isCancellation;
    }

    private final void notifyCompletion(NodeList $this$notifyCompletion, Throwable cause) throws Throwable {
        Throwable th;
        Object exception$iv = null;
        NodeList this_$iv$iv = $this$notifyCompletion;
        LockFreeLinkedListNode nextNode = (LockFreeLinkedListNode) this_$iv$iv.getNext();
        while (true) {
            LockFreeLinkedListNode cur$iv$iv = nextNode;
            if (Intrinsics.areEqual(cur$iv$iv, this_$iv$iv)) {
                break;
            }
            if (cur$iv$iv instanceof JobNode) {
                JobNode node$iv = (JobNode) cur$iv$iv;
                try {
                    node$iv.invoke(cause);
                } catch (Throwable ex$iv) {
                    Throwable $this$notifyHandlers_u24lambda_u2d14_u24lambda_u2d12$iv = (Throwable) exception$iv;
                    if ($this$notifyHandlers_u24lambda_u2d14_u24lambda_u2d12$iv == null) {
                        th = null;
                    } else {
                        kotlin.ExceptionsKt.addSuppressed($this$notifyHandlers_u24lambda_u2d14_u24lambda_u2d12$iv, ex$iv);
                        th = $this$notifyHandlers_u24lambda_u2d14_u24lambda_u2d12$iv;
                    }
                    if (th == null) {
                        JobSupport $this$notifyHandlers_u24lambda_u2d14_u24lambda_u2d13$iv = this;
                        exception$iv = new CompletionHandlerException("Exception in completion handler " + node$iv + " for " + $this$notifyHandlers_u24lambda_u2d14_u24lambda_u2d13$iv, ex$iv);
                    }
                }
            }
            nextNode = cur$iv$iv.getNextNode();
        }
        Throwable it$iv = (Throwable) exception$iv;
        if (it$iv == null) {
            return;
        }
        handleOnCompletionException$kotlinx_coroutines_core(it$iv);
    }

    private final /* synthetic */ <T extends JobNode> void notifyHandlers(NodeList list, Throwable cause) throws Throwable {
        Throwable th;
        Object exception = null;
        NodeList this_$iv = list;
        LockFreeLinkedListNode nextNode = (LockFreeLinkedListNode) this_$iv.getNext();
        while (true) {
            LockFreeLinkedListNode cur$iv = nextNode;
            if (Intrinsics.areEqual(cur$iv, this_$iv)) {
                break;
            }
            Intrinsics.reifiedOperationMarker(3, PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE);
            if (cur$iv instanceof LockFreeLinkedListNode) {
                JobNode node = (JobNode) cur$iv;
                try {
                    node.invoke(cause);
                } catch (Throwable ex) {
                    Throwable th2 = (Throwable) exception;
                    if (th2 == null) {
                        th = null;
                    } else {
                        Throwable $this$notifyHandlers_u24lambda_u2d14_u24lambda_u2d12 = th2;
                        kotlin.ExceptionsKt.addSuppressed($this$notifyHandlers_u24lambda_u2d14_u24lambda_u2d12, ex);
                        th = th2;
                    }
                    if (th == null) {
                        JobSupport $this$notifyHandlers_u24lambda_u2d14_u24lambda_u2d13 = this;
                        exception = new CompletionHandlerException("Exception in completion handler " + node + " for " + $this$notifyHandlers_u24lambda_u2d14_u24lambda_u2d13, ex);
                    }
                }
            }
            nextNode = cur$iv.getNextNode();
        }
        Throwable th3 = (Throwable) exception;
        if (th3 == null) {
            return;
        }
        Throwable it = th3;
        handleOnCompletionException$kotlinx_coroutines_core(it);
    }

    @Override // kotlinx.coroutines.Job
    public final boolean start() {
        while (true) {
            Object state = getState$kotlinx_coroutines_core();
            switch (startInternal(state)) {
                case 0:
                    return false;
                case 1:
                    return true;
            }
        }
    }

    private final int startInternal(Object state) {
        if (state instanceof Empty) {
            if (((Empty) state).isActive()) {
                return 0;
            }
            if (!_state$FU.compareAndSet(this, state, JobSupportKt.EMPTY_ACTIVE)) {
                return -1;
            }
            onStart();
            return 1;
        }
        if (state instanceof InactiveNodeList) {
            if (!_state$FU.compareAndSet(this, state, ((InactiveNodeList) state).getList())) {
                return -1;
            }
            onStart();
            return 1;
        }
        return 0;
    }

    protected void onStart() {
    }

    @Override // kotlinx.coroutines.Job
    @NotNull
    public final CancellationException getCancellationException() {
        Object state = getState$kotlinx_coroutines_core();
        if (!(state instanceof Finishing)) {
            if (state instanceof Incomplete) {
                throw new IllegalStateException(Intrinsics.stringPlus("Job is still new or active: ", this).toString());
            }
            return state instanceof CompletedExceptionally ? toCancellationException$default(this, ((CompletedExceptionally) state).cause, null, 1, null) : new JobCancellationException(Intrinsics.stringPlus(DebugStringsKt.getClassSimpleName(this), " has completed normally"), null, this);
        }
        Throwable rootCause = ((Finishing) state).getRootCause();
        if (rootCause != null) {
            return toCancellationException(rootCause, Intrinsics.stringPlus(DebugStringsKt.getClassSimpleName(this), " is cancelling"));
        }
        throw new IllegalStateException(Intrinsics.stringPlus("Job is still new or active: ", this).toString());
    }

    public static /* synthetic */ CancellationException toCancellationException$default(JobSupport jobSupport, Throwable th, String str, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: toCancellationException");
        }
        if ((i & 1) != 0) {
            str = null;
        }
        return jobSupport.toCancellationException(th, str);
    }

    @NotNull
    protected final CancellationException toCancellationException(@NotNull Throwable $this$toCancellationException, @Nullable String message) {
        CancellationException cancellationException = $this$toCancellationException instanceof CancellationException ? (CancellationException) $this$toCancellationException : null;
        if (cancellationException != null) {
            return cancellationException;
        }
        return new JobCancellationException(message == null ? cancellationExceptionMessage() : message, $this$toCancellationException, this);
    }

    @Nullable
    protected final Throwable getCompletionCause() {
        Object state = getState$kotlinx_coroutines_core();
        if (state instanceof Finishing) {
            Throwable rootCause = ((Finishing) state).getRootCause();
            if (rootCause != null) {
                return rootCause;
            }
            throw new IllegalStateException(Intrinsics.stringPlus("Job is still new or active: ", this).toString());
        }
        if (state instanceof Incomplete) {
            throw new IllegalStateException(Intrinsics.stringPlus("Job is still new or active: ", this).toString());
        }
        if (state instanceof CompletedExceptionally) {
            return ((CompletedExceptionally) state).cause;
        }
        return null;
    }

    protected final boolean getCompletionCauseHandled() {
        Object it = getState$kotlinx_coroutines_core();
        return (it instanceof CompletedExceptionally) && ((CompletedExceptionally) it).getHandled();
    }

    @Override // kotlinx.coroutines.Job
    @NotNull
    public final DisposableHandle invokeOnCompletion(@NotNull Function1<? super Throwable, Unit> function1) {
        return invokeOnCompletion(false, true, function1);
    }

    @Override // kotlinx.coroutines.Job
    @NotNull
    public final DisposableHandle invokeOnCompletion(boolean onCancelling, boolean invokeImmediately, @NotNull Function1<? super Throwable, Unit> function1) {
        JobNode node = makeNode(function1, onCancelling);
        while (true) {
            Object state = getState$kotlinx_coroutines_core();
            if (state instanceof Empty) {
                if (((Empty) state).isActive()) {
                    if (_state$FU.compareAndSet(this, state, node)) {
                        return node;
                    }
                } else {
                    promoteEmptyToNodeList((Empty) state);
                }
            } else if (state instanceof Incomplete) {
                NodeList list = ((Incomplete) state).getList();
                if (list != null) {
                    Object rootCause = null;
                    Object handle = NonDisposableHandle.INSTANCE;
                    if (onCancelling && (state instanceof Finishing)) {
                        synchronized (state) {
                            rootCause = ((Finishing) state).getRootCause();
                            if (rootCause == null || ((function1 instanceof ChildHandleNode) && !((Finishing) state).isCompleting())) {
                                if (addLastAtomic(state, list, node)) {
                                    if (rootCause == null) {
                                        return node;
                                    }
                                    handle = node;
                                }
                            }
                            Unit unit = Unit.INSTANCE;
                        }
                    }
                    if (rootCause != null) {
                        if (invokeImmediately) {
                            Object cause$iv = rootCause;
                            function1.invoke(cause$iv);
                        }
                        return (DisposableHandle) handle;
                    }
                    if (addLastAtomic(state, list, node)) {
                        return node;
                    }
                } else {
                    if (state == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.JobNode");
                    }
                    promoteSingleToNodeList((JobNode) state);
                }
            } else {
                if (invokeImmediately) {
                    CompletedExceptionally completedExceptionally = state instanceof CompletedExceptionally ? (CompletedExceptionally) state : null;
                    Throwable cause$iv2 = completedExceptionally == null ? null : completedExceptionally.cause;
                    function1.invoke(cause$iv2);
                }
                return NonDisposableHandle.INSTANCE;
            }
        }
    }

    private final JobNode makeNode(Function1<? super Throwable, Unit> function1, boolean onCancelling) {
        JobNode jobNode;
        InvokeOnCompletion invokeOnCompletion;
        if (onCancelling) {
            JobCancellingNode jobCancellingNode = function1 instanceof JobCancellingNode ? (JobCancellingNode) function1 : null;
            invokeOnCompletion = jobCancellingNode == null ? new InvokeOnCancelling(function1) : jobCancellingNode;
        } else {
            JobNode it = function1 instanceof JobNode ? (JobNode) function1 : null;
            if (it == null) {
                jobNode = null;
            } else {
                if (DebugKt.getASSERTIONS_ENABLED()) {
                    if (!(!(it instanceof JobCancellingNode))) {
                        throw new AssertionError();
                    }
                }
                jobNode = it;
            }
            JobNode jobNode2 = jobNode;
            invokeOnCompletion = jobNode2 == null ? new InvokeOnCompletion(function1) : jobNode2;
        }
        JobNode node = invokeOnCompletion;
        node.setJob(this);
        return node;
    }

    private final boolean addLastAtomic(final Object expect, NodeList list, JobNode node) {
        NodeList this_$iv = list;
        final JobNode jobNode = node;
        LockFreeLinkedListNode.CondAddOp condAdd$iv = new LockFreeLinkedListNode.CondAddOp(this, expect) { // from class: kotlinx.coroutines.JobSupport$addLastAtomic$$inlined$addLastIf$1
            final /* synthetic */ JobSupport this$0;
            final /* synthetic */ Object $expect$inlined;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.$node);
                this.this$0 = this;
                this.$expect$inlined = expect;
            }

            @Override // kotlinx.coroutines.internal.AtomicOp
            @Nullable
            public Object prepare(@NotNull LockFreeLinkedListNode affected) {
                if (this.this$0.getState$kotlinx_coroutines_core() == this.$expect$inlined) {
                    return null;
                }
                return LockFreeLinkedListKt.getCONDITION_FALSE();
            }
        };
        while (true) {
            LockFreeLinkedListNode prev$iv = this_$iv.getPrevNode();
            switch (prev$iv.tryCondAddNext(node, this_$iv, condAdd$iv)) {
                case 1:
                    return true;
                case 2:
                    return false;
            }
        }
    }

    private final void promoteEmptyToNodeList(Empty state) {
        NodeList list = new NodeList();
        Incomplete update = state.isActive() ? list : new InactiveNodeList(list);
        _state$FU.compareAndSet(this, state, update);
    }

    private final void promoteSingleToNodeList(JobNode state) {
        state.addOneIfEmpty(new NodeList());
        LockFreeLinkedListNode list = state.getNextNode();
        _state$FU.compareAndSet(this, state, list);
    }

    @Override // kotlinx.coroutines.Job
    @Nullable
    public final Object join(@NotNull Continuation<? super Unit> continuation) {
        if (!joinInternal()) {
            JobKt.ensureActive(continuation.getContext());
            return Unit.INSTANCE;
        }
        Object objJoinSuspend = joinSuspend(continuation);
        return objJoinSuspend == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objJoinSuspend : Unit.INSTANCE;
    }

    private final boolean joinInternal() {
        Object state;
        do {
            state = getState$kotlinx_coroutines_core();
            if (!(state instanceof Incomplete)) {
                return false;
            }
        } while (startInternal(state) < 0);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object joinSuspend(Continuation<? super Unit> continuation) {
        CancellableContinuationImpl cancellable$iv = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellable$iv.initCancellability();
        CancellableContinuationImpl cont = cancellable$iv;
        CompletionHandlerBase $this$asHandler$iv = new ResumeOnCompletion(cont);
        CancellableContinuationKt.disposeOnCancellation(cont, invokeOnCompletion($this$asHandler$iv));
        Object result = cancellable$iv.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? result : Unit.INSTANCE;
    }

    @Override // kotlinx.coroutines.Job
    @NotNull
    public final SelectClause0 getOnJoin() {
        return this;
    }

    @Override // kotlinx.coroutines.selects.SelectClause0
    public final <R> void registerSelectClause0(@NotNull SelectInstance<? super R> selectInstance, @NotNull Function1<? super Continuation<? super R>, ? extends Object> function1) {
        Object state;
        do {
            state = getState$kotlinx_coroutines_core();
            if (selectInstance.isSelected()) {
                return;
            }
            if (!(state instanceof Incomplete)) {
                if (selectInstance.trySelect()) {
                    UndispatchedKt.startCoroutineUnintercepted(function1, selectInstance.getCompletion());
                    return;
                }
                return;
            }
        } while (startInternal(state) != 0);
        CompletionHandlerBase $this$asHandler$iv = new SelectJoinOnCompletion(selectInstance, function1);
        selectInstance.disposeOnSelect(invokeOnCompletion($this$asHandler$iv));
    }

    public final void removeNode$kotlinx_coroutines_core(@NotNull JobNode node) {
        Object state;
        do {
            state = getState$kotlinx_coroutines_core();
            if (state instanceof JobNode) {
                if (state != node) {
                    return;
                }
            } else {
                if (!(state instanceof Incomplete) || ((Incomplete) state).getList() == null) {
                    return;
                }
                node.remove();
                return;
            }
        } while (!_state$FU.compareAndSet(this, state, JobSupportKt.EMPTY_ACTIVE));
    }

    public boolean getOnCancelComplete$kotlinx_coroutines_core() {
        return false;
    }

    @Override // kotlinx.coroutines.Job
    public void cancel(@Nullable CancellationException cause) throws Throwable {
        JobCancellationException jobCancellationException;
        if (cause != null) {
            jobCancellationException = cause;
        } else {
            jobCancellationException = new JobCancellationException(cancellationExceptionMessage(), null, this);
        }
        cancelInternal(jobCancellationException);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @NotNull
    public String cancellationExceptionMessage() {
        return "Job was cancelled";
    }

    @Override // kotlinx.coroutines.Job
    @Deprecated(message = "Added since 1.2.0 for binary compatibility with versions <= 1.1.x", level = DeprecationLevel.HIDDEN)
    public /* synthetic */ boolean cancel(Throwable cause) throws Throwable {
        JobCancellationException cancellationException$default;
        if (cause != null) {
            cancellationException$default = toCancellationException$default(this, cause, null, 1, null);
        } else {
            cancellationException$default = new JobCancellationException(cancellationExceptionMessage(), null, this);
        }
        cancelInternal(cancellationException$default);
        return true;
    }

    public void cancelInternal(@NotNull Throwable cause) throws Throwable {
        cancelImpl$kotlinx_coroutines_core(cause);
    }

    @Override // kotlinx.coroutines.ChildJob
    public final void parentCancelled(@NotNull ParentJob parentJob) throws Throwable {
        cancelImpl$kotlinx_coroutines_core(parentJob);
    }

    public boolean childCancelled(@NotNull Throwable cause) {
        if (cause instanceof CancellationException) {
            return true;
        }
        return cancelImpl$kotlinx_coroutines_core(cause) && getHandlesException$kotlinx_coroutines_core();
    }

    public final boolean cancelCoroutine(@Nullable Throwable cause) {
        return cancelImpl$kotlinx_coroutines_core(cause);
    }

    public final boolean cancelImpl$kotlinx_coroutines_core(@Nullable Object cause) throws Throwable {
        Object finalState = JobSupportKt.COMPLETING_ALREADY;
        if (getOnCancelComplete$kotlinx_coroutines_core()) {
            finalState = cancelMakeCompleting(cause);
            if (finalState == JobSupportKt.COMPLETING_WAITING_CHILDREN) {
                return true;
            }
        }
        if (finalState == JobSupportKt.COMPLETING_ALREADY) {
            finalState = makeCancelling(cause);
        }
        if (finalState == JobSupportKt.COMPLETING_ALREADY || finalState == JobSupportKt.COMPLETING_WAITING_CHILDREN) {
            return true;
        }
        if (finalState == JobSupportKt.TOO_LATE_TO_CANCEL) {
            return false;
        }
        afterCompletion(finalState);
        return true;
    }

    private final Object cancelMakeCompleting(Object cause) {
        Object finalState;
        do {
            Object state = getState$kotlinx_coroutines_core();
            if (!(state instanceof Incomplete) || ((state instanceof Finishing) && ((Finishing) state).isCompleting())) {
                return JobSupportKt.COMPLETING_ALREADY;
            }
            CompletedExceptionally proposedUpdate = new CompletedExceptionally(createCauseException(cause), false, 2, null);
            finalState = tryMakeCompleting(state, proposedUpdate);
        } while (finalState == JobSupportKt.COMPLETING_RETRY);
        return finalState;
    }

    public static /* synthetic */ JobCancellationException defaultCancellationException$kotlinx_coroutines_core$default(JobSupport jobSupport, String message, Throwable cause, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: defaultCancellationException");
        }
        if ((i & 1) != 0) {
            message = null;
        }
        if ((i & 2) != 0) {
            cause = null;
        }
        String str = message;
        return new JobCancellationException(str == null ? jobSupport.cancellationExceptionMessage() : str, cause, jobSupport);
    }

    @NotNull
    public final JobCancellationException defaultCancellationException$kotlinx_coroutines_core(@Nullable String message, @Nullable Throwable cause) {
        return new JobCancellationException(message == null ? cancellationExceptionMessage() : message, cause, this);
    }

    @Override // kotlinx.coroutines.ParentJob
    @NotNull
    public CancellationException getChildJobCancellationCause() {
        Throwable rootCause;
        Object state = getState$kotlinx_coroutines_core();
        if (state instanceof Finishing) {
            rootCause = ((Finishing) state).getRootCause();
        } else if (state instanceof CompletedExceptionally) {
            rootCause = ((CompletedExceptionally) state).cause;
        } else {
            if (state instanceof Incomplete) {
                throw new IllegalStateException(Intrinsics.stringPlus("Cannot be cancelling child in this state: ", state).toString());
            }
            rootCause = null;
        }
        Throwable rootCause2 = rootCause;
        CancellationException cancellationException = rootCause2 instanceof CancellationException ? (CancellationException) rootCause2 : null;
        return cancellationException == null ? new JobCancellationException(Intrinsics.stringPlus("Parent job is ", stateString(state)), rootCause2, this) : cancellationException;
    }

    private final Throwable createCauseException(Object cause) {
        if (!(cause == null ? true : cause instanceof Throwable)) {
            if (cause == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.ParentJob");
            }
            return ((ParentJob) cause).getChildJobCancellationCause();
        }
        Throwable th = (Throwable) cause;
        if (th != null) {
            return th;
        }
        return new JobCancellationException(cancellationExceptionMessage(), null, this);
    }

    private final Object makeCancelling(Object cause) throws Throwable {
        Throwable th;
        Throwable th2 = null;
        while (true) {
            Object state = getState$kotlinx_coroutines_core();
            if (state instanceof Finishing) {
                synchronized (state) {
                    if (((Finishing) state).isSealed()) {
                        return JobSupportKt.TOO_LATE_TO_CANCEL;
                    }
                    boolean wasCancelling = ((Finishing) state).isCancelling();
                    if (cause != null || !wasCancelling) {
                        Throwable th3 = th2;
                        ((Finishing) state).addExceptionLocked(th3 == null ? createCauseException(cause) : th3);
                    }
                    Throwable notifyRootCause = !wasCancelling ? ((Finishing) state).getRootCause() : null;
                    if (notifyRootCause != null) {
                        notifyCancelling(((Finishing) state).getList(), notifyRootCause);
                    }
                    return JobSupportKt.COMPLETING_ALREADY;
                }
            }
            if (!(state instanceof Incomplete)) {
                return JobSupportKt.TOO_LATE_TO_CANCEL;
            }
            Throwable th4 = th2;
            if (th4 == null) {
                Throwable it = createCauseException(cause);
                th2 = it;
                th = it;
            } else {
                th = th4;
            }
            Throwable causeException = th;
            if (!((Incomplete) state).isActive()) {
                Object finalState = tryMakeCompleting(state, new CompletedExceptionally(causeException, false, 2, null));
                if (finalState == JobSupportKt.COMPLETING_ALREADY) {
                    throw new IllegalStateException(Intrinsics.stringPlus("Cannot happen in ", state).toString());
                }
                if (finalState != JobSupportKt.COMPLETING_RETRY) {
                    return finalState;
                }
            } else if (tryMakeCancelling((Incomplete) state, causeException)) {
                return JobSupportKt.COMPLETING_ALREADY;
            }
        }
    }

    private final NodeList getOrPromoteCancellingList(Incomplete state) {
        NodeList list = state.getList();
        if (list != null) {
            return list;
        }
        if (state instanceof Empty) {
            return new NodeList();
        }
        if (state instanceof JobNode) {
            promoteSingleToNodeList((JobNode) state);
            return (NodeList) null;
        }
        throw new IllegalStateException(Intrinsics.stringPlus("State should have list: ", state).toString());
    }

    private final boolean tryMakeCancelling(Incomplete state, Throwable rootCause) throws Throwable {
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(!(state instanceof Finishing))) {
                throw new AssertionError();
            }
        }
        if (DebugKt.getASSERTIONS_ENABLED() && !state.isActive()) {
            throw new AssertionError();
        }
        NodeList list = getOrPromoteCancellingList(state);
        if (list == null) {
            return false;
        }
        Finishing cancelling = new Finishing(list, false, rootCause);
        if (!_state$FU.compareAndSet(this, state, cancelling)) {
            return false;
        }
        notifyCancelling(list, rootCause);
        return true;
    }

    public final boolean makeCompleting$kotlinx_coroutines_core(@Nullable Object proposedUpdate) {
        Object finalState;
        do {
            Object state = getState$kotlinx_coroutines_core();
            finalState = tryMakeCompleting(state, proposedUpdate);
            if (finalState == JobSupportKt.COMPLETING_ALREADY) {
                return false;
            }
            if (finalState == JobSupportKt.COMPLETING_WAITING_CHILDREN) {
                return true;
            }
        } while (finalState == JobSupportKt.COMPLETING_RETRY);
        afterCompletion(finalState);
        return true;
    }

    @Nullable
    public final Object makeCompletingOnce$kotlinx_coroutines_core(@Nullable Object proposedUpdate) {
        Object finalState;
        do {
            Object state = getState$kotlinx_coroutines_core();
            finalState = tryMakeCompleting(state, proposedUpdate);
            if (finalState == JobSupportKt.COMPLETING_ALREADY) {
                throw new IllegalStateException("Job " + this + " is already complete or completing, but is being completed with " + proposedUpdate, getExceptionOrNull(proposedUpdate));
            }
        } while (finalState == JobSupportKt.COMPLETING_RETRY);
        return finalState;
    }

    private final Object tryMakeCompleting(Object state, Object proposedUpdate) {
        if (!(state instanceof Incomplete)) {
            return JobSupportKt.COMPLETING_ALREADY;
        }
        if (((state instanceof Empty) || (state instanceof JobNode)) && !(state instanceof ChildHandleNode) && !(proposedUpdate instanceof CompletedExceptionally)) {
            if (!tryFinalizeSimpleState((Incomplete) state, proposedUpdate)) {
                return JobSupportKt.COMPLETING_RETRY;
            }
            return proposedUpdate;
        }
        return tryMakeCompletingSlowPath((Incomplete) state, proposedUpdate);
    }

    private final Object tryMakeCompletingSlowPath(Incomplete state, Object proposedUpdate) throws Throwable {
        NodeList list = getOrPromoteCancellingList(state);
        if (list == null) {
            return JobSupportKt.COMPLETING_RETRY;
        }
        Finishing finishing = state instanceof Finishing ? (Finishing) state : null;
        Finishing finishing2 = finishing == null ? new Finishing(list, false, null) : finishing;
        synchronized (finishing2) {
            if (finishing2.isCompleting()) {
                return JobSupportKt.COMPLETING_ALREADY;
            }
            finishing2.setCompleting(true);
            if (finishing2 != state && !_state$FU.compareAndSet(this, state, finishing2)) {
                return JobSupportKt.COMPLETING_RETRY;
            }
            if (DebugKt.getASSERTIONS_ENABLED()) {
                if (!(!finishing2.isSealed())) {
                    throw new AssertionError();
                }
            }
            boolean wasCancelling = finishing2.isCancelling();
            CompletedExceptionally it = proposedUpdate instanceof CompletedExceptionally ? (CompletedExceptionally) proposedUpdate : null;
            if (it != null) {
                finishing2.addExceptionLocked(it.cause);
            }
            Throwable rootCause = !wasCancelling ? finishing2.getRootCause() : null;
            Unit unit = Unit.INSTANCE;
            if (rootCause != null) {
                notifyCancelling(list, rootCause);
            }
            ChildHandleNode child = firstChild(state);
            return (child == null || !tryWaitForChild(finishing2, child, proposedUpdate)) ? finalizeFinishingState(finishing2, proposedUpdate) : JobSupportKt.COMPLETING_WAITING_CHILDREN;
        }
    }

    private final Throwable getExceptionOrNull(Object $this$exceptionOrNull) {
        CompletedExceptionally completedExceptionally = $this$exceptionOrNull instanceof CompletedExceptionally ? (CompletedExceptionally) $this$exceptionOrNull : null;
        if (completedExceptionally == null) {
            return null;
        }
        return completedExceptionally.cause;
    }

    private final ChildHandleNode firstChild(Incomplete state) {
        ChildHandleNode childHandleNode = state instanceof ChildHandleNode ? (ChildHandleNode) state : null;
        if (childHandleNode != null) {
            return childHandleNode;
        }
        NodeList list = state.getList();
        if (list == null) {
            return null;
        }
        return nextChild(list);
    }

    private final boolean tryWaitForChild(Finishing state, ChildHandleNode child, Object proposedUpdate) {
        JobSupport jobSupport = this;
        Finishing finishing = state;
        ChildHandleNode childHandleNode = child;
        Object obj = proposedUpdate;
        while (true) {
            Object obj2 = obj;
            JobSupport jobSupport2 = jobSupport;
            Finishing finishing2 = finishing;
            ChildHandleNode childHandleNode2 = childHandleNode;
            ChildJob childJob = childHandleNode2.childJob;
            CompletionHandlerBase $this$asHandler$iv = new ChildCompletion(jobSupport2, finishing2, childHandleNode2, obj2);
            DisposableHandle handle = Job.DefaultImpls.invokeOnCompletion$default(childJob, false, false, $this$asHandler$iv, 1, null);
            if (handle != NonDisposableHandle.INSTANCE) {
                return true;
            }
            ChildHandleNode nextChild = jobSupport2.nextChild(childHandleNode2);
            if (nextChild == null) {
                return false;
            }
            jobSupport = jobSupport2;
            finishing = finishing2;
            childHandleNode = nextChild;
            obj = obj2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void continueCompleting(Finishing state, ChildHandleNode lastChild, Object proposedUpdate) throws Throwable {
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(getState$kotlinx_coroutines_core() == state)) {
                throw new AssertionError();
            }
        }
        ChildHandleNode waitChild = nextChild(lastChild);
        if (waitChild == null || !tryWaitForChild(state, waitChild, proposedUpdate)) {
            Object finalState = finalizeFinishingState(state, proposedUpdate);
            afterCompletion(finalState);
        }
    }

    private final ChildHandleNode nextChild(LockFreeLinkedListNode $this$nextChild) {
        LockFreeLinkedListNode cur;
        LockFreeLinkedListNode prevNode = $this$nextChild;
        while (true) {
            cur = prevNode;
            if (!cur.isRemoved()) {
                break;
            }
            prevNode = cur.getPrevNode();
        }
        while (true) {
            cur = cur.getNextNode();
            if (!cur.isRemoved()) {
                if (cur instanceof ChildHandleNode) {
                    return (ChildHandleNode) cur;
                }
                if (cur instanceof NodeList) {
                    return null;
                }
            }
        }
    }

    @Override // kotlinx.coroutines.Job
    @NotNull
    public final Sequence<Job> getChildren() {
        return SequencesKt.sequence(new JobSupport$children$1(this, null));
    }

    @Override // kotlinx.coroutines.Job
    @NotNull
    public final ChildHandle attachChild(@NotNull ChildJob child) {
        CompletionHandlerBase $this$asHandler$iv = new ChildHandleNode(child);
        return (ChildHandle) Job.DefaultImpls.invokeOnCompletion$default(this, true, false, $this$asHandler$iv, 2, null);
    }

    public void handleOnCompletionException$kotlinx_coroutines_core(@NotNull Throwable exception) throws Throwable {
        throw exception;
    }

    protected void onCancelling(@Nullable Throwable cause) {
    }

    protected boolean isScopedCoroutine() {
        return false;
    }

    public boolean getHandlesException$kotlinx_coroutines_core() {
        return true;
    }

    protected boolean handleJobException(@NotNull Throwable exception) {
        return false;
    }

    protected void onCompletionInternal(@Nullable Object state) {
    }

    protected void afterCompletion(@Nullable Object state) {
    }

    @NotNull
    public String toString() {
        return toDebugString() + '@' + DebugStringsKt.getHexAddress(this);
    }

    @InternalCoroutinesApi
    @NotNull
    public final String toDebugString() {
        return nameString$kotlinx_coroutines_core() + '{' + stateString(getState$kotlinx_coroutines_core()) + '}';
    }

    @NotNull
    public String nameString$kotlinx_coroutines_core() {
        return DebugStringsKt.getClassSimpleName(this);
    }

    private final String stateString(Object state) {
        return state instanceof Finishing ? ((Finishing) state).isCancelling() ? "Cancelling" : ((Finishing) state).isCompleting() ? "Completing" : "Active" : state instanceof Incomplete ? ((Incomplete) state).isActive() ? "Active" : "New" : state instanceof CompletedExceptionally ? "Cancelled" : "Completed";
    }

    /* compiled from: JobSupport.kt */
    @Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n��\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010��\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\u0018\u0002\b\u0002\u0018��2\u00060\u0018j\u0002`,2\u00020-B!\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0015\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\u0005¢\u0006\u0004\b\u000b\u0010\fJ\u001f\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\u00050\rj\b\u0012\u0004\u0012\u00020\u0005`\u000eH\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\u001d\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00050\u00122\b\u0010\u0011\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0013\u0010\u0014J\u000f\u0010\u0016\u001a\u00020\u0015H\u0016¢\u0006\u0004\b\u0016\u0010\u0017R(\u0010\u001e\u001a\u0004\u0018\u00010\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u00188B@BX\u0082\u000e¢\u0006\f\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u0014\u0010\u001f\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010 R\u0011\u0010!\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b!\u0010 R$\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u00038F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0004\u0010 \"\u0004\b\"\u0010#R\u0011\u0010$\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b$\u0010 R\u001a\u0010\u0002\u001a\u00020\u00018\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0002\u0010%\u001a\u0004\b&\u0010'R(\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\u0010\u0019\u001a\u0004\u0018\u00010\u00058F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b(\u0010)\"\u0004\b*\u0010\f¨\u0006+"}, d2 = {"Lkotlinx/coroutines/JobSupport$Finishing;", "Lkotlinx/coroutines/NodeList;", BeanDefinitionParserDelegate.LIST_ELEMENT, "", "isCompleting", "", "rootCause", Constants.CONSTRUCTOR_NAME, "(Lkotlinx/coroutines/NodeList;ZLjava/lang/Throwable;)V", "exception", "", "addExceptionLocked", "(Ljava/lang/Throwable;)V", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "allocateList", "()Ljava/util/ArrayList;", "proposedException", "", "sealLocked", "(Ljava/lang/Throwable;)Ljava/util/List;", "", "toString", "()Ljava/lang/String;", "", "value", "getExceptionsHolder", "()Ljava/lang/Object;", "setExceptionsHolder", "(Ljava/lang/Object;)V", "exceptionsHolder", "isActive", "()Z", "isCancelling", "setCompleting", "(Z)V", "isSealed", "Lkotlinx/coroutines/NodeList;", "getList", "()Lkotlinx/coroutines/NodeList;", "getRootCause", "()Ljava/lang/Throwable;", "setRootCause", "kotlinx-coroutines-core", "Lkotlinx/coroutines/internal/SynchronizedObject;", "Lkotlinx/coroutines/Incomplete;"})
    /* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/JobSupport$Finishing.class */
    private static final class Finishing implements Incomplete {

        @NotNull
        private final NodeList list;

        @NotNull
        private volatile /* synthetic */ int _isCompleting;

        @NotNull
        private volatile /* synthetic */ Object _rootCause;

        @NotNull
        private volatile /* synthetic */ Object _exceptionsHolder = null;

        @Override // kotlinx.coroutines.Incomplete
        @NotNull
        public NodeList getList() {
            return this.list;
        }

        public Finishing(@NotNull NodeList nodeList, boolean z, @Nullable Throwable th) {
            this.list = nodeList;
            this._isCompleting = z ? 1 : 0;
            this._rootCause = th;
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [boolean, int] */
        public final boolean isCompleting() {
            return this._isCompleting;
        }

        public final void setCompleting(boolean z) {
            this._isCompleting = z ? 1 : 0;
        }

        @Nullable
        public final Throwable getRootCause() {
            return (Throwable) this._rootCause;
        }

        public final void setRootCause(@Nullable Throwable value) {
            this._rootCause = value;
        }

        private final Object getExceptionsHolder() {
            return this._exceptionsHolder;
        }

        private final void setExceptionsHolder(Object value) {
            this._exceptionsHolder = value;
        }

        public final boolean isSealed() {
            return getExceptionsHolder() == JobSupportKt.SEALED;
        }

        public final boolean isCancelling() {
            return getRootCause() != null;
        }

        @Override // kotlinx.coroutines.Incomplete
        public boolean isActive() {
            return getRootCause() == null;
        }

        @NotNull
        public final List<Throwable> sealLocked(@Nullable Throwable proposedException) {
            ArrayList arrayListAllocateList;
            Object eh = getExceptionsHolder();
            if (eh == null) {
                arrayListAllocateList = allocateList();
            } else if (eh instanceof Throwable) {
                ArrayList it = allocateList();
                it.add(eh);
                arrayListAllocateList = it;
            } else {
                if (!(eh instanceof ArrayList)) {
                    throw new IllegalStateException(Intrinsics.stringPlus("State is ", eh).toString());
                }
                arrayListAllocateList = (ArrayList) eh;
            }
            ArrayList list = arrayListAllocateList;
            Throwable rootCause = getRootCause();
            if (rootCause != null) {
                list.add(0, rootCause);
            }
            if (proposedException != null && !Intrinsics.areEqual(proposedException, rootCause)) {
                list.add(proposedException);
            }
            setExceptionsHolder(JobSupportKt.SEALED);
            return list;
        }

        public final void addExceptionLocked(@NotNull Throwable exception) {
            Throwable rootCause = getRootCause();
            if (rootCause == null) {
                setRootCause(exception);
                return;
            }
            if (exception == rootCause) {
                return;
            }
            Object eh = getExceptionsHolder();
            if (eh != null) {
                if (eh instanceof Throwable) {
                    if (exception == eh) {
                        return;
                    }
                    ArrayList $this$addExceptionLocked_u24lambda_u2d2 = allocateList();
                    $this$addExceptionLocked_u24lambda_u2d2.add(eh);
                    $this$addExceptionLocked_u24lambda_u2d2.add(exception);
                    Unit unit = Unit.INSTANCE;
                    setExceptionsHolder($this$addExceptionLocked_u24lambda_u2d2);
                    return;
                }
                if (!(eh instanceof ArrayList)) {
                    throw new IllegalStateException(Intrinsics.stringPlus("State is ", eh).toString());
                }
                ((ArrayList) eh).add(exception);
                return;
            }
            setExceptionsHolder(exception);
        }

        private final ArrayList<Throwable> allocateList() {
            return new ArrayList<>(4);
        }

        @NotNull
        public String toString() {
            return "Finishing[cancelling=" + isCancelling() + ", completing=" + isCompleting() + ", rootCause=" + getRootCause() + ", exceptions=" + getExceptionsHolder() + ", list=" + getList() + ']';
        }
    }

    private final boolean isCancelling(Incomplete $this$isCancelling) {
        return ($this$isCancelling instanceof Finishing) && ((Finishing) $this$isCancelling).isCancelling();
    }

    /* compiled from: JobSupport.kt */
    @Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��0\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0010\u0002\n��\n\u0002\u0010\u0003\n��\b\u0002\u0018��2\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nJ\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0096\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n��R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n��¨\u0006\u000f"}, d2 = {"Lkotlinx/coroutines/JobSupport$ChildCompletion;", "Lkotlinx/coroutines/JobNode;", "parent", "Lkotlinx/coroutines/JobSupport;", "state", "Lkotlinx/coroutines/JobSupport$Finishing;", "child", "Lkotlinx/coroutines/ChildHandleNode;", "proposedUpdate", "", "(Lkotlinx/coroutines/JobSupport;Lkotlinx/coroutines/JobSupport$Finishing;Lkotlinx/coroutines/ChildHandleNode;Ljava/lang/Object;)V", "invoke", "", "cause", "", "kotlinx-coroutines-core"})
    /* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/JobSupport$ChildCompletion.class */
    private static final class ChildCompletion extends JobNode {

        @NotNull
        private final JobSupport parent;

        @NotNull
        private final Finishing state;

        @NotNull
        private final ChildHandleNode child;

        @Nullable
        private final Object proposedUpdate;

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Throwable th) throws Throwable {
            invoke(th);
            return Unit.INSTANCE;
        }

        public ChildCompletion(@NotNull JobSupport parent, @NotNull Finishing state, @NotNull ChildHandleNode child, @Nullable Object proposedUpdate) {
            this.parent = parent;
            this.state = state;
            this.child = child;
            this.proposedUpdate = proposedUpdate;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlinx.coroutines.CompletionHandlerBase
        public void invoke(@Nullable Throwable cause) throws Throwable {
            this.parent.continueCompleting(this.state, this.child, this.proposedUpdate);
        }
    }

    /* compiled from: JobSupport.kt */
    @Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��,\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\b\u0002\u0018��*\u0004\b��\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001b\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028��0\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\rH\u0014R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n��¨\u0006\u000e"}, d2 = {"Lkotlinx/coroutines/JobSupport$AwaitContinuation;", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "Lkotlinx/coroutines/CancellableContinuationImpl;", "delegate", "Lkotlin/coroutines/Continuation;", "job", "Lkotlinx/coroutines/JobSupport;", "(Lkotlin/coroutines/Continuation;Lkotlinx/coroutines/JobSupport;)V", "getContinuationCancellationCause", "", "parent", "Lkotlinx/coroutines/Job;", "nameString", "", "kotlinx-coroutines-core"})
    /* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/JobSupport$AwaitContinuation.class */
    private static final class AwaitContinuation<T> extends CancellableContinuationImpl<T> {

        @NotNull
        private final JobSupport job;

        public AwaitContinuation(@NotNull Continuation<? super T> continuation, @NotNull JobSupport job) {
            super(continuation, 1);
            this.job = job;
        }

        @Override // kotlinx.coroutines.CancellableContinuationImpl
        @NotNull
        public Throwable getContinuationCancellationCause(@NotNull Job parent) {
            Throwable it;
            Object state = this.job.getState$kotlinx_coroutines_core();
            if (!(state instanceof Finishing) || (it = ((Finishing) state).getRootCause()) == null) {
                return state instanceof CompletedExceptionally ? ((CompletedExceptionally) state).cause : parent.getCancellationException();
            }
            return it;
        }

        @Override // kotlinx.coroutines.CancellableContinuationImpl
        @NotNull
        protected String nameString() {
            return "AwaitContinuation";
        }
    }

    public final boolean isCompletedExceptionally() {
        return getState$kotlinx_coroutines_core() instanceof CompletedExceptionally;
    }

    @Nullable
    public final Throwable getCompletionExceptionOrNull() {
        Object state = getState$kotlinx_coroutines_core();
        if (!(!(state instanceof Incomplete))) {
            throw new IllegalStateException("This job has not completed yet".toString());
        }
        return getExceptionOrNull(state);
    }

    @Nullable
    public final Object getCompletedInternal$kotlinx_coroutines_core() throws Throwable {
        Object state = getState$kotlinx_coroutines_core();
        if (!(!(state instanceof Incomplete))) {
            throw new IllegalStateException("This job has not completed yet".toString());
        }
        if (state instanceof CompletedExceptionally) {
            throw ((CompletedExceptionally) state).cause;
        }
        return JobSupportKt.unboxState(state);
    }

    @Nullable
    public final Object awaitInternal$kotlinx_coroutines_core(@NotNull Continuation<Object> continuation) throws Throwable {
        Object state;
        do {
            state = getState$kotlinx_coroutines_core();
            if (!(state instanceof Incomplete)) {
                if (state instanceof CompletedExceptionally) {
                    Throwable exception$iv = ((CompletedExceptionally) state).cause;
                    if (!DebugKt.getRECOVER_STACK_TRACES()) {
                        throw exception$iv;
                    }
                    if (continuation instanceof CoroutineStackFrame) {
                        throw StackTraceRecoveryKt.recoverFromStackFrame(exception$iv, (CoroutineStackFrame) continuation);
                    }
                    throw exception$iv;
                }
                return JobSupportKt.unboxState(state);
            }
        } while (startInternal(state) < 0);
        return awaitSuspend(continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object awaitSuspend(Continuation<Object> continuation) {
        AwaitContinuation cont = new AwaitContinuation(IntrinsicsKt.intercepted(continuation), this);
        cont.initCancellability();
        CompletionHandlerBase $this$asHandler$iv = new ResumeAwaitOnCompletion(cont);
        CancellableContinuationKt.disposeOnCancellation(cont, invokeOnCompletion($this$asHandler$iv));
        Object result = cont.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    public final <T, R> void registerSelectClause1Internal$kotlinx_coroutines_core(@NotNull SelectInstance<? super R> selectInstance, @NotNull Function2<? super T, ? super Continuation<? super R>, ? extends Object> function2) {
        Object state;
        do {
            state = getState$kotlinx_coroutines_core();
            if (selectInstance.isSelected()) {
                return;
            }
            if (!(state instanceof Incomplete)) {
                if (selectInstance.trySelect()) {
                    if (state instanceof CompletedExceptionally) {
                        selectInstance.resumeSelectWithException(((CompletedExceptionally) state).cause);
                        return;
                    } else {
                        UndispatchedKt.startCoroutineUnintercepted(function2, JobSupportKt.unboxState(state), selectInstance.getCompletion());
                        return;
                    }
                }
                return;
            }
        } while (startInternal(state) != 0);
        CompletionHandlerBase $this$asHandler$iv = new SelectAwaitOnCompletion(selectInstance, function2);
        selectInstance.disposeOnSelect(invokeOnCompletion($this$asHandler$iv));
    }

    public final <T, R> void selectAwaitCompletion$kotlinx_coroutines_core(@NotNull SelectInstance<? super R> selectInstance, @NotNull Function2<? super T, ? super Continuation<? super R>, ? extends Object> function2) throws Throwable {
        Object state = getState$kotlinx_coroutines_core();
        if (state instanceof CompletedExceptionally) {
            selectInstance.resumeSelectWithException(((CompletedExceptionally) state).cause);
        } else {
            CancellableKt.startCoroutineCancellable$default(function2, JobSupportKt.unboxState(state), selectInstance.getCompletion(), null, 4, null);
        }
    }
}
