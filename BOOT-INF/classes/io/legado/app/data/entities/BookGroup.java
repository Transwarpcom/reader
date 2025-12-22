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
   d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u001e\b\u0086\b\u0018\u00002\u00020\u0001B9\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0005HÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010!\u001a\u00020\bHÆ\u0003J\t\u0010\"\u001a\u00020\nHÆ\u0003J=\u0010#\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\nHÆ\u0001J\u0013\u0010$\u001a\u00020\n2\b\u0010%\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010&\u001a\u00020\bHÖ\u0001J\t\u0010'\u001a\u00020\u0005HÖ\u0001R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\r\"\u0004\b\u0015\u0010\u000fR\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001d¨\u0006("},
   d2 = {"Lio/legado/app/data/entities/BookGroup;", "", "groupId", "", "groupName", "", "cover", "order", "", "show", "", "(JLjava/lang/String;Ljava/lang/String;IZ)V", "getCover", "()Ljava/lang/String;", "setCover", "(Ljava/lang/String;)V", "getGroupId", "()J", "setGroupId", "(J)V", "getGroupName", "setGroupName", "getOrder", "()I", "setOrder", "(I)V", "getShow", "()Z", "setShow", "(Z)V", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "toString", "reader-pro"}
)
public final class BookGroup {
   private long groupId;
   @NotNull
   private String groupName;
   @Nullable
   private String cover;
   private int order;
   private boolean show;

   public BookGroup(long groupId, @NotNull String groupName, @Nullable String cover, int order, boolean show) {
      Intrinsics.checkNotNullParameter(groupName, "groupName");
      super();
      this.groupId = groupId;
      this.groupName = groupName;
      this.cover = cover;
      this.order = order;
      this.show = show;
   }

   // $FF: synthetic method
   public BookGroup(long var1, String var3, String var4, int var5, boolean var6, int var7, DefaultConstructorMarker var8) {
      if ((var7 & 1) != 0) {
         var1 = 0L;
      }

      if ((var7 & 2) != 0) {
         var3 = "";
      }

      if ((var7 & 4) != 0) {
         var4 = null;
      }

      if ((var7 & 8) != 0) {
         var5 = 0;
      }

      if ((var7 & 16) != 0) {
         var6 = true;
      }

      this(var1, var3, var4, var5, var6);
   }

   public final long getGroupId() {
      return this.groupId;
   }

   public final void setGroupId(long <set-?>) {
      this.groupId = var1;
   }

   @NotNull
   public final String getGroupName() {
      return this.groupName;
   }

   public final void setGroupName(@NotNull String <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.groupName = var1;
   }

   @Nullable
   public final String getCover() {
      return this.cover;
   }

   public final void setCover(@Nullable String <set-?>) {
      this.cover = var1;
   }

   public final int getOrder() {
      return this.order;
   }

   public final void setOrder(int <set-?>) {
      this.order = var1;
   }

   public final boolean getShow() {
      return this.show;
   }

   public final void setShow(boolean <set-?>) {
      this.show = var1;
   }

   public final long component1() {
      return this.groupId;
   }

   @NotNull
   public final String component2() {
      return this.groupName;
   }

   @Nullable
   public final String component3() {
      return this.cover;
   }

   public final int component4() {
      return this.order;
   }

   public final boolean component5() {
      return this.show;
   }

   @NotNull
   public final BookGroup copy(long groupId, @NotNull String groupName, @Nullable String cover, int order, boolean show) {
      Intrinsics.checkNotNullParameter(groupName, "groupName");
      return new BookGroup(groupId, groupName, cover, order, show);
   }

   // $FF: synthetic method
   public static BookGroup copy$default(BookGroup var0, long var1, String var3, String var4, int var5, boolean var6, int var7, Object var8) {
      if ((var7 & 1) != 0) {
         var1 = var0.groupId;
      }

      if ((var7 & 2) != 0) {
         var3 = var0.groupName;
      }

      if ((var7 & 4) != 0) {
         var4 = var0.cover;
      }

      if ((var7 & 8) != 0) {
         var5 = var0.order;
      }

      if ((var7 & 16) != 0) {
         var6 = var0.show;
      }

      return var0.copy(var1, var3, var4, var5, var6);
   }

   @NotNull
   public String toString() {
      return "BookGroup(groupId=" + this.groupId + ", groupName=" + this.groupName + ", cover=" + this.cover + ", order=" + this.order + ", show=" + this.show + ')';
   }

   public int hashCode() {
      int result = Long.hashCode(this.groupId);
      result = result * 31 + this.groupName.hashCode();
      result = result * 31 + (this.cover == null ? 0 : this.cover.hashCode());
      result = result * 31 + Integer.hashCode(this.order);
      int var10000 = result * 31;
      byte var10001 = this.show;
      if (var10001 != 0) {
         var10001 = 1;
      }

      result = var10000 + var10001;
      return result;
   }

   public boolean equals(@Nullable Object other) {
      if (this == other) {
         return true;
      } else if (!(other instanceof BookGroup)) {
         return false;
      } else {
         BookGroup var2 = (BookGroup)other;
         if (this.groupId != var2.groupId) {
            return false;
         } else if (!Intrinsics.areEqual(this.groupName, var2.groupName)) {
            return false;
         } else if (!Intrinsics.areEqual(this.cover, var2.cover)) {
            return false;
         } else if (this.order != var2.order) {
            return false;
         } else {
            return this.show == var2.show;
         }
      }
   }

   public BookGroup() {
      this(0L, (String)null, (String)null, 0, false, 31, (DefaultConstructorMarker)null);
   }
}
