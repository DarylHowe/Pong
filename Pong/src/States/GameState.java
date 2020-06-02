/**
 * GameState - A state for the main game screen. 
 */

package States;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
import Entities.Ball;
import Entities.Player;
import Entities.SlowPlayerPowerUp;
import Music.Music;
import Pong.PongGame;

public class GameState extends State {

	private int tickCounter;
	
	private int counter, counterP2;

	private Player player01, player02;
	private Ball ball;
	
	private SlowPlayerPowerUp slowPlayer;
	
	//private Font font = new Font("Sans Serif", Font.BOLD, 15);
	
	private Random random;
	private Music music;

	/**
	 * A constructor for GameState. 
	 * @param pongGame
	 * @param width
	 * @param height
	 */
	public GameState(PongGame pongGame, int width, int height) {
		super(pongGame, width, height);
		
		// Create player 01 and player 02.
		player01 = new Player(pongGame, 50, 20, 10, 60);
		player02 = new Player(pongGame, width - 60, 20, 10, 60);
		
		// Set which player is player 01.
		player01.setIsPlayer01(true);
		player02.setIsPlayer01(false);

		// Create a ball. 
		ball = new Ball(width / 2, height / 2, 10, 10);

		// Create power up. 
		slowPlayerPowerUpRandomSpawn();
		
		music = new Music();
	}


	/**
	 * A method to create a 'SlowPlayerPowerUp' power up at a random location. 
	 */
	private void slowPlayerPowerUpRandomSpawn() {

		random = new Random();
		int randomX, randomY;
		randomX = random.nextInt(430);
		randomY = random.nextInt(390);

		slowPlayer = new SlowPlayerPowerUp(randomX + 70, randomY, 10, 10);
	}
	
	/**
	 * A method to a change the location of the 'SlowPlayerPowerUp'. Note it will only change if the methods randomly chosen number = 1.  
	 */
	private void possibleChangePowerUpLocation() {
		
		random = new Random();
		int r = random.nextInt(5);
		if (r == 1) {
			slowPlayerPowerUpRandomSpawn();
		}
	}

	@Override
	public void tick() {
		
		player01.tick();
		player02.tick();
		ball.tick();
		slowPlayer.tick();

		Rectangle player01Rect = player01.getEntityBounds();
		Rectangle player02Rect = player02.getEntityBounds();
		Rectangle ballRect = ball.getEntityBounds();
		Rectangle slowPlayerBounds = slowPlayer.getEntityBounds();

		// If player01 hits the ball..
		if (ballRect.intersects(player01Rect)) {
			
			music.playMusic("Player01_Hit.wav");
			
			ball.reverseDirection(1);
			ball.incrementSpeed();

			player01.setIsLastToHitBall(true);
			player02.setIsLastToHitBall(false);
			
			possibleChangePowerUpLocation();
		}

		// If player02 hits the ball..
		if (ballRect.intersects(player02Rect)) {
						
			music.playMusic("Player02_Hit.wav");

			ball.reverseDirection(-1);
			ball.incrementSpeed();

			player01.setIsLastToHitBall(false);
			player02.setIsLastToHitBall(true);

			possibleChangePowerUpLocation();
		}

		// If player 02 wins the round..
		if (ball.getXPosition() <= 0) {
			player02.incrementPlayerScore();
			ball.setBallInitialDirection();
			ball.resetBall();

			resetPlayers();
			
			slowPlayerPowerUpRandomSpawn();
		}

		// If player 01 wins the round..
		if (ball.getXPosition() >= 600) {
			player01.incrementPlayerScore();
			ball.setBallInitialDirection();
			ball.resetBall();

			resetPlayers();

			slowPlayerPowerUpRandomSpawn();
		}

		// If the ball hits the power up.. 
		if (ballRect.intersects(slowPlayerBounds)) {

			music.playMusic("Power_Up.wav");
			
			// If player 01 was last to touch the ball .IE Wins the power up..
			if (player01.getIsLastToHitBall()) {

				counterP2 = tickCounter + 200;
				
				// Slow down player 02
				// Set to isSlow
				player02.setPlayerSpeed(1);
				player02.setIsSlow(true);

				slowPlayerPowerUpRandomSpawn();
			} else {

				counter = tickCounter + 200;
				player01.setPlayerSpeed(1);
				player01.setIsSlow(true);

				slowPlayerPowerUpRandomSpawn();
			}
		}

		// If player 02 is currently slow IE player 01 just won power up..
		if (player02.getIsPlayerSlow()) {

			// After 200 frames..
			if (counterP2 <= tickCounter) {
				
				// Reset player 02 speed.
				// Set player 02 to not slow
				player02.setPlayerSpeed(6);
				player02.setIsSlow(false);
			}
		}

		if (player01.getIsPlayerSlow()) {

			if (counter <= tickCounter) {
				player01.setPlayerSpeed(6);
				player01.setIsSlow(false);
			}
		}
		tickCounter++;
	}
	
	
	/**
	 * A method to reset the player's states after each round. 
	 */
	public void resetPlayers() {
		
		player01.setIsLastToHitBall(false);
		player02.setIsLastToHitBall(false);
		player01.setPlayerSpeed(6);
		player02.setPlayerSpeed(6);
		player01.setIsSlow(false);
		player02.setIsSlow(false);
	}

	@Override
	public void render(Graphics g) {

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);

		g.setColor(Color.WHITE);

		// If player 01 is slow..
		if (player01.getIsPlayerSlow()) {
			
			// Draw player 01 in red.
			g.setColor(Color.RED);
		} else {
			
			// Otherwise draw in white. 
			g.setColor(Color.WHITE);
		}

		player01.render(g);

		if (player02.getIsPlayerSlow()) {
			g.setColor(Color.RED);
		} else {
			g.setColor(Color.WHITE);
		}

		player02.render(g);

		g.setColor(Color.WHITE);

		g.drawLine(width / 2, 0, width / 2, 400);

		g.setColor(Color.GREEN);
		ball.render(g);

		g.setColor(Color.GREEN);

		g.setColor(Color.WHITE);
		slowPlayer.render(g);

		/*
		 * g.setFont(font); g.drawString("PLAYER 01: " +
		 * Integer.toString(player01.getPlayerScore()), width/2 - 200, 50);
		 * g.drawString("PLAYER 02: " + Integer.toString(player02.getPlayerScore()),
		 * width/2 + 100, 50);
		 */
	}
}
