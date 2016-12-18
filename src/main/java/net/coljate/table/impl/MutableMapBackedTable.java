package net.coljate.table.impl;

import net.coljate.map.MutableMap;
import net.coljate.table.MutableTable;

/**
 *
 * @author ollie
 */
public class MutableMapBackedTable<R, C, V>
        extends MapBackedTable<R, C, V>
        implements MutableTable<R, C, V> {

    private final MutableMap<KeyPair<R, C>, V> map;

    protected MutableMapBackedTable(final MutableMap<KeyPair<R, C>, V> map) {
        super(map);
        this.map = map;
    }

    @Override
    public V put(final R rowKey, C columnKey, V value) {
        return map.put(new KeyPair<>(rowKey, columnKey), value);
    }

    @Override
    public boolean remove(final Object rowKey, final Object columnKey, final Object value) {
        return map.remove(new KeyPair<>(rowKey, columnKey), value);
    }

    @Override
    public void clear() {
        map.clear();
    }

}
