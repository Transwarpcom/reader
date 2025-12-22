package cn.hutool.core.map.multi;

import cn.hutool.core.lang.Opt;
import cn.hutool.core.lang.func.Consumer3;
import cn.hutool.core.map.MapUtil;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/map/multi/Table.class */
public interface Table<R, C, V> extends Iterable<Cell<R, C, V>> {

    /* loaded from: reader.jar:BOOT-INF/lib/hutool-core-5.8.0.M1.jar:cn/hutool/core/map/multi/Table$Cell.class */
    public interface Cell<R, C, V> {
        R getRowKey();

        C getColumnKey();

        V getValue();
    }

    Map<R, Map<C, V>> rowMap();

    Map<C, Map<R, V>> columnMap();

    Collection<V> values();

    Set<Cell<R, C, V>> cellSet();

    V put(R r, C c, V v);

    V remove(R r, C c);

    boolean isEmpty();

    void clear();

    default boolean contains(R rowKey, C columnKey) {
        return ((Boolean) Opt.ofNullable(getRow(rowKey)).map(map -> {
            return Boolean.valueOf(map.containsKey(columnKey));
        }).get()).booleanValue();
    }

    default boolean containsRow(R rowKey) {
        return ((Boolean) Opt.ofNullable(rowMap()).map(map -> {
            return Boolean.valueOf(map.containsKey(rowKey));
        }).get()).booleanValue();
    }

    default Map<C, V> getRow(R rowKey) {
        return (Map) Opt.ofNullable(rowMap()).map(map -> {
            return (Map) map.get(rowKey);
        }).get();
    }

    default Set<R> rowKeySet() {
        return (Set) Opt.ofNullable(rowMap()).map((v0) -> {
            return v0.keySet();
        }).get();
    }

    default boolean containsColumn(C columnKey) {
        return ((Boolean) Opt.ofNullable(columnMap()).map(map -> {
            return Boolean.valueOf(map.containsKey(columnKey));
        }).get()).booleanValue();
    }

    default Map<R, V> getColumn(C columnKey) {
        return (Map) Opt.ofNullable(columnMap()).map(map -> {
            return (Map) map.get(columnKey);
        }).get();
    }

    default Set<C> columnKeySet() {
        return (Set) Opt.ofNullable(columnMap()).map((v0) -> {
            return v0.keySet();
        }).get();
    }

    default boolean containsValue(V value) {
        Collection<Map<C, V>> rows = (Collection) Opt.ofNullable(rowMap()).map((v0) -> {
            return v0.values();
        }).get();
        if (null != rows) {
            for (Map<C, V> row : rows) {
                if (row.containsValue(value)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    default V get(R r, C c) {
        return (V) Opt.ofNullable(getRow(r)).map(map -> {
            return map.get(c);
        }).get();
    }

    default void putAll(Table<? extends R, ? extends C, ? extends V> table) {
        if (null != table) {
            for (Cell<? extends R, ? extends C, ? extends V> cell : table.cellSet()) {
                put(cell.getRowKey(), cell.getColumnKey(), cell.getValue());
            }
        }
    }

    default int size() {
        Map<R, Map<C, V>> rowMap = rowMap();
        if (MapUtil.isEmpty(rowMap)) {
            return 0;
        }
        int size = 0;
        for (Map<C, V> map : rowMap.values()) {
            size += map.size();
        }
        return size;
    }

    default void forEach(Consumer3<? super R, ? super C, ? super V> consumer3) {
        for (Cell<R, C, V> cell : this) {
            consumer3.accept(cell.getRowKey(), cell.getColumnKey(), cell.getValue());
        }
    }
}
