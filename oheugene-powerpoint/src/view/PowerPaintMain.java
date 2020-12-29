/*
 * TCSS 305 - Fall 2020
 * Assignment 4 - PowerPaint
*/

package view;

/**
 * This class will start the Power Point GUI.
 * 
 * @author Eugene Oh
 * @version Fall 2020
 */

import java.awt.EventQueue;

public class PowerPaintMain {
	
	/**
	 * Runs the Power Point GUI.
	 */
    public static void main(final String[] theArgs) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PowerPaintGUI().start();
            }
        });
    }
}
