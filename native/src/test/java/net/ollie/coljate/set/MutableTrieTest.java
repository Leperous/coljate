package net.ollie.coljate.set;

import java.util.UUID;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Ollie
 */
public class MutableTrieTest extends MutableSetTest<String> {

    @Override
    protected MutableTrie create() {
        return this.createFrom();
    }

    @Override
    protected MutableTrie create(final String string) {
        return this.createFrom(string);
    }

    @Override
    protected MutableTrie create(String first, String second) {
        return this.createFrom(first, second);
    }

    protected MutableTrie createFrom(final String... strings) {
        final MutableTrie trie = new MutableTrie();
        for (final String string : strings) {
            trie.add(string);
        }
        return trie;
    }

    @Override
    protected String randomValue() {
        return UUID.randomUUID().toString().substring(0, 5);
    }

    @Test
    public void testEmptyString() {
        final MutableTrie trie = this.create();
        assertFalse(trie.contains(""));
        trie.add("");
        assertTrue(trie.contains(""));
    }

}
