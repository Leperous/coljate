package net.coljate.table.impl;

import java.util.Iterator;
import java.util.Objects;

import net.coljate.map.Entry;
import net.coljate.map.Map;
import net.coljate.table.Cell;
import net.coljate.table.Table;
import net.coljate.util.Iterators;

/**
 *
 * @author ollie
 */
public class MapBackedTable<R, C, V> implements Table<R, C, V> {

    private final Map<KeyPair<R, C>, V> map;

    protected MapBackedTable(final Map<KeyPair<R, C>, V> map) {
        this.map = map;
    }

    @Override
    public boolean contains(final Object row, final Object column) {
        return map.containsKey(new KeyPair<>(row, column));
    }

    @Override
    public Cell<R, C, V> cellIfPresent(final Object row, final Object column) {
        final Entry<KeyPair<R, C>, V> entry = map.entry(new KeyPair<>(row, column));
        return entry == null ? null : new EntryBackedCell<>(entry);
    }

    @Override
    public Iterator<Cell<R, C, V>> iterator() {
        return Iterators.transform(map.iterator(), EntryBackedCell::new);
    }

    protected final class KeyPair<R, C> {

        private final R rowKey;
        private final C columnKey;

        KeyPair(final R rowKey, final C columnKey) {
            this.rowKey = rowKey;
            this.columnKey = columnKey;
        }

        R rowKey() {
            return rowKey;
        }

        C columnKey() {
            return columnKey;
        }

        @Override
        public int hashCode() {
            int hash = 3;
            hash = 37 * hash + Objects.hashCode(this.rowKey);
            hash = 37 * hash + Objects.hashCode(this.columnKey);
            return hash;
        }

        @Override
        public boolean equals(final Object object) {
            return object instanceof KeyPair
                    && this.equals((KeyPair<?, ?>) object);
        }

        protected boolean equals(final KeyPair<?, ?> that) {
            return Objects.equals(rowKey, that.rowKey)
                    && Objects.equals(columnKey, that.columnKey);
        }

    }

    protected class EntryBackedCell<R, C, V> implements Cell<R, C, V> {

        private final KeyPair<R, C> keys;
        private final V value;

        public EntryBackedCell(final Entry<KeyPair<R, C>, V> entry) {
            this(entry.key(), entry.value());
        }

        protected EntryBackedCell(final KeyPair<R, C> keys, final V value) {
            this.keys = keys;
            this.value = value;
        }

        @Override
        public R rowKey() {
            return keys.rowKey();
        }

        @Override
        public C columnKey() {
            return keys.columnKey();
        }

        @Override
        public V value() {
            return value;
        }

    }

}
