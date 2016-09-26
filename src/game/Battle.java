package game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Initialize the battle mode and handle battle gameplay and termination
 * 
 * Dependencies: BattleScene.java, BattleEffects.java, GameOver.java
 * 
 * @author Robert H. Steilberg II
 */
public class Battle {
	public static final String TITLE = "Pokemon Battle: PLAYER vs. COACH K!";
	public static final int SIZE = 400;
	public static final int FRAMES_PER_SECOND = 60;
	public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	private static final int OPPONENT_ATTACK_SPEED = 400;
	private static final int PLAYER_HP = 140;
	private static final int OPPONENT_HP = 200;
	private static final int PLAYER_POWER = 6;
	private static final int OPPONENT_POWER = 5;
	private Stage myStage;
	private Timeline myAnimation;
	private ImageView myPlayerPow;
	private ImageView myOpponentPow;
	private Text myPlayerHPdisplay;
	private Text myOpponentHPdisplay;
	private long myMillis;
	private int myPlayerHP = PLAYER_HP;
	private int myOpponentHP = OPPONENT_HP;
	private boolean myInvincibility = false;

	/**
	 * Initialize variables for Battle
	 * 
	 * @param s
	 *            the stage
	 */
	public Battle(Stage s) {
		myStage = s;
	}

	/**
	 * End the battle and call the game over screen
	 * 
	 * @param won
	 *            boolean set true if player wins, false otherwise
	 */
	private void endBattle(Group root, boolean won) {
		GameOver endGame = new GameOver(myStage, SIZE);
		myAnimation.stop();
		if (won) {
			endGame.setGameWonScene();
		} else {
			endGame.setGameLostScene();
		}
	}

	/**
	 * Update the specified player's HP value according to attacks made
	 * 
	 * @param damageOpponent
	 *            boolean set true if opponent should be damaged, false if
	 *            player
	 */
	private void updateHP(Group root, boolean damageOpponent) {
		if (damageOpponent) {
			myOpponentHP -= PLAYER_POWER;
			myOpponentHPdisplay.setText(myOpponentHP + " HP");
			if (myOpponentHP <= 0) { // player won
				endBattle(root, true);
			}
		} else { // damage player
			myPlayerHP -= OPPONENT_POWER;
			myPlayerHPdisplay.setText(myPlayerHP + " HP");
			if (myPlayerHP <= 0) { // opponent won
				endBattle(root, false);
			}
		}
	}

	/**
	 * Decrement player's HP if invincibility isn't toggled
	 */
	private void attackPlayer(Group root, BattleEffects effects) {
		if (!myInvincibility) {
			updateHP(root, false);
		}
		myPlayerPow = effects.getPowAttack(80, 80, 100, 200);
		root.getChildren().add(myPlayerPow);
	}

	/**
	 * Handle user input to the keyboard
	 * 
	 * @param e
	 *            the user's input event
	 */
	private void handleInput(Group root, KeyEvent e, BattleEffects effects) {
		String input = e.getCode().toString();
		myOpponentPow = effects.getPowAttack(80, 80, 250, 80);
		if (input.equals("Z")) {
			effects.playAttackNoise();
			root.getChildren().add(myOpponentPow);
			updateHP(root, true); // decrement opponent's HP
		}
		if (input.equals("I")) { // toggle invincibility
			myInvincibility = !myInvincibility;
		}
	}

	/**
	 * Handle key release
	 * 
	 * @param e
	 *            the user's input event
	 */
	private void handleRelease(Group root, KeyEvent e) {
		// remove POW image after attack
		root.getChildren().remove(myOpponentPow);
	}

	/**
	 * Set up and call the step function
	 */
	private void beginStep(Group root, BattleEffects effects) {
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(root, SECOND_DELAY, effects));
		myAnimation = new Timeline();
		myAnimation.setCycleCount(Timeline.INDEFINITE);
		myAnimation.getKeyFrames().add(frame);
		myAnimation.play();
	}

	/**
	 * Step through the gameplay
	 * 
	 * @param elapsedTime
	 */
	public void step(Group root, double elapsedTime, BattleEffects effects) {
		long millisCompare = System.currentTimeMillis();
		// remove POW image after 1 second
		if (millisCompare - myMillis > OPPONENT_ATTACK_SPEED - 100) {
			root.getChildren().remove(myPlayerPow);
		}
		if (millisCompare - myMillis > OPPONENT_ATTACK_SPEED) {
			myMillis = millisCompare;
			// attack every OPPONENT_ATTACK_SPEED milliseconds
			attackPlayer(root, effects);
			effects.playAttackNoise();
		}
	}

	/**
	 * Initialize battle stage and gameplay
	 * 
	 * @param width
	 *            the width of the window
	 * @param height
	 *            the height of the window
	 * @return the scene
	 */
	public Scene init(int width, int height) {
		myStage.setTitle(TITLE);
		Group root = new Group();
		Scene battleScene = new Scene(root, width, height, Color.WHITE);
		myStage.setScene(battleScene);
		myStage.show();
		BattleScene bScene = new BattleScene();
		bScene.init(root);
		myPlayerHPdisplay = bScene.setPlayerHP(root, myPlayerHP);
		myOpponentHPdisplay = bScene.setOpponentHP(root, myOpponentHP);
		BattleEffects effects = new BattleEffects();
		// keep track of time for opponent's attacks
		myMillis = System.currentTimeMillis();
		beginStep(root, effects);
		battleScene.setOnKeyPressed(e -> handleInput(root, e, effects));
		battleScene.setOnKeyReleased(e -> handleRelease(root, e));
		return battleScene;
	}
}
