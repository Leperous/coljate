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
    public <N extends Node<?, ?, N>> N findNode(final N node, final Predicate<? super N> predicate) {
        if (node == null || predicate.test(node)) {
            return node;
        }
        for (final N child : node.children()) {
            final N found = this.findNode(child, predicate);
            if (found != null) {
                return found;
            }
        }
        return null;
    }

    @Override
    public <N extends Node<?, ?, N>> void collect(
            final N node,
            final MutableList<N> list) {
        if (node != null) {
            list.suffix(node);
            for (final N child : node.children()) {
                this.collect(child, list);
            }
        }
    }

}
