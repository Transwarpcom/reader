package com.htmake.reader.utils;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.htmake.reader.config.AppConfig;
import com.htmake.reader.entity.License;
import com.htmake.reader.entity.MongoFile;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReplaceOptions;
import com.mongodb.client.result.UpdateResult;
import io.legado.app.data.entities.Book;
import io.legado.app.utils.Base64;
import io.legado.app.utils.EncoderUtils;
import io.legado.app.utils.FileUtils;
import io.legado.app.utils.MD5Utils;
import io.legado.app.utils.MapDeserializerDoubleAsIntFix;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.KeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;
import java.util.Base64.Encoder;
import java.util.Map.Entry;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;
import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SpreadBuilder;
import kotlin.random.Random;
import kotlin.ranges.IntRange;
import kotlin.reflect.KMutableProperty;
import kotlin.reflect.KProperty1;
import kotlin.reflect.KMutableProperty.Setter;
import kotlin.reflect.full.KClasses;
import kotlin.text.Charsets;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import mu.KLogger;
import mu.KotlinLogging;
import okhttp3.HttpUrl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 5, 1},
   k = 2,
   xi = 48,
   d1 = {"\u0000§\u0001\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0010\n\u0002\u0010\"\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\b\n*\u0001\u000e\u001a\u0012\u0010\"\u001a\u0004\u0018\u00010#2\b\u0010$\u001a\u0004\u0018\u00010%\u001a\u0012\u0010&\u001a\u0004\u0018\u00010'2\b\u0010$\u001a\u0004\u0018\u00010%\u001a\u0016\u0010(\u001a\u00020\u00012\u0006\u0010)\u001a\u00020\u00172\u0006\u0010*\u001a\u00020\u0017\u001a\u000e\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020\u0017\u001a\u000e\u0010.\u001a\u00020,2\u0006\u0010-\u001a\u00020\u0017\u001a\u0010\u0010/\u001a\u0004\u0018\u00010\u00172\u0006\u00100\u001a\u00020\u0017\u001a\u0010\u00101\u001a\u0004\u0018\u0001022\u0006\u00100\u001a\u00020\u0017\u001a\u000e\u00103\u001a\u00020\u00172\u0006\u00104\u001a\u00020\u0017\u001a\u0016\u00105\u001a\u00020\u00172\u0006\u00106\u001a\u00020\u00172\u0006\u00107\u001a\u00020\u0017\u001a6\u00108\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00010:092\f\u0010;\u001a\b\u0012\u0004\u0012\u00020\u0017092\u0006\u0010<\u001a\u00020\u00172\u0006\u0010=\u001a\u00020\u0017\u001a\u0010\u0010>\u001a\u0002022\b\b\u0002\u0010?\u001a\u00020\u0003\u001a\u000e\u0010@\u001a\n\u0012\u0004\u0012\u00020B\u0018\u00010A\u001a\u000e\u0010C\u001a\u00020\u00172\u0006\u0010D\u001a\u00020\u0001\u001a\u001f\u0010E\u001a\u00020\u00172\u0012\u0010F\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00170G\"\u00020\u0017¢\u0006\u0002\u0010H\u001a+\u0010I\u001a\u0004\u0018\u00010\u00172\u0012\u0010J\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00170G\"\u00020\u00172\b\b\u0002\u0010K\u001a\u00020\u0017¢\u0006\u0002\u0010L\u001a)\u0010M\u001a\u00020,2\u0012\u0010J\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00170G\"\u00020\u00172\b\b\u0002\u0010K\u001a\u00020\u0017¢\u0006\u0002\u0010N\u001a\u0006\u0010O\u001a\u00020\u0017\u001a\u0006\u0010P\u001a\u00020\u0017\u001a\u001f\u0010Q\u001a\u00020\u00172\u0012\u0010F\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00170G\"\u00020\u0017¢\u0006\u0002\u0010H\u001a\u0010\u0010Q\u001a\u00020\u00172\b\b\u0002\u0010R\u001a\u00020\u0017\u001a\u0018\u0010S\u001a\u00020\u00172\u0006\u0010$\u001a\u00020%2\b\b\u0002\u0010T\u001a\u00020\u0003\u001ar\u0010U\u001a\u0004\u0018\u00010#2\u0006\u0010V\u001a\u00020,2\u0010\b\u0002\u0010W\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010X2\u0010\b\u0002\u0010Y\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010X2\b\b\u0002\u0010Z\u001a\u00020\u00012\b\b\u0002\u0010[\u001a\u00020\u00012\u0010\b\u0002\u0010\\\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010X2\u0016\b\u0002\u0010]\u001a\u0010\u0012\u0004\u0012\u00020_\u0012\u0004\u0012\u00020\u0003\u0018\u00010^\u001a!\u0010`\u001a\u0002Ha\"\u0004\b\u0000\u0010a2\u0006\u0010b\u001a\u00020%2\u0006\u0010c\u001a\u00020\u0017¢\u0006\u0002\u0010d\u001a\u0010\u0010e\u001a\u0004\u0018\u00010\u00172\u0006\u0010f\u001a\u00020\u0017\u001a\u0016\u0010g\u001a\u00020\u00032\u0006\u0010f\u001a\u00020\u00172\u0006\u00100\u001a\u00020\u0017\u001a;\u0010h\u001a\u00020i2\u0012\u0010J\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00170G\"\u00020\u00172\u0006\u0010$\u001a\u00020%2\b\b\u0002\u0010T\u001a\u00020\u00032\b\b\u0002\u0010K\u001a\u00020\u0017¢\u0006\u0002\u0010j\u001a\u001e\u0010k\u001a\u00020\u00032\u0006\u0010l\u001a\u00020\u00172\u0006\u0010<\u001a\u00020\u00172\u0006\u0010=\u001a\u00020\u0017\u001a\u001e\u0010m\u001a\u00020i2\u0006\u0010b\u001a\u00020%2\u0006\u0010c\u001a\u00020\u00172\u0006\u0010n\u001a\u00020%\u001a\u000e\u0010o\u001a\u00020i2\u0006\u0010p\u001a\u00020\u0003\u001a\u000e\u0010q\u001a\u00020\u00032\u0006\u0010r\u001a\u00020\u0017\u001a\u001c\u0010s\u001a\u00020\u00032\f\u0010t\u001a\b\u0012\u0004\u0012\u00020,092\u0006\u0010u\u001a\u00020\u0017\u001a)\u0010v\u001a\u0010\u0012\f\u0012\n \n*\u0004\u0018\u00010%0%0w\"\u0006\b\u0000\u0010x\u0018\u0001*\b\u0012\u0004\u0012\u0002Hx0wH\u0086\b\u001a \u0010y\u001a\u0002Hz\"\u0004\b\u0000\u0010{\"\u0006\b\u0001\u0010z\u0018\u0001*\u0002H{H\u0086\b¢\u0006\u0002\u0010|\u001a%\u0010}\u001a\b\u0012\u0004\u0012\u00020,09*\u00020,2\u000e\u0010~\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010G¢\u0006\u0002\u0010\u007f\u001a\u000b\u0010\u0080\u0001\u001a\u00020i*\u00020,\u001a&\u0010\u0081\u0001\u001a\u00030\u0082\u0001*\u00030\u0082\u00012\b\u0010\u0083\u0001\u001a\u00030\u0082\u00012\r\u0010\u0084\u0001\u001a\b\u0012\u0004\u0012\u00020\u001709\u001a\u0011\u0010\u0085\u0001\u001a\b\u0012\u0004\u0012\u00020,09*\u00020,\u001a$\u0010\u0086\u0001\u001a\u000f\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020%0\u0087\u0001\"\u0004\b\u0000\u0010x*\u0002Hx¢\u0006\u0003\u0010\u0088\u0001\u001a)\u0010\u0089\u0001\u001a\u0002Hx\"\u0006\b\u0000\u0010x\u0018\u0001*\u000f\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020%0\u0087\u0001H\u0086\b¢\u0006\u0003\u0010\u008a\u0001\u001a\u0016\u0010\u008b\u0001\u001a\u00020\u0017*\u00020\u00172\t\b\u0002\u0010\u008c\u0001\u001a\u00020\u0003\u001a$\u0010\u008d\u0001\u001a\u000f\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020%0\u0087\u0001\"\u0004\b\u0000\u0010x*\u0002Hx¢\u0006\u0003\u0010\u0088\u0001\u001a\u0014\u0010\u008e\u0001\u001a\u00020\u0003*\u00020,2\u0007\u0010\u008f\u0001\u001a\u00020\u0017\u001a\u000b\u0010\u0090\u0001\u001a\u00020\u0017*\u00020\u0017\u001a\u0012\u0010s\u001a\u00020\u0003*\u00020,2\u0006\u0010u\u001a\u00020\u0017\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007\"\u0019\u0010\b\u001a\n \n*\u0004\u0018\u00010\t0\t¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0010\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000f\"\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0019\u0010\u0014\u001a\n \n*\u0004\u0018\u00010\t0\t¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\f\"\u001a\u0010\u0016\u001a\u00020\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001b\"\u001a\u0010\u001c\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0005\"\u0004\b\u001e\u0010\u0007\"\u001a\u0010\u001f\u001a\u00020\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0019\"\u0004\b!\u0010\u001b¨\u0006\u0091\u0001"},
   d2 = {"MAX_CACHE_SIZE", "", "_licenseValid", "", "get_licenseValid", "()Z", "set_licenseValid", "(Z)V", "gson", "Lcom/google/gson/Gson;", "kotlin.jvm.PlatformType", "getGson", "()Lcom/google/gson/Gson;", "lockMap", "com/htmake/reader/utils/ExtKt$lockMap$1", "Lcom/htmake/reader/utils/ExtKt$lockMap$1;", "logger", "Lmu/KLogger;", "getLogger", "()Lmu/KLogger;", "prettyGson", "getPrettyGson", "storageFinalPath", "", "getStorageFinalPath", "()Ljava/lang/String;", "setStorageFinalPath", "(Ljava/lang/String;)V", "workDirInit", "getWorkDirInit", "setWorkDirInit", "workDirPath", "getWorkDirPath", "setWorkDirPath", "asJsonArray", "Lio/vertx/core/json/JsonArray;", "value", "", "asJsonObject", "Lio/vertx/core/json/JsonObject;", "countOccurrences", "str", "subStr", "createDir", "Ljava/io/File;", "filePath", "createFile", "decryptData", "content", "decryptToLicense", "Lcom/htmake/reader/entity/License;", "encodeBase64", "text", "genEncryptedPassword", "password", "salt", "getCommand", "", "Lkotlin/Pair;", "to", "subject", "body", "getInstalledLicense", "ignoreInvalid", "getMongoFileStorage", "Lcom/mongodb/client/MongoCollection;", "Lcom/htmake/reader/entity/MongoFile;", "getRandomString", "length", "getRelativePath", "subDirFiles", "", "([Ljava/lang/String;)Ljava/lang/String;", "getStorage", "name", "ext", "([Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "getStorageFile", "([Ljava/lang/String;Ljava/lang/String;)Ljava/io/File;", "getStoragePath", "getTraceId", "getWorkDir", "subPath", "jsonEncode", "pretty", "parseJsonStringList", "file", "fields", "", "exclude", "startIndex", "endIndex", "checkNotEmpty", "filter", "Lkotlin/Function1;", "Lcom/fasterxml/jackson/databind/node/ObjectNode;", "readInstanceProperty", "R", "instance", "propertyName", "(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;", "readMongoFile", "path", "saveMongoFile", "saveStorage", "", "([Ljava/lang/String;Ljava/lang/Object;ZLjava/lang/String;)V", "sendEmail", "toEmail", "setInstanceProperty", "propertyValue", "setLicenseValid", "isValid", "validateEmail", "email", "zip", "files", "zipFilePath", "arrayType", "Ljava/lang/Class;", "T", "convert", "O", "I", "(Ljava/lang/Object;)Ljava/lang/Object;", "deepListFiles", "allowExtensions", "(Ljava/io/File;[Ljava/lang/String;)Ljava/util/List;", "deleteRecursively", "fillData", "Lio/legado/app/data/entities/Book;", "newBook", "keys", "listFilesRecursively", "serializeToMap", "", "(Ljava/lang/Object;)Ljava/util/Map;", "toDataClass", "(Ljava/util/Map;)Ljava/lang/Object;", "toDir", "absolute", "toMap", "unzip", "descDir", "url", "reader-pro"}
)
public final class ExtKt {
   @NotNull
   private static final KLogger logger;
   private static final Gson gson;
   private static final Gson prettyGson;
   @NotNull
   private static String storageFinalPath;
   @NotNull
   private static String workDirPath;
   private static boolean workDirInit;
   private static final int MAX_CACHE_SIZE = 1000;
   @NotNull
   private static final <undefinedtype> lockMap;
   private static boolean _licenseValid;

   @NotNull
   public static final KLogger getLogger() {
      return logger;
   }

   public static final Gson getGson() {
      return gson;
   }

   public static final Gson getPrettyGson() {
      return prettyGson;
   }

   @NotNull
   public static final String getStorageFinalPath() {
      return storageFinalPath;
   }

   public static final void setStorageFinalPath(@NotNull String <set-?>) {
      Intrinsics.checkNotNullParameter(var0, "<set-?>");
      storageFinalPath = var0;
   }

   @NotNull
   public static final String getWorkDirPath() {
      return workDirPath;
   }

   public static final void setWorkDirPath(@NotNull String <set-?>) {
      Intrinsics.checkNotNullParameter(var0, "<set-?>");
      workDirPath = var0;
   }

   public static final boolean getWorkDirInit() {
      return workDirInit;
   }

   public static final void setWorkDirInit(boolean <set-?>) {
      workDirInit = var0;
   }

   @NotNull
   public static final String url(@NotNull String $this$url) {
      Intrinsics.checkNotNullParameter($this$url, "<this>");
      if (StringsKt.startsWith$default($this$url, "//", false, 2, (Object)null)) {
         return HttpUrl.Companion.get(Intrinsics.stringPlus("http:", $this$url)).toString();
      } else {
         return StringsKt.startsWith$default($this$url, "http", false, 2, (Object)null) ? HttpUrl.Companion.get($this$url).toString() : $this$url;
      }
   }

   @NotNull
   public static final String toDir(@NotNull String $this$toDir, boolean absolute) {
      Intrinsics.checkNotNullParameter($this$toDir, "<this>");
      String path = $this$toDir;
      if (StringsKt.endsWith$default($this$toDir, "/", false, 2, (Object)null)) {
         byte var4 = 0;
         int var5 = $this$toDir.length() - 1;
         boolean var6 = false;
         String var10000 = $this$toDir.substring(var4, var5);
         Intrinsics.checkNotNullExpressionValue(var10000, "(this as java.lang.Strin…ing(startIndex, endIndex)");
         path = var10000;
      }

      if (absolute && !StringsKt.startsWith$default(path, "/", false, 2, (Object)null)) {
         path = Intrinsics.stringPlus("/", path);
      }

      return path;
   }

   // $FF: synthetic method
   public static String toDir$default(String var0, boolean var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = false;
      }

      return toDir(var0, var1);
   }

   public static final void deleteRecursively(@NotNull File $this$deleteRecursively) {
      Intrinsics.checkNotNullParameter($this$deleteRecursively, "<this>");
      if ($this$deleteRecursively.exists()) {
         if ($this$deleteRecursively.isFile()) {
            $this$deleteRecursively.delete();
         } else {
            File[] var1 = $this$deleteRecursively.listFiles();
            Intrinsics.checkNotNullExpressionValue(var1, "this.listFiles()");
            Object[] $this$forEach$iv = (Object[])var1;
            int $i$f$forEach = false;
            Object[] var3 = $this$forEach$iv;
            int var4 = $this$forEach$iv.length;

            for(int var5 = 0; var5 < var4; ++var5) {
               Object element$iv = var3[var5];
               File it = (File)element$iv;
               int var8 = false;
               Intrinsics.checkNotNullExpressionValue(it, "it");
               deleteRecursively(it);
            }

            $this$deleteRecursively.delete();
         }
      }

   }

   @NotNull
   public static final List<File> listFilesRecursively(@NotNull File $this$listFilesRecursively) {
      Intrinsics.checkNotNullParameter($this$listFilesRecursively, "<this>");
      Object list = null;
      boolean var2 = false;
      list = new ArrayList();
      if ($this$listFilesRecursively.exists()) {
         if ($this$listFilesRecursively.isFile()) {
            list.add($this$listFilesRecursively);
         } else {
            File[] var10 = $this$listFilesRecursively.listFiles();
            Intrinsics.checkNotNullExpressionValue(var10, "this.listFiles()");
            Object[] $this$forEach$iv = (Object[])var10;
            int $i$f$forEach = false;
            Object[] var4 = $this$forEach$iv;
            int var5 = $this$forEach$iv.length;

            for(int var6 = 0; var6 < var5; ++var6) {
               Object element$iv = var4[var6];
               File it = (File)element$iv;
               int var9 = false;
               list.add(it);
               if (it.isDirectory()) {
                  Intrinsics.checkNotNullExpressionValue(it, "it");
                  list.addAll((Collection)listFilesRecursively(it));
               }
            }
         }
      }

      return (List)list;
   }

   public static final boolean unzip(@NotNull File $this$unzip, @NotNull String descDir) {
      Intrinsics.checkNotNullParameter($this$unzip, "<this>");
      Intrinsics.checkNotNullParameter(descDir, "descDir");
      if (!$this$unzip.exists()) {
         return false;
      } else {
         byte[] buffer = new byte[1024];
         OutputStream outputStream = null;
         InputStream inputStream = null;
         boolean var19 = false;

         boolean var22;
         label160: {
            try {
               var19 = true;
               ZipFile zf = new ZipFile($this$unzip.toString());
               Enumeration entries = zf.entries();

               while(entries.hasMoreElements()) {
                  Object var8 = entries.nextElement();
                  if (var8 == null) {
                     throw new NullPointerException("null cannot be cast to non-null type java.util.zip.ZipEntry");
                  }

                  ZipEntry zipEntry = (ZipEntry)var8;
                  String descFilePath = zipEntry.getName();
                  Intrinsics.checkNotNullExpressionValue(descFilePath, "zipEntry.name");
                  descFilePath = descDir + File.separator + descFilePath;
                  if (zipEntry.isDirectory()) {
                     createDir(descFilePath);
                  } else {
                     inputStream = zf.getInputStream(zipEntry);
                     File descFile = createFile(descFilePath);
                     outputStream = (OutputStream)(new FileOutputStream(descFile));
                     boolean var11 = false;

                     while(true) {
                        int var12 = inputStream.read(buffer);
                        boolean var13 = false;
                        boolean var14 = false;
                        int var16 = false;
                        if (var12 <= 0) {
                           inputStream.close();
                           ((FileOutputStream)outputStream).close();
                           break;
                        }

                        ((FileOutputStream)outputStream).write(buffer, 0, var12);
                     }
                  }
               }

               var22 = true;
               var19 = false;
               break label160;
            } catch (Exception var20) {
               var20.printStackTrace();
               var19 = false;
            } finally {
               if (var19) {
                  if (inputStream != null) {
                     inputStream.close();
                  }

                  if (outputStream != null) {
                     outputStream.close();
                  }

               }
            }

            if (inputStream != null) {
               inputStream.close();
            }

            if (outputStream != null) {
               outputStream.close();
            }

            return false;
         }

         if (inputStream != null) {
            inputStream.close();
         }

         if (outputStream != null) {
            outputStream.close();
         }

         return var22;
      }
   }

   public static final boolean zip(@NotNull File $this$zip, @NotNull String zipFilePath) {
      Intrinsics.checkNotNullParameter($this$zip, "<this>");
      Intrinsics.checkNotNullParameter(zipFilePath, "zipFilePath");
      if (!$this$zip.exists()) {
         return false;
      } else {
         File[] files;
         if ($this$zip.isDirectory()) {
            files = $this$zip.listFiles();
            Intrinsics.checkNotNullExpressionValue(files, "files");
            List filesList = ArraysKt.toList(files);
            return zip(filesList, zipFilePath);
         } else {
            files = new File[]{$this$zip};
            return zip((List)CollectionsKt.arrayListOf(files), zipFilePath);
         }
      }
   }

   public static final boolean zip(@NotNull List<? extends File> files, @NotNull String zipFilePath) {
      Intrinsics.checkNotNullParameter(files, "files");
      Intrinsics.checkNotNullParameter(zipFilePath, "zipFilePath");
      if (files.isEmpty()) {
         return false;
      } else {
         File zipFile = createFile(zipFilePath);
         byte[] buffer = new byte[1024];
         ZipOutputStream zipOutputStream = null;
         FileInputStream inputStream = null;
         boolean var16 = false;

         boolean var19;
         label142: {
            try {
               var16 = true;
               zipOutputStream = new ZipOutputStream((OutputStream)(new FileOutputStream(zipFile)));
               Iterator var6 = files.iterator();

               while(var6.hasNext()) {
                  File file = (File)var6.next();
                  if (file.exists()) {
                     zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
                     inputStream = new FileInputStream(file);
                     boolean var8 = false;

                     while(true) {
                        int var9 = inputStream.read(buffer);
                        boolean var10 = false;
                        boolean var11 = false;
                        int var13 = false;
                        if (var9 <= 0) {
                           zipOutputStream.closeEntry();
                           break;
                        }

                        zipOutputStream.write(buffer, 0, var9);
                     }
                  }
               }

               var19 = true;
               var16 = false;
               break label142;
            } catch (Exception var17) {
               var17.printStackTrace();
               var16 = false;
            } finally {
               if (var16) {
                  if (inputStream != null) {
                     inputStream.close();
                  }

                  if (zipOutputStream != null) {
                     zipOutputStream.close();
                  }

               }
            }

            if (inputStream != null) {
               inputStream.close();
            }

            if (zipOutputStream != null) {
               zipOutputStream.close();
            }

            return false;
         }

         if (inputStream != null) {
            inputStream.close();
         }

         zipOutputStream.close();
         return var19;
      }
   }

   @NotNull
   public static final File createDir(@NotNull String filePath) {
      Intrinsics.checkNotNullParameter(filePath, "filePath");
      logger.debug("createDir filePath {}", filePath);
      File file = new File(filePath);
      if (!file.exists()) {
         file.mkdirs();
      }

      return file;
   }

   @NotNull
   public static final File createFile(@NotNull String filePath) {
      Intrinsics.checkNotNullParameter(filePath, "filePath");
      logger.debug("createFile filePath {}", filePath);
      File file = new File(filePath);
      File var10000 = file.getParentFile();
      Intrinsics.checkNotNull(var10000);
      File parentFile = var10000;
      if (!parentFile.exists()) {
         parentFile.mkdirs();
      }

      if (!file.exists()) {
         file.createNewFile();
      }

      return file;
   }

   @NotNull
   public static final String getWorkDir(@NotNull String subPath) {
      Intrinsics.checkNotNullParameter(subPath, "subPath");
      String var10000;
      if (!workDirInit) {
         CharSequence var1 = (CharSequence)workDirPath;
         boolean var2 = false;
         if (var1.length() == 0) {
            AppConfig appConfig = (AppConfig)SpringContextUtils.getBean("appConfig", AppConfig.class);
            boolean var3;
            CharSequence var7;
            String currentDir;
            if (appConfig != null) {
               var7 = (CharSequence)appConfig.getWorkDir();
               var3 = false;
               if (var7.length() > 0 && !appConfig.getWorkDir().equals(".")) {
                  File workDirFile = new File(appConfig.getWorkDir());
                  if (workDirFile.exists() && !workDirFile.isDirectory()) {
                     logger.error("reader.app.workDir={} is not a directory", appConfig.getWorkDir());
                  } else {
                     if (!workDirFile.exists()) {
                        logger.info("reader.app.workDir={} not exists, creating", appConfig.getWorkDir());
                        workDirFile.mkdirs();
                     }

                     currentDir = workDirFile.getAbsolutePath();
                     Intrinsics.checkNotNullExpressionValue(currentDir, "workDirFile.absolutePath");
                     workDirPath = currentDir;
                  }
               }
            }

            var7 = (CharSequence)workDirPath;
            var3 = false;
            if (var7.length() == 0) {
               label72: {
                  String osName = System.getProperty("os.name");
                  currentDir = System.getProperty("user.dir");
                  logger.info("osName: {} currentDir: {}", osName, currentDir);
                  Intrinsics.checkNotNullExpressionValue(osName, "osName");
                  if (StringsKt.startsWith(osName, "Mac OS", true)) {
                     Intrinsics.checkNotNullExpressionValue(currentDir, "currentDir");
                     if (!StringsKt.startsWith$default(currentDir, "/Users/", false, 2, (Object)null)) {
                        var10000 = System.getProperty("user.home");
                        String[] var4 = new String[]{".reader"};
                        workDirPath = Paths.get(var10000, var4).toString();
                        break label72;
                     }
                  }

                  Intrinsics.checkNotNullExpressionValue(currentDir, "currentDir");
                  workDirPath = currentDir;
               }
            }

            logger.info("Using workdir: {}", workDirPath);
            workDirInit = true;
         }
      }

      var10000 = workDirPath;
      String[] var11 = new String[]{subPath};
      Path path = Paths.get(var10000, var11);
      return path.toString();
   }

   // $FF: synthetic method
   public static String getWorkDir$default(String var0, int var1, Object var2) {
      if ((var1 & 1) != 0) {
         var0 = "";
      }

      return getWorkDir(var0);
   }

   @NotNull
   public static final String getWorkDir(@NotNull String... subDirFiles) {
      Intrinsics.checkNotNullParameter(subDirFiles, "subDirFiles");
      return getWorkDir(getRelativePath((String[])Arrays.copyOf(subDirFiles, subDirFiles.length)));
   }

   @NotNull
   public static final String getRelativePath(@NotNull String... subDirFiles) {
      Intrinsics.checkNotNullParameter(subDirFiles, "subDirFiles");
      StringBuilder path = new StringBuilder("");
      int $i$f$forEach = false;
      String[] var4 = subDirFiles;
      int var5 = subDirFiles.length;

      boolean var9;
      for(int var6 = 0; var6 < var5; ++var6) {
         Object element$iv = var4[var6];
         var9 = false;
         CharSequence var10 = (CharSequence)element$iv;
         boolean var11 = false;
         if (var10.length() > 0) {
            path.append(File.separator).append(element$iv);
         }
      }

      String var2 = path.toString();
      $i$f$forEach = false;
      boolean var12 = false;
      int var13 = false;
      Intrinsics.checkNotNullExpressionValue(var2, "it");
      String var10000;
      if (StringsKt.startsWith$default(var2, "/", false, 2, (Object)null)) {
         byte var8 = 1;
         var9 = false;
         var10000 = var2.substring(var8);
         Intrinsics.checkNotNullExpressionValue(var10000, "(this as java.lang.String).substring(startIndex)");
      } else {
         var10000 = var2;
      }

      return var10000;
   }

   @NotNull
   public static final String getStoragePath() {
      CharSequence var0 = (CharSequence)storageFinalPath;
      boolean var1 = false;
      if (var0.length() > 0) {
         return storageFinalPath;
      } else {
         String storagePath = "";
         AppConfig appConfig = (AppConfig)SpringContextUtils.getBean("appConfig", AppConfig.class);
         if (appConfig != null) {
            storagePath = getWorkDir("storage");
            storageFinalPath = storagePath;
         } else {
            String var2 = (new File("storage")).getPath();
            Intrinsics.checkNotNullExpressionValue(var2, "File(\"storage\").path");
            storagePath = var2;
         }

         logger.info("Using storagePath: {}", storagePath);
         return storagePath;
      }
   }

   public static final void saveStorage(@NotNull String[] name, @NotNull Object value, boolean pretty, @NotNull String ext) {
      // $FF: Couldn't be decompiled
   }

   // $FF: synthetic method
   public static void saveStorage$default(String[] var0, Object var1, boolean var2, String var3, int var4, Object var5) {
      if ((var4 & 4) != 0) {
         var2 = false;
      }

      if ((var4 & 8) != 0) {
         var3 = ".json";
      }

      saveStorage(var0, var1, var2, var3);
   }

   @NotNull
   public static final File getStorageFile(@NotNull String[] name, @NotNull String ext) {
      Intrinsics.checkNotNullParameter(name, "name");
      Intrinsics.checkNotNullParameter(ext, "ext");
      String storagePath = getStoragePath();
      File storageDir = new File(storagePath);
      if (!storageDir.exists()) {
         storageDir.mkdirs();
      }

      String filename = (String)ArraysKt.last(name);
      SpreadBuilder var6 = new SpreadBuilder(2);
      byte var8 = 0;
      int var9 = name.length - 1;
      boolean var10 = false;
      var6.addSpread(ArraysKt.copyOfRange(name, var8, var9));
      var6.add(Intrinsics.stringPlus(filename, ext));
      String path = getRelativePath((String[])var6.toArray(new String[var6.size()]));
      return new File(storagePath + File.separator + path);
   }

   // $FF: synthetic method
   public static File getStorageFile$default(String[] var0, String var1, int var2, Object var3) {
      if ((var2 & 2) != 0) {
         var1 = ".json";
      }

      return getStorageFile(var0, var1);
   }

   @Nullable
   public static final String getStorage(@NotNull String[] name, @NotNull String ext) {
      // $FF: Couldn't be decompiled
   }

   // $FF: synthetic method
   public static String getStorage$default(String[] var0, String var1, int var2, Object var3) {
      if ((var2 & 2) != 0) {
         var1 = ".json";
      }

      return getStorage(var0, var1);
   }

   @Nullable
   public static final MongoCollection<MongoFile> getMongoFileStorage() {
      AppConfig appConfig = (AppConfig)SpringContextUtils.getBean("appConfig", AppConfig.class);
      return MongoManager.INSTANCE.fileStorage(appConfig.getMongoDbName(), "storage");
   }

   @Nullable
   public static final String readMongoFile(@NotNull String path) {
      Intrinsics.checkNotNullParameter(path, "path");
      if (MongoManager.INSTANCE.isInit()) {
         logger.info("Get mongoFile {}", path);
         MongoCollection var2 = getMongoFileStorage();
         MongoFile var10000;
         if (var2 == null) {
            var10000 = null;
         } else {
            FindIterable var3 = var2.find(Filters.eq("path", path));
            var10000 = var3 == null ? null : (MongoFile)var3.first();
         }

         MongoFile doc = var10000;
         if (doc != null) {
            return doc.getContent();
         }
      }

      return null;
   }

   public static final boolean saveMongoFile(@NotNull String path, @NotNull String content) {
      Intrinsics.checkNotNullParameter(path, "path");
      Intrinsics.checkNotNullParameter(content, "content");
      if (MongoManager.INSTANCE.isInit()) {
         logger.info("Save mongoFile {}", path);
         MongoCollection var3 = getMongoFileStorage();
         MongoFile var10000;
         if (var3 == null) {
            var10000 = null;
         } else {
            FindIterable var4 = var3.find(Filters.eq("path", path));
            var10000 = var4 == null ? null : (MongoFile)var4.first();
         }

         MongoFile doc = var10000;
         if (doc != null) {
            doc.setContent(content);
            doc.setUpdated_at(System.currentTimeMillis());
            MongoCollection var7 = getMongoFileStorage();
            UpdateResult result = var7 == null ? null : var7.replaceOne(Filters.eq("path", path), doc, (new ReplaceOptions()).upsert(true));
            return result != null && result.getModifiedCount() > 0L;
         }

         doc = new MongoFile(path, content, 0L, 0L, 12, (DefaultConstructorMarker)null);

         try {
            var3 = getMongoFileStorage();
            if (var3 != null) {
               var3.insertOne(doc);
            }

            return true;
         } catch (Exception var5) {
            logger.info("Save mongoFile {} failed", path);
            var5.printStackTrace();
         }
      }

      return false;
   }

   public static final int countOccurrences(@NotNull String str, @NotNull String subStr) {
      Intrinsics.checkNotNullParameter(str, "str");
      Intrinsics.checkNotNullParameter(subStr, "subStr");
      int count = 0;

      int index;
      for(int startIndex = 0; startIndex < str.length(); startIndex = index + subStr.length()) {
         index = StringsKt.indexOf$default((CharSequence)str, subStr, startIndex, false, 4, (Object)null);
         if (index == -1) {
            break;
         }

         ++count;
      }

      return count;
   }

   @Nullable
   public static final JsonArray asJsonArray(@Nullable Object value) {
      if (value instanceof JsonArray) {
         return (JsonArray)value;
      } else if (value instanceof String) {
         try {
            return new JsonArray((String)value);
         } catch (Exception var2) {
            logger.error("解析内容出错: {}  内容: \n{}", var2, value);
            throw var2;
         }
      } else {
         return null;
      }
   }

   @Nullable
   public static final JsonArray parseJsonStringList(@NotNull File file, @Nullable Set<String> fields, @Nullable Set<String> exclude, int startIndex, int endIndex, @Nullable Set<String> checkNotEmpty, @Nullable Function1<? super ObjectNode, Boolean> filter) {
      Intrinsics.checkNotNullParameter(file, "file");
      if (!file.exists()) {
         return null;
      } else {
         try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonFactory factory = objectMapper.getFactory();
            JsonArray resultList = new JsonArray();
            int currentIndex = false;
            int currentIndex = -1;
            Closeable var11 = (Closeable)factory.createParser(file);
            boolean var12 = false;
            boolean var13 = false;
            Throwable var31 = (Throwable)null;

            try {
               JsonParser parser = (JsonParser)var11;
               int var15 = false;
               if (parser.nextToken() == JsonToken.START_ARRAY) {
                  label285:
                  while(true) {
                     while(true) {
                        while(true) {
                           while(true) {
                              do {
                                 if (parser.nextToken() == JsonToken.END_ARRAY) {
                                    break label285;
                                 }
                              } while(parser.currentToken() != JsonToken.START_OBJECT);

                              Collection var16 = (Collection)fields;
                              boolean var17 = false;
                              boolean var18 = false;
                              boolean $i$f$forEach;
                              boolean var20;
                              if (var16 == null || var16.isEmpty()) {
                                 JsonNode jsonNode;
                                 TreeNode var36;
                                 ObjectNode objectNode;
                                 String var41;
                                 if (filter == null) {
                                    ++currentIndex;
                                    if (currentIndex < startIndex) {
                                       parser.skipChildren();
                                    } else {
                                       if (currentIndex > endIndex) {
                                          break label285;
                                       }

                                       var36 = parser.readValueAsTree();
                                       Intrinsics.checkNotNullExpressionValue(var36, "parser.readValueAsTree()");
                                       jsonNode = (JsonNode)var36;
                                       objectNode = (ObjectNode)jsonNode;
                                       Collection var39 = (Collection)exclude;
                                       $i$f$forEach = false;
                                       var20 = false;
                                       if (var39 != null && !var39.isEmpty()) {
                                          Iterable $this$forEach$iv = (Iterable)exclude;
                                          $i$f$forEach = false;
                                          Iterator var42 = $this$forEach$iv.iterator();

                                          while(var42.hasNext()) {
                                             Object element$iv = var42.next();
                                             String it = (String)element$iv;
                                             int var23 = false;
                                             objectNode.remove(it);
                                          }
                                       }

                                       var41 = objectNode.toString();
                                       Intrinsics.checkNotNullExpressionValue(var41, "objectNode.toString()");
                                       resultList.add(var41);
                                    }
                                 } else {
                                    var36 = parser.readValueAsTree();
                                    Intrinsics.checkNotNullExpressionValue(var36, "parser.readValueAsTree()");
                                    jsonNode = (JsonNode)var36;
                                    objectNode = (ObjectNode)jsonNode;
                                    if ((Boolean)filter.invoke(objectNode)) {
                                       ++currentIndex;
                                    }

                                    if (currentIndex >= startIndex) {
                                       if (currentIndex > endIndex) {
                                          break label285;
                                       }

                                       var41 = objectNode.toString();
                                       Intrinsics.checkNotNullExpressionValue(var41, "objectNode.toString()");
                                       resultList.add(var41);
                                    }
                                 }
                              } else {
                                 ++currentIndex;
                                 if (currentIndex < startIndex) {
                                    parser.skipChildren();
                                 } else {
                                    if (currentIndex > endIndex) {
                                       break label285;
                                    }

                                    JsonObject item = new JsonObject();

                                    while(true) {
                                       while(parser.nextToken() != JsonToken.END_OBJECT) {
                                          String fieldName = parser.getCurrentName();
                                          parser.nextToken();
                                          if (fields.contains(fieldName)) {
                                             item.put(fieldName, parser.getValueAsString());
                                          } else if (checkNotEmpty != null && checkNotEmpty.contains(fieldName)) {
                                             CharSequence var38 = (CharSequence)parser.getValueAsString();
                                             $i$f$forEach = false;
                                             var20 = false;
                                             item.put(fieldName, var38 != null && var38.length() != 0);
                                          } else {
                                             parser.skipChildren();
                                          }
                                       }

                                       resultList.add(item.toString());
                                       break;
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }
               }

               parser.close();
               Unit var32 = Unit.INSTANCE;
            } catch (Throwable var27) {
               var31 = var27;
               throw var27;
            } finally {
               CloseableKt.closeFinally(var11, var31);
            }

            return resultList;
         } catch (Exception var29) {
            logger.error("解析文件内容出错: {}  文件: \n{}", var29, file);
            throw var29;
         }
      }
   }

   // $FF: synthetic method
   public static JsonArray parseJsonStringList$default(File var0, Set var1, Set var2, int var3, int var4, Set var5, Function1 var6, int var7, Object var8) {
      if ((var7 & 2) != 0) {
         var1 = null;
      }

      if ((var7 & 4) != 0) {
         var2 = null;
      }

      if ((var7 & 8) != 0) {
         var3 = 0;
      }

      if ((var7 & 16) != 0) {
         var4 = Integer.MAX_VALUE;
      }

      if ((var7 & 32) != 0) {
         var5 = null;
      }

      if ((var7 & 64) != 0) {
         var6 = null;
      }

      return parseJsonStringList(var0, var1, var2, var3, var4, var5, var6);
   }

   @Nullable
   public static final JsonObject asJsonObject(@Nullable Object value) {
      if (value instanceof JsonObject) {
         return (JsonObject)value;
      } else if (value instanceof String) {
         try {
            return new JsonObject((String)value);
         } catch (Exception var2) {
            logger.error("解析内容出错: {}  内容: \n{}", var2, value);
            throw var2;
         }
      } else {
         return null;
      }
   }

   @NotNull
   public static final <T> Map<String, Object> serializeToMap(T $this$serializeToMap) {
      int $i$f$convert = false;
      String json$iv = $this$serializeToMap instanceof String ? (String)$this$serializeToMap : getGson().toJson($this$serializeToMap);
      return (Map)getGson().fromJson(json$iv, (new ExtKt$serializeToMap$$inlined$convert$1()).getType());
   }

   @NotNull
   public static final <T> Map<String, Object> toMap(T $this$toMap) {
      int $i$f$convert = false;
      String json$iv = $this$toMap instanceof String ? (String)$this$toMap : getGson().toJson($this$toMap);
      return (Map)getGson().fromJson(json$iv, (new ExtKt$toMap$$inlined$convert$1()).getType());
   }

   // $FF: synthetic method
   public static final <T> T toDataClass(Map<String, ? extends Object> $this$toDataClass) {
      Intrinsics.checkNotNullParameter($this$toDataClass, "<this>");
      int $i$f$toDataClass = false;
      int $i$f$convert = false;
      String json$iv = $this$toDataClass instanceof String ? (String)$this$toDataClass : getGson().toJson($this$toDataClass);
      Gson var10000 = getGson();
      Intrinsics.needClassReification();
      return var10000.fromJson(json$iv, (new ExtKt$toDataClass$$inlined$convert$1()).getType());
   }

   // $FF: synthetic method
   public static final <I, O> O convert(I $this$convert) {
      int $i$f$convert = false;
      String json = $this$convert instanceof String ? (String)$this$convert : getGson().toJson($this$convert);
      Gson var10000 = getGson();
      Intrinsics.needClassReification();
      return var10000.fromJson(json, (new TypeToken<O>() {
      }).getType());
   }

   // $FF: synthetic method
   public static final <T> Class<Object> arrayType(Class<T> $this$arrayType) {
      Intrinsics.checkNotNullParameter($this$arrayType, "<this>");
      int $i$f$arrayType = false;
      return Array.newInstance($this$arrayType, 0).getClass();
   }

   public static final <R> R readInstanceProperty(@NotNull Object instance, @NotNull String propertyName) {
      Intrinsics.checkNotNullParameter(instance, "instance");
      Intrinsics.checkNotNullParameter(propertyName, "propertyName");
      Iterable $this$first$iv = (Iterable)KClasses.getMemberProperties(Reflection.getOrCreateKotlinClass(instance.getClass()));
      int $i$f$first = false;
      Iterator var5 = $this$first$iv.iterator();

      Object element$iv;
      KProperty1 it;
      do {
         if (!var5.hasNext()) {
            throw (Throwable)(new NoSuchElementException("Collection contains no element matching the predicate."));
         }

         element$iv = var5.next();
         it = (KProperty1)element$iv;
         int var8 = false;
      } while(!Intrinsics.areEqual(it.getName(), propertyName));

      KProperty1 var2 = (KProperty1)element$iv;
      return var2.get(instance);
   }

   public static final void setInstanceProperty(@NotNull Object instance, @NotNull String propertyName, @NotNull Object propertyValue) {
      Intrinsics.checkNotNullParameter(instance, "instance");
      Intrinsics.checkNotNullParameter(propertyName, "propertyName");
      Intrinsics.checkNotNullParameter(propertyValue, "propertyValue");
      Iterable $this$first$iv = (Iterable)KClasses.getMemberProperties(Reflection.getOrCreateKotlinClass(instance.getClass()));
      int $i$f$first = false;
      Iterator var6 = $this$first$iv.iterator();

      Object element$iv;
      KProperty1 it;
      do {
         if (!var6.hasNext()) {
            throw (Throwable)(new NoSuchElementException("Collection contains no element matching the predicate."));
         }

         element$iv = var6.next();
         it = (KProperty1)element$iv;
         int var9 = false;
      } while(!Intrinsics.areEqual(it.getName(), propertyName));

      KProperty1 property = (KProperty1)element$iv;
      if (property instanceof KMutableProperty) {
         Setter var10000 = ((KMutableProperty)property).getSetter();
         Object[] var10 = new Object[]{instance, propertyValue};
         var10000.call(var10);
      }

   }

   @NotNull
   public static final Book fillData(@NotNull Book $this$fillData, @NotNull Book newBook, @NotNull List<String> keys) {
      Intrinsics.checkNotNullParameter($this$fillData, "<this>");
      Intrinsics.checkNotNullParameter(newBook, "newBook");
      Intrinsics.checkNotNullParameter(keys, "keys");
      boolean var4 = false;
      boolean var5 = false;
      int var7 = false;
      Iterator var8 = keys.iterator();

      while(true) {
         String key;
         CharSequence var11;
         boolean var13;
         do {
            if (!var8.hasNext()) {
               return $this$fillData;
            }

            key = (String)var8.next();
            String current = (String)readInstanceProperty($this$fillData, key);
            var11 = (CharSequence)current;
            boolean var12 = false;
            var13 = false;
         } while(var11 != null && var11.length() != 0);

         String cacheValue = (String)readInstanceProperty(newBook, key);
         CharSequence var16 = (CharSequence)cacheValue;
         var13 = false;
         boolean var14 = false;
         if (var16 != null && var16.length() != 0) {
            setInstanceProperty($this$fillData, key, cacheValue);
         }
      }
   }

   @NotNull
   public static final String getRandomString(int length) {
      String allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz0123456789";
      Iterable $this$map$iv = (Iterable)(new IntRange(1, length));
      int $i$f$map = false;
      Collection destination$iv$iv = (Collection)(new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10)));
      int $i$f$mapTo = false;
      Iterator var7 = $this$map$iv.iterator();

      while(var7.hasNext()) {
         int item$iv$iv = ((IntIterator)var7).nextInt();
         int var10 = false;
         CharSequence var11 = (CharSequence)allowedChars;
         boolean var12 = false;
         Character var14 = StringsKt.random(var11, (Random)Random.Default);
         destination$iv$iv.add(var14);
      }

      return CollectionsKt.joinToString$default((Iterable)((List)destination$iv$iv), (CharSequence)"", (CharSequence)null, (CharSequence)null, 0, (CharSequence)null, (Function1)null, 62, (Object)null);
   }

   @NotNull
   public static final String genEncryptedPassword(@NotNull String password, @NotNull String salt) {
      Intrinsics.checkNotNullParameter(password, "password");
      Intrinsics.checkNotNullParameter(salt, "salt");
      return MD5Utils.INSTANCE.md5Encode(Intrinsics.stringPlus(MD5Utils.INSTANCE.md5Encode(Intrinsics.stringPlus(password, salt)), salt)).toString();
   }

   @NotNull
   public static final String jsonEncode(@NotNull Object value, boolean pretty) {
      Intrinsics.checkNotNullParameter(value, "value");
      String var2;
      if (pretty) {
         var2 = prettyGson.toJson(value);
         Intrinsics.checkNotNullExpressionValue(var2, "prettyGson.toJson(value)");
         return var2;
      } else {
         var2 = gson.toJson(value);
         Intrinsics.checkNotNullExpressionValue(var2, "gson.toJson(value)");
         return var2;
      }
   }

   // $FF: synthetic method
   public static String jsonEncode$default(Object var0, boolean var1, int var2, Object var3) {
      if ((var2 & 2) != 0) {
         var1 = false;
      }

      return jsonEncode(var0, var1);
   }

   @NotNull
   public static final List<File> deepListFiles(@NotNull File $this$deepListFiles, @Nullable String[] allowExtensions) {
      Intrinsics.checkNotNullParameter($this$deepListFiles, "<this>");
      boolean var3 = false;
      ArrayList fileList = new ArrayList();
      File[] var16 = $this$deepListFiles.listFiles();
      Intrinsics.checkNotNullExpressionValue(var16, "this.listFiles()");
      Object[] $this$forEach$iv = (Object[])var16;
      int $i$f$forEach = false;
      Object[] var5 = $this$forEach$iv;
      int var6 = $this$forEach$iv.length;

      for(int var7 = 0; var7 < var6; ++var7) {
         Object element$iv = var5[var7];
         File it = (File)element$iv;
         int var10 = false;
         if (it.isDirectory()) {
            Intrinsics.checkNotNullExpressionValue(it, "it");
            fileList.addAll((Collection)deepListFiles(it, allowExtensions));
         } else {
            FileUtils var10000 = FileUtils.INSTANCE;
            String var11 = it.getName();
            Intrinsics.checkNotNullExpressionValue(var11, "it.name");
            String extension = var10000.getExtension(var11);
            boolean var18;
            if (allowExtensions == null) {
               var18 = false;
            } else {
               boolean var14 = false;
               String var15 = ArraysKt.contentDeepToString(allowExtensions);
               var18 = var15 == null ? false : StringsKt.contains$default((CharSequence)var15, (CharSequence)extension, false, 2, (Object)null);
            }

            if (var18 || allowExtensions == null) {
               fileList.add(it);
            }
         }
      }

      return (List)fileList;
   }

   @NotNull
   public static final String getTraceId() {
      return UUID.randomUUID().toString().subSequence(0, 8).toString();
   }

   public static final boolean get_licenseValid() {
      return _licenseValid;
   }

   public static final void set_licenseValid(boolean <set-?>) {
      _licenseValid = var0;
   }

   public static final void setLicenseValid(boolean isValid) {
      _licenseValid = isValid;
   }

   @NotNull
   public static final License getInstalledLicense(boolean ignoreInvalid) {
      String[] var2 = new String[]{"data", "license"};
      String licenseKeyString = getStorage(var2, ".key");
      CharSequence var5 = (CharSequence)licenseKeyString;
      boolean var3 = false;
      boolean var4 = false;
      if (var5 == null || var5.length() == 0) {
         return new License((String)null, 0, 0L, false, 0L, 0, (String)null, (String)null, (String)null, false, (Long)null, 2047, (DefaultConstructorMarker)null);
      } else if (!ignoreInvalid && !_licenseValid) {
         return new License((String)null, 0, 0L, false, 0L, 0, (String)null, (String)null, (String)null, false, (Long)null, 2047, (DefaultConstructorMarker)null);
      } else {
         License license = decryptToLicense(licenseKeyString);
         logger.info("license: {}", license);
         return license != null && license.getVerified() ? license : new License((String)null, 0, 0L, false, 0L, 0, (String)null, (String)null, (String)null, false, (Long)null, 2047, (DefaultConstructorMarker)null);
      }
   }

   // $FF: synthetic method
   public static License getInstalledLicense$default(boolean var0, int var1, Object var2) {
      if ((var1 & 1) != 0) {
         var0 = false;
      }

      return getInstalledLicense(var0);
   }

   @Nullable
   public static final License decryptToLicense(@NotNull String content) {
      Intrinsics.checkNotNullParameter(content, "content");
      CharSequence var1 = (CharSequence)content;
      boolean var2 = false;
      boolean var3 = false;
      if (var1.length() == 0) {
         return null;
      } else {
         String var13 = decryptData(content);
         License var10000;
         if (var13 == null) {
            var10000 = null;
         } else {
            boolean var4 = false;
            boolean var5 = false;
            int var7 = false;
            Map $this$toDataClass$iv = toMap(var13);
            int $i$f$toDataClass = false;
            int $i$f$convert = false;
            String json$iv$iv = $this$toDataClass$iv instanceof String ? (String)$this$toDataClass$iv : getGson().toJson($this$toDataClass$iv);
            License var14 = (License)getGson().fromJson(json$iv$iv, (new ExtKt$decryptToLicense$lambda-19$$inlined$toDataClass$1()).getType());
            var10000 = var14 == null ? null : var14;
         }

         return var10000;
      }
   }

   @Nullable
   public static final String decryptData(@NotNull String content) {
      Intrinsics.checkNotNullParameter(content, "content");
      String publicKeyString = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAj0G3qEPjVTvVd7pXFUVYZFHT8KaoG4onc5rLUKqFQ2DCh/5hFK9t2nKh2XB+C2Jp/GSK2ONwD7ceXenmA6uvr90uCK/gp6j62XFVRvc8sIm0d/bGbzZFJRk3HKtxEckBmASduPObY691DVVixxNtUrSJktx/TZaB42pUQk4j+7FuOVNNPra44hDdnyGhmYBBf2B4kjXVMjL+0NCblFIN1+qjmcol44k6NFKFF54q05bjR3CRyYdAnNTCOyt9va0oB6lDlKHplSZmAOH9JGMUki/HDJbABESXMnyIpux27w9SQ8aJStYttnJWHALO1hiFJsxbz5KUkldH6Ny1p/2W5QIDAQAB";
      PublicKey publicKey = KeyFactory.getInstance("RSA").generatePublic((KeySpec)(new X509EncodedKeySpec(Base64.decode((String)publicKeyString, 2))));
      EncoderUtils var10000 = EncoderUtils.INSTANCE;
      Intrinsics.checkNotNullExpressionValue(publicKey, "publicKey");
      return EncoderUtils.decryptSegmentByPublicKey$default(var10000, content, publicKey, 0, 4, (Object)null);
   }

   public static final boolean validateEmail(@NotNull String email) {
      Intrinsics.checkNotNullParameter(email, "email");
      Regex regex = new Regex("^[A-Za-z0-9._%+-]+@(163|126|qq|yahoo|sina|sohu|yeah|139|189|21cn|outlook|gmail|icloud).com$");
      return regex.matches((CharSequence)email);
   }

   public static final boolean sendEmail(@NotNull String toEmail, @NotNull String subject, @NotNull String body) {
      Intrinsics.checkNotNullParameter(toEmail, "toEmail");
      Intrinsics.checkNotNullParameter(subject, "subject");
      Intrinsics.checkNotNullParameter(body, "body");
      String host = "smtp.qiye.aliyun.com";
      int port = 465;
      Function3 sendCommand = (Function3)null.INSTANCE;

      try {
         SocketFactory sslSocketFactory = SSLSocketFactory.getDefault();
         Socket socket = sslSocketFactory.createSocket(host, port);
         OutputStream var9 = socket.getOutputStream();
         Intrinsics.checkNotNullExpressionValue(var9, "socket.getOutputStream()");
         OutputStreamWriter writer = new OutputStreamWriter(var9);
         InputStream var11 = socket.getInputStream();
         Intrinsics.checkNotNullExpressionValue(var11, "socket.getInputStream()");
         Charset var12 = Charsets.UTF_8;
         boolean res = false;
         boolean var15 = false;
         Reader var14 = (Reader)(new InputStreamReader(var11, var12));
         short var23 = 8192;
         boolean var16 = false;
         BufferedReader reader = var14 instanceof BufferedReader ? (BufferedReader)var14 : new BufferedReader(var14, var23);
         String response = reader.readLine();
         Intrinsics.checkNotNullExpressionValue(response, "response");
         if (!StringsKt.startsWith$default(response, "220", false, 2, (Object)null)) {
            logger.error("Error connecting to the SMTP server.");
            return false;
         } else {
            String[] var21 = new String[]{toEmail};
            List commandList = getCommand((List)CollectionsKt.arrayListOf(var21), subject, body);
            res = false;
            int var22 = 0;
            int var24 = commandList.size();
            if (var22 < var24) {
               do {
                  int i = var22++;
                  res = (Boolean)sendCommand.invoke(writer, reader, commandList.get(i));
               } while(res && var22 < var24);
            }

            writer.close();
            reader.close();
            socket.close();
            return res;
         }
      } catch (Exception var17) {
         var17.printStackTrace();
         return false;
      }
   }

   @NotNull
   public static final List<Pair<String, Integer>> getCommand(@NotNull List<String> to, @NotNull String subject, @NotNull String body) {
      Intrinsics.checkNotNullParameter(to, "to");
      Intrinsics.checkNotNullParameter(subject, "subject");
      Intrinsics.checkNotNullParameter(body, "body");
      String username = "no-reply@onmy.top";
      String password = "no-reply@1.";
      String from = "no-reply@onmy.top";
      String fromname = "Reader";
      String separator = "----=_Part_" + System.currentTimeMillis() + UUID.randomUUID();
      Pair[] var9 = new Pair[]{new Pair("HELO sendmail\r\n", 250)};
      List command = CollectionsKt.mutableListOf(var9);
      CharSequence var13 = (CharSequence)username;
      boolean var10 = false;
      boolean var11 = false;
      if (var13.length() != 0) {
         command.add(new Pair("AUTH LOGIN\r\n", 334));
         command.add(new Pair(Intrinsics.stringPlus(encodeBase64(username), "\r\n"), 334));
         command.add(new Pair(Intrinsics.stringPlus(encodeBase64(password), "\r\n"), 235));
      }

      command.add(new Pair("MAIL FROM: <" + from + ">\r\n", 250));
      String header = "FROM: " + fromname + '<' + from + ">\r\n";
      Collection var15 = (Collection)to;
      var11 = false;
      boolean var12 = false;
      if (!var15.isEmpty()) {
         int count = to.size();
         if (count == 1) {
            command.add(new Pair("RCPT TO: <" + (String)to.get(0) + ">\r\n", 250));
            header = header + "TO: <" + (String)to.get(0) + ">\r\n";
         } else {
            int var17 = 0;
            if (var17 < count) {
               do {
                  int i = var17++;
                  command.add(new Pair("RCPT TO: <" + (String)to.get(i) + ">\r\n", 250));
                  if (i == 0) {
                     header = header + "TO: <" + (String)to.get(i) + '>';
                  } else if (i + 1 == count) {
                     header = header + ",<" + (String)to.get(i) + ">\r\n";
                  } else {
                     header = header + ",<" + (String)to.get(i) + '>';
                  }
               } while(var17 < count);
            }
         }
      }

      header = header + "Subject: =?UTF-8?B?" + encodeBase64(subject) + "?=\r\n";
      header = Intrinsics.stringPlus(header, "Content-Type: multipart/alternative;\r\n");
      header = header + "\tboundary=\"" + separator + '"';
      header = Intrinsics.stringPlus(header, "\r\nMIME-Version: 1.0\r\n");
      header = header + "\r\n--" + separator + "\r\n";
      header = Intrinsics.stringPlus(header, "Content-Type:text/html; charset=utf-8\r\n");
      header = Intrinsics.stringPlus(header, "Content-Transfer-Encoding: base64\r\n\r\n");
      header = header + encodeBase64(body) + "\r\n";
      header = header + "--" + separator + "\r\n";
      header = Intrinsics.stringPlus(header, "\r\n.\r\n");
      command.add(new Pair("DATA\r\n", 354));
      command.add(new Pair(header, 250));
      command.add(new Pair("QUIT\r\n", 221));
      return command;
   }

   @NotNull
   public static final String encodeBase64(@NotNull String text) {
      Intrinsics.checkNotNullParameter(text, "text");
      Encoder var10000 = java.util.Base64.getEncoder();
      Charset var3 = Charsets.UTF_8;
      boolean var4 = false;
      byte[] var10001 = text.getBytes(var3);
      Intrinsics.checkNotNullExpressionValue(var10001, "(this as java.lang.String).getBytes(charset)");
      String var1 = var10000.encodeToString(var10001);
      Intrinsics.checkNotNullExpressionValue(var1, "getEncoder().encodeToString(text.toByteArray())");
      return var1;
   }

   static {
      logger = KotlinLogging.INSTANCE.logger((Function0)null.INSTANCE);
      gson = (new GsonBuilder()).registerTypeAdapter((new TypeToken<Map<String, ? extends Object>>() {
      }).getType(), new MapDeserializerDoubleAsIntFix()).registerTypeAdapter((Type)Integer.TYPE, new IntTypeAdapter()).registerTypeAdapter((Type)Long.TYPE, new LongTypeAdapter()).disableHtmlEscaping().create();
      prettyGson = (new GsonBuilder()).registerTypeAdapter((new TypeToken<Map<String, ? extends Object>>() {
      }).getType(), new MapDeserializerDoubleAsIntFix()).registerTypeAdapter((Type)Integer.TYPE, new IntTypeAdapter()).registerTypeAdapter((Type)Long.TYPE, new LongTypeAdapter()).disableHtmlEscaping().setPrettyPrinting().create();
      storageFinalPath = "";
      workDirPath = "";
      lockMap = new LinkedHashMap<String, ReadWriteLock>() {
         protected boolean removeEldestEntry(@Nullable Entry<String, ReadWriteLock> eldest) {
            return this.size() > 1000;
         }
      };
      _licenseValid = true;
   }
}
