package com.leetcode.easy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Array {

    /**
     * (#26)
     * Given a sorted array nums, remove the duplicates in-place such that each element appear only once and return the new length.
     *
     * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
     *
     * @param nums The sorted array nums.
     * @return The updated array length.
     */
    public int removeDuplicates(int[] nums) {
        int length = 1;
        int index = 1;
        int size = nums.length;

        for(int i = 1; i < size; i++){
            if(nums[i] != nums[i-1]){
                length++;
                nums[index] = nums[i];
                index++;
            }
        }

        return length;
    }

    /**
     * (#122)
     * Say you have an array for which the ith element is the price of a given stock on day i.
     *
     * Design an algorithm to find the maximum profit. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).
     *
     * Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int total = 0;
        for(int i = 0; i < prices.length-1; i++){
            if(prices[i+1] > prices[i]) {
                total += prices[i+1]-prices[i];
            }
        }

        return total;
    }

    /**
     * (#217)
     * Given an array of integers, find if the array contains any duplicates.
     *
     * Your function should return true if any value appears at least twice in the array, and it should return false if every element is distinct.
     *
     * @param nums The array of integers.
     * @return Whether there is a duplicate.
     */
    public boolean containsDuplicate(int[] nums) {
        Map<Integer, Integer> hMap = new HashMap<>();

        for(int i = 0; i < nums.length; i++){
            if(!hMap.containsKey(nums[i])){
                hMap.put(nums[i], i);
            } else {
                return true;
            }
        }

        return false;
    }

    /**
     * (#136)
     * Given a non-empty array of integers, every element appears twice except for one. Find that single one.
     *
     * Note:
     *
     * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
     *
     * @param nums The non-empty array of integers.
     * @return The single element.
     */
    public int singleNumber(int[] nums) {
        Set<Integer> numSet = new HashSet<>();

        for(int num : nums) {
            if(!numSet.contains(num)) {
                numSet.add(num);
            } else {
                numSet.remove(num);
            }
        }

        return numSet.iterator().next();
    }

    /**
     * (#66)
     * Given a non-empty array of digits representing a non-negative integer, plus one to the integer.
     *
     * The digits are stored such that the most significant digit is at the head of the list, and each element in the array contain a single digit.
     *
     * You may assume the integer does not contain any leading zero, except the number 0 itself.
     *
     * @param digits The array of digits.
     * @return The incremented number array.
     */
    public int[] plusOne(int[] digits) {
        int i = digits.length-1;

        if(digits[digits.length-1] != 9){
            digits[digits.length-1]++;
        } else {
            while(digits[i] == 9) {
                i--;
                if(i<0){
                    int[] digitsNew = new int[digits.length+1];
                    digitsNew[0] = 1;
                    return digitsNew;
                }
            }
            digits[i]++;

            while(i+1 < digits.length){
                digits[i+1] = 0;
                i++;
            }
        }

        return digits;
    }

    /**
     * (#1)
     * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
     *
     * You may assume that each input would have exactly one solution, and you may not use the same element twice.
     *
     * @param nums The array of integers.
     * @param target The specific target.
     * @return The indices of the two numbers.
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> hMap = new HashMap<>();
        int[] ret = new int[2];

        for(int i = 0; i < nums.length; i++) {
            hMap.put(nums[i], i);
        }

        for(int i = 0; i < nums.length; i++) {
            int diff = target - nums[i];
            if(hMap.containsKey(diff) && hMap.get(diff) != i) {
                ret[0] = i;
                ret[1] = hMap.get(diff);
                return ret;
            }
        }

        return ret;
    }

}
