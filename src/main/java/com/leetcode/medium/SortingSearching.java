package com.leetcode.medium;

import java.util.*;

public class SortingSearching {

    /**
     * (#215)
     * Find the kth largest element in an unsorted array.
     * Note that it is the kth largest element in the sorted order, not the kth distinct element.
     *
     * @param nums The unsorted array.
     * @param k The k largest.
     * @return The kth largest sorted element.
     */
    public int findKthLargest(int[] nums, int k) {
        int temp = 0;
        Arrays.sort(nums);

        for(int i = nums.length-1; i >= nums.length-k; i--) {
            temp = nums[i];
        }

        return temp;
    }

}
