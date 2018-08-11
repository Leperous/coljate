package net.coljate.map.impl;

import net.coljate.map.AbstractMap;
import net.coljate.map.MutableEntry;
import net.coljate.map.MutableMap;
import net.coljate.set.Set;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

public class StringTrie<V>
        extends AbstractMap<String, V>
        implements MutableMap<String, V> {

    private TrieEntry<V> root = null;

    @CheckForNull
    @Override
    public V put(final String key, final V value) {
        Objects.requireNonNull(key);
        if (root == null) {
            root = new TrieEntry<>();
        }
        return root.put(key, value);
    }

    @Override
    public boolean remove(@Nullable final Object key, @Nullable final Object value) {
        Objects.requireNonNull(key);
        if (root == null || !(key instanceof String)) {
            return false;
        }
        return root.remove((String) key, value);
    }

    @CheckForNull
    @Override
    public V get(@Nullable final String key) {
        if (root == null) {
            return null;
        }
        return root.get(key);
    }

    @CheckForNull
    @Override
    public MutableEntry<String, V> getEntry(@Nullable final Object key) {
        return ViewEntry.viewOf(key, this);
    }

    @Nonnull
    @Override
    public Set<String> keys() {
        throw new UnsupportedOperationException(); //TODO
    }

    private static class TrieEntry<V> {

        private final MutableMap<Character, TrieEntry<V>> children = MutableMap.createHashMap();

        private boolean end;
        private V value;

        private TrieEntry() {
        }

        V get(final String key) {
            TrieEntry<V> entry = this;
            for (int i = 0; i < key.length(); i++) {
                final char c = key.charAt(i);
                entry = entry.children.get(c);
                if (entry == null) {
                    return null;
                }
            }
            return entry.end ? entry.value : null;
        }

        V put(final String key, final V value) {
            TrieEntry<V> entry = this;
            for (int i = 0; i < key.length(); i++) {
                final char c = key.charAt(i);
                entry = entry.children.computeIfAbsent(c, cc -> new TrieEntry<>());
            }
            final V previous = entry.value;
            entry.end = true;
            entry.value = value;
            return previous;
        }

        boolean remove(final String key, final Object value) {
            TrieEntry<V> entry = this;
            for (int i = 0; i < key.length(); i++) {
                final char c = key.charAt(i);
                entry = entry.children.get(c);
                if (entry == null) {
                    return false;
                }
            }
            if (entry.end && Objects.equals(entry.value, value)) {
                entry.value = null;
                entry.end = false;
                return true;
            }
            return false;
        }

    }

}
