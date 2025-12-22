package com.mongodb;

import com.mongodb.annotations.Immutable;
import com.mongodb.annotations.NotThreadSafe;
import com.mongodb.assertions.Assertions;
import com.mongodb.client.gridfs.codecs.GridFSFileCodecProvider;
import com.mongodb.client.model.geojson.codecs.GeoJsonCodecProvider;
import com.mongodb.connection.ClusterSettings;
import com.mongodb.connection.ConnectionPoolSettings;
import com.mongodb.connection.ServerSettings;
import com.mongodb.connection.SocketSettings;
import com.mongodb.connection.SslSettings;
import com.mongodb.connection.StreamFactoryFactory;
import com.mongodb.event.CommandListener;
import com.mongodb.lang.Nullable;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.bson.codecs.BsonValueCodecProvider;
import org.bson.codecs.DocumentCodecProvider;
import org.bson.codecs.IterableCodecProvider;
import org.bson.codecs.MapCodecProvider;
import org.bson.codecs.ValueCodecProvider;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.jsr310.Jsr310CodecProvider;

@Immutable
/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/MongoClientSettings.class */
public final class MongoClientSettings {
    private static final CodecRegistry DEFAULT_CODEC_REGISTRY = CodecRegistries.fromProviders((List<? extends CodecProvider>) Arrays.asList(new ValueCodecProvider(), new BsonValueCodecProvider(), new DBRefCodecProvider(), new DBObjectCodecProvider(), new DocumentCodecProvider(new DocumentToDBRefTransformer()), new IterableCodecProvider(new DocumentToDBRefTransformer()), new MapCodecProvider(new DocumentToDBRefTransformer()), new GeoJsonCodecProvider(), new GridFSFileCodecProvider(), new Jsr310CodecProvider()));
    private final ReadPreference readPreference;
    private final WriteConcern writeConcern;
    private final boolean retryWrites;
    private final ReadConcern readConcern;
    private final MongoCredential credential;
    private final StreamFactoryFactory streamFactoryFactory;
    private final List<CommandListener> commandListeners;
    private final CodecRegistry codecRegistry;
    private final ClusterSettings clusterSettings;
    private final SocketSettings socketSettings;
    private final SocketSettings heartbeatSocketSettings;
    private final ConnectionPoolSettings connectionPoolSettings;
    private final ServerSettings serverSettings;
    private final SslSettings sslSettings;
    private final String applicationName;
    private final List<MongoCompressor> compressorList;

