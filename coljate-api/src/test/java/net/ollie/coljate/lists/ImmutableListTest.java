package net.ollie.coljate.lists;

import org.checkerframework.checker.nullness.qual.NonNull;

import net.ollie.coljate.list.ImmutableList;

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
