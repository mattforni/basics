package com.mattforni.basics.trees.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.mattforni.basics.trees.Node;
import com.mattforni.basics.trees.Tree;

public class DepthFirstSearchTest {
    @Test
    public void testSearch() {
        final Tree<String, Object> tree = new Tree<String, Object>();
        tree.add(null, new Node<String, Object>("a"));
        tree.add("a", new Node<String, Object>("b"));
        tree.add("a", new Node<String, Object>("c"));
        tree.add("b", new Node<String, Object>("d"));
        tree.add("b", new Node<String, Object>("e"));
        tree.add("b", new Node<String, Object>("f"));
        tree.add("c", new Node<String, Object>("g"));
        tree.add("g", new Node<String, Object>("h"));

        final Node<String, Object> found = tree.depthFirstSearch("g");
        assertNotNull(found);
        assertEquals("g", found.getKey());
    }
}
