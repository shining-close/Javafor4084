package Example.Collection.Queue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.PriorityQueue;

public class QueueTest {
    public static void main(String[] args) {
        // 1. LinkedList实现普通队列（FIFO）
        Queue<String> queue = new LinkedList<>();
        // 入队（add()/offer()，add()失败抛异常，offer()返回false）
        queue.offer("任务1");
        queue.offer("任务2");
        queue.offer("任务3");
        System.out.println("队列元素：" + queue); // [任务1, 任务2, 任务3]

        // 出队（poll()/remove()，poll()空队列返回null，remove()抛异常）
        String task = queue.poll();
        System.out.println("出队元素：" + task); // 任务1
        System.out.println("出队后队列：" + queue); // [任务2, 任务3]

        // 查看队头元素（peek()/element()）
        System.out.println("当前队头：" + queue.peek()); // 任务2

        // 2. PriorityQueue实现优先队列（按优先级排序，默认升序）
        Queue<Integer> priorityQueue = new PriorityQueue<>();
        priorityQueue.offer(3);
        priorityQueue.offer(1);
        priorityQueue.offer(2);
        System.out.println("优先队列出队顺序：");
        while (!priorityQueue.isEmpty()) {
            System.out.println(priorityQueue.poll()); // 1 → 2 → 3（按优先级）
        }
    }
}
