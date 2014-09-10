package net.ollie.coljate.maps;

import net.ollie.coljate.maps.MutableWrappedHashMap;
import net.ollie.coljate.AbstractMutableMapTest;
import net.ollie.coljate.Map;

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
