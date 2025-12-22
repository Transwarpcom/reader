package io.vertx.ext.auth;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.shareddata.impl.ClusterSerializable;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

@Deprecated
/* loaded from: reader.jar:BOOT-INF/lib/vertx-auth-common-3.8.5.jar:io/vertx/ext/auth/AbstractUser.class */
public abstract class AbstractUser implements User, ClusterSerializable {
    private final Set<String> cachedPermissions = new HashSet();

    protected abstract void doIsPermitted(String str, Handler<AsyncResult<Boolean>> handler);

    @Override // io.vertx.ext.auth.User
    public User isAuthorized(String authority, Handler<AsyncResult<Boolean>> resultHandler) {
        if (this.cachedPermissions.contains(authority)) {
            resultHandler.handle(Future.succeededFuture(true));
        } else {
            doIsPermitted(authority, res -> {
                if (res.succeeded() && ((Boolean) res.result()).booleanValue()) {
                    this.cachedPermissions.add(authority);
                }
                resultHandler.handle(res);
            });
        }
        return this;
    }

    @Override // io.vertx.ext.auth.User
    public User clearCache() {
        this.cachedPermissions.clear();
        return this;
    }

    @Override // io.vertx.core.shareddata.impl.ClusterSerializable
    public void writeToBuffer(Buffer buff) {
        writeStringSet(buff, this.cachedPermissions);
    }

    @Override // io.vertx.core.shareddata.impl.ClusterSerializable
    public int readFromBuffer(int pos, Buffer buffer) {
        return readStringSet(buffer, this.cachedPermissions, pos);
    }

    public boolean cachePermission(String authority) {
        return this.cachedPermissions.add(authority);
    }

    private void writeStringSet(Buffer buff, Set<String> set) {
        buff.appendInt(set == null ? 0 : set.size());
        if (set != null) {
            for (String entry : set) {
                byte[] bytes = entry.getBytes(StandardCharsets.UTF_8);
                buff.appendInt(bytes.length).appendBytes(bytes);
            }
        }
    }

    private int readStringSet(Buffer buffer, Set<String> set, int pos) {
        int num = buffer.getInt(pos);
        int pos2 = pos + 4;
        for (int i = 0; i < num; i++) {
            int len = buffer.getInt(pos2);
            int pos3 = pos2 + 4;
            byte[] bytes = buffer.getBytes(pos3, pos3 + len);
            pos2 = pos3 + len;
            set.add(new String(bytes, StandardCharsets.UTF_8));
        }
        return pos2;
    }
}
