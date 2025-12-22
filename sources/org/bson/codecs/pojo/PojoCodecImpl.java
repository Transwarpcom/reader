package org.bson.codecs.pojo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.bson.BsonInvalidOperationException;
import org.bson.BsonReader;
import org.bson.BsonReaderMark;
import org.bson.BsonType;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.codecs.configuration.CodecConfigurationException;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.TypeData;
import org.bson.diagnostics.Logger;
import org.bson.diagnostics.Loggers;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/pojo/PojoCodecImpl.class */
final class PojoCodecImpl<T> extends PojoCodec<T> {
    private static final Logger LOGGER = Loggers.getLogger("PojoCodec");
    private final ClassModel<T> classModel;
    private final CodecRegistry registry;
    private final PropertyCodecRegistry propertyCodecRegistry;
    private final DiscriminatorLookup discriminatorLookup;
    private final ConcurrentMap<ClassModel<?>, Codec<?>> codecCache;
    private final boolean specialized;

    PojoCodecImpl(ClassModel<T> classModel, CodecRegistry codecRegistry, List<PropertyCodecProvider> propertyCodecProviders, DiscriminatorLookup discriminatorLookup) {
        this.classModel = classModel;
        this.registry = CodecRegistries.fromRegistries(CodecRegistries.fromCodecs((Codec<?>[]) new Codec[]{this}), codecRegistry);
        this.discriminatorLookup = discriminatorLookup;
        this.codecCache = new ConcurrentHashMap();
        this.propertyCodecRegistry = new PropertyCodecRegistryImpl(this, this.registry, propertyCodecProviders);
        this.specialized = shouldSpecialize(classModel);
        specialize();
    }

    PojoCodecImpl(ClassModel<T> classModel, CodecRegistry registry, PropertyCodecRegistry propertyCodecRegistry, DiscriminatorLookup discriminatorLookup, ConcurrentMap<ClassModel<?>, Codec<?>> codecCache, boolean specialized) {
        this.classModel = classModel;
        this.registry = CodecRegistries.fromRegistries(CodecRegistries.fromCodecs((Codec<?>[]) new Codec[]{this}), registry);
        this.discriminatorLookup = discriminatorLookup;
        this.codecCache = codecCache;
        this.propertyCodecRegistry = propertyCodecRegistry;
        this.specialized = specialized;
        specialize();
    }

    private void specialize() {
        if (this.specialized) {
            this.codecCache.put(this.classModel, this);
            Iterator<PropertyModel<?>> it = this.classModel.getPropertyModels().iterator();
            while (it.hasNext()) {
                PropertyModel<S> propertyModel = (PropertyModel) it.next();
                try {
                    addToCache(propertyModel);
                } catch (Exception e) {
                    throw new CodecConfigurationException(String.format("Could not create a PojoCodec for '%s'. Property '%s' errored with: %s", this.classModel.getName(), propertyModel.getName(), e.getMessage()), e);
                }
            }
        }
    }

    @Override // org.bson.codecs.Encoder
    public void encode(BsonWriter bsonWriter, T t, EncoderContext encoderContext) {
        if (!this.specialized) {
            throw new CodecConfigurationException(String.format("%s contains generic types that have not been specialised.%nTop level classes with generic types are not supported by the PojoCodec.", this.classModel.getName()));
        }
        if (areEquivalentTypes(t.getClass(), this.classModel.getType())) {
            bsonWriter.writeStartDocument();
            PropertyModel idPropertyModel = this.classModel.getIdPropertyModel();
            if (idPropertyModel != null) {
                encodeProperty(bsonWriter, t, encoderContext, idPropertyModel);
            }
            if (this.classModel.useDiscriminator()) {
                bsonWriter.writeString(this.classModel.getDiscriminatorKey(), this.classModel.getDiscriminator());
            }
            Iterator<PropertyModel<?>> it = this.classModel.getPropertyModels().iterator();
            while (it.hasNext()) {
                PropertyModel<S> propertyModel = (PropertyModel) it.next();
                if (!propertyModel.equals(this.classModel.getIdPropertyModel())) {
                    encodeProperty(bsonWriter, t, encoderContext, propertyModel);
                }
            }
            bsonWriter.writeEndDocument();
            return;
        }
        this.registry.get(t.getClass()).encode(bsonWriter, t, encoderContext);
    }

    @Override // org.bson.codecs.Decoder
    public T decode(BsonReader reader, DecoderContext decoderContext) {
        if (decoderContext.hasCheckedDiscriminator()) {
            if (!this.specialized) {
                throw new CodecConfigurationException(String.format("%s contains generic types that have not been specialised.%nTop level classes with generic types are not supported by the PojoCodec.", this.classModel.getName()));
            }
            InstanceCreator<T> instanceCreator = this.classModel.getInstanceCreator();
            decodeProperties(reader, decoderContext, instanceCreator);
            return instanceCreator.getInstance();
        }
        return getCodecFromDocument(reader, this.classModel.useDiscriminator(), this.classModel.getDiscriminatorKey(), this.registry, this.discriminatorLookup, this).decode(reader, DecoderContext.builder().checkedDiscriminator(true).build());
    }

