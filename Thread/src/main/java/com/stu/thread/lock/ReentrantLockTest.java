package com.stu.thread.lock;

import java.sql.Time;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * synchronized 和 reentrantLock 都是重入锁/独占锁
 * <p>
 * 1.synchronized 加锁解锁的过程是隐式的, reentrantLock 需要手动控制 且加锁解锁次数必须一致
 * 2.reentrantLock 可以实现公平锁，响应中断以及获取锁限时等待
 * 3.reentrantLock 和 condition 结合可实现线程等待通知机制; synchronized 和 wait，notify实现线程的等待通知机制
 * https://www.jb51.net/article/105762.htm
 *
 * @author chenxiangwu
 * @title: ReentrantLockTest
 * @projectName my-rmi
 * @description: TODO
 * @date 2020/8/27 14:36
 */
public class ReentrantLockTest {
    static Lock lock1 = new ReentrantLock();
    static Lock lock2 = new ReentrantLock();

    public static void main(String[] args) {
        Thread thread1 = new Thread(new ThreadDemo1(lock1, lock2));
        Thread thread2 = new Thread(new ThreadDemo1(lock2, lock1));
        thread1.start();
        thread2.start();
//        thread1.interrupt();
    }

    //公平锁

    /**
     * 可响应中断
     */
    static class ThreadDemo implements Runnable {

        Lock firstLock;
        Lock secondLock;

        public ThreadDemo(Lock firstLock, Lock secondLock) {
            this.firstLock = firstLock;
            this.secondLock = secondLock;
        }

        @Override
        public void run() {
            try {
                firstLock.lockInterruptibly();
                TimeUnit.MILLISECONDS.sleep(10);
                secondLock.lockInterruptibly();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                firstLock.unlock();
                secondLock.unlock();
                System.out.println(Thread.currentThread().getName() + "：正常结束");

            }
        }
    }

    /**
     * 获取锁限时等待
     */
    static class ThreadDemo1 implements Runnable {
        Lock firstLock;
        Lock secondLock;

        public ThreadDemo1(Lock firstLock, Lock secondLock) {
            this.firstLock = firstLock;
            this.secondLock = secondLock;
        }

        @Override
        public void run() {

            try {
                while (!lock1.tryLock()) {
                    TimeUnit.MILLISECONDS.sleep(10);
                }
                while (!lock2.tryLock()) {
                    lock1.unlock();
                    TimeUnit.MILLISECONDS.sleep(10);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                firstLock.unlock();
                secondLock.unlock();
                System.out.println(Thread.currentThread().getName() + ":正常结束");
            }
        }
    }

    static class ThreadDemo2 implements Runnable {

        @Override
        public void run() {
            try {
            } catch (Exception e) {
                e.printStackTrace();
            } finally {

            }
        }
    }

    static class ConditionTest {
        static ReentrantLock lock = new ReentrantLock();
        static Condition condition = lock.newCondition();

        public static void main(String[] args) {
            lock.lock();
            new Thread(new SignalThread()).start();
            System.out.println("主线程等待通知");
            try {
                condition.await();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            System.out.println("主线程恢复运行");
        }

        static class SignalThread implements Runnable {

            @Override
            public void run() {
                lock.lock();
                try {
                    condition.signal();
                    System.out.println("子线程通知");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

}
