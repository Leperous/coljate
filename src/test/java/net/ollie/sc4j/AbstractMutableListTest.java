package net.ollie.sc4j;

import java.util.Arrays;
import org.junit.Test;

/**
 *
 * @author Ollie
 */
public abstract class AbstractMutableListTest<C extends List.Mutable<Object>>
        extends AbstractCollectionTest<C> {
    
    @Test
    public void shouldPrefix() {
        final Object o1 = new Object(), o2 = new Object();
        final C list = this.create(o1);
        list.prefix(o2);
        assertContains(list, o2, o1);
    }

    @Test
    public void shouldPrefixAll() {
        final Object o1 = new Object(), o2 = new Object(), o3 = new Object();
        final C list = this.create(o1);
        list.prefixAll(Arrays.asList(o2, o3));
        assertContains(list, o2, o3, o1);
    }

    @Test
    public void shouldSuffix() {
        final Object o1 = new Object(), o2 = new Object();
        final C list = this.create(o1);
        list.suffix(o2);
        assertContains(list, o1, o2);
    }

    @Test
    public void shouldSuffixAll() {
        final Object o1 = new Object(), o2 = new Object(), o3 = new Object();
        final C list = this.create(o1);
        list.suffixAll(Arrays.asList(o2, o3));
        assertContains(list, o1, o2, o3);
    }

}
