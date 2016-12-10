package net.coljate.set.impl;

import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.coljate.map.Entry;
import net.coljate.map.MutableMap;
import net.coljate.set.AbstractSet;
import net.coljate.set.MutableSet;
import net.coljate.util.Arrays;

/**
 *
 * @author ollie
 */
public class Trie
        extends AbstractSet<String>
        implements MutableSet<String> {

    private static final Logger logger = LoggerFactory.getLogger(Trie.class);

    public static Trie copyOf(final String... strings) {
        final Trie trie = new Trie();
        Arrays.copyInto(strings, trie::add);
        return trie;
    }

    public static Trie copyOf(final Iterable<String> sequences) {
        final Trie trie = new Trie();
        sequences.forEach(trie::add);
        return trie;
    }

    private final Node root = new Node(null);

    @Override
    public boolean contains(final Object object) {
        return object instanceof String
                && this.contains((String) object);
    }

    public boolean contains(final String string) {
        return this.findWordEndNode(string) != null;
    }

    private Node findWordEndNode(final String string) {
        Node current = root;
        for (int i = 0; current != null && i < string.length(); i++) {
            final char c = string.charAt(i);
            current = current.get(c);
        }
        return current == null || !current.isWordEnd ? null : current;
    }

    @Override
    public int count() {
        return root.count();
    }

    @Override
    public boolean isEmpty() {
        return root.isEmpty();
    }

    @Override
    public boolean add(final String string) {
        Node current = root;
        for (int i = 0; i < string.length(); i++) {
            final char c = string.charAt(i);
            current = current.add(c);
        }
        final boolean wasTerminal = current.isWordEnd;
        current.isWordEnd = true;
        return !wasTerminal;
    }

    @Override
    public Iterator<String> iterator() {
        return new TrieIterator();
    }

    @Override
    public boolean remove(final Object element) {
        return element instanceof String
                && this.remove((String) element);
    }

    public boolean remove(final String string) {
        final Node node = this.findWordEndNode(string);
        if (node == null) {
            return false;
        } else {
            node.isWordEnd = false;
            node.deleteIfEmpty();
            return true;
        }
    }

    @Override
    public void clear() {
        root.clear();
    }

    private final class Node {

        private final Node parent;
        private final MutableMap<Character, Node> nodes = MutableMap.createHashMap(4);
        private boolean isWordEnd = false;

        Node(final Node parent) {
            this.parent = parent;
        }

        Node get(final char c) {
            return nodes.get(c);
        }

        Node add(final char c) {
            return nodes.computeIfAbsent(c, ch -> new Node(this));
        }

        boolean isEmpty() {
            return !this.isWordEnd && nodes.isEmpty();
        }

        void clear() {
            nodes.clear();
        }

        int count() {
            return (isWordEnd ? 1 : 0) + nodes.values().sum(Node::count);
        }

        Iterator<Entry<Character, Node>> iterator() {
            return nodes.iterator();
        }

        void deleteIfEmpty() {
            if (parent != null && this.isEmpty()) {
                logger.info("Deleting this empty node");
                parent.nodes.removeAllMatchingEntries((c, node) -> node == this);
                parent.deleteIfEmpty();
            }
        }

    }

    private final class TrieIterator implements Iterator<String> {

        private Node current = root;
        private final StringBuffer buffer = new StringBuffer();

        @Override
        public boolean hasNext() {
            throw new UnsupportedOperationException();
        }

        @Override
        public String next() {
            throw new UnsupportedOperationException();
        }

    }

}
