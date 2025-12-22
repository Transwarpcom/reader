package io.vertx.core.net.impl;

import cn.hutool.crypto.KeyUtil;
import io.netty.util.internal.PlatformDependent;
import io.vertx.core.VertxException;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.file.FileSystem;
import io.vertx.core.impl.VertxInternal;
import io.vertx.core.net.JksOptions;
import io.vertx.core.net.KeyCertOptions;
import io.vertx.core.net.PemKeyCertOptions;
import io.vertx.core.net.PemTrustOptions;
import io.vertx.core.net.PfxOptions;
import io.vertx.core.net.TrustOptions;
import io.vertx.core.net.impl.pkcs1.PrivateKeyParser;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.naming.ldap.LdapName;
import javax.naming.ldap.Rdn;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509KeyManager;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-core-3.8.5.jar:io/vertx/core/net/impl/KeyStoreHelper.class */
public class KeyStoreHelper {
    private static final String DUMMY_PASSWORD = "dummy";
    private static final String DUMMY_CERT_ALIAS = "cert-";
    private static final Pattern BEGIN_PATTERN = Pattern.compile("-----BEGIN ([A-Z ]+)-----");
    private static final Pattern END_PATTERN = Pattern.compile("-----END ([A-Z ]+)-----");
    private final String password;
    private final KeyStore store;
    private final Map<String, X509KeyManager> wildcardMgrMap = new HashMap();
    private final Map<String, X509KeyManager> mgrMap = new HashMap();
    private final Map<String, TrustManagerFactory> trustMgrMap = new HashMap();

    public static KeyStoreHelper create(VertxInternal vertx, KeyCertOptions options) throws Exception {
        Supplier<Buffer> value;
        Supplier<Buffer> value2;
        if (options instanceof JksOptions) {
            JksOptions jks = (JksOptions) options;
            if (jks.getPath() != null) {
                value2 = () -> {
                    return vertx.fileSystem().readFileBlocking(vertx.resolveFile(jks.getPath()).getAbsolutePath());
                };
            } else if (jks.getValue() != null) {
                jks.getClass();
                value2 = jks::getValue;
            } else {
                return null;
            }
            return new KeyStoreHelper(loadJKSOrPKCS12("JKS", jks.getPassword(), value2), jks.getPassword());
        }
        if (options instanceof PfxOptions) {
            PfxOptions pkcs12 = (PfxOptions) options;
            if (pkcs12.getPath() != null) {
                value = () -> {
                    return vertx.fileSystem().readFileBlocking(vertx.resolveFile(pkcs12.getPath()).getAbsolutePath());
                };
            } else if (pkcs12.getValue() != null) {
                pkcs12.getClass();
                value = pkcs12::getValue;
            } else {
                return null;
            }
            return new KeyStoreHelper(loadJKSOrPKCS12("PKCS12", pkcs12.getPassword(), value), pkcs12.getPassword());
        }
        if (options instanceof PemKeyCertOptions) {
            PemKeyCertOptions keyCert = (PemKeyCertOptions) options;
            List<Buffer> keys = new ArrayList<>();
            for (String keyPath : keyCert.getKeyPaths()) {
                keys.add(vertx.fileSystem().readFileBlocking(vertx.resolveFile(keyPath).getAbsolutePath()));
            }
            keys.addAll(keyCert.getKeyValues());
            List<Buffer> certs = new ArrayList<>();
            for (String certPath : keyCert.getCertPaths()) {
                certs.add(vertx.fileSystem().readFileBlocking(vertx.resolveFile(certPath).getAbsolutePath()));
            }
            certs.addAll(keyCert.getCertValues());
            return new KeyStoreHelper(loadKeyCert(keys, certs), DUMMY_PASSWORD);
        }
        return null;
    }

    public static KeyStoreHelper create(VertxInternal vertx, TrustOptions options) throws Exception {
        if (options instanceof KeyCertOptions) {
            return create(vertx, (KeyCertOptions) options);
        }
        if (options instanceof PemTrustOptions) {
            PemTrustOptions trustOptions = (PemTrustOptions) options;
            Stream<R> map = trustOptions.getCertPaths().stream().map(path -> {
                return vertx.resolveFile(path).getAbsolutePath();
            });
            FileSystem fileSystem = vertx.fileSystem();
            fileSystem.getClass();
            Stream<Buffer> certValues = map.map(fileSystem::readFileBlocking);
            return new KeyStoreHelper(loadCA(Stream.concat(certValues, trustOptions.getCertValues().stream())), null);
        }
        return null;
    }

