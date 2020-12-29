/*
 * TCSS 305 - Fall 2020
 * Assignment 4 - PowerPaint
*/

package view;

import java.awt.Color;
import java.awt.Shape;
import java.awt.Stroke;

/**
 * This class stores information on the previous shapes used.
 * 
 * @author Eugene Oh
 * @version Fall 2020
 */

public class ShapeDetails {
	
	/** The shape to store. */
	private Shape myShape;
	
	/** The color to store. */
	private Color myColor;
	
	/** The stroke to store. */
	private Stroke myStroke;
	
	/**
	 * Constructor to set the shape detail fields to.
	 * 
	 * @param theShape The shape to store.
	 * @param theColor The color to store.
	 * @param theWidth The stroke to store.
	 */
	public ShapeDetails(Shape theShape, Color theColor, Stroke theWidth) {
		myShape = theShape;
		myColor = theColor;
		myStroke = theWidth;
	}
	
	/**
	 * @return The shape that was stored.
	 */
	protected Shape getShape() {
		return myShape;
	}
	
	/**
	 * @return The color that was stored.
	 */
	protected Color getColor() {
		return myColor;
	}
	
	/**
	 * @return The stroke that was stored.
	 */
	protected Stroke getStroke() {
		return myStroke;
	}
}
