package net.ollie.coljate.list;

import org.checkerframework.checker.nullness.qual.NonNull;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

/**
 *
 * @author Ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public abstract class ImmutableListTest extends ListTest {

    @NonNull
    @Override
    public abstract ImmutableList<Object> createFrom(final Object... objects);

    @Override
    public ImmutableList<Object> create() {
        return this.createFrom();
    }

    @Override
    public ImmutableList<Object> create(final Object object) {
        return this.createFrom(object);
    }

    @Override
    public ImmutableList<Object> create(final Object o1, final Object o2) {
        return this.createFrom(o1, o2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testEmpty_Get() {
        this.create().get(0);
    }

    @Test
    public void testSingleton_Get() {
        final Object object = this.randomValue();
        final ImmutableList<Object> create = this.create(object);
        assertThat(create.get(0), is(object));
    }

    @Test
    public void testDual_Get() {
        final Object first = this.randomValue(), second = this.randomValue();
        final ImmutableList<Object> list = this.create(first, second);
        assertThat(list.get(0), is(first));
        assertThat(list.get(1), is(second));
    }

}
