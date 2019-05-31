package com.leetcode.easy;

import com.leetcode.util.TreeNode;

import java.lang.Math;

public class Trees {

    /**
     * (#104)
     * Given a binary tree, find its maximum depth.
     *
     * The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
     *
     * Note: A leaf is a node with no children.
     *
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if(root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}
