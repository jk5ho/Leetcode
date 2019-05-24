package com.leetcode.misc;

import com.leetcode.util.ListNode;

import java.util.ArrayList;
import java.util.List;

public class Medium {

    /**
     * (#3)
     * Given a string, find the length of the longest substring without repeating characters.
     *
     * @param s The string.
     * @return The length of substring.
     */
    public int lengthOfLongestSubstring(String s) {
        List<String> currentMap = new ArrayList<String>();
        char[] charArray = s.toCharArray();
        int max = 0;

        if (charArray.length <= 1) {
            return charArray.length;
        }

        for (int i = 0; i < charArray.length; i++) {
            if(currentMap.contains(String.valueOf(charArray[i]))) {
                int removeIndex = currentMap.indexOf(String.valueOf(charArray[i]));
                currentMap.remove(removeIndex);
                for(int j = 0; j < removeIndex; j++) {
                    currentMap.remove(0);
                }
            }
            currentMap.add(String.valueOf(charArray[i]));

            if(currentMap.size() > max) {
                max = currentMap.size();
            }
        }
        return max;
    }

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

}
