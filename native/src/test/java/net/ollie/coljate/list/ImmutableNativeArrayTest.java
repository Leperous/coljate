package net.ollie.coljate.list;

/**
 *
 * @author ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class ImmutableNativeArrayTest extends ImmutableArrayTest {

    @Override
    public ImmutableArray<Object> createFrom(Object... objects) {
        return ImmutableNativeArray.of(objects);
    }

}
