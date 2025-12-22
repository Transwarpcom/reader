package io.legado.app.data.entities;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 5, 1},
   k = 1,
   xi = 48,
   d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u001e\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0005HÆ\u0003J\t\u0010 \u001a\u00020\u0005HÆ\u0003J\t\u0010!\u001a\u00020\bHÆ\u0003J\t\u0010\"\u001a\u00020\nHÆ\u0003J;\u0010#\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\nHÆ\u0001J\u0013\u0010$\u001a\u00020\n2\b\u0010%\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010&\u001a\u00020\bHÖ\u0001J\t\u0010'\u001a\u00020\u0005HÖ\u0001R\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0006\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0015\"\u0004\b\u0019\u0010\u0017R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001d¨\u0006("},
   d2 = {"Lio/legado/app/data/entities/TxtTocRule;", "", "id", "", "name", "", "rule", "serialNumber", "", "enable", "", "(JLjava/lang/String;Ljava/lang/String;IZ)V", "getEnable", "()Z", "setEnable", "(Z)V", "getId", "()J", "setId", "(J)V", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "getRule", "setRule", "getSerialNumber", "()I", "setSerialNumber", "(I)V", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "toString", "reader-pro"}
)
public final class TxtTocRule {
   private long id;
   @NotNull
   private String name;
   @NotNull
   private String rule;
   private int serialNumber;
   private boolean enable;

   public TxtTocRule(long id, @NotNull String name, @NotNull String rule, int serialNumber, boolean enable) {
      Intrinsics.checkNotNullParameter(name, "name");
      Intrinsics.checkNotNullParameter(rule, "rule");
      super();
      this.id = id;
      this.name = name;
      this.rule = rule;
      this.serialNumber = serialNumber;
      this.enable = enable;
   }

   // $FF: synthetic method
   public TxtTocRule(long var1, String var3, String var4, int var5, boolean var6, int var7, DefaultConstructorMarker var8) {
      if ((var7 & 1) != 0) {
         var1 = System.currentTimeMillis();
      }

      if ((var7 & 2) != 0) {
         var3 = "";
      }

      if ((var7 & 4) != 0) {
         var4 = "";
      }

      if ((var7 & 8) != 0) {
         var5 = -1;
      }

      if ((var7 & 16) != 0) {
         var6 = true;
      }

      this(var1, var3, var4, var5, var6);
   }

   public final long getId() {
      return this.id;
   }

   public final void setId(long <set-?>) {
      this.id = var1;
   }

   @NotNull
   public final String getName() {
      return this.name;
   }

   public final void setName(@NotNull String <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.name = var1;
   }

   @NotNull
   public final String getRule() {
      return this.rule;
   }

   public final void setRule(@NotNull String <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.rule = var1;
   }

   public final int getSerialNumber() {
      return this.serialNumber;
   }

   public final void setSerialNumber(int <set-?>) {
      this.serialNumber = var1;
   }

   public final boolean getEnable() {
      return this.enable;
   }

   public final void setEnable(boolean <set-?>) {
      this.enable = var1;
   }

   public final long component1() {
      return this.id;
   }

   @NotNull
   public final String component2() {
      return this.name;
   }

   @NotNull
   public final String component3() {
      return this.rule;
   }

   public final int component4() {
      return this.serialNumber;
   }

   public final boolean component5() {
      return this.enable;
   }

   @NotNull
   public final TxtTocRule copy(long id, @NotNull String name, @NotNull String rule, int serialNumber, boolean enable) {
      Intrinsics.checkNotNullParameter(name, "name");
      Intrinsics.checkNotNullParameter(rule, "rule");
      return new TxtTocRule(id, name, rule, serialNumber, enable);
   }

   // $FF: synthetic method
   public static TxtTocRule copy$default(TxtTocRule var0, long var1, String var3, String var4, int var5, boolean var6, int var7, Object var8) {
      if ((var7 & 1) != 0) {
         var1 = var0.id;
      }

      if ((var7 & 2) != 0) {
         var3 = var0.name;
      }

      if ((var7 & 4) != 0) {
         var4 = var0.rule;
      }

      if ((var7 & 8) != 0) {
         var5 = var0.serialNumber;
      }

      if ((var7 & 16) != 0) {
         var6 = var0.enable;
      }

      return var0.copy(var1, var3, var4, var5, var6);
   }

   @NotNull
   public String toString() {
      return "TxtTocRule(id=" + this.id + ", name=" + this.name + ", rule=" + this.rule + ", serialNumber=" + this.serialNumber + ", enable=" + this.enable + ')';
   }

   public int hashCode() {
      int result = Long.hashCode(this.id);
      result = result * 31 + this.name.hashCode();
      result = result * 31 + this.rule.hashCode();
      result = result * 31 + Integer.hashCode(this.serialNumber);
      int var10000 = result * 31;
      byte var10001 = this.enable;
      if (var10001 != 0) {
         var10001 = 1;
      }

      result = var10000 + var10001;
      return result;
   }

   public boolean equals(@Nullable Object other) {
      if (this == other) {
         return true;
      } else if (!(other instanceof TxtTocRule)) {
         return false;
      } else {
         TxtTocRule var2 = (TxtTocRule)other;
         if (this.id != var2.id) {
            return false;
         } else if (!Intrinsics.areEqual(this.name, var2.name)) {
            return false;
         } else if (!Intrinsics.areEqual(this.rule, var2.rule)) {
            return false;
         } else if (this.serialNumber != var2.serialNumber) {
            return false;
         } else {
            return this.enable == var2.enable;
         }
      }
   }

   public TxtTocRule() {
      this(0L, (String)null, (String)null, 0, false, 31, (DefaultConstructorMarker)null);
   }
}
