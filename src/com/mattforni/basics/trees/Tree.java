package com.mattforni.basics.trees;

import java.util.Queue;
import java.util.Stack;

import com.google.common.collect.Queues;

/**
 * The most basic representation of a Tree data structure.
 * @author Matthew Fornaciari <mattforni@gmail.com>
 *
 * @param <K> The type of the key for this node.
 * @param <V> The type of the value for this node (optional).
 */
@SuppressWarnings("serial")
public class Tree<K, V> {
    protected Node<K, V> root;

    /**
     * Adds a node to a tree instance.
     * @param parentKey The key of the parent node or null if the node to add should be root.
     * @param child The new node to add to the tree instance.
     * @return Whether or not the new node was added to the tree.
     * @throws NodeException If the parent cannot be located or no parent is provided but a root already exists.
     */
    public boolean add(final K parentKey, final Node<K, V> child) throws NodeException {
        // If the parent is not null search for it.
        if (parentKey != null) {
            final Node<K, V> found = depthFirstSearch(parentKey);
            if (found == null) {
                throw new NodeNotFound("Unable to locate node with key '"+parentKey+"'");
            }
            return found.addChild(child);
        } else { // Otherwise the node should be added as the root.
            if (root == null) {
                root = child;
                return true;
            } else {
                throw new NodeAlreadyExists("There is already a root node");
            }
        }
    }

    public final Node<K, V> breadthFirstSearch(final K key) {
        final Queue<Node<K, V>> nodes = Queues.newArrayDeque();
        if (root != null) { nodes.add(root); }

        while (!nodes.isEmpty()) {
            final Node<K, V> node = nodes.poll();
            if (node.getKey().equals(key)) { return node; }

            for (final Node<K, V> child : node.getChildren()) {
                nodes.add(child);
            }
        }

        return null;
    }

    /**
     * Performs a depth first search for the provided key.
     * @param key The key for which to search.
     * @return The found node or null if no node was found.
     */
    public final Node<K, V> depthFirstSearch(final K key) {
        if (root == null) { return null; }
        return depthFirstSearch(root, key);
    }

    private Node<K, V> depthFirstSearch(final Node<K, V> node, final K key) {
        // If the key has been found return the associated node.
        if (node.getKey().equals(key)) { return node; }

        // Otherwise recursively search each of the child paths.
        for (final Node<K, V> child : node.getChildren()) {
            final Node<K, V> found = depthFirstSearch(child, key);
            if (found != null) { return found; }
        }

        // The node does not exist on any child paths return null.
        return null;
    }

    public abstract static class NodeException extends RuntimeException {
        protected NodeException(final String message) {
            super(message);
        }
    }

    public static class NodeAlreadyExists extends NodeException {
        public NodeAlreadyExists(final String message) {
            super(message);
        }
    }

    public static class NodeNotFound extends NodeException {
        public NodeNotFound(final String message) {
            super(message);
        }
    }

    public static enum Search {
        DFS (Queue.class),
        BFS (Stack.class);

        private final Class dataStructure;

        private Search(final Class dataStructure) {
            this.dataStructure = dataStructure;
        }
    }
}
