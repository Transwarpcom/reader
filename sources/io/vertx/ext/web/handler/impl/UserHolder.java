package io.vertx.ext.web.handler.impl;

import io.vertx.core.VertxException;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.shareddata.impl.ClusterSerializable;
import io.vertx.ext.auth.User;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.impl.Utils;
import java.nio.charset.StandardCharsets;

/* loaded from: reader.jar:BOOT-INF/lib/vertx-web-3.8.5.jar:io/vertx/ext/web/handler/impl/UserHolder.class */
public class UserHolder implements ClusterSerializable {
    public RoutingContext context;
    public User user;

    public UserHolder() {
    }

    public UserHolder(RoutingContext context) {
        this.context = context;
    }

    @Override // io.vertx.core.shareddata.impl.ClusterSerializable
    public void writeToBuffer(Buffer buffer) {
        User user = this.context != null ? this.context.user() : this.user;
        if (user instanceof ClusterSerializable) {
            buffer.appendByte((byte) 1);
            String className = user.getClass().getName();
            if (className == null) {
                throw new IllegalStateException("Cannot serialize " + user.getClass().getName());
            }
            byte[] bytes = className.getBytes(StandardCharsets.UTF_8);
            buffer.appendInt(bytes.length);
            buffer.appendBytes(bytes);
            ClusterSerializable cs = (ClusterSerializable) user;
            cs.writeToBuffer(buffer);
            return;
        }
        buffer.appendByte((byte) 0);
    }

    @Override // io.vertx.core.shareddata.impl.ClusterSerializable
    public int readFromBuffer(int pos, Buffer buffer) throws ClassNotFoundException {
        int pos2 = pos + 1;
        byte b = buffer.getByte(pos);
        if (b == 1) {
            int len = buffer.getInt(pos2);
            int pos3 = pos2 + 4;
            byte[] bytes = buffer.getBytes(pos3, pos3 + len);
            int pos4 = pos3 + len;
            String className = new String(bytes, StandardCharsets.UTF_8);
            try {
                Class<?> clazz = Utils.getClassLoader().loadClass(className);
                if (!ClusterSerializable.class.isAssignableFrom(clazz)) {
                    throw new ClassCastException(className + " is not ClusterSerializable");
                }
                ClusterSerializable obj = (ClusterSerializable) clazz.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                pos2 = obj.readFromBuffer(pos4, buffer);
                this.user = (User) obj;
            } catch (Exception e) {
                throw new VertxException(e);
            }
        } else {
            this.user = null;
        }
        return pos2;
    }
}
