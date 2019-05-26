package com.leetcode.misc;

import java.util.Arrays;

public class Easy {

    /**
     * (#9)
     * Determine whether an integer is a palindrome. An integer is a palindrome when it reads the same backward as forward.
     *
     * @param x The integer.
     * @return Whether it is a palindrome.
     */
    public boolean isPalindrome(int x) {
        char[] charArray = String.valueOf(x).toCharArray();

        for(int i = 0; i < charArray.length/2 ; i++) {
            if(charArray[i] != charArray[charArray.length-1 - i]){
                return false;
            }
        }
        return true;
    }

    /**
     * (#14)
     * Write a function to find the longest common prefix string amongst an array of strings.
     *
     * If there is no common prefix, return an empty string "".
     *
     * @param strs The array of strings.
     * @return The longest common prefix.
     */
    public String longestCommonPrefix(String[] strs) {
        int count = 0;
        boolean same = true;

        // Empty array, Single array
        if(strs.length == 0) {
            return "";
        } else if (strs.length == 1) {
            return strs[0];
        }

        // Shortest string
        char[] temp = strs[0].toCharArray();

        int j = 0;
        while(same) {
            for(int i = 0; i < strs.length; i++) {

                // Empty strings
                if(strs[i].equals("")) {
                    return "";
                }

                char[] letters = strs[i].toCharArray();

                // Length check
                if(letters.length <= j) {
                    return strs[i];
                } else if (letters[j] != temp[j]) {
                    same = false;
                    break;
                }
                count++;
            }
            j++;
        }

        char[] retVal = new char[count / strs.length];
        char[] letter = strs[0].toCharArray();
        for(int k = 0; k < count / strs.length; k++) {
            retVal[k] = letter[k];
        }

        return String.valueOf(retVal);
    }

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
     * (#268)
     * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.
     *
     * @param nums The array of distinct numbers.
     * @return The missing number.
     */
    public int missingNumber(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i) {
                return i;
            }
        }

        return nums.length;
    }

}
