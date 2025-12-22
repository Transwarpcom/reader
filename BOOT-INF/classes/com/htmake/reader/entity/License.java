package com.htmake.reader.entity;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 5, 1},
   k = 1,
   xi = 48,
   d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b;\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001Bu\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\r\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000f\u001a\u00020\t\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\u0011J\t\u00103\u001a\u00020\u0003HÆ\u0003J\t\u00104\u001a\u00020\tHÆ\u0003J\u0010\u00105\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010/J\t\u00106\u001a\u00020\u0005HÆ\u0003J\t\u00107\u001a\u00020\u0007HÆ\u0003J\t\u00108\u001a\u00020\tHÆ\u0003J\t\u00109\u001a\u00020\u0007HÆ\u0003J\t\u0010:\u001a\u00020\u0005HÆ\u0003J\t\u0010;\u001a\u00020\u0003HÆ\u0003J\t\u0010<\u001a\u00020\u0003HÆ\u0003J\t\u0010=\u001a\u00020\u0003HÆ\u0003J~\u0010>\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\t2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0002\u0010?J\u0013\u0010@\u001a\u00020\t2\b\u0010A\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010B\u001a\u00020\u0005HÖ\u0001J\u0006\u0010C\u001a\u00020\tJ\u0006\u0010D\u001a\u00020EJ\t\u0010F\u001a\u00020\u0003HÖ\u0001J\u000e\u0010G\u001a\u00020\t2\u0006\u0010H\u001a\u00020\u0003R\u001a\u0010\u000e\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0013\"\u0004\b\u001b\u0010\u0015R\u001a\u0010\r\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0013\"\u0004\b\u001d\u0010\u0015R\u001a\u0010\u000b\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u001a\u0010\n\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u0017\"\u0004\b'\u0010\u0019R\u001a\u0010\f\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u0013\"\u0004\b)\u0010\u0015R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010\u001f\"\u0004\b+\u0010!R\u001a\u0010\u000f\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010#\"\u0004\b-\u0010%R\u001e\u0010\u0010\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u0010\n\u0002\u00102\u001a\u0004\b.\u0010/\"\u0004\b0\u00101¨\u0006I"},
   d2 = {"Lcom/htmake/reader/entity/License;", "", "host", "", "userMaxLimit", "", "expiredAt", "", "openApi", "", "simpleWebExpiredAt", "instances", "type", "id", "code", "verified", "verifyTime", "(Ljava/lang/String;IJZJILjava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/Long;)V", "getCode", "()Ljava/lang/String;", "setCode", "(Ljava/lang/String;)V", "getExpiredAt", "()J", "setExpiredAt", "(J)V", "getHost", "setHost", "getId", "setId", "getInstances", "()I", "setInstances", "(I)V", "getOpenApi", "()Z", "setOpenApi", "(Z)V", "getSimpleWebExpiredAt", "setSimpleWebExpiredAt", "getType", "setType", "getUserMaxLimit", "setUserMaxLimit", "getVerified", "setVerified", "getVerifyTime", "()Ljava/lang/Long;", "setVerifyTime", "(Ljava/lang/Long;)V", "Ljava/lang/Long;", "component1", "component10", "component11", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;IJZJILjava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/Long;)Lcom/htmake/reader/entity/License;", "equals", "other", "hashCode", "isValid", "toActiveLicense", "Lcom/htmake/reader/entity/ActiveLicense;", "toString", "validHost", "queryHost", "reader-pro"}
)
public final class License {
   @NotNull
   private String host;
   private int userMaxLimit;
   private long expiredAt;
   private boolean openApi;
   private long simpleWebExpiredAt;
   private int instances;
   @NotNull
   private String type;
   @NotNull
   private String id;
   @NotNull
   private String code;
   private boolean verified;
   @Nullable
   private Long verifyTime;

