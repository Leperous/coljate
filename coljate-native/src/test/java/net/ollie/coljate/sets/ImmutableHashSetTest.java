package net.ollie.coljate.sets;

/**
 *
 * @author Ollie
 */
@SuppressWarnings({"rawtypes", "unchecked", "null"})
public class ImmutableHashSetTest extends AbstractImmutableSetTest {

    @Override
    protected Set.Immutable<Object> create(final Object... objects) {
        return ImmutableHashSet.create(objects);
    }

}
