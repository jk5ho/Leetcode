package com.leetcode.misc;

import com.leetcode.util.ListNode;

import java.util.Arrays;
import java.util.Stack;

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
     * (#206)
     * Reverse a singly linked list.
     *
     * @param head The linked list.
     * @return The reversed list.
     */
    public ListNode reverseList(ListNode head) {
        Stack<Integer> reverseStack = new Stack<Integer>();

        if(head == null) return null;

        while(head.next != null) {
            reverseStack.push(head.val);
            head = head.next;
        }
        if(head.next == null) reverseStack.push(head.val);

        ListNode reverseHead = new ListNode(0);
        ListNode returnList = reverseHead;
        while(!reverseStack.empty()) {
            ListNode tempNode = new ListNode(reverseStack.pop());
            reverseHead.next = tempNode;
            reverseHead = reverseHead.next;
        }

        return returnList.next;
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
