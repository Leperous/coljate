package net.ollie.sc4j.maps;

import net.ollie.sc4j.AbstractMutableMapTest;
import net.ollie.sc4j.Map;

/**
 *
 * @author Ollie
 */
public class MutableWrappedHashMapTest
        extends AbstractMutableMapTest<Map.Mutable<Object, Object>> {

    @Override
    protected Map.Mutable<Object, Object> create(final Object... objects) {
        final Map.Mutable<Object, Object> map = MutableWrappedHashMap.create(1);
        for (final Object object : objects) {
            map.put(object, object);
        }
        return map;
    }

}
