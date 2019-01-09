public class Creature {
  
  private int listIndex;
  private int xIndex;
  private int yIndex;
  private int size;
  
  private boolean alive;
  public boolean toLive;
  
  // Initialize Creature
  public Creature (int listIndex, int xIndex, int yIndex, int size, boolean alive) {
    this.listIndex = listIndex;
    this.xIndex = xIndex;
    this.yIndex = yIndex;
    this.size = size;
    this.alive = alive;
    this.toLive = alive;
  }
  
  // Draw an individual creature to the screen
  public void drawCreature() {
    if (alive) {
      fill(255);
    }
    else {
      fill(60);
    }
    rect(this.xIndex * this.size, this.yIndex * this.size, this.size, this.size); 
  }
  
  // Get index of creature in creatures list
  public int getListIndex() {
    return this.listIndex;
  }
  
  // Get x index
  public int getX() {
    return this.xIndex;
  }
  
  // Get y index
  public int getY() {
    return this.yIndex;
  }
  
  // Check if the creature is alive
  public boolean isAlive() {
    return this.alive;
  }
  
  // Check how many neighbors next to the creature are living
  public int aliveNeighbors() {
    int aliveNeighbors = 0;
    
    // Corner Neighbor Checks
    // Upper Left
    if (this.getListIndex() == 0) {
      if (grid.creatures.get(this.getListIndex() + 1).isAlive()) {
        aliveNeighbors++;
      }
      if (grid.creatures.get(this.getListIndex() + grid.getCols()).isAlive()) {
        aliveNeighbors++;
      }
      if (grid.creatures.get(this.getListIndex() + grid.getCols() + 1).isAlive()) {
        aliveNeighbors++;
      }
    }
    
    //Upper Right
    else if (this.getListIndex() == grid.getCols() - 1) {
      if (grid.creatures.get(this.getListIndex() - 1).isAlive()) {
        aliveNeighbors++;
      }
      if (grid.creatures.get(this.getListIndex() + grid.getCols()).isAlive()) {
        aliveNeighbors++;
      }
      if (grid.creatures.get(this.getListIndex() + grid.getCols() - 1).isAlive()) {
        aliveNeighbors++;
      }
    }
    
    // Bottom Left
    else if (this.getListIndex() == grid.getCols() * (grid.getRows() - 1) + 1)
    {
      if (grid.creatures.get(this.getListIndex() + 1).isAlive()) {
        aliveNeighbors++;
      }
      if (grid.creatures.get(this.getListIndex() - grid.getCols()).isAlive()) {
        aliveNeighbors++;
      }
      if (grid.creatures.get(this.getListIndex() - grid.getCols() + 1).isAlive()) {
        aliveNeighbors++;
      }
    }
    
    // Bottom Right
    else if (this.getListIndex() == grid.getCols() * grid.getRows() -1) {
      if (grid.creatures.get(this.getListIndex() - 1).isAlive()) {
        aliveNeighbors++;
      }
      if (grid.creatures.get(this.getListIndex() - grid.getCols()).isAlive()) {
        aliveNeighbors++;
      }
      if (grid.creatures.get(this.getListIndex() - grid.getCols() - 1).isAlive()) {
        aliveNeighbors++;
      }
    }
    
    // Edge Neighbor Checks
    // Upper Edge 
    else if (this.getY() == 0) {
      if (grid.creatures.get(this.getListIndex() + 1).isAlive()) {
        aliveNeighbors++;
      }
      if (grid.creatures.get(this.getListIndex() - 1).isAlive()) {
        aliveNeighbors++;
      }
      if (grid.creatures.get(this.getListIndex() + grid.getCols()).isAlive()) {
        aliveNeighbors++;
      }
      if (grid.creatures.get(this.getListIndex() + grid.getCols() + 1).isAlive()) {
        aliveNeighbors++;
      }
      if (grid.creatures.get(this.getListIndex() + grid.getCols() - 1).isAlive()) {
        aliveNeighbors++;
      }
    }
    
    // Bottom Edge
    else if (this.getY() == grid.getRows() - 1) {
      if (grid.creatures.get(this.getListIndex() + 1).isAlive()) {
        aliveNeighbors++;
      }
      if (grid.creatures.get(this.getListIndex() - 1).isAlive()) {
        aliveNeighbors++;
      }
      if (grid.creatures.get(this.getListIndex() - grid.getCols()).isAlive()) {
        aliveNeighbors++;
      }
      if (grid.creatures.get(this.getListIndex() - grid.getCols() + 1).isAlive()) {
        aliveNeighbors++;
      }
      if (grid.creatures.get(this.getListIndex() - grid.getCols() - 1).isAlive()) {
        aliveNeighbors++;
      }
    }
    
    // Left Edge
    else if (this.getX() == 0) {
      if (grid.creatures.get(this.getListIndex() + 1).isAlive()) {
        aliveNeighbors++;
      }
      if (grid.creatures.get(this.getListIndex() - grid.getCols()).isAlive()) {
        aliveNeighbors++;
      }
      if (grid.creatures.get(this.getListIndex() - grid.getCols() + 1).isAlive()) {
        aliveNeighbors++;
      }
      if (grid.creatures.get(this.getListIndex() + grid.getCols()).isAlive()) {
        aliveNeighbors++;
      }
      if (grid.creatures.get(this.getListIndex() + grid.getCols() + 1).isAlive()) {
        aliveNeighbors++;
      }
    }
    
    // Right Edge
    else if (this.getX() == grid.getCols() - 1) {
      if (grid.creatures.get(this.getListIndex() - 1).isAlive()) {
        aliveNeighbors++;
      }
      if (grid.creatures.get(this.getListIndex() - grid.getCols()).isAlive()) {
        aliveNeighbors++;
      }
      if (grid.creatures.get(this.getListIndex() - grid.getCols() - 1).isAlive()) {
        aliveNeighbors++;
      }
      if (grid.creatures.get(this.getListIndex() + grid.getCols()).isAlive()) {
        aliveNeighbors++;
      }
      if (grid.creatures.get(this.getListIndex() + grid.getCols() - 1).isAlive()) {
        aliveNeighbors++;
      }
    }
    
    //Normal Neighbor Check
    else {
      if (grid.creatures.get(this.getListIndex() + 1).isAlive()) {
        aliveNeighbors++;
      }
      if (grid.creatures.get(this.getListIndex() - 1).isAlive()) {
        aliveNeighbors++;
      }
      if (grid.creatures.get(this.getListIndex() - grid.getCols()).isAlive()) {
        aliveNeighbors++;
      }
      if (grid.creatures.get(this.getListIndex() - grid.getCols() + 1).isAlive()) {
        aliveNeighbors++;
      }
      if (grid.creatures.get(this.getListIndex() - grid.getCols() - 1).isAlive()) {
        aliveNeighbors++;
      }
      if (grid.creatures.get(this.getListIndex() + grid.getCols()).isAlive()) {
        aliveNeighbors++;
      }
      if (grid.creatures.get(this.getListIndex() + grid.getCols() + 1).isAlive()) {
        aliveNeighbors++;
      }
      if (grid.creatures.get(this.getListIndex() + grid.getCols() - 1).isAlive()) {
        aliveNeighbors++;
      }
    }
    
    return aliveNeighbors;
  }
  
  // Kills the creature
  public void die() {
    this.alive = false;
  }
  
  // Spawns a new creature
  public void reproduce() {
    this.alive = true;
  }
  
}
