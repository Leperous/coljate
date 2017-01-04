package net.coljate.tree.navigation;

import java.util.function.Predicate;

import net.coljate.list.MutableList;
import net.coljate.tree.Node;

/**
 *
 * @author ollie
 * @since 1.0
 */
public class DepthFirstRecursiveTreeNavigation implements TreeNavigation {

    protected DepthFirstRecursiveTreeNavigation() {
    }

    @Override
    public <N extends Node<?, ?, N>> N first(final N node, final Predicate<? super N> predicate) {
        if (node == null || predicate.test(node)) {
            return node;
        }
        for (final N child : node.children()) {
            final N found = this.first(child, predicate);
            if (found != null) {
                return found;
            }
        }
        return null;
    }

    @Override
    public <N extends Node<?, ?, N>> void collect(
            final N node,
            final MutableList<N> list,
            final Predicate<? super N> predicate) {
        if (node != null) {
            if (predicate.test(node)) {
                list.suffix(node);
            }
            for (final N child : node.children()) {
                this.collect(child, list, predicate);
            }
        }
    }

}
