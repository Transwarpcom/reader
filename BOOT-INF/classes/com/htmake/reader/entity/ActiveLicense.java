package com.htmake.reader.entity;

import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 5, 1},
   k = 1,
   xi = 48,
   d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\bW\b\u0086\b\u0018\u00002\u00020\u0001B½\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\r\u001a\u00020\t\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0003¢\u0006\u0002\u0010\u0018J\t\u0010H\u001a\u00020\u0003HÆ\u0003J\t\u0010I\u001a\u00020\u0005HÆ\u0003J\t\u0010J\u001a\u00020\u0003HÆ\u0003J\t\u0010K\u001a\u00020\u0005HÆ\u0003J\t\u0010L\u001a\u00020\u0007HÆ\u0003J\t\u0010M\u001a\u00020\u0003HÆ\u0003J\t\u0010N\u001a\u00020\u0003HÆ\u0003J\t\u0010O\u001a\u00020\u0003HÆ\u0003J\u0010\u0010P\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u00106J\t\u0010Q\u001a\u00020\u0003HÆ\u0003J\t\u0010R\u001a\u00020\u0005HÆ\u0003J\t\u0010S\u001a\u00020\u0007HÆ\u0003J\t\u0010T\u001a\u00020\tHÆ\u0003J\t\u0010U\u001a\u00020\u0007HÆ\u0003J\t\u0010V\u001a\u00020\u0003HÆ\u0003J\t\u0010W\u001a\u00020\u0003HÆ\u0003J\t\u0010X\u001a\u00020\tHÆ\u0003J\u0010\u0010Y\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u00106JÆ\u0001\u0010Z\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\t2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\u000f\u001a\u00020\u00052\b\b\u0002\u0010\u0010\u001a\u00020\u00032\b\b\u0002\u0010\u0011\u001a\u00020\u00052\b\b\u0002\u0010\u0012\u001a\u00020\u00072\b\b\u0002\u0010\u0013\u001a\u00020\u00032\b\b\u0002\u0010\u0014\u001a\u00020\u00032\b\b\u0002\u0010\u0015\u001a\u00020\u00032\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\u0017\u001a\u00020\u0003HÆ\u0001¢\u0006\u0002\u0010[J\u0013\u0010\\\u001a\u00020\t2\b\u0010]\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010^\u001a\u00020\u0005HÖ\u0001J\t\u0010_\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0014\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001a\u0010\u0013\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001a\"\u0004\b\u001e\u0010\u001cR\u001a\u0010\u0011\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001a\u0010\u0012\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u001a\u0010\f\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\u001a\"\u0004\b(\u0010\u001cR\u001a\u0010\u0017\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\u001a\"\u0004\b*\u0010\u001cR\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010$\"\u0004\b,\u0010&R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010\u001a\"\u0004\b.\u0010\u001cR\u001a\u0010\u000b\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010\u001a\"\u0004\b0\u0010\u001cR\u001a\u0010\u000f\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u0010 \"\u0004\b2\u0010\"R\u001a\u0010\u0015\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u0010\u001a\"\u0004\b4\u0010\u001cR\u001e\u0010\u0016\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u0010\n\u0002\u00109\u001a\u0004\b5\u00106\"\u0004\b7\u00108R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010;\"\u0004\b<\u0010=R\u001a\u0010\n\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b>\u0010$\"\u0004\b?\u0010&R\u001a\u0010\u0010\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b@\u0010\u001a\"\u0004\bA\u0010\u001cR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bB\u0010 \"\u0004\bC\u0010\"R\u001a\u0010\r\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bD\u0010;\"\u0004\bE\u0010=R\u001e\u0010\u000e\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u0010\n\u0002\u00109\u001a\u0004\bF\u00106\"\u0004\bG\u00108¨\u0006`"},
   d2 = {"Lcom/htmake/reader/entity/ActiveLicense;", "", "host", "", "userMaxLimit", "", "expiredAt", "", "openApi", "", "simpleWebExpiredAt", "id", "code", "verified", "verifyTime", "instances", "type", "activeOrder", "activeTime", "activeIp", "activeEmail", "lastOnlineIp", "lastOnlineTime", "errorMsg", "(Ljava/lang/String;IJZJLjava/lang/String;Ljava/lang/String;ZLjava/lang/Long;ILjava/lang/String;IJLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;)V", "getActiveEmail", "()Ljava/lang/String;", "setActiveEmail", "(Ljava/lang/String;)V", "getActiveIp", "setActiveIp", "getActiveOrder", "()I", "setActiveOrder", "(I)V", "getActiveTime", "()J", "setActiveTime", "(J)V", "getCode", "setCode", "getErrorMsg", "setErrorMsg", "getExpiredAt", "setExpiredAt", "getHost", "setHost", "getId", "setId", "getInstances", "setInstances", "getLastOnlineIp", "setLastOnlineIp", "getLastOnlineTime", "()Ljava/lang/Long;", "setLastOnlineTime", "(Ljava/lang/Long;)V", "Ljava/lang/Long;", "getOpenApi", "()Z", "setOpenApi", "(Z)V", "getSimpleWebExpiredAt", "setSimpleWebExpiredAt", "getType", "setType", "getUserMaxLimit", "setUserMaxLimit", "getVerified", "setVerified", "getVerifyTime", "setVerifyTime", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;IJZJLjava/lang/String;Ljava/lang/String;ZLjava/lang/Long;ILjava/lang/String;IJLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;)Lcom/htmake/reader/entity/ActiveLicense;", "equals", "other", "hashCode", "toString", "reader-pro"}
)
public final class ActiveLicense {
   @NotNull
   private String host;
   private int userMaxLimit;
   private long expiredAt;
   private boolean openApi;
   private long simpleWebExpiredAt;
   @NotNull
   private String id;
   @NotNull
   private String code;
   private boolean verified;
   @Nullable
   private Long verifyTime;
   private int instances;
   @NotNull
   private String type;
   private int activeOrder;
   private long activeTime;
   @NotNull
   private String activeIp;
   @NotNull
   private String activeEmail;
   @NotNull
   private String lastOnlineIp;
   @Nullable
   private Long lastOnlineTime;
   @NotNull
   private String errorMsg;