   public License(@NotNull String host, int userMaxLimit, long expiredAt, boolean openApi, long simpleWebExpiredAt, int instances, @NotNull String type, @NotNull String id, @NotNull String code, boolean verified, @Nullable Long verifyTime) {
      Intrinsics.checkNotNullParameter(host, "host");
      Intrinsics.checkNotNullParameter(type, "type");
      Intrinsics.checkNotNullParameter(id, "id");
      Intrinsics.checkNotNullParameter(code, "code");
      super();
      this.host = host;
      this.userMaxLimit = userMaxLimit;
      this.expiredAt = expiredAt;
      this.openApi = openApi;
      this.simpleWebExpiredAt = simpleWebExpiredAt;
      this.instances = instances;
      this.type = type;
      this.id = id;
      this.code = code;
      this.verified = verified;
      this.verifyTime = verifyTime;
   }

   // $FF: synthetic method
   public License(String var1, int var2, long var3, boolean var5, long var6, int var8, String var9, String var10, String var11, boolean var12, Long var13, int var14, DefaultConstructorMarker var15) {
      if ((var14 & 1) != 0) {
         var1 = "*";
      }

      if ((var14 & 2) != 0) {
         var2 = 15;
      }

      if ((var14 & 4) != 0) {
         var3 = 0L;
      }

      if ((var14 & 8) != 0) {
         var5 = false;
      }

      if ((var14 & 16) != 0) {
         var6 = 1688140799000L;
      }

      if ((var14 & 32) != 0) {
         var8 = 1;
      }

      if ((var14 & 64) != 0) {
         var9 = "default";
      }

      String var16;
      if ((var14 & 128) != 0) {
         var16 = UUID.randomUUID().toString();
         Intrinsics.checkNotNullExpressionValue(var16, "randomUUID().toString()");
         var10 = var16;
      }

      if ((var14 & 256) != 0) {
         var16 = UUID.randomUUID().toString();
         Intrinsics.checkNotNullExpressionValue(var16, "randomUUID().toString()");
         var11 = var16;
      }

      if ((var14 & 512) != 0) {
         var12 = false;
      }

      if ((var14 & 1024) != 0) {
         var13 = null;
      }

      this(var1, var2, var3, var5, var6, var8, var9, var10, var11, var12, var13);
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

   public final boolean isValid() {
      return this.expiredAt == 0L || this.expiredAt >= System.currentTimeMillis();
   }

   public final boolean validHost(@NotNull String queryHost) {
      Intrinsics.checkNotNullParameter(queryHost, "queryHost");
      if (!this.isValid()) {
         return false;
      } else {
         CharSequence var2 = (CharSequence)queryHost;
         boolean var3 = false;
         boolean var4 = false;
         if (var2.length() == 0) {
            return false;
         } else if ("*".equals(this.host)) {
            return true;
         } else {
            CharSequence var10000 = (CharSequence)queryHost;
            String[] var13 = new String[]{":"};
            List hostParts = StringsKt.split$default(var10000, var13, false, 0, 6, (Object)null);
            var10000 = (CharSequence)hostParts.get(0);
            String[] var15 = new String[]{"."};
            List queryParts = StringsKt.split$default(var10000, var15, false, 0, 6, (Object)null);
            var10000 = (CharSequence)this.host;
            String[] var5 = new String[]{","};
            List hostList = StringsKt.split$default(var10000, var5, false, 0, 6, (Object)null);
            Iterator var17 = hostList.iterator();

            boolean isValid;
            do {
               List parts;
               do {
                  if (!var17.hasNext()) {
                     return false;
                  }

                  String hostname = (String)var17.next();
                  var10000 = (CharSequence)hostname;
                  String[] var8 = new String[]{"."};
                  parts = StringsKt.split$default(var10000, var8, false, 0, 6, (Object)null);
               } while(parts.size() != queryParts.size());

               isValid = true;
               int var9 = 0;
               int var10 = parts.size();
               if (var9 < var10) {
                  do {
                     int i = var9++;
                     if (!"*".equals(parts.get(i)) && !((String)parts.get(i)).equals(queryParts.get(i))) {
                        isValid = false;
                     }
                  } while(var9 < var10);
               }
            } while(!isValid);

            return true;
         }
      }
   }

   @NotNull
   public final ActiveLicense toActiveLicense() {
      return new ActiveLicense(this.host, this.userMaxLimit, this.expiredAt, this.openApi, this.simpleWebExpiredAt, this.id, this.code, this.verified, this.verifyTime, this.instances, this.type, 0, 0L, (String)null, (String)null, (String)null, (Long)null, (String)null, 260096, (DefaultConstructorMarker)null);
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

   public final int component6() {
      return this.instances;
   }

   @NotNull
   public final String component7() {
      return this.type;
   }

   @NotNull
   public final String component8() {
      return this.id;
   }

   @NotNull
   public final String component9() {
      return this.code;
   }

   public final boolean component10() {
      return this.verified;
   }

   @Nullable
   public final Long component11() {
      return this.verifyTime;
   }

   @NotNull
   public final License copy(@NotNull String host, int userMaxLimit, long expiredAt, boolean openApi, long simpleWebExpiredAt, int instances, @NotNull String type, @NotNull String id, @NotNull String code, boolean verified, @Nullable Long verifyTime) {
      Intrinsics.checkNotNullParameter(host, "host");
      Intrinsics.checkNotNullParameter(type, "type");
      Intrinsics.checkNotNullParameter(id, "id");
      Intrinsics.checkNotNullParameter(code, "code");
      return new License(host, userMaxLimit, expiredAt, openApi, simpleWebExpiredAt, instances, type, id, code, verified, verifyTime);
   }

   // $FF: synthetic method
   public static License copy$default(License var0, String var1, int var2, long var3, boolean var5, long var6, int var8, String var9, String var10, String var11, boolean var12, Long var13, int var14, Object var15) {
      if ((var14 & 1) != 0) {
         var1 = var0.host;
      }

      if ((var14 & 2) != 0) {
         var2 = var0.userMaxLimit;
      }

      if ((var14 & 4) != 0) {
         var3 = var0.expiredAt;
      }

      if ((var14 & 8) != 0) {
         var5 = var0.openApi;
      }

      if ((var14 & 16) != 0) {
         var6 = var0.simpleWebExpiredAt;
      }

      if ((var14 & 32) != 0) {
         var8 = var0.instances;
      }

      if ((var14 & 64) != 0) {
         var9 = var0.type;
      }

      if ((var14 & 128) != 0) {
         var10 = var0.id;
      }

      if ((var14 & 256) != 0) {
         var11 = var0.code;
      }

      if ((var14 & 512) != 0) {
         var12 = var0.verified;
      }

      if ((var14 & 1024) != 0) {
         var13 = var0.verifyTime;
      }

      return var0.copy(var1, var2, var3, var5, var6, var8, var9, var10, var11, var12, var13);
   }

   @NotNull
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append("License(host=").append(this.host).append(", userMaxLimit=").append(this.userMaxLimit).append(", expiredAt=").append(this.expiredAt).append(", openApi=").append(this.openApi).append(", simpleWebExpiredAt=").append(this.simpleWebExpiredAt).append(", instances=").append(this.instances).append(", type=").append(this.type).append(", id=").append(this.id).append(", code=").append(this.code).append(", verified=").append(this.verified).append(", verifyTime=").append(this.verifyTime).append(')');
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
      result = result * 31 + Integer.hashCode(this.instances);
      result = result * 31 + this.type.hashCode();
      result = result * 31 + this.id.hashCode();
      result = result * 31 + this.code.hashCode();
      var10000 = result * 31;
      var10001 = this.verified;
      if (var10001 != 0) {
         var10001 = 1;
      }

      result = var10000 + var10001;
      result = result * 31 + (this.verifyTime == null ? 0 : this.verifyTime.hashCode());
      return result;
   }

   public boolean equals(@Nullable Object other) {
      if (this == other) {
         return true;
      } else if (!(other instanceof License)) {
         return false;
      } else {
         License var2 = (License)other;
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
         } else if (this.instances != var2.instances) {
            return false;
         } else if (!Intrinsics.areEqual(this.type, var2.type)) {
            return false;
         } else if (!Intrinsics.areEqual(this.id, var2.id)) {
            return false;
         } else if (!Intrinsics.areEqual(this.code, var2.code)) {
            return false;
         } else if (this.verified != var2.verified) {
            return false;
         } else {
            return Intrinsics.areEqual(this.verifyTime, var2.verifyTime);
         }
      }
   }

   public License() {
      this((String)null, 0, 0L, false, 0L, 0, (String)null, (String)null, (String)null, false, (Long)null, 2047, (DefaultConstructorMarker)null);
   }
}
