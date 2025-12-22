package io.legado.app.model;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.logging.HttpLoggingInterceptor.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 5, 1},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J*\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\b\u001a\u00020\tH\u0016¨\u0006\n"},
   d2 = {"Lio/legado/app/model/DebugLog;", "Lokhttp3/logging/HttpLoggingInterceptor$Logger;", "log", "", "message", "", "sourceUrl", "msg", "isHtml", "", "reader-pro"}
)
public interface DebugLog extends Logger {
   void log(@Nullable String sourceUrl, @Nullable String msg, boolean isHtml);

   void log(@NotNull String message);

   @Metadata(
      mv = {1, 5, 1},
      k = 3,
      xi = 48
   )
   public static final class DefaultImpls {
      public static void log(@NotNull DebugLog this, @Nullable String sourceUrl, @Nullable String msg, boolean isHtml) {
         Intrinsics.checkNotNullParameter(var0, "this");
         DebugLogKt.access$getLogger$p().info("sourceUrl: {}, msg: {}", sourceUrl, msg);
      }

      // $FF: synthetic method
      public static void log$default(DebugLog var0, String var1, String var2, boolean var3, int var4, Object var5) {
         if (var5 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: log");
         } else {
            if ((var4 & 1) != 0) {
               var1 = "";
            }

            if ((var4 & 2) != 0) {
               var2 = "";
            }

            if ((var4 & 4) != 0) {
               var3 = false;
            }

            var0.log(var1, var2, var3);
         }
      }

      public static void log(@NotNull DebugLog this, @NotNull String message) {
         Intrinsics.checkNotNullParameter(var0, "this");
         Intrinsics.checkNotNullParameter(message, "message");
         DebugLogKt.access$getLogger$p().debug(message);
      }
   }
}
