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

    /**
     * Calculates a route (usually the shortest) from the current location to the destination
     * @param dest the Location object to pathfind to
     * @return the route, an ArrayList of bytes
     */
    public ArrayList<Byte> pathfind(Location dest) {//ready for testing
        for(Location l: Location.locationList) {//Error case: destination overlaps with an obstacle
            if(l.checkConflict(dest)) {
                System.out.println("Destination is unreachable");
                return null;

            }
        }

        ArrayList<Byte> route = new ArrayList<>();
        //Creates a temporary MobileLocation to trace a route, moving around obstacles
        MobileLocation tempScout = new MobileLocation("FAKE", this.location.getX(), this.location.getY());
        for(int i = 0; i < Location.locationList.size(); i ++) {//removes tempScout from the list of Locations
            if(Location.locationList.get(i) == tempScout) {
                Location.locationList.remove(i);
                break;

            }
        }

        boolean blockYMovement = false;//Ignored when necessary for diversions
        boolean blockXMovement = false;//Ignored when necessary for diversions

        while(!tempScout.samePlace(dest) && route.size() <= this.batteryCapacity * 2) {
            //Stops if route length exceeds double the battery capacity (the length may be reduced after the loop)
            //Tries to move towards the destination
            //By default, moves north/south first
            if(!blockYMovement && (route.isEmpty() || route.getLast() != 2) && tempScout.getY() < dest.getY() && tempScout.move(1, (byte)0)) {//North
                //System.out.print(0);
                route.add((byte)0);
                blockXMovement = false;

            } else if(!blockYMovement && (route.isEmpty() || route.getLast() != 0) && tempScout.getY() > dest.getY() && tempScout.move(1, (byte)2)) {//South
                //System.out.print(1);
                route.add((byte) 2);
                blockXMovement = false;

            } else if(!blockXMovement && (route.isEmpty() || route.getLast() != 3) && tempScout.getX() < dest.getX() && tempScout.move(1, (byte)1)) {//East
                //System.out.print(2);
                route.add((byte) 1);
                blockYMovement = false;

            } else if(!blockXMovement && (route.isEmpty() || route.getLast() != 1) && tempScout.getX() > dest.getX() && tempScout.move(1, (byte)3)) {//West
                //System.out.print(3);
                route.add((byte) 3);
                blockYMovement = false;

            } else if((route.isEmpty() || route.getLast() != 2) && tempScout.getY() >= dest.getY() && tempScout.move(1, (byte)0)) {
                //North, wrong direction
                System.out.print(5);
                route.add((byte)0);
                blockYMovement = false;//resets after attempting to divert
                blockXMovement = false;

            } else if((route.isEmpty() || route.getLast() != 0) && tempScout.getY() <= dest.getY() && tempScout.move(1, (byte)2)) {
                //South, wrong direction
                System.out.print(6);
                route.add((byte)2);
                blockYMovement = false;
                blockXMovement = false;

            } else if((route.isEmpty() || route.getLast() != 3) && tempScout.getX() >= dest.getX() && tempScout.move(1, (byte)1)) {
                //East, wrong direction
                System.out.print(7);
                route.add((byte)1);
                blockYMovement = false;
                blockXMovement = false;

            } else if((route.isEmpty() || route.getLast() != 1) && tempScout.getX() <= dest.getX() && tempScout.move(1, (byte)3)) {
                //West, wrong direction
                System.out.print(8);
                route.add((byte)3);
                blockYMovement = false;
                blockXMovement = false;

            } else if(((route.contains((byte)0) && tempScout.getY() <= dest.getY() && route.indexOf((byte)0) > route.indexOf((byte)2))
                    || (route.contains((byte)2) && tempScout.getY() >= dest.getY() && route.indexOf((byte)2) > route.indexOf((byte)0)))
                    && tempScout.getX() != dest.getX()) {
                //Obstacle in the way
                //Reverts last north/south movement towards the destination, blocks north/south movement
                //Only reverts movement towards the destination
                //Last case that will not require a detour that increases the length of the route
                //System.out.print(4);
                while(route.getLast() != 0 && route.getLast() != 2) {
                    tempScout.move(1, (byte)((route.getLast() + 2) % 4));
                    route.removeLast();

                }
                tempScout.move(1, (byte)((route.getLast() + 2) % 4));
                route.removeLast();
                blockYMovement = true;

            } else if((route.contains((byte)1) && tempScout.getX() <= dest.getX() && route.indexOf((byte)1) > route.indexOf((byte)3))
                    || (route.contains((byte)3) && tempScout.getX() >= dest.getX() && route.indexOf((byte)3) > route.indexOf((byte)1))) {
                //Reverts last east/west movement towards the destination, forces a diversion
                //Only reverts movement towards the destination
                System.out.print(9);
                while(route.getLast() != 1 && route.getLast() != 3) {
                    tempScout.move(1, (byte)((route.getLast() + 2) % 4));
                    route.removeLast();

                }
                tempScout.move(1, (byte)((route.getLast() + 2) % 4));
                route.removeLast();
                blockYMovement = true;
                blockXMovement = true;

            } /*else if((route.isEmpty() || route.getLast() != 2) && tempScout.move(1, (byte)0)) {
                //North, overrides blockYMovement and blockXMovement but doesn't reset them
                route.add((byte)0);

            } else if((route.isEmpty() || route.getLast() != 3) && tempScout.move(1, (byte)1)) {
                //East, overrides blockYMovement and blockXMovement but doesn't reset them
                route.add((byte)2);

            } else if((route.isEmpty() || route.getLast() != 0) && tempScout.move(1, (byte)2)) {
                //South, overrides blockYMovement and blockXMovement but doesn't reset them
                route.add((byte)1);

            } else if((route.isEmpty() || route.getLast() != 1) && tempScout.move(1, (byte)3)) {
                //West, overrides blockYMovement and blockXMovement but doesn't reset them
                route.add((byte)3);

            }*/ else if(tempScout.samePlace(this.location)) {
                //Can't move from starting location
                System.out.println("Could not find a valid route.");
                return null;

            } else {
                System.out.print('F');
                //Reverts the last movement, blocks that movement from being immediately repeated
                tempScout.move(1, (byte)((route.getLast() + 2) % 4));
                route.add((byte)((route.getLast() + 2) % 4));

            }
            //System.out.print(route.size() + " ");//TEMP
        }
        System.out.println();

        if(route.size() > this.batteryCapacity * 2) {
            System.out.println("Pathfinding timed out.");
            return route;//TEMP
            //return null;

        }
        for(int i = 0; i < route.size() - 1; i ++) {
            //removes backtracks
            if(route.get(i) == (route.get(i+1) + 2) % 4) {
                route.remove(i);
                route.remove(i);
                i -= 2;

            } else if(i < route.size() - 2 && route.get(i) == (route.get(i+2) + 2 ) % 4) {
                route.remove(i);
                route.remove(i+1);
                i -= 2;

            }
        }

        System.out.println(route.size());//TEMP
        return route;

    }
}
