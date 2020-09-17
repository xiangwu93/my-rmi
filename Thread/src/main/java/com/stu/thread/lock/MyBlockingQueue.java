package com.stu.thread.lock;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chenxiangwu
 * @title: MyBlockingQueue
 * @projectName my-rmi
 * @description: reentrantLock + condition 实现简单的阻塞队列
 * @date 2020/8/27 15:16
 */
public class MyBlockingQueue<E> {
    int size;
    ReentrantLock lock = new ReentrantLock();
    LinkedList<E> list = new LinkedList();
    Condition notFull = lock.newCondition();
    Condition notEmpty = lock.newCondition();

    public MyBlockingQueue(int size) {
        this.size = size;
    }

    //入队
    public void enqueue(E e) throws InterruptedException {
        lock.lock();
        try {
            while (list.size() == size){
                notFull.await();
            }
            list.add(e);
            System.out.println("入队：" + e);
            notEmpty.signal();
        }  finally {
            lock.unlock();
        }
    }

    public E dequeue() throws InterruptedException {
        E e;
        lock.lock();
        try {
            while (list.size() == 0){
                notEmpty.await();
            }
            e = list.removeFirst();
            System.out.println("出队："+ e);
            notFull.signal();
            return e;
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        MyBlockingQueue queue = new MyBlockingQueue(2);
        for (int i = 0; i< 10; i++){
            int data = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        queue.enqueue(data);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        for (int i= 0; i< 10 ;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        int data = (int) queue.dequeue();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
