package com.leetcode.misc;

import com.leetcode.util.ListNode;

import java.util.*;

public class Medium {

    /**
     * (#6)
     * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
     *
     * P   A   H   N
     * A P L S I I G
     * Y   I   R
     *
     * And then read line by line: "PAHNAPLSIIGYIR"
     *
     * @param s The string.
     * @param numRows The number of rows.
     * @return The formatted string.
     */
    public String convert(String s, int numRows) {

        char[] stringArray = s.toCharArray();
        String[] retArray = new String[numRows];
        for(int i = 0; i < numRows; i++) {
            retArray[i] = "";
        }

        int position = 0;
        boolean decrement = false;

        if(numRows == 1 || s.isEmpty() || numRows > stringArray.length) {
            return s;
        }

        for(char element : stringArray) {
            retArray[position] += element;

            if(position == 0) {
                decrement = false;
            } else if(position == numRows - 1) {
                decrement = true;
            }

            if(decrement) {
                position--;
            } else {
                position++;
            }
        }

        String retString = "";
        for(String ret : retArray) {
            retString += ret;
        }

        return retString;
    }

    /**
     * (#19)
     * Given a linked list, remove the n-th node from the end of list and return its head.
     *
     * @param head The linked list.
     * @param n The nth value.
     * @return The updated linked list.
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode returnNode = new ListNode(0);
        returnNode.next = head;

        // Case: only 1 element
        if(n == 1 && head.next == null) {
            return null;
        }

        // Find cursor node
        ListNode cursorNode = new ListNode(0);
        cursorNode = head;
        for(int x = 0; x < n; x++) {
            cursorNode = cursorNode.next;
            if(cursorNode == null) {
                returnNode.next = head.next;
                return returnNode.next;
            }
        }

        // Remove node
        while(cursorNode.next != null) {
            head = head.next;
            cursorNode = cursorNode.next;
        }
        head.next = head.next.next;

        return returnNode.next;
    }


    /**
     * (#56)
     * Given a collection of intervals, merge all overlapping intervals.
     *
     * @param intervals The collection of intervals.
     * @return The overlapping merged intervals.
     */
    public int[][] merge(int[][] intervals) {

        // sorting by start
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        int[][] list = new int[intervals.length][2];
        int prev_end = Integer.MIN_VALUE;
        int curr = 0;

        for(int i = 0; i < intervals.length; i++) {
            if(intervals[i][0] <= prev_end) {
                // modify last merged
                prev_end = Math.max(intervals[i][1], prev_end);
                list[curr-1][1] = prev_end;
            } else {
                // adding new interval
                prev_end = intervals[i][1];
                list[curr][0] = intervals[i][0];
                list[curr][1] = prev_end;
                curr++;
            }
        }

        return Arrays.copyOf(list, curr);
    }

    /**
     * (#151)
     * Given an input string, reverse the string word by word.
     *
     * @param s The input string.
     * @return The reversed string.
     */
    public String reverseWords(String s) {

        String prev = "";
        String curr = "";

        for(char letter : s.toCharArray()) {
            if(!Character.isWhitespace(letter)) {
                curr += letter;
            } else if(curr != "") {
                prev = " " + curr + prev;
                curr = "";
            }
        }
        prev = curr + prev;

        return prev.trim();
    }

    /**
     * (#228)
     * Given a sorted integer array without duplicates, return the summary of its ranges.
     *
     * @param nums The sorted integer array.
     * @return The summary of its ranges.
     */
    public List<String> summaryRanges(int[] nums) {
        List<String> ret = new ArrayList<>();
        if(nums.length == 0) return ret;

        int head = nums[0];
        int tail = nums[0];
        if(nums.length == 1){
            ret.add(Integer.toString(tail));
            return ret;
        }

        StringBuilder s = new StringBuilder();
        for(int i = 1; i < nums.length; i++) {
            if(nums[i] != tail+1) {
                s.append(head);
                if(head != tail) s.append("->").append(tail);
                ret.add(s.toString());
                s = new StringBuilder();
                head = nums[i];
            }
            tail = nums[i];
        }
        s.append(head);
        if(head != tail) s.append("->").append(tail);
        ret.add(s.toString());
        return ret;
    }

    /**
     * (#287)
     * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist.
     * Assume that there is only one duplicate number, find the duplicate one.
     *
     * @param nums The array nums.
     * @return The duplicated integer.
     */
    public int findDuplicate(int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        for(int num : nums) {
            if(!numSet.add(num)) {
                return num;
            }
        }
        return -1;
    }

    /**
     * (#739)
     * Given a list of daily temperatures T, return a list such that, for each day in the input, tells you how many days you would have to wait until a warmer temperature. If there is no future day for which this is possible, put 0 instead.
     *
     * For example, given the list of temperatures T = [73, 74, 75, 71, 69, 72, 76, 73], your output should be [1, 1, 4, 2, 1, 1, 0, 0].
     *
     * Note: The length of temperatures will be in the range [1, 30000]. Each temperature will be an integer in the range [30, 100].
     *
     * @param T The list of daily temperatures.
     * @return The list of days to wait.
     */
    public int[] dailyTemperatures(int[] T) {
        int size = T.length;
        int[] ret = new int[T.length];
        int[] nextHigh = new int[71];
        int min = Integer.MAX_VALUE;
        boolean found = false;

        for(int i = size-1; i >= 0; i--) {
            int curr = T[i];
            nextHigh[curr-30] = i;

            if(i != size-1) {
                for(int j = curr-30; j < nextHigh.length; j++) {
                    if(nextHigh[j] > i) {
                        min = (nextHigh[j] - i < min) ? nextHigh[j] - i : min;
                        found = true;
                    }
                }
                ret[i] = (found) ? min : 0;
                min = Integer.MAX_VALUE;
                found = false;
            }
        }

        return ret;
    }

}
