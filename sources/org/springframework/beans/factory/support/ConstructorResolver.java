package org.springframework.beans.factory.support;

import java.beans.ConstructorProperties;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.security.AccessController;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.springframework.beans.BeanMetadataElement;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.TypeConverter;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.beans.factory.UnsatisfiedDependencyException;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.config.DependencyDescriptor;
import org.springframework.beans.factory.xml.BeanDefinitionParserDelegate;
import org.springframework.core.CollectionFactory;
import org.springframework.core.GenericTypeResolver;
import org.springframework.core.MethodParameter;
import org.springframework.core.NamedThreadLocal;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.MethodInvoker;
import org.springframework.util.ObjectUtils;
import org.springframework.util.ReflectionUtils;

/* loaded from: reader.jar:BOOT-INF/lib/spring-beans-5.1.8.RELEASE.jar:org/springframework/beans/factory/support/ConstructorResolver.class */
class ConstructorResolver {
    private static final Object[] EMPTY_ARGS = new Object[0];
    private static final NamedThreadLocal<InjectionPoint> currentInjectionPoint = new NamedThreadLocal<>("Current injection point");
    private final AbstractAutowireCapableBeanFactory beanFactory;
    private final Log logger;

    public ConstructorResolver(AbstractAutowireCapableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
        this.logger = beanFactory.getLogger();
    }

