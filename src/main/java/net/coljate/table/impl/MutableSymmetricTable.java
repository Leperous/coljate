package net.coljate.table.impl;

import net.coljate.map.MutableMap;
import net.coljate.table.MutableTable;

/**
 *
 * @author ollie
 */
public class MutableSymmetricTable<K, V>
        extends SymmetricTable<K, V>
        implements MutableTable<K, K, V> {

    public static <K, V> MutableSymmetricTable<K, V> createHashBackedTable() {
        return new MutableSymmetricTable<>(MutableMap.createHashMap());
    }

    private final MutableMap<OneOrTwoSet<K>, V> map;

    protected MutableSymmetricTable(final MutableMap<OneOrTwoSet<K>, V> map) {
        super(map);
        this.map = map;
    }

    @Override
    public V put(K rowKey, K columnKey, V value) {
        return map.put(new OneOrTwoSet<>(rowKey, columnKey), value);
    }

    @Override
    public boolean remove(final Object rowKey, final Object columnKey, final Object value) {
        return map.remove(new OneOrTwoSet<>(rowKey, columnKey), value);
    }

    @Override
    public void clear() {
        map.clear();
    }

}
