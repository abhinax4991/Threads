package org.example;

public class ThreadExceptions {
    public static void main(String[] args) throws Exception {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                throw  new RuntimeException("An error occurred");
            }
        });

        thread.setName("Worker thread");
        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("A critical error happened in thread: " + t.getName() + " the error is " + e.getMessage());
            }
        });
        thread.start();
    }
}
