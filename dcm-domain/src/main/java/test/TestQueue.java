package test;


public class TestQueue {

    private int[] data;

    public synchronized void offer(int num) {
        if (data == null) {
            data = new int[1];
            data[0] = num;
        } else {
            int len = data.length;
            if (len == Integer.MAX_VALUE) {
                //队列元素已满
                return;
            }
            int[] temp = new int[len + 1];
            for (int i = 0; i < len; i++) {
                temp[i] = data[i];
            }
            temp[len] = num;
            data = temp;
        }
    }


    public synchronized int peek() {
        if (data == null) {
            return 0;
        }
        int len = data.length;
        int[] temp = new int[len - 1];
        int res = data[0];
        for (int i = 1; i < len; i++) {
            temp[i - 1] = data[i];
        }
        data = temp;

        return res;
    }


    public static void main(String[] args) {
        TestQueue testQueue = new TestQueue();

        testQueue.offer(1);
        testQueue.offer(2);
        testQueue.offer(3);
        System.out.println(testQueue.peek());
        System.out.println(testQueue.peek());
        testQueue.offer(4);
        testQueue.offer(5);
        testQueue.offer(6);
        System.out.println(testQueue.peek());
        System.out.println(testQueue.peek());
        System.out.println(testQueue.peek());
        System.out.println(testQueue.peek());

    }

}
