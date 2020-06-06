package com.leetcode.medium;

import com.leetcode.util.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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

}
