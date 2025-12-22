package com.mongodb;

import ch.qos.logback.classic.spi.CallerData;
import cn.hutool.core.text.StrPool;
import com.mongodb.diagnostics.logging.Logger;
import com.mongodb.diagnostics.logging.Loggers;
import com.mongodb.internal.dns.DnsResolver;
import com.mongodb.lang.Nullable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.springframework.beans.factory.xml.BeanDefinitionParserDelegate;
import org.springframework.beans.propertyeditors.CustomBooleanEditor;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/ConnectionString.class */
public class ConnectionString {
    private static final String MONGODB_PREFIX = "mongodb://";
    private static final String MONGODB_SRV_PREFIX = "mongodb+srv://";
    private static final String UTF_8 = "UTF-8";
    private final MongoCredential credential;
    private final List<String> hosts;
    private final String database;
    private final String collection;
    private final String connectionString;
    private ReadPreference readPreference;
    private WriteConcern writeConcern;
    private boolean retryWrites;
    private ReadConcern readConcern;
    private Integer minConnectionPoolSize;
    private Integer maxConnectionPoolSize;
    private Integer threadsAllowedToBlockForConnectionMultiplier;
    private Integer maxWaitTime;
    private Integer maxConnectionIdleTime;
    private Integer maxConnectionLifeTime;
    private Integer connectTimeout;
    private Integer socketTimeout;
    private Boolean sslEnabled;
    private Boolean sslInvalidHostnameAllowed;
    private String streamType;
    private String requiredReplicaSetName;
    private Integer serverSelectionTimeout;
    private Integer localThreshold;
    private Integer heartbeatFrequency;
    private String applicationName;
    private List<MongoCompressor> compressorList;
    private static final Set<String> ALLOWED_OPTIONS_IN_TXT_RECORD = new HashSet(Arrays.asList("authsource", "replicaset"));
    private static final Logger LOGGER = Loggers.getLogger("uri");
    private static final Set<String> GENERAL_OPTIONS_KEYS = new HashSet();
    private static final Set<String> AUTH_KEYS = new HashSet();
    private static final Set<String> READ_PREFERENCE_KEYS = new HashSet();
    private static final Set<String> WRITE_CONCERN_KEYS = new HashSet();
    private static final Set<String> COMPRESSOR_KEYS = new HashSet();
    private static final Set<String> ALL_KEYS = new HashSet();

    static {
        GENERAL_OPTIONS_KEYS.add("minpoolsize");
        GENERAL_OPTIONS_KEYS.add("maxpoolsize");
        GENERAL_OPTIONS_KEYS.add("waitqueuemultiple");
        GENERAL_OPTIONS_KEYS.add("waitqueuetimeoutms");
        GENERAL_OPTIONS_KEYS.add("connecttimeoutms");
        GENERAL_OPTIONS_KEYS.add("maxidletimems");
        GENERAL_OPTIONS_KEYS.add("maxlifetimems");
        GENERAL_OPTIONS_KEYS.add("sockettimeoutms");
        GENERAL_OPTIONS_KEYS.add("ssl");
        GENERAL_OPTIONS_KEYS.add("streamtype");
        GENERAL_OPTIONS_KEYS.add("sslinvalidhostnameallowed");
        GENERAL_OPTIONS_KEYS.add("replicaset");
        GENERAL_OPTIONS_KEYS.add("readconcernlevel");
        GENERAL_OPTIONS_KEYS.add("serverselectiontimeoutms");
        GENERAL_OPTIONS_KEYS.add("localthresholdms");
        GENERAL_OPTIONS_KEYS.add("heartbeatfrequencyms");
        GENERAL_OPTIONS_KEYS.add("retrywrites");
        GENERAL_OPTIONS_KEYS.add("appname");
        COMPRESSOR_KEYS.add("compressors");
        COMPRESSOR_KEYS.add("zlibcompressionlevel");
        READ_PREFERENCE_KEYS.add("readpreference");
        READ_PREFERENCE_KEYS.add("readpreferencetags");
        READ_PREFERENCE_KEYS.add("maxstalenessseconds");
        WRITE_CONCERN_KEYS.add("safe");
        WRITE_CONCERN_KEYS.add(OperatorName.SET_LINE_WIDTH);
        WRITE_CONCERN_KEYS.add("wtimeoutms");
        WRITE_CONCERN_KEYS.add("fsync");
        WRITE_CONCERN_KEYS.add("journal");
        AUTH_KEYS.add("authmechanism");
        AUTH_KEYS.add("authsource");
        AUTH_KEYS.add("gssapiservicename");
        AUTH_KEYS.add("authmechanismproperties");
        ALL_KEYS.addAll(GENERAL_OPTIONS_KEYS);
        ALL_KEYS.addAll(AUTH_KEYS);
        ALL_KEYS.addAll(READ_PREFERENCE_KEYS);
        ALL_KEYS.addAll(WRITE_CONCERN_KEYS);
        ALL_KEYS.addAll(COMPRESSOR_KEYS);
    }

