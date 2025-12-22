package cn.hutool.core.net;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/net/LocalPortGenerater.class */
public class LocalPortGenerater implements Serializable {
    private static final long serialVersionUID = 1;
    private final AtomicInteger alternativePort;

    public LocalPortGenerater(int beginPort) {
        this.alternativePort = new AtomicInteger(beginPort);
    }

    public int generate() {
        int iIncrementAndGet = this.alternativePort.get();
        while (true) {
            int validPort = iIncrementAndGet;
            if (false == NetUtil.isUsableLocalPort(validPort)) {
                iIncrementAndGet = this.alternativePort.incrementAndGet();
            } else {
                return validPort;
            }
        }
    }
}
