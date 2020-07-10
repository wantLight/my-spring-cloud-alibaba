package com.itmuch.usercenter;

import lombok.Data;

@Data
public class TreeNode {
      Integer val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
}
