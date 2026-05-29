import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);

        //Welcomes the user
        System.out.println("Welcome to the Autonomous Drone Delivery System.");

        //Gets depot location and establishes it
        System.out.println("Please enter the depot/charging station's coordinates: ");
        System.out.print("Enter X coordinate: ");
        int x = reader.nextInt();
        System.out.print("Enter Y coordinate: ");
        int y = reader.nextInt();




    }
}
