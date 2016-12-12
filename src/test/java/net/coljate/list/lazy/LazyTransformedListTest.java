package net.coljate.list.lazy;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

import net.coljate.ObjectContainerTest;
import net.coljate.list.Array;
import net.coljate.list.List;
import net.coljate.map.Map;

/**
 *
 * @author Ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class LazyTransformedListTest extends ObjectContainerTest {

    @Test
    public void testLazyTransform_Singleton() {

        final Object object = this.createObject();
        final Integer value = 123;

        final List<Object> list = Array.copyOf(object);
        final Map<Object, Integer> mapped = Map.of(object, value);
        final LazyList<Integer> lazyList = new LazyTransformedList<>(list, mapped::get);

        assertThat(lazyList.count(), is(list.count()));
        assertThat(lazyList.first(), is(value));

        final List<Integer> frozen = lazyList.immutableCopy();
        assertThat(frozen.first(), is(value));

    }

}
