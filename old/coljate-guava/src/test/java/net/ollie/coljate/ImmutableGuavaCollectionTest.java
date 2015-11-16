package net.ollie.coljate;

/**
 *
 * @author Ollie
 */
@SuppressWarnings({"rawtypes", "unchecked", "null"})
public class ImmutableGuavaCollectionTest extends AbstractCollectionTest<ImmutableGuavaCollection<Object>> {

    @Override
    protected ImmutableGuavaCollection<Object> create(final Object... objects) {
        return ImmutableGuavaCollection.create(objects);
    }

}
