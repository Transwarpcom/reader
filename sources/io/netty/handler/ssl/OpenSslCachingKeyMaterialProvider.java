package io.netty.handler.ssl;

import io.netty.buffer.ByteBufAllocator;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import javax.net.ssl.X509KeyManager;

/* loaded from: reader.jar:BOOT-INF/lib/netty-handler-4.1.42.Final.jar:io/netty/handler/ssl/OpenSslCachingKeyMaterialProvider.class */
final class OpenSslCachingKeyMaterialProvider extends OpenSslKeyMaterialProvider {
    private final ConcurrentMap<String, OpenSslKeyMaterial> cache;

    OpenSslCachingKeyMaterialProvider(X509KeyManager keyManager, String password) {
        super(keyManager, password);
        this.cache = new ConcurrentHashMap();
    }

    @Override // io.netty.handler.ssl.OpenSslKeyMaterialProvider
    OpenSslKeyMaterial chooseKeyMaterial(ByteBufAllocator allocator, String alias) throws Exception {
        OpenSslKeyMaterial material = this.cache.get(alias);
        if (material == null) {
            material = super.chooseKeyMaterial(allocator, alias);
            if (material == null) {
                return null;
            }
            OpenSslKeyMaterial old = this.cache.putIfAbsent(alias, material);
            if (old != null) {
                material.release();
                material = old;
            }
        }
        return material.retain();
    }

    @Override // io.netty.handler.ssl.OpenSslKeyMaterialProvider
    void destroy() {
        do {
            Iterator<OpenSslKeyMaterial> iterator = this.cache.values().iterator();
            while (iterator.hasNext()) {
                iterator.next().release();
                iterator.remove();
            }
        } while (!this.cache.isEmpty());
    }
}
