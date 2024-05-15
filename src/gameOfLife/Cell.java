// Cell.java
package gameOfLife;

import javax.swing.*;
import java.awt.*;

public class Cell extends JPanel {
    
    private Lifeform lifeform;
    
    private static final int DEFAULT_SIZE = 20;

    public Cell() {
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setSize(DEFAULT_SIZE, DEFAULT_SIZE);
    }

    public Cell(Lifeform lifeform) {
        this();
        setObject(lifeform);
    }

    public void setObject(Lifeform lifeform) {
        this.lifeform = lifeform;
        updateColor();
    }
    
    public Lifeform getLifeform() {
        return lifeform;
    }

    public void updateColor() {
        if (lifeform instanceof Herbivore) {
            setBackground(Color.YELLOW);
        } else if (lifeform instanceof Plant) {
            setBackground(Color.GREEN);
            //if (((Plant) lifeform).id == 0) setBackground(Color.RED);
        } else if (lifeform instanceof Carnivore) {
            setBackground(Color.RED);
        } else if (lifeform instanceof Omnivore) {
            setBackground(Color.BLUE);
        } else if (lifeform instanceof Blank) {
            setBackground(Color.WHITE);
        }
    }
    
   
}
