package game;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Handle termination of the gameplay when the battle concludes
 * 
 * @author Robertm H. Steilberg II
 *
 */
public class GameOver {
	private static final String WIN_TEXT = "CONGRATULATIONS! You have become\nDUKE UNIVERSITY'S new POKEMON MASTER!";
	private static final String LOSE_TEXT = "You have FAILED to defeat Coach K!\nTry again next time!";
	private Stage myStage;
	private int mySize;

	public GameOver(Stage stage, int size) {
		myStage = stage;
		mySize = size;
	}

	/**
	 * Display specified string in text box
	 * 
	 * @param root
	 *            is the node group
	 * @param text
	 *            the specified String to be displayed
	 */
	protected void displayText(Group root, String text) {
		Text t = new Text(10, 360, text);
		t.setFont(Font.font("Verdana", 15));
		root.getChildren().add(t);
	}

	/**
	 * Initializes the window for the stage
	 * 
	 * @return the node group of the scene
	 */
	private Group setStageScene() {
		Group root = new Group();
		Scene battleScene = new Scene(root, mySize, mySize, Color.WHITE);
		myStage.setScene(battleScene);
		myStage.show();
		return root;
	}

	/**
	 * Change scene to losing game over scene
	 */
	public void setGameLostScene() {
		myStage.setTitle("YOU LOSE!");
		Group root = setStageScene();
		Image loseImage = new Image(getClass().getClassLoader().getResourceAsStream("lose.jpg"));
		ImageView loseScene = new ImageView(loseImage);
		loseScene.setFitHeight(600);
		loseScene.setFitWidth(600);
		loseScene.setPreserveRatio(true);
		root.getChildren().add(loseScene);
		displayText(root, LOSE_TEXT);
	}

	/**
	 * Change scene to winning game over scene
	 */
	public void setGameWonScene() {
		myStage.setTitle("Congratulations! You are the POKEMON CHAMPION!");
		Group root = setStageScene();
		Image winImage = new Image(getClass().getClassLoader().getResourceAsStream("win.jpg"));
		ImageView winScene = new ImageView(winImage);
		winScene.setFitHeight(450);
		winScene.setFitWidth(450);
		winScene.setPreserveRatio(true);
		root.getChildren().add(winScene);
		displayText(root, WIN_TEXT);
	}
}
