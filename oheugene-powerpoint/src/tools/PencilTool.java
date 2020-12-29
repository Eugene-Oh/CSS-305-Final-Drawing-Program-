/*
 * TCSS 305 - Fall 2020
 * Assignment 4 - PowerPaint
*/

package tools;

/**
 * This class creates the pencil tool.
 * 
 * @author Eugene Oh
 * @version Fall 2020
 */

import java.awt.Shape;
import java.awt.geom.Path2D;
import java.awt.Point;

public class PencilTool extends AbstractTool {
	
	/** Path object used to freehand draw on the panel. */
	private Path2D.Double myPath;
	
	/**
	 * Constructs the path object.
	 */
	public PencilTool() {
		super();
		myPath = new Path2D.Double();
	}
	
	/**
	 * Moves the path object to the given point.
	 * 
	 * @param thePoint the given point.
	 */
	@Override
	public void setStartingPoint(final Point thePoint) {
		super.setStartingPoint(thePoint);
		myPath.moveTo(thePoint.getX(), thePoint.getY());
	}
	
	/**
	 * Moves the path object to the given point.
	 * 
	 * @param thePoint The given point.
	 */
	@Override
	public void setEndingPoint(final Point thePoint) {
		myPath.lineTo(thePoint.getX(), thePoint.getY());
	}
	
	/**
	 * @return The tool's drawn shape.
	 */
	@Override
	public Shape getShape() {
		return myPath;
	}
	
	/**
	 * Resets the path object.
	 */
	@Override
	public void setNoPoint() {
		myPath = new Path2D.Double();
	}
}
