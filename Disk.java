package types;

/**
 * The class that manages Disks that can be used in Tower of Hanoi game
 * 
 * @author Pedro Reinaldo Mendes (nº63729)
 * @version 1.0
 */
public class Disk {
     
     private final int size;     // The size of our Disk
     
    /**
     * The constructor of the Disk given a certain size
     * 
     * @param size The size of the Disk we're creating
     * @requires {@code size >= 1 && size <= 26}
     * @ensures {@code this.size >= 1 && this.size <= 26}
     */
    public Disk(int size) {
         this.size = size;     // We'll set the disk size
    }

    /**
     * Returns the size of our Disk
     * 
     * @return The size of the Disk
     */
    public int getSize() {
        return this.size;     // We'll return the size of our Disk
    }

    /**
     * Return whether our Disk is larger than a given one or not
     * 
     * @param other The Disk we want to compare
     * @requires {@code other != null}
     * @return True if our Disk is larger in size. False otherwise.
     */
    public boolean isLargerThan(Disk other) {
        return this.size > other.getSize();     // We must compare both sizes and return true if this is larger
    }
    
    /**
     * Returns the String value of our Disk
     * 
     * @return The String value of our Disk (A to Z)
     * @ensures {@code \result != null}
     */
    @Override
    public String toString() {
        return "" + (char) (64 + this.size);
    }
}
