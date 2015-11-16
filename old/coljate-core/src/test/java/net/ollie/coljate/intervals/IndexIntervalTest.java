package net.ollie.coljate.intervals;

import net.ollie.coljate.utils.numeric.NonNegativeInteger;
import static net.ollie.coljate.utils.numeric.NonNegativeInteger.INFINITY;
import static net.ollie.coljate.utils.numeric.NonNegativeInteger.ONE;
import static net.ollie.coljate.utils.numeric.NonNegativeInteger.ZERO;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Ollie
 */
@SuppressWarnings({"rawtypes", "unchecked", "null"})
public class IndexIntervalTest {

    private static final NonNegativeInteger TWO = NonNegativeInteger.of(2);

    public IndexIntervalTest() {
    }

    @Test
    public void testLessThan() {

        final IndexInterval interval = IndexInterval.lessThan(TWO);
        assertThat(interval.count(), is(TWO));
        assertFalse(interval.contains(TWO));
        assertTrue(interval.contains(ONE));
        assertTrue(interval.contains(ZERO));
        assertFalse(interval.isEmpty());

    }

    @Test
    public void testGreaterThan() {

        final IndexInterval interval = IndexInterval.greaterThan(TWO);
        assertThat(interval.count(), is(INFINITY));
        assertFalse(interval.isEmpty());
        assertTrue(interval.contains(INFINITY));

    }

}