   public ActiveLicense(@NotNull String host, int userMaxLimit, long expiredAt, boolean openApi, long simpleWebExpiredAt, @NotNull String id, @NotNull String code, boolean verified, @Nullable Long verifyTime, int instances, @NotNull String type, int activeOrder, long activeTime, @NotNull String activeIp, @NotNull String activeEmail, @NotNull String lastOnlineIp, @Nullable Long lastOnlineTime, @NotNull String errorMsg) {
      Intrinsics.checkNotNullParameter(host, "host");
      Intrinsics.checkNotNullParameter(id, "id");
      Intrinsics.checkNotNullParameter(code, "code");
      Intrinsics.checkNotNullParameter(type, "type");
      Intrinsics.checkNotNullParameter(activeIp, "activeIp");
      Intrinsics.checkNotNullParameter(activeEmail, "activeEmail");
      Intrinsics.checkNotNullParameter(lastOnlineIp, "lastOnlineIp");
      Intrinsics.checkNotNullParameter(errorMsg, "errorMsg");
      super();
      this.host = host;
      this.userMaxLimit = userMaxLimit;
      this.expiredAt = expiredAt;
      this.openApi = openApi;
      this.simpleWebExpiredAt = simpleWebExpiredAt;
      this.id = id;
      this.code = code;
      this.verified = verified;
      this.verifyTime = verifyTime;
      this.instances = instances;
      this.type = type;
      this.activeOrder = activeOrder;
      this.activeTime = activeTime;
      this.activeIp = activeIp;
      this.activeEmail = activeEmail;
      this.lastOnlineIp = lastOnlineIp;
      this.lastOnlineTime = lastOnlineTime;
      this.errorMsg = errorMsg;
   }

