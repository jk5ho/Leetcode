package com.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ArrayStrings {

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

}
