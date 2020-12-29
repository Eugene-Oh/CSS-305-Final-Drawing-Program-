/*
 * TCSS 305 - Fall 2020
 * Assignment 4 - PowerPaint
*/

package tools;

/**
 * This class creates the line tool.
 * 
 * @author Eugene Oh
 * @version Fall 2020
 */

import java.awt.Shape;
import java.awt.geom.Line2D;

public class LineTool extends AbstractTool {
	
	/**
	 * @return The tool's drawn shape.
	 */
	public Shape getShape() {
		return new Line2D.Double(getStartingPoint(), getEndingPoint());
	}
}
