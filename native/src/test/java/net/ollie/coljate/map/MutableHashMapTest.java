package net.ollie.coljate.map;

/**
 *
 * @author Ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class MutableHashMapTest extends MutableMapTest {

    @Override
    protected <V> MutableMap<Integer, V> create() {
        return MutableHashMap.create();
    }

}
