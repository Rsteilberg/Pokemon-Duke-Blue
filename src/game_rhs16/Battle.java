package game_rhs16;

import java.io.File;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**-
 * Initialize the battle mode and handle battle gameplay
 * 
 * @author Robert H. Steilberg II
 */
public class Battle {
	public static final String TITLE = "Pokemon Battle: PLAYER vs. COACH K!";
	public static final int SIZE = 400;
	public static final int FRAMES_PER_SECOND = 60;
	public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	private static final int PLAYER_POWER = 6;
	private static final int OPPONENT_POWER = 5;
	private static final String INSTRUCTIONS = "Press Z to attack your opponent's Pokemon. Hurry,\nor else they'll deplete your Pokemon's HP first!";
	private static final String WIN_TEXT = "CONGRATULATIONS! You have become\nDUKE UNIVERSITY'S new POKEMON MASTER!";
	private static final String LOSE_TEXT = "You have FAILED to defeat Coach K!\nTry again next time!";
	private Stage myStage;
	private Scene myScene;
	private Group myRoot = new Group();
	private Text myText;
	private ImageView myPlayerPow;
	private ImageView myOpponentPow;
	private String myInput;
	private Text myPlayerHP;
	private Text myOpponentHP;
	private long myMillis;
	private int playerHP = 140;
	private int opponentHP = 200;
	private boolean myInvincibility = false;
	private boolean gameOver;

	/**
	 * Initialize variables for Battle
	 * 
	 * @param s the stage
	 */
	public Battle(Stage s) {
		myStage = s;
	}

	/**
	 * Get the window's title
	 * 
	 * @return the window's title
	 */
	public String getTitle() {
		return TITLE;
	}

	/**
	 * Change scene to losing game over scene
	 */
	private void setGameLostScene() {
		myStage.setTitle("YOU LOSE!");
		Image loseImage = new Image(getClass().getClassLoader().getResourceAsStream("lose.jpg"));
		ImageView loseScene = new ImageView(loseImage);
		loseScene.setFitHeight(600);
		loseScene.setFitWidth(600);
		loseScene.setPreserveRatio(true);
		myRoot.getChildren().add(loseScene);
		displayText(LOSE_TEXT);
	}
	
	/**
	 * Change scene to winning game over scene
	 */
	private void setGameWonScene() {
		myStage.setTitle("Congratulations! You are the POKEMON CHAMPION!");
		Image winImage = new Image(getClass().getClassLoader().getResourceAsStream("win.jpg"));
		ImageView winScene = new ImageView(winImage);
		winScene.setFitHeight(450);
		winScene.setFitWidth(450);
		winScene.setPreserveRatio(true);
		myRoot.getChildren().add(winScene);
		displayText(WIN_TEXT);
	}

	/**
	 * End the battle and call the game over screen
	 * 
	 * @param won true if player won, false otherwise
	 */
	private void endBattle(boolean won) {
		gameOver = true; // end opponent's attacks
		if (won) {
			setGameWonScene();
		} else {
			setGameLostScene();
		}
	}
	
	/**
	 * Add the opponent's Pokemon to the battle scene
	 */
	private void addOpponentPokemon() {
		Image NPCPokemonImage = new Image(getClass().getClassLoader().getResourceAsStream("opponentPokemon.png"));
		ImageView NPCPokemon = new ImageView(NPCPokemonImage);
		NPCPokemon.setFitHeight(210);
		NPCPokemon.setFitWidth(210);
		NPCPokemon.setPreserveRatio(true);
		NPCPokemon.setX(200.0);
		NPCPokemon.setY(30.0);
		myRoot.getChildren().add(NPCPokemon);
	}

	/**
	 * Add the player's Pokemon to the battle scene
	 */
	private void addPlayerPokemon() {
		Image userPokemonImage = new Image(getClass().getClassLoader().getResourceAsStream("playerPokemon.png"));
		ImageView userPokemon = new ImageView(userPokemonImage);
		userPokemon.setFitHeight(180);
		userPokemon.setFitWidth(180);
		userPokemon.setPreserveRatio(true);
		userPokemon.setX(20.0);
		userPokemon.setY(150.0);
		myRoot.getChildren().add(userPokemon);
	}

	/**
	 * Display specified string in text box
	 * 
	 * @param text the specified String to be displayed
	 */
	private void displayText(String text) {
		myRoot.getChildren().remove(myText); // get rid of any previous text
		myText = new Text(10, 360, text);
		myText.setFont(Font.font("Verdana", 15));
		myRoot.getChildren().add(myText);
	}
	
