package cn.hutool.core.lang;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.EnumerationIter;
import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.ClassLoaderUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/lang/ClassScanner.class */
public class ClassScanner implements Serializable {
    private static final long serialVersionUID = 1;
    private final String packageName;
    private final String packageNameWithDot;
    private final String packageDirName;
    private final String packagePath;
    private final Filter<Class<?>> classFilter;
    private final Charset charset;
    private ClassLoader classLoader;
    private boolean initialize;
    private final Set<Class<?>> classes;

    public static Set<Class<?>> scanAllPackageByAnnotation(String packageName, Class<? extends Annotation> annotationClass) {
        return scanAllPackage(packageName, clazz -> {
            return clazz.isAnnotationPresent(annotationClass);
        });
    }

    public static Set<Class<?>> scanPackageByAnnotation(String packageName, Class<? extends Annotation> annotationClass) {
        return scanPackage(packageName, clazz -> {
            return clazz.isAnnotationPresent(annotationClass);
        });
    }

    public static Set<Class<?>> scanAllPackageBySuper(String packageName, Class<?> superClass) {
        return scanAllPackage(packageName, clazz -> {
            return superClass.isAssignableFrom(clazz) && !superClass.equals(clazz);
        });
    }

    public static Set<Class<?>> scanPackageBySuper(String packageName, Class<?> superClass) {
        return scanPackage(packageName, clazz -> {
            return superClass.isAssignableFrom(clazz) && !superClass.equals(clazz);
        });
    }

    public static Set<Class<?>> scanAllPackage() {
        return scanAllPackage("", null);
    }

    public static Set<Class<?>> scanPackage() {
        return scanPackage("", null);
    }

    public static Set<Class<?>> scanPackage(String packageName) {
        return scanPackage(packageName, null);
    }

    public static Set<Class<?>> scanAllPackage(String packageName, Filter<Class<?>> classFilter) {
        return new ClassScanner(packageName, classFilter).scan(true);
    }

    public static Set<Class<?>> scanPackage(String packageName, Filter<Class<?>> classFilter) {
        return new ClassScanner(packageName, classFilter).scan();
    }

    public ClassScanner() {
        this(null);
    }

    public ClassScanner(String packageName) {
        this(packageName, null);
    }

    public ClassScanner(String packageName, Filter<Class<?>> classFilter) {
        this(packageName, classFilter, CharsetUtil.CHARSET_UTF_8);
    }

    public ClassScanner(String packageName, Filter<Class<?>> classFilter, Charset charset) {
        this.classes = new HashSet();
        String packageName2 = StrUtil.nullToEmpty(packageName);
        this.packageName = packageName2;
        this.packageNameWithDot = StrUtil.addSuffixIfNot(packageName2, ".");
        this.packageDirName = packageName2.replace('.', File.separatorChar);
        this.packagePath = packageName2.replace('.', '/');
        this.classFilter = classFilter;
        this.charset = charset;
    }

    public Set<Class<?>> scan() {
        return scan(false);
    }

    public Set<Class<?>> scan(boolean forceScanJavaClassPaths) {
        URL url;
        Iterator<URL> it = ResourceUtil.getResourceIter(this.packagePath).iterator();
        while (it.hasNext()) {
            url = it.next();
            switch (url.getProtocol()) {
                case "file":
                    scanFile(new File(URLUtil.decode(url.getFile(), this.charset.name())), null);
                    break;
                case "jar":
                    scanJar(URLUtil.getJarFile(url));
                    break;
            }
        }
        if (forceScanJavaClassPaths || CollUtil.isEmpty((Collection<?>) this.classes)) {
            scanJavaClassPaths();
        }
        return Collections.unmodifiableSet(this.classes);
    }

    public void setInitialize(boolean initialize) {
        this.initialize = initialize;
    }

    public void setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    private void scanJavaClassPaths() {
        String[] javaClassPaths = ClassUtil.getJavaClassPaths();
        for (String classPath : javaClassPaths) {
            scanFile(new File(URLUtil.decode(classPath, CharsetUtil.systemCharsetName())), null);
        }
    }

    private void scanFile(File file, String rootDir) {
        File[] files;
        if (file.isFile()) {
            String fileName = file.getAbsolutePath();
            if (fileName.endsWith(".class")) {
                String className = fileName.substring(rootDir.length(), fileName.length() - 6).replace(File.separatorChar, '.');
                addIfAccept(className);
                return;
            } else {
                if (fileName.endsWith(".jar")) {
                    try {
                        scanJar(new JarFile(file));
                        return;
                    } catch (IOException e) {
                        throw new IORuntimeException(e);
                    }
                }
                return;
            }
        }
        if (file.isDirectory() && null != (files = file.listFiles())) {
            for (File subFile : files) {
                scanFile(subFile, null == rootDir ? subPathBeforePackage(file) : rootDir);
            }
        }
    }

    private void scanJar(JarFile jar) {
        Iterator<E> it = new EnumerationIter(jar.entries()).iterator();
        while (it.hasNext()) {
            JarEntry entry = (JarEntry) it.next();
            String name = StrUtil.removePrefix(entry.getName(), "/");
            if (StrUtil.isEmpty(this.packagePath) || name.startsWith(this.packagePath)) {
                if (name.endsWith(".class") && false == entry.isDirectory()) {
                    String className = name.substring(0, name.length() - 6).replace('/', '.');
                    addIfAccept(loadClass(className));
                }
            }
        }
    }

    private Class<?> loadClass(String className) throws ClassNotFoundException {
        ClassLoader loader = this.classLoader;
        if (null == loader) {
            loader = ClassLoaderUtil.getClassLoader();
            this.classLoader = loader;
        }
        Class<?> clazz = null;
        try {
            clazz = Class.forName(className, this.initialize, loader);
        } catch (ClassNotFoundException | NoClassDefFoundError e) {
        } catch (Exception e2) {
            throw new RuntimeException(e2);
        } catch (UnsupportedClassVersionError e3) {
        }
        return clazz;
    }

    private void addIfAccept(String className) {
        if (StrUtil.isBlank(className)) {
            return;
        }
        int classLen = className.length();
        int packageLen = this.packageName.length();
        if (classLen == packageLen) {
            if (className.equals(this.packageName)) {
                addIfAccept(loadClass(className));
            }
        } else if (classLen > packageLen) {
            if (".".equals(this.packageNameWithDot) || className.startsWith(this.packageNameWithDot)) {
                addIfAccept(loadClass(className));
            }
        }
    }

    private void addIfAccept(Class<?> clazz) {
        if (null != clazz) {
            Filter<Class<?>> classFilter = this.classFilter;
            if (classFilter == null || classFilter.accept(clazz)) {
                this.classes.add(clazz);
            }
        }
    }

    private String subPathBeforePackage(File file) {
        String filePath = file.getAbsolutePath();
        if (StrUtil.isNotEmpty(this.packageDirName)) {
            filePath = StrUtil.subBefore((CharSequence) filePath, (CharSequence) this.packageDirName, true);
        }
        return StrUtil.addSuffixIfNot(filePath, File.separator);
    }
}
