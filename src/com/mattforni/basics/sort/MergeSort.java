package com.mattforni.basics.sort;

import java.util.ArrayList;
import java.util.List;

public abstract class MergeSort {
    // TODO set up a logger which logs to system out by default.
    /**
     * Sorts the provided unsorted array by performing a merge sort.
     * @param list An unsorted list of Comparable objects to be sorted.
     * @return A sorted list of Comparable objects.
     */
    // TODO add a verbose option to log the steps being performed.
    public static <T extends Comparable<T>> List<T> sort(final List<T> list) {
        // TODO possibly turn this into an annotation.
        if (list == null) {
            throw new IllegalArgumentException("List may not be null");
        }

        // If the list is empty, or has 1 element, it is sorted.
        int size = list.size();
        if (size < 2) { return list; }

        // Else the list should be partitioned into two sublists and sorted.
        // This is the divide portion of the algorithm.
        int middle = size / 2;
        List<T> left = sort(list.subList(0, middle));
        List<T> right = sort(list.subList(middle, size));

        // Merge the now sorted lists from the recursive calls to sort.
        // This is the conquer portion of the algorithm.
        return merge(left, right);
    }

    private static <T extends Comparable<T>> List<T> merge(
            final List<T> left, final List<T> right) {
        // Initialize indices, sizes and the new list to be merged into.
        int lIndex = 0, lEnd = left.size();
        int rIndex = 0, rEnd = right.size();
        List<T> merged = new ArrayList<T>(lEnd+rEnd);

        // While there are still elements to evaluate in left or right.
        while (lIndex < lEnd && rIndex < rEnd) {
            // If the left element is smaller or equal, add and increment.
            if (left.get(lIndex).compareTo(right.get(rIndex)) <= 0) {
                merged.add(left.get(lIndex++));
            } else { // Else add the right element and increment.
                merged.add(right.get(rIndex++));
            }
        }

        // If there are more elements in the left list, append them.
        while (lIndex < lEnd) {
            merged.add(left.get(lIndex++));
        }
        // If there are more elements in the right list, append them.
        while (rIndex < rEnd) {
            merged.add(right.get(rIndex++));
        }
        return merged;
    }
}
