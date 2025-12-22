package io.legado.app.data.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.Predicate;
import com.jayway.jsonpath.ReadContext;
import com.script.SimpleBindings;
import io.legado.app.help.http.StrResponse;
import io.legado.app.model.DebugLog;
import io.legado.app.model.analyzeRule.QueryTTF;
import io.legado.app.utils.GsonExtensionsKt;
import io.legado.app.utils.JsonExtensionsKt;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jsoup.Connection.Response;

@JsonIgnoreProperties({"headerMap", "source", "_userNameSpace", "userNameSpace"})
@Metadata(
   mv = {1, 5, 1},
   k = 1,
   xi = 48,
   d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b(\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0087\b\u0018\u0000 P2\u00020\u0001:\u0001PB\u008d\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0003¢\u0006\u0002\u0010\u0011J\t\u00103\u001a\u00020\u0003HÆ\u0003J\u0010\u00104\u001a\u0004\u0018\u00010\u000eHÆ\u0003¢\u0006\u0002\u0010\u001cJ\u000b\u00105\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u00106\u001a\u00020\u0003HÆ\u0003J\t\u00107\u001a\u00020\u0005HÆ\u0003J\t\u00108\u001a\u00020\u0005HÆ\u0003J\u000b\u00109\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010:\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010;\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010<\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010=\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010>\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0096\u0001\u0010?\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0010\u001a\u00020\u0003HÆ\u0001¢\u0006\u0002\u0010@J\u0013\u0010A\u001a\u00020\u000e2\b\u0010B\u001a\u0004\u0018\u00010CHÖ\u0003J\b\u0010D\u001a\u00020\u0005H\u0016J\n\u0010E\u001a\u0004\u0018\u00010\u001aH\u0016J\b\u0010F\u001a\u00020\u0005H\u0016J\b\u0010G\u001a\u00020\u0005H\u0016J\t\u0010H\u001a\u00020IHÖ\u0001J\u0010\u0010J\u001a\u00020K2\b\u0010L\u001a\u0004\u0018\u00010\u001aJ\u000e\u0010M\u001a\u00020K2\u0006\u0010N\u001a\u00020\u0005J\t\u0010O\u001a\u00020\u0005HÖ\u0001R\u000e\u0010\u0012\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\b\u001a\u0004\u0018\u00010\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0014\"\u0004\b\u0018\u0010\u0016R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0096\u000e¢\u0006\u0010\n\u0002\u0010\u001f\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0014\"\u0004\b!\u0010\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u001c\u0010\f\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u0014\"\u0004\b%\u0010\u0016R\u001a\u0010\u0010\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010#\"\u0004\b'\u0010(R\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\u0014\"\u0004\b*\u0010\u0016R\u001c\u0010\n\u001a\u0004\u0018\u00010\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010\u0014\"\u0004\b,\u0010\u0016R\u001c\u0010\t\u001a\u0004\u0018\u00010\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010\u0014\"\u0004\b.\u0010\u0016R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010\u0014\"\u0004\b0\u0010\u0016R\u001a\u0010\u0006\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u0010\u0014\"\u0004\b2\u0010\u0016¨\u0006Q"},
   d2 = {"Lio/legado/app/data/entities/HttpTTS;", "Lio/legado/app/data/entities/BaseSource;", "id", "", "name", "", "url", "contentType", "concurrentRate", "loginUrl", "loginUi", "header", "jsLib", "enabledCookieJar", "", "loginCheckJs", "lastUpdateTime", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;J)V", "_userNameSpace", "getConcurrentRate", "()Ljava/lang/String;", "setConcurrentRate", "(Ljava/lang/String;)V", "getContentType", "setContentType", "debugLog", "Lio/legado/app/model/DebugLog;", "getEnabledCookieJar", "()Ljava/lang/Boolean;", "setEnabledCookieJar", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "getHeader", "setHeader", "getId", "()J", "getJsLib", "setJsLib", "getLastUpdateTime", "setLastUpdateTime", "(J)V", "getLoginCheckJs", "setLoginCheckJs", "getLoginUi", "setLoginUi", "getLoginUrl", "setLoginUrl", "getName", "setName", "getUrl", "setUrl", "component1", "component10", "component11", "component12", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;J)Lio/legado/app/data/entities/HttpTTS;", "equals", "other", "", "getKey", "getLogger", "getTag", "getUserNameSpace", "hashCode", "", "setLogger", "", "logger", "setUserNameSpace", "nameSpace", "toString", "Companion", "reader-pro"}
)
public final class HttpTTS implements BaseSource {
   @NotNull
   public static final HttpTTS.Companion Companion = new HttpTTS.Companion((DefaultConstructorMarker)null);
   private final long id;
   @NotNull
   private String name;
   @NotNull
   private String url;
   @Nullable
   private String contentType;
   @Nullable
   private String concurrentRate;
   @Nullable
   private String loginUrl;
   @Nullable
   private String loginUi;
   @Nullable
   private String header;
   @Nullable
   private String jsLib;
   @Nullable
   private Boolean enabledCookieJar;
   @Nullable
   private String loginCheckJs;
   private long lastUpdateTime;
   @NotNull
   private transient String _userNameSpace;
   @Nullable
   private transient DebugLog debugLog;

