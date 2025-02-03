package bookmyshow;

import java.util.Scanner;

public class Food {
    Scanner sc = new Scanner(System.in);
    
    
    private static final int popcorn_cost= 80;
    private static final int colddrink_cost= 50;
    private static final int sandwich_cost = 120;
    
    private int totalFoodCost = 0; 

    
    public int placeFoodOrder() {
        boolean continuePlacingOrders;
        
        do {
            System.out.println("\n MENU ");
            System.out.println("\n 1. Popcorns (Rs. 80)");
            System.out.println("\n 2. Cold drinks (Rs. 50)");
            System.out.println("\n 3. Sandwiches (Rs. 120)");
            System.out.println("Choose from the menu:");
            
            int choice = sc.nextInt();
            sc.nextLine();  // Consume the newline

            switch (choice) {
                case 1:
                    totalFoodCost += popcorn_cost;
                    System.out.println("Popcorns are added to the cart. Cost: Rs. 80");
                    break;
                case 2:
                    totalFoodCost += colddrink_cost;
                    System.out.println("Cold drinks are added to the cart. Cost: Rs. 50");
                    break;
                case 3:
                    totalFoodCost += sandwich_cost;
                    System.out.println("Sandwiches are added to the cart. Cost: Rs. 120");
                    break;
                default:
                    System.out.println("Please choose a valid option.");
                    break;
            }
            
            System.out.println("\nDo you want to add more items to the cart? (yes or no)");
            String response = sc.next().toLowerCase();
            continuePlacingOrders = response.equals("yes");
            
        } while (continuePlacingOrders);

        System.out.println("Thank you for placing the order.");
        System.out.println("***************************************************************************************");
        
        return totalFoodCost;  
    }
}
