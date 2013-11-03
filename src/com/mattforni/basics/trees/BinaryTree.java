package com.mattforni.basics.trees;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

// TODO javadoc
public class BinaryTree<K, V> {
    private Node<K, V> root;

    public ImmutableList<Node<K, V>> inorder() {
        if (root == null) { return ImmutableList.of(); }
        List<Node<K, V>> inorder = Lists.newLinkedList();
        inorder(root, inorder);
        return ImmutableList.copyOf(inorder);
    }

    // TODO javadoc
    private final void inorder(final Node<K, V> node,
            final List<Node<K, V>> list) {
        if (node.hasLeft()) { inorder(node.getLeft(), list); }
        list.add(node);
        if (node.hasRight()) { inorder(node.getRight(), list); }
    }

    // TODO javadoc all
    public static class Node<K, V> {
        private final K key;
        private final V value;
        private Node<K, V> left, right;
        public Node(final K key, final V value) {
            this.key = key;
            this.value = value;
        }

        public final K getKey() {
            return key;
        }

        public final Node<K, V> getLeft() {
            return left;
        }

        public final Node<K, V> getRight() {
            return right;
        }

        public final V getValue() {
            return value;
        }

        public final boolean hasLeft() {
            return left != null;
        }

        public final boolean hasRight() {
            return right != null;
        }
    }
}
