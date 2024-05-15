package gameOfLife;

import javax.swing.*;

public class GUI {
    
    private Game game;
    
    private World world;
    
    private Herbivore herb;
    
    private Plant plant;

    public GUI() {     
        
        world = new World(); 
        
        game = new Game(world);
        
        herb = new Herbivore();
        
        plant = new Plant();
    }

    public void start() {
        
        SwingUtilities.invokeLater(() -> {
            
            game.start();
            
            game.getFrame().addMouseListener(new java.awt.event.MouseAdapter() {
                
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    
                    printWindowClickedMessage();
                    
                    world.updateWorld(); 
                    world.repaint();
                    //game = new Game(world);
                    
                }
            });
        });
    }

    private static void printWindowClickedMessage() {
        
        System.out.println("Window is Clicked!");
    }
}
