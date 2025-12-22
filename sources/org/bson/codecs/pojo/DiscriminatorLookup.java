package org.bson.codecs.pojo;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.bson.codecs.configuration.CodecConfigurationException;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/pojo/DiscriminatorLookup.class */
final class DiscriminatorLookup {
    private final Map<String, Class<?>> discriminatorClassMap = new ConcurrentHashMap();
    private final Set<String> packages;

    DiscriminatorLookup(Map<Class<?>, ClassModel<?>> classModels, Set<String> packages) {
        for (ClassModel<?> classModel : classModels.values()) {
            if (classModel.getDiscriminator() != null) {
                this.discriminatorClassMap.put(classModel.getDiscriminator(), classModel.getType());
            }
        }
        this.packages = packages;
    }

    public Class<?> lookup(String discriminator) throws ClassNotFoundException {
        if (this.discriminatorClassMap.containsKey(discriminator)) {
            return this.discriminatorClassMap.get(discriminator);
        }
        Class<?> clazz = getClassForName(discriminator);
        if (clazz == null) {
            clazz = searchPackages(discriminator);
        }
        if (clazz == null) {
            throw new CodecConfigurationException(String.format("A class could not be found for the discriminator: '%s'.", discriminator));
        }
        this.discriminatorClassMap.put(discriminator, clazz);
        return clazz;
    }

    void addClassModel(ClassModel<?> classModel) {
        if (classModel.getDiscriminator() != null) {
            this.discriminatorClassMap.put(classModel.getDiscriminator(), classModel.getType());
        }
    }

    private Class<?> getClassForName(String discriminator) throws ClassNotFoundException {
        Class<?> clazz = null;
        try {
            clazz = Class.forName(discriminator);
        } catch (ClassNotFoundException e) {
        }
        return clazz;
    }

    private Class<?> searchPackages(String discriminator) throws ClassNotFoundException {
        Class<?> clazz = null;
        for (String packageName : this.packages) {
            clazz = getClassForName(packageName + "." + discriminator);
            if (clazz != null) {
                return clazz;
            }
        }
        return clazz;
    }
}
