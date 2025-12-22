package kotlin.reflect.jvm.internal.impl.utils;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;
import org.springframework.cache.interceptor.CacheOperationExpressionEvaluator;
import org.springframework.cglib.core.Constants;

/* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/utils/DFS.class */
public class DFS {

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/utils/DFS$Neighbors.class */
    public interface Neighbors<N> {
        @NotNull
        Iterable<? extends N> getNeighbors(N n);
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/utils/DFS$NodeHandler.class */
    public interface NodeHandler<N, R> {
        boolean beforeChildren(N n);

        void afterChildren(N n);

        /* renamed from: result */
        R mo3664result();
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/utils/DFS$Visited.class */
    public interface Visited<N> {
        boolean checkAndMarkVisited(N n);
    }

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        Object[] objArr = new Object[3];
        switch (i) {
            case 0:
            case 4:
            case 7:
            case 17:
            case 20:
            default:
                objArr[0] = "nodes";
                break;
            case 1:
            case 5:
            case 8:
            case 11:
            case 15:
            case 18:
            case 21:
            case 23:
                objArr[0] = "neighbors";
                break;
            case 2:
            case 12:
            case 16:
            case 19:
            case 24:
                objArr[0] = "visited";
                break;
            case 3:
            case 6:
            case 13:
            case 25:
                objArr[0] = "handler";
                break;
            case 9:
                objArr[0] = "predicate";
                break;
            case 10:
            case 14:
                objArr[0] = "node";
                break;
            case 22:
                objArr[0] = "current";
                break;
        }
        objArr[1] = "kotlin/reflect/jvm/internal/impl/utils/DFS";
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            default:
                objArr[2] = "dfs";
                break;
            case 7:
            case 8:
            case 9:
                objArr[2] = "ifAny";
                break;
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
                objArr[2] = "dfsFromNode";
                break;
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
                objArr[2] = "topologicalOrder";
                break;
            case 22:
            case 23:
            case 24:
            case 25:
                objArr[2] = "doDfs";
                break;
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    public static <N, R> R dfs(@NotNull Collection<N> nodes, @NotNull Neighbors<N> neighbors, @NotNull Visited<N> visited, @NotNull NodeHandler<N, R> handler) {
        if (nodes == null) {
            $$$reportNull$$$0(0);
        }
        if (neighbors == null) {
            $$$reportNull$$$0(1);
        }
        if (visited == null) {
            $$$reportNull$$$0(2);
        }
        if (handler == null) {
            $$$reportNull$$$0(3);
        }
        for (N node : nodes) {
            doDfs(node, neighbors, visited, handler);
        }
        return handler.mo3664result();
    }

    public static <N, R> R dfs(@NotNull Collection<N> collection, @NotNull Neighbors<N> neighbors, @NotNull NodeHandler<N, R> nodeHandler) {
        if (collection == null) {
            $$$reportNull$$$0(4);
        }
        if (neighbors == null) {
            $$$reportNull$$$0(5);
        }
        if (nodeHandler == null) {
            $$$reportNull$$$0(6);
        }
        return (R) dfs(collection, neighbors, new VisitedWithSet(), nodeHandler);
    }

    public static <N> Boolean ifAny(@NotNull Collection<N> nodes, @NotNull Neighbors<N> neighbors, @NotNull final Function1<N, Boolean> predicate) {
        if (nodes == null) {
            $$$reportNull$$$0(7);
        }
        if (neighbors == null) {
            $$$reportNull$$$0(8);
        }
        if (predicate == null) {
            $$$reportNull$$$0(9);
        }
        final boolean[] result = new boolean[1];
        return (Boolean) dfs(nodes, neighbors, new AbstractNodeHandler<N, Boolean>() { // from class: kotlin.reflect.jvm.internal.impl.utils.DFS.1
            @Override // kotlin.reflect.jvm.internal.impl.utils.DFS.AbstractNodeHandler, kotlin.reflect.jvm.internal.impl.utils.DFS.NodeHandler
            public boolean beforeChildren(N current) {
                if (((Boolean) predicate.invoke(current)).booleanValue()) {
                    result[0] = true;
                }
                return !result[0];
            }

            @Override // kotlin.reflect.jvm.internal.impl.utils.DFS.NodeHandler
            /* renamed from: result */
            public Boolean mo3664result() {
                return Boolean.valueOf(result[0]);
            }
        });
    }

