import java.awt.Point;

/**
 * A <code>Node</code> represents a space that 
 * and Actor can walk to, whether the space is 
 * occupied or not. This is used for AI pathfinding.
 */

public class Node extends Point
{
	private int f, g, h;
	private Point start, end;
	
	/**
	 * Creates a Node object at the location (x, y). Note 
	 * 1 unit in the coordinates corresponds to 10 pixels
	 * on the screen.
	 * @param x x coordinate of this node (0 <= x <= 96)
	 * @param y y coordinate of this node (0 <= y <= 54)
	 * @param xStart x coordinate of the AI's starting location (0 <= x <= 96)
	 * @param yStart y coordinate of the AI's starting location (0 <= y <= 54)
	 * @param xEnd x coordinate of the AI's ending location (0 <= x <= 96)
	 * @param yEnd y coordinate of the AI's ending location (0 <= y <= 54)
	 */
	public Node(int x, int y, int xStart, int yStart, int xEnd, int yEnd)
	{
		super(x, y);
		start = new Point(xStart, yStart);
		end = new Point(xEnd, yEnd);
	}
	
	/**
	 * Sets this Node's movement cost from the starting point (g),
	 * its estimated movement cost to the destination (h), and calculates
	 * its new total movement cost.
	 * @param g this Node's new movement cost from the starting point
	 * @param h this Node's new movement cost from the ending point
	 */
	public void setgh(int g, int h)
	{
		this.g = g;
		this.h = h;
		f = g + h;
	}
}
