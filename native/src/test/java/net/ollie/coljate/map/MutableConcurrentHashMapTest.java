package net.ollie.coljate.map;

/**
 *
 * @author ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class MutableConcurrentHashMapTest extends MutableMapTest {

    @Override
    protected <V> MutableMap<Integer, V> create() {
        return MutableConcurrentHashMap.create();
    }

}
