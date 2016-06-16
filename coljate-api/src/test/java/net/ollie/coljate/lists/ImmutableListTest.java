package net.ollie.coljate.lists;

import net.ollie.coljate.list.ImmutableList;

import org.checkerframework.checker.nullness.qual.NonNull;

/**
 *
 * @author Ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public abstract class ImmutableListTest extends ListTest {

    @NonNull
    protected abstract ImmutableList<Object> create(Object... objects);

    protected ImmutableList<Object> empty() {
        return this.create();
    }

}
