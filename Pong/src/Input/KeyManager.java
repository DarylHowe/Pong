/**
 * KeyManager - A class to register user inputs on the keyboard. 
 */

package Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//NOTE: Implements KeyListener
public class KeyManager implements KeyListener {

	public boolean up, down, upP2, downP2;
	private boolean[] keys;

	/**
	 * A constructor for KeyManager.
	 */
	public KeyManager() {
		keys = new boolean[256];
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {

		// Put the pressed key id value = true.
		keys[e.getExtendedKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {

		// When a key is not being pressed = false.
		keys[e.getExtendedKeyCode()] = false;
	}

	public void tick() {

		// Link boolean variable with a keyboard key.

		up = keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_S];

		upP2 = keys[KeyEvent.VK_UP];
		downP2 = keys[KeyEvent.VK_DOWN];
	}
}