   // $FF: synthetic method
   public ActiveLicense(String var1, int var2, long var3, boolean var5, long var6, String var8, String var9, boolean var10, Long var11, int var12, String var13, int var14, long var15, String var17, String var18, String var19, Long var20, String var21, int var22, DefaultConstructorMarker var23) {
      if ((var22 & 1) != 0) {
         var1 = "*";
      }

      if ((var22 & 2) != 0) {
         var2 = 15;
      }

      if ((var22 & 4) != 0) {
         var3 = 0L;
      }

      if ((var22 & 8) != 0) {
         var5 = false;
      }

      if ((var22 & 16) != 0) {
         var6 = 1682870399000L;
      }

      String var24;
      if ((var22 & 32) != 0) {
         var24 = UUID.randomUUID().toString();
         Intrinsics.checkNotNullExpressionValue(var24, "randomUUID().toString()");
         var8 = var24;
      }

      if ((var22 & 64) != 0) {
         var24 = UUID.randomUUID().toString();
         Intrinsics.checkNotNullExpressionValue(var24, "randomUUID().toString()");
         var9 = var24;
      }

      if ((var22 & 128) != 0) {
         var10 = false;
      }

      if ((var22 & 256) != 0) {
         var11 = null;
      }

      if ((var22 & 512) != 0) {
         var12 = 1;
      }

      if ((var22 & 1024) != 0) {
         var13 = "default";
      }

      if ((var22 & 2048) != 0) {
         var14 = 1;
      }

      if ((var22 & 4096) != 0) {
         var15 = System.currentTimeMillis();
      }

      if ((var22 & 8192) != 0) {
         var17 = "";
      }

      if ((var22 & 16384) != 0) {
         var18 = "";
      }

      if ((var22 & '耀') != 0) {
         var19 = "";
      }

      if ((var22 & 65536) != 0) {
         var20 = null;
      }

      if ((var22 & 131072) != 0) {
         var21 = "";
      }

      this(var1, var2, var3, var5, var6, var8, var9, var10, var11, var12, var13, var14, var15, var17, var18, var19, var20, var21);
   }

   @NotNull
   public final String getHost() {
      return this.host;
   }

   public final void setHost(@NotNull String <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.host = var1;
   }

   public final int getUserMaxLimit() {
      return this.userMaxLimit;
   }

   public final void setUserMaxLimit(int <set-?>) {
      this.userMaxLimit = var1;
   }

   public final long getExpiredAt() {
      return this.expiredAt;
   }

   public final void setExpiredAt(long <set-?>) {
      this.expiredAt = var1;
   }

   public final boolean getOpenApi() {
      return this.openApi;
   }

   public final void setOpenApi(boolean <set-?>) {
      this.openApi = var1;
   }

   public final long getSimpleWebExpiredAt() {
      return this.simpleWebExpiredAt;
   }

   public final void setSimpleWebExpiredAt(long <set-?>) {
      this.simpleWebExpiredAt = var1;
   }

   @NotNull
   public final String getId() {
      return this.id;
   }

   public final void setId(@NotNull String <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.id = var1;
   }

   @NotNull
   public final String getCode() {
      return this.code;
   }

   public final void setCode(@NotNull String <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.code = var1;
   }

   public final boolean getVerified() {
      return this.verified;
   }

   public final void setVerified(boolean <set-?>) {
      this.verified = var1;
   }

   @Nullable
   public final Long getVerifyTime() {
      return this.verifyTime;
   }

   public final void setVerifyTime(@Nullable Long <set-?>) {
      this.verifyTime = var1;
   }

   public final int getInstances() {
      return this.instances;
   }

   public final void setInstances(int <set-?>) {
      this.instances = var1;
   }

   @NotNull
   public final String getType() {
      return this.type;
   }

   public final void setType(@NotNull String <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.type = var1;
   }

   public final int getActiveOrder() {
      return this.activeOrder;
   }

   public final void setActiveOrder(int <set-?>) {
      this.activeOrder = var1;
   }

   public final long getActiveTime() {
      return this.activeTime;
   }

   public final void setActiveTime(long <set-?>) {
      this.activeTime = var1;
   }

   @NotNull
   public final String getActiveIp() {
      return this.activeIp;
   }

   public final void setActiveIp(@NotNull String <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.activeIp = var1;
   }

   @NotNull
   public final String getActiveEmail() {
      return this.activeEmail;
   }

   public final void setActiveEmail(@NotNull String <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.activeEmail = var1;
   }

   @NotNull
   public final String getLastOnlineIp() {
      return this.lastOnlineIp;
   }

   public final void setLastOnlineIp(@NotNull String <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.lastOnlineIp = var1;
   }

   @Nullable
   public final Long getLastOnlineTime() {
      return this.lastOnlineTime;
   }

   public final void setLastOnlineTime(@Nullable Long <set-?>) {
      this.lastOnlineTime = var1;
   }

   @NotNull
   public final String getErrorMsg() {
      return this.errorMsg;
   }

   public final void setErrorMsg(@NotNull String <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.errorMsg = var1;
   }

   @NotNull
   public final String component1() {
      return this.host;
   }

   public final int component2() {
      return this.userMaxLimit;
   }

   public final long component3() {
      return this.expiredAt;
   }

   public final boolean component4() {
      return this.openApi;
   }

