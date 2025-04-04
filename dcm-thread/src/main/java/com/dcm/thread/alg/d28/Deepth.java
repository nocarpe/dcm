package com.dcm.thread.alg.d28;

import com.dcm.thread.alg.TreeNode;
import java.util.*;

/**
 * @author : yyyao
 * @date : 2025/2/26
 * @description :
 **/
public class Deepth {


    /**
     * 前序遍历
     */
    public void preOrder(TreeNode subTree) {
        if (subTree == null) {
            return;
        }
        System.out.println(subTree.getVal());
        preOrder(subTree.getLeft());
        preOrder(subTree.getRight());
    }


    public void postOrder(TreeNode subTree) {
        postOrder(subTree.getLeft());
        postOrder(subTree.getRight());
        System.out.println(subTree.getVal());
    }


    private TreeNode ans = null;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root, p, q);
        return ans;
    }

    private boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return false;
        }
        boolean lson = dfs(root.getLeft(), p, q);
        boolean rson = dfs(root.getRight(), p, q);
        if ((lson && rson) || ((root.getVal() == p.getVal()) || root.getVal() == q.getVal()) && (lson || rson)) {
            ans = root;
        }
        return lson || rson || (root.getVal() == p.getVal() || root.getVal() == q.getVal());
    }


    private boolean dfszuidagogngong(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return false;
        }
        boolean lson = dfszuidagogngong(root.getLeft(), p, q);
        boolean rson = dfszuidagogngong(root.getRight(), p, q);

        if ((lson & rson) || ((root.getVal() == p.getVal() || root.getVal() == q.getVal()) && (lson || rson))) {
            ans = root;
        }
        return lson || rson || (root.getVal() == p.getVal() || root.getVal() == q.getVal());
    }


    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.getLeft()), maxDepth(root.getRight())) + 1;
    }


    public boolean isSymmetric(TreeNode node) {
        return check(node.getLeft(), node.getRight());
    }

    public boolean check(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        return p.getVal() == q.getVal() && check(p.getLeft(), q.getRight()) && check(p.getRight(), q.getLeft());
    }

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        } else {
            return Math.abs(treeHeight(root.getLeft()) - treeHeight(root.getRight())) <= 1 && isBalanced(root.getLeft())
                && isBalanced(root.getRight());
        }
    }

    public int treeHeight(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            return Math.max(treeHeight(root.getLeft()), treeHeight(root.getRight())) + 1;
        }
    }


    public List<List<Integer>> zigzaglevelOrder(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        if (root == null) {
            return result;
        }
        boolean flag = true;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Deque<Integer> level = new LinkedList<>();
            int currentSize = queue.size();//本层的节点数量
            for (int i = 1; i <= currentSize; i++) {
                //从左往右
                TreeNode node = queue.poll();
                if (flag) {
                    level.offerLast(node.getVal());
                } else {
                    level.offerFirst(node.getVal());
                }
                level.add(node.getVal());
                if (node.getLeft() != null) {
                    queue.offer(node.getLeft());
                }
                if (node.getRight() != null) {
                    queue.offer(node.getRight());
                }

            }
            result.add(new LinkedList<>(level));
            flag = !flag;
        }

        return result;
    }

}
