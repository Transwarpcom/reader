package io.legado.app.data.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 5, 1},
   k = 1,
   xi = 48,
   d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b1\b\u0086\b\u0018\u00002\u00020\u0001B\u0081\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\u0005\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\u000b\u0012\b\b\u0002\u0010\r\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0011¢\u0006\u0002\u0010\u0012J\t\u00101\u001a\u00020\u0003HÆ\u0003J\t\u00102\u001a\u00020\u000bHÆ\u0003J\t\u00103\u001a\u00020\u0003HÆ\u0003J\t\u00104\u001a\u00020\u0011HÆ\u0003J\t\u00105\u001a\u00020\u0005HÆ\u0003J\u000b\u00106\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u00107\u001a\u00020\u0005HÆ\u0003J\t\u00108\u001a\u00020\u0005HÆ\u0003J\u000b\u00109\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010:\u001a\u00020\u000bHÆ\u0003J\t\u0010;\u001a\u00020\u000bHÆ\u0003J\t\u0010<\u001a\u00020\u000bHÆ\u0003J\u0085\u0001\u0010=\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\u000b2\b\b\u0002\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u0011HÆ\u0001J\u0013\u0010>\u001a\u00020\u000b2\b\u0010?\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010@\u001a\u00020\u0011HÖ\u0001J\t\u0010A\u001a\u00020\u0005HÖ\u0001R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001c\u0010\r\u001a\u00020\u000b8\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001c\u0010\u000e\u001a\u00020\u000b8\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u001b\"\u0004\b\u001e\u0010\u001dR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0014\"\u0004\b \u0010\u0016R\u001a\u0010\u0010\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001a\u0010\u0007\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0014\"\u0004\b&\u0010\u0016R\u001a\u0010\b\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\u0014\"\u0004\b(\u0010\u0016R\u001c\u0010\t\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\u0014\"\u0004\b*\u0010\u0016R\u001a\u0010\f\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010\u001b\"\u0004\b,\u0010\u001dR\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010\u001b\"\u0004\b.\u0010\u001dR\u001a\u0010\u000f\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010\u0018\"\u0004\b0\u0010\u001a¨\u0006B"},
   d2 = {"Lio/legado/app/data/entities/ReplaceRule;", "", "id", "", "name", "", "group", "pattern", "replacement", "scope", "scopeTitle", "", "scopeContent", "isEnabled", "isRegex", "timeoutMillisecond", "order", "", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZZZJI)V", "getGroup", "()Ljava/lang/String;", "setGroup", "(Ljava/lang/String;)V", "getId", "()J", "setId", "(J)V", "()Z", "setEnabled", "(Z)V", "setRegex", "getName", "setName", "getOrder", "()I", "setOrder", "(I)V", "getPattern", "setPattern", "getReplacement", "setReplacement", "getScope", "setScope", "getScopeContent", "setScopeContent", "getScopeTitle", "setScopeTitle", "getTimeoutMillisecond", "setTimeoutMillisecond", "component1", "component10", "component11", "component12", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toString", "reader-pro"}
)
public final class ReplaceRule {
   private long id;
   @NotNull
   private String name;
   @Nullable
   private String group;
   @NotNull
   private String pattern;
   @NotNull
   private String replacement;
   @Nullable
   private String scope;
   private boolean scopeTitle;
   private boolean scopeContent;
   private boolean isEnabled;
   private boolean isRegex;
   private long timeoutMillisecond;
   private int order;

   public ReplaceRule(long id, @NotNull String name, @Nullable String group, @NotNull String pattern, @NotNull String replacement, @Nullable String scope, boolean scopeTitle, boolean scopeContent, boolean isEnabled, boolean isRegex, long timeoutMillisecond, int order) {
      Intrinsics.checkNotNullParameter(name, "name");
      Intrinsics.checkNotNullParameter(pattern, "pattern");
      Intrinsics.checkNotNullParameter(replacement, "replacement");
      super();
      this.id = id;
      this.name = name;
      this.group = group;
      this.pattern = pattern;
      this.replacement = replacement;
      this.scope = scope;
      this.scopeTitle = scopeTitle;
      this.scopeContent = scopeContent;
      this.isEnabled = isEnabled;
      this.isRegex = isRegex;
      this.timeoutMillisecond = timeoutMillisecond;
      this.order = order;
   }

