package ru.job4j.threads;

public class ThreadTester {
    public static void main(String[] args) {
        Thread myThread = new Thread(() -> go(), "headFirst");
        myThread.start();
        System.out.println(Thread.currentThread().getName() + ": back in main");
        Thread.dumpStack();
    }
    
    private static void go() {
        doMore();
    }
    
    private static void doMore() {
        System.out.println(Thread.currentThread().getName() + ":top 0' the stack");
        Thread.dumpStack();
    }
}