    public ConnectionString(String connectionString) {
        String unprocessedConnectionString;
        String userAndHostInformation;
        String unprocessedConnectionString2;
        String hostIdentifier;
        String nsPart;
        String unprocessedConnectionString3;
        this.connectionString = connectionString;
        boolean isMongoDBProtocol = connectionString.startsWith(MONGODB_PREFIX);
        boolean isSRVProtocol = connectionString.startsWith(MONGODB_SRV_PREFIX);
        if (!isMongoDBProtocol && !isSRVProtocol) {
            throw new IllegalArgumentException(String.format("The connection string is invalid. Connection strings must start with either '%s' or '%s", MONGODB_PREFIX, MONGODB_SRV_PREFIX));
        }
        if (isMongoDBProtocol) {
            unprocessedConnectionString = connectionString.substring(MONGODB_PREFIX.length());
        } else {
            unprocessedConnectionString = connectionString.substring(MONGODB_SRV_PREFIX.length());
        }
        int idx = unprocessedConnectionString.indexOf("/");
        if (idx == -1) {
            if (unprocessedConnectionString.contains(CallerData.NA)) {
                throw new IllegalArgumentException("The connection string contains options without trailing slash");
            }
            userAndHostInformation = unprocessedConnectionString;
            unprocessedConnectionString2 = "";
        } else {
            userAndHostInformation = unprocessedConnectionString.substring(0, idx);
            unprocessedConnectionString2 = unprocessedConnectionString.substring(idx + 1);
        }
        String userName = null;
        char[] password = null;
        int idx2 = userAndHostInformation.lastIndexOf(StrPool.AT);
        if (idx2 > 0) {
            String userInfo = userAndHostInformation.substring(0, idx2);
            hostIdentifier = userAndHostInformation.substring(idx2 + 1);
            int colonCount = countOccurrences(userInfo, ":");
            if (userInfo.contains(StrPool.AT) || colonCount > 1) {
                throw new IllegalArgumentException("The connection string contains invalid user information. If the username or password contains a colon (:) or an at-sign (@) then it must be urlencoded");
            }
            if (colonCount == 0) {
                userName = urldecode(userInfo);
            } else {
                int idx3 = userInfo.indexOf(":");
                userName = urldecode(userInfo.substring(0, idx3));
                password = urldecode(userInfo.substring(idx3 + 1), true).toCharArray();
            }
        } else {
            hostIdentifier = userAndHostInformation;
        }
        List<String> unresolvedHosts = Collections.unmodifiableList(parseHosts(Arrays.asList(hostIdentifier.split(",")), isSRVProtocol));
        this.hosts = isSRVProtocol ? DnsResolver.resolveHostFromSrvRecords(unresolvedHosts.get(0)) : unresolvedHosts;
        int idx4 = unprocessedConnectionString2.indexOf(CallerData.NA);
        if (idx4 == -1) {
            nsPart = unprocessedConnectionString2;
            unprocessedConnectionString3 = "";
        } else {
            nsPart = unprocessedConnectionString2.substring(0, idx4);
            unprocessedConnectionString3 = unprocessedConnectionString2.substring(idx4 + 1);
        }
        if (nsPart.length() > 0) {
            String nsPart2 = urldecode(nsPart);
            int idx5 = nsPart2.indexOf(".");
            if (idx5 < 0) {
                this.database = nsPart2;
                this.collection = null;
            } else {
                this.database = nsPart2.substring(0, idx5);
                this.collection = nsPart2.substring(idx5 + 1);
            }
            MongoNamespace.checkDatabaseNameValidity(this.database);
        } else {
            this.database = null;
            this.collection = null;
        }
        String txtRecordsQueryParameters = isSRVProtocol ? DnsResolver.resolveAdditionalQueryParametersFromTxtRecords(unresolvedHosts.get(0)) : "";
        String connectionStringQueryParamenters = unprocessedConnectionString3;
        Map<String, List<String>> connectionStringOptionsMap = parseOptions(connectionStringQueryParamenters);
        Map<String, List<String>> txtRecordsOptionsMap = parseOptions(txtRecordsQueryParameters);
        if (!ALLOWED_OPTIONS_IN_TXT_RECORD.containsAll(txtRecordsOptionsMap.keySet())) {
            throw new MongoConfigurationException(String.format("A TXT record is only permitted to contain the keys %s, but the TXT record for '%s' contains the keys %s", ALLOWED_OPTIONS_IN_TXT_RECORD, unresolvedHosts.get(0), txtRecordsOptionsMap.keySet()));
        }
        Map<String, List<String>> combinedOptionsMaps = combineOptionsMaps(txtRecordsOptionsMap, connectionStringOptionsMap);
        if (isSRVProtocol && !combinedOptionsMaps.containsKey("ssl")) {
            combinedOptionsMaps.put("ssl", Collections.singletonList("true"));
        }
        translateOptions(combinedOptionsMaps);
        this.credential = createCredentials(combinedOptionsMaps, userName, password);
        warnOnUnsupportedOptions(combinedOptionsMaps);
    }

