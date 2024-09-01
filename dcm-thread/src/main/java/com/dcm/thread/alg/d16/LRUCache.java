package com.dcm.thread.alg.d16;

import com.dcm.thread.alg.TreeNode;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : yyyao
 * @date : 2024/4/20
 * @description :
 **/
public class LRUCache {


    private Map<Integer, TreeNode> cache = new HashMap<>();

    private int size;

    private int capacity;

    private TreeNode head, tail;


    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        head = new TreeNode();
        tail = new TreeNode();
        head.setRight(tail);
        tail.setLeft(head);
    }

    public int get(int key) {
        TreeNode node = cache.get(key);
        if (node == null) {
            return -1;
        }
        moveToHead(node);
        return node.getVal();
    }

    public void put(int key, int value) {
        TreeNode node = cache.get(key);
        if (node == null) {
            //
            TreeNode newNode = new TreeNode(key, value);
            cache.put(key, node);
            addToHead(newNode);
            ++size;
            if (size > capacity) {
                TreeNode tailNode = removeTail();

                cache.remove(tailNode.getKey());
                --size;
            }

        } else {
            node.setVal(value);
            moveToHead(node);
            cache.put(key,node);
        }
    }


    private void addToHead(TreeNode node) {
        node.setLeft(head);
        node.setRight(head.getRight());
        head.getRight().setLeft(node);
        head.setRight(node);
    }


    private void removeNode(TreeNode node) {
        node.getLeft().setRight(node.getRight());
        node.getRight().setLeft(node.getLeft());
    }

    private void moveToHead(TreeNode node) {
        removeNode(node);
        addToHead(node);

    }


    private TreeNode removeTail() {
        TreeNode node = tail.getLeft();
        removeNode(node);
        return node;
    }

}
