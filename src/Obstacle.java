public class Obstacle extends Location{

    //Variable for decay. Obstacles last for a certain number of time units, time is -1 for permanent
    private int time;

    /**
     * Constructor
     * Adds the object to locationList
     * Randomly generates instance variables
     */
    public Obstacle() {
        super("Obstacle", (int)(Math.random() * (SIZE*2+1) - SIZE),
                (int)(Math.random() * (SIZE*2+1) - SIZE), false);

        while(this.time == 0) {
            this.time = (int)(Math.random() * 52 - 1);

        }
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
        return other != null && this.samePlace(other);
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