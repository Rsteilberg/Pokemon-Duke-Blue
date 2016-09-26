package game;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Initialize images and text for the battle scene
 * 
 * @author Robert H. Steilberg II
 */
public class BattleScene {
	private static final String INSTRUCTIONS = "Press Z to attack your opponent's Pokemon. Hurry,\nor else they'll deplete your Pokemon's HP first!";

	/**
	 * Initialize the initial HP value for the player
	 * 
	 * @param root
	 *            the node group
	 * @param playerHP
	 *            the initial player health value
	 * @return a Text object containing the HP value
	 */
	public Text setPlayerHP(Group root, int playerHP) {
		Text myPlayerHP = new Text(250, 300, Integer.toString(playerHP) + " HP");
		myPlayerHP.setFont(Font.font("Verdana", 30));
		root.getChildren().add(myPlayerHP);
		return myPlayerHP;
	}

	/**
	 * Initialize the initial HP value for the opponent
	 * 
	 * @param root
	 *            the node group
	 * @param opponentHP
	 *            the initial opponent health value
	 * @return a Text object containing the HP value
	 */
	public Text setOpponentHP(Group root, int opponentHP) {
		Text myOpponentHP = new Text(40, 55, Integer.toString(opponentHP) + " HP");
		myOpponentHP.setFont(Font.font("Verdana", 30));
		root.getChildren().add(myOpponentHP);
		return myOpponentHP;
	}

	/**
	 * Add the opponent's Pokemon sprite to the battle scene
	 * 
	 * @param root
	 *            the node group
	 */
	private void addOpponentPokemon(Group root) {
		Image NPCPokemonImage = new Image(getClass().getClassLoader().getResourceAsStream("opponentPokemon.png"));
		ImageView NPCPokemon = new ImageView(NPCPokemonImage);
		NPCPokemon.setFitHeight(210);
		NPCPokemon.setFitWidth(210);
		NPCPokemon.setPreserveRatio(true);
		NPCPokemon.setX(200.0); // x position of Pokemon relative to window
		NPCPokemon.setY(30.0); // y position of Pokemon relative to window
		root.getChildren().add(NPCPokemon);
	}

	/**
	 * Add the player's Pokemon sprite to the battle scene
	 * 
	 * @param root
	 *            the node group
	 */
	private void addPlayerPokemon(Group root) {
		Image userPokemonImage = new Image(getClass().getClassLoader().getResourceAsStream("playerPokemon.png"));
		ImageView userPokemon = new ImageView(userPokemonImage);
		userPokemon.setFitHeight(180);
		userPokemon.setFitWidth(180);
		userPokemon.setPreserveRatio(true);
		userPokemon.setX(20.0); // x position of Pokemon relative to window
		userPokemon.setY(150.0); // y position of Pokemon relative to window
		root.getChildren().add(userPokemon);
	}

	/**
	 * Add instructions to the text box on the screen
	 * 
	 * @param root
	 *            the node group
	 */
	public void addInstructions(Group root) {
		Text instructions = new Text(10, 360, INSTRUCTIONS);
		instructions.setFont(Font.font("Verdana", 15));
		root.getChildren().add(instructions);
	}

	/**
	 * Set the background image for the battle scene
	 * 
	 * @param root
	 *            the node group
	 */
	private void setBattleBackground(Group root) {
		Image battleImage = new Image(getClass().getClassLoader().getResourceAsStream("battle.png"));
		ImageView battleScene = new ImageView(battleImage);
		battleScene.setFitHeight(330);
		battleScene.setFitWidth(450);
		battleScene.setX(-30.0); // x position of background relative to window
		root.getChildren().add(battleScene);
	}

	/**
	 * Initialize the battle scene
	 * 
	 * @param root
	 *            the node group
	 */
	public void init(Group root) {
		addInstructions(root);
		setBattleBackground(root);
		addPlayerPokemon(root);
		addOpponentPokemon(root);
	}
}
