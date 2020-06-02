/**
 * Player - A blueprint for a PONG player. 
 */
package Entities;

import java.awt.Graphics;

import Pong.PongGame;

public class Player extends Entity {

	private int score = 0;
	private float playerSpeed = 6f;
	
	private boolean isPlayer01 = true;
	private boolean isLastToHitBall = false;
	private boolean isSlow = false;

	protected PongGame pongGame;

	/**
	 * A constructor for Player.
	 * @param pongGame	
	 * @param xPosition	A float setting the player's xPosition.
	 * @param yPosition	A float setting the player's yPosition.
	 * @param width		An int setting the player's width.
	 * @param height	An int setting the player's height.
	 */
	public Player(PongGame pongGame, float xPosition, float yPosition, int width, int height) {
		super(xPosition, yPosition, width, height);
		this.pongGame = pongGame;
	}

	@Override
	public void tick() {

		// If you are player one.. 
		if (isPlayer01) {
			
			// If you press down..
			if (pongGame.getKeyManager().down) {
				
				// Add to the player's yPosition (results in downward movement)
				yPosition += playerSpeed;
				
				// If you press up
			} else if (pongGame.getKeyManager().up) {
				
				// Subtract from the player's yPosition (results in upward movement)
				yPosition -= playerSpeed;
			}
		} else {
			
			// If you are player two.. 
			if (pongGame.getKeyManager().downP2) {
				yPosition += playerSpeed;
			} else if (pongGame.getKeyManager().upP2) {
				yPosition -= playerSpeed;
			}
		}

		// If your height is great than the screen height..
		if (yPosition + height > 400) {
			
			// Set player position so that then cannot move out of the screen.
			yPosition = 400 - height;
		}

		if (yPosition < 0) {
			yPosition = 0;
		}
	}

	@Override
	public void render(Graphics g) {
		g.fillRoundRect((int) xPosition, (int) yPosition, width, height, 0, 0);
	}

	/**
	 * A method to increment the player's score by 1. 
	 */
	public void incrementPlayerScore() {
		score++;
	}

	
	// Setters, Getters
	
	public int getPlayerScore() {
		return score;
	}

	public void setIsPlayer01(boolean player) {
		this.isPlayer01 = player;
	}

	public void setPlayerSpeed(float playerSpeed) {
		this.playerSpeed = playerSpeed;
	}

	public boolean getIsPlayerSlow() {
		return isSlow;
	}

	public void setIsSlow(boolean bool) {
		this.isSlow = bool;
	}

	public void setIsLastToHitBall(boolean bool) {
		this.isLastToHitBall = bool;
	}

	public boolean getIsLastToHitBall() {
		return isLastToHitBall;
	}

}
