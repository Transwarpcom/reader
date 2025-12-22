package org.springframework.boot.loader.jar;

/* loaded from: reader.jar:org/springframework/boot/loader/jar/JarEntryFilter.class */
interface JarEntryFilter {
    AsciiBytes apply(AsciiBytes name);
}
