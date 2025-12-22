package com.mongodb.internal.connection;

import com.mongodb.AuthenticationMechanism;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.internal.authentication.NativeAuthenticationHelper;
import com.mongodb.internal.authentication.SaslPrep;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Random;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.security.sasl.SaslClient;
import javax.security.sasl.SaslException;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.apache.pdfbox.pdmodel.common.PDPageLabelRange;
import org.bson.internal.Base64;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/ScramShaAuthenticator.class */
class ScramShaAuthenticator extends SaslAuthenticator {
    private final RandomStringGenerator randomStringGenerator;
    private final AuthenticationHashGenerator authenticationHashGenerator;
    private static final int MINIMUM_ITERATION_COUNT = 4096;
    private static final String GS2_HEADER = "n,,";
    private static final int RANDOM_LENGTH = 24;
    private static final byte[] INT_1 = {0, 0, 0, 1};
    private static final AuthenticationHashGenerator DEFAULT_AUTHENTICATION_HASH_GENERATOR = new AuthenticationHashGenerator() { // from class: com.mongodb.internal.connection.ScramShaAuthenticator.1
        @Override // com.mongodb.internal.connection.ScramShaAuthenticator.AuthenticationHashGenerator
        public String generate(MongoCredential credential) {
            char[] password = credential.getPassword();
            if (password == null) {
                throw new IllegalArgumentException("Password must not be null");
            }
            return new String(password);
        }
    };
    private static final AuthenticationHashGenerator LEGACY_AUTHENTICATION_HASH_GENERATOR = new AuthenticationHashGenerator() { // from class: com.mongodb.internal.connection.ScramShaAuthenticator.2
        @Override // com.mongodb.internal.connection.ScramShaAuthenticator.AuthenticationHashGenerator
        public String generate(MongoCredential credential) {
            String username = credential.getUserName();
            char[] password = credential.getPassword();
            if (username == null || password == null) {
                throw new IllegalArgumentException("Username and password must not be null");
            }
            return NativeAuthenticationHelper.createAuthenticationHash(username, password);
        }
    };

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/ScramShaAuthenticator$AuthenticationHashGenerator.class */
    public interface AuthenticationHashGenerator {
        String generate(MongoCredential mongoCredential);
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/ScramShaAuthenticator$RandomStringGenerator.class */
    public interface RandomStringGenerator {
        String generate(int i);
    }

    ScramShaAuthenticator(MongoCredentialWithCache credential) {
        this(credential, new DefaultRandomStringGenerator(), getAuthenicationHashGenerator(credential.getAuthenticationMechanism()));
    }

    ScramShaAuthenticator(MongoCredentialWithCache credential, RandomStringGenerator randomStringGenerator) {
        this(credential, randomStringGenerator, getAuthenicationHashGenerator(credential.getAuthenticationMechanism()));
    }

    ScramShaAuthenticator(MongoCredentialWithCache credential, RandomStringGenerator randomStringGenerator, AuthenticationHashGenerator authenticationHashGenerator) {
        super(credential);
        this.randomStringGenerator = randomStringGenerator;
        this.authenticationHashGenerator = authenticationHashGenerator;
    }

    @Override // com.mongodb.internal.connection.SaslAuthenticator
    public String getMechanismName() {
        AuthenticationMechanism authMechanism = getMongoCredential().getAuthenticationMechanism();
        if (authMechanism == null) {
            throw new IllegalArgumentException("Authentication mechanism cannot be null");
        }
        return authMechanism.getMechanismName();
    }