    private Map<String, List<String>> combineOptionsMaps(Map<String, List<String>> txtRecordsOptionsMap, Map<String, List<String>> connectionStringOptionsMap) {
        Map<String, List<String>> combinedOptionsMaps = new HashMap<>(txtRecordsOptionsMap);
        for (Map.Entry<String, List<String>> entry : connectionStringOptionsMap.entrySet()) {
            combinedOptionsMaps.put(entry.getKey(), entry.getValue());
        }
        return combinedOptionsMaps;
    }

    private void warnOnUnsupportedOptions(Map<String, List<String>> optionsMap) {
        for (String key : optionsMap.keySet()) {
            if (!ALL_KEYS.contains(key) && LOGGER.isWarnEnabled()) {
                LOGGER.warn(String.format("Unsupported option '%s' in the connection string '%s'.", key, this.connectionString));
            }
        }
    }

    private void translateOptions(Map<String, List<String>> optionsMap) {
        for (String key : GENERAL_OPTIONS_KEYS) {
            String value = getLastValue(optionsMap, key);
            if (value != null) {
                if (key.equals("maxpoolsize")) {
                    this.maxConnectionPoolSize = Integer.valueOf(parseInteger(value, "maxpoolsize"));
                } else if (key.equals("minpoolsize")) {
                    this.minConnectionPoolSize = Integer.valueOf(parseInteger(value, "minpoolsize"));
                } else if (key.equals("maxidletimems")) {
                    this.maxConnectionIdleTime = Integer.valueOf(parseInteger(value, "maxidletimems"));
                } else if (key.equals("maxlifetimems")) {
                    this.maxConnectionLifeTime = Integer.valueOf(parseInteger(value, "maxlifetimems"));
                } else if (key.equals("waitqueuemultiple")) {
                    this.threadsAllowedToBlockForConnectionMultiplier = Integer.valueOf(parseInteger(value, "waitqueuemultiple"));
                } else if (key.equals("waitqueuetimeoutms")) {
                    this.maxWaitTime = Integer.valueOf(parseInteger(value, "waitqueuetimeoutms"));
                } else if (key.equals("connecttimeoutms")) {
                    this.connectTimeout = Integer.valueOf(parseInteger(value, "connecttimeoutms"));
                } else if (key.equals("sockettimeoutms")) {
                    this.socketTimeout = Integer.valueOf(parseInteger(value, "sockettimeoutms"));
                } else if (key.equals("sslinvalidhostnameallowed")) {
                    this.sslInvalidHostnameAllowed = Boolean.valueOf(parseBoolean(value, "sslinvalidhostnameallowed"));
                } else if (key.equals("ssl")) {
                    this.sslEnabled = Boolean.valueOf(parseBoolean(value, "ssl"));
                } else if (key.equals("streamtype")) {
                    this.streamType = value;
                } else if (key.equals("replicaset")) {
                    this.requiredReplicaSetName = value;
                } else if (key.equals("readconcernlevel")) {
                    this.readConcern = new ReadConcern(ReadConcernLevel.fromString(value));
                } else if (key.equals("serverselectiontimeoutms")) {
                    this.serverSelectionTimeout = Integer.valueOf(parseInteger(value, "serverselectiontimeoutms"));
                } else if (key.equals("localthresholdms")) {
                    this.localThreshold = Integer.valueOf(parseInteger(value, "localthresholdms"));
                } else if (key.equals("heartbeatfrequencyms")) {
                    this.heartbeatFrequency = Integer.valueOf(parseInteger(value, "heartbeatfrequencyms"));
                } else if (key.equals("appname")) {
                    this.applicationName = value;
                } else if (key.equals("retrywrites")) {
                    this.retryWrites = parseBoolean(value, "retrywrites");
                }
            }
        }
        this.writeConcern = createWriteConcern(optionsMap);
        this.readPreference = createReadPreference(optionsMap);
        this.compressorList = createCompressors(optionsMap);
    }

    private List<MongoCompressor> createCompressors(Map<String, List<String>> optionsMap) {
        String compressors = "";
        Integer zlibCompressionLevel = null;
        for (String key : COMPRESSOR_KEYS) {
            String value = getLastValue(optionsMap, key);
            if (value != null) {
                if (key.equals("compressors")) {
                    compressors = value;
                } else if (key.equals("zlibcompressionlevel")) {
                    zlibCompressionLevel = Integer.valueOf(Integer.parseInt(value));
                }
            }
        }
        return buildCompressors(compressors, zlibCompressionLevel);
    }

