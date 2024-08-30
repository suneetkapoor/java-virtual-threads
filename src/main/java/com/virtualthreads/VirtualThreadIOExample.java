package src.main.java.com.virtualthreads;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class VirtualThreadIOExample {
    public static void main(String[] args) {
        // Creating a Virtual Thread for a blocking I/O operation
        Thread virtualThread = Thread.ofVirtual().start(() -> {
            try {
                URL url = new URL("https://jsonplaceholder.typicode.com/posts/1");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Wait for the virtual thread to complete
        try {
            virtualThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
