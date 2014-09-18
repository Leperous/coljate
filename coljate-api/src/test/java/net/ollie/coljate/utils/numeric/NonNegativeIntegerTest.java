package net.ollie.coljate.utils.numeric;

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
public class NonNegativeIntegerTest {

    @Test
    public void shouldCreateZero() {
        final NonNegativeInteger i = NonNegativeInteger.of(0);
        assertTrue(i.isZero());
        assertThat(i.intValue(), is(0));
    }

    @Test
    public void shouldCreateOne() {
        final NonNegativeInteger i = NonNegativeInteger.of(1);
        assertFalse(i.isZero());
        assertThat(i.intValue(), is(1));
    }

}
