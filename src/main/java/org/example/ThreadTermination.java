package org.example;


//threads consume resources
//cpu ccylces and cache memory
//if a thread is misbheaving we may want to stop it.
//To close the application, even if one thread kept running the applicaiton wont close
    // if main thread is closed but other threads are running applicaiton would not stop running.
//if a thread finished its works, but the application is still running we want to cleanup the threads resources.
public class ThreadTermination {
    public static void main(String[] args) {
        Thread thread = new Thread(new BlockingTask());
        thread.setName("my thread one");
        thread.start();
        thread.interrupt();

    }

    public static class BlockingTask implements Runnable {
        @Override
        public void run() {
            try {
                System.out.println("Starting blocking thread");
                Thread.sleep(50000);
                System.out.println("Starting blocking  thread after sleep");
            }catch (Exception e) {
                System.out.println("Exiting blocking thread");
            }

        }
    }
}
