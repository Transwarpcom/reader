package com.mongodb.internal.connection;

import com.mongodb.MongoDriverInformation;
import com.mongodb.assertions.Assertions;
import com.mongodb.internal.build.MongoDriverVersion;
import java.nio.charset.Charset;
import java.util.List;
import org.bson.BsonBinaryWriter;
import org.bson.BsonDocument;
import org.bson.BsonString;
import org.bson.BsonWriter;
import org.bson.codecs.BsonDocumentCodec;
import org.bson.codecs.EncoderContext;
import org.bson.io.BasicOutputBuffer;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/ClientMetadataHelper.class */
public final class ClientMetadataHelper {
    public static final BsonDocument CLIENT_METADATA_DOCUMENT = new BsonDocument();
    private static final String SEPARATOR = "|";
    private static final String APPLICATION_FIELD = "application";
    private static final String APPLICATION_NAME_FIELD = "name";
    private static final String DRIVER_FIELD = "driver";
    private static final String DRIVER_NAME_FIELD = "name";
    private static final String DRIVER_VERSION_FIELD = "version";
    private static final String PLATFORM_FIELD = "platform";
    private static final String OS_FIELD = "os";
    private static final String OS_TYPE_FIELD = "type";
    private static final String OS_NAME_FIELD = "name";
    private static final String OS_ARCHITECTURE_FIELD = "architecture";
    private static final String OS_VERSION_FIELD = "version";
    private static final int MAXIMUM_CLIENT_METADATA_ENCODED_SIZE = 512;

    static {
        BsonDocument driverMetadataDocument = addDriverInformation(null, new BsonDocument());
        CLIENT_METADATA_DOCUMENT.append(DRIVER_FIELD, driverMetadataDocument.get((Object) DRIVER_FIELD));
        try {
            String operatingSystemName = System.getProperty("os.name", "unknown");
            CLIENT_METADATA_DOCUMENT.append(OS_FIELD, new BsonDocument().append("type", new BsonString(getOperatingSystemType(operatingSystemName))).append("name", new BsonString(operatingSystemName)).append(OS_ARCHITECTURE_FIELD, new BsonString(System.getProperty("os.arch", "unknown"))).append("version", new BsonString(System.getProperty("os.version", "unknown")))).append(PLATFORM_FIELD, driverMetadataDocument.get(PLATFORM_FIELD, new BsonString("")));
        } catch (SecurityException e) {
        }
    }

    private static String getOperatingSystemType(String operatingSystemName) {
        if (nameMatches(operatingSystemName, "linux")) {
            return "Linux";
        }
        if (nameMatches(operatingSystemName, "mac")) {
            return "Darwin";
        }
        if (nameMatches(operatingSystemName, "windows")) {
            return "Windows";
        }
        if (nameMatches(operatingSystemName, "hp-ux", "aix", "irix", "solaris", "sunos")) {
            return "Unix";
        }
        return "unknown";
    }

    private static boolean nameMatches(String name, String... prefixes) {
        for (String prefix : prefixes) {
            if (name.toLowerCase().startsWith(prefix.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    static BsonDocument createClientMetadataDocument(String applicationName) {
        return createClientMetadataDocument(applicationName, null);
    }

    public static BsonDocument createClientMetadataDocument(String applicationName, MongoDriverInformation mongoDriverInformation) {
        return createClientMetadataDocument(applicationName, mongoDriverInformation, CLIENT_METADATA_DOCUMENT);
    }

    static BsonDocument createClientMetadataDocument(String applicationName, MongoDriverInformation mongoDriverInformation, BsonDocument templateDocument) {
        if (applicationName != null) {
            Assertions.isTrueArgument("applicationName UTF-8 encoding length <= 128", applicationName.getBytes(Charset.forName("UTF-8")).length <= 128);
        }
        BsonDocument document = templateDocument.mo1002clone();
        if (applicationName != null) {
            document.append("application", new BsonDocument("name", new BsonString(applicationName)));
        }
        if (mongoDriverInformation != null) {
            addDriverInformation(mongoDriverInformation, document);
        }
        if (clientMetadataDocumentTooLarge(document)) {
            BsonDocument operatingSystemDocument = document.getDocument(OS_FIELD, null);
            if (operatingSystemDocument != null) {
                operatingSystemDocument.remove("version");
                operatingSystemDocument.remove(OS_ARCHITECTURE_FIELD);
                operatingSystemDocument.remove("name");
            }
            if (operatingSystemDocument == null || clientMetadataDocumentTooLarge(document)) {
                document.remove(PLATFORM_FIELD);
                if (clientMetadataDocumentTooLarge(document)) {
                    document = new BsonDocument(DRIVER_FIELD, templateDocument.getDocument(DRIVER_FIELD));
                    document.append(OS_FIELD, new BsonDocument("type", new BsonString("unknown")));
                    if (clientMetadataDocumentTooLarge(document)) {
                        document = null;
                    }
                }
            }
        }
        return document;
    }

    private static BsonDocument addDriverInformation(MongoDriverInformation mongoDriverInformation, BsonDocument document) {
        MongoDriverInformation driverInformation = getDriverInformation(mongoDriverInformation);
        BsonDocument driverMetadataDocument = new BsonDocument("name", listToBsonString(driverInformation.getDriverNames())).append("version", listToBsonString(driverInformation.getDriverVersions()));
        document.append(DRIVER_FIELD, driverMetadataDocument);
        document.append(PLATFORM_FIELD, listToBsonString(driverInformation.getDriverPlatforms()));
        return document;
    }

    static boolean clientMetadataDocumentTooLarge(BsonDocument document) {
        BasicOutputBuffer buffer = new BasicOutputBuffer(512);
        new BsonDocumentCodec().encode((BsonWriter) new BsonBinaryWriter(buffer), document, EncoderContext.builder().build());
        return buffer.getPosition() > 512;
    }

    static MongoDriverInformation getDriverInformation(MongoDriverInformation mongoDriverInformation) {
        MongoDriverInformation.Builder builder = mongoDriverInformation != null ? MongoDriverInformation.builder(mongoDriverInformation) : MongoDriverInformation.builder();
        return builder.driverName(MongoDriverVersion.NAME).driverVersion(MongoDriverVersion.VERSION).driverPlatform(String.format("Java/%s/%s", System.getProperty("java.vendor", "unknown-vendor"), System.getProperty("java.runtime.version", "unknown-version"))).build();
    }

    static BsonString listToBsonString(List<String> listOfStrings) {
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        for (String val : listOfStrings) {
            if (i > 0) {
                stringBuilder.append(SEPARATOR);
            }
            stringBuilder.append(val);
            i++;
        }
        return new BsonString(stringBuilder.toString());
    }

    private ClientMetadataHelper() {
    }
}
