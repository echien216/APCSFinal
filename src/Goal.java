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
	 * Draws this Goal.
	 * @param g the Graphics object used to draw the Goal. Must not be null. 
	 */
	public void draw(Graphics g)
	{
		g.setColor(Color.YELLOW);
		g.fillRect(this.getHitbox().x, this.getHitbox().y, WIDTH, WIDTH);

	}
}