   public final long component5() {
      return this.simpleWebExpiredAt;
   }

   @NotNull
   public final String component6() {
      return this.id;
   }

   @NotNull
   public final String component7() {
      return this.code;
   }

   public final boolean component8() {
      return this.verified;
   }

   @Nullable
   public final Long component9() {
      return this.verifyTime;
   }

   public final int component10() {
      return this.instances;
   }

   @NotNull
   public final String component11() {
      return this.type;
   }

   public final int component12() {
      return this.activeOrder;
   }

   public final long component13() {
      return this.activeTime;
   }

   @NotNull
   public final String component14() {
      return this.activeIp;
   }

   @NotNull
   public final String component15() {
      return this.activeEmail;
   }

   @NotNull
   public final String component16() {
      return this.lastOnlineIp;
   }

   @Nullable
   public final Long component17() {
      return this.lastOnlineTime;
   }

   @NotNull
   public final String component18() {
      return this.errorMsg;
   }

   @NotNull
   public final ActiveLicense copy(@NotNull String host, int userMaxLimit, long expiredAt, boolean openApi, long simpleWebExpiredAt, @NotNull String id, @NotNull String code, boolean verified, @Nullable Long verifyTime, int instances, @NotNull String type, int activeOrder, long activeTime, @NotNull String activeIp, @NotNull String activeEmail, @NotNull String lastOnlineIp, @Nullable Long lastOnlineTime, @NotNull String errorMsg) {
      Intrinsics.checkNotNullParameter(host, "host");
      Intrinsics.checkNotNullParameter(id, "id");
      Intrinsics.checkNotNullParameter(code, "code");
      Intrinsics.checkNotNullParameter(type, "type");
      Intrinsics.checkNotNullParameter(activeIp, "activeIp");
      Intrinsics.checkNotNullParameter(activeEmail, "activeEmail");
      Intrinsics.checkNotNullParameter(lastOnlineIp, "lastOnlineIp");
      Intrinsics.checkNotNullParameter(errorMsg, "errorMsg");
      return new ActiveLicense(host, userMaxLimit, expiredAt, openApi, simpleWebExpiredAt, id, code, verified, verifyTime, instances, type, activeOrder, activeTime, activeIp, activeEmail, lastOnlineIp, lastOnlineTime, errorMsg);
   }

   // $FF: synthetic method
   public static ActiveLicense copy$default(ActiveLicense var0, String var1, int var2, long var3, boolean var5, long var6, String var8, String var9, boolean var10, Long var11, int var12, String var13, int var14, long var15, String var17, String var18, String var19, Long var20, String var21, int var22, Object var23) {
      if ((var22 & 1) != 0) {
         var1 = var0.host;
      }

      if ((var22 & 2) != 0) {
         var2 = var0.userMaxLimit;
      }

      if ((var22 & 4) != 0) {
         var3 = var0.expiredAt;
      }

      if ((var22 & 8) != 0) {
         var5 = var0.openApi;
      }

      if ((var22 & 16) != 0) {
         var6 = var0.simpleWebExpiredAt;
      }

      if ((var22 & 32) != 0) {
         var8 = var0.id;
      }

      if ((var22 & 64) != 0) {
         var9 = var0.code;
      }

      if ((var22 & 128) != 0) {
         var10 = var0.verified;
      }

      if ((var22 & 256) != 0) {
         var11 = var0.verifyTime;
      }

      if ((var22 & 512) != 0) {
         var12 = var0.instances;
      }

      if ((var22 & 1024) != 0) {
         var13 = var0.type;
      }

      if ((var22 & 2048) != 0) {
         var14 = var0.activeOrder;
      }

      if ((var22 & 4096) != 0) {
         var15 = var0.activeTime;
      }

      if ((var22 & 8192) != 0) {
         var17 = var0.activeIp;
      }

      if ((var22 & 16384) != 0) {
         var18 = var0.activeEmail;
      }

      if ((var22 & '耀') != 0) {
         var19 = var0.lastOnlineIp;
      }

      if ((var22 & 65536) != 0) {
         var20 = var0.lastOnlineTime;
      }

      if ((var22 & 131072) != 0) {
         var21 = var0.errorMsg;
      }

      return var0.copy(var1, var2, var3, var5, var6, var8, var9, var10, var11, var12, var13, var14, var15, var17, var18, var19, var20, var21);
   }

