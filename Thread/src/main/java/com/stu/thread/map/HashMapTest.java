package com.stu.thread.map;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author chenxiangwu
 * @title: HashMapTest
 * @projectName my-rmi
 * @description: hashMap 多线程情况下不安全原因
 * rehash -> 操作未加锁
 * @date 2020/8/28 10:39
 */
public class HashMapTest extends Thread {
    static HashMap<Integer, Integer> map=new HashMap<Integer, Integer>(1);
    static AtomicInteger aInteger=new AtomicInteger();
    public void run(){
        while(aInteger.get()<1000000){
            map.put(aInteger.get(), aInteger.get());
            //sysout操作非常耗时，加入将很难遇到冲突。
            //System.out.println(Thread.currentThread().getName()+"put了: "+aInteger.get());
            aInteger.incrementAndGet();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        HashMapTest t=new HashMapTest();
        Thread[] threads=new Thread[5];
        for (int i = 0; i < 5; i++) {
            threads[i]=new Thread(t,"线程"+i);
            threads[i].start();
        }
        //默认还有个守护线程
        while (Thread.activeCount() > 2) {
            //  Thread.currentThread().getThreadGroup().list();
            Thread.yield();
        }
        System.out.println(t.map.size());
        int count=0;
        for(int i=0;i<1000000;i++){
            if(!t.map.containsKey(i)){
                count++;
                //System.out.println("key: "+i+" is not contains");
            }
        }
        System.out.println(t.map.size());
        System.out.println(count);
    }
}
