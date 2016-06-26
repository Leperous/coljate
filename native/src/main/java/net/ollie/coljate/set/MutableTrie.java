package net.ollie.coljate.set;

import java.util.Iterator;

import net.ollie.coljate.map.MutableMap;
import net.ollie.coljate.map.MutableWrappedHashMap;

/**
 *
 * @author Ollie
 */
public class MutableTrie implements Trie, MutableSet<String> {

    private final MutableMap<Character, MutableTrie> children;
    private boolean endOfWord;

    MutableTrie() {
        this(MutableWrappedHashMap.create());
    }

    protected MutableTrie(final MutableMap<Character, MutableTrie> children) {
        this.children = children;
    }

    @Override
    public boolean add(final String string) {
        boolean added = false;
        MutableTrie trie = this;
        for (final char c : string.toCharArray()) {
            MutableTrie next = trie.children.get(c);
            if (next == null) {
                next = new MutableTrie();
                trie.children.put(c, next);
                added = true;
            }
            trie = next;
        }
        final boolean wasEndOfWord = trie.endOfWord;
        trie.endOfWord = true;
        return added || !wasEndOfWord;
    }

    @Override
    public boolean contains(final String string) {
        MutableTrie trie = this;
        for (final char c : string.toCharArray()) {
            trie = trie.children.get(c);
            if (trie == null) {
                return false;
            }
        }
        return trie.isEndOfWord();
    }

    @Override
    public boolean isEndOfWord() {
        return endOfWord;
    }

    @Override
    public void clear() {
        children.values().forEach(MutableTrie::clear);
        children.clear();
    }

    @Override
    public Set<Trie> children() {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public MutableTrie mutableCopy() {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public ImmutableSet<String> immutableCopy() {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public Set<String> tail() {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public Iterator<String> iterator() {
        return new TrieIterator();
    }

    class TrieIterator implements Iterator<String> {

        @Override
        public boolean hasNext() {
            throw new UnsupportedOperationException(); //TODO
        }

        @Override
        public String next() {
            throw new UnsupportedOperationException(); //TODO
        }

    }

}
