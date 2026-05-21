import java.util.ArrayList;
public class Drone {

    //Static and instance variables
    public ArrayList<Drone> droneList = new ArrayList<>();
    private double batteryCapacity, carryCapacity, weight;
    private String name;
    private Task currentTask;
    private MobileLocation location;


    /**
     * Constructor
     * @param batteryCapacity a double, positive
     * @param carryCapacity a double, positive
     * @param name String for name
     * @param weight a double, positive
     *
     * Defaults starting location to depot's
     * Adds drone to list
     */
    public Drone(double batteryCapacity, double carryCapacity, String name, double weight){
        this.batteryCapacity = batteryCapacity;
        if(this.batteryCapacity <= 0){
            this.batteryCapacity = 1;
        }
        this.carryCapacity = carryCapacity;
        if(this.carryCapacity <= 0){
            this.carryCapacity = 1;
        }
        this.name = name;
        this.weight = weight;
        if(this.weight <= 0){
            this.weight = 1;
        }

        location = new MobileLocation(name + "Location", Location.getDepot().getX(), Location.getDepot().getY());

        droneList.add(this);
    }


    /**
     * Calculates a route (usually the shortest) from the current location to the destination
     * @param dest the Location object to pathfind to
     * @return the route, an ArrayList of bytes
     */
    public ArrayList<Byte> pathfind(Location dest) {
        ArrayList<Byte> route = new ArrayList<>();
        MobileLocation tempScout = new MobileLocation("FAKE", this.location.getX(), this.location.getY());

        while(!tempScout.samePlace(dest)) {
            //
        }

        return route;
    }
}
