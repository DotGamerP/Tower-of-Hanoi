package types;

import java.util.Iterator;

/**
 * The class that manages Towers that can be used in Tower of Hanoi game
 * 
 * @author Pedro Reinaldo Mendes (nº63729)
 * @version 1.0
 */
public class Tower {
    
    public static final String EOL = System.lineSeparator();     // The system's line separator
    public static final int DEFAULT_HEIGHT = 8;     // The default height of the tower
    
    private final int height;     // The tower's height
    private final Stack<Disk> tower = new LinkedStack<>();     // The tower's stack with all the disks
    private int disks;     // The number of disks in the tower at the moment

    /**
     * Tower's default constructor that will create a tower with the default height
     */
    public Tower() {
        this(DEFAULT_HEIGHT);     // We'll create a tower with the default height value
    }

    /**
     * Tower's constructor that will create a tower with a given height
     * 
     * @param height The height of the tower we want to create
     * @requires {@code height <= 26 && height >= 1}
     * @ensures {@code this.height <= 26 && this.height >= 1}
     */
    public Tower(int height) {
        this.height = height;     // We'll set the tower's height
    }

    /**
     * Adds a certain {@code Disk} to this {@code Tower}
     * 
     * @param d The disk we want to add to the tower
     * @requires {@code d != null && isValidMove(d)}
     */
    public void addToTower(Disk d) {
         this.tower.push(d);     // We'll push the Disk to our tower stack
         this.disks++;     // We must also increment the disks counter
    }

    /**
     * Returns whether our tower is full of disks or not
     * 
     * @return True if the number of disks is the same as the height of our tower. False otherwise
     */
    public boolean isFull() {
        return this.disks == this.height;     // We'll compare the number of disks in the tower and the height of it
    }

    /**
     * Removes the latest inserted disk of our tower
     * 
     * @requires {@code !isEmpty()}
     */
    public void removeFromTower() {
         this.tower.pop();     // We'll do a pop in our tower stack
         this.disks--;     // We must also decrement the number of disks
    }

    /**
     * Returns whether putting a certain Disk in our tower is valid at the moment or not
     * 
     * @param d The disk we want to verify if it can be added to the tower
     * @requires {@code d != null}
     * @return True if it's a valid move. False otherwise
     */
    public boolean isValidMove(Disk d) {
        return isEmpty() || !isFull() && viewTopDisk().isLargerThan(d);     // We must check that the previous disk is larger and the tower isn't full
    }

    /**
     * Returns the last disk inserted in our tower
     * 
     * @requires {@code !isEmpty()}
     * @return The Disk on the top of our tower
     */
    public Disk viewTopDisk() {
        return this.tower.peek();     // We'll peek on our tower Stack
    }

    /**
     * Returns whether our tower stack is empty at the moment
     * 
     * @return True if our tower does not have any disks on it. False otherwise
     */
    public boolean isEmpty() {
        return this.tower.isEmpty();     // We'll use the method of our tower Stack
    }

    /**
     * Returns the maximum height of our tower
     * 
     * @return The tower's height
     */
    public int height() {
        return this.height;     // We'll return the maximum height previously defined
    }

    /**
     * Returns the number of disks in the tower at the moment
     * 
     * @return The number of disks previously inserted in our tower
     */
    public int numberOfDisks() {
        return this.disks;     // We'll return the number of inserted disks
    }
    
    /**
     * Returns the String representation of our tower
     * 
     * @returns The String representation of our tower
     * @ensures {@code \result != null}
     */
    @Override
    public String toString() {
         
         // We start by creating our result StrinBuilder
        StringBuilder result = new StringBuilder();
        
        // We'll first append to our result every empty position of our tower as "|"
        for (int i = this.disks; i < this.height; i++) {
             result.append("|" + EOL);
        }
        
        // We'll now create an Iterator in order to go through every element of our tower
        Iterator<Disk> iterator = this.tower.iterator();
        while (iterator.hasNext()) {
            result.append(iterator.next() + EOL);
        }
        
        // We end up returning our result converted to String
        return result.append("_").toString();
    }
}