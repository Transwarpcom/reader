package org.springframework.beans.factory.support;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import org.apache.pdfbox.contentstream.operator.OperatorName;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.CannotLoadBeanClassException;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.lang.Nullable;
import org.springframework.util.DefaultPropertiesPersister;
import org.springframework.util.PropertiesPersister;
import org.springframework.util.StringUtils;

/* loaded from: reader.jar:BOOT-INF/lib/spring-beans-5.1.8.RELEASE.jar:org/springframework/beans/factory/support/PropertiesBeanDefinitionReader.class */
public class PropertiesBeanDefinitionReader extends AbstractBeanDefinitionReader {
    public static final String TRUE_VALUE = "true";
    public static final String SEPARATOR = ".";
    public static final String CLASS_KEY = "(class)";
    public static final String PARENT_KEY = "(parent)";
    public static final String SCOPE_KEY = "(scope)";
    public static final String SINGLETON_KEY = "(singleton)";
    public static final String ABSTRACT_KEY = "(abstract)";
    public static final String LAZY_INIT_KEY = "(lazy-init)";
    public static final String REF_SUFFIX = "(ref)";
    public static final String REF_PREFIX = "*";
    public static final String CONSTRUCTOR_ARG_PREFIX = "$";

    @Nullable
    private String defaultParentBean;
    private PropertiesPersister propertiesPersister;

