package com.leetcode.medium;

import com.leetcode.util.ListNode;

public class LinkedList {

    /**
     * (#2)
     * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
     *
     * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
     *
     * @param l1 The first linked list.
     * @param l2 The second linked list.
     * @return The added linked list.
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode ret = new ListNode(0);
        int carryOver;

        ListNode head = new ListNode(0);
        head.next = ret;

        int sum = l1.val + l2.val;
        if(sum >= 10) {
            ret.val = sum % 10;
            carryOver = 1;
        } else {
            ret.val = sum;
            carryOver = 0;
        }

        while(l1.next != null | l2.next != null | carryOver == 1) {

            if(l1.next == null) {
                l1.next = new ListNode(0);
            }

            if(l2.next == null) {
                l2.next = new ListNode(0);
            }

            l1 = l1.next;
            l2 = l2.next;

            ListNode node = new ListNode(carryOver);
            sum = node.val + l1.val + l2.val;
            if(sum >= 10) {
                node.val = sum % 10;
                carryOver = 1;
            } else {
                node.val = sum;
                carryOver = 0;
            }

            ret.next = node;
            ret = ret.next;
        }
        return head.next;
    }

}
