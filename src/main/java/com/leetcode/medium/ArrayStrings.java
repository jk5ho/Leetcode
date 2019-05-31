package com.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ArrayStrings {

    /**
     * (#15)
     * Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
     *
     * Note:
     *
     * The solution set must not contain duplicate triplets.
     *
     * @param nums The array of integers.
     * @return The list of unique triplets.
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> retList = new ArrayList<>();
        Arrays.sort(nums);

        int left;
        int right;

        // a + b = -c
        for(int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            left = i+1;
            right = nums.length-1;
            while(left < right) {
                if(nums[left] + nums[right] < -nums[i]) {
                    left++;
                } else if(nums[left] + nums[right] > -nums[i]) {
                    right--;
                } else {
                    retList.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    right--;
                    while(left < right && nums[left] == nums[left-1]) {
                        left++;
                    }
                    while(left < right && nums[right] == nums[right+1]) {
                        right--;
                    }
                }
            }
        }

        return retList;
    }

    /**
     * (#49)
     * Given an array of strings, group anagrams together.
     *
     * @param strs The array of strings.
     * @return The list of anagrams.
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, ArrayList<String>> anagrams = new HashMap<String, ArrayList<String>>();

        for(int i = 0; i < strs.length; i++) {

            // Sorting to find key
            char keyArray[] = strs[i].toCharArray();
            Arrays.sort(keyArray);
            String key = new String(keyArray);

            // Find in hashmap and append...
            if ( anagrams.containsKey(key) ){
                ArrayList<String> getVal = anagrams.get(key);
                getVal.add(strs[i]);
                anagrams.put(key, getVal);
            }

            // ...Or add to hashmap if not found
            else {
                ArrayList<String> putVal = new ArrayList<>();
                putVal.add(strs[i]);
                anagrams.put(key, putVal);
            }
        }

        List<List<String>> arrayList = new ArrayList<>();

        for (String j : anagrams.keySet()) {

            // Retrieve each key/value pair
            List<String> arrays = anagrams.get(j);

            // Add them to return list
            arrayList.add(arrays);
        }

        return arrayList;
    }

    /**
     * (#3)
     * Given a string, find the length of the longest substring without repeating characters.
     *
     * @param s The string.
     * @return The length of longest substring.
     */
    public int lengthOfLongestSubstring(String s) {
        int max = 0;
        int count = 0;
        List maxList = new ArrayList<String>();
        char[] stringArray = s.toCharArray();

        if(s.length() == 1) return 1;

        for(int i = 0; i < stringArray.length; i++) {
            if(maxList.contains(stringArray[i])) {
                int temp = maxList.indexOf(stringArray[i]);
                while(temp > 0) {
                    maxList.remove(temp);
                    count--;
                    temp--;
                }
                maxList.remove(0);
            } else {
                count++;
            }
            maxList.add(stringArray[i]);
            if(count > max) max = count;
        }

        return max;
    }

}
