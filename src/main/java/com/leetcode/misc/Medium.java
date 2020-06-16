package com.leetcode.misc;

import com.leetcode.util.ListNode;
import javafx.util.Pair;

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
     * (#24)
     * Given a linked list, swap every two adjacent nodes and return its head.
     *
     * You may not modify the values in the list's nodes, only nodes itself may be changed.
     *
     * @param head The linked list.
     * @return The updated linked list.
     */
    public ListNode swapPairs(ListNode head) {
        ListNode copyHead = new ListNode(0);
        ListNode prev = copyHead;
        ListNode temp = head;

        while(temp != null && temp.next != null) {
            ListNode tail = temp.next.next;
            prev.next = temp.next;
            prev = prev.next;
            prev.next = temp;
            prev = prev.next;
            prev.next = tail;
            temp = temp.next;
        }
        return copyHead.next;
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
     * (#80)
     * Given a sorted array nums, remove the duplicates in-place such that duplicates appeared at most twice and return the new length.
     *
     * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
     *
     * @param nums The sorted array nums.
     * @return The new length.
     */
    public int removeDuplicates(int[] nums) {
        if(nums.length == 0) return 0;
        int tracer = 1;
        int count = 1;
        int prev = nums[0];
        for(int i = 1; i < nums.length; i++) {
            if(prev == nums[i]) {
                count++;
            } else {
                prev = nums[i];
                count = 1;
            }
            if(count <= 2) {
                nums[tracer] = nums[i];
                tracer++;
            }
        }
        return tracer;
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

    static int rows;
    static int cols;
    /**
     * (#289)
     * According to the Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."
     *
     * Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):
     *
     *     Any live cell with fewer than two live neighbors dies, as if caused by under-population.
     *     Any live cell with two or three live neighbors lives on to the next generation.
     *     Any live cell with more than three live neighbors dies, as if by over-population..
     *     Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
     *
     * Write a function to compute the next state (after one update) of the board given its current state. The next state is created by applying the above rules simultaneously to every cell in the current state, where births and deaths occur simultaneously.
     *
     * @param board The Game of Life board.
     */
    public void gameOfLife(int[][] board) {
        cols = board.length;
        rows = board[0].length;

        Set<Pair<Integer, Integer>> points = new HashSet<>();
        for(int y = 0; y < rows; y++) {
            for(int x = 0; x < cols; x++) {
                if(shouldToggle(x, y, board)) {
                    points.add(new Pair<>(x, y));
                }
            }
        }

        Iterator<Pair<Integer, Integer>> itr = points.iterator();
        while(itr.hasNext()) {
            Pair<Integer, Integer> currPoint = itr.next();
            doToggle(currPoint.getKey(), currPoint.getValue(), board);
        }
    }

    public void doToggle(int x, int y, int[][] board) {
        if(board[x][y]==0) {
            board[x][y]=1;
        } else {
            board[x][y]=0;
        }
    }

    public boolean shouldToggle(int x, int y, int[][] grid) {

        int alives = 0;
        if(x-1 >= 0 && grid[x-1][y] == 1) {
            alives++;
        }
        if(y-1 >= 0 && grid[x][y-1] == 1) {
            alives++;
        }
        if(x+1 <= cols-1 && grid[x+1][y] == 1) {
            alives++;
        }
        if(y+1 <= rows-1 && grid[x][y+1] == 1) {
            alives++;
        }
        if(x-1 >= 0 && y-1 >= 0 && grid[x-1][y-1] == 1) {
            alives++;
        }
        if(x-1 >= 0 && y+1 <= rows-1 && grid[x-1][y+1] == 1) {
            alives++;
        }
        if(x+1 <= cols-1 && y-1 >= 0 && grid[x+1][y-1] == 1) {
            alives++;
        }
        if(x+1 <= cols-1 && y+1 <= rows-1 && grid[x+1][y+1] == 1) {
            alives++;
        }

        return ((alives > 3 && grid[x][y] == 1) || (alives < 2 && grid[x][y] == 1) || (alives == 3 && grid[x][y] == 0));
    }

    /**
     * (#442)
     * Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
     *
     * Find all the elements that appear twice in this array.
     *
     * Could you do it without extra space and in O(n) runtime?
     *
     * @param nums The array of integers
     * @return The elements that appear twice.
     */
    public List<Integer> findDuplicates(int[] nums) {
        int[] radix = new int[nums.length];
        for(int num : nums) {
            radix[num-1]++;
        }

        List<Integer> ret = new ArrayList<>();
        for(int i = 0; i < radix.length; i++) {
            if(radix[i] == 2) {
                ret.add(i+1);
            }
        }

        return ret;
    }

    /**
     * (#658)
     * Given a sorted array arr, two integers k and x, find the k closest elements to x in the array.
     * The result should also be sorted in ascending order.
     * If there is a tie, the smaller elements are always preferred.
     *
     * @param arr The sorted array.
     * @param k The number closest.
     * @param x The number target.
     * @return The k closest elements to x.
     */
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> window = new ArrayList<>();

        if(x > arr[arr.length-1]) {
            for(int i = arr.length-k; i < arr.length; i++) {
                window.add(arr[i]);
            }
            return window;
        }

        if(x < arr[0]) {
            for(int i = 0; i < k; i++) {
                window.add(arr[i]);
            }
            return window;
        }

        for(int i = 0; i < arr.length; i++) {
            if(window.size()<k) {
                window.add(arr[i]);
            } else {
                if(Math.abs(arr[i]-x) < Math.abs(x-window.get(0))) {
                    window.remove(0);
                    window.add(arr[i]);
                }
            }
        }

        return window;
    }

    /**
     * (#609)
     * Given a list of directory info including directory path, and all the files with contents in this directory, you need to find out all the groups of duplicate files in the file system in terms of their paths.
     *
     * A group of duplicate files consists of at least two files that have exactly the same content.
     *
     * A single directory info string in the input list has the following format:
     *
     *  "root/d1/d2/.../dm f1.txt(f1_content) f2.txt(f2_content) ... fn.txt(fn_content)"
     *
     * It means there are n files (f1.txt, f2.txt ... fn.txt with content f1_content, f2_content ... fn_content, respectively) in directory root/d1/d2/.../dm. Note that n >= 1 and m >= 0. If m = 0, it means the directory is just the root directory.
     *
     * The output is a list of group of duplicate file paths. For each group, it contains all the file paths of the files that have the same content. A file path is a string that has the following format:
     *
     *  "directory_path/file_name.txt"
     *
     * @param paths The list of directory paths.
     * @return The duplicated files.
     */
    public List<List<String>> findDuplicate(String[] paths) {
        Map<String, List<String>> contentPaths = new HashMap<>();

        for(String path : paths) {

            List<String> mapping = splitPath(path);
            String directory = mapping.remove(0);

            for(String file : mapping) {
                char[] fileName = file.toCharArray();
                int i = fileName.length-2;
                StringBuilder key = new StringBuilder();
                while(fileName[i] != '(') {
                    key.append(fileName[i]);
                    i--;
                }

                List<String> temp = new ArrayList<String>();
                if(contentPaths.containsKey(key.toString())) {
                    temp = contentPaths.get(key.toString());
                }
                temp.add(directory + "/" + file.substring(0,i));
                contentPaths.put(key.toString(), temp);
            }

        }

        List<List<String>> ret = new ArrayList<>();
        for(Map.Entry<String, List<String>> val : contentPaths.entrySet()) {
            if(val.getValue().size() > 1 ) {
                ret.add(val.getValue());
            }
        }
        return ret;
    }

    public List<String> splitPath(String path) {
        List<String> ret = new ArrayList<>();
        StringBuilder s = new StringBuilder();
        for(char letter : path.toCharArray()) {
            if(letter == ' ') {
                ret.add(s.toString());
                s = new StringBuilder();
            } else {
                s.append(letter);
            }
        }
        ret.add(s.toString());
        return ret;
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
                        min = Math.min(nextHigh[j] - i, min);
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

    /**
     * (#791)
     * S and T are strings composed of lowercase letters. In S, no letter occurs more than once.
     *
     * S was sorted in some custom order previously. We want to permute the characters of T so that they match the order that S was sorted. More specifically, if x occurs before y in S, then x should occur before y in the returned string.
     *
     * Return any permutation of T (as a string) that satisfies this property.
     *
     * @param S Sorted string pattern.
     * @param T String to sort.
     * @return Sorted string T based on pattern.
     */
    public String customSortString(String S, String T) {
        Map<Character, Integer> letters = new HashMap<>();
        Set<Character> distincts = new HashSet<>();
        StringBuilder s = new StringBuilder();

        for(char letter : T.toCharArray()) {
            if(letters.containsKey(letter)) {
                int frequency = letters.get(letter);
                letters.put(letter, ++frequency);
            } else {
                letters.put(letter, 1);
                distincts.add(letter);
            }
        }

        for(char letter : S.toCharArray()) {
            if(letters.containsKey(letter)) {
                int frequency = letters.get(letter);
                distincts.remove(letter);
                for(int n = 0; n < frequency; n++) {
                    s.append(letter);
                }
            }
        }

        if(!distincts.isEmpty()) {
            Iterator itr = distincts.iterator();
            while(itr.hasNext()) {
                char letter = (char)itr.next();
                int frequency = letters.get(letter);
                for(int n = 0; n < frequency; n++) {
                    s.append(letter);
                }
                itr.remove();
            }
        }

        return s.toString();
    }

    /**
     * (#885)
     * On a 2 dimensional grid with R rows and C columns, we start at (r0, c0) facing east.
     *
     * Here, the north-west corner of the grid is at the first row and column, and the south-east corner of the grid is at the last row and column.
     *
     * Now, we walk in a clockwise spiral shape to visit every position in this grid.
     *
     * Whenever we would move outside the boundary of the grid, we continue our walk outside the grid (but may return to the grid boundary later.)
     *
     * Eventually, we reach all R * C spaces of the grid.
     *
     * Return a list of coordinates representing the positions of the grid in the order they were visited.
     *
     * @param R The total rows.
     * @param C The total columns.
     * @param r0 The starting row.
     * @param c0 The starting column.
     * @return The list of coordinates.
     */
    public int[][] spiralMatrixIII(int R, int C, int r0, int c0) {
        List<Pair<Integer, Integer>> list = new ArrayList<>();
        int countR = 1;
        int countC = 1;
        int currR, currC;

        list.add(new Pair<>(r0, c0));
        int total = R * C;
        int added = 1;
        while(added < total) {
            currC = 0;
            while( currC++ < countC ) {
                c0++;
                if(r0 >= 0 && c0 >= 0 && r0 < R && c0 < C) {
                    list.add(new Pair<>(r0, c0));
                    added++;
                }
            }
            currR = 0;
            while( currR++ < countR ) {
                r0++;
                if(r0 >= 0 && c0 >= 0 && r0 < R && c0 < C) {
                    list.add(new Pair<>(r0, c0));
                    added++;
                }
            }
            countC++;
            countR++;
            currC = 0;
            while( currC++ < countC ) {
                c0--;
                if(r0 >= 0 && c0 >= 0 && r0 < R && c0 < C) {
                    list.add(new Pair<>(r0, c0));
                    added++;
                }
            }
            currR = 0;
            while( currR++ < countR ) {
                r0--;
                if(r0 >= 0 && c0 >= 0 && r0 < R && c0 < C) {
                    list.add(new Pair<>(r0, c0));
                    added++;
                }
            }
            countC++;
            countR++;
        }

        int index = 0;
        int[][] ret = new int[added][2];
        for(Pair<Integer, Integer> point : list) {
            ret[index][0] = point.getKey();
            ret[index][1] = point.getValue();
            index++;
        }
        return ret;
    }

    /**
     * (#1276)
     * Given two integers tomatoSlices and cheeseSlices. The ingredients of different burgers are as follows:
     *
     *     Jumbo Burger: 4 tomato slices and 1 cheese slice.
     *     Small Burger: 2 Tomato slices and 1 cheese slice.
     *
     * Return [total_jumbo, total_small] so that the number of remaining tomatoSlices equal to 0 and the number of remaining cheeseSlices equal to 0.
     * If it is not possible to make the remaining tomatoSlices and cheeseSlices equal to 0 return [].
     *
     * @param tomatoSlices Number of tomato slices.
     * @param cheeseSlices Number of cheese slices.
     * @return Number of Jumbo and Small burgers.
     */
    public List<Integer> numOfBurgers(int tomatoSlices, int cheeseSlices) {
        List<Integer> pair = new ArrayList<>();
        if( tomatoSlices % 2 == 1 || tomatoSlices < 2*cheeseSlices || tomatoSlices > 4*cheeseSlices ) {
            return pair;
        }

        pair.add((tomatoSlices - 2*cheeseSlices) / 2);
        pair.add((4*cheeseSlices - tomatoSlices) / 2);
        return pair;
    }

}
