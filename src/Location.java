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
}
