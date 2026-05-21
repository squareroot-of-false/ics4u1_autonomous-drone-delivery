import java.util.ArrayList;

public class Task {
    public static ArrayList<Task> taskQueue = new ArrayList<>();
    private Location origin, dest;
    private byte taskState;
    private double minCarryCapacity;

    /**
     * Constructor
     * Sets taskState to 0
     * Adds the task to taskQueue (beginning if it's a priority task, end otherwise)
     * @param origin a Location object
     * @param dest a Location object
     * @param minCarryCapacity a positive double
     * @param priorityTask a boolean value
     */
    public Task(Location origin, Location dest, double minCarryCapacity, boolean priorityTask) {
        this.origin = origin;
        this.dest = dest;
        this.taskState = 0;
        this.minCarryCapacity = minCarryCapacity;

        if(priorityTask)
            taskQueue.add(0, this);
        else
            taskQueue.add(this);

    }

    /**
     * Accessor for origin
     * @return origin variable
     */
    public Location getOrigin() {
        return this.origin;

    }

    /**
     * Accessor for dest
     * @return dest variable
     */
    public Location getDest() {
        return this.dest;

    }

    /**
     * Accessor for taskState
     * @return taskState variable
     */
    public byte getTaskState() {
        return this.taskState;

    }

    /**
     * Accessor for minCarryCapacity
     * @return minCarryCapacity variable
     */
    public double getMinCarryCapacity() {
        return this.minCarryCapacity;

    }

    /**
     * Increments the task state
     */
    public void advanceTaskState() {
        this.taskState ++;

    }

    /**
     * Checks if the task is completed (taskState is at least 2)
     * @return
     */
    public boolean isCompleted() {
        return this.taskState >= 2;

    }

    /**
     * May be updated later
     * @return a String listing the values of each instance variable
     */
    @Override
    public String toString() {
        return "Origin:\n" + this.origin + "\nDest:\n" + this.dest +
                "\nTask State: " + this.taskState + "\nMin Carry Capacity: " + minCarryCapacity;

    }

    /**
     * Prints out each task in the TaskQueue
     */
    public static void displayTasks() {
        for(int i = 0; i < taskQueue.size(); i ++) {
            System.out.println((i+1) + ":");
            System.out.println(taskQueue.get(i));

        }
    }
}
