package com.sk.virtualthreads;

import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.LongStream;

public class VirtualThreadParallelProcessing {
    public static void main(String[] args) {
        AtomicLong totalSum = new AtomicLong();

        // Splitting the task into 20,000 virtual threads
        LongStream.range(0, 20_000).forEach(i -> {
            Thread.ofVirtual().start(() -> {
                totalSum.addAndGet(i);
            });
        });

        // Wait for a moment to let all threads complete
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Total Sum: " + totalSum.get());
    }
}
