import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

/** An <code>Obstacle<\code> represents anything in
 * Mater Tua that <code>Actors<\code> cannot
 * move through.
 * @author eugenia
 */

public class Obstacle implements Solid
{
	private Rectangle hitbox;
	public static final int WIDTH = 10;
	
	/**
	 * Creates an Obstacle object. Its "hitbox" used for collision detection has
	 * its top left corner at (x, y), and its width is 10 pixels.
	 * @param x x coordinate of hitbox's top left corner
	 * @param y y coordinate of hitbox's top left corner
	 */
	public Obstacle(int x, int y)
	{
		hitbox = new Rectangle(x, y, WIDTH, WIDTH);
	}
	
	/**
	 * Returns this Obstacle's hitbox used for
	 * collision detection.
	 */
	public Rectangle getHitbox()
	{
		return hitbox;
	}
	
	/**
	 * Draws this Obstacle.
	 * @param g the Graphics object used to draw the Obstacle. Must not be null. 
	 */
	public void draw(Graphics g)
	{
		g.setColor(new Color(204, 128, 47));
		g.fillRect(hitbox.x, hitbox.y, WIDTH, WIDTH);
	}

	/**
	 * Does nothing because Obstacles are not able to move.
	 * @param dir direction in which this Solid should move horizontally (-1 = left, 1 = right)
	 */
	public void moveHorizontal(int dir, ArrayList<Solid> solids) 
	{
		
	}

	/**
	 * Does nothing because Obstacles are not able to move.
	 * @param dir direction in which this Solid should move horizontally (-1 = left, 1 = right)
	 */
	public void moveVertical(int dir, ArrayList<Solid> solids) 
	{
		
	}

	/**
	 * Does nothing.
	 */
	public void act() 
	{
		
	}
	
	
}
