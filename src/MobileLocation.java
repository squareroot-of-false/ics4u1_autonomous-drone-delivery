public class MobileLocation extends Location{

    /**.
     * Constructor
     * Adds the object to locationList
     *
     * @param name    a String
     * @param x       a positive int that is less than SIZE
     * @param y       a positive int that is less than SIZE

     */
    public MobileLocation(String name, int x, int y) {
        //Mobile locations are never depots
        super(name, x, y, false);
    }

    /**
     * moves the location across a certain distance in a direction
     * @param dist distance location is moving
     * @param direction what cardinal direction location is moving in
     *                  O for North, 1 for East, 2 for South, 3 for West
     * @return true if the movement is completed successfully, false otherwise
     */
    public boolean move(int dist, byte direction){//return type changed from UML
        //Move North
        if(direction == 0){
            this.y += dist;
            if(this.y > SIZE){
                this.y = SIZE;
                return false;

            } else {//checks for collisions
                for(Location l: locationList) {
                    if(l.checkConflict(this)) {
                        this.y -= dist;
                        return false;

                    }
                }
            }
        //Move East
        } else if (direction == 1){
            this.x += dist;
            if(this.x > SIZE){
                this.x = SIZE;
                return false;

            } else {//checks for collisions
                for(Location l: locationList) {
                    if(l.checkConflict(this)) {
                        this.x -= dist;
                        return false;

                    }
                }
            }
        //Move South
        } else if (direction == 2){
            this.y -= dist;
            if(this.y < -SIZE){
                this.y = -SIZE;
                return false;

            } else {//checks for collisions
                for(Location l: locationList) {
                    if(l.checkConflict(this)) {
                        this.y += dist;
                        return false;

                    }
                }
            }
        //Move West
        } else if (direction == 3){
            this.x -= dist;
            if(this.x < -SIZE){
                this.x = -SIZE;
                return false;

            } else {//checks for collisions
                for(Location l: locationList) {
                    if(l.checkConflict(this)) {
                        this.x += dist;
                        return false;

                    }
                }
            }
        //Error message if invalid direction inputed
        } else {
            System.out.println("Direction " + direction + " invalid. Direction must be 0, 1, 2 or 3.");
            return false;

        }

        return true;

    }


    /**
     * toString (temp) Currently outputs all instance variables. Will print in format for grid
     * @return
     */
    public String toString(){
        return "Name: " + name + "\nPosition: (" + x + ", " + y + ")\nIsDepot: " + isDepot;
    }


}
