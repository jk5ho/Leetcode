package com.leetcode.easy;

import java.util.*;

public class Others {

    /**
     * (#20)
     * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
     *
     * An input string is valid if:
     *
     *     Open brackets must be closed by the same type of brackets.
     *     Open brackets must be closed in the correct order.
     *
     * Note that an empty string is also considered valid.
     *
     * @param s The given string.
     * @return Whether input string is valid.
     */
    public boolean isValid(String s) {
        Map<Character, Character> paraMap = new HashMap<Character, Character>();
        paraMap.put("(".charAt(0), ")".charAt(0));
        paraMap.put("{".charAt(0), "}".charAt(0));
        paraMap.put("[".charAt(0), "]".charAt(0));

        Set<Character> openParaSet = new HashSet<Character>();
        openParaSet.add("(".charAt(0));
        openParaSet.add("{".charAt(0));
        openParaSet.add("[".charAt(0));

        Stack<Character> paraStack = new Stack<Character>();

        for(char parantheses : s.toCharArray()) {
            if(openParaSet.contains(parantheses)) {
                paraStack.push(parantheses);
            } else if(paraStack.empty()) {
                return false;
            } else {
                if(parantheses != paraMap.get(paraStack.pop())) {
                    return false;
                }
            }
        }

        return paraStack.empty();
    }
}