   // $FF: synthetic method
   public ReplaceRule(long var1, String var3, String var4, String var5, String var6, String var7, boolean var8, boolean var9, boolean var10, boolean var11, long var12, int var14, int var15, DefaultConstructorMarker var16) {
      if ((var15 & 1) != 0) {
         var1 = System.currentTimeMillis();
      }

      if ((var15 & 2) != 0) {
         var3 = "";
      }

      if ((var15 & 4) != 0) {
         var4 = null;
      }

      if ((var15 & 8) != 0) {
         var5 = "";
      }

      if ((var15 & 16) != 0) {
         var6 = "";
      }

      if ((var15 & 32) != 0) {
         var7 = null;
      }

      if ((var15 & 64) != 0) {
         var8 = false;
      }

      if ((var15 & 128) != 0) {
         var9 = true;
      }

      if ((var15 & 256) != 0) {
         var10 = true;
      }

      if ((var15 & 512) != 0) {
         var11 = false;
      }

      if ((var15 & 1024) != 0) {
         var12 = 3000L;
      }

      if ((var15 & 2048) != 0) {
         var14 = 0;
      }

      this(var1, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var14);
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

   @Nullable
   public final String getGroup() {
      return this.group;
   }

   public final void setGroup(@Nullable String <set-?>) {
      this.group = var1;
   }

   @NotNull
   public final String getPattern() {
      return this.pattern;
   }

   public final void setPattern(@NotNull String <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.pattern = var1;
   }

   @NotNull
   public final String getReplacement() {
      return this.replacement;
   }

   public final void setReplacement(@NotNull String <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.replacement = var1;
   }

   @Nullable
   public final String getScope() {
      return this.scope;
   }

   public final void setScope(@Nullable String <set-?>) {
      this.scope = var1;
   }

   public final boolean getScopeTitle() {
      return this.scopeTitle;
   }

   public final void setScopeTitle(boolean <set-?>) {
      this.scopeTitle = var1;
   }

   public final boolean getScopeContent() {
      return this.scopeContent;
   }

   public final void setScopeContent(boolean <set-?>) {
      this.scopeContent = var1;
   }

   @JsonProperty("isEnabled")
   public final boolean isEnabled() {
      return this.isEnabled;
   }

   public final void setEnabled(boolean <set-?>) {
      this.isEnabled = var1;
   }

   @JsonProperty("isRegex")
   public final boolean isRegex() {
      return this.isRegex;
   }

   public final void setRegex(boolean <set-?>) {
      this.isRegex = var1;
   }

   public final long getTimeoutMillisecond() {
      return this.timeoutMillisecond;
   }

   public final void setTimeoutMillisecond(long <set-?>) {
      this.timeoutMillisecond = var1;
   }

   public final int getOrder() {
      return this.order;
   }

   public final void setOrder(int <set-?>) {
      this.order = var1;
   }

   public final long component1() {
      return this.id;
   }

   @NotNull
   public final String component2() {
      return this.name;
   }

   @Nullable
   public final String component3() {
      return this.group;
   }

   @NotNull
   public final String component4() {
      return this.pattern;
   }

   @NotNull
   public final String component5() {
      return this.replacement;
   }

   @Nullable
   public final String component6() {
      return this.scope;
   }

   public final boolean component7() {
      return this.scopeTitle;
   }

   public final boolean component8() {
      return this.scopeContent;
   }

   public final boolean component9() {
      return this.isEnabled;
   }

   public final boolean component10() {
      return this.isRegex;
   }

   public final long component11() {
      return this.timeoutMillisecond;
   }

   public final int component12() {
      return this.order;
   }

   @NotNull
   public final ReplaceRule copy(long id, @NotNull String name, @Nullable String group, @NotNull String pattern, @NotNull String replacement, @Nullable String scope, boolean scopeTitle, boolean scopeContent, boolean isEnabled, boolean isRegex, long timeoutMillisecond, int order) {
      Intrinsics.checkNotNullParameter(name, "name");
      Intrinsics.checkNotNullParameter(pattern, "pattern");
      Intrinsics.checkNotNullParameter(replacement, "replacement");
      return new ReplaceRule(id, name, group, pattern, replacement, scope, scopeTitle, scopeContent, isEnabled, isRegex, timeoutMillisecond, order);
   }

   // $FF: synthetic method
   public static ReplaceRule copy$default(ReplaceRule var0, long var1, String var3, String var4, String var5, String var6, String var7, boolean var8, boolean var9, boolean var10, boolean var11, long var12, int var14, int var15, Object var16) {
      if ((var15 & 1) != 0) {
         var1 = var0.id;
      }

      if ((var15 & 2) != 0) {
         var3 = var0.name;
      }

      if ((var15 & 4) != 0) {
         var4 = var0.group;
      }

      if ((var15 & 8) != 0) {
         var5 = var0.pattern;
      }

      if ((var15 & 16) != 0) {
         var6 = var0.replacement;
      }

      if ((var15 & 32) != 0) {
         var7 = var0.scope;
      }

      if ((var15 & 64) != 0) {
         var8 = var0.scopeTitle;
      }

      if ((var15 & 128) != 0) {
         var9 = var0.scopeContent;
      }

      if ((var15 & 256) != 0) {
         var10 = var0.isEnabled;
      }

      if ((var15 & 512) != 0) {
         var11 = var0.isRegex;
      }

      if ((var15 & 1024) != 0) {
         var12 = var0.timeoutMillisecond;
      }

      if ((var15 & 2048) != 0) {
         var14 = var0.order;
      }

      return var0.copy(var1, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var14);
   }

   @NotNull
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append("ReplaceRule(id=").append(this.id).append(", name=").append(this.name).append(", group=").append(this.group).append(", pattern=").append(this.pattern).append(", replacement=").append(this.replacement).append(", scope=").append(this.scope).append(", scopeTitle=").append(this.scopeTitle).append(", scopeContent=").append(this.scopeContent).append(", isEnabled=").append(this.isEnabled).append(", isRegex=").append(this.isRegex).append(", timeoutMillisecond=").append(this.timeoutMillisecond).append(", order=");
      var1.append(this.order).append(')');
      return var1.toString();
   }

