package kotlinx.coroutines.internal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.pdfbox.pdmodel.common.PDPageLabelRange;
import org.jetbrains.annotations.NotNull;

/* compiled from: FastServiceLoader.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��N\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018��2\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J!\u0010\u0005\u001a\u0004\u0018\u00010\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\b2\u0006\u0010\t\u001a\u00020\u0004H\u0082\bJ1\u0010\n\u001a\u0002H\u000b\"\u0004\b��\u0010\u000b2\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u000b0\bH\u0002¢\u0006\u0002\u0010\u0010J*\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u0012\"\u0004\b��\u0010\u000b2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u000b0\b2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0013\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00060\u0012H��¢\u0006\u0002\b\u0014J/\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u0012\"\u0004\b��\u0010\u000b2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u000b0\b2\u0006\u0010\r\u001a\u00020\u000eH��¢\u0006\u0002\b\u0016J\u0016\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00040\u00122\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u0016\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00040\u00122\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J,\u0010\u001d\u001a\u0002H\u001e\"\u0004\b��\u0010\u001e*\u00020\u001f2\u0012\u0010 \u001a\u000e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u0002H\u001e0!H\u0082\b¢\u0006\u0002\u0010\"R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n��¨\u0006#"}, d2 = {"Lkotlinx/coroutines/internal/FastServiceLoader;", "", "()V", "PREFIX", "", "createInstanceOf", "Lkotlinx/coroutines/internal/MainDispatcherFactory;", "baseClass", "Ljava/lang/Class;", "serviceClass", "getProviderInstance", "S", "name", "loader", "Ljava/lang/ClassLoader;", "service", "(Ljava/lang/String;Ljava/lang/ClassLoader;Ljava/lang/Class;)Ljava/lang/Object;", "load", "", "loadMainDispatcherFactory", "loadMainDispatcherFactory$kotlinx_coroutines_core", "loadProviders", "loadProviders$kotlinx_coroutines_core", "parse", "url", "Ljava/net/URL;", "parseFile", PDPageLabelRange.STYLE_ROMAN_LOWER, "Ljava/io/BufferedReader;", "use", "R", "Ljava/util/jar/JarFile;", "block", "Lkotlin/Function1;", "(Ljava/util/jar/JarFile;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "kotlinx-coroutines-core"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlinx-coroutines-core-jvm-1.5.2.jar:kotlinx/coroutines/internal/FastServiceLoader.class */
public final class FastServiceLoader {

    @NotNull
    public static final FastServiceLoader INSTANCE = new FastServiceLoader();

    @NotNull
    private static final String PREFIX = "META-INF/services/";

    private FastServiceLoader() {
    }

