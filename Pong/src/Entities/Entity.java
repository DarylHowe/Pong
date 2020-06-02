/**
 * Entity - An abstract class to set the blueprint for all game entities. 
 */
package Entities;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Entity {

	protected float xPosition, yPosition;
	protected int width, height;

	/**
	 * A constructor for Entity.
	 * @param x      a float to store the entities x Position.
	 * @param y      a float to store the entities y Position.
	 * @param width  an in to store the entities width.
	 * @param height an in to store the entities height.
	 */
	public Entity(float xPosition, float yPosition, int width, int height) {
		
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.width = width;
		this.height = height;
	}

	
	// Abstract methods

	public abstract void tick();

	public abstract void render(Graphics g);

	public Rectangle getEntityBounds() {
		return new Rectangle((int) xPosition, (int) yPosition, width, height);
	}
	
	// Getters
	
	public float getXPosition() {
		return xPosition;
	}

	public float getYPosition() {
		return yPosition;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}



}
