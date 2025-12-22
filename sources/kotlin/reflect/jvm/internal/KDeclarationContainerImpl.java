package kotlin.reflect.jvm.internal;

import ch.qos.logback.core.joran.action.ActionConst;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.ClassBasedDeclarationContainer;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.ReflectProperties;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.components.ReflectJavaClassFinderKt;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.components.RuntimeModuleData;
import kotlin.reflect.jvm.internal.impl.descriptors.runtime.structure.ReflectClassUtilKt;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.ResolutionScope;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.cache.interceptor.CacheOperationExpressionEvaluator;
import org.springframework.cglib.core.Constants;

/* compiled from: KDeclarationContainerImpl.kt */
@Metadata(mv = {1, 5, 1}, k = 1, d1 = {"��\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n��\n\u0002\u0010!\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010\u000b\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\b\n\b \u0018�� <2\u00020\u0001:\u0003<=>B\u0005¢\u0006\u0002\u0010\u0002J*\u0010\f\u001a\u00020\r2\u0010\u0010\u000e\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t0\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0014\u0010\u0014\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00152\u0006\u0010\u0010\u001a\u00020\u0011J\u0014\u0010\u0016\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00152\u0006\u0010\u0010\u001a\u00020\u0011J \u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u001a\u001a\u00020\u0013J\u0016\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u001d\u001a\u00020\u0011J\u0018\u0010\u001e\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u0011J\u0016\u0010\u001f\u001a\u00020 2\u0006\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u001d\u001a\u00020\u0011J\u0016\u0010!\u001a\b\u0012\u0004\u0012\u00020\u001c0\u00042\u0006\u0010\u0019\u001a\u00020\"H&J\u0012\u0010#\u001a\u0004\u0018\u00010 2\u0006\u0010$\u001a\u00020%H&J\"\u0010&\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030'0\u00042\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+H\u0004J\u0016\u0010,\u001a\b\u0012\u0004\u0012\u00020 0\u00042\u0006\u0010\u0019\u001a\u00020\"H&J\u001a\u0010-\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t0.2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u0014\u0010/\u001a\u0006\u0012\u0002\b\u00030\t2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J$\u00100\u001a\u0006\u0012\u0002\b\u00030\t2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u00101\u001a\u00020%2\u0006\u00102\u001a\u00020%H\u0002JE\u00103\u001a\u0004\u0018\u00010\u0018*\u0006\u0012\u0002\b\u00030\t2\u0006\u0010\u0019\u001a\u00020\u00112\u0010\u00104\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t052\n\u00106\u001a\u0006\u0012\u0002\b\u00030\t2\u0006\u00107\u001a\u00020\u0013H\u0002¢\u0006\u0002\u00108J(\u00109\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0015*\u0006\u0012\u0002\b\u00030\t2\u0010\u00104\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t0.H\u0002J=\u0010:\u001a\u0004\u0018\u00010\u0018*\u0006\u0012\u0002\b\u00030\t2\u0006\u0010\u0019\u001a\u00020\u00112\u0010\u00104\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t052\n\u00106\u001a\u0006\u0012\u0002\b\u00030\tH\u0002¢\u0006\u0002\u0010;R\u0018\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0018\u0010\b\u001a\u0006\u0012\u0002\b\u00030\t8TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006?"}, d2 = {"Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;", "Lkotlin/jvm/internal/ClassBasedDeclarationContainer;", "()V", "constructorDescriptors", "", "Lkotlin/reflect/jvm/internal/impl/descriptors/ConstructorDescriptor;", "getConstructorDescriptors", "()Ljava/util/Collection;", "methodOwner", "Ljava/lang/Class;", "getMethodOwner", "()Ljava/lang/Class;", "addParametersAndMasks", "", CacheOperationExpressionEvaluator.RESULT_VARIABLE, "", "desc", "", "isConstructor", "", "findConstructorBySignature", "Ljava/lang/reflect/Constructor;", "findDefaultConstructor", "findDefaultMethod", "Ljava/lang/reflect/Method;", "name", "isMember", "findFunctionDescriptor", "Lkotlin/reflect/jvm/internal/impl/descriptors/FunctionDescriptor;", "signature", "findMethodBySignature", "findPropertyDescriptor", "Lkotlin/reflect/jvm/internal/impl/descriptors/PropertyDescriptor;", "getFunctions", "Lkotlin/reflect/jvm/internal/impl/name/Name;", "getLocalProperty", "index", "", "getMembers", "Lkotlin/reflect/jvm/internal/KCallableImpl;", "scope", "Lkotlin/reflect/jvm/internal/impl/resolve/scopes/MemberScope;", "belonginess", "Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl$MemberBelonginess;", "getProperties", "loadParameterTypes", "", "loadReturnType", "parseType", "begin", "end", "lookupMethod", "parameterTypes", "", "returnType", "isStaticDefault", "(Ljava/lang/Class;Ljava/lang/String;[Ljava/lang/Class;Ljava/lang/Class;Z)Ljava/lang/reflect/Method;", "tryGetConstructor", "tryGetMethod", "(Ljava/lang/Class;Ljava/lang/String;[Ljava/lang/Class;Ljava/lang/Class;)Ljava/lang/reflect/Method;", "Companion", "Data", "MemberBelonginess", "kotlin-reflection"})
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/KDeclarationContainerImpl.class */
public abstract class KDeclarationContainerImpl implements ClassBasedDeclarationContainer {

