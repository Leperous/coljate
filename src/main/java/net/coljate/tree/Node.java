package net.coljate.tree;

import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.Nonnull;

import net.coljate.map.Entry;
import net.coljate.set.Set;
import net.coljate.tree.navigation.TreeNavigation;
import net.coljate.util.SelfTyped;

/**
 *
 * @author ollie
 */
public interface Node<K, V, N extends Node<K, V, N>>
        extends Entry<K, V>, SelfTyped<N> {

    @Nonnull
    Set<? extends N> children();

    default boolean hasChildren() {
        return !this.children().isEmpty();
    }

    default int countDescendents() {
        return this.countDescendents(TreeNavigation.getDefault());
    }

    default int countDescendents(final TreeNavigation treeNavigation) {
        final AtomicInteger counter = new AtomicInteger(1);
        treeNavigation.collect((Node) this, node -> counter.incrementAndGet());
        return counter.get();
    }

}
