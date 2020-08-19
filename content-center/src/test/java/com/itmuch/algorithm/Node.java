package com.itmuch.algorithm;

import lombok.Data;

@Data
public class Node {
    private final int value;

    private Node next;

    public Node(int value) {
        this.value = value;
        this.next = null;
    }
}
