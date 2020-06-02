/**
 * PONG - The traditional pong game with menu screen.  
 @author Daryl Howe
 */
package Pong;

import Music.Music;

public class PongLauncher {

	public static void main(String args[]) {

		PongGame pong = new PongGame("Pong", 600, 400);
		pong.start();

		String filePath = "Not Made For Humans - Monsters of Time (Original) .wav";

		Music music = new Music();
		music.playMusic(filePath);
	}
}
