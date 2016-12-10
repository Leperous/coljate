package net.coljate.set.impl;

import net.coljate.set.MutableSet;
import net.coljate.set.MutableSetTest;
import net.coljate.util.Arrays;

/**
 *
 * @author ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class TrieTest extends MutableSetTest {

    @Override
    protected String createObject() {
        return super.createObject().toString();
    }

    @Override
    protected <T> MutableSet<T> create(final T... elements) {
        final String[] words = Arrays.transformAllSource(elements, Object::toString, new String[elements.length]);
        return (MutableSet<T>) Trie.copyOf(words);
    }

}