    @NotNull
    public static final Companion Companion = new Companion(null);
    private static final Class<?> DEFAULT_CONSTRUCTOR_MARKER = Class.forName("kotlin.jvm.internal.DefaultConstructorMarker");

    @NotNull
    private static final Regex LOCAL_PROPERTY_SIGNATURE = new Regex("<v#(\\d+)>");

    @NotNull
    public abstract Collection<ConstructorDescriptor> getConstructorDescriptors();

    @NotNull
    public abstract Collection<PropertyDescriptor> getProperties(@NotNull Name name);

    @NotNull
    public abstract Collection<FunctionDescriptor> getFunctions(@NotNull Name name);

    @Nullable
    public abstract PropertyDescriptor getLocalProperty(int i);

    /* compiled from: KDeclarationContainerImpl.kt */
    @Metadata(mv = {1, 5, 1}, k = 1, d1 = {"��\u0014\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b¦\u0004\u0018��2\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl$Data;", "", "(Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl;)V", "moduleData", "Lkotlin/reflect/jvm/internal/impl/descriptors/runtime/components/RuntimeModuleData;", "getModuleData", "()Lorg/jetbrains/kotlin/descriptors/runtime/components/RuntimeModuleData;", "moduleData$delegate", "Lkotlin/reflect/jvm/internal/ReflectProperties$LazySoftVal;", "kotlin-reflection"})
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/KDeclarationContainerImpl$Data.class */
    public abstract class Data {
        static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Data.class), "moduleData", "getModuleData()Lorg/jetbrains/kotlin/descriptors/runtime/components/RuntimeModuleData;"))};

        @NotNull
        private final ReflectProperties.LazySoftVal moduleData$delegate = ReflectProperties.lazySoft(new Function0<RuntimeModuleData>() { // from class: kotlin.reflect.jvm.internal.KDeclarationContainerImpl$Data$moduleData$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final RuntimeModuleData invoke() {
                return ModuleByClassLoaderKt.getOrCreateModule(KDeclarationContainerImpl.this.getJClass());
            }
        });

        /* JADX WARN: Multi-variable type inference failed */
        @NotNull
        public final RuntimeModuleData getModuleData() {
            return (RuntimeModuleData) this.moduleData$delegate.getValue(this, $$delegatedProperties[0]);
        }

        public Data() {
        }
    }

    @NotNull
    protected Class<?> getMethodOwner() {
        Class<?> wrapperByPrimitive = ReflectClassUtilKt.getWrapperByPrimitive(getJClass());
        return wrapperByPrimitive != null ? wrapperByPrimitive : getJClass();
    }

    @NotNull
    protected final Collection<KCallableImpl<?>> getMembers(@NotNull MemberScope scope, @NotNull MemberBelonginess belonginess) {
        Intrinsics.checkNotNullParameter(scope, "scope");
        Intrinsics.checkNotNullParameter(belonginess, "belonginess");
        CreateKCallableVisitor createKCallableVisitor = new CreateKCallableVisitor(this) { // from class: kotlin.reflect.jvm.internal.KDeclarationContainerImpl$getMembers$visitor$1
            @Override // kotlin.reflect.jvm.internal.impl.descriptors.impl.DeclarationDescriptorVisitorEmptyBodies, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor
            @NotNull
            public KCallableImpl<?> visitConstructorDescriptor(@NotNull ConstructorDescriptor descriptor, @NotNull Unit data) {
                Intrinsics.checkNotNullParameter(descriptor, "descriptor");
                Intrinsics.checkNotNullParameter(data, "data");
                throw new IllegalStateException("No constructors should appear here: " + descriptor);
            }
        };
        Iterable $this$mapNotNull$iv = ResolutionScope.DefaultImpls.getContributedDescriptors$default(scope, null, null, 3, null);
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
            DeclarationDescriptor descriptor = (DeclarationDescriptor) element$iv$iv$iv;
            KCallableImpl kCallableImpl = ((descriptor instanceof CallableMemberDescriptor) && (Intrinsics.areEqual(((CallableMemberDescriptor) descriptor).getVisibility(), DescriptorVisibilities.INVISIBLE_FAKE) ^ true) && belonginess.accept((CallableMemberDescriptor) descriptor)) ? (KCallableImpl) descriptor.accept(createKCallableVisitor, Unit.INSTANCE) : null;
            if (kCallableImpl != null) {
                destination$iv$iv.add(kCallableImpl);
            }
        }
        return CollectionsKt.toList((List) destination$iv$iv);
    }

    /* compiled from: KDeclarationContainerImpl.kt */
    @Metadata(mv = {1, 5, 1}, k = 1, d1 = {"��\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0002\n\u0002\u0010\u000b\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0084\u0001\u0018��2\b\u0012\u0004\u0012\u00020��0\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl$MemberBelonginess;", "", "(Ljava/lang/String;I)V", "accept", "", "member", "Lkotlin/reflect/jvm/internal/impl/descriptors/CallableMemberDescriptor;", "DECLARED", ActionConst.INHERITED, "kotlin-reflection"})
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/KDeclarationContainerImpl$MemberBelonginess.class */
    protected enum MemberBelonginess {
        DECLARED,
        INHERITED;

        public final boolean accept(@NotNull CallableMemberDescriptor member) {
            Intrinsics.checkNotNullParameter(member, "member");
            CallableMemberDescriptor.Kind kind = member.getKind();
            Intrinsics.checkNotNullExpressionValue(kind, "member.kind");
            return kind.isReal() == (this == DECLARED);
        }
    }

    @NotNull
    public final PropertyDescriptor findPropertyDescriptor(@NotNull String name, @NotNull String signature) {
        Object obj;
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(signature, "signature");
        MatchResult match = LOCAL_PROPERTY_SIGNATURE.matchEntire(signature);
        if (match != null) {
            String number = match.getDestructured().getMatch().getGroupValues().get(1);
            PropertyDescriptor localProperty = getLocalProperty(Integer.parseInt(number));
            if (localProperty != null) {
                return localProperty;
            }
            throw new KotlinReflectionInternalError("Local property #" + number + " not found in " + getJClass());
        }
        Name nameIdentifier = Name.identifier(name);
        Intrinsics.checkNotNullExpressionValue(nameIdentifier, "Name.identifier(name)");
        Iterable $this$filter$iv = getProperties(nameIdentifier);
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            PropertyDescriptor descriptor = (PropertyDescriptor) element$iv$iv;
            if (Intrinsics.areEqual(RuntimeTypeMapper.INSTANCE.mapPropertySignature(descriptor).asString(), signature)) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        List properties = (List) destination$iv$iv;
        if (properties.isEmpty()) {
            throw new KotlinReflectionInternalError("Property '" + name + "' (JVM signature: " + signature + ") not resolved in " + this);
        }
        if (properties.size() != 1) {
            List $this$groupBy$iv = properties;
            Map destination$iv$iv2 = new LinkedHashMap();
            for (Object element$iv$iv2 : $this$groupBy$iv) {
                PropertyDescriptor it = (PropertyDescriptor) element$iv$iv2;
                DescriptorVisibility visibility = it.getVisibility();
                Object value$iv$iv$iv = destination$iv$iv2.get(visibility);
                if (value$iv$iv$iv == null) {
                    ArrayList arrayList = new ArrayList();
                    destination$iv$iv2.put(visibility, arrayList);
                    obj = arrayList;
                } else {
                    obj = value$iv$iv$iv;
                }
                List list$iv$iv = (List) obj;
                list$iv$iv.add(element$iv$iv2);
            }
            Collection collectionValues = MapsKt.toSortedMap(destination$iv$iv2, new Comparator<DescriptorVisibility>() { // from class: kotlin.reflect.jvm.internal.KDeclarationContainerImpl$findPropertyDescriptor$mostVisibleProperties$2
                @Override // java.util.Comparator
                public final int compare(DescriptorVisibility first, DescriptorVisibility second) {
                    Integer numCompare = DescriptorVisibilities.compare(first, second);
                    if (numCompare != null) {
                        return numCompare.intValue();
                    }
                    return 0;
                }
            }).values();
            Intrinsics.checkNotNullExpressionValue(collectionValues, "properties\n             …                }).values");
            List mostVisibleProperties = (List) CollectionsKt.last(collectionValues);
            if (mostVisibleProperties.size() == 1) {
                Intrinsics.checkNotNullExpressionValue(mostVisibleProperties, "mostVisibleProperties");
                return (PropertyDescriptor) CollectionsKt.first(mostVisibleProperties);
            }
            Name nameIdentifier2 = Name.identifier(name);
            Intrinsics.checkNotNullExpressionValue(nameIdentifier2, "Name.identifier(name)");
            String allMembers = CollectionsKt.joinToString$default(getProperties(nameIdentifier2), "\n", null, null, 0, null, new Function1<PropertyDescriptor, CharSequence>() { // from class: kotlin.reflect.jvm.internal.KDeclarationContainerImpl$findPropertyDescriptor$allMembers$1
                @Override // kotlin.jvm.functions.Function1
                @NotNull
                public final CharSequence invoke(@NotNull PropertyDescriptor descriptor2) {
                    Intrinsics.checkNotNullParameter(descriptor2, "descriptor");
                    return DescriptorRenderer.DEBUG_TEXT.render(descriptor2) + " | " + RuntimeTypeMapper.INSTANCE.mapPropertySignature(descriptor2).asString();
                }
            }, 30, null);
            throw new KotlinReflectionInternalError("Property '" + name + "' (JVM signature: " + signature + ") not resolved in " + this + ':' + (allMembers.length() == 0 ? " no members found" : '\n' + allMembers));
        }
        return (PropertyDescriptor) CollectionsKt.single(properties);
    }

    @NotNull
    public final FunctionDescriptor findFunctionDescriptor(@NotNull String name, @NotNull String signature) {
        Iterable functions;
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(signature, "signature");
        if (Intrinsics.areEqual(name, Constants.CONSTRUCTOR_NAME)) {
            functions = CollectionsKt.toList(getConstructorDescriptors());
        } else {
            Name nameIdentifier = Name.identifier(name);
            Intrinsics.checkNotNullExpressionValue(nameIdentifier, "Name.identifier(name)");
            functions = getFunctions(nameIdentifier);
        }
        Iterable members = functions;
        Iterable $this$filter$iv = members;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            FunctionDescriptor descriptor = (FunctionDescriptor) element$iv$iv;
            if (Intrinsics.areEqual(RuntimeTypeMapper.INSTANCE.mapSignature(descriptor).asString(), signature)) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        List functions2 = (List) destination$iv$iv;
        if (functions2.size() != 1) {
            String allMembers = CollectionsKt.joinToString$default(members, "\n", null, null, 0, null, new Function1<FunctionDescriptor, CharSequence>() { // from class: kotlin.reflect.jvm.internal.KDeclarationContainerImpl$findFunctionDescriptor$allMembers$1
                @Override // kotlin.jvm.functions.Function1
                @NotNull
                public final CharSequence invoke(@NotNull FunctionDescriptor descriptor2) {
                    Intrinsics.checkNotNullParameter(descriptor2, "descriptor");
                    return DescriptorRenderer.DEBUG_TEXT.render(descriptor2) + " | " + RuntimeTypeMapper.INSTANCE.mapSignature(descriptor2).asString();
                }
            }, 30, null);
            throw new KotlinReflectionInternalError("Function '" + name + "' (JVM signature: " + signature + ") not resolved in " + this + ':' + (allMembers.length() == 0 ? " no members found" : '\n' + allMembers));
        }
        return (FunctionDescriptor) CollectionsKt.single(functions2);
    }

    private final Method lookupMethod(Class<?> cls, String name, Class<?>[] clsArr, Class<?> cls2, boolean isStaticDefault) throws NoSuchMethodException, SecurityException {
        Class defaultImpls;
        Method it;
        if (isStaticDefault) {
            clsArr[0] = cls;
        }
        Method it2 = tryGetMethod(cls, name, clsArr, cls2);
        if (it2 != null) {
            return it2;
        }
        Class<? super Object> superclass = cls.getSuperclass();
        if (superclass != null && (it = lookupMethod(superclass, name, clsArr, cls2, isStaticDefault)) != null) {
            return it;
        }
        for (Class superInterface : cls.getInterfaces()) {
            Intrinsics.checkNotNullExpressionValue(superInterface, "superInterface");
            Method it3 = lookupMethod(superInterface, name, clsArr, cls2, isStaticDefault);
            if (it3 != null) {
                return it3;
            }
            if (isStaticDefault && (defaultImpls = ReflectJavaClassFinderKt.tryLoadClass(ReflectClassUtilKt.getSafeClassLoader(superInterface), superInterface.getName() + "$DefaultImpls")) != null) {
                clsArr[0] = superInterface;
                Method it4 = tryGetMethod(defaultImpls, name, clsArr, cls2);
                if (it4 != null) {
                    return it4;
                }
            }
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00a0 A[Catch: NoSuchMethodException -> 0x00ac, LOOP:0: B:6:0x0044->B:18:0x00a0, LOOP_END, TryCatch #0 {NoSuchMethodException -> 0x00ac, blocks: (B:2:0x0000, B:5:0x0029, B:8:0x004b, B:10:0x006b, B:12:0x0078, B:18:0x00a0), top: B:25:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x009b A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final java.lang.reflect.Method tryGetMethod(java.lang.Class<?> r6, java.lang.String r7, java.lang.Class<?>[] r8, java.lang.Class<?> r9) throws java.lang.NoSuchMethodException, java.lang.SecurityException {
        /*
            r5 = this;
            r0 = r6
            r1 = r7
            r2 = r8
            r3 = r2
            int r3 = r3.length     // Catch: java.lang.NoSuchMethodException -> Lac
            java.lang.Object[] r2 = java.util.Arrays.copyOf(r2, r3)     // Catch: java.lang.NoSuchMethodException -> Lac
            java.lang.Class[] r2 = (java.lang.Class[]) r2     // Catch: java.lang.NoSuchMethodException -> Lac
            java.lang.reflect.Method r0 = r0.getDeclaredMethod(r1, r2)     // Catch: java.lang.NoSuchMethodException -> Lac
            r10 = r0
            r0 = r10
            r1 = r0
            java.lang.String r2 = "result"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)     // Catch: java.lang.NoSuchMethodException -> Lac
            java.lang.Class r0 = r0.getReturnType()     // Catch: java.lang.NoSuchMethodException -> Lac
            r1 = r9
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)     // Catch: java.lang.NoSuchMethodException -> Lac
            if (r0 == 0) goto L29
            r0 = r10
            goto La7
        L29:
            r0 = r6
            java.lang.reflect.Method[] r0 = r0.getDeclaredMethods()     // Catch: java.lang.NoSuchMethodException -> Lac
            r1 = r0
            java.lang.String r2 = "declaredMethods"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)     // Catch: java.lang.NoSuchMethodException -> Lac
            r11 = r0
            r0 = 0
            r12 = r0
            r0 = r11
            r13 = r0
            r0 = r13
            int r0 = r0.length     // Catch: java.lang.NoSuchMethodException -> Lac
            r14 = r0
            r0 = 0
            r15 = r0
        L44:
            r0 = r15
            r1 = r14
            if (r0 >= r1) goto La6
            r0 = r13
            r1 = r15
            r0 = r0[r1]     // Catch: java.lang.NoSuchMethodException -> Lac
            r16 = r0
            r0 = r16
            r17 = r0
            r0 = 0
            r18 = r0
            r0 = r17
            r1 = r0
            java.lang.String r2 = "method"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)     // Catch: java.lang.NoSuchMethodException -> Lac
            java.lang.String r0 = r0.getName()     // Catch: java.lang.NoSuchMethodException -> Lac
            r1 = r7
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)     // Catch: java.lang.NoSuchMethodException -> Lac
            if (r0 == 0) goto L97
            r0 = r17
            java.lang.Class r0 = r0.getReturnType()     // Catch: java.lang.NoSuchMethodException -> Lac
            r1 = r9
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)     // Catch: java.lang.NoSuchMethodException -> Lac
            if (r0 == 0) goto L97
            r0 = r17
            java.lang.Class[] r0 = r0.getParameterTypes()     // Catch: java.lang.NoSuchMethodException -> Lac
            r1 = r0
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)     // Catch: java.lang.NoSuchMethodException -> Lac
            r19 = r0
            r0 = r8
            r20 = r0
            r0 = 0
            r21 = r0
            r0 = r19
            r1 = r20
            boolean r0 = java.util.Arrays.equals(r0, r1)     // Catch: java.lang.NoSuchMethodException -> Lac
            if (r0 == 0) goto L97
            r0 = 1
            goto L98
        L97:
            r0 = 0
        L98:
            if (r0 == 0) goto La0
            r0 = r16
            goto La7
        La0:
            int r15 = r15 + 1
            goto L44
        La6:
            r0 = 0
        La7:
            r10 = r0
            goto Lb1
        Lac:
            r11 = move-exception
            r0 = 0
            r10 = r0
        Lb1:
            r0 = r10
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.KDeclarationContainerImpl.tryGetMethod(java.lang.Class, java.lang.String, java.lang.Class[], java.lang.Class):java.lang.reflect.Method");
    }

    private final Constructor<?> tryGetConstructor(Class<?> cls, List<? extends Class<?>> list) throws NoSuchMethodException, SecurityException {
        Constructor<?> declaredConstructor;
        Object[] array;
        try {
            List<? extends Class<?>> $this$toTypedArray$iv = list;
            array = $this$toTypedArray$iv.toArray(new Class[0]);
        } catch (NoSuchMethodException e) {
            declaredConstructor = null;
        }
        if (array == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
        }
        Class[] clsArr = (Class[]) array;
        declaredConstructor = cls.getDeclaredConstructor((Class[]) Arrays.copyOf(clsArr, clsArr.length));
        return declaredConstructor;
    }

    @Nullable
    public final Method findMethodBySignature(@NotNull String name, @NotNull String desc) throws NoSuchMethodException, SecurityException {
        Method it;
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(desc, "desc");
        if (Intrinsics.areEqual(name, Constants.CONSTRUCTOR_NAME)) {
            return null;
        }
        Collection $this$toTypedArray$iv = loadParameterTypes(desc);
        Object[] array = $this$toTypedArray$iv.toArray(new Class[0]);
        if (array == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
        }
        Class[] parameterTypes = (Class[]) array;
        Class returnType = loadReturnType(desc);
        Method it2 = lookupMethod(getMethodOwner(), name, parameterTypes, returnType, false);
        if (it2 != null) {
            return it2;
        }
        if (getMethodOwner().isInterface() && (it = lookupMethod(Object.class, name, parameterTypes, returnType, false)) != null) {
            return it;
        }
        return null;
    }

    @Nullable
    public final Method findDefaultMethod(@NotNull String name, @NotNull String desc, boolean isMember) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(desc, "desc");
        if (Intrinsics.areEqual(name, Constants.CONSTRUCTOR_NAME)) {
            return null;
        }
        ArrayList parameterTypes = new ArrayList();
        if (isMember) {
            parameterTypes.add(getJClass());
        }
        addParametersAndMasks(parameterTypes, desc, false);
        Class<?> methodOwner = getMethodOwner();
        String str = name + "$default";
        ArrayList $this$toTypedArray$iv = parameterTypes;
        Object[] array = $this$toTypedArray$iv.toArray(new Class[0]);
        if (array == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
        }
        return lookupMethod(methodOwner, str, (Class[]) array, loadReturnType(desc), isMember);
    }

    @Nullable
    public final Constructor<?> findConstructorBySignature(@NotNull String desc) {
        Intrinsics.checkNotNullParameter(desc, "desc");
        return tryGetConstructor(getJClass(), loadParameterTypes(desc));
    }

    @Nullable
    public final Constructor<?> findDefaultConstructor(@NotNull String desc) {
        Intrinsics.checkNotNullParameter(desc, "desc");
        Class<?> jClass = getJClass();
        ArrayList parameterTypes = new ArrayList();
        addParametersAndMasks(parameterTypes, desc, true);
        Unit unit = Unit.INSTANCE;
        return tryGetConstructor(jClass, parameterTypes);
    }

    private final void addParametersAndMasks(List<Class<?>> list, String desc, boolean isConstructor) {
        List valueParameters = loadParameterTypes(desc);
        list.addAll(valueParameters);
        int size = ((valueParameters.size() + 32) - 1) / 32;
        for (int i = 0; i < size; i++) {
            Class<?> cls = Integer.TYPE;
            Intrinsics.checkNotNullExpressionValue(cls, "Integer.TYPE");
            list.add(cls);
        }
        Class cls2 = isConstructor ? DEFAULT_CONSTRUCTOR_MARKER : Object.class;
        Intrinsics.checkNotNullExpressionValue(cls2, "if (isConstructor) DEFAU…RKER else Any::class.java");
        list.add(cls2);
    }

    private final List<Class<?>> loadParameterTypes(String desc) {
        int end;
        ArrayList result = new ArrayList();
        int i = 1;
        while (true) {
            int begin = i;
            if (desc.charAt(begin) != ')') {
                int end2 = begin;
                while (desc.charAt(end2) == '[') {
                    end2++;
                }
                char cCharAt = desc.charAt(end2);
                if (StringsKt.contains$default((CharSequence) "VZCBSIFJD", cCharAt, false, 2, (Object) null)) {
                    end = end2 + 1;
                } else {
                    if (cCharAt != 'L') {
                        throw new KotlinReflectionInternalError("Unknown type prefix in the method signature: " + desc);
                    }
                    end = StringsKt.indexOf$default((CharSequence) desc, ';', begin, false, 4, (Object) null) + 1;
                }
                result.add(parseType(desc, begin, end));
                i = end;
            } else {
                return result;
            }
        }
    }

    private final Class<?> parseType(String desc, int begin, int end) throws ClassNotFoundException {
        switch (desc.charAt(begin)) {
            case 'B':
                return Byte.TYPE;
            case 'C':
                return Character.TYPE;
            case 'D':
                return Double.TYPE;
            case 'F':
                return Float.TYPE;
            case 'I':
                return Integer.TYPE;
            case 'J':
                return Long.TYPE;
            case 'L':
                ClassLoader safeClassLoader = ReflectClassUtilKt.getSafeClassLoader(getJClass());
                int i = begin + 1;
                int i2 = end - 1;
                if (desc == null) {
                    throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                }
                String strSubstring = desc.substring(i, i2);
                Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                Class<?> clsLoadClass = safeClassLoader.loadClass(StringsKt.replace$default(strSubstring, '/', '.', false, 4, (Object) null));
                Intrinsics.checkNotNullExpressionValue(clsLoadClass, "jClass.safeClassLoader.l…d - 1).replace('/', '.'))");
                return clsLoadClass;
            case 'S':
                return Short.TYPE;
            case 'V':
                Class<?> cls = Void.TYPE;
                Intrinsics.checkNotNullExpressionValue(cls, "Void.TYPE");
                return cls;
            case 'Z':
                return Boolean.TYPE;
            case '[':
                return UtilKt.createArrayType(parseType(desc, begin + 1, end));
            default:
                throw new KotlinReflectionInternalError("Unknown type prefix in the method signature: " + desc);
        }
    }

    private final Class<?> loadReturnType(String desc) {
        return parseType(desc, StringsKt.indexOf$default((CharSequence) desc, ')', 0, false, 6, (Object) null) + 1, desc.length());
    }

    /* compiled from: KDeclarationContainerImpl.kt */
    @Metadata(mv = {1, 5, 1}, k = 1, d1 = {"��\u001c\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018��2\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001e\u0010\u0003\u001a\u0012\u0012\u0002\b\u0003 \u0005*\b\u0012\u0002\b\u0003\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n��R\u0014\u0010\u0006\u001a\u00020\u0007X\u0080\u0004¢\u0006\b\n��\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lkotlin/reflect/jvm/internal/KDeclarationContainerImpl$Companion;", "", "()V", "DEFAULT_CONSTRUCTOR_MARKER", "Ljava/lang/Class;", "kotlin.jvm.PlatformType", "LOCAL_PROPERTY_SIGNATURE", "Lkotlin/text/Regex;", "getLOCAL_PROPERTY_SIGNATURE$kotlin_reflection", "()Lkotlin/text/Regex;", "kotlin-reflection"})
    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/KDeclarationContainerImpl$Companion.class */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        @NotNull
        public final Regex getLOCAL_PROPERTY_SIGNATURE$kotlin_reflection() {
            return KDeclarationContainerImpl.LOCAL_PROPERTY_SIGNATURE;
        }
    }
}