   public int hashCode() {
      int result = Long.hashCode(this.id);
      result = result * 31 + this.name.hashCode();
      result = result * 31 + (this.group == null ? 0 : this.group.hashCode());
      result = result * 31 + this.pattern.hashCode();
      result = result * 31 + this.replacement.hashCode();
      result = result * 31 + (this.scope == null ? 0 : this.scope.hashCode());
      int var10000 = result * 31;
      byte var10001 = this.scopeTitle;
      if (var10001 != 0) {
         var10001 = 1;
      }

      result = var10000 + var10001;
      var10000 = result * 31;
      var10001 = this.scopeContent;
      if (var10001 != 0) {
         var10001 = 1;
      }

      result = var10000 + var10001;
      var10000 = result * 31;
      var10001 = this.isEnabled;
      if (var10001 != 0) {
         var10001 = 1;
      }

      result = var10000 + var10001;
      var10000 = result * 31;
      var10001 = this.isRegex;
      if (var10001 != 0) {
         var10001 = 1;
      }

      result = var10000 + var10001;
      result = result * 31 + Long.hashCode(this.timeoutMillisecond);
      result = result * 31 + Integer.hashCode(this.order);
      return result;
   }

   public boolean equals(@Nullable Object other) {
      if (this == other) {
         return true;
      } else if (!(other instanceof ReplaceRule)) {
         return false;
      } else {
         ReplaceRule var2 = (ReplaceRule)other;
         if (this.id != var2.id) {
            return false;
         } else if (!Intrinsics.areEqual(this.name, var2.name)) {
            return false;
         } else if (!Intrinsics.areEqual(this.group, var2.group)) {
            return false;
         } else if (!Intrinsics.areEqual(this.pattern, var2.pattern)) {
            return false;
         } else if (!Intrinsics.areEqual(this.replacement, var2.replacement)) {
            return false;
         } else if (!Intrinsics.areEqual(this.scope, var2.scope)) {
            return false;
         } else if (this.scopeTitle != var2.scopeTitle) {
            return false;
         } else if (this.scopeContent != var2.scopeContent) {
            return false;
         } else if (this.isEnabled != var2.isEnabled) {
            return false;
         } else if (this.isRegex != var2.isRegex) {
            return false;
         } else if (this.timeoutMillisecond != var2.timeoutMillisecond) {
            return false;
         } else {
            return this.order == var2.order;
         }
      }
   }

   public ReplaceRule() {
      this(0L, (String)null, (String)null, (String)null, (String)null, (String)null, false, false, false, false, 0L, 0, 4095, (DefaultConstructorMarker)null);
   }
}
