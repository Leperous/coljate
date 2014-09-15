package net.ollie.coljate.lists;

/**
 *
 * @author Ollie
 */
@SuppressWarnings({"rawtypes", "unchecked", "null"})
public class ImmutableArrayTest extends AbstractImmutableArrayTest {

    @Override
    protected Array.Immutable<Object> create(final Object... objects) {
        return ImmutableArray.copy(objects);
    }

}
