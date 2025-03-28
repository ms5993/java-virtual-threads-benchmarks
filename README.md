# java-virtual-threads-benchmarks

**Evaluating Memory Management & Garbage Collection with Virtual Threads**  

This repository contains benchmark implementations used in my research on **high-concurrency Java applications**. It compares **Virtual Threads vs. Platform Threads** across various workloads, analyzing **CPU utilization, GC behavior, and synchronization performance**.  

## Running the Benchmarks  

Each benchmark directory contains:  
- `Main.java` → Uses **Platform Threads**  
- `MainVT.java` → Uses **Virtual Threads**  

Both are executable Java classes.  

### **Compile All Java Classes in the Folder and Run Benchmarks**  
Before running the benchmarks, **compile all Java files in the folder** and execute them with different garbage collectors using the following commands:  

```sh
# Compile all Java files
javac *.java

# Run with G1GC (Default in Java 11+)
java -XX:+UseG1GC Main
java -XX:+UseG1GC MainVT

# Run with Shenandoah GC 
java -XX:+UseShenandoahGC Main
java -XX:+UseShenandoahGC MainVT

# Run with ZGC 
java -XX:+UseZGC Main
java -XX:+UseZGC MainVT

# Run with ZGC Generational (Experimental in Java 21+)
java -XX:+UseZGC -XX:+ZGenerational Main
java -XX:+UseZGC -XX:+ZGenerational MainVT

