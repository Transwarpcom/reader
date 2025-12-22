package org.springframework.objenesis;

import org.springframework.objenesis.strategy.StdInstantiatorStrategy;

/* loaded from: reader.jar:BOOT-INF/lib/spring-core-5.1.8.RELEASE.jar:org/springframework/objenesis/ObjenesisStd.class */
public class ObjenesisStd extends ObjenesisBase {
    public ObjenesisStd() {
        super(new StdInstantiatorStrategy());
    }

    public ObjenesisStd(boolean useCache) {
        super(new StdInstantiatorStrategy(), useCache);
    }
}
