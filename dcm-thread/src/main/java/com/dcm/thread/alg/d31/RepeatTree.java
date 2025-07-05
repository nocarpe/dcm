package com.dcm.thread.alg.d31;

import com.dcm.thread.alg.TreeNode;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author : yyyao
 * @date : 2025/6/10
 * @description :
 **/
public class RepeatTree {

    //层序遍历
    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return result;
        }

        ideaTree(root, 0);

        return result;
    }


    public void ideaTree(TreeNode node, int level) {
        if (result.size() == level) {
            result.add(new ArrayList<>());
        }
        result.get(level).add(node.getVal());
        if (node.getLeft() != null) {
            ideaTree(node.getLeft(), level + 1);
        }
        if (node.getRight() != null) {
            ideaTree(node, level + 1);
        }
    }



    public List<List<Integer>> zcardTree(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if (root == null) {
            return res;
        }

        boolean leftFlag = true;
        Queue<TreeNode> nodeQueue = new ArrayDeque<>();
        nodeQueue.offer(root);

        while (!nodeQueue.isEmpty()) {
            Deque<Integer> levelQ = new LinkedList<>();
            int size = nodeQueue.size();

            for (int i = 0; i < size; i++) {
                TreeNode curNode = nodeQueue.poll();
                if (leftFlag) {
                    levelQ.offerLast(curNode.getLeft().getVal());
                } else {
                    levelQ.offerFirst(curNode.getRight().getVal());
                }
                if (curNode.getLeft() != null) {
                    nodeQueue.offer(curNode.getLeft());
                }

                if (curNode.getRight() != null) {
                    nodeQueue.offer(curNode.getRight());
                }
            }
            res.add(new LinkedList<>(levelQ));
            leftFlag = !leftFlag;
        }

        return res;

    }

}
