package net.ollie.coljate.utils.functions;

import java.util.Objects;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

/**
 *
 * @author Ollie
 */
@SuppressWarnings({"rawtypes", "unchecked", "null"})
public class FirstTest {

    @Test
    public void testNonNull() {
        final Object o1 = new Object(), o2 = new Object();
        final Object out = First.of(() -> null)
                .or(() -> null)
                .or(() -> o1)
                .or(() -> o2)
                .or(() -> null)
                .nonNull();
        assertThat(out, is(o1));
    }

    @Test
    public void testPredicate() {
        final Object o1 = new Object(), o2 = new Object();
        final Object out = First.of(() -> null)
                .or(() -> null)
                .or(() -> o1)
                .or(() -> o2)
                .or(() -> null)
                .apply(o -> Objects.equals(o1, o));
        assertThat(out, is(o1));
    }

}
