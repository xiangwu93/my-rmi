package com.stu.thread.lock;

/**
 * @author chenxiangwu
 * @title: ThreadTest
 * @projectName my-rmi
 * @description:
 *
 * wait和sleep的区别
 * 1.sleep是线程中的方法，wait是Object中的方法
 * 2.sleep不会释放lock，wait会释放，而且会加入到等待队列中
 * 3.sleep不依赖synchronized，wait需要依赖synchronized
 * 4.sleep不需要被唤醒，休眠之后推出阻塞，wait需要（不指定时间需要被中断）
 *
 * https://blog.csdn.net/qq_20009015/article/details/89980966
 *
 * @date 2020/8/27 16:59
 */
public class WaitAndSleep {
}
