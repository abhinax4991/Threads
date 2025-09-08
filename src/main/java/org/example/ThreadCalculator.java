package org.example;

import java.math.BigInteger;

public class ThreadCalculator {
    public static void main(String[] args) throws InterruptedException {
        LongComputationTask longComputationTaskOne =  new LongComputationTask(
                new BigInteger("2"), new BigInteger("1000000")
        );
        LongComputationTask longComputationTaskTwo =  new LongComputationTask(
                new BigInteger("2"), new BigInteger("1000000")
        );
        Thread resultOneThread  = new Thread(
                longComputationTaskOne
        );
        Thread resultTwoThread  = new Thread(
                longComputationTaskTwo
        );

        resultOneThread.start();
        resultTwoThread.start();
        resultOneThread.join();
        resultTwoThread.join();
        System.out.println("answer is = "+longComputationTaskOne.getResult() + longComputationTaskTwo.getResult());



    }

    public static class LongComputationTask implements Runnable {
        private BigInteger base;
        private BigInteger power;

        private BigInteger result;

        LongComputationTask(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }

        @Override
        public void run() {
           result = BigInteger.ONE;
            for (BigInteger i = BigInteger.ZERO; i.compareTo(power) !=0; i = i.add(BigInteger.ONE)) {
                result =  result.multiply(base);
            }

        }

        private BigInteger getResult() {
            return result;
        }
    }
}