    private List<MongoCompressor> buildCompressors(String compressors, @Nullable Integer zlibCompressionLevel) {
        List<MongoCompressor> compressorsList = new ArrayList<>();
        for (String cur : compressors.split(",")) {
            if (cur.equals("zlib")) {
                MongoCompressor zlibCompressor = MongoCompressor.createZlibCompressor();
                if (zlibCompressionLevel != null) {
                    zlibCompressor = zlibCompressor.withProperty(MongoCompressor.LEVEL, zlibCompressionLevel);
                }
                compressorsList.add(zlibCompressor);
            } else if (cur.equals("snappy")) {
                compressorsList.add(MongoCompressor.createSnappyCompressor());
            } else if (!cur.isEmpty()) {
                throw new IllegalArgumentException("Unsupported compressor '" + cur + OperatorName.SHOW_TEXT_LINE);
            }
        }
        return Collections.unmodifiableList(compressorsList);
    }

    @Nullable
    private WriteConcern createWriteConcern(Map<String, List<String>> optionsMap) {
        Boolean safe = null;
        String w = null;
        Integer wTimeout = null;
        Boolean fsync = null;
        Boolean journal = null;
        for (String key : WRITE_CONCERN_KEYS) {
            String value = getLastValue(optionsMap, key);
            if (value != null) {
                if (key.equals("safe")) {
                    safe = Boolean.valueOf(parseBoolean(value, "safe"));
                } else if (key.equals(OperatorName.SET_LINE_WIDTH)) {
                    w = value;
                } else if (key.equals("wtimeoutms")) {
                    wTimeout = Integer.valueOf(Integer.parseInt(value));
                } else if (key.equals("fsync")) {
                    fsync = Boolean.valueOf(parseBoolean(value, "fsync"));
                } else if (key.equals("journal")) {
                    journal = Boolean.valueOf(parseBoolean(value, "journal"));
                }
            }
        }
        return buildWriteConcern(safe, w, wTimeout, fsync, journal);
    }

    @Nullable
    private ReadPreference createReadPreference(Map<String, List<String>> optionsMap) {
        String readPreferenceType = null;
        List<TagSet> tagSetList = new ArrayList<>();
        long maxStalenessSeconds = -1;
        for (String key : READ_PREFERENCE_KEYS) {
            String value = getLastValue(optionsMap, key);
            if (value != null) {
                if (key.equals("readpreference")) {
                    readPreferenceType = value;
                } else if (key.equals("maxstalenessseconds")) {
                    maxStalenessSeconds = parseInteger(value, "maxstalenessseconds");
                } else if (key.equals("readpreferencetags")) {
                    for (String cur : optionsMap.get(key)) {
                        TagSet tagSet = getTags(cur.trim());
                        tagSetList.add(tagSet);
                    }
                }
            }
        }
        return buildReadPreference(readPreferenceType, tagSetList, maxStalenessSeconds);
    }

    @Nullable
    private MongoCredential createCredentials(Map<String, List<String>> optionsMap, @Nullable String userName, @Nullable char[] password) {
        MongoCredential mongoCredentialWithMechanismProperty;
        AuthenticationMechanism mechanism = null;
        String authSource = null;
        String gssapiServiceName = null;
        String authMechanismProperties = null;
        for (String key : AUTH_KEYS) {
            String value = getLastValue(optionsMap, key);
            if (value != null) {
                if (key.equals("authmechanism")) {
                    mechanism = AuthenticationMechanism.fromMechanismName(value);
                } else if (key.equals("authsource")) {
                    authSource = value;
                } else if (key.equals("gssapiservicename")) {
                    gssapiServiceName = value;
                } else if (key.equals("authmechanismproperties")) {
                    authMechanismProperties = value;
                }
            }
        }
        MongoCredential credential = null;
        if (mechanism != null) {
            credential = createMongoCredentialWithMechanism(mechanism, userName, password, authSource, gssapiServiceName);
        } else if (userName != null) {
            credential = MongoCredential.createCredential(userName, getAuthSourceOrDefault(authSource, this.database != null ? this.database : "admin"), password);
        }
        if (credential != null && authMechanismProperties != null) {
            for (String part : authMechanismProperties.split(",")) {
                String[] mechanismPropertyKeyValue = part.split(":");
                if (mechanismPropertyKeyValue.length != 2) {
                    throw new IllegalArgumentException(String.format("The connection string contains invalid authentication properties. '%s' is not a key value pair", part));
                }
                String key2 = mechanismPropertyKeyValue[0].trim().toLowerCase();
                String value2 = mechanismPropertyKeyValue[1].trim();
                if (key2.equals("canonicalize_host_name")) {
                    mongoCredentialWithMechanismProperty = credential.withMechanismProperty(key2, Boolean.valueOf(value2));
                } else {
                    mongoCredentialWithMechanismProperty = credential.withMechanismProperty(key2, value2);
                }
                credential = mongoCredentialWithMechanismProperty;
            }
        }
        return credential;
    }

