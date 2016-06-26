package net.ollie.coljate.set;

import java.util.UUID;

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
        return this.createFrom();
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
        return UUID.randomUUID().toString();
    }

}