    public KeyStoreHelper(KeyStore ks, String password) throws Exception {
        Enumeration<String> en = ks.aliases();
        while (en.hasMoreElements()) {
            String alias = en.nextElement();
            Certificate cert = ks.getCertificate(alias);
            if (ks.isCertificateEntry(alias) && !alias.startsWith(DUMMY_CERT_ALIAS)) {
                KeyStore keyStore = createEmptyKeyStore();
                keyStore.setCertificateEntry("cert-1", cert);
                TrustManagerFactory fact = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                fact.init(keyStore);
                this.trustMgrMap.put(alias, fact);
            }
            if (ks.isKeyEntry(alias) && (cert instanceof X509Certificate)) {
                X509Certificate x509Cert = (X509Certificate) cert;
                Collection<List<?>> ans = x509Cert.getSubjectAlternativeNames();
                List<String> domains = new ArrayList<>();
                if (ans != null) {
                    for (List<?> l : ans) {
                        if (l.size() == 2 && (l.get(0) instanceof Number) && ((Number) l.get(0)).intValue() == 2) {
                            String dns = l.get(1).toString();
                            domains.add(dns);
                        }
                    }
                }
                String dn = x509Cert.getSubjectX500Principal().getName();
                domains.addAll(getX509CertificateCommonNames(dn));
                if (!domains.isEmpty()) {
                    final PrivateKey key = (PrivateKey) ks.getKey(alias, password != null ? password.toCharArray() : null);
                    Certificate[] tmp = ks.getCertificateChain(alias);
                    if (tmp != null) {
                        final List<X509Certificate> chain = (List) Arrays.asList(tmp).stream().map(c -> {
                            return (X509Certificate) c;
                        }).collect(Collectors.toList());
                        X509KeyManager mgr = new X509KeyManager() { // from class: io.vertx.core.net.impl.KeyStoreHelper.1
                            @Override // javax.net.ssl.X509KeyManager
                            public String[] getClientAliases(String s, Principal[] principals) {
                                throw new UnsupportedOperationException();
                            }

                            @Override // javax.net.ssl.X509KeyManager
                            public String chooseClientAlias(String[] strings, Principal[] principals, Socket socket) {
                                throw new UnsupportedOperationException();
                            }

                            @Override // javax.net.ssl.X509KeyManager
                            public String[] getServerAliases(String s, Principal[] principals) {
                                throw new UnsupportedOperationException();
                            }

                            @Override // javax.net.ssl.X509KeyManager
                            public String chooseServerAlias(String s, Principal[] principals, Socket socket) {
                                throw new UnsupportedOperationException();
                            }

                            @Override // javax.net.ssl.X509KeyManager
                            public X509Certificate[] getCertificateChain(String s) {
                                return (X509Certificate[]) chain.toArray(new X509Certificate[chain.size()]);
                            }

                            @Override // javax.net.ssl.X509KeyManager
                            public PrivateKey getPrivateKey(String s) {
                                return key;
                            }
                        };
                        for (String domain : domains) {
                            if (domain.startsWith("*.")) {
                                this.wildcardMgrMap.put(domain.substring(2), mgr);
                            } else {
                                this.mgrMap.put(domain, mgr);
                            }
                        }
                    }
                }
            }
        }
        this.store = ks;
        this.password = password;
    }

    public KeyManagerFactory getKeyMgrFactory() throws Exception {
        KeyManagerFactory fact = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        fact.init(this.store, this.password != null ? this.password.toCharArray() : null);
        return fact;
    }

    public X509KeyManager getKeyMgr(String serverName) {
        int index;
        X509KeyManager mgr = this.mgrMap.get(serverName);
        if (mgr == null && !this.wildcardMgrMap.isEmpty() && (index = serverName.indexOf(46) + 1) > 0) {
            String s = serverName.substring(index);
            mgr = this.wildcardMgrMap.get(s);
        }
        return mgr;
    }

    public KeyManager[] getKeyMgr() throws Exception {
        return getKeyMgrFactory().getKeyManagers();
    }

    public TrustManager[] getTrustMgr(String serverName) {
        TrustManagerFactory fact = this.trustMgrMap.get(serverName);
        if (fact != null) {
            return fact.getTrustManagers();
        }
        return null;
    }

    public TrustManagerFactory getTrustMgrFactory(VertxInternal vertx) throws Exception {
        TrustManagerFactory fact = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        fact.init(this.store);
        return fact;
    }

    public TrustManager[] getTrustMgrs(VertxInternal vertx) throws Exception {
        return getTrustMgrFactory(vertx).getTrustManagers();
    }

