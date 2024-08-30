# Virtual threads

Project Loom is a new initiative in the Java ecosystem aimed at simplifying concurrency and improving the scalability of Java applications by introducing lightweight, user-mode threads called Virtual Threads. Unlike traditional Java threads (often referred to as Platform Threads), Virtual Threads are much lighter and more efficient, allowing applications to handle a large number of concurrent tasks without consuming excessive system # resources. 

# Advantages of Java virtual threads
1. Improves application availability
2. Improves application throughput
3. Reduces ‘OutOfMemoryError: unable to create new native thread’
4. Reduces application memory consumption
5. Improves code quality
6. 100% compatible with Platform Threads

# Performance impact of virtual threads
1. If your application either have lot of threads or large stack size (i.e. -Xss), then switching to virtual threads would reduce your application’s memory consumption.
2. If your application is creating new threads frequently (instead of leveraging thread pool), switching to virtual threads can improve your application’s response time.

# Issues in Java Virtual Thread that Netflix found
Netflix engineers shared their experience with adopting virtual threads in Java 21, focusing on a specific issue they encountered. Virtual threads are lightweight, making it easier to write and maintain high-throughput concurrent applications by allowing threads to be suspended and resumed without holding onto system resources. Netflix encountered a problem where applications using virtual threads in Java 21 would intermittently stop serving traffic, despite the Java Virtual Machine (JVM) still running.

The problem was traced to a situation where virtual threads would get stuck in a state called closeWait, where sockets remained open but inactive. Investigations revealed that these virtual threads were pinned to the operating system (OS) threads while waiting for locks, which led to a deadlock-like scenario. Since all available OS threads were occupied, new virtual threads could not progress, effectively freezing the application.
The engineers identified that the interaction between virtual threads and the underlying OS threads was the root cause of the issue, which was exacerbated by the specific way Tomcat, the server framework, handled incoming requests. They used thread and heap dumps to analyze the problem, eventually understanding that the virtual threads were waiting indefinitely for resources that were blocked by other threads in a similar state.

Read more from the Netflix article - https://netflixtechblog.com/java-21-virtual-threads-dude-wheres-my-lock-3052540e231d


# Example 1: Creating and Running a Virtual Thread
This is a simple example of how to create and run a Virtual Thread using Project Loom.

Explanation:
Thread.ofVirtual(): This is a factory method that creates a new Virtual Thread.
start(): This method starts the Virtual Thread, similar to how you would start a traditional thread.
join(): This method makes the main thread wait until the Virtual Thread finishes its execution.


# Example 2: Using Virtual Threads for Concurrent Tasks
This example demonstrates how Virtual Threads can be used to execute multiple tasks concurrently, making it easy to handle a large number of tasks.

Explanation:
In this example, we create 200,000 Virtual Threads, each executing a simple task of printing a message. This demonstrates the scalability of Virtual Threads, as such a large number of threads would be difficult to manage using traditional Platform Threads.
The Thread.sleep(4000) call is used to give the Virtual Threads time to complete their execution before the main thread exits.


# Example 3: Blocking I/O with Virtual Threads
One of the benefits of Virtual Threads is that they make blocking I/O operations more efficient. 

Explanation:
Blocking I/O: The Virtual Thread performs a blocking I/O operation by making an HTTP request to a URL and reading the response. Unlike traditional threads, Virtual Threads handle blocking I/O more efficiently because they don't tie up system resources while waiting for the I/O operation to complete.
The join() method ensures that the main thread waits for the Virtual Thread to finish the I/O operation.


# Example 4: Parallel Processing Using Virtual Threads
This is an example of using Virtual Threads to parallelize a computational task, such as calculating the sum of a large range of numbers.

Explanation:
Parallel Processing: This example demonstrates how to split a computational task into multiple parts, each handled by a Virtual Thread. The threads concurrently add numbers to an AtomicLong, which safely accumulates the result.
The use of Thread.sleep(2000) allows enough time for all Virtual Threads to complete their work before printing the total sum.


# Example 5: Potential deadlock 
https://gist.github.com/DanielThomas/0b099c5f208d7deed8a83bf5fc03179e


# Conclusion
Project Loom and Virtual Threads bring powerful new capabilities to Java, enabling efficient management of high concurrency with simplified code. These examples demonstrate how Virtual Threads can be used to handle concurrent tasks, manage blocking I/O, and perform parallel processing, making Java a more versatile language for modern applications.
