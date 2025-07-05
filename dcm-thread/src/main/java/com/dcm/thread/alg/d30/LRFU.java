package com.dcm.thread.alg.d30;

import com.dcm.thread.alg.TreeNode;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : yyyao
 * @date : 2025/4/12
 * @description :
 **/
public class LRFU {

    private TreeNode head;
    private TreeNode tail;
    private int capacity;
    private int size;
    private Map<Integer, TreeNode> cacheMap;


    public LRFU(int capacity) {
        this.capacity = capacity;
        this.cacheMap = new HashMap<>();
        head = new TreeNode();
        tail = new TreeNode();
        head.setRight(head);
        tail.setLeft(head);
    }


    public int get(Integer key) {
        TreeNode node = cacheMap.get(key);
        if (node == null) {
            return -1;
        }
        //移到队首
        moveNodetoHead(node);
        return node.getVal();

    }

    public void put(int key, int value) {
        TreeNode node = cacheMap.get(key);
        if (node == null) {
            TreeNode tmp = new TreeNode(key,value);
            cacheMap.put(key,tmp);
            ++size;
            addNodetoHead(tmp);
            if(size>capacity){
                //移除队尾元素
              TreeNode tail =  removeTail();
              cacheMap.remove(tail.getKey());
              size--;
            }
            return;
        }
        node.setVal(value);
        moveNodetoHead(node);

    }

    private void moveNodetoHead(TreeNode tmp) {
        removeNode(tmp);
        addNodetoHead(tmp);
    }

    private TreeNode removeTail() {
      TreeNode node= tail.getLeft();
      removeNode(node);
        return node;
    }

    private void removeNode(TreeNode node) {
        node.getLeft().setRight(node.getRight());
        node.getRight().setLeft(node.getLeft());
    }


    private void addNodetoHead(TreeNode node) {
        node.setRight(head.getRight());
        node.setLeft(head);
        head.getRight().setLeft(node);
        head.setRight(node);
    }


}
