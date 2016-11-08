package main.java.utils;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class RandomNumGenerator extends Random {
    private Lock lock = new ReentrantLock();
    private long u;
    private long v = nextLong();
    private long w = 1;
    private long seed;

    public RandomNumGenerator() {
        this(System.nanoTime());
    }

    public RandomNumGenerator(long seed) {
        setSeed(seed);
        lock.lock();
        u = seed ^ v;
        nextLong();
        v = u;
        nextLong();
        w = v;
        nextLong();
        lock.unlock();
    }

    @Override
    public long nextLong() {
        lock.lock();
        try {
            u = u * System.nanoTime() + ((Long.MAX_VALUE / (int) (Math.random() + 1) * System.nanoTime()));
            v ^= v >>> 17;
            v ^= v << 31;
            v ^= v >>> 8;
            w = ((Long.MAX_VALUE / (int) (Math.random() + 1) * System.nanoTime() * w + (w >>> 32)));
            long x = u ^ (u << 21);
            x ^= x >>> 35;
            x ^= x << 4;
            return (x + v) ^ w;
        } finally {
            lock.unlock();
        }
    }


    @Override
    protected int next(int bits) {
        return (int) (nextLong() >>> (64 - bits));
    }

    public void setSeed(long givenSeed) {
        this.seed = givenSeed;
    }

    public long getSeed() {
        return this.seed;
    }
}
