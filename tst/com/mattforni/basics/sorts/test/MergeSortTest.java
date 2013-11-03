package com.mattforni.basics.sorts.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.mattforni.basics.sorts.MergeSort;

public class MergeSortTest {
    private static final int NUM_ELEMENTS = 150;

    @Test
    public void testEmptyList() {
        List<Integer> sorted = MergeSort.sort(new ArrayList<Integer>(0));
        assertTrue(sorted.isEmpty());
    }

    @Test
    public void testAscendingList() {
        List<Integer> nums = new ArrayList<Integer>(NUM_ELEMENTS);
        for (int i = 0; i < NUM_ELEMENTS; i++) { nums.add(i); }
        List<Integer> sorted = MergeSort.sort(nums);
        assertEquals(NUM_ELEMENTS, sorted.size());
        assertAscending(sorted);
    }

    @Test
    public void testDescendingList() {
        List<Integer> nums = new ArrayList<Integer>(NUM_ELEMENTS);
        for (int i = NUM_ELEMENTS-1; i >= 0; i--) { nums.add(i); }
        List<Integer> sorted = MergeSort.sort(nums);
        assertEquals(NUM_ELEMENTS, sorted.size());
        assertAscending(sorted);
    }

    private static void assertAscending(final List<Integer> sorted) {
        int length = sorted.size();
        for (int i = 0; i < length-1; i++) {
            assertTrue(sorted.get(i) < sorted.get(i+1));
        }
    }
}
