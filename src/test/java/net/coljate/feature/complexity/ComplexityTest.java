package net.coljate.feature.complexity;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class ComplexityTest {

    @Test
    public void testTimes_Unknown() {
        assertThat(Complexity.UNKNOWN.times(Complexity.LINEAR), is(Complexity.UNKNOWN));
    }

    @Test
    public void testTimes_Constant() {
        assertThat(Complexity.CONSTANT.times(Complexity.CONSTANT), is(Complexity.CONSTANT));
    }

    @Test
    public void testTimes_Linear() {
        assertThat(Complexity.LINEAR.times(Complexity.LINEAR), is(Complexity.QUADRATIC));
        assertThat(Complexity.LINEAR.times(Complexity.UNKNOWN), is(Complexity.UNKNOWN));
    }

    @Test
    public void testTimes_Polynominal() {
        assertThat(Complexity.POLYNOMIAL.times(Complexity.POLYNOMIAL), is(Complexity.POLYNOMIAL));
    }

}
