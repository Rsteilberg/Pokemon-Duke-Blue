package game_rhs16;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/**
 * Initialize the cheat scene and provide segues into other game modes
 * 
 * @author Robert H. Steilberg II
 */
public class Cheat {
	public static final String TITLE = "Cheat codes: Pokemon Duke Blue";
	private Scene myScene;
	private Group myRoot = new Group();

	public String getTitle() {
		return TITLE;
	}

	/**
	 * Add the cheat scene to the stage
	 */
	private void createCheat() {
		Image cheatScreen = new Image(getClass().getClassLoader().getResourceAsStream("cheat.png"));
		ImageView cheatScene = new ImageView(cheatScreen);
		cheatScene.setX(-24.0);
		myRoot.getChildren().add(cheatScene);
	}

	/**
	 * Initialize cheat stage and gameplay
	 * 
	 * @param width the width of the window
	 * @param height the height of the window
	 * @return the scene
	 */
	public Scene init(int width, int height) {
		myScene = new Scene(myRoot, width, height, Color.WHITE);
		createCheat();
		return myScene;
	}
}
