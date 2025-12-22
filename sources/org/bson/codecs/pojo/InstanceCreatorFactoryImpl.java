package org.bson.codecs.pojo;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/pojo/InstanceCreatorFactoryImpl.class */
final class InstanceCreatorFactoryImpl<T> implements InstanceCreatorFactory<T> {
    private final CreatorExecutable<T> creatorExecutable;

    InstanceCreatorFactoryImpl(CreatorExecutable<T> creatorExecutable) {
        this.creatorExecutable = creatorExecutable;
    }

    @Override // org.bson.codecs.pojo.InstanceCreatorFactory
    public InstanceCreator<T> create() {
        return new InstanceCreatorImpl(this.creatorExecutable);
    }
}
