package net.coljate.tree.impl;

import java.io.Serializable;
import java.util.function.Predicate;

import net.coljate.UnmodifiableIterator;
import net.coljate.collection.Collection;
import net.coljate.collection.Empty;
import net.coljate.map.Entry;
import net.coljate.map.ImmutableEntry;
import net.coljate.tree.AbstractTree;
import net.coljate.tree.ImmutableTree;
import net.coljate.tree.Tree;

/**
 *
 * @author ollie
 */
public class EmptyTree<K, V>
        extends AbstractTree<K, V, ImmutableEntry<K, V>>
        implements Empty<Entry<K, V>>, ImmutableTree<K, V, ImmutableEntry<K, V>>, Serializable {

    private static final long serialVersionUID = 1L;
    private static final EmptyTree INSTANCE = new EmptyTree();

    public static <K, V> EmptyTree<K, V> instance() {
        return INSTANCE;
    }

    @Override
    public ImmutableEntry<K, V> root() {
        return null;
    }

    @Override
    public Collection<? extends ImmutableTree<K, V, ImmutableEntry<K, V>>> subtrees(Object key) {
        return Collection.of();
    }

    @Override
    public SingletonTree<K, V> with(final K key, final V value) {
        return Tree.of(key, value);
    }

    @Override
    @Deprecated
    public EmptyTree<K, V> filter(final Predicate<? super Entry<K, V>> predicate) {
        return this;
    }

    @Override
    public UnmodifiableIterator<Entry<K, V>> iterator() {
        return Empty.super.iterator();
    }

    @Override
    public boolean contains(Object object) {
        return Empty.super.contains(object);
    }

}
