import java.util.ArrayList;
public class Drone {

    //Static and instance variables
    public ArrayList<Drone> droneList = new ArrayList<>();
    private double batteryCapacity, batteryLevel, carryCapacity, weight;
    private String name;
    private Task currentTask;
    private MobileLocation location;


    /**
     * Constructor
     * @param batteryCapacity a double, positive
     * @param carryCapacity a double, positive
     * @param name String for name
     * @param weight a double, positive
     * Defaults batteryLevel to maximum
     * Defaults starting location to depot's
     * Adds drone to list
     */
    public Drone(double batteryCapacity, double carryCapacity, String name, double weight){
        //Assigns variables and does error handling
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

        //Sets battery level to full
        this.batteryLevel = this.batteryCapacity;

        //Sets drone's location to depot's x and y
        location = new MobileLocation(name + "Location", Location.getDepot().getX(), Location.getDepot().getY());

        //Adds drone to list
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

    //Setters and getters
    /**
     * @return batteryCapacity
     */
    public double getBatteryCapacity(){
        return batteryCapacity;
    }

    /**
     * @return batteryLevel
     */
    public double getBatteryLevel(){
        return batteryLevel;
    }

    /**
     * @return batteryCapacity
     */
    public double getCarryCapacity() {
        return carryCapacity;
    }

    /**
     * @return name
     */
    public String getName(){
        return name;
    }

    /**
     * @return location
     */
    public MobileLocation getLocation(){
        return location;
    }

    /**
     * @return weight
     */
    public double getWeight(){
        return weight;
    }

    /**
     * @param name for new name
     */
    public void setName (String name){
        this.name = name;
    }

    /**
     * @param batteryLevel for new battery level
     */
    public void setBatteryLevel(double batteryLevel) {
        this.batteryLevel = batteryLevel;
    }
}
