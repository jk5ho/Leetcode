package com.leetcode.easy;

public class DynamicProgramming {

    /**
     * (#121)
     * Say you have an array for which the ith element is the price of a given stock on day i.
     *
     * If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.
     *
     * Note that you cannot sell a stock before you buy one.
     * @param prices The array of prices.
     * @return The max profit.
     */
    public int maxProfit(int[] prices) {

        int min = Integer.MAX_VALUE;
        int profit = 0;
        int max = 0;

        for(int i = 0; i < prices.length; i++) {
            min = (prices[i] < min) ? prices[i] : min;
            profit = prices[i] - min;
            max = (profit > max) ? profit : max;
        }

        return max;

    }

    /**
     * (#53)
     * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
     *
     * @param nums The integer array.
     * @return The contiguous subarray.
     */
    public int maxSubArray(int[] nums) {
        if(nums.length == 0) return Integer.MIN_VALUE;

        int maxCurr = nums[0];
        int maxAll = nums[0];

        for(int i = 1; i < nums.length; i++) {
            if(nums[i] > maxCurr + nums[i]) {
                maxCurr = nums[i];
            } else {
                maxCurr += nums[i];
            }

            if(maxCurr > maxAll) {
                maxAll = maxCurr;
            }
        }
        return maxAll;
    }

}
