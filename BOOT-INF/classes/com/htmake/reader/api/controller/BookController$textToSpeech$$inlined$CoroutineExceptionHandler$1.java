package com.htmake.reader.api.controller;

import io.vertx.core.http.HttpServerResponse;
import kotlin.Metadata;
import kotlin.coroutines.AbstractCoroutineContextElement;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineExceptionHandler;
import kotlinx.coroutines.CoroutineExceptionHandler.Key;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 5, 1},
   k = 1,
   xi = 48,
   d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u00012\u00020\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t¸\u0006\u0000"},
   d2 = {"kotlinx/coroutines/CoroutineExceptionHandlerKt$CoroutineExceptionHandler$1", "Lkotlin/coroutines/AbstractCoroutineContextElement;", "Lkotlinx/coroutines/CoroutineExceptionHandler;", "handleException", "", "context", "Lkotlin/coroutines/CoroutineContext;", "exception", "", "kotlinx-coroutines-core"}
)
public final class BookController$textToSpeech$$inlined$CoroutineExceptionHandler$1 extends AbstractCoroutineContextElement implements CoroutineExceptionHandler {
   // $FF: synthetic field
   final HttpServerResponse $response$inlined;

   public BookController$textToSpeech$$inlined$CoroutineExceptionHandler$1(Key $super_call_param$1, HttpServerResponse var2) {
      super((kotlin.coroutines.CoroutineContext.Key)$super_call_param$1);
      this.$response$inlined = var2;
   }

   public void handleException(@NotNull CoroutineContext context, @NotNull Throwable exception) {
      int var5 = false;
      BookControllerKt.access$getLogger$p().info("tts error: {}", exception.getMessage());
      this.$response$inlined.setStatusCode(404).end();
   }
}
