package gameOfLife;

import java.util.ArrayList;
import java.util.Random;

public class Carnivore extends Lifeform {
    
    /**
     * instance for keeping track of when a turn has passed
     * in the Game (when user has clicked)
     */
    private int deathCounter = 0; 
    
    /**
     * the X position instance of Carnivore object
     */
    private int xCoord;
    
    /**
     * the Y position instance of Carnivore object
     */
    private int yCoord;
    
//    /**
//     * birth status
//     */
//    private boolean justBorn = false; 
    
    /**
     * move tracker 
     */
    private boolean moveTracker = false;
    
    /**
     * birth status
     */
    private boolean birthStatus = false;
    
    /**
     * Constructor for a creating a Carnivore object
     */
    public Carnivore() { 
    }
    
    /**
     * Second constructor for passing through Carnivore's
     * X and Y coordinates
     */
    public Carnivore(int x, int y) {
        xCoord = x;
        yCoord = y;
    }
    
    public int getXCoord() {
        return xCoord;
    }
    
    public int getYCoord() {
        return yCoord;
    }
        

    @Override
    public void move(Cell[][] carnivoreSurroundCells) {
        // Create an ArrayList to store new coordinates
//        xCoord = x;
//        yCoord = y; 
//        xyCoord = new ArrayList<>();
        
        ArrayList<Cell> cellList = new ArrayList<>(); 
        
        // loop through 
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 1 && j == 1) {
                    Lifeform lifeform = carnivoreSurroundCells[1][1].getLifeform(); 
                    
                    if (lifeform instanceof Carnivore) {
                        lifeform.updateDeathCounter();  
                    }
                } else {
                    Cell cell = carnivoreSurroundCells[i][j];
                    if (cell != null) {
                        cellList.add(cell);
                    } 
                }
            }
        }
        
        Random random = new Random();
        int randomIndex = random.nextInt(cellList.size());
        
        // from an array list of surrounding cells, random number 
        // generator returns random index of the array list of surround cells,
        // and that cell will be the next cell that the Carnivore will move into.
        Lifeform lifeform = cellList.get(randomIndex).getLifeform(); 
        
        Lifeform currentLifeform = carnivoreSurroundCells[1][1].getLifeform();
        
        if (currentLifeform.getDeathCounter() == 5) {
            //currentLifeform.resetLifeCounter();
            carnivoreSurroundCells[1][1].setObject(new Blank());
        } else {
            if (lifeform instanceof Blank && currentLifeform.getMovedStatus() == false) {
                carnivoreSurroundCells[1][1].setObject(new Blank());
                cellList.get(randomIndex).setObject(new Carnivore());
                Lifeform movedLifeform =  cellList.get(randomIndex).getLifeform();
                movedLifeform.setMovedDeathCounter(currentLifeform.getDeathCounter());
                movedLifeform.hasMoved(); 
            } else if (lifeform instanceof Herbivore && currentLifeform.getMovedStatus() == false){
                carnivoreSurroundCells[1][1].setObject(new Blank());
                cellList.get(randomIndex).setObject(new Carnivore());
                Lifeform movedLifeform = cellList.get(randomIndex).getLifeform(); 
                movedLifeform.hasMoved();
            } else if (lifeform instanceof Omnivore && currentLifeform.getMovedStatus() == false) {
                carnivoreSurroundCells[1][1].setObject(new Blank());
                cellList.get(randomIndex).setObject(new Carnivore());
                Lifeform movedLifeform = cellList.get(randomIndex).getLifeform(); 
                movedLifeform.hasMoved();
            }
        }
        

        System.out.println("Current Death Count: " + deathCounter); 
    }


    @Override
    boolean die() {
        // TODO Auto-generated method stub
        
        if (deathCounter == 5) {
            return true; 
        } else {
            return false;
        }
    }

    @Override
    void eat() {
        // TODO Auto-generated method stub
        deathCounter = 0; 
        
    }

    @Override
    public void procreate(Cell[][] surroundingCells) {
        // TODO Auto-generated method stub
    }

    public void setXCoord(int newXCoord) {
        // TODO Auto-generated method stub
        xCoord = newXCoord; 
        
    }

    public void setYCoord(int newYCoord) {
        // TODO Auto-generated method stub
        yCoord = newYCoord; 
    }

    public int getDeathCounter() {
        return deathCounter; 
    }
    
    @Override
    void resetLifeCounter() {
        // TODO Auto-generated method stub
        deathCounter = 0; 
        
    }
    
    void updateDeathCounter() {
        deathCounter++; 
    }
    
    void setMovedDeathCounter(int currentDeathCount) {
        deathCounter = currentDeathCount; 
    }

    @Override
    void hasMoved() {
        // TODO Auto-generated method stub
        moveTracker = true;
    }

    @Override
    void resetHasMoved() {
        // TODO Auto-generated method stub
        moveTracker = false; 
    }

    @Override
    boolean getMovedStatus() {
        // TODO Auto-generated method stub
        return moveTracker; 
    }

    @Override
    void giveBirth(Cell[][] surroundingCells) {
        // TODO Auto-generated method stub
        
        
            
         // counters for number of neighboring (at least 1) Carnivore,
            // free cells (at least 2), plants (at least 2).
            int omnivoreCounter = 0;
            int freeCellCounter = 0;
            int herbCounter = 0; 
            int carnivoreCounter = 0;
            
            ArrayList<Cell> cellList = new ArrayList<>(); 
            
            // loop through 
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (i == 1 && j == 1) {
                        Lifeform lifeform = surroundingCells[1][1].getLifeform(); 
                        
                        if (lifeform instanceof Carnivore) {
                           // lifeform.updateDeathCounter();  
                        }
                    } else {
                        Cell cell = surroundingCells[i][j];
                        if (cell != null) {
                            cellList.add(cell);
                        } 
                    }
                }
            }
            
            // ArrayList to hold references to surrounding blank cells
            ArrayList<Cell> blankCellList = new ArrayList<>(); 
            
            for (int i = 0; i < cellList.size(); i++) {
                Lifeform lifeform = cellList.get(i).getLifeform();
                if (lifeform instanceof Omnivore) {
                    omnivoreCounter++;
                } else if (lifeform instanceof Blank) {
                    freeCellCounter++; 
                } else if (lifeform instanceof Herbivore) {
                    herbCounter++;
                } else if (lifeform instanceof Carnivore) {
                    carnivoreCounter++;
                }
            }
            
            if ((herbCounter + omnivoreCounter) >= 2 && freeCellCounter >= 3 && carnivoreCounter >= 1) {
                for (int i = 0; i < cellList.size(); i++) {
                    Lifeform lifeform = cellList.get(i).getLifeform();
                    if (lifeform instanceof Blank) {
                        // adds blank cell to blankCell array list
                        blankCellList.add(cellList.get(i));
                    }
                }
                
                Random random = new Random();
                int randomIndex = random.nextInt(blankCellList.size());
                
                // change whichever blank cell that has been returned to
                // another herbivore
                
                blankCellList.get(randomIndex).setObject(new Carnivore());
                
                Lifeform newbornCarnivore = blankCellList.get(randomIndex).getLifeform();
                
                newbornCarnivore.hasMoved();
                newbornCarnivore.justBorn();
                   
            } 
        
            
    }

    @Override
    void justBorn() {
        // TODO Auto-generated method stub
        birthStatus = true; 
        
    }

    @Override
    boolean getBirthStatus() {
        // TODO Auto-generated method stub
        return birthStatus;
    }

    @Override
    void resetBirthStatus() {
        // TODO Auto-generated method stub
        birthStatus = false; 
        
    }
        
  

}
