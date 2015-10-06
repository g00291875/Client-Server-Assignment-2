package A1V18;

/**
 * Author : Thomas Flynn
 * Student ID: G00291875
 * Subject: Client Server Programming
 * Assignment 1&2: Streams & Thread Safe Swing
 *
 * Summary: 
 * 1: Jfilechooser returns a String of the filepath 
 * 2: File path is sent to the "Rearrange" class constructor
 * 3: Rearrange class holds the contents of the text file in a string array
 * 4: each of the 4 buttons change the contents of this array
 *
 */

import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main (String args[]){
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                //new MyGui().setVisible(true);
                new MyGui().setVisible(true);
            }
        });
    }
}
