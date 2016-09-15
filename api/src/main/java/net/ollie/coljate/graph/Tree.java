package net.ollie.coljate.graph;

import java.util.Objects;
import java.util.Optional;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import net.ollie.coljate.Collection;
import net.ollie.coljate.map.KeyValue;
import net.ollie.coljate.theory.Associative;

/**
 *
 * @author ollie
 */
public interface Tree<@NonNull K, @Nullable V>
        extends Associative<K, V> {

    @CheckForNull
    Node<K, V> root();

    default boolean isEmpty() {
        return this.root() == null;
    }

    @Override
    default V get(final K key) {
        final Node<K, V> root = this.root();
        return root == null ? null : root.get(key);
    }

    @Override
    default boolean containsKey(final K key) {
        final Node<K, V> root = this.root();
        return root != null && root.containsKey(key);
    }

    @Override
    default boolean containsValue(final V value) {
        final Node<K, V> root = this.root();
        return root != null && root.containsValue(value);
    }

    default int count() {
        final Node<K, V> root = this.root();
        return root == null ? 0 : 1 + root.descendants();
    }

    interface Node<@NonNull K, @Nullable V> extends KeyValue<K, V>, Associative<K, V> {

        @Nonnull
        Collection<? extends Node<K, V>> children();

        @Override
        default V get(final K key) {
            return this.find(key).map(Node<K, V>::value).orElse(null);
        }

        default Optional<? extends Node<K, V>> find(final K key) {
            return Objects.equals(key, this.key())
                    ? Optional.of(this)
                    : this.children().first(child -> child.find(key).isPresent()); //FIXME
        }

        @Override
        default boolean containsKey(final K key) {
            return Objects.equals(key, this.key())
                    || this.children().any(child -> child.containsKey(key));
        }

        @Override
        default boolean containsValue(final V value) {
            return Objects.equals(value, this.value())
                    || this.children().any(child -> child.containsValue(value));
        }

        default int descendants() {
            int count = 0;
            for (final Node<K, V> child : this.children()) {
                count += 1 + child.descendants();
            }
            return count;
        }

    }

}
