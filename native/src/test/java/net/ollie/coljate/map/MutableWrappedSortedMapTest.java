package net.ollie.coljate.map;

/**
 *
 * @author Ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class MutableWrappedSortedMapTest extends MutableSortedMapTest {

    @Override
    protected <V> MutableWrappedSortedMap<Integer, V> create() {
        return MutableWrappedSortedMap.create();
    }

}