	/**
	 * Update the specified player's HP value according to attacks made
	 * 
	 * @param damageOpponent true if opponent should be damaged, false if player
	 *                       should be damaged
	 */
	private void updateHP(boolean damageOpponent) {
		if (damageOpponent) {
			myRoot.getChildren().remove(myOpponentHP); // get rid of old HP
														// value
			opponentHP -= PLAYER_POWER;
			myOpponentHP = new Text(40, 55, Integer.toString(opponentHP) + " HP");
			myOpponentHP.setFont(Font.font("Verdana", 30));
			myRoot.getChildren().add(myOpponentHP);
			if (opponentHP < 0) { // player won
				endBattle(true);
			}
		} else { // damage player
			myRoot.getChildren().remove(myPlayerHP); // get rid of old HP value
			playerHP -= OPPONENT_POWER;
			myPlayerHP = new Text(250, 300, Integer.toString(playerHP) + " HP");
			myPlayerHP.setFont(Font.font("Verdana", 30));
			myRoot.getChildren().add(myPlayerHP);
			if (playerHP < 0) { // opponent won
				endBattle(false);
			}
		}
	}

	/**
	 * Display the attack on the screen
	 * 
	 * @param height
	 *            of the POW image
	 * @param width
	 *            of the POW image
	 * @param x
	 *            horizontal position of POW image
	 * @param y
	 *            vertical position of POW image
	 * @return appropriately sized and placed POW image
	 */
	private ImageView getPowAttack(int height, int width, int x, int y) {
		Image powImage = new Image(getClass().getClassLoader().getResourceAsStream("pow.png"));
		ImageView pow = new ImageView(powImage);
		pow.setFitHeight(height);
		pow.setFitWidth(width);
		pow.setX(x);
		pow.setY(y);
		return pow;
	}
	
	/**
	 * Play POW attack sound
	 */
	private void playAttackNoise() {
		Media sound = new Media(new File("music/attack.mp3").toURI().toString());
		MediaPlayer player = new MediaPlayer(sound);
		player.play();
	}
	
	/**
	 * Decrement player's HP if invincibility isn't toggled
	 */
	private void attackPlayer() {
		if (!myInvincibility) {
			updateHP(false);
		}
		myPlayerPow = getPowAttack(80, 80, 100, 200);
		myRoot.getChildren().add(myPlayerPow);
	}

	/**
	 * Initialize the initial HP values for the player and opponent
	 */
	private void setHP() {
		myPlayerHP = new Text(250, 300, Integer.toString(playerHP) + " HP");
		myPlayerHP.setFont(Font.font("Verdana", 30));
		myRoot.getChildren().add(myPlayerHP);
		myOpponentHP = new Text(40, 55, Integer.toString(opponentHP) + " HP");
		myOpponentHP.setFont(Font.font("Verdana", 30));
		myRoot.getChildren().add(myOpponentHP);
	}
	
	/**
	 * Set the background image for the battle scene
	 */
	private void setBattleBackground() {
		Image battleImage = new Image(getClass().getClassLoader().getResourceAsStream("battle.png"));
		ImageView battleScene = new ImageView(battleImage);
		battleScene.setFitHeight(330);
		battleScene.setFitWidth(450);
		battleScene.setX(-30.0);
		myRoot.getChildren().add(battleScene);
	}

	/**
	 * Handle user input to the keyboard
	 * 
	 * @param e the user's input event
	 */
	private void handleInput(KeyEvent e) {
		myInput = e.getCode().toString();
		myOpponentPow = getPowAttack(80, 80, 250, 80);
		if (myInput.equals("Z") && !gameOver) {
			playAttackNoise();
			myRoot.getChildren().add(myOpponentPow);
			updateHP(true); // decrement opponent's HP
		}
		if (myInput.equals("I") && !gameOver) { // toggle invincibility
			myInvincibility = !myInvincibility;
		}
	}

	/**
	 * Handle key release
	 * 
	 * @param e the user's input event
	 */
	private void handleRelease(KeyEvent e) {
		myInput = "";
		myRoot.getChildren().remove(myOpponentPow); // don't keep the POW image after attack
	}
	
	/**
	 * Set up and call the step function
	 */
	private void beginStep() {
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
		Timeline animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}

	/**
	 * Step through the gameplay
	 * 
	 * @param elapsedTime
	 */
	public void step(double elapsedTime) {
		long millisCompare = System.currentTimeMillis();
		if (millisCompare - myMillis > 300) { // remove POW image
			myRoot.getChildren().remove(myPlayerPow);
		}
		if (millisCompare - myMillis > 400 && !gameOver) {
			myMillis = millisCompare;
			attackPlayer();
			playAttackNoise();
		}
	}
	
	/**
	 * Initialize battle stage and gameplay
	 * 
	 * @param width the width of the window
	 * @param height the height of the window
	 * @return the scene
	 */
	public Scene init(int width, int height) {
		myStage.setTitle(TITLE);
		myScene = new Scene(myRoot, width, height, Color.WHITE);
		myStage.setScene(myScene);
		myStage.show();
		beginStep();
		setBattleBackground();
		addPlayerPokemon();
		addOpponentPokemon();
		displayText(INSTRUCTIONS);
		setHP();
		myMillis = System.currentTimeMillis();  // keep track of time for opponent's attacks
		myScene.setOnKeyPressed(e -> handleInput(e));
		myScene.setOnKeyReleased(e -> handleRelease(e));
		return myScene;
	}
}
