package com.sk.virtualthreads;

public class VirtualThreadExample {
    public static void main(String[] args) {
        // Creating a Virtual Thread
        Thread virtualThread = Thread.ofVirtual().start(() -> {
            System.out.println("Running in a virtual thread!");
        });

        // Alternative APIs:
            //  Thread.startVirtualThread(Runnable);
            //  Thread.ofVirtual().unstarted(Runnable);
            //  Executors.newVirtualThreadPerTaskExecutor();
            //  Executors.newThreadPerTaskExecutor(ThreadFactory);

        // Wait for the virtual thread to complete
        try {
            virtualThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
