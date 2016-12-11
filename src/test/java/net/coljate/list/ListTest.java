package net.coljate.list;

import org.junit.Test;

import net.coljate.collection.CollectionTest;
import net.coljate.list.lazy.LazyTransformedList;
import net.coljate.map.Map;
import net.coljate.map.MutableMap;

/**
 *
 * @author Ollie
 */
public interface ListTest<T> extends CollectionTest<T> {

    @Override
    List<T> create(java.util.List<T> elements);

    @Override
    default List<T> create(final T element) {
        return this.create(singletonList(element));
    }

}
