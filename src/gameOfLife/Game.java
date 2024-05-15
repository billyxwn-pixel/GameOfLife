package gameOfLife;

import javax.swing.JFrame;

public class Game {
    
    private JFrame frame;
    
    private World newWorld;

    public Game(World world) {
        
        frame = new JFrame("Game of Life");
        
        newWorld = world;
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.setSize(800, 800);
        
        frame.add(newWorld);
    }

    public void start() {
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }
}