    public static <N> void doDfs(@NotNull N current, @NotNull Neighbors<N> neighbors, @NotNull Visited<N> visited, @NotNull NodeHandler<N, ?> handler) {
        if (current == null) {
            $$$reportNull$$$0(22);
        }
        if (neighbors == null) {
            $$$reportNull$$$0(23);
        }
        if (visited == null) {
            $$$reportNull$$$0(24);
        }
        if (handler == null) {
            $$$reportNull$$$0(25);
        }
        if (visited.checkAndMarkVisited(current) && handler.beforeChildren(current)) {
            for (N neighbor : neighbors.getNeighbors(current)) {
                doDfs(neighbor, neighbors, visited, handler);
            }
            handler.afterChildren(current);
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/utils/DFS$AbstractNodeHandler.class */
    public static abstract class AbstractNodeHandler<N, R> implements NodeHandler<N, R> {
        @Override // kotlin.reflect.jvm.internal.impl.utils.DFS.NodeHandler
        public boolean beforeChildren(N current) {
            return true;
        }

        @Override // kotlin.reflect.jvm.internal.impl.utils.DFS.NodeHandler
        public void afterChildren(N current) {
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/utils/DFS$VisitedWithSet.class */
    public static class VisitedWithSet<N> implements Visited<N> {
        private final Set<N> visited;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visited", "kotlin/reflect/jvm/internal/impl/utils/DFS$VisitedWithSet", Constants.CONSTRUCTOR_NAME));
        }

        public VisitedWithSet() {
            this(new HashSet());
        }

        public VisitedWithSet(@NotNull Set<N> visited) {
            if (visited == null) {
                $$$reportNull$$$0(0);
            }
            this.visited = visited;
        }

        @Override // kotlin.reflect.jvm.internal.impl.utils.DFS.Visited
        public boolean checkAndMarkVisited(N current) {
            return this.visited.add(current);
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/utils/DFS$CollectingNodeHandler.class */
    public static abstract class CollectingNodeHandler<N, R, C extends Iterable<R>> extends AbstractNodeHandler<N, C> {

        @NotNull
        protected final C result;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            String str;
            int i2;
            switch (i) {
                case 0:
                default:
                    str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                    break;
                case 1:
                    str = "@NotNull method %s.%s must not return null";
                    break;
            }
            switch (i) {
                case 0:
                default:
                    i2 = 3;
                    break;
                case 1:
                    i2 = 2;
                    break;
            }
            Object[] objArr = new Object[i2];
            switch (i) {
                case 0:
                default:
                    objArr[0] = CacheOperationExpressionEvaluator.RESULT_VARIABLE;
                    break;
                case 1:
                    objArr[0] = "kotlin/reflect/jvm/internal/impl/utils/DFS$CollectingNodeHandler";
                    break;
            }
            switch (i) {
                case 0:
                default:
                    objArr[1] = "kotlin/reflect/jvm/internal/impl/utils/DFS$CollectingNodeHandler";
                    break;
                case 1:
                    objArr[1] = CacheOperationExpressionEvaluator.RESULT_VARIABLE;
                    break;
            }
            switch (i) {
                case 0:
                default:
                    objArr[2] = Constants.CONSTRUCTOR_NAME;
                    break;
                case 1:
                    break;
            }
            String str2 = String.format(str, objArr);
            switch (i) {
                case 0:
                default:
                    throw new IllegalArgumentException(str2);
                case 1:
                    throw new IllegalStateException(str2);
            }
        }

        protected CollectingNodeHandler(@NotNull C result) {
            if (result == null) {
                $$$reportNull$$$0(0);
            }
            this.result = result;
        }

        @Override // kotlin.reflect.jvm.internal.impl.utils.DFS.NodeHandler
        @NotNull
        /* renamed from: result */
        public C mo3664result() {
            C c = this.result;
            if (c == null) {
                $$$reportNull$$$0(1);
            }
            return c;
        }
    }

    /* loaded from: reader.jar:BOOT-INF/lib/kotlin-reflect-1.5.21.jar:kotlin/reflect/jvm/internal/impl/utils/DFS$NodeHandlerWithListResult.class */
    public static abstract class NodeHandlerWithListResult<N, R> extends CollectingNodeHandler<N, R, LinkedList<R>> {
        protected NodeHandlerWithListResult() {
            super(new LinkedList());
        }
    }
}
