public class Test {
    public static void main(String[] args) {
        new Location("H", 0, 0, true);
        Drone drone = new Drone(40, 100, "D", 10);
        drone.getLocation().move(10, (byte)3);

        for(int i = 0; i < 700; i ++) {
            new Obstacle();
        }
        Location dest = new Location("X", 15, 15, false);

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

        for(byte i: drone.pathfind(drone.getLocation(), dest)) {
            System.out.print(i + " ");
        }
    }
}