    public PropertiesBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
        this.propertiesPersister = new DefaultPropertiesPersister();
    }

    public void setDefaultParentBean(@Nullable String defaultParentBean) {
        this.defaultParentBean = defaultParentBean;
    }

    @Nullable
    public String getDefaultParentBean() {
        return this.defaultParentBean;
    }

    public void setPropertiesPersister(@Nullable PropertiesPersister propertiesPersister) {
        this.propertiesPersister = propertiesPersister != null ? propertiesPersister : new DefaultPropertiesPersister();
    }

    public PropertiesPersister getPropertiesPersister() {
        return this.propertiesPersister;
    }

    @Override // org.springframework.beans.factory.support.BeanDefinitionReader
    public int loadBeanDefinitions(Resource resource) throws BeanDefinitionStoreException {
        return loadBeanDefinitions(new EncodedResource(resource), (String) null);
    }

    public int loadBeanDefinitions(Resource resource, @Nullable String prefix) throws BeanDefinitionStoreException {
        return loadBeanDefinitions(new EncodedResource(resource), prefix);
    }

    public int loadBeanDefinitions(EncodedResource encodedResource) throws BeanDefinitionStoreException {
        return loadBeanDefinitions(encodedResource, (String) null);
    }

    /* JADX WARN: Failed to apply debug info
    java.lang.NullPointerException
     */
    /* JADX WARN: Failed to calculate best type for var: r11v0 ??
    java.lang.NullPointerException
     */
    /* JADX WARN: Failed to calculate best type for var: r12v0 ??
    java.lang.NullPointerException
     */
    /* JADX WARN: Multi-variable type inference failed. Error: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.RegisterArg.getSVar()" because the return value of "jadx.core.dex.nodes.InsnNode.getResult()" is null
    	at jadx.core.dex.visitors.typeinference.AbstractTypeConstraint.collectRelatedVars(AbstractTypeConstraint.java:31)
    	at jadx.core.dex.visitors.typeinference.AbstractTypeConstraint.<init>(AbstractTypeConstraint.java:19)
    	at jadx.core.dex.visitors.typeinference.TypeSearch$1.<init>(TypeSearch.java:376)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.makeMoveConstraint(TypeSearch.java:376)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.makeConstraint(TypeSearch.java:361)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.collectConstraints(TypeSearch.java:341)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:60)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.runMultiVariableSearch(FixTypesVisitor.java:116)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:91)
     */
    /* JADX WARN: Not initialized variable reg: 11, insn: 0x009c: MOVE (r0 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r11 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('is' java.io.InputStream)]) A[TRY_LEAVE], block:B:23:0x009c */
    /* JADX WARN: Not initialized variable reg: 12, insn: 0x00a1: MOVE (r0 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r12 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]), block:B:25:0x00a1 */
    /* JADX WARN: Type inference failed for: r11v0, names: [is], types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r12v0, types: [java.lang.Throwable] */
    public int loadBeanDefinitions(EncodedResource encodedResource, @Nullable String prefix) throws IOException, NumberFormatException, BeansException {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace("Loading properties bean definitions from " + encodedResource);
        }
        Properties props = new Properties();
        try {
            try {
                InputStream inputStream = encodedResource.getResource().getInputStream();
                Throwable th = null;
                if (encodedResource.getEncoding() != null) {
                    getPropertiesPersister().load(props, new InputStreamReader(inputStream, encodedResource.getEncoding()));
                } else {
                    getPropertiesPersister().load(props, inputStream);
                }
                if (inputStream != null) {
                    if (0 != 0) {
                        try {
                            inputStream.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                    } else {
                        inputStream.close();
                    }
                }
                int iRegisterBeanDefinitions = registerBeanDefinitions(props, prefix, encodedResource.getResource().getDescription());
                if (this.logger.isDebugEnabled()) {
                    this.logger.debug("Loaded " + iRegisterBeanDefinitions + " bean definitions from " + encodedResource);
                }
                return iRegisterBeanDefinitions;
            } finally {
            }
        } catch (IOException e) {
            throw new BeanDefinitionStoreException("Could not parse properties from " + encodedResource.getResource(), e);
        }
    }

    public int registerBeanDefinitions(ResourceBundle rb) throws BeanDefinitionStoreException {
        return registerBeanDefinitions(rb, (String) null);
    }

    public int registerBeanDefinitions(ResourceBundle rb, @Nullable String prefix) throws BeanDefinitionStoreException {
        Map<String, Object> map = new HashMap<>();
        Enumeration<String> keys = rb.getKeys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            map.put(key, rb.getObject(key));
        }
        return registerBeanDefinitions(map, prefix);
    }

    public int registerBeanDefinitions(Map<?, ?> map) throws BeansException {
        return registerBeanDefinitions(map, (String) null);
    }

    public int registerBeanDefinitions(Map<?, ?> map, @Nullable String prefix) throws BeansException {
        return registerBeanDefinitions(map, prefix, "Map " + map);
    }

    public int registerBeanDefinitions(Map<?, ?> map, @Nullable String prefix, String resourceDescription) throws NumberFormatException, BeansException {
        int sepIdx;
        if (prefix == null) {
            prefix = "";
        }
        int beanCount = 0;
        for (Object key : map.keySet()) {
            if (!(key instanceof String)) {
                throw new IllegalArgumentException("Illegal key [" + key + "]: only Strings allowed");
            }
            String keyString = (String) key;
            if (keyString.startsWith(prefix)) {
                String nameAndProperty = keyString.substring(prefix.length());
                int propKeyIdx = nameAndProperty.indexOf("[");
                if (propKeyIdx != -1) {
                    sepIdx = nameAndProperty.lastIndexOf(".", propKeyIdx);
                } else {
                    sepIdx = nameAndProperty.lastIndexOf(".");
                }
                if (sepIdx != -1) {
                    String beanName = nameAndProperty.substring(0, sepIdx);
                    if (this.logger.isTraceEnabled()) {
                        this.logger.trace("Found bean name '" + beanName + OperatorName.SHOW_TEXT_LINE);
                    }
                    if (!getRegistry().containsBeanDefinition(beanName)) {
                        registerBeanDefinition(beanName, map, prefix + beanName, resourceDescription);
                        beanCount++;
                    }
                } else if (this.logger.isDebugEnabled()) {
                    this.logger.debug("Invalid bean name and property [" + nameAndProperty + "]");
                }
            }
        }
        return beanCount;
    }

    protected void registerBeanDefinition(String beanName, Map<?, ?> map, String prefix, String resourceDescription) throws NumberFormatException, BeansException {
        String className = null;
        String parent = null;
        String scope = "singleton";
        boolean isAbstract = false;
        boolean lazyInit = false;
        ConstructorArgumentValues cas = new ConstructorArgumentValues();
        MutablePropertyValues pvs = new MutablePropertyValues();
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            String key = StringUtils.trimWhitespace((String) entry.getKey());
            if (key.startsWith(prefix + ".")) {
                String property = key.substring(prefix.length() + ".".length());
                if (CLASS_KEY.equals(property)) {
                    className = StringUtils.trimWhitespace((String) entry.getValue());
                } else if (PARENT_KEY.equals(property)) {
                    parent = StringUtils.trimWhitespace((String) entry.getValue());
                } else if (ABSTRACT_KEY.equals(property)) {
                    isAbstract = "true".equals(StringUtils.trimWhitespace((String) entry.getValue()));
                } else if (SCOPE_KEY.equals(property)) {
                    scope = StringUtils.trimWhitespace((String) entry.getValue());
                } else if (SINGLETON_KEY.equals(property)) {
                    String val = StringUtils.trimWhitespace((String) entry.getValue());
                    scope = ("".equals(val) || "true".equals(val)) ? "singleton" : "prototype";
                } else if (LAZY_INIT_KEY.equals(property)) {
                    lazyInit = "true".equals(StringUtils.trimWhitespace((String) entry.getValue()));
                } else if (property.startsWith(CONSTRUCTOR_ARG_PREFIX)) {
                    if (property.endsWith(REF_SUFFIX)) {
                        int index = Integer.parseInt(property.substring(1, property.length() - REF_SUFFIX.length()));
                        cas.addIndexedArgumentValue(index, new RuntimeBeanReference(entry.getValue().toString()));
                    } else {
                        int index2 = Integer.parseInt(property.substring(1));
                        cas.addIndexedArgumentValue(index2, readValue(entry));
                    }
                } else if (property.endsWith(REF_SUFFIX)) {
                    String property2 = property.substring(0, property.length() - REF_SUFFIX.length());
                    String ref = StringUtils.trimWhitespace((String) entry.getValue());
                    pvs.add(property2, new RuntimeBeanReference(ref));
                } else {
                    pvs.add(property, readValue(entry));
                }
            }
        }
        if (this.logger.isTraceEnabled()) {
            this.logger.trace("Registering bean definition for bean name '" + beanName + "' with " + pvs);
        }
        if (parent == null && className == null && !beanName.equals(this.defaultParentBean)) {
            parent = this.defaultParentBean;
        }
        try {
            AbstractBeanDefinition bd = BeanDefinitionReaderUtils.createBeanDefinition(parent, className, getBeanClassLoader());
            bd.setScope(scope);
            bd.setAbstract(isAbstract);
            bd.setLazyInit(lazyInit);
            bd.setConstructorArgumentValues(cas);
            bd.setPropertyValues(pvs);
            getRegistry().registerBeanDefinition(beanName, bd);
        } catch (ClassNotFoundException ex) {
            throw new CannotLoadBeanClassException(resourceDescription, beanName, className, ex);
        } catch (LinkageError err) {
            throw new CannotLoadBeanClassException(resourceDescription, beanName, className, err);
        }
    }

    private Object readValue(Map.Entry<?, ?> entry) {
        Object val = entry.getValue();
        if (val instanceof String) {
            String strVal = (String) val;
            if (strVal.startsWith("*")) {
                String targetName = strVal.substring(1);
                val = targetName.startsWith("*") ? targetName : new RuntimeBeanReference(targetName);
            }
        }
        return val;
    }
}
