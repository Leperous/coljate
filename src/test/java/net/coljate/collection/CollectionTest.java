package net.coljate.collection;

import net.coljate.Tests;

/**
 *
 * @author ollie
 */
public interface CollectionTest<T> extends Tests<T> {

    Collection<T> create(java.util.List<T> elements);

    default Collection<T> create() {
        return this.create(emptyList());
    }

    default Collection<T> create(final T element) {
        return this.create(singletonList(element));
    }

    default <T> java.util.List<T> emptyList() {
        return java.util.Collections.emptyList();
    }

    default <T> java.util.List<T> singletonList(final T element) {
        return java.util.Collections.singletonList(element);
    }

}
