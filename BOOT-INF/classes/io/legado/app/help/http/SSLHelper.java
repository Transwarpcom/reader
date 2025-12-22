package io.legado.app.help.http;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.KeyStore.LoadStoreParameter;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 5, 1},
   k = 1,
   xi = 48,
   d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001)B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001b\u0010\u0015\u001a\u00020\u00122\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017H\u0002¢\u0006\u0002\u0010\u0019J \u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u0012J1\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0012\u0010\u001f\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u001b0\u0017\"\u00020\u001b¢\u0006\u0002\u0010 J\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u001e\u001a\u00020\u0012J!\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0012\u0010\u001f\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u001b0\u0017\"\u00020\u001b¢\u0006\u0002\u0010!JA\u0010\"\u001a\u0004\u0018\u00010\u00042\b\u0010\u001e\u001a\u0004\u0018\u00010\u00122\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0012\u0010\u001f\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u001b0\u0017\"\u00020\u001bH\u0002¢\u0006\u0002\u0010#J)\u0010$\u001a\n\u0012\u0004\u0012\u00020%\u0018\u00010\u00172\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0002¢\u0006\u0002\u0010&J'\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\u0012\u0010\u001f\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u001b0\u0017\"\u00020\u001bH\u0002¢\u0006\u0002\u0010(R\u0013\u0010\u0003\u001a\u0004\u0018\u00010\u00048F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001b\u0010\u000b\u001a\u00020\f8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006*"},
   d2 = {"Lio/legado/app/help/http/SSLHelper;", "", "()V", "sslSocketFactory", "Lio/legado/app/help/http/SSLHelper$SSLParams;", "getSslSocketFactory", "()Lio/legado/app/help/http/SSLHelper$SSLParams;", "unsafeHostnameVerifier", "Ljavax/net/ssl/HostnameVerifier;", "getUnsafeHostnameVerifier", "()Ljavax/net/ssl/HostnameVerifier;", "unsafeSSLSocketFactory", "Ljavax/net/ssl/SSLSocketFactory;", "getUnsafeSSLSocketFactory", "()Ljavax/net/ssl/SSLSocketFactory;", "unsafeSSLSocketFactory$delegate", "Lkotlin/Lazy;", "unsafeTrustManager", "Ljavax/net/ssl/X509TrustManager;", "getUnsafeTrustManager", "()Ljavax/net/ssl/X509TrustManager;", "chooseTrustManager", "trustManagers", "", "Ljavax/net/ssl/TrustManager;", "([Ljavax/net/ssl/TrustManager;)Ljavax/net/ssl/X509TrustManager;", "bksFile", "Ljava/io/InputStream;", "password", "", "trustManager", "certificates", "(Ljava/io/InputStream;Ljava/lang/String;[Ljava/io/InputStream;)Lio/legado/app/help/http/SSLHelper$SSLParams;", "([Ljava/io/InputStream;)Lio/legado/app/help/http/SSLHelper$SSLParams;", "getSslSocketFactoryBase", "(Ljavax/net/ssl/X509TrustManager;Ljava/io/InputStream;Ljava/lang/String;[Ljava/io/InputStream;)Lio/legado/app/help/http/SSLHelper$SSLParams;", "prepareKeyManager", "Ljavax/net/ssl/KeyManager;", "(Ljava/io/InputStream;Ljava/lang/String;)[Ljavax/net/ssl/KeyManager;", "prepareTrustManager", "([Ljava/io/InputStream;)[Ljavax/net/ssl/TrustManager;", "SSLParams", "reader-pro"}
)
public final class SSLHelper {
   @NotNull
   public static final SSLHelper INSTANCE = new SSLHelper();
   @NotNull
   private static final X509TrustManager unsafeTrustManager = (X509TrustManager)(new X509TrustManager() {
      public void checkClientTrusted(@NotNull X509Certificate[] chain, @NotNull String authType) throws CertificateException {
         Intrinsics.checkNotNullParameter(chain, "chain");
         Intrinsics.checkNotNullParameter(authType, "authType");
      }

      public void checkServerTrusted(@NotNull X509Certificate[] chain, @NotNull String authType) throws CertificateException {
         Intrinsics.checkNotNullParameter(chain, "chain");
         Intrinsics.checkNotNullParameter(authType, "authType");
      }

      @NotNull
      public X509Certificate[] getAcceptedIssuers() {
         return new X509Certificate[0];
      }
   });
   @NotNull
   private static final Lazy unsafeSSLSocketFactory$delegate;
   @NotNull
   private static final HostnameVerifier unsafeHostnameVerifier;

