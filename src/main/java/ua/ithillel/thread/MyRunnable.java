package ua.ithillel.thread;

public class MyRunnable implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            if (Thread.currentThread().isInterrupted()) {
                break;
            }
            System.out.printf("My Runnable %d%n", i);
        }
    }
}
