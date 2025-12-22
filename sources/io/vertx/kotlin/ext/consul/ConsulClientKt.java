package io.vertx.kotlin.ext.consul;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.consul.AclToken;
import io.vertx.ext.consul.BlockingQueryOptions;
import io.vertx.ext.consul.Check;
import io.vertx.ext.consul.CheckList;
import io.vertx.ext.consul.CheckOptions;
import io.vertx.ext.consul.CheckQueryOptions;
import io.vertx.ext.consul.CheckStatus;
import io.vertx.ext.consul.ConsulClient;
import io.vertx.ext.consul.CoordinateList;
import io.vertx.ext.consul.DcCoordinates;
import io.vertx.ext.consul.Event;
import io.vertx.ext.consul.EventList;
import io.vertx.ext.consul.EventListOptions;
import io.vertx.ext.consul.EventOptions;
import io.vertx.ext.consul.HealthState;
import io.vertx.ext.consul.KeyValue;
import io.vertx.ext.consul.KeyValueList;
import io.vertx.ext.consul.KeyValueOptions;
import io.vertx.ext.consul.MaintenanceOptions;
import io.vertx.ext.consul.NodeList;
import io.vertx.ext.consul.NodeQueryOptions;
import io.vertx.ext.consul.PreparedQueryDefinition;
import io.vertx.ext.consul.PreparedQueryExecuteOptions;
import io.vertx.ext.consul.PreparedQueryExecuteResponse;
import io.vertx.ext.consul.Service;
import io.vertx.ext.consul.ServiceEntryList;
import io.vertx.ext.consul.ServiceList;
import io.vertx.ext.consul.ServiceOptions;
import io.vertx.ext.consul.ServiceQueryOptions;
import io.vertx.ext.consul.Session;
import io.vertx.ext.consul.SessionList;
import io.vertx.ext.consul.SessionOptions;
import io.vertx.ext.consul.TxnRequest;
import io.vertx.ext.consul.TxnResponse;
import io.vertx.kotlin.coroutines.VertxCoroutineKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ConsulClient.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��¢\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a\u0015\u0010��\u001a\u00020\u0001*\u00020\u0002H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0003\u001a\u001b\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005*\u00020\u0002H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0003\u001a\u001d\u0010\u0007\u001a\u00020\b*\u00020\u00022\u0006\u0010\t\u001a\u00020\u0006H\u0086@ø\u0001��¢\u0006\u0002\u0010\n\u001a%\u0010\u000b\u001a\u00020\b*\u00020\u00022\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\rH\u0086@ø\u0001��¢\u0006\u0002\u0010\u000e\u001a\u0015\u0010\u000f\u001a\u00020\u0010*\u00020\u0002H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0003\u001a\u001d\u0010\u0011\u001a\u00020\u0010*\u00020\u00022\u0006\u0010\f\u001a\u00020\u0012H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0013\u001a\u001d\u0010\u0014\u001a\u00020\b*\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0006H\u0086@ø\u0001��¢\u0006\u0002\u0010\n\u001a%\u0010\u0016\u001a\u00020\b*\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0017H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0018\u001a\u0015\u0010\u0019\u001a\u00020\b*\u00020\u0002H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0003\u001a\u001d\u0010\u001a\u001a\u00020\b*\u00020\u00022\u0006\u0010\f\u001a\u00020\rH\u0086@ø\u0001��¢\u0006\u0002\u0010\u001b\u001a\u001d\u0010\u001c\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u001d\u001a\u00020\u0006H\u0086@ø\u0001��¢\u0006\u0002\u0010\n\u001a\u001b\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u0005*\u00020\u0002H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0003\u001a\u0015\u0010 \u001a\u00020!*\u00020\u0002H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0003\u001a\u001d\u0010\"\u001a\u00020!*\u00020\u00022\u0006\u0010\f\u001a\u00020\rH\u0086@ø\u0001��¢\u0006\u0002\u0010\u001b\u001a\u001d\u0010#\u001a\u00020\u0006*\u00020\u00022\u0006\u0010$\u001a\u00020%H\u0086@ø\u0001��¢\u0006\u0002\u0010&\u001a\u001d\u0010'\u001a\u00020\u0006*\u00020\u00022\u0006\u0010(\u001a\u00020)H\u0086@ø\u0001��¢\u0006\u0002\u0010*\u001a\u0015\u0010+\u001a\u00020\u0006*\u00020\u0002H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0003\u001a\u001d\u0010,\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\f\u001a\u00020-H\u0086@ø\u0001��¢\u0006\u0002\u0010.\u001a\u001d\u0010/\u001a\u000200*\u00020\u00022\u0006\u0010\u001d\u001a\u00020\u0006H\u0086@ø\u0001��¢\u0006\u0002\u0010\n\u001a\u001d\u00101\u001a\u000200*\u00020\u00022\u0006\u00102\u001a\u00020\u0006H\u0086@ø\u0001��¢\u0006\u0002\u0010\n\u001a\u001d\u00103\u001a\u000200*\u00020\u00022\u0006\u00104\u001a\u00020\u0006H\u0086@ø\u0001��¢\u0006\u0002\u0010\n\u001a\u001d\u00105\u001a\u000200*\u00020\u00022\u0006\u00106\u001a\u00020\u0006H\u0086@ø\u0001��¢\u0006\u0002\u0010\n\u001a\u001d\u00107\u001a\u000200*\u00020\u00022\u0006\u0010\u001d\u001a\u00020\u0006H\u0086@ø\u0001��¢\u0006\u0002\u0010\n\u001a\u001d\u00108\u001a\u000200*\u00020\u00022\u0006\u0010\u001d\u001a\u00020\u0006H\u0086@ø\u0001��¢\u0006\u0002\u0010\n\u001a\u001d\u00109\u001a\u000200*\u00020\u00022\u0006\u0010\u001d\u001a\u00020\u0006H\u0086@ø\u0001��¢\u0006\u0002\u0010\n\u001a\u001d\u0010:\u001a\u00020;*\u00020\u00022\u0006\u0010<\u001a\u00020\u0006H\u0086@ø\u0001��¢\u0006\u0002\u0010\n\u001a%\u0010=\u001a\u00020;*\u00020\u00022\u0006\u0010<\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020>H\u0086@ø\u0001��¢\u0006\u0002\u0010?\u001a\u001d\u0010@\u001a\u000200*\u00020\u00022\u0006\u00106\u001a\u00020\u0006H\u0086@ø\u0001��¢\u0006\u0002\u0010\n\u001a%\u0010A\u001a\u000200*\u00020\u00022\u0006\u00106\u001a\u00020\u00062\u0006\u0010B\u001a\u00020\u0006H\u0086@ø\u0001��¢\u0006\u0002\u0010C\u001a\u001d\u0010D\u001a\u00020E*\u00020\u00022\u0006\u0010F\u001a\u00020\u0006H\u0086@ø\u0001��¢\u0006\u0002\u0010\n\u001a%\u0010G\u001a\u00020E*\u00020\u00022\u0006\u0010F\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020HH\u0086@ø\u0001��¢\u0006\u0002\u0010I\u001a\u001b\u0010J\u001a\b\u0012\u0004\u0012\u00020)0\u0005*\u00020\u0002H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0003\u001a#\u0010K\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005*\u00020\u00022\u0006\u00104\u001a\u00020\u0006H\u0086@ø\u0001��¢\u0006\u0002\u0010\n\u001a+\u0010L\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005*\u00020\u00022\u0006\u00104\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\rH\u0086@ø\u0001��¢\u0006\u0002\u0010\u000e\u001a\u001d\u0010M\u001a\u00020)*\u00020\u00022\u0006\u0010\u001d\u001a\u00020\u0006H\u0086@ø\u0001��¢\u0006\u0002\u0010\n\u001a\u001d\u0010N\u001a\u00020O*\u00020\u00022\u0006\u00102\u001a\u00020\u0006H\u0086@ø\u0001��¢\u0006\u0002\u0010\n\u001a%\u0010P\u001a\u00020O*\u00020\u00022\u0006\u00102\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\rH\u0086@ø\u0001��¢\u0006\u0002\u0010\u000e\u001a\u001d\u0010Q\u001a\u00020R*\u00020\u00022\u0006\u00104\u001a\u00020\u0006H\u0086@ø\u0001��¢\u0006\u0002\u0010\n\u001a%\u0010S\u001a\u00020R*\u00020\u00022\u0006\u00104\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\rH\u0086@ø\u0001��¢\u0006\u0002\u0010\u000e\u001a\u001d\u0010T\u001a\u00020U*\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0006H\u0086@ø\u0001��¢\u0006\u0002\u0010\n\u001a%\u0010V\u001a\u00020U*\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020WH\u0086@ø\u0001��¢\u0006\u0002\u0010X\u001a%\u0010Y\u001a\u00020Z*\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u00062\u0006\u0010[\u001a\u00020\\H\u0086@ø\u0001��¢\u0006\u0002\u0010]\u001a-\u0010^\u001a\u00020Z*\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u00062\u0006\u0010[\u001a\u00020\\2\u0006\u0010\f\u001a\u00020\u0017H\u0086@ø\u0001��¢\u0006\u0002\u0010_\u001a\u001d\u0010`\u001a\u00020U*\u00020\u00022\u0006\u0010a\u001a\u00020bH\u0086@ø\u0001��¢\u0006\u0002\u0010c\u001a%\u0010d\u001a\u00020U*\u00020\u00022\u0006\u0010a\u001a\u00020b2\u0006\u0010\f\u001a\u00020WH\u0086@ø\u0001��¢\u0006\u0002\u0010e\u001a\u001d\u0010f\u001a\u00020%*\u00020\u00022\u0006\u0010\u001d\u001a\u00020\u0006H\u0086@ø\u0001��¢\u0006\u0002\u0010\n\u001a\u001d\u0010g\u001a\u00020h*\u00020\u00022\u0006\u0010\u001d\u001a\u00020\u0006H\u0086@ø\u0001��¢\u0006\u0002\u0010\n\u001a%\u0010i\u001a\u00020h*\u00020\u00022\u0006\u0010\u001d\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\rH\u0086@ø\u0001��¢\u0006\u0002\u0010\u000e\u001a\u0015\u0010j\u001a\u00020\u0006*\u00020\u0002H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0003\u001a\u001b\u0010k\u001a\b\u0012\u0004\u0012\u00020%0\u0005*\u00020\u0002H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0003\u001a\u0015\u0010l\u001a\u00020m*\u00020\u0002H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0003\u001a\u001d\u0010n\u001a\u00020m*\u00020\u00022\u0006\u0010\f\u001a\u00020oH\u0086@ø\u0001��¢\u0006\u0002\u0010p\u001a\u001d\u0010q\u001a\u00020r*\u00020\u00022\u0006\u0010s\u001a\u00020\u0006H\u0086@ø\u0001��¢\u0006\u0002\u0010\n\u001a%\u0010t\u001a\u00020r*\u00020\u00022\u0006\u0010s\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\rH\u0086@ø\u0001��¢\u0006\u0002\u0010\u000e\u001a\u0015\u0010u\u001a\u00020r*\u00020\u0002H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0003\u001a\u001d\u0010v\u001a\u00020r*\u00020\u00022\u0006\u0010\f\u001a\u00020\rH\u0086@ø\u0001��¢\u0006\u0002\u0010\u001b\u001a\u001b\u0010w\u001a\b\u0012\u0004\u0012\u00020x0\u0005*\u00020\u0002H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0003\u001a\u001b\u0010y\u001a\b\u0012\u0004\u0012\u00020z0\u0005*\u00020\u0002H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0003\u001a\u001d\u0010{\u001a\u000200*\u00020\u00022\u0006\u0010|\u001a\u00020}H\u0086@ø\u0001��¢\u0006\u0002\u0010~\u001a\u001d\u0010\u007f\u001a\u000200*\u00020\u00022\u0006\u00106\u001a\u00020\u0006H\u0086@ø\u0001��¢\u0006\u0002\u0010\n\u001a&\u0010\u0080\u0001\u001a\u000200*\u00020\u00022\u0006\u00106\u001a\u00020\u00062\u0006\u0010B\u001a\u00020\u0006H\u0086@ø\u0001��¢\u0006\u0002\u0010C\u001a\u001c\u0010\u0081\u0001\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005*\u00020\u0002H\u0086@ø\u0001��¢\u0006\u0002\u0010\u0003\u001a'\u0010\u0082\u0001\u001a\u00020\\*\u00020\u00022\u0006\u00102\u001a\u00020\u00062\u0007\u0010\u0083\u0001\u001a\u00020\u0006H\u0086@ø\u0001��¢\u0006\u0002\u0010C\u001a1\u0010\u0084\u0001\u001a\u00020\\*\u00020\u00022\u0006\u00102\u001a\u00020\u00062\u0007\u0010\u0083\u0001\u001a\u00020\u00062\u0007\u0010\f\u001a\u00030\u0085\u0001H\u0086@ø\u0001��¢\u0006\u0003\u0010\u0086\u0001\u001a!\u0010\u0087\u0001\u001a\u000200*\u00020\u00022\b\u0010\u0088\u0001\u001a\u00030\u0089\u0001H\u0086@ø\u0001��¢\u0006\u0003\u0010\u008a\u0001\u001a!\u0010\u008b\u0001\u001a\u000200*\u00020\u00022\b\u0010\u008c\u0001\u001a\u00030\u008d\u0001H\u0086@ø\u0001��¢\u0006\u0003\u0010\u008e\u0001\u001a\u001e\u0010\u008f\u0001\u001a\u00020h*\u00020\u00022\u0006\u0010\u001d\u001a\u00020\u0006H\u0086@ø\u0001��¢\u0006\u0002\u0010\n\u001a\"\u0010\u0090\u0001\u001a\u00030\u0091\u0001*\u00020\u00022\b\u0010\u0092\u0001\u001a\u00030\u0093\u0001H\u0086@ø\u0001��¢\u0006\u0003\u0010\u0094\u0001\u001a\u001e\u0010\u0095\u0001\u001a\u00020\u0006*\u00020\u00022\u0006\u0010$\u001a\u00020%H\u0086@ø\u0001��¢\u0006\u0002\u0010&\u001a)\u0010\u0096\u0001\u001a\u000200*\u00020\u00022\u0006\u00106\u001a\u00020\u00062\b\u0010\u0097\u0001\u001a\u00030\u0098\u0001H\u0086@ø\u0001��¢\u0006\u0003\u0010\u0099\u0001\u001a1\u0010\u009a\u0001\u001a\u000200*\u00020\u00022\u0006\u00106\u001a\u00020\u00062\b\u0010\u0097\u0001\u001a\u00030\u0098\u00012\u0006\u0010B\u001a\u00020\u0006H\u0086@ø\u0001��¢\u0006\u0003\u0010\u009b\u0001\u001a\u001e\u0010\u009c\u0001\u001a\u000200*\u00020\u00022\u0006\u0010(\u001a\u00020)H\u0086@ø\u0001��¢\u0006\u0002\u0010*\u001a\u001e\u0010\u009d\u0001\u001a\u000200*\u00020\u00022\u0006\u00106\u001a\u00020\u0006H\u0086@ø\u0001��¢\u0006\u0002\u0010\n\u001a&\u0010\u009e\u0001\u001a\u000200*\u00020\u00022\u0006\u00106\u001a\u00020\u00062\u0006\u0010B\u001a\u00020\u0006H\u0086@ø\u0001��¢\u0006\u0002\u0010C\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u009f\u0001"}, d2 = {"agentInfoAwait", "Lio/vertx/core/json/JsonObject;", "Lio/vertx/ext/consul/ConsulClient;", "(Lio/vertx/ext/consul/ConsulClient;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "catalogDatacentersAwait", "", "", "catalogNodeServicesAwait", "Lio/vertx/ext/consul/ServiceList;", "node", "(Lio/vertx/ext/consul/ConsulClient;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "catalogNodeServicesWithOptionsAwait", "options", "Lio/vertx/ext/consul/BlockingQueryOptions;", "(Lio/vertx/ext/consul/ConsulClient;Ljava/lang/String;Lio/vertx/ext/consul/BlockingQueryOptions;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "catalogNodesAwait", "Lio/vertx/ext/consul/NodeList;", "catalogNodesWithOptionsAwait", "Lio/vertx/ext/consul/NodeQueryOptions;", "(Lio/vertx/ext/consul/ConsulClient;Lio/vertx/ext/consul/NodeQueryOptions;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "catalogServiceNodesAwait", "service", "catalogServiceNodesWithOptionsAwait", "Lio/vertx/ext/consul/ServiceQueryOptions;", "(Lio/vertx/ext/consul/ConsulClient;Ljava/lang/String;Lio/vertx/ext/consul/ServiceQueryOptions;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "catalogServicesAwait", "catalogServicesWithOptionsAwait", "(Lio/vertx/ext/consul/ConsulClient;Lio/vertx/ext/consul/BlockingQueryOptions;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "cloneAclTokenAwait", "id", "coordinateDatacentersAwait", "Lio/vertx/ext/consul/DcCoordinates;", "coordinateNodesAwait", "Lio/vertx/ext/consul/CoordinateList;", "coordinateNodesWithOptionsAwait", "createAclTokenAwait", "token", "Lio/vertx/ext/consul/AclToken;", "(Lio/vertx/ext/consul/ConsulClient;Lio/vertx/ext/consul/AclToken;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createPreparedQueryAwait", "definition", "Lio/vertx/ext/consul/PreparedQueryDefinition;", "(Lio/vertx/ext/consul/ConsulClient;Lio/vertx/ext/consul/PreparedQueryDefinition;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createSessionAwait", "createSessionWithOptionsAwait", "Lio/vertx/ext/consul/SessionOptions;", "(Lio/vertx/ext/consul/ConsulClient;Lio/vertx/ext/consul/SessionOptions;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deletePreparedQueryAwait", "", "deleteValueAwait", "key", "deleteValuesAwait", "keyPrefix", "deregisterCheckAwait", "checkId", "deregisterServiceAwait", "destroyAclTokenAwait", "destroySessionAwait", "executePreparedQueryAwait", "Lio/vertx/ext/consul/PreparedQueryExecuteResponse;", "query", "executePreparedQueryWithOptionsAwait", "Lio/vertx/ext/consul/PreparedQueryExecuteOptions;", "(Lio/vertx/ext/consul/ConsulClient;Ljava/lang/String;Lio/vertx/ext/consul/PreparedQueryExecuteOptions;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "failCheckAwait", "failCheckWithNoteAwait", "note", "(Lio/vertx/ext/consul/ConsulClient;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fireEventAwait", "Lio/vertx/ext/consul/Event;", "name", "fireEventWithOptionsAwait", "Lio/vertx/ext/consul/EventOptions;", "(Lio/vertx/ext/consul/ConsulClient;Ljava/lang/String;Lio/vertx/ext/consul/EventOptions;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllPreparedQueriesAwait", "getKeysAwait", "getKeysWithOptionsAwait", "getPreparedQueryAwait", "getValueAwait", "Lio/vertx/ext/consul/KeyValue;", "getValueWithOptionsAwait", "getValuesAwait", "Lio/vertx/ext/consul/KeyValueList;", "getValuesWithOptionsAwait", "healthChecksAwait", "Lio/vertx/ext/consul/CheckList;", "healthChecksWithOptionsAwait", "Lio/vertx/ext/consul/CheckQueryOptions;", "(Lio/vertx/ext/consul/ConsulClient;Ljava/lang/String;Lio/vertx/ext/consul/CheckQueryOptions;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "healthServiceNodesAwait", "Lio/vertx/ext/consul/ServiceEntryList;", "passing", "", "(Lio/vertx/ext/consul/ConsulClient;Ljava/lang/String;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "healthServiceNodesWithOptionsAwait", "(Lio/vertx/ext/consul/ConsulClient;Ljava/lang/String;ZLio/vertx/ext/consul/ServiceQueryOptions;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "healthStateAwait", "healthState", "Lio/vertx/ext/consul/HealthState;", "(Lio/vertx/ext/consul/ConsulClient;Lio/vertx/ext/consul/HealthState;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "healthStateWithOptionsAwait", "(Lio/vertx/ext/consul/ConsulClient;Lio/vertx/ext/consul/HealthState;Lio/vertx/ext/consul/CheckQueryOptions;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "infoAclTokenAwait", "infoSessionAwait", "Lio/vertx/ext/consul/Session;", "infoSessionWithOptionsAwait", "leaderStatusAwait", "listAclTokensAwait", "listEventsAwait", "Lio/vertx/ext/consul/EventList;", "listEventsWithOptionsAwait", "Lio/vertx/ext/consul/EventListOptions;", "(Lio/vertx/ext/consul/ConsulClient;Lio/vertx/ext/consul/EventListOptions;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "listNodeSessionsAwait", "Lio/vertx/ext/consul/SessionList;", "nodeId", "listNodeSessionsWithOptionsAwait", "listSessionsAwait", "listSessionsWithOptionsAwait", "localChecksAwait", "Lio/vertx/ext/consul/Check;", "localServicesAwait", "Lio/vertx/ext/consul/Service;", "maintenanceServiceAwait", "maintenanceOptions", "Lio/vertx/ext/consul/MaintenanceOptions;", "(Lio/vertx/ext/consul/ConsulClient;Lio/vertx/ext/consul/MaintenanceOptions;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "passCheckAwait", "passCheckWithNoteAwait", "peersStatusAwait", "putValueAwait", "value", "putValueWithOptionsAwait", "Lio/vertx/ext/consul/KeyValueOptions;", "(Lio/vertx/ext/consul/ConsulClient;Ljava/lang/String;Ljava/lang/String;Lio/vertx/ext/consul/KeyValueOptions;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "registerCheckAwait", "checkOptions", "Lio/vertx/ext/consul/CheckOptions;", "(Lio/vertx/ext/consul/ConsulClient;Lio/vertx/ext/consul/CheckOptions;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "registerServiceAwait", "serviceOptions", "Lio/vertx/ext/consul/ServiceOptions;", "(Lio/vertx/ext/consul/ConsulClient;Lio/vertx/ext/consul/ServiceOptions;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "renewSessionAwait", "transactionAwait", "Lio/vertx/ext/consul/TxnResponse;", "request", "Lio/vertx/ext/consul/TxnRequest;", "(Lio/vertx/ext/consul/ConsulClient;Lio/vertx/ext/consul/TxnRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateAclTokenAwait", "updateCheckAwait", "status", "Lio/vertx/ext/consul/CheckStatus;", "(Lio/vertx/ext/consul/ConsulClient;Ljava/lang/String;Lio/vertx/ext/consul/CheckStatus;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateCheckWithNoteAwait", "(Lio/vertx/ext/consul/ConsulClient;Ljava/lang/String;Lio/vertx/ext/consul/CheckStatus;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updatePreparedQueryAwait", "warnCheckAwait", "warnCheckWithNoteAwait", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/consul/ConsulClientKt.class */
public final class ConsulClientKt {
    @Nullable
    public static final Object agentInfoAwait(@NotNull final ConsulClient $this$agentInfoAwait, @NotNull Continuation<? super JsonObject> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<JsonObject>>, Unit>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.agentInfoAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<JsonObject>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<JsonObject>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$agentInfoAwait.agentInfo(it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object coordinateNodesAwait(@NotNull final ConsulClient $this$coordinateNodesAwait, @NotNull Continuation<? super CoordinateList> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<CoordinateList>>, Unit>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.coordinateNodesAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<CoordinateList>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<CoordinateList>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$coordinateNodesAwait.coordinateNodes(it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object coordinateNodesWithOptionsAwait(@NotNull final ConsulClient $this$coordinateNodesWithOptionsAwait, @NotNull final BlockingQueryOptions options, @NotNull Continuation<? super CoordinateList> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<CoordinateList>>, Unit>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.coordinateNodesWithOptionsAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<CoordinateList>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<CoordinateList>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$coordinateNodesWithOptionsAwait.coordinateNodesWithOptions(options, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object coordinateDatacentersAwait(@NotNull final ConsulClient $this$coordinateDatacentersAwait, @NotNull Continuation<? super List<? extends DcCoordinates>> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<List<? extends DcCoordinates>>>, Unit>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.coordinateDatacentersAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<List<? extends DcCoordinates>>> handler) {
                invoke2((Handler<AsyncResult<List<DcCoordinates>>>) handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<List<DcCoordinates>>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$coordinateDatacentersAwait.coordinateDatacenters(it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object getKeysAwait(@NotNull final ConsulClient $this$getKeysAwait, @NotNull final String keyPrefix, @NotNull Continuation<? super List<String>> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<List<? extends String>>>, Unit>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.getKeysAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<List<? extends String>>> handler) {
                invoke2((Handler<AsyncResult<List<String>>>) handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<List<String>>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$getKeysAwait.getKeys(keyPrefix, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object getKeysWithOptionsAwait(@NotNull final ConsulClient $this$getKeysWithOptionsAwait, @NotNull final String keyPrefix, @NotNull final BlockingQueryOptions options, @NotNull Continuation<? super List<String>> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<List<? extends String>>>, Unit>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.getKeysWithOptionsAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<List<? extends String>>> handler) {
                invoke2((Handler<AsyncResult<List<String>>>) handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<List<String>>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$getKeysWithOptionsAwait.getKeysWithOptions(keyPrefix, options, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object getValueAwait(@NotNull final ConsulClient $this$getValueAwait, @NotNull final String key, @NotNull Continuation<? super KeyValue> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<KeyValue>>, Unit>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.getValueAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<KeyValue>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<KeyValue>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$getValueAwait.getValue(key, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object getValueWithOptionsAwait(@NotNull final ConsulClient $this$getValueWithOptionsAwait, @NotNull final String key, @NotNull final BlockingQueryOptions options, @NotNull Continuation<? super KeyValue> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<KeyValue>>, Unit>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.getValueWithOptionsAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<KeyValue>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<KeyValue>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$getValueWithOptionsAwait.getValueWithOptions(key, options, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object deleteValueAwait(@NotNull final ConsulClient $this$deleteValueAwait, @NotNull final String key, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.deleteValueAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Unit>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull final Handler<AsyncResult<Unit>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$deleteValueAwait.deleteValue(key, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.deleteValueAwait.2.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // io.vertx.core.Handler
                    public final void handle(AsyncResult<Void> asyncResult) {
                        it.handle(asyncResult.mapEmpty());
                    }
                });
            }
        }, continuation);
    }

    @Nullable
    public static final Object getValuesAwait(@NotNull final ConsulClient $this$getValuesAwait, @NotNull final String keyPrefix, @NotNull Continuation<? super KeyValueList> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<KeyValueList>>, Unit>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.getValuesAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<KeyValueList>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<KeyValueList>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$getValuesAwait.getValues(keyPrefix, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object getValuesWithOptionsAwait(@NotNull final ConsulClient $this$getValuesWithOptionsAwait, @NotNull final String keyPrefix, @NotNull final BlockingQueryOptions options, @NotNull Continuation<? super KeyValueList> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<KeyValueList>>, Unit>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.getValuesWithOptionsAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<KeyValueList>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<KeyValueList>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$getValuesWithOptionsAwait.getValuesWithOptions(keyPrefix, options, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object deleteValuesAwait(@NotNull final ConsulClient $this$deleteValuesAwait, @NotNull final String keyPrefix, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.deleteValuesAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Unit>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull final Handler<AsyncResult<Unit>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$deleteValuesAwait.deleteValues(keyPrefix, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.deleteValuesAwait.2.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // io.vertx.core.Handler
                    public final void handle(AsyncResult<Void> asyncResult) {
                        it.handle(asyncResult.mapEmpty());
                    }
                });
            }
        }, continuation);
    }

    @Nullable
    public static final Object putValueAwait(@NotNull final ConsulClient $this$putValueAwait, @NotNull final String key, @NotNull final String value, @NotNull Continuation<? super Boolean> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Boolean>>, Unit>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.putValueAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Boolean>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<Boolean>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$putValueAwait.putValue(key, value, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object putValueWithOptionsAwait(@NotNull final ConsulClient $this$putValueWithOptionsAwait, @NotNull final String key, @NotNull final String value, @NotNull final KeyValueOptions options, @NotNull Continuation<? super Boolean> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Boolean>>, Unit>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.putValueWithOptionsAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Boolean>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<Boolean>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$putValueWithOptionsAwait.putValueWithOptions(key, value, options, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object transactionAwait(@NotNull final ConsulClient $this$transactionAwait, @NotNull final TxnRequest request, @NotNull Continuation<? super TxnResponse> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<TxnResponse>>, Unit>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.transactionAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<TxnResponse>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<TxnResponse>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$transactionAwait.transaction(request, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object createAclTokenAwait(@NotNull final ConsulClient $this$createAclTokenAwait, @NotNull final AclToken token, @NotNull Continuation<? super String> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<String>>, Unit>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.createAclTokenAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<String>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<String>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$createAclTokenAwait.createAclToken(token, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object updateAclTokenAwait(@NotNull final ConsulClient $this$updateAclTokenAwait, @NotNull final AclToken token, @NotNull Continuation<? super String> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<String>>, Unit>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.updateAclTokenAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<String>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<String>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$updateAclTokenAwait.updateAclToken(token, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object cloneAclTokenAwait(@NotNull final ConsulClient $this$cloneAclTokenAwait, @NotNull final String id, @NotNull Continuation<? super String> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<String>>, Unit>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.cloneAclTokenAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<String>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<String>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$cloneAclTokenAwait.cloneAclToken(id, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object listAclTokensAwait(@NotNull final ConsulClient $this$listAclTokensAwait, @NotNull Continuation<? super List<? extends AclToken>> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<List<? extends AclToken>>>, Unit>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.listAclTokensAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<List<? extends AclToken>>> handler) {
                invoke2((Handler<AsyncResult<List<AclToken>>>) handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<List<AclToken>>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$listAclTokensAwait.listAclTokens(it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object infoAclTokenAwait(@NotNull final ConsulClient $this$infoAclTokenAwait, @NotNull final String id, @NotNull Continuation<? super AclToken> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<AclToken>>, Unit>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.infoAclTokenAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<AclToken>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<AclToken>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$infoAclTokenAwait.infoAclToken(id, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object destroyAclTokenAwait(@NotNull final ConsulClient $this$destroyAclTokenAwait, @NotNull final String id, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.destroyAclTokenAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Unit>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull final Handler<AsyncResult<Unit>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$destroyAclTokenAwait.destroyAclToken(id, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.destroyAclTokenAwait.2.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // io.vertx.core.Handler
                    public final void handle(AsyncResult<Void> asyncResult) {
                        it.handle(asyncResult.mapEmpty());
                    }
                });
            }
        }, continuation);
    }

    @Nullable
    public static final Object fireEventAwait(@NotNull final ConsulClient $this$fireEventAwait, @NotNull final String name, @NotNull Continuation<? super Event> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Event>>, Unit>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.fireEventAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Event>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<Event>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$fireEventAwait.fireEvent(name, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object fireEventWithOptionsAwait(@NotNull final ConsulClient $this$fireEventWithOptionsAwait, @NotNull final String name, @NotNull final EventOptions options, @NotNull Continuation<? super Event> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Event>>, Unit>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.fireEventWithOptionsAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Event>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<Event>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$fireEventWithOptionsAwait.fireEventWithOptions(name, options, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object listEventsAwait(@NotNull final ConsulClient $this$listEventsAwait, @NotNull Continuation<? super EventList> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<EventList>>, Unit>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.listEventsAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<EventList>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<EventList>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$listEventsAwait.listEvents(it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object listEventsWithOptionsAwait(@NotNull final ConsulClient $this$listEventsWithOptionsAwait, @NotNull final EventListOptions options, @NotNull Continuation<? super EventList> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<EventList>>, Unit>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.listEventsWithOptionsAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<EventList>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<EventList>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$listEventsWithOptionsAwait.listEventsWithOptions(options, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object registerServiceAwait(@NotNull final ConsulClient $this$registerServiceAwait, @NotNull final ServiceOptions serviceOptions, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.registerServiceAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Unit>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull final Handler<AsyncResult<Unit>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$registerServiceAwait.registerService(serviceOptions, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.registerServiceAwait.2.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // io.vertx.core.Handler
                    public final void handle(AsyncResult<Void> asyncResult) {
                        it.handle(asyncResult.mapEmpty());
                    }
                });
            }
        }, continuation);
    }

    @Nullable
    public static final Object maintenanceServiceAwait(@NotNull final ConsulClient $this$maintenanceServiceAwait, @NotNull final MaintenanceOptions maintenanceOptions, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.maintenanceServiceAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Unit>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull final Handler<AsyncResult<Unit>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$maintenanceServiceAwait.maintenanceService(maintenanceOptions, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.maintenanceServiceAwait.2.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // io.vertx.core.Handler
                    public final void handle(AsyncResult<Void> asyncResult) {
                        it.handle(asyncResult.mapEmpty());
                    }
                });
            }
        }, continuation);
    }

    @Nullable
    public static final Object deregisterServiceAwait(@NotNull final ConsulClient $this$deregisterServiceAwait, @NotNull final String id, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.deregisterServiceAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Unit>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull final Handler<AsyncResult<Unit>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$deregisterServiceAwait.deregisterService(id, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.deregisterServiceAwait.2.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // io.vertx.core.Handler
                    public final void handle(AsyncResult<Void> asyncResult) {
                        it.handle(asyncResult.mapEmpty());
                    }
                });
            }
        }, continuation);
    }

    @Nullable
    public static final Object catalogServiceNodesAwait(@NotNull final ConsulClient $this$catalogServiceNodesAwait, @NotNull final String service, @NotNull Continuation<? super ServiceList> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<ServiceList>>, Unit>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.catalogServiceNodesAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<ServiceList>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<ServiceList>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$catalogServiceNodesAwait.catalogServiceNodes(service, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object catalogServiceNodesWithOptionsAwait(@NotNull final ConsulClient $this$catalogServiceNodesWithOptionsAwait, @NotNull final String service, @NotNull final ServiceQueryOptions options, @NotNull Continuation<? super ServiceList> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<ServiceList>>, Unit>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.catalogServiceNodesWithOptionsAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<ServiceList>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<ServiceList>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$catalogServiceNodesWithOptionsAwait.catalogServiceNodesWithOptions(service, options, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object catalogDatacentersAwait(@NotNull final ConsulClient $this$catalogDatacentersAwait, @NotNull Continuation<? super List<String>> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<List<? extends String>>>, Unit>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.catalogDatacentersAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<List<? extends String>>> handler) {
                invoke2((Handler<AsyncResult<List<String>>>) handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<List<String>>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$catalogDatacentersAwait.catalogDatacenters(it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object catalogNodesAwait(@NotNull final ConsulClient $this$catalogNodesAwait, @NotNull Continuation<? super NodeList> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<NodeList>>, Unit>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.catalogNodesAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<NodeList>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<NodeList>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$catalogNodesAwait.catalogNodes(it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object catalogNodesWithOptionsAwait(@NotNull final ConsulClient $this$catalogNodesWithOptionsAwait, @NotNull final NodeQueryOptions options, @NotNull Continuation<? super NodeList> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<NodeList>>, Unit>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.catalogNodesWithOptionsAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<NodeList>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<NodeList>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$catalogNodesWithOptionsAwait.catalogNodesWithOptions(options, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object healthChecksAwait(@NotNull final ConsulClient $this$healthChecksAwait, @NotNull final String service, @NotNull Continuation<? super CheckList> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<CheckList>>, Unit>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.healthChecksAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<CheckList>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<CheckList>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$healthChecksAwait.healthChecks(service, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object healthChecksWithOptionsAwait(@NotNull final ConsulClient $this$healthChecksWithOptionsAwait, @NotNull final String service, @NotNull final CheckQueryOptions options, @NotNull Continuation<? super CheckList> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<CheckList>>, Unit>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.healthChecksWithOptionsAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<CheckList>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<CheckList>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$healthChecksWithOptionsAwait.healthChecksWithOptions(service, options, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object healthStateAwait(@NotNull final ConsulClient $this$healthStateAwait, @NotNull final HealthState healthState, @NotNull Continuation<? super CheckList> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<CheckList>>, Unit>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.healthStateAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<CheckList>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<CheckList>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$healthStateAwait.healthState(healthState, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object healthStateWithOptionsAwait(@NotNull final ConsulClient $this$healthStateWithOptionsAwait, @NotNull final HealthState healthState, @NotNull final CheckQueryOptions options, @NotNull Continuation<? super CheckList> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<CheckList>>, Unit>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.healthStateWithOptionsAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<CheckList>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<CheckList>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$healthStateWithOptionsAwait.healthStateWithOptions(healthState, options, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object healthServiceNodesAwait(@NotNull final ConsulClient $this$healthServiceNodesAwait, @NotNull final String service, final boolean passing, @NotNull Continuation<? super ServiceEntryList> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<ServiceEntryList>>, Unit>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.healthServiceNodesAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<ServiceEntryList>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<ServiceEntryList>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$healthServiceNodesAwait.healthServiceNodes(service, passing, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object healthServiceNodesWithOptionsAwait(@NotNull final ConsulClient $this$healthServiceNodesWithOptionsAwait, @NotNull final String service, final boolean passing, @NotNull final ServiceQueryOptions options, @NotNull Continuation<? super ServiceEntryList> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<ServiceEntryList>>, Unit>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.healthServiceNodesWithOptionsAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<ServiceEntryList>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<ServiceEntryList>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$healthServiceNodesWithOptionsAwait.healthServiceNodesWithOptions(service, passing, options, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object catalogServicesAwait(@NotNull final ConsulClient $this$catalogServicesAwait, @NotNull Continuation<? super ServiceList> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<ServiceList>>, Unit>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.catalogServicesAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<ServiceList>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<ServiceList>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$catalogServicesAwait.catalogServices(it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object catalogServicesWithOptionsAwait(@NotNull final ConsulClient $this$catalogServicesWithOptionsAwait, @NotNull final BlockingQueryOptions options, @NotNull Continuation<? super ServiceList> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<ServiceList>>, Unit>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.catalogServicesWithOptionsAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<ServiceList>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<ServiceList>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$catalogServicesWithOptionsAwait.catalogServicesWithOptions(options, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object catalogNodeServicesAwait(@NotNull final ConsulClient $this$catalogNodeServicesAwait, @NotNull final String node, @NotNull Continuation<? super ServiceList> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<ServiceList>>, Unit>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.catalogNodeServicesAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<ServiceList>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<ServiceList>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$catalogNodeServicesAwait.catalogNodeServices(node, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object catalogNodeServicesWithOptionsAwait(@NotNull final ConsulClient $this$catalogNodeServicesWithOptionsAwait, @NotNull final String node, @NotNull final BlockingQueryOptions options, @NotNull Continuation<? super ServiceList> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<ServiceList>>, Unit>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.catalogNodeServicesWithOptionsAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<ServiceList>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<ServiceList>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$catalogNodeServicesWithOptionsAwait.catalogNodeServicesWithOptions(node, options, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object localServicesAwait(@NotNull final ConsulClient $this$localServicesAwait, @NotNull Continuation<? super List<? extends Service>> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<List<? extends Service>>>, Unit>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.localServicesAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<List<? extends Service>>> handler) {
                invoke2((Handler<AsyncResult<List<Service>>>) handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<List<Service>>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$localServicesAwait.localServices(it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object localChecksAwait(@NotNull final ConsulClient $this$localChecksAwait, @NotNull Continuation<? super List<? extends Check>> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<List<? extends Check>>>, Unit>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.localChecksAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<List<? extends Check>>> handler) {
                invoke2((Handler<AsyncResult<List<Check>>>) handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<List<Check>>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$localChecksAwait.localChecks(it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object registerCheckAwait(@NotNull final ConsulClient $this$registerCheckAwait, @NotNull final CheckOptions checkOptions, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.registerCheckAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Unit>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull final Handler<AsyncResult<Unit>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$registerCheckAwait.registerCheck(checkOptions, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.registerCheckAwait.2.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // io.vertx.core.Handler
                    public final void handle(AsyncResult<Void> asyncResult) {
                        it.handle(asyncResult.mapEmpty());
                    }
                });
            }
        }, continuation);
    }

    @Nullable
    public static final Object deregisterCheckAwait(@NotNull final ConsulClient $this$deregisterCheckAwait, @NotNull final String checkId, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.deregisterCheckAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Unit>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull final Handler<AsyncResult<Unit>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$deregisterCheckAwait.deregisterCheck(checkId, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.deregisterCheckAwait.2.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // io.vertx.core.Handler
                    public final void handle(AsyncResult<Void> asyncResult) {
                        it.handle(asyncResult.mapEmpty());
                    }
                });
            }
        }, continuation);
    }

    @Nullable
    public static final Object passCheckAwait(@NotNull final ConsulClient $this$passCheckAwait, @NotNull final String checkId, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.passCheckAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Unit>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull final Handler<AsyncResult<Unit>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$passCheckAwait.passCheck(checkId, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.passCheckAwait.2.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // io.vertx.core.Handler
                    public final void handle(AsyncResult<Void> asyncResult) {
                        it.handle(asyncResult.mapEmpty());
                    }
                });
            }
        }, continuation);
    }

    @Nullable
    public static final Object passCheckWithNoteAwait(@NotNull final ConsulClient $this$passCheckWithNoteAwait, @NotNull final String checkId, @NotNull final String note, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.passCheckWithNoteAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Unit>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull final Handler<AsyncResult<Unit>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$passCheckWithNoteAwait.passCheckWithNote(checkId, note, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.passCheckWithNoteAwait.2.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // io.vertx.core.Handler
                    public final void handle(AsyncResult<Void> asyncResult) {
                        it.handle(asyncResult.mapEmpty());
                    }
                });
            }
        }, continuation);
    }

    @Nullable
    public static final Object warnCheckAwait(@NotNull final ConsulClient $this$warnCheckAwait, @NotNull final String checkId, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.warnCheckAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Unit>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull final Handler<AsyncResult<Unit>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$warnCheckAwait.warnCheck(checkId, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.warnCheckAwait.2.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // io.vertx.core.Handler
                    public final void handle(AsyncResult<Void> asyncResult) {
                        it.handle(asyncResult.mapEmpty());
                    }
                });
            }
        }, continuation);
    }

    @Nullable
    public static final Object warnCheckWithNoteAwait(@NotNull final ConsulClient $this$warnCheckWithNoteAwait, @NotNull final String checkId, @NotNull final String note, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.warnCheckWithNoteAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Unit>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull final Handler<AsyncResult<Unit>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$warnCheckWithNoteAwait.warnCheckWithNote(checkId, note, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.warnCheckWithNoteAwait.2.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // io.vertx.core.Handler
                    public final void handle(AsyncResult<Void> asyncResult) {
                        it.handle(asyncResult.mapEmpty());
                    }
                });
            }
        }, continuation);
    }

    @Nullable
    public static final Object failCheckAwait(@NotNull final ConsulClient $this$failCheckAwait, @NotNull final String checkId, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.failCheckAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Unit>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull final Handler<AsyncResult<Unit>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$failCheckAwait.failCheck(checkId, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.failCheckAwait.2.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // io.vertx.core.Handler
                    public final void handle(AsyncResult<Void> asyncResult) {
                        it.handle(asyncResult.mapEmpty());
                    }
                });
            }
        }, continuation);
    }

    @Nullable
    public static final Object failCheckWithNoteAwait(@NotNull final ConsulClient $this$failCheckWithNoteAwait, @NotNull final String checkId, @NotNull final String note, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.failCheckWithNoteAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Unit>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull final Handler<AsyncResult<Unit>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$failCheckWithNoteAwait.failCheckWithNote(checkId, note, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.failCheckWithNoteAwait.2.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // io.vertx.core.Handler
                    public final void handle(AsyncResult<Void> asyncResult) {
                        it.handle(asyncResult.mapEmpty());
                    }
                });
            }
        }, continuation);
    }

    @Nullable
    public static final Object updateCheckAwait(@NotNull final ConsulClient $this$updateCheckAwait, @NotNull final String checkId, @NotNull final CheckStatus status, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.updateCheckAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Unit>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull final Handler<AsyncResult<Unit>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$updateCheckAwait.updateCheck(checkId, status, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.updateCheckAwait.2.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // io.vertx.core.Handler
                    public final void handle(AsyncResult<Void> asyncResult) {
                        it.handle(asyncResult.mapEmpty());
                    }
                });
            }
        }, continuation);
    }

    @Nullable
    public static final Object updateCheckWithNoteAwait(@NotNull final ConsulClient $this$updateCheckWithNoteAwait, @NotNull final String checkId, @NotNull final CheckStatus status, @NotNull final String note, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.updateCheckWithNoteAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Unit>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull final Handler<AsyncResult<Unit>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$updateCheckWithNoteAwait.updateCheckWithNote(checkId, status, note, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.updateCheckWithNoteAwait.2.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // io.vertx.core.Handler
                    public final void handle(AsyncResult<Void> asyncResult) {
                        it.handle(asyncResult.mapEmpty());
                    }
                });
            }
        }, continuation);
    }

    @Nullable
    public static final Object leaderStatusAwait(@NotNull final ConsulClient $this$leaderStatusAwait, @NotNull Continuation<? super String> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<String>>, Unit>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.leaderStatusAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<String>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<String>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$leaderStatusAwait.leaderStatus(it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object peersStatusAwait(@NotNull final ConsulClient $this$peersStatusAwait, @NotNull Continuation<? super List<String>> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<List<? extends String>>>, Unit>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.peersStatusAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<List<? extends String>>> handler) {
                invoke2((Handler<AsyncResult<List<String>>>) handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<List<String>>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$peersStatusAwait.peersStatus(it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object createSessionAwait(@NotNull final ConsulClient $this$createSessionAwait, @NotNull Continuation<? super String> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<String>>, Unit>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.createSessionAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<String>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<String>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$createSessionAwait.createSession(it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object createSessionWithOptionsAwait(@NotNull final ConsulClient $this$createSessionWithOptionsAwait, @NotNull final SessionOptions options, @NotNull Continuation<? super String> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<String>>, Unit>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.createSessionWithOptionsAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<String>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<String>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$createSessionWithOptionsAwait.createSessionWithOptions(options, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object infoSessionAwait(@NotNull final ConsulClient $this$infoSessionAwait, @NotNull final String id, @NotNull Continuation<? super Session> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Session>>, Unit>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.infoSessionAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Session>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<Session>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$infoSessionAwait.infoSession(id, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object infoSessionWithOptionsAwait(@NotNull final ConsulClient $this$infoSessionWithOptionsAwait, @NotNull final String id, @NotNull final BlockingQueryOptions options, @NotNull Continuation<? super Session> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Session>>, Unit>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.infoSessionWithOptionsAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Session>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<Session>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$infoSessionWithOptionsAwait.infoSessionWithOptions(id, options, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object renewSessionAwait(@NotNull final ConsulClient $this$renewSessionAwait, @NotNull final String id, @NotNull Continuation<? super Session> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Session>>, Unit>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.renewSessionAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Session>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<Session>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$renewSessionAwait.renewSession(id, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object listSessionsAwait(@NotNull final ConsulClient $this$listSessionsAwait, @NotNull Continuation<? super SessionList> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<SessionList>>, Unit>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.listSessionsAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<SessionList>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<SessionList>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$listSessionsAwait.listSessions(it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object listSessionsWithOptionsAwait(@NotNull final ConsulClient $this$listSessionsWithOptionsAwait, @NotNull final BlockingQueryOptions options, @NotNull Continuation<? super SessionList> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<SessionList>>, Unit>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.listSessionsWithOptionsAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<SessionList>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<SessionList>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$listSessionsWithOptionsAwait.listSessionsWithOptions(options, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object listNodeSessionsAwait(@NotNull final ConsulClient $this$listNodeSessionsAwait, @NotNull final String nodeId, @NotNull Continuation<? super SessionList> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<SessionList>>, Unit>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.listNodeSessionsAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<SessionList>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<SessionList>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$listNodeSessionsAwait.listNodeSessions(nodeId, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object listNodeSessionsWithOptionsAwait(@NotNull final ConsulClient $this$listNodeSessionsWithOptionsAwait, @NotNull final String nodeId, @NotNull final BlockingQueryOptions options, @NotNull Continuation<? super SessionList> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<SessionList>>, Unit>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.listNodeSessionsWithOptionsAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<SessionList>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<SessionList>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$listNodeSessionsWithOptionsAwait.listNodeSessionsWithOptions(nodeId, options, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object destroySessionAwait(@NotNull final ConsulClient $this$destroySessionAwait, @NotNull final String id, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.destroySessionAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Unit>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull final Handler<AsyncResult<Unit>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$destroySessionAwait.destroySession(id, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.destroySessionAwait.2.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // io.vertx.core.Handler
                    public final void handle(AsyncResult<Void> asyncResult) {
                        it.handle(asyncResult.mapEmpty());
                    }
                });
            }
        }, continuation);
    }

    @Nullable
    public static final Object createPreparedQueryAwait(@NotNull final ConsulClient $this$createPreparedQueryAwait, @NotNull final PreparedQueryDefinition definition, @NotNull Continuation<? super String> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<String>>, Unit>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.createPreparedQueryAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<String>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<String>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$createPreparedQueryAwait.createPreparedQuery(definition, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object getPreparedQueryAwait(@NotNull final ConsulClient $this$getPreparedQueryAwait, @NotNull final String id, @NotNull Continuation<? super PreparedQueryDefinition> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<PreparedQueryDefinition>>, Unit>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.getPreparedQueryAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<PreparedQueryDefinition>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<PreparedQueryDefinition>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$getPreparedQueryAwait.getPreparedQuery(id, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object getAllPreparedQueriesAwait(@NotNull final ConsulClient $this$getAllPreparedQueriesAwait, @NotNull Continuation<? super List<? extends PreparedQueryDefinition>> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<List<? extends PreparedQueryDefinition>>>, Unit>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.getAllPreparedQueriesAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<List<? extends PreparedQueryDefinition>>> handler) {
                invoke2((Handler<AsyncResult<List<PreparedQueryDefinition>>>) handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<List<PreparedQueryDefinition>>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$getAllPreparedQueriesAwait.getAllPreparedQueries(it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object updatePreparedQueryAwait(@NotNull final ConsulClient $this$updatePreparedQueryAwait, @NotNull final PreparedQueryDefinition definition, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.updatePreparedQueryAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Unit>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull final Handler<AsyncResult<Unit>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$updatePreparedQueryAwait.updatePreparedQuery(definition, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.updatePreparedQueryAwait.2.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // io.vertx.core.Handler
                    public final void handle(AsyncResult<Void> asyncResult) {
                        it.handle(asyncResult.mapEmpty());
                    }
                });
            }
        }, continuation);
    }

    @Nullable
    public static final Object deletePreparedQueryAwait(@NotNull final ConsulClient $this$deletePreparedQueryAwait, @NotNull final String id, @NotNull Continuation<? super Unit> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<Unit>>, Unit>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.deletePreparedQueryAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<Unit>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull final Handler<AsyncResult<Unit>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$deletePreparedQueryAwait.deletePreparedQuery(id, new Handler<AsyncResult<Void>>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.deletePreparedQueryAwait.2.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // io.vertx.core.Handler
                    public final void handle(AsyncResult<Void> asyncResult) {
                        it.handle(asyncResult.mapEmpty());
                    }
                });
            }
        }, continuation);
    }

    @Nullable
    public static final Object executePreparedQueryAwait(@NotNull final ConsulClient $this$executePreparedQueryAwait, @NotNull final String query, @NotNull Continuation<? super PreparedQueryExecuteResponse> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<PreparedQueryExecuteResponse>>, Unit>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.executePreparedQueryAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<PreparedQueryExecuteResponse>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<PreparedQueryExecuteResponse>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$executePreparedQueryAwait.executePreparedQuery(query, it);
            }
        }, continuation);
    }

    @Nullable
    public static final Object executePreparedQueryWithOptionsAwait(@NotNull final ConsulClient $this$executePreparedQueryWithOptionsAwait, @NotNull final String query, @NotNull final PreparedQueryExecuteOptions options, @NotNull Continuation<? super PreparedQueryExecuteResponse> continuation) {
        return VertxCoroutineKt.awaitResult(new Function1<Handler<AsyncResult<PreparedQueryExecuteResponse>>, Unit>() { // from class: io.vertx.kotlin.ext.consul.ConsulClientKt.executePreparedQueryWithOptionsAwait.2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Handler<AsyncResult<PreparedQueryExecuteResponse>> handler) {
                invoke2(handler);
                return Unit.INSTANCE;
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Handler<AsyncResult<PreparedQueryExecuteResponse>> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                $this$executePreparedQueryWithOptionsAwait.executePreparedQueryWithOptions(query, options, it);
            }
        }, continuation);
    }
}
