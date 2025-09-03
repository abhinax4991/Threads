package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ThreadHackerExample {

    public static final int MAX_PASSWORD = 9999;
    public static void main(String[] args) {

        Vault vault = new Vault(900);
        List<Thread> threads = new ArrayList<>();
        threads.add(new AscendingHackerThread(vault));
        threads.add(new DescendingHackerThread(vault));
        threads.add(new PoliceThread());
        for (Thread  thread : threads) {
            thread.start();
        }

    }

    private static class Vault {
        private final int password;
        public Vault(int password) {
            this.password = password;
        }

        private boolean isCorrectPassword(int password) {
           try {
               Thread.sleep(5);
           } catch (Exception e) {

           }
           return this.password == password;
        }

    }

    private static abstract class HackerThread extends Thread {
        protected Vault vault;
        public HackerThread(Vault vault) {
            this.vault = vault;
            this.setName(this.getClass().getSimpleName());
            this.setPriority(Thread.MAX_PRIORITY);
        }

        @Override
        public synchronized void start() {
            System.out.println("Starting thread " + this.getName());
            super.start();
        }


    }
    private static class AscendingHackerThread extends HackerThread {
        public AscendingHackerThread(Vault vault) {
            super(vault);
        }

        @Override
        public synchronized void run() {
            for (int i = 0; i <= MAX_PASSWORD; i++) {
                if (vault.isCorrectPassword(i)) {
                    System.out.println(this.getName() + " guessed the password: " + i);
                    System.exit(0);
                }
            }
        }
    }

    private static class DescendingHackerThread extends HackerThread {
        public DescendingHackerThread(Vault vault) {
            super(vault);
        }

        @Override
        public synchronized void run() {
            for (int i = MAX_PASSWORD; i >= 0; i--) {
                if (vault.isCorrectPassword(i)) {
                    System.out.println(this.getName() + " guessed the password: " + i);
                    System.exit(0);
                }
            }
        }
    }

    private static class PoliceThread extends Thread {
        @Override
        public synchronized void run() {
            for(int i = 10; i > 0; i--) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(i);
            }
            System.out.println("Hackers you are fucked! Police pew pew");
            System.exit(0);
        }
    }

}
