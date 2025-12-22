package org.apache.commons.lang3;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/* loaded from: reader.jar:BOOT-INF/lib/commons-lang3-3.8.1.jar:org/apache/commons/lang3/SerializationUtils.class */
public class SerializationUtils {
    public static <T extends Serializable> T clone(T object) throws IOException {
        if (object == null) {
            return null;
        }
        byte[] objectData = serialize(object);
        ByteArrayInputStream bais = new ByteArrayInputStream(objectData);
        try {
            ClassLoaderAwareObjectInputStream in = new ClassLoaderAwareObjectInputStream(bais, object.getClass().getClassLoader());
            Throwable th = null;
            try {
                T t = (T) in.readObject();
                if (in != null) {
                    if (0 != 0) {
                        try {
                            in.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                    } else {
                        in.close();
                    }
                }
                return t;
            } finally {
            }
        } catch (IOException ex) {
            throw new SerializationException("IOException while reading or closing cloned object data", ex);
        } catch (ClassNotFoundException ex2) {
            throw new SerializationException("ClassNotFoundException while reading cloned object data", ex2);
        }
    }

    public static <T extends Serializable> T roundtrip(T msg) {
        return (T) deserialize(serialize(msg));
    }

    public static void serialize(Serializable obj, OutputStream outputStream) throws IOException {
        Validate.isTrue(outputStream != null, "The OutputStream must not be null", new Object[0]);
        try {
            ObjectOutputStream out = new ObjectOutputStream(outputStream);
            Throwable th = null;
            try {
                try {
                    out.writeObject(obj);
                    if (out != null) {
                        if (0 != 0) {
                            try {
                                out.close();
                            } catch (Throwable th2) {
                                th.addSuppressed(th2);
                            }
                        } else {
                            out.close();
                        }
                    }
                } finally {
                }
            } finally {
            }
        } catch (IOException ex) {
            throw new SerializationException(ex);
        }
    }

    public static byte[] serialize(Serializable obj) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(512);
        serialize(obj, baos);
        return baos.toByteArray();
    }

    public static <T> T deserialize(InputStream inputStream) throws IOException {
        Validate.isTrue(inputStream != null, "The InputStream must not be null", new Object[0]);
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            Throwable th = null;
            try {
                T t = (T) objectInputStream.readObject();
                if (objectInputStream != null) {
                    if (0 != 0) {
                        try {
                            objectInputStream.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                    } else {
                        objectInputStream.close();
                    }
                }
                return t;
            } finally {
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new SerializationException(e);
        }
    }

    public static <T> T deserialize(byte[] bArr) {
        Validate.isTrue(bArr != null, "The byte[] must not be null", new Object[0]);
        return (T) deserialize(new ByteArrayInputStream(bArr));
    }

    /* loaded from: reader.jar:BOOT-INF/lib/commons-lang3-3.8.1.jar:org/apache/commons/lang3/SerializationUtils$ClassLoaderAwareObjectInputStream.class */
    static class ClassLoaderAwareObjectInputStream extends ObjectInputStream {
        private static final Map<String, Class<?>> primitiveTypes = new HashMap();
        private final ClassLoader classLoader;

        static {
            primitiveTypes.put("byte", Byte.TYPE);
            primitiveTypes.put("short", Short.TYPE);
            primitiveTypes.put("int", Integer.TYPE);
            primitiveTypes.put("long", Long.TYPE);
            primitiveTypes.put("float", Float.TYPE);
            primitiveTypes.put("double", Double.TYPE);
            primitiveTypes.put("boolean", Boolean.TYPE);
            primitiveTypes.put("char", Character.TYPE);
            primitiveTypes.put("void", Void.TYPE);
        }

        ClassLoaderAwareObjectInputStream(InputStream in, ClassLoader classLoader) throws IOException {
            super(in);
            this.classLoader = classLoader;
        }

        @Override // java.io.ObjectInputStream
        protected Class<?> resolveClass(ObjectStreamClass desc) throws ClassNotFoundException, IOException {
            String name = desc.getName();
            try {
                return Class.forName(name, false, this.classLoader);
            } catch (ClassNotFoundException e) {
                try {
                    return Class.forName(name, false, Thread.currentThread().getContextClassLoader());
                } catch (ClassNotFoundException cnfe) {
                    Class<?> cls = primitiveTypes.get(name);
                    if (cls != null) {
                        return cls;
                    }
                    throw cnfe;
                }
            }
        }
    }
}
