package mu.internal;

import io.vertx.core.cli.UsageMessageFormatter;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import me.ag2s.epublib.epub.PackageDocumentBase;
import mu.KLogger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;
import org.slf4j.spi.LocationAwareLogger;

/* compiled from: LocationAwareKLogger.kt */
@Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"��N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u0003\n��\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\b\b��\u0018��2\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0018\u0010\n\u001a\u00020\u000b2\u000e\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\rH\u0016J\u0012\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0007H\u0016J\u001c\u0010\n\u001a\u00020\u000b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00072\b\u0010\u0010\u001a\u0004\u0018\u00010\u000eH\u0016J&\u0010\n\u001a\u00020\u000b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00072\b\u0010\u0011\u001a\u0004\u0018\u00010\u000e2\b\u0010\u0012\u001a\u0004\u0018\u00010\u000eH\u0016J'\u0010\n\u001a\u00020\u000b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00072\u000e\u0010\u0013\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u0014H\u0016¢\u0006\u0002\u0010\u0015J\u001c\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u00072\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\"\u0010\n\u001a\u00020\u000b2\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u000e\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\rH\u0016J\"\u0010\n\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u000e\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\rH\u0016J\u001c\u0010\n\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\f\u001a\u0004\u0018\u00010\u0007H\u0016J&\u0010\n\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u000f\u001a\u0004\u0018\u00010\u00072\b\u0010\u0010\u001a\u0004\u0018\u00010\u000eH\u0016J0\u0010\n\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u000f\u001a\u0004\u0018\u00010\u00072\b\u0010\u0011\u001a\u0004\u0018\u00010\u000e2\b\u0010\u0012\u001a\u0004\u0018\u00010\u000eH\u0016J1\u0010\n\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u000f\u001a\u0004\u0018\u00010\u00072\u000e\u0010\u0013\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u0014H\u0016¢\u0006\u0002\u0010\u001aJ&\u0010\n\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\f\u001a\u0004\u0018\u00010\u00072\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J,\u0010\n\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u000e\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\rH\u0016J\u0018\u0010\u001b\u001a\u00020\u000b2\u000e\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\rH\u0016J\u0012\u0010\u001b\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0007H\u0016J\u001c\u0010\u001b\u001a\u00020\u000b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00072\b\u0010\u0010\u001a\u0004\u0018\u00010\u000eH\u0016J&\u0010\u001b\u001a\u00020\u000b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00072\b\u0010\u0011\u001a\u0004\u0018\u00010\u000e2\b\u0010\u0012\u001a\u0004\u0018\u00010\u000eH\u0016J'\u0010\u001b\u001a\u00020\u000b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00072\u000e\u0010\u0013\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u0014H\u0016¢\u0006\u0002\u0010\u0015J\u001c\u0010\u001b\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u00072\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\"\u0010\u001b\u001a\u00020\u000b2\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u000e\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\rH\u0016J\"\u0010\u001b\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u000e\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\rH\u0016J\u001c\u0010\u001b\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\f\u001a\u0004\u0018\u00010\u0007H\u0016J&\u0010\u001b\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u000f\u001a\u0004\u0018\u00010\u00072\b\u0010\u0010\u001a\u0004\u0018\u00010\u000eH\u0016J0\u0010\u001b\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u000f\u001a\u0004\u0018\u00010\u00072\b\u0010\u0011\u001a\u0004\u0018\u00010\u000e2\b\u0010\u0012\u001a\u0004\u0018\u00010\u000eH\u0016J1\u0010\u001b\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u000f\u001a\u0004\u0018\u00010\u00072\u000e\u0010\u0013\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u0014H\u0016¢\u0006\u0002\u0010\u001aJ&\u0010\u001b\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\f\u001a\u0004\u0018\u00010\u00072\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J,\u0010\u001b\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u000e\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\rH\u0016J\u0011\u0010\u001c\u001a\n \u001d*\u0004\u0018\u00010\u00070\u0007H\u0096\u0001J\u0018\u0010\u001e\u001a\u00020\u000b2\u000e\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\rH\u0016J\u0012\u0010\u001e\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0007H\u0016J\u001c\u0010\u001e\u001a\u00020\u000b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00072\b\u0010\u0010\u001a\u0004\u0018\u00010\u000eH\u0016J&\u0010\u001e\u001a\u00020\u000b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00072\b\u0010\u0011\u001a\u0004\u0018\u00010\u000e2\b\u0010\u0012\u001a\u0004\u0018\u00010\u000eH\u0016J'\u0010\u001e\u001a\u00020\u000b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00072\u000e\u0010\u0013\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u0014H\u0016¢\u0006\u0002\u0010\u0015J\u001c\u0010\u001e\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u00072\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\"\u0010\u001e\u001a\u00020\u000b2\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u000e\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\rH\u0016J\"\u0010\u001e\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u000e\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\rH\u0016J\u001c\u0010\u001e\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\f\u001a\u0004\u0018\u00010\u0007H\u0016J&\u0010\u001e\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u000f\u001a\u0004\u0018\u00010\u00072\b\u0010\u0010\u001a\u0004\u0018\u00010\u000eH\u0016J0\u0010\u001e\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u000f\u001a\u0004\u0018\u00010\u00072\b\u0010\u0011\u001a\u0004\u0018\u00010\u000e2\b\u0010\u0012\u001a\u0004\u0018\u00010\u000eH\u0016J1\u0010\u001e\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u000f\u001a\u0004\u0018\u00010\u00072\u000e\u0010\u0013\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u0014H\u0016¢\u0006\u0002\u0010\u001aJ&\u0010\u001e\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\f\u001a\u0004\u0018\u00010\u00072\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J,\u0010\u001e\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u000e\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\rH\u0016J\t\u0010\u001f\u001a\u00020 H\u0096\u0001J\u0019\u0010\u001f\u001a\u00020 2\u000e\u0010!\u001a\n \u001d*\u0004\u0018\u00010\u00190\u0019H\u0096\u0001J\t\u0010\"\u001a\u00020 H\u0096\u0001J\u0019\u0010\"\u001a\u00020 2\u000e\u0010!\u001a\n \u001d*\u0004\u0018\u00010\u00190\u0019H\u0096\u0001J\t\u0010#\u001a\u00020 H\u0096\u0001J\u0019\u0010#\u001a\u00020 2\u000e\u0010!\u001a\n \u001d*\u0004\u0018\u00010\u00190\u0019H\u0096\u0001J\t\u0010$\u001a\u00020 H\u0096\u0001J\u0019\u0010$\u001a\u00020 2\u000e\u0010!\u001a\n \u001d*\u0004\u0018\u00010\u00190\u0019H\u0096\u0001J\t\u0010%\u001a\u00020 H\u0096\u0001J\u0019\u0010%\u001a\u00020 2\u000e\u0010!\u001a\n \u001d*\u0004\u0018\u00010\u00190\u0019H\u0096\u0001J\u0018\u0010&\u001a\u00020\u000b2\u000e\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\rH\u0016J\u0012\u0010&\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0007H\u0016J\u001c\u0010&\u001a\u00020\u000b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00072\b\u0010\u0010\u001a\u0004\u0018\u00010\u000eH\u0016J&\u0010&\u001a\u00020\u000b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00072\b\u0010\u0011\u001a\u0004\u0018\u00010\u000e2\b\u0010\u0012\u001a\u0004\u0018\u00010\u000eH\u0016J'\u0010&\u001a\u00020\u000b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00072\u000e\u0010\u0013\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u0014H\u0016¢\u0006\u0002\u0010\u0015J\u001c\u0010&\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u00072\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\"\u0010&\u001a\u00020\u000b2\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u000e\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\rH\u0016J\"\u0010&\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u000e\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\rH\u0016J\u001c\u0010&\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\f\u001a\u0004\u0018\u00010\u0007H\u0016J&\u0010&\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u000f\u001a\u0004\u0018\u00010\u00072\b\u0010\u0010\u001a\u0004\u0018\u00010\u000eH\u0016J0\u0010&\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u000f\u001a\u0004\u0018\u00010\u00072\b\u0010\u0011\u001a\u0004\u0018\u00010\u000e2\b\u0010\u0012\u001a\u0004\u0018\u00010\u000eH\u0016J1\u0010&\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u000f\u001a\u0004\u0018\u00010\u00072\u000e\u0010\u0013\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u0014H\u0016¢\u0006\u0002\u0010\u001aJ&\u0010&\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\f\u001a\u0004\u0018\u00010\u00072\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J,\u0010&\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u000e\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\rH\u0016J\u0018\u0010'\u001a\u00020\u000b2\u000e\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\rH\u0016J\u0012\u0010'\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0007H\u0016J\u001c\u0010'\u001a\u00020\u000b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00072\b\u0010\u0010\u001a\u0004\u0018\u00010\u000eH\u0016J&\u0010'\u001a\u00020\u000b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00072\b\u0010\u0011\u001a\u0004\u0018\u00010\u000e2\b\u0010\u0012\u001a\u0004\u0018\u00010\u000eH\u0016J'\u0010'\u001a\u00020\u000b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00072\u000e\u0010\u0013\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u0014H\u0016¢\u0006\u0002\u0010\u0015J\u001c\u0010'\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u00072\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\"\u0010'\u001a\u00020\u000b2\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u000e\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\rH\u0016J\"\u0010'\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u000e\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\rH\u0016J\u001c\u0010'\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\f\u001a\u0004\u0018\u00010\u0007H\u0016J&\u0010'\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u000f\u001a\u0004\u0018\u00010\u00072\b\u0010\u0010\u001a\u0004\u0018\u00010\u000eH\u0016J0\u0010'\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u000f\u001a\u0004\u0018\u00010\u00072\b\u0010\u0011\u001a\u0004\u0018\u00010\u000e2\b\u0010\u0012\u001a\u0004\u0018\u00010\u000eH\u0016J1\u0010'\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u000f\u001a\u0004\u0018\u00010\u00072\u000e\u0010\u0013\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u0014H\u0016¢\u0006\u0002\u0010\u001aJ&\u0010'\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\f\u001a\u0004\u0018\u00010\u00072\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J,\u0010'\u001a\u00020\u000b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u000e\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\rH\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n��R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\b\u0010\t¨\u0006("}, d2 = {"Lmu/internal/LocationAwareKLogger;", "Lmu/KLogger;", "Lorg/slf4j/Logger;", "underlyingLogger", "Lorg/slf4j/spi/LocationAwareLogger;", "(Lorg/slf4j/spi/LocationAwareLogger;)V", "fqcn", "", "getUnderlyingLogger", "()Lorg/slf4j/spi/LocationAwareLogger;", "debug", "", "msg", "Lkotlin/Function0;", "", PackageDocumentBase.DCTags.format, UsageMessageFormatter.DEFAULT_ARG_NAME, "arg1", "arg2", "argArray", "", "(Ljava/lang/String;[Ljava/lang/Object;)V", "t", "", "marker", "Lorg/slf4j/Marker;", "(Lorg/slf4j/Marker;Ljava/lang/String;[Ljava/lang/Object;)V", "error", "getName", "kotlin.jvm.PlatformType", "info", "isDebugEnabled", "", "p0", "isErrorEnabled", "isInfoEnabled", "isTraceEnabled", "isWarnEnabled", "trace", "warn", "kotlin-logging"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-logging-1.6.24.jar:mu/internal/LocationAwareKLogger.class */
public final class LocationAwareKLogger implements KLogger, Logger {
    private final String fqcn;

    @NotNull
    private final LocationAwareLogger underlyingLogger;

    @Override // org.slf4j.Logger
    public String getName() {
        return this.underlyingLogger.getName();
    }

    @Override // org.slf4j.Logger
    public boolean isDebugEnabled() {
        return this.underlyingLogger.isDebugEnabled();
    }

    @Override // org.slf4j.Logger
    public boolean isDebugEnabled(Marker p0) {
        return this.underlyingLogger.isDebugEnabled(p0);
    }

    @Override // org.slf4j.Logger
    public boolean isErrorEnabled() {
        return this.underlyingLogger.isErrorEnabled();
    }

    @Override // org.slf4j.Logger
    public boolean isErrorEnabled(Marker p0) {
        return this.underlyingLogger.isErrorEnabled(p0);
    }

    @Override // org.slf4j.Logger
    public boolean isInfoEnabled() {
        return this.underlyingLogger.isInfoEnabled();
    }

    @Override // org.slf4j.Logger
    public boolean isInfoEnabled(Marker p0) {
        return this.underlyingLogger.isInfoEnabled(p0);
    }

    @Override // org.slf4j.Logger
    public boolean isTraceEnabled() {
        return this.underlyingLogger.isTraceEnabled();
    }

    @Override // org.slf4j.Logger
    public boolean isTraceEnabled(Marker p0) {
        return this.underlyingLogger.isTraceEnabled(p0);
    }

    @Override // org.slf4j.Logger
    public boolean isWarnEnabled() {
        return this.underlyingLogger.isWarnEnabled();
    }

    @Override // org.slf4j.Logger
    public boolean isWarnEnabled(Marker p0) {
        return this.underlyingLogger.isWarnEnabled(p0);
    }

    @Override // mu.KLogger
    @NotNull
    public LocationAwareLogger getUnderlyingLogger() {
        return this.underlyingLogger;
    }

    public LocationAwareKLogger(@NotNull LocationAwareLogger underlyingLogger) {
        Intrinsics.checkParameterIsNotNull(underlyingLogger, "underlyingLogger");
        this.underlyingLogger = underlyingLogger;
        String name = LocationAwareKLogger.class.getName();
        Intrinsics.checkExpressionValueIsNotNull(name, "LocationAwareKLogger::class.java.name");
        this.fqcn = name;
    }

    @Override // org.slf4j.Logger
    public void trace(@Nullable String msg) {
        if (!getUnderlyingLogger().isTraceEnabled()) {
            return;
        }
        getUnderlyingLogger().log(null, this.fqcn, 0, msg, null, null);
    }

    @Override // org.slf4j.Logger
    public void trace(@Nullable String format, @Nullable Object arg) {
        if (!getUnderlyingLogger().isTraceEnabled()) {
            return;
        }
        String formattedMessage = MessageFormatter.format(format, arg).getMessage();
        getUnderlyingLogger().log(null, this.fqcn, 0, formattedMessage, new Object[]{arg}, null);
    }

    @Override // org.slf4j.Logger
    public void trace(@Nullable String format, @Nullable Object arg1, @Nullable Object arg2) {
        if (!getUnderlyingLogger().isTraceEnabled()) {
            return;
        }
        String formattedMessage = MessageFormatter.format(format, arg1, arg2).getMessage();
        getUnderlyingLogger().log(null, this.fqcn, 0, formattedMessage, new Object[]{arg1, arg2}, null);
    }

    @Override // org.slf4j.Logger
    public void trace(@Nullable String format, @NotNull Object[] argArray) {
        Intrinsics.checkParameterIsNotNull(argArray, "argArray");
        if (!getUnderlyingLogger().isTraceEnabled()) {
            return;
        }
        String formattedMessage = MessageFormatter.arrayFormat(format, argArray).getMessage();
        getUnderlyingLogger().log(null, this.fqcn, 0, formattedMessage, argArray, null);
    }

    @Override // org.slf4j.Logger
    public void trace(@Nullable String msg, @Nullable Throwable t) {
        if (!getUnderlyingLogger().isTraceEnabled()) {
            return;
        }
        getUnderlyingLogger().log(null, this.fqcn, 0, msg, null, t);
    }

    @Override // org.slf4j.Logger
    public void trace(@Nullable Marker marker, @Nullable String msg) {
        if (!getUnderlyingLogger().isTraceEnabled()) {
            return;
        }
        getUnderlyingLogger().log(marker, this.fqcn, 0, msg, null, null);
    }

    @Override // org.slf4j.Logger
    public void trace(@Nullable Marker marker, @Nullable String format, @Nullable Object arg) {
        if (!getUnderlyingLogger().isTraceEnabled()) {
            return;
        }
        String formattedMessage = MessageFormatter.format(format, arg).getMessage();
        getUnderlyingLogger().log(marker, this.fqcn, 0, formattedMessage, new Object[]{arg}, null);
    }

    @Override // org.slf4j.Logger
    public void trace(@Nullable Marker marker, @Nullable String format, @Nullable Object arg1, @Nullable Object arg2) {
        if (!getUnderlyingLogger().isTraceEnabled()) {
            return;
        }
        String formattedMessage = MessageFormatter.format(format, arg1, arg2).getMessage();
        getUnderlyingLogger().log(marker, this.fqcn, 0, formattedMessage, new Object[]{arg1, arg2}, null);
    }

    @Override // org.slf4j.Logger
    public void trace(@Nullable Marker marker, @Nullable String format, @NotNull Object[] argArray) {
        Intrinsics.checkParameterIsNotNull(argArray, "argArray");
        if (!getUnderlyingLogger().isTraceEnabled()) {
            return;
        }
        String formattedMessage = MessageFormatter.arrayFormat(format, argArray).getMessage();
        getUnderlyingLogger().log(marker, this.fqcn, 0, formattedMessage, argArray, null);
    }

    @Override // org.slf4j.Logger
    public void trace(@Nullable Marker marker, @Nullable String msg, @Nullable Throwable t) {
        if (!getUnderlyingLogger().isTraceEnabled()) {
            return;
        }
        getUnderlyingLogger().log(marker, this.fqcn, 0, msg, null, t);
    }

    @Override // org.slf4j.Logger
    public void debug(@Nullable String msg) {
        if (!getUnderlyingLogger().isDebugEnabled()) {
            return;
        }
        getUnderlyingLogger().log(null, this.fqcn, 10, msg, null, null);
    }

    @Override // org.slf4j.Logger
    public void debug(@Nullable String format, @Nullable Object arg) {
        if (!getUnderlyingLogger().isDebugEnabled()) {
            return;
        }
        String formattedMessage = MessageFormatter.format(format, arg).getMessage();
        getUnderlyingLogger().log(null, this.fqcn, 10, formattedMessage, new Object[]{arg}, null);
    }

    @Override // org.slf4j.Logger
    public void debug(@Nullable String format, @Nullable Object arg1, @Nullable Object arg2) {
        if (!getUnderlyingLogger().isDebugEnabled()) {
            return;
        }
        String formattedMessage = MessageFormatter.format(format, arg1, arg2).getMessage();
        getUnderlyingLogger().log(null, this.fqcn, 10, formattedMessage, new Object[]{arg1, arg2}, null);
    }

    @Override // org.slf4j.Logger
    public void debug(@Nullable String format, @NotNull Object[] argArray) {
        Intrinsics.checkParameterIsNotNull(argArray, "argArray");
        if (!getUnderlyingLogger().isDebugEnabled()) {
            return;
        }
        FormattingTuple ft = MessageFormatter.arrayFormat(format, argArray);
        getUnderlyingLogger().log(null, this.fqcn, 10, ft.getMessage(), ft.getArgArray(), ft.getThrowable());
    }

    @Override // org.slf4j.Logger
    public void debug(@Nullable String msg, @Nullable Throwable t) {
        if (!getUnderlyingLogger().isDebugEnabled()) {
            return;
        }
        getUnderlyingLogger().log(null, this.fqcn, 10, msg, null, t);
    }

    @Override // org.slf4j.Logger
    public void debug(@Nullable Marker marker, @Nullable String msg) {
        if (!getUnderlyingLogger().isDebugEnabled()) {
            return;
        }
        getUnderlyingLogger().log(marker, this.fqcn, 10, msg, null, null);
    }

    @Override // org.slf4j.Logger
    public void debug(@Nullable Marker marker, @Nullable String format, @Nullable Object arg) {
        if (!getUnderlyingLogger().isDebugEnabled()) {
            return;
        }
        FormattingTuple ft = MessageFormatter.format(format, arg);
        getUnderlyingLogger().log(marker, this.fqcn, 10, ft.getMessage(), ft.getArgArray(), ft.getThrowable());
    }

    @Override // org.slf4j.Logger
    public void debug(@Nullable Marker marker, @Nullable String format, @Nullable Object arg1, @Nullable Object arg2) {
        if (!getUnderlyingLogger().isDebugEnabled()) {
            return;
        }
        String formattedMessage = MessageFormatter.format(format, arg1, arg2).getMessage();
        getUnderlyingLogger().log(marker, this.fqcn, 10, formattedMessage, new Object[]{arg1, arg2}, null);
    }

    @Override // org.slf4j.Logger
    public void debug(@Nullable Marker marker, @Nullable String format, @NotNull Object[] argArray) {
        Intrinsics.checkParameterIsNotNull(argArray, "argArray");
        if (!getUnderlyingLogger().isDebugEnabled()) {
            return;
        }
        FormattingTuple ft = MessageFormatter.arrayFormat(format, argArray);
        getUnderlyingLogger().log(marker, this.fqcn, 10, ft.getMessage(), argArray, ft.getThrowable());
    }

    @Override // org.slf4j.Logger
    public void debug(@Nullable Marker marker, @Nullable String msg, @Nullable Throwable t) {
        if (!getUnderlyingLogger().isDebugEnabled()) {
            return;
        }
        getUnderlyingLogger().log(marker, this.fqcn, 10, msg, null, t);
    }

    @Override // org.slf4j.Logger
    public void info(@Nullable String msg) {
        if (!getUnderlyingLogger().isInfoEnabled()) {
            return;
        }
        getUnderlyingLogger().log(null, this.fqcn, 20, msg, null, null);
    }

    @Override // org.slf4j.Logger
    public void info(@Nullable String format, @Nullable Object arg) {
        if (!getUnderlyingLogger().isInfoEnabled()) {
            return;
        }
        String formattedMessage = MessageFormatter.format(format, arg).getMessage();
        getUnderlyingLogger().log(null, this.fqcn, 20, formattedMessage, new Object[]{arg}, null);
    }

    @Override // org.slf4j.Logger
    public void info(@Nullable String format, @Nullable Object arg1, @Nullable Object arg2) {
        if (!getUnderlyingLogger().isInfoEnabled()) {
            return;
        }
        String formattedMessage = MessageFormatter.format(format, arg1, arg2).getMessage();
        getUnderlyingLogger().log(null, this.fqcn, 20, formattedMessage, new Object[]{arg1, arg2}, null);
    }

    @Override // org.slf4j.Logger
    public void info(@Nullable String format, @NotNull Object[] argArray) {
        Intrinsics.checkParameterIsNotNull(argArray, "argArray");
        if (!getUnderlyingLogger().isInfoEnabled()) {
            return;
        }
        String formattedMessage = MessageFormatter.arrayFormat(format, argArray).getMessage();
        getUnderlyingLogger().log(null, this.fqcn, 20, formattedMessage, argArray, null);
    }

    @Override // org.slf4j.Logger
    public void info(@Nullable String msg, @Nullable Throwable t) {
        if (!getUnderlyingLogger().isInfoEnabled()) {
            return;
        }
        getUnderlyingLogger().log(null, this.fqcn, 20, msg, null, t);
    }

    @Override // org.slf4j.Logger
    public void info(@Nullable Marker marker, @Nullable String msg) {
        if (!getUnderlyingLogger().isInfoEnabled()) {
            return;
        }
        getUnderlyingLogger().log(marker, this.fqcn, 20, msg, null, null);
    }

    @Override // org.slf4j.Logger
    public void info(@Nullable Marker marker, @Nullable String format, @Nullable Object arg) {
        if (!getUnderlyingLogger().isInfoEnabled()) {
            return;
        }
        String formattedMessage = MessageFormatter.format(format, arg).getMessage();
        getUnderlyingLogger().log(marker, this.fqcn, 20, formattedMessage, new Object[]{arg}, null);
    }

    @Override // org.slf4j.Logger
    public void info(@Nullable Marker marker, @Nullable String format, @Nullable Object arg1, @Nullable Object arg2) {
        if (!getUnderlyingLogger().isInfoEnabled()) {
            return;
        }
        String formattedMessage = MessageFormatter.format(format, arg1, arg2).getMessage();
        getUnderlyingLogger().log(marker, this.fqcn, 20, formattedMessage, new Object[]{arg1, arg2}, null);
    }

    @Override // org.slf4j.Logger
    public void info(@Nullable Marker marker, @Nullable String format, @NotNull Object[] argArray) {
        Intrinsics.checkParameterIsNotNull(argArray, "argArray");
        if (!getUnderlyingLogger().isInfoEnabled()) {
            return;
        }
        String formattedMessage = MessageFormatter.arrayFormat(format, argArray).getMessage();
        getUnderlyingLogger().log(marker, this.fqcn, 20, formattedMessage, argArray, null);
    }

    @Override // org.slf4j.Logger
    public void info(@Nullable Marker marker, @Nullable String msg, @Nullable Throwable t) {
        if (!getUnderlyingLogger().isInfoEnabled()) {
            return;
        }
        getUnderlyingLogger().log(marker, this.fqcn, 20, msg, null, t);
    }

    @Override // org.slf4j.Logger
    public void warn(@Nullable String msg) {
        if (!getUnderlyingLogger().isWarnEnabled()) {
            return;
        }
        getUnderlyingLogger().log(null, this.fqcn, 30, msg, null, null);
    }

    @Override // org.slf4j.Logger
    public void warn(@Nullable String format, @Nullable Object arg) {
        if (!getUnderlyingLogger().isWarnEnabled()) {
            return;
        }
        String formattedMessage = MessageFormatter.format(format, arg).getMessage();
        getUnderlyingLogger().log(null, this.fqcn, 30, formattedMessage, new Object[]{arg}, null);
    }

    @Override // org.slf4j.Logger
    public void warn(@Nullable String format, @Nullable Object arg1, @Nullable Object arg2) {
        if (!getUnderlyingLogger().isWarnEnabled()) {
            return;
        }
        String formattedMessage = MessageFormatter.format(format, arg1, arg2).getMessage();
        getUnderlyingLogger().log(null, this.fqcn, 30, formattedMessage, new Object[]{arg1, arg2}, null);
    }

    @Override // org.slf4j.Logger
    public void warn(@Nullable String format, @NotNull Object[] argArray) {
        Intrinsics.checkParameterIsNotNull(argArray, "argArray");
        if (!getUnderlyingLogger().isWarnEnabled()) {
            return;
        }
        String formattedMessage = MessageFormatter.arrayFormat(format, argArray).getMessage();
        getUnderlyingLogger().log(null, this.fqcn, 30, formattedMessage, argArray, null);
    }

    @Override // org.slf4j.Logger
    public void warn(@Nullable String msg, @Nullable Throwable t) {
        if (!getUnderlyingLogger().isWarnEnabled()) {
            return;
        }
        getUnderlyingLogger().log(null, this.fqcn, 30, msg, null, t);
    }

    @Override // org.slf4j.Logger
    public void warn(@Nullable Marker marker, @Nullable String msg) {
        if (!getUnderlyingLogger().isWarnEnabled()) {
            return;
        }
        getUnderlyingLogger().log(marker, this.fqcn, 30, msg, null, null);
    }

    @Override // org.slf4j.Logger
    public void warn(@Nullable Marker marker, @Nullable String format, @Nullable Object arg) {
        if (!getUnderlyingLogger().isWarnEnabled()) {
            return;
        }
        String formattedMessage = MessageFormatter.format(format, arg).getMessage();
        getUnderlyingLogger().log(marker, this.fqcn, 30, formattedMessage, new Object[]{arg}, null);
    }

    @Override // org.slf4j.Logger
    public void warn(@Nullable Marker marker, @Nullable String format, @Nullable Object arg1, @Nullable Object arg2) {
        if (!getUnderlyingLogger().isWarnEnabled()) {
            return;
        }
        String formattedMessage = MessageFormatter.format(format, arg1, arg2).getMessage();
        getUnderlyingLogger().log(marker, this.fqcn, 30, formattedMessage, new Object[]{arg1, arg2}, null);
    }

    @Override // org.slf4j.Logger
    public void warn(@Nullable Marker marker, @Nullable String format, @NotNull Object[] argArray) {
        Intrinsics.checkParameterIsNotNull(argArray, "argArray");
        if (!getUnderlyingLogger().isWarnEnabled()) {
            return;
        }
        String formattedMessage = MessageFormatter.arrayFormat(format, argArray).getMessage();
        getUnderlyingLogger().log(marker, this.fqcn, 30, formattedMessage, argArray, null);
    }

    @Override // org.slf4j.Logger
    public void warn(@Nullable Marker marker, @Nullable String msg, @Nullable Throwable t) {
        if (!getUnderlyingLogger().isWarnEnabled()) {
            return;
        }
        getUnderlyingLogger().log(marker, this.fqcn, 30, msg, null, t);
    }

    @Override // org.slf4j.Logger
    public void error(@Nullable String msg) {
        if (!getUnderlyingLogger().isErrorEnabled()) {
            return;
        }
        getUnderlyingLogger().log(null, this.fqcn, 40, msg, null, null);
    }

    @Override // org.slf4j.Logger
    public void error(@Nullable String format, @Nullable Object arg) {
        if (!getUnderlyingLogger().isErrorEnabled()) {
            return;
        }
        String formattedMessage = MessageFormatter.format(format, arg).getMessage();
        getUnderlyingLogger().log(null, this.fqcn, 40, formattedMessage, new Object[]{arg}, null);
    }

    @Override // org.slf4j.Logger
    public void error(@Nullable String format, @Nullable Object arg1, @Nullable Object arg2) {
        if (!getUnderlyingLogger().isErrorEnabled()) {
            return;
        }
        String formattedMessage = MessageFormatter.format(format, arg1, arg2).getMessage();
        getUnderlyingLogger().log(null, this.fqcn, 40, formattedMessage, new Object[]{arg1, arg2}, null);
    }

    @Override // org.slf4j.Logger
    public void error(@Nullable String format, @NotNull Object[] argArray) {
        Intrinsics.checkParameterIsNotNull(argArray, "argArray");
        if (!getUnderlyingLogger().isErrorEnabled()) {
            return;
        }
        String formattedMessage = MessageFormatter.arrayFormat(format, argArray).getMessage();
        getUnderlyingLogger().log(null, this.fqcn, 40, formattedMessage, argArray, null);
    }

    @Override // org.slf4j.Logger
    public void error(@Nullable String msg, @Nullable Throwable t) {
        if (!getUnderlyingLogger().isErrorEnabled()) {
            return;
        }
        getUnderlyingLogger().log(null, this.fqcn, 40, msg, null, t);
    }

    @Override // org.slf4j.Logger
    public void error(@Nullable Marker marker, @Nullable String msg) {
        if (!getUnderlyingLogger().isErrorEnabled()) {
            return;
        }
        getUnderlyingLogger().log(marker, this.fqcn, 40, msg, null, null);
    }

    @Override // org.slf4j.Logger
    public void error(@Nullable Marker marker, @Nullable String format, @Nullable Object arg) {
        if (!getUnderlyingLogger().isErrorEnabled()) {
            return;
        }
        String formattedMessage = MessageFormatter.format(format, arg).getMessage();
        getUnderlyingLogger().log(marker, this.fqcn, 40, formattedMessage, new Object[]{arg}, null);
    }

    @Override // org.slf4j.Logger
    public void error(@Nullable Marker marker, @Nullable String format, @Nullable Object arg1, @Nullable Object arg2) {
        if (!getUnderlyingLogger().isErrorEnabled()) {
            return;
        }
        String formattedMessage = MessageFormatter.format(format, arg1, arg2).getMessage();
        getUnderlyingLogger().log(marker, this.fqcn, 40, formattedMessage, new Object[]{arg1, arg2}, null);
    }

    @Override // org.slf4j.Logger
    public void error(@Nullable Marker marker, @Nullable String format, @NotNull Object[] argArray) {
        Intrinsics.checkParameterIsNotNull(argArray, "argArray");
        if (!getUnderlyingLogger().isErrorEnabled()) {
            return;
        }
        String formattedMessage = MessageFormatter.arrayFormat(format, argArray).getMessage();
        getUnderlyingLogger().log(marker, this.fqcn, 40, formattedMessage, argArray, null);
    }

    @Override // org.slf4j.Logger
    public void error(@Nullable Marker marker, @Nullable String msg, @Nullable Throwable t) {
        if (!getUnderlyingLogger().isErrorEnabled()) {
            return;
        }
        getUnderlyingLogger().log(marker, this.fqcn, 40, msg, null, t);
    }

    @Override // mu.KLogger
    public void trace(@NotNull Function0<? extends Object> msg) {
        String strValueOf;
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        if (!isTraceEnabled()) {
            return;
        }
        try {
            strValueOf = String.valueOf(msg.invoke());
        } catch (Exception e$iv) {
            strValueOf = "Log message invocation failed: " + e$iv;
        }
        trace(strValueOf);
    }

    @Override // mu.KLogger
    public void debug(@NotNull Function0<? extends Object> msg) {
        String strValueOf;
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        if (!isDebugEnabled()) {
            return;
        }
        try {
            strValueOf = String.valueOf(msg.invoke());
        } catch (Exception e$iv) {
            strValueOf = "Log message invocation failed: " + e$iv;
        }
        debug(strValueOf);
    }

    @Override // mu.KLogger
    public void info(@NotNull Function0<? extends Object> msg) {
        String strValueOf;
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        if (!isInfoEnabled()) {
            return;
        }
        try {
            strValueOf = String.valueOf(msg.invoke());
        } catch (Exception e$iv) {
            strValueOf = "Log message invocation failed: " + e$iv;
        }
        info(strValueOf);
    }

    @Override // mu.KLogger
    public void warn(@NotNull Function0<? extends Object> msg) {
        String strValueOf;
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        if (!isWarnEnabled()) {
            return;
        }
        try {
            strValueOf = String.valueOf(msg.invoke());
        } catch (Exception e$iv) {
            strValueOf = "Log message invocation failed: " + e$iv;
        }
        warn(strValueOf);
    }

    @Override // mu.KLogger
    public void error(@NotNull Function0<? extends Object> msg) {
        String strValueOf;
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        if (!isErrorEnabled()) {
            return;
        }
        try {
            strValueOf = String.valueOf(msg.invoke());
        } catch (Exception e$iv) {
            strValueOf = "Log message invocation failed: " + e$iv;
        }
        error(strValueOf);
    }

    @Override // mu.KLogger
    public void trace(@Nullable Throwable t, @NotNull Function0<? extends Object> msg) {
        String strValueOf;
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        if (!isTraceEnabled()) {
            return;
        }
        try {
            strValueOf = String.valueOf(msg.invoke());
        } catch (Exception e$iv) {
            strValueOf = "Log message invocation failed: " + e$iv;
        }
        trace(strValueOf, t);
    }

    @Override // mu.KLogger
    public void debug(@Nullable Throwable t, @NotNull Function0<? extends Object> msg) {
        String strValueOf;
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        if (!isDebugEnabled()) {
            return;
        }
        try {
            strValueOf = String.valueOf(msg.invoke());
        } catch (Exception e$iv) {
            strValueOf = "Log message invocation failed: " + e$iv;
        }
        debug(strValueOf, t);
    }

    @Override // mu.KLogger
    public void info(@Nullable Throwable t, @NotNull Function0<? extends Object> msg) {
        String strValueOf;
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        if (!isInfoEnabled()) {
            return;
        }
        try {
            strValueOf = String.valueOf(msg.invoke());
        } catch (Exception e$iv) {
            strValueOf = "Log message invocation failed: " + e$iv;
        }
        info(strValueOf, t);
    }

    @Override // mu.KLogger
    public void warn(@Nullable Throwable t, @NotNull Function0<? extends Object> msg) {
        String strValueOf;
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        if (!isWarnEnabled()) {
            return;
        }
        try {
            strValueOf = String.valueOf(msg.invoke());
        } catch (Exception e$iv) {
            strValueOf = "Log message invocation failed: " + e$iv;
        }
        warn(strValueOf, t);
    }

    @Override // mu.KLogger
    public void error(@Nullable Throwable t, @NotNull Function0<? extends Object> msg) {
        String strValueOf;
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        if (!isErrorEnabled()) {
            return;
        }
        try {
            strValueOf = String.valueOf(msg.invoke());
        } catch (Exception e$iv) {
            strValueOf = "Log message invocation failed: " + e$iv;
        }
        error(strValueOf, t);
    }

    @Override // mu.KLogger
    public void trace(@Nullable Marker marker, @NotNull Function0<? extends Object> msg) {
        String strValueOf;
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        if (!isTraceEnabled()) {
            return;
        }
        try {
            strValueOf = String.valueOf(msg.invoke());
        } catch (Exception e$iv) {
            strValueOf = "Log message invocation failed: " + e$iv;
        }
        trace(marker, strValueOf);
    }

    @Override // mu.KLogger
    public void debug(@Nullable Marker marker, @NotNull Function0<? extends Object> msg) {
        String strValueOf;
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        if (!isDebugEnabled()) {
            return;
        }
        try {
            strValueOf = String.valueOf(msg.invoke());
        } catch (Exception e$iv) {
            strValueOf = "Log message invocation failed: " + e$iv;
        }
        debug(marker, strValueOf);
    }

    @Override // mu.KLogger
    public void info(@Nullable Marker marker, @NotNull Function0<? extends Object> msg) {
        String strValueOf;
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        if (!isInfoEnabled()) {
            return;
        }
        try {
            strValueOf = String.valueOf(msg.invoke());
        } catch (Exception e$iv) {
            strValueOf = "Log message invocation failed: " + e$iv;
        }
        info(marker, strValueOf);
    }

    @Override // mu.KLogger
    public void warn(@Nullable Marker marker, @NotNull Function0<? extends Object> msg) {
        String strValueOf;
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        if (!isWarnEnabled()) {
            return;
        }
        try {
            strValueOf = String.valueOf(msg.invoke());
        } catch (Exception e$iv) {
            strValueOf = "Log message invocation failed: " + e$iv;
        }
        warn(marker, strValueOf);
    }

    @Override // mu.KLogger
    public void error(@Nullable Marker marker, @NotNull Function0<? extends Object> msg) {
        String strValueOf;
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        if (!isErrorEnabled()) {
            return;
        }
        try {
            strValueOf = String.valueOf(msg.invoke());
        } catch (Exception e$iv) {
            strValueOf = "Log message invocation failed: " + e$iv;
        }
        error(marker, strValueOf);
    }

    @Override // mu.KLogger
    public void trace(@Nullable Marker marker, @Nullable Throwable t, @NotNull Function0<? extends Object> msg) {
        String strValueOf;
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        if (!isTraceEnabled()) {
            return;
        }
        try {
            strValueOf = String.valueOf(msg.invoke());
        } catch (Exception e$iv) {
            strValueOf = "Log message invocation failed: " + e$iv;
        }
        trace(marker, strValueOf, t);
    }

    @Override // mu.KLogger
    public void debug(@Nullable Marker marker, @Nullable Throwable t, @NotNull Function0<? extends Object> msg) {
        String strValueOf;
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        if (!isDebugEnabled()) {
            return;
        }
        try {
            strValueOf = String.valueOf(msg.invoke());
        } catch (Exception e$iv) {
            strValueOf = "Log message invocation failed: " + e$iv;
        }
        debug(marker, strValueOf, t);
    }

    @Override // mu.KLogger
    public void info(@Nullable Marker marker, @Nullable Throwable t, @NotNull Function0<? extends Object> msg) {
        String strValueOf;
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        if (!isInfoEnabled()) {
            return;
        }
        try {
            strValueOf = String.valueOf(msg.invoke());
        } catch (Exception e$iv) {
            strValueOf = "Log message invocation failed: " + e$iv;
        }
        info(marker, strValueOf, t);
    }

    @Override // mu.KLogger
    public void warn(@Nullable Marker marker, @Nullable Throwable t, @NotNull Function0<? extends Object> msg) {
        String strValueOf;
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        if (!isWarnEnabled()) {
            return;
        }
        try {
            strValueOf = String.valueOf(msg.invoke());
        } catch (Exception e$iv) {
            strValueOf = "Log message invocation failed: " + e$iv;
        }
        warn(marker, strValueOf, t);
    }

    @Override // mu.KLogger
    public void error(@Nullable Marker marker, @Nullable Throwable t, @NotNull Function0<? extends Object> msg) {
        String strValueOf;
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        if (!isErrorEnabled()) {
            return;
        }
        try {
            strValueOf = String.valueOf(msg.invoke());
        } catch (Exception e$iv) {
            strValueOf = "Log message invocation failed: " + e$iv;
        }
        error(marker, strValueOf, t);
    }
}