    @Override // com.mongodb.internal.connection.SaslAuthenticator
    protected SaslClient createSaslClient(ServerAddress serverAddress) {
        return new ScramShaSaslClient(getMongoCredentialWithCache(), this.randomStringGenerator, this.authenticationHashGenerator);
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/ScramShaAuthenticator$ScramShaSaslClient.class */
    class ScramShaSaslClient implements SaslClient {
        private final MongoCredentialWithCache credential;
        private final RandomStringGenerator randomStringGenerator;
        private final AuthenticationHashGenerator authenticationHashGenerator;
        private final String hAlgorithm;
        private final String hmacAlgorithm;
        private String clientFirstMessageBare;
        private String clientNonce;
        private byte[] serverSignature;
        private int step = -1;

        ScramShaSaslClient(MongoCredentialWithCache credential, RandomStringGenerator randomStringGenerator, AuthenticationHashGenerator authenticationHashGenerator) {
            this.credential = credential;
            this.randomStringGenerator = randomStringGenerator;
            this.authenticationHashGenerator = authenticationHashGenerator;
            if (credential.getAuthenticationMechanism().equals(AuthenticationMechanism.SCRAM_SHA_1)) {
                this.hAlgorithm = "SHA-1";
                this.hmacAlgorithm = "HmacSHA1";
            } else {
                this.hAlgorithm = "SHA-256";
                this.hmacAlgorithm = "HmacSHA256";
            }
        }

        public String getMechanismName() {
            return this.credential.getAuthenticationMechanism().getMechanismName();
        }

        public boolean hasInitialResponse() {
            return true;
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: javax.security.sasl.SaslException */
        public byte[] evaluateChallenge(byte[] challenge) throws SaslException {
            this.step++;
            if (this.step == 0) {
                return computeClientFirstMessage();
            }
            if (this.step == 1) {
                return computeClientFinalMessage(challenge);
            }
            if (this.step == 2) {
                return validateServerSignature(challenge);
            }
            throw new SaslException(String.format("Too many steps involved in the %s negotiation.", getMechanismName()));
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: javax.security.sasl.SaslException */
        private byte[] validateServerSignature(byte[] challenge) throws SaslException {
            String serverResponse = encodeUTF8(challenge);
            HashMap<String, String> map = parseServerResponse(serverResponse);
            if (!MessageDigest.isEqual(decodeBase64(map.get(OperatorName.CURVE_TO_REPLICATE_INITIAL_POINT)), this.serverSignature)) {
                throw new SaslException("Server signature was invalid.");
            }
            return challenge;
        }

        public boolean isComplete() {
            return this.step == 2;
        }

        public byte[] unwrap(byte[] incoming, int offset, int len) {
            throw new UnsupportedOperationException("Not implemented yet!");
        }

        public byte[] wrap(byte[] outgoing, int offset, int len) {
            throw new UnsupportedOperationException("Not implemented yet!");
        }

        public Object getNegotiatedProperty(String propName) {
            throw new UnsupportedOperationException("Not implemented yet!");
        }

        public void dispose() {
        }

        private byte[] computeClientFirstMessage() throws SaslException {
            this.clientNonce = this.randomStringGenerator.generate(24);
            String clientFirstMessage = "n=" + getUserName() + ",r=" + this.clientNonce;
            this.clientFirstMessageBare = clientFirstMessage;
            return decodeUTF8(ScramShaAuthenticator.GS2_HEADER + clientFirstMessage);
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: javax.security.sasl.SaslException */
        private byte[] computeClientFinalMessage(byte[] challenge) throws NumberFormatException, SaslException {
            String serverFirstMessage = encodeUTF8(challenge);
            HashMap<String, String> map = parseServerResponse(serverFirstMessage);
            String serverNonce = map.get(PDPageLabelRange.STYLE_ROMAN_LOWER);
            if (!serverNonce.startsWith(this.clientNonce)) {
                throw new SaslException("Server sent an invalid nonce.");
            }
            String salt = map.get(OperatorName.CLOSE_AND_STROKE);
            int iterationCount = Integer.parseInt(map.get("i"));
            if (iterationCount < 4096) {
                throw new SaslException("Invalid iteration count.");
            }
            String clientFinalMessageWithoutProof = "c=" + encodeBase64(ScramShaAuthenticator.GS2_HEADER) + ",r=" + serverNonce;
            String authMessage = this.clientFirstMessageBare + "," + serverFirstMessage + "," + clientFinalMessageWithoutProof;
            String clientFinalMessage = clientFinalMessageWithoutProof + ",p=" + getClientProof(getAuthenicationHash(), salt, iterationCount, authMessage);
            return decodeUTF8(clientFinalMessage);
        }

        String getClientProof(String password, String salt, int iterationCount, String authMessage) throws IllegalStateException, NoSuchAlgorithmException, InvalidKeyException, SaslException {
            String hashedPasswordAndSalt = encodeUTF8(h(decodeUTF8(password + salt)));
            CacheKey cacheKey = new CacheKey(hashedPasswordAndSalt, salt, iterationCount);
            CacheValue cachedKeys = (CacheValue) ScramShaAuthenticator.this.getMongoCredentialWithCache().getFromCache(cacheKey, CacheValue.class);
            if (cachedKeys == null) {
                byte[] saltedPassword = hi(decodeUTF8(password), decodeBase64(salt), iterationCount);
                byte[] clientKey = hmac(saltedPassword, "Client Key");
                byte[] serverKey = hmac(saltedPassword, "Server Key");
                cachedKeys = new CacheValue(clientKey, serverKey);
                ScramShaAuthenticator.this.getMongoCredentialWithCache().putInCache(cacheKey, new CacheValue(clientKey, serverKey));
            }
            this.serverSignature = hmac(cachedKeys.serverKey, authMessage);
            byte[] storedKey = h(cachedKeys.clientKey);
            byte[] clientSignature = hmac(storedKey, authMessage);
            byte[] clientProof = xor(cachedKeys.clientKey, clientSignature);
            return encodeBase64(clientProof);
        }

        private byte[] decodeBase64(String str) {
            return Base64.decode(str);
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: javax.security.sasl.SaslException */
        private byte[] decodeUTF8(String str) throws SaslException {
            try {
                return str.getBytes("UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new SaslException("UTF-8 is not a supported encoding.", e);
            }
        }

        private String encodeBase64(String str) throws SaslException {
            return Base64.encode(decodeUTF8(str));
        }

        private String encodeBase64(byte[] bytes) {
            return Base64.encode(bytes);
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: javax.security.sasl.SaslException */
        private String encodeUTF8(byte[] bytes) throws SaslException {
            try {
                return new String(bytes, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new SaslException("UTF-8 is not a supported encoding.", e);
            }
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: javax.security.sasl.SaslException */
        private byte[] h(byte[] data) throws SaslException {
            try {
                return MessageDigest.getInstance(this.hAlgorithm).digest(data);
            } catch (NoSuchAlgorithmException e) {
                throw new SaslException(String.format("Algorithm for '%s' could not be found.", this.hAlgorithm), e);
            }
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: javax.security.sasl.SaslException */
        private byte[] hi(byte[] password, byte[] salt, int iterations) throws IllegalStateException, NoSuchAlgorithmException, InvalidKeyException, SaslException {
            try {
                SecretKeySpec key = new SecretKeySpec(password, this.hmacAlgorithm);
                Mac mac = Mac.getInstance(this.hmacAlgorithm);
                mac.init(key);
                mac.update(salt);
                mac.update(ScramShaAuthenticator.INT_1);
                byte[] result = mac.doFinal();
                byte[] previous = null;
                for (int i = 1; i < iterations; i++) {
                    mac.update(previous != null ? previous : result);
                    previous = mac.doFinal();
                    xorInPlace(result, previous);
                }
                return result;
            } catch (InvalidKeyException e) {
                throw new SaslException(String.format("Invalid key for %s", this.hmacAlgorithm), e);
            } catch (NoSuchAlgorithmException e2) {
                throw new SaslException(String.format("Algorithm for '%s' could not be found.", this.hmacAlgorithm), e2);
            }
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: javax.security.sasl.SaslException */
        private byte[] hmac(byte[] bytes, String key) throws NoSuchAlgorithmException, InvalidKeyException, SaslException {
            try {
                Mac mac = Mac.getInstance(this.hmacAlgorithm);
                mac.init(new SecretKeySpec(bytes, this.hmacAlgorithm));
                return mac.doFinal(decodeUTF8(key));
            } catch (InvalidKeyException e) {
                throw new SaslException("Could not initialize mac.", e);
            } catch (NoSuchAlgorithmException e2) {
                throw new SaslException(String.format("Algorithm for '%s' could not be found.", this.hmacAlgorithm), e2);
            }
        }

        private HashMap<String, String> parseServerResponse(String response) {
            HashMap<String, String> map = new HashMap<>();
            String[] pairs = response.split(",");
            for (String pair : pairs) {
                String[] parts = pair.split("=", 2);
                map.put(parts[0], parts[1]);
            }
            return map;
        }

        private String getUserName() {
            String userName = this.credential.getCredential().getUserName();
            if (userName == null) {
                throw new IllegalArgumentException("Username can not be null");
            }
            return userName.replace("=", "=3D").replace(",", "=2C");
        }

        private String getAuthenicationHash() {
            String password = this.authenticationHashGenerator.generate(this.credential.getCredential());
            if (this.credential.getAuthenticationMechanism() == AuthenticationMechanism.SCRAM_SHA_256) {
                password = SaslPrep.saslPrepStored(password);
            }
            return password;
        }

        private byte[] xorInPlace(byte[] a, byte[] b) {
            for (int i = 0; i < a.length; i++) {
                int i2 = i;
                a[i2] = (byte) (a[i2] ^ b[i]);
            }
            return a;
        }

        private byte[] xor(byte[] a, byte[] b) {
            byte[] result = new byte[a.length];
            System.arraycopy(a, 0, result, 0, a.length);
            return xorInPlace(result, b);
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/ScramShaAuthenticator$DefaultRandomStringGenerator.class */
    private static class DefaultRandomStringGenerator implements RandomStringGenerator {
        private DefaultRandomStringGenerator() {
        }

        @Override // com.mongodb.internal.connection.ScramShaAuthenticator.RandomStringGenerator
        public String generate(int length) {
            int next;
            Random random = new SecureRandom();
            int range = 126 - 33;
            char[] text = new char[length];
            for (int i = 0; i < length; i++) {
                int iNextInt = random.nextInt(range);
                while (true) {
                    next = iNextInt + 33;
                    if (next == 44) {
                        iNextInt = random.nextInt(range);
                    }
                }
                text[i] = (char) next;
            }
            return new String(text);
        }
    }

    private static AuthenticationHashGenerator getAuthenicationHashGenerator(AuthenticationMechanism authenticationMechanism) {
        return authenticationMechanism == AuthenticationMechanism.SCRAM_SHA_1 ? LEGACY_AUTHENTICATION_HASH_GENERATOR : DEFAULT_AUTHENTICATION_HASH_GENERATOR;
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/ScramShaAuthenticator$CacheKey.class */
    private static class CacheKey {
        private final String hashedPasswordAndSalt;
        private final String salt;
        private final int iterationCount;

        CacheKey(String hashedPasswordAndSalt, String salt, int iterationCount) {
            this.hashedPasswordAndSalt = hashedPasswordAndSalt;
            this.salt = salt;
            this.iterationCount = iterationCount;
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            CacheKey that = (CacheKey) o;
            if (this.iterationCount != that.iterationCount || !this.hashedPasswordAndSalt.equals(that.hashedPasswordAndSalt)) {
                return false;
            }
            return this.salt.equals(that.salt);
        }

        public int hashCode() {
            int result = this.hashedPasswordAndSalt.hashCode();
            return (31 * ((31 * result) + this.salt.hashCode())) + this.iterationCount;
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/ScramShaAuthenticator$CacheValue.class */
    private static class CacheValue {
        private byte[] clientKey;
        private byte[] serverKey;

        CacheValue(byte[] clientKey, byte[] serverKey) {
            this.clientKey = clientKey;
            this.serverKey = serverKey;
        }
    }
}
