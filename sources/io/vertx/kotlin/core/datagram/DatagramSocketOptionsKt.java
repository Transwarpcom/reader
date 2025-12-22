package io.vertx.kotlin.core.datagram;

import io.vertx.core.datagram.DatagramSocketOptions;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: DatagramSocketOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u001e\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u000e\n��\n\u0002\u0010\b\n\u0002\b\b\u001a\u0091\u0001\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\nH\u0007¢\u0006\u0002\u0010\u0010\u001a\u008f\u0001\u0010\u0011\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u0010¨\u0006\u0012"}, d2 = {"DatagramSocketOptions", "Lio/vertx/core/datagram/DatagramSocketOptions;", "broadcast", "", "ipV6", "logActivity", "loopbackModeDisabled", "multicastNetworkInterface", "", "multicastTimeToLive", "", "receiveBufferSize", "reuseAddress", "reusePort", "sendBufferSize", "trafficClass", "(Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/Integer;)Lio/vertx/core/datagram/DatagramSocketOptions;", "datagramSocketOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/core/datagram/DatagramSocketOptionsKt.class */
public final class DatagramSocketOptionsKt {
    @NotNull
    public static /* synthetic */ DatagramSocketOptions datagramSocketOptionsOf$default(Boolean bool, Boolean bool2, Boolean bool3, Boolean bool4, String str, Integer num, Integer num2, Boolean bool5, Boolean bool6, Integer num3, Integer num4, int i, Object obj) {
        if ((i & 1) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 2) != 0) {
            bool2 = (Boolean) null;
        }
        if ((i & 4) != 0) {
            bool3 = (Boolean) null;
        }
        if ((i & 8) != 0) {
            bool4 = (Boolean) null;
        }
        if ((i & 16) != 0) {
            str = (String) null;
        }
        if ((i & 32) != 0) {
            num = (Integer) null;
        }
        if ((i & 64) != 0) {
            num2 = (Integer) null;
        }
        if ((i & 128) != 0) {
            bool5 = (Boolean) null;
        }
        if ((i & 256) != 0) {
            bool6 = (Boolean) null;
        }
        if ((i & 512) != 0) {
            num3 = (Integer) null;
        }
        if ((i & 1024) != 0) {
            num4 = (Integer) null;
        }
        return datagramSocketOptionsOf(bool, bool2, bool3, bool4, str, num, num2, bool5, bool6, num3, num4);
    }

    @NotNull
    public static final DatagramSocketOptions datagramSocketOptionsOf(@Nullable Boolean broadcast, @Nullable Boolean ipV6, @Nullable Boolean logActivity, @Nullable Boolean loopbackModeDisabled, @Nullable String multicastNetworkInterface, @Nullable Integer multicastTimeToLive, @Nullable Integer receiveBufferSize, @Nullable Boolean reuseAddress, @Nullable Boolean reusePort, @Nullable Integer sendBufferSize, @Nullable Integer trafficClass) {
        DatagramSocketOptions $this$apply = new DatagramSocketOptions();
        if (broadcast != null) {
            $this$apply.setBroadcast(broadcast.booleanValue());
        }
        if (ipV6 != null) {
            $this$apply.setIpV6(ipV6.booleanValue());
        }
        if (logActivity != null) {
            $this$apply.setLogActivity(logActivity.booleanValue());
        }
        if (loopbackModeDisabled != null) {
            $this$apply.setLoopbackModeDisabled(loopbackModeDisabled.booleanValue());
        }
        if (multicastNetworkInterface != null) {
            $this$apply.setMulticastNetworkInterface(multicastNetworkInterface);
        }
        if (multicastTimeToLive != null) {
            $this$apply.setMulticastTimeToLive(multicastTimeToLive.intValue());
        }
        if (receiveBufferSize != null) {
            $this$apply.setReceiveBufferSize(receiveBufferSize.intValue());
        }
        if (reuseAddress != null) {
            $this$apply.setReuseAddress(reuseAddress.booleanValue());
        }
        if (reusePort != null) {
            $this$apply.setReusePort(reusePort.booleanValue());
        }
        if (sendBufferSize != null) {
            $this$apply.setSendBufferSize(sendBufferSize.intValue());
        }
        if (trafficClass != null) {
            $this$apply.setTrafficClass(trafficClass.intValue());
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "datagramSocketOptionsOf(broadcast, ipV6, logActivity, loopbackModeDisabled, multicastNetworkInterface, multicastTimeToLive, receiveBufferSize, reuseAddress, reusePort, sendBufferSize, trafficClass)"))
    @NotNull
    public static /* synthetic */ DatagramSocketOptions DatagramSocketOptions$default(Boolean bool, Boolean bool2, Boolean bool3, Boolean bool4, String str, Integer num, Integer num2, Boolean bool5, Boolean bool6, Integer num3, Integer num4, int i, Object obj) {
        if ((i & 1) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 2) != 0) {
            bool2 = (Boolean) null;
        }
        if ((i & 4) != 0) {
            bool3 = (Boolean) null;
        }
        if ((i & 8) != 0) {
            bool4 = (Boolean) null;
        }
        if ((i & 16) != 0) {
            str = (String) null;
        }
        if ((i & 32) != 0) {
            num = (Integer) null;
        }
        if ((i & 64) != 0) {
            num2 = (Integer) null;
        }
        if ((i & 128) != 0) {
            bool5 = (Boolean) null;
        }
        if ((i & 256) != 0) {
            bool6 = (Boolean) null;
        }
        if ((i & 512) != 0) {
            num3 = (Integer) null;
        }
        if ((i & 1024) != 0) {
            num4 = (Integer) null;
        }
        return DatagramSocketOptions(bool, bool2, bool3, bool4, str, num, num2, bool5, bool6, num3, num4);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "datagramSocketOptionsOf(broadcast, ipV6, logActivity, loopbackModeDisabled, multicastNetworkInterface, multicastTimeToLive, receiveBufferSize, reuseAddress, reusePort, sendBufferSize, trafficClass)"))
    @NotNull
    public static final DatagramSocketOptions DatagramSocketOptions(@Nullable Boolean broadcast, @Nullable Boolean ipV6, @Nullable Boolean logActivity, @Nullable Boolean loopbackModeDisabled, @Nullable String multicastNetworkInterface, @Nullable Integer multicastTimeToLive, @Nullable Integer receiveBufferSize, @Nullable Boolean reuseAddress, @Nullable Boolean reusePort, @Nullable Integer sendBufferSize, @Nullable Integer trafficClass) {
        DatagramSocketOptions $this$apply = new DatagramSocketOptions();
        if (broadcast != null) {
            $this$apply.setBroadcast(broadcast.booleanValue());
        }
        if (ipV6 != null) {
            $this$apply.setIpV6(ipV6.booleanValue());
        }
        if (logActivity != null) {
            $this$apply.setLogActivity(logActivity.booleanValue());
        }
        if (loopbackModeDisabled != null) {
            $this$apply.setLoopbackModeDisabled(loopbackModeDisabled.booleanValue());
        }
        if (multicastNetworkInterface != null) {
            $this$apply.setMulticastNetworkInterface(multicastNetworkInterface);
        }
        if (multicastTimeToLive != null) {
            $this$apply.setMulticastTimeToLive(multicastTimeToLive.intValue());
        }
        if (receiveBufferSize != null) {
            $this$apply.setReceiveBufferSize(receiveBufferSize.intValue());
        }
        if (reuseAddress != null) {
            $this$apply.setReuseAddress(reuseAddress.booleanValue());
        }
        if (reusePort != null) {
            $this$apply.setReusePort(reusePort.booleanValue());
        }
        if (sendBufferSize != null) {
            $this$apply.setSendBufferSize(sendBufferSize.intValue());
        }
        if (trafficClass != null) {
            $this$apply.setTrafficClass(trafficClass.intValue());
        }
        return $this$apply;
    }
}
