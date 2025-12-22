package io.vertx.kotlin.ext.web.api.contract;

import io.vertx.ext.web.api.contract.RouterFactoryOptions;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: RouterFactoryOptions.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��\u0018\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\u001aI\u0010��\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003H\u0007¢\u0006\u0002\u0010\t\u001aG\u0010\n\u001a\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\t¨\u0006\u000b"}, d2 = {"RouterFactoryOptions", "Lio/vertx/ext/web/api/contract/RouterFactoryOptions;", "mountNotImplementedHandler", "", "mountResponseContentTypeHandler", "mountValidationFailureHandler", "operationModelKey", "", "requireSecurityHandlers", "(Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Boolean;)Lio/vertx/ext/web/api/contract/RouterFactoryOptions;", "routerFactoryOptionsOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/web/api/contract/RouterFactoryOptionsKt.class */
public final class RouterFactoryOptionsKt {
    @NotNull
    public static /* synthetic */ RouterFactoryOptions routerFactoryOptionsOf$default(Boolean bool, Boolean bool2, Boolean bool3, String str, Boolean bool4, int i, Object obj) {
        if ((i & 1) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 2) != 0) {
            bool2 = (Boolean) null;
        }
        if ((i & 4) != 0) {
            bool3 = (Boolean) null;
        }
        if ((i & 8) != 0) {
            str = (String) null;
        }
        if ((i & 16) != 0) {
            bool4 = (Boolean) null;
        }
        return routerFactoryOptionsOf(bool, bool2, bool3, str, bool4);
    }

    @NotNull
    public static final RouterFactoryOptions routerFactoryOptionsOf(@Nullable Boolean mountNotImplementedHandler, @Nullable Boolean mountResponseContentTypeHandler, @Nullable Boolean mountValidationFailureHandler, @Nullable String operationModelKey, @Nullable Boolean requireSecurityHandlers) {
        RouterFactoryOptions $this$apply = new RouterFactoryOptions();
        if (mountNotImplementedHandler != null) {
            $this$apply.setMountNotImplementedHandler(mountNotImplementedHandler.booleanValue());
        }
        if (mountResponseContentTypeHandler != null) {
            $this$apply.setMountResponseContentTypeHandler(mountResponseContentTypeHandler.booleanValue());
        }
        if (mountValidationFailureHandler != null) {
            $this$apply.setMountValidationFailureHandler(mountValidationFailureHandler.booleanValue());
        }
        if (operationModelKey != null) {
            $this$apply.setOperationModelKey(operationModelKey);
        }
        if (requireSecurityHandlers != null) {
            $this$apply.setRequireSecurityHandlers(requireSecurityHandlers.booleanValue());
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "routerFactoryOptionsOf(mountNotImplementedHandler, mountResponseContentTypeHandler, mountValidationFailureHandler, operationModelKey, requireSecurityHandlers)"))
    @NotNull
    public static /* synthetic */ RouterFactoryOptions RouterFactoryOptions$default(Boolean bool, Boolean bool2, Boolean bool3, String str, Boolean bool4, int i, Object obj) {
        if ((i & 1) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 2) != 0) {
            bool2 = (Boolean) null;
        }
        if ((i & 4) != 0) {
            bool3 = (Boolean) null;
        }
        if ((i & 8) != 0) {
            str = (String) null;
        }
        if ((i & 16) != 0) {
            bool4 = (Boolean) null;
        }
        return RouterFactoryOptions(bool, bool2, bool3, str, bool4);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "routerFactoryOptionsOf(mountNotImplementedHandler, mountResponseContentTypeHandler, mountValidationFailureHandler, operationModelKey, requireSecurityHandlers)"))
    @NotNull
    public static final RouterFactoryOptions RouterFactoryOptions(@Nullable Boolean mountNotImplementedHandler, @Nullable Boolean mountResponseContentTypeHandler, @Nullable Boolean mountValidationFailureHandler, @Nullable String operationModelKey, @Nullable Boolean requireSecurityHandlers) {
        RouterFactoryOptions $this$apply = new RouterFactoryOptions();
        if (mountNotImplementedHandler != null) {
            $this$apply.setMountNotImplementedHandler(mountNotImplementedHandler.booleanValue());
        }
        if (mountResponseContentTypeHandler != null) {
            $this$apply.setMountResponseContentTypeHandler(mountResponseContentTypeHandler.booleanValue());
        }
        if (mountValidationFailureHandler != null) {
            $this$apply.setMountValidationFailureHandler(mountValidationFailureHandler.booleanValue());
        }
        if (operationModelKey != null) {
            $this$apply.setOperationModelKey(operationModelKey);
        }
        if (requireSecurityHandlers != null) {
            $this$apply.setRequireSecurityHandlers(requireSecurityHandlers.booleanValue());
        }
        return $this$apply;
    }
}
