package game_rhs16;

/**
 * Store data for possible obstructions in the game's world mode
 * 
 * @author Robert H. Steilberg II
 */
public class House {
	private double topEdge;
	private double bottomEdge;
	private double leftEdge;
	private double rightEdge;

	/**
	 * Initialize variables for Battle
	 * 
	 * @param top y value of top edge of house
	 * @param bottom y value of bottom edge of house
	 * @param left x value of left edge of house
	 * @param right x value of right edge of house
	 */
	public House(double top, double bottom, double left, double right) {
		topEdge = top;
		bottomEdge = bottom;
		leftEdge = left;
		rightEdge = right;
	}

	/**
	 * Determine if a house is obstructing a sprite
	 * 
	 * @param dir direction in which the sprite is pointed
	 * @param x horizontal location of sprite
	 * @param y vertical location of sprite
	 * @return true if house obstructs player, false otherwise
	 */
	public boolean isObstruction(int dir, double x, double y) {
		if (dir == 1) { // up
			return y == bottomEdge && x < leftEdge && x > rightEdge;
		}
		if (dir == 2) { // down
			return y == topEdge && x < leftEdge && x > rightEdge;
		}
		if (dir == 3) { // left
			return x == rightEdge && y > bottomEdge && y < topEdge;
		}
		if (dir == 4) { // right
			return x == leftEdge && y < topEdge && y > bottomEdge;
		}
		return false;
	}
}
