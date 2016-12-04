package net.coljate.collection;

/**
 *
 * @author ollie
 */
public interface LazyCollection<T> extends Collection<T> {

    default Collection<? extends T> evaluate() {
        return this.immutableCopy();
    }

}
