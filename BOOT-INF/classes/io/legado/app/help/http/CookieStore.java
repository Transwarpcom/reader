package io.legado.app.help.http;

import io.legado.app.adapters.ReaderAdapterHelper;
import io.legado.app.adapters.ReaderAdapterInterface;
import io.legado.app.help.http.api.CookieManager;
import io.legado.app.utils.ACache;
import io.legado.app.utils.NetworkUtils;
import io.legado.app.utils.TextUtils;
import java.io.File;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 5, 1},
   k = 1,
   xi = 48,
   d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010%\n\u0002\b\u0007\n\u0002\u0010$\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u000b\u001a\u00020\fJ\u001c\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000e2\u0006\u0010\u000f\u001a\u00020\u0003H\u0016J\u0010\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0003H\u0016J\u0016\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u0003J \u0010\u0014\u001a\u0004\u0018\u00010\u00032\u0014\u0010\u0015\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0016H\u0016J\u0010\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u0003H\u0016J\u0018\u0010\u0018\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0003H\u0016J\u001a\u0010\u0019\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u00032\b\u0010\u000f\u001a\u0004\u0018\u00010\u0003H\u0016R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u001a"},
   d2 = {"Lio/legado/app/help/http/CookieStore;", "Lio/legado/app/help/http/api/CookieManager;", "userNameSpace", "", "(Ljava/lang/String;)V", "cacheInstance", "Lio/legado/app/utils/ACache;", "getCacheInstance", "()Lio/legado/app/utils/ACache;", "getUserNameSpace", "()Ljava/lang/String;", "clear", "", "cookieToMap", "", "cookie", "getCookie", "url", "getKey", "key", "mapToCookie", "cookieMap", "", "removeCookie", "replaceCookie", "setCookie", "reader-pro"}
)
public final class CookieStore implements CookieManager {
   @NotNull
   private final String userNameSpace;
   @NotNull
   private final ACache cacheInstance;

   public CookieStore(@NotNull String userNameSpace) {
      Intrinsics.checkNotNullParameter(userNameSpace, "userNameSpace");
      super();
      this.userNameSpace = userNameSpace;
      ReaderAdapterInterface var10002 = ReaderAdapterHelper.INSTANCE.getAdapter();
      String[] var3 = new String[]{"storage", "cache", "cookie", this.userNameSpace};
      File cacheDir = new File(var10002.getWorkDir(var3));
      this.cacheInstance = ACache.Companion.get(cacheDir, 50000000L, 1000000);
   }

   @NotNull
   public final String getUserNameSpace() {
      return this.userNameSpace;
   }

   @NotNull
   public final ACache getCacheInstance() {
      return this.cacheInstance;
   }

   public void setCookie(@NotNull String url, @Nullable String cookie) {
      Intrinsics.checkNotNullParameter(url, "url");
      String domain = NetworkUtils.INSTANCE.getSubDomain(url);
      CharSequence var4 = (CharSequence)domain;
      boolean var5 = false;
      if (var4.length() > 0) {
         this.cacheInstance.put(domain, cookie == null ? "" : cookie);
      }

   }

   public void replaceCookie(@NotNull String url, @NotNull String cookie) {
      Intrinsics.checkNotNullParameter(url, "url");
      Intrinsics.checkNotNullParameter(cookie, "cookie");
      if (!TextUtils.isEmpty((CharSequence)url) && !TextUtils.isEmpty((CharSequence)cookie)) {
         String oldCookie = this.getCookie(url);
         if (TextUtils.isEmpty((CharSequence)oldCookie)) {
            this.setCookie(url, cookie);
         } else {
            Map cookieMap = this.cookieToMap(oldCookie);
            cookieMap.putAll(this.cookieToMap(cookie));
            String newCookie = this.mapToCookie(cookieMap);
            this.setCookie(url, newCookie);
         }

      }
   }

   @NotNull
   public String getCookie(@NotNull String url) {
      Intrinsics.checkNotNullParameter(url, "url");
      String domain = NetworkUtils.INSTANCE.getSubDomain(url);
      CharSequence var3 = (CharSequence)domain;
      boolean var4 = false;
      if (var3.length() == 0) {
         return "";
      } else {
         String var5 = this.cacheInstance.getAsString(domain);
         return var5 == null ? "" : var5;
      }
   }

   @NotNull
   public final String getKey(@NotNull String url, @NotNull String key) {
      Intrinsics.checkNotNullParameter(url, "url");
      Intrinsics.checkNotNullParameter(key, "key");
      String cookie = this.getCookie(url);
      Map cookieMap = this.cookieToMap(cookie);
      String var5 = (String)cookieMap.get(key);
      return var5 == null ? "" : var5;
   }

