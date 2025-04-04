package com.dcm.thread.alg.d23;

import com.dcm.thread.alg.TreeNode;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : yyyao
 * @date : 2025/1/12
 * @description :
 **/
public class SecondTree {


    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inOrder(root, res);
        return res;
    }

    public void inOrder(TreeNode root, List<Integer> res) {
        inOrder(root.getLeft(), res);
        res.add(root.getVal());
        inOrder(root.getRight(), res);
    }

    List<Integer> rightRes = new ArrayList<>();

    //右视图
    public List<Integer> rightSideView(TreeNode root) {
        dfs(root, 0);
        return rightRes;
    }

    private void dfs(TreeNode root, int depth) {
        if (root == null) {
            return;
        }
        if (depth == rightRes.size()) {
            rightRes.add(root.getVal());
        }
        depth++;
        dfs(root.getRight(), depth);
        dfs(root.getLeft(), depth);
    }

    int maxDepth;

    //二叉树的直径
    public int diameterOfBinaryTree(TreeNode root) {
        maxDepth = 0;
        depth(root);
        return maxDepth;
    }

    public int depth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int l, r;
        l = depth(node.getLeft());
        r = depth(node.getRight());
        maxDepth = Math.max(maxDepth, l + r);
        return Math.max(l, r) + 1;
    }

    public int depth2(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int ans = node.getVal();
        int l, r;
        l = depth(node.getLeft());
        r = depth(node.getRight());
        ans = Math.max(ans, Math.max(node.getVal() + l, node.getVal() + r));
        maxDepth = Math.max(maxDepth, Math.max(ans, node.getVal()) + l + r);
        return ans;
    }
}
