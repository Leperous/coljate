package net.coljate.map;

import java.util.Objects;

import net.coljate.collection.Collection;

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

    interface TreeSearch {

        <K, V> Entry<K, V> get(Object key, Tree<K, V> tree);

        TreeSearch DEPTH_FIRST_RECURSIVE = new TreeSearch() {

            @Override
            public <K, V> Entry<K, V> get(final Object key, final Tree<K, V> tree) {
                final Entry<K, V> root = tree.root();
                if (root == null || Objects.equals(key, root.key())) {
                    return root;
                }
                for (final Tree<K, V> subtree : tree.subtrees(key)) {
                    final Entry<K, V> found = this.get(key, subtree);
                    if (found != null) {
                        return found;
                    }
                }
                return null;
            }

        };

    }

}
