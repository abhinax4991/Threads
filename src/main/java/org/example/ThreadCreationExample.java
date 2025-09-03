package org.example;

public class ThreadCreationExample {
    public static void main(String[] args) throws Exception {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("We are now in a thread " + Thread.currentThread().getName());

            }
        });

        System.out.println("We are in thread: " + Thread.currentThread().getName() + " before starting");
        thread.start();
        System.out.println("We are in thread: " + Thread.currentThread().getName() + " after starting");
        Thread.sleep(10000);
    }
}
