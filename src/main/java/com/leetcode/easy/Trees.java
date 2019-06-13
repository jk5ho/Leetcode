package com.leetcode.easy;

import com.leetcode.util.TreeNode;

import java.lang.Math;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Trees {

    /**
     * (#104)
     * Given a binary tree, find its maximum depth.
     *
     * The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
     *
     * Note: A leaf is a node with no children.
     *
     * @param root The binary tree.
     * @return Its maximum depth.
     */
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    /**
     * (#101)
     * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
     *
     * @param root The root of the binary tree.
     * @return Whether it is a mirror.
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isSame(root.left, root.right);
    }

    public boolean isSame(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        } else if (left == null || right == null) {
            return false;
        }

        return (left.val == right.val)
                && isSame(left.left, right.right)
                && isSame(left.right, right.left);
    }

    /**
     * (#102)
     * Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
     *
     * @param root The binary tree.
     * @return The level order traversal.
     */
    public List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> levels = new ArrayList<>();
        if(root == null) return levels;

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);

        while(!queue.isEmpty()){
            int levelSize = queue.size();
            List<Integer> subList = new ArrayList<Integer>();

            // Check all the children of this level
            for(int i = 0; i < levelSize; i++) {
                TreeNode curr = queue.poll();

                if(curr.left != null) {
                    queue.offer(curr.left);
                }
                if(curr.right != null) {
                    queue.offer(curr.right);
                }
                subList.add(curr.val);
            }

            // Append this level
            levels.add(subList);
        }
        return levels;
    }
}