    private MongoCredential createMongoCredentialWithMechanism(AuthenticationMechanism mechanism, String userName, @Nullable char[] password, @Nullable String authSource, @Nullable String gssapiServiceName) {
        String mechanismAuthSource;
        MongoCredential credential;
        switch (mechanism) {
            case PLAIN:
                mechanismAuthSource = getAuthSourceOrDefault(authSource, this.database != null ? this.database : "$external");
                break;
            case GSSAPI:
            case MONGODB_X509:
                mechanismAuthSource = getAuthSourceOrDefault(authSource, "$external");
                if (!mechanismAuthSource.equals("$external")) {
                    throw new IllegalArgumentException(String.format("Invalid authSource for %s, it must be '$external'", mechanism));
                }
                break;
            default:
                mechanismAuthSource = getAuthSourceOrDefault(authSource, this.database != null ? this.database : "admin");
                break;
        }
        switch (mechanism) {
            case PLAIN:
                credential = MongoCredential.createPlainCredential(userName, mechanismAuthSource, password);
                break;
            case GSSAPI:
                credential = MongoCredential.createGSSAPICredential(userName);
                if (gssapiServiceName != null) {
                    credential = credential.withMechanismProperty(MongoCredential.SERVICE_NAME_KEY, gssapiServiceName);
                }
                if (password != null && LOGGER.isWarnEnabled()) {
                    LOGGER.warn("Password in connection string not used with MONGODB_X509 authentication mechanism.");
                    break;
                }
                break;
            case MONGODB_X509:
                if (password != null) {
                    throw new IllegalArgumentException("Invalid mechanism, MONGODB_x509 does not support passwords");
                }
                credential = MongoCredential.createMongoX509Credential(userName);
                break;
            case MONGODB_CR:
                credential = MongoCredential.createMongoCRCredential(userName, mechanismAuthSource, password);
                break;
            case SCRAM_SHA_1:
                credential = MongoCredential.createScramSha1Credential(userName, mechanismAuthSource, password);
                break;
            case SCRAM_SHA_256:
                credential = MongoCredential.createScramSha256Credential(userName, mechanismAuthSource, password);
                break;
            default:
                throw new UnsupportedOperationException(String.format("The connection string contains an invalid authentication mechanism'. '%s' is not a supported authentication mechanism", mechanism));
        }
        return credential;
    }

    private String getAuthSourceOrDefault(@Nullable String authSource, String defaultAuthSource) {
        if (authSource != null) {
            return authSource;
        }
        return defaultAuthSource;
    }

    @Nullable
    private String getLastValue(Map<String, List<String>> optionsMap, String key) {
        List<String> valueList = optionsMap.get(key);
        if (valueList == null) {
            return null;
        }
        return valueList.get(valueList.size() - 1);
    }

    private Map<String, List<String>> parseOptions(String optionsPart) {
        HashMap map = new HashMap();
        if (optionsPart.length() == 0) {
            return map;
        }
        for (String part : optionsPart.split("&|;")) {
            if (part.length() != 0) {
                int idx = part.indexOf("=");
                if (idx >= 0) {
                    String key = part.substring(0, idx).toLowerCase();
                    String value = part.substring(idx + 1);
                    List<String> valueList = (List) map.get(key);
                    if (valueList == null) {
                        valueList = new ArrayList<>(1);
                    }
                    valueList.add(urldecode(value));
                    map.put(key, valueList);
                } else {
                    throw new IllegalArgumentException(String.format("The connection string contains an invalid option '%s'. '%s' is missing the value delimiter eg '%s=value'", optionsPart, part, part));
                }
            }
        }
        if (map.containsKey("wtimeout") && !map.containsKey("wtimeoutms")) {
            map.put("wtimeoutms", map.remove("wtimeout"));
            if (LOGGER.isWarnEnabled()) {
                LOGGER.warn("Uri option 'wtimeout' has been deprecated, use 'wtimeoutms' instead.");
            }
        }
        String slaveok = getLastValue(map, "slaveok");
        if (slaveok != null && !map.containsKey("readpreference")) {
            String readPreference = parseBoolean(slaveok, "slaveok") ? "secondaryPreferred" : BeanDefinitionParserDelegate.PRIMARY_ATTRIBUTE;
            map.put("readpreference", Collections.singletonList(readPreference));
            if (LOGGER.isWarnEnabled()) {
                LOGGER.warn("Uri option 'slaveok' has been deprecated, use 'readpreference' instead.");
            }
        }
        if (map.containsKey(OperatorName.SET_LINE_JOINSTYLE) && !map.containsKey("journal")) {
            map.put("journal", map.remove(OperatorName.SET_LINE_JOINSTYLE));
            if (LOGGER.isWarnEnabled()) {
                LOGGER.warn("Uri option 'j' has been deprecated, use 'journal' instead.");
            }
        }
        return map;
    }

