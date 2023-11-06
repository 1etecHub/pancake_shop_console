package org.pancakeApp.pancakeShop;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

public class PancakeShopConcurrent {
    public static void main(String[] args) {
        PancakeShopConcurrent pancakeShop = new PancakeShopConcurrent();

        for (int i = 1; i <= 10; i++) {
            System.out.println("Starting 30 seconds slot " + i);
            pancakeShop.simulate30SecondsConcurrently();
            System.out.println("Ending 30 seconds slot " + i);
        }
    }

    public void simulate30SecondsConcurrently() {
        List<CompletableFuture<Integer>> pancakeMakingFutures = new ArrayList<>();
        List<CompletableFuture<Integer>> pancakeEatingFutures = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            pancakeMakingFutures.add(CompletableFuture.supplyAsync(() -> shopkeepersMakePancakes()));
            pancakeEatingFutures.add(CompletableFuture.supplyAsync(() -> usersEatPancakes()));
        }

        CompletableFuture<Void> allPancakeMaking = CompletableFuture.allOf(pancakeMakingFutures.toArray(new CompletableFuture[0]));
        CompletableFuture<Void> allPancakeEating = CompletableFuture.allOf(pancakeEatingFutures.toArray(new CompletableFuture[0]));

        allPancakeMaking.join();
        allPancakeEating.join();

        int pancakesMade = pancakeMakingFutures.stream()
                .map(CompletableFuture::join)
                .mapToInt(Integer::intValue)
                .sum();
        int pancakesEaten = pancakeEatingFutures.stream()
                .map(CompletableFuture::join)
                .mapToInt(Integer::intValue)
                .sum();

        System.out.println("Shopkeeper made " + pancakesMade + " pancakes.");
        System.out.println("Total pancakes eaten by users: " + pancakesEaten);

        int wastedPancakes = pancakesMade - pancakesEaten;
        if (wastedPancakes > 0) {
            System.out.println("Wasted pancakes: " + wastedPancakes);
        }

        if (pancakesEaten <= pancakesMade) {
            System.out.println("Shopkeeper met the needs of all users.");
        } else {
            System.out.
                    println("Shopkeeper could not meet all needs.");
            int unmetOrders = pancakesEaten - pancakesMade;
            System.out.println("Unmet pancake orders: " + unmetOrders);
        }
    }

    // method to enable shopkeeper make pancake
    private int shopkeepersMakePancakes() {
        Random random = new Random();
        return random.nextInt(13);
    }

    // method to enable user request for pancake
    private int usersEatPancakes() {
        Random random = new Random();
        return random.nextInt(6); // Up to 5 pancakes
    }
}
