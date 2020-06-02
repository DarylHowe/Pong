/**
 * Ball - A blueprint for the pong ball. 
 */
package Entities;

import java.awt.Graphics;
import java.util.Random;

public class Ball extends Entity {

	private float xDirection = 1.5f;
	private float yDirection = 1.5f;
	private float speed = 1f;
	
	private boolean topWallBounce = false;
	private boolean bottomWallBounce = false;
	
	private Random random;

	/**
	 * @param xPosition	A float setting the ball's xPosition.
	 * @param yPosition	A float setting the ball's yPosition.
	 * @param width		An int setting the ball's width.
	 * @param height	An int setting the ball's height.
	 */
	public Ball(float xPosition, float yPosition, int width, int height) {
		super(xPosition, yPosition, width, height);
		resetBall();
		setBallInitialDirection();
	}

	@Override
	public void tick() {

		// If the ball hits the bottom wall.. 
		if (yPosition >= 400 - height) {

			bottomWallBounce = true;
			topWallBounce = false;

			// If the ball hits the top wall..
		} else if (yPosition <= 0) {

			topWallBounce = true;
			bottomWallBounce = false;
		}

		// If the ball hit the bottom and is moving up..
		if (bottomWallBounce && yDirection > 0) {
			yPosition = yPosition - (yDirection);
			xPosition = xPosition + xDirection;

			// If the ball hit the bottom and is moving down..
		} else if (bottomWallBounce && yDirection < 0) {
			yPosition = yPosition + (yDirection);
			xPosition = xPosition + xDirection;

			// If the ball hit the top and is moving down..
		} else if (topWallBounce && yDirection < 0) {
			yPosition = yPosition - (yDirection);
			xPosition = xPosition + xDirection;

			// If the ball hit the top and is moving up..
		} else if (topWallBounce && yDirection > 0) {

			yPosition = yPosition + (yDirection);
			xPosition = xPosition + xDirection;
			
			// Otherwise the ball moves 'freely'..
		} else {
			xPosition = xPosition + xDirection;
			yPosition = yPosition + yDirection;
		}

	}

	/**
	 * A method to reset the ball's position, direction and speed. 
	 */
	public void resetBall() {
		xPosition = 300;
		yPosition = 200;
		xDirection = 1.5f;
		yDirection = 1.5f;
		speed = 1f;
	}

	/**
	 * A method to set the ball moving in a 'random' direction at the beginning of each round. 
	 */
	public void setBallInitialDirection(){

		random = new Random();
		int r = random.nextInt(4);

		if (r == 0) {
			return;
		} else if (r == 1) {
			
			xDirection = xDirection * -1;
			
		} else if (r == 2) {
			
			yDirection = yDirection * -1;
		} else {
			
			xDirection = xDirection * -1;
			yDirection = yDirection * -1;
		}
	}

	@Override
	public void render(Graphics g) {

		g.fillOval((int) xPosition, (int) yPosition, width, height);
	}

	/**
	 * A method to reverse the direction the ball is traveling (x-horizon only) .
	 * @param direction An int where -1 moves ball right to left, 1 moves ball left to right. 
	 */
	public void reverseDirection(int direction) {
		this.xDirection = direction;
		xDirection = xDirection * speed;
	}

	/**
	 * A method to increment the balls speed. 
	 */
	public void incrementSpeed() {
		speed = speed + .5f;
	}

	// Getter(s)
	
	public float getSpeed() {
		return speed;
	}

}
