package types;

/**
 * The class that manages the Tower of Hanoi game
 * 
 * @author Pedro RM (Dot)
 * @version 1.0
 */
public class HanoiGame {

    public static final String EOL = System.lineSeparator();    // The system's line separator
    
    private final Tower[] towers;       // Every tower present in our game
    private int numMoves;       // We'll keep track of the number of moves we do

    /**
     * The constructor of our Tower of Hanoi game
     * 
     * @param numberOfRods The number of towers that we'll have in our game
     * @param numberOfDisks The number of disks used in our game
     * @requires {@code numberOfRods >= 3 && numberOfDisks >= 1 && numberOfDisks <= 26}
     */
    public HanoiGame(int numberOfRods, int numberOfDisks) {
        
        this.towers = new Tower[numberOfRods];  // We created our towers array
        
        // Now we must create every tower with a maximum height equivalent to the number of disks
        for (int i = 0; i < this.towers.length; i++) {
            this.towers[i] = new Tower(numberOfDisks);
        }
        
        // We finally fill the first tower
        fillTower(this.towers[0]);
        
    }
    
    /**
     * Fills an empty tower completely
     * 
     * @param tower The tower to be filled
     * @requires {@code tower != null && tower.isEmpty()}
     */
    private void fillTower(Tower tower) {
        
        // Starting with the largest Disk possible, we'll be filling the tower from bottom to top
        for (int diskSize = tower.height(); diskSize >= 1; diskSize--) {
            tower.addToTower(new Disk(diskSize));       // We'll be adding each correspondent Disk
        }
        
    }

    /**
     * Moves the Disk on the top of the {@code from} tower to the tower {@code to}
     * 
     * @param from The tower from where we're removing our Disk
     * @param to The tower in which we're putting the Disk
     * @requires {@code from >= 0 && from < this.towers.length &&
     *                  to >= 0 && to < this.towers.length && 
     *                  from != to}
     */
    public void play(int from, int to) {
        
        // Only if the game didn't finish, we'll do our move
        if (!isTerminated()) {
            
            // First, we add the Disk to the indicated tower
            this.towers[to].addToTower(this.towers[from].viewTopDisk());

            // Now we'll simply remove the Disk from the original Tower
            this.towers[from].removeFromTower();
            
            this.numMoves++;    // We finally increment the number of moves done
        }
        
    }

    /**
     * Verifies if the game is over or not
     * 
     * @return {@code true} if the game concluded, {@code false} otherwise
     */
    public boolean isTerminated() {
        
        // To improve the speed of our method in case the first tower is full, we'll verify this situation
        if (this.towers[0].isFull()) {
            return false;       // If the first tower is full, the game didn't end yet
        }
        
        // Starting on the tower with index 1 (because we want to exclude the first tower) we'll verify if any tower is full
        for (int i = 1; i < this.towers.length; i++) {
            
            if (this.towers[i].isFull()) {
                return true;    // If yes, we'll return that the game has ended
            }
            
        }
        
        // If no full tower was found (excluding the first one) we'll finally return false
        return false;
        
    }

    /**
     * Returns the number of moves done during our game
     * 
     * @return the number of moves done during our game
     */
    public int numberOfMoves() {
        return this.numMoves;   // We'll return the number of moves that we used until now
    }
    
    /**
     * Returns the String representation of our actual game
     * 
     * @ensures {@code \result != null}
     */
    @Override
    public String toString() {
        
        // We start by creating our result's StringBuilder
        StringBuilder result = new StringBuilder("Moves: " + this.numMoves + EOL);
        
        // We'll split every String representation of each tower by the system's line separator and store it
        String[][] towersStr = new String[this.towers.length][];
        
        for (int t = 0; t < this.towers.length; t++) {
            towersStr[t] = this.towers[t].toString().split(EOL);
        }
        
        // We'll now go through each level of our towers
        for (int i = 0; i < this.towers[0].height(); i++) {
            
            // And through each tower on each level
            for (String[] towerStr : towersStr) {
                // We'll append the String representation of that position
                result.append("  " + towerStr[i] + "  ");
            }
            
            // Also, whenever we change our tower level, we must append a line separator
            result.append(EOL);
        }
        
        // In order to create a visual "floor" in the representation we'll append 5 hyphens for each existent tower
        for (int x = 0; x < this.towers.length; x++) {
            result.append("-----");
        }
        
        // We finally return our result converted to String
        return result.toString();
    }
}
