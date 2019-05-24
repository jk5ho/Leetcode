package com.leetcode.easy;

import java.util.Arrays;

public class SortingSearching {

    /**
     * (#88)
     * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
     *
     * Note:
     *     The number of elements initialized in nums1 and nums2 are m and n respectively.
     *     You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2.
     *
     * @param nums1 The first array of integers.
     * @param m The length of valid integers in first array.
     * @param nums2 The second array of integers.
     * @param n The length of valid integers in second array.
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] nums0 = Arrays.copyOf(nums1, m);
        int counter0 = 0;
        int counter2 = 0;

        for(int i = 0; i < m+n; i++) {
            if(counter0 < m && counter2 < n) {
                if (nums0[counter0] < nums2[counter2]) {
                    nums1[i] = nums0[counter0];
                    counter0++;
                } else if (nums0[counter0] >= nums2[counter2]) {
                    nums1[i] = nums2[counter2];
                    counter2++;
                }
            } else if (counter0 < m) {
                nums1[i] = nums0[counter0];
                counter0++;
            } else if (counter2 < n) {
                nums1[i] = nums2[counter2];
                counter2++;
            }
        }
    }

}
