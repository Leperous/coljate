package net.coljate.set.lazy;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

import net.coljate.set.Set;

/**
 *
 * @author Ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class LazyIntersectionSetTest {

    @Test
    public void testIntersection() {

        final Set<Integer> intersection = LazyIntersectionSet.of(Set.of(3, 5), Set.of(5, 7));
        assertThat(intersection, instanceOf(LazySet.class));

    }

}