   private SSLHelper() {
   }

   @Nullable
   public final SSLHelper.SSLParams getSslSocketFactory() {
      return this.getSslSocketFactoryBase((X509TrustManager)null, (InputStream)null, (String)null);
   }

   @NotNull
   public final X509TrustManager getUnsafeTrustManager() {
      return unsafeTrustManager;
   }

   @NotNull
   public final SSLSocketFactory getUnsafeSSLSocketFactory() {
      Lazy var2 = unsafeSSLSocketFactory$delegate;
      boolean var4 = false;
      Object var1 = var2.getValue();
      Intrinsics.checkNotNullExpressionValue(var1, "<get-unsafeSSLSocketFactory>(...)");
      return (SSLSocketFactory)var1;
   }

   @NotNull
   public final HostnameVerifier getUnsafeHostnameVerifier() {
      return unsafeHostnameVerifier;
   }

   @Nullable
   public final SSLHelper.SSLParams getSslSocketFactory(@NotNull X509TrustManager trustManager) {
      Intrinsics.checkNotNullParameter(trustManager, "trustManager");
      return this.getSslSocketFactoryBase(trustManager, (InputStream)null, (String)null);
   }

   @Nullable
   public final SSLHelper.SSLParams getSslSocketFactory(@NotNull InputStream... certificates) {
      Intrinsics.checkNotNullParameter(certificates, "certificates");
      return this.getSslSocketFactoryBase((X509TrustManager)null, (InputStream)null, (String)null, (InputStream[])Arrays.copyOf(certificates, certificates.length));
   }

   @Nullable
   public final SSLHelper.SSLParams getSslSocketFactory(@NotNull InputStream bksFile, @NotNull String password, @NotNull InputStream... certificates) {
      Intrinsics.checkNotNullParameter(bksFile, "bksFile");
      Intrinsics.checkNotNullParameter(password, "password");
      Intrinsics.checkNotNullParameter(certificates, "certificates");
      return this.getSslSocketFactoryBase((X509TrustManager)null, bksFile, password, (InputStream[])Arrays.copyOf(certificates, certificates.length));
   }

   @Nullable
   public final SSLHelper.SSLParams getSslSocketFactory(@NotNull InputStream bksFile, @NotNull String password, @NotNull X509TrustManager trustManager) {
      Intrinsics.checkNotNullParameter(bksFile, "bksFile");
      Intrinsics.checkNotNullParameter(password, "password");
      Intrinsics.checkNotNullParameter(trustManager, "trustManager");
      return this.getSslSocketFactoryBase(trustManager, bksFile, password);
   }

   private final SSLHelper.SSLParams getSslSocketFactoryBase(X509TrustManager trustManager, InputStream bksFile, String password, InputStream... certificates) {
      SSLHelper.SSLParams sslParams = new SSLHelper.SSLParams();

      try {
         KeyManager[] keyManagers = this.prepareKeyManager(bksFile, password);
         TrustManager[] trustManagers = this.prepareTrustManager((InputStream[])Arrays.copyOf(certificates, certificates.length));
         X509TrustManager manager = trustManager == null ? this.chooseTrustManager(trustManagers) : trustManager;
         SSLContext sslContext = SSLContext.getInstance("TLS");
         TrustManager[] var10 = new TrustManager[]{(TrustManager)manager};
         sslContext.init(keyManagers, var10, (SecureRandom)null);
         SSLSocketFactory var13 = sslContext.getSocketFactory();
         Intrinsics.checkNotNullExpressionValue(var13, "sslContext.socketFactory");
         sslParams.setSSLSocketFactory(var13);
         sslParams.setTrustManager(manager);
         return sslParams;
      } catch (NoSuchAlgorithmException var11) {
         var11.printStackTrace();
      } catch (KeyManagementException var12) {
         var12.printStackTrace();
      }

      return null;
   }

