package game;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Initialize intro scene and provide segues into other game modes
 * 
 * @author Robert H. Steilberg II
 */
public class Intro {
	private static final String TITLE = "Welcome to Pokemon Duke Blue!";
	private static final String INSTRUCTIONS = "Battle your way through each of the four DORM LEADERS until you\nbattle Duke's POKEMON CHAMPION: COACH K! Move your sprite\nwith the arrow keys, and interact with characters using the Z key.";
	private Scene myScene;
	private Group myRoot = new Group();
	private ImageView myIntro;

	/**
	 * Get the window's title
	 * 
	 * @return the window's title
	 */
	public String getTitle() {
		return TITLE;
	}

	/**
	 * Add instructions to a text box and prompt to start the game
	 */
	private void createInstructions() {
		Text instructions = new Text(10, 360, INSTRUCTIONS);
		instructions.setFont(Font.font("Verdanda", 12));
		myRoot.getChildren().add(instructions);
		Text t = new Text(155, 170, "PRESS SPACE");
		t.setFont(Font.font("Verdanda", 15));
		myRoot.getChildren().add(t);
	}

	/**
	 * Create the intro scene
	 */
	private void createIntro() {
		Image splashScreen = new Image(getClass().getClassLoader().getResourceAsStream("intro.png"));
		myIntro = new ImageView(splashScreen);
		myIntro.setX(-30.0);
		myRoot.getChildren().add(myIntro);
	}

	/**
	 * Initialize intro stage
	 * 
	 * @param width
	 *            the width of the window
	 * @param height
	 *            the height of the window
	 * @return the scene
	 */
	public Scene init(int width, int height) {
		myScene = new Scene(myRoot, width, height, Color.WHITE);
		createIntro();
		createInstructions();
		return myScene;
	}
}
