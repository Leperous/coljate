package net.ollie.coljate.map;

/**
 *
 * @author Ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class MutableUnbalancedTreeMapTest extends MutableSortedMapTest {

    @Override
    protected <V> MutableUnbalancedTreeMap<Integer, V> create() {
        return MutableUnbalancedTreeMap.create();
    }

}
