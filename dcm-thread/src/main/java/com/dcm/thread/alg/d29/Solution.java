package com.dcm.thread.alg.d29;

import com.dcm.thread.alg.TreeNode;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author : yyyao
 * @date : 2025/3/20
 * @description :
 **/
public class Solution {


    //求根节点到叶子节点数字之和
    public int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }

    private int dfs(TreeNode root, int prevSum) {

        if (root == null) {
            return 0;
        }
        int sum = root.getVal() + prevSum * 10;

        if (root.getLeft() == null && root.getRight() == null) {
            return sum;
        } else {
            return dfs(root.getLeft(), sum) + dfs(root.getRight(), sum);
        }

    }


    public boolean isValidBST(TreeNode root) {
        // return recurse(root, null, null);
        //中序遍历法

        Stack<TreeNode> stack = new Stack<>();

        Integer inorder = null;

        while (!stack.isEmpty() || root != null) {

            while (root != null) {
                stack.push(root);
                root = root.getLeft();
            }
            root = stack.pop();
            if (inorder != null && root.getVal() <= inorder) {
                return false;
            }
            inorder = root.getVal();
            root = root.getRight();
        }
        return true;

    }

    private boolean recurse(TreeNode root, Integer lower, Integer upper) {
        if (root == null) {
            return false;
        }

        if (lower != null && root.getVal() <= lower) {
            return false;
        }
        if (upper != null && root.getVal() >= upper) {
            return false;
        }

        if (!recurse(root.getLeft(), lower, root.getVal())) {
            return false;
        }
        return recurse(root.getRight(), root.getVal(), upper);
    }




}
