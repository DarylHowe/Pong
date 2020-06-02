/**
 * State - an abstract class. A state can be thought of as different screens eg menu, settings, game etc. 
 * Each state all have a certain number of behaviors in common. 
 * For example, they all need a 'tick()' so they can update their variables. 
 * They all need a 'render()' so they update the screen graphics .ie draw to canvas
 */
package States;

import java.awt.Graphics;

import Pong.PongGame;

public abstract class State {

	protected PongGame game;
	protected int width, height;

	/**
	 * A constructor for State.
	 * @param game
	 */	public State(PongGame game, int width, int height) {
		this.game = game;
		this.width = width;
		this.height = height;
	}

	public abstract void tick();

	public abstract void render(Graphics g);

}
