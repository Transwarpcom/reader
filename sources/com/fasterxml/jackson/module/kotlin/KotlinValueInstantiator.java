package com.fasterxml.jackson.module.kotlin;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.deser.impl.NullsAsEmptyProvider;
import com.fasterxml.jackson.databind.deser.impl.PropertyValueBuffer;
import com.fasterxml.jackson.databind.deser.std.StdValueInstantiator;
import com.fasterxml.jackson.databind.introspect.AnnotatedWithParams;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KParameter;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeProjection;
import kotlin.reflect.jvm.ReflectJvmMapping;
import me.ag2s.epublib.epub.NCXDocumentV2;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.xml.BeanDefinitionParserDelegate;

/* compiled from: KotlinValueInstantiator.kt */
@Metadata(mv = {1, 5, 1}, k = 1, xi = 48, d1 = {"��>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\b��\u0018��2\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\u0006¢\u0006\u0002\u0010\nJ/\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000e2\u000e\u0010\u000f\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00110\u00102\u0006\u0010\u0012\u001a\u00020\u0013H\u0016¢\u0006\u0002\u0010\u0014J\f\u0010\u0015\u001a\u00020\u0006*\u00020\u0011H\u0002J\f\u0010\u0016\u001a\u00020\u0006*\u00020\u0017H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\b\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\t\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n��¨\u0006\u0018"}, d2 = {"Lcom/fasterxml/jackson/module/kotlin/KotlinValueInstantiator;", "Lcom/fasterxml/jackson/databind/deser/std/StdValueInstantiator;", NCXDocumentV2.NCXAttributes.src, "cache", "Lcom/fasterxml/jackson/module/kotlin/ReflectionCache;", "nullToEmptyCollection", "", "nullToEmptyMap", "nullIsSameAsDefault", "strictNullChecks", "(Lcom/fasterxml/jackson/databind/deser/std/StdValueInstantiator;Lcom/fasterxml/jackson/module/kotlin/ReflectionCache;ZZZZ)V", "createFromObjectWith", "", "ctxt", "Lcom/fasterxml/jackson/databind/DeserializationContext;", BeanDefinitionParserDelegate.PROPS_ELEMENT, "", "Lcom/fasterxml/jackson/databind/deser/SettableBeanProperty;", "buffer", "Lcom/fasterxml/jackson/databind/deser/impl/PropertyValueBuffer;", "(Lcom/fasterxml/jackson/databind/DeserializationContext;[Lcom/fasterxml/jackson/databind/deser/SettableBeanProperty;Lcom/fasterxml/jackson/databind/deser/impl/PropertyValueBuffer;)Ljava/lang/Object;", "hasInjectableValueId", "isPrimitive", "Lkotlin/reflect/KParameter;", "jackson-module-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/jackson-module-kotlin-2.13.5.jar:com/fasterxml/jackson/module/kotlin/KotlinValueInstantiator.class */
public final class KotlinValueInstantiator extends StdValueInstantiator {

    @NotNull
    private final ReflectionCache cache;
    private final boolean nullToEmptyCollection;
    private final boolean nullToEmptyMap;
    private final boolean nullIsSameAsDefault;
    private final boolean strictNullChecks;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KotlinValueInstantiator(@NotNull StdValueInstantiator src, @NotNull ReflectionCache cache, boolean nullToEmptyCollection, boolean nullToEmptyMap, boolean nullIsSameAsDefault, boolean strictNullChecks) {
        super(src);
        Intrinsics.checkNotNullParameter(src, "src");
        Intrinsics.checkNotNullParameter(cache, "cache");
        this.cache = cache;
        this.nullToEmptyCollection = nullToEmptyCollection;
        this.nullToEmptyMap = nullToEmptyMap;
        this.nullIsSameAsDefault = nullIsSameAsDefault;
        this.strictNullChecks = strictNullChecks;
    }

    @Override // com.fasterxml.jackson.databind.deser.ValueInstantiator
    @Nullable
    public Object createFromObjectWith(@NotNull DeserializationContext ctxt, @NotNull SettableBeanProperty[] props, @NotNull PropertyValueBuffer buffer) throws IllegalAccessException, SecurityException, JsonMappingException {
        int numCallableParameters;
        KParameter[] callableParameters;
        Object[] jsonParamValueList;
        Object absentValue;
        KType type;
        boolean z;
        KType type2;
        boolean z2;
        KType type3;
        boolean z3;
        JsonDeserializer<Object> valueDeserializer;
        Intrinsics.checkNotNullParameter(ctxt, "ctxt");
        Intrinsics.checkNotNullParameter(props, "props");
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        ReflectionCache reflectionCache = this.cache;
        AnnotatedWithParams _withArgsCreator = this._withArgsCreator;
        Intrinsics.checkNotNullExpressionValue(_withArgsCreator, "_withArgsCreator");
        ValueCreator valueCreator = reflectionCache.valueCreatorFromJava(_withArgsCreator);
        if (valueCreator == null) {
            return super.createFromObjectWith(ctxt, props, buffer);
        }
        if (valueCreator instanceof MethodValueCreator) {
            int propCount = props.length + 1;
            numCallableParameters = 1;
            KParameter[] $this$createFromObjectWith_u24lambda_u2d0 = new KParameter[propCount];
            $this$createFromObjectWith_u24lambda_u2d0[0] = ((MethodValueCreator) valueCreator).getInstanceParameter();
            callableParameters = $this$createFromObjectWith_u24lambda_u2d0;
            Object[] $this$createFromObjectWith_u24lambda_u2d1 = new Object[propCount];
            $this$createFromObjectWith_u24lambda_u2d1[0] = ((MethodValueCreator) valueCreator).getCompanionObjectInstance();
            jsonParamValueList = $this$createFromObjectWith_u24lambda_u2d1;
        } else {
            int propCount2 = props.length;
            numCallableParameters = 0;
            callableParameters = new KParameter[propCount2];
            jsonParamValueList = new Object[propCount2];
        }
        Iterable $this$forEachIndexed$iv = valueCreator.getValueParameters();
        int index$iv = 0;
        for (Object item$iv : $this$forEachIndexed$iv) {
            int idx = index$iv;
            index$iv++;
            if (idx < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            KParameter paramDef = (KParameter) item$iv;
            SettableBeanProperty jsonProp = props[idx];
            boolean isMissing = !buffer.hasParameter(jsonProp);
            if (!isMissing || !paramDef.isOptional()) {
                if (!isMissing || isPrimitive(paramDef) || hasInjectableValueId(jsonProp)) {
                    Object tempParamVal = buffer.getParameter(jsonProp);
                    if (!this.nullIsSameAsDefault || tempParamVal != null || !paramDef.isOptional()) {
                        absentValue = tempParamVal;
                    }
                } else {
                    absentValue = (paramDef.getType().isMarkedNullable() || (valueDeserializer = jsonProp.getValueDeserializer()) == null) ? null : valueDeserializer.getAbsentValue(ctxt);
                }
                Object paramVal = absentValue;
                if (paramVal == null && ((this.nullToEmptyCollection && jsonProp.getType().isCollectionLikeType()) || (this.nullToEmptyMap && jsonProp.getType().isMapLikeType()))) {
                    paramVal = new NullsAsEmptyProvider(jsonProp.getValueDeserializer()).getNullValue(ctxt);
                }
                boolean isGenericTypeVar = ReflectJvmMapping.getJavaType(paramDef.getType()) instanceof TypeVariable;
                boolean isMissingAndRequired = paramVal == null && isMissing && jsonProp.isRequired();
                if (isMissingAndRequired || (!isGenericTypeVar && paramVal == null && !paramDef.getType().isMarkedNullable())) {
                    MissingKotlinParameterException missingKotlinParameterException = new MissingKotlinParameterException(paramDef, ctxt.getParser(), "Instantiation of " + ((Object) getValueTypeDesc()) + " value failed for JSON property " + ((Object) jsonProp.getName()) + " due to missing (therefore NULL) value for creator parameter " + ((Object) paramDef.getName()) + " which is a non-nullable type");
                    Class<?> valueClass = getValueClass();
                    String name = jsonProp.getName();
                    Intrinsics.checkNotNullExpressionValue(name, "jsonProp.name");
                    JsonMappingException jsonMappingExceptionWrapWithPath = ExtensionsKt.wrapWithPath(missingKotlinParameterException, valueClass, name);
                    Intrinsics.checkNotNullExpressionValue(jsonMappingExceptionWrapWithPath, "MissingKotlinParameterEx…alueClass, jsonProp.name)");
                    throw jsonMappingExceptionWrapWithPath;
                }
                if (this.strictNullChecks && paramVal != null) {
                    String paramType = null;
                    KType itemType = null;
                    if (jsonProp.getType().isCollectionLikeType()) {
                        KTypeProjection kTypeProjection = (KTypeProjection) CollectionsKt.getOrNull(paramDef.getType().getArguments(), 0);
                        boolean z4 = (kTypeProjection == null || (type3 = kTypeProjection.getType()) == null || type3.isMarkedNullable()) ? false : true;
                        if (z4) {
                            Iterable $this$any$iv = (Collection) paramVal;
                            if (!($this$any$iv instanceof Collection) || !((Collection) $this$any$iv).isEmpty()) {
                                Iterator it = $this$any$iv.iterator();
                                while (true) {
                                    if (it.hasNext()) {
                                        Object element$iv = it.next();
                                        if (element$iv == null) {
                                            z3 = true;
                                            break;
                                        }
                                    } else {
                                        z3 = false;
                                        break;
                                    }
                                }
                            } else {
                                z3 = false;
                            }
                            if (z3) {
                                paramType = "collection";
                                itemType = paramDef.getType().getArguments().get(0).getType();
                            }
                        }
                    }
                    if (jsonProp.getType().isMapLikeType()) {
                        KTypeProjection kTypeProjection2 = (KTypeProjection) CollectionsKt.getOrNull(paramDef.getType().getArguments(), 1);
                        boolean z5 = (kTypeProjection2 == null || (type2 = kTypeProjection2.getType()) == null || type2.isMarkedNullable()) ? false : true;
                        if (z5) {
                            Map $this$any$iv2 = (Map) paramVal;
                            if (!$this$any$iv2.isEmpty()) {
                                Iterator it2 = $this$any$iv2.entrySet().iterator();
                                while (true) {
                                    if (it2.hasNext()) {
                                        Map.Entry element$iv2 = (Map.Entry) it2.next();
                                        if (element$iv2.getValue() == null) {
                                            z2 = true;
                                            break;
                                        }
                                    } else {
                                        z2 = false;
                                        break;
                                    }
                                }
                            } else {
                                z2 = false;
                            }
                            if (z2) {
                                paramType = BeanDefinitionParserDelegate.MAP_ELEMENT;
                                itemType = paramDef.getType().getArguments().get(1).getType();
                            }
                        }
                    }
                    if (jsonProp.getType().isArrayType()) {
                        KTypeProjection kTypeProjection3 = (KTypeProjection) CollectionsKt.getOrNull(paramDef.getType().getArguments(), 0);
                        boolean z6 = (kTypeProjection3 == null || (type = kTypeProjection3.getType()) == null || type.isMarkedNullable()) ? false : true;
                        if (z6) {
                            Object[] $this$any$iv3 = (Object[]) paramVal;
                            int length = $this$any$iv3.length;
                            int i = 0;
                            while (true) {
                                if (i < length) {
                                    Object element$iv3 = $this$any$iv3[i];
                                    if (element$iv3 == null) {
                                        z = true;
                                        break;
                                    }
                                    i++;
                                } else {
                                    z = false;
                                    break;
                                }
                            }
                            if (z) {
                                paramType = BeanDefinitionParserDelegate.ARRAY_ELEMENT;
                                itemType = paramDef.getType().getArguments().get(0).getType();
                            }
                        }
                    }
                    if (paramType != null && itemType != null) {
                        MissingKotlinParameterException missingKotlinParameterException2 = new MissingKotlinParameterException(paramDef, ctxt.getParser(), "Instantiation of " + itemType + ' ' + ((Object) paramType) + " failed for JSON property " + ((Object) jsonProp.getName()) + " due to null value in a " + ((Object) paramType) + " that does not allow null values");
                        Class<?> valueClass2 = getValueClass();
                        String name2 = jsonProp.getName();
                        Intrinsics.checkNotNullExpressionValue(name2, "jsonProp.name");
                        JsonMappingException jsonMappingExceptionWrapWithPath2 = ExtensionsKt.wrapWithPath(missingKotlinParameterException2, valueClass2, name2);
                        Intrinsics.checkNotNullExpressionValue(jsonMappingExceptionWrapWithPath2, "MissingKotlinParameterEx…alueClass, jsonProp.name)");
                        throw jsonMappingExceptionWrapWithPath2;
                    }
                }
                jsonParamValueList[numCallableParameters] = paramVal;
                callableParameters[numCallableParameters] = paramDef;
                numCallableParameters++;
            }
        }
        if (numCallableParameters == jsonParamValueList.length && (valueCreator instanceof ConstructorValueCreator)) {
            return super.createFromObjectWith(ctxt, jsonParamValueList);
        }
        valueCreator.checkAccessibility(ctxt);
        LinkedHashMap callableParametersByName = new LinkedHashMap();
        KParameter[] kParameterArr = callableParameters;
        Collection destination$iv$iv = new ArrayList(kParameterArr.length);
        int index$iv$iv = 0;
        for (KParameter kParameter : kParameterArr) {
            int idx2 = index$iv$iv;
            index$iv$iv++;
            if (kParameter != null) {
                callableParametersByName.put(kParameter, jsonParamValueList[idx2]);
            }
            destination$iv$iv.add(Unit.INSTANCE);
        }
        return valueCreator.callBy(callableParametersByName);
    }

    private final boolean isPrimitive(KParameter $this$isPrimitive) {
        Type javaType = ReflectJvmMapping.getJavaType($this$isPrimitive.getType());
        if (javaType instanceof Class) {
            return ((Class) javaType).isPrimitive();
        }
        return false;
    }

    private final boolean hasInjectableValueId(SettableBeanProperty $this$hasInjectableValueId) {
        return $this$hasInjectableValueId.getInjectableValueId() != null;
    }
}
