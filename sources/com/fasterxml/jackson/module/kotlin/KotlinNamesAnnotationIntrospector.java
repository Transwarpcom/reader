package com.fasterxml.jackson.module.kotlin;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedConstructor;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import com.fasterxml.jackson.databind.introspect.NopAnnotationIntrospector;
import java.util.Locale;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlin.text.CharsKt;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: KotlinNamesAnnotationIntrospector.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\b��\u0018��2\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0010\u0010\u0006\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\b0\u0007¢\u0006\u0002\u0010\tJ\u0012\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0012\u0010\u0014\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J&\u0010\u0017\u001a\u0004\u0018\u00010\u00182\n\u0010\u0019\u001a\u0006\u0012\u0002\b\u00030\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0018H\u0016J\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u0012\u001a\u00020 H\u0016J\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u0012\u001a\u00020!H\u0002R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000bR\u001b\u0010\u0006\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\b0\u0007¢\u0006\b\n��\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u000e\u0010\u000f¨\u0006\""}, d2 = {"Lcom/fasterxml/jackson/module/kotlin/KotlinNamesAnnotationIntrospector;", "Lcom/fasterxml/jackson/databind/introspect/NopAnnotationIntrospector;", "module", "Lcom/fasterxml/jackson/module/kotlin/KotlinModule;", "cache", "Lcom/fasterxml/jackson/module/kotlin/ReflectionCache;", "ignoredClassesForImplyingJsonCreator", "", "Lkotlin/reflect/KClass;", "(Lcom/fasterxml/jackson/module/kotlin/KotlinModule;Lcom/fasterxml/jackson/module/kotlin/ReflectionCache;Ljava/util/Set;)V", "getCache", "()Lcom/fasterxml/jackson/module/kotlin/ReflectionCache;", "getIgnoredClassesForImplyingJsonCreator", "()Ljava/util/Set;", "getModule", "()Lcom/fasterxml/jackson/module/kotlin/KotlinModule;", "findImplicitPropertyName", "", "member", "Lcom/fasterxml/jackson/databind/introspect/AnnotatedMember;", "findKotlinParameterName", "param", "Lcom/fasterxml/jackson/databind/introspect/AnnotatedParameter;", "findRenameByField", "Lcom/fasterxml/jackson/databind/PropertyName;", "config", "Lcom/fasterxml/jackson/databind/cfg/MapperConfig;", "field", "Lcom/fasterxml/jackson/databind/introspect/AnnotatedField;", "implName", "hasCreatorAnnotation", "", "Lcom/fasterxml/jackson/databind/introspect/Annotated;", "Lcom/fasterxml/jackson/databind/introspect/AnnotatedConstructor;", "jackson-module-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/jackson-module-kotlin-2.13.5.jar:com/fasterxml/jackson/module/kotlin/KotlinNamesAnnotationIntrospector.class */
public final class KotlinNamesAnnotationIntrospector extends NopAnnotationIntrospector {

    @NotNull
    private final KotlinModule module;

    @NotNull
    private final ReflectionCache cache;

    @NotNull
    private final Set<KClass<?>> ignoredClassesForImplyingJsonCreator;

    /* JADX WARN: Multi-variable type inference failed */
    public KotlinNamesAnnotationIntrospector(@NotNull KotlinModule module, @NotNull ReflectionCache cache, @NotNull Set<? extends KClass<?>> ignoredClassesForImplyingJsonCreator) {
        Intrinsics.checkNotNullParameter(module, "module");
        Intrinsics.checkNotNullParameter(cache, "cache");
        Intrinsics.checkNotNullParameter(ignoredClassesForImplyingJsonCreator, "ignoredClassesForImplyingJsonCreator");
        this.module = module;
        this.cache = cache;
        this.ignoredClassesForImplyingJsonCreator = ignoredClassesForImplyingJsonCreator;
    }

    @NotNull
    public final KotlinModule getModule() {
        return this.module;
    }

    @NotNull
    public final ReflectionCache getCache() {
        return this.cache;
    }

    @NotNull
    public final Set<KClass<?>> getIgnoredClassesForImplyingJsonCreator() {
        return this.ignoredClassesForImplyingJsonCreator;
    }

    @Override // com.fasterxml.jackson.databind.AnnotationIntrospector
    @Nullable
    public String findImplicitPropertyName(@NotNull AnnotatedMember member) {
        String strSubstringAfter$default;
        String string;
        Intrinsics.checkNotNullParameter(member, "member");
        if (!(member instanceof AnnotatedMethod)) {
            if (member instanceof AnnotatedParameter) {
                return findKotlinParameterName((AnnotatedParameter) member);
            }
            return null;
        }
        String name = ((AnnotatedMethod) member).getName();
        Intrinsics.checkNotNullExpressionValue(name, "member.name");
        if (!StringsKt.contains$default((CharSequence) name, '-', false, 2, (Object) null) || ((AnnotatedMethod) member).getParameterCount() != 0) {
            return null;
        }
        String name2 = ((AnnotatedMethod) member).getName();
        Intrinsics.checkNotNullExpressionValue(name2, "member.name");
        if (StringsKt.startsWith$default(name2, BeanUtil.PREFIX_GETTER_GET, false, 2, (Object) null)) {
            String name3 = ((AnnotatedMethod) member).getName();
            Intrinsics.checkNotNullExpressionValue(name3, "member.name");
            strSubstringAfter$default = StringsKt.substringAfter$default(name3, BeanUtil.PREFIX_GETTER_GET, (String) null, 2, (Object) null);
        } else {
            String name4 = ((AnnotatedMethod) member).getName();
            Intrinsics.checkNotNullExpressionValue(name4, "member.name");
            if (StringsKt.startsWith$default(name4, BeanUtil.PREFIX_GETTER_IS, false, 2, (Object) null)) {
                String name5 = ((AnnotatedMethod) member).getName();
                Intrinsics.checkNotNullExpressionValue(name5, "member.name");
                strSubstringAfter$default = StringsKt.substringAfter$default(name5, BeanUtil.PREFIX_GETTER_IS, (String) null, 2, (Object) null);
            } else {
                strSubstringAfter$default = null;
            }
        }
        String str = strSubstringAfter$default;
        if (str == null) {
            return null;
        }
        if (str.length() > 0) {
            StringBuilder sb = new StringBuilder();
            char it = str.charAt(0);
            Locale locale = Locale.getDefault();
            Intrinsics.checkNotNullExpressionValue(locale, "getDefault()");
            StringBuilder sbAppend = sb.append(CharsKt.lowercase(it, locale).toString());
            String strSubstring = str.substring(1);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.String).substring(startIndex)");
            string = sbAppend.append(strSubstring).toString();
        } else {
            string = str;
        }
        String str2 = string;
        if (str2 == null) {
            return null;
        }
        return StringsKt.substringBefore$default(str2, '-', (String) null, 2, (Object) null);
    }

    @Nullable
    public PropertyName findRenameByField(@NotNull MapperConfig<?> config, @NotNull AnnotatedField field, @NotNull PropertyName implName) {
        String mangledName;
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(field, "field");
        Intrinsics.checkNotNullParameter(implName, "implName");
        String origSimple = implName.getSimpleName();
        Class<?> declaringClass = field.getDeclaringClass();
        Intrinsics.checkNotNullExpressionValue(declaringClass, "field.declaringClass");
        if (KotlinModuleKt.isKotlinClass(declaringClass)) {
            Intrinsics.checkNotNullExpressionValue(origSimple, "origSimple");
            if (StringsKt.startsWith$default(origSimple, BeanUtil.PREFIX_GETTER_IS, false, 2, (Object) null) && (mangledName = com.fasterxml.jackson.databind.util.BeanUtil.stdManglePropertyName(origSimple, 2)) != null && !mangledName.equals(origSimple)) {
                return PropertyName.construct(mangledName);
            }
            return null;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0378  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean hasCreatorAnnotation(com.fasterxml.jackson.databind.introspect.AnnotatedConstructor r6) {
        /*
            Method dump skipped, instructions count: 920
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.module.kotlin.KotlinNamesAnnotationIntrospector.hasCreatorAnnotation(com.fasterxml.jackson.databind.introspect.AnnotatedConstructor):boolean");
    }

    @Override // com.fasterxml.jackson.databind.AnnotationIntrospector
    public boolean hasCreatorAnnotation(@NotNull Annotated member) {
        Intrinsics.checkNotNullParameter(member, "member");
        if ((member instanceof AnnotatedConstructor) && KotlinNamesAnnotationIntrospectorKt.isKotlinConstructorWithParameters((AnnotatedConstructor) member)) {
            return this.cache.checkConstructorIsCreatorAnnotated((AnnotatedConstructor) member, new Function1<AnnotatedConstructor, Boolean>() { // from class: com.fasterxml.jackson.module.kotlin.KotlinNamesAnnotationIntrospector.hasCreatorAnnotation.2
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                @NotNull
                public final Boolean invoke(@NotNull AnnotatedConstructor it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return Boolean.valueOf(KotlinNamesAnnotationIntrospector.this.hasCreatorAnnotation(it));
                }
            });
        }
        return false;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockSplitter
        jadx.core.utils.exceptions.JadxRuntimeException: Unexpected missing predecessor for block: B:36:0x00cf
        	at jadx.core.dex.visitors.blocks.BlockSplitter.addTempConnectionsForExcHandlers(BlockSplitter.java:280)
        	at jadx.core.dex.visitors.blocks.BlockSplitter.visit(BlockSplitter.java:79)
        */
    private final java.lang.String findKotlinParameterName(com.fasterxml.jackson.databind.introspect.AnnotatedParameter r4) {
        /*
            Method dump skipped, instructions count: 441
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.module.kotlin.KotlinNamesAnnotationIntrospector.findKotlinParameterName(com.fasterxml.jackson.databind.introspect.AnnotatedParameter):java.lang.String");
    }
}
