import java.awt.Point;

/**
 * A <code>Node</code> represents a space that 
 * and Actor can walk to, whether the space is 
 * occupied or not. This is used for AI pathfinding.
 */

public class Node extends Point
{
	private int f, g, h;
	
	/**
	 * Creates a Node object at the location (x, y) with a movement
	 * cost of g. Note that 1 unit in the coordinates corresponds to 10 pixels
	 * on the screen.
	 * @param x x coordinate of this node (0 <= x <= 96)
	 * @param y y coordinate of this node (0 <= y <= 54)
	 * @param g this Node's base movement cost
	 */
	public Node(int x, int y, int g)
	{
		super(x, y);
		this.g = g;
		f = g + h;
	}
	
	/**
	 * Returns this Node's total movement cost. 
	 */
	public int getf()
	{
		return f;
	}
	
	/**
	 * Returns this Node's base movement cost. 
	 */
	public int getg()
	{
		return g;
	}
	
	/**
	 * Returns this Node's estimated movement cost. 
	 */
	public int geth()
	{
		return h;
	}
	
	/**
	 * Sets this Node's base and estimated 
	 * movement costs to the given values.
	 * @param g this Node's new base movement cost.
	 * @param h this Node's new estimated movement cost.
	 */
	public void setgh(int g, int h)
	{
		this.g = g;
		this.h = h;
		f = g + h;
	}
	
	/**
	 * Returns true if the given Node has the same (x, y)
	 * coordinate as this Node, false otherwise.
	 * @param n other Node to compare this Node to
	 */ 
	public boolean equals(Node n)
	{
		return super.equals(n);
	} 

	/**
	 * Returns this Node as a string in the form
	 * Node[x,y] cost: [total movement cost]
	 */
	public String toString()
	{
		return super.toString() + " cost:" + f;
	}
}