    /* JADX WARN: Removed duplicated region for block: B:109:0x029b  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x02a5  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x02b5  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x02ce  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public org.springframework.beans.BeanWrapper autowireConstructor(java.lang.String r13, org.springframework.beans.factory.support.RootBeanDefinition r14, @org.springframework.lang.Nullable java.lang.reflect.Constructor<?>[] r15, @org.springframework.lang.Nullable java.lang.Object[] r16) {
        /*
            Method dump skipped, instructions count: 954
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.springframework.beans.factory.support.ConstructorResolver.autowireConstructor(java.lang.String, org.springframework.beans.factory.support.RootBeanDefinition, java.lang.reflect.Constructor[], java.lang.Object[]):org.springframework.beans.BeanWrapper");
    }

    private Object instantiate(String beanName, RootBeanDefinition mbd, Constructor<?> constructorToUse, Object[] argsToUse) {
        try {
            InstantiationStrategy strategy = this.beanFactory.getInstantiationStrategy();
            if (System.getSecurityManager() != null) {
                return AccessController.doPrivileged(() -> {
                    return strategy.instantiate(mbd, beanName, this.beanFactory, constructorToUse, argsToUse);
                }, this.beanFactory.getAccessControlContext());
            }
            return strategy.instantiate(mbd, beanName, this.beanFactory, constructorToUse, argsToUse);
        } catch (Throwable ex) {
            throw new BeanCreationException(mbd.getResourceDescription(), beanName, "Bean instantiation via constructor failed", ex);
        }
    }

    public void resolveFactoryMethodIfPossible(RootBeanDefinition mbd) {
        Class<?> factoryClass;
        boolean isStatic;
        if (mbd.getFactoryBeanName() != null) {
            factoryClass = this.beanFactory.getType(mbd.getFactoryBeanName());
            isStatic = false;
        } else {
            factoryClass = mbd.getBeanClass();
            isStatic = true;
        }
        Assert.state(factoryClass != null, "Unresolvable factory class");
        Method[] candidates = getCandidateMethods(ClassUtils.getUserClass(factoryClass), mbd);
        Method uniqueCandidate = null;
        int length = candidates.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            Method candidate = candidates[i];
            if (Modifier.isStatic(candidate.getModifiers()) == isStatic && mbd.isFactoryMethod(candidate)) {
                if (uniqueCandidate == null) {
                    uniqueCandidate = candidate;
                } else if (!Arrays.equals(uniqueCandidate.getParameterTypes(), candidate.getParameterTypes())) {
                    uniqueCandidate = null;
                    break;
                }
            }
            i++;
        }
        mbd.factoryMethodToIntrospect = uniqueCandidate;
    }

    private Method[] getCandidateMethods(Class<?> factoryClass, RootBeanDefinition mbd) {
        if (System.getSecurityManager() != null) {
            return (Method[]) AccessController.doPrivileged(() -> {
                return mbd.isNonPublicAccessAllowed() ? ReflectionUtils.getAllDeclaredMethods(factoryClass) : factoryClass.getMethods();
            });
        }
        return mbd.isNonPublicAccessAllowed() ? ReflectionUtils.getAllDeclaredMethods(factoryClass) : factoryClass.getMethods();
    }

    /* JADX WARN: Removed duplicated region for block: B:116:0x0308  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0312  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0322  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x033b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public org.springframework.beans.BeanWrapper instantiateUsingFactoryMethod(java.lang.String r13, org.springframework.beans.factory.support.RootBeanDefinition r14, @org.springframework.lang.Nullable java.lang.Object[] r15) {
        /*
            Method dump skipped, instructions count: 1488
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.springframework.beans.factory.support.ConstructorResolver.instantiateUsingFactoryMethod(java.lang.String, org.springframework.beans.factory.support.RootBeanDefinition, java.lang.Object[]):org.springframework.beans.BeanWrapper");
    }

    private Object instantiate(String beanName, RootBeanDefinition mbd, @Nullable Object factoryBean, Method factoryMethod, Object[] args) {
        try {
            if (System.getSecurityManager() != null) {
                return AccessController.doPrivileged(() -> {
                    return this.beanFactory.getInstantiationStrategy().instantiate(mbd, beanName, this.beanFactory, factoryBean, factoryMethod, args);
                }, this.beanFactory.getAccessControlContext());
            }
            return this.beanFactory.getInstantiationStrategy().instantiate(mbd, beanName, this.beanFactory, factoryBean, factoryMethod, args);
        } catch (Throwable ex) {
            throw new BeanCreationException(mbd.getResourceDescription(), beanName, "Bean instantiation via factory method failed", ex);
        }
    }

    private int resolveConstructorArguments(String beanName, RootBeanDefinition mbd, BeanWrapper bw, ConstructorArgumentValues cargs, ConstructorArgumentValues resolvedValues) {
        TypeConverter customConverter = this.beanFactory.getCustomTypeConverter();
        TypeConverter converter = customConverter != null ? customConverter : bw;
        BeanDefinitionValueResolver valueResolver = new BeanDefinitionValueResolver(this.beanFactory, beanName, mbd, converter);
        int minNrOfArgs = cargs.getArgumentCount();
        for (Map.Entry<Integer, ConstructorArgumentValues.ValueHolder> entry : cargs.getIndexedArgumentValues().entrySet()) {
            int index = entry.getKey().intValue();
            if (index < 0) {
                throw new BeanCreationException(mbd.getResourceDescription(), beanName, "Invalid constructor argument index: " + index);
            }
            if (index > minNrOfArgs) {
                minNrOfArgs = index + 1;
            }
            ConstructorArgumentValues.ValueHolder valueHolder = entry.getValue();
            if (valueHolder.isConverted()) {
                resolvedValues.addIndexedArgumentValue(index, valueHolder);
            } else {
                Object resolvedValue = valueResolver.resolveValueIfNecessary("constructor argument", valueHolder.getValue());
                ConstructorArgumentValues.ValueHolder resolvedValueHolder = new ConstructorArgumentValues.ValueHolder(resolvedValue, valueHolder.getType(), valueHolder.getName());
                resolvedValueHolder.setSource(valueHolder);
                resolvedValues.addIndexedArgumentValue(index, resolvedValueHolder);
            }
        }
        for (ConstructorArgumentValues.ValueHolder valueHolder2 : cargs.getGenericArgumentValues()) {
            if (valueHolder2.isConverted()) {
                resolvedValues.addGenericArgumentValue(valueHolder2);
            } else {
                Object resolvedValue2 = valueResolver.resolveValueIfNecessary("constructor argument", valueHolder2.getValue());
                ConstructorArgumentValues.ValueHolder resolvedValueHolder2 = new ConstructorArgumentValues.ValueHolder(resolvedValue2, valueHolder2.getType(), valueHolder2.getName());
                resolvedValueHolder2.setSource(valueHolder2);
                resolvedValues.addGenericArgumentValue(resolvedValueHolder2);
            }
        }
        return minNrOfArgs;
    }

    private ArgumentsHolder createArgumentArray(String beanName, RootBeanDefinition mbd, @Nullable ConstructorArgumentValues resolvedValues, BeanWrapper bw, Class<?>[] paramTypes, @Nullable String[] paramNames, Executable executable, boolean autowiring, boolean fallback) throws UnsatisfiedDependencyException {
        Object convertedValue;
        TypeConverter customConverter = this.beanFactory.getCustomTypeConverter();
        TypeConverter converter = customConverter != null ? customConverter : bw;
        ArgumentsHolder args = new ArgumentsHolder(paramTypes.length);
        Set<ConstructorArgumentValues.ValueHolder> usedValueHolders = new HashSet<>(paramTypes.length);
        Set<String> autowiredBeanNames = new LinkedHashSet<>(4);
        for (int paramIndex = 0; paramIndex < paramTypes.length; paramIndex++) {
            Class<?> paramType = paramTypes[paramIndex];
            String paramName = paramNames != null ? paramNames[paramIndex] : "";
            ConstructorArgumentValues.ValueHolder valueHolder = null;
            if (resolvedValues != null) {
                valueHolder = resolvedValues.getArgumentValue(paramIndex, paramType, paramName, usedValueHolders);
                if (valueHolder == null && (!autowiring || paramTypes.length == resolvedValues.getArgumentCount())) {
                    valueHolder = resolvedValues.getGenericArgumentValue(null, null, usedValueHolders);
                }
            }
            if (valueHolder != null) {
                usedValueHolders.add(valueHolder);
                Object originalValue = valueHolder.getValue();
                if (valueHolder.isConverted()) {
                    convertedValue = valueHolder.getConvertedValue();
                    args.preparedArguments[paramIndex] = convertedValue;
                } else {
                    MethodParameter methodParam = MethodParameter.forExecutable(executable, paramIndex);
                    try {
                        convertedValue = converter.convertIfNecessary(originalValue, paramType, methodParam);
                        Object sourceHolder = valueHolder.getSource();
                        if (sourceHolder instanceof ConstructorArgumentValues.ValueHolder) {
                            Object sourceValue = ((ConstructorArgumentValues.ValueHolder) sourceHolder).getValue();
                            args.resolveNecessary = true;
                            args.preparedArguments[paramIndex] = sourceValue;
                        }
                    } catch (TypeMismatchException ex) {
                        throw new UnsatisfiedDependencyException(mbd.getResourceDescription(), beanName, new InjectionPoint(methodParam), "Could not convert argument value of type [" + ObjectUtils.nullSafeClassName(valueHolder.getValue()) + "] to required type [" + paramType.getName() + "]: " + ex.getMessage());
                    }
                }
                args.arguments[paramIndex] = convertedValue;
                args.rawArguments[paramIndex] = originalValue;
            } else {
                MethodParameter methodParam2 = MethodParameter.forExecutable(executable, paramIndex);
                if (!autowiring) {
                    throw new UnsatisfiedDependencyException(mbd.getResourceDescription(), beanName, new InjectionPoint(methodParam2), "Ambiguous argument values for parameter of type [" + paramType.getName() + "] - did you specify the correct bean references as arguments?");
                }
                try {
                    Object autowiredArgument = resolveAutowiredArgument(methodParam2, beanName, autowiredBeanNames, converter, fallback);
                    args.rawArguments[paramIndex] = autowiredArgument;
                    args.arguments[paramIndex] = autowiredArgument;
                    args.preparedArguments[paramIndex] = new AutowiredArgumentMarker();
                    args.resolveNecessary = true;
                } catch (BeansException ex2) {
                    throw new UnsatisfiedDependencyException(mbd.getResourceDescription(), beanName, new InjectionPoint(methodParam2), ex2);
                }
            }
        }
        for (String autowiredBeanName : autowiredBeanNames) {
            this.beanFactory.registerDependentBean(autowiredBeanName, beanName);
            if (this.logger.isDebugEnabled()) {
                this.logger.debug("Autowiring by type from bean name '" + beanName + "' via " + (executable instanceof Constructor ? BeanDefinitionParserDelegate.AUTOWIRE_CONSTRUCTOR_VALUE : "factory method") + " to bean named '" + autowiredBeanName + OperatorName.SHOW_TEXT_LINE);
            }
        }
        return args;
    }

    private Object[] resolvePreparedArguments(String beanName, RootBeanDefinition mbd, BeanWrapper bw, Executable executable, Object[] argsToResolve, boolean fallback) {
        TypeConverter customConverter = this.beanFactory.getCustomTypeConverter();
        TypeConverter converter = customConverter != null ? customConverter : bw;
        BeanDefinitionValueResolver valueResolver = new BeanDefinitionValueResolver(this.beanFactory, beanName, mbd, converter);
        Class<?>[] paramTypes = executable.getParameterTypes();
        Object[] resolvedArgs = new Object[argsToResolve.length];
        for (int argIndex = 0; argIndex < argsToResolve.length; argIndex++) {
            Object argValue = argsToResolve[argIndex];
            MethodParameter methodParam = MethodParameter.forExecutable(executable, argIndex);
            GenericTypeResolver.resolveParameterType(methodParam, executable.getDeclaringClass());
            if (argValue instanceof AutowiredArgumentMarker) {
                argValue = resolveAutowiredArgument(methodParam, beanName, null, converter, fallback);
            } else if (argValue instanceof BeanMetadataElement) {
                argValue = valueResolver.resolveValueIfNecessary("constructor argument", argValue);
            } else if (argValue instanceof String) {
                argValue = this.beanFactory.evaluateBeanDefinitionString((String) argValue, mbd);
            }
            Class<?> paramType = paramTypes[argIndex];
            try {
                resolvedArgs[argIndex] = converter.convertIfNecessary(argValue, paramType, methodParam);
            } catch (TypeMismatchException ex) {
                throw new UnsatisfiedDependencyException(mbd.getResourceDescription(), beanName, new InjectionPoint(methodParam), "Could not convert argument value of type [" + ObjectUtils.nullSafeClassName(argValue) + "] to required type [" + paramType.getName() + "]: " + ex.getMessage());
            }
        }
        return resolvedArgs;
    }

    protected Constructor<?> getUserDeclaredConstructor(Constructor<?> constructor) {
        Class<?> declaringClass = constructor.getDeclaringClass();
        Class<?> userClass = ClassUtils.getUserClass(declaringClass);
        if (userClass != declaringClass) {
            try {
                return userClass.getDeclaredConstructor(constructor.getParameterTypes());
            } catch (NoSuchMethodException e) {
            }
        }
        return constructor;
    }

    @Nullable
    protected Object resolveAutowiredArgument(MethodParameter param, String beanName, @Nullable Set<String> autowiredBeanNames, TypeConverter typeConverter, boolean fallback) {
        Class<?> paramType = param.getParameterType();
        if (InjectionPoint.class.isAssignableFrom(paramType)) {
            InjectionPoint injectionPoint = currentInjectionPoint.get();
            if (injectionPoint == null) {
                throw new IllegalStateException("No current InjectionPoint available for " + param);
            }
            return injectionPoint;
        }
        try {
            return this.beanFactory.resolveDependency(new DependencyDescriptor(param, true), beanName, autowiredBeanNames, typeConverter);
        } catch (NoUniqueBeanDefinitionException ex) {
            throw ex;
        } catch (NoSuchBeanDefinitionException ex2) {
            if (fallback) {
                if (paramType.isArray()) {
                    return Array.newInstance(paramType.getComponentType(), 0);
                }
                if (CollectionFactory.isApproximableCollectionType(paramType)) {
                    return CollectionFactory.createCollection(paramType, 0);
                }
                if (CollectionFactory.isApproximableMapType(paramType)) {
                    return CollectionFactory.createMap(paramType, 0);
                }
            }
            throw ex2;
        }
    }

    static InjectionPoint setCurrentInjectionPoint(@Nullable InjectionPoint injectionPoint) {
        InjectionPoint old = currentInjectionPoint.get();
        if (injectionPoint != null) {
            currentInjectionPoint.set(injectionPoint);
        } else {
            currentInjectionPoint.remove();
        }
        return old;
    }

    /* loaded from: reader.jar:BOOT-INF/lib/spring-beans-5.1.8.RELEASE.jar:org/springframework/beans/factory/support/ConstructorResolver$ArgumentsHolder.class */
    private static class ArgumentsHolder {
        public final Object[] rawArguments;
        public final Object[] arguments;
        public final Object[] preparedArguments;
        public boolean resolveNecessary = false;

