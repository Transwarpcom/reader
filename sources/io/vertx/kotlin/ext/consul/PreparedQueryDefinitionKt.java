package io.vertx.kotlin.ext.consul;

import io.vertx.ext.consul.PreparedQueryDefinition;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: PreparedQueryDefinition.kt */
@Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 2, d1 = {"��*\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u001c\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010\b\n��\n\u0002\u0010\u000b\n\u0002\b\t\u001aÁ\u0001\u0010��\u001a\u00020\u00012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00042\u0016\b\u0002\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00042\u0010\b\u0002\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0004H\u0007¢\u0006\u0002\u0010\u0014\u001a¿\u0001\u0010\u0015\u001a\u00020\u00012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00042\u0016\b\u0002\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00042\u0010\b\u0002\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0014¨\u0006\u0016"}, d2 = {"PreparedQueryDefinition", "Lio/vertx/ext/consul/PreparedQueryDefinition;", "dcs", "", "", "dnsTtl", "id", "meta", "", "name", "nearestN", "", "passing", "", "service", "session", "tags", "templateRegexp", "templateType", "token", "(Ljava/lang/Iterable;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Iterable;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lio/vertx/ext/consul/PreparedQueryDefinition;", "preparedQueryDefinitionOf", "vertx-lang-kotlin"})
/* loaded from: reader.jar:BOOT-INF/lib/vertx-lang-kotlin-3.8.5.jar:io/vertx/kotlin/ext/consul/PreparedQueryDefinitionKt.class */
public final class PreparedQueryDefinitionKt {
    @NotNull
    public static /* synthetic */ PreparedQueryDefinition preparedQueryDefinitionOf$default(Iterable iterable, String str, String str2, Map map, String str3, Integer num, Boolean bool, String str4, String str5, Iterable iterable2, String str6, String str7, String str8, int i, Object obj) {
        if ((i & 1) != 0) {
            iterable = (Iterable) null;
        }
        if ((i & 2) != 0) {
            str = (String) null;
        }
        if ((i & 4) != 0) {
            str2 = (String) null;
        }
        if ((i & 8) != 0) {
            map = (Map) null;
        }
        if ((i & 16) != 0) {
            str3 = (String) null;
        }
        if ((i & 32) != 0) {
            num = (Integer) null;
        }
        if ((i & 64) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 128) != 0) {
            str4 = (String) null;
        }
        if ((i & 256) != 0) {
            str5 = (String) null;
        }
        if ((i & 512) != 0) {
            iterable2 = (Iterable) null;
        }
        if ((i & 1024) != 0) {
            str6 = (String) null;
        }
        if ((i & 2048) != 0) {
            str7 = (String) null;
        }
        if ((i & 4096) != 0) {
            str8 = (String) null;
        }
        return preparedQueryDefinitionOf(iterable, str, str2, map, str3, num, bool, str4, str5, iterable2, str6, str7, str8);
    }

    @NotNull
    public static final PreparedQueryDefinition preparedQueryDefinitionOf(@Nullable Iterable<String> iterable, @Nullable String dnsTtl, @Nullable String id, @Nullable Map<String, String> map, @Nullable String name, @Nullable Integer nearestN, @Nullable Boolean passing, @Nullable String service, @Nullable String session, @Nullable Iterable<String> iterable2, @Nullable String templateRegexp, @Nullable String templateType, @Nullable String token) {
        PreparedQueryDefinition $this$apply = new PreparedQueryDefinition();
        if (iterable != null) {
            $this$apply.setDcs(CollectionsKt.toList(iterable));
        }
        if (dnsTtl != null) {
            $this$apply.setDnsTtl(dnsTtl);
        }
        if (id != null) {
            $this$apply.setId(id);
        }
        if (map != null) {
            $this$apply.setMeta(map);
        }
        if (name != null) {
            $this$apply.setName(name);
        }
        if (nearestN != null) {
            $this$apply.setNearestN(nearestN.intValue());
        }
        if (passing != null) {
            $this$apply.setPassing(passing.booleanValue());
        }
        if (service != null) {
            $this$apply.setService(service);
        }
        if (session != null) {
            $this$apply.setSession(session);
        }
        if (iterable2 != null) {
            $this$apply.setTags(CollectionsKt.toList(iterable2));
        }
        if (templateRegexp != null) {
            $this$apply.setTemplateRegexp(templateRegexp);
        }
        if (templateType != null) {
            $this$apply.setTemplateType(templateType);
        }
        if (token != null) {
            $this$apply.setToken(token);
        }
        return $this$apply;
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "preparedQueryDefinitionOf(dcs, dnsTtl, id, meta, name, nearestN, passing, service, session, tags, templateRegexp, templateType, token)"))
    @NotNull
    public static /* synthetic */ PreparedQueryDefinition PreparedQueryDefinition$default(Iterable iterable, String str, String str2, Map map, String str3, Integer num, Boolean bool, String str4, String str5, Iterable iterable2, String str6, String str7, String str8, int i, Object obj) {
        if ((i & 1) != 0) {
            iterable = (Iterable) null;
        }
        if ((i & 2) != 0) {
            str = (String) null;
        }
        if ((i & 4) != 0) {
            str2 = (String) null;
        }
        if ((i & 8) != 0) {
            map = (Map) null;
        }
        if ((i & 16) != 0) {
            str3 = (String) null;
        }
        if ((i & 32) != 0) {
            num = (Integer) null;
        }
        if ((i & 64) != 0) {
            bool = (Boolean) null;
        }
        if ((i & 128) != 0) {
            str4 = (String) null;
        }
        if ((i & 256) != 0) {
            str5 = (String) null;
        }
        if ((i & 512) != 0) {
            iterable2 = (Iterable) null;
        }
        if ((i & 1024) != 0) {
            str6 = (String) null;
        }
        if ((i & 2048) != 0) {
            str7 = (String) null;
        }
        if ((i & 4096) != 0) {
            str8 = (String) null;
        }
        return PreparedQueryDefinition(iterable, str, str2, map, str3, num, bool, str4, str5, iterable2, str6, str7, str8);
    }

    @Deprecated(message = "This function will be removed in a future version", replaceWith = @ReplaceWith(imports = {}, expression = "preparedQueryDefinitionOf(dcs, dnsTtl, id, meta, name, nearestN, passing, service, session, tags, templateRegexp, templateType, token)"))
    @NotNull
    public static final PreparedQueryDefinition PreparedQueryDefinition(@Nullable Iterable<String> iterable, @Nullable String dnsTtl, @Nullable String id, @Nullable Map<String, String> map, @Nullable String name, @Nullable Integer nearestN, @Nullable Boolean passing, @Nullable String service, @Nullable String session, @Nullable Iterable<String> iterable2, @Nullable String templateRegexp, @Nullable String templateType, @Nullable String token) {
        PreparedQueryDefinition $this$apply = new PreparedQueryDefinition();
        if (iterable != null) {
            $this$apply.setDcs(CollectionsKt.toList(iterable));
        }
        if (dnsTtl != null) {
            $this$apply.setDnsTtl(dnsTtl);
        }
        if (id != null) {
            $this$apply.setId(id);
        }
        if (map != null) {
            $this$apply.setMeta(map);
        }
        if (name != null) {
            $this$apply.setName(name);
        }
        if (nearestN != null) {
            $this$apply.setNearestN(nearestN.intValue());
        }
        if (passing != null) {
            $this$apply.setPassing(passing.booleanValue());
        }
        if (service != null) {
            $this$apply.setService(service);
        }
        if (session != null) {
            $this$apply.setSession(session);
        }
        if (iterable2 != null) {
            $this$apply.setTags(CollectionsKt.toList(iterable2));
        }
        if (templateRegexp != null) {
            $this$apply.setTemplateRegexp(templateRegexp);
        }
        if (templateType != null) {
            $this$apply.setTemplateType(templateType);
        }
        if (token != null) {
            $this$apply.setToken(token);
        }
        return $this$apply;
    }
}
