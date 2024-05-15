package gameOfLife;

import java.util.ArrayList;
import java.util.Random;

public class Omnivore extends Lifeform {
    
    /**
     * instance for keeping track of when a turn has passed
     * in the Game (when user has clicked)
     */
    private int deathCounter = 0; 
    
    /**
     * the X position instance of Omnivore object
     */
    private int xCoord;
    
    /**
     * the Y position instance of Omnivore object
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
     * Constructor for a creating a Omnivore object
     */
    public Omnivore() { 
        deathCounter = 0;
    }
    
    /**
     * Second constructor for passing through Omnivore's
     * X and Y coordinates
     */
    public Omnivore(int x, int y) {
        xCoord = x;
        yCoord = y;
    }
    
    public int getXCoord() {
        return xCoord;
    }
    
    public int getYCoord() {
        return yCoord;
    }
        
    /**
     * move method takes in a 3 by 3 2D array of cells
     * from the World. The middle cell with coordinates
     * 1 and 1 is the current cell that contains
     * an omnivore object. The surrounding cells are 
     * added to an array list, where the array list 
     * is then looped through to determine if there are
     * cells that the omnivore object can move into using
     * counters for cells containing the other 
     * life forms. If there are, the cells are then added
     * to another array list that will hold all 
     * the cells that the current omnivore is allowed 
     * to move into. A random number generator is then 
     * used to randomly select an index, which will
     * be the index within the array list that holds 
     * the next cell that the omnivore object will move into.
     * If the omnivore is moving into a cell that contains 
     * a Blank life form (no life form), then that cell
     * will be set with a new omnivore where the new omnivore
     * will be given the previous omnivore's death counter 
     * using a setter and a getter. The previous cell
     * that was holding the moved omnivore is then set to 
     * a Blank life form (no life form). 
     * 
     * The eat method is combined with move method, where
     * if the next cell that the omnivore is moving into 
     * contains a life form that the omnivore can eat,
     * then the omnivore is considered to have eaten in which
     * the cell that the omnivore is moving into will 
     * be set with a new omnivore that will have a
     * death counter of 0. The cell that the omnivore 
     * was previously in is then set to Blank. 
     * 
     */
    @Override
    public void move(Cell[][] omnivoreSurroundCells) {
        // Create an ArrayList to store new coordinates
//        xCoord = x;
//        yCoord = y; 
//        xyCoord = new ArrayList<>();
        
        ArrayList<Cell> cellList = new ArrayList<>(); 
        
        // loop through 
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 1 && j == 1) {
                    Lifeform lifeform = omnivoreSurroundCells[1][1].getLifeform(); 
                    
                    if (lifeform instanceof Omnivore) {
                        lifeform.updateDeathCounter();  
                    }
                } else {
                    Cell cell = omnivoreSurroundCells[i][j];
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
        // and that cell will be the next cell that the Omnivore will move into.
        Lifeform lifeform = cellList.get(randomIndex).getLifeform(); 
        
        Lifeform currentLifeform = omnivoreSurroundCells[1][1].getLifeform();
        
        if (currentLifeform.getDeathCounter() >= 4) {
            //currentLifeform.resetLifeCounter();
            omnivoreSurroundCells[1][1].setObject(new Blank());
        } else {
            if (lifeform instanceof Blank && currentLifeform.getMovedStatus() == false) {
                omnivoreSurroundCells[1][1].setObject(new Blank());
                cellList.get(randomIndex).setObject(new Omnivore());
                Lifeform movedLifeform =  cellList.get(randomIndex).getLifeform();
                movedLifeform.setMovedDeathCounter(currentLifeform.getDeathCounter());
                movedLifeform.hasMoved(); 
            } else if (lifeform instanceof Herbivore && currentLifeform.getMovedStatus() == false){
                omnivoreSurroundCells[1][1].setObject(new Blank());
                cellList.get(randomIndex).setObject(new Omnivore());
                Lifeform movedLifeform = cellList.get(randomIndex).getLifeform(); 
                movedLifeform.hasMoved();
            } else if (lifeform instanceof Carnivore && currentLifeform.getMovedStatus() == false) {
                omnivoreSurroundCells[1][1].setObject(new Blank());
                cellList.get(randomIndex).setObject(new Omnivore());
                Lifeform movedLifeform = cellList.get(randomIndex).getLifeform(); 
                movedLifeform.hasMoved();
            } else if (lifeform instanceof Plant && currentLifeform.getMovedStatus() == false) {
                omnivoreSurroundCells[1][1].setObject(new Blank());
                cellList.get(randomIndex).setObject(new Omnivore());
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
    /**
     * giveBirth method allows the omnivore object to
     * populate one of the surrounding blank cells 
     * with another omnivore object, based on the
     * conditions specified for omnivore reproduction.
     * The method takes in a three by three cell from 
     * World, where the middle cell with coordinates
     * 1 and 1 is the current cell that has the omnivore
     * contained in it within the World. 
     */
    @Override
    void giveBirth(Cell[][] surroundingCells) {
        // TODO Auto-generated method stub
        
       
         // counters for number of neighboring (at least 1) Omnivore,
            // free cells (at least 2), plants (at least 2).
            int omnivoreCounter = 0;
            int freeCellCounter = 0;
            int herbCounter = 0; 
            int carnivoreCounter = 0;
            int plantCounter = 0;
            
            ArrayList<Cell> cellList = new ArrayList<>(); 
            
         // loop through 
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (i == 1 && j == 1) {
                        Lifeform lifeform = surroundingCells[1][1].getLifeform(); 
                        
                        if (lifeform instanceof Omnivore) {
                            //lifeform.updateDeathCounter();  
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
                } else if (lifeform instanceof Plant) {
                    plantCounter++; 
                }
            }
            
            if ((herbCounter + carnivoreCounter + plantCounter) >= 1 && freeCellCounter >= 3 && omnivoreCounter >= 1) {
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
                
                blankCellList.get(randomIndex).setObject(new Omnivore());
                
                Lifeform newbornOmnivore = blankCellList.get(randomIndex).getLifeform();
                
                newbornOmnivore.hasMoved();
                newbornOmnivore.justBorn();
                   
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
