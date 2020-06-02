/**
 * SlowPlayerPowerUp - An in-game power up which reduces the speed of a player when intersected.  
 */
package Entities;

import java.awt.Graphics;

public class SlowPlayerPowerUp extends Entity {

	private boolean isActive = false;

	public SlowPlayerPowerUp(float xPosition, float yPosition, int width, int height) {
		super(xPosition, yPosition, width, height);
	}

	@Override
	public void tick() {
	}

	@Override
	public void render(Graphics g) {
		g.fillRect((int) xPosition, (int) yPosition, width, height);
	}

	public void setIsActive(boolean bool) {
		this.isActive = bool;
	}

	public boolean getIsActive() {
		return isActive;
	}

}
