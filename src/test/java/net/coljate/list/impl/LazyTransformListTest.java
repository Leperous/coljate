package net.coljate.list.impl;

import net.coljate.list.lazy.LazyTransformedList;

import java.util.function.Function;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

import net.coljate.ObjectContainerTest;
import net.coljate.collection.Collection;
import net.coljate.list.Array;
import net.coljate.list.lazy.LazyList;
import net.coljate.list.List;
import net.coljate.map.Map;

/**
 *
 * @author Ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class LazyTransformListTest extends ObjectContainerTest {

    @Test
    public void testLazyTransform_Singleton() {

        final Object object = this.createObject();
        final Integer value = 123;

        final List<Object> list = Array.copyOf(object);
        final Map<Object, Integer> mapped = Map.of(object, value);
        final LazyList<Integer> lazyList = list.lazily(LazyTransformedList.transform(mapped::get));

        assertThat(lazyList.count(), is(list.count()));
        assertThat(lazyList.first(), is(value));

        final List<Integer> frozen = lazyList.evaluate();
        assertThat(frozen.first(), is(value));

    }

}
