package org.pancakeApp.pancakeShop;

import java.util.Random;

public class PancakeShopApp {

    public static void main(String[] args) {
        Random random = new Random();
        int pancakesMade, pancakesEaten;
        int user1Wants, user2Wants, user3Wants;

        for (int i = 1; i <= 10; i++) { // Simulate 10 rounds of 30 seconds each
            System.out.println("Starting 30 seconds slot " + i);

            // Shopkeeper makes pancakes (up to 12)
            pancakesMade = random.nextInt(13);
            System.out.println("Shopkeeper made " + pancakesMade + " pancakes.");

            // Users decide how many pancakes they want (up to 5 each)
            user1Wants = random.nextInt(6);
            user2Wants = random.nextInt(6);
            user3Wants = random.nextInt(6);

            // Calculate the total number of pancakes eaten by the users
            pancakesEaten = user1Wants + user2Wants + user3Wants;

            if (pancakesEaten <= pancakesMade) {
                System.out.println("Users ate " + pancakesEaten + " pancakes.");
            } else {
                System.out.println("Users ate " + pancakesMade + " pancakes.");
                System.out.println("Shopkeeper could not meet all needs.");
                int unmetOrders = pancakesEaten - pancakesMade;
                System.out.println("Unmet pancake orders: " + unmetOrders);
                int wastedPancakes = user1Wants - user1Wants + unmetOrders;
                System.out.println("Wasted pancakes: " + wastedPancakes);
            }

            System.out.println("Ending 30 seconds slot " + i);
        }
    }
}