   @NotNull
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append("ActiveLicense(host=").append(this.host).append(", userMaxLimit=").append(this.userMaxLimit).append(", expiredAt=").append(this.expiredAt).append(", openApi=").append(this.openApi).append(", simpleWebExpiredAt=").append(this.simpleWebExpiredAt).append(", id=").append(this.id).append(", code=").append(this.code).append(", verified=").append(this.verified).append(", verifyTime=").append(this.verifyTime).append(", instances=").append(this.instances).append(", type=").append(this.type).append(", activeOrder=");
      var1.append(this.activeOrder).append(", activeTime=").append(this.activeTime).append(", activeIp=").append(this.activeIp).append(", activeEmail=").append(this.activeEmail).append(", lastOnlineIp=").append(this.lastOnlineIp).append(", lastOnlineTime=").append(this.lastOnlineTime).append(", errorMsg=").append(this.errorMsg).append(')');
      return var1.toString();
   }

   public int hashCode() {
      int result = this.host.hashCode();
      result = result * 31 + Integer.hashCode(this.userMaxLimit);
      result = result * 31 + Long.hashCode(this.expiredAt);
      int var10000 = result * 31;
      byte var10001 = this.openApi;
      if (var10001 != 0) {
         var10001 = 1;
      }

      result = var10000 + var10001;
      result = result * 31 + Long.hashCode(this.simpleWebExpiredAt);
      result = result * 31 + this.id.hashCode();
      result = result * 31 + this.code.hashCode();
      var10000 = result * 31;
      var10001 = this.verified;
      if (var10001 != 0) {
         var10001 = 1;
      }

      result = var10000 + var10001;
      result = result * 31 + (this.verifyTime == null ? 0 : this.verifyTime.hashCode());
      result = result * 31 + Integer.hashCode(this.instances);
      result = result * 31 + this.type.hashCode();
      result = result * 31 + Integer.hashCode(this.activeOrder);
      result = result * 31 + Long.hashCode(this.activeTime);
      result = result * 31 + this.activeIp.hashCode();
      result = result * 31 + this.activeEmail.hashCode();
      result = result * 31 + this.lastOnlineIp.hashCode();
      result = result * 31 + (this.lastOnlineTime == null ? 0 : this.lastOnlineTime.hashCode());
      result = result * 31 + this.errorMsg.hashCode();
      return result;
   }

   public boolean equals(@Nullable Object other) {
      if (this == other) {
         return true;
      } else if (!(other instanceof ActiveLicense)) {
         return false;
      } else {
         ActiveLicense var2 = (ActiveLicense)other;
         if (!Intrinsics.areEqual(this.host, var2.host)) {
            return false;
         } else if (this.userMaxLimit != var2.userMaxLimit) {
            return false;
         } else if (this.expiredAt != var2.expiredAt) {
            return false;
         } else if (this.openApi != var2.openApi) {
            return false;
         } else if (this.simpleWebExpiredAt != var2.simpleWebExpiredAt) {
            return false;
         } else if (!Intrinsics.areEqual(this.id, var2.id)) {
            return false;
         } else if (!Intrinsics.areEqual(this.code, var2.code)) {
            return false;
         } else if (this.verified != var2.verified) {
            return false;
         } else if (!Intrinsics.areEqual(this.verifyTime, var2.verifyTime)) {
            return false;
         } else if (this.instances != var2.instances) {
            return false;
         } else if (!Intrinsics.areEqual(this.type, var2.type)) {
            return false;
         } else if (this.activeOrder != var2.activeOrder) {
            return false;
         } else if (this.activeTime != var2.activeTime) {
            return false;
         } else if (!Intrinsics.areEqual(this.activeIp, var2.activeIp)) {
            return false;
         } else if (!Intrinsics.areEqual(this.activeEmail, var2.activeEmail)) {
            return false;
         } else if (!Intrinsics.areEqual(this.lastOnlineIp, var2.lastOnlineIp)) {
            return false;
         } else if (!Intrinsics.areEqual(this.lastOnlineTime, var2.lastOnlineTime)) {
            return false;
         } else {
            return Intrinsics.areEqual(this.errorMsg, var2.errorMsg);
         }
      }
   }

   public ActiveLicense() {
      this((String)null, 0, 0L, false, 0L, (String)null, (String)null, false, (Long)null, 0, (String)null, 0, 0L, (String)null, (String)null, (String)null, (Long)null, (String)null, 262143, (DefaultConstructorMarker)null);
   }
}
