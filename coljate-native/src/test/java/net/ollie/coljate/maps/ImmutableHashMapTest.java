package net.ollie.coljate.maps;

import net.ollie.coljate.AbstractImmutableMapTest;
import net.ollie.coljate.Map;

/**
 *
 * @author Ollie
 */
@SuppressWarnings({"rawtypes", "unchecked", "null"})
public class ImmutableHashMapTest extends AbstractImmutableMapTest<Map.Immutable<Object, Object>> {

    @Override
    protected Map.Immutable<Object, Object> create(final Iterable<Map.Entry<Object, Object>> entries) {
        Map.Immutable<Object, Object> map = ImmutableHashMap.create();
        for (final Map.Entry<Object, Object> entry : entries) {
            map = map.with(entry.key(), entry.value());
        }
        return map;
    }

}
