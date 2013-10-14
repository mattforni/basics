package com.mattforni.basics.main;

import java.util.ArrayList;
import java.util.List;

import com.mattforni.basics.sort.MergeSort;

public class MergeSortMain {
    /**
     * @param args
     */
    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<Integer>(8);
        nums.add(23);
        nums.add(89);
        nums.add(2);
        nums.add(16);
        nums.add(342);
        nums.add(55);
        nums.add(37);
        nums.add(78);
        nums.add(-4);
        for (Integer num : nums) {
            System.out.print(num+" ");
        }
        System.out.println("\n\nNow sorted:\n");
        List<Integer> sorted = MergeSort.sort(nums);
        for (Integer num : sorted) {
            System.out.print(num+" ");
        }
    }
}
