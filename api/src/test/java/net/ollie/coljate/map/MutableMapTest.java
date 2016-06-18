package net.ollie.coljate.map;

import org.hamcrest.BaseMatcher;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Ollie
 */
public abstract class MutableMapTest {

    protected abstract <V> MutableMap<Integer, V> create();

    @Test
    public void testEmpty() {
        assertThat(this.create(), isEmptyMap());
    }

    @Test
    public void testSingleton() {
        final MutableMap<Integer, Object> map = this.create();
        final Object value = new Object();
        //When
        map.put(1, value);
        //Then
        assertThat(map, not(isEmptyMap()));
        assertThat(map.get(1), is(value));
        assertTrue(map.containsKey(1));
        assertFalse(map.containsKey(2));
        assertThat(map.count(), is(1));
    }

    @Test
    public void testPutRemove() {
        final MutableMap<Integer, Object> map = this.create();
        map.put(1, new Object());
        map.delete(1);
        assertThat(map, isEmptyMap());
    }

    @Test
    public void testSingleton_Equality() {
        final Object value = new Object();
        final MutableMap<Integer, Object> m1 = this.create();
        m1.put(1, value);
        final MutableMap<Integer, Object> m2 = this.create();
        m2.put(1, value);
        assertThat(m1, is(m2));
        assertThat(m2, is(m1));
        assertThat(m1.hashCode(), is(m2.hashCode()));
    }

    protected static Matcher<Map<?, ?>> isEmptyMap() {
        return new BaseMatcher<Map<?, ?>>() {

            @Override
            public boolean matches(final Object object) {
                return object instanceof Map
                        && this.matches((Map) object);
            }

            boolean matches(final Map<?, ?> map) {
                return map.isEmpty() && map.count() == 0;
            }

            @Override
            public void describeTo(final Description d) {
                d.appendText("Empty map");
            }

        };
    }

}
