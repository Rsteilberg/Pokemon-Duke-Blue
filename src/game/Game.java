package game;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Handle game engine and overall gameplay with scenes, images, music, input,
 * etc.
 * 
 * Dependencies: Battle.java, House.java, NPC.java, World.java
 * 
 * @author Robert H. Steilberg II
 */
class Game {
	public static final String TITLE = "Pokemon Duke Blue Version";
	public static final int KEY_INPUT_SPEED = 2;
	private static final String WORLDINFO = "Explore the campus to begin your \nPokemon adventure!";
	private static final String BOSSLINE = "I'm COACH K. You'll never defeat me!";
	private static final String TOPLEFTLINE = "I'm the WILSON dorm leader. The best way to\ndefeat COACH K is to hit his Pokemon before it\ncan hit yours!";
	private static final String TOPRIGHTLINE = "I'm the BROWN dorm leader. I'm not giving you\nany help. You're just a stupid freshman!";
	private static final String BOTTOMLEFTLINE = "I'm the BASSETT dorm leader. WOAH! Is that a\nCHARIZARD? Where did you catch that Pokemon?";
	private static final String BOTTOMRIGHTLINE = "I'm the ALSPAUGH dorm leader. I've heard that\nCOACH K has a mega-evolved Pokemon that is\ncrazy strong!";
	private int myStageSize;
	private Stage myStage;
	private Scene myScene;
	private Group myRoot;
	private World myFence;
	private Battle myBattle;
	private Text myText;
	private String myInput;
	private ImageView mySprite;
	private ImageView myWorld;
	private MediaPlayer myMediaPlayer;
	private ArrayList<House> myHouses;
	private HashMap<Integer, NPC> myNPCs;

