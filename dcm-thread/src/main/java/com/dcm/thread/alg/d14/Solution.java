package com.dcm.thread.alg.d14;

import com.dcm.thread.alg.TreeNode;
import com.sun.jmx.remote.internal.ArrayQueue;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author : yyyao
 * @date : 2024/3/2
 * @description :二叉树的层序遍历
 **/
public class Solution {

    /**
     * 前序遍历
     */
    public void preOrder(TreeNode subTree) {
        if (subTree == null) {
            return;
        }
        System.out.println("key:" + subTree.getVal());
        preOrder(subTree.getLeft());
        preOrder(subTree.getRight());
    }

    /**
     * 中序遍历
     */
    public void inOrder(TreeNode subTree) {

        if (subTree == null) {
            return;
        }
        inOrder(subTree.getLeft());
        System.out.println("key:" + subTree.getVal());
        inOrder(subTree.getRight());
    }

    /**
     * 后序遍历
     */
    public void postOrder(TreeNode subTree) {
        if (subTree == null) {
            return;
        }
        preOrder(subTree.getLeft());
        preOrder(subTree.getRight());
        System.out.println("key:" + subTree.getVal());

    }

    List<List<Integer>> results = new ArrayList<>();

    //层序遍历
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return results;
        }
        helper(root, 0);
        return results;
    }

    public void helper(TreeNode subTree, int level) {
        if (results.size() == level) {
            results.add(new ArrayList<>());
        }
        results.get(level).add(subTree.getVal());
        if (subTree.getLeft() != null) {
            helper(subTree.getLeft(), level + 1);
        }
        if (subTree.getRight() != null) {
            helper(subTree.getRight(), level + 1);
        }


    }


    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();

        //        queue.offer(root);
        queue.add(root);
        while (!queue.isEmpty()) {

            List<Integer> level = new ArrayList<>();
            int currentSize = queue.size();

            for (int i = 1; i < currentSize; ++i) {
                TreeNode node = queue.poll();
                level.add(node.getVal());
                if (node.getLeft() != null) {
                    queue.offer(node.getLeft());
                }
                if (node.getRight() != null) {
                    queue.offer(node.getRight());
                }


            }
            result.add(level);

        }
        return result;

    }

    //二叉树的最近公共祖先
    //
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

        if ((lson && rson) || ((root.getVal() == p.getVal() || root.getVal() == q.getVal()) && (lson || rson))) {
            ans = root;
        }

        return lson || rson || (root.getVal() == p.getVal() || root.getVal() == q.getVal());

    }

    // 二叉树的锯齿形层序遍历
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();

        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();

        boolean flag = true;

        queue.offer(root);
        while (!queue.isEmpty()) {
            Deque<Integer> level = new LinkedList<>();
            int currentSize = queue.size();//本层的节点数量
            System.out.println("遍历时的queue size:"+currentSize);
            for (int i = 1; i <= currentSize; ++i) {
                //从右往左
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


    public List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        boolean flag = true;
        queue.offer(root);

        while (!queue.isEmpty()) {
            Deque<Integer> level = new LinkedList<>();
            int currentSize = queue.size();

            for (int i = 1; i <= currentSize; i++) {
                TreeNode temp = queue.poll();

                if (flag) {
                    level.offerLast(temp.getVal());
                } else {
                    level.offerFirst(temp.getVal());
                }

                if (temp.getLeft() != null) {
                    queue.offer(temp.getLeft());
                }
                if (temp.getRight() != null) {
                    queue.offer(temp.getRight());
                }

            }
            result.add(new LinkedList<>(level));
            flag = !flag;


        }

        return result;
    }

    //二叉树最大深度

    public int maxDepth(TreeNode root){

        if (root ==null) return 0;

        return Math.max(maxDepth(root.getLeft()),maxDepth(root.getRight())) +1;
    }

}
