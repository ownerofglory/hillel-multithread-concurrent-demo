package ua.ithillel.counter;

import java.util.concurrent.locks.Lock;

public class Counter {
    private int count;

    public int incrementAndGet() {
        synchronized (this) {
            return ++count;
        }

    }

    public static void printClassName() {
        synchronized (Counter.class) {
            System.out.println(Counter.class.getName());
        }
    }
}