        public ArgumentsHolder(int size) {
            this.rawArguments = new Object[size];
            this.arguments = new Object[size];
            this.preparedArguments = new Object[size];
        }

        public ArgumentsHolder(Object[] args) {
            this.rawArguments = args;
            this.arguments = args;
            this.preparedArguments = args;
        }

        public int getTypeDifferenceWeight(Class<?>[] paramTypes) {
            int typeDiffWeight = MethodInvoker.getTypeDifferenceWeight(paramTypes, this.arguments);
            int rawTypeDiffWeight = MethodInvoker.getTypeDifferenceWeight(paramTypes, this.rawArguments) - 1024;
            return rawTypeDiffWeight < typeDiffWeight ? rawTypeDiffWeight : typeDiffWeight;
        }

        public int getAssignabilityWeight(Class<?>[] paramTypes) {
            for (int i = 0; i < paramTypes.length; i++) {
                if (!ClassUtils.isAssignableValue(paramTypes[i], this.arguments[i])) {
                    return Integer.MAX_VALUE;
                }
            }
            for (int i2 = 0; i2 < paramTypes.length; i2++) {
                if (!ClassUtils.isAssignableValue(paramTypes[i2], this.rawArguments[i2])) {
                    return 2147483135;
                }
            }
            return 2147482623;
        }

