package com.mongodb.client.model.geojson.codecs;

import com.mongodb.client.model.geojson.CoordinateReferenceSystem;
import com.mongodb.client.model.geojson.Geometry;
import com.mongodb.client.model.geojson.GeometryCollection;
import com.mongodb.client.model.geojson.LineString;
import com.mongodb.client.model.geojson.MultiLineString;
import com.mongodb.client.model.geojson.MultiPoint;
import com.mongodb.client.model.geojson.MultiPolygon;
import com.mongodb.client.model.geojson.NamedCoordinateReferenceSystem;
import com.mongodb.client.model.geojson.Point;
import com.mongodb.client.model.geojson.Polygon;
import com.mongodb.client.model.geojson.PolygonCoordinates;
import com.mongodb.client.model.geojson.Position;
import com.mongodb.lang.Nullable;
import java.util.ArrayList;
import java.util.List;
import me.ag2s.epublib.epub.PackageDocumentBase;
import org.bson.BsonReader;
import org.bson.BsonReaderMark;
import org.bson.BsonType;
import org.bson.codecs.configuration.CodecConfigurationException;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/geojson/codecs/GeometryDecoderHelper.class */
final class GeometryDecoderHelper {
    static <T extends Geometry> T decodeGeometry(BsonReader bsonReader, Class<T> cls) {
        if (cls.equals(Point.class)) {
            return decodePoint(bsonReader);
        }
        if (cls.equals(MultiPoint.class)) {
            return decodeMultiPoint(bsonReader);
        }
        if (cls.equals(Polygon.class)) {
            return decodePolygon(bsonReader);
        }
        if (cls.equals(MultiPolygon.class)) {
            return decodeMultiPolygon(bsonReader);
        }
        if (cls.equals(LineString.class)) {
            return decodeLineString(bsonReader);
        }
        if (cls.equals(MultiLineString.class)) {
            return decodeMultiLineString(bsonReader);
        }
        if (cls.equals(GeometryCollection.class)) {
            return decodeGeometryCollection(bsonReader);
        }
        if (cls.equals(Geometry.class)) {
            return (T) decodeGeometry(bsonReader);
        }
        throw new CodecConfigurationException(String.format("Unsupported Geometry: %s", cls));
    }

