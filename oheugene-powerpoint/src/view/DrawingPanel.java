/*
 * TCSS 305 - Fall 2020
 * Assignment 4 - PowerPaint
*/

package view;

/**
 * This class will setup a panel that can be drawn on by the user.
 * 
 * @author Eugene Oh
 * @version Fall 2020
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

import tools.EraserTool;
import tools.Tool;

public class DrawingPanel extends JPanel {
	
	/** The size of the drawing panel. */
	private static final Dimension SIZE = new Dimension(500, 300);
	
	/** The current paint tool being used by the user. */
	private Tool myCurrentTool;
	
	/** The current primary color being used by the user. */
	private Color myCurrentColor;
	
	/** The current secondary color being used by the user. */
	private Color myCurrentColor2;
	
	/** An integer determining if the user pressed MB1 or MB3. */
	private int colorChoice;
	
	/** The current thickness being used by the user. */
	private Stroke myCurrentStroke;
	
	/** The graphics object used to draw on the panel. */
	Graphics2D g;
	
	/** A data structure to store the previous shapes drawn by the user. */
	private final List<ShapeDetails> myPreviousShapeDetails;
	
	/**
	 * Constructs a new JPanel and sets up an array that will contains all the previous shapes.
	 */
	public DrawingPanel() {
		super();
		myPreviousShapeDetails = new ArrayList<>();
		panelSetup();
	}
	
	/**
	 * Sets up the size and color of the panel, as well as the mouse listeners.
	 */
	private void panelSetup() {
		setPreferredSize(SIZE);
		setBackground(Color.WHITE);
		colorChoice = 1;
        // The mouse listener setup.
        final MouseInputAdapter mouseHandler = new MyMouseHandler();
        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);
	}	
	
	public List getShapeDetails() {
		return myPreviousShapeDetails;
	}
	
	/**
	 * Sets the current tool to the given one.
	 * 
	 * @param theTool A given tool object.
	 */
    public void setCurrentTool(final Tool theTool) {
        myCurrentTool = theTool;
    }
    
    /**
     * Sets the current color to the given one.
     * 
     * @param theColor A given color object.
     */
    public void setCurrentColor(final Color theColor) {
    	myCurrentColor = theColor;
    }
    
    /**
     * Sets the 2nd current color to the given one.
     * 
     * @param theColor A given color object.
     */
    public void setCurrentColor2(final Color theColor) {
    	myCurrentColor2 = theColor;
    }
    
    /**
     * @return The current color.
     */
    protected Color getCurrentColor() {
    	return myCurrentColor;
    }
    
    /**
     * @return The current 2nd color.
     */
    protected Color getCurrentColor2() {
    	return myCurrentColor2;
    }
    
    /**
     * Sets the current line thickness to the given one.
     * 
     * @param theThickness A given integer to set the thickess to.
     */
    public void setCurrentThickness(final Stroke theThickness) {
    	myCurrentStroke = theThickness;
    }
    
    /**
     * Clears the drawing panel of all shapes.
     */
    public void clearPanel() {
    	myPreviousShapeDetails.clear();
    	myCurrentTool.setNoPoint();
    	repaint();
    }
    
	/**
	 * Sets up the graphics object needed to display drawings on the frame.
	 */
    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        g = (Graphics2D) theGraphics;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        		 RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Draws the previous shapes.
        for (final ShapeDetails s : myPreviousShapeDetails) {
        	g.setStroke(s.getStroke());
        	g.setColor(s.getColor());
        	g.draw(s.getShape());
        }
        
        // Draws the current shape and chooses the color based on whether MB1 or MB2 was clicked.
        if (myCurrentTool instanceof EraserTool) { 
        	g.setColor(Color.WHITE);
        } else if (colorChoice == 1) {
        	g.setColor(myCurrentColor);
        } else {
        	g.setColor(myCurrentColor2);
        }
        g.setStroke(myCurrentStroke);
        g.draw(myCurrentTool.getShape());
    }
    
	/**
	 * Sets up the mouse handlers needed to capture mouse events in the Jframe.
	 */
	private class MyMouseHandler extends MouseInputAdapter {
        @Override
        /**
         * Creates a mouse listener for MB1 and MB3 to decide which color to use.
         */
        public void mousePressed(final MouseEvent theEvent) {
        	if (theEvent.getButton() == MouseEvent.BUTTON1) {
        		colorChoice = 1;
        	} else if (theEvent.getButton() == MouseEvent.BUTTON3) {
        		colorChoice = 3;
        	}
            myCurrentTool.setStartingPoint(theEvent.getPoint());
            repaint();
           
        }
        
        /**
         * Creates a mouse listener to set the ending point of the shape drawn.
         */
        @Override
        public void mouseDragged(final MouseEvent theEvent) {
            myCurrentTool.setEndingPoint(theEvent.getPoint());
            repaint(); 
        }
        
        /**
         * Creates a mouse listener to store the shape into memory.
         */
        @Override
        public void mouseReleased(final MouseEvent theEvent) {
        	// Saves the current shape to the array list. 
        	ShapeDetails temp;
        	if (myCurrentTool instanceof EraserTool) {
        		temp = new ShapeDetails(myCurrentTool.getShape(), Color.WHITE, myCurrentStroke);
        	} else if (colorChoice == 1) {
        		temp = new ShapeDetails(myCurrentTool.getShape(), myCurrentColor, myCurrentStroke);
        	} else { 
        		temp = new ShapeDetails(myCurrentTool.getShape(), myCurrentColor2, myCurrentStroke);
        	}
        	myPreviousShapeDetails.add(temp);
        	myCurrentTool.setNoPoint();
        }
	}	
}
