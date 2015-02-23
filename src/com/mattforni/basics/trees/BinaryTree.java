package com.mattforni.basics.trees;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

// TODO javadoc
public class BinaryTree<K, V> extends Tree<K, V> {
    public final List<Node<K, V>> inorder() {
        if (root == null) { return ImmutableList.of(); }
        List<Node<K, V>> inorder = Lists.newLinkedList();
        inorder((BinaryNode<K, V>)root, inorder);
        return ImmutableList.copyOf(inorder);
    }

    // TODO javadoc
    private void inorder(final BinaryNode<K, V> node, final List<Node<K, V>> list) {
        if (node.hasLeft()) { inorder(node.getLeft(), list); }
        list.add(node);
        if (node.hasRight()) { inorder(node.getRight(), list); }
    }

    // TODO javadoc all
    public static class BinaryNode<K, V> extends Node<K, V> {
        public BinaryNode(final K key, final V value) {
            super(key, value, 2);
        }

        public final BinaryNode<K, V> getLeft() {
            return (BinaryNode<K, V>)children.get(0);
        }

        public final BinaryNode<K, V> getRight() {
            return (BinaryNode<K, V>)children.get(1);
        }

        public final boolean hasLeft() {
            return getLeft() != null;
        }

        public final boolean hasRight() {
            return getRight() != null;
        }
    }
}
