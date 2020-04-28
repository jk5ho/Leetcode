package com.leetcode.medium;

import java.util.*;

public class SortingSearching {

    /**
     * (#75)
     * Given an array with n objects colored red, white or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white and blue.
     *
     * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
     *
     * Note: You are not suppose to use the library's sort function for this problem.
     *
     * @param nums The array with n objects.
     */
    public void sortColors(int[] nums) {
        int two = nums.length;
        int one = 0;

        for(int x = 0; x < nums.length; x++) {
            if(nums[x] == 0) {
                one++;
            } else if(nums[x] == 2) {
                two--;
            }
            nums[x] = 0;
        }

        for(int i = one; i < two; i++) {
            nums[i] = 1;
        }

        for(int j = two; j < nums.length; j++) {
            nums[j] = 2;
        }
    }

    /**
     * (#347)
     */
    public int[] topKFrequent(int[] nums, int k) {
        if(k == nums.length) return nums;

        // Populate the hashmap <number, frequency>
        Map<Integer, Integer> freqMap = new HashMap<Integer, Integer>();
        for(int i = 0; i < nums.length; i++) {
            if(!freqMap.containsKey(nums[i])){
                freqMap.put(nums[i],1);
            }
            else {
                freqMap.replace(nums[i], freqMap.get(nums[i])+1);
            }
        }

        // Convert into arrays
        Integer[] keys = freqMap.keySet().toArray(new Integer[0]);
        Integer[] vals = freqMap.values().toArray(new Integer[0]);

        // Iterate to find entries >= k
        ArrayList<Integer> ret = new ArrayList<Integer>();

        Integer[] temp2 = vals.clone();
        Arrays.sort(temp2);
        int min = temp2[temp2.length-k];

        for(int i = 0; i < vals.length; i++) {
            if(vals[i] >= min) {
                ret.add(keys[i]);
            }
        }
        return ret.stream().mapToInt(Integer::intValue).toArray();
    }

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

    /**
     * (#162)
     */
    public int findPeakElement(int[] nums) {
        int compare = Integer.MIN_VALUE;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] > compare) {
                if((i < nums.length-1 && nums[i] > nums[i+1]) || (i == nums.length-1)) {
                    return i;
                }
            }
        }
        return 0;
    }

    /**
     * (#34)
     * Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
     *
     * Your algorithm's runtime complexity must be in the order of O(log n).
     *
     * If the target is not found in the array, return [-1, -1].
     *
     * @param nums The array of integers.
     * @param target The given target value.
     * @return Starting and Ending positions.
     */
    public int[] searchRange(int[] nums, int target) {
        int[] ret = new int[2];
        int index = binarySearch(nums, 0, nums.length-1, target);
        int frst = index;
        int last = index;

        if(index != -1) {
            for(int i = index; i < nums.length; i++) {
                if(nums[i] == target && i > last) last = i;
            }
            for(int j = index; j >= 0; j--) {
                if(nums[j] == target && j < frst) frst = j;
            }
        }

        ret[0] = frst;
        ret[1] = last;
        return ret;
    }

    public int binarySearch(int arr[], int l, int r, int x) {
        if (r >= l) {
            int mid = l + (r - l) / 2;
            if (arr[mid] == x) return mid;
            if (arr[mid] > x) return binarySearch(arr, l, mid - 1, x);
            return binarySearch(arr, mid + 1, r, x);
        }
        return -1;
    }

}
