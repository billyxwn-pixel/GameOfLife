package gameOfLife;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.Random;

public class World extends JPanel {
    
    private static final double PLANT_CHANCE = 0.30;
    
    private static final double HERBIVORE_CHANCE = 0.08;
    
    private static final double CARNIVORE_CHANCE = 0.07;
    
    private static final double OMNIVORE_CHANCE = 0.05;
    
    private static final int WORLD_ROWS = 25;
    
    private static final int WORLD_COLS = 25;
    
    private static Cell[][] cells;
    
    private ArrayList<Herbivore> herbList; 
    
    private ArrayList<Plant> plantList; 
    
    private ArrayList<Carnivore> carnList; 
 
    private ArrayList<Omnivore> omniList; 

    public World() {
        setLayout(null);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resizeCells();
            }
        });
        
        //herbList = new ArrayList<>(); 
        
        // initializes the cells in world when World object is created
        initializeCells();
    }

    private void initializeCells() {
        
        RandomGenerator.reset();
        
        cells = new Cell[WORLD_ROWS][WORLD_COLS];

        for (int y = 0; y < WORLD_ROWS; y++) {
            for (int x = 0; x < WORLD_COLS; x++) {
                
                double rand = RandomGenerator.nextNumber(100) / 100.0;
                
                if (rand < PLANT_CHANCE) {
                    
                    cells[x][y] = new Cell();
                    
                    Plant plant = new Plant(x, y);
                    
                    cells[x][y].setObject(plant);
                    if (plant.id == 0) {
                        System.out.println("y = " + y + " x = " + x);
                    }
                    
                } else if (rand < PLANT_CHANCE + HERBIVORE_CHANCE) {
                    
                    cells[x][y] = new Cell();
                    
                    Herbivore herb = new Herbivore(x, y);
                    
                    cells[x][y].setObject(herb);                  
                    
                } else if (rand < PLANT_CHANCE + HERBIVORE_CHANCE + CARNIVORE_CHANCE) {
                    
                    cells[x][y] = new Cell();
                    
                    Carnivore carn = new Carnivore(x, y);
                    
                    cells[x][y].setObject(carn);
                    
                }else if (rand < PLANT_CHANCE + HERBIVORE_CHANCE + CARNIVORE_CHANCE + OMNIVORE_CHANCE) {
                    
                    cells[x][y] = new Cell();
                    
                    Omnivore omni = new Omnivore(x, y);
                    
                    cells[x][y].setObject(omni);
                    
                } else {
                    Cell tmp = new Cell();
                    tmp.setObject(new Blank());
                    cells[x][y] = tmp;
                    
                    
                }

                int cellSize = Math.min(getWidth() / WORLD_COLS, getHeight() / WORLD_ROWS);
                int xOffset = (getWidth() - WORLD_COLS * cellSize) / 2;
                int yOffset = (getHeight() - WORLD_ROWS * cellSize) / 2;
                cells[x][y].setBounds((xOffset + x) * cellSize, (yOffset + y) * cellSize, cellSize, cellSize);
                add(cells[x][y]);
            }
        }
    }
    
    public void updateWorld() {
        // Create a temporary list to store the new locations of Herbivores
        ArrayList<Herbivore> newHerbivores = new ArrayList<>();
        
        // Create a temporary list to store the new locations of the Plants
        ArrayList<Plant> newPlants = new ArrayList<>();
        
        // Create a temporary list to store the new locations of the Carnivores
        ArrayList<Carnivore> newCarnivores = new ArrayList<>();
        
        // Create a temporary list to store the new locations of the Omnivores
        ArrayList<Omnivore> newOmnivores = new ArrayList<>(); 

        // Iterate through the cells of the world
        for (int y = 0; y < cells.length; y++) {
            for (int x = 0; x < cells[y].length; x++) {
                
                Lifeform lifeform = cells[x][y].getLifeform();
                
                if (lifeform instanceof Plant) {
                    
                    Cell[][] plantSurround = getSurroundingCells(x, y); 
                    
                    lifeform.procreate(plantSurround); 
                   
                    System.out.println("current x = " + x + " current y = " + y);
                    
                } else if (lifeform instanceof Herbivore && lifeform.getBirthStatus() == false && lifeform.getMovedStatus() == false) {
                    
                    Cell[][] herbivoreSurround = getSurroundingCells(x, y); 
                    
                    lifeform.giveBirth(herbivoreSurround); 

                    lifeform.move(herbivoreSurround);
                    
                } else if (lifeform instanceof Carnivore && lifeform.getBirthStatus() == false && lifeform.getMovedStatus() == false) {

                    Cell[][] carnivoreSurround = getSurroundingCells(x, y); 

                    lifeform.giveBirth(carnivoreSurround); 

                    lifeform.move(carnivoreSurround);

                } else if (lifeform instanceof Omnivore && lifeform.getBirthStatus() == false && lifeform.getMovedStatus() == false) {

                    Cell[][] omnivoreSurround = getSurroundingCells(x, y); 

                    lifeform.giveBirth(omnivoreSurround); 

                    lifeform.move(omnivoreSurround);
                    
                } 
                
            }
            
        }
        
        for (int y = 0; y < cells.length; y++) {
            for (int x = 0; x < cells[y].length; x++) {
                Lifeform lifeform = cells[x][y].getLifeform();
                if (lifeform instanceof Herbivore) {
                    lifeform.resetHasMoved(); 
                    lifeform.resetBirthStatus();
                    //lifeform.resetBirthStatus(); 
                } else if (lifeform instanceof Carnivore) {
                    lifeform.resetHasMoved();
                    lifeform.resetBirthStatus();
                } else if (lifeform instanceof Omnivore) {
                    lifeform.resetHasMoved();
                    lifeform.resetBirthStatus();
                } else if (lifeform instanceof Plant) {
                    lifeform.resetBirthStatus();
                }
            }
        }

        // If herbList is not null, clear it and add all Herbivores from newHerbivores
        if (herbList != null) {
            herbList.clear();
            herbList.addAll(newHerbivores);
        } else { // If herbList is null, initialize it with newHerbivores
            herbList = new ArrayList<>(newHerbivores);
        }
        
        if (plantList != null) {
            plantList.clear();
            plantList.addAll(newPlants);
        } else {
            plantList = new ArrayList<>(newPlants);
        }
        
        if (carnList != null) {
            carnList.clear();
            carnList.addAll(newCarnivores);
        } else {
            carnList = new ArrayList<>(newCarnivores);
        }
        
        if (omniList != null) {
            omniList.clear();
            omniList.addAll(newOmnivores);
        } else {
            omniList = new ArrayList<>(newOmnivores);
        }
        
        //repaint();
        
        
    }
    
    public static Cell[][] getSurroundingCells(int x, int y) {
        Cell[][] surroundingCells = new Cell[3][3];
        
        // Iterate over the 3x3 grid around the given position (x, y)
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                // Check if the current position (i, j) is within the bounds of the world
                if (i >= 0 && i < cells.length && j >= 0 && j < cells[0].length) {
                    // If within bounds, assign the corresponding cell to surroundingCells
                    surroundingCells[i - (x - 1)][j - (y - 1)] = cells[i][j];
//                    System.out.print("x = " + i + " y = " + j);
//                    System.out.println(" inside = " + cells[i][j].getLifeform());
                } else {
                    // If out of bounds, assign null to the corresponding cell
                    surroundingCells[i - (x - 1)][j - (y - 1)] = null;
                }
            }
        }

        return surroundingCells;
    }

    private void resizeCells() {
        if (cells != null) {
            int cellSize = Math.min(getWidth() / WORLD_COLS, getHeight() / WORLD_ROWS);

            for (int i = 0; i < WORLD_ROWS; i++) {
                for (int j = 0; j < WORLD_COLS; j++) {
                    int xOffset = (getWidth() - WORLD_COLS * cellSize) / 2;
                    int yOffset = (getHeight() - WORLD_ROWS * cellSize) / 2;
                    cells[i][j].setBounds(xOffset + j * cellSize, yOffset + i * cellSize, cellSize, cellSize);
                }
            }
        }
    }
}
