package ua.ithillel.counter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockCounter {
    private int count;
    private Lock lock;

    public LockCounter() {
        // fair
        lock = new ReentrantLock(true);
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
        return count;
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