	/**
	 * Initialize variables for Game
	 * 
	 * @param s
	 *            the stage
	 */
	public Game(Stage s) {
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
	 * Play a noise when an NPC is engaged
	 */
	private void playClick() {
		String musicFile = "music/click.mp3";
		Media sound = new Media(new File(musicFile).toURI().toString());
		MediaPlayer m = new MediaPlayer(sound);
		m.play();
	}

	/**
	 * Play a specified music file
	 * 
	 * @param filename
	 *            the name of the music file to play
	 */
	private void playMusic(String filename) {
		String musicFile = "music/" + filename;
		Media sound = new Media(new File(musicFile).toURI().toString());
		myMediaPlayer = new MediaPlayer(sound);
		myMediaPlayer.play();
	}

	/**
	 * Store id, position, and text lines respective to each NPC
	 */
	private void createNPCs() {
		myNPCs = new HashMap<Integer, NPC>();
		myNPCs.put(1, new NPC(1, -416.0, -64.0, BOSSLINE));
		myNPCs.put(2, new NPC(2, -162.0, -288.0, TOPLEFTLINE));
		myNPCs.put(3, new NPC(3, -670.0, -288.0, TOPRIGHTLINE));
		myNPCs.put(4, new NPC(4, -162.0, -668.0, BOTTOMLEFTLINE));
		myNPCs.put(5, new NPC(5, -670.0, -668.0, BOTTOMRIGHTLINE));
	}

	/**
	 * Store position of each house in the world
	 */
	private void createHouses() {
		myHouses = new ArrayList<House>();
		myHouses.add(new House(-64.0, -288.0, -32.0, -288.0)); // top left
		myHouses.add(new House(-64.0, -288.0, -548.0, -796.0)); // top right
		myHouses.add(new House(-448.0, -668.0, -32.0, -288.0)); // bottom left
		myHouses.add(new House(-448.0, -668.0, -548.0, -796.0)); // bottom right
	}

	/**
	 * Display text in a text box on the screen
	 */
	private void createTextbox() {
		Rectangle r = new Rectangle(400, 70, Color.WHITE);
		r.setX(0);
		r.setY(330);
		myRoot.getChildren().add(r);
		myText = new Text(10, 360, WORLDINFO);
		myText.setFont(Font.font("Verdana", 15));
		myRoot.getChildren().add(myText);
	}

	/**
	 * Create the user sprite and place in the middle of the stage
	 * 
	 * @param width
	 *            the width of the stage
	 * @param height
	 *            the height of the stage
	 */
	private void createPlayer() {
		Image rawSprite = new Image(getClass().getClassLoader().getResourceAsStream("player.png"));
		PixelReader reader = rawSprite.getPixelReader();
		WritableImage player = new WritableImage(reader, 0, 6, 30, 40);
		mySprite = new ImageView(player);
		// place sprite in the middle horizontally
		mySprite.setX(myStageSize / 2 - mySprite.getBoundsInLocal().getWidth() / 2);
		// place sprite in the middle vertically
		mySprite.setY(myStageSize / 2 - mySprite.getBoundsInLocal().getHeight() / 2);
	}

	/**
	 * Create the world
	 */
	private void createWorld() {
		Image world = new Image(getClass().getClassLoader().getResourceAsStream("world.jpg"));
		myWorld = new ImageView(world);
		myWorld.setFitHeight(1252);
		myWorld.setFitWidth(1284);
		myWorld.setPreserveRatio(true);
		myWorld.setX(-416.0); // x position of world map relative to window
		myWorld.setY(-732.0); // y position of world map relative to window
		myRoot.getChildren().add(myWorld);
		myRoot.getChildren().add(mySprite);
		// contain player sprite within world fence
		myFence = new World(-64.0, -752.0, -52.0, -784.0);
	}

	/**
	 * Determine if the sprite is obstructed by something
	 * 
	 * @param dir
	 *            the direction the sprite is pointed in
	 * @param x
	 *            the horizontal position of the map relative to the sprite
	 * @param y
	 *            the vertical position of the map relative to the sprite
	 * @return true if obstructed, false otherwise
	 */
	private boolean obstructed(int dir, double x, double y) {
		boolean result = false;
		for (House house : myHouses) {
			if (house.isObstruction(dir, x, y)) {
				myInput = "";
				playMusic("bump.mp3");
				result = true;
			}
		}
		if (myFence.isObstruction(dir, x, y)) {
			myInput = "";
			playMusic("bump.mp3");
			result = true;
		}
		return result;
	}

	/**
	 * Determine if the sprite is engaging an NPC, and return the NPC's ID if so
	 * 
	 * @param x
	 *            the horizontal position of the map relative to the sprite
	 * @param y
	 *            the vertical position of the map relative to the sprite
	 * @return -1 if no NPC engaged, and the NPC's ID otherwise
	 */
	private int isNPC(double x, double y) {
		for (NPC person : myNPCs.values()) {
			int result = person.isEngaged(person.getID(), myNPCs, x, y);
			if (result != 0) {
				return person.getID();
			}
		}
		return -1;
	}

	/**
	 * Change the sprite image to turn the sprite's "body"
	 * 
	 * @param myRoot
	 *            where the nodes are
	 * @param y
	 *            crop location in sprite image
	 */
	private void turnSprite(Group myRoot, int y) {
		myRoot.getChildren().remove(mySprite);
		Image rawSprite = new Image(getClass().getClassLoader().getResourceAsStream("player.png"));
		PixelReader reader = rawSprite.getPixelReader();
		// crop the sprite image to the sprite's correct orientation (y)
		WritableImage player = new WritableImage(reader, 0, y, 30, 40);
		mySprite = new ImageView(player);
		mySprite.setX(myStageSize / 2 - mySprite.getBoundsInLocal().getWidth() / 2);
		mySprite.setY(myStageSize / 2 - mySprite.getBoundsInLocal().getHeight() / 2);
		myRoot.getChildren().add(mySprite);
	}

	/**
	 * Move the world around the sprite to simulate walking
	 */
	private void updateSprite() {
		double x = myWorld.getX();
		double y = myWorld.getY();
		if (myInput.equals("UP")) {
			if (!obstructed(1, x, y)) {
				myWorld.setY(y + KEY_INPUT_SPEED);
			}
			turnSprite(myRoot, 150);
		}
		if (myInput.equals("RIGHT")) {
			if (!obstructed(4, x, y)) {
				myWorld.setX(x - KEY_INPUT_SPEED);
			}
			turnSprite(myRoot, 102);
		}
		if (myInput.equals("LEFT")) {
			if (!obstructed(3, x, y)) {
				myWorld.setX(x + KEY_INPUT_SPEED);
			}
			turnSprite(myRoot, 54);
		}
		if (myInput.equals("DOWN")) {
			if (!obstructed(2, x, y)) {
				myWorld.setY(y - KEY_INPUT_SPEED);
			}
			turnSprite(myRoot, 6);
		}
		if (myInput.equals("Z")) { // check for engagement with NPC
			myInput = "";
			int id = isNPC(x, y);
			if (id != -1) { // -1 = not an NPC
				playClick();
				myText.setText(myNPCs.get(id).getText());
			}
			if (id == 1) { // boss battle engaged
				myBattle = new Battle(myStage);
				Scene battleScene = myBattle.init(myStageSize, myStageSize);
				myStage.setScene(battleScene);
				myStage.show();
			}
		}
	}

	/**
	 * Handle user input to the keyboard
	 * 
	 * @param e
	 *            the user's input event
	 */
	private void handleInput(KeyEvent e) {
		myInput = e.getCode().toString();
	}

	/**
	 * Handle key release
	 * 
	 * @param e
	 *            the user's input event
	 */
	private void handleRelease(KeyEvent e) {
		myInput = "";
	}

	/**
	 * Step through the gameplay
	 * 
	 * @param elapsedTime
	 */
	public void step(double elapsedTime) {
		updateSprite();
	}

	/**
	 * Initialize game stage and gameplay
	 * 
	 * @param width
	 *            the width of the window
	 * @param height
	 *            the height of the window
	 * 
	 * @return the scene
	 */
	public Scene init(int width, int height) {
		myRoot = new Group();
		myScene = new Scene(myRoot, width, height, Color.WHITE);
		myStageSize = width;
		createPlayer();
		createWorld();
		createHouses();
		createNPCs();
		createTextbox();
		playMusic("world.mp3");
		myScene.setOnKeyPressed(e -> handleInput(e));
		myScene.setOnKeyReleased(e -> handleRelease(e));
		return myScene;
	}
}
