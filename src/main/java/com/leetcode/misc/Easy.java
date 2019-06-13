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
