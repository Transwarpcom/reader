package com.htmake.reader.utils;

import com.htmake.reader.entity.MongoFile;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 5, 1},
   k = 1,
   xi = 48,
   d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0010\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\t\u001a\u00020\bJ\u001e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\bJ\u0006\u0010\u000f\u001a\u00020\u0010R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0011"},
   d2 = {"Lcom/htmake/reader/utils/MongoManager;", "", "()V", "mongoClient", "Lcom/mongodb/client/MongoClient;", "connect", "", "uri", "", "db", "Lcom/mongodb/client/MongoDatabase;", "fileStorage", "Lcom/mongodb/client/MongoCollection;", "Lcom/htmake/reader/entity/MongoFile;", "collection", "isInit", "", "reader-pro"}
)
public final class MongoManager {
   @NotNull
   public static final MongoManager INSTANCE = new MongoManager();
   private static MongoClient mongoClient;

   private MongoManager() {
   }

   public final boolean isInit() {
      return mongoClient != null;
   }

   public final void connect(@NotNull String uri) {
      Intrinsics.checkNotNullParameter(uri, "uri");

      try {
         MongoClient var2 = MongoClients.create(uri);
         Intrinsics.checkNotNullExpressionValue(var2, "create(uri)");
         mongoClient = var2;
      } catch (MongoException var3) {
         ExtKt.getLogger().info("mongodb 连接失败，请检查链接({})是否正确", uri);
         var3.printStackTrace();
      }

   }

   @Nullable
   public final MongoDatabase db(@NotNull String db) {
      Intrinsics.checkNotNullParameter(db, "db");
      if (!this.isInit()) {
         return null;
      } else {
         PojoCodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
         CodecRegistry[] var4 = new CodecRegistry[]{MongoClientSettings.getDefaultCodecRegistry(), null};
         CodecProvider[] var5 = new CodecProvider[]{(CodecProvider)pojoCodecProvider};
         var4[1] = CodecRegistries.fromProviders(var5);
         CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(var4);
         MongoClient var6 = mongoClient;
         if (var6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mongoClient");
            throw null;
         } else {
            return var6.getDatabase(db).withCodecRegistry(pojoCodecRegistry);
         }
      }
   }

   @Nullable
   public final MongoCollection<MongoFile> fileStorage(@NotNull String db, @NotNull String collection) {
      Intrinsics.checkNotNullParameter(db, "db");
      Intrinsics.checkNotNullParameter(collection, "collection");
      MongoDatabase var3 = this.db(db);
      return var3 == null ? null : var3.getCollection(collection, MongoFile.class);
   }
}
