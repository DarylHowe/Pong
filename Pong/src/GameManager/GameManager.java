/**
 * GameManager - A class to control the game's state. Ie menu screen, game in play. 
 */
package GameManager;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Pong.PongGame;
import States.State;

//NOTE: Implements MosueListener
public class GameManager implements MouseListener {

	private static State currentState = null;
	private PongGame pongGame;
	private int mouseX, mouseY;

	/**
	 * A constructor for GameManager.
	 * @param game A PongGame object to pass. 	
	 */
	public GameManager(PongGame pongGame) {
		this.pongGame = pongGame;
	}


	/**
	 * A method which is called when the user clicks on screen.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {

		mouseX = e.getX();
		mouseY = e.getY();

		// If the user clicks on the 'Play' button area..
		if (mouseX < 400 && mouseX > 200 && mouseY > 152 && mouseY < 205) {
			pongGame.setToGameState();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
	
	// Setters Getters

	public int getMouseX() {
		return mouseX;
	}

	public int getMouseY() {
		return mouseY;
	}
	
	public static void setState(State state) {
		currentState = state;
	}

	public static State getState() {
		return currentState;
	}

}
