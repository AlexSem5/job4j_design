package ru.job4j.threads;

public class ThreadTester1 {
    public static void main(String[] args) {
        Runnable threadJob = new MyRunnable();
        Thread myThread = new Thread(threadJob, "headFirst");
        myThread.start();
        System.out.println(Thread.currentThread().getName()
                           + ": back in main");
        Thread.dumpStack();
    }
}

class MyRunnable implements Runnable {
    
    @Override
    public void run() {
        go();
    }
    
    public void go() {
        doMore();
    }
    
    public void doMore() {
        System.out.println(Thread.currentThread().getName()
                           + ": top o' the stack");
        Thread.dumpStack();
    }
}
