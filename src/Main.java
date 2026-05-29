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

        Location Depot = new Location("Depot",x,y,true);

        //Loops until the user would like to finish using the program

        boolean running = true;

        while(running){

            //Menu
            System.out.println("1 Manage Drones");
            System.out.println("2 Manage Tasks");
            System.out.println("3 See Grid");
            System.out.println("4 Exit");
            System.out.print("Enter an option:");
            int choice = reader.nextInt();

            if(choice == 1){
                //Menu for drones
                System.out.println("1 Add Drone");
                System.out.println("2 View Drones");
                System.out.print("Enter an option:");
                int droneChoice = reader.nextInt();

                if(droneChoice == 1){
                    //Creates a new drone by user's specifications
                    System.out.print("Enter drone name: ");
                    String name = reader.nextLine();
                    System.out.print("Enter drone battery capacity: ");
                    double batteryCapacity = reader.nextDouble();
                    System.out.print("Enter drone carry capacity: ");
                    double carryCapacity = reader.nextDouble();
                    System.out.print("Enter drone weight: ");
                    double weight = reader.nextDouble();

                    Drone tempDrone = new Drone(batteryCapacity, carryCapacity, name, weight);
                } else {
                    //Outputs the drones on the list
                    System.out.println("List of drones:");
                    Drone.displayDrones();
                }
            } else if (choice == 2){
                //Task options
                System.out.println("1 Add Task");
                System.out.println("2 View Tasks");
                System.out.println("Enter an option: ");
                int taskChoice = reader.nextInt();

                if(taskChoice == 1){
                    //Creates new task
                    System.out.print("Enter x coordinate of start of task: ");
                    int originX = reader.nextInt();
                    System.out.print("Enter y coordinate of start of task: ");
                    int originY = reader.nextInt();
                    System.out.print("Enter x coordinate of end of task: ");
                    int destX = reader.nextInt();
                    System.out.print("Enter y coordinate of end of task: ");
                    int destY = reader.nextInt();
                    System.out.print("Enter the minimum carry capacity for the task: ");
                    double minCarryCapacity = reader.nextDouble();
                    //Priority task
                    System.out.print("Enter 1 if priority task: ");
                    int temp = reader.nextInt();
                    boolean priority = false;
                    if(temp == 1){
                        priority = true;
                    }

                    Task tempTask = new Task(new Location("",originX,originY,false), new Location("",destX,destY,false), minCarryCapacity, priority);
                }
            } else if (choice == 3){
                //Outputs the grid

                //Location.displayGrid will be similar to this section
                for(int i = 25; i >= -25; i --) {
                    for(int j = -25; j <= 25; j ++) {
                        Location temp = new Location("", j, i, false);
                        for(int k = 0; k < Location.locationList.size(); k ++) {
                            if(Location.locationList.get(k) == temp) {
                                Location.locationList.remove(k);
                                break;

                            }
                        }

                        boolean foundMatch = false;
                        for(Location l: Location.locationList) {
                            if(l.samePlace(temp)) {
                                System.out.print(l.getName().charAt(0));
                                foundMatch = true;
                                break;

                            }
                        }

                        if(!foundMatch) {
                            System.out.print("*");

                        }
                    }
                    System.out.println();
                }
            } else {
                //User chooses to exit program
                running = false;
            }

            //Passage of time every time loop runs

            //Generates 10 obstacles every time it runs through
            for(int i = 0; i < 10; i ++) {
                new Obstacle();
            }

            //Goes through and assigns all tasks possible
            for (int i = 0; i < Task.taskQueue.size(); i++){
                //Assigns best drone to each task
                Drone.selectBestDrone(Task.taskQueue.get(i));
            }

            //Loops through drones. If they have a task, they will do the task. If they do not have task, they go to depot. If at depot, they charge
            for (int i = 0; i < Drone.droneList.size(); i++){
                //If it has a task, it will do the steps of the task
                if(Drone.droneList.get(i).getCurrentTask() != null){
                    Drone.droneList.get(i).doTask(Drone.droneList.get(i).getCurrentTask());
                //No task, not at depot
                } else if (!(Drone.droneList.get(i).getLocation().samePlace(Location.getDepot()))){
                    Drone.droneList.get(i).returnToDepot();
                //No task, at depot
                } else {
                    //Charges + 10
                    Drone.droneList.get(i).setBatteryLevel(Drone.droneList.get(i).getBatteryLevel()+10);
                    //If the battery level is greater than capacity, sets back to capacity
                    if (Drone.droneList.get(i).getBatteryLevel() >  Drone.droneList.get(i).getBatteryCapacity()){
                        Drone.droneList.get(i).setBatteryLevel(Drone.droneList.get(i).getBatteryCapacity());
                    }
                }
            }

            //Loops through Locations. If it is named obstacle, decays the time
            for (int i = 0; i < Location.locationList.size(); i++){
                if (Location.locationList.get(i).getName().equals("Obstacle")){
                    Location.locationList.get(i).decay();
                }
            }

        }
    }
}
