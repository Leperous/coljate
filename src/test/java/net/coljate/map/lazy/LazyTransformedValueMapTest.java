package net.coljate.map.lazy;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

import net.coljate.map.Map;

/**
 *
 * @author Ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class LazyTransformedValueMapTest {

    @Test
    public void testTransformValues() {

        final Object key = new Object();
        final Object v1 = new Object();

        final Map<Object, Object> map = Map.of(key, v1);
        final LazyMap<Object, Integer> lazyMap = map.lazily(LazyTransformedValueMap.transform(o -> o.hashCode()));

        assertThat(lazyMap.count(), is(1));
        assertThat(lazyMap.get(key), is(v1.hashCode()));

    }

}
