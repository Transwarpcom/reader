package org.bson.codecs.pojo;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/codecs/pojo/ConventionDefaultsImpl.class */
final class ConventionDefaultsImpl implements Convention {
    ConventionDefaultsImpl() {
    }

    @Override // org.bson.codecs.pojo.Convention
    public void apply(ClassModelBuilder<?> classModelBuilder) {
        if (classModelBuilder.getDiscriminatorKey() == null) {
            classModelBuilder.discriminatorKey("_t");
        }
        if (classModelBuilder.getDiscriminator() == null && classModelBuilder.getType() != null) {
            classModelBuilder.discriminator(classModelBuilder.getType().getName());
        }
        for (PropertyModelBuilder<?> propertyModel : classModelBuilder.getPropertyModelBuilders()) {
            if (classModelBuilder.getIdPropertyName() == null) {
                String propertyName = propertyModel.getName();
                if (propertyName.equals("_id") || propertyName.equals("id")) {
                    classModelBuilder.idPropertyName(propertyName);
                }
            }
        }
    }
}
