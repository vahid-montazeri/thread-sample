package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Main {


    public static void main(String[] args) {


        List<Integer> randomNumbers = new ArrayList<>(1000);
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            int randomInteger = random.nextInt();
            randomNumbers.add(randomInteger);
        }


        calculateWithFixedThead(randomNumbers);
        calculateWithCachedThead(randomNumbers);
        calculateWithScheduledThreadPool(randomNumbers);


    }

    private static void calculateWithFixedThead(List<Integer> randomNumbers) {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);

        long startTime = System.currentTimeMillis();

        fixedThreadPool.submit(() -> {
            int sum = sum(randomNumbers);
            long endTime = System.currentTimeMillis();
            System.out.println("sum = " + sum + ", duration = " + (endTime - startTime) + " milliSec");
        });
    }

    private static void calculateWithCachedThead(List<Integer> randomNumbers) {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        long startTime = System.currentTimeMillis();

        cachedThreadPool.submit(() -> {
            int sum = sum(randomNumbers);
            long endTime = System.currentTimeMillis();
            System.out.println("sum = " + sum + ", duration = " + (endTime - startTime) + " milliSec");
        });
    }

    private static void calculateWithScheduledThreadPool(List<Integer> randomNumbers) {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(2);

        long startTime = System.currentTimeMillis();

        scheduledThreadPool.submit(() -> {
            int sum = sum(randomNumbers);
            long endTime = System.currentTimeMillis();
            System.out.println("sum = " + sum + ", duration = " + (endTime - startTime) + " milliSec");
        });
    }

    private static Integer sum(List<Integer> numbers) {
        int sum = 0;
        for (Integer number : numbers) {
            sum += number;
        }
        return sum;
    }


}