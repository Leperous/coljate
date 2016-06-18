package net.ollie.coljate.map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Test;

/**
 *
 * @author Ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class MutableUnbalancedTreeMapTest extends MutableMapTest {

    @Override
    protected <V> MutableUnbalancedTreeMap<Integer, V> create() {
        return MutableUnbalancedTreeMap.create();
    }

    @Test
    public void testMinMaxKey() {
        final MutableUnbalancedTreeMap<Integer, Object> map = this.create();
        map.put(3, new Object());
        map.put(2, new Object());
        map.put(4, new Object());
        assertThat(map.minKey(), is(2));
        assertThat(map.maxKey(), is(4));
    }

}
