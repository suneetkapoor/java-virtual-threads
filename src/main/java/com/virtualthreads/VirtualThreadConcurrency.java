package src.main.java.com.virtualthreads;

import java.util.stream.IntStream;

public class VirtualThreadConcurrency {

    public static void main(String[] args) {
        // Running 200,000 tasks using Virtual Threads
        IntStream.range(0, 200_000).forEach(i -> {
            Thread.ofVirtual().start(() -> {
                System.out.println("Task " + i + " is running in a virtual thread");
            });
        });

        // Keep the main thread alive for a short time to let virtual threads complete
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