   public HttpTTS(long id, @NotNull String name, @NotNull String url, @Nullable String contentType, @Nullable String concurrentRate, @Nullable String loginUrl, @Nullable String loginUi, @Nullable String header, @Nullable String jsLib, @Nullable Boolean enabledCookieJar, @Nullable String loginCheckJs, long lastUpdateTime) {
      Intrinsics.checkNotNullParameter(name, "name");
      Intrinsics.checkNotNullParameter(url, "url");
      super();
      this.id = id;
      this.name = name;
      this.url = url;
      this.contentType = contentType;
      this.concurrentRate = concurrentRate;
      this.loginUrl = loginUrl;
      this.loginUi = loginUi;
      this.header = header;
      this.jsLib = jsLib;
      this.enabledCookieJar = enabledCookieJar;
      this.loginCheckJs = loginCheckJs;
      this.lastUpdateTime = lastUpdateTime;
      this._userNameSpace = "";
   }

   // $FF: synthetic method
   public HttpTTS(long var1, String var3, String var4, String var5, String var6, String var7, String var8, String var9, String var10, Boolean var11, String var12, long var13, int var15, DefaultConstructorMarker var16) {
      if ((var15 & 1) != 0) {
         var1 = System.currentTimeMillis();
      }

      if ((var15 & 2) != 0) {
         var3 = "";
      }

      if ((var15 & 4) != 0) {
         var4 = "";
      }

      if ((var15 & 8) != 0) {
         var5 = null;
      }

      if ((var15 & 16) != 0) {
         var6 = "0";
      }

      if ((var15 & 32) != 0) {
         var7 = null;
      }

      if ((var15 & 64) != 0) {
         var8 = null;
      }

      if ((var15 & 128) != 0) {
         var9 = null;
      }

      if ((var15 & 256) != 0) {
         var10 = null;
      }

      if ((var15 & 512) != 0) {
         var11 = false;
      }

      if ((var15 & 1024) != 0) {
         var12 = null;
      }

      if ((var15 & 2048) != 0) {
         var13 = System.currentTimeMillis();
      }

      this(var1, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
   }

   public final long getId() {
      return this.id;
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
   public final String getUrl() {
      return this.url;
   }

   public final void setUrl(@NotNull String <set-?>) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.url = var1;
   }

   @Nullable
   public final String getContentType() {
      return this.contentType;
   }

   public final void setContentType(@Nullable String <set-?>) {
      this.contentType = var1;
   }

   @Nullable
   public String getConcurrentRate() {
      return this.concurrentRate;
   }

   public void setConcurrentRate(@Nullable String <set-?>) {
      this.concurrentRate = var1;
   }

   @Nullable
   public String getLoginUrl() {
      return this.loginUrl;
   }

   public void setLoginUrl(@Nullable String <set-?>) {
      this.loginUrl = var1;
   }

   @Nullable
   public String getLoginUi() {
      return this.loginUi;
   }

   public void setLoginUi(@Nullable String <set-?>) {
      this.loginUi = var1;
   }

   @Nullable
   public String getHeader() {
      return this.header;
   }

   public void setHeader(@Nullable String <set-?>) {
      this.header = var1;
   }

   @Nullable
   public final String getJsLib() {
      return this.jsLib;
   }

   public final void setJsLib(@Nullable String <set-?>) {
      this.jsLib = var1;
   }

   @Nullable
   public Boolean getEnabledCookieJar() {
      return this.enabledCookieJar;
   }

   public void setEnabledCookieJar(@Nullable Boolean <set-?>) {
      this.enabledCookieJar = var1;
   }

   @Nullable
   public final String getLoginCheckJs() {
      return this.loginCheckJs;
   }

   public final void setLoginCheckJs(@Nullable String <set-?>) {
      this.loginCheckJs = var1;
   }

   public final long getLastUpdateTime() {
      return this.lastUpdateTime;
   }

   public final void setLastUpdateTime(long <set-?>) {
      this.lastUpdateTime = var1;
   }

   public final void setUserNameSpace(@NotNull String nameSpace) {
      Intrinsics.checkNotNullParameter(nameSpace, "nameSpace");
      this._userNameSpace = nameSpace;
   }

   @NotNull
   public String getUserNameSpace() {
      return this._userNameSpace;
   }

   public final void setLogger(@Nullable DebugLog logger) {
      this.debugLog = logger;
   }

   @Nullable
   public DebugLog getLogger() {
      return this.debugLog;
   }

   @NotNull
   public String getTag() {
      return this.name;
   }

   @NotNull
   public String getKey() {
      return Intrinsics.stringPlus("httpTts:", this.id);
   }

   @Nullable
   public Object evalJS(@NotNull String jsStr, @NotNull Function1<? super SimpleBindings, Unit> bindingsConfig) throws Exception {
      return BaseSource.DefaultImpls.evalJS(this, jsStr, bindingsConfig);
   }

   @Nullable
   public byte[] aesBase64DecodeToByteArray(@NotNull String str, @NotNull String key, @NotNull String transformation, @NotNull String iv) {
      return BaseSource.DefaultImpls.aesBase64DecodeToByteArray((BaseSource)this, str, key, transformation, iv);
   }

   @Nullable
   public String aesBase64DecodeToString(@NotNull String str, @NotNull String key, @NotNull String transformation, @NotNull String iv) {
      return BaseSource.DefaultImpls.aesBase64DecodeToString((BaseSource)this, str, key, transformation, iv);
   }

   @Nullable
   public String aesDecodeArgsBase64Str(@NotNull String data, @NotNull String key, @NotNull String mode, @NotNull String padding, @NotNull String iv) {
      return BaseSource.DefaultImpls.aesDecodeArgsBase64Str((BaseSource)this, data, key, mode, padding, iv);
   }

   @Nullable
   public byte[] aesDecodeToByteArray(@NotNull String str, @NotNull String key, @NotNull String transformation, @NotNull String iv) {
      return BaseSource.DefaultImpls.aesDecodeToByteArray((BaseSource)this, str, key, transformation, iv);
   }

   @Nullable
   public String aesDecodeToString(@NotNull String str, @NotNull String key, @NotNull String transformation, @NotNull String iv) {
      return BaseSource.DefaultImpls.aesDecodeToString((BaseSource)this, str, key, transformation, iv);
   }

   @Nullable
   public String aesEncodeArgsBase64Str(@NotNull String data, @NotNull String key, @NotNull String mode, @NotNull String padding, @NotNull String iv) {
      return BaseSource.DefaultImpls.aesEncodeArgsBase64Str((BaseSource)this, data, key, mode, padding, iv);
   }

   @Nullable
   public byte[] aesEncodeToBase64ByteArray(@NotNull String data, @NotNull String key, @NotNull String transformation, @NotNull String iv) {
      return BaseSource.DefaultImpls.aesEncodeToBase64ByteArray((BaseSource)this, data, key, transformation, iv);
   }

   @Nullable
   public String aesEncodeToBase64String(@NotNull String data, @NotNull String key, @NotNull String transformation, @NotNull String iv) {
      return BaseSource.DefaultImpls.aesEncodeToBase64String((BaseSource)this, data, key, transformation, iv);
   }

   @Nullable
   public byte[] aesEncodeToByteArray(@NotNull String data, @NotNull String key, @NotNull String transformation, @NotNull String iv) {
      return BaseSource.DefaultImpls.aesEncodeToByteArray((BaseSource)this, data, key, transformation, iv);
   }

   @Nullable
   public String aesEncodeToString(@NotNull String data, @NotNull String key, @NotNull String transformation, @NotNull String iv) {
      return BaseSource.DefaultImpls.aesEncodeToString((BaseSource)this, data, key, transformation, iv);
   }

   @Nullable
   public String ajax(@NotNull String urlStr) {
      return BaseSource.DefaultImpls.ajax((BaseSource)this, urlStr);
   }

   @NotNull
   public StrResponse[] ajaxAll(@NotNull String[] urlList) {
      return BaseSource.DefaultImpls.ajaxAll((BaseSource)this, urlList);
   }

   @NotNull
   public String androidId() {
      return BaseSource.DefaultImpls.androidId((BaseSource)this);
   }

   @NotNull
   public String base64Decode(@NotNull String str) {
      return BaseSource.DefaultImpls.base64Decode((BaseSource)this, str);
   }

   @NotNull
   public String base64Decode(@NotNull String str, int flags) {
      return BaseSource.DefaultImpls.base64Decode((BaseSource)this, str, flags);
   }

   @Nullable
   public byte[] base64DecodeToByteArray(@Nullable String str) {
      return BaseSource.DefaultImpls.base64DecodeToByteArray((BaseSource)this, str);
   }

   @Nullable
   public byte[] base64DecodeToByteArray(@Nullable String str, int flags) {
      return BaseSource.DefaultImpls.base64DecodeToByteArray((BaseSource)this, str, flags);
   }

   @Nullable
   public String base64Encode(@NotNull String str) {
      return BaseSource.DefaultImpls.base64Encode((BaseSource)this, str);
   }

   @Nullable
   public String base64Encode(@NotNull String str, int flags) {
      return BaseSource.DefaultImpls.base64Encode((BaseSource)this, str, flags);
   }

   @Nullable
   public String cacheFile(@NotNull String urlStr) {
      return BaseSource.DefaultImpls.cacheFile((BaseSource)this, urlStr);
   }

   @Nullable
   public String cacheFile(@NotNull String urlStr, int saveTime) {
      return BaseSource.DefaultImpls.cacheFile((BaseSource)this, urlStr, saveTime);
   }

   @NotNull
   public StrResponse connect(@NotNull String urlStr) {
      return BaseSource.DefaultImpls.connect((BaseSource)this, urlStr);
   }

   @NotNull
   public StrResponse connect(@NotNull String urlStr, @Nullable String header) {
      return BaseSource.DefaultImpls.connect((BaseSource)this, urlStr, header);
   }

   public void deleteFile(@NotNull String path) {
      BaseSource.DefaultImpls.deleteFile((BaseSource)this, path);
   }

   @Nullable
   public String desBase64DecodeToString(@NotNull String data, @NotNull String key, @NotNull String transformation, @NotNull String iv) {
      return BaseSource.DefaultImpls.desBase64DecodeToString((BaseSource)this, data, key, transformation, iv);
   }

   @Nullable
   public String desDecodeToString(@NotNull String data, @NotNull String key, @NotNull String transformation, @NotNull String iv) {
      return BaseSource.DefaultImpls.desDecodeToString((BaseSource)this, data, key, transformation, iv);
   }

   @Nullable
   public String desEncodeToBase64String(@NotNull String data, @NotNull String key, @NotNull String transformation, @NotNull String iv) {
      return BaseSource.DefaultImpls.desEncodeToBase64String((BaseSource)this, data, key, transformation, iv);
   }

   @Nullable
   public String desEncodeToString(@NotNull String data, @NotNull String key, @NotNull String transformation, @NotNull String iv) {
      return BaseSource.DefaultImpls.desEncodeToString((BaseSource)this, data, key, transformation, iv);
   }

   @Nullable
   public String digestBase64Str(@NotNull String data, @NotNull String algorithm) {
      return BaseSource.DefaultImpls.digestBase64Str((BaseSource)this, data, algorithm);
   }

   @Nullable
   public String digestHex(@NotNull String data, @NotNull String algorithm) {
      return BaseSource.DefaultImpls.digestHex((BaseSource)this, data, algorithm);
   }

   @NotNull
   public String downloadFile(@NotNull String content, @NotNull String url) {
      return BaseSource.DefaultImpls.downloadFile((BaseSource)this, content, url);
   }

   @NotNull
   public String encodeURI(@NotNull String str) {
      return BaseSource.DefaultImpls.encodeURI((BaseSource)this, str);
   }

   @NotNull
   public String encodeURI(@NotNull String str, @NotNull String enc) {
      return BaseSource.DefaultImpls.encodeURI((BaseSource)this, str, enc);
   }

   @NotNull
   public Response get(@NotNull String urlStr, @NotNull Map<String, String> headers) {
      return BaseSource.DefaultImpls.get((BaseSource)this, urlStr, headers);
   }

   @NotNull
   public String getCookie(@NotNull String tag, @Nullable String key) {
      return BaseSource.DefaultImpls.getCookie((BaseSource)this, tag, key);
   }

   @NotNull
   public File getFile(@NotNull String path) {
      return BaseSource.DefaultImpls.getFile((BaseSource)this, path);
   }

   @NotNull
   public HashMap<String, String> getHeaderMap(boolean hasLoginHeader) {
      return BaseSource.DefaultImpls.getHeaderMap(this, hasLoginHeader);
   }

   @Nullable
   public String getLoginHeader() {
      return BaseSource.DefaultImpls.getLoginHeader(this);
   }

   @Nullable
   public Map<String, String> getLoginHeaderMap() {
      return BaseSource.DefaultImpls.getLoginHeaderMap(this);
   }

   @Nullable
   public String getLoginInfo() {
      return BaseSource.DefaultImpls.getLoginInfo(this);
   }

   @Nullable
   public Map<String, String> getLoginInfoMap() {
      return BaseSource.DefaultImpls.getLoginInfoMap(this);
   }

   @Nullable
   public String getLoginJs() {
      return BaseSource.DefaultImpls.getLoginJs(this);
   }

   @Nullable
   public BaseSource getSource() {
      return BaseSource.DefaultImpls.getSource(this);
   }

   @NotNull
   public String getTxtInFolder(@NotNull String unzipPath) {
      return BaseSource.DefaultImpls.getTxtInFolder((BaseSource)this, unzipPath);
   }

   @Nullable
   public String getVariable() {
      return BaseSource.DefaultImpls.getVariable(this);
   }

   @Nullable
   public byte[] getZipByteArrayContent(@NotNull String url, @NotNull String path) {
      return BaseSource.DefaultImpls.getZipByteArrayContent((BaseSource)this, url, path);
   }

   @NotNull
   public String getZipStringContent(@NotNull String url, @NotNull String path) {
      return BaseSource.DefaultImpls.getZipStringContent((BaseSource)this, url, path);
   }

   @NotNull
   public String getZipStringContent(@NotNull String url, @NotNull String path, @NotNull String charsetName) {
      return BaseSource.DefaultImpls.getZipStringContent((BaseSource)this, url, path, charsetName);
   }

   @NotNull
   public Response head(@NotNull String urlStr, @NotNull Map<String, String> headers) {
      return BaseSource.DefaultImpls.head((BaseSource)this, urlStr, headers);
   }

   @NotNull
   public String htmlFormat(@NotNull String str) {
      return BaseSource.DefaultImpls.htmlFormat((BaseSource)this, str);
   }

   @NotNull
   public String importScript(@NotNull String path) {
      return BaseSource.DefaultImpls.importScript((BaseSource)this, path);
   }

   @NotNull
   public String log(@NotNull String msg) {
      return BaseSource.DefaultImpls.log((BaseSource)this, msg);
   }

   public void logType(@Nullable Object any) {
      BaseSource.DefaultImpls.logType((BaseSource)this, any);
   }

   public void login() {
      BaseSource.DefaultImpls.login(this);
   }

   public void longToast(@Nullable Object msg) {
      BaseSource.DefaultImpls.longToast((BaseSource)this, msg);
   }

   @NotNull
   public String md5Encode(@NotNull String str) {
      return BaseSource.DefaultImpls.md5Encode((BaseSource)this, str);
   }

   @NotNull
   public String md5Encode16(@NotNull String str) {
      return BaseSource.DefaultImpls.md5Encode16((BaseSource)this, str);
   }

   @NotNull
   public Response post(@NotNull String urlStr, @NotNull String body, @NotNull Map<String, String> headers) {
      return BaseSource.DefaultImpls.post((BaseSource)this, urlStr, body, headers);
   }

   public void putLoginHeader(@NotNull String header) {
      BaseSource.DefaultImpls.putLoginHeader(this, header);
   }

   public boolean putLoginInfo(@NotNull String info) {
      return BaseSource.DefaultImpls.putLoginInfo(this, info);
   }

   @Nullable
   public QueryTTF queryBase64TTF(@Nullable String base64) {
      return BaseSource.DefaultImpls.queryBase64TTF((BaseSource)this, base64);
   }

   @Nullable
   public QueryTTF queryTTF(@Nullable String str) {
      return BaseSource.DefaultImpls.queryTTF((BaseSource)this, str);
   }

   @NotNull
   public String randomUUID() {
      return BaseSource.DefaultImpls.randomUUID((BaseSource)this);
   }

   @Nullable
   public byte[] readFile(@NotNull String path) {
      return BaseSource.DefaultImpls.readFile((BaseSource)this, path);
   }

   @NotNull
   public String readTxtFile(@NotNull String path) {
      return BaseSource.DefaultImpls.readTxtFile((BaseSource)this, path);
   }

   @NotNull
   public String readTxtFile(@NotNull String path, @NotNull String charsetName) {
      return BaseSource.DefaultImpls.readTxtFile((BaseSource)this, path, charsetName);
   }

   public void removeLoginHeader() {
      BaseSource.DefaultImpls.removeLoginHeader(this);
   }

   public void removeLoginInfo() {
      BaseSource.DefaultImpls.removeLoginInfo(this);
   }

   @NotNull
   public String replaceFont(@NotNull String text, @Nullable QueryTTF font1, @Nullable QueryTTF font2) {
      return BaseSource.DefaultImpls.replaceFont((BaseSource)this, text, font1, font2);
   }

   public void setVariable(@Nullable String variable) {
      BaseSource.DefaultImpls.setVariable(this, variable);
   }

   @NotNull
   public String timeFormat(long time) {
      return BaseSource.DefaultImpls.timeFormat((BaseSource)this, time);
   }

   @Nullable
   public String timeFormatUTC(long time, @NotNull String format, int sh) {
      return BaseSource.DefaultImpls.timeFormatUTC((BaseSource)this, time, format, sh);
   }

   public void toast(@Nullable Object msg) {
      BaseSource.DefaultImpls.toast((BaseSource)this, msg);
   }

   @Nullable
   public String tripleDESDecodeArgsBase64Str(@NotNull String data, @NotNull String key, @NotNull String mode, @NotNull String padding, @NotNull String iv) {
      return BaseSource.DefaultImpls.tripleDESDecodeArgsBase64Str((BaseSource)this, data, key, mode, padding, iv);
   }

   @Nullable
   public String tripleDESDecodeStr(@NotNull String data, @NotNull String key, @NotNull String mode, @NotNull String padding, @NotNull String iv) {
      return BaseSource.DefaultImpls.tripleDESDecodeStr((BaseSource)this, data, key, mode, padding, iv);
   }

   @Nullable
   public String tripleDESEncodeArgsBase64Str(@NotNull String data, @NotNull String key, @NotNull String mode, @NotNull String padding, @NotNull String iv) {
      return BaseSource.DefaultImpls.tripleDESEncodeArgsBase64Str((BaseSource)this, data, key, mode, padding, iv);
   }

   @Nullable
   public String tripleDESEncodeBase64Str(@NotNull String data, @NotNull String key, @NotNull String mode, @NotNull String padding, @NotNull String iv) {
      return BaseSource.DefaultImpls.tripleDESEncodeBase64Str((BaseSource)this, data, key, mode, padding, iv);
   }

   @NotNull
   public String unzipFile(@NotNull String zipPath) {
      return BaseSource.DefaultImpls.unzipFile((BaseSource)this, zipPath);
   }

   @NotNull
   public String utf8ToGbk(@NotNull String str) {
      return BaseSource.DefaultImpls.utf8ToGbk((BaseSource)this, str);
   }

   @Nullable
   public String webView(@Nullable String html, @Nullable String url, @Nullable String js) {
      return BaseSource.DefaultImpls.webView((BaseSource)this, html, url, js);
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
      return this.url;
   }

   @Nullable
   public final String component4() {
      return this.contentType;
   }

   @Nullable
   public final String component5() {
      return this.getConcurrentRate();
   }

   @Nullable
   public final String component6() {
      return this.getLoginUrl();
   }

   @Nullable
   public final String component7() {
      return this.getLoginUi();
   }

   @Nullable
   public final String component8() {
      return this.getHeader();
   }

   @Nullable
   public final String component9() {
      return this.jsLib;
   }

   @Nullable
   public final Boolean component10() {
      return this.getEnabledCookieJar();
   }

   @Nullable
   public final String component11() {
      return this.loginCheckJs;
   }

   public final long component12() {
      return this.lastUpdateTime;
   }

   @NotNull
   public final HttpTTS copy(long id, @NotNull String name, @NotNull String url, @Nullable String contentType, @Nullable String concurrentRate, @Nullable String loginUrl, @Nullable String loginUi, @Nullable String header, @Nullable String jsLib, @Nullable Boolean enabledCookieJar, @Nullable String loginCheckJs, long lastUpdateTime) {
      Intrinsics.checkNotNullParameter(name, "name");
      Intrinsics.checkNotNullParameter(url, "url");
      return new HttpTTS(id, name, url, contentType, concurrentRate, loginUrl, loginUi, header, jsLib, enabledCookieJar, loginCheckJs, lastUpdateTime);
   }

   // $FF: synthetic method
   public static HttpTTS copy$default(HttpTTS var0, long var1, String var3, String var4, String var5, String var6, String var7, String var8, String var9, String var10, Boolean var11, String var12, long var13, int var15, Object var16) {
      if ((var15 & 1) != 0) {
         var1 = var0.id;
      }

      if ((var15 & 2) != 0) {
         var3 = var0.name;
      }

      if ((var15 & 4) != 0) {
         var4 = var0.url;
      }

      if ((var15 & 8) != 0) {
         var5 = var0.contentType;
      }

      if ((var15 & 16) != 0) {
         var6 = var0.getConcurrentRate();
      }

      if ((var15 & 32) != 0) {
         var7 = var0.getLoginUrl();
      }

      if ((var15 & 64) != 0) {
         var8 = var0.getLoginUi();
      }

      if ((var15 & 128) != 0) {
         var9 = var0.getHeader();
      }

      if ((var15 & 256) != 0) {
         var10 = var0.jsLib;
      }

      if ((var15 & 512) != 0) {
         var11 = var0.getEnabledCookieJar();
      }

      if ((var15 & 1024) != 0) {
         var12 = var0.loginCheckJs;
      }

      if ((var15 & 2048) != 0) {
         var13 = var0.lastUpdateTime;
      }

      return var0.copy(var1, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13);
   }

   @NotNull
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append("HttpTTS(id=").append(this.id).append(", name=").append(this.name).append(", url=").append(this.url).append(", contentType=").append(this.contentType).append(", concurrentRate=").append(this.getConcurrentRate()).append(", loginUrl=").append(this.getLoginUrl()).append(", loginUi=").append(this.getLoginUi()).append(", header=").append(this.getHeader()).append(", jsLib=").append(this.jsLib).append(", enabledCookieJar=").append(this.getEnabledCookieJar()).append(", loginCheckJs=").append(this.loginCheckJs).append(", lastUpdateTime=");
      var1.append(this.lastUpdateTime).append(')');
      return var1.toString();
   }

