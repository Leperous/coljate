package net.ollie.coljate.set;

import java.util.Iterator;
import java.util.NoSuchElementException;

import net.ollie.coljate.map.MutableMap;
import net.ollie.coljate.map.MutableWrappedHashMap;
import net.ollie.coljate.set.mixin.CopiedToHashSet;

/**
 *
 * @author Ollie
 */
public class MutableTrie implements Trie, MutableSet<String>, CopiedToHashSet<String> {

    private final MutableMap<Character, MutableTrie> children;
    private boolean endOfWord;

    public MutableTrie() {
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
    @Deprecated
    public boolean removeOnce(final Object element) {
        return element instanceof String && this.removeOnce((String) element);
    }

    public boolean removeOnce(final String string) {
        throw new UnsupportedOperationException();
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
    public boolean isEmpty() {
        return children.isEmpty();
    }

    @Override
    public MutableTrie mutableCopy() {
        final MutableTrie trie = new MutableTrie();
        final Iterator<String> iterator = this.iterator();
        while (iterator.hasNext()) {
            trie.add(iterator.next());
        }
        return trie;
    }

    @Override
    public Set<String> tail() {
        final Iterator<String> iterator = this.iterator();
        if (!iterator.hasNext()) {
            return this;
        }
        iterator.next();
        final MutableTrie trie = new MutableTrie();
        while (iterator.hasNext()) {
            trie.add(iterator.next());
        }
        return trie;
    }

    @Override
    public Iterator<String> iterator() {
        return new TrieIterator();
    }

    class TrieIterator implements Iterator<String> {

        String next;

        @Override
        public boolean hasNext() {
            throw new UnsupportedOperationException(); //TODO
        }

        @Override
        public String next() {
            if (next == null) {
                throw new NoSuchElementException();
            }
            final String next = this.next;
            this.next = this.computeNext();
            return next;
        }

        private String computeNext() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove() {
            MutableTrie.this.removeOnce(this.next());
        }

    }

}
