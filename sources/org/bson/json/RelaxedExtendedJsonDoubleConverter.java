package org.bson.json;

/* loaded from: reader.jar:BOOT-INF/lib/bson-3.8.2.jar:org/bson/json/RelaxedExtendedJsonDoubleConverter.class */
class RelaxedExtendedJsonDoubleConverter implements Converter<Double> {
    private static final Converter<Double> FALLBACK_CONVERTER = new ExtendedJsonDoubleConverter();

    RelaxedExtendedJsonDoubleConverter() {
    }

    @Override // org.bson.json.Converter
    public void convert(Double value, StrictJsonWriter writer) {
        if (value.isNaN() || value.isInfinite()) {
            FALLBACK_CONVERTER.convert(value, writer);
        } else {
            writer.writeNumber(Double.toString(value.doubleValue()));
        }
    }
}