   public int hashCode() {
      int result = Long.hashCode(this.id);
      result = result * 31 + this.name.hashCode();
      result = result * 31 + this.url.hashCode();
      result = result * 31 + (this.contentType == null ? 0 : this.contentType.hashCode());
      result = result * 31 + (this.getConcurrentRate() == null ? 0 : this.getConcurrentRate().hashCode());
      result = result * 31 + (this.getLoginUrl() == null ? 0 : this.getLoginUrl().hashCode());
      result = result * 31 + (this.getLoginUi() == null ? 0 : this.getLoginUi().hashCode());
      result = result * 31 + (this.getHeader() == null ? 0 : this.getHeader().hashCode());
      result = result * 31 + (this.jsLib == null ? 0 : this.jsLib.hashCode());
      result = result * 31 + (this.getEnabledCookieJar() == null ? 0 : this.getEnabledCookieJar().hashCode());
      result = result * 31 + (this.loginCheckJs == null ? 0 : this.loginCheckJs.hashCode());
      result = result * 31 + Long.hashCode(this.lastUpdateTime);
      return result;
   }

   public boolean equals(@Nullable Object other) {
      if (this == other) {
         return true;
      } else if (!(other instanceof HttpTTS)) {
         return false;
      } else {
         HttpTTS var2 = (HttpTTS)other;
         if (this.id != var2.id) {
            return false;
         } else if (!Intrinsics.areEqual(this.name, var2.name)) {
            return false;
         } else if (!Intrinsics.areEqual(this.url, var2.url)) {
            return false;
         } else if (!Intrinsics.areEqual(this.contentType, var2.contentType)) {
            return false;
         } else if (!Intrinsics.areEqual(this.getConcurrentRate(), var2.getConcurrentRate())) {
            return false;
         } else if (!Intrinsics.areEqual(this.getLoginUrl(), var2.getLoginUrl())) {
            return false;
         } else if (!Intrinsics.areEqual(this.getLoginUi(), var2.getLoginUi())) {
            return false;
         } else if (!Intrinsics.areEqual(this.getHeader(), var2.getHeader())) {
            return false;
         } else if (!Intrinsics.areEqual(this.jsLib, var2.jsLib)) {
            return false;
         } else if (!Intrinsics.areEqual(this.getEnabledCookieJar(), var2.getEnabledCookieJar())) {
            return false;
         } else if (!Intrinsics.areEqual(this.loginCheckJs, var2.loginCheckJs)) {
            return false;
         } else {
            return this.lastUpdateTime == var2.lastUpdateTime;
         }
      }
   }

