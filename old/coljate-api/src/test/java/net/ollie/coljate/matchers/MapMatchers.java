package net.ollie.coljate.matchers;

import net.ollie.coljate.maps.Map;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

/**
 *
 * @author Ollie
 */
public class MapMatchers {

    protected MapMatchers() {
    }

    public static <M extends Map<?, ?>> org.hamcrest.Matcher<M> containsKey(final Object key) {
        return new BaseMatcher<M>() {

            @Override
            public boolean matches(final Object item) {
                return item instanceof Map && ((Map) item).containsKey(key);
            }

            @Override
            public void describeTo(final Description description) {
                description.appendText("Contains key: ").appendValue(key);
            }

        };
    }

}
