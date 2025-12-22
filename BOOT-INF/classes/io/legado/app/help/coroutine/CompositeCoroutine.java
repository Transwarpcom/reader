package io.legado.app.help.coroutine;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 5, 1},
   k = 1,
   xi = 48,
   d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001c\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B#\b\u0016\u0012\u001a\u0010\u0003\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u00050\u0004\"\u0006\u0012\u0002\b\u00030\u0005¢\u0006\u0002\u0010\u0006B\u0019\b\u0016\u0012\u0010\u0010\u0003\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00050\u0007¢\u0006\u0002\u0010\bJ\u0014\u0010\u0013\u001a\u00020\n2\n\u0010\u0014\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0016J)\u0010\u0015\u001a\u00020\n2\u001a\u0010\u0003\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u00050\u0004\"\u0006\u0012\u0002\b\u00030\u0005H\u0016¢\u0006\u0002\u0010\u0016J\b\u0010\u0017\u001a\u00020\u0018H\u0016J\u0014\u0010\u0019\u001a\u00020\n2\n\u0010\u0014\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0016J\u0014\u0010\u001a\u001a\u00020\n2\n\u0010\u0014\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0016R\u0011\u0010\t\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b\t\u0010\u000bR*\u0010\f\u001a\u001e\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0005\u0018\u00010\rj\u000e\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0005\u0018\u0001`\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u000f\u001a\u00020\u00108F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001b"},
   d2 = {"Lio/legado/app/help/coroutine/CompositeCoroutine;", "Lio/legado/app/help/coroutine/CoroutineContainer;", "()V", "coroutines", "", "Lio/legado/app/help/coroutine/Coroutine;", "([Lio/legado/app/help/coroutine/Coroutine;)V", "", "(Ljava/lang/Iterable;)V", "isEmpty", "", "()Z", "resources", "Ljava/util/HashSet;", "Lkotlin/collections/HashSet;", "size", "", "getSize", "()I", "add", "coroutine", "addAll", "([Lio/legado/app/help/coroutine/Coroutine;)Z", "clear", "", "delete", "remove", "reader-pro"}
)
public final class CompositeCoroutine implements CoroutineContainer {
   @Nullable
   private HashSet<Coroutine<?>> resources;

   public final int getSize() {
      HashSet var1 = this.resources;
      int var10000;
      if (var1 == null) {
         var10000 = 0;
      } else {
         int var2 = var1.size();
         var10000 = var2;
      }

      return var10000;
   }

   public final boolean isEmpty() {
      return this.getSize() == 0;
   }

   public CompositeCoroutine() {
   }

   public CompositeCoroutine(@NotNull Coroutine<?>... coroutines) {
      Intrinsics.checkNotNullParameter(coroutines, "coroutines");
      super();
      this.resources = SetsKt.hashSetOf(Arrays.copyOf(coroutines, coroutines.length));
   }

   public CompositeCoroutine(@NotNull Iterable<? extends Coroutine<?>> coroutines) {
      Intrinsics.checkNotNullParameter(coroutines, "coroutines");
      super();
      boolean var2 = false;
      this.resources = new HashSet();
      Iterator var5 = coroutines.iterator();

      while(var5.hasNext()) {
         Coroutine d = (Coroutine)var5.next();
         HashSet var4 = this.resources;
         if (var4 != null) {
            var4.add(d);
         }
      }

   }

   public boolean add(@NotNull Coroutine<?> coroutine) {
      // $FF: Couldn't be decompiled
   }

   public boolean addAll(@NotNull Coroutine<?>... coroutines) {
      Intrinsics.checkNotNullParameter(coroutines, "coroutines");
      boolean var2 = false;
      boolean var3 = false;
      synchronized(this){}

      boolean var11;
      try {
         int var4 = false;
         HashSet set = this.resources;
         if (this.resources == null) {
            boolean var6 = false;
            set = new HashSet();
            this.resources = set;
         }

         Coroutine[] var15 = coroutines;
         int var7 = 0;
         int var8 = coroutines.length;

         boolean add;
         do {
            if (var7 >= var8) {
               Unit var14 = Unit.INSTANCE;
               return true;
            }

            Coroutine coroutine = var15[var7];
            ++var7;
            Intrinsics.checkNotNull(set);
            add = set.add(coroutine);
         } while(add);

         var11 = false;
      } finally {
         ;
      }

      return var11;
   }

   public boolean remove(@NotNull Coroutine<?> coroutine) {
      Intrinsics.checkNotNullParameter(coroutine, "coroutine");
      if (this.delete(coroutine)) {
         Coroutine.cancel$default(coroutine, (CancellationException)null, 1, (Object)null);
         return true;
      } else {
         return false;
      }
   }

   public boolean delete(@NotNull Coroutine<?> coroutine) {
      Intrinsics.checkNotNullParameter(coroutine, "coroutine");
      boolean var2 = false;
      boolean var3 = false;
      synchronized(this){}

      boolean var6;
      try {
         int var4 = false;
         HashSet set = this.resources;
         if (set != null && set.remove(coroutine)) {
            Unit var9 = Unit.INSTANCE;
            return true;
         }

         var6 = false;
      } finally {
         ;
      }

      return var6;
   }

   public void clear() {
      Object set = null;
      boolean var2 = false;
      boolean var3 = false;
      boolean $i$f$forEachIndexed;
      synchronized(this) {
         $i$f$forEachIndexed = false;
         set = this.resources;
         this.resources = null;
         Unit var14 = Unit.INSTANCE;
      }

      if (set != null) {
         Iterable $this$forEachIndexed$iv = (Iterable)set;
         $i$f$forEachIndexed = false;
         int index$iv = 0;
         Iterator var6 = $this$forEachIndexed$iv.iterator();

         while(var6.hasNext()) {
            Object item$iv = var6.next();
            int var8 = index$iv++;
            boolean var9 = false;
            if (var8 < 0) {
               CollectionsKt.throwIndexOverflow();
            }

            Coroutine coroutine = (Coroutine)item$iv;
            int var12 = false;
            Coroutine.cancel$default(coroutine, (CancellationException)null, 1, (Object)null);
         }
      }

   }
}
