package com.leetcode.medium;

import com.leetcode.util.TreeNode;
import javafx.util.Pair;

import java.util.*;
import java.util.LinkedList;

public class TreesGraphs {

    /**
     * (#94)
     * Given a binary tree, return the inorder traversal of its nodes' values.
     *
     * @param root The tree root node.
     * @return The inorder traversal.
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();

        if(root == null) {
            return new ArrayList<>();
        }

        if(root.left != null) {
            list.addAll(inorderTraversal(root.left));
        }
        list.add(root.val);
        if(root.right != null) {
            list.addAll(inorderTraversal(root.right));
        }

        return list;
    }

    /**
     * (#116)
     * You are given a perfect binary tree where all leaves are on the same level, and every parent has two children.
     *
     * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
     *
     * Initially, all next pointers are set to NULL.
     *
     * @param root The perfect binary tree.
     * @return The tree with populated level order next pointer.
     */
    public TreeNode connect(TreeNode root) {
        if(root == null) return null;
        int level = 1;
        int pops = 0;
        Queue<TreeNode> frontier = new LinkedList<TreeNode>();
        frontier.add(root);

        while(!frontier.isEmpty()) {
            TreeNode curr = frontier.remove();
            pops++;
            if(pops == level) {
                level = level << 1;
                pops = 0;
                curr.next = null;
            } else {
                curr.next = frontier.peek();
            }

            if(curr.left != null || curr.right != null) {
                frontier.add(curr.left);
                frontier.add(curr.right);
            }
        }
        return root;
    }

    /**
     * (#230)
     * Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
     *
     * Note:
     * You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
     *
     * @param root The tree root node.
     * @param k The kth value.
     * @return The kth smallest node.
     */
    public int kthSmallest(TreeNode root, int k) {
        Object[] array = inorderTraversal(root).toArray();
        return (int) array[k-1];
    }



    static Set<Pair<Integer, Integer>> ones = new HashSet<>();
    static int cols;
    static int rows;
    /**
     * (#200)
     * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands.
     * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
     * You may assume all four edges of the grid are all surrounded by water.
     *
     * @param grid The 2d grid map.
     * @return The number of islands.
     */
    public int numIslands(char[][] grid) {
        if(grid.length == 0) return 0;
        cols = grid.length;
        rows = grid[0].length;
        int count = 0;

        for(int x = 0; x < cols; x++) {
            for(int y = 0; y < rows; y++) {
                if(grid[x][y] == '1') {
                    ones.add(new Pair<>(x, y));
                }
            }
        }

        Iterator<Pair<Integer, Integer>> iter;
        while(!ones.isEmpty()) {
            iter = ones.iterator();
            if(iter.hasNext()) {
                Pair<Integer, Integer> curr = iter.next();
                BFS(curr.getKey(), curr.getValue(), grid);
                count++;
            }
        }

        return count;
    }

    public void BFS(int x, int y, char[][] grid) {
        Queue<Pair<Integer, Integer>> frontier = new LinkedList<>();
        frontier.add(new Pair<>(x, y));
        while(!frontier.isEmpty()) {
            Pair<Integer, Integer> curr = frontier.remove();
            ones.remove(curr);
            int currX = curr.getKey();
            int currY = curr.getValue();
            grid[currX][currY] = '0';

            if(currY-1>=0 && grid[currX][currY-1]=='1') {
                curr = new Pair<>(currX, currY-1);
                if(!frontier.contains(curr)) {
                    frontier.add(curr);
                }
            }
            if(currX+1<cols && grid[currX+1][currY]=='1') {
                curr = new Pair<>(currX+1, currY);
                if(!frontier.contains(curr)) {
                    frontier.add(curr);
                }
            }
            if(currY+1<rows && grid[currX][currY+1]=='1') {
                curr = new Pair<>(currX, currY+1);
                if(!frontier.contains(curr)) {
                    frontier.add(curr);
                }
            }
            if(currX-1>=0 && grid[currX-1][currY]=='1') {
                curr = new Pair<>(currX-1, currY);
                if(!frontier.contains(curr)) {
                    frontier.add(curr);
                }
            }
        }
    }
    
}
