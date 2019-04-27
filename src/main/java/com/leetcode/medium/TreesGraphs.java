package com.leetcode.medium;

import com.leetcode.util.TreeNode;

import java.util.ArrayList;
import java.util.List;

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

}
