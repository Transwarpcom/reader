package com.htmake.reader;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import mu.KLogger;
import mu.KotlinLogging;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.SpringApplication;

@Metadata(
   mv = {1, 5, 1},
   k = 2,
   xi = 48,
   d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\u0019\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\b"},
   d2 = {"logger", "Lmu/KLogger;", "main", "", "args", "", "", "([Ljava/lang/String;)V", "reader-pro"}
)
public final class ReaderApplicationKt {
   @NotNull
   private static final KLogger logger;

   public static final void main(@NotNull String[] args) {
      Intrinsics.checkNotNullParameter(args, "args");
      logger.info("Starting application with args: {}", args);
      SpringApplication.run(ReaderApplication.class, (String[])Arrays.copyOf(args, args.length));
   }

   static {
      logger = KotlinLogging.INSTANCE.logger((Function0)null.INSTANCE);
   }
}
