/**
 * MenuState - A state for the menu screen. 
 */

package States;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import Entities.Ball;
import Entities.Player;
import Pong.PongGame;

public class MenuState extends State {

	private Ball menuBall;
	private Player player01, player02;
	private Rectangle ballRect;
	private Font font;

	/**
	 * A constructor for the menu state. 
	 * @param game			
	 * @param width
	 * @param height
	 */
	public MenuState(PongGame game, int width, int height) {
		super(game, width, height);
		menuBall = new Ball(100, 100, 10, 10);

		player01 = new Player(game, 50, 0, 10, 600);
		player02 = new Player(game, width - 60, 0, 10, 600);
		font = new Font("Sans Serif", 1, 22);
	}

	@Override
	public void tick() {
		menuBall.tick();
		player01.tick();
		player02.tick();

		Rectangle player01Rect = player01.getEntityBounds();
		Rectangle player02Rect = player02.getEntityBounds();
		ballRect = menuBall.getEntityBounds();
		
		if (ballRect.intersects(player01Rect)) {
			menuBall.reverseDirection(1);
		}

		if (ballRect.intersects(player02Rect)) {
			menuBall.reverseDirection(-1);
		}

		if (menuBall.getXPosition() <= 0) {
			menuBall.setBallInitialDirection();
			menuBall.resetBall();
		}

		if (menuBall.getXPosition() >= 600) {
			menuBall.setBallInitialDirection();
			menuBall.resetBall();
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);

		g.setColor(Color.WHITE);
		g.fillRect(width / 2 - 100, height / 2 - 50, 200, 50);

		g.setColor(Color.BLACK);
		g.setFont(font);
		g.drawString("PLAY PONG", (width / 2 - 62), height / 2 - 18);

		g.setColor(Color.WHITE);
		player01.render(g);
		player02.render(g);

		// If ball hovers over play button.. 
		if (ballRect.intersects(new Rectangle((width / 2 - 100), height / 2 - 50, 200, 50))) {
			
			// Draw ball in black.
			g.setColor(Color.BLACK);
		} else {
			g.setColor(Color.WHITE);
		}
		menuBall.render(g);
	}
}
