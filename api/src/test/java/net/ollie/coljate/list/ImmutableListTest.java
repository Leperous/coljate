package net.ollie.coljate.list;

import org.checkerframework.checker.nullness.qual.NonNull;

/**
 *
 * @author Ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public abstract class ImmutableListTest extends ListTest {

    @NonNull
    @Override
    protected abstract ImmutableList<Object> create(Object... objects);

    @Override
    protected ImmutableList<Object> empty() {
        return this.create();
    }

}
