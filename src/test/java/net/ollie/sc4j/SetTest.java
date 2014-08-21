package net.ollie.sc4j;

import org.junit.Test;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 *
 * @author Ollie
 */
@SuppressWarnings({"rawtypes", "unchecked", "null"})
public class SetTest {

    public SetTest() {
    }

    @Test
    public void testInliner() {

        final Set.Mutable<Object> mockSet = mock(Set.Mutable.class);
        final Set.Mutable.Inliner<Object> inliner = new Set.Mutable.Inliner<>(mockSet);

        final Object object = new Object();
        inliner.add(object);
        verify(mockSet, times(1)).add(eq(object));
        verifyNoMoreInteractions(mockSet);

        inliner.remove(object);
        verify(mockSet, times(1)).remove(eq(object));
        verifyNoMoreInteractions(mockSet);

    }

}
