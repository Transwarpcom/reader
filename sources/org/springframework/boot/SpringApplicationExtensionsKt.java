package org.springframework.boot;

import io.legado.app.constant.Action;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.apache.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import org.springframework.context.ConfigurableApplicationContext;

/* compiled from: SpringApplicationExtensions.kt */
@Metadata(mv = {1, 1, 11}, bv = {1, 0, 2}, k = 2, d1 = {"��.\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n��\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a.\u0010��\u001a\u00020\u0001\"\n\b��\u0010\u0002\u0018\u0001*\u00020\u00032\u0012\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u0005\"\u00020\u0006H\u0086\b¢\u0006\u0002\u0010\u0007\u001aG\u0010��\u001a\u00020\u0001\"\n\b��\u0010\u0002\u0018\u0001*\u00020\u00032\u0012\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u0005\"\u00020\u00062\u0017\u0010\b\u001a\u0013\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\t¢\u0006\u0002\b\fH\u0086\b¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"runApplication", "Lorg/springframework/context/ConfigurableApplicationContext;", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE, "", "args", "", "", "([Ljava/lang/String;)Lorg/springframework/context/ConfigurableApplicationContext;", Action.init, "Lkotlin/Function1;", "Lorg/springframework/boot/SpringApplication;", "", "Lkotlin/ExtensionFunctionType;", "([Ljava/lang/String;Lkotlin/jvm/functions/Function1;)Lorg/springframework/context/ConfigurableApplicationContext;", "spring-boot"})
/* loaded from: reader.jar:BOOT-INF/lib/spring-boot-2.1.6.RELEASE.jar:org/springframework/boot/SpringApplicationExtensionsKt.class */
public final class SpringApplicationExtensionsKt {
    private static final <T> ConfigurableApplicationContext runApplication(String... args) {
        Intrinsics.reifiedOperationMarker(4, PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE);
        ConfigurableApplicationContext configurableApplicationContextRun = SpringApplication.run((Class<?>) Object.class, (String[]) Arrays.copyOf(args, args.length));
        Intrinsics.checkExpressionValueIsNotNull(configurableApplicationContextRun, "SpringApplication.run(T::class.java, *args)");
        return configurableApplicationContextRun;
    }

    private static final <T> ConfigurableApplicationContext runApplication(String[] args, Function1<? super SpringApplication, Unit> init) throws IllegalStateException {
        Intrinsics.reifiedOperationMarker(4, PDNumberFormatDictionary.FRACTIONAL_DISPLAY_TRUNCATE);
        SpringApplication springApplication = new SpringApplication(Object.class);
        init.invoke(springApplication);
        ConfigurableApplicationContext configurableApplicationContextRun = springApplication.run((String[]) Arrays.copyOf(args, args.length));
        Intrinsics.checkExpressionValueIsNotNull(configurableApplicationContextRun, "SpringApplication(T::cla…a).apply(init).run(*args)");
        return configurableApplicationContextRun;
    }
}
