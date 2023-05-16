package ua.ithillel.counter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockCounter {
    private int count;
    private ReentrantReadWriteLock lock;
    private Lock readLock;
    private Lock wrireLock;


    public ReadWriteLockCounter() {
        // fair
        lock = new ReentrantReadWriteLock(true);
        readLock = lock.readLock();
        wrireLock = lock.writeLock();
    }

    public int incrementAndGet() {
        try {
            wrireLock.lock();

            count = increment(count);
        } finally {
            wrireLock.unlock();
        }


        return count;
    }

    public int getCount() {
        try {
            readLock.lock();
        } finally {
            readLock.unlock();
        }
        return count;
    }



    private int increment(int i) {
       try {
           wrireLock.lock();
           i = ++i;
       } finally {
           wrireLock.unlock();
       }
        return i;
    }

}