    private static Point decodePoint(BsonReader reader) {
        String type = null;
        Position position = null;
        CoordinateReferenceSystem crs = null;
        reader.readStartDocument();
        while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
            String key = reader.readName();
            if (key.equals("type")) {
                type = reader.readString();
            } else if (key.equals("coordinates")) {
                position = decodePosition(reader);
            } else if (key.equals("crs")) {
                crs = decodeCoordinateReferenceSystem(reader);
            } else {
                throw new CodecConfigurationException(String.format("Unexpected key '%s' found when decoding a GeoJSON point", key));
            }
        }
        reader.readEndDocument();
        if (type == null) {
            throw new CodecConfigurationException("Invalid Point, document contained no type information.");
        }
        if (!type.equals("Point")) {
            throw new CodecConfigurationException(String.format("Invalid Point, found type '%s'.", type));
        }
        if (position == null) {
            throw new CodecConfigurationException("Invalid Point, missing position coordinates.");
        }
        return crs != null ? new Point(crs, position) : new Point(position);
    }

    private static MultiPoint decodeMultiPoint(BsonReader reader) {
        String type = null;
        List<Position> coordinates = null;
        CoordinateReferenceSystem crs = null;
        reader.readStartDocument();
        while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
            String key = reader.readName();
            if (key.equals("type")) {
                type = reader.readString();
            } else if (key.equals("coordinates")) {
                coordinates = decodeCoordinates(reader);
            } else if (key.equals("crs")) {
                crs = decodeCoordinateReferenceSystem(reader);
            } else {
                throw new CodecConfigurationException(String.format("Unexpected key '%s' found when decoding a GeoJSON point", key));
            }
        }
        reader.readEndDocument();
        if (type == null) {
            throw new CodecConfigurationException("Invalid MultiPoint, document contained no type information.");
        }
        if (!type.equals("MultiPoint")) {
            throw new CodecConfigurationException(String.format("Invalid MultiPoint, found type '%s'.", type));
        }
        if (coordinates == null) {
            throw new CodecConfigurationException("Invalid MultiPoint, missing position coordinates.");
        }
        return crs != null ? new MultiPoint(crs, coordinates) : new MultiPoint(coordinates);
    }

    private static Polygon decodePolygon(BsonReader reader) {
        String type = null;
        PolygonCoordinates coordinates = null;
        CoordinateReferenceSystem crs = null;
        reader.readStartDocument();
        while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
            String key = reader.readName();
            if (key.equals("type")) {
                type = reader.readString();
            } else if (key.equals("coordinates")) {
                coordinates = decodePolygonCoordinates(reader);
            } else if (key.equals("crs")) {
                crs = decodeCoordinateReferenceSystem(reader);
            } else {
                throw new CodecConfigurationException(String.format("Unexpected key '%s' found when decoding a GeoJSON Polygon", key));
            }
        }
        reader.readEndDocument();
        if (type == null) {
            throw new CodecConfigurationException("Invalid Polygon, document contained no type information.");
        }
        if (!type.equals("Polygon")) {
            throw new CodecConfigurationException(String.format("Invalid Polygon, found type '%s'.", type));
        }
        if (coordinates == null) {
            throw new CodecConfigurationException("Invalid Polygon, missing coordinates.");
        }
        return crs != null ? new Polygon(crs, coordinates) : new Polygon(coordinates);
    }

    private static MultiPolygon decodeMultiPolygon(BsonReader reader) {
        String type = null;
        List<PolygonCoordinates> coordinates = null;
        CoordinateReferenceSystem crs = null;
        reader.readStartDocument();
        while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
            String key = reader.readName();
            if (key.equals("type")) {
                type = reader.readString();
            } else if (key.equals("coordinates")) {
                coordinates = decodeMultiPolygonCoordinates(reader);
            } else if (key.equals("crs")) {
                crs = decodeCoordinateReferenceSystem(reader);
            } else {
                throw new CodecConfigurationException(String.format("Unexpected key '%s' found when decoding a GeoJSON Polygon", key));
            }
        }
        reader.readEndDocument();
        if (type == null) {
            throw new CodecConfigurationException("Invalid MultiPolygon, document contained no type information.");
        }
        if (!type.equals("MultiPolygon")) {
            throw new CodecConfigurationException(String.format("Invalid MultiPolygon, found type '%s'.", type));
        }
        if (coordinates == null) {
            throw new CodecConfigurationException("Invalid MultiPolygon, missing coordinates.");
        }
        return crs != null ? new MultiPolygon(crs, coordinates) : new MultiPolygon(coordinates);
    }

    private static LineString decodeLineString(BsonReader reader) {
        String type = null;
        List<Position> coordinates = null;
        CoordinateReferenceSystem crs = null;
        reader.readStartDocument();
        while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
            String key = reader.readName();
            if (key.equals("type")) {
                type = reader.readString();
            } else if (key.equals("coordinates")) {
                coordinates = decodeCoordinates(reader);
            } else if (key.equals("crs")) {
                crs = decodeCoordinateReferenceSystem(reader);
            } else {
                throw new CodecConfigurationException(String.format("Unexpected key '%s' found when decoding a GeoJSON Polygon", key));
            }
        }
        reader.readEndDocument();
        if (type == null) {
            throw new CodecConfigurationException("Invalid LineString, document contained no type information.");
        }
        if (!type.equals("LineString")) {
            throw new CodecConfigurationException(String.format("Invalid LineString, found type '%s'.", type));
        }
        if (coordinates == null) {
            throw new CodecConfigurationException("Invalid LineString, missing coordinates.");
        }
        return crs != null ? new LineString(crs, coordinates) : new LineString(coordinates);
    }

    private static MultiLineString decodeMultiLineString(BsonReader reader) {
        String type = null;
        List<List<Position>> coordinates = null;
        CoordinateReferenceSystem crs = null;
        reader.readStartDocument();
        while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
            String key = reader.readName();
            if (key.equals("type")) {
                type = reader.readString();
            } else if (key.equals("coordinates")) {
                coordinates = decodeMultiCoordinates(reader);
            } else if (key.equals("crs")) {
                crs = decodeCoordinateReferenceSystem(reader);
            } else {
                throw new CodecConfigurationException(String.format("Unexpected key '%s' found when decoding a GeoJSON Polygon", key));
            }
        }
        reader.readEndDocument();
        if (type == null) {
            throw new CodecConfigurationException("Invalid MultiLineString, document contained no type information.");
        }
        if (!type.equals("MultiLineString")) {
            throw new CodecConfigurationException(String.format("Invalid MultiLineString, found type '%s'.", type));
        }
        if (coordinates == null) {
            throw new CodecConfigurationException("Invalid MultiLineString, missing coordinates.");
        }
        return crs != null ? new MultiLineString(crs, coordinates) : new MultiLineString(coordinates);
    }

    private static GeometryCollection decodeGeometryCollection(BsonReader reader) {
        String type = null;
        List<? extends Geometry> geometries = null;
        CoordinateReferenceSystem crs = null;
        reader.readStartDocument();
        while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
            String key = reader.readName();
            if (key.equals("type")) {
                type = reader.readString();
            } else if (key.equals("geometries")) {
                geometries = decodeGeometries(reader);
            } else if (key.equals("crs")) {
                crs = decodeCoordinateReferenceSystem(reader);
            } else {
                throw new CodecConfigurationException(String.format("Unexpected key '%s' found when decoding a GeoJSON Polygon", key));
            }
        }
        reader.readEndDocument();
        if (type == null) {
            throw new CodecConfigurationException("Invalid GeometryCollection, document contained no type information.");
        }
        if (!type.equals("GeometryCollection")) {
            throw new CodecConfigurationException(String.format("Invalid GeometryCollection, found type '%s'.", type));
        }
        if (geometries == null) {
            throw new CodecConfigurationException("Invalid GeometryCollection, missing geometries.");
        }
        return crs != null ? new GeometryCollection(crs, geometries) : new GeometryCollection(geometries);
    }

    private static List<? extends Geometry> decodeGeometries(BsonReader reader) {
        validateIsArray(reader);
        reader.readStartArray();
        List<Geometry> values = new ArrayList<>();
        while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
            Geometry geometry = decodeGeometry(reader);
            values.add(geometry);
        }
        reader.readEndArray();
        return values;
    }

    private static Geometry decodeGeometry(BsonReader reader) {
        Geometry geometry;
        String type = null;
        BsonReaderMark mark = reader.getMark();
        validateIsDocument(reader);
        reader.readStartDocument();
        while (true) {
            if (reader.readBsonType() == BsonType.END_OF_DOCUMENT) {
                break;
            }
            String key = reader.readName();
            if (key.equals("type")) {
                type = reader.readString();
                break;
            }
            reader.skipValue();
        }
        mark.reset();
        if (type == null) {
            throw new CodecConfigurationException("Invalid Geometry item, document contained no type information.");
        }
        if (type.equals("Point")) {
            geometry = decodePoint(reader);
        } else if (type.equals("MultiPoint")) {
            geometry = decodeMultiPoint(reader);
        } else if (type.equals("Polygon")) {
            geometry = decodePolygon(reader);
        } else if (type.equals("MultiPolygon")) {
            geometry = decodeMultiPolygon(reader);
        } else if (type.equals("LineString")) {
            geometry = decodeLineString(reader);
        } else if (type.equals("MultiLineString")) {
            geometry = decodeMultiLineString(reader);
        } else if (type.equals("GeometryCollection")) {
            geometry = decodeGeometryCollection(reader);
        } else {
            throw new CodecConfigurationException(String.format("Invalid Geometry item, found type '%s'.", type));
        }
        return geometry;
    }

    private static PolygonCoordinates decodePolygonCoordinates(BsonReader reader) {
        validateIsArray(reader);
        reader.readStartArray();
        List<List<Position>> values = new ArrayList<>();
        while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
            values.add(decodeCoordinates(reader));
        }
        reader.readEndArray();
        if (values.isEmpty()) {
            throw new CodecConfigurationException("Invalid Polygon no coordinates.");
        }
        List<Position> exterior = values.remove(0);
        ArrayList[] holes = (ArrayList[]) values.toArray(new ArrayList[values.size()]);
        try {
            return new PolygonCoordinates(exterior, holes);
        } catch (IllegalArgumentException e) {
            throw new CodecConfigurationException(String.format("Invalid Polygon: %s", e.getMessage()));
        }
    }

    private static List<PolygonCoordinates> decodeMultiPolygonCoordinates(BsonReader reader) {
        validateIsArray(reader);
        reader.readStartArray();
        List<PolygonCoordinates> values = new ArrayList<>();
        while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
            values.add(decodePolygonCoordinates(reader));
        }
        reader.readEndArray();
        if (values.isEmpty()) {
            throw new CodecConfigurationException("Invalid MultiPolygon no coordinates.");
        }
        return values;
    }

    private static List<Position> decodeCoordinates(BsonReader reader) {
        validateIsArray(reader);
        reader.readStartArray();
        List<Position> values = new ArrayList<>();
        while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
            values.add(decodePosition(reader));
        }
        reader.readEndArray();
        return values;
    }

    private static List<List<Position>> decodeMultiCoordinates(BsonReader reader) {
        validateIsArray(reader);
        reader.readStartArray();
        List<List<Position>> values = new ArrayList<>();
        while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
            values.add(decodeCoordinates(reader));
        }
        reader.readEndArray();
        return values;
    }

    private static Position decodePosition(BsonReader reader) {
        validateIsArray(reader);
        reader.readStartArray();
        List<Double> values = new ArrayList<>();
        while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
            if (reader.getCurrentBsonType() != BsonType.DOUBLE) {
                throw new CodecConfigurationException("Invalid position");
            }
            values.add(Double.valueOf(reader.readDouble()));
        }
        reader.readEndArray();
        try {
            return new Position(values);
        } catch (IllegalArgumentException e) {
            throw new CodecConfigurationException(String.format("Invalid Position: %s", e.getMessage()));
        }
    }

    @Nullable
    static CoordinateReferenceSystem decodeCoordinateReferenceSystem(BsonReader reader) {
        String crsName = null;
        validateIsDocument(reader);
        reader.readStartDocument();
        while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
            String name = reader.readName();
            if (name.equals("type")) {
                String type = reader.readString();
                if (!type.equals("name")) {
                    throw new CodecConfigurationException(String.format("Unsupported CoordinateReferenceSystem '%s'.", type));
                }
            } else if (name.equals(PackageDocumentBase.OPFAttributes.properties)) {
                crsName = decodeCoordinateReferenceSystemProperties(reader);
            } else {
                throw new CodecConfigurationException(String.format("Found invalid key '%s' in the CoordinateReferenceSystem.", name));
            }
        }
        reader.readEndDocument();
        if (crsName != null) {
            return new NamedCoordinateReferenceSystem(crsName);
        }
        return null;
    }

    private static String decodeCoordinateReferenceSystemProperties(BsonReader reader) {
        String crsName = null;
        validateIsDocument(reader);
        reader.readStartDocument();
        while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
            String name = reader.readName();
            if (name.equals("name")) {
                crsName = reader.readString();
            } else {
                throw new CodecConfigurationException(String.format("Found invalid key '%s' in the CoordinateReferenceSystem.", name));
            }
        }
        reader.readEndDocument();
        if (crsName == null) {
            throw new CodecConfigurationException("Found invalid properties in the CoordinateReferenceSystem.");
        }
        return crsName;
    }

    private static void validateIsDocument(BsonReader reader) {
        BsonType currentType = reader.getCurrentBsonType();
        if (currentType == null) {
            currentType = reader.readBsonType();
        }
        if (!currentType.equals(BsonType.DOCUMENT)) {
            throw new CodecConfigurationException("Invalid BsonType expecting a Document");
        }
    }

    private static void validateIsArray(BsonReader reader) {
        if (reader.getCurrentBsonType() != BsonType.ARRAY) {
            throw new CodecConfigurationException("Invalid BsonType expecting an Array");
        }
    }

    private GeometryDecoderHelper() {
    }
}
