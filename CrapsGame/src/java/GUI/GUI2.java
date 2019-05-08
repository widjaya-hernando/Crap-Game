package GUI;

import GUI.GameInterface;
import javax.swing.JFrame;
/**
 * CMPSC 221 Exercise 14.16
 * Purpose: A Craps game with a GUI
 * 
 * @author Paul Han
 * @version 1.0 3/1/2018
 */
public class GUI2 {

    /**
     * Main method that create the GUI
     * 
     * @param args Not used
     */
    public static void main(String[] args) {
        GameInterface gi = new GameInterface();
        gi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gi.setSize(500, 300);
        gi.setVisible(true);
        
    }
    
}