   private final KeyManager[] prepareKeyManager(InputStream bksFile, String password) {
      try {
         if (bksFile != null && password != null) {
            KeyStore clientKeyStore = KeyStore.getInstance("BKS");
            boolean var5 = false;
            char[] var10002 = password.toCharArray();
            Intrinsics.checkNotNullExpressionValue(var10002, "(this as java.lang.String).toCharArray()");
            clientKeyStore.load(bksFile, var10002);
            KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            boolean var6 = false;
            var10002 = password.toCharArray();
            Intrinsics.checkNotNullExpressionValue(var10002, "(this as java.lang.String).toCharArray()");
            kmf.init(clientKeyStore, var10002);
            return kmf.getKeyManagers();
         } else {
            return null;
         }
      } catch (Exception var7) {
         var7.printStackTrace();
         return null;
      }
   }

   private final TrustManager[] prepareTrustManager(InputStream... certificates) {
      CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
      KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
      keyStore.load((LoadStoreParameter)null);
      InputStream[] var4 = certificates;
      int var5 = 0;
      int var6 = certificates.length;

      while(var5 < var6) {
         int index = var5;
         InputStream certStream = var4[var5];
         ++var5;
         String certificateAlias = Integer.toString(index);
         Certificate cert = certificateFactory.generateCertificate(certStream);
         keyStore.setCertificateEntry(certificateAlias, cert);

         try {
            certStream.close();
         } catch (IOException var12) {
            var12.printStackTrace();
         }
      }

      TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
      tmf.init(keyStore);
      TrustManager[] var14 = tmf.getTrustManagers();
      Intrinsics.checkNotNullExpressionValue(var14, "tmf.trustManagers");
      return var14;
   }

   private final X509TrustManager chooseTrustManager(TrustManager[] trustManagers) {
      TrustManager[] var2 = trustManagers;
      int var3 = 0;
      int var4 = trustManagers.length;

      TrustManager trustManager;
      do {
         if (var3 >= var4) {
            throw new NullPointerException();
         }

         trustManager = var2[var3];
         ++var3;
      } while(!(trustManager instanceof X509TrustManager));

      return (X509TrustManager)trustManager;
   }

   private static final boolean unsafeHostnameVerifier$lambda_0/* $FF was: unsafeHostnameVerifier$lambda-0*/(String $noName_0, SSLSession $noName_1) {
      return true;
   }

   static {
      unsafeSSLSocketFactory$delegate = LazyKt.lazy((Function0)null.INSTANCE);
      unsafeHostnameVerifier = SSLHelper::unsafeHostnameVerifier$lambda-0;
   }

   @Metadata(
      mv = {1, 5, 1},
      k = 1,
      xi = 48,
      d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u000f"},
      d2 = {"Lio/legado/app/help/http/SSLHelper$SSLParams;", "", "()V", "sSLSocketFactory", "Ljavax/net/ssl/SSLSocketFactory;", "getSSLSocketFactory", "()Ljavax/net/ssl/SSLSocketFactory;", "setSSLSocketFactory", "(Ljavax/net/ssl/SSLSocketFactory;)V", "trustManager", "Ljavax/net/ssl/X509TrustManager;", "getTrustManager", "()Ljavax/net/ssl/X509TrustManager;", "setTrustManager", "(Ljavax/net/ssl/X509TrustManager;)V", "reader-pro"}
   )
   public static final class SSLParams {
      public SSLSocketFactory sSLSocketFactory;
      public X509TrustManager trustManager;

      @NotNull
      public final SSLSocketFactory getSSLSocketFactory() {
         SSLSocketFactory var1 = this.sSLSocketFactory;
         if (var1 != null) {
            return var1;
         } else {
            Intrinsics.throwUninitializedPropertyAccessException("sSLSocketFactory");
            throw null;
         }
      }

      public final void setSSLSocketFactory(@NotNull SSLSocketFactory <set-?>) {
         Intrinsics.checkNotNullParameter(var1, "<set-?>");
         this.sSLSocketFactory = var1;
      }

      @NotNull
      public final X509TrustManager getTrustManager() {
         X509TrustManager var1 = this.trustManager;
         if (var1 != null) {
            return var1;
         } else {
            Intrinsics.throwUninitializedPropertyAccessException("trustManager");
            throw null;
         }
      }

      public final void setTrustManager(@NotNull X509TrustManager <set-?>) {
         Intrinsics.checkNotNullParameter(var1, "<set-?>");
         this.trustManager = var1;
      }
   }
}
