/*
 * TCSS 305 - Fall 2020
 * Assignment 4 - PowerPaint
*/

package tools;

/**
 * This class models the required behavior for each tool component.
 * 
 * @author Eugene Oh
 * @version Fall 2020
 */

import java.awt.Point;

public abstract class AbstractTool implements Tool {
	
	/** A point off the panel. */
	public static final Point NO_POINT = new Point(-100, -100);
	
	/** The starting point to draw the shape. */
	private Point myStartingPoint;
	
	/** The ending point to draw the shape. */
	private Point myEndingPoint;
	
	/**
	 * Constructor that sets the tool to be off the panel.
	 */
	public AbstractTool() {
		setNoPoint();
	}
	
	/**
	 * Sets the tool's points to the given point.
	 * 
	 * @param thePoint the given point.
	 */
	public void setStartingPoint(final Point thePoint) {
		myStartingPoint = thePoint;
		myEndingPoint = thePoint;
	}
	
	/**
	 * @return The starting point.
	 */
	protected Point getStartingPoint() {
		return myStartingPoint;
	}
	
	/**
	 * Sets the ending point to the given point
	 * 
	 * @param thePoint The given point.
	 */
	public void setEndingPoint(final Point thePoint) {
		myEndingPoint = thePoint;
	}
	
	/**
	 * @return The ending point.
	 */
	protected Point getEndingPoint() {
		return myEndingPoint;
	}
	
	/**
	 * Sets the tool's point to a point off the panel.
	 */
	public void setNoPoint() {
		myStartingPoint = NO_POINT;
		myEndingPoint = NO_POINT;
	}
}
