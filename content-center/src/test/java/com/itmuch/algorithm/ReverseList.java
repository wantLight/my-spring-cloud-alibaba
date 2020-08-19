package com.itmuch.algorithm;

/**
 * 反转链表
 * 1 -》 2 -》 3 -》 4 —》 null
 */
public class ReverseList {

    /**
     * 递归解法 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * @param head
     * @return
     */
    public Node reverseListedList(Node head){
        if (head == null || head.getNext() == null){
            return head;
        }
        //将1号节点以后的链表反转
        Node newHead = reverseListedList(head.getNext());
        //以后的链表的下一个指向头节点
        head.getNext().setNext(head);
        //头节点的下一个指向指向null
        head.setNext(null);
        return newHead;
    }

    /**
     * 迭代解法 - 优秀一点
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * @param head
     * @return
     */
    public Node reverseList(Node head) {
        Node prev = null;

        while (head != null){
            Node nxt =  head.getNext();
            // 翻转箭头
            head.setNext(prev);
            // 前面的=现在的
            prev = head;
            // 现在的=下一个
            head = nxt;
        }
        return prev;
    }
}
