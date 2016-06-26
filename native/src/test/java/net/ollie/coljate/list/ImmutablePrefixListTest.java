package net.ollie.coljate.list;

/**
 *
 * @author ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class ImmutablePrefixListTest extends ImmutableListTest {

    @Override
    public ImmutableList<Object> createFrom(Object... objects) {
        if (objects.length == 0) {
            return ImmutableEmptyArray.get();
        }
        ImmutablePrefixList<Object> list = ImmutablePrefixList.of(objects[0]);
        for (int i = 1; i < objects.length; i++) {
            list = list.suffixed(objects[i]);
        }
        return list;
    }

}
