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
     * @param x a positive int that is less than SIZE
     * @param y a positive int that is less than SIZE
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
     * @param name the new value for name
     */
    public void setName(String name) {
        this.name = name;

    }

    /**
     * Prevents an obstacle from being in the same place as an Obstacle (child of Location)
     * @param other another location to compare with
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
}
