public class Obstacle extends Location{

    //Variable for decay. Obstacles last for a certain number of time units, time is -1 for permanent
    private int time;

    /**
     * Constructor
     * Adds the object to locationList
     *
     * @param name    a String
     * @param x       a positive int that is less than SIZE
     * @param y       a positive int that is less than SIZE
     */
    public Obstacle(String name, int x, int y) {
        super(name, x, y, false);
    }

    /**
     * getter for time
     * @return time variable
     */
    public int getTime() {
       return time;
    }

    /**
     * @param other another location to compare with
     * @return true if x and y are the same
     */
    public boolean checkConflict(Location other){
        return other != null && this.x == other.x && this.y == other.y;
    }

    /**
     * decay method for time passage. Each time called, time goes down by 1
     * When time hits 0, removed from list (permanent obstacles never hit 0)
     */
    public void decay(){
        time--;
        if(time == 0){
            locationList.remove(this);
        }
    }

    /**
     * Temp toString returns all info
     * @return info for grid
     */
    public String toString(){
        return "Name: " + name + "\nPosition: (" + x + ", " + y + ")\nIs Depot: " + isDepot + "\nTime: " + time;
    }
}