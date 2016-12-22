package net.coljate.map;

import net.coljate.collection.Collection;
import net.coljate.util.Functions;

/**
 *
 * @author ollie
 */
public interface ImmutableTree<K, V> extends Tree<K, V>, ImmutableMap<K, V> {

    @Override
    ImmutableEntry<K, V> root();

    @Override
    Collection<? extends ImmutableTree<K, V>> subtrees(Object key);

    @Override
    default ImmutableEntry<K, V> entry(final Object key) {
        return Functions.ifNonNull(Tree.super.entry(key), Entry::immutableCopy);
    }

    @Override
    @Deprecated
    default ImmutableTree<K, V> immutableCopy() {
        return this;
    }

}
