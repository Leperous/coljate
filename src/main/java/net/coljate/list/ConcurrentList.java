package net.coljate.list;

import java.util.Spliterator;
import java.util.Spliterators;

import net.coljate.collection.ConcurrentCollection;

/**
 *
 * @author ollie
 */
public interface ConcurrentList<T>
        extends MutableList<T>, ConcurrentCollection<T> {

    @Override
    ConcurrentList<T> mutableCopy();

    @Override
    default Spliterator<T> spliterator() {
        return Spliterators.spliterator(this.iterator(), this.count(), Spliterator.ORDERED | Spliterator.CONCURRENT);
    }

}
