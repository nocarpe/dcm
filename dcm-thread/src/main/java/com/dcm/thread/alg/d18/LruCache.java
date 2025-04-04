package com.dcm.thread.alg.d18;

import com.dcm.thread.alg.TreeNode;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : yyyao
 * @date : 2024/9/1
 * @description :惰性淘汰策略 移除队列中最近最不经常使用的元素
 **/
public class LruCache {


    private Map<Integer, TreeNode> cache = new HashMap<>();

    private int capacity;

    private int size;
    private TreeNode head, tail;

    public LruCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        head = new TreeNode();
        tail = new TreeNode();
        head.setRight(tail);
        tail.setLeft(head);
    }


    //put
    public void put(int key, int value) {
        TreeNode node = cache.get(key);
        if (node == null) {
            TreeNode newNode = new TreeNode(key, value);
            cache.put(key, node);
            addToHead(newNode);
            ++size;
            if (size > capacity) {
                //移除尾部
                TreeNode tailNode = removeTail();
                cache.remove(tailNode.getKey());
                --size;
            }

        } else {
            node.setVal(value);
            //移至队首
            moveToHead(node);
            cache.put(key, node);
        }

    }


    //get
    public int get(int key) {
        TreeNode node = cache.get(key);
        if (node == null) {
            return -1;
        }
        moveToHead(node);
        return node.getVal();
    }

    public void addToHead(TreeNode node) {
        node.setLeft(head);
        node.setRight(head.getRight());
        head.getRight().setLeft(node);
        head.setRight(node);

    }
    public void moveToHead(TreeNode node){
        removeNode(node);
        addToHead(node);
    }

    private void removeNode(TreeNode node) {
        node.getLeft().setRight(node.getRight());
        node.getRight().setLeft(node.getLeft());
    }

    private TreeNode removeTail() {
        TreeNode node = tail.getLeft();
        removeNode(node);
        return node;
    }


}
