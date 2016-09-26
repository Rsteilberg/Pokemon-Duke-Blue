package game;

/**
 * Store data for map extremities (i.e. edge of navigable world) in the game's
 * overworld mode
 * 
 * @author Robert H. Steilberg II
 */
public class World {
	private double topEdge;
	private double bottomEdge;
	private double leftEdge;
	private double rightEdge;

	/**
	 * Initialize variables for World
	 * 
	 * @param top
	 *            the y position of the top extremity
	 * @param bottom
	 *            the y position of the bottom extremity
	 * @param left
	 *            the x position of the left extremity
	 * @param right
	 *            the x position of the right extremity
	 */
	public World(double top, double bottom, double left, double right) {
		topEdge = top;
		bottomEdge = bottom;
		leftEdge = left;
		rightEdge = right;
	}

	/**
	 * Determine if the player is obstructed by one of the world's extremities
	 * 
	 * @param dir
	 *            the direction of the player
	 * @param x
	 *            the horizontal position of the player
	 * @param y
	 *            the vertical position of the player
	 * @return true if the player is obstructed, false otherwise
	 */
	public boolean isObstruction(int dir, double x, double y) {
		if (dir == 1) { // up
			return y == topEdge;
		}
		if (dir == 2) { // down
			return y == bottomEdge;
		}
		if (dir == 3) { // left
			return x == leftEdge;
		}
		if (dir == 4) { // right
			return x == rightEdge;
		}
		return false;
	}
}