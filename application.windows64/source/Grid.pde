public class Grid {
  private int gridSize;
  private int rows;
  private int cols;
  
  ArrayList<Creature> creatures;

  // Initialize grid
  public Grid (int gridSize) {
    this.gridSize = gridSize;
    this.rows = height/gridSize;
    this.cols = width/gridSize;
    this.creatures = new ArrayList<Creature>();
  }
  
  // Called at start to create all of the creatures in the grid
  public void initializeCreatures (){
    int i = 0;
    for (int y = 0; y < rows; y++) {
      for(int x = 0; x < cols; x++) {
        this.creatures.add(new Creature(i, x, y, this.gridSize, false));
        i++;
      }
    }
  }
  
  // Resets grid, killing all alive creatures
  public void resetGrid() {
    for (Creature creature : this.creatures) {
      creature.die();
    }
  }
  
  // Remove all creatures from the grid and then create a new random arrangement
  public void randomizeGrid() {
    this.resetGrid();
    for (Creature creature : this.creatures) {
      if (random(1) < probability) {
        creature.reproduce();
      }
    }
  }
  
  // Print every creature to the console in the format  i: x: # | y: # | alive: _____
  public void printCreatures (){
    for (Creature creature : this.creatures) {
      System.out.println(creature.getListIndex() + ": x: " + creature.getX() + " | y: " + creature.getY() + " | alive: " + creature.isAlive());
    }
  }
  
  // Run through every creature on the grid and draw them
  public void drawCreatures () {
    for (Creature creature : this.creatures) {
      creature.drawCreature();
    }
  }
  
  // Get how many columns the grid has
  public int getCols() {
    return this.cols;
  }
  
  // Get how many rows the grid has
  public int getRows() {
    return this.rows;
  }
  
  // Get the total size of the grid
  public int getListSize() {
    return this.creatures.size();
  }
  
  // Called each frame to play the game of life.  Checks neighbors and updates creatures accordingly
  public void updateGrid() {
    
    // Go through entire list to check if creatures are going to live or die
    for (Creature creature : this.creatures){
      if (creature.isAlive()) {
        if (creature.aliveNeighbors() < 2 || creature.aliveNeighbors() > 3) {
          creature.toLive = false;
        }
        else {
          creature.toLive = true;
        }
      }
      else {
        if(creature.aliveNeighbors() == 3) {
          creature.toLive = true;
        }
        else {
          creature.toLive = false;
        }
      }
    }
    
    // After going through the whole list, kill and create new creatures
    for (Creature creature : creatures) {
      if (creature.toLive) {
        creature.reproduce();
      }
      else {
        creature.die();
      }
    }
  } 
}
