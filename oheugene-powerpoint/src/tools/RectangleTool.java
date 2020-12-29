/*
 * TCSS 305 - Fall 2020
 * Assignment 4 - PowerPaint
*/

package tools;

/**
 * This class creates the rectangle tool.
 *  
 * @author Eugene Oh
 * @version Fall 2020
 */

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

public class RectangleTool extends AbstractTool {
	
	/**
	 * @return The tool's drawn shape.
	 */
	public Shape getShape() {
		final Rectangle2D.Double Rectangle = new Rectangle.Double();
		final Point start = getStartingPoint();
		final Point end = getEndingPoint();
		double minX = Math.min(start.getX(), end.getX());
		double minY = Math.min(start.getY(), end.getY());
		double maxX = Math.max(start.getX(), end.getX());
		double maxY = Math.max(start.getY(),  end.getY());
		Rectangle.setFrame(minX, minY, maxX - minX, maxY - minY);
		return Rectangle;
	}
}