    public static CodecRegistry getDefaultCodecRegistry() {
        return DEFAULT_CODEC_REGISTRY;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(MongoClientSettings settings) {
        return new Builder();
    }

    @NotThreadSafe
    /* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/MongoClientSettings$Builder.class */
    public static final class Builder {
        private ReadPreference readPreference;
        private WriteConcern writeConcern;
        private boolean retryWrites;
        private ReadConcern readConcern;
        private CodecRegistry codecRegistry;
        private StreamFactoryFactory streamFactoryFactory;
        private List<CommandListener> commandListeners;
        private final ClusterSettings.Builder clusterSettingsBuilder;
        private final SocketSettings.Builder socketSettingsBuilder;
        private final ConnectionPoolSettings.Builder connectionPoolSettingsBuilder;
        private final ServerSettings.Builder serverSettingsBuilder;
        private final SslSettings.Builder sslSettingsBuilder;
        private MongoCredential credential;
        private String applicationName;
        private List<MongoCompressor> compressorList;

        private Builder() {
            this.readPreference = ReadPreference.primary();
            this.writeConcern = WriteConcern.ACKNOWLEDGED;
            this.readConcern = ReadConcern.DEFAULT;
            this.codecRegistry = MongoClientSettings.getDefaultCodecRegistry();
            this.commandListeners = new ArrayList();
            this.clusterSettingsBuilder = ClusterSettings.builder();
            this.socketSettingsBuilder = SocketSettings.builder();
            this.connectionPoolSettingsBuilder = ConnectionPoolSettings.builder();
            this.serverSettingsBuilder = ServerSettings.builder();
            this.sslSettingsBuilder = SslSettings.builder();
            this.compressorList = Collections.emptyList();
        }

        private Builder(MongoClientSettings settings) {
            this.readPreference = ReadPreference.primary();
            this.writeConcern = WriteConcern.ACKNOWLEDGED;
            this.readConcern = ReadConcern.DEFAULT;
            this.codecRegistry = MongoClientSettings.getDefaultCodecRegistry();
            this.commandListeners = new ArrayList();
            this.clusterSettingsBuilder = ClusterSettings.builder();
            this.socketSettingsBuilder = SocketSettings.builder();
            this.connectionPoolSettingsBuilder = ConnectionPoolSettings.builder();
            this.serverSettingsBuilder = ServerSettings.builder();
            this.sslSettingsBuilder = SslSettings.builder();
            this.compressorList = Collections.emptyList();
            Assertions.notNull("settings", settings);
            this.applicationName = settings.getApplicationName();
            this.commandListeners = new ArrayList(settings.getCommandListeners());
            this.compressorList = new ArrayList(settings.getCompressorList());
            this.codecRegistry = settings.getCodecRegistry();
            this.readPreference = settings.getReadPreference();
            this.writeConcern = settings.getWriteConcern();
            this.retryWrites = settings.getRetryWrites();
            this.readConcern = settings.getReadConcern();
            this.credential = settings.getCredential();
            this.streamFactoryFactory = settings.getStreamFactoryFactory();
            this.clusterSettingsBuilder.applySettings(settings.getClusterSettings());
            this.serverSettingsBuilder.applySettings(settings.getServerSettings());
            this.socketSettingsBuilder.applySettings(settings.getSocketSettings());
            this.connectionPoolSettingsBuilder.applySettings(settings.getConnectionPoolSettings());
            this.sslSettingsBuilder.applySettings(settings.getSslSettings());
        }

        public Builder applyConnectionString(ConnectionString connectionString) {
            if (connectionString.getApplicationName() != null) {
                this.applicationName = connectionString.getApplicationName();
            }
            this.clusterSettingsBuilder.applyConnectionString(connectionString);
            if (!connectionString.getCompressorList().isEmpty()) {
                this.compressorList = connectionString.getCompressorList();
            }
            this.connectionPoolSettingsBuilder.applyConnectionString(connectionString);
            if (connectionString.getCredential() != null) {
                this.credential = connectionString.getCredential();
            }
            if (connectionString.getReadConcern() != null) {
                this.readConcern = connectionString.getReadConcern();
            }
            if (connectionString.getReadPreference() != null) {
                this.readPreference = connectionString.getReadPreference();
            }
            this.retryWrites = connectionString.getRetryWrites();
            this.serverSettingsBuilder.applyConnectionString(connectionString);
            this.socketSettingsBuilder.applyConnectionString(connectionString);
            this.sslSettingsBuilder.applyConnectionString(connectionString);
            if (connectionString.getWriteConcern() != null) {
                this.writeConcern = connectionString.getWriteConcern();
            }
            return this;
        }

        public Builder applyToClusterSettings(Block<ClusterSettings.Builder> block) {
            ((Block) Assertions.notNull("block", block)).apply(this.clusterSettingsBuilder);
            return this;
        }

        public Builder applyToSocketSettings(Block<SocketSettings.Builder> block) {
            ((Block) Assertions.notNull("block", block)).apply(this.socketSettingsBuilder);
            return this;
        }

        public Builder applyToConnectionPoolSettings(Block<ConnectionPoolSettings.Builder> block) {
            ((Block) Assertions.notNull("block", block)).apply(this.connectionPoolSettingsBuilder);
            return this;
        }

        public Builder applyToServerSettings(Block<ServerSettings.Builder> block) {
            ((Block) Assertions.notNull("block", block)).apply(this.serverSettingsBuilder);
            return this;
        }

        public Builder applyToSslSettings(Block<SslSettings.Builder> block) {
            ((Block) Assertions.notNull("block", block)).apply(this.sslSettingsBuilder);
            return this;
        }

        public Builder readPreference(ReadPreference readPreference) {
            this.readPreference = (ReadPreference) Assertions.notNull("readPreference", readPreference);
            return this;
        }

        public Builder writeConcern(WriteConcern writeConcern) {
            this.writeConcern = (WriteConcern) Assertions.notNull("writeConcern", writeConcern);
            return this;
        }

        public Builder retryWrites(boolean retryWrites) {
            this.retryWrites = retryWrites;
            return this;
        }

        public Builder readConcern(ReadConcern readConcern) {
            this.readConcern = (ReadConcern) Assertions.notNull("readConcern", readConcern);
            return this;
        }

        public Builder credential(MongoCredential credential) {
            this.credential = (MongoCredential) Assertions.notNull("credential", credential);
            return this;
        }

        public Builder codecRegistry(CodecRegistry codecRegistry) {
            this.codecRegistry = (CodecRegistry) Assertions.notNull("codecRegistry", codecRegistry);
            return this;
        }

        public Builder streamFactoryFactory(StreamFactoryFactory streamFactoryFactory) {
            this.streamFactoryFactory = (StreamFactoryFactory) Assertions.notNull("streamFactoryFactory", streamFactoryFactory);
            return this;
        }

        public Builder addCommandListener(CommandListener commandListener) {
            Assertions.notNull("commandListener", commandListener);
            this.commandListeners.add(commandListener);
            return this;
        }

        public Builder commandListenerList(List<CommandListener> commandListeners) {
            Assertions.notNull("commandListeners", commandListeners);
            this.commandListeners = new ArrayList(commandListeners);
            return this;
        }

        public Builder applicationName(@Nullable String applicationName) {
            if (applicationName != null) {
                Assertions.isTrueArgument("applicationName UTF-8 encoding length <= 128", applicationName.getBytes(Charset.forName("UTF-8")).length <= 128);
            }
            this.applicationName = applicationName;
            return this;
        }

        public Builder compressorList(List<MongoCompressor> compressorList) {
            Assertions.notNull("compressorList", compressorList);
            this.compressorList = new ArrayList(compressorList);
            return this;
        }

        public MongoClientSettings build() {
            return new MongoClientSettings(this);
        }
    }

    public ReadPreference getReadPreference() {
        return this.readPreference;
    }

    @Nullable
    public MongoCredential getCredential() {
        return this.credential;
    }

    public WriteConcern getWriteConcern() {
        return this.writeConcern;
    }

    public boolean getRetryWrites() {
        return this.retryWrites;
    }

    public ReadConcern getReadConcern() {
        return this.readConcern;
    }

    public CodecRegistry getCodecRegistry() {
        return this.codecRegistry;
    }

    @Nullable
    public StreamFactoryFactory getStreamFactoryFactory() {
        return this.streamFactoryFactory;
    }

    public List<CommandListener> getCommandListeners() {
        return Collections.unmodifiableList(this.commandListeners);
    }

    @Nullable
    public String getApplicationName() {
        return this.applicationName;
    }

    public List<MongoCompressor> getCompressorList() {
        return Collections.unmodifiableList(this.compressorList);
    }

    public ClusterSettings getClusterSettings() {
        return this.clusterSettings;
    }

    public SslSettings getSslSettings() {
        return this.sslSettings;
    }

    public SocketSettings getSocketSettings() {
        return this.socketSettings;
    }

    public SocketSettings getHeartbeatSocketSettings() {
        return this.heartbeatSocketSettings;
    }

    public ConnectionPoolSettings getConnectionPoolSettings() {
        return this.connectionPoolSettings;
    }

    public ServerSettings getServerSettings() {
        return this.serverSettings;
    }

    private MongoClientSettings(Builder builder) {
        this.readPreference = builder.readPreference;
        this.writeConcern = builder.writeConcern;
        this.retryWrites = builder.retryWrites;
        this.readConcern = builder.readConcern;
        this.credential = builder.credential;
        this.streamFactoryFactory = builder.streamFactoryFactory;
        this.codecRegistry = builder.codecRegistry;
        this.commandListeners = builder.commandListeners;
        this.applicationName = builder.applicationName;
        this.clusterSettings = builder.clusterSettingsBuilder.build();
        this.serverSettings = builder.serverSettingsBuilder.build();
        this.socketSettings = builder.socketSettingsBuilder.build();
        this.connectionPoolSettings = builder.connectionPoolSettingsBuilder.build();
        this.sslSettings = builder.sslSettingsBuilder.build();
        this.compressorList = builder.compressorList;
        SocketSettings.Builder heartbeatSocketSettingsBuilder = SocketSettings.builder().readTimeout(this.socketSettings.getConnectTimeout(TimeUnit.MILLISECONDS), TimeUnit.MILLISECONDS).connectTimeout(this.socketSettings.getConnectTimeout(TimeUnit.MILLISECONDS), TimeUnit.MILLISECONDS);
        this.heartbeatSocketSettings = heartbeatSocketSettingsBuilder.build();
    }
}