    public KeyStore store() {
        return this.store;
    }

    public static List<String> getX509CertificateCommonNames(String dn) throws Exception {
        List<String> names = new ArrayList<>();
        if (!PlatformDependent.isAndroid()) {
            LdapName ldapDN = new LdapName(dn);
            for (Rdn rdn : ldapDN.getRdns()) {
                if (rdn.getType().equalsIgnoreCase("cn")) {
                    String name = rdn.getValue().toString();
                    names.add(name);
                }
            }
        } else {
            String[] rdns = dn.trim().split("[,;]");
            for (String str : rdns) {
                String[] nvp = str.trim().split("=");
                if (nvp.length == 2 && "cn".equalsIgnoreCase(nvp[0])) {
                    names.add(nvp[1]);
                }
            }
        }
        return names;
    }

    private static KeyStore loadJKSOrPKCS12(String type, String password, Supplier<Buffer> value) throws Exception {
        KeyStore ks = KeyStore.getInstance(type);
        InputStream in = null;
        try {
            in = new ByteArrayInputStream(value.get().getBytes());
            ks.load(in, password != null ? password.toCharArray() : null);
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
            return ks;
        } catch (Throwable th) {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e2) {
                }
            }
            throw th;
        }
    }

    private static KeyStore loadKeyCert(List<Buffer> keyValue, List<Buffer> certValue) throws Exception {
        if (keyValue.size() < certValue.size()) {
            throw new VertxException("Missing private key");
        }
        if (keyValue.size() > certValue.size()) {
            throw new VertxException("Missing X.509 certificate");
        }
        KeyStore keyStore = createEmptyKeyStore();
        Iterator<Buffer> keyValueIt = keyValue.iterator();
        Iterator<Buffer> certValueIt = certValue.iterator();
        int index = 0;
        while (keyValueIt.hasNext() && certValueIt.hasNext()) {
            PrivateKey key = loadPrivateKey(keyValueIt.next());
            Certificate[] chain = loadCerts(certValueIt.next());
            int i = index;
            index++;
            keyStore.setEntry("dummy-entry-" + i, new KeyStore.PrivateKeyEntry(key, chain), new KeyStore.PasswordProtection(DUMMY_PASSWORD.toCharArray()));
        }
        return keyStore;
    }

    private static PrivateKey loadPrivateKey(Buffer keyValue) throws Exception {
        if (keyValue == null) {
            throw new RuntimeException("Missing private key path");
        }
        KeyFactory rsaKeyFactory = KeyFactory.getInstance("RSA");
        KeyFactory ecKeyFactory = getECKeyFactory();
        List<PrivateKey> pems = loadPems(keyValue, (delimiter, content) -> {
            try {
                switch (delimiter) {
                    case "RSA PRIVATE KEY":
                        return Collections.singletonList(rsaKeyFactory.generatePrivate(PrivateKeyParser.getRSAKeySpec(content)));
                    case "PRIVATE KEY":
                        String algorithm = PrivateKeyParser.getPKCS8EncodedKeyAlgorithm(content);
                        if (rsaKeyFactory.getAlgorithm().equals(algorithm)) {
                            return Collections.singletonList(rsaKeyFactory.generatePrivate(new PKCS8EncodedKeySpec(content)));
                        }
                        if (ecKeyFactory != null && ecKeyFactory.getAlgorithm().equals(algorithm)) {
                            return Collections.singletonList(ecKeyFactory.generatePrivate(new PKCS8EncodedKeySpec(content)));
                        }
                        break;
                }
                return Collections.emptyList();
            } catch (InvalidKeySpecException e) {
                throw new VertxException(e);
            }
        });
        if (pems.isEmpty()) {
            throw new RuntimeException("Missing -----BEGIN PRIVATE KEY----- or -----BEGIN RSA PRIVATE KEY----- delimiter");
        }
        return pems.get(0);
    }

    private static KeyFactory getECKeyFactory() {
        try {
            return KeyFactory.getInstance("EC");
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    private static KeyStore loadCA(Stream<Buffer> certValues) throws Exception {
        KeyStore keyStore = createEmptyKeyStore();
        keyStore.load(null, null);
        int count = 0;
        certValues.getClass();
        Iterable<Buffer> iterable = certValues::iterator;
        for (Buffer certValue : iterable) {
            for (Certificate cert : loadCerts(certValue)) {
                int i = count;
                count++;
                keyStore.setCertificateEntry(DUMMY_CERT_ALIAS + i, cert);
            }
        }
        return keyStore;
    }

    /* JADX WARN: Incorrect condition in loop: B:4:0x002a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static <P> java.util.List<P> loadPems(io.vertx.core.buffer.Buffer r5, java.util.function.BiFunction<java.lang.String, byte[], java.util.Collection<P>> r6) throws java.io.IOException {
        /*
            r0 = r5
            java.lang.String r0 = r0.toString()
            r7 = r0
            java.util.ArrayList r0 = new java.util.ArrayList
            r1 = r0
            r1.<init>()
            r8 = r0
            java.util.regex.Pattern r0 = io.vertx.core.net.impl.KeyStoreHelper.BEGIN_PATTERN
            r1 = r7
            java.util.regex.Matcher r0 = r0.matcher(r1)
            r9 = r0
            java.util.regex.Pattern r0 = io.vertx.core.net.impl.KeyStoreHelper.END_PATTERN
            r1 = r7
            java.util.regex.Matcher r0 = r0.matcher(r1)
            r10 = r0
        L21:
            r0 = r9
            boolean r0 = r0.find()
            r11 = r0
            r0 = r11
            if (r0 != 0) goto L30
            goto Le6
        L30:
            r0 = r9
            r1 = 1
            java.lang.String r0 = r0.group(r1)
            r12 = r0
            r0 = r10
            boolean r0 = r0.find()
            r13 = r0
            r0 = r13
            if (r0 != 0) goto L65
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            r1 = r0
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r3 = r2
            r3.<init>()
            java.lang.String r3 = "Missing -----END "
            java.lang.StringBuilder r2 = r2.append(r3)
            r3 = r12
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r3 = "----- delimiter"
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            throw r0
        L65:
            r0 = r10
            r1 = 1
            java.lang.String r0 = r0.group(r1)
            r14 = r0
            r0 = r12
            r1 = r14
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L98
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            r1 = r0
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r3 = r2
            r3.<init>()
            java.lang.String r3 = "Missing -----END "
            java.lang.StringBuilder r2 = r2.append(r3)
            r3 = r12
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r3 = "----- delimiter"
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            throw r0
        L98:
            r0 = r7
            r1 = r9
            int r1 = r1.end()
            r2 = r10
            int r2 = r2.start()
            java.lang.String r0 = r0.substring(r1, r2)
            r15 = r0
            r0 = r15
            java.lang.String r1 = "\\s"
            java.lang.String r2 = ""
            java.lang.String r0 = r0.replaceAll(r1, r2)
            r15 = r0
            r0 = r15
            int r0 = r0.length()
            if (r0 != 0) goto Lc5
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            r1 = r0
            java.lang.String r2 = "Empty pem file"
            r1.<init>(r2)
            throw r0
        Lc5:
            r0 = r6
            r1 = r14
            java.util.Base64$Decoder r2 = java.util.Base64.getDecoder()
            r3 = r15
            byte[] r2 = r2.decode(r3)
            java.lang.Object r0 = r0.apply(r1, r2)
            java.util.Collection r0 = (java.util.Collection) r0
            r16 = r0
            r0 = r8
            r1 = r16
            boolean r0 = r0.addAll(r1)
            goto L21
        Le6:
            r0 = r8
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.vertx.core.net.impl.KeyStoreHelper.loadPems(io.vertx.core.buffer.Buffer, java.util.function.BiFunction):java.util.List");
    }

    private static X509Certificate[] loadCerts(Buffer buffer) throws Exception {
        if (buffer == null) {
            throw new RuntimeException("Missing X.509 certificate path");
        }
        CertificateFactory certFactory = CertificateFactory.getInstance(KeyUtil.CERT_TYPE_X509);
        List<X509Certificate> certs = loadPems(buffer, (delimiter, content) -> {
            try {
                switch (delimiter) {
                    case "CERTIFICATE":
                        return certFactory.generateCertificates(new ByteArrayInputStream(content));
                    default:
                        return Collections.emptyList();
                }
            } catch (CertificateException e) {
                throw new VertxException(e);
            }
        });
        if (certs.isEmpty()) {
            throw new RuntimeException("Missing -----BEGIN CERTIFICATE----- delimiter");
        }
        return (X509Certificate[]) certs.toArray(new X509Certificate[certs.size()]);
    }

    private static KeyStore createEmptyKeyStore() throws NoSuchAlgorithmException, IOException, KeyStoreException, CertificateException {
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        try {
            keyStore.load(null, null);
            return keyStore;
        } catch (IOException | NoSuchAlgorithmException | CertificateException e) {
            throw new KeyStoreException("Failed to initialize the keystore", e);
        }
    }
}
