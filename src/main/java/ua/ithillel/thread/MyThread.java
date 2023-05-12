package ua.ithillel.thread;

public class MyThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            if (isInterrupted()) {
                break;
            }
            System.out.printf("My Thread %d%n", i);
        }
    }
}
