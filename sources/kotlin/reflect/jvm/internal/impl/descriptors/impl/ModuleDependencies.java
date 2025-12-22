package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import java.util.List;
import java.util.Set;
import org.jetbrains.annotations.NotNull;

/* compiled from: ModuleDescriptorImpl.kt */
/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/descriptors/impl/ModuleDependencies.class */
public interface ModuleDependencies {
    @NotNull
    List<ModuleDescriptorImpl> getAllDependencies();

    @NotNull
    Set<ModuleDescriptorImpl> getModulesWhoseInternalsAreVisible();

    @NotNull
    List<ModuleDescriptorImpl> getDirectExpectedByDependencies();
}
