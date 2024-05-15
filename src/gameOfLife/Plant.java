package gameOfLife;

import java.util.ArrayList;
import java.util.Random;

public class Plant extends Lifeform {
    
    /**
     * seeded status
     */
    private boolean justSeeded = false;
    
    public static int count = 0;
    public int id;
    
    /**
     * Constructor for a creating a Herbivore object
     */
    public Plant() {  
        id = count++;
    }
    
    /**
     * Second constructor for passing through Herbivore's
     * X and Y coordinates
     */
    public Plant(int x, int y) {
        id = count++;
    }

    @Override
    public void move(Cell[][] herbivoreSurroundCells) {
        // TODO Auto-generated method stub
    }

    @Override
    boolean die() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    void eat() {
        // TODO Auto-generated method stub
        
    }

    @Override
    void resetLifeCounter() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void procreate(Cell[][] surroundingCells) {
        
        if (justSeeded == false) {
            
            int blankCount = 0;
            int plantCount = 0;
            
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
            
            System.out.println("blank cell count: " + blankCount + ", plant cell count: " + plantCount);
            
            // newPlants will hold references to all the surround empty cells
            ArrayList<Cell> newPlants = new ArrayList<>();
            
            for (int i = 0; i < cellList.size(); i ++) {
                Lifeform lifeform = cellList.get(i).getLifeform();
                if (lifeform instanceof Blank) {
                    blankCount++;
                } else if (lifeform instanceof Plant) {
                    plantCount++;
                }
            }
            
            if (blankCount >= 4 && plantCount >= 3) {
                
                for (int i = 0; i < cellList.size(); i++) {
                    Lifeform lifeform = cellList.get(i).getLifeform();
                    if (lifeform instanceof Blank) {
                        newPlants.add(cellList.get(i));
                    }
                }
                
                Random random = new Random();
                int removeRandomIndex = random.nextInt(newPlants.size());
                
                newPlants.remove(removeRandomIndex);
                
                for (int j = 0; j < newPlants.size(); j++) {
                    newPlants.get(j).setObject(new Plant());
                    Lifeform lifeform = newPlants.get(j).getLifeform();
                    lifeform.justBorn();
                }
            }
            
        }
       
    }

    @Override
    int getDeathCounter() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    void updateDeathCounter() {
        // TODO Auto-generated method stub
        
    }

    @Override
    void setMovedDeathCounter(int i) {
        // TODO Auto-generated method stub
        
    }

    @Override
    void hasMoved() {
        // TODO Auto-generated method stub
        
    }

    @Override
    void resetHasMoved() {
        // TODO Auto-generated method stub
        
    }

    @Override
    boolean getMovedStatus() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    void giveBirth(Cell[][] surroundingCells) {
        // TODO Auto-generated method stub
        
    }

    @Override
    void justBorn() {
        // TODO Auto-generated method stub
        justSeeded = true;
        
    }

    @Override
    void resetBirthStatus() {
        // TODO Auto-generated method stub
        justSeeded = false;
        
    }

    @Override
    boolean getBirthStatus() {
        // TODO Auto-generated method stub
        return justSeeded;
    }

   

   
    
    
}
