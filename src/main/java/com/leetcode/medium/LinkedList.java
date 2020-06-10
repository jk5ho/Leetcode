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

    /**
     * (#328)
     * Given a singly linked list, group all odd nodes together followed by the even nodes. Please note here we are talking about the node number and not the value in the nodes.
     *
     * You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.
     *
     * @param head The singly linked list.
     * @return The modified linked list.
     */
    public ListNode oddEvenList(ListNode head) {
        if(head == null) return null;

        ListNode odd = head;
        ListNode even = head.next;
        ListNode second = even;

        while(odd.next != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = second;

        return head;
    }

    /**
     * (#160)
     * Write a program to find the node at which the intersection of two singly linked lists begins.
     *
     * @param headA First linked List.
     * @param headB Second Linked List.
     * @return Intersection of two singly linked list.
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null) return null;
        if(headA == headB) return headA;

        int lengthA = 1;
        ListNode currA = headA;
        while(currA.next != null) {
            currA = currA.next;
            lengthA++;
        }

        int lengthB = 1;
        ListNode currB = headB;
        while(currB.next != null) {
            currB = currB.next;
            lengthB++;
        }

        if(lengthA > lengthB) {
            for(int a = 0; a < lengthA-lengthB; a++) {
                headA = headA.next;
            }
        } else {
            for(int b = 0; b < lengthB- lengthA; b++) {
                headB = headB.next;
            }
        }

        while(headA!=null||headB!=null) {
            if(headA == headB) {
                return headA;
            }
            headA=headA.next;
            headB=headB.next;
        }
        return null;
    }
}
