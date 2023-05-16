package ua.ithillel.counter;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SemaphoreCounter {
    private int count;
    private Lock lock;
    private Semaphore semaphore;

    public SemaphoreCounter() {
        // fair
        lock = new ReentrantLock(true);
        semaphore = new Semaphore(2);
    }

    public int incrementAndGet() {
        try {
            lock.lock();

            count = increment(count);
        } finally {
            lock.unlock();
        }


        return count;
    }

    public int getCount() {
        try {
            semaphore.acquire();

        } finally {
            semaphore.release();
            return count;
        }
    }



    private int increment(int i) {
       try {
           lock.lock();
           i = ++i;
       } finally {
           lock.unlock();
       }
        return i;
    }

}
