/*
 * TCSS 305 - Fall 2020
 * Assignment 4 - PowerPaint
*/

package tools;

/**
 * This class creates the ellipse tool.
 * 
 * @author Eugene Oh
 * @version Fall 2020
 */

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

public class EllipseTool extends AbstractTool {
	
	/**
	 * @return The tool's drawn shape.
	 */
	public Shape getShape() {
		final Ellipse2D.Double circle = new Ellipse2D.Double();
		final Point start = getStartingPoint();
		final Point end = getEndingPoint();
		double minX = Math.min(start.getX(), end.getX());
		double minY = Math.min(start.getY(), end.getY());
		double maxX = Math.max(start.getX(), end.getX());
		double maxY = Math.max(start.getY(),  end.getY());
		circle.setFrame(minX, minY, maxX - minX, maxY - minY);
		return circle;
	}
}
