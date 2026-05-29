import java.util.ArrayList;

public class Location {
    //static and instance variables
    public static ArrayList<Location> locationList = new ArrayList<>();
    protected static final int SIZE = 25;//can be changed later
    protected String name;
    protected int x, y;
    protected boolean isDepot;

    /**
     * Constructor
     * Adds the object to locationList
     * @param name a String
     * @param x an int that's absolute value is less than SIZE
     * @param y an int that's absolute value is less than SIZE
     * @param isDepot a boolean value, there will only be one Location
     *                object for which this is true at a given time
     */
    public Location(String name, int x, int y, boolean isDepot) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.isDepot = isDepot;
        locationList.add(this);
        //debating whether to sort the ArrayList immediately
        //Location.sort(locationList);

        //error handling
        if(Math.abs(this.x) > SIZE) {
            System.out.println("Location out of bounds!");
            this.x = 0;

        }
        if(Math.abs(this.y) > SIZE) {
            System.out.println("Location out of bounds!");
            this.y = 0;
        }

    }

    /**
     * accessor for name
     * @return name variable
     */
    public String getName() {
        return this.name;

    }

    /**
     * accessor for x
     * @return x variable
     */
    public int getX() {
        return this.x;

    }

    /**
     * accessor for y
     * @return y variable
     */
    public int getY() {
        return this.y;

    }

    /**
     * mutator for name
     * @param name the new value for name, a String
     */
    public void setName(String name) {
        this.name = name;

    }

    /**
     * Prevents an obstacle from being in the same place as an Obstacle (child of Location)
     * @param other the other Location object to compare to
     * @return false, overridden by Obstacle class
     */
    public boolean checkConflict(Location other) {
        return false;

    }

    /**
     * Checks if two Location objects have the same coordinates
     * @param other the other Location object to compare to
     * @return true if the x and y instance variables are both equal, false otherwise
     */
    public boolean samePlace(Location other) {//argument accidentally left out of UML
        return other != null && this.x == other.x && this.y == other.y;
    }

    /**
     * Temporary body for testing, may be changed later
     * @return a String listing the values of each instance variable
     */
    @Override
    public String toString() {
        return "Name: " + this.name + "\nPosition: (" + this.x + ", " + this.y +
                ")\nIs Depot: " + this.isDepot;

    }

    /**
     * Sorts an ArrayList of Locations by position, using the insertion sort algorithm
     * @param arr the ArrayList to sort
     */
    private static void sort(ArrayList<Location> arr) {
        for(int i = 1; i < arr.size(); i ++) {
            for(int j = 0; j < i; j ++) {
                if(arr.get(i).y < arr.get(j).y ||
                        (arr.get(i).y == arr.get(j).y && arr.get(i).x < arr.get(j).x)) {
                    Location temp = arr.get(i);
                    arr.remove(i);
                    arr.add(j, temp);
                    break;

                }
            }
        }
    }

    /**
     * Finds and returns the depot
     * @return the first Location object in locationList that is a depot, null if one can't be found
     */
    public static Location getDepot() {
        for(Location l: locationList) {
            if(l.isDepot)
                return l;

        }

        System.out.println("Depot could not be found.");
        return null;

    }

    /**
     * Method to be overidden in obstacle class
     */
    public void decay(){
    }
}
