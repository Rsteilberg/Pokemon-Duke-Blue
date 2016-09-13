package game_rhs16;

import java.util.HashMap;

/**
 * Store data for non-player-characters (NPCs) in the game's world mode
 * 
 * @author Robert H. Steilberg II
 */
public class NPC {
	private int id;
	private double posX;
	private double posY;
	private String text;

	/**
	 * Initialize variables for NPC
	 * 
	 * @param idNumber the id of the NPC
	 * @param x the horizontal position of the NPC
	 * @param y the vertical position of the NPC
	 * @param s the stage
	 */
	public NPC(int idNumber, double x, double y, String s) {
		id = idNumber;
		posX = x;
		posY = y;
		text = s;
	}
	
	/**
	 * Get the NPC's ID
	 * @return the NPC's int ID
	 */
	public int getID() {
		return id;
	}

	/**
	 * Get the NPC's text
	 * @return the NPC's String text
	 */
	public String getText() {
		return text;
	}
	
	/**
	 * Determine if NPC is being engaged by the player's sprite
	 * 
	 * @param id the id of the NPC
	 * @param NPCs map of all NPCs and their associated data
	 * @param x the horizontal position of the player's sprite
	 * @param y the vertical position of the player's sprite
	 * @return id of NPC if engaged, 0 otherwise
	 */
	public int isEngaged(int id, HashMap<Integer, NPC> NPCs, double x, double y) {
		for (NPC person : NPCs.values()) {
			if (y == posY && x <= posX + 12 && x >= posX - 12) {
				return person.id;
			}
		}
		return 0;
	}
}