   public void removeCookie(@NotNull String url) {
      Intrinsics.checkNotNullParameter(url, "url");
      String domain = NetworkUtils.INSTANCE.getSubDomain(url);
      CharSequence var3 = (CharSequence)domain;
      boolean var4 = false;
      if (var3.length() != 0) {
         this.cacheInstance.remove(domain);
      }
   }

   @NotNull
   public Map<String, String> cookieToMap(@NotNull String cookie) {
      Intrinsics.checkNotNullParameter(cookie, "cookie");
      boolean var3 = false;
      Map cookieMap = (Map)(new LinkedHashMap());
      if (StringsKt.isBlank((CharSequence)cookie)) {
         return cookieMap;
      } else {
         CharSequence var9;
         boolean $i$f$toTypedArray;
         List var10000;
         boolean $i$f$toTypedArray;
         String pair;
         label175: {
            CharSequence var4 = (CharSequence)cookie;
            String var5 = ";";
            boolean var6 = false;
            Regex var26 = new Regex(var5);
            byte var28 = 0;
            boolean var7 = false;
            List $this$dropLastWhile$iv = var26.split(var4, var28);
            $i$f$toTypedArray = false;
            if (!$this$dropLastWhile$iv.isEmpty()) {
               ListIterator iterator$iv = $this$dropLastWhile$iv.listIterator($this$dropLastWhile$iv.size());

               while(iterator$iv.hasPrevious()) {
                  pair = (String)iterator$iv.previous();
                  int var8 = false;
                  var9 = (CharSequence)pair;
                  $i$f$toTypedArray = false;
                  if (var9.length() != 0) {
                     var10000 = CollectionsKt.take((Iterable)$this$dropLastWhile$iv, iterator$iv.nextIndex() + 1);
                     break label175;
                  }
               }
            }

            var10000 = CollectionsKt.emptyList();
         }

         Collection $this$toTypedArray$iv = (Collection)var10000;
         $i$f$toTypedArray = false;
         Object[] var49 = $this$toTypedArray$iv.toArray(new String[0]);
         if (var49 == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
         } else {
            String[] pairArray = (String[])var49;
            String[] var25 = pairArray;
            int var29 = 0;
            int var31 = pairArray.length;

            while(true) {
               boolean $i$f$trim;
               CharSequence $this$trim$iv$iv;
               boolean $i$f$trim;
               int endIndex$iv$iv;
               boolean match$iv$iv;
               String value;
               String key;
               String it;
               CharSequence $this$trim$iv$iv;
               int startIndex$iv$iv;
               int endIndex$iv$iv;
               boolean startFound$iv$iv;
               do {
                  boolean $i$f$trim;
                  boolean $i$f$trim;
                  String[] pairs;
                  do {
                     if (var29 >= var31) {
                        return cookieMap;
                     }

                     label118: {
                        pair = var25[var29];
                        ++var29;
                        var9 = (CharSequence)pair;
                        value = "=";
                        $i$f$trim = false;
                        Regex var38 = new Regex(value);
                        byte var39 = 0;
                        $i$f$trim = false;
                        List $this$dropLastWhile$iv = var38.split(var9, var39);
                        $i$f$toTypedArray = false;
                        if (!$this$dropLastWhile$iv.isEmpty()) {
                           ListIterator iterator$iv = $this$dropLastWhile$iv.listIterator($this$dropLastWhile$iv.size());

                           while(iterator$iv.hasPrevious()) {
                              it = (String)iterator$iv.previous();
                              $i$f$trim = false;
                              $this$trim$iv$iv = (CharSequence)it;
                              $i$f$trim = false;
                              if ($this$trim$iv$iv.length() != 0) {
                                 var10000 = CollectionsKt.take((Iterable)$this$dropLastWhile$iv, iterator$iv.nextIndex() + 1);
                                 break label118;
                              }
                           }
                        }

                        var10000 = CollectionsKt.emptyList();
                     }

                     Collection $this$toTypedArray$iv = (Collection)var10000;
                     $i$f$toTypedArray = false;
                     var49 = $this$toTypedArray$iv.toArray(new String[0]);
                     if (var49 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
                     }

                     pairs = (String[])var49;
                  } while(pairs.length == 1);

                  value = pairs[0];
                  $i$f$trim = false;
                  CharSequence $this$trim$iv$iv = (CharSequence)value;
                  $i$f$trim = false;
                  int startIndex$iv$iv = 0;
                  startIndex$iv$iv = $this$trim$iv$iv.length() - 1;
                  boolean startFound$iv$iv = false;

                  boolean match$iv$iv;
                  while(startIndex$iv$iv <= startIndex$iv$iv) {
                     endIndex$iv$iv = !startFound$iv$iv ? startIndex$iv$iv : startIndex$iv$iv;
                     char it = $this$trim$iv$iv.charAt(endIndex$iv$iv);
                     match$iv$iv = false;
                     startFound$iv$iv = Intrinsics.compare(it, 32) <= 0;
                     if (!startFound$iv$iv) {
                        if (!startFound$iv$iv) {
                           startFound$iv$iv = true;
                        } else {
                           ++startIndex$iv$iv;
                        }
                     } else {
                        if (!startFound$iv$iv) {
                           break;
                        }

                        --startIndex$iv$iv;
                     }
                  }

                  key = $this$trim$iv$iv.subSequence(startIndex$iv$iv, startIndex$iv$iv + 1).toString();
                  value = pairs[1];
                  CharSequence var42 = (CharSequence)value;
                  $i$f$trim = false;
                  if (!StringsKt.isBlank(var42)) {
                     break;
                  }

                  $i$f$trim = false;
                  $this$trim$iv$iv = (CharSequence)value;
                  int $i$f$trim = false;
                  startIndex$iv$iv = 0;
                  endIndex$iv$iv = $this$trim$iv$iv.length() - 1;
                  boolean startFound$iv$iv = false;

                  while(startIndex$iv$iv <= endIndex$iv$iv) {
                     int index$iv$iv = !startFound$iv$iv ? startIndex$iv$iv : endIndex$iv$iv;
                     char it = $this$trim$iv$iv.charAt(index$iv$iv);
                     match$iv$iv = false;
                     match$iv$iv = Intrinsics.compare(it, 32) <= 0;
                     if (!startFound$iv$iv) {
                        if (!match$iv$iv) {
                           startFound$iv$iv = true;
                        } else {
                           ++startIndex$iv$iv;
                        }
                     } else {
                        if (!match$iv$iv) {
                           break;
                        }

                        --endIndex$iv$iv;
                     }
                  }
               } while(!Intrinsics.areEqual($this$trim$iv$iv.subSequence(startIndex$iv$iv, endIndex$iv$iv + 1).toString(), "null"));

               $i$f$trim = false;
               $this$trim$iv$iv = (CharSequence)value;
               $i$f$trim = false;
               endIndex$iv$iv = 0;
               endIndex$iv$iv = $this$trim$iv$iv.length() - 1;
               startFound$iv$iv = false;

               while(endIndex$iv$iv <= endIndex$iv$iv) {
                  int index$iv$iv = !startFound$iv$iv ? endIndex$iv$iv : endIndex$iv$iv;
                  char it = $this$trim$iv$iv.charAt(index$iv$iv);
                  int var21 = false;
                  match$iv$iv = Intrinsics.compare(it, 32) <= 0;
                  if (!startFound$iv$iv) {
                     if (!match$iv$iv) {
                        startFound$iv$iv = true;
                     } else {
                        ++endIndex$iv$iv;
                     }
                  } else {
                     if (!match$iv$iv) {
                        break;
                     }

                     --endIndex$iv$iv;
                  }
               }

               it = $this$trim$iv$iv.subSequence(endIndex$iv$iv, endIndex$iv$iv + 1).toString();
               $i$f$trim = false;
               cookieMap.put(key, it);
            }
         }
      }
   }

   @Nullable
   public String mapToCookie(@Nullable Map<String, String> cookieMap) {
      if (cookieMap != null && !cookieMap.isEmpty()) {
         StringBuilder builder = new StringBuilder();
         Iterator var3 = cookieMap.keySet().iterator();

         while(var3.hasNext()) {
            String key = (String)var3.next();
            String value = (String)cookieMap.get(key);
            boolean var10000;
            if (value == null) {
               var10000 = false;
            } else {
               CharSequence var7 = (CharSequence)value;
               boolean var8 = false;
               var10000 = !StringsKt.isBlank(var7);
            }

            if (var10000) {
               builder.append(key).append("=").append(value).append(";");
            }
         }

         return builder.deleteCharAt(builder.lastIndexOf(";")).toString();
      } else {
         return null;
      }
   }

   public final void clear() {
      this.cacheInstance.clear();
   }
}
