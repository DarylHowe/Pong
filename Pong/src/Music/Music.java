/**
 * Music - A class to play sound files during the game. 
 */
package Music;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Music {

	/**
	 * A method to player music at a specific file location.
	 * @param musicLocation A string containing the file name which you wish to play.
	 */
	public void playMusic(String musicLocation) {

		try {
			// Create new file
			File musicPath = new File(musicLocation);

			if (musicPath.exists()) {

				AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
				Clip clip = AudioSystem.getClip();
				clip.open(audioInput);
				clip.start();
				// clip.loop(Clip.LOOP_CONTINUOUSLY);

			} else {
				System.out.println("Cant find file.");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