    @Nullable
    private ReadPreference buildReadPreference(@Nullable String readPreferenceType, List<TagSet> tagSetList, long maxStalenessSeconds) {
        if (readPreferenceType != null) {
            if (tagSetList.isEmpty() && maxStalenessSeconds == -1) {
                return ReadPreference.valueOf(readPreferenceType);
            }
            if (maxStalenessSeconds == -1) {
                return ReadPreference.valueOf(readPreferenceType, tagSetList);
            }
            return ReadPreference.valueOf(readPreferenceType, tagSetList, maxStalenessSeconds, TimeUnit.SECONDS);
        }
        if (!tagSetList.isEmpty() || maxStalenessSeconds != -1) {
            throw new IllegalArgumentException("Read preference mode must be specified if either read preference tags or max staleness is specified");
        }
        return null;
    }

    @Nullable
    private WriteConcern buildWriteConcern(@Nullable Boolean safe, @Nullable String w, @Nullable Integer wTimeout, @Nullable Boolean fsync, @Nullable Boolean journal) {
        WriteConcern retVal;
        WriteConcern retVal2 = null;
        if (w != null || wTimeout != null || fsync != null || journal != null) {
            if (w == null) {
                retVal = WriteConcern.ACKNOWLEDGED;
            } else {
                try {
                    retVal = new WriteConcern(Integer.parseInt(w));
                } catch (NumberFormatException e) {
                    retVal = new WriteConcern(w);
                }
            }
            if (wTimeout != null) {
                retVal = retVal.withWTimeout(wTimeout.intValue(), TimeUnit.MILLISECONDS);
            }
            if (journal != null) {
                retVal = retVal.withJournal(journal);
            }
            if (fsync != null) {
                retVal = retVal.withFsync(fsync.booleanValue());
            }
            return retVal;
        }
        if (safe != null) {
            if (safe.booleanValue()) {
                retVal2 = WriteConcern.ACKNOWLEDGED;
            } else {
                retVal2 = WriteConcern.UNACKNOWLEDGED;
            }
        }
        return retVal2;
    }

    private TagSet getTags(String tagSetString) {
        List<Tag> tagList = new ArrayList<>();
        if (tagSetString.length() > 0) {
            for (String tag : tagSetString.split(",")) {
                String[] tagKeyValuePair = tag.split(":");
                if (tagKeyValuePair.length != 2) {
                    throw new IllegalArgumentException(String.format("The connection string contains an invalid read preference tag. '%s' is not a key value pair", tagSetString));
                }
                tagList.add(new Tag(tagKeyValuePair[0].trim(), tagKeyValuePair[1].trim()));
            }
        }
        return new TagSet(tagList);
    }

    private boolean parseBoolean(String input, String key) {
        String trimmedInput = input.trim();
        boolean isTrue = trimmedInput.length() > 0 && (trimmedInput.equals(CustomBooleanEditor.VALUE_1) || trimmedInput.toLowerCase().equals("true") || trimmedInput.toLowerCase().equals(CustomBooleanEditor.VALUE_YES));
        if (!input.equals("true") && !input.equals("false") && LOGGER.isWarnEnabled()) {
            LOGGER.warn(String.format("Deprecated boolean value ('%s') in the connection string for '%s', please update to %s=%s", input, key, key, Boolean.valueOf(isTrue)));
        }
        return isTrue;
    }

