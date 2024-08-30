# Virtual threads

Project Loom is a new initiative in the Java ecosystem aimed at simplifying concurrency and improving the scalability of Java applications by introducing lightweight, user-mode threads called Virtual Threads. Unlike traditional Java threads (often referred to as Platform Threads), Virtual Threads are much lighter and more efficient, allowing applications to handle a large number of concurrent tasks without consuming excessive system resources.

Example 1: Creating and Running a Virtual Thread
This is a simple example of how to create and run a Virtual Thread using Project Loom.

Explanation:
Thread.ofVirtual(): This is a factory method that creates a new Virtual Thread.
start(): This method starts the Virtual Thread, similar to how you would start a traditional thread.
join(): This method makes the main thread wait until the Virtual Thread finishes its execution.


Example 2: Using Virtual Threads for Concurrent Tasks
This example demonstrates how Virtual Threads can be used to execute multiple tasks concurrently, making it easy to handle a large number of tasks.

Explanation:
In this example, we create 100,000 Virtual Threads, each executing a simple task of printing a message. This demonstrates the scalability of Virtual Threads, as such a large number of threads would be difficult to manage using traditional Platform Threads.
The Thread.sleep(2000) call is used to give the Virtual Threads time to complete their execution before the main thread exits.


Example 3: Blocking I/O with Virtual Threads
One of the benefits of Virtual Threads is that they make blocking I/O operations more efficient. 

Explanation:
Blocking I/O: The Virtual Thread performs a blocking I/O operation by making an HTTP request to a URL and reading the response. Unlike traditional threads, Virtual Threads handle blocking I/O more efficiently because they don't tie up system resources while waiting for the I/O operation to complete.
The join() method ensures that the main thread waits for the Virtual Thread to finish the I/O operation.


Example 4: Parallel Processing Using Virtual Threads
This is an example of using Virtual Threads to parallelize a computational task, such as calculating the sum of a large range of numbers.

Explanation:
Parallel Processing: This example demonstrates how to split a computational task into multiple parts, each handled by a Virtual Thread. The threads concurrently add numbers to an AtomicLong, which safely accumulates the result.
The use of Thread.sleep(1000) allows enough time for all Virtual Threads to complete their work before printing the total sum.


Conclusion
Project Loom and Virtual Threads bring powerful new capabilities to Java, enabling efficient management of high concurrency with simplified code. These examples demonstrate how Virtual Threads can be used to handle concurrent tasks, manage blocking I/O, and perform parallel processing, making Java a more versatile language for modern applications.
