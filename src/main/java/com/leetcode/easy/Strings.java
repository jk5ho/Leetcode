package com.leetcode.easy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Strings {

    /**
     * (#344)
     * Write a function that reverses a string. The input string is given as an array of characters char[].
     *
     * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
     *
     * You may assume all the characters consist of printable ascii characters.
     *
     * @param s The array of characters.
     * @return The reversed string.
     */
    public String reverseString(String s) {
        char[] charOg = s.toCharArray();
        char[] charNew = new char[charOg.length];
        int b = charNew.length-1;

        for(int a = 0; a < charOg.length; a++) {
            charNew[b] = charOg[a];
            b--;
        }

        return new String(charNew);
    }

    /**
     * (#7)
     * Given a 32-bit signed integer, reverse digits of an integer.
     *
     * @param x The 32-bit signed integer.
     * @return The reversed integer.
     */
    public int reverse(int x) {
        char[] array = Integer.toString(x).toCharArray();
        char temp;
        int a = 0;
        int ret = 0;

        if(x < 0){
            a = 1;
        }

        for(int b = array.length-1; b > (array.length-1)/2; b--) {
            temp = array[a];
            array[a] = array[b];
            array[b] = temp;
            a++;
        }

        try {
            ret = Integer.parseInt(String.valueOf(array));
        } catch (Exception e) {
            return 0;
        }

        return ret;
    }

    /**
     * (#387)
     * Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.
     *
     * @param s The string.
     * @return The first non-repeating character's index.
     */
    public int firstUniqChar(String s) {
        // char[](Original) : l e e t c o d e
        // List(Compare)    : l t c o d e
        // List(Index)      : 0 3 4 5 6 7
        // Set(Repeated)    : e

        char[] original = s.toCharArray();
        List<Character> compare = new ArrayList<Character>();
        List<Integer> index = new ArrayList<Integer>();
        Set<Character> repeated = new HashSet<Character>();

        for(int i = 0; i < original.length; i++) {
            if(compare.contains(original[i])) {
                index.remove(compare.indexOf(original[i]));     // Remove from index
                compare.remove((Object) original[i]);           // Remove from compare
                repeated.add(original[i]);                      // Add to repeated
            } else if(!repeated.contains(original[i])) {
                index.add(i);
                compare.add(original[i]);
            }
        }

        return (index.size()==0) ? -1 : index.get(0);
    }

    /**
     * (#125)
     * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
     *
     * Note: For the purpose of this problem, we define empty string as valid palindrome.
     *
     * @param s The string.
     * @return Whether is it a palindrome.
     */
    public boolean isPalindrome(String s) {
        char[] strArray = s.toCharArray();
        int left = 0;
        int right = strArray.length-1;

        if(s == "" || strArray.length == 1) {
            return true;
        }

        while(left < right) {
            while(!Character.isLetter(strArray[left]) && !Character.isDigit(strArray[left])) {
                left++;
                if(left == strArray.length-1) {
                    return true;
                }
            }

            while(!Character.isLetter(strArray[right]) && !Character.isDigit(strArray[right])) {
                right--;
                if(right == 0) {
                    return true;
                }
            }

            if(Character.toLowerCase(strArray[left]) != Character.toLowerCase(strArray[right])) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /**
     * (#28)
     * Implement strStr().
     *
     * Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
     *
     * Clarification:
     *
     * What should we return when needle is an empty string? This is a great question to ask during an interview.
     *
     * For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's strstr() and Java's indexOf().
     *
     * @param haystack The haystack.
     * @param needle The needle.
     * @return The first occurrence of the needle in the haystack.
     */
    public int strStr(String haystack, String needle) {
        if(needle.length() == 0) {
            return 0;
        }
        if(haystack.length() == 0) {
            return -1;
        }

        for(int h = 0; h < haystack.length(); h++) {

            if(h + needle.length() > haystack.length()) {
                return -1;
            }

            if(haystack.charAt(h) == needle.charAt(0)) {
                for(int n = 0; n < needle.length(); n++) {
                    if(haystack.charAt(h+n) != needle.charAt(n)) {
                        break;
                    }
                    if(n == needle.length()-1) {
                        return h;
                    }
                }
            }

        }
        return -1;
    }

    /**
     * (#14)
     * Write a function to find the longest common prefix string amongst an array of strings.
     *
     * If there is no common prefix, return an empty string ""
     *
     * @param strs The array of strings.
     * @return The longest common prefix.
     */
    public String longestCommonPrefix(String[] strs) {
        if(strs.length == 0) {
            return "";
        }

        if(strs.length == 1) {
            return strs[0];
        }

        // Find smallest length
        int min = Integer.MAX_VALUE;
        for(String word : strs) {
            min = (word.length() < min) ? word.length() : min;
        }

        // Matching (using 1st input as key)
        boolean same = false;
        String ret = "";

        for(int i = 0; i < min; i++) {
            char compare = strs[0].charAt(i);
            for(int j = 1; j < strs.length; j++) {
                same = (strs[j].charAt(i) == compare);
                if(!same) break;
            }

            if(same) ret += String.valueOf(compare);
            else break;
        }

        return ret;
    }
}
