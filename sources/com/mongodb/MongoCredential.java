package com.mongodb;

import com.mongodb.annotations.Immutable;
import com.mongodb.assertions.Assertions;
import com.mongodb.lang.Nullable;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import me.ag2s.epublib.epub.PackageDocumentBase;

@Immutable
/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/MongoCredential.class */
public final class MongoCredential {
    private final AuthenticationMechanism mechanism;
    private final String userName;
    private final String source;
    private final char[] password;
    private final Map<String, Object> mechanismProperties;

    @Deprecated
    public static final String MONGODB_CR_MECHANISM = AuthenticationMechanism.MONGODB_CR.getMechanismName();
    public static final String GSSAPI_MECHANISM = AuthenticationMechanism.GSSAPI.getMechanismName();
    public static final String PLAIN_MECHANISM = AuthenticationMechanism.PLAIN.getMechanismName();
    public static final String MONGODB_X509_MECHANISM = AuthenticationMechanism.MONGODB_X509.getMechanismName();
    public static final String SCRAM_SHA_1_MECHANISM = AuthenticationMechanism.SCRAM_SHA_1.getMechanismName();
    public static final String SCRAM_SHA_256_MECHANISM = AuthenticationMechanism.SCRAM_SHA_256.getMechanismName();
    public static final String SERVICE_NAME_KEY = "SERVICE_NAME";
    public static final String CANONICALIZE_HOST_NAME_KEY = "CANONICALIZE_HOST_NAME";
    public static final String JAVA_SASL_CLIENT_PROPERTIES_KEY = "JAVA_SASL_CLIENT_PROPERTIES";
    public static final String JAVA_SUBJECT_KEY = "JAVA_SUBJECT";

    public static MongoCredential createCredential(String userName, String database, char[] password) {
        return new MongoCredential(null, userName, database, password);
    }

    public static MongoCredential createScramSha1Credential(String userName, String source, char[] password) {
        return new MongoCredential(AuthenticationMechanism.SCRAM_SHA_1, userName, source, password);
    }

    public static MongoCredential createScramSha256Credential(String userName, String source, char[] password) {
        return new MongoCredential(AuthenticationMechanism.SCRAM_SHA_256, userName, source, password);
    }

    @Deprecated
    public static MongoCredential createMongoCRCredential(String userName, String database, char[] password) {
        return new MongoCredential(AuthenticationMechanism.MONGODB_CR, userName, database, password);
    }

    public static MongoCredential createMongoX509Credential(String userName) {
        return new MongoCredential(AuthenticationMechanism.MONGODB_X509, userName, "$external", null);
    }

    public static MongoCredential createMongoX509Credential() {
        return new MongoCredential(AuthenticationMechanism.MONGODB_X509, null, "$external", null);
    }

    public static MongoCredential createPlainCredential(String userName, String source, char[] password) {
        return new MongoCredential(AuthenticationMechanism.PLAIN, userName, source, password);
    }

    public static MongoCredential createGSSAPICredential(String userName) {
        return new MongoCredential(AuthenticationMechanism.GSSAPI, userName, "$external", null);
    }

    public <T> MongoCredential withMechanismProperty(String key, T value) {
        return new MongoCredential(this, key, value);
    }

    public MongoCredential withMechanism(AuthenticationMechanism mechanism) {
        if (this.mechanism != null) {
            throw new IllegalArgumentException("Mechanism already set");
        }
        return new MongoCredential(mechanism, this.userName, this.source, this.password, this.mechanismProperties);
    }

    MongoCredential(@Nullable AuthenticationMechanism mechanism, @Nullable String userName, String source, @Nullable char[] password) {
        this(mechanism, userName, source, password, Collections.emptyMap());
    }

    MongoCredential(@Nullable AuthenticationMechanism mechanism, @Nullable String userName, String source, @Nullable char[] password, Map<String, Object> mechanismProperties) {
        if (mechanism != AuthenticationMechanism.MONGODB_X509 && userName == null) {
            throw new IllegalArgumentException("username can not be null");
        }
        if (mechanism == null && password == null) {
            throw new IllegalArgumentException("Password can not be null when the authentication mechanism is unspecified");
        }
        if (mechanismRequiresPassword(mechanism) && password == null) {
            throw new IllegalArgumentException("Password can not be null for " + mechanism + " mechanism");
        }
        if ((mechanism == AuthenticationMechanism.GSSAPI || mechanism == AuthenticationMechanism.MONGODB_X509) && password != null) {
            throw new IllegalArgumentException("Password must be null for the " + mechanism + " mechanism");
        }
        this.mechanism = mechanism;
        this.userName = userName;
        this.source = (String) Assertions.notNull(PackageDocumentBase.DCTags.source, source);
        this.password = password != null ? (char[]) password.clone() : null;
        this.mechanismProperties = new HashMap(mechanismProperties);
    }

    private boolean mechanismRequiresPassword(@Nullable AuthenticationMechanism mechanism) {
        return mechanism == AuthenticationMechanism.PLAIN || mechanism == AuthenticationMechanism.MONGODB_CR || mechanism == AuthenticationMechanism.SCRAM_SHA_1 || mechanism == AuthenticationMechanism.SCRAM_SHA_256;
    }

    <T> MongoCredential(MongoCredential from, String mechanismPropertyKey, T mechanismPropertyValue) {
        Assertions.notNull("mechanismPropertyKey", mechanismPropertyKey);
        this.mechanism = from.mechanism;
        this.userName = from.userName;
        this.source = from.source;
        this.password = from.password;
        this.mechanismProperties = new HashMap(from.mechanismProperties);
        this.mechanismProperties.put(mechanismPropertyKey.toLowerCase(), mechanismPropertyValue);
    }

    @Nullable
    public String getMechanism() {
        if (this.mechanism == null) {
            return null;
        }
        return this.mechanism.getMechanismName();
    }

    @Nullable
    public AuthenticationMechanism getAuthenticationMechanism() {
        return this.mechanism;
    }

    @Nullable
    public String getUserName() {
        return this.userName;
    }

    public String getSource() {
        return this.source;
    }

    @Nullable
    public char[] getPassword() {
        if (this.password == null) {
            return null;
        }
        return (char[]) this.password.clone();
    }

    @Nullable
    public <T> T getMechanismProperty(String str, @Nullable T t) {
        Assertions.notNull("key", str);
        T t2 = (T) this.mechanismProperties.get(str.toLowerCase());
        return t2 == null ? t : t2;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MongoCredential that = (MongoCredential) o;
        if (this.mechanism != that.mechanism || !Arrays.equals(this.password, that.password) || !this.source.equals(that.source)) {
            return false;
        }
        if (this.userName != null) {
            if (!this.userName.equals(that.userName)) {
                return false;
            }
        } else if (that.userName != null) {
            return false;
        }
        if (!this.mechanismProperties.equals(that.mechanismProperties)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int result = this.mechanism != null ? this.mechanism.hashCode() : 0;
        return (31 * ((31 * ((31 * ((31 * result) + (this.userName != null ? this.userName.hashCode() : 0))) + this.source.hashCode())) + (this.password != null ? Arrays.hashCode(this.password) : 0))) + this.mechanismProperties.hashCode();
    }

    public String toString() {
        return "MongoCredential{mechanism=" + this.mechanism + ", userName='" + this.userName + "', source='" + this.source + "', password=<hidden>, mechanismProperties=" + this.mechanismProperties + '}';
    }
}
