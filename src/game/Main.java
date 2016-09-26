package game;

import java.io.File;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Initialize the game, start game engine, and call all helper classes
 * Dependencies: Intro.java, Game.java, Cheat.java
 * 
 * @author Robert H. Steilberg II
 */
public class Main extends Application {
	public static final int SIZE = 400;
	public static final int FRAMES_PER_SECOND = 60;
	public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	private Intro myIntro;
	private Game myGame;
	private Cheat myCheat;
	private MediaPlayer myMediaPlayer;

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
	 * Set the cheat instruction panel
	 * 
	 * @param s
	 *            the stage
	 */
	private void setCheatScene(Stage s) {
		myCheat = new Cheat();
		s.setTitle(myCheat.getTitle());
		Scene cheatScene = myCheat.init(SIZE, SIZE);
		s.setScene(cheatScene);
		s.show();
		playMusic("cheat.mp3");
		cheatScene.setOnKeyPressed(e -> handleInput(e, s));
	}

	/**
	 * Set the scene for overworld gameplay
	 * 
	 * @param s
	 *            the stage
	 */
	private void setGameScene(Stage s) {
		myGame = new Game(s);
		s.setTitle(myGame.getTitle());
		Scene gameScene = myGame.init(SIZE, SIZE);
		s.setScene(gameScene);
		s.show();
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> myGame.step(SECOND_DELAY));
		Timeline animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}

	/**
	 * Handle user input to the keyboard
	 * 
	 * @param e
	 *            the user's input event
	 * @param s
	 *            the stage
	 */
	private void handleInput(KeyEvent e, Stage s) {
		String input = "";
		input = e.getCode().toString();
		if (input.equals("SPACE")) {
			myMediaPlayer.stop(); // change background music
			setGameScene(s);
		}
		if (input.equals("C")) {
			myMediaPlayer.stop(); // change background music
			setCheatScene(s);
		}
		if (input.equals("B")) {
			myMediaPlayer.stop(); // change background music
			playMusic("battle.mp3");
			Battle battle = new Battle(s);
			Scene battleScene = battle.init(SIZE, SIZE);
			s.setScene(battleScene);
			s.show();
		}
	}

	/**
	 * Setup the window and initiate intro screen
	 */
	@Override
	public void start(Stage s) {
		s.setResizable(false);
		myIntro = new Intro();
		s.setTitle(myIntro.getTitle());
		Scene intro = myIntro.init(SIZE, SIZE);
		playMusic("menu.mp3");
		intro.setOnKeyPressed(e -> handleInput(e, s));
		s.setScene(intro);
		s.show();
	}

	/**
	 * Start the game
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
