package com.leetcode.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Backtracking {

    List<String> ret = new ArrayList<String>();
    HashMap<Integer, String> dialMap = new HashMap<Integer, String>();

    /**
     * (#17)
     * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
     *
     * A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
     * 2 - a b c
     * 3 - d e f
     * 4 - g h i
     * 5 - j k l
     * 6 - m n o
     * 7 - p q r s
     * 8 - t u v
     * 9 - w x y z
     *
     * @param digits The string containing digits.
     * @return All possible combinations.
     */
    public List<String> letterCombinations(String digits) {
        dialMap.put(2, "abc");
        dialMap.put(3, "def");
        dialMap.put(4, "ghi");
        dialMap.put(5, "jkl");
        dialMap.put(6, "mno");
        dialMap.put(7, "pqrs");
        dialMap.put(8, "tuv");
        dialMap.put(9, "wxyz");

        if(digits.length() != 0) backtrack("", digits);
        return ret;
    }

    public void backtrack(String combination, String next_digit) {
        // Add the completed permutation
        if(next_digit.length() == 0) {
            ret.add(combination);
        }

        // If there are still digits left
        else {
            int digit = Character.getNumericValue(next_digit.charAt(0));
            String letters = dialMap.get(digit);
            // Recursively find all the permutations
            for(char letter : letters.toCharArray()) {
                backtrack(combination + letter, next_digit.substring(1));
            }
        }
    }
}
