package net.coljate.tree;

import net.coljate.tree.search.TreeSearch;

import java.util.Objects;

import net.coljate.collection.Collection;
import net.coljate.map.Entry;
import net.coljate.map.Map;

/**
 *
 * @author ollie
 */
public interface Tree<K, V> extends Map<K, V> {

    Entry<K, V> root();

    Collection<? extends Tree<K, V>> subtrees(Object key);

    @Override
    default Entry<K, V> entry(final Object key) {
        return this.entry(key, TreeSearch.DEPTH_FIRST_RECURSIVE);
    }

    default Entry<K, V> entry(final Object key, final TreeSearch search) {
        return search.get(key, this);
    }

    @Override
    MutableTree<K, V> mutableCopy();

    @Override
    ImmutableTree<K, V> immutableCopy();


}
