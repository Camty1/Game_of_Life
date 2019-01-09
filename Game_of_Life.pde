// Game Settings

int gridSize = 10;
float probability = .1;
boolean allowDrawing = false;

// Don't Touch This one!!
Grid grid;

void setup () {
  size(800, 800);
  background(60);
  frameRate(10);
  grid = new Grid(gridSize);
  grid.initializeCreatures();
  grid.printCreatures();
}

void draw () {
  background(60);
  if (!allowDrawing) { 
    grid.updateGrid();
  }
  grid.drawCreatures();
}

void keyPressed() {
  
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

void mousePressed () {
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