        public void storeCache(RootBeanDefinition mbd, Executable constructorOrFactoryMethod) {
            synchronized (mbd.constructorArgumentLock) {
                mbd.resolvedConstructorOrFactoryMethod = constructorOrFactoryMethod;
                mbd.constructorArgumentsResolved = true;
                if (this.resolveNecessary) {
                    mbd.preparedConstructorArguments = this.preparedArguments;
                } else {
                    mbd.resolvedConstructorArguments = this.arguments;
                }
            }
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/spring-beans-5.1.8.RELEASE.jar:org/springframework/beans/factory/support/ConstructorResolver$AutowiredArgumentMarker.class */
    private static class AutowiredArgumentMarker {
        private AutowiredArgumentMarker() {
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/spring-beans-5.1.8.RELEASE.jar:org/springframework/beans/factory/support/ConstructorResolver$ConstructorPropertiesChecker.class */
    private static class ConstructorPropertiesChecker {
        private ConstructorPropertiesChecker() {
        }

        @Nullable
        public static String[] evaluate(Constructor<?> candidate, int paramCount) {
            ConstructorProperties cp = candidate.getAnnotation(ConstructorProperties.class);
            if (cp != null) {
                String[] names = cp.value();
                if (names.length != paramCount) {
                    throw new IllegalStateException("Constructor annotated with @ConstructorProperties but not corresponding to actual number of parameters (" + paramCount + "): " + candidate);
                }
                return names;
            }
            return null;
        }
    }
}
