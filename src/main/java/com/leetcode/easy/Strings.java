package com.leetcode.easy;

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

}
