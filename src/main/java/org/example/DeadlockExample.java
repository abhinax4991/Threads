package org.example;

import java.util.Random;

public class DeadlockExample {
    public static void main(String[] args) {
        Intersectino intersectino = new Intersectino();
        Thread trainAThread = new Thread(new TrainA(intersectino));
        trainAThread.setName("TRAIN_A");
        Thread trainBThread = new Thread(new TrainB(intersectino));
        trainBThread.setName("TRAIN_B");
        trainBThread.start();
        trainAThread.start();

    }

    public static class TrainB implements Runnable {
        private Intersectino intersectino;
        private Random random = new Random();
        TrainB(Intersectino intersectino) {
            this.intersectino = intersectino;
        }

        @Override
        public void run() {
            while (true) {
                long sleepingTime = random.nextInt(5);
                try {
                    Thread.sleep(sleepingTime);
                }catch (Exception e) {

                }
                intersectino.takeRoadB();
            }

        }
    }

    public static class TrainA implements Runnable {
        private Intersectino intersectino;
        private Random random = new Random();
        TrainA(Intersectino intersectino) {
            this.intersectino = intersectino;
        }

        @Override
        public void run() {
            while (true) {
                long sleepingTime = random.nextInt(5);
                try {
                    Thread.sleep(sleepingTime);
                }catch (Exception e) {

                }
                intersectino.takeRoadA();
            }

        }
    }

    public static class Intersectino {
        Object roadA = new Object();
        Object roadB = new Object();




        public void takeRoadA() {
            synchronized (roadA) {
                System.out.println("Road A is locked by train " + Thread.currentThread().getName());
                synchronized (roadB) {
                    System.out.println("Train is passing through A");
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {

                    }
                }
            }
        }

        public void takeRoadB() {
            synchronized (roadB) {
                System.out.println("Road B is locked by train " + Thread.currentThread().getName());
                synchronized (roadA) {
                    System.out.println("Train is passing through B");
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {

                    }
                }
            }
        }


    }

}