    private int parseInteger(String input, String key) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(String.format("The connection string contains an invalid value for '%s'. '%s' is not a valid integer", key, input));
        }
    }

    private List<String> parseHosts(List<String> rawHosts, boolean isSRVProtocol) throws NumberFormatException {
        if (rawHosts.size() == 0) {
            throw new IllegalArgumentException("The connection string must contain at least one host");
        }
        List<String> hosts = new ArrayList<>();
        for (String host : rawHosts) {
            if (host.length() == 0) {
                throw new IllegalArgumentException(String.format("The connection string contains an empty host '%s'. ", rawHosts));
            }
            if (host.endsWith(".sock")) {
                host = urldecode(host);
            } else if (host.startsWith("[")) {
                if (!host.contains("]")) {
                    throw new IllegalArgumentException(String.format("The connection string contains an invalid host '%s'. IPv6 address literals must be enclosed in '[' and ']' according to RFC 2732", host));
                }
                int idx = host.indexOf("]:");
                if (idx != -1) {
                    validatePort(host, host.substring(idx + 2));
                }
            } else {
                int colonCount = countOccurrences(host, ":");
                if (colonCount > 1) {
                    throw new IllegalArgumentException(String.format("The connection string contains an invalid host '%s'. Reserved characters such as ':' must be escaped according RFC 2396. Any IPv6 address literal must be enclosed in '[' and ']' according to RFC 2732.", host));
                }
                if (colonCount != 1) {
                    continue;
                } else {
                    if (isSRVProtocol) {
                        throw new IllegalArgumentException("A connection string using the mongodb+srv protocol can notcontain a host name that specifies a port");
                    }
                    validatePort(host, host.substring(host.indexOf(":") + 1));
                }
            }
            hosts.add(host);
        }
        if (isSRVProtocol && hosts.size() > 1) {
            throw new IllegalArgumentException("The mongodb+srv protocol requires a single host name but this connection string has more than one: " + this.connectionString);
        }
        Collections.sort(hosts);
        return hosts;
    }

    private void validatePort(String host, String port) throws NumberFormatException {
        boolean invalidPort = false;
        try {
            int portInt = Integer.parseInt(port);
            if (portInt <= 0 || portInt > 65535) {
                invalidPort = true;
            }
        } catch (NumberFormatException e) {
            invalidPort = true;
        }
        if (invalidPort) {
            throw new IllegalArgumentException(String.format("The connection string contains an invalid host '%s'. The port '%s' is not a valid, it must be an integer between 0 and 65535", host, port));
        }
    }

    private int countOccurrences(String haystack, String needle) {
        return haystack.length() - haystack.replace(needle, "").length();
    }

    private String urldecode(String input) {
        return urldecode(input, false);
    }

    private String urldecode(String input, boolean password) {
        try {
            return URLDecoder.decode(input, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            if (password) {
                throw new IllegalArgumentException("The connection string contained unsupported characters in the password.");
            }
            throw new IllegalArgumentException(String.format("The connection string contained unsupported characters: '%s'.Decoding produced the following error: %s", input, e.getMessage()));
        }
    }

    @Nullable
    public String getUsername() {
        if (this.credential != null) {
            return this.credential.getUserName();
        }
        return null;
    }

    @Nullable
    public char[] getPassword() {
        if (this.credential != null) {
            return this.credential.getPassword();
        }
        return null;
    }

    public List<String> getHosts() {
        return this.hosts;
    }

    @Nullable
    public String getDatabase() {
        return this.database;
    }

    @Nullable
    public String getCollection() {
        return this.collection;
    }

    @Deprecated
    public String getURI() {
        return getConnectionString();
    }

    public String getConnectionString() {
        return this.connectionString;
    }

    @Deprecated
    public List<MongoCredential> getCredentialList() {
        return this.credential != null ? Collections.singletonList(this.credential) : Collections.emptyList();
    }

    @Nullable
    public MongoCredential getCredential() {
        return this.credential;
    }

    @Nullable
    public ReadPreference getReadPreference() {
        return this.readPreference;
    }

    @Nullable
    public ReadConcern getReadConcern() {
        return this.readConcern;
    }

    @Nullable
    public WriteConcern getWriteConcern() {
        return this.writeConcern;
    }

    public boolean getRetryWrites() {
        return this.retryWrites;
    }

    @Nullable
    public Integer getMinConnectionPoolSize() {
        return this.minConnectionPoolSize;
    }

    @Nullable
    public Integer getMaxConnectionPoolSize() {
        return this.maxConnectionPoolSize;
    }

    @Nullable
    public Integer getThreadsAllowedToBlockForConnectionMultiplier() {
        return this.threadsAllowedToBlockForConnectionMultiplier;
    }

    @Nullable
    public Integer getMaxWaitTime() {
        return this.maxWaitTime;
    }

    @Nullable
    public Integer getMaxConnectionIdleTime() {
        return this.maxConnectionIdleTime;
    }

    @Nullable
    public Integer getMaxConnectionLifeTime() {
        return this.maxConnectionLifeTime;
    }

    @Nullable
    public Integer getConnectTimeout() {
        return this.connectTimeout;
    }

    @Nullable
    public Integer getSocketTimeout() {
        return this.socketTimeout;
    }

    @Nullable
    public Boolean getSslEnabled() {
        return this.sslEnabled;
    }

    @Nullable
    public String getStreamType() {
        return this.streamType;
    }

    @Nullable
    public Boolean getSslInvalidHostnameAllowed() {
        return this.sslInvalidHostnameAllowed;
    }

    @Nullable
    public String getRequiredReplicaSetName() {
        return this.requiredReplicaSetName;
    }

    @Nullable
    public Integer getServerSelectionTimeout() {
        return this.serverSelectionTimeout;
    }

    @Nullable
    public Integer getLocalThreshold() {
        return this.localThreshold;
    }

    @Nullable
    public Integer getHeartbeatFrequency() {
        return this.heartbeatFrequency;
    }

    @Nullable
    public String getApplicationName() {
        return this.applicationName;
    }

    public List<MongoCompressor> getCompressorList() {
        return this.compressorList;
    }

    public String toString() {
        return this.connectionString;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ConnectionString)) {
            return false;
        }
        ConnectionString that = (ConnectionString) o;
        if (this.collection != null) {
            if (!this.collection.equals(that.collection)) {
                return false;
            }
        } else if (that.collection != null) {
            return false;
        }
        if (this.connectTimeout != null) {
            if (!this.connectTimeout.equals(that.connectTimeout)) {
                return false;
            }
        } else if (that.connectTimeout != null) {
            return false;
        }
        if (this.credential != null) {
            if (!this.credential.equals(that.credential)) {
                return false;
            }
        } else if (that.credential != null) {
            return false;
        }
        if (this.database != null) {
            if (!this.database.equals(that.database)) {
                return false;
            }
        } else if (that.database != null) {
            return false;
        }
        if (!this.hosts.equals(that.hosts)) {
            return false;
        }
        if (this.maxConnectionIdleTime != null) {
            if (!this.maxConnectionIdleTime.equals(that.maxConnectionIdleTime)) {
                return false;
            }
        } else if (that.maxConnectionIdleTime != null) {
            return false;
        }
        if (this.maxConnectionLifeTime != null) {
            if (!this.maxConnectionLifeTime.equals(that.maxConnectionLifeTime)) {
                return false;
            }
        } else if (that.maxConnectionLifeTime != null) {
            return false;
        }
        if (this.maxConnectionPoolSize != null) {
            if (!this.maxConnectionPoolSize.equals(that.maxConnectionPoolSize)) {
                return false;
            }
        } else if (that.maxConnectionPoolSize != null) {
            return false;
        }
        if (this.maxWaitTime != null) {
            if (!this.maxWaitTime.equals(that.maxWaitTime)) {
                return false;
            }
        } else if (that.maxWaitTime != null) {
            return false;
        }
        if (this.minConnectionPoolSize != null) {
            if (!this.minConnectionPoolSize.equals(that.minConnectionPoolSize)) {
                return false;
            }
        } else if (that.minConnectionPoolSize != null) {
            return false;
        }
        if (this.readPreference != null) {
            if (!this.readPreference.equals(that.readPreference)) {
                return false;
            }
        } else if (that.readPreference != null) {
            return false;
        }
        if (this.requiredReplicaSetName != null) {
            if (!this.requiredReplicaSetName.equals(that.requiredReplicaSetName)) {
                return false;
            }
        } else if (that.requiredReplicaSetName != null) {
            return false;
        }
        if (this.socketTimeout != null) {
            if (!this.socketTimeout.equals(that.socketTimeout)) {
                return false;
            }
        } else if (that.socketTimeout != null) {
            return false;
        }
        if (this.sslEnabled != null) {
            if (!this.sslEnabled.equals(that.sslEnabled)) {
                return false;
            }
        } else if (that.sslEnabled != null) {
            return false;
        }
        if (this.threadsAllowedToBlockForConnectionMultiplier != null) {
            if (!this.threadsAllowedToBlockForConnectionMultiplier.equals(that.threadsAllowedToBlockForConnectionMultiplier)) {
                return false;
            }
        } else if (that.threadsAllowedToBlockForConnectionMultiplier != null) {
            return false;
        }
        if (this.writeConcern != null) {
            if (!this.writeConcern.equals(that.writeConcern)) {
                return false;
            }
        } else if (that.writeConcern != null) {
            return false;
        }
        if (this.applicationName != null) {
            if (!this.applicationName.equals(that.applicationName)) {
                return false;
            }
        } else if (that.applicationName != null) {
            return false;
        }
        if (!this.compressorList.equals(that.compressorList)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int iHashCode;
        int result = this.credential != null ? this.credential.hashCode() : 0;
        int iHashCode2 = 31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * result) + this.hosts.hashCode())) + (this.database != null ? this.database.hashCode() : 0))) + (this.collection != null ? this.collection.hashCode() : 0))) + (this.readPreference != null ? this.readPreference.hashCode() : 0))) + (this.writeConcern != null ? this.writeConcern.hashCode() : 0))) + (this.minConnectionPoolSize != null ? this.minConnectionPoolSize.hashCode() : 0))) + (this.maxConnectionPoolSize != null ? this.maxConnectionPoolSize.hashCode() : 0));
        if (this.threadsAllowedToBlockForConnectionMultiplier != null) {
            iHashCode = this.threadsAllowedToBlockForConnectionMultiplier.hashCode();
        } else {
            iHashCode = 0;
        }
        int result2 = iHashCode2 + iHashCode;
        return (31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * ((31 * result2) + (this.maxWaitTime != null ? this.maxWaitTime.hashCode() : 0))) + (this.maxConnectionIdleTime != null ? this.maxConnectionIdleTime.hashCode() : 0))) + (this.maxConnectionLifeTime != null ? this.maxConnectionLifeTime.hashCode() : 0))) + (this.connectTimeout != null ? this.connectTimeout.hashCode() : 0))) + (this.socketTimeout != null ? this.socketTimeout.hashCode() : 0))) + (this.sslEnabled != null ? this.sslEnabled.hashCode() : 0))) + (this.requiredReplicaSetName != null ? this.requiredReplicaSetName.hashCode() : 0))) + (this.applicationName != null ? this.applicationName.hashCode() : 0))) + this.compressorList.hashCode();
    }
}
