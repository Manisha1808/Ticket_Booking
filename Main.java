package bookmyshow;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        
        CustomerService customerService = new CustomerService();
        Retrieve retrieve = new Retrieve("bookmyshow");
        Food food = new Food();
        
        double ticketPrice = 250.00; 
        double totalTicketCost = 0.0;
        
        System.out.println("***************************************************************************************");
        System.out.println("                              Welcome To BookMyShow                                  ");
        System.out.println("  Too tired to book tickets offline? Get your tickets booked from the comfort of your home.");
        System.out.println("               Check the availability of all new shows in one click.");
        System.out.println("***************************************************************************************");
        
        System.out.println("\n1. New to BookMyShow? Register now!");
        System.out.println("2. Login to your Existing Account");
        System.out.print("Choose an option (1 or 2): ");
        int choice = sc.nextInt();
        sc.nextLine();

        Customer customer = null;
        
        if (choice == 1) {
            System.out.println("\n----- Register New Account -----");
            System.out.print("Enter your name: ");
            String name = sc.nextLine();
            System.out.print("Enter your email: ");
            String email = sc.nextLine();
            System.out.print("Enter your phone number: ");
            String phone = sc.nextLine();
            System.out.print("Enter your password: ");
            String password = sc.nextLine();

            boolean registered = customerService.registerCustomer(name, email, phone, password);
            if (registered) {
                System.out.println("\nRegistration successful! Welcome, " + name + ".");
                customer = customerService.loginCustomer(email, password);
            } else {
                System.out.println("Registration failed. Please try again.");
                return;  
            }
        } else if (choice == 2) {
            System.out.println("\n----- Login to Your Account -----");
            System.out.print("Enter your email: ");
            String email = sc.nextLine();
            System.out.print("Enter your password: ");
            String password = sc.nextLine();
            
            customer = customerService.loginCustomer(email, password);
            if (customer != null) {
                System.out.println("\nLogin successful! Welcome back, " + customer.getname() + ".");
            } else {
                System.out.println("Invalid credentials. Please try again.");
                return;
            }
        } else {
            System.out.println("Invalid choice. Please restart the application.");
            return;
        }
        
        System.out.println("\n***************************************************************************************");
        System.out.println("                      Available Shows for Booking                                ");
        System.out.println("***************************************************************************************");
        retrieve.loadShows();
        retrieve.displayShows();

        boolean continueBooking;
        
        do {
            System.out.println("\n----- Book Tickets -----");
            System.out.print("Enter the show number to book tickets: ");
            int showIndex = sc.nextInt();

            System.out.print("Enter number of seats to book: ");
            int seats = sc.nextInt();
            sc.nextLine();

            Booking booking = new Booking(customer.getname());
            booking.bookTickets(retrieve, showIndex, seats);

            totalTicketCost = seats * ticketPrice;
            System.out.println("\nTotal cost for tickets: Rs. " + totalTicketCost);
            
            System.out.print("\nDo you want to continue booking other tickets? (yes or no): ");
            String response = sc.next().toLowerCase();
            continueBooking = response.equals("yes");
        } while (continueBooking);

        System.out.println("\n***************************************************************************************");
        System.out.println("               Would you like to order food with your show tickets?                ");
        System.out.println("***************************************************************************************");
        System.out.print("Do you want Food Services? (yes or no): ");
        sc.nextLine();
        String c = sc.nextLine();
        
        double totalFoodCost = 0.0;
        if (c.equalsIgnoreCase("yes")) {
            totalFoodCost = food.placeFoodOrder();  
        }
        
        double totalCost = totalTicketCost + totalFoodCost;
        double  gst = 0.28;
        
        System.out.println("\n***************************************************************************************");
        System.out.println("                                  Final Bill                                             ");
        System.out.println("***************************************************************************************");
        System.out.println("Total Tickets Cost: Rs. " + totalTicketCost);
        System.out.println("Total Food Service Cost: Rs. " + totalFoodCost);
        System.out.println("Gst added: "+gst);
        System.out.println("***************************************************************************************");
        System.out.println("Total Amount to Pay: Rs. " + totalCost+ gst);
        System.out.println("***************************************************************************************");
        
        System.out.println(" ");
        
        System.out.println("\n***************************************************************************************");
        System.out.println(" "); 
        System.out.println("                   Thank you for using BookMyShow. Have a great day!                 ");
        System.out.println(" ");
        System.out.println("***************************************************************************************");
        
        sc.close();
    }
}
