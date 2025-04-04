package com.dcm.thread.alg.d20;

import com.dcm.thread.alg.TreeNode;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author : yyyao
 * @date : 2024/12/17
 * @description :
 **/
public class NodeListSolution {


    List<List<Integer>> results = new ArrayList<>();

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return results;
        }
        helper(root, 0);
        return results;
    }

    private void helper(TreeNode root, int level) {
        if (results.size() == level) {
            results.add(new ArrayList<>());
        }
        results.get(level).add(root.getVal());
        if (root.getLeft() != null) {
            helper(root.getLeft(), level + 1);
        }
        if (root.getRight() != null) {
            helper(root.getRight(), level + 1);
        }

    }

    public List<List<Integer>> zigzagLevelSort(TreeNode root) {
        List<List<Integer>> results = new ArrayList<>();
        if (root == null) {
            return results;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        boolean flag = true;
        queue.offer(root);
        while (!queue.isEmpty()) {
            Deque<Integer> level = new LinkedList<>();
            int currentSize = queue.size();

            for (int i = 1; i <= currentSize; ++i) {
                TreeNode node = queue.poll();
                if (flag) {
                    level.offerLast(node.getVal());
                } else {
                    level.offerFirst(node.getVal());
                }

                if (node.getLeft() != null) {
                    queue.offer(node.getLeft());
                }
                if (node.getRight() != null) {
                    queue.offer(node.getRight());
                }


            }
            results.add(new LinkedList<>(level));
            flag=!flag;


        }
        return results;


    }


}
