package game;

import java.io.File;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Provide image and sound effects for the battle gameplay
 * 
 * @author Robert H. Steilberg II
 */
public class BattleEffects {

	/**
	 * Play battle attack sound
	 */
	public void playAttackNoise() {
		Media sound = new Media(new File("music/attack.mp3").toURI().toString());
		MediaPlayer player = new MediaPlayer(sound);
		player.play();
	}

	/**
	 * Display the attack visualization on the screen
	 * 
	 * @param height
	 *            of the POW image
	 * @param width
	 *            of the POW image
	 * @param x
	 *            horizontal position of POW image
	 * @param y
	 *            vertical position of POW image
	 * @return correctly sized and placed POW image
	 */
	public ImageView getPowAttack(int height, int width, int x, int y) {
		Image powImage = new Image(getClass().getClassLoader().getResourceAsStream("pow.png"));
		ImageView pow = new ImageView(powImage);
		pow.setFitHeight(height);
		pow.setFitWidth(width);
		pow.setX(x);
		pow.setY(y);
		return pow;
	}
}
