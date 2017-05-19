import java.awt.Color;
import java.awt.Graphics;

/**
 *  A <code>Goal</code> represents anything in
 * Mater Tua that the player must touch to move on
 * to the next level.
 * 
 * @author eugenia
 *
 */

public class Goal extends Obstacle
{
	private boolean status;
	
	/**
	 * Creates a Goal object. Its "hitbox" used for collision detection has
	 * its top left corner at (x, y), and its width is 10 pixels.
	 * @param x x coordinate of hitbox's top left corner
	 * @param y y coordinate of hitbox's top left corner
	 */
	public Goal(int x, int y)
	{
		super(x,y);
	}
	
	/**
	 * Returns this Goal's status (true = complete, false = incomplete)
	 */
	public boolean getStatus()
	{
		return status;
	}
	
	/**
	 * Sets this Goal's status to the given boolean.
	 * @param status this Goal's new status
	 */
	public void setStatus(boolean status)
	{
		this.status = status;
	}
	
	/**
	 * Returns true if the specified object is a Goal 
	 * (for level completion determination purposes), false otherwise.
	 * @param o the other Object this Goal will be compared to
	 */
	public boolean equals(Object o)
	{
		if (o instanceof Goal) return true;
		else return false;
	}
	
	/**
	 * Draws this Goal.
	 * @param g the Graphics object used to draw the Goal. Must not be null. 
	 */
	public void draw(Graphics g)
	{
		g.setColor(new Color(255, 247, 0));
		g.fillRect(getHitbox().x, getHitbox().y, WIDTH, WIDTH);
		g.setColor(new Color(255, 208, 0));
		g.drawRect(getHitbox().x - 2, getHitbox().y - 2, WIDTH + 4, WIDTH + 4);
		g.setColor(new Color(255, 162, 0));
		g.drawRect(getHitbox().x - 4, getHitbox().y - 4, WIDTH + 8, WIDTH + 8);
	}
}
