package net.ollie.coljate.map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

/**
 *
 * @author Ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class MutableWrappedHashMapTest extends MutableMapTest {

    @Override
    protected <V> MutableMap<Integer, V> create() {
        return MutableWrappedHashMap.create();
    }

    @Test
    public void testToString() {
        final MutableMap<Integer, String> map = this.create();
        map.put(1, "two");
        assertThat(map.toString(), is("MutableWrappedHashMap: [1=two]"));
    }

}
