package net.coljate.collection;

import java.util.Spliterator;
import java.util.Spliterators;

import net.coljate.util.iterator.UnmodifiableIterator;
import net.coljate.collection.impl.EmptyCollection;
import net.coljate.collection.impl.SingletonCollection;
import net.coljate.set.Set;

/**
 *
 * @author ollie
 */
public interface ImmutableCollection<T> extends Collection<T> {

    @Override
    UnmodifiableIterator<T> iterator();

    @Deprecated
    @Override
    default ImmutableCollection<T> immutableCopy() {
        return this;
    }

    @Override
    default Spliterator<T> spliterator() {
        return Spliterators.spliterator(this.iterator(), this.count(), Spliterator.SIZED | Spliterator.IMMUTABLE);
    }

    static <T> EmptyCollection<T> of() {
        return EmptyCollection.instance();
    }

    static <T> SingletonCollection<T> of(final T element) {
        return Set.of(element);
    }

}
