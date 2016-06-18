package net.ollie.coljate.map;

/**
 *
 * @author Ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class MutableWrappedHashMapTest extends MutableMapTest {

    @Override
    protected MutableMap<Integer, Object> create() {
        return MutableWrappedHashMap.create();
    }

}