   public HttpTTS() {
      this(0L, (String)null, (String)null, (String)null, (String)null, (String)null, (String)null, (String)null, (String)null, (Boolean)null, (String)null, 0L, 4095, (DefaultConstructorMarker)null);
   }

   @Metadata(
      mv = {1, 5, 1},
      k = 1,
      xi = 48,
      d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J$\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u0007ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\b\u0010\tJ4\u0010\n\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u00050\u000bj\b\u0012\u0004\u0012\u00020\u0005`\f0\u00042\u0006\u0010\r\u001a\u00020\u0007ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\u000e\u0010\tJ$\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0010\u001a\u00020\u0011ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\u0012\u0010\u0013\u0082\u0002\u000f\n\u0002\b\u0019\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006\u0014"},
      d2 = {"Lio/legado/app/data/entities/HttpTTS$Companion;", "", "()V", "fromJson", "Lkotlin/Result;", "Lio/legado/app/data/entities/HttpTTS;", "json", "", "fromJson-IoAF18A", "(Ljava/lang/String;)Ljava/lang/Object;", "fromJsonArray", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "jsonArray", "fromJsonArray-IoAF18A", "fromJsonDoc", "doc", "Lcom/jayway/jsonpath/DocumentContext;", "fromJsonDoc-IoAF18A", "(Lcom/jayway/jsonpath/DocumentContext;)Ljava/lang/Object;", "reader-pro"}
   )
   public static final class Companion {
      private Companion() {
      }

      @NotNull
      public final Object fromJsonDoc_IoAF18A/* $FF was: fromJsonDoc-IoAF18A*/(@NotNull DocumentContext doc) {
         Intrinsics.checkNotNullParameter(doc, "doc");
         boolean var2 = false;

         Object var3;
         try {
            kotlin.Result.Companion var8 = Result.Companion;
            int var4 = false;
            Object loginUi = doc.read("$.loginUi", new Predicate[0]);
            Long var12 = JsonExtensionsKt.readLong((ReadContext)doc, "$.id");
            long var10002 = var12 == null ? System.currentTimeMillis() : var12;
            String var10003 = JsonExtensionsKt.readString((ReadContext)doc, "$.name");
            Intrinsics.checkNotNull(var10003);
            String var10004 = JsonExtensionsKt.readString((ReadContext)doc, "$.url");
            Intrinsics.checkNotNull(var10004);
            HttpTTS var9 = new HttpTTS(var10002, var10003, var10004, JsonExtensionsKt.readString((ReadContext)doc, "$.contentType"), JsonExtensionsKt.readString((ReadContext)doc, "$.concurrentRate"), JsonExtensionsKt.readString((ReadContext)doc, "$.loginUrl"), loginUi instanceof List ? GsonExtensionsKt.getGSON().toJson(loginUi) : (loginUi == null ? null : loginUi.toString()), JsonExtensionsKt.readString((ReadContext)doc, "$.header"), (String)null, (Boolean)null, JsonExtensionsKt.readString((ReadContext)doc, "$.loginCheckJs"), 0L, 2816, (DefaultConstructorMarker)null);
            boolean var11 = false;
            var3 = Result.constructor-impl(var9);
         } catch (Throwable var7) {
            kotlin.Result.Companion var5 = Result.Companion;
            boolean var6 = false;
            var3 = Result.constructor-impl(ResultKt.createFailure(var7));
         }

         return var3;
      }

      @NotNull
      public final Object fromJson_IoAF18A/* $FF was: fromJson-IoAF18A*/(@NotNull String json) {
         Intrinsics.checkNotNullParameter(json, "json");
         DocumentContext var2 = JsonExtensionsKt.getJsonPath().parse(json);
         Intrinsics.checkNotNullExpressionValue(var2, "jsonPath.parse(json)");
         return this.fromJsonDoc-IoAF18A(var2);
      }

      @NotNull
      public final Object fromJsonArray_IoAF18A/* $FF was: fromJsonArray-IoAF18A*/(@NotNull String jsonArray) {
         Intrinsics.checkNotNullParameter(jsonArray, "jsonArray");
         boolean var2 = false;

         Object var3;
         try {
            kotlin.Result.Companion var20 = Result.Companion;
            int var4 = false;
            boolean var21 = false;
            ArrayList sources = new ArrayList();
            List doc = (List)JsonExtensionsKt.getJsonPath().parse(jsonArray).read("$", new Predicate[0]);
            Intrinsics.checkNotNullExpressionValue(doc, "doc");
            Iterable $this$forEach$iv = (Iterable)doc;
            int $i$f$forEach = false;
            Iterator var9 = $this$forEach$iv.iterator();

            while(var9.hasNext()) {
               Object element$iv = var9.next();
               int var12 = false;
               DocumentContext jsonItem = JsonExtensionsKt.getJsonPath().parse(element$iv);
               HttpTTS.Companion var10000 = HttpTTS.Companion;
               Intrinsics.checkNotNullExpressionValue(jsonItem, "jsonItem");
               Object var14 = var10000.fromJsonDoc-IoAF18A(jsonItem);
               boolean var15 = false;
               ResultKt.throwOnFailure(var14);
               var15 = false;
               boolean var16 = false;
               HttpTTS source = (HttpTTS)var14;
               int var18 = false;
               sources.add(source);
            }

            var21 = false;
            var3 = Result.constructor-impl(sources);
         } catch (Throwable var19) {
            kotlin.Result.Companion var5 = Result.Companion;
            boolean var6 = false;
            var3 = Result.constructor-impl(ResultKt.createFailure(var19));
         }

         return var3;
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker $constructor_marker) {
         this();
      }
   }
}
