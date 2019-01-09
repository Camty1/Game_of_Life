import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Game_of_Life extends PApplet {

// Game Settings

int gridSize = 10;
float probability = .1f;
boolean allowDrawing = false;

// Don't Touch This one!!
Grid grid;

public void setup () {
  
  background(60);
  frameRate(10);
  grid = new Grid(gridSize);
  grid.initializeCreatures();
  grid.printCreatures();
}

public void draw () {
  background(60);
  if (!allowDrawing) { 
    grid.updateGrid();
  }
  grid.drawCreatures();
}

public void keyPressed() {
  
  // Randomize the grid
  if (key == ' ') {
    grid.randomizeGrid();
  }
  
  // Reset/ Clear the grid
  if (key == BACKSPACE) {
    grid.resetGrid();
  }
  
  // Change game state from drawing cells or not
  if (key == ENTER || key == RETURN) {
    allowDrawing = !allowDrawing;
    System.out.println("Allow Drawing: " + allowDrawing);
  }
}

public void mousePressed () {
  if (allowDrawing) {
    int mousePosToIndex = ceil(mouseX/gridSize) + ceil(mouseY/gridSize) * grid.getCols();
    if (grid.creatures.get(mousePosToIndex).isAlive()) {
      grid.creatures.get(mousePosToIndex).die();
      grid.creatures.get(mousePosToIndex).toLive = false;
    }
    else {
      grid.creatures.get(mousePosToIndex).reproduce();
      grid.creatures.get(mousePosToIndex).toLive = true;
    }
  }
}
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
  public void settings() {  size(800, 800); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Game_of_Life" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