    @Override // org.bson.codecs.Encoder
    public Class<T> getEncoderClass() {
        return this.classModel.getType();
    }

    public String toString() {
        return String.format("PojoCodec<%s>", this.classModel);
    }

    @Override // org.bson.codecs.pojo.PojoCodec
    ClassModel<T> getClassModel() {
        return this.classModel;
    }

    private <S> void encodeProperty(BsonWriter writer, T instance, EncoderContext encoderContext, PropertyModel<S> propertyModel) {
        if (propertyModel.isReadable()) {
            S propertyValue = propertyModel.getPropertyAccessor().get(instance);
            if (propertyModel.shouldSerialize(propertyValue)) {
                writer.writeName(propertyModel.getReadName());
                if (propertyValue == null) {
                    writer.writeNull();
                } else {
                    propertyModel.getCachedCodec().encode(writer, propertyValue, encoderContext);
                }
            }
        }
    }

    private void decodeProperties(BsonReader reader, DecoderContext decoderContext, InstanceCreator<T> instanceCreator) {
        reader.readStartDocument();
        while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
            String name = reader.readName();
            if (this.classModel.useDiscriminator() && this.classModel.getDiscriminatorKey().equals(name)) {
                reader.readString();
            } else {
                decodePropertyModel(reader, decoderContext, instanceCreator, name, getPropertyModelByWriteName(this.classModel, name));
            }
        }
        reader.readEndDocument();
    }

    private <S> void decodePropertyModel(BsonReader reader, DecoderContext decoderContext, InstanceCreator<T> instanceCreator, String name, PropertyModel<S> propertyModel) {
        if (propertyModel != null) {
            try {
                Object objDecodeWithChildContext = null;
                if (reader.getCurrentBsonType() == BsonType.NULL) {
                    reader.readNull();
                } else {
                    objDecodeWithChildContext = decoderContext.decodeWithChildContext(propertyModel.getCachedCodec(), reader);
                }
                if (propertyModel.isWritable()) {
                    instanceCreator.set(objDecodeWithChildContext, propertyModel);
                }
                return;
            } catch (BsonInvalidOperationException e) {
                throw new CodecConfigurationException(String.format("Failed to decode '%s'. Decoding '%s' errored with: %s", this.classModel.getName(), name, e.getMessage()), e);
            } catch (CodecConfigurationException e2) {
                throw new CodecConfigurationException(String.format("Failed to decode '%s'. Decoding '%s' errored with: %s", this.classModel.getName(), name, e2.getMessage()), e2);
            }
        }
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace(String.format("Found property not present in the ClassModel: %s", name));
        }
        reader.skipValue();
    }

    private <S> void addToCache(PropertyModel<S> propertyModel) {
        Codec<S> codec = propertyModel.getCodec() != null ? propertyModel.getCodec() : specializePojoCodec(propertyModel, this.propertyCodecRegistry.get(propertyModel.getTypeData()));
        propertyModel.cachedCodec(codec);
    }

    private <S, V> boolean areEquivalentTypes(Class<S> t1, Class<V> t2) {
        if (t1.equals(t2)) {
            return true;
        }
        if (Collection.class.isAssignableFrom(t1) && Collection.class.isAssignableFrom(t2)) {
            return true;
        }
        if (Map.class.isAssignableFrom(t1) && Map.class.isAssignableFrom(t2)) {
            return true;
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <S> Codec<S> specializePojoCodec(PropertyModel<S> propertyModel, Codec<S> defaultCodec) {
        Codec<S> codec = defaultCodec;
        if (codec != null && (codec instanceof PojoCodec)) {
            PojoCodec<S> pojoCodec = (PojoCodec) codec;
            ClassModel<S> specialized = getSpecializedClassModel(pojoCodec.getClassModel(), propertyModel);
            codec = this.codecCache.containsKey(specialized) ? (Codec) this.codecCache.get(specialized) : new LazyPojoCodec(specialized, this.registry, this.propertyCodecRegistry, this.discriminatorLookup, this.codecCache);
        }
        return codec;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <S, V> ClassModel<S> getSpecializedClassModel(ClassModel<S> clazzModel, PropertyModel<V> propertyModel) {
        boolean useDiscriminator = propertyModel.useDiscriminator() == null ? clazzModel.useDiscriminator() : propertyModel.useDiscriminator().booleanValue();
        boolean validDiscriminator = (clazzModel.getDiscriminatorKey() == null || clazzModel.getDiscriminator() == null) ? false : true;
        boolean changeTheDiscriminator = useDiscriminator != clazzModel.useDiscriminator() && validDiscriminator;
        if (propertyModel.getTypeData().getTypeParameters().isEmpty() && !changeTheDiscriminator) {
            return clazzModel;
        }
        ArrayList<PropertyModel<?>> concretePropertyModels = new ArrayList<>(clazzModel.getPropertyModels());
        PropertyModel idPropertyModel = clazzModel.getIdPropertyModel();
        List<TypeData<?>> propertyTypeParameters = propertyModel.getTypeData().getTypeParameters();
        for (int i = 0; i < concretePropertyModels.size(); i++) {
            PropertyModel<V> propertyModel2 = (PropertyModel) concretePropertyModels.get(i);
            String propertyName = propertyModel2.getName();
            TypeParameterMap typeParameterMap = clazzModel.getPropertyNameToTypeParameterMap().get(propertyName);
            if (typeParameterMap.hasTypeParameters()) {
                PropertyModel specializedPropertyModel = getSpecializedPropertyModel(propertyModel2, typeParameterMap, propertyTypeParameters);
                concretePropertyModels.set(i, specializedPropertyModel);
                if (idPropertyModel != null && idPropertyModel.getName().equals(propertyName)) {
                    idPropertyModel = specializedPropertyModel;
                }
            }
        }
        boolean discriminatorEnabled = changeTheDiscriminator ? propertyModel.useDiscriminator().booleanValue() : clazzModel.useDiscriminator();
        return new ClassModel<>(clazzModel.getType(), clazzModel.getPropertyNameToTypeParameterMap(), clazzModel.getInstanceCreatorFactory(), Boolean.valueOf(discriminatorEnabled), clazzModel.getDiscriminatorKey(), clazzModel.getDiscriminator(), idPropertyModel, concretePropertyModels);
    }

    private <V> PropertyModel<V> getSpecializedPropertyModel(PropertyModel<V> propertyModel, TypeParameterMap typeParameterMap, List<TypeData<?>> propertyTypeParameters) {
        TypeData<?> typeDataBuild;
        Map<Integer, Integer> propertyToClassParamIndexMap = typeParameterMap.getPropertyToClassParamIndexMap();
        Integer classTypeParamRepresentsWholeProperty = propertyToClassParamIndexMap.get(-1);
        if (classTypeParamRepresentsWholeProperty != null) {
            typeDataBuild = propertyTypeParameters.get(classTypeParamRepresentsWholeProperty.intValue());
        } else {
            TypeData.Builder<V> builder = TypeData.builder(propertyModel.getTypeData().getType());
            List<TypeData<?>> typeParameters = new ArrayList<>(propertyModel.getTypeData().getTypeParameters());
            for (int i = 0; i < typeParameters.size(); i++) {
                for (Map.Entry<Integer, Integer> mapping : propertyToClassParamIndexMap.entrySet()) {
                    if (mapping.getKey().equals(Integer.valueOf(i))) {
                        typeParameters.set(i, propertyTypeParameters.get(mapping.getValue().intValue()));
                    }
                }
            }
            builder.addTypeParameters(typeParameters);
            typeDataBuild = builder.build();
        }
        if (propertyModel.getTypeData().equals(typeDataBuild)) {
            return propertyModel;
        }
        return new PropertyModel<>(propertyModel.getName(), propertyModel.getReadName(), propertyModel.getWriteName(), typeDataBuild, null, propertyModel.getPropertySerialization(), propertyModel.useDiscriminator(), propertyModel.getPropertyAccessor());
    }

    private Codec<T> getCodecFromDocument(BsonReader reader, boolean useDiscriminator, String discriminatorKey, CodecRegistry registry, DiscriminatorLookup discriminatorLookup, Codec<T> defaultCodec) {
        Codec<T> codec = defaultCodec;
        if (useDiscriminator) {
            BsonReaderMark mark = reader.getMark();
            reader.readStartDocument();
            boolean discriminatorKeyFound = false;
            while (!discriminatorKeyFound && reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
                String name = reader.readName();
                if (discriminatorKey.equals(name)) {
                    discriminatorKeyFound = true;
                    try {
                        codec = registry.get(discriminatorLookup.lookup(reader.readString()));
                    } catch (Exception e) {
                        throw new CodecConfigurationException(String.format("Failed to decode '%s'. Decoding errored with: %s", this.classModel.getName(), e.getMessage()), e);
                    }
                } else {
                    reader.skipValue();
                }
            }
            mark.reset();
        }
        return codec;
    }

    private PropertyModel<?> getPropertyModelByWriteName(ClassModel<T> classModel, String readName) {
        for (PropertyModel<?> propertyModel : classModel.getPropertyModels()) {
            if (propertyModel.isWritable() && propertyModel.getWriteName().equals(readName)) {
                return propertyModel;
            }
        }
        return null;
    }

    private static <T> boolean shouldSpecialize(ClassModel<T> classModel) {
        if (!classModel.hasTypeParameters()) {
            return true;
        }
        for (Map.Entry<String, TypeParameterMap> entry : classModel.getPropertyNameToTypeParameterMap().entrySet()) {
            TypeParameterMap typeParameterMap = entry.getValue();
            PropertyModel<?> propertyModel = classModel.getPropertyModel(entry.getKey());
            if (typeParameterMap.hasTypeParameters() && (propertyModel == null || propertyModel.getCodec() == null)) {
                return false;
            }
        }
        return true;
    }
}
