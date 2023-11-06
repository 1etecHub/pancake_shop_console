package org.pancakeApp.pancakeShop;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        List<Integer> pancakesMadeList = new ArrayList<>();
        List<Integer> pancakesEatenList = new ArrayList<>();

        //using parallel streams to carry out concurrency
        shopkeepersMakePancakes(pancakesMadeList);
        usersEatPancakes(pancakesEatenList);

        int pancakesMade = pancakesMadeList.stream().mapToInt(Integer::intValue).sum();
        int pancakesEaten = pancakesEatenList.stream().mapToInt(Integer::intValue).sum();

        System.out.println("Shopkeeper made " + pancakesMade + " pancakes.");
        System.out.println("Total pancakes eaten by users: " + pancakesEaten);

        int wastedPancakes = pancakesMade - pancakesEaten;
        if (wastedPancakes > 0) {
            System.out.println("Wasted pancakes: " + wastedPancakes);
        }

        if (pancakesEaten <= pancakesMade) {
            System.out.println("Shopkeeper met the needs of all users.");
        } else {
            System.out.println("Shopkeeper could not meet all needs.");
            int unmetOrders = pancakesEaten - pancakesMade;
            System.out.println("Unmet pancake orders: " + unmetOrders);
        }
    }

    // method to enable shopkepper make pancake
    private void shopkeepersMakePancakes(List<Integer> pancakesMadeList) {
        Random random = new Random();
        pancakesMadeList.add(random.nextInt(13));
    }

    // method to enable user request for pancake
    private void usersEatPancakes(List<Integer> pancakesEatenList) {
        Random random = new Random();
        pancakesEatenList.add(random.nextInt(6)); // Up to 5 pancakes
        pancakesEatenList.add(random.nextInt(6)); // Up to 5 pancakes
        pancakesEatenList.add(random.nextInt(6)); // Up to 5 pancakes
    }

    /*
    *OBSERVATION
    *
    Both the non-concurrent and concurrent versions are similar in behavior, as there are no actual threads or parallel execution involved in the concurrent version.

    In both versions, I simulated 10 rounds of 30 seconds each.

    The main difference is that the concurrent version may give the impression of running in parallel because it calculates for multiple "30 seconds slots" in a loop. However, there are no actual threads in this implementation. To achieve true parallelism, you would need to use threads or ExecutorService*/
}
