package net.ollie.coljate.list;

/**
 *
 * @author Ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class MutableWrappedStackTest extends MutableListTest {

    @Override
    protected MutableWrappedStack<Object> createFrom(final Object... objects) {
        final MutableWrappedStack<Object> stack = new MutableWrappedStack();
        for (final Object object : objects) {
            stack.add(object);
        }
        return stack;
    }

}
