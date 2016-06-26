package net.ollie.coljate.set;

import java.util.Iterator;
import java.util.NoSuchElementException;

import javax.annotation.CheckForNull;

import net.ollie.coljate.list.MutableWrappedStack;
import net.ollie.coljate.list.Stack;
import net.ollie.coljate.map.MapEntry;
import net.ollie.coljate.map.MutableMap;
import net.ollie.coljate.map.MutableWrappedHashMap;
import net.ollie.coljate.set.mixin.CopiedToHashSet;
import net.ollie.coljate.utils.Iterators;

/**
 *
 * @author Ollie
 */
public class MutableTrie
        extends AbstractSet<String>
        implements Trie, MutableSet<String>, CopiedToHashSet<String> {

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
        return trie.endOfWord;
    }

    @Override
    @Deprecated
    public boolean removeOnce(final Object element) {
        return element instanceof String && this.removeOnce((String) element);
    }

    public boolean removeOnce(final String string) {
        throw new UnsupportedOperationException(); //TODO removeOnce
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
        return this.isEmpty() ? Iterators.none() : new TrieIterator();
    }

    private final class TrieIterator implements Iterator<String> {

        private final Stack<TriePointer> stack = new MutableWrappedStack<>();
        private String next;

        TrieIterator() {
            this.buildStack(new TriePointer(MutableTrie.this));
        }

        private void buildStack(TriePointer pointer) {
            while (pointer != null) {
                stack.push(pointer);
                pointer = pointer.child();
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public String next() {
            final StringBuilder sb = new StringBuilder(stack.count());
            for (final TriePointer pointer : stack) {
                sb.append(pointer.c());
            }
            this.moveToNext();
            next = sb.toString();
            return next;
        }

        private void moveToNext() {
            //Pop exhausted iterators off the stack
            while (!stack.isEmpty() && !stack.peek().hasNext()) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                return;
            }
            //Advance the head
            stack.peek().advance();
            //Push children onto the stack
            this.buildStack(stack.pop());
        }

        @Override
        public void remove() {
            if (next == null) {
                throw new NoSuchElementException();
            }
            MutableTrie.this.removeOnce(next);
        }

        private final class TriePointer {

            private final Iterator<MapEntry<Character, MutableTrie>> children;
            private MapEntry<Character, MutableTrie> currentChild;

            TriePointer(final MutableTrie trie) {
                this.children = trie.children.iterator();
                this.advance();
            }

            char c() {
                return currentChild.key();
            }

            boolean hasNext() {
                return children.hasNext();
            }

            final void advance() {
                currentChild = children.next();
            }

            @CheckForNull
            TriePointer child() {
                return currentChild.value().isEmpty()
                        ? null
                        : new TriePointer(currentChild.value());
            }

        }

    }

}
