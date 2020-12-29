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
import java.awt.Shape;

public interface Tool {
	
	/**
	 * @return The tool's drawn shape.
	 */
	Shape getShape();
	
	/**
	 * Sets the tool's points to the given point.
	 * 
	 * @param thePoint the given point.
	 */
	void setStartingPoint(Point thePoint);
	
	/**
	 * Sets the ending point to the given point
	 * 
	 * @param thePoint The given point.
	 */
	void setEndingPoint(Point thePoint);
	
	/**
	 * Sets the tool's point to a point off the panel.
	 */
	void setNoPoint();
}
