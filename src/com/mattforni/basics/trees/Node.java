package com.mattforni.basics.trees;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

/**
 * The most basic representation of a Node in a Tree data structure.
 * @author Matthew Fornaciari <mattforni@gmail.com>
 *
 * @param <K> The type of the key for this node.
 * @param <V> The type of the value for this node (optional).
 */
public class Node<K, V> {
    private static final int DEFAULT_DIMENSION = 64;

    protected final K key;
    protected final V value;
    protected final List<Node<K, V>> children;

    public Node(final K key) {
        this(key, null, DEFAULT_DIMENSION);
    }

    public Node(final K key, final V value) {
        this(key, value, DEFAULT_DIMENSION);
    }

    protected Node(final K key, final V value, final int dimension) {
        this.key = key;
        this.value = value;
        this.children = Lists.newArrayListWithExpectedSize(dimension);
    }

    public final boolean addChild(final Node<K, V> child) {
        return children.add(child);
    }

    /**
     * Returns the key stored at this node.
     * @return The key stored at this node.
     */
    public final K getKey() {
        return key;
    }

    /**
     * Returns an {@link ImmutableList} of this node's children.
     * @return An immutable list of this node's children.
     */
    public final List<Node<K, V>> getChildren() {
        return ImmutableList.copyOf(children);
    }

    /**
     * Returns the value stored at this node.
     * @return The value stored at this node.
     */
    public final V getValue() {
        return value;
    }

    /**
     * Returns whether or not this node is a leaf node.
     * @return Whether or not this node is a leaf node.
     */
    public final boolean isLeaf() {
        return children.isEmpty();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(key.toString());
        if (value != null) {
            builder.append(":");
            builder.append(value.toString());
        }
        return builder.toString();
    }
}