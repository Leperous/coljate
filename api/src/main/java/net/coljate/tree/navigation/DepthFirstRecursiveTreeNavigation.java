package net.coljate.tree.navigation;

import java.util.function.Consumer;
import java.util.function.Predicate;

import net.coljate.tree.TreeNode;
import net.coljate.tree.BinaryTreeMapNode;

/**
 *
 * @author Ollie
 * @since 1.0
 */
public class DepthFirstRecursiveTreeNavigation implements TreeNavigation {

    protected DepthFirstRecursiveTreeNavigation() {
    }

    @Override
    public <N extends TreeNode<N>> N first(final N node, final Predicate<? super N> predicate) {
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
    public <N extends TreeNode<N>> void collect(
            final N node,
            final Consumer<? super N> consumer,
            final Predicate<? super N> predicate) {
        if (node != null) {
            if (predicate.test(node)) {
                consumer.accept(node);
            }
            for (final N child : node.children()) {
                this.collect(child, consumer, predicate);
            }
        }
    }

    @Override
    public <N extends BinaryTreeMapNode<?, ?, N>> void collectBinaryNodes(
            final N node,
            final Consumer<? super N> consumer,
            final Predicate<? super N> predicate) {
        if (node != null) {
            if (predicate.test(node)) {
                consumer.accept(node);
            }
            this.collectBinaryNodes(node.left(), consumer, predicate);
            this.collectBinaryNodes(node.right(), consumer, predicate);
        }
    }

}
