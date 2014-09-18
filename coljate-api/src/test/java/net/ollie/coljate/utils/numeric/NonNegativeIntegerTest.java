package net.ollie.coljate.utils.numeric;

import java.math.BigInteger;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
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

    @Test
    public void shouldMaybeBigInteger() {
        assertNull(NonNegativeInteger.maybe(BigInteger.valueOf(-1)));
        assertNotNull(NonNegativeInteger.maybe(BigInteger.valueOf(0)));
        assertNotNull(NonNegativeInteger.maybe(BigInteger.valueOf(1)));
    }

}
