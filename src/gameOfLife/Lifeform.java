package gameOfLife;

import java.util.ArrayList;

public abstract class Lifeform {
    
    abstract void resetLifeCounter(); 
    
    abstract void hasMoved();
    
    abstract void resetHasMoved();
    
    abstract boolean getMovedStatus();
    
    abstract void move(Cell[][] herbivoreSurroundCells);
    
    abstract boolean die();
    
    abstract void eat();
    
    abstract void procreate(Cell[][] surroundingCells);
    
    abstract void giveBirth(Cell[][] surroundingCells); 
    
    abstract void justBorn();
    
    abstract boolean getBirthStatus();
    
    abstract void resetBirthStatus(); 
//    
    abstract int getDeathCounter();

    abstract void updateDeathCounter();

    abstract void setMovedDeathCounter(int i);
    
}
