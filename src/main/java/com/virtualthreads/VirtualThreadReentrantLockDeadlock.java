package src.main.java.com.virtualthreads;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;
import java.util.stream.Stream;

    /**
     * This example demonstrates a scenario where a {@link ReentrantLock} can potentially lead to deadlock
     * when a virtual thread and other threads compete for the same lock, especially if the threads are
     * configured differently in terms of synchronization.
     */
    public class VirtualThreadReentrantLockDeadlock {

        public static void main(String[] args) {
            final boolean enableSync = args.length == 0 || Boolean.parseBoolean(args[0]);
            final ReentrantLock resourceLock = new ReentrantLock(true); // Using fairness to ensure the virtual thread gets a fair chance

            resourceLock.lock();

            Runnable lockTask = () -> {
                try {
                    System.out.println(Thread.currentThread() + " is waiting to acquire the lock");
                    resourceLock.lock();
                    System.out.println(Thread.currentThread() + " has acquired the lock");
                } finally {
                    resourceLock.unlock();
                    System.out.println(Thread.currentThread() + " has released the lock");
                }
            };

            Thread unpinnedVirtualThread = Thread.ofVirtual().name("unpinned-virtual").start(lockTask);

            List<Thread> pinnedThreads = IntStream.range(0, Runtime.getRuntime().availableProcessors())
                    .mapToObj(i -> Thread.ofVirtual().name("pinned-thread-" + i).start(() -> {
                        if (enableSync) {
                            synchronized (new Object()) {
                                lockTask.run();
                            }
                        } else {
                            lockTask.run();
                        }
                    })).toList();

            resourceLock.unlock();

            Stream.concat(Stream.of(unpinnedVirtualThread), pinnedThreads.stream()).forEach(thread -> {
                try {
                    if (!thread.join(Duration.ofSeconds(3))) {
                        throw new RuntimeException("Potential deadlock detected");
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