    @NotNull
    public final List<MainDispatcherFactory> loadMainDispatcherFactory$kotlinx_coroutines_core() {
        List<MainDispatcherFactory> listLoad;
        MainDispatcherFactory mainDispatcherFactory;
        MainDispatcherFactory mainDispatcherFactory2;
        if (!FastServiceLoaderKt.getANDROID_DETECTED()) {
            return load(MainDispatcherFactory.class, MainDispatcherFactory.class.getClassLoader());
        }
        try {
            ArrayList result = new ArrayList(2);
            try {
                Class clz$iv = Class.forName("kotlinx.coroutines.android.AndroidDispatcherFactory", true, MainDispatcherFactory.class.getClassLoader());
                mainDispatcherFactory = (MainDispatcherFactory) MainDispatcherFactory.class.cast(clz$iv.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]));
            } catch (ClassNotFoundException e) {
                mainDispatcherFactory = (MainDispatcherFactory) null;
            }
            MainDispatcherFactory $this$loadMainDispatcherFactory_u24lambda_u2d0 = mainDispatcherFactory;
            if ($this$loadMainDispatcherFactory_u24lambda_u2d0 != null) {
                result.add($this$loadMainDispatcherFactory_u24lambda_u2d0);
            }
            try {
                Class clz$iv2 = Class.forName("kotlinx.coroutines.test.internal.TestMainDispatcherFactory", true, MainDispatcherFactory.class.getClassLoader());
                mainDispatcherFactory2 = (MainDispatcherFactory) MainDispatcherFactory.class.cast(clz$iv2.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]));
            } catch (ClassNotFoundException e2) {
                mainDispatcherFactory2 = (MainDispatcherFactory) null;
            }
            MainDispatcherFactory $this$loadMainDispatcherFactory_u24lambda_u2d1 = mainDispatcherFactory2;
            if ($this$loadMainDispatcherFactory_u24lambda_u2d1 != null) {
                result.add($this$loadMainDispatcherFactory_u24lambda_u2d1);
            }
            listLoad = result;
        } catch (Throwable th) {
            listLoad = load(MainDispatcherFactory.class, MainDispatcherFactory.class.getClassLoader());
        }
        return listLoad;
    }

    private final MainDispatcherFactory createInstanceOf(Class<MainDispatcherFactory> cls, String serviceClass) throws ClassNotFoundException {
        MainDispatcherFactory mainDispatcherFactoryCast;
        try {
            Class clz = Class.forName(serviceClass, true, cls.getClassLoader());
            mainDispatcherFactoryCast = cls.cast(clz.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]));
        } catch (ClassNotFoundException e) {
            mainDispatcherFactoryCast = (MainDispatcherFactory) null;
        }
        return mainDispatcherFactoryCast;
    }

    private final <S> List<S> load(Class<S> cls, ClassLoader loader) {
        List<S> list;
        try {
            list = loadProviders$kotlinx_coroutines_core(cls, loader);
        } catch (Throwable th) {
            list = CollectionsKt.toList(ServiceLoader.load(cls, loader));
        }
        return list;
    }

    @NotNull
    public final <S> List<S> loadProviders$kotlinx_coroutines_core(@NotNull Class<S> cls, @NotNull ClassLoader loader) throws IOException {
        String fullServiceName = Intrinsics.stringPlus(PREFIX, cls.getName());
        Enumeration urls = loader.getResources(fullServiceName);
        Iterable list = Collections.list(urls);
        Intrinsics.checkNotNullExpressionValue(list, "java.util.Collections.list(this)");
        Iterable $this$flatMap$iv = (List) list;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$flatMap$iv) {
            URL it = (URL) element$iv$iv;
            Iterable list$iv$iv = INSTANCE.parse(it);
            CollectionsKt.addAll(destination$iv$iv, list$iv$iv);
        }
        Iterable providers = CollectionsKt.toSet((List) destination$iv$iv);
        if (!(!((Collection) providers).isEmpty())) {
            throw new IllegalArgumentException("No providers were loaded with FastServiceLoader".toString());
        }
        Iterable $this$map$iv = providers;
        Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            String it2 = (String) item$iv$iv;
            destination$iv$iv2.add(INSTANCE.getProviderInstance(it2, loader, cls));
        }
        return (List) destination$iv$iv2;
    }

    private final <S> S getProviderInstance(String name, ClassLoader loader, Class<S> cls) throws ClassNotFoundException {
        Class clazz = Class.forName(name, false, loader);
        if (!cls.isAssignableFrom(clazz)) {
            throw new IllegalArgumentException(("Expected service of class " + cls + ", but found " + clazz).toString());
        }
        return cls.cast(clazz.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]));
    }

    private final List<String> parse(URL url) {
        String path = url.toString();
        if (StringsKt.startsWith$default(path, "jar", false, 2, (Object) null)) {
            String pathToJar = StringsKt.substringBefore$default(StringsKt.substringAfter$default(path, "jar:file:", (String) null, 2, (Object) null), '!', (String) null, 2, (Object) null);
            String entry = StringsKt.substringAfter$default(path, "!/", (String) null, 2, (Object) null);
            JarFile $this$use$iv = new JarFile(pathToJar, false);
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader($this$use$iv.getInputStream(new ZipEntry(entry)), "UTF-8"));
                Throwable th = (Throwable) null;
                try {
                    try {
                        BufferedReader r = bufferedReader;
                        List<String> file = INSTANCE.parseFile(r);
                        CloseableKt.closeFinally(bufferedReader, th);
                        $this$use$iv.close();
                        return file;
                    } catch (Throwable th2) {
                        CloseableKt.closeFinally(bufferedReader, th);
                        throw th2;
                    }
                } finally {
                }
            } catch (Throwable th3) {
                try {
                    $this$use$iv.close();
                    throw th3;
                } catch (Throwable closeException$iv) {
                    if (0 == 0) {
                        throw closeException$iv;
                    }
                    ExceptionsKt.addSuppressed(null, closeException$iv);
                    throw null;
                }
            }
        }
        BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(url.openStream()));
        Throwable th4 = (Throwable) null;
        try {
            try {
                BufferedReader reader = bufferedReader2;
                List<String> file2 = INSTANCE.parseFile(reader);
                CloseableKt.closeFinally(bufferedReader2, th4);
                return file2;
            } catch (Throwable th5) {
                CloseableKt.closeFinally(bufferedReader2, th4);
                throw th5;
            }
        } finally {
        }
    }

    private final <R> R use(JarFile $this$use, Function1<? super JarFile, ? extends R> function1) {
        try {
            R rInvoke = function1.invoke($this$use);
            InlineMarker.finallyStart(1);
            $this$use.close();
            InlineMarker.finallyEnd(1);
            return rInvoke;
        } catch (Throwable th) {
            InlineMarker.finallyStart(1);
            try {
                $this$use.close();
                InlineMarker.finallyEnd(1);
                throw th;
            } catch (Throwable closeException) {
                if (0 == 0) {
                    throw closeException;
                }
                ExceptionsKt.addSuppressed(null, closeException);
                throw null;
            }
        }
    }

    private final List<String> parseFile(BufferedReader r) throws IOException {
        boolean z;
        Set names = new LinkedHashSet();
        while (true) {
            String line = r.readLine();
            if (line == null) {
                return CollectionsKt.toList(names);
            }
            String strSubstringBefore$default = StringsKt.substringBefore$default(line, "#", (String) null, 2, (Object) null);
            if (strSubstringBefore$default == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.CharSequence");
            }
            String serviceName = StringsKt.trim((CharSequence) strSubstringBefore$default).toString();
            String $this$all$iv = serviceName;
            int i = 0;
            while (true) {
                if (i >= $this$all$iv.length()) {
                    z = true;
                    break;
                }
                char element$iv = $this$all$iv.charAt(i);
                if (!(element$iv == '.' || Character.isJavaIdentifierPart(element$iv))) {
                    z = false;
                    break;
                }
                i++;
            }
            if (!z) {
                throw new IllegalArgumentException(Intrinsics.stringPlus("Illegal service provider class name: ", serviceName).toString());
            }
            if (serviceName.length() > 0) {
                names.add(serviceName);
            }
        }
    }
}
