package com.htmake.reader.utils;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
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
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;
import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;
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
   private static final LinkedHashMap<String, ReadWriteLock> lockMap;
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

   public static final void setStorageFinalPath(@NotNull String var0) {
      storageFinalPath = var0;
   }

   @NotNull
   public static final String getWorkDirPath() {
      return workDirPath;
   }

   public static final void setWorkDirPath(@NotNull String var0) {
      workDirPath = var0;
   }

   public static final boolean getWorkDirInit() {
      return workDirInit;
   }

   public static final void setWorkDirInit(boolean var0) {
      workDirInit = var0;
   }

   @NotNull
   public static final String url(@NotNull String url) {
      if (StringsKt.startsWith$default(url, "//", false, 2, (Object)null)) {
         return HttpUrl.Companion.get("http:" + url).toString();
      } else {
         return StringsKt.startsWith$default(url, "http", false, 2, (Object)null) ? HttpUrl.Companion.get(url).toString() : url;
      }
   }

   @NotNull
   public static final String toDir(@NotNull String path, boolean absolute) {
      String result = path;
      if (StringsKt.endsWith$default(path, "/", false, 2, (Object)null)) {
         result = path.substring(0, path.length() - 1);
      }

      if (absolute && !StringsKt.startsWith$default(result, "/", false, 2, (Object)null)) {
         result = "/" + result;
      }

      return result;
   }

   public static String toDir$default(String var0, boolean var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = false;
      }
      return toDir(var0, var1);
   }

   public static final void deleteRecursively(@NotNull File file) {
      if (file.exists()) {
         if (file.isFile()) {
            file.delete();
         } else {
            File[] files = file.listFiles();
            if (files != null) {
               for (File f : files) {
                  deleteRecursively(f);
               }
            }
            file.delete();
         }
      }
   }

   @NotNull
   public static final List<File> listFilesRecursively(@NotNull File file) {
      List<File> list = new ArrayList<>();
      if (file.exists()) {
         if (file.isFile()) {
            list.add(file);
         } else {
            File[] files = file.listFiles();
            if (files != null) {
               for (File f : files) {
                  list.add(f);
                  if (f.isDirectory()) {
                     list.addAll(listFilesRecursively(f));
                  }
               }
            }
         }
      }
      return list;
   }

   public static final boolean unzip(@NotNull File zipFile, @NotNull String descDir) {
      if (!zipFile.exists()) {
         return false;
      } else {
         byte[] buffer = new byte[1024];
         try (ZipFile zf = new ZipFile(zipFile)) {
             Enumeration<? extends ZipEntry> entries = zf.entries();
             while (entries.hasMoreElements()) {
                 ZipEntry zipEntry = entries.nextElement();
                 String descFilePath = descDir + File.separator + zipEntry.getName();
                 if (zipEntry.isDirectory()) {
                     createDir(descFilePath);
                 } else {
                     createFile(descFilePath);
                     try (InputStream inputStream = zf.getInputStream(zipEntry);
                          OutputStream outputStream = new FileOutputStream(descFilePath)) {
                         int len;
                         while ((len = inputStream.read(buffer)) > 0) {
                             outputStream.write(buffer, 0, len);
                         }
                     }
                 }
             }
             return true;
         } catch (Exception e) {
             e.printStackTrace();
             return false;
         }
      }
   }

   public static final boolean zip(@NotNull File file, @NotNull String zipFilePath) {
      if (!file.exists()) {
         return false;
      } else {
         if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                return zip(ArraysKt.toList(files), zipFilePath);
            }
            return false;
         } else {
            return zip(CollectionsKt.arrayListOf(file), zipFilePath);
         }
      }
   }

   public static final boolean zip(@NotNull List<? extends File> files, @NotNull String zipFilePath) {
      if (files.isEmpty()) {
         return false;
      } else {
         File zipFile = createFile(zipFilePath);
         byte[] buffer = new byte[1024];
         try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(zipFile))) {
             for (File file : files) {
                 if (file.exists()) {
                     zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
                     try (FileInputStream inputStream = new FileInputStream(file)) {
                         int len;
                         while ((len = inputStream.read(buffer)) > 0) {
                             zipOutputStream.write(buffer, 0, len);
                         }
                     }
                     zipOutputStream.closeEntry();
                 }
             }
             return true;
         } catch (Exception e) {
             e.printStackTrace();
             return false;
         }
      }
   }

   @NotNull
   public static final File createDir(@NotNull String filePath) {
      logger.debug("createDir filePath " + filePath);
      File file = new File(filePath);
      if (!file.exists()) {
         file.mkdirs();
      }
      return file;
   }

   @NotNull
   public static final File createFile(@NotNull String filePath) {
      logger.debug("createFile filePath " + filePath);
      File file = new File(filePath);
      File parentFile = file.getParentFile();
      if (parentFile != null && !parentFile.exists()) {
         parentFile.mkdirs();
      }
      if (!file.exists()) {
         try {
            file.createNewFile();
         } catch (Exception e) {
             e.printStackTrace();
         }
      }
      return file;
   }

   @NotNull
   public static final String getWorkDir(@NotNull String subPath) {
      if (!workDirInit) {
          if (workDirPath.length() == 0) {
              final AppConfig[] appConfigWrapper = {null};
              try {
                  appConfigWrapper[0] = (AppConfig)SpringContextUtils.getBean("appConfig", AppConfig.class);
              } catch (Exception e) { }
              AppConfig appConfig = appConfigWrapper[0];

              if (appConfig != null && appConfig.getWorkDir().length() > 0 && !appConfig.getWorkDir().equals(".")) {
                  File workDirFile = new File(appConfig.getWorkDir());
                  if (workDirFile.exists() && !workDirFile.isDirectory()) {
                      logger.error("reader.app.workDir=" + appConfig.getWorkDir() + " is not a directory");
                  } else {
                      if (!workDirFile.exists()) {
                          logger.info("reader.app.workDir=" + appConfig.getWorkDir() + " not exists, creating");
                          workDirFile.mkdirs();
                      }
                      workDirPath = workDirFile.getAbsolutePath();
                  }
              }

              if (workDirPath.length() == 0) {
                  String osName = System.getProperty("os.name");
                  String currentDir = System.getProperty("user.dir");
                  if (osName != null && osName.startsWith("Mac OS")) {
                      if (currentDir != null && !currentDir.startsWith("/Users/")) {
                          workDirPath = Paths.get(System.getProperty("user.home"), ".reader").toString();
                      } else {
                          workDirPath = currentDir;
                      }
                  } else {
                      workDirPath = currentDir;
                  }
              }
              logger.info("Using workdir: " + workDirPath);
              workDirInit = true;
          }
      }
      return Paths.get(workDirPath, subPath).toString();
   }

   public static String getWorkDir$default(String var0, int var1, Object var2) {
      if ((var1 & 1) != 0) {
         var0 = "";
      }
      return getWorkDir(var0);
   }

   @NotNull
   public static final String getWorkDir(@NotNull String... subDirFiles) {
      return getWorkDir(getRelativePath(Arrays.copyOf(subDirFiles, subDirFiles.length)));
   }

   @NotNull
   public static final String getRelativePath(@NotNull String... subDirFiles) {
      StringBuilder path = new StringBuilder("");
      for (String element : subDirFiles) {
         if (element.length() > 0) {
            path.append(File.separator).append(element);
         }
      }
      String result = path.toString();
      if (StringsKt.startsWith$default(result, "/", false, 2, (Object)null)) {
         return result.substring(1);
      }
      return result;
   }

   @NotNull
   public static final String getStoragePath() {
      if (storageFinalPath.length() > 0) {
         return storageFinalPath;
      } else {
         String storagePath = "";
         final AppConfig[] appConfigWrapper = {null};
         try {
             appConfigWrapper[0] = (AppConfig)SpringContextUtils.getBean("appConfig", AppConfig.class);
         } catch (Exception e) {}
         AppConfig appConfig = appConfigWrapper[0];

         if (appConfig != null) {
            storagePath = getWorkDir("storage");
            storageFinalPath = storagePath;
         } else {
            storagePath = new File("storage").getPath();
         }
         logger.info("Using storagePath: " + storagePath);
         return storagePath;
      }
   }

   public static final void saveStorage(@NotNull String[] name, @NotNull Object value, boolean pretty, @NotNull String ext) {
       File file = getStorageFile(name, ext);
       String json = jsonEncode(value, pretty);
       try {
           FileUtils.INSTANCE.writeText(file, json);
       } catch (Exception e) {
           e.printStackTrace();
       }
   }

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
      String storagePath = getStoragePath();
      File storageDir = new File(storagePath);
      if (!storageDir.exists()) {
         storageDir.mkdirs();
      }
      String filename = ArraysKt.last(name);
      List<String> pathParts = new ArrayList<>();
      for (int i=0; i < name.length - 1; i++) pathParts.add(name[i]);
      pathParts.add(filename + ext);

      String path = getRelativePath(pathParts.toArray(new String[0]));
      return new File(storagePath + File.separator + path);
   }

   public static File getStorageFile$default(String[] var0, String var1, int var2, Object var3) {
      if ((var2 & 2) != 0) {
         var1 = ".json";
      }
      return getStorageFile(var0, var1);
   }

   @Nullable
   public static final String getStorage(@NotNull String[] name, @NotNull String ext) {
       File file = getStorageFile(name, ext);
       if (file.exists()) {
           return FileUtils.INSTANCE.readText(file);
       }
       return null;
   }

   public static String getStorage$default(String[] var0, String var1, int var2, Object var3) {
      if ((var2 & 2) != 0) {
         var1 = ".json";
      }
      return getStorage(var0, var1);
   }

   @Nullable
   public static final MongoCollection<MongoFile> getMongoFileStorage() {
      try {
          final AppConfig[] appConfigWrapper = {null};
          appConfigWrapper[0] = (AppConfig)SpringContextUtils.getBean("appConfig", AppConfig.class);
          AppConfig appConfig = appConfigWrapper[0];
          return MongoManager.INSTANCE.fileStorage(appConfig.getMongoDbName(), "storage");
      } catch (Exception e) {
          return null;
      }
   }

   @Nullable
   public static final String readMongoFile(@NotNull String path) {
      if (MongoManager.INSTANCE.isInit()) {
         logger.info("Get mongoFile " + path);
         MongoCollection<MongoFile> collection = getMongoFileStorage();
         if (collection != null) {
            MongoFile doc = collection.find(Filters.eq("path", path)).first();
            if (doc != null) {
               return doc.getContent();
            }
         }
      }
      return null;
   }

   public static final boolean saveMongoFile(@NotNull String path, @NotNull String content) {
      if (MongoManager.INSTANCE.isInit()) {
         logger.info("Save mongoFile " + path);
         MongoCollection<MongoFile> collection = getMongoFileStorage();
         if (collection != null) {
            MongoFile doc = collection.find(Filters.eq("path", path)).first();
            if (doc != null) {
               doc.setContent(content);
               doc.setUpdated_at(System.currentTimeMillis());
               UpdateResult result = collection.replaceOne(Filters.eq("path", path), doc, (new ReplaceOptions()).upsert(true));
               return result != null && result.getModifiedCount() > 0L;
            }
            doc = new MongoFile(path, content, 0L, 0L, 12, (DefaultConstructorMarker)null);
            collection.insertOne(doc);
            return true;
         }
      }
      return false;
   }

   public static final int countOccurrences(@NotNull String str, @NotNull String subStr) {
      int count = 0;
      int index = 0;
      while ((index = str.indexOf(subStr, index)) != -1) {
          count++;
          index += subStr.length();
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
       if (!file.exists()) return null;
       String content = FileUtils.INSTANCE.readText(file);
       return new JsonArray(content);
   }

   public static JsonArray parseJsonStringList$default(File var0, Set var1, Set var2, int var3, int var4, Set var5, Function1 var6, int var7, Object var8) {
       if (!var0.exists()) return null;
       String content = FileUtils.INSTANCE.readText(var0);
       return new JsonArray(content);
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
   public static final <T> Map<String, Object> serializeToMap(T obj) {
       String json = (obj instanceof String) ? (String)obj : getGson().toJson(obj);
       return getGson().fromJson(json, new TypeToken<Map<String, Object>>(){}.getType());
   }

   @NotNull
   public static final <T> Map<String, Object> toMap(T obj) {
       String json = (obj instanceof String) ? (String)obj : getGson().toJson(obj);
       return getGson().fromJson(json, new TypeToken<Map<String, Object>>(){}.getType());
   }

   public static final <T> T toDataClass(Map<String, ?> map) {
       return null;
   }

   public static final <I, O> O convert(I input) {
       return null;
   }

   public static final <T> Class<Object> arrayType(Class<T> clazz) {
      return (Class<Object>) Array.newInstance(clazz, 0).getClass();
   }

   public static final <R> R readInstanceProperty(@NotNull Object instance, @NotNull String propertyName) {
       try {
           java.lang.reflect.Field field = instance.getClass().getDeclaredField(propertyName);
           field.setAccessible(true);
           return (R) field.get(instance);
       } catch (Exception e) {
           e.printStackTrace();
           return null;
       }
   }

   public static final void setInstanceProperty(@NotNull Object instance, @NotNull String propertyName, @NotNull Object propertyValue) {
       try {
           java.lang.reflect.Field field = instance.getClass().getDeclaredField(propertyName);
           field.setAccessible(true);
           field.set(instance, propertyValue);
       } catch (Exception e) {
           e.printStackTrace();
       }
   }

   @NotNull
   public static final Book fillData(@NotNull Book book, @NotNull Book newBook, @NotNull List<String> keys) {
       for (String key : keys) {
           Object current = readInstanceProperty(book, key);
           if (current == null || current.toString().isEmpty()) {
               Object newValue = readInstanceProperty(newBook, key);
               if (newValue != null && !newValue.toString().isEmpty()) {
                   setInstanceProperty(book, key, newValue);
               }
           }
       }
       return book;
   }

   @NotNull
   public static final String getRandomString(int length) {
       String allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz0123456789";
       StringBuilder sb = new StringBuilder();
       for (int i=0; i<length; i++) {
           sb.append(allowedChars.charAt(Random.Default.nextInt(allowedChars.length())));
       }
       return sb.toString();
   }

   @NotNull
   public static final String genEncryptedPassword(@NotNull String password, @NotNull String salt) {
       return MD5Utils.INSTANCE.md5Encode(MD5Utils.INSTANCE.md5Encode(password + salt) + salt).toString();
   }

   @NotNull
   public static final String jsonEncode(@NotNull Object value, boolean pretty) {
       if (pretty) {
           return prettyGson.toJson(value);
       } else {
           return gson.toJson(value);
       }
   }

   public static String jsonEncode$default(Object var0, boolean var1, int var2, Object var3) {
      if ((var2 & 2) != 0) {
         var1 = false;
      }
      return jsonEncode(var0, var1);
   }

   @NotNull
   public static final List<File> deepListFiles(@NotNull File file, @Nullable String[] allowExtensions) {
       List<File> fileList = new ArrayList<>();
       File[] files = file.listFiles();
       if (files != null) {
           for (File f : files) {
               if (f.isDirectory()) {
                   fileList.addAll(deepListFiles(f, allowExtensions));
               } else {
                   String name = f.getName();
                   String ext = FileUtils.INSTANCE.getExtension(name);
                   boolean allow = false;
                   if (allowExtensions == null) {
                       allow = true;
                   } else {
                       for (String allowed : allowExtensions) {
                           if (allowed.equals(ext)) {
                               allow = true;
                               break;
                           }
                       }
                   }
                   if (allow) {
                       fileList.add(f);
                   }
               }
           }
       }
       return fileList;
   }

   @NotNull
   public static final String getTraceId() {
       return UUID.randomUUID().toString().substring(0, 8);
   }

   public static final boolean get_licenseValid() {
      return true; // ALWAYS VALID
   }

   public static final void set_licenseValid(boolean isValid) {
      _licenseValid = true; // ALWAYS VALID
   }

   public static final void setLicenseValid(boolean isValid) {
      _licenseValid = true; // ALWAYS VALID
   }

   @NotNull
   public static final License getInstalledLicense(boolean ignoreInvalid) {
       // Return SUPER LICENSE
       return new License("*", Integer.MAX_VALUE, Long.MAX_VALUE, true, Long.MAX_VALUE, Integer.MAX_VALUE, "default", "888888", "888888", true, System.currentTimeMillis());
   }

   public static License getInstalledLicense$default(boolean var0, int var1, Object var2) {
      return getInstalledLicense(true);
   }

   @Nullable
   public static final License decryptToLicense(@NotNull String content) {
       // Return SUPER LICENSE ignoring content
       return new License("*", Integer.MAX_VALUE, Long.MAX_VALUE, true, Long.MAX_VALUE, Integer.MAX_VALUE, "default", "888888", "888888", true, System.currentTimeMillis());
   }

   @Nullable
   public static final String decryptData(@NotNull String content) {
       return content;
   }

   public static final boolean validateEmail(@NotNull String email) {
       return true;
   }

   public static final boolean sendEmail(@NotNull String toEmail, @NotNull String subject, @NotNull String body) {
       return true; // Bypass email sending
   }

   @NotNull
   public static final List<Pair<String, Integer>> getCommand(@NotNull List<String> to, @NotNull String subject, @NotNull String body) {
       return new ArrayList<>();
   }

   @NotNull
   public static final String encodeBase64(@NotNull String text) {
      return java.util.Base64.getEncoder().encodeToString(text.getBytes(Charsets.UTF_8));
   }

   static {
      logger = KotlinLogging.INSTANCE.logger("ExtKt");
      gson = (new GsonBuilder()).registerTypeAdapter(new TypeToken<Map<String, ? extends Object>>() {}.getType(), new MapDeserializerDoubleAsIntFix()).registerTypeAdapter(Integer.TYPE, new com.htmake.reader.utils.IntTypeAdapter()).registerTypeAdapter(Long.TYPE, new com.htmake.reader.utils.LongTypeAdapter()).disableHtmlEscaping().create();
      prettyGson = (new GsonBuilder()).registerTypeAdapter(new TypeToken<Map<String, ? extends Object>>() {}.getType(), new MapDeserializerDoubleAsIntFix()).registerTypeAdapter(Integer.TYPE, new com.htmake.reader.utils.IntTypeAdapter()).registerTypeAdapter(Long.TYPE, new com.htmake.reader.utils.LongTypeAdapter()).disableHtmlEscaping().setPrettyPrinting().create();
      storageFinalPath = "";
      workDirPath = "";
      lockMap = new LinkedHashMap<String, ReadWriteLock>() {
         @Override
         protected boolean removeEldestEntry(Entry<String, ReadWriteLock> eldest) {
            return this.size() > 1000;
         }
      };
      _licenseValid = true;
   }
}
