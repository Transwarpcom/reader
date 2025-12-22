package org.springframework.boot.autoconfigure.influx;

import java.util.function.Supplier;
import okhttp3.OkHttpClient;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-autoconfigure-2.1.6.RELEASE.jar:org/springframework/boot/autoconfigure/influx/InfluxDbOkHttpClientBuilderProvider.class */
public interface InfluxDbOkHttpClientBuilderProvider extends Supplier<OkHttpClient.Builder> {
}